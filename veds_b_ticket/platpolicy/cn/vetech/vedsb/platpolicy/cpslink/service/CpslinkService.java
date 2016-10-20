package cn.vetech.vedsb.platpolicy.cpslink.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.modules.utils.Exceptions;
import org.vetech.core.modules.utils.Identities;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.Shcsb;
import cn.vetech.vedsb.common.entity.Shshb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.ShcsbServiceImpl;
import cn.vetech.vedsb.common.service.base.ShshbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcFzsz;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcFzszServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.platpolicy.PnrAuthService;
import cn.vetech.vedsb.platpolicy.PolicyItem;
import cn.vetech.vedsb.platpolicy.PolicyMatchUtil;
import cn.vetech.vedsb.platpolicy.cpslink.DsResponse;
import cn.vetech.vedsb.platpolicy.cpslink.PlatOrderParam;
import cn.vetech.vedsb.platpolicy.cpslink.PlatSearchParam;
import cn.vetech.vedsb.platpolicy.cpslink.request.GetRealtimePolicyRequest;
import cn.vetech.vedsb.platpolicy.cpslink.request.SubmitOrderRequest;
import cn.vetech.vedsb.platpolicy.cpslink.request.TicketRefundRequest;
import cn.vetech.vedsb.platpolicy.cpslink.request.TptzcZh;
import cn.vetech.vedsb.platpolicy.cpslink.response.GetRealtimePolicyRes;
import cn.vetech.vedsb.platpolicy.cpslink.response.RealtimePolicy;
import cn.vetech.vedsb.platpolicy.cpslink.response.SubmitOrderRes;
import cn.vetech.vedsb.platpolicy.cpslink.response.TicketRefundRes;
import cn.vetech.vedsb.utils.DataUtils;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.PlatCode;
import cn.vetech.vedsb.utils.SysUtils;

