package cn.vetech.vedsb.jp.service.jpzwgl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mapper.BeanMapper;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.dao.jpzwgl.JpTjsqDao;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsq;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqCjr;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqCjrZdsc;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqDr;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqZdsc;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.vedsb.specialticket.response.KwCjr;
import cn.vetech.vedsb.specialticket.response.KwOrder;
import cn.vetech.vedsb.specialticket.response.KwStatusResponse;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.ZwUtil;

@Service
public class JpTjsqServiceImpl extends MBaseService<JpTjsq, JpTjsqDao> {
	@Autowired
	private JpTjsqCjrServiceImpl jpTjsqCjrServiceImpl;
	@Autowired
	private JpTjsqZdscServiceImpl jpTjsqZdscServiceImpl;
	@Autowired
	private JpTjsqCjrZdscServiceImpl jpTjsqCjrZdscServiceImpl;
	@Autowired
	private JpTjsqJcgzServiceImpl jpTjsqJcgzServiceImpl;
	@Autowired
	private JpTjsqDrServiceImpl jpTjsqDrServiceImpl;
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	
	/**
	 * 根据机票订单号和商户编号查询没有取消的追位申请单数量
	 * 
	 * @return
	 */
	public int countWqxTjsq(String ddbh, String shbh) {
		return this.getMyBatisDao().countWqxTjsq(ddbh, shbh);
	}

	@Transactional
	public void saveJptjsq(JpTjsq jpTjsq, List<JpTjsqCjr> cjrList) throws Exception {
		super.insert(jpTjsq);
		for (JpTjsqCjr cjr : cjrList) {
			jpTjsqCjrServiceImpl.insert(cjr);
		}
	}
	
