package cn.vetech.vedsb.jp.jpddsz.qunar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.HttpClientUtil;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;
import cn.vetech.vedsb.jp.entity.jpddsz.PlatJpBean;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.jpddsz.JpddGySuper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class QunarGy_GJ extends JpddGySuper {
	private String URL = "";

	private String USER;// daodan

	private String PASS;// shengyi456

	private String encode = "UTF-8";
	// 按用户存储上一次执行时间
	private static Map<String, String> PRETIMEMAP = new HashMap<String, String>();
	// 按用户保存lastid
//	private static Map<String, String> LASTIDMAP = new HashMap<String, String>();

	public static String plat = "10100010";

	private String gy_autoqr = "0";
	
	private JpDdsz jpDdsz;

	private String ddwzffs; //订单无支付方式传入（对应去哪的一种派单业务）；0导入订单时按设置的默认科目支付  1订单不自动支付 默认0
	public QunarGy_GJ(JpDdsz jpDdsz) {
		this.jpDdsz = jpDdsz;
		this.USER = jpDdsz.getDdJkzh();
		this.PASS = jpDdsz.getDdJkmm();
		this.URL = jpDdsz.getDdJkdz();
	}



	@Override
	public List<OrderBean> queryOrders(JpPtLog ptlb) throws Exception {
		List<OrderBean> list_gj = new ArrayList<OrderBean>();
		String nowtime = VeDate.getStringDate();
		// 按去哪要求国际改成5分钟请求一次
		String pretime = PRETIMEMAP.get(USER+URL);
		if (StringUtils.isNotBlank(pretime) && VeDate.getPreMin(pretime, 5).compareTo(nowtime) >= 0) {
			return list_gj;
		}
		PRETIMEMAP.put(USER+URL, nowtime);
		list_gj = queryOrders_gj(ptlb);
		return list_gj;
	}
	@Override
	public List<OrderBean> queryOrders(JpPtLog ptlb,String dateStr) throws Exception {
		List<OrderBean> list_gj = new ArrayList<OrderBean>();
		list_gj = queryOrders_gj(ptlb);
		return list_gj;
	}
	/**
	 * 从QueryOrders中抽出来的国际票导单代码
	 * @param ptlb
	 * @param lastid
	 * @return
	 * @throws Exception
	 */
	public List<OrderBean> queryOrders_gj(JpPtLog ptlb) throws Exception{
		List<OrderBean> list = new ArrayList<OrderBean>();	
		String jsonStr = requestOrderexport_pay_ok_gj(ptlb);
		JSONObject jsom = JSONObject.parseObject(jsonStr);
		String ret = jsom.getString("ret");
		JSONArray data = new JSONArray();
		if("true".equals(ret)){
			data = jsom.getJSONArray("data");
		}else{
			throw new Exception("国际订单导入失败！，错误原因:"+jsom.getString("errmsg"));
		}
		for (int i = 0; i < data.size(); i++) {
			JSONObject oneJsom = data.getJSONObject(i);
			ddInfo_gj(oneJsom, list, ptlb);
		}
		return list;
	}
	private void ddInfo_gj(JSONObject oneJsom, List<OrderBean> listOrder, JpPtLog ptlb) {		
		Map<String, String> khddMap = new HashMap<String, String>();
		List<Map<String, String>> hdList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> cjrList = new ArrayList<Map<String,String>>();
		
		String order_no = StringUtils.trimToEmpty(oneJsom.getString("order_no"));//供应订单编号
		String flight_type = StringUtils.trimToEmpty(oneJsom.getString("flight_type"));//航程类型 1单程 2往返 3联程
		String status = StringUtils.trimToEmpty(oneJsom.getString("status")); //订单状态  这里导单通常是1。0订座成功等待支付；1支付成功等待出票；2出票完成；5出票中；12订单取消；
		Integer children_count = oneJsom.getInteger("children_count");//儿童数
		Integer adults_count = oneJsom.getInteger("adults_count");//成人数
		Integer passenger_count = oneJsom.getInteger("passenger_count");//总乘客数
		Integer flight_segment_num = oneJsom.getInteger("flight_segment_num"); //行程的第几段
		Double adult_price = oneJsom.getDouble("adult_price");//成人票价
		Double adult_tax = oneJsom.getDouble("adult_tax");//成人税
		Double child_price = oneJsom.getDouble("child_price");//儿童票价
		Double child_tax = oneJsom.getDouble("child_tax");//儿童税
		Double old_allprice = oneJsom.getDouble("old_allprice");//订单总价旧值
		Double allprice = oneJsom.getDouble("allprice"); //订单总价
		Double no_pay = oneJsom.getDouble("no_pay");  //未支付价格
		Double bx_fee = oneJsom.getDouble("bx_fee");  //保险价格
		Integer bx_count = oneJsom.getInteger("bx_count"); //保险份数
		Double increase_price = oneJsom.getDouble("increase_price");//订单账单价
		String policy_id = StringUtils.trimToEmpty(oneJsom.getString("policy_id")); //政策id
		String source = StringUtils.trimToEmpty(oneJsom.getString("source")); //订单来源 self 普通政策  qfare 公布运价  spec 特殊
		//判断是否是"null"
		if("null".equals(source)) {
			source = "";
		}
		String wd_zclx = source;
		if("self".equals(source)){
			wd_zclx = "普通政策";
		}else if("qfare".equals(source)){
			wd_zclx = "公布运价";
		}else if("spec".equals(source)){
			wd_zclx = "特殊";
		}
		String pnr_code = StringUtils.trimToEmpty(oneJsom.getString("pnr_code")); //pnr
		pnr_code = (pnr_code.equals("null")?"":pnr_code);
		String pnr_status = StringUtils.trimToEmpty(oneJsom.getString("pnr_status")); //记录状态   0 PNR 当前有效; 1 PNR 清理成功; 2PNR 清理失败
//		String order_need_confirm = oneJsom.getString("order_need_confirm"); //是否需要支付校验 示例没有该字段
		String change_date_rule = StringUtils.trimToEmpty(oneJsom.getString("change_date_rule")); //改期规则
		String return_ticket_rule = StringUtils.trimToEmpty(oneJsom.getString("return_ticket_rule")); //退票规则
		String luggage_rule = StringUtils.trimToEmpty(oneJsom.getString("luggage_rule")); //行李额
		String statement = StringUtils.trimToEmpty(oneJsom.getString("statement")); //其他说明
		String pay_way_qn = StringUtils.trimToEmpty(oneJsom.getString("pay_way")); //
//		0:未支付
//		1:财付通支付
//		2:支付宝支付
//		99:支付中心
//		5:现金支付
//		10:快钱支付
//		11:快钱分账
//		12:支付宝分账
//		13:财付通分账
//		14:无线支付
//		15:招商银行信用卡支付
//		17:银联在线分账
//		16:汇付天下分账
//		19:快钱wap
//		18:网银在线wap
//		21:快捷支付
//		20:招商直连wap
//		-11:快钱信用卡退款
//		23:QUNAR代收-无线
//		22:QUNAR代收-无线非担保
//		24:余额支付
//		-2:支付宝退款
//		-1:财付通退款
		String pay_way = pay_way_qn;
		if (pay_way.equals("1")) {
			pay_way = "财付通支付";
		} else if (pay_way.equals("2")) {
			pay_way = "支付宝支付";
		} else if (pay_way.equals("99")) {
			pay_way = "支付中心";
		} else if (pay_way.equals("5")) {
			pay_way = "现金支付";
		} else if (pay_way.equals("10")) {
			pay_way = "快钱支付";
		} else if (pay_way.equals("11")) {
			pay_way = "快钱分账";
		} else if (pay_way.equals("12")) {
			pay_way = "支付宝分账";
		} else if (pay_way.equals("13")) {
			pay_way = "财付通分账";
		} else if (pay_way.equals("14")) {
			pay_way = "无线支付";
		} else if (pay_way.equals("15")) {
			pay_way = "招商银行信用卡支付";
		} else if (pay_way.equals("16")) {
			pay_way = "汇付天下分账";
		} else if (pay_way.equals("17")) {
			pay_way = "银联在线分账";
		} else if (pay_way.equals("18")) {
			pay_way = "网银在线wap";
		} else if (pay_way.equals("19")) {
			pay_way = "快钱wap";
		} else if (pay_way.equals("20")) {
			pay_way = "招商直连wap";
		} else if (pay_way.equals("21")) {
			pay_way = "快捷支付";
		} else if (pay_way.equals("22")) {
			pay_way = "QUNAR代收-无线非担保";
		} else if (pay_way.equals("23")) {
			pay_way = "QUNAR代收-无线";
		} else if (pay_way.equals("24")) {
			pay_way = "余额支付";
		}
		ptlb.add("订单信息："+ "平台支付方式：" + pay_way_qn + "系统：" + pay_way);

		
		String transaction_no = StringUtils.trimToEmpty(oneJsom.getString("transaction_no"));//支付交易流水号
//		String fzStatus = oneJsom.getString("fzStatus");//资金解冻状态
//		String refund_cause = oneJsom.getString("refund_cause"); //退款原因
		String contact_name = StringUtils.trimToEmpty(oneJsom.getString("contact_name")); //联系人姓名
		String contact_mob = StringUtils.trimToEmpty(oneJsom.getString("contact_mob"));//联系人手机
		String contact_email = StringUtils.trimToEmpty(oneJsom.getString("contact_email")); //电子邮箱
		
		//行程单暂不解析，去哪说暂时没有
		String journey_sheet = StringUtils.trimToEmpty(oneJsom.getString("journey_sheet")); //是否包含行程单
		String journey_sheet_no = StringUtils.trimToEmpty(oneJsom.getString("journey_sheet_no")); //行程单号
		String journey_sheet_way = StringUtils.trimToEmpty(oneJsom.getString("journey_sheet_way")); //行程单投递方式
		String journey_sheet_price = StringUtils.trimToEmpty(oneJsom.getString("journey_sheet_price")); //行程单价格
		String journey_sheet_status = StringUtils.trimToEmpty(oneJsom.getString("journey_sheet_status")); //行程单状态
		
		String receiver = StringUtils.trimToEmpty(oneJsom.getString("receiver")); //收件人
		String postcode = StringUtils.trimToEmpty(oneJsom.getString("postcode")); //邮编
		String address = StringUtils.trimToEmpty(oneJsom.getString("address")); //配送地址
		String remark = StringUtils.trimToEmpty(oneJsom.getString("remark")); //用户备注
		String create_time = StringUtils.trimToEmpty(oneJsom.getString("create_time")); //订单创建时间  eg1413351817000
		String last_updated = StringUtils.trimToEmpty(oneJsom.getString("last_updated")); //订单最后更新日期 eg1413427055000
//		String ticket_date = oneJsom.getString("ticket_date");  //出票时间
//		String unfzTime = oneJsom.getString("unfzTime");  //资金解冻时间
//		String operator = oneJsom.getString("operator");  //操作员
		
		khddMap.put("SHBH",jpDdsz.getShbh());
		khddMap.put("DDYH", jpDdsz.getDdUserid());
		khddMap.put("DDBM", jpDdsz.getDdBmid());
		khddMap.put("WBDH", order_no);
		khddMap.put("WDID", jpDdsz.getWdid());
		khddMap.put("WDPT", jpDdsz.getWdpt());
		khddMap.put("JJD", "");
		khddMap.put("CPCS", "");
		khddMap.put("NOSX", "");
		khddMap.put("SKKM", jpDdsz.getDdMrkm());
		khddMap.put("DDLX", "1");
		khddMap.put("DDZT", "1");
		if("0".equals(ddwzffs)&&StringUtils.isBlank(pay_way)){//取默认设置支付科目方式
			ptlb.add("该订单没有传支付科目方式，根据设置取默认支付科目方式");
			khddMap.put("SKKM", jpDdsz.getDdMrkm());
		}
		if("1".equals(ddwzffs)&&StringUtils.isNotBlank(pay_way)){
			ptlb.add("该订单没有传支付科目方式，根据设置让订单不自动支付");
		}
		//添加政策类型节点
		if(StringUtils.isNotBlank(jpDdsz.getLxrsj())){
			contact_mob = jpDdsz.getLxrsj();
		}
		khddMap.put("WD_ZCLX", wd_zclx);
		khddMap.put("WD_ZCDM", policy_id);
		khddMap.put("NXR", contact_name);
		khddMap.put("NXSJ", contact_mob);
		JSONArray hds = oneJsom.getJSONArray("flight_segments");
		for(int i=0;i<hds.size();i++){
			Map<String, String> hdMap = new HashMap<String, String>();
			JSONObject hd = hds.getJSONObject(i);	
			String carrier = hd.getString("carrier");  //航空公司二字码  CX
			String flight_num = hd.getString("flight_num");  //航班号 CX684
			String cabin = hd.getString("cabin");  //舱位
			String plane_module = hd.getString("plane_module"); //飞机机型
			String terminal = hd.getString("terminal"); //航站楼
			String dep_aircode = hd.getString("dep_aircode"); //出发机场三字码
			String arr_aircode = hd.getString("arr_aircode"); //到达机场三字码
			String departure_date = hd.getString("departure_date"); //起飞日期
			String departure_time = hd.getString("departure_time"); //起飞时间
			String arrival_date = hd.getString("arrival_date");  //到达日期
			String arrival_time = hd.getString("arrival_time");  //到达时间
			hdMap.put("XS_HBH", flight_num);
			hdMap.put("XS_CW", cabin);
			hdMap.put("CFSJ", departure_date+" "+departure_time); // 2014-10-29 01:30 
			hdMap.put("DDSJ", arrival_date+" "+arrival_time); //
			hdMap.put("FJJX", plane_module);
			hdMap.put("CFCITY", dep_aircode);
			hdMap.put("DDCITY", arr_aircode);
			hdMap.put("XS_TGQ", "改签规则："+change_date_rule+"\n退票规则："+return_ticket_rule);
			hdList.add(hdMap);
		}
		Double bx_fee_one = 0.0;
		if(bx_count!=0){
			bx_fee_one = Arith.div(bx_fee, bx_count);
		}
		JSONArray cjrs = oneJsom.getJSONArray("passengers");
		boolean going = false;
		for(int j=0;j<cjrs.size();j++){
			Map<String, String> cjrMap = new HashMap<String, String>();
			JSONObject cjr = cjrs.getJSONObject(j);
			String name = cjr.getString("name");//乘机人姓名
			String gender = cjr.getString("gender");//性别 
			String age_type_qn = cjr.getString("age_type");//乘机人类型 -1 留学生； 0 成人； 1 儿童
			String age_type = age_type_qn;
			Double tax = adult_tax;//税费
			Integer count = adults_count;//人数
			Double ticket_price = cjr.getDouble("ticket_price"); //单个乘机人销售价
			Double xs_zdj = increase_price;
			Double xs_je = Arith.add(ticket_price, tax);
			if(age_type_qn.equals("0")){ //成人
				age_type = "1";
				tax = adult_tax;
				count = adults_count;
			}else if(age_type_qn.equals("1")){ //儿童
				age_type = "2";
				tax = child_tax;
				count = children_count;
				xs_zdj = ticket_price;//儿童票面价取ticket_price字段
			}
			String birthday = cjr.getString("birthday");//出生日期
			String nationality = cjr.getString("nationality");//国籍 cn 中国； 
			String card_type = cjr.getString("card_type");//证件类型
			// NI=身份证 PP=护照 ID=其他 HX=回乡证 TB=台胞证 GA=港澳通行证
			if("NI,PP".indexOf(card_type)==-1){
				card_type = "ID";
			}
			String card_num = cjr.getString("card_num"); //证件号码
			String card_expired = cjr.getString("card_expired");//证件有效期
			String card_issue_place = cjr.getString("card_issue_place"); //证件签发国家 cn 中国；
			Integer bx_count_cjr = cjr.getInteger("bx_count");  //保险份数单个乘机人的保险份数	
			if(bx_count_cjr>0){ //只有BXLX不为空且去哪传递的保险份数大于1时才传保险节点到后台
				cjrMap.put("XS_BXFS", bx_count_cjr + "");//保险份数
			}
			cjrMap.put("CJR", name);
			cjrMap.put("CJRLX", age_type);
			cjrMap.put("ZJLX", card_type);
			cjrMap.put("ZJHM", card_num);
			cjrMap.put("XS_ZDJ", xs_zdj+"");
			cjrMap.put("XS_PJ", ticket_price+"");	
			cjrMap.put("XS_JE", xs_je+"");
			cjrMap.put("XS_JSF", "0");
			cjrMap.put("XS_TAX", tax+"");
			cjrMap.put("CSRQ", birthday);
			cjrMap.put("GJ", nationality);
			cjrMap.put("ZJYXQ", card_expired);
			cjrMap.put("ZJQFG", card_issue_place);
			cjrMap.put("SXH",cjrList.size()+1+"");
			cjrList.add(cjrMap);
		}
		
		OrderBean orderBean = new OrderBean();
		orderBean.setKhddMap(khddMap);
		// 如果有订单编号
		orderBean.setCjrList(cjrList);
		orderBean.setHdList(hdList);
		listOrder.add(orderBean);
	}
	/**
	 * 支付成功订单导出（国际）
	 * @param ptlb
	 * @return
	 * @throws Exception
	 */
	private String requestOrderexport_pay_ok_gj(JpPtLog ptlb) throws Exception{
//		http://www.test.com/tts/importexport/international/orderSyncApi/getPaidOrderDetails.do?user
//			name=admin&password=123	
		String url = URL + "/tts/importexport/international/orderSyncApi/getPaidOrderDetails.do";
		Map<String, String> mparam = new HashMap<String, String>();
		mparam.put("user", USER);
		mparam.put("pass", PASS);
		ptlb.add("去哪扫描请求已支付（国际）[POST]："+ url+ " 请求参数："+ mparam.toString());
		String xml = HttpClientUtil.doGet(url, mparam, encode);
		xml = proc(xml);
		ptlb.add("去哪（国际）返回已支付待出票"+ xml);
		String msg = "去哪扫描请求已支付（国际）[POST],地址"+url+"\r\n参数" +mparam.toString()+"\r\n";
		msg += "去哪（国际）返回已支付待出票:"+xml;
		return xml;
	}
	@Override
	public boolean orderOutTicket(JpPtLog ptlb, PlatJpBean pjb)
			throws Exception {
		return orderOutTicket_gj(ptlb,pjb);
	}
	/**
	 * 回填票号_国际
	 */
	public boolean orderOutTicket_gj(JpPtLog ptlb, PlatJpBean pjb) throws Exception {
		String json = updateorder_gj(ptlb, pjb);
		if(StringUtils.isBlank(json)){
			throw new Exception("供应票号回填错误去哪返回为空。");
		}
		JSONObject jsom = JSONObject.parseObject(json);
		if("true".equals(jsom.getString("ret"))){
			ptlb.add("票号回填成功！");
			return true;
		}else{
			ptlb.add("票号回填失败，失败原因："+jsom.getString("errmsg"));
			throw new Exception("供应票号回填错误" + jsom.getString("errmsg"));
		}
	}
	private String updateorder_gj(JpPtLog ptlb, PlatJpBean pjb) throws Exception{
		StringBuffer data = new StringBuffer("{\"otaUserID\":\""+USER+"\",\"otaPwd\":\""+PASS+"\",");
		data.append("\"orderNo\":\""+pjb.getWdbh()+"\",\"ticketNoItems\":[");
		
		List<JpKhdd> jpKhddList = pjb.getJpKhddList();
		Map<String,List<JpJp>> jpListMap = pjb.getJpJpListMap();
		for(int i=0;i<jpKhddList.size();i++){
			JpKhdd oneJpKhdd = jpKhddList.get(i);
			List<JpJp> jpList = jpListMap.get(oneJpKhdd.getDdbh());
			String tmpPnr = oneJpKhdd.getXsPnrNo();
			for(int j=0;j<jpList.size();j++){
				JpJp oneJp = jpList.get(j);
				String cjr = oneJp.getCjr();
				String zjhm = oneJp.getZjhm();
				String tkno = oneJp.getTkno();
				String channelType = "10";//由于没有出票类型，所以出票渠道默认为10  其他
				data.append("{\"psrName\":\""+cjr+"\",\"idNo\":\""+zjhm+"\",\"tktNo\":\""+tkno+"\",\"channelType\":\""+channelType+"\",\"pnr\":\""+tmpPnr+"\"}");
			}
		}
		data.append("]}");
		String url = URL + "/tts/importexport/international/orderSyncApi/setOrderIssued.do";
		ptlb.add("去哪国际订单票号回填，请求地址："+url+"\n请求参数：data="+data.toString());
		Map<String, String> mparam = new HashMap<String, String>();
		mparam.put("data", data.toString());
		String json = HttpClientUtil.doPost(url, mparam, encode);
		ptlb.add("去哪国际订单票号回填返回结果："+json);		
		return json;
	}
	@Override
	public List<OrderBean> getByWbdh(String wbdh, JpPtLog ptlb)
			throws Exception {
		throw new Exception("去哪国际接口文档不支持精确导出功能（根据外部单号查订单详情）");
	}
	private String proc(String xml) {
		xml = StringUtils.trimToEmpty(xml);
		xml = xml.replaceAll("&", "");
		xml = xml.replaceAll("<不含>", "");
		xml = xml.replaceAll("<br>", "");
		xml = xml.replaceAll("<br/>", "");
		xml = xml.replaceAll("GW<250", "");
		xml = VeStr.clearToXml(xml);
		return xml;
	}
	static Map<String, String> errorMap = new HashMap<String, String>();
	static {
		errorMap.put("100", "解析xml异常");
		errorMap.put("101", "必输字段有没有的");
		errorMap.put("102", "订单号为空");
		errorMap.put("102", "没有此订单");
		errorMap.put("103", "需要更新的状态不是出票完成");
		errorMap.put("104", "xml为空的");
		errorMap.put("105", "该代理商没有设置白名单");
		errorMap.put("106", "用户名或则密码错误");
		errorMap.put("107", "IP错误");
		errorMap.put("108", "标号校验没有全部通过");
		errorMap.put("109", "分账失败");
		errorMap.put("110", "没有一个乘机人的姓名是对的");
		errorMap.put("111", "订单状态不是支付成功等待出票");

	}
	
	public String getBigPnr(String pnrText){
		String bigpnr = "";
		String reg="[0-9]{1,2}.RMK\\s{1,3}[A-Z]{2}\\/([0-9A-Z]{6,8})";
		Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher mp = p.matcher(pnrText);
		if (mp.find()) {
			bigpnr = mp.group(1);
		}
		return bigpnr;
	}
	/**
	 * 解析政策说明字符串，并将其存放进khddMap里面
	 */
	private static void resolveZCSM(String zcsm,Map<String,String> khddMap){
//		String tfid = ""; //投放ID
		String zcfd = ""; //政策返点
		String wdld = ""; //网店留点
		String wdlq = ""; //网店留钱
		String wdjz = ""; //网店降折
		String wdpmjj = ""; //网店票面加价
		String wdldlx = ""; //网店留点类型
		try {
			List<String> tempArr = new ArrayList<String>();
			//八千翼(22066793,1251830911,6.5,1400.0,0.8)留(-3.0,-4.0,0.0,0.0,1)[21 10:44]
			if(StringUtils.isNotBlank(zcsm)){
				Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");      
				Matcher matcher = pattern.matcher(zcsm);
				
				while(matcher.find())  {
					tempArr.add(matcher.group());
				}
			}
			if(tempArr.size()==2){
				String[] str1Arr = tempArr.get(0).split(",");
				String[] str2Arr = tempArr.get(1).split(",");
//				tfid = str1Arr[1]; //投放ID
				zcfd = str1Arr[2]; //政策返点
				wdld = str2Arr[0]; //网店留点
				wdlq = str2Arr[1]; //网店留钱
				wdjz = str2Arr[2]; //网店降折
				wdpmjj = str2Arr[3]; //网店票面加价
				wdldlx = str2Arr[4]; //网店留点类型
				khddMap.put("ZC_FD", zcfd);
				khddMap.put("ZC_LD", wdld);
				khddMap.put("ZC_LQ", wdlq);
			}
		} catch (Exception e) {
			e.printStackTrace();
//			PlatUtils.ptlog(TaobaoGy2.plat, "", "外部单号"+khddMap.get("GY_DDBH")+",解析政策说明字符串报错"+zcsm);
		}
	}
	/**
	 * 解析政策代码，取出投放方案代码翻入TFID节点中
	 * V50_GJDYIHKWEE314635422
	 * @return
	 */
	private static void resolveZCDM(String zcdm,Map<String,String> khddMap){
		String tfid = "";
//		khddMap.put("WD_TFID", tfid);
		if(StringUtils.isNotBlank(zcdm)){
			Pattern pattern = Pattern.compile("(?<=^V)[a-zA-Z0-9]{2,10}(?=_)");      
			Matcher matcher = pattern.matcher(zcdm);
			if (matcher.find()) {
				tfid = matcher.group(0);
			}
			khddMap.put("ZC_TFID", tfid);
		}
	}
}
