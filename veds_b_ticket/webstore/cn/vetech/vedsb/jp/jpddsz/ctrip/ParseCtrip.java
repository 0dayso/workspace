package cn.vetech.vedsb.jp.jpddsz.ctrip;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Element;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.ParseXml;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;


/**
 * 携程供应导单接口解析累
 * 
 * @author shenjianxin
 * @version [版本号, Jan 17, 2014]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ParseCtrip {
	public static List<Map<String,String>> parseOpenIssueOrderList(String xml,JpPtLog ptlb) throws Exception{
		List<Map<String,String>> orderList=new ArrayList<Map<String,String>>();
		try{
			ParseXml parse = new ParseXml(xml);
			Element HeaderEle =parse.ele("Header");
			String resultMsg=parse.attr("ResultMsg",HeaderEle);
			if(StringUtils.isNotBlank(resultMsg)){
				ptlb.add("解析到订单列表返回XML中有错误提示信息："+ resultMsg);
				throw new Exception(resultMsg);
			}
			Element issueOrderListEle =parse.ele("OpenIssueOrderListResponse IssueOrderList");
			if(issueOrderListEle==null){
				ptlb.add("解析到订单列表返回XML中无订单信息节点OpenIssueOrderListResponse IssueOrderList");
				ptlb.add("没有需要导入的订单");
				return null;
			}
			List<Element> issueOrderList=parse.get("OpenIssueBillResponse",issueOrderListEle);
			if(CollectionUtils.isEmpty(issueOrderList)){
				ptlb.add("解析到订单列表返回XML中无订单信息节点OpenIssueBillResponse");
				ptlb.add("没有需要导入的订单");
				return null;
			}
			String strTmp = "";
			for(int i=0;i<issueOrderList.size();i++){
				HashMap<String,String> hashMap = new HashMap<String, String>();
				Element issueOrder = issueOrderList.get(i);
				String OrderID = parse.textTrim("OrderID",issueOrder);
				String pnrno1=parse.textTrim("RecordNO",issueOrder);
				String IssueBillID = parse.textTrim("IssueBillID", issueOrder);
				String IssueBillAssignOperator  = parse.textTrim("IssueBillAssignOperator ",issueOrder);
				String Urgency = parse.textTrim("Urgency", issueOrder);//出票紧急度
				String UrgeTimes = parse.textTrim("UrgeTimes", issueOrder);//催出票次数
				
				String qfrqsj = "";
				List<Element> flightInfoList=parse.get("BillDetailList OpenIssueBillDetailResponse",issueOrder); 
				qfrqsj = parse.textTrim("TakeOffTime",flightInfoList.get(0));
				if(strTmp.indexOf(OrderID)>-1){//过滤掉重复的订单编号
					strTmp+=OrderID+",";
					continue;
				}
				hashMap.put("OrderID", OrderID);
				hashMap.put("IssueBillID", IssueBillID);//出票单号
				hashMap.put("IssueBillAssignOperator ", IssueBillAssignOperator);//认领人
				hashMap.put("Urgency",Urgency);
				hashMap.put("UrgeTimes", UrgeTimes);
				orderList.add(hashMap);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return orderList;
	}
	/**
	 * 解析出票单详情
	 * 由于没有保险等数据所以暂时不采用作为订单详
	 * @param swdbean
	 * @param ptlb
	 * @param xml
	 * @param t_ptzc_zh_gy
	 * @param ds
	 * @return
	 * @throws Exception
	 */
	public static OrderBean parseOrderList2(JpPtLog ptlb,String xml,JpDdsz jpDdsz) throws Exception{
		ParseXml parse=null;
		try{
			parse = new ParseXml(xml);
		}catch (Exception e) { 
			e.printStackTrace();
			ptlb.add("解析订单详XML发生异常：异常信息："+e.getMessage()+",XML:"+xml);
			return null;
		}
		Element OpenIssueBillInfoEle =parse.ele("OpenIssueBillInfoResponse");
		if(OpenIssueBillInfoEle==null){
			ptlb.add("解析订单详XML发生未发现OpenIssueBillInfoResponse");
			return null;
		}
		
		//订单信息
		String IssueBillID=parse.textTrim("IssueBillID",OpenIssueBillInfoEle);//出票单号
		String OrderID=parse.textTrim("OrderID",OpenIssueBillInfoEle);//订单编号
		String IssueStatus = parse.textTrim("IssueStatus",OpenIssueBillInfoEle);//出票单状态 1:待处理 2:出票失败 3:出票成功 4:取消出票 5:已作废 6:已入库 7:已支付 8:验证中
		String CancelIssueStatus = parse.textTrim("CancelIssueStatus",OpenIssueBillInfoEle);//出票单是否申请取消 1取消，0正常
		String TBookingDateTime = parse.textTrim("TBookingDateTime",OpenIssueBillInfoEle);//创建出票单时间
		String RecordNO = parse.textTrim("RecordNO",OpenIssueBillInfoEle);//订位PNR
		String OfficeNO = parse.textTrim("OfficeNO",OpenIssueBillInfoEle);//订位OFFICE号
		String OldOrderID = parse.textTrim("OldOrderID",OpenIssueBillInfoEle);//国际订单改签原订单号
		String ExtOrderID = parse.textTrim("ExtOrderID",OpenIssueBillInfoEle);//B2C网站入库号
		String FlightClass = parse.textTrim("FlightClass",OpenIssueBillInfoEle);//国内国际 N：国内 I：国际
		String TicketType = parse.textTrim("TicketType",OpenIssueBillInfoEle);//机票类型 1:BSP  2:B2B  3:B2C  4:P2P
		String FlightAgency = parse.textTrim("FlightAgency",OpenIssueBillInfoEle);//供应商ID
		String PolicyID = parse.textTrim("PolicyID",OpenIssueBillInfoEle); //政策ID
		String PolicyType = parse.textTrim("PolicyType",OpenIssueBillInfoEle);//产品类型
		String PolicyCode = parse.textTrim("PolicyCode",OpenIssueBillInfoEle);//政策代码
		String ProductSource = parse.textTrim("ProductSource",OpenIssueBillInfoEle);//政策来源
		String LastPrintTicketTime = parse.textTrim("LastPrintTicketTime",OpenIssueBillInfoEle);//出票单最晚出票时间
		String IssueRemark = parse.textTrim("IssueRemark",OpenIssueBillInfoEle);//出票备注
		String IssueWay = parse.textTrim("IssueWay",OpenIssueBillInfoEle);//出票方式 1自动出票  2手工出票
		String Adtk = parse.textTrim("Adtk",OpenIssueBillInfoEle);//ADTK时间
		String UrgeTimes = parse.textTrim("UrgeTimes",OpenIssueBillInfoEle); //催单次数
		String SaleType = parse.textTrim("SaleType",OpenIssueBillInfoEle); //套餐类型
		String RealReservationType = parse.textTrim("RealReservationType",OpenIssueBillInfoEle);//国际机票订位方式
		String CurrencyType = parse.textTrim("CurrencyType",OpenIssueBillInfoEle);//货币单位
		String AirlineRecodeNo = parse.textTrim("AirlineRecodeNo",OpenIssueBillInfoEle);//航司编码
		String CustomerID = parse.textTrim("CustomerID",OpenIssueBillInfoEle);//大客户编码
		String LoadAccount = parse.textTrim("LoadAccount",OpenIssueBillInfoEle);//自动出票登陆账号
		String EIRemark = parse.textTrim("EIRemark",OpenIssueBillInfoEle);//退改签说明
		String E_EIRemark = parse.textTrim("E_EIRemark",OpenIssueBillInfoEle);//英文 退改签说明
		String flightWay = parse.textTrim("FlightWay",OpenIssueBillInfoEle);//航程类型 1单程 2往返 3联程 4多程
		//新增解析节点
		Map<String, String> khddMap = new HashMap<String, String>();
		//出票单状态 1:待处理 2:出票失败 3:出票成功 4:取消出票 5:已作废 6:已入库 7:已支付 8:验证中
		String wd_ddzt = "";
		if("1".equals(IssueStatus)){
			wd_ddzt="待处理";
		}else if("2".equals(IssueStatus)){
			wd_ddzt = "出票失败";
		}else if("3".equals(IssueStatus)){
			wd_ddzt = "出票成功";
		}else if("4".equals(IssueStatus)){
			wd_ddzt = "取消出票";
		}else if("5".equals(IssueStatus)){
			wd_ddzt = "已作废";
		}else if("6".equals(IssueStatus)){
			wd_ddzt = "已入库";
		}else if("7".equals(IssueStatus)){
			wd_ddzt = "已支付";
		}else if("8".equals(IssueStatus)){
			wd_ddzt = "验证中";
		}	
		khddMap.put("SHBH",jpDdsz.getShbh());
		khddMap.put("DDYH", jpDdsz.getDdUserid());
		khddMap.put("DDBM", jpDdsz.getDdBmid());
		khddMap.put("WBDH", OrderID);
		khddMap.put("WDID", jpDdsz.getWdid());
		khddMap.put("WDPT", jpDdsz.getWdpt());
		khddMap.put("JJD", "");
		khddMap.put("CPCS", "");
		khddMap.put("NOSX", "");
		khddMap.put("SKKM", jpDdsz.getDdMrkm());
		khddMap.put("DDLX", "1");
		khddMap.put("DDZT", "1");
		khddMap.put("XS_PNR_NO", RecordNO.replace("000000", ""));
		khddMap.put("CPDH", IssueBillID);
		String bigPnr =AirlineRecodeNo;
		
		if(bigPnr.length()<5||VeStr.isDouble(bigPnr)){//如果位数小于5或者是纯数字
			bigPnr="";
		}
		khddMap.put("XS_HKGS_PNR", bigPnr);
		khddMap.put("WD_ZCDM",PolicyCode);
		if(StringUtils.isNotBlank(Adtk)){//和我们系统时间格式保持一致
			khddMap.put("TMP_ADTK",Adtk.replace("T", " "));
		}
		/*
		 * 携程系统定义：1单程 2往返 3联程 4多程
		 * 胜意系统定义：1单、2往返、3联程、4缺口;
		 */
		String BZBZ="携程订单号："+OrderID+" ";
		if(StringUtils.isNotBlank(PolicyID)){
			BZBZ+=" 政策号："+PolicyID;
		}
		if(StringUtils.isNotBlank(PolicyCode)){
			BZBZ+=" 政策代码："+PolicyCode;
		}
		if(StringUtils.isNotBlank(IssueRemark)) {
			BZBZ+=" 政策说明："+IssueRemark;
		}
		if(StringUtils.isNotBlank(OfficeNO)) {
			BZBZ+=" 订位office号："+OfficeNO;
		}
		if(StringUtils.isNotBlank(Adtk)) {
			BZBZ+=" ADTK时间："+Adtk;
		}
		if(StringUtils.isNotBlank(TicketType)) {
			////机票类型 1:BSP  2:B2B  3:B2C  4:P2P
			String jplx = "";
			if("1".equals(TicketType)){
				jplx = "BSP";
			}else if("2".equals(TicketType)){
				jplx = "B2B";
			}else if("3".equals(TicketType)){
				jplx = "B2C";
			}else if("4".equals(TicketType)){
				jplx = "P2P";
			}
			BZBZ+=" 票种类型："+jplx;
		}
		if(StringUtils.isNotBlank(LastPrintTicketTime)) {
			BZBZ+=" 出票单最晚出票时间："+LastPrintTicketTime;
		}
		if(StringUtils.isNotBlank(CustomerID)) {//大客户编码
			BZBZ+=" 大客户编码："+CustomerID;
		}
		khddMap.put("BZBZ", BZBZ);
//		1	NFD
//		2	清仓产品
//		3	商旅产品
//		5	中转产品
//		6	包机
//		7	切位
//		8	航司VIP卡
//		9	其他
//		10000	普通
//		10001	规则运价
//		10002	公布运价
//		10003	私有运价
//		10004	供应商直连
//		10005	申请
//		10006	包机/切位
//		10007	直销
//		10008	特惠
//		10009	固定K位
//		10010	里程兑换
		String wdzc_lx = "";
		if("0".equals(PolicyType)){
			wdzc_lx = "全部";
		}else if("1".equals(PolicyType)){
			wdzc_lx = "NFD";
		}else if("2".equals(PolicyType)){
			wdzc_lx = "清仓产品";
		}else if("3".equals(PolicyType)){
			wdzc_lx = "商旅产品";
		}else if("4".equals(PolicyType)){
			wdzc_lx = "中转";
		}else if("5".equals(PolicyType)){
			wdzc_lx = "中转产品";
		}else if("6".equals(PolicyType)){
			wdzc_lx = "包机";
		}else if("7".equals(PolicyType)){
			wdzc_lx = "切位";
		}else if("8".equals(PolicyType)){
			wdzc_lx = "航司VIP";
		}else if("9".equals(PolicyType)){
			wdzc_lx = "其他";
		}else if("10".equals(PolicyType)){
			wdzc_lx = "特价";
		}else if("10000".equals(PolicyType)){
			wdzc_lx = "普通";
		}else if("10001".equals(PolicyType)){
			wdzc_lx = "规则运价";
		}else if("10002".equals(PolicyType)){
			wdzc_lx = "公布运价";
		}else if("10003".equals(PolicyType)){
			wdzc_lx = "私有运价";
		}else if("10004".equals(PolicyType)){
			wdzc_lx = "供应商直连";
		}else if("10005".equals(PolicyType)){
			wdzc_lx = "申请";
		}else if("10006".equals(PolicyType)){
			wdzc_lx = "包机/切位";
		}else if("10007".equals(PolicyType)){
			wdzc_lx = "直销";
		}else if("10008".equals(PolicyType)){
			wdzc_lx = "特惠";
		}else if("10009".equals(PolicyType)){
			wdzc_lx = "固定K位";
		}else if("10010".equals(PolicyType)){
			wdzc_lx = "里程兑换";
		}else if("10011".equals(PolicyType)){
			wdzc_lx = "公布转私有";
		}else if("10021".equals(PolicyType)){
			wdzc_lx = "包机/切位";
		}else if("10022".equals(PolicyType)){
			wdzc_lx = "申请";
		}
		khddMap.put("WD_ZCLX",wdzc_lx);
		resolveZCDM(PolicyCode,khddMap);
		resolveZCSM(IssueRemark,khddMap.get("ZC_QD"), khddMap);

		
		Map<String,Map<String,String>> cjrMaps = new HashMap<String,Map<String,String>>();
		List<Map<String, String>> hdList =new ArrayList<Map<String,String>>();
		List<Element> IBDetailInfoList=parse.get("IssueBillDetailInfoList IssueBillDetailInfo",OpenIssueBillInfoEle);
		//判断重复用
		String cjrxmTmp = "";//乘机人姓名
		String hdXh ="";//航段序号
		for(Element IBDetailInfo:IBDetailInfoList){
			String PassengerName = parse.textTrim("PassengerName",IBDetailInfo);//乘客姓名
			String Nationality = parse.textTrim("Nationality",IBDetailInfo);//国籍
			String Gender = parse.textTrim("Gender",IBDetailInfo);//性别
			String CardType = parse.textTrim("CardType",IBDetailInfo);//证件类型
			String CardNo = parse.textTrim("CardNo",IBDetailInfo);//证件号码
			String BirthDate = parse.textTrim("BirthDate",IBDetailInfo);//出生日期
			String CardTimelimit = parse.textTrim("CardTimelimit",IBDetailInfo);//证件有效期
			String AgeType = parse.textTrim("AgeType",IBDetailInfo);//年龄类型 (ADU:成人,CHI:儿童,BAB:婴儿)
			String PassengerType = parse.textTrim("PassengerType",IBDetailInfo);//乘客类型
			String Sequence = parse.textTrim("Sequence",IBDetailInfo);//航程序号
			String Flight = parse.textTrim("Flight",IBDetailInfo);//航班号
			String CarrierFlight = parse.textTrim("CarrierFlight",IBDetailInfo);//实际承运航班号
			String DCity = parse.textTrim("DCity",IBDetailInfo);//出发城市
			String ACity = parse.textTrim("ACity",IBDetailInfo);//到达城市
			String DPort = parse.textTrim("DPort",IBDetailInfo);//出发机场
			String APort = parse.textTrim("APort",IBDetailInfo);//到达机场
			String Class = parse.textTrim("Class",IBDetailInfo);//舱位等级
			String SubClass = parse.textTrim("SubClass",IBDetailInfo);//子舱位
			String TakeOffTime = parse.textTrim("TakeOffTime",IBDetailInfo);//起飞时间
			String ArrivalTime = parse.textTrim("ArrivalTime",IBDetailInfo);//到达时间
			String PrintPrice = parse.textTrim("PrintPrice",IBDetailInfo);//票面价
			String ForeignPrintPrice = parse.textTrim("ForeignPrintPrice",IBDetailInfo);//票面价（外币）
			String Cost = parse.textTrim("Cost",IBDetailInfo);//底价
			String ForeignCost = parse.textTrim("ForeignCost",IBDetailInfo);//底价（外币）
			String ThirdPartCost = parse.textTrim("ThirdPartCost",IBDetailInfo);//第三方支付底价
			String CostRate = parse.textTrim("CostRate",IBDetailInfo);//第三方支付底价
			String Tax = parse.textTrim("Tax",IBDetailInfo);//税
			String ForeignTax = parse.textTrim("ForeignTax",IBDetailInfo);//税（外币）
			String OilFee = parse.textTrim("OilFee",IBDetailInfo);//燃油
			String ForeignOilFee = parse.textTrim("ForeignOilFee",IBDetailInfo);//燃油（外币）
			String AgencyRecordNO = parse.textTrim("AgencyRecordNO",IBDetailInfo);//出票的PNR
			String AgencyOfficeNo = parse.textTrim("AgencyOfficeNo",IBDetailInfo);//出票的OFFICE号
			String AirLineCode = parse.textTrim("AirLineCode",IBDetailInfo);//回填的航司三字码
			String TicketNO = parse.textTrim("TicketNO",IBDetailInfo);//回填的票号
			cjrxmTmp+= ","+PassengerName;
			
			
			String cjrlx = "1";
			if("ADU".equals(AgeType)){
				cjrlx = "1";
			}else if("CHI".equals(AgeType)){
				cjrlx = "2";
			}else if("BAB".equals(AgeType)){
				cjrlx = "3";
			}
			String zjlx="ID";
			if("1".equals(CardType)){
				zjlx="NI";
			}else if("2".equals(CardType)){
				zjlx="PP";
			}
			Double xs_tax = NumberUtils.toDouble(OilFee,0.00);
			Double xs_jsf = NumberUtils.toDouble(Tax,0.00);
			Double xs_zdj = NumberUtils.toDouble(PrintPrice,0.00);
			Double xs_pj = NumberUtils.toDouble(Cost,0.00);
			double xs_je=Arith.add(xs_pj, NumberUtils.toDouble(Tax)+NumberUtils.toDouble(OilFee));
			if("I".equals(FlightClass)){//当是国际票时，基建为0，pj_tax取Tax节点。同时把OilFee节点值（Q值）加到账单价上面
				xs_jsf = 0.00;
				xs_tax = NumberUtils.toDouble(Tax,0.00);;
				xs_zdj = Arith.add(NumberUtils.toDouble(PrintPrice,0.00),NumberUtils.toDouble(OilFee,0.00));
				xs_je=Arith.add(NumberUtils.toDouble(Cost), NumberUtils.toDouble(Tax));
			}
			
			
			Map<String, String> cjrMap;
			if(cjrMaps.get(PassengerName)!=null){//非单程的情况下，要合计多段航程的总金额
				cjrMap = cjrMaps.get(PassengerName);
				String xs_zdj_tmp = cjrMap.get("XS_ZDJ");
				String xs_pj_tmp = cjrMap.get("XS_PJ");
				String xs_jsf_tmp = cjrMap.get("XS_JSF");//机建
				String xs_tax_tmp = cjrMap.get("XS_TAX");
				String xs_je_tmp = cjrMap.get("XS_JE");
				Double xs_pj_new = Arith.add(NumberUtils.toDouble(xs_pj_tmp), xs_pj);
				Double xs_zdj_new = Arith.add(NumberUtils.toDouble(xs_zdj_tmp), xs_zdj);
				Double xs_jsf_new = Arith.add(NumberUtils.toDouble(xs_jsf_tmp), xs_jsf);
				Double xs_tax_new = Arith.add(NumberUtils.toDouble(xs_tax_tmp), xs_tax);
				Double xs_je_new = Arith.add(NumberUtils.toDouble(xs_je_tmp), xs_je);
				cjrMap.put("XS_PJ", ""+xs_pj_new);
				cjrMap.put("XS_ZDJ", ""+xs_zdj_new);
				cjrMap.put("XS_JSF", ""+xs_jsf_new);
				cjrMap.put("XS_TAX", ""+xs_tax_new);
				cjrMap.put("XS_JE", ""+xs_je_new);
				cjrMap.put("SXH",cjrMaps.size()+1+"");
			}else{
				cjrMap = new HashMap<String, String>();
				cjrMap.put("CJR", PassengerName);
				cjrMap.put("CJRLX", cjrlx);
				cjrMap.put("ZJLX", zjlx);
				cjrMap.put("ZJHM", CardNo);
				cjrMap.put("XB", Gender);
				if(StringUtils.isNotBlank(BirthDate)){
					BirthDate=BirthDate.substring(0,10);
				}
				cjrMap.put("CSRQ", BirthDate);
				cjrMap.put("XS_ZDJ", xs_zdj+"");
				cjrMap.put("XS_PJ", xs_pj+"");
				cjrMap.put("XS_JSF", xs_jsf+"");
				cjrMap.put("XS_TAX", xs_tax+"");
				cjrMap.put("XS_JE", ""+xs_je);
				cjrMap.put("GJ", Nationality);
				cjrMap.put("XB", Gender);
				if(StringUtils.isNotBlank(CardTimelimit)){
					CardTimelimit=CardTimelimit.substring(0,10);
				}
				cjrMap.put("ZJYXQ", CardTimelimit);
			}
			cjrMaps.put(PassengerName, cjrMap);
			
			if(hdXh.indexOf(Sequence)==-1){
				hdXh+=","+Sequence;
				Map<String, String> hdMap = new HashMap<String, String>();
				hdMap.put("WBSXH", Sequence);
				hdMap.put("XS_HBH", Flight);
				hdMap.put("XS_CW", SubClass);
				String cfsj = StringUtils.replace(TakeOffTime, "T", " ");
				hdMap.put("CFSJ", cfsj);
				String ddsj = StringUtils.replace(ArrivalTime, "T", " ");
				hdMap.put("DDSJ", ddsj);
				hdMap.put("CFCITY", DPort);
				hdMap.put("DDCITY", APort);
				hdMap.put("XS_TGQ", EIRemark);
				if(StringUtils.isBlank(Flight)){//航班号为空时
					hdMap.put("HBH", "ARNK");
				}
				if(StringUtils.isNotBlank(CarrierFlight)&&!"null".equalsIgnoreCase(CarrierFlight)){//如果实际承运航班号节点不为空
					hdMap.put("HBH", "*"+Flight);//HBH节点要加*共享航班标记
					hdMap.put("OP_HKGS",CarrierFlight);
				}
				hdList.add(hdMap);
			}
		}
		List<Map<String, String>> cjrList =new ArrayList<Map<String,String>>();
		cjrList.addAll(cjrMaps.values());
		OrderBean orderBean=new OrderBean();
		orderBean.setKhddMap(khddMap);
		orderBean.setCjrList(cjrList);
		orderBean.setHdList(hdList);
		

		return orderBean;
	}
	public static boolean parseModify(JpPtLog ptlb,String xml) throws Exception{
		ParseXml parse=null;
		try{
			parse = new ParseXml(xml);
		}catch (Exception e) {
			e.printStackTrace();
			ptlb.add("解析修改订单返回XML发生异常：异常信息："+e.getMessage()+",XML:"+xml);
			throw new Exception("解析修改订单返回XML发生异常：异常信息："+e.getMessage()+",XML:"+xml);
		}
		Element responseEle =parse.ele("OpenModifyOrderResponse");
		if(responseEle==null){
			ptlb.add("解析修改订单返回XML未发现OpenModifyOrderResponse节点");
			throw new Exception("解析修改订单返回XML未发现OpenModifyOrderResponse节点");
		}
		String ResultCode=parse.textTrim("ResultCode",responseEle);//0:成功 -1:失败 
		String Message=parse.textTrim("Message",responseEle);//订单不属于该用户或订单已出票
		
		if("0".equals(ResultCode)){
			return true;
		}else{
			ptlb.add("修改订单失败："+Message);	
			throw new Exception("修改订单失败："+Message);
		}
	}
	
	public static String[] parsePnrPermission(JpPtLog ptlb,String xml) throws Exception{
		ParseXml parse=null;
		String [] result = new String[2];
		result[0] = "-1";
		result[1] = "";
		try{
			parse = new ParseXml(xml);
		}catch (Exception e) {
			e.printStackTrace();
			ptlb.add("解析PNR授权返回XML发生异常：异常信息："+e.getMessage()+",XML:"+xml);
			result[1] = "解析PNR授权返回XML发生异常：异常信息："+e.getMessage()+",XML:"+xml;
			return result;
		}
		Element headerEle = parse.ele("Header");
		String resultCode=parse.attr("ResultCode",headerEle);
		if(!"Success".equals(resultCode)){
			String resultMsg=parse.attr("ResultMsg",headerEle);
			ptlb.add("解析到订单列表返回XML中有错误提示信息："+ resultMsg);
			result[1] = "授权失败，携程返回："+resultMsg;
			return result;
		}
		Element responseEle =parse.ele("GetPnrPermissionResponse");
		if(responseEle==null){
			ptlb.add("解析PNR授权返回XML未发现GetPnrPermissionResponse节点");
			result[1] = "授权失败，解析PNR授权返回XML未发现GetPnrPermissionResponse节点";
			return result;
		}
		String ResultCode=parse.textTrim("ResultCode",responseEle);//0:成功 -1:失败 
		String Message=parse.textTrim("Message",responseEle);//订单不属于该用户或订单已出票
		
		result[0] = ResultCode;
		result[1] = Message;
		
		if(!"0".equals(ResultCode)){
			ptlb.add("PNR授权失败："+Message);	
		}
		return result;
	}
	public static String[] parseLockOrder(JpPtLog ptlb,String xml) throws Exception{
		ParseXml parse=null;
		String [] result = new String[2];
		result[0] = "-1";
		result[1] = "";
		try{
			parse = new ParseXml(xml);
		}catch (Exception e) {
			e.printStackTrace();
			ptlb.add("解析锁单返回XML发生异常：异常信息："+e.getMessage()+",XML:"+xml);
			result[1] = "解析锁单返回XML发生异常：异常信息："+e.getMessage()+",XML:"+xml;
			return result;
		}
		Element responseEle =parse.ele("OpenModifyLockOrderResponse");
		if(responseEle==null){
			ptlb.add("解析锁单返回XML未发现OpenModifyLockOrderResponse节点");
			result[1] = "解析锁单返回XML未发现OpenModifyLockOrderResponse节点";
			return result;
		}
		String ResultCode=parse.textTrim("ResultCode",responseEle);//0:成功 -1:失败 
		String Message=parse.textTrim("Message",responseEle);//订单不属于该用户或订单已出票
		
		result[0] = ResultCode;
		result[1] = Message;
		
		if(!"0".equals(ResultCode)){
			ptlb.add("锁单失败："+Message);	
		}
		return result;
	}
	public static boolean parseIBAssignTo(JpPtLog ptlb,String xml) throws Exception{
		ParseXml parse=null;
		try{
			parse = new ParseXml(xml);
		}catch (Exception e) {
			e.printStackTrace();
			ptlb.add("解析认领接口返回XML发生异常：异常信息："+e.getMessage()+",XML:"+xml);
			return false;
		}
		Element responseEle =parse.ele("OpenIssueBillAssignResponse");
		if(responseEle==null){
			ptlb.add("解析认领接口返回XML未发现OpenIssueBillAssignResponse节点");
			return false;
		}
		String Result=parse.textTrim("Result",responseEle);//0:成功 -1:失败 
		String Description=parse.textTrim("Description",responseEle);//订单不属于该用户或订单已出票		
		if(!"0".equals(Result)){
			ptlb.add("返回的错误描述为："+Description);
			return false;
		}
		ptlb.add("认领接口调用成功！");
		return true;
	}
	/**
	 * 解析政策代码，取出投放方案代码翻入TFID节点中
	 * V50_GJDYIHKWEE314635422
	 * @return
	 */
	private static void resolveZCDM(String zcdm,Map<String,String> khddMap){
		String zc_qd = "";
		if(StringUtils.isBlank(zcdm)){//不能为空
			return;
		}
		if(!StringUtils.startsWith(zcdm, "V")&&!StringUtils.startsWith(zcdm, "v")){//必须V开头
			return;
		}
		if(StringUtils.indexOf(zcdm, "_")==-1){//必须包含下划线
			return;
		}
		int index = zcdm.indexOf("_");
		zc_qd = zcdm.substring(index+1,index+2);
		khddMap.put("ZC_QD", zc_qd);
	}
	/**
	 * 解析政策说明字符串，并将其存放进khddMap里面
	 * 根据政策渠道不同，政策说明字符串也不相同
	 * G 官网[J,3U2200.0,FD0,返点0.0,留点-1.38,留钱0.0,3U2][12 10:17]
	 * Z 自有[M,FD1890,1890,返点3.1,0,留点0.0,留钱-4.0,d174bf64fd5d474aa7799610a56d9979][12 06:27]
     * P 八千翼[H,FD880,返点0.2,留点0.0,留钱0.0,225837027][12 09:24]
     * N NFD[Z,NFD340,返点0.0,留点0.0,留钱1.0,17866466][12 12:27]
     * T 旗舰店[L,3U830,FD830,返点0.0,留点0.0,留钱2.0,null][12 12:01]
     * B
	 */
	private static void resolveZCSM(String zcsm,String zcqd,Map<String,String> khddMap){
		if(StringUtils.isBlank(zcqd)){//默认当做自有政策格式处理
			zcqd = "Z";
		}
		String zcfd = ""; //政策返点
		String wdld = ""; //网店留点
		String wdlq = ""; //网店留钱
		String zc_tfid = ""; //政策ID
		try {
			List<String> tempArr = new ArrayList<String>();
			//51BOOK[E,FD890,返点6.2,留点-0.5,留钱-2.0,155295997][12 08:09]
			if(StringUtils.isNotBlank(zcsm)){
				Pattern pattern = Pattern.compile("(?<=\\[)[^\\]]+");      
				Matcher matcher = pattern.matcher(zcsm);
				
				while(matcher.find())  {
					tempArr.add(matcher.group());
				}
			}
			if(tempArr.size()<=0){
				return;
			}
			String[] str1Arr = tempArr.get(0).split(",");
			if("Z".indexOf(zcqd)>-1){
				zcfd = str1Arr[3].replace("返点", ""); //政策返点
				wdld = str1Arr[5].replace("留点", ""); //网店留点
				wdlq = str1Arr[6].replace("留钱", ""); //网店留钱
				zc_tfid = str1Arr[7];
			}else if("G,T".indexOf(zcqd)>-1){
				zcfd = str1Arr[3].replace("返点", ""); //政策返点
				wdld = str1Arr[4].replace("留点", ""); //网店留点
				wdlq = str1Arr[5].replace("留钱", ""); //网店留钱
				zc_tfid = str1Arr[6];
			}else if("P,N".indexOf(zcqd)>-1){
				zcfd = str1Arr[2].replace("返点", ""); //政策返点
				wdld = str1Arr[3].replace("留点", ""); //网店留点
				wdlq = str1Arr[4].replace("留钱", ""); //网店留钱
				zc_tfid = str1Arr[5];
			}else{
				return;
			}
			khddMap.put("ZC_FD", zcfd); //政策返点
			khddMap.put("ZC_LD", wdld); //政策留点
			khddMap.put("ZC_LQ", wdlq); //政策留钱
			khddMap.put("ZC_TFID", zc_tfid); //政策ID
		} catch (Exception e) {
			e.printStackTrace();
//			PlatUtils.ptlog(TaobaoGy2.plat, "", "外部单号"+khddMap.get("GY_DDBH")+",解析政策说明字符串报错"+zcsm);
		}
	}
	public static void main(String[] args) throws Exception {
		String zcdm="VHZXCA_P0TVLXACTUY1746180";
		String zc_qd = "";
		if(StringUtils.isBlank(zcdm)){//不能为空
			return;
		}
		if(!StringUtils.startsWith(zcdm, "V")&&!StringUtils.startsWith(zcdm, "v")){//必须V开头
			return;
		}
		if(StringUtils.indexOf(zcdm, "_")==-1){//必须包含下划线
			return;
		}
		int index = zcdm.indexOf("_");
		zc_qd = zcdm.substring(index+1,index+2);
		System.out.println(zc_qd);
	}
}