@Service
public class CpslinkService {
	@Autowired
	private CpslinkClient cpslinkClient;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired 
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
	@Autowired
	private JpPtzcFzszServiceImpl jpPtzcFzszServiceImpl;
	@Autowired
	private JpTpdServiceImpl jpTpdServiceImpl;
	@Autowired
	private JpTpdMxServiceImpl jpTpdMxServiceImpl;
	@Autowired
	private ShshbServiceImpl shshbServiceImpl;
	@Autowired
	private ShcsbServiceImpl shcsbServiceImpl;
	@Autowired
	private PnrAuthService pnrAuthService;
	/**
	 * <功能详细描述>
	 * 查询政策
	 * @param searchParam
	 * @return [参数说明]
	 * 
	 * @return GetRealtimePolicyRes [返回类型说明]
	 * @throws Exception 
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public GetRealtimePolicyRes getRealtimePolicy(PlatSearchParam searchParam) throws Exception{
		Shyhb yhb=searchParam.getYhb();
		String ddbh=searchParam.getDdbh();
		String isSpecial=searchParam.getIsSpecial();
		JpPtLog ptLog=new JpPtLog();
		String shbh=yhb.getShbh();
		String service="getRealtimePolicy";
		ptLog.setShbh(shbh);
		ptLog.setYhbh(yhb.getBh());
		ptLog.setDdbh(ddbh);
		GetRealtimePolicyRes res=null;
		try {
			JpKhdd jpKhdd=genJpKhdd(ddbh, shbh);
			ptLog.setLogDate(new Date());
			String platcodes=searchParam.getPlatcodes();
			if(platcodes.length()>10){
				ptLog.setPtzcbs("search");//不能为空
			}else{
				ptLog.setPtzcbs(platcodes);
			}
			ptLog.setPnrNo(jpKhdd.getCgPnrNo());
			ptLog.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
			ptLog.setYwlx(DictEnum.PTLOGYWLX.CZC.getValue());
			List<JpKhddCjr> cjrList=genJpKhddCjr(ddbh, shbh);
			List<JpKhddHd> hdList=genJpKhddHd(ddbh, shbh);
			JpKhddHd firstHd=hdList.get(0);//第一段航段
			JpKhddHd lastHd=hdList.get(hdList.size()-1);//最后一段航段
			String hclx=jpKhdd.getHclx();//1单程，2往返，3联程，4缺口
			String cjrxm=null;
			String cjrzjhm=null;
			for (JpKhddCjr cjr : cjrList) {
				if("3".equals(cjr.getCjrlx()) || "2".equals(cjr.getCjrlx())){
					throw new Exception("平台不支持儿童和婴儿订单！");
				}
				cjrxm=pinJie(cjrxm, cjr.getCjr());
				cjrzjhm=pinJie(cjrzjhm, cjr.getZjhm());
			}
			String cjrlx="1";//平台只支持成人
			if(("2".equals(hclx) || "3".equals(hclx)) && hdList.size()!=2){
				throw new Exception("联程和往返只支持两段航程！");
			}
			GetRealtimePolicyRequest policyRequest=new GetRealtimePolicyRequest();
			String fromDatetime=StringUtils.substring(firstHd.getCfsj(), 0,16);
			policyRequest.setFromDatetime(fromDatetime);
			policyRequest.setFromCity(firstHd.getCfcity());
			policyRequest.setToCity(lastHd.getDdcity());
			if("3".equals(hclx) || "4".equals(hclx)){
				policyRequest.setTransitCity(lastHd.getCfcity());
			}
			policyRequest.setPassengerType(cjrlx);
			policyRequest.setTravelType(hclx);
			if("2".equals(hclx) || "3".equals(hclx) || "4".equals(hclx)){
				String fcrq=StringUtils.substring(lastHd.getCfsj(), 0,16);
				policyRequest.setBackFlightDate(fcrq);
			}
			String hbh=jpKhdd.getCgHbh().replace("*", "");
			policyRequest.setAirways(hbh.substring(0,2));
			String flightNo=null;
			String cw=null;
			for(JpKhddHd hdb : hdList){
				flightNo=pinJie(flightNo, hdb.getCgHbh());
				cw=pinJie(cw, hdb.getCgCw());
			}
			policyRequest.setFlightNo(flightNo);
			policyRequest.setSeatClass(cw);
			String cgzdj=objToStr(jpKhdd.getCgZdj());
			policyRequest.setTicketPrice(cgzdj);
			//addPnrnr(jpKhdd, cjrlx, yhb, policyRequest);//添加pnr内容和pat内容
			JpKhdd jpkhddRted = this.jpKhddServiceImpl.khddRtPnr(yhb, ddbh);
			policyRequest.setPnrContent(jpkhddRted.getPnrLr());
			policyRequest.setPatContent(jpkhddRted.getPatLr());
			if(StringUtils.isBlank(isSpecial)){//0返回普通政策 1返回特殊政策 3是都返回
				isSpecial="3";
			}
			
			policyRequest.setIsSpecial(isSpecial);
			String cfsj=firstHd.getCfsj().substring(11);
			String ddsj=lastHd.getDdsj().substring(11);
			policyRequest.setCfsj(cfsj);
			policyRequest.setDdsj(ddsj);
			JpKhddCjr cjr=cjrList.get(0);
			policyRequest.setScny(objToStr(cjr.getCgZdj()));
			long jsry=cjr.getCgJsf()+cjr.getCgTax();
			policyRequest.setJsry(objToStr(jsry));
			policyRequest.setAdultno(objToStr(jpKhdd.getCjrs()));
			policyRequest.setPassenger(cjrxm);
			policyRequest.setCardId(cjrzjhm);
			policyRequest.setPnrNo(jpKhdd.getCgPnrNo());
			List<TptzcZh> ptzhs=genPtzh(shbh,searchParam.getPlatcodes());
			policyRequest.setPtzczhList(ptzhs);
			ptLog.add("|查询政策开始：");
			ptLog.add("查询政策请求参数:"+policyRequest.toString());
			policyRequest.setService(service);
			policyRequest.setShbh(shbh);
			try {
				res=cpslinkClient.send(policyRequest,GetRealtimePolicyRes.class);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("调用查询平台政策接口失败！");
			}
			if(res==null){
				throw new Exception("调用查询平台政策接口失败！");
			}
			ptLog.add("查询政策返回:"+res.toString());
		} catch (Exception e) {
			e.printStackTrace();
			String error="查询政策失败:"+Exceptions.getMessageAsString(e);
			error=VeStr.substrByte(error,150);
			ptLog.setCzsm(error);
			ptLog.add("|"+error);
			throw e;
		}finally{
			try {
				jpPtLogServiceImpl.insertLog(ptLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	/**
	 * <功能详细描述>
	 * 将link返回的数据转成公共bean
	 * @param list
	 * @return [参数说明]
	 * 
	 * @return List<PolicyItem> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public List<PolicyItem> linkPolicyToItem(List<RealtimePolicy> policyList,String platcode) throws Exception{
		List<PolicyItem> list = new ArrayList<PolicyItem>();
		for(RealtimePolicy policy : policyList){
			PolicyItem item = new PolicyItem();
			item.setPtzcbs(platcode);
			item.setId(policy.getPolicyId());
			String productType = policy.getProductType();//产品类型--=0 普通政策 =1 特殊政策
			item.setIsspecmark(productType);
			String rebate = policy.getRebate();//平台返点
			item.setRate(NumberUtils.toDouble(rebate));
			item.setRateshow(NumberUtils.toDouble(rebate));//用于页面显示(能够看到的返点)
			String ifChangePnr = policy.getIfChangePnr();//是否换编码--是否换编码出票：1是，0否
			item.setChangerecord(ifChangePnr);
			String jgxx = policy.getPriceOption();
			item.setShowsjdlfl(NumberUtils.toDouble(policy.getShowsjdlfl()));//代理费率
			item.setSuperProfit(NumberUtils.toDouble(policy.getSuperProfit()));//代理费
			item.setPayfee(NumberUtils.toDouble(policy.getSettlementPrice()));//结算价
			item.setPtmc(PlatCode.getPtname(platcode));//政策名称
			item.setCabin(policy.getCabin());//舱位
			item.setPj_cgj(NumberUtils.toDouble(policy.getScny()));
			item.setTgqgd(policy.getTgqgdStr());
			String bzbz = "";
			if(StringUtils.isNotBlank(policy.getSalesRule())){
				bzbz += StringUtils.trimToEmpty(policy.getSalesRule())+";";
			}
			if("1".equals(policy.getIfAutoEtdz())){//是否自动出票 1是，0否
				bzbz += "系统将自动出票；";
			}
			if ("1".equals(jgxx)) {
				bzbz += "系统将以PAT价格为准；";
			} else if ("0".equals(jgxx)) {
				bzbz += "系统将以政策价格为准；";
			}
			if(StringUtils.isNotBlank(policy.getTicketType())){
				bzbz += "政策类型：" + policy.getTicketType()+";";
				item.setPolicytype(policy.getTicketType());
			}
			item.setNote(bzbz);// 备注
			item.setWorktime(PolicyMatchUtil.getValidDatetime(policy.getEtdzDatetime()));// 供应商上班时间 工作日/非工作日
			item.setChooseOutWorkTime(PolicyMatchUtil.getValidDatetime(policy.getVoidDatetime()));// 废票时间   工作日/非工作日
			String office=StringUtils.trimToEmpty(policy.getOfficeId());
			if(office.length()<6){
				office="";
			}
			item.setOffice(office);
			String billSaleMatch = StringUtils.trimToEmpty(PolicyMatchUtil.convertPmxsjxf(policy.getIfAccordWithPar()));//票面销售价是否相符 1不相符
			if(StringUtils.isNotBlank(billSaleMatch)){
			    if("1".equals(billSaleMatch)){
			        billSaleMatch = "0";
			    }else{
			        billSaleMatch = "1";
			    }
			}
			item.setBillSaleMatch(billSaleMatch);
			item.setZcpj(defval(policy.getTicketPrice(),"0"));
			list.add(item);
		}
		return list;
	}
	 private String defval(String num,String defvalue){
	        return null == num ? defvalue : num;
	 }
	/**
	 * <功能详细描述>
	 * 下单
	 * @param ddbh
	 * @param platcode
	 * @param policyId
	 * @param jsj
	 * @return [参数说明]
	 * 
	 * @return SubmitOrderRes [返回类型说明]
	 * @throws Exception 
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public SubmitOrderRes submitorder(PlatOrderParam orderParam) throws Exception {
		String service="submitorder";
		Shyhb yhb=orderParam.getYhb();
		String ddbh=orderParam.getDdbh();
		JpPtLog ptLog=new JpPtLog();
		String shbh=yhb.getShbh();
		ptLog.setShbh(shbh);
		ptLog.setYhbh(yhb.getBh());
		ptLog.setDdbh(ddbh);
		ptLog.setLogDate(new Date());
		SubmitOrderRes res=null;
		try {
			JpKhdd jpKhdd=genJpKhdd(ddbh, shbh);
			ptLog.setPtzcbs(orderParam.getPlatcode());//不能为空
			ptLog.setPnrNo(jpKhdd.getCgPnrNo());
			ptLog.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
			ptLog.setYwlx(DictEnum.PTLOGYWLX.XD.getValue());
			List<JpKhddCjr> cjrList=genJpKhddCjr(ddbh, shbh);
			List<JpKhddHd> hdList=genJpKhddHd(ddbh, shbh);
			String hclx=jpKhdd.getHclx();//1单程，2往返，3联程，4缺口
			String cjrxm=null;
			String cjrzjhm=null;
			String cardType=null;
			String passengerMobile=null;
			String cjrId=null;
			String csrq=null;
			String xb=null;
			Shshb shshb = yhb.getShshb();
			String lxr=this.getLxr(jpKhdd, shbh, shshb);
			String lxrhm=this.getLxrsj(jpKhdd, shbh, shshb);
			int i=0;
			for (JpKhddCjr cjr : cjrList) {
				if("3".equals(cjr.getCjrlx()) || "2".equals(cjr.getCjrlx())){
					throw new Exception("平台不支持儿童和婴儿订单！");
				}
				cjrxm=pinJie(cjrxm, cjr.getCjr());
				cjrzjhm=pinJie(cjrzjhm, cjr.getZjhm());
				cardType=pinJie(cardType, cjr.getZjlx());
				passengerMobile=pinJie(passengerMobile, cjr.getSjhm());
				cjrId=pinJie(cjrId, ""+(i++));
				csrq=pinJie(csrq, cjr.getCsrq());
				xb=pinJie(xb, cjr.getXb());
			}
			String cjrlx="1";//平台只支持成人
			if(("2".equals(hclx) || "3".equals(hclx)) && hdList.size()!=2){
				throw new Exception("联程和往返只支持两段航程！");
			}
			SubmitOrderRequest policyRequest=new SubmitOrderRequest();
			policyRequest.setShbh(shbh);
			policyRequest.setPnrNo(jpKhdd.getCgPnrNo());
			policyRequest.setBigPnrNo(jpKhdd.getCgHkgsPnr());
			policyRequest.setPolicyId(orderParam.getPolicyId());
			policyRequest.setTravelType(hclx);
			String flightNo=null;
			String cw=null;
			String zcw=null;
			String hc=null;
			String planeType=null;
			String fromDatetime=null;
			String toDatetime=null;
			String cfHzl=null;//出发航站楼
			String ddHzl=null;//到达航站楼
			String fromCity=null;
			String toCity=null;
			for(JpKhddHd hdb : hdList){
				flightNo=pinJie(flightNo, hdb.getCgHbh());
				cw=pinJie(cw, hdb.getCgCw());
				zcw=pinJie(zcw, hdb.getCgZcw());
				hc=pinJie(hc, hdb.getCfcity()+hdb.getDdcity());
				planeType=pinJie(planeType, hdb.getFjjx());
				String cfsj=StringUtils.substring(hdb.getCfsj(), 0,16);
				String ddsj=StringUtils.substring(hdb.getDdsj(), 0,16);
				fromDatetime=pinJie(fromDatetime, cfsj);
				toDatetime=pinJie(toDatetime, ddsj);
				String fromHzl=StringUtils.replace(hdb.getCfhzl(), "-", "");
				String toHzl=StringUtils.replace(hdb.getDdhzl(), "-", "");
				cfHzl=pinJie(cfHzl, fromHzl);
				ddHzl=pinJie(ddHzl, toHzl);
				fromCity=pinJie(fromCity, hdb.getCfcity());
				toCity=pinJie(toCity, hdb.getDdcity());
			}
			policyRequest.setTravelRange(hc);
			policyRequest.setFlightNo(flightNo);
			policyRequest.setPlaneType(planeType);
			policyRequest.setSeatClass(cw);
			policyRequest.setSubSeatClass(zcw);
			policyRequest.setFromDatetime(fromDatetime);
			policyRequest.setToDatetime(toDatetime);
			policyRequest.setTofromterminal(cfHzl);
			policyRequest.setTerminal(ddHzl);
			policyRequest.setPassenger(cjrxm);
			policyRequest.setPassengerType(cjrlx);
			policyRequest.setCardType(cardType);
			policyRequest.setCardId(cjrzjhm);
			policyRequest.setPassengerMobile(passengerMobile);
			JpKhddCjr cjr=cjrList.get(0);
			policyRequest.setScny(objToStr(cjr.getCgZdj()));
			policyRequest.setTax(objToStr(cjr.getCgTax()));
			policyRequest.setYq(objToStr(cjr.getCgJsf()));
			policyRequest.setSettlementPrice(orderParam.getJsj());
			//添加pnr内容和pat内容
			//addPnrnr(jpKhdd, cjrlx, yhb, policyRequest);
			JpKhdd jpkhddRted = this.jpKhddServiceImpl.khddRtPnr(yhb, ddbh);
			policyRequest.setPnrContent(jpkhddRted.getPnrLr());
			policyRequest.setPatContent(jpkhddRted.getPatLr());
			policyRequest.setOutOrderNo(ddbh);
			policyRequest.setLinker(lxr);
			policyRequest.setLinkMobile(lxrhm);
			policyRequest.setPlatCode(orderParam.getPlatcode());
			JpPtzcZh ptzcZh=jpPtzcZhServiceImpl.getPtzhByPtBs(orderParam.getPlatcode(), shbh);
			if(ptzcZh==null){
				throw new Exception("没有配置平台账号");
			}
			TptzcZh ptzh=zhPtzh(ptzcZh);
			String autopay=orderParam.getAutopay();
			if(!"1".equals(ptzcZh.getSfkqdk())){//账号没开启自动代扣
				autopay="0";
			}
			policyRequest.setAutopay(autopay);
			policyRequest.setFromCity(fromCity);
			policyRequest.setToCity(toCity);
			policyRequest.setPassengerId(cjrId);
			policyRequest.setCsrq(csrq);
			policyRequest.setXb(xb);
			policyRequest.setNotifyAddress(SysUtils.getTicketUrl());
			policyRequest.setPnrState(jpKhdd.getCgPnrZt());
			policyRequest.setTptzcZh(ptzh);
			ptLog.add("|开始调用平台下单|请求参数:"+policyRequest.toString());
			policyRequest.setService(service);
			res=cpslinkClient.send(policyRequest, SubmitOrderRes.class);
			ptLog.add("下单返回:"+res.toString());
			if(DsResponse.SUCCESS.equals(res.getStatus())){//下单成功
				creatCgdd(jpKhdd, res, yhb, policyRequest, orderParam.getIsQzd());
				JpKhdd khdd=new JpKhdd();
				khdd.setDdbh(ddbh);
				khdd.setCgly("OP");
				khdd.setCgdw(orderParam.getPlatcode());
				khdd.setCgDdbh(res.getOrderNo());
				if("1".equals(res.getOrderStatus())){
					
				}
				jpKhddServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(khdd);
			}
			try {
				if(StringUtils.isNotBlank(res.getOfficeId())){
					pnrAuthService.authPnr(orderParam.getPlatcode(), res.getOfficeId(), jpKhdd, yhb, ptLog, orderParam.getPolicytype(), res.getIfChangePnr());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			String error="查询政策失败:"+Exceptions.getMessageAsString(e);
			error=VeStr.substrByte(error,150);
			ptLog.setCzsm(error);
			ptLog.add("|"+error);
			throw e;
		}finally{
			try {
				jpPtLogServiceImpl.insertLog(ptLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	/**
	 * 首先取订单上的联系人,订单上没有取编号为2023参数,参数中没有最后取商户联系人
	 * @param ddlxr
	 * @param csszlxr
	 * @param shlxr
	 * @return
	 */
	private String getLxr(JpKhdd jpkhdd,String shbh,Shshb shshb){
		String ddlxr = jpkhdd.getNxr();//订单联系人
		if(StringUtils.isNotBlank(ddlxr)){
			return StringUtils.trimToEmpty(ddlxr);
		} 
		Shcsb shcsb = this.shcsbServiceImpl.findCs("2023", shbh);
		String csszlxr = shcsb.getCsz1();//取参数联系人
		if(StringUtils.isNotBlank(csszlxr)){
			return StringUtils.trimToEmpty(csszlxr);
		} 
		if(shshb == null){
			shshb = this.shshbServiceImpl.getShbhByBh(shbh);
		}
		String shlxr = shshb.getLxr();//商户联系人
		if(StringUtils.isNotBlank(shlxr)){
			return StringUtils.trimToEmpty(shlxr);
		}else{
			return "";
		}
	}
	