	/**
	 * 追位成功订单分页查询
	 * @param jptjsq
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page zwQueryPage(Map<String, Object> jptjsq, int pageNum, int pageSize) {
		Page page =new Page(pageNum,pageSize);
		List<Map<String, Object>> list = this.getMyBatisDao().getzwSuccList(jptjsq);
		for(Map<String,Object> m : list){
			BigDecimal tpf = (BigDecimal)m.get("TPF");
			BigDecimal cj = (BigDecimal)m.get("CJ");
			if(null!=tpf && null!=cj){
				double lr = Arith.sub(tpf.doubleValue(), cj.doubleValue());//利润
				m.put("LR", lr);
			}
			BigDecimal tpfl = (BigDecimal)m.get("TPFL");
			String fl = Arith.mul(tpfl.doubleValue(), 100)+"%";
			m.put("FL", fl);
		}
		int totalCount = this.getMyBatisDao().getTotalCount(jptjsq);
		page.setTotalCount(totalCount);
		page.setList(list);
		return page;
	}
	
	/**
	 * 查询审核追位订单
	 * @param sh
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page shQueryPage(Map<String, Object> sh, int pageNum, int pageSize) {
		Page page =new Page(pageNum,pageSize);
		List<Map<String, Object>> list = this.getMyBatisDao().getshZwddList(sh);
		for(Map<String,Object> m : list){
			BigDecimal tpfl = (BigDecimal)m.get("TPFL");
			String fl = Arith.mul(tpfl.doubleValue(), 100)+"%";
			m.put("FL", fl);
		}
		int totalCount = this.getMyBatisDao().getshTotalCount(sh);
		page.setTotalCount(totalCount);
		page.setList(list);
		return page;
	}
	
	/**
	 * 查询追位成功未处理的订单
	 * @param zwcgwcl
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page zwcgwclQueryPage(Map<String, Object> zwcgwcl, int pageNum, int pageSize) {
		Page page =new Page(pageNum,pageSize);
		List<Map<String, Object>> list = this.getMyBatisDao().getzwcgwclList(zwcgwcl);
		for(Map<String,Object> m : list){
			BigDecimal tpf = (BigDecimal)m.get("TPF");
			BigDecimal cj = (BigDecimal)m.get("CJ");
			if(null!=tpf && null!=cj){
				double lr = Arith.sub(tpf.doubleValue(), cj.doubleValue());//利润
				m.put("LR", lr);
			}
			BigDecimal tpfl = (BigDecimal)m.get("TPFL");
			String fl = Arith.mul(tpfl.doubleValue(), 100)+"%";
			m.put("FL", fl);
		}
		int totalCount = this.getMyBatisDao().getcgwclTotalCount(zwcgwcl);
		page.setTotalCount(totalCount);
		page.setList(list);
		return page;
	}
	
	/**
	 * 将临时队列导入正式队列
	 * @param jpTjsqZdsc
	 * @throws Exception
	 */
	@Transactional
	public void lsdlToTjsq(JpTjsqZdsc jpTjsqZdsc) throws Exception{
		Map yzResult=procedureServiceImpl.checkCanzw(jpTjsqZdsc.getSqdh());
		String isZw=objToString(yzResult.get("result"));
		if(!"1".equals(isZw)){//不可追位
			jpTjsqZdsc.setSqZt(DictEnum.ZWLSDLSQZT.ZSB.getValue());
			jpTjsqZdsc.setSftjzw("1");
			jpTjsqZdsc.setTjsj(VeDate.getNow());
			jpTjsqZdsc.setBy3(objToString(yzResult.get("p_error")));
		}else{
			//可以追位导入正式队列
			JpTjsq jpTjsq=BeanMapper.map(jpTjsqZdsc, JpTjsq.class);
			jpTjsq.setCw(objToString(yzResult.get("p_out_cw")));
			String zwfs=objToString(yzResult.get("p_out_zwfs"));
			jpTjsq.setZwfs(zwfs);
			Double pjfw= yzResult.get("p_out_jg")==null ? null : NumberUtils.toDouble(objToString(yzResult.get("p_out_jg")));
			jpTjsq.setPjfw(pjfw);
			jpTjsq.setSfwbdr("0");
			JpTjsqCjrZdsc zdscCjr=new JpTjsqCjrZdsc();
			zdscCjr.setShbh(jpTjsqZdsc.getShbh());
			zdscCjr.setSqdh(jpTjsqZdsc.getSqdh());
			List<JpTjsqCjrZdsc> zdscCjrList=jpTjsqCjrZdscServiceImpl.getMyBatisDao().select(zdscCjr);
			List<JpTjsqCjr> cjrList=BeanMapper.mapList(zdscCjrList, JpTjsqCjr.class);
			super.insert(jpTjsq);
			for (JpTjsqCjr cjr : cjrList) {
				jpTjsqCjrServiceImpl.insert(cjr);
			}
			jpTjsqZdsc.setSqZt(DictEnum.ZWLSDLSQZT.ZWC.getValue());
			jpTjsqZdsc.setSftjzw("1");
			jpTjsqZdsc.setTjsj(VeDate.getNow());
		}
		jpTjsqZdscServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(jpTjsqZdsc);
	}
	/**
	 *  将导入的申请单导入正式队列
	 * @param jpTjsqDr
	 * @throws Exception
	 */
	@Transactional
	public void drToTjsq(JpTjsqDr jpTjsqDr) throws Exception{
		Map yzResult = procedureServiceImpl.checkCanzwDr(jpTjsqDr.getId());
		String isZw=objToString(yzResult.get("result"));
		if(!"1".equals(isZw)){//不可追位
			jpTjsqDr.setSqZt(DictEnum.ZWLSDLSQZT.ZSB.getValue());
			jpTjsqDr.setSftjzw("1");
			jpTjsqDr.setTjsj(VeDate.getNow());
			jpTjsqDr.setBy3(objToString(yzResult.get("p_error")));
		}else{
			Date now= VeDate.getNowDateShort();
			Date cpdate=jpTjsqDr.getCpDatetime();
			String zwlx="0";
			if(cpdate!=null && cpdate.getTime()<now.getTime()){//隔天追位
				zwlx="3";
			}
			JpTjsq jpTjsq=jpTjsqDr.copyToJptjsq();
			jpTjsq.setZwlx(zwlx);
			jpTjsq.setCw(objToString(yzResult.get("p_out_cw")));
			String zwfs=objToString(yzResult.get("p_out_zwfs"));
			jpTjsq.setZwfs(zwfs);
			Double pjfw= yzResult.get("p_out_jg")==null ? null : NumberUtils.toDouble(objToString(yzResult.get("p_out_jg")));
			jpTjsq.setPjfw(pjfw);
			Map tpXx=procedureServiceImpl.genZwddTpf(jpTjsqDr.getId(),jpTjsqDr.getShbh());
			Object tpflObj=tpXx.get("p_tpfl");
			Object tpfObj=tpXx.get("p_tpf");
			double tpfl=tpflObj==null ? 0 : NumberUtils.toDouble(tpflObj.toString());//退票费等调后台取
			double tpf=tpfObj==null ? 0 : NumberUtils.toDouble(tpfObj.toString());
			jpTjsq.setTpf(tpf);
			jpTjsq.setTpfl(tpfl);
			//是否追共享航班
			Map<String, String> zwParamMap=jpTjsqJcgzServiceImpl.getTjsqJcgzMap(jpTjsqDr.getShbh());
			String isgx = ZwUtil.getValueByMap(zwParamMap, "GXZW", "0");
			if(!"0".equals(isgx)){
				String gxcjStr=ZwUtil.getValueByMap(zwParamMap, "GXCJ", "0");
				jpTjsq.setGxhbcj(NumberUtils.toDouble(gxcjStr));
			}
			jpTjsq.setGxhbsfzw(isgx);
			String sqdh=VeDate.getNo(6);
			jpTjsq.setSqdh(sqdh);
			JpTjsqCjr jpTjsqCjr=new JpTjsqCjr();
			jpTjsqCjr.setId(VeDate.getNo(6));
			jpTjsqCjr.setSqdh(sqdh);
			jpTjsqCjr.setCjr(jpTjsqDr.getPnrCjr());
			jpTjsqCjr.setZjlx("ID");
			jpTjsqCjr.setZjhm(jpTjsqDr.getPnrCjrzjh());
			jpTjsqCjr.setSfzwcg("0");
			jpTjsqCjr.setYprice(pjfw);
			jpTjsqCjrServiceImpl.insert(jpTjsqCjr);//插入乘机人
			jpTjsqDr.setSqdh(sqdh);
			jpTjsqDr.setSftjzw("1");
		}
		jpTjsqDrServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(jpTjsqDr);
	}
	
