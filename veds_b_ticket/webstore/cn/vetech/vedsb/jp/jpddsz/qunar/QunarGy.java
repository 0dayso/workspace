package cn.vetech.vedsb.jp.jpddsz.qunar;

import java.math.BigDecimal;
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
import org.vetech.core.modules.utils.HttpClientUtil;
import org.vetech.core.modules.utils.ParseXml;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;
import cn.vetech.vedsb.jp.entity.jpddsz.PlatJpBean;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.jpddsz.JpddGySuper;

public class QunarGy extends JpddGySuper {
	private String URL = "";

	private String USER;// daodan

	private String PASS;// shengyi456

	private String encode = "UTF-8";
	// 按用户存储上一次执行时间
	private static Map<String, String> PRETIMEMAP = new HashMap<String, String>();
	// 按用户保存lastid
//	private static Map<String, String> LASTIDMAP = new HashMap<String, String>();

	private String gy_autoqr = "0";
	
	private JpDdsz jpDdsz;

	private String ddwzffs; //订单无支付方式传入（对应去哪的一种派单业务）；0导入订单时按设置的默认科目支付  1订单不自动支付 默认0
	public QunarGy(JpDdsz jpDdsz) {
		this.jpDdsz = jpDdsz;
		this.USER = jpDdsz.getDdJkzh();
		this.PASS = jpDdsz.getDdJkmm();
		this.URL = jpDdsz.getDdJkdz();
	}

	/**
	 * 按状态查询订单列表
	 */
	private String requestOrderexport_pay_ok(JpPtLog ptlb) throws Exception {
		String status = "PAY_OK";  //默认查询已支付待出票订单
		/*
		 * 去哪  51订座成功等待价格确认  20等待座位确认  0订座成功等待支付
		 * ASMS qn1订座成功等待价格确认  qn2等待座位确认  qn3订座成功等待支付 
		 * */
		String url = URL + "/tts/interface/new/orderExportNew";
		Map<String, String> mparam = new HashMap<String, String>();
		mparam.put("type", "incr");
		mparam.put("user", USER);
		mparam.put("pass", PASS);
		mparam.put("lastId", "1");
		mparam.put("status", status);
		ptlb.add("去哪扫描请求已支付[POST]："+ url+ " 参数："+ mparam.toString());
		String xml = HttpClientUtil.doPost(url, mparam, encode);
		xml = proc(xml);
		ptlb.add("去哪返回已支付："+ xml);
		return xml;
	}

