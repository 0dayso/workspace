package cn.vetech.vedsb.jp.jpddsz.ctrip;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.modules.mapper.JsonMapper;
import org.vetech.core.modules.utils.MD5Tool;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;
import cn.vetech.vedsb.jp.entity.jpddsz.PlatJpBean;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.jpddsz.JpddGySuper;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddKzServiceImpl;

import com.alibaba.fastjson.util.Base64;
import com.ctrip.ws.flights.GetPnrPermission;
import com.ctrip.ws.flights.GetPnrPermissionSoap;
import com.ctrip.ws.flights.IssueBillAssignTo;
import com.ctrip.ws.flights.IssueBillAssignToSoap;
import com.ctrip.ws.flights.IssueBillInfo;
import com.ctrip.ws.flights.IssueBillInfoSoap;
import com.ctrip.ws.flights.IssueBillModify;
import com.ctrip.ws.flights.IssueBillModifySoap;
import com.ctrip.ws.flights.OpenIssueOrderList;
import com.ctrip.ws.flights.OpenIssueOrderListSoap;
import com.ctrip.ws.flights.SearchModifyOrderInfo;
import com.ctrip.ws.flights.SearchModifyOrderInfoSoap;

public class CtripGy extends JpddGySuper{
	private static Logger logger = LoggerFactory.getLogger(CtripGy.class);
	public static String userid = "120225";
	private JpDdsz jpDdsz;
	private JpKhddKzServiceImpl jkksImpl;
	public CtripGy(JpDdsz jpDdsz){
		this.jpDdsz = jpDdsz;
	}