	/**
	 * 把object转字符串
	 * @param o
	 * @return
	 */
	private String objToString(Object o){
		return o==null ? null : o.toString();
	}
	/**
	 * 获取需要提交追位系统的队列
	 * @param jpTjsq
	 * @return
	 */
	public List<JpTjsq> getDtjDlList(JpTjsq jpTjsq){
		return getMyBatisDao().getDtjDlList(jpTjsq);
	}
	/**
	 * 查询追位成功采用
	 * @param zwcgwcl
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page zwcgcyQueryPage(Map<String, Object> zwcgwcl, int pageNum, int pageSize) {
		Page page =new Page(pageNum,pageSize);
		List<Map<String, Object>> list = this.getMyBatisDao().getzwcgcyList(zwcgwcl);
		for(Map<String,Object> m : list){
			BigDecimal tpf = (BigDecimal)m.get("TPF");
			BigDecimal cj = (BigDecimal)m.get("CJ");
			if(null!=tpf && null!=cj){
				double lr = Arith.sub(tpf.doubleValue(), cj.doubleValue());//利润
				m.put("LR", lr);
			}
			BigDecimal tpfl = (BigDecimal)m.get("TPFL");
			String fl = Arith.mul(tpfl.doubleValue(), 100)+"%";
			m.put("FL", fl);
		}
		int totalCount = this.getMyBatisDao().getzwcgcyTotalCount(zwcgwcl);
		page.setTotalCount(totalCount);
		page.setList(list);
		return page;
	}
	
//	/**
//	 * 查询所有追位成功采用订单
//	 * @param cgcy
//	 * @return
//	 */
//	public List<Map<String, Object>> getAllzwcgcyList(Map<String, Object> cgcy){
//		List<Map<String, Object>> list = this.getMyBatisDao().getAllzwcgcyList(cgcy);
//		for(Map<String,Object> m : list){
//			BigDecimal tpf = (BigDecimal)m.get("TPF");
//			BigDecimal cj = (BigDecimal)m.get("CJ");
//			if(null!=tpf && null!=cj){
//				double lr = Arith.sub(tpf.doubleValue(), cj.doubleValue());//利润
//				m.put("LR", lr);
//			}
//			BigDecimal tpfl = (BigDecimal)m.get("TPFL");
//			String fl = Arith.mul(tpfl.doubleValue(), 100)+"%";
//			m.put("FL", fl);
//		}
//		return list;
//	}
	/**
	 * 追位成功取消
	 * @param zwcgwcl
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page zwcgqxQueryPage(Map<String, Object> zwcgwcl, int pageNum, int pageSize) {
		Page page =new Page(pageNum,pageSize);
		List<Map<String, Object>> list = this.getMyBatisDao().getzwcgqxList(zwcgwcl);
		for(Map<String,Object> m : list){
			BigDecimal tpf = (BigDecimal)m.get("TPF");
			BigDecimal cj = (BigDecimal)m.get("CJ");
			if(null!=tpf && null!=cj){
				double lr = Arith.sub(tpf.doubleValue(), cj.doubleValue());//利润
				m.put("LR", lr);
			}
			BigDecimal tpfl = (BigDecimal)m.get("TPFL");
			String fl = Arith.mul(tpfl.doubleValue(), 100)+"%";
			m.put("FL", fl);
		}
		int totalCount = this.getMyBatisDao().getzwcgqxTotalCount(zwcgwcl);
		page.setTotalCount(totalCount);
		page.setList(list);
		return page;
	}
	
	/**
	 * 追位成功预留
	 * @param zwcgwcl
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page zwcgylQueryPage(Map<String, Object> zwcgwcl, int pageNum, int pageSize) {
		Page page =new Page(pageNum,pageSize);
		List<Map<String, Object>> list = this.getMyBatisDao().getzwcgylList(zwcgwcl);
		for(Map<String,Object> m : list){
			BigDecimal tpf = (BigDecimal)m.get("TPF");
			BigDecimal cj = (BigDecimal)m.get("CJ");
			if(null!=tpf && null!=cj){
				double lr = Arith.sub(tpf.doubleValue(), cj.doubleValue());//利润
				m.put("LR", lr);
			}
			BigDecimal tpfl = (BigDecimal)m.get("TPFL");
			String fl = Arith.mul(tpfl.doubleValue(), 100)+"%";
			m.put("FL", fl);
		}
		int totalCount = this.getMyBatisDao().getzwcgylTotalCount(zwcgwcl);
		page.setTotalCount(totalCount);
		page.setList(list);
		return page;
	}
	
	/**
	 * 根据申请单号和商户编号获取追位订单
	 * @param shbh
	 * @param sqdh
	 * @return
	 */
	public JpTjsq gettjsq(String shbh,String sqdh){
		return this.getMyBatisDao().gettjsq(shbh, sqdh);
	}
	
