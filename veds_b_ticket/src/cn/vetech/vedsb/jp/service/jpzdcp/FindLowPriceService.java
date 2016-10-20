package cn.vetech.vedsb.jp.service.jpzdcp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.cache.BairwaycwCache;
import org.vetech.core.business.cache.BairwaycwCacheService;
import org.vetech.core.business.pid.api.avh2.Avh2;
import org.vetech.core.business.pid.api.avh2.Avh2Param;
import org.vetech.core.business.pid.api.avh2.Avh2Parser;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.pidbean.AvCabinModel;
import org.vetech.core.business.pid.pidbean.CommandBean;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.Identities;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Shcsb;
import cn.vetech.vedsb.common.entity.Shshb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.ShcsbServiceImpl;
import cn.vetech.vedsb.common.service.base.ShshbServiceImpl;
import cn.vetech.vedsb.jp.entity.airway.Bairprice;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpDbjpnr;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpJk;
import cn.vetech.vedsb.jp.service.jpddgl.BairpriceServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.vedsb.utils.BookOrderUtil;
import cn.vetech.vedsb.utils.DataUtils;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.LogUtil;

@Service
public class FindLowPriceService {
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private BairwaycwCacheService bairwaycwCacheService;
	@Autowired
	private BairpriceServiceImpl bairpriceServiceImpl;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired
	private ShshbServiceImpl shshbServiceImpl;
	@Autowired
	private ShcsbServiceImpl shcsbServiceImpl;
	@Autowired
	private JpZdcpDbjpnrServiceImpl jpZdcpDbjpnrServiceImpl;
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	private JpPz jpPz;
	