	@Override
	public List<OrderBean> queryOrders(JpPtLog ptlb) throws Exception {
		List<OrderBean> listOrder = new ArrayList<OrderBean>();
		listOrder.addAll(_queryOrders(ptlb, 120, ""));
		return listOrder;
	}
	@Override
	public List<OrderBean> queryOrders(JpPtLog ptlb,String dateStr) throws Exception {
		List<OrderBean> listOrder = new ArrayList<OrderBean>();
		listOrder.addAll(_queryOrders(ptlb, 120, dateStr));
		return listOrder;
	}
	public List<OrderBean> _queryOrders(JpPtLog ptlb,int min,String dateStr) throws Exception {
		String now = dateStr;
		if(StringUtils.isBlank(now)){
			now = VeDate.getStringDateShortmm();
		}
		List<OrderBean> listOrder = new ArrayList<OrderBean>();
		String ksrq=VeDate.getPreMin(now, -min).replaceAll(" ", "T");
		String jsrq=now.replaceAll(" ", "T");	
		String reqXml = "<OpenIssueOrderListRequest><OrderBeginDateTime>"+ksrq+"</OrderBeginDateTime><OrderEndDateTime>"+jsrq+"</OrderEndDateTime></OpenIssueOrderListRequest>";
		//1.请求列表
		String resXml=handle(reqXml, "1",ptlb);
		if(StringUtils.isBlank(resXml)){
			ptlb.add("接口请求返回为空，请求XML："+reqXml);
			return listOrder;
		}
		
		List<Map<String,String>> orderMapList = null;
		//去掉之前的订单列表
		orderMapList=ParseCtrip.parseOpenIssueOrderList(resXml,ptlb);
		if(CollectionUtils.isEmpty(orderMapList)){
			return listOrder;
		}
		for (Map<String,String> oneMap:orderMapList) {
			String orderid = oneMap.get("OrderID");//订单编号
			String IssueBillID = oneMap.get("IssueBillID");//出票单号
			String IssueBillAssignOperator = oneMap.get("IssueBillAssignOperator");
		    String jjd = oneMap.get("Urgency");//紧急度
		    String cpcs = oneMap.get("UrgeTimes");//催票次数
		    Map<String,String> kzMap = new HashMap<String, String>();
			//记录网店订单扩展表信息		
			//请求出票单详细	  
			String IBInfoReqXml = "<OpenIssueBillInfoRequest><IssueBillID>"+IssueBillID+"</IssueBillID></OpenIssueBillInfoRequest>";		
			String IBInfoResXml = handle(IBInfoReqXml, "2",ptlb);//出票单详细
			OrderBean orderBean =ParseCtrip.parseOrderList2(ptlb,IBInfoResXml,jpDdsz);
			kzMap.put("JJD", jjd);
			kzMap.put("CPCS", cpcs);
			kzMap.put("CPDH", IssueBillID);
			kzMap.put("SHBH", jpDdsz.getShbh());
			if(orderBean!=null){
				orderBean.getKhddMap().put("JJD", jjd);//携程用节点出票单号	
				orderBean.getKhddMap().put("CPCS", cpcs);
			}
			listOrder.add(orderBean);
		}
		return listOrder;
	}
	@Override
	public boolean orderOutTicket(JpPtLog ptlb, PlatJpBean pjb)
			throws Exception {
		//携程票号回填时按出票单来的， 一个出票单对应我们系统一个订单
		String orderid = pjb.getWdbh();
		String ddbh = pjb.getDdbhs().get(0);
		String issuebillid = pjb.getJpKhddKzList().get(0).getCpdh();
		List<JpJp> jpList = pjb.getJpJpListMap().get(ddbh);
		List<JpKhddHd> hdList = pjb.getJpKhddHdListMap().get(ddbh);
		//票号回填XML
		StringBuffer reqXml=new StringBuffer();
		reqXml.append("<OpenModifyOrderRequest>");
		reqXml.append(XmlUtils.xmlnode("OrderID", orderid));//对同一订单号多次重复提交以最后一次票号为准
		reqXml.append(XmlUtils.xmlnode("BookingChannel", ""));//为国际机票保留
		reqXml.append(XmlUtils.xmlnode("ExtOrderID", ""));//外部单号  B2C航司订单编号
		if(StringUtils.isNotBlank(issuebillid)){
			reqXml.append(XmlUtils.xmlnode("IssueBillID", issuebillid));//出票单号
		}
		reqXml.append("<PNRLists>");
		for(int i=0;i<hdList.size();i++){
			JpKhddHd oneHd = hdList.get(i);
			reqXml.append("<PNRList>");
			reqXml.append(XmlUtils.xmlnode("PNR", ""));// 代理人系统PNR
			reqXml.append(XmlUtils.xmlnode("PNRResultStatus", "1"));// 出票状态 1:成功 3:失败 设置失败将不更新订单
			reqXml.append(XmlUtils.xmlnode("FlightNo", oneHd.getXsHbh()));
			reqXml.append(XmlUtils.xmlnode("SubClass",oneHd.getXsCw()));
			reqXml.append(XmlUtils.xmlnode("Sequence", oneHd.getWbsxh()));// 航程序号 订单中的航程序列号
			reqXml.append(XmlUtils.xmlnode("DPort", oneHd.getCfcity()));//出发城市三字码
			reqXml.append(XmlUtils.xmlnode("APort", oneHd.getDdcity()));//到达城市三字码
			reqXml.append("<TicketLists>");
			for(int j=0;j<jpList.size();j++){
				JpJp oneJp = jpList.get(j);
				String tkno = oneJp.getTkno();
				reqXml.append("<TicketList>");
				reqXml.append(XmlUtils.xmlnode("PassengerName", oneJp.getCjr()));// 乘客姓名
				reqXml.append(XmlUtils.xmlnode("AirLineCode", tkno.split("-")[0]));// 航空公司三字码 731
				reqXml.append(XmlUtils.xmlnode("TicketNo", tkno.split("-")[1]));// 票号 十位号码
				reqXml.append(XmlUtils.xmlnode("TicketResultStatus", "1"));// 票出票状态 1:成功3:失败 // 全部航程全部票号的状态都为1，才会更新
				reqXml.append("</TicketList>");
			}
			reqXml.append("</TicketLists>");
			reqXml.append("</PNRList>");
		}
		reqXml.append("</PNRLists>");
		reqXml.append("</OpenModifyOrderRequest>");
		logger.error("执行票号回填请求XML："+reqXml.toString());
		//携程修改PNR接口
		String resXml=handle(reqXml.toString(), "6",ptlb);
//		String resXml="";
		return ParseCtrip.parseModify(ptlb, resXml);
	}
	/**
	 * 通过外部单号获取订单详情
	 * 备注：携程要传出票单号
	 */
	@Override
	public List<OrderBean> getByWbdh(String wbdh, JpPtLog ptlb)
			throws Exception {
		List<OrderBean> list = new ArrayList<OrderBean>();
		
		//请求出票单详细	  
		String IBInfoReqXml = "<OpenIssueBillInfoRequest><IssueBillID>"+wbdh+"</IssueBillID></OpenIssueBillInfoRequest>";		
		String IBInfoResXml = handle(IBInfoReqXml, "2",ptlb);//出票单详细
		try {
			OrderBean orderBean =ParseCtrip.parseOrderList2(ptlb,IBInfoResXml,jpDdsz);
			if(orderBean!=null){
				list.add(orderBean);
			}		
		} catch (Exception e) {
			ptlb.add("获取携程订单详情报错"+e.getMessage());
		}
		return list;
	}
	/**
	 * PNR 授权接口 
	 * @param orderId   供应订单号 
	 * @param officeNo   OFFICE号  被授权的OFFICE号
	 * @param Pnr   小编码
	 * @param filghtClass  国内/国际   N—国内  I—国际
	 * @param ptlb   平台日志
	 * @return   String['状态','信息']   状态0 成功    -1失败    信息：提示信息
	 * @throws Exception
	 */
	public String[] PnrPermission(String orderId,String cpdh, String officeNo, String pnr, JpPtLog ptlb) throws Exception {
		String[] result = new String[2];
		try {
			if(StringUtils.isBlank(orderId)||StringUtils.isBlank(cpdh)||StringUtils.isBlank(officeNo)||StringUtils.isBlank(pnr)){
				result[0] = "-1";
				result[1] = "[orderid]供应订单编号[cpdh]出票单号[officeNo]被授权office号[pnr]小编码不能为空。";
				return result;
			}
			String filghtClass = "N";
			if("1".equals(jpDdsz.getDdGngj())){
				filghtClass = "I";
			}
			StringBuffer stb = new StringBuffer("<GetPnrPermissionRequest>");
			stb.append(XmlUtils.xmlnode("OrderID", orderId));
			stb.append(XmlUtils.xmlnode("IssueBillID", cpdh));
			stb.append(XmlUtils.xmlnode("OfficeNo", officeNo));
			stb.append(XmlUtils.xmlnode("PNR", pnr));
			stb.append(XmlUtils.xmlnode("FlightClass", filghtClass));
			stb.append("</GetPnrPermissionRequest>");
			String responseXml = handle(stb.toString(), "4", ptlb);
			String str = "携程授权接口请求XML:"+stb.toString()+"\r\n";
			str += "授权接口返回："+responseXml+"\r\n";
			return ParseCtrip.parsePnrPermission(ptlb, responseXml);
		} catch (Exception e) {
			result[0] = "-1";
			result[1] = e.getMessage();
			return result;
		}
	}
	/**
	 * 订单认领
	 * operatorType  1认领，2取消认领
	 */
	public boolean IBAssignTo(String orderID,String issueBillID,String operatorType,JpPtLog ptlb) throws Exception {
		//订单认领
		ptlb.add("开始认领出票单……");
		String assignToReqXml="<OpenIssueBillAssignRequest><OperatorType>"+operatorType+"</OperatorType><OrderID>"+orderID+"</OrderID><IssueBillID>"+issueBillID+"</IssueBillID></OpenIssueBillAssignRequest>";
		String responseXml = handle(assignToReqXml, "3",ptlb);
		return ParseCtrip.parseIBAssignTo(ptlb, responseXml);
	}
	/**
	 * 补充账号信息到XML
	 */
	private String fillXML(String xml, String method) {
		String username=jpDdsz.getDdJkzh();
		String password= jpDdsz.getDdJkmm();

		password = MD5Tool.MD5Encode(username+"#"+password);
		
		StringBuffer str = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		str.append("<Request UserName=\"" + username + "\" UserPassword=\"" + password + "\"><Header UserID=\"" + userid + "\" />");
		str.append(xml);
		str.append("</Request>");
		return str.toString();
	}
	/**
	 * 通讯
	 * @param xml 业务XML
	 * @param method 
	 */
	@SuppressWarnings("finally")
	private  String handle(String xml, String method,JpPtLog ptlb) {
		String url=jpDdsz.getDdJkdz();
		if(StringUtils.isBlank(url)){
			url="http://Flights.ws.ctrip.com/Flight.Order.SupplierOpenAPI";
		}
		String reqXml=fillXML(xml, method);
		ptlb.add("携程接口"+method+"请求XML:"+reqXml);
		logger.error("携程接口"+method+"请求XML:"+reqXml);
		StringBuffer result = new StringBuffer();
		InputStream in = null;
		java.util.zip.GZIPInputStream gzin = null;
		InputStreamReader isr = null;
		try {
			String data="";
			if ("1".equals(method)) { //今日出票单接口
				URL wsdlURL = new URL(url+"/OpenIssueOrderList.asmx?WSDL");
		        OpenIssueOrderList ss = new OpenIssueOrderList(wsdlURL, new QName("http://tempuri.org/", "OpenIssueOrderList"));
		        OpenIssueOrderListSoap port = ss.getOpenIssueOrderListSoap();  
		        data = port.handle(reqXml);
			}else if ("2".equals(method)) { //出票单详情接口
				URL wsdlURL = new URL(url+"/IssueBillInfo.asmx?WSDL");
		        IssueBillInfo ss = new IssueBillInfo(wsdlURL, new QName("http://tempuri.org/", "IssueBillInfo"));
		        IssueBillInfoSoap port = ss.getIssueBillInfoSoap();  
		        data = port.handle(reqXml);
			}else if ("3".equals(method)){//出票单认领接口
				URL wsdlURL = new URL(url+"/IssueBillAssignTo.asmx?WSDL");
				IssueBillAssignTo ss = new IssueBillAssignTo(wsdlURL, new QName("http://tempuri.org/", "IssueBillAssignTo"));
				IssueBillAssignToSoap port = ss.getIssueBillAssignToSoap();
				data = port.handle(reqXml);
			}else if ("4".equals(method)){//PNR授权接口
				URL wsdlURL = new URL(url+"/Getpnrpermission.asmx?WSDL");
				GetPnrPermission ss = new GetPnrPermission(wsdlURL, new QName("http://tempuri.org/", "GetPnrPermission"));
				GetPnrPermissionSoap port = ss.getGetPnrPermissionSoap();
				data = port.handle(reqXml);
			}else if ("5".equals(method)){//出票单（pnr）修改接口
				URL wsdlURL = new URL(url+"/IssueBillModify.asmx?WSDL");
				IssueBillModify ss = new IssueBillModify(wsdlURL, new QName("http://tempuri.org/", "IssueBillModify"));
				IssueBillModifySoap port = ss.getIssueBillModifySoap();
				data = port.handle(reqXml);
			}else if ("6".equals(method)){//票号回填接口
				URL wsdlURL = new URL(url+"/SearchModifyOrderInfo.asmx?WSDL");
				SearchModifyOrderInfo ss = new SearchModifyOrderInfo(wsdlURL, new QName("http://tempuri.org/", "IssueBillModify"));
				SearchModifyOrderInfoSoap port = ss.getSearchModifyOrderInfoSoap();
				data = port.handle(reqXml);
			}
			ptlb.add("携程接口"+method+"返回原始数据:"+data);
			Base64 base64 = new Base64();
			in = new ByteArrayInputStream(base64.decodeFast(data));
			gzin = new java.util.zip.GZIPInputStream(in);
			isr = new InputStreamReader(gzin, "UTF-8");
			char[] c = new char[1024];
			int a = isr.read(c);
			while (a != -1) {
				result.append(new String(c, 0, a));
				a = isr.read(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(isr!=null){
					isr.close();
				}
				if(gzin!=null){
					gzin.close();
				}
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ptlb.add("携程接口"+method+"返回XML数据:"+result.toString());
		logger.error("携程接口"+method+"返回XML数据:"+result.toString());
		return result.toString();
	}
	public JpKhddKzServiceImpl getJkksImpl() {
		return jkksImpl;
	}
	public void setJkksImpl(JpKhddKzServiceImpl jkksImpl) {
		this.jkksImpl = jkksImpl;
	}
	public static void main(String[] args) {
		Map<String,String> aaa = new HashMap<String, String>();
		aaa.put("123", "123");
		aaa.put("234", "234");
		aaa.put("345", "345");
		JsonMapper jsonMapper = new JsonMapper();
		System.out.println(jsonMapper.toJson(aaa));
	}
}
