package cn.vetech.vedsb.jp.jpddsz.kuxun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Element;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.ParseXml;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;

public class ParseKuxunGy {
	public static void ddInfo(Element OrderInfo, ParseXml parse,List<OrderBean> listOrder,JpDdsz jpDdsz, JpPtLog ptlb) {
		Map<String, String> khddMap = new HashMap<String, String>();
		//String no=parse.attr("no",OrderInfo);//搜索结果中机票订单序号，起始值为 1
		String flightOrderId=parse.attr("flightOrderId",OrderInfo);//机票订单 id，由酷讯生成的（18 位）唯一值
		String source=parse.attr("source",OrderInfo);//订单来源
		String ticketSource=parse.attr("ticketSource",OrderInfo);//政策代码（代理商上传政策时自定义）
		String ticketType=parse.attr("ticketType",OrderInfo);//b2c 平台，航空公司官网等
		String ticketPlat=parse.attr("ticketPlat",OrderInfo);//51book,百拓等具体出票平台
		String policyId=parse.attr("policyId",OrderInfo);//代理商上传政策后酷讯分配的唯一政策 id
		String payChannel=parse.attr("payChannel",OrderInfo);//最后一次支付使用的支付渠道（目前暂时只支持 3 种支付渠道） ALIPAY：支付宝 BILLPAY：快钱 TENPAY：财付通
		String payStatus=parse.attr("payStatus",OrderInfo);//支付金额的支付分账退款状态 NOPAY: 未支付 PAYED: 已支付 SPLIT: 已分账 REFUND: 已退款
		String productType=parse.attr("productType",OrderInfo);//产品类型，取值范围如下： common: 普通政策 customer: 包机政策 prepay: 预付政策 lowprice: 特价政策 apply: 申请政策
		
		
		String rcsdesc=parse.attr("rcsdesc",OrderInfo);//退改签说明
		String cabinNote=parse.attr("cabinNote",OrderInfo);//代理商添加政策时填入的舱位说明
		String expressPrice=parse.attr("expressPrice",OrderInfo);//快递费
		String tripType=parse.attr("tripType",OrderInfo);//行程单或发票
		String linkmanName=parse.attr("linkmanName",OrderInfo);//收件人姓名
		String linkmanMobile=parse.attr("linkmanMobile",OrderInfo);//收件人电话
		String linkmanAddress=parse.attr("linkmanAddress",OrderInfo);//收件人地址
		String expressCompany=parse.attr("expressCompany",OrderInfo);//快递公司
		String expressType=parse.attr("expressType",OrderInfo);//快递费支付方式 1: 预付 2: 到付
		String expressNo=parse.attr("expressNo",OrderInfo);//快递单号（若已配送则值不为空）
		String expressDate=parse.attr("expressDate",OrderInfo);//快递日期
		
		String insurancePrice=parse.attr("insurancePrice",OrderInfo);//保单单价
		String ticketPrice=parse.attr("ticketPrice",OrderInfo);//成人票面价
		//String salePrice=parse.attr("salePrice",OrderInfo);//成人销售价
		String adultPnr=parse.attr("adultPnr",OrderInfo);//成人 PNR
		String childPnr=parse.attr("childPnr",OrderInfo);//儿童 PNR
		String constructionFee=parse.attr("constructionFee",OrderInfo);//成人机场建设费
		String fuelTax=parse.attr("fuelTax",OrderInfo);//成人燃油附加税
		String childFuelTax=parse.attr("childFuelTax",OrderInfo);//儿童燃油附加税
		
		
		String flightAmount=parse.attr("flightAmount",OrderInfo);//订单金额，订单内所有机票的实际销售总额（若用户选择需要配送行程单 or 保险发票，且配送费支付方式为预付时，还包含配送费）
		String noPay=parse.attr("noPay",OrderInfo);//未支付金额
		String childParPrice=parse.attr("childParPrice",OrderInfo);//儿童票面价
		String childSalePrice=parse.attr("childSalePrice",OrderInfo);//儿童销售价
		
		/**
		 * 订单状态取值表如下：
			100: WAITTING_FOR_SEAT_CONFIRM: 等待座位确认
			101: WAITTING_FOR_PAYED: 订座成功等待支付
			102: WAITTING_FOR_PRICE_CONFIRM: 订座成功等待价格确认
			200: ORDER_CANCELLED: 订单取消
			300: PAYED_UNISSUED 支付成功等待出票
			400: TICKET_ISSUING 出票中
			401: ISSUE_DONE 出票完成
			500: APPLY_RESCHEDULE 改签申请
			501: RESCHEDULE_DONE 改签完成
			600: APPLY_REFUND_TICKET 退票申请
			601: UNISSUE_REFUND_MONEY 未出票申请退款
			602: WAITING_FOR_REFUND_MONEY 退票完成等待退款
			603: REFUND_DONE 退款完成
		 */
		String flightOrderStatus=parse.attr("flightOrderStatus",OrderInfo);//订单状态
		String contactName=parse.attr("contactName",OrderInfo);//订单联系人姓名
		String contactMobile=parse.attr("contactMobile",OrderInfo);//订单联系人手机号
		String contactEmail=parse.attr("contactEmail",OrderInfo);//订单联系人邮件地址
		String createTime=parse.attr("createTime",OrderInfo);//订单创建时间
		String needExpress=parse.attr("needExpress",OrderInfo);//是否需要配送（注：若需要配送且已购保险，则必须要配送保单） NO: 不需要配送 YES: 需要配送
		ptlb.add("订单编号：" + flightOrderId+",订单状态："+flightOrderStatus);
		if(!"300".equals(flightOrderStatus)&&!"PAYED_UNISSUED".equals(flightOrderStatus)&&!"支付成功等待出票".equals(flightOrderStatus)){
			ptlb.add("订单状态不是支付成功等待出票，该订单被忽略导入系统");
			return;
		}
		//新增解析节点pay，立面有交易流水号
		Element payElm = parse.ele("pay",OrderInfo);
		String paySerialNumber = parse.attr("paySerialNumber",payElm);//第三方交易流水号（仅指机票款）
		String payFee = parse.attr("payFee",payElm);//支付手续费率 支付手续费率
		

		khddMap.put("SHBH",jpDdsz.getShbh());
		khddMap.put("DDYH", jpDdsz.getDdUserid());
		khddMap.put("DDBM", jpDdsz.getDdBmid());
		khddMap.put("WBDH", flightOrderId);
		khddMap.put("WDID", jpDdsz.getWdid());
		khddMap.put("WDPT", jpDdsz.getWdpt());
		khddMap.put("JJD", "");
		khddMap.put("CPCS", "");
		khddMap.put("NOSX", "");
		khddMap.put("DDLX", "1");
		khddMap.put("DDZT", "1");	
		khddMap.put("SKKM", jpDdsz.getDdMrkm());
		khddMap.put("WD_ZCDM", policyId);
		khddMap.put("NXR", contactName);
		khddMap.put("NXRSJ", contactMobile);

		String payStatus_name="未支付";
		if("REFUND".equals(payStatus)){
			payStatus_name="已退款";
		}
		String pay_method_asms = "";
		if("PAYED".equals(payStatus)||"SPLIT".equals(payStatus)){
			//最后一次支付使用的支付渠道（目前暂时只支持 3 种支付渠道） ALIPAY：支付宝 BILLPAY：快钱 TENPAY：财付通
			if ("ALIPAY".equals(payChannel)) {
				pay_method_asms = "支付宝";
			} else if ("BILLPAY".equals(payChannel)) {
				pay_method_asms = "快钱";
			} else if ("TENPAY".equals(payChannel)) {
				pay_method_asms = "财付通";
			} else {
				pay_method_asms = payChannel;
			}
			if("PAYED".equals(payStatus)){
				payStatus_name="已支付";
			}else if("SPLIT".equals(payStatus)){
				payStatus_name="已分账";
			}
			payStatus_name+="  支付方式："+pay_method_asms;
		}

		String productTypeName="普通政策";
		if("customer".equals(productType)){
			productTypeName="包机政策";
		}else if("prepay".equals(productType)){
			productTypeName="预付政策";
		}else if("lowprice".equals(productType)){
			productTypeName="特价政策";
		}else if("apply".equals(productType)){
			productTypeName="申请政策";
		}
		khddMap.put("WD_ZCLX", productTypeName);
		khddMap.put("WD_ZCDM", ticketSource);
		resolveZCDM(ticketSource, khddMap);
		resolveZCSM(cabinNote,khddMap.get("ZC_QD"),khddMap);
		// 是否需要配送（注：若需要配送且已购保险，则必须要配送保单） NO: 不需要配送 YES: 需要配送
		String needPost="无需配送";
		if ("YES".equalsIgnoreCase(needExpress)) {
			if(tripType.indexOf("行程单")>-1){
				khddMap.put("SFYJXCD", "1");//是否邮寄行程单
			}
			khddMap.put("SJR", linkmanName);//收件人
			khddMap.put("YZBM", "");//邮政编码
			khddMap.put("XJDZ", linkmanAddress);//收件地址
			needPost="需要配送";
		}
//		khddMap.put("TGQ", rcsdesc+"<br>舱位说明："+cabinNote);//舱位销售说明		
		
		//乘机人信息
		Map<String, String> bzbzMap=new HashMap<String, String>();
		List<Map<String, String>> cr_cjrList = new ArrayList<Map<String, String>>();//成人航段
		List<Map<String, String>> et_cjrList = new ArrayList<Map<String, String>>();//儿童航段
		List<Element> cjrEleList=parse.get("passenger", OrderInfo);
		int cjrcnt=cjrEleList.size();
		int cjrnum = 0;
		boolean hasXcd = false;
		if(StringUtils.isBlank(expressPrice)){
			hasXcd = true;
		}
		for (Element cjrEle:cjrEleList) {
			String name=parse.attr("name",cjrEle);//乘机人姓名
			String type=parse.attr("type",cjrEle);//乘机人类型 0: 成人 1: 儿童
			String certificateType=parse.attr("certificateType",cjrEle);//证件类型 0: 身份证 1: 护照 2: 港澳通行证 3: 台胞证 4: 回乡证 5: 军人证 6: 其他
			String finalSalePrice=parse.attr("finalSalePrice",cjrEle);//最终销售价
			String priceType=parse.attr("priceType",cjrEle);//价格类型，取值列表如下： 1: 成人非套餐 2: 成人套餐 3: 成人特殊套餐 1 101: 儿童非套餐 102: 儿童套餐 103: 儿童特殊套餐 1 200: 未知
			String certificateNo=parse.attr("certificateNo",cjrEle);//证件号
			String ticketNo=parse.attr("ticketNo",cjrEle);//票号
			String insuranceCount=parse.attr("insuranceCount",cjrEle);//保险份数
			String insuranceCompany=parse.attr("insuranceCompany",cjrEle);//保险所在保险公司名称
			String insuranceName=parse.attr("insuranceName",cjrEle);//保单所属保险产品名称
			String cjrlx="1";
			if("1".equals(type)){
				cjrlx="2";
			}
			String zjlx="ID";
			if("0".equals(certificateType)){
				zjlx="NI";
			}else if("1".equals(certificateType)){
				zjlx="PP";
			}
			Map<String, String> cjrMap = new HashMap<String, String>();
			cjrMap.put("CJR", name);
			cjrMap.put("CJRLX", cjrlx);
			cjrMap.put("ZJLX", zjlx);
			cjrMap.put("ZJHM", certificateNo);
			
			
			if("2".equals(cjrlx)){
				cjrMap.put("XS_ZDJ", childParPrice);
				cjrMap.put("XS_PJ", finalSalePrice);
				cjrMap.put("XS_JSF", "0");
				cjrMap.put("XS_TAX", childFuelTax);			
				String drpj = Arith.round(NumberUtils.toDouble(finalSalePrice) +
						+ NumberUtils.toDouble(childFuelTax), 2)
						+ "";
				cjrMap.put("XS_JE", drpj);
				cjrMap.put("SXH",et_cjrList.size()+1+"");
				et_cjrList.add(cjrMap);
			}else{
				cjrMap.put("XS_ZDJ", ticketPrice);
				cjrMap.put("XS_PJ", finalSalePrice);
				cjrMap.put("XS_JSF", constructionFee);
				cjrMap.put("XS_TAX", fuelTax);				
				String drpj = Arith.round(NumberUtils.toDouble(finalSalePrice) + NumberUtils.toDouble(constructionFee)
						+ NumberUtils.toDouble(fuelTax), 2)
						+ "";
				cjrMap.put("XS_JE", drpj);
				cjrMap.put("SXH",cr_cjrList.size()+1+"");
				cr_cjrList.add(cjrMap);
			}
			
			
//			// 快递费平均在每个人上面
//			cjrMap.put("QT1", Arith.round(post_amount /cjrcnt, 2) + "");
			//快递费放在第一个人上面
			if(hasXcd&&(cjrlx.equals("1")||cjrnum == cjrEleList.size())) {
				hasXcd=false;
				cjrMap.put("XS_YJF", Arith.round(NumberUtils.toDouble(expressPrice), 2) + "");
			}
			
			// 保险分数
			int insuranceCountInt = NumberUtils.toInt(insuranceCount);
			if(insuranceCountInt>0){
				cjrMap.put("XS_HYXFS", insuranceCountInt+"");
			}
			cjrnum ++;
		}
		
		//航段信息
		List<Map<String, String>> cr_hdList = new ArrayList<Map<String, String>>();//成人航段
		List<Map<String, String>> et_hdList = new ArrayList<Map<String, String>>();//儿童航段
		List<Element> hdEleList=parse.get("segment", OrderInfo);
		int index = 1;
		for (Element hdEle:hdEleList) {
			String flightNo=parse.attr("flightNo",hdEle);
			String adultCabin=parse.attr("adultCabin",hdEle);//成人舱位
			String childCabin=parse.attr("childCabin",hdEle);//儿童舱位
			String departCode=parse.attr("departCode",hdEle);//起飞城市三字码
			String arriveCode=parse.attr("arriveCode",hdEle);//到达城市三字码
			String flightDate=parse.attr("flightDate",hdEle);//起飞日期
			String departTime=parse.attr("departTime",hdEle);//起飞时间
			String arriveTime=parse.attr("arriveTime",hdEle);//到达时间
			String realFlightNo=parse.attr("realFlightNo",hdEle);//实际航班号，不为空表示是共享航班
			
			
			if (StringUtils.isNotBlank(realFlightNo) && !realFlightNo.equalsIgnoreCase(flightNo)) {
				if (flightNo.indexOf("*") < 0) {
					flightNo = "*" + flightNo;
				}
			}
			
			Map<String, String> hdMap = new HashMap<String, String>();
			hdMap.put("XS_HBH", flightNo);
			hdMap.put("CFSJ", flightDate+" "+splitTime(departTime));
			hdMap.put("DDSJ", flightDate+" "+splitTime(arriveTime));
			hdMap.put("CFCITY", departCode);
			hdMap.put("DDCITY", arriveCode);
			hdMap.put("OP_HKGS", realFlightNo);
			if(cr_cjrList!=null &&  !cr_cjrList.isEmpty()){
				Map<String, String> hdMap_new = new HashMap<String, String>(hdMap);
				hdMap.put("XS_CW", adultCabin);
				cr_hdList.add(hdMap_new);
			}
			
			if(et_cjrList!=null && !et_cjrList.isEmpty()){
				Map<String, String> hdMap_new = new HashMap<String, String>(hdMap);
				hdMap.put("XS_CW", childCabin);
				et_hdList.add(hdMap_new);
			}
		}

		ptlb.add("酷讯成人编码"+ adultPnr);
		ptlb.add("酷讯儿童编码"+ childPnr);
		if(cr_cjrList!=null &&  !cr_cjrList.isEmpty()){
			OrderBean order=new OrderBean();
			Map<String, String> khddMap_new = new HashMap<String, String>(khddMap);
			khddMap_new.put("XS_PNR_NO", adultPnr);
			order.setKhddMap(khddMap_new);
			order.setCjrList(cr_cjrList);
			order.setHdList(cr_hdList);
			listOrder.add(order);
			ptlb.add("酷讯成人订单乘机人数,航段数"+ cr_cjrList.size()+","+cr_hdList.size());
		}
		if(et_cjrList!=null && !et_cjrList.isEmpty()){
			OrderBean order=new OrderBean();
			Map<String, String> khddMap_new = new HashMap<String, String>(khddMap);
			khddMap_new.put("XS_PNR_NO", childPnr);
			khddMap_new.put("DDXH", index+"");
			index++;
			order.setKhddMap(khddMap_new);
			order.setCjrList(et_cjrList);
			order.setHdList(et_hdList);
			listOrder.add(order);
			ptlb.add("酷讯儿童订单乘机人数,航段数"+ cr_cjrList.size()+","+cr_hdList.size());
		}
	}
	public static String splitTime(String time) {
		String t = time;
		if(time.length()==4) {
			t = time.substring(0, 2)+":"+time.substring(2);
		}
		return t;
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
}
