package cn.vetech.vedsb.jp.jpddsz.tongcheng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.modules.utils.Arith;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ParseTongchengGy {
	public static List<String> parseOrderIdList(String resJson,JpPtLog ptlb){	
		JSONObject resObj = JSONObject.parseObject(resJson);
		String msg = checkResJson(resJson);
		if(StringUtils.isNotBlank(msg)){
			ptlb.add("订单列表查询接口返回存在错误信息:"+msg);
			return null;
		}
		JSONArray orderList = resObj.getJSONArray("OrderList");
		if(CollectionUtils.isEmpty(orderList)){
			ptlb.add("解析到订单列表返回json中无订单信息节点OrderList");
			ptlb.add("没有需要导入的订单");
			return null;
		}
		List<String> orderIdList=new ArrayList<String>();
		for(int i=0;i<orderList.size();i++){
			JSONObject oneOrder = orderList.getJSONObject(i);
			String OrderSerialid = oneOrder.getJSONObject("OrderInfo").getString("OrderSerialid");
			orderIdList.add(OrderSerialid);
		}
		return orderIdList;
	}
	public static List<OrderBean> parseOrderList(JpPtLog ptlb,String resJson,JpDdsz jpDdsz) throws Exception{
		JSONObject resObj = JSONObject.parseObject(resJson);
		String msg = checkResJson(resJson);
		if(StringUtils.isNotBlank(msg)){
			ptlb.add("订单详细接口返回存在错误信息:"+msg);
			return null;
		}
		//用PNR做key存放乘机人集合
		Map<String, List<Map<String, String>>> cjrListMap = new HashMap<String, List<Map<String,String>>>();
		Map<String, String> khddMap = new HashMap<String, String>();
		List<Map<String, String>> cjrList =new ArrayList<Map<String,String>>();
		List<Map<String, String>> hdList =new ArrayList<Map<String,String>>();
		String orderSerialid = resObj.getString("OrderSerialid");
		//订单基本信息
		JSONObject baseInfo = resObj.getJSONObject("BaseInfo");
		String orderStatus = baseInfo.getString("OrderStatus");//订单状态 N已支付待出票
		String flightWay = baseInfo.getString("FlightWay");//航程类型 S单程
		String orderDesc = baseInfo.getString("OrderDesc");//订单描述
		String pnr = baseInfo.getString("PNR");//PNR
		String bigPNR = baseInfo.getString("BigPNR");//大编码
		String cpnr = baseInfo.getString("CPNR");//儿童PNR
		String cBigPNR = baseInfo.getString("CBigPNR");//儿童大编码
		String allPrice = baseInfo.getString("AllPrice");//总价  应付总价
		String allFacePrice = baseInfo.getString("AllFacePrice");//总票面价  票面价*乘客数量
		String AllSalePrice = baseInfo.getString("AllSalePrice");//总销售价  票面价*（1-返点）*乘客数量
		String AllTaxPrice = baseInfo.getString("AllTaxPrice");//总机建燃油 
		//政策信息
		JSONObject policyInfo = resObj.getJSONObject("PolicyInfo");
		String policyId = policyInfo.getString("PolicyId");//政策id 供应商的政策 ID
		String policyType = policyInfo.getString("PolicyType");//政策类型  如：“普通政策”
		String benefit = policyInfo.getString("Benefit");//返点 如：1.5
		String policyRemark = policyInfo.getString("PolicyRemark");//政策备注
		//联系人信息
		JSONObject contractInfo = resObj.getJSONObject("ContractInfo");
		String contractName = contractInfo.getString("ContractName");//联系人姓名
		String linkMobiel = contractInfo.getString("LinkMobiel");//联系人手机
		String wd_zclx = policyType;		
		/**
		 * 普通政策  1
		 * 见舱低折扣 40
		 * 见舱低价格 41
		 * 固定舱位价格 42
		 * 特价舱位 21
		 */
		if("1".equals(policyType)){
			wd_zclx = "普通政策";
		}else if("40".equals(policyType)){
			wd_zclx = "见舱低折扣";
		}else if("41".equals(policyType)){
			wd_zclx = "见舱低价格";
		}else if("42".equals(policyType)){
			wd_zclx = "固定舱位价格";
		}else if("21".equals(policyType)){
			wd_zclx = "特价舱位";
		}
		khddMap.put("SHBH",jpDdsz.getShbh());
		khddMap.put("DDYH", jpDdsz.getDdUserid());
		khddMap.put("DDBM", jpDdsz.getDdBmid());
		khddMap.put("WBDH", orderSerialid);
		khddMap.put("WDID", jpDdsz.getWdid());
		khddMap.put("WDPT", jpDdsz.getWdpt());
		khddMap.put("JJD", "");
		khddMap.put("NOSX", "");
		khddMap.put("DDLX", "1");	
		khddMap.put("WD_ZCLX", wd_zclx);
		khddMap.put("SKKM", jpDdsz.getDdMrkm());
		khddMap.put("WD_ZCDM", policyId);
		khddMap.put("NXR", contractName);
		khddMap.put("NXRSJ", linkMobiel);
		resolveZCDM(policyId,khddMap);
		resolveZCSM(policyRemark,khddMap.get("ZC_QD"),khddMap);
		//航班信息
		JSONArray flightList = resObj.getJSONArray("FlightList");
		for(int i=0;i<flightList.size();i++){
			Map<String, String> hdMap = new HashMap<String, String>();
			JSONObject oneFlight = flightList.getJSONObject(i);
			String sequence = oneFlight.getString("Sequence");//航班序号  去程：1，返程：2
			String flightNo = oneFlight.getString("FlightNo");//航班号 ，如：FM2145
			String Class = oneFlight.getString("Class");//舱位等级  舱位等级，如：Y、C、F
			String subClass = oneFlight.getString("SubClass");//实际舱位  订单实际舱位，如：F
			String dport = oneFlight.getString("Dport");
			String aport = oneFlight.getString("Aport");
			String takeOffTime = StringUtils.trimToEmpty(oneFlight.getString("TakeOffTime"));//起飞时间  eg:2014/12/8 10:05:00
			String arrivalTime = StringUtils.trimToEmpty(oneFlight.getString("ArrivalTime"));//抵达时间
			takeOffTime = formatDateStr(takeOffTime);
			arrivalTime = formatDateStr(arrivalTime);
			hdMap.put("XS_HBH", flightNo);
			hdMap.put("XS_CW", subClass);
			hdMap.put("CFSJ", takeOffTime);
			hdMap.put("DDSJ", arrivalTime);
			hdMap.put("CFCITY", dport);
			hdMap.put("DDCITY", aport);
			hdMap.put("TGQ", "");
			hdList.add(hdMap);
		}
		//乘机人信息
		JSONArray passengerList = resObj.getJSONArray("PassengerList");
		for(int i=0;i<passengerList.size();i++){
			Map<String, String> cjrMap = new HashMap<String, String>();
			JSONObject passenger = passengerList.getJSONObject(i);
			String passengerName = passenger.getString("PassengerName");//乘机人姓名
			String passengerType = passenger.getString("PassengerType");//乘客类型  1:成人  2：儿童  3：婴儿
			String certType = passenger.getString("CertType");//证件类型 0：身份证 1：护照 2：军官回乡证 5：台胞证 9：其他
			String certNO = passenger.getString("CertNO");//证件号
			String facePrice = passenger.getString("FacePrice");//票面价 
			String salePrice = passenger.getString("SalePrice");//销售价
			String airPortBuildFee = passenger.getString("AirPortBuildFee");//机建费
			String oileFee = passenger.getString("OileFee");//燃油费
			String eticketNo = passenger.getString("EticketNo");//电子客票号  暂时不用解析
			String tCabinCode = passenger.getString("TCabinCode");//实际舱位  乘客实际舱位
			String subPnr = passenger.getString("SubPNR");//
			String subBigPnr = passenger.getString("SubBigPNR");//
			String zjlx="ID";
			if(StringUtils.isBlank(passengerType)||"null".equalsIgnoreCase(passengerType)){
				passengerType = "1";
			}
			if(StringUtils.isBlank(subPnr)){
				if("2".equals(passengerType)){
					subPnr = cpnr;
				}else{
					subPnr = pnr;
				}
			}
			if(StringUtils.isBlank(subPnr)){//当PNR为空时 乘机人类型1/3算成人组  其他算儿童组。用于无PNR时按成人和儿童拆分订单
				subPnr = NumberUtils.toInt(passengerType)%2 == 1 ? "成人" : "儿童";
			}
			if(StringUtils.isBlank(subBigPnr)){
				if("2".equals(passengerType)){
					subBigPnr = cBigPNR;
				}else{
					subBigPnr = bigPNR;
				}
			}
//			if(StringUtils.isBlank(subPnr)){//当存在儿童乘机人类型，同时cpnr为空时，pnr和bigpnr取成人的。
//				subPnr = pnr;
//				subBigPnr = bigPNR;
//			}
			if("0".equals(certType)){
				zjlx="NI";
			}else if("1".equals(certType)){
				zjlx="PP";
			}
			cjrMap.put("TMP_BIGPNR", subBigPnr);//非订单包字段给后面取的。
			cjrMap.put("CJR", passengerName);
			cjrMap.put("CJRLX", passengerType);
			cjrMap.put("ZJLX", zjlx);
			cjrMap.put("ZJHM", certNO);
			cjrMap.put("XS_JSF", airPortBuildFee);
			cjrMap.put("XS_TAX", oileFee);
			cjrMap.put("XS_ZDJ", facePrice);
			cjrMap.put("XS_PJ", salePrice);
			Double jjry = Arith.add(NumberUtils.toDouble(airPortBuildFee, 0.0), NumberUtils.toDouble(oileFee, 0.0));//机建燃油
			Double xs_je = Arith.add(NumberUtils.toDouble(salePrice, 0.0), jjry);
			cjrMap.put("XS_JE", xs_je+"");
			String key = subPnr;
			List<Map<String,String>> cjrListTemp = cjrListMap.get(key);
			if(cjrListTemp==null){
				cjrListTemp = new ArrayList<Map<String,String>>();
			}
			cjrMap.put("SXH",cjrListTemp.size()+1+"");
			cjrListTemp.add(cjrMap);
			cjrListMap.put(key,cjrListTemp);
		}
		
		List<OrderBean> orderBeanList=new ArrayList<OrderBean>();
		int index = 1;
		for(String key : cjrListMap.keySet()){
			OrderBean orderBean=new OrderBean();
			Map<String, String> khddMap1 = new HashMap<String, String>();
			String xs_hkgs_pnr = cjrListMap.get(key).get(0).get("TMP_BIGPNR");
			khddMap1.putAll(khddMap);
			khddMap1.put("XS_PNR_NO", key);
			khddMap1.put("XS_HKGS_PNR", xs_hkgs_pnr);
			khddMap1.put("DDXH", index+"");
			index++;
			List<Map<String,String>> hdList_new = new ArrayList<Map<String,String>>();
			hdList_new.addAll(hdList);
			String cw = cjrListMap.get(key).get(0).get("TMP_TCABINCODE");
			if(StringUtils.isNotBlank(cw)){
				for(int j=0;j<hdList_new.size();j++){
					hdList_new.get(j).put("CW", cw);
				}
			}
			orderBean.setKhddMap(khddMap1);
			orderBean.setHdList(hdList_new);
			orderBean.setCjrList(cjrListMap.get(key));
			orderBeanList.add(orderBean);
		}
		return orderBeanList;
	}
	public static String parseOrderConfirm(String resJson,JpPtLog ptlb){
		String msg = checkResJson(resJson);
		if(StringUtils.isNotBlank(msg)){
			ptlb.add("确认订单接口返回存在错误信息:"+msg);
			return "确认订单接口返回存在错误信息:"+msg;
		}
		ptlb.add("确认订单接口返回正常");
		return "";
	}
	public static String parseTicketNotify(String resJson,JpPtLog ptlb){
		String msg = checkResJson(resJson);
		if(StringUtils.isNotBlank(msg)){
			ptlb.add("票号回填接口返回存在错误信息:"+msg);
			return "票号回填接口返回存在错误信息:"+msg;
		}
		ptlb.add("票号回填接口返回正常");
		return "";
	}
	/**
	 * 检查接口返回是否有错误信息
	 * @return
	 */
	private static String checkResJson(String resJson){
		if(StringUtils.isBlank(resJson)){
			return "接口返回空，无法解析";
		}
		String msg = "";
		JSONObject resObj = JSONObject.parseObject(resJson);
		String errorCode = resObj.getString("ErrorCode");
		if(!"100000".equals(errorCode)){
			String errorMsg = resObj.getString("ErrorMsg");			
			if("NODATA".equals(errorMsg)){
				msg = "请求数据为空";
			}else if("DATAFORMAT_INVALID".equals(errorMsg)){
				msg = "数据格式错误";
			}else if("USERNAME_OR_PASSWORD_ERROR".equals(errorMsg)){
				msg = "用户名或者密码错误";
			}else if("DATERANGE_ERROR".equals(errorMsg)){
				msg = "时间区间错误";
			}else if("ORDERSTATUS_INVALID".equals(errorMsg)){
				msg = "无效的订单状态";
			}else if("ORDERLIST_EMPTY".equals(errorMsg)){
				msg = "订单列表为空";
			}else if("ORDERDETAIL_IS_NULL".equals(errorMsg)){
				msg = "无效订单号";
			}else if("TICKETINFO_ERROR".equals(errorMsg)){
				msg = "出票信息错误";
			}else if("TICKETINFO_NAMEERROR".equals(errorMsg)){
				msg = "出票信息乘客姓名错误";
			}else if("HASTICKETED".equals(errorMsg)){
				msg = "该订单已出票，请勿重复提交";
			}else if("HASREFUNDED".equals(errorMsg)){
				msg = "该订单已退票，无法通知出票";
			}else if("UNKNOWN_SYSTEM_ERROR".equals(errorMsg)){
				msg = "未知系统异常";
			}else if("REQUESTBUSY".equals(errorMsg)){
				msg = "请求过于繁忙";
			}
			msg += "("+errorMsg+")";
		}
		return msg;
	}
	/**
	 * 时间格式字符串进行转换，将2015/1/1 09:00:00格式字符串转换成2015-01-01 09:00:00格式
	 */
	private static String formatDateStr(String dateStr){
		String[] strArr = dateStr.split("-");
		if(strArr[1].length()==1){
			strArr[1] = "0"+strArr[1];
		}
		if(strArr[2].length()!=11){
			String[] strArr_tmp = strArr[2].split(" ");
			if(strArr_tmp[0].length()==1){//如果日期格式长度少于2
				strArr_tmp[0] = "0"+strArr_tmp[0];
			}
			if(strArr_tmp[1].length()==7){
				strArr_tmp[1] = "0"+strArr_tmp[1];
			}
			strArr[2] = StringUtils.join(strArr_tmp, " ");
		}		
		return StringUtils.join(strArr, "-");
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
