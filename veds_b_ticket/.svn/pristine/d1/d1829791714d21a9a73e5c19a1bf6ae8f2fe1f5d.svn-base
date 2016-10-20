package cn.vetech.vedsb.jp.jpddsz.kuxun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.vetech.core.modules.utils.HttpClientUtil;
import org.vetech.core.modules.utils.ParseXml;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;
import cn.vetech.vedsb.jp.entity.jpddsz.PlatJpBean;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.jpddsz.JpddGySuper;

public class KuxunGy extends JpddGySuper {
	// http://fuwu.kuxun.cn/order/interface
	private String URL = "";

	private String USER;// daodan

	private String PASS;// shengyi456

	private String encode = "UTF-8";

	private String SITEID = "999";
	
	private JpDdsz jpDdsz;

	private static Map<String, String> pretimemap = new HashMap<String, String>();
	// 按用户保存lastid
	private static Map<String, String> LASTIDMAP = new HashMap<String, String>();

	private String ddlastId = "1";// 增量导出中起始订单编号

	public KuxunGy(JpDdsz jpDdsz) {
		this.USER = StringUtils.trimToEmpty(jpDdsz.getDdJkzh());
		this.PASS = StringUtils.trimToEmpty(jpDdsz.getDdJkmm());
		this.URL = StringUtils.trimToEmpty(jpDdsz.getDdJkdz());
		if (this.URL.endsWith("/")) {
			this.URL = StringUtils.substring(this.URL, 0, this.URL.length() - 1);
		}
		this.jpDdsz = jpDdsz;
	}

	private String queryOrders_req(JpPtLog ptlb, String lastOrderSn) throws Exception {
		// 增量导出 5 分钟以上 指定订单号导出之后的 50 个订单
		// 全量导出 23 小时以上 1. 只导出当前时间一个月内的订单。 2. 如未添加订单状态参数则默认导出所有订单。 默认为当前时间前 31 天所有订单。
		// 精确导出 不能小于 1 分 导出指定订单信息
		// 可选 exact(精确导出)、incr（增量导出）或 all（全量导出） ，不可为空。
		// 300: PAYED_UNISSUED 支付成功等待出票
		String url = URL + "/export?user=" + USER + "&pass=" + PASS + "&type=incr&status=300&siteId=" + SITEID + "&lastId=" + lastOrderSn;

		ptlb.add("酷讯扫描请求支付成功等待出票"+ url);
		String xml = HttpClientUtil.doPost(url, null, encode) ;
		ptlb.add("酷讯扫描请求支付成功等待出票返回"+ xml);
		return xml;
	}

