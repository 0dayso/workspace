package cn.vetech.vedsb.platpolicy.cps.service;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.Identities;
import org.vetech.core.modules.utils.ParseXml;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.common.entity.Shcsb;
import cn.vetech.vedsb.common.entity.Shshb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.ShcsbServiceImpl;
import cn.vetech.vedsb.common.service.base.ShshbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.platpolicy.GetPolicyUtil;
import cn.vetech.vedsb.platpolicy.PnrAuthService;
import cn.vetech.vedsb.platpolicy.PolicyItem;
import cn.vetech.vedsb.platpolicy.PolicyMatchUtil;
import cn.vetech.vedsb.platpolicy.cps.CpsOrderParam;
import cn.vetech.vedsb.platpolicy.cps.CpsSearchParam;
import cn.vetech.vedsb.platpolicy.cps.request.CpsAccount;
import cn.vetech.vedsb.platpolicy.cps.request.pay.GetOrderPayLinkRequest;
import cn.vetech.vedsb.platpolicy.cps.request.pay.IsAvailableRequest;
import cn.vetech.vedsb.platpolicy.cps.request.pay.PayNotifyRequest;
import cn.vetech.vedsb.platpolicy.cps.request.ticket.CreateOrderRequest;
import cn.vetech.vedsb.platpolicy.cps.request.ticket.EtdzRequest;
import cn.vetech.vedsb.platpolicy.cps.request.ticket.GetRealtimePolicyReq;
import cn.vetech.vedsb.platpolicy.cps.request.ticket.GetTicketByDetrRequest;
import cn.vetech.vedsb.platpolicy.cps.request.ticket.TicketRefundCpsRequest;
import cn.vetech.vedsb.platpolicy.cps.request.ticket.TicketRefundNoticeRequest;
import cn.vetech.vedsb.platpolicy.cps.response.pay.CpsShzhData;
import cn.vetech.vedsb.platpolicy.cps.response.pay.CpsZhResult;
import cn.vetech.vedsb.platpolicy.cps.response.pay.GetOrderPayLinkResponse;
import cn.vetech.vedsb.platpolicy.cps.response.pay.IsAvailableResponse;
import cn.vetech.vedsb.platpolicy.cps.response.ticket.CreateOrderResponse;
import cn.vetech.vedsb.platpolicy.cps.response.ticket.GetRealtimePolicyRes;
import cn.vetech.vedsb.platpolicy.cps.response.ticket.GetTicketByDetrResponse;
import cn.vetech.vedsb.platpolicy.cps.response.ticket.RealtimePolicy;
import cn.vetech.vedsb.platpolicy.cps.response.ticket.TicketRefundResponse;
import cn.vetech.vedsb.utils.DataUtils;
import cn.vetech.vedsb.utils.DesEncode;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.PlatCode;
import cn.vetech.web.vedsb.SessionUtils;

@Service
public class CpsService {
	public static String plat = "10100000";
	@Autowired
	private CpsInvoke cpsInvoke;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired 
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private ShcsbServiceImpl shcsbServiceImpl;
	@Autowired
	private ShshbServiceImpl shshbServiceImpl;
	@Autowired
	private PnrAuthService pnrAuthService;
	public static String BUYER_PLAT = "10100000";