	/**
	 * 批量取消时根据查询条件返回追位订单list
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getzwdlList(Map<String, Object> map){
		return this.getMyBatisDao().getzwdlList(map);
	}
	/**
	 * 获取机票订单已取消且未取消的追位单
	 * @param sqdate
	 * @return
	 */
	public List<JpTjsq> getDqxForJpddqx(Date sqdate,String shbh){
		return getMyBatisDao().getDqxForJpddqx(sqdate,shbh);
	}
	/**
	 * 出票后取消原追位单(申请时间<出票时间)
	 * @param sqdate
	 * @return
	 */
	public List<JpTjsq> getDqxForYcp(Date sqdate,String shbh){
		return getMyBatisDao().getDqxForYcp(sqdate,shbh);
	}
	/**
	 * 退废后取消原追位单
	 * @param sqdate
	 * @param tfzt
	 * @return
	 */
	public List<JpTjsq> getDqxForTp(Date sqdate,String shbh){
		Map<String, String> zwParamMap=jpTjsqJcgzServiceImpl.getTjsqJcgzMap(shbh);
		String tfqx = ZwUtil.getValueByMap(zwParamMap, "TFSFQX", "0");
		if("2".equals(tfqx)){
			return null;
		}else if("1".equals(tfqx)){//申请并复核就取消
			return getMyBatisDao().getDqxForTpfh(sqdate,shbh);
		}else {//申请就取消
			return getMyBatisDao().getDqxForTpsq(sqdate,shbh);
		}
	}
	/**
	 * 改签后取消原追位单
	 * @param sqdate
	 * @param tfzt
	 * @return
	 */
	public List<JpTjsq> getDqxForGq(Date sqdate,String shbh){
		Map<String, String> zwParamMap=jpTjsqJcgzServiceImpl.getTjsqJcgzMap(shbh);
		String gqqx = ZwUtil.getValueByMap(zwParamMap, "GQSFQX", "0");
		if("2".equals(gqqx)){
			return null;
		}else if("1".equals(gqqx)){//申请并复核就取消
			return getMyBatisDao().getDqxForGqfh(sqdate,shbh);
		}else {//申请就取消
			return getMyBatisDao().getDqxForGqsq(sqdate,shbh);
		}
	}
	/**
	 * 获取已失效的追位单
	 * @param sqdate
	 * @return
	 */
	public List<JpTjsq> getDqxForYsx(Date sqdate,String shbh){
		return getMyBatisDao().getDqxForYsx(sqdate,shbh);
	}
	/**
	 * 未出票过了指定时间后取消原追位单
	 * @param sqdate
	 * @param tfzt
	 * @return
	 */
	public List<JpTjsq> getDqxForYdQf(Date sqdate,Date cfsj,String shbh){
		return getMyBatisDao().getDqxForYdQf(sqdate, cfsj, shbh);
	}
	