	@Override
	public List<OrderBean> queryOrders(JpPtLog ptlb) throws Exception {
		String nowtime = VeDate.getStringDate();
		// 控制只能1分钟扫描一次
		String pretime = pretimemap.get(USER);
		if (StringUtils.isNotBlank(pretime) && VeDate.getPreMin(pretime, 1).compareTo(nowtime) > 0) {
			return null;
		}
		pretimemap.put(USER, nowtime);
		String lastid = StringUtils.trimToEmpty(LASTIDMAP.get(USER));
		if (lastid.length() > 1000) {
			LASTIDMAP.remove(USER);
		}
		List<OrderBean> list = new ArrayList<OrderBean>();

		String lastOrderSn = ddlastId;
		if (StringUtils.isBlank(ddlastId) && StringUtils.isNotBlank(lastid)) {
			lastOrderSn = lastid.substring(lastid.lastIndexOf(",") + 1);
		}
		String rtnxml = queryOrders_req(ptlb, lastOrderSn);
		if (StringUtils.isBlank(rtnxml)) {
			return null;
		}
		String error = "";
		if (rtnxml.indexOf("wrong site ip request") >= 0) {
			error = "联系酷讯,IP地址没有授权或IP登记不正确";
			ptlb.add(error);
			throw new Exception("导单出错，" + error);
		}
		if (rtnxml.indexOf("wrong site request") >= 0) {
			error = "联系酷讯,开通生产环境接口";
			ptlb.add(error);
			throw new Exception("导单出错，" + error);
		}

		ParseXml parse = null;
		try {
			parse = new ParseXml(rtnxml);
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add("解析订单详XML发生异常：异常信息：" + e.getMessage() + ",XML:"+ rtnxml);
			return null;
		}

		String status = parse.attr("status");
		if (!"OK".equals(status.toUpperCase())) {
			error = "返回异常，返回状态为:" + status;
			ptlb.add(error);
			throw new Exception("导单异常，" + error);
		}
		String lastorder_sn = "";
		List<Element> OrderInfoList = parse.get("order");
		for (int i = 0; OrderInfoList != null && i < OrderInfoList.size(); i++) {
			Element OrderInfo = OrderInfoList.get(i);
			String order_sn = parse.attr("flightOrderId", OrderInfo);
			lastid = StringUtils.trimToEmpty(LASTIDMAP.get(USER));
			if (i == OrderInfoList.size() - 1) {
				lastorder_sn = order_sn;
			}
			if (lastid.indexOf("," + order_sn) >= 0) {
				ptlb.add("订单号" + lastid + "已经获取过");
				continue;
			} else {
				LASTIDMAP.put(USER, lastid + "," + order_sn);
			}
			ParseKuxunGy.ddInfo(OrderInfo, parse, list, jpDdsz, ptlb);
		}

		ptlb.add("订单数量：" + list.size());
		return list;
	}
	@Override
	public List<OrderBean> queryOrders(JpPtLog ptlb,String dateStr) throws Exception {
		List<OrderBean> list = new ArrayList<OrderBean>();
		ptlb.add("不支持酷讯的手工扫描导单!");
		return list;
	}
	/**
	 * 票号回填
	 */
	@SuppressWarnings("unchecked")
	public String requestUpdateorder(String xmlto, JpPtLog ptlb) throws Exception {
		// String body = Base64.encode(xmlto.getBytes("utf-8"));
		String url = URL + "/update";
		// String url_log = URL + "/index.php?action=outdatasync&method=updateTicket&siteId=" + SITEID + "&user=" + USER
		// + "&password=" + PASS + "&data=" + body;
		Map<String,String> mparam = new HashMap<String, String>();
		mparam.put("user", USER);
		mparam.put("pass", PASS);
		mparam.put("xml", xmlto);
		ptlb.add("酷讯回填票号请求："+ url+ " 接口名：update 参数："+ mparam.toString());
		String strtrn = HttpClientUtil.doPost(url, mparam, encode);
		ptlb.add("酷讯回填票号返回"+ strtrn);
		return strtrn;
	}

