package cn.vetech.vedsb.jp.service.jpddgl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.cache.BcityCache;
import org.vetech.core.business.cache.VeclassCache;
import org.vetech.core.business.pid.api.avh.Avh;
import org.vetech.core.business.pid.api.avh.AvhParam;
import org.vetech.core.business.pid.api.avh.AvhParser;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.api.pnrpat2.PnrHd;
import org.vetech.core.business.pid.api.rtpnr.PNRParser;
import org.vetech.core.business.pid.api.rtpnr.ParseGjPNR;
import org.vetech.core.business.pid.api.rtpnr.PnrRtParam;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.business.pid.pidbean.AvCabinModel;
import org.vetech.core.business.tag.Function;
import org.vetech.core.business.tag.FunctionCode;
import org.vetech.core.modules.lock.IBillIdentify;
import org.vetech.core.modules.lock.RedisBillLockHandler;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.service.ServiceException;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.Bean2Map;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.dao.jpddgl.JpKhddDao;
import cn.vetech.vedsb.jp.dao.jpddgl.JpKhddYjDao;
import cn.vetech.vedsb.jp.entity.airway.Bairprice;
import cn.vetech.vedsb.jp.entity.jpddgl.JpDdBean;
import cn.vetech.vedsb.jp.entity.jpddgl.JpDdCjrBean;
import cn.vetech.vedsb.jp.entity.jpddgl.JpDdHdBean;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddYj;
import cn.vetech.vedsb.jp.entity.jpddsz.BookOrder;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpDbjpnr;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcPkgPnrServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.vedsb.utils.BookOrderUtil;
import cn.vetech.vedsb.utils.DataUtils;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.web.vedsb.SessionUtils;

@Service
public class JpKhddServiceImpl extends MBaseService<JpKhdd, JpKhddDao>{
	@Autowired
	private JpkhddYjServiceImpl jpkhddYjService;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	@Autowired
	private ProcPkgPnrServiceImpl procPkgPnrServiceImpl;
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpKhddKzServiceImpl jpKhddKzServiceImpl;
	@Autowired
	private BairpriceServiceImpl bairpriceServiceImpl;
	@Autowired
	private JpKhddYjDao jpKhddYjDao;
	
	@Autowired
	private JpPzServiceImpl jppzImpl;
	
	@Autowired
	private RedisBillLockHandler redisBillLockHandler;
	