	private String getLxrsj(JpKhdd jpkhdd,String shbh,Shshb shshb){
		String ddlxhm = jpkhdd.getNxsj();//订单联系手机
		if(StringUtils.isNotBlank(ddlxhm)){
			return StringUtils.trimToEmpty(ddlxhm);
		} 
		Shcsb shcsb = this.shcsbServiceImpl.findCs("2023", shbh);
		String csszlxrhm = shcsb.getCsz2();//取参数联系手机
		if(StringUtils.isNotBlank(csszlxrhm)){
			return StringUtils.trimToEmpty(csszlxrhm);
		} 
		if(shshb == null){
			shshb = this.shshbServiceImpl.getShbhByBh(shbh);
		}
		String shlxrhm = shshb.getLxrsj();//商户联系手机
		if(StringUtils.isNotBlank(shlxrhm)){
			return StringUtils.trimToEmpty(shlxrhm);
		}else{
			return "";
		}
	}
	/**
	 * <功能详细描述>
	 * 退票
	 * @param ddbh
	 * @param shbh
	 * @return [参数说明]
	 * 
	 * @return ticketRefundRequest [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public TicketRefundRes ticketRefundRequest(String tpdh,Shyhb yhb,String platcode,String ptDdbh) throws Exception {
		String service="ticketRefundRequest";
		JpPtLog ptLog=new JpPtLog();
		String shbh = yhb.getShbh();
		ptLog.setShbh(shbh);
		ptLog.setYhbh(yhb.getBh());
		ptLog.setTfid(tpdh);
		ptLog.setLogDate(new Date());
		ptLog.setPtzcbs(platcode);
		ptLog.setDdlx("2");
		TicketRefundRes res = null;
		try {
			JpTpd jptpd = this.jpTpdServiceImpl.getJpTpdByTpdh(tpdh, shbh);
			if(jptpd==null){
				throw new Exception("退票单号对应的退票记录不存在!");
			}
			String tply = jptpd.getXsTply();
			JpPtzcZh ptzcZh=jpPtzcZhServiceImpl.getPtzhByPtBs(platcode, shbh);
			if(ptzcZh==null){
				throw new Exception("没有配置平台账号");
			}
			TptzcZh ptzh=zhPtzh(ptzcZh);
			String ddbh = jptpd.getDdbh();
			String tfzt = jptpd.getCgTpzt();
			//JpCgdd jpcgdd = this.jpCgddServiceImpl.getJpCgddByDdbh(ddbh, shbh);
			if("3".equals(tfzt)){
				ptLog.add("退废票申请失败,已经到平台申请退废票成功，无需再次申请");
				return null;
			}
			ptLog.setDdbh(ddbh);
			List<JpKhddHd> hdList=genJpKhddHd(ddbh, shbh);
			String hc = "";
			for(JpKhddHd hdb : hdList){
				hc=pinJie(hc, hdb.getCfcity()+hdb.getDdcity());
			}
			String pnrstatus = jptpd.getXsPnrZt();//电商系统pnr状态
			String refundType = jptpd.getCgBllx();//退废类型
			//String refundReasondm = jptpd.getCgTply();
			String refundReason = jptpd.getCgQtly();
			String certificateUrl = jptpd.getCgTpzm();
			List<JpTpdMx> mxs = this.jpTpdMxServiceImpl.getJpTpdMxByTpdh(tpdh, shbh);
			String tknos = "";
			String cjrs = "";
			String zjhms = "";
			if(mxs!=null&&CollectionUtils.isNotEmpty(mxs)){
				for(JpTpdMx mx :mxs){
					tknos=pinJie(tknos, mx.getTkno());
					cjrs = pinJie(cjrs,mx.getCjr());
					zjhms = pinJie(zjhms,mx.getZjhm());
				}
			}
			TicketRefundRequest request = new TicketRefundRequest();
			request.setShbh(shbh);
			request.setXtpnrzt(pnrstatus);
			request.setRefundType(refundType);
			request.setTravelRange(hc);
			request.setTicketNo(tknos);
			request.setPassenger(cjrs);
			request.setRefundReason(refundReason);
			request.setRefundReasondm(tply);
			request.setCertificateUrl(certificateUrl);
			request.setPlatCode(platcode);
			request.setPnrCjrZjh(zjhms);
			request.setTptzcZh(ptzh);
			request.setOutOrderNo(ddbh);
			request.setOrderNo(ptDdbh);//平台订单编号
			//request.setPnrno(jptpd.getCgPnrNo());
			ptLog.add("|开始调用平台退票|请求参数:"+request.toString());
			request.setService(service);
			JpCgdd cgdd = this.jpCgddServiceImpl.getJpCgddByCgly(ddbh, "OP", shbh);
			if(cgdd == null){
				ptLog.add("平台退废票失败,根据订单编号和采购来源查询采购订单为空");
				throw new Exception("根据订单编号和采购来源查询采购订单为空");
			}
			res=cpslinkClient.send(request, TicketRefundRes.class);
			if(TicketRefundRes.SUCCESS.equals(res.getStatus())){//退废申请成功
				ptLog.add("平台退废票申请成功");
				cgdd.setJyzt("5");//提交成功
				this.jpCgddServiceImpl.update(cgdd);
				jptpd.setCgTpzt("2");//采购退废已提交
				String refundNo = res.getRefundNo();
				if(StringUtils.isNotBlank(refundNo)){
					jptpd.setCgTpdh(refundNo);
				}
				try {
					this.jpTpdServiceImpl.update(jptpd);//更新退票单,修改采购退废票状态为已办理
				} catch (Exception e) {
					throw e;
				}
			}
			ptLog.add("退废票返回:"+res.toString());
		} catch (Exception e) {
			e.printStackTrace();
			String error="平台退废票失败:"+Exceptions.getMessageAsString(e);
			error=VeStr.substrByte(error,150);
			ptLog.setCzsm(error);
			ptLog.add("|"+error);
			throw e;
		}finally{ 
			try {
				jpPtLogServiceImpl.insertLog(ptLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	/**
	 * <功能详细描述>
	 * 写入采购订单
	 * @param jpKhdd
	 * @param jpCgdd [参数说明]
	 * @throws Exception 
	 */
	private void creatCgdd(JpKhdd jpKhdd,SubmitOrderRes res,Shyhb yhb,SubmitOrderRequest policyRequest,String isQzd) throws Exception{
		JpCgdd cgdd=new JpCgdd();
		cgdd.setId(Identities.randomLong()+"");
		cgdd.setDdbh(jpKhdd.getDdbh());
		cgdd.setCjdatetime(new Date());
		cgdd.setCjUserid(yhb.getBh());
		cgdd.setOrderhkgs(jpKhdd.getHkgs());
		cgdd.setHkgs(jpKhdd.getHkgs());
		cgdd.setHkgsDdbh(res.getOrderNo());
		cgdd.setPnrno(jpKhdd.getCgPnrNo());
		cgdd.setHkgsPnrno(jpKhdd.getCgHkgsPnr());
		cgdd.setHbh(jpKhdd.getCgHbh());
		cgdd.setCw(policyRequest.getSeatClass());
		double zdj=jpKhdd.getCgZdj();
		cgdd.setCwpj(zdj);
		cgdd.setIfqzdcp(isQzd);
		cgdd.setYsmcs(0);
		cgdd.setIfxepnr("1");
		cgdd.setCgly("OP");
		cgdd.setCgdw(policyRequest.getPlatCode());
		cgdd.setShbh(jpKhdd.getShbh());
		String ptddzt=res.getOrderStatus();//平台订单状态--1已支付，2自动代扣失败，3待支付
		if("1".equals(ptddzt)){
			cgdd.setZt("2");
			cgdd.setPaykind(res.getPayment());
			Double zfje=NumberUtils.toDouble(res.getPaymentAmount());
			cgdd.setZfje(zfje);
			cgdd.setJyzt("1");
			JpPtzcFzsz zfdysz=jpPtzcFzszServiceImpl.genZfkmByDjm("2",jpKhdd.getShbh(), res.getPayment(), policyRequest.getPlatCode());
			if(zfdysz!=null){
				cgdd.setCgZfkm(zfdysz.getXtZfkm());
			}
		}else {
			cgdd.setZt("0");
			cgdd.setJyzt("0");
		}
		jpCgddServiceImpl.insert(cgdd);
	}
	/**
	 * <功能详细描述>
	 * 拼接数据
	 * @param orgStr
	 * @param prop
	 */
	private String pinJie(String orgStr,String prop){
		if(orgStr==null){
			orgStr=StringUtils.trimToEmpty(prop);
		}else {
			orgStr+="|"+StringUtils.trimToEmpty(prop);
		}
		return orgStr;
	}
	/**
	 * 查询机票订单
	 */
	private JpKhdd genJpKhdd(String ddbh,String shbh){
		JpKhdd t=new JpKhdd();
		t.setDdbh(ddbh);
		t.setShbh(shbh);
		return jpKhddServiceImpl.getEntityById(t);
	}
	/**
	 * 查询机票订单航段
	 */
	private List<JpKhddHd> genJpKhddHd(String ddbh,String shbh){
		JpKhddHd t=new JpKhddHd();
		t.setDdbh(ddbh);
		t.setShbh(shbh);
		t.setOrderBy("cfsj asc");
		return jpKhddHdServiceImpl.getMyBatisDao().select(t);
	}
	/**
	 * 查询机票乘机人
	 */
	private List<JpKhddCjr> genJpKhddCjr(String ddbh,String shbh){
		JpKhddCjr t=new JpKhddCjr();
		t.setDdbh(ddbh);
		t.setShbh(shbh);
		return jpKhddCjrServiceImpl.getMyBatisDao().select(t);
	}
	/**
	 * <功能详细描述>
	 * 获取pnr内容
	 * @param jpKhdd
	 * @param cjrlx
	 * @param yhb
	 * @param request [参数说明]
	 */
	private void addPnrnr(JpKhdd jpKhdd,String cjrlx,Shyhb yhb,Object request) throws Exception{
		String ddbh=jpKhdd.getDdbh();
		String shbh=yhb.getShbh();
		String patnr=jpKhdd.getPatLr();
		String pnrnr=jpKhdd.getPnrLr();
		if(StringUtils.isBlank(patnr) || StringUtils.isBlank(pnrnr)){
			JpPz jppz = jpPzServiceImpl.getJpPzByShbh(shbh);
			if(jppz==null){
				throw new Exception("没获取到商户"+shbh+"的pid配置");
			}
			Pnr pnrObj=DataUtils.getPnrPat(jpKhdd.getCgPnrNo(), cjrlx,jppz ,yhb,"");
			if(pnrObj==null){
				return;
			}
			pnrnr=pnrObj.getPnr_lr();
			patnr=pnrObj.getPat();
			JpKhdd updd=new JpKhdd();
			updd.setDdbh(ddbh);
			updd.setPnrLr(pnrnr);
			updd.setPatLr(patnr);
			updd.setCgHkgsPnr(pnrObj.getBigPnrno());
			updd.setShbh(shbh);
			jpKhddServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(updd);
		}
		pnrnr = StringUtils.trimToEmpty(pnrnr).replaceAll("\r\n", "\n");
		patnr = StringUtils.trimToEmpty(patnr).replaceAll("\r", "\n");
		jpKhdd.setPnrLr(pnrnr);
		jpKhdd.setPatLr(patnr);
		BeanUtils.setProperty(request, "pnrContent", pnrnr);
		BeanUtils.setProperty(request, "patContent", patnr);
	}
	/**
	 * <功能详细描述>
	 * 获取平台账号
	 * @param shbh
	 * @param platcodes
	 * @return List<JpPtzcZh> [返回类型说明]
	 * @throws Exception 
	 */
	private List<TptzcZh> genPtzh(String shbh,String platcodes) throws Exception{
		List<JpPtzcZh> ptzhs=jpPtzcZhServiceImpl.genPtzhByPtbss(shbh, platcodes);
		if(CollectionUtils.isEmpty(ptzhs)){
			throw new Exception("没有配置平台账号");
		}
		List<TptzcZh> list=new ArrayList<TptzcZh>();
		for(JpPtzcZh ptzh : ptzhs){
			TptzcZh zh=zhPtzh(ptzh);
			list.add(zh);
		}
		return list;
	}
	private TptzcZh zhPtzh(JpPtzcZh ptzh){
		TptzcZh zh=new TptzcZh();
		zh.setPlatCode(ptzh.getPtzcbs());
		zh.setPwd1(ptzh.getPwd1());
		zh.setPwd2(ptzh.getPwd2());
		zh.setSfhbmcp(ptzh.getXx5());
		zh.setShbh(ptzh.getShbh());
		zh.setUrl1(ptzh.getUrl1());
		zh.setUrl2(ptzh.getUrl2());
		zh.setUrl3(ptzh.getGyurl1());
		zh.setUrl4(ptzh.getGyurl2());
		zh.setUser1(ptzh.getUser1());
		zh.setUser2(ptzh.getUser2());
		zh.setXx7(ptzh.getXx7());
		zh.setZddklx(ptzh.getZddklx());
		return zh;
	}
	private String objToStr(Object o){
		return o==null ? "" : o.toString();
	}
}