	/**
	 * 回填票号
	 */
	@Override
	public boolean orderOutTicket(JpPtLog ptlb, PlatJpBean pjb) throws Exception {
		/**
		 * <orderList> <orderDetail status="300" flightOrderId="1001208301930382787" errorCode="" errorMsg="">
		 * <passenger name="张三" ticketNo="123-12344567123" certificateNo="310231198812"/> </orderDetail> </orderList>
		 */
		StringBuffer xml = new StringBuffer("<orderList>");
		xml.append("<orderDetail status='300' flightOrderId='" + pjb.getWdbh() + "' errorCode='' errorMsg=''>");
		List<JpKhdd> jpKhddList = pjb.getJpKhddList();
		Map<String,List<JpJp>> jpListMap = pjb.getJpJpListMap();
		for(int i=0;i<jpKhddList.size();i++){
			JpKhdd oneJpKhdd = jpKhddList.get(i);
			List<JpJp> jpList = jpListMap.get(oneJpKhdd.getDdbh());
			for(int j=0;j<jpList.size();j++){
				JpJp oneJp = jpList.get(j);
				String cjr = oneJp.getCjr();
				String tkno = oneJp.getTkno();
				String zjhm = oneJp.getZjhm();
				xml.append("<passenger name='" + cjr + "' ticketNo='" + tkno + "' certificateNo='" + zjhm + "'/>");
			}
		}
		xml.append("<issueTicket ticketType=\"16000\" ticketPlat=\"16001\"/>");
		xml.append("</orderDetail>");
		xml.append("</orderList>");

		ptlb.add("供应票号回填请求"+ xml.toString());
		String rtnxml = requestUpdateorder(xml.toString(), ptlb);
		if (StringUtils.isBlank(rtnxml)) {
			throw new Exception("供应票号回填错误酷讯返回为空。");
		}
		ParseXml parseXml = new ParseXml(rtnxml);
		Element orderDetail = parseXml.ele("orderDetail");
		String errorCode = orderDetail.attributeValue("errorCode");
		String errorMsg = orderDetail.attributeValue("errorMsg");
		if (StringUtils.isNotBlank(errorCode)) {
			String errinfo = "调用接口失败，错误代码：" + errorCode + ",错误信息：" + decodeUnicode(errorMsg);
			ptlb.add("供应票号回填失败"+ errinfo);
			throw new Exception(errinfo);
		}
		List<Element> list = orderDetail.elements("passenger");
		for (int i = 0; i < list.size(); i++) {
			Element element = list.get(i);
			String ticketStatus = element.attributeValue("ticketStatus");
			String ticketNo = element.attributeValue("ticketNo");
			if (!"1".equals(ticketStatus)) {
				ptlb.add("供应票号回填失败："+ "票号验真失败");
				throw new Exception(ticketNo + "票号验真失败");
			}
		}
		return true;
	}
	@Override
	public List<OrderBean> getByWbdh(String wbdh, JpPtLog ptlb)
			throws Exception {
		List<OrderBean> list = new ArrayList<OrderBean>();
		String rtnxml = byWbdh_req(ptlb, wbdh);
		if (StringUtils.isBlank(rtnxml)) {
			return null;
		}
		String error = "";
		if (rtnxml.indexOf("wrong site ip request") >= 0) {
			error = "联系酷讯,IP地址没有授权或IP登记不正确";
			ptlb.add(error);
			throw new Exception("导单出错，" + error);
		}
		if (rtnxml.indexOf("wrong site request") >= 0) {
			error = "联系酷讯,开通生产环境接口";
			ptlb.add(error);
			throw new Exception("导单出错，" + error);
		}

		ParseXml parse = null;
		try {
			parse = new ParseXml(rtnxml);
		} catch (Exception e) {
			e.printStackTrace();
			ptlb.add("解析订单详XML发生异常：异常信息：" + e.getMessage() + ",XML:"+ rtnxml);
			return null;
		}

		String status = parse.attr("status");
		if (!"OK".equals(status.toUpperCase())) {
			error = "返回异常，返回状态为:" + status;
			ptlb.add(error);
			throw new Exception("导单异常，" + error);
		}
		String lastorder_sn = "";
		List<Element> OrderInfoList = parse.get("order");
		for (int i = 0; OrderInfoList != null && i < OrderInfoList.size(); i++) {
			Element OrderInfo = OrderInfoList.get(i);
			String order_sn = parse.attr("flightOrderId", OrderInfo);
			if (i == OrderInfoList.size() - 1) {
				lastorder_sn = order_sn;
			}
			ParseKuxunGy.ddInfo(OrderInfo, parse, list, jpDdsz, ptlb);
		}

		ptlb.add("订单数量：" + list.size());
		return list;
	}
	private String byWbdh_req(JpPtLog ptlb, String wbdh) throws Exception {
		// 增量导出 5 分钟以上 指定订单号导出之后的 50 个订单
		// 全量导出 23 小时以上 1. 只导出当前时间一个月内的订单。 2. 如未添加订单状态参数则默认导出所有订单。 默认为当前时间前 31 天所有订单。
		// 精确导出 不能小于 1 分 导出指定订单信息
		// 可选 exact(精确导出)、incr（增量导出）或 all（全量导出） ，不可为空。
		// 300: PAYED_UNISSUED 支付成功等待出票
		String url = URL + "/export?user=" + USER + "&pass=" + PASS + "&type=exact&status=300&siteId=" + SITEID + "&orderNo=" + wbdh;

		ptlb.add("酷讯扫描请求支付成功等待出票(精确导出)"+ url);
		String xml = HttpClientUtil.doPost(url, null, encode) ;
		ptlb.add("酷讯扫描请求支付成功等待出票(精确导出)返回"+ xml);
		return xml;
	}
	public static StringBuffer decodeUnicode(String dataStr) {
		try {
			dataStr = dataStr.replace("&#x", "\\u");
			final StringBuffer buffer = new StringBuffer();
			String tempStr = "";
			String operStr = dataStr;
			if (operStr != null && operStr.indexOf("\\u") == -1)
				return buffer.append(operStr);
			if (operStr != null && !operStr.equals("") && !operStr.startsWith("\\u")) {
				tempStr = operStr.substring(0, operStr.indexOf("\\u"));
				operStr = operStr.substring(operStr.indexOf("\\u"), operStr.length());
			}
			buffer.append(tempStr);
			while (operStr != null && !operStr.equals("") && operStr.startsWith("\\u")) {
				tempStr = operStr.substring(0, 6);
				operStr = operStr.substring(7, operStr.length());
				String charStr = "";
				charStr = tempStr.substring(2, tempStr.length());
				char letter = (char) Integer.parseInt(charStr, 16);
				buffer.append(new Character(letter).toString());
				if (operStr.indexOf("\\u") == -1) {
					buffer.append(operStr);
				} else {
					tempStr = operStr.substring(0, operStr.indexOf("\\u"));
					operStr = operStr.substring(operStr.indexOf("\\u"), operStr.length());
					buffer.append(tempStr);
				}
			}
			return buffer;
		} catch (Exception e) {
			return new StringBuffer(dataStr);
		}
	}

}