	/**
	 * 已出票过了指定时间后取消原追位单
	 * @param sqdate
	 * @param tfzt
	 * @return
	 */
	public List<JpTjsq> getDqxForCpqf(Date sqdate,Date cfsj,String shbh){
		return getMyBatisDao().getDqxForCpqf(sqdate, cfsj, shbh);
	}
	/**
	 * 获取未追位完成的且已出票的追位单
	 * @param sqdate
	 * @param shbh
	 * @return
	 */
	public List<JpTjsq> getYcpWwc(Date sqdate,String shbh){
		return getMyBatisDao().getYcpWwc(sqdate, shbh);
	}
	
	public void updateZwcg(String shbh,KwStatusResponse response) {
		for (KwOrder bean : response.getOrders()) {
			JpTjsq t=new JpTjsq();
			t.setSqdh(bean.getSqdh());
			t.setShbh(shbh);
			JpTjsq jpTjsq=this.getEntityById(t);
			if(jpTjsq==null){
				continue;//追位单不存在
			}
			if(DictEnum.ZWDLSQZT.ZWC.getValue().equals(jpTjsq.getSqZt())){
				continue;//追位状态已更新
			}
			JpTjsqCjr tcjr=new JpTjsqCjr();
			tcjr.setShbh(shbh);
			tcjr.setSqdh(bean.getSqdh());
			List<JpTjsqCjr> cjrList=jpTjsqCjrServiceImpl.getMyBatisDao().select(tcjr);
			Map<String, JpTjsqCjr> cjrMap=new HashMap<String, JpTjsqCjr>();
			for (JpTjsqCjr cjr : cjrList) {
				cjrMap.put(cjr.getId(), cjr);
			}
			boolean needUpdate=false;
			String allPnr="";
			for (KwCjr oneCjr : bean.getCjrList()) {
				JpTjsqCjr cjr=cjrMap.get(oneCjr.getId());
				if(StringUtils.isNotBlank(cjr.getPnrNo())){
					continue;//该乘机人已追位了，且更新了状态
				}else {
					needUpdate=true;
				}
				cjr.setSfzwcg(DictEnum.ZWCJRZWZT.ZCG.getValue());
				cjr.setPnrNo(oneCjr.getPnr());
				allPnr+=","+oneCjr.getPnr();
				cjr.setCw(oneCjr.getCabin());
				double price=NumberUtils.toDouble(oneCjr.getPrice(), 0);
				if(price==0){//没获取到价格，则通过cds维护的运价计算，根据Y舱价和折扣率计算
					
				}
				cjr.setPrice(price);
				jpTjsqCjrServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(cjr);
			}
			if(needUpdate){
				jpTjsq.setBy2(allPnr.substring(1));
				String sqZt=cjrList.size()== bean.getCjrList().size() ? DictEnum.ZWDLSQZT.ZWC.getValue() : DictEnum.ZWDLSQZT.BFZ.getValue();
				jpTjsq.setSqZt(sqZt);
				jpTjsq.setDpDatetime(VeDate.getNow());
				if(StringUtils.isNotBlank(bean.getHbh())){
					jpTjsq.setZwhbh(bean.getHbh());
					jpTjsq.setQftime(VeDate.strToDateLong(bean.getDatetime()));
				}
				if (StringUtils.isNotBlank(bean.getOffice())) {
					jpTjsq.setOffice(bean.getOffice());
				}
				this.getMyBatisDao().updateByPrimaryKeySelective(jpTjsq);
				if("1".equals(jpTjsq.getSfwbdr())){
					JpTjsqDr tdr=new JpTjsqDr();
					tdr.setSqdh(bean.getSqdh());
					tdr.setShbh(shbh);
					List<JpTjsqDr> jptjsqdrList=jpTjsqDrServiceImpl.getMyBatisDao().select(tdr);
					for (JpTjsqDr jpTjsqDr : jptjsqdrList) {
						jpTjsqDr.setSqZt(sqZt);
						jpTjsqDrServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(jpTjsqDr);
					}
				}
			}
		}
	}
	/**
	 * 取消本地订单
	 * @param jpTjsq
	 * @param shbh
	 * @param qxyy
	 */
	@Transactional
	public void cancelBdzwd(JpTjsq jpTjsq,String shbh,String qxyy){
		jpTjsq.setSqZt(DictEnum.ZWDLSQZT.YQX.getValue());
		jpTjsq.setZdqxyy(qxyy);
		getMyBatisDao().updateByPrimaryKeySelective(jpTjsq);
		if("1".equals(jpTjsq.getSfwbdr())){
			JpTjsqDr tdr=new JpTjsqDr();
			tdr.setSqdh(jpTjsq.getSqdh());
			tdr.setShbh(shbh);
			List<JpTjsqDr> jptjsqdrList=jpTjsqDrServiceImpl.getMyBatisDao().select(tdr);
			for (JpTjsqDr jpTjsqDr : jptjsqdrList) {
				jpTjsqDr.setSqZt(DictEnum.ZWDLSQZT.YQX.getValue());
				jpTjsqDrServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(jpTjsqDr);
			}
			JpTjsqCjr cjrB=new JpTjsqCjr();
			cjrB.setSqdh(jpTjsq.getSqdh());
			cjrB.setShbh(shbh);
			List<JpTjsqCjr> cjrList=jpTjsqCjrServiceImpl.getMyBatisDao().select(cjrB);
			for (JpTjsqCjr cjr : cjrList) {
				cjr.setSfzwcg(DictEnum.ZWCJRZWZT.YQX.getValue());
				cjr.setQxSm(qxyy);
			}
		}
	}
	
}