	private String requestOrderByID(JpPtLog ptlb, String orderNo) throws Exception {
		String url = URL + "/tts/interface/new/orderExportNew";
		Map<String, String> mparam = new HashMap<String, String>();
		mparam.put("type", "exact");
		mparam.put("user", USER);
		mparam.put("pass", PASS);
		mparam.put("orderNo", orderNo);
		ptlb.add("去哪获得订单详细[POST]："+ url+ " 参数："+ mparam.toString());
		String xml = HttpClientUtil.doPost(url, mparam, encode);
		
		xml = proc(xml);
		ptlb.add("去哪返回："+ xml);
		return xml;
	}
	@SuppressWarnings("unchecked")
	private String requestUpdateorder(String xmlto, JpPtLog ptlb) throws Exception {
		String url = URL + "/tts/interface/updateorder.jsp";
		Map<String, String> mparam = new HashMap<String, String>();
		mparam.put("username", USER);
		mparam.put("password", PASS);
		mparam.put("orderdata", xmlto);
		ptlb.add("去哪回填票号请求："+ url+ "参数："+ mparam.toString());
		String xml = HttpClientUtil.doPost(url, mparam, encode);
		ptlb.add("去哪回填票号返回："+ xml);
		return xml;
	}
	@Override
	public List<OrderBean> queryOrders(JpPtLog ptlb) throws Exception {
		List<OrderBean> list_gn = new ArrayList<OrderBean>();
		String nowtime = VeDate.getStringDate();
		// 控制只能1分钟扫描一次
		String pretime = PRETIMEMAP.get(USER+URL);
		if (StringUtils.isNotBlank(pretime) && VeDate.getPreMin(pretime, 1).compareTo(nowtime) >= 0) {
			return list_gn;
		}
		PRETIMEMAP.put(USER+URL, nowtime);
		list_gn = _queryOrders(ptlb);
		return list_gn;
	}
	@Override
	public List<OrderBean> queryOrders(JpPtLog ptlb,String dateStr) throws Exception {
		List<OrderBean> list_gn = new ArrayList<OrderBean>();
		list_gn = _queryOrders(ptlb);
		return list_gn;
	}
	/**
	 * 从QueryOrders中抽出来的国内票导单代码
	 * @param ptlb
	 * @param lastid
	 * @return
	 * @throws Exception
	 */
	public List<OrderBean> _queryOrders(JpPtLog ptlb) throws Exception{
		List<OrderBean> list = new ArrayList<OrderBean>();	
		String rtnxml = requestOrderexport_pay_ok(ptlb);
		if(StringUtils.isBlank(rtnxml)){
			return null;
		}
		ParseXml parse = new ParseXml(rtnxml);
		if (parse == null) {
			return null;
		}
		String status = parse.attr("status");
		if (!"ok".equals(status)) {
			
			Element msg = parse.ele("msg");
			String content = "";
			if (msg != null) {
				content = parse.attr("content", msg);
			}
			throw new Exception("导单返回" + status + "  " + content);
		}
		List<Element> OrderInfoList = parse.get("order");
		for (int i = 0; OrderInfoList != null && i < OrderInfoList.size(); i++) {
			Element OrderInfo = OrderInfoList.get(i);
			ddInfo(OrderInfo, list, ptlb);
		}
		return list;
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
	private void ddInfo(Element OrderInfo, List<OrderBean> listOrder, JpPtLog ptlb) {

		Map<String, String> khddMap = new HashMap<String, String>();
		List<Map<String, String>> hdList = new ArrayList<Map<String, String>>();

		String orderNo = OrderInfo.attributeValue("orderNo");
		// ：kkl120301091519853 如果客人需要保险发票，这个信息也要导入到备注中。
		// insuranceCount="2" bxSource="合众人寿" bxName="合众网销百万航空意外计划A"
		String cabinnote = StringUtils.trimToEmpty(OrderInfo.attributeValue("cabinnote"));
		String policyType = StringUtils.trimToEmpty(OrderInfo.attributeValue("policyType"));
		String policySource = StringUtils.trimToEmpty(OrderInfo.attributeValue("policySource"));	
		String payChannel_qn = StringUtils.trimToEmpty(OrderInfo.attributeValue("payChannel"));
		String pnr = StringUtils.trimToEmpty(OrderInfo.attributeValue("pnr"));
		String price_type = StringUtils.trimToEmpty(OrderInfo.attributeValue("price_type"));//新加节点 报价类型price_type
		String contact = StringUtils.trimToEmpty(OrderInfo.attributeValue("contact"));
		String contactMob = StringUtils.trimToEmpty(OrderInfo.attributeValue("contactMob"));
		// 行程单
		String xcd = StringUtils.trimToEmpty(OrderInfo.attributeValue("xcd"));
		String xcdjg = StringUtils.trimToEmpty(OrderInfo.attributeValue("xcdPrice"));
		// 退改签规定
		String tgq = OrderInfo.attributeValue("backnote");

		khddMap.put("SHBH",jpDdsz.getShbh());
		khddMap.put("DDYH", jpDdsz.getDdUserid());
		khddMap.put("DDBM", jpDdsz.getDdBmid());
		khddMap.put("WBDH", orderNo);
		khddMap.put("XS_PNR_NO", pnr);
		khddMap.put("WDID", jpDdsz.getWdid());
		khddMap.put("WDPT", jpDdsz.getWdpt());
		khddMap.put("JJD", "");
		khddMap.put("NOSX", "");
		khddMap.put("DDLX", "1");
		khddMap.put("WD_DDLX", price_type);
		khddMap.put("WD_ZCLX", policyType);
		khddMap.put("WD_ZCDM", policySource);
		khddMap.put("NXR", contact);
		//解析政策代码
		resolveZCDM(policySource,khddMap);
		//解析政策说明字符串
		resolveZCSM(cabinnote,khddMap.get("ZC_QD"),khddMap);

		if(StringUtils.isNotBlank(jpDdsz.getLxrsj())){
			contactMob = jpDdsz.getLxrsj();
		}
		khddMap.put("NXSJ", contactMob);
		if("0".equals(ddwzffs) && StringUtils.isBlank(payChannel_qn)){//取默认设置支付科目方式
			ptlb.add("该订单没有传支付科目方式，根据设置取默认支付科目方式");
			khddMap.put("SKKM", jpDdsz.getDdMrkm());
		}
		if("1".equals(ddwzffs)&&StringUtils.isBlank(payChannel_qn)){
			ptlb.add("该订单没有传支付科目方式，根据设置让订单不自动支付");
		}
		// 航段信息
		List<Element> hds = OrderInfo.elements("flight");
		for (int j = 0; j < hds.size(); j++) {
			Map<String, String> hdMap = new HashMap<String, String>();
			Element hd = hds.get(j);
			String CFCITY = StringUtils.trimToEmpty(hd.attributeValue("dep"));
			String DDCITY = StringUtils.trimToEmpty(hd.attributeValue("arr"));
			String CFSJ = StringUtils.trimToEmpty(hd.attributeValue("depDay")) + " "
					+ StringUtils.trimToEmpty(hd.attributeValue("depTime"));
			String DDSJ = StringUtils.trimToEmpty(hd.attributeValue("depDay")) + " "
					+ StringUtils.trimToEmpty(hd.attributeValue("arrTime"));
			String HBH = StringUtils.trimToEmpty(hd.attributeValue("code"));
			String realCode = StringUtils.trimToEmpty(hd.attributeValue("realCode"));
			String CW = StringUtils.trimToEmpty(hd.attributeValue("cabin"));
			String CCW = StringUtils.trimToEmpty(hd.attributeValue("ccabin"));
			if (StringUtils.isNotBlank(realCode) && !realCode.equalsIgnoreCase(HBH)) {
				if (HBH.indexOf("*") < 0) {
					HBH = "*" + HBH;
				}
				hdMap.put("OP_HKGS", realCode);
			}
			hdMap.put("XS_HBH", HBH);
			hdMap.put("CFSJ", CFSJ);
			hdMap.put("DDSJ", DDSJ);
			hdMap.put("CFCITY", CFCITY);
			hdMap.put("DDCITY", DDCITY);
			hdMap.put("CRCW", CW);
			hdMap.put("ETCW", CCW);
			hdMap.put("XS_TGQ", tgq);	
			hdList.add(hdMap);
		}
		Map<String, List<Map<String, String>>> pnrCjr = new HashMap<String, List<Map<String, String>>>();
		// 乘机人信息
		List<Element> cList = OrderInfo.elements("passenger");
		List<Map<String, String>> cjrList_sd = new ArrayList<Map<String,String>>();//锁单用乘机人List
		
		Boolean hasXcd = false;
		if(StringUtils.isNotBlank(xcd)){//包含行程单信息
			hasXcd = true;
			
			String sjr = StringUtils.trimToEmpty(OrderInfo.attributeValue("sjr"));
			String address = StringUtils.trimToEmpty(OrderInfo.attributeValue("address"));
			khddMap.put("SFYJXCD", "1");//是否邮寄行程单
			khddMap.put("SJR", sjr);//收件人
			khddMap.put("YZBM", "");//邮政编码
			khddMap.put("XJDZ", address);//收件地址
		}
		for (int k = 0; k < cList.size(); k++) {
			Element cjr = cList.get(k);
			String CJRXM = StringUtils.trimToEmpty(cjr.attributeValue("name"));
			String ageType = StringUtils.trimToEmpty(cjr.attributeValue("ageType"));// 乘机人类型 0为成人,1为儿童
			String ZDJ = StringUtils.trimToEmpty(OrderInfo.attributeValue("viewPrice"));// 订单上票价
			String fuelTax = StringUtils.trimToEmpty(OrderInfo.attributeValue("fuelTax"));
			String cjrprice = StringUtils.trimToEmpty(cjr.attributeValue("price"));// 销售价
			String eticketNum = StringUtils.trimToEmpty(cjr.attributeValue("eticketNum"));

			String constructionFee = StringUtils.trimToEmpty(OrderInfo.attributeValue("constructionFee"));
			String JSF = NumberUtils.toDouble(constructionFee, 0) + "";// 基建
			String TAX = NumberUtils.toDouble(fuelTax, 0) + "";// 税费
			String CJRLX = "1";
			if ("0".equals(ageType)) {
				CJRLX = "1";
			} else if ("1".equals(ageType)) {
				String priceType = StringUtils.trimToEmpty(cjr.attributeValue("priceType"));//套餐
				CJRLX = "2";
				ZDJ = StringUtils.trimToEmpty(OrderInfo.attributeValue("childFaceValue"));// 订单上票价
				TAX = StringUtils.trimToEmpty(OrderInfo.attributeValue("childFuelTax"));
				JSF = "0";
				if("成人套餐".equals(priceType)||"成人".equals(priceType)){
					Double cjrprice_num = NumberUtils.toDouble(cjrprice,0.0); //成人套餐儿童优惠价
					Double jsf_num = NumberUtils.toDouble(constructionFee, 0.0);
					Double tax_num_cr = NumberUtils.toDouble(fuelTax, 0.0);//成人燃油
					//儿童燃油等于成人燃油除2后去掉个位数
					BigDecimal b1 = new BigDecimal(Double.toString(tax_num_cr));
					BigDecimal b2 = new BigDecimal(Double.toString(2));
					Double tax_num_et =b1.divide(b2, -1, BigDecimal.ROUND_HALF_DOWN).doubleValue();
					
					cjrprice_num = Arith.add(cjrprice_num, jsf_num);			
					cjrprice_num = Arith.sub(Arith.add(cjrprice_num,tax_num_cr), tax_num_et);
					cjrprice = cjrprice_num+"";
					TAX = tax_num_et+"";
				}
			}else{
				//文档没有婴儿销售价，直接取票价
				CJRLX = "3";
				ZDJ = StringUtils.trimToEmpty(cjr.attributeValue("price"));
				//文档没有婴儿机建和燃油，默认0
				TAX = "0";
				JSF = "0";
			}
			// NI=身份证 PP=护照 ID=其他 HX=回乡证 TB=台胞证 GA=港澳通行证
			String ZJLX = StringUtils.trimToEmpty(cjr.attributeValue("cardType"));
			if ("NI".equals(ZJLX)) {
				ZJLX = "NI";
			} else if ("PP".equals(ZJLX)) {
				ZJLX = "PP";
			} else {
				ZJLX = "ID";
			}
			String ZJHM = StringUtils.trimToEmpty(cjr.attributeValue("cardNum"));

			if (StringUtils.isBlank(ZJHM) && "1".equals(ageType)) {
				ZJHM = StringUtils.trimToEmpty(cjr.attributeValue("birthday"));
			}
			Map<String, String> cjrMap = new HashMap<String, String>();
			if(hasXcd&&"1".equals(CJRLX)){
//				cjrMap.put("XCD", xcd);
				cjrMap.put("XS_YJF", xcdjg);
			}
			cjrMap.put("CJR", CJRXM);
			cjrMap.put("CJRLX", CJRLX);
			cjrMap.put("ZJLX", ZJLX);
			cjrMap.put("ZJHM", ZJHM);
			cjrMap.put("XS_ZDJ", ZDJ);
			cjrMap.put("XS_PJ", cjrprice);
			cjrMap.put("XS_JSF", JSF);
			cjrMap.put("XS_TAX", TAX);
			String xs_je = Arith.round(NumberUtils.toDouble(cjrprice) + NumberUtils.toDouble(JSF)
					+ NumberUtils.toDouble(TAX), 2)+ "";
			cjrMap.put("XS_JE", xs_je);

			int insuranceCount = NumberUtils.toInt(cjr.attributeValue("insuranceCount"));
			if(insuranceCount>0){
				cjrMap.put("XS_BXFS",insuranceCount+"");
			}		
			if ("0".equals(ageType)) {
				pnr = StringUtils.trimToEmpty(OrderInfo.attributeValue("pnr"));
			} else if ("1".equals(ageType)) {
				pnr = StringUtils.trimToEmpty(OrderInfo.attributeValue("cpnr"));
			}
			if (StringUtils.isBlank(pnr)) {
				pnr = StringUtils.trimToEmpty(OrderInfo.attributeValue("pnr"));
			}

			List<Map<String, String>> cjrList = pnrCjr.get(pnr);
			if (cjrList == null) {
				cjrList = new ArrayList<Map<String, String>>();
				pnrCjr.put(pnr, cjrList);
			}
			cjrMap.put("SXH",cjrList.size()+1+"");
			cjrList.add(cjrMap);
			cjrList_sd.add(cjrMap);
		}

		int count = pnrCjr.size();
		int index = 1;
		for (String key : pnrCjr.keySet()) {
			List<Map<String, String>> cjrList = pnrCjr.get(key);
			count--;
			String cjrlx = cjrList.get(0).get("CJRLX");
			Map<String, String> khddMap_new = new HashMap<String, String>(khddMap);
			List<Map<String, String>> hdList_new = new ArrayList<Map<String,String>>();
			for(Map<String,String>  m : hdList) {
				Map<String, String> mn=new HashMap<String, String>(m);
				hdList_new.add(mn);
			}
			if(!"1".equals(cjrlx)) {//乘机人类型不是1（成人）的，清除大编码
				khddMap_new.put("BIG_PNRNO","");
				//循环航段MAP，替换舱位
				for(Map<String,String> map : hdList_new) {
					map.put("CW", map.get("ETCW"));
				}
			}else {
				//循环航段MAP，替换舱位
				for(Map<String,String> map : hdList_new) {
					map.put("CW", map.get("CRCW"));
				}
			}
			khddMap_new.put("PNR_NO", key);
			khddMap_new.put("DDXH", index+"");
			index++;
			OrderBean orderBean = new OrderBean();
			orderBean.setKhddMap(khddMap_new);
			// 如果有订单编号
			orderBean.setCjrList(cjrList);
			orderBean.setHdList(hdList_new);
			listOrder.add(orderBean);
		}
	}
	@Override
	public List<OrderBean> getByWbdh(String wbdh, JpPtLog ptlb) throws Exception {
		String rtnxml_info = StringUtils.trimToEmpty(requestOrderByID(ptlb,wbdh));
		ParseXml parseXml = new ParseXml(rtnxml_info);
		String status = parseXml.attr("status");
		if (!"ok".equals(status)) {
			return null;
		}
		Element OrderInfo = parseXml.ele("order");
		List<OrderBean> list = new ArrayList<OrderBean>();
		ddInfo(OrderInfo, list, ptlb);
		return list;
	}
	/**
	 * 回填票号
	 */
	@Override
	public boolean orderOutTicket(JpPtLog ptlb,PlatJpBean pjb) throws Exception {
		return orderOutTicket_gn(ptlb,pjb);
	}
	/**
	 * 回填票号_国内
	 */
	public boolean orderOutTicket_gn(JpPtLog ptlb, PlatJpBean pjb) throws Exception {
		String rtnxml = updateorder(ptlb, pjb);
		if (StringUtils.isBlank(rtnxml)) {
			throw new Exception("供应票号回填错误去哪返回为空。");
		}
		ParseXml parseXml = new ParseXml(rtnxml);
		Element OrderDetail = parseXml.ele("OrderDetail");
		String errorCode = OrderDetail.attributeValue("errorCode");
		String errorMsg = OrderDetail.attributeValue("errorMsg");
		// 去哪回填票号返回:=<OrderList> <OrderDetail no="kkl120810114920070" status="2" errorCode="" errorMsg="修改成功">
		// <passenger name="王清山" no="731-2355236790" status="1" cano="350583197408165416"/> </OrderDetail></OrderList>
		boolean err_b = true;
		List<Element> passengerList = OrderDetail.elements("passenger");
		if (passengerList != null && passengerList.size() > 0) {
			for (int i = 0; i < passengerList.size(); i++) {
				Element pe = passengerList.get(i);
				String status_p = pe.attributeValue("status");
				if (!"1".equals(status_p)) {
					err_b = false;
				}
			}
		}
		if (StringUtils.isBlank(errorCode) && err_b && passengerList != null && passengerList.size() > 0) {
			return true;
		} else {
			try {
				ptlb.add("回填失败，检索订单详细，检查去哪上是否已经出票成功");
				String rtnxml_info = StringUtils.trimToEmpty(requestOrderByID(ptlb, pjb.getWdbh())).toUpperCase();
				if (rtnxml_info.indexOf("STATUS=\"TICKET_OK\"") > 0) {
					ptlb.add("订单已经TICKET_OK，表示票号已经回填了");
					return true;
				}
			} catch (Exception e) {
				ptlb.add("获得订单详细异常");
			}
			String rtninfo = errorMsg;
			String err = "错误代码：" + errorCode + "错误内容：" + rtninfo;
			ptlb.add("供应票号回填错误："+ err);
			throw new Exception("供应票号回填错误" + err);
		}
	} 
	/**
	 * 将固定格式字符串转换成Map返回
	 * @param str eg BSPET-黑屏,BPET-官网
	 * @return 
	 * @throws Exception 
	 */
	private static Map<String,String> getMapByStr(String str) throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		if(StringUtils.isBlank(str)){
			return map;
		}
		try {
			String[] strArr = str.split(",");
			for(int i=0;i<strArr.length;i++){
				String[] tmpStrArr = strArr[i].split("-");
				map.put(tmpStrArr[0],tmpStrArr[1]);
			}
		} catch (RuntimeException e) {
			throw new Exception("解析固定格式字符串报错,例：BSPET-1,BPET-8");
		}

		return map;
	}
	private String updateorder(JpPtLog ptlb, PlatJpBean pjb) throws Exception {
		String xmlto = "<OrderList><OrderDetail no=\"" + pjb.getWdbh() + "\" status=\"2\" >"; // 订单号和订单状态(现在只能是2不能修改为其他值)
		
		List<JpKhdd> jpKhddList = pjb.getJpKhddList();
		Map<String,List<JpJp>> jpListMap = pjb.getJpJpListMap();
		Double purchasePrice = 0.0;
		for(int i=0;i<jpKhddList.size();i++){
			JpKhdd oneJpKhdd = jpKhddList.get(i);
			List<JpJp> jpList = jpListMap.get(oneJpKhdd.getDdbh());
			purchasePrice = Arith.sum(purchasePrice,oneJpKhdd.getCgZdj(),oneJpKhdd.getCgJsf(),oneJpKhdd.getCgTax());
			for(int j=0;j<jpList.size();j++){
				JpJp oneJp = jpList.get(j);
				String cjr = oneJp.getCjr();
				String zjhm = oneJp.getZjhm();
				String tkno = oneJp.getTkno();
				String channelType = "10";//由于没有出票类型，所以出票渠道默认为10  其他
				xmlto += "<passenger name=\"" + cjr + "\" no=\"" + tkno + "\" cano=\"" + zjhm + "\"/>"; // 乘机人姓名和票号
			}
		}
		if(purchasePrice!=0.0){
			xmlto += "<issueticket ticketType=\"5\" ticketPlatform=\"BSP\" purchasePrice=\"" + purchasePrice + "\" />";
		}	
		xmlto += "</OrderDetail></OrderList>";
		ptlb.add("供应票号回填请求");
		String rtnxml = requestUpdateorder(xmlto, ptlb);
		return rtnxml;
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