	//寻找更低舱位,并生成新编码
	public boolean findLowPrice(JpZdcpJk jk,Shyhb yhb,JpKhdd jpkhdd,String sftsk,double jjcj,StringBuffer pnrztflag){
		try {
			if(!"1".equals(jk.getDdHclx())){
				return false;
			}
			//初始化PID配置
			init(jk.getShbh());
			//String qfrq=jk.getDdQfsj().substring(0,10);
			//String qfsk=jk.getDdQfsj().substring(11,16);
			//通过AVH获取比原舱位价格更低的同等级舱位
			List<AvCabinModel> lowCabinList= null;
			if("1".equals(sftsk)){
				lowCabinList = genLowCabin(jk, yhb,jpkhdd);//找低价格低的舱位
			}else if("2".equals(sftsk)){
				lowCabinList = genCyhbCabin(jk, yhb,jpkhdd);//找共享航班的实际承运航班
			}
			if(CollectionUtils.isEmpty(lowCabinList)){
				return false;
			}
			if(lowCabinList.size()>1){
				long t = System.currentTimeMillis();
				DataUtils.sortList(lowCabinList, new String[]{"price,asc"});
				LogUtil.getAutoEtdz().error(jpkhdd.getDdbh()+"航班舱位价格排序【耗时：" + (System.currentTimeMillis() - t) + "】");
				
			}
			//取价格最低的舱位生成PNR
			AvCabinModel cabinModel=lowCabinList.get(0);
			jk.add("寻找到的低舱位账单价:"+cabinModel.getPrice());
			double cj = Arith.sub(jk.getDdZdj(),cabinModel.getPrice());//差价
			if(Arith.sub(jjcj, cj)>0){//寻找到低价舱位与原航班的舱位价格的差价比设置规则的净价大才按照低价舱位出票,否则按原舱位出票
				return false;
			}
			JpKhdd t=new JpKhdd();
			t.setDdbh(jk.getDdbh());
			t.setShbh(jk.getShbh());
			JpKhdd khdd=jpKhddServiceImpl.getEntityById(t);
			if(DictEnum.JPDDZT.YWC.getValue().equals(khdd.getDdzt())){//防止重复出票，重复生成pnr
				throw new Exception("换编码时发现pnr已经出过票,自动出票终止！");
			}
			Map<String, String> result=creatPnr(jk, yhb, cabinModel,khdd);
			String pnrzt = result.get("PNRZT");
			String newPnrNo=result.get("PNRNO");
			if(StringUtils.isBlank(newPnrNo)){
				throw new Exception("获取到了更低舱位,但生成编码失败！");
			}
			jk.add("成功寻找到更低舱位,新的pnr为:"+newPnrNo+"|新的pnr状态:"+pnrzt);
			if(!"HK".equals(pnrzt) && !"KK".equals(pnrzt) && !"KL".equals(pnrzt)){
				pnrztflag.append(newPnrNo);
				LogUtil.getAutoEtdz().error(jpkhdd.getDdbh()+"生成没有座位的编码,将pnr赋值给pnrztflag变量:"+pnrztflag.toString()+"<br>");
				return true;
			}
			//插入待比价编码
			insertToDbj(jk, khdd, result, cabinModel);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * <初始化配置>
	 * @throws Exception 
	 */
	private void init(String shbh) throws Exception{
		JpPz jppz = jpPzServiceImpl.getJpPzByShbh(shbh);
		if(jppz==null){
			throw new Exception("没获取到商户"+shbh+"的pid配置");
		}
		this.jpPz = jppz;
	}
	/**
	 * <获取比原舱位价格低的舱位信息>
	 */
	private List<AvCabinModel> genLowCabin(JpZdcpJk jk,Shyhb yhb,JpKhdd jpkhdd) throws Exception{
		List<AvCabinModel> cwList=avh2(jk,yhb,jpkhdd);//AVH查出同一时刻的所有航班
		if(CollectionUtils.isEmpty(cwList)){
			return null;
		}
		List<JpKhddCjr> cjrList=jpKhddCjrServiceImpl.getKhddCjrListByDDbh(jk.getDdbh(), jk.getShbh());
		if(CollectionUtils.isEmpty(cjrList)){
			throw new Exception("没找订单乘机人！");
		}
		int cjrs=cjrList.size();
		String qfrq=jk.getDdQfsj().substring(0,10);
		String currentCw=jk.getDdCw();
		String currentHkgs=jk.getDdHkgs();
		String hc=jk.getDdHc();
		String currentHbh=jk.getDdHbh().replace("*", "");
		String cfcs=hc.substring(0,3);
		String ddcs=hc.substring(3);
		BairwaycwCache currentCabin=genBairwayCabin(currentCw, qfrq, currentHkgs);
		if(currentCabin==null){//没获取到当前舱位信息，则无法获取舱位等级
			return null;
		}
		String currentCwdjLx=currentCabin.getCwdjlx();//当前舱位的等级类型
		Map<String, Double> ycjMap=new HashMap<String, Double>();
		List<AvCabinModel> hasPriceList=new ArrayList<AvCabinModel>();//存放有价格的舱位
		List<AvCabinModel> noPriceList=new ArrayList<AvCabinModel>();//存放非公布运价的舱位
		for(AvCabinModel avCw : cwList){
			String hbh=avCw.getHbh().replace("*", "");
			String hkgs=hbh.substring(0,2);
			if(currentCw.equals(avCw.getCw()) && currentHbh.equals(hbh)){//相同舱位不再重复生成
				continue;
			}
			String cyhbh=StringUtils.trimToEmpty(avCw.getCyhbh());
			if(StringUtils.isNotBlank(cyhbh)){
				cyhbh=cyhbh.replace("*", "");
			}
			//只找同一航班或共享航班的舱位或实际承运航班的舱位
			if(!currentHbh.equals(hbh) && !currentHbh.equals(cyhbh)){
				continue;
			}
			BairwaycwCache airCabin=genBairwayCabin(avCw.getCw(), qfrq, hkgs);
			if(airCabin==null){//没找到维护的舱位数据，无法参与比价
				continue;
			}
			if(!currentCwdjLx.equals(airCabin.getCwdjlx())){//只能找同等级舱位
				continue;
			}
			String cwNum=avCw.getCwNum();
			if(!"A".equals(cwNum) && NumberUtils.toInt(cwNum) < cjrs){//座位数不够
				continue;
			}
			Map<String, Object> hcMap = new HashMap<String, Object>();
			hcMap.put("hkgs", hkgs);
			hcMap.put("cw", avCw.getCw());
			hcMap.put("hc", hc);
			hcMap.put("cfsj", qfrq);
			hcMap.put("hbh", hbh);
			//调用过程获取折扣率
			procedureServiceImpl.getZkl(hcMap);
			Double ycj=ycjMap.get(hkgs);
			if(ycj==null){
				ycj=genYcj(cfcs, ddcs, hkgs, qfrq);
			}
			if(ycj==null){
				continue;
			}
			ycjMap.put(hkgs, ycj);
			Double price=computerPrice(hcMap, ycj);
			if((price==null || price==0) && "006".equals(airCabin.getBy6()) 
					&& "0".equals(airCabin.getIfgbyj())){//特价舱需要pat
				noPriceList.add(avCw);
			}else if(price!=null && price < jk.getDdZdj()){
				avCw.setPrice(price);
				hasPriceList.add(avCw);
			}
		}
		//对特价舱进行PAT
//		pat(noPriceList, jk, yhb,cjrList.get(0).getCjrlx());
//		if(CollectionUtils.isNotEmpty(noPriceList)){
//			hasPriceList.addAll(noPriceList);
//		}
		return hasPriceList;
	}
	
	/**寻找共享航班*/
	private List<AvCabinModel> genCyhbCabin(JpZdcpJk jk,Shyhb yhb,JpKhdd jpkhdd) throws Exception{
		List<AvCabinModel> hasPriceList=new ArrayList<AvCabinModel>();//存放有价格的舱位
		List<AvCabinModel> cycwlist = new ArrayList<AvCabinModel>();
		String currentHbh=jk.getDdHbh().replace("*", "");//订单航班号
		String hc=jk.getDdHc();
		String cfcs=hc.substring(0,3);
		String ddcs=hc.substring(3);
		String qfrq=jk.getDdQfsj().substring(0,10);
		String currentCw=jk.getDdCw();
		String currentHkgs=jk.getDdHkgs();
		BairwaycwCache currentCabin=genBairwayCabin(currentCw, qfrq, currentHkgs);
		if(currentCabin==null){//没获取到当前舱位信息，则无法获取舱位等级
			return null;
		}
		
		String currentCwdjLx=currentCabin.getCwdjlx();//当前舱位的等级类型
		List<JpKhddCjr> cjrList=jpKhddCjrServiceImpl.getKhddCjrListByDDbh(jk.getDdbh(), jk.getShbh());
		if(CollectionUtils.isEmpty(cjrList)){
			throw new Exception("没找订单乘机人！");
		}
		int cjrs=cjrList.size();
		String sjcyhbh = "";
		String sjcyhs = "";
		long t = System.currentTimeMillis();
		List<AvCabinModel> cwList=avh2(jk,yhb,jpkhdd);//AVH查出同一时刻的所有航班
		LogUtil.getAutoEtdz().error(jpkhdd.getDdbh()+"avh查询同一时刻的所有航班【耗时：" + (System.currentTimeMillis() - t) + "】");
		
		if(CollectionUtils.isEmpty(cwList)){
			return null;
		}
		for(AvCabinModel avCw : cwList){
			String hbh=avCw.getHbh();
			if(currentHbh.equals(hbh)){//相同航班号
				sjcyhbh = avCw.getCyhbh();
				sjcyhs = sjcyhbh.substring(0,2);
				break;
			}
		}
		Double ycj=genYcj(cfcs, ddcs, sjcyhs, qfrq);//寻找实际承运航班的y舱价
		if(ycj == null || ycj <= 0){//该航线没有维护y舱价
			return null;
		}
		for(AvCabinModel avCw : cwList){
			String hbh=avCw.getHbh();
			if(sjcyhbh.equals(hbh)){//相同航班号
				cycwlist.add(avCw);
			}
		}
		AvCabinModel cw = null;
		int avcwsize = cycwlist.size();
		long t1 = System.currentTimeMillis();
		for(int i=avcwsize;i>0;i--){
			cw = cycwlist.get(i-1);
			if(cw!=null){
				String hbh=cw.getHbh();
				String hkgs=hbh.substring(0,2);
				String cwNum=cw.getCwNum();
				BairwaycwCache airCabin=genBairwayCabin(cw.getCw(), qfrq, hkgs);
				if(airCabin==null){//没找到维护的舱位数据，无法参与比价
					continue;
				}
				if(!currentCwdjLx.equals(airCabin.getCwdjlx())){//只能找同等级舱位
					continue;
				}
				if(!"A".equals(cwNum) && NumberUtils.toInt(cwNum) < cjrs){//座位数不够
					continue;
				}
				
				Map<String, Object> hcMap = new HashMap<String, Object>();
				hcMap.put("hkgs", hkgs);
				hcMap.put("cw", cw.getCw());
				hcMap.put("hc", hc);
				hcMap.put("cfsj", qfrq);
				hcMap.put("hbh", hbh);
				//调用过程获取折扣率
				procedureServiceImpl.getZkl(hcMap);
				Double price=computerPrice(hcMap, ycj);
				if(price == null || price <= 0){
					continue;
				}
				cw.setPrice(price);
			}
			hasPriceList.add(cw);
			LogUtil.getAutoEtdz().error(jpkhdd.getDdbh()+"寻找共享航班对应的主飞航班所有舱位【耗时：" + (System.currentTimeMillis() - t1) + "】");
			return hasPriceList;
		}
		return null;
	}
	
	/**
	 * <特价舱批量PAT>
	 * @throws Exception 
	 */
	private void pat(List<AvCabinModel> noPriceList,JpZdcpJk jk,Shyhb yhb,String cjrlx) throws Exception{
		if(CollectionUtils.isEmpty(noPriceList)){
			return;
		}
		String qfrq=jk.getDdQfsj().substring(0,10);
		try {
			List<AvCabinModel> cList=new ArrayList<AvCabinModel>();
			for (AvCabinModel cabin : noPriceList) {
				PatCallable callable=new PatCallable(cabin,jpPz,cjrlx,jk.getDdHc(),qfrq,yhb.getPidyh());
				Double pj=callable.call();
				if(pj==null || pj==0 || pj >= jk.getDdZdj()){
					continue;
				}
				cabin.setPrice(pj);
				cList.add(cabin);
			}
			noPriceList.clear();
			noPriceList.addAll(cList);
		} catch (Exception e) {//不抛异常，不影响后面的流程继续进行
			e.printStackTrace();
		}
	}
	/**
	 * <计算舱位价格，非公布运价则返回false>
	 * @param cabinModel
	 * @param hcMap
	 * @param ycj
	 */
	private Double computerPrice(Map<String, Object> hcMap,Double ycj){
		BigDecimal tempzkl = (BigDecimal)hcMap.get("result");
		double zkl = 0.0;
		if(tempzkl != null){
			zkl = tempzkl.doubleValue();
		}
		if (zkl <=0){
			return null;
		}
		return Arith.round(Arith.mul(ycj, zkl), -1);
	}
	/**
	 * avh获取舱位信息
	 * @throws Exception 
	 */
	private List<AvCabinModel> avh2(JpZdcpJk jk,Shyhb yhb,JpKhdd jpkhdd) throws Exception{
		String cfrqLong = VeDate.dateToStrLong(jpkhdd.getCfrq());
		Avh2Param avh2Param=new Avh2Param();
		String hc=jk.getDdHc();
		String cfcs=hc.substring(0,3);
		String ddcs=hc.substring(3);
		String qfrq=cfrqLong.substring(0,10);
		String qfsk=cfrqLong.substring(11,16);
		qfsk = qfsk.replace(":", "");
		avh2Param.setCfcs(cfcs);
		avh2Param.setDdcs(ddcs);
		avh2Param.setDepDate(qfrq);
		avh2Param.setDepTime(qfsk);
		avh2Param.setIsDirect("1");
		avh2Param.setUrl(jpPz.getPzIp()+":"+jpPz.getPzPort());
		avh2Param.setUserid(yhb.getPidyh());
		Avh2 avh2=new Avh2();
		String avhData=avh2.avh(avh2Param);
		return Avh2Parser.parseAvData(avhData);
	}
	/**
	 * 在b_airway_cw中匹配舱位
	 * @param cabins
	 * @param cws
	 * @param qfrq
	 * @return
	 */
	private BairwaycwCache genBairwayCabin(String cabin, String qfrq,String hkgs) {
		List<BairwaycwCache> cws=bairwaycwCacheService.getEzdh(hkgs);
		if(cws==null){
			return null;
		}
		Map<String, BairwaycwCache> result = new HashMap<String, BairwaycwCache>();
		String today=VeDate.getStringDateShort();
		for (BairwaycwCache cb : cws) {
			if (!(cb.getCwdj()).equals(cabin)) { // 如果不是需要的舱位的不处理
				continue;
			}
			String yxq = StringUtils.isBlank(cb.getYxq()) ? "1900-01-01" : cb.getYxq();
			String sxrq = StringUtils.isBlank(cb.getBy5()) ? "1900-01-01" : cb.getBy5();
			int day = VeDate.getTwoDay(qfrq, yxq);
			int day1 = VeDate.getTwoDay(today, sxrq);
			// 查询日期必须大于有效期
			// 当天必须大于生效日期
			// 获取最近的一个有效期
			if (day >= 0 && day1 >= 0) {
				if (!result.containsKey(cb.getCwdj())
						|| VeDate.getTwoDay(result.get(cb.getCwdj()).getYxq(), cb.getYxq()) < 0) {
					result.put(cb.getCwdj(), cb);
				}
			}
		}
		return result.get(cabin);
	}
	/**
	 * <获取Y舱价>
	 * 
	 * @param cfcity
	 * @param ddcity
	 * @param hkgs
	 * @param cfrq
	 */
	private Double genYcj(String cfcity,String ddcity,String hkgs,String cfrq){
		try {
			//获取Y舱位价
			Bairprice bairprice = new Bairprice();
			bairprice.setCfcity(cfcity);
			bairprice.setDdcity(ddcity);
			bairprice.setHkgs(hkgs);
			bairprice.setYxq(cfrq);
			return bairpriceServiceImpl.getYcj(bairprice);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * <生成新编码>
	 * @throws Exception 
	 */
	private Map<String, String> creatPnr(JpZdcpJk jk,Shyhb yhb,AvCabinModel cabin,JpKhdd khdd) throws Exception{
		try {
			String ddbh=jk.getDdbh();
			String shbh=jk.getShbh();
			List<JpKhddHd> hdList=jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
			if(CollectionUtils.isEmpty(hdList)){
				throw new Exception("没找到订单航段信息！");
			}
			List<JpKhddCjr> cjrList=jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, shbh);
			if(CollectionUtils.isEmpty(cjrList)){
				throw new Exception("没找到订单乘机人信息！");
			}
			int hdLen=hdList.size();
			String[] cfcityArr=new String[hdLen];
			String[] ddcityArr=new String[hdLen];
			String[] cfdateArr=new String[hdLen];
			String[] cfskArr=new String[hdLen];
			String[] hkgsArr=new String[hdLen];
			String[] hbhArr=new String[hdLen];
			String[] cwArr=new String[hdLen];
			String[] ifnoseatArr=new String[hdLen];
			String hbh=cabin.getHbh().replace("*", "");
			String hkgs=hbh.substring(0,2);
			for(int i=0;i<hdLen;i++){
				JpKhddHd hd=hdList.get(i);
				cfcityArr[i]=hd.getCfcity();
				ddcityArr[i]=hd.getDdcity();
				cfdateArr[i]=hd.getCfsj().substring(0,10);
				cfskArr[i]=hd.getCfsj().substring(11,16);
				hkgsArr[i]=hkgs;
				hbhArr[i]=hbh;
				cwArr[i]=cabin.getCw();
				ifnoseatArr[i]="1";
			}
			int cjrLen=cjrList.size();
			String lxsj=genLxsj(khdd, shbh);
			String[] cjrlxArr=new String[cjrLen];
			String[] cjrxmArr=new String[cjrLen];
			String[] zjhmArr=new String[cjrLen];
			String[] csrqArr=new String[cjrLen];
			String[] sjhmArr=new String[cjrLen];
			for(int i=0;i<cjrLen;i++){
				JpKhddCjr cjr=cjrList.get(i);
				cjrlxArr[i]=cjr.getCjrlx();
				cjrxmArr[i]=cjr.getCjr();
				zjhmArr[i]=cjr.getZjhm();
				csrqArr[i]=cjr.getCsrq();
				sjhmArr[i]=lxsj;
			}
			
			CommandBean commandBean=new CommandBean();
			commandBean.setBookType("0");//ETERM
			commandBean.setGngj(khdd.getGngj());
			commandBean.setDpyid(yhb.getPidyh());
			commandBean.setHkgs(hkgsArr);
			commandBean.setHbh(hbhArr);
			commandBean.setCfcity(cfcityArr);
			commandBean.setDdcity(ddcityArr);
			commandBean.setCfdate(cfdateArr);
			commandBean.setCfsj(cfskArr);
			commandBean.setCw(cwArr);
			commandBean.setIfnoseat(ifnoseatArr);
			commandBean.setRs(cjrLen+"");
			commandBean.setCjrlx(cjrlxArr);
			commandBean.setCjrxm(cjrxmArr);
			commandBean.setZjhm(zjhmArr);
			commandBean.setCsrq(csrqArr);
			commandBean.setSjhm(sjhmArr);
			commandBean.setIfqk("1");
			commandBean.setCt_phoneno(lxsj);
			BookOrderUtil bookutil = new BookOrderUtil();
			return bookutil.excCommand(commandBean, jpPz.getPzIp()+":"+jpPz.getPzPort());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("生成编码失败！");
		}
	}
	private String genLxsj(JpKhdd khdd,String shbh){
		String lxsj = khdd.getNxsj();//订单联系手机
		if(StringUtils.isBlank(lxsj)){
			Shcsb shcsb = this.shcsbServiceImpl.findCs("2023", shbh);
			lxsj = shcsb.getCsz2();//取参数联系手机
		}
		if(StringUtils.isBlank(lxsj)){
			Shshb shshb = shshbServiceImpl.getShbhByBh(shbh);
			lxsj = shshb.getLxrsj();
		}
		return lxsj;
	}
	/**
	 * 插入待比价编码
	 * @throws Exception 
	 */
	private void insertToDbj(JpZdcpJk jk,JpKhdd khdd,Map<String, String> pnrInfo,AvCabinModel cabinModel) throws Exception{
		List<JpKhddHd> hdList=jpKhddHdServiceImpl.getKhddHdListByDDbh(jk.getDdbh(), jk.getShbh());
		JpZdcpDbjpnr dbjPnr=new JpZdcpDbjpnr();
		dbjPnr.setId(Identities.randomLong()+"");
		dbjPnr.setJkbid(jk.getId());
		dbjPnr.setDdbh(khdd.getDdbh());
		dbjPnr.setPnr(pnrInfo.get("PNRNO"));
		dbjPnr.setHkgspnr(pnrInfo.get("BIGPNR"));
		String hbh=cabinModel.getHbh().replace("*", "");
		String hkgs=hbh.substring(0,2);
		dbjPnr.setHkgs(hkgs);
		dbjPnr.setHclx(khdd.getHclx());
		dbjPnr.setHcgngj(khdd.getGngj());
		dbjPnr.setHc(khdd.getHc());
		dbjPnr.setCw(cabinModel.getCw());
		dbjPnr.setCwzkl("0");
		dbjPnr.setHbh(cabinModel.getHbh());
		dbjPnr.setCyHbh(cabinModel.getCyhbh());
		JpKhddHd hd=hdList.get(0);
		dbjPnr.setQfsj(hd.getCfsj());
		dbjPnr.setDdsj(hd.getDdsj());
		dbjPnr.setHzl(cabinModel.getHzl());
		dbjPnr.setCjr(khdd.getCjr());
		dbjPnr.setYdDatetime(new Date());
		dbjPnr.setPnrZt(pnrInfo.get("PNRZT"));
		dbjPnr.setSfcpbm("0");
		dbjPnr.setPatZdj(cabinModel.getPrice());
		double jsf=Arith.div(khdd.getCgJsf(), khdd.getCjrs());
		dbjPnr.setPatJj(jsf);
		dbjPnr.setPatSf(0d);
		jpZdcpDbjpnrServiceImpl.insert(dbjPnr);
	}
	
	public static void main(String[] args) {
		AvCabinModel a = new AvCabinModel();
		a.setCyhbh("MU1254");
		AvCabinModel a1 = new AvCabinModel();
		a1.setCyhbh("MU1354");
		List<AvCabinModel> list =null;
//		list.add(a);
//		list.add(a1);
//		
//		for(AvCabinModel as : list){
//			if(as.getCyhbh().equals("MU1254")){
//				list.remove(as);
//			}
//		}
		Double s = 0.0;
		System.out.println(s<=0);
	}
}