	/**
	 * 验证余额
	 */
	public IsAvailableResponse isAvailable(IsAvailableRequest request, CpsAccount account) throws Exception {
		request.setService("isAvailable");
		IsAvailableResponse iar = cpsInvoke.send(request, account, IsAvailableResponse.class);
		return iar;
	}
	/**
	 * 获取账户信息
	 * @throws Exception 
	 */
	public List<CpsShzhData> genShzhList(IsAvailableRequest request, CpsAccount account) throws Exception {
		request.setService("getShzhglList");
		IsAvailableResponse iar = null;
		try {
			cpsInvoke.send(request, account, IsAvailableResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("与CPS通讯异常");
		}
		if("-1".equals(iar.getStatus())){
			throw new Exception(iar.getErrorMessage());
		}
		String resXml=iar.getXml();
		CpsZhResult result=null;
		try {
			result=(CpsZhResult) XmlUtils.fromXml(resXml,CpsZhResult.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("解析xml异常");
		}
		if(!"OK".equals(result.getStatus().getStatusCode())){
			throw new Exception(result.getStatus().getStatusMessage());
		}
		return result.getZhdata();
	}
	/**
	 * 获取追位账户
	 * @throws Exception 
	 */
	public CpsShzhData getZwZh(String shbh,String zhbh) throws Exception{
		CpsAccount account=getCpsAccount(shbh);
		IsAvailableRequest request=new IsAvailableRequest();
		StringBuffer xmldata = new StringBuffer();
		xmldata.append("<SHZHGL>");
		xmldata.append(XmlUtils.xmlnode("ZT", "1"));
		xmldata.append(XmlUtils.xmlnode("ZHBH", zhbh));
		xmldata.append("</SHZHGL>");
		request.setXmldata(xmldata.toString());
		List<CpsShzhData> cpsShList=genShzhList(request, account);
		return cpsShList.get(cpsShList.size()-1);
	}
	/**
	 * 获取CPS账号余额
	 * @return
	 */
	public String getCpsMoney(String cpbh,int adtnum,String shbh){
		StringBuffer xml = new StringBuffer();
		xml.append("<SHZHGL>");
		xml.append(XmlUtils.xmlnode("CPBH", cpbh));// c站
		xml.append(XmlUtils.xmlnode("GNGJ", "1"));// 国内
		xml.append(XmlUtils.xmlnode("COUNT", adtnum));
		xml.append(XmlUtils.xmlnode("PRICE", ""));
		xml.append(XmlUtils.xmlnode("TOTAL", ""));
		xml.append(XmlUtils.xmlnode("YWPZ", "0"));// 无配置
		xml.append("</SHZHGL>");
		IsAvailableRequest request = new IsAvailableRequest();
		request.setXmldata(xml.toString());
		try {
			IsAvailableResponse iar = isAvailable(request, getCpsAccount(shbh));
			String rtnXml = iar.getXml();
			ParseXml pXml = new ParseXml(rtnXml);

			Element root = pXml.getRoot();
			Element Status = root.element("Status");
			String StatusCode = Status.elementText("StatusCode");
			String StatusMessage = Status.elementText("StatusMessage");
			if (!"OK".equals(StatusCode)) {
				throw new Exception(StatusMessage);
			}
			String kyye = root.elementText("Kyye");
			String Result = root.elementText("Result");
			if ("0".equals(Result)) {
				return kyye;
			} else {
				throw new Exception("余额不够，可用余额为：" + kyye);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "查询余额失败:" + e.getMessage();
		}
	}
	/**
	 * 根据商户获取CpsAccount
	 * @param shbh
	 * @return
	 * request.setSystemId("VETECH");
		request.setBusinessNo(t_ptzc_zh.getUser1());
		request.setUserid(t_ptzc_zh.getUser2());
		request.setOperateTime(VeDate.getStringDate());
		request.setCompid(t_ptzc_zh.getCompid());
	 */
	public CpsAccount getCpsAccount(String shbh){
		JpPtzcZh serachParm=new JpPtzcZh();
		serachParm.setPtzcbs(BUYER_PLAT);
		serachParm.setShbh(shbh);
		List<JpPtzcZh> zhList=jpPtzcZhServiceImpl.getMyBatisDao().select(serachParm);
		if(CollectionUtils.isEmpty(zhList)){
			return null;
		}
		JpPtzcZh zh=zhList.get(0);//平台政策标识和商户编号只会获取到一条数据
		CpsAccount cpsAccount=new CpsAccount();
		cpsAccount.setBusinessNo(zh.getUser1());
		cpsAccount.setSystemId("VETECH");
		cpsAccount.setUserid(zh.getUser2());
		cpsAccount.setCompid(zh.getShbh());
		cpsAccount.setUrl(zh.getUrl1());
		cpsAccount.setPwd(zh.getPwd1());
		return cpsAccount;
	}
	/**
	 * 
	 * @param szmtkno
	 * @param t_ptzc_zh
	 * @param ds
	 * @return
	 * @throws Exception
	 */
	
	public  GetTicketByDetrResponse callCpsDetr(String szmtkno, CpsAccount account) throws Exception{
		GetTicketByDetrRequest request = new GetTicketByDetrRequest();
		request.setTicketno(szmtkno);
		JpPtLog ptlog = new JpPtLog();
		request.setService("getTicketByDetrService");
		ptlog.add("请求CPS XML:" + XmlUtils.toXml(request));
		String rtXml = cpsInvoke.send(request, account);
		ptlog.add("接收CPS XML:" + rtXml);
		return (GetTicketByDetrResponse) XmlUtils.fromXml(rtXml,GetTicketByDetrResponse.class);
	}
	
	
	/**
	 * 采购提交到平台申请退票
	 * @param request
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public TicketRefundResponse RefundApply(TicketRefundCpsRequest request,CpsAccount account) throws Exception {
		request.setService("refundApply");
		TicketRefundResponse response = cpsInvoke.send(request, account, TicketRefundResponse.class);;
		return response;
	}
	
	/**
	 * 调取cps接口获取实时政策
	 * @return
	 * @throws Exception
	 */
	public List<PolicyItem> searchpolicy(CpsSearchParam searchparam) throws Exception{
		Shyhb user = searchparam.getYhb();
		String shbh = user.getShbh();
		String ddbh = searchparam.getDdbh();
		CpsAccount cpsaccount = this.getCpsAccount(shbh);
		JpPtLog ptLog=new JpPtLog();
		ptLog.setShbh(shbh);
		ptLog.setYhbh(user.getBh());
		ptLog.setDdbh(ddbh);
		GetRealtimePolicyReq request = new GetRealtimePolicyReq();
		request.setService("getRealtimePolicy");
		GetRealtimePolicyRes  res = null;
		//List<RealtimePolicy> list = new ArrayList<RealtimePolicy>();
		ptLog.setPtzcbs(PlatCode.CPS);//不能为空
		ptLog.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
		ptLog.setYwlx(DictEnum.PTLOGYWLX.CZC.getValue());
			JpKhdd jpKhdd=null;
			List<JpKhddHd> jpkhddhd = null;
			List<JpKhddCjr> cjrlist = null;
			int zrs = 0;
			double jjs = 0.0;
			double tax = 0.0;
			try {
				jpKhdd = this.genJpKhdd(ddbh, shbh);
				jpkhddhd = this.genJpKhddHd(ddbh, shbh);
				cjrlist = this.genJpKhddCjr(ddbh, shbh);
				ptLog.setPnrNo(jpKhdd.getCgPnrNo());
				jjs = jpKhdd.getCgJsf();
				tax = jpKhdd.getCgTax();
				int adultno = 0;
				int chdno = 0;
				int infno =0;
				for(JpKhddCjr khddcjr : cjrlist){//cps只支持成人或儿童订单,这里需要验证乘机人类型
					if("1".equals(khddcjr.getCjrlx())){
						adultno++;
					}else if("2".equals(khddcjr.getCjrlx())){
						chdno++;
					}else if("3".equals(khddcjr.getCjrlx())){
						infno++;
					}
				}
				if ((adultno > 0 && chdno > 0) || infno > 0) {
					throw new Exception("只支持成人或儿童订单");
				}
				zrs = adultno + chdno +infno;
			} catch (Exception e) {
				throw new Exception("通过订单编号查询订单相关数据发生异常"+e.getMessage());
			}
			double zdj = jpKhdd.getCgZdj();
			JpKhddCjr cjr0 = cjrlist.get(0);//取第一个乘机人用他来获取乘机人类型
			this.addPnrnr(jpKhdd, cjr0.getCjrlx(), user, request);
			if(StringUtils.isBlank(request.getPnrContent())){//如果PNR内容为空，则需要提取订单信息作为接口入参
				this.setGetRealtimeReq(jpKhdd, jpkhddhd, request, cjr0.getCjrlx());
			}
			ptLog.add("|CPS查询政策开始:");
			ptLog.add("CPS查询政策请求参数:"+request.toString()+cpsaccount.toXml());
		try {
			res = cpsInvoke.send(request, cpsaccount,GetRealtimePolicyRes.class);
			if(res==null){
				ptLog.add("CPS政策查询返回null");
				throw new Exception("CPS政策查询返回null");
			}else if("-1".equals(res.getStatus())){
				ptLog.add("CPS政策查询返回异常:"+res.getErrorMessage());
				throw new Exception(res.getErrorMessage());
			}else{
				ptLog.add("CPS查询政策返回:"+res.toString());
				//list = res.getPolicyList();
				return this.cpsPolicyToItem(res.getPolicyList(), jpKhdd.getHclx(),zrs,jjs,tax,zdj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			try {
				this.jpPtLogServiceImpl.insertLog(ptLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setGetRealtimeReq(JpKhdd khdd,List<JpKhddHd> khddhdbList,GetRealtimePolicyReq req,String cjrlx) throws Exception{
		String hclx = khdd.getHclx();
		req.setTravelType(hclx);// 航程类型 1单程，2往返，3联程，4缺口
		req.setPassengerType(cjrlx);// 乘机人类型 1成人，2儿童
		req.setFlightDate(khddhdbList.get(0).getCfsj().substring(0, 10));// 起飞日期 YYYY-MM-DD
		req.setFromCity(khddhdbList.get(0).getCfcity());// 出发城市
		String hbhStr = khddhdbList.get(0).getXsHbh();
	    String cwStr = StringUtils.trimToEmpty(khddhdbList.get(0).getXsZcw());
	    if (StringUtils.isBlank(cwStr)) {
            cwStr = khddhdbList.get(0).getXsCw();
        }
	    
	    if("1".equals(hclx)){
	    	 req.setToCity(khddhdbList.get(0).getDdcity());// 到达城市
	    }else if("2".equals(hclx)){
	    	 req.setToCity(khddhdbList.get(0).getDdcity());// 到达城市
	         req.setBackFlightDate(khddhdbList.get(1).getCfsj().substring(0, 10));// 返程乘机日
	         hbhStr += "|" + khddhdbList.get(1).getXsHbh();
	         String zcw = StringUtils.trimToEmpty(khddhdbList.get(1).getXsZcw());
             if (StringUtils.isBlank(zcw)) {
                 cwStr += "|" + khddhdbList.get(1).getXsCw();
             } else {
                 cwStr += "|" + zcw;
             }
             
	    }else if("3".equals(hclx)){
	    	 req.setToCity(khddhdbList.get(1).getDdcity());// 到达城市
	         req.setTransitCity(khddhdbList.get(1).getCfcity());// 中转城市
	         req.setBackFlightDate(khddhdbList.get(1).getCfsj().substring(0, 10));// 返程乘机日
	         hbhStr += "|" + khddhdbList.get(1).getXsHbh();
	         String zcw = StringUtils.trimToEmpty(khddhdbList.get(1).getXsZcw());
	         if (StringUtils.isBlank(zcw)) {
	             cwStr += "|" + khddhdbList.get(1).getXsCw();
	         } else {
	             cwStr += "|" + zcw;
	         }
	    }else if("4".equals(hclx)){
	    	String cfcityF = StringUtils.trimToEmpty(khddhdbList.get(0).getCfcity());
            String ddcityF = StringUtils.trimToEmpty(khddhdbList.get(0).getDdcity());
            
            String cfcityS = StringUtils.trimToEmpty(khddhdbList.get(1).getCfcity());
            String ddcityS = StringUtils.trimToEmpty(khddhdbList.get(1).getDdcity());
            if (!(cfcityF.equals(ddcityS) && !ddcityF.equals(cfcityS))) {
                throw new Exception("政策匹配缺口程最多支持两段航段，其中第一段出发城市同第二段到达城市相同的缺口订单！");
            }
            req.setToCity(ddcityF);// 到达城市
            req.setTransitCity(cfcityS);// 中转城市
            req.setBackFlightDate(khddhdbList.get(1).getCfsj().substring(0, 10));// 返程乘机日
            hbhStr += "|" + khddhdbList.get(1).getXsHbh();
            String zcw = StringUtils.trimToEmpty(khddhdbList.get(1).getXsZcw());
            if (StringUtils.isBlank(zcw)) {
                cwStr += "|" + khddhdbList.get(1).getXsCw();
            } else {
                cwStr += "|" + zcw;
            }
	    }
	    req.setAirways(khddhdbList.get(0).getXsHbh().replace("*", "").substring(0, 2));// 航空公司
        req.setFlightNo(hbhStr);// 航班号
        req.setSeatClass(cwStr);// 舱位
	}
	
	public List<PolicyItem> cpsPolicyToItem(List<RealtimePolicy> policylist,String hclx,int zrs,double jjs,double tax,double zdj) throws Exception{
		if(CollectionUtils.isEmpty(policylist)){
			return null;
		}
		List<PolicyItem> list = new ArrayList<PolicyItem>();
		for(RealtimePolicy policy : policylist){
			PolicyItem policyItem = new  PolicyItem();
			String policyid = policy.getId();
			policyItem.setId(policyid);
			policyItem.setPtzcbs(plat);
			policyItem.setPolicytype(policy.getPzlx());
			double paypoundage = getSuperStayMoney(policy);
			policyItem.setPaypoundage(paypoundage);
			policyItem.setRate(Arith.mul(defval(policy.getFd(),0.0), 100));// 返点 这个返点数是乘以 100了的
			policyItem.setRateshow(Arith.mul(defval(policy.getFd(),0.0), 100));// 能看到的返点
			policyItem.setPj_sjjsj(Arith.mul(getBuyPrice(policy), zrs));//采购净价(上级结算价)
			double superProfit=Arith.sub(Arith.mul((int)defval(policy.getPj(),0), defval(policy.getFd(),0.0)), defval(policy.getLq(),0.0));
			String otherParam = this.getOtherParam(policy);
			Map<String, String> paramMap = new HashMap<String, String>();
			String csValues = "";
			if(StringUtils.isNotBlank(otherParam)){
				String[] strings = otherParam.split("\\|",-1);
				for(String str : strings){
					if(str.startsWith("1022")){
						csValues = str.split(":",-1)[1];
					}
				}
				paramMap.put("1022", csValues);
			}
			superProfit=PolicyMatchUtil.roundForCgFd(superProfit, paramMap);
			superProfit = Arith.mul(superProfit, zrs);
			policyItem.setSuperProfit(superProfit);
			policyItem.setPayfee(Arith.add(policyItem.getPj_sjjsj(), Arith.add(jjs, tax)));
			String jgxx = policy.getJgxx();//1 以PAT价格为准 0政策为准
			String zclx = policy.getZclx();//1 普通政策 2 K位政策 3 免票政策 4 其它政策 5 特价政策【含新版特价】 6精准营销价格类
			policyItem.setZclx(zclx);
			if("6".equals(zclx) || "1".equals(zclx)){
				policyItem.setIsspecmark("0");// 特殊政策 =0 普通政策 =1 特殊政策 2是所有
				policyItem.setXsj(defval(policy.getPj(),0));
				policyItem.setPj_sjjsj(this.getBuyPrice(policy));
			}else{
				policyItem.setIsspecmark("1");// 特殊政策 =0 普通政策 =1 特殊政策 2是所有
				if ("2".equals(jgxx)){
					jgxx = "0";
				}
				policyItem.setJgxx(jgxx);
				if ("0".equals(jgxx) || "3,4".indexOf(zclx) > -1) {
					policyItem.setPj_cgj(defval(policy.getPj(),0));
				}
				if ("2,3,4,5".indexOf(policyItem.getZclx()) > -1) {
						policyItem.setXsj(defval(policy.getPj(),0));
						policyItem.setPj_sjjsj(this.getBuyPrice(policy));
				}
			}
			policyItem.setPj_cgj(Arith.mul(defval(policy.getPj(),0), zrs));
			if(policyItem.getPj_cgj() == null || policyItem.getPj_cgj() == 0){
				policyItem.setPj_cgj(zdj);
			}
			policyItem.setChangerecord(policy.getSfhbmcp());// 是否更换编码出票 1需要换编码 0 不需要
			policyItem.setTgqgd(policy.getTgqgd());
			policyItem.setNoteStr(policy.getTgqgdStr());
			
			String bzbz = "";
			if(StringUtils.isNotBlank(policy.getWbbz())){
				bzbz += StringUtils.trimToEmpty(policy.getWbbz())+";";
			}
			if("1".equals(policy.getSfzdcp())){//是否自动出票 1是，0否
				bzbz += "系统将自动出票；";
			}
			if ("1".equals(jgxx)) {
				bzbz += "系统将以PAT价格为准；";
			} else if ("0".equals(jgxx)) {
				bzbz += "系统将以政策价格为准；";
			}
			if(StringUtils.isNotBlank(policy.getPzlx())){
				bzbz += "政策类型：" + policy.getPzlx()+";";
			}
			
			policyItem.setWorktime(PolicyMatchUtil.getValidDatetime(policy.getEtdzDatetime()));// 供应商上班时间 工作日/非工作日
			policyItem.setChooseOutWorkTime(PolicyMatchUtil.getValidDatetime(policy.getVoidDatetime()));// 废票时间   工作日/非工作日
			policyItem.setNote(bzbz);// 备注
			String office=StringUtils.trimToEmpty(policy.getOffice());
			if(office.length()<6){
				office="";
			}
			policyItem.setOffice(office);
			policyItem.setShmc(policy.getFb_shmc());
			
			String billSaleMatch = StringUtils.trimToEmpty(PolicyMatchUtil.convertPmxsjxf(policy.getPmxsjxf()));//票面销售价是否相符 1不相符
			if(StringUtils.isNotBlank(billSaleMatch)){
			    if("1".equals(billSaleMatch)){
			        billSaleMatch = "0";
			    }else{
			        billSaleMatch = "1";
			    }
			}
			policyItem.setBillSaleMatch(billSaleMatch);
			policyItem.setHclx(hclx);
			policyItem.setSmnm("");
			policyItem.setShbh(policy.getFb_shbh());
			policyItem.setAssurePlan(policy.getBzjh());
			policyItem.setAssurePlanName(policy.getBzjhMc());
			policyItem.setAssurePlanIcon(policy.getBzjhIcon());
			policyItem.setAppraiseCount(0);
			policyItem.setDealCount(0);
			policyItem.setZcpj(String.valueOf((int)defval(policy.getPj(),0)));
			
			String payCompMsg = policy.getPayCompMsg();//支付公司代号|支付公司名称|图片地址  多个逗号隔开
			
			policyItem.setCjrlx(policy.getPassengerType());//适用乘机人类型【CPS政策收集报表使用】
			policyItem.setZcfd(policy.getRewRates());//政策原始返点【CPS政策收集报表使用】
			
			try{
				if(StringUtils.isNotBlank(payCompMsg)){
					String[] payCompMsgArr=payCompMsg.split(",");
					for (String payCompMsgTmp:payCompMsgArr) {
						String[] tmpArr=payCompMsgTmp.split("\\|");
						policyItem.cps_payCompMsgMap.put(tmpArr[0], new String[]{tmpArr[1],tmpArr[2]});
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			list.add(policyItem);
		}
		return list;
	}
	
	/**
	 * 获取Cps控润后留钱【上级留钱】
	 * @param cabin
	 * @return [参数说明]
	 */
	public static double getSuperStayMoney(RealtimePolicy policy){
		
		double stayMoney =defval(policy.getLq(),0.0);
		if(stayMoney < 0){
			//stayMoney = 0;
		}
		
		return stayMoney;
	}
	 private static double defval(Double num,double defvalue){
	        return null == num ? defvalue : num.doubleValue();
	 }
	 /**
     * 获取CPS控制精度的参数
     * @param policy
     * @return [参数说明]
     */
    private String getOtherParam(RealtimePolicy policy){
        String otherParam = policy.getOtherParam();
        otherParam = DesEncode.decryptdb(otherParam);
        if(StringUtils.startsWith(otherParam, ";")){
            otherParam = " " + otherParam;
        }
        String[] arr = StringUtils.split(otherParam,";",-1);
        otherParam = StringUtils.trimToEmpty(arr[1]);
        otherParam = StringUtils.replace(otherParam, "|", ",");
        return otherParam;
    }
    /**
     * 计算采购净价
     * @param cabinT
     * @param policy
     * @return [参数说明]
     */
    private double getBuyPrice(RealtimePolicy policy) {
        String otherParam = getOtherParam(policy);
        String[] arrT = StringUtils.split(otherParam,":");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(arrT[0], arrT[1]);
        return PolicyMatchUtil.calculateCgjj((int)defval(policy.getPj(),0), defval(policy.getFd(),0.0), 0, 0, defval(policy.getLq(),0.0), 0, 0, 0, 0, paramMap);
    }
	/**
	 * 调取cps接口下单
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public CreateOrderResponse submitorder(CpsOrderParam orderparam) throws Exception{
		Shyhb user = orderparam.getYhb();
		String shbh = user.getShbh();
		String ddbh = orderparam.getDdbh();
		JpPtLog ptLog=new JpPtLog();
		ptLog.setShbh(shbh);
		ptLog.setYhbh(user.getBh());
		ptLog.setDdbh(ddbh);
		CpsAccount cpsaccount = this.getCpsAccount(shbh);
		CreateOrderRequest request = new CreateOrderRequest();
		request.setService("createOrder");
		CreateOrderResponse res = null;
		JpKhdd jpKhdd=null;
		List<JpKhddCjr> cjrlist = null;
		List<JpKhddHd> jpkhddhd = null;
		try {
			jpKhdd = this.genJpKhdd(ddbh, shbh);
			cjrlist = this.genJpKhddCjr(ddbh, shbh);
			jpkhddhd = this.genJpKhddHd(ddbh, shbh);
			ptLog.setPtzcbs(PlatCode.CPS);//不能为空
			ptLog.setPnrNo(jpKhdd.getCgPnrNo());
			ptLog.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
			ptLog.setYwlx(DictEnum.PTLOGYWLX.XD.getValue());
		} catch (Exception e) {
			throw new Exception("根据订单编号查询订单相关数据发生异常!");
		}
		String cfhzls = "";//出发航站楼
		String ddhzls = "";//到达航站楼
		for(JpKhddHd hdb : jpkhddhd){
			String fromHzl=StringUtils.replace(hdb.getCfhzl(), "-", "");
			String toHzl=StringUtils.replace(hdb.getDdhzl(), "-", "");
			cfhzls=pinJie(cfhzls, fromHzl);
			ddhzls=pinJie(ddhzls, toHzl);
		}
		request.setTofromterminal(cfhzls);
		request.setTerminal(ddhzls);
		String pnr_hclx = jpKhdd.getHclx();
		String zclx = orderparam.getZclx();
		String zcid = orderparam.getPolicyId();
		request.setOrderNo(orderparam.getDdbh());
		request.setBigPnrNo(jpKhdd.getXsHkgsPnr());
		request.setPnrNo(jpKhdd.getXsPnrNo());
		if("5".equals(zclx)){
	        zclx = "7";//新版特价
		}
		request.setProductType(zclx);//产品类型 1 普通政策 2 K位政策 3 免票政策 4 其它政策 5 特价政策 6精准营销 7新版特价
		JpKhddCjr cjr0 = cjrlist.get(0);//取第一个乘机人用他来获取乘机人类型
		this.addPnrnr(jpKhdd, cjr0.getCjrlx(), user, request);
		
		if(StringUtils.contains(zcid, "@")){
		    zcid = StringUtils.substringAfterLast(zcid, "@");//说明是匹配贴点后，政策ID重命名.后面电商用到cps贴点时用到
		}
		request.setPolicyId(zcid);//政策ID
		request.setTravelType(pnr_hclx);//航程类型 1单程、2往返、3联程、4缺口
		String hc="";
		String hbh="";
		String realhbh="";//实际承运航班号
		String jx="";
		String cw="";
		String zcw = "";
		String zk="";
		String cfdatetime="";
		String dddatetime="";
		String cfhzl="";
		String ddhzl="";
		JpKhddHd khddhd = jpkhddhd.get(0);
		if("1".equals(pnr_hclx)){//单程
			hc = khddhd.getCfcity()+khddhd.getDdcity();
			hbh = khddhd.getXsHbh();
			realhbh = khddhd.getXsHbh();
			jx = khddhd.getFjjx();
			cw = khddhd.getXsCw();
			zcw = khddhd.getXsZcw();
			zk = "0";
			cfdatetime = khddhd.getCfsj();
			dddatetime = khddhd.getDdsj();
			cfhzl = khddhd.getCfhzl();
			ddhzl = khddhd.getDdhzl();
		}else if("2".equals(pnr_hclx)){//往返
			JpKhddHd khddhd1 = jpkhddhd.get(1);
			hc = khddhd.getCfcity() + khddhd.getDdcity();
			hc+="|"+khddhd.getDdcity()+khddhd.getCfcity();
			hbh = khddhd.getXsHbh();
			hbh += "|" + khddhd1.getXsHbh();
			realhbh = khddhd.getXsHbh();
			realhbh += "|" + khddhd1.getXsHbh();
			jx = khddhd.getFjjx();
			jx += "|" + khddhd1.getFjjx();
			cw = khddhd.getXsCw();
			cw += "|" + khddhd1.getXsCw();
			zcw = khddhd.getXsZcw();
			zcw += "|" + khddhd1.getXsZcw();
			zk = "0|0";
			cfdatetime = khddhd.getCfsj();
			cfdatetime += "|" + khddhd1.getCfsj();
			dddatetime = khddhd.getDdsj();
			dddatetime += "|" + khddhd1.getDdsj();
			cfhzl = khddhd.getCfhzl();
			cfhzl += "|" + khddhd1.getCfhzl();
			ddhzl = khddhd.getDdhzl();
			ddhzl += "|" + khddhd1.getDdhzl();
		}else if("3".equals(pnr_hclx)){
			JpKhddHd khddhd1 = jpkhddhd.get(1);
			hc = khddhd.getCfcity() + khddhd.getDdcity();
			hc+="|"+khddhd1.getCfcity()+khddhd1.getDdcity();
			hbh = khddhd.getXsHbh();
			hbh += "|" + khddhd1.getXsHbh();
			realhbh = khddhd.getXsHbh();
			realhbh += "|" + khddhd1.getXsHbh();
			jx = khddhd.getFjjx();
			jx += "|" + khddhd1.getFjjx();
			cw = khddhd.getXsCw();
			cw += "|" + khddhd1.getXsCw();
			zcw = khddhd.getXsZcw();
			zcw += "|" + khddhd1.getXsZcw();
			zk = "0|0";
			cfdatetime = khddhd.getCfsj();
			cfdatetime += "|" + khddhd1.getCfsj();
			dddatetime = khddhd.getDdsj();
			dddatetime += "|" + khddhd1.getDdsj();
			cfhzl = khddhd.getCfhzl();
			cfhzl += "|" + khddhd1.getCfhzl();
			ddhzl = khddhd.getDdhzl();
			ddhzl += "|" + khddhd1.getDdhzl();
		}else if("4".equals(pnr_hclx)){
			JpKhddHd khddhd2 = null;
			if(jpkhddhd.size()==2){
				khddhd2 = jpkhddhd.get(1);
			}else{
				khddhd2 = jpkhddhd.get(2);
			}
			hc = khddhd.getCfcity() + khddhd.getDdcity();
			hc+="|"+khddhd2.getCfcity()+khddhd2.getDdcity();
			hbh = khddhd.getXsHbh();
			hbh += "|" + khddhd2.getXsHbh();
			realhbh = khddhd.getXsHbh();
			realhbh += "|" + khddhd2.getXsHbh();
			jx = khddhd.getFjjx();
			jx += "|" + khddhd2.getFjjx();
			cw = khddhd.getXsCw();
			cw += "|" + khddhd2.getXsCw();
			zcw = khddhd.getXsZcw();
			zcw += "|" + khddhd2.getXsZcw();
			zk = "0|0";
			cfdatetime = khddhd.getCfsj();
			cfdatetime += "|" + khddhd2.getCfsj();
			dddatetime = khddhd.getDdsj();
			dddatetime += "|" + khddhd2.getDdsj();
			cfhzl = khddhd.getCfhzl();
			cfhzl += "|" + khddhd2.getCfhzl();
			ddhzl = khddhd.getDdhzl();
			ddhzl += "|" + khddhd2.getDdhzl();
		}
		request.setTravelRange(hc);//航程 WUHPEK|PEKCAN|WUHPEK|PEKCAN
		request.setFlightNo(hbh);//航班号
		request.setRealFlightNo(realhbh);//实际承运航班号
		request.setDiscount(zk);
		request.setPlaneType(jx);//机型
		request.setSeatClass(cw);//舱位
		if(!"|".equals(zcw)){
			request.setSubSeatClass(zcw);//子舱位
		}
		request.setFromDatetime(cfdatetime);//起飞时间 格式示例：2013-08-30 09:30| 2013-08-31 10:30
		request.setToDatetime(dddatetime);//到达时间 格式示例：2013-08-30 10:30| 2013-08-31 11:30
		request.setTofromterminal(cfhzl);//出发航站楼
		request.setTerminal(ddhzl);//到达航站楼
		String cjrs=cjrlist.get(0).getCjr();//乘机人
		String csrqs = cjrlist.get(0).getCsrq();//出生日期
		String cjrlxs=cjrlist.get(0).getCjrlx();//乘机人类型
		String zjlxs=cjrlist.get(0).getZjlx();//证件类型
		String zjhms=cjrlist.get(0).getZjhm();//证件号码
		Shshb shshb = user.getShshb();
		String lxr = this.getLxr(jpKhdd, shbh, shshb);
		String sjhms = this.getLxrsj(jpKhdd, shbh, shshb);
		String sjhm = sjhms;
		for(int i=1;i<cjrlist.size();i++){
			cjrs+="|"+cjrlist.get(i).getCjr();
			csrqs+="|"+cjrlist.get(i).getCsrq();
			cjrlxs+="|"+cjrlist.get(i).getCjrlx();
			zjlxs+="|"+cjrlist.get(i).getZjlx();
			zjhms+="|"+cjrlist.get(i).getZjhm();
			sjhms+="|"+sjhms;
		}
		int cjrcnt=cjrlist.size();
		double zdj=Arith.div(NumberUtils.toDouble(orderparam.getPjCgj()),cjrcnt);
		double yq=Arith.div(jpKhdd.getXsJsf(), cjrcnt);
		double tax=Arith.div(jpKhdd.getXsTax(), cjrcnt);
		String scnys=GetPolicyUtil.getIntStr(zdj);//账单价  单个人的
		String yqs=GetPolicyUtil.getIntStr(tax);
		String taxs=GetPolicyUtil.getIntStr(yq);
		double yqss = Arith.mul(NumberUtils.toDouble(yqs), cjrcnt);
		double taxss = Arith.mul(NumberUtils.toDouble(taxs), cjrcnt);
		double jjrys = Arith.add(yqss, taxss);
		String settlementPrice= (null== orderparam.getPolicyJsj()) ? "0" : (Arith.sub(NumberUtils.toDouble(orderparam.getPolicyJsj()),jjrys)+"");
		request.setPassenger(cjrs);//乘机人 格式示例： 乘机人名1｜乘机人名1｜乘机人名2｜乘机人名2
		request.setBirthDate(csrqs);//
		request.setPassengerType(cjrlxs);//乘机人类型
		request.setCardType(zjlxs);//证件类型 格式示例：NI|ID|PP
		request.setCardId(zjhms);//证件号码
		request.setPassengerMobile(sjhms);//手机号
		request.setScny(scnys);//账单价
		request.setYq(yqs);//税费
		request.setTax(taxs);//机建
		request.setSettlementPrice(settlementPrice);//结算价 不写值，只参与比较之用
		request.setIfChangeOrder("0");//是否升航换开订单 1是升舱换开，0不是升舱换开
		
		request.setLinker(lxr);
		request.setLinkMobile(sjhm);
		try {
			ptLog.add("|CPS下单开始:");
			ptLog.add("CPS创建订单请求XML1:"+request.toString());
			res = cpsInvoke.send(request, cpsaccount, CreateOrderResponse.class);
			ptLog.add("CPS下单返回:"+res.toString());
			if(res ==null){
				throw new Exception("调取cps接口下单出现异常!");
			}else{
				if("-1".equals(res.getStatus())){
					String errMsg = StringUtils.trimToEmpty(res.getErrorMessage());
					if(errMsg.indexOf("timed out")>-1 || errMsg.indexOf("time out")>-1){
						errMsg = "下单超时，请上CPS B系统查看是否已正常下单，避免重复出票。";
					}
					ptLog.add("下单超时，请上CPS B系统查看是否已正常下单，避免重复出票。");
					throw new Exception("错误代码:"+res.getErrorCode()+"错误原因:"+errMsg);
				}
				try {
					if(StringUtils.isNotBlank(res.getOfficeId())){
						pnrAuthService.authPnr(PlatCode.CPS, res.getOfficeId(), jpKhdd, user, ptLog, orderparam.getPolicytype(), "0");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				String minutes = res.getPaymentTimeLimit();//支付时限
				String zt=res.getOrderStatus();//CPS订单状态，已确认待支付112，待确认101，待确认订单不能直接支付
				// 1预定成功,等待采购方支付 10：支付完成，等待出票 12：无法出票 13：出票完成 14：更换编码出票完成
				// 21：退票处理中 22：无法退票 31：废票处理中 32：无法废票 90：完成退费票 99：交易取消退款
				if("101".equals(zt)){
					throw new Exception("当前订单在CPS的订单状态为待确认，不能直接支付！请等待供应商的确认！");
				}
				if(StringUtils.isNotBlank(minutes)){//支付时限，如果是已确认待支付的订单，则返回此值，单位为分钟
					res.setErrMsgTip("请在"+minutes+"分钟内完成支付！否则系统会自动取消订单！");
				}
				boolean zfFlag=false;
				if("1".equals(orderparam.getAutopay())){
					try {
						submitautopay(res.getOrderNo(),res.getPaymentAmount(),"");
						zfFlag=true;
						res.setOrderStatus("10");//已支付
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(!zfFlag){
					ptLog.add("CPS下单成功，开始获取支付链接，传入的支付单号/金额为："+res.getOrderNo()+"/"+res.getPaymentAmount());
					GetOrderPayLinkResponse paylink = getPayUrl(res.getOrderNo(), res.getPaymentAmount());
					String payurl=paylink.getUrl();
					String payment = paylink.getPayment();
					ptLog.add("CPS下单成功，获取支付链接："+payurl);
					res.setPayurl(payurl);
					res.setPayment(payment);
				}
				this.creatCgdd(jpKhdd, res, user, request, orderparam.getIsQzd());
				return res;
			}
		} catch (SocketTimeoutException e) {
			throw new Exception("错误原因:下单超时，请上CPS B系统查看是否已正常下单，避免重复出票。");
		}finally{
			try {
				jpPtLogServiceImpl.insertLog(ptLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
	 * <功能详细描述>
	 * 写入采购订单
	 * @param jpKhdd
	 * @param jpCgdd [参数说明]
	 * @throws Exception 
	 */
	private void creatCgdd(JpKhdd jpKhdd,CreateOrderResponse res,Shyhb yhb,CreateOrderRequest policyRequest,String isQzd) throws Exception{
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
		cgdd.setCgdw(PlatCode.CPS);
		cgdd.setShbh(jpKhdd.getShbh());
		String ptddzt=res.getOrderStatus();
		if("10".equals(ptddzt)){
			cgdd.setZt("2");
			Double zfje=NumberUtils.toDouble(res.getPaymentAmount());
			cgdd.setZfje(zfje);
			cgdd.setJyzt("1");
		}else {
			cgdd.setZt("0");
			cgdd.setJyzt("0");
		}
		jpCgddServiceImpl.insert(cgdd);
	}
	/**
	 * cps自动代扣
	 * @param request
	 * @throws Exception
	 */
	public void submitautopay(String orderNo,String payfee,String payment) throws Exception{
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		CpsAccount cpsaccount = this.getCpsAccount(shbh);
		GetOrderPayLinkRequest request = new GetOrderPayLinkRequest();
		request.setOrderNo(orderNo);//平台订单号
		request.setOrderType("1");//订单类型1正常，2改签
		request.setService("payWithholding");
		request.setOrderAmt(payfee);//支付金额s
		request.setPayment(payment);//支付方式
		GetOrderPayLinkResponse res = null;
		try {
			res = cpsInvoke.send(request, cpsaccount, GetOrderPayLinkResponse.class);
			if(res == null){
				throw new Exception("调用cps自动代扣接口发生异常!");
			}else{
				if("-1".equals(res.getStatus())){
					String string="提交CPS获取支付连接出错误，错误代码:"+res.getErrorCode()+"错误原因:"+res.getErrorMessage();
					throw new Exception(string);
				}
			}
		} catch (SocketTimeoutException e) {
			throw new Exception("自动支付请求超时，请上CPS B系统查看订单状态，错误：" + e.getMessage());
		} catch(Exception e){
			throw new Exception("CPS订单自动支付报错" + e.toString());
		}
	}
	
	/**
	 * 调取cps接口获取支付链接
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public GetOrderPayLinkResponse getPayUrl(String orderNo,String paymentAmount) throws Exception{
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		CpsAccount cpsaccount = this.getCpsAccount(shbh);
		GetOrderPayLinkRequest request = new GetOrderPayLinkRequest();
		request.setOrderNo(orderNo);
		request.setOrderType("1");//订单类型1正常，2改签
		request.setService("getOrderPayLink");
		request.setOrderAmt(paymentAmount);
		GetOrderPayLinkResponse res = null;
		try {
			res = cpsInvoke.send(request, cpsaccount, GetOrderPayLinkResponse.class);
			if(res == null){
				throw new Exception("调CPS接口获取支付链接发生异常!");
			}else if("-1".equals(res.getStatus())){
				throw new Exception("提交CPS获取支付连接出错误，错误代码:"+res.getErrorCode()+"错误原因:"+res.getErrorMessage());
			}else{
				String payurl = res.getUrl();
				if(StringUtils.isBlank(payurl)){
					throw new Exception("提交CPS获取支付连接出错误，返回的支付URL为空");
				}else{
					return res;
				}
			}
		} catch (Exception e) {
			throw new Exception("调CPS接口获取支付链接发生异常!");
		}
	}
	
	/**
	 * cps回调出票通知(params参数是cps回调的请求参数  name - value)
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public EtdzRequest etdzNotify(Map<String, String[]> params) throws Exception{
		if(null == params){
			throw new Exception("参数为NULL");
		}
		String[] param=params.get("content");
		if(null == param || param.length == 0){
			throw new Exception("参数为NULL");
		}
		String xml=param[0];
		EtdzRequest request = null;
		try {
			request = (EtdzRequest) XmlUtils.fromXml(xml, EtdzRequest.class);
		} catch (Exception e) {
			throw new Exception("解析出票通知回调xml出现异常!");
		}
		return request;
	}
	
	/**
	 * 采购商接收平台退废办理通知参数解析
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public TicketRefundNoticeRequest refundNotify(Map<String, String[]> params) throws Exception{
		if(params == null){
			throw new Exception("接受CPS退废票通知xml为空!");
		}
		String[] param=params.get("content");
		if(null == param || param.length == 0){
			throw new Exception("接受CPS退废票通知xml为空");
		}
		String xml = param[0];
		TicketRefundNoticeRequest request = null;
		try {
			request = (TicketRefundNoticeRequest)XmlUtils.fromXml(xml, TicketRefundNoticeRequest.class);
		} catch (Exception e) {
			throw new Exception("解析平台退废票回调xml出现异常");
		}
		return request;
	}
	
	/**
	 * 接收采购商支付通知处理数据
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public PayNotifyRequest payNotify(Map<String, String[]> params) throws Exception{
		if(null == params){
			 throw new Exception("接受cps支付通知xml为空!");
		}
		String[] param=params.get("content");
		if(null == param || param.length == 0){
			throw new Exception("接受CPS支付回调xml为空");
		}
		String xml = param[0];
		PayNotifyRequest request = null;
		try {
			request =(PayNotifyRequest)XmlUtils.fromXml(xml, PayNotifyRequest.class);
		} catch (Exception e) {
			throw new Exception("解析平台支付通知回调xml异常");
		}
		return request;
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
			Pnr pnrObj=DataUtils.getPnrPat(jpKhdd.getCgPnrNo(), cjrlx,jppz,yhb,"");
			if(pnrObj==null){
				return;
			}
			pnrnr=pnrObj.getPnr_lr();
			patnr=pnrObj.getPat();
			JpKhdd updd=new JpKhdd();
			updd.setDdbh(ddbh);
			updd.setPnrLr(pnrnr);
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
}