	/**
	 * 通过订单编号提取订单中的pnr内容、pat内容、航空公司大编码，判断其中一个是否为空，如果为空调用pid的RT指令， 然后更新订单表。
	 * 
	 * 同一个订单只允许一个调用，如果有并发第二个会等待第一个执行完成。
	 * 
	 * 
	 * @param user
	 *            操作用户
	 * @param ddbh
	 *            订单号
	 * @param cjrlx
	 *            乘机人类型
	 * @param sfxyh
	 *            三方协议号
	 * @return
	 */
	public JpKhdd khddRtPnr(Shyhb user, final String ddbh) {
		IBillIdentify ib = new IBillIdentify() {
			@Override
			public String uniqueIdentify() {
				return ddbh;
			}
			@Override
			public int getSingle_expire_time() {
				return 30;
			}
		};
		try {
			redisBillLockHandler.tryLock(ib, 30, TimeUnit.SECONDS);
			JpKhdd t = new JpKhdd();
			t.setDdbh(ddbh);
			t.setShbh(user.getShbh());
			JpKhdd jpKhdd = this.getEntityById(t);
			
			String patnr = jpKhdd.getPatLr();
			String pnrnr = jpKhdd.getPnrLr();
			String cghkgspnr = jpKhdd.getCgHkgsPnr();
			if (StringUtils.isBlank(patnr) || StringUtils.isBlank(pnrnr) || StringUtils.isBlank(cghkgspnr)) {
				JpPz jppz = jppzImpl.getJpPzByShbh(user.getShbh());
				if (jppz == null) {
					throw new ServiceException("没有找到PID请求配置，无法做PAT和RT指令");
				}
				List<JpKhddCjr> cjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, user.getShbh());
				String cjrlx = "1";
				if(CollectionUtils.isNotEmpty(cjrList)){
					cjrlx =  cjrList.get(0).getCjrlx();
				}
				Pnr pnr = DataUtils.getPnrPat(jpKhdd.getCgPnrNo(), cjrlx, jppz, user, "");
				if (pnr != null) {
					// 放到传入的对象中去
					jpKhdd.setPnrLr(pnr.getPnr_lr());
					jpKhdd.setPatLr(pnr.getPat());
					jpKhdd.setCgHkgsPnr(pnr.getBigPnrno());
					jpKhdd.setXsHkgsPnr(pnr.getBigPnrno());

					try {
						JpKhdd _jpKhddBean = new JpKhdd();
						_jpKhddBean.setDdbh(jpKhdd.getDdbh());
						_jpKhddBean.setPnrLr(pnr.getPnr_lr());
						_jpKhddBean.setPatLr(pnr.getPat());
						_jpKhddBean.setCgHkgsPnr(pnr.getBigPnrno());
						_jpKhddBean.setXsHkgsPnr(pnr.getBigPnrno());
						update(_jpKhddBean);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			return jpKhdd;
		} finally {
			redisBillLockHandler.unLock(ib);
		}
	}
	
	
	
	/**
	 * 分页查询改签单和改签单明细
	 * @param jpgqd
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page query(Map<String, Object> paramsMap, String sortType) throws Exception {
		Page page = new Page(Integer.parseInt((String)paramsMap.get("pageNum")), Integer.parseInt((String)paramsMap.get("pageSize")));
		String hbh=VeStr.getValue(paramsMap, "hbh");
		hbh=hbh.replace("*", "");
		if(StringUtils.isNotBlank(hbh)){
			if(hbh.length() == 2 ){
				paramsMap.put("hbh", "");
				paramsMap.put("hkgs", hbh);
			}
		}
		List<Map<String,Object>> jpKhddList = this.getMyBatisDao().query(paramsMap);
		int pagecount = this.getMyBatisDao().getPageCount(paramsMap);
		if(CollectionUtils.isNotEmpty(jpKhddList)){
			List<VeclassCache> cglylist=FunctionCode.getVeclassLb("10014");//10014是采购来源在数据字典中的类别
			List<VeclassCache> zcqdlist=FunctionCode.getVeclassLb("10002");//10002是政策渠道在数据字典中的类别
			for(Map<String,Object> map: jpKhddList){
				int i=0; int j=0;
				for(VeclassCache cgly:cglylist){
					if(cgly.getId().equals(map.get("CGLY"))){
						map.put("CGLYNAME",cgly.getMc());
						i=1;
						break;
					}
				}
				if(i==0){
					map.put("CGLYNAME",map.get("CGLY"));
				}
				for(VeclassCache zcqd:zcqdlist){
					if(StringUtils.isNotBlank(zcqd.getYwmc())){
						if(zcqd.getYwmc().equals(map.get("ZC_QD"))){
							map.put("ZCQDNAME",zcqd.getMc());
							j=1;
							break;
						}
					}
				}
				if(j==0){
					map.put("ZCQDNAME",map.get("ZC_QD"));
				}
			}
			
		}
		page.setList(jpKhddList);
		page.setTotalCount(pagecount);
		return page;
	}
	
	/**
	 * 获取订单详信息
	 */
	public Map<String, Object> detail(Map<String, Object> paramMap) {
		return this.getMyBatisDao().detail(paramMap);
	}
	
	
	/**
	 * <查询待打印订单的状态>
	 * 
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public String findDDYcount(JpKhdd jpKhdd) {
		return getMyBatisDao().findDDYcount(jpKhdd);
	}

	/**
	 * <查询待邮寄订单的状态>
	 * 
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public String findDYJcount(JpKhdd jpKhdd) {
		return getMyBatisDao().findDYJcount(jpKhdd);
	}
	
	/**
	 * <查询已邮寄订单的状态>
	 * 
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public String findYYJcount(JpKhdd jpKhdd) {
		return getMyBatisDao().findYYJcount(jpKhdd);
	}

	/**
	 * <功能详细描述>
	 * 
	 * @param jpKhdd
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @return [参数说明]
	 * 
	 * @return Page [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Page queryPage(JpKhdd jpKhdd, int pageNum, int pageSize,
			String sortType) {
		Page page=new Page(pageNum, pageSize);
		if(jpKhdd!=null&&StringUtils.isNotBlank(jpKhdd.getEndTime())){
			String endtime=jpKhdd.getEndTime()+" 23:59:59";
			jpKhdd.setEndTime(endtime);
		}
		List<Map<String,Object>> list=getMyBatisDao().queryXcdList(jpKhdd,pageNum,pageSize,sortType);
		int totalCount=getMyBatisDao().queryXcdListCount(jpKhdd);
		page.setList(list);
		page.setTotalCount(totalCount);
		return page;
	}
	/**
	 * 根据外部单号（订单编号用数组存放，支持查多个）查航段表数据，用于交票
	 * @param wbdh String
	 * @return
	 */
	public List<JpKhdd> selectJpByWbdh(Map<String,Object> param){
		return this.getMyBatisDao().selectJpByWbdh(param);
	}

	/**
	 * <客户取消邮寄行程单>
	 * 
	 * @param bh
	 * @return [参数说明]
	 * 
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public int cancelYjdd(String bh,String flag) {
		if("1".equals(flag)){//如果是待邮寄中的取消邮寄才删除邮寄单
			jpKhddYjDao.deleteYjByDdbh(bh);
		}
		return this.getMyBatisDao().cancelYjdd(bh);
	}
	
	public List<JpKhdd> getJpKhddForGqApply(Map<String,Object> param) {
		return this.getMyBatisDao().getJpKhddForGqApply(param);
	}
	/**
	 * 通过商户编号和订单编号获取订单信息
	 * @param shbh
	 * @param ddbh
	 * @return
	 */
	public JpKhdd getKhddByDdbh(String shbh,String ddbh) {
		JpKhdd bean = new JpKhdd();
		bean.setDdbh(ddbh);
		bean.setShbh(shbh);
		return getKhddByDdbh(bean);
	}
	
	public JpKhdd getKhddByDdbh(JpKhdd jpKhdd) {
		return this.getMyBatisDao().getKhddByDdbh(jpKhdd);
	}

	/**
	 * <打印快递单后，将订单的邮寄状态改为待邮寄或 邮寄之后将邮寄状态改为已邮寄>
	 * 
	 * @param yjzt [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public int updateYjzt(JpKhddYj jpKhddYj) {
		return this.getMyBatisDao().updateYjzt(jpKhddYj);
	}

	/**
	 * 换PNR出票
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> changePnr(JpKhdd jpkhdd) throws Exception{
		Shyhb user = SessionUtils.getShshbSession();
		JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(user.getShbh());
		List<JpKhddHd> jpHdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(jpkhdd.getDdbh(), user.getShbh());
		List<JpKhddCjr> jpCjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(jpkhdd.getDdbh(), user.getShbh());
		
		BookOrderUtil bookutil = new BookOrderUtil();
		Map<String, Object> pnrMap = bookutil.excCommand(jpkhdd, jpPz, user.getPidyh(), jpHdList, jpCjrList);
		return pnrMap;//pnrMap.get("PNRNO");
	}

	/**
	 * <行程单点邮寄或重新邮寄时,修改邮寄单,和订单的状态>
	 * 
	 * @param jpKhddYj
	 * @return [参数说明]
	 * 
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public int updateYjdAndDd(JpKhddYj jpKhddYj){
		int result=0;
		if(StringUtils.isNotBlank(jpKhddYj.getId())){//有邮寄单时
			jpkhddYjService.updateYjdhMore(jpKhddYj);
			result=this.getMyBatisDao().updateYjzt(jpKhddYj);
		}else{//没有邮寄单时
			if(StringUtils.isBlank(jpKhddYj.getYjdh())){//跳过邮寄
				result=jpkhddYjService.updateYjzt(jpKhddYj);
				result=this.getMyBatisDao().updateYjzt(jpKhddYj);
			}else{//确定邮寄的保存邮寄
				String yjid=jpkhddYjService.queryYjlistByddbh(jpKhddYj.getDdbh());
				jpKhddYj.setId(yjid);
				jpkhddYjService.updateYjdhMore(jpKhddYj);
				result=this.getMyBatisDao().updateYjzt(jpKhddYj);
			}
		}
		return result;
	}

	/**
	 * <根据订单编号查找邮寄单和订单>
	 * 
	 * @param ddbh
	 * @return [参数说明]
	 * 
	 * @return Map<String,Object> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String, Object> queryYjdDdByDdbh(String ddbh) {
		return this.getMyBatisDao().queryYjdDdByDdbh(ddbh);
	}

	/**
	 * <当对订单发送短信时,对订单的短信状态修改>
	 * 
	 * @param jpKhdd
	 * @return [参数说明]
	 * 
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public int updateSmszt(JpKhdd jpKhdd) {
		return this.getMyBatisDao().updateSmszt(jpKhdd);
	}
	
	/**
	 * Avh 出舱位信息
	 * @param shbh
	 * @param jpKhdd
	 * @param jpKhddHd
	 * @return
	 * @throws EtermException
	 */
	public List<AvCabinModel> getCabinList(String shbh, JpKhdd jpKhdd, JpKhddHd jpKhddHd) throws Exception{
		Shyhb yhb = SessionUtils.getShshbSession();
		try {
			//调用RTPNR接口
			JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(shbh);
			AvhParam avhParam = new AvhParam();
			avhParam.setShbh(jpPz.getShbh());
			avhParam.setUserid(yhb.getPidyh());
			avhParam.setPassword(yhb.getMm());
			avhParam.setUrl(jpPz.getPzIp()+":"+jpPz.getPzPort());
			avhParam.setOfficeId(jpPz.getOfficeid());
			avhParam.setHkgs(jpKhdd.getHkgs());
			avhParam.setCfcs(jpKhddHd.getCfcity());
			avhParam.setDdcs(jpKhddHd.getDdcity());
			avhParam.setHc(jpKhddHd.getCfcity()+jpKhddHd.getDdcity());
			avhParam.setHbh(jpKhddHd.getXsHbh());
			avhParam.setCfrq(jpKhddHd.getCfsj());
			Avh avh = new Avh();
			String avData = avh.avh(avhParam);
			return this.getAvStructureData(avData, jpKhdd, jpKhddHd);
		} catch (Exception e) {
			logger.error("调用RTPNR接口出错:"+e.getMessage());
			throw new Exception("调用RTPNR接口出错:"+e.getMessage());
		}
	}
	
	/**
	 * 解析AV出的数据，并格式化
	 * @param avData
	 * @param jpKhdd
	 * @param jpkhddhd
	 * @return
	 */
	public List<AvCabinModel> getAvStructureData(String avData, JpKhdd jpKhdd, JpKhddHd jpkhddhd){
		//获取Y舱位价
		Bairprice bairprice = new Bairprice();
		bairprice.setCfcity(jpkhddhd.getCfcity());
		bairprice.setDdcity(jpkhddhd.getDdcity());
		bairprice.setHkgs(jpKhdd.getHkgs());
		bairprice.setYxq(jpkhddhd.getCfsj().substring(0, 10));
		Double ycj = bairpriceServiceImpl.getYcj(bairprice);

		//获取AV格式化数据
		String currentHbh = StringUtils.trimToEmpty(jpkhddhd.getXsHbh()).replace("*", "");
		List<String[]> cwList = AvhParser.parseAvData(avData, currentHbh);
		
		//组装舱位列表到bean
		List<AvCabinModel> cabinList = this.getCabinListByAvDate(cwList, jpKhdd, jpkhddhd, ycj);
		return cabinList;
	}
	
	/**
	 * 获取AV格式化数据
	 * @param cwList
	 * @param jpKhdd
	 * @param jpkhddhd
	 * @param ycj
	 * @return
	 */
	public List<AvCabinModel> getCabinListByAvDate(List<String[]> cwList, JpKhdd jpKhdd, JpKhddHd jpkhddhd, Double ycj){
		List<AvCabinModel> cabinList = new ArrayList<AvCabinModel>();
		// 当前舱位和航班,航空公司，起飞日期
		String currentCw = jpkhddhd.getXsCw();
		String currentHbh = StringUtils.trimToEmpty(jpkhddhd.getXsHbh()).replace("*", "");
        // 获得原舱位票价
		double ycwpj = Arith.div(jpKhdd.getCgPj(), jpKhdd.getCjrs());
		AvCabinModel currentAvCabin = new AvCabinModel();
		currentAvCabin.setCw(currentCw);
		currentAvCabin.setPrice(ycwpj);
        // 计算有座舱位的折扣和价格
		if (CollectionUtils.isNotEmpty(cwList)) {
			String cfrq = jpkhddhd.getCfsj().substring(0, 10);
			String hkgs = currentHbh.substring(0, 2);
			
			for (String[] cwArr : cwList) {
				Map<String, Object> hcMap = new HashMap<String, Object>();
				hcMap.put("hkgs", hkgs);
				hcMap.put("cw", cwArr[0]);
				hcMap.put("hc", jpkhddhd.getCfcity() + jpkhddhd.getDdcity());
				hcMap.put("cfsj", cfrq);
				hcMap.put("hbh", currentHbh);
				//调用过程获取折扣率
				procedureServiceImpl.getZkl(hcMap);
				
				Object tempzkl=hcMap.get("result");
				double zkl = 0.0;
				if(tempzkl != null){
					zkl = ((Number)tempzkl).doubleValue();
				}
				if (zkl < 0)
					zkl = 0.0;
				double zdpj = ycj.doubleValue() * zkl / 10;
				String zdpjStr = Function.round2(zdpj + "", "0", "4");
				double zdpjInt = NumberUtils.toDouble(zdpjStr) * 10;
				double cj = ycwpj - (int) zdpjInt;
				if (currentCw.equals(cwArr[0])) {
					currentAvCabin.setCwNum(cwArr[1]);
					continue;
				}
				//if (cj > 0) {// 差价大于0才显示
					AvCabinModel ac = new AvCabinModel();
					ac.setCw(cwArr[0]);
					ac.setCwNum(cwArr[1]);
					ac.setPrice(zdpjInt);
					ac.setSubPrice(cj);
					ac.setFjjx(cwArr[2]);
					ac.setCfsj(cwArr[3]);
					ac.setDdsj(cwArr[4]);
					ac.setHzl(cwArr[5]);
					ac.setHbh(cwArr[6]);
					cabinList.add(ac);
				//}
			}
		}
		
		//排序
		if (CollectionUtils.isNotEmpty(cabinList)) {
			Comparator mycmp = ComparableComparator.getInstance();
			mycmp = ComparatorUtils.nullLowComparator(mycmp); // 允许null
			ArrayList<Object> sortFields = new ArrayList<Object>();
			sortFields.add(new BeanComparator("subPrice", mycmp)); // id逆序 (主)
			ComparatorChain multiSort = new ComparatorChain(sortFields);
			Collections.sort(cabinList, multiSort);
		}
		cabinList.add(currentAvCabin);
		return cabinList;
	}
	
	/**
	 * 采购对账异常新增查询
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> addcgdzycSearch(Map<String, Object> map){
		return this.getMyBatisDao().addcgdzycSearch(map);
	}
	/**
	 * 订单入库或跟新订单
	 * @param shbh
	 * @param jpDdBean
	 * @param jpDdCjrList
	 * @param jpDdHdList
	 */
	public BookOrder saveOrder(String shbh, JpDdBean jpDdBean, List<JpDdCjrBean>  jpDdCjrList, List<JpDdHdBean>  jpDdHdList, Logger logger){
		List<Map<String, String>> jpDdCjrLists = new ArrayList<Map<String,String>>();
		List<Map<String, String>> jpDdHdLists = new ArrayList<Map<String,String>>();
		if(CollectionUtils.isNotEmpty(jpDdCjrList)){
			for(JpDdCjrBean cjrbean : jpDdCjrList){//乘机人bean转map
				Map<String, String> cjrbeanMap = Bean2Map.getMap(cjrbean);
				jpDdCjrLists.add(cjrbeanMap);
			}
		}
		if(CollectionUtils.isNotEmpty(jpDdHdList)){
			for(JpDdHdBean hdbean : jpDdHdList){//航段bean转map
				Map<String, String> hdbeanMap = Bean2Map.getMap(hdbean);
				jpDdHdLists.add(hdbeanMap);
			}
		}
		Map<String, String> jpDdBeanMap = Bean2Map.getMap(jpDdBean);
		OrderBean orderbean = new OrderBean();
		orderbean.setCjrList(jpDdCjrLists);
		orderbean.setHdList(jpDdHdLists);
		orderbean.setKhddMap(jpDdBeanMap);
		BookOrder bean = new BookOrder(orderbean);
		if(logger != null){
			logger.error("订单入库参数："+jpDdBeanMap);
		}
		procPkgPnrServiceImpl.insertJpKhdd(bean,null);
		return bean;
	}
	
	/**
	 * 根据城市三字获取城市名称
	 */
	public void setCityName(Pnr pnrObject){
		//根据城市三字获取城市名称
		List<PnrHd> hdList = pnrObject.getHdblist();
		if(hdList != null){
			for(PnrHd pnrHd : hdList){
				if(pnrHd != null){
					if(StringUtils.isNotBlank(pnrHd.getCfcity())){
						BcityCache cfCity = FunctionCode.getBcity(pnrHd.getCfcity());
						pnrHd.setCfcityName(cfCity.getMc());
					}
					if(StringUtils.isNotBlank(pnrHd.getDdcity())){
						BcityCache ddCity = FunctionCode.getBcity(pnrHd.getDdcity());
						pnrHd.setDdcityName(ddCity.getMc());
					}
				}
			}
		}
	}
	
	/**
	 * 根据PNR内容提取订单信息
	 * @param jpPz 机票配置信息
	 * @param pnrContent PNR内容
	 * @return
	 * @throws Exception
	 */
	public Pnr tqByPnrContent(String pnrContent, JpPz jpPz) throws Exception{
		Shyhb yhb = SessionUtils.getShshbSession();
		PnrRtParam pnrRtParam = new PnrRtParam();
		pnrRtParam.setShbh(jpPz.getShbh());
		pnrRtParam.setUserid(yhb.getPidyh());
		pnrRtParam.setPassword(yhb.getMm());
		pnrRtParam.setUrl(jpPz.getPzIp()+":"+jpPz.getPzPort());
		pnrRtParam.setOfficeId(jpPz.getOfficeid());
		//去除PNR内容包含asc码为''字符
		pnrContent = pnrContent.replace("", "");
		pnrRtParam.setPnrnr(pnrContent);
		pnrRtParam.setMethodType(PNRParser.PARSEPNR);
		pnrRtParam.setIfthrowexception("1");//检查异常
		PNRParser pnrParser = new PNRParser(pnrRtParam);
		
		//解析国际票的PNR内容
		Pnr pnrObject = ParseGjPNR.parseSJPNR(pnrContent);
		//解析国内票的PNR内容
		if("-1".equals(pnrObject.getFlag())) { //不是SJ航空公司，调原来的接口
			pnrObject.setGngj("1");//国内
			pnrObject = pnrParser.parserPNR();
		}
		//根据城市三字码，填入城市姓名信息
		this.setCityName(pnrObject);
		return pnrObject;
	}
	
	/**
	 * 根据采购单号查询订单
	 * @param shbh
	 * @param cgddbh
	 * @param cgdw
	 * @return
	 * @throws Exception
	 */
	public JpKhdd getJpKhddByCgDdbh(String shbh,String cgddbh,String cgdw) throws Exception{
		return this.getMyBatisDao().getJpKhddByCgDdbh(shbh, cgddbh, cgdw);
	}
	/**
	 * <自动出票，降舱后修改订单采购信息>
	 * 
	 * @param ddbh
	 * @param shhb
	 * @param dbjPnr [参数说明]
	 */
	@Transactional
	public void changeCabin(String ddbh,String shbh,JpZdcpDbjpnr dbjPnr){
		JpKhdd t=new JpKhdd();
		t.setDdbh(ddbh);
		t.setShbh(shbh);
		JpKhdd jpKhdd=this.getEntityById(t);
		if(jpKhdd.getCgPnrNo().equals(dbjPnr.getPnr())){
			return;
		}
		List<JpKhddCjr> cjrList=jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, shbh);
		List<JpKhddHd> hdList=jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
		JpKhdd khdd=new JpKhdd();
		khdd.setDdbh(ddbh);
		khdd.setShbh(shbh);
		khdd.setCgPnrNo(dbjPnr.getPnr());
		khdd.setCgPnrZt(dbjPnr.getPnrZt());
		khdd.setCgHkgsPnr(dbjPnr.getHkgspnr());
		khdd.setCgHbh(dbjPnr.getHbh());
		khdd.setCgCw(dbjPnr.getCw());
		double cgZdj=Arith.mul(dbjPnr.getPatZdj(), cjrList.size());
		khdd.setCgZdj((long)cgZdj);
		khdd.setCgPj(cgZdj);
		khdd.setPnrLr("");
		khdd.setPatLr("");
		this.getMyBatisDao().updateByPrimaryKeySelective(khdd);
		for(JpKhddHd hd : hdList){
			hd.setCgCw(dbjPnr.getCw());
			hd.setCgHbh(dbjPnr.getHbh());
			jpKhddHdServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(hd);
		}
		double zdj=dbjPnr.getPatZdj();
		for(JpKhddCjr cjr : cjrList){
			cjr.setCgZdj((long)zdj);
			cjr.setCgPj(new BigDecimal(zdj));
			jpKhddCjrServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(cjr);
		}
	}

	/**
	 * <出票控制台中一键完成时.>
	 * 
	 * @param jpDdBean
	 * @param dd [参数说明]
	 * @author heqiwen
	 * @return void [返回类型说明]
	 * @throws Exception 
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void oneButtonFinished(JpDdBean jpDdBean, JpKhdd dd,Shyhb user) throws Exception {
		dd.setSfyjxcd(jpDdBean.getSfyjxcd());
		dd.setSjr(jpDdBean.getSjr());
		dd.setXjdz(jpDdBean.getXjdz());
		dd.setXsYjf(jpDdBean.getXs_yjf());
		dd.setYzbm(jpDdBean.getYzbm());
		dd.setXgly("一键完成");
		dd.setXgyh(user.getBh());
		dd.setShbh(user.getShbh());
		dd.setDdzt(DictEnum.JPDDZT.YWC.getValue());//已取消
		this.update(dd);
		String[] tknos=jpDdBean.getTknoArr();
		String[] cjrs=jpDdBean.getCjrArr();
		for(int i=0;i<tknos.length;i++){
			JpKhddCjr jpKhddCjr=new JpKhddCjr();
			jpKhddCjr.setId(cjrs[i]);
			jpKhddCjr.setTkno(tknos[i]);
			jpKhddCjrServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(jpKhddCjr);
		}
	}
	public int updateJpKhddDdztByWbdh(JpKhdd jpkhdd) throws Exception{
		return this.getMyBatisDao().updateJpKhddDdztByWbdh(jpkhdd);
	}
	/**
	 * where参数shbh  商户编号;  ddbhs List<String>订单编号集合
	 * set参数 xgly 修改来源;   xgyh 修改用户   
	 */
	public int updateXglyByDdbh(Map<String,Object> param) throws Exception{
		return this.getMyBatisDao().updateXglyByDdbh(param);
	}
	/**
	 *根据时间字符串和网店ID获取淘宝出票中订单（或者获取淘宝票号异常订单）
	 */
	public List<JpKhdd> getKhddBySfwbcpz(String kssj,String jssj,String wdid) throws Exception{
		return this.getMyBatisDao().getKhddBySfwbcpz(kssj,jssj,wdid);
	}
	
	public List<String> getZjpWbdh(){
		return this.getMyBatisDao().getZjpWbdh();
	}
	
	public List<JpKhdd> getKhddByWbdh(String shbh,String wbdh){
		return this.getMyBatisDao().getKhddByWbdh(shbh, wbdh);
	}
	@Transactional
	public int deleteAllByWbdh(String wbdh,String shbh){
		try {
			this.getMyBatisDao().deleteByWbdh(wbdh, shbh);
			jpKhddKzServiceImpl.deleteByWbdh(wbdh, shbh);
			jpKhddHdServiceImpl.deleteByWbdh(wbdh, shbh);
			jpKhddCjrServiceImpl.deleteByWbdh(wbdh, shbh);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
}

