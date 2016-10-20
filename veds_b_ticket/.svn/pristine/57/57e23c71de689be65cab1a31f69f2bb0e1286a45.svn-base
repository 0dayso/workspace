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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.modules.utils.MD5Tool;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.TicketRefundBean;
import cn.vetech.vedsb.jp.jpddsz.JpTpdGySuper;

import com.alibaba.fastjson.util.Base64;
import com.ctrip.ws.flights.RefundConfirm;
import com.ctrip.ws.flights.RefundConfirmSoap;
import com.ctrip.ws.flights.RefundFeeConfirm;
import com.ctrip.ws.flights.RefundFeeConfirmSoap;
import com.ctrip.ws.flights.RefundFeeWaitConfirmList;
import com.ctrip.ws.flights.RefundFeeWaitConfirmListSoap;
import com.ctrip.ws.flights.RefundWaitConfirmList;
import com.ctrip.ws.flights.RefundWaitConfirmListSoap;

public class CtripGy_tf extends JpTpdGySuper{
	private static Logger logger = LoggerFactory.getLogger(CtripGy_tf.class);
	public static String userid = "120225";
	public static final String encode = "utf-8";
	
	private String USER;//
	private JpDdsz jpDdsz;
		
	public CtripGy_tf(JpDdsz jpDdsz){
		this.USER = jpDdsz.getDdJkdz();
		this.jpDdsz = jpDdsz;
	}
	// 按用户存储上一次执行时间
	private static Map<String, String> PRETIMEMAP = new HashMap<String, String>();
	/**
	 * 扫描退废单
	 */
	public List<TicketRefundBean> queryOrders_tf(JpPtLog ptlb){
		// 控制只能1分钟扫描一次
		String nowtime = VeDate.getStringDate();
		String pretime = PRETIMEMAP.get(USER+"TF");
		if (StringUtils.isNotBlank(pretime) && VeDate.getPreMin(pretime, 1).compareTo(nowtime) >= 0) {
			return null;
		}
		PRETIMEMAP.put(USER+"TF", nowtime);
		String ksrq=VeDate.getStringDateShort();
		String jsrq=VeDate.getStringDateShort();
		//IsInternational 0.国内  1.国际		
		String status = "UnAudit";
		String gngj = StringUtils.isBlank(jpDdsz.getDdGngj()) ? "0" : jpDdsz.getDdGngj();
		String reqXml = "<OpenRefundWaitConfirmListRequest>"+
			"<IsInternational>"+gngj+"</IsInternational>"+
			"<StartRefundDate>"+ksrq+"</StartRefundDate>"+
			"<EndRefundDate>"+jsrq+"</EndRefundDate>"+
			"</OpenRefundWaitConfirmListRequest>";
		String resXml=handle(reqXml, "1",ptlb);
//		String resXml="";
		List<TicketRefundBean> tfList = ParseCtrip_tf.parseRefundList(resXml, jpDdsz, ptlb);
		//查已结算退废单，更新退废单价格,并将结果保存进
		return tfList;
	}
	public List<TicketRefundBean> queryOrders_tf(JpPtLog ptlb,String dateStr){
		List<TicketRefundBean> tfList = new ArrayList<TicketRefundBean>();
		String ksrq=StringUtils.substring(dateStr, 10);
		String jsrq=ksrq;
		//IsInternational 0.国内  1.国际		
		String status = "UnAudit";
		String gngj = StringUtils.isBlank(jpDdsz.getDdGngj()) ? "0" : jpDdsz.getDdGngj();
		String reqXml = "<OpenRefundWaitConfirmListRequest>"+
			"<IsInternational>"+gngj+"</IsInternational>"+
			"<StartRefundDate>"+ksrq+"</StartRefundDate>"+
			"<EndRefundDate>"+jsrq+"</EndRefundDate>"+
			"</OpenRefundWaitConfirmListRequest>";
		String resXml=handle(reqXml, "1",ptlb);
//		String resXml="";
		List<TicketRefundBean> _tfList = ParseCtrip_tf.parseRefundList(resXml, jpDdsz, ptlb);
		tfList.addAll(_tfList);
		//查已结算退废单，更新退废单价格,并将结果保存进
		return tfList;
	}
	/**
	 * 退票确认接口
	 */
	public boolean refundConfirm(JpPtLog ptlb,TicketRefundBean tfBean){
		try {
			ptlb.add("开始调用解析退票确认接口……");
			String wbtpdh = tfBean.getTfdMap().get("WBDH");
			String wbddbh = tfBean.getTfdMap().get("WBDDBH"); 
			String reqXml = "<OpenRefundConfirmRequest><OpenRefundConfirmItems>"+
			"<OpenRefundConfirmItem><OrderId>"+wbddbh+"</OrderId>"+
			"<Prid>"+wbtpdh+"</Prid></OpenRefundConfirmItem>"+
			"</OpenRefundConfirmItems></OpenRefundConfirmRequest>";
		String resXml=handle(reqXml, "2",ptlb);
//		String resXml = "";
		return ParseCtrip_tf.parseRefundConfirm(resXml, ptlb);
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 退票费用确认接口
	 * @param ptlb
	 * @param tfBean
	 */
	public Map<String,String> refundFeeConfirm(JpPtLog ptlb){
		String reqXml ="<OpenRefundProcessRequest><OpenRefundItems>OpenRefundItem>"+
			"<OrderId>"+""+"</OrderId><Prid>"+""+"</Prid>"+
			"<AgencyModifyReason>"+""+"</AgencyModifyReason><OpenRefundItemLists>";
		List<String> ticketNos = new ArrayList<String>();
		List<String> passengers = new ArrayList<String>();
		List<String> sxhs = new ArrayList<String>();
		List<String> refunds = new ArrayList<String>();//
		List<String> modifyRefunds = new ArrayList<String>();
		for(int i=0;i<ticketNos.size();i++){
			//注 只传十位长度票号
			String ticketNo = ticketNos.get(i);
			if(ticketNo.split("-").length>1){
				ticketNo = ticketNo.split("-")[1];
			}
			String oneMF = modifyRefunds.get(i);
			if(oneMF.indexOf(".")==-1){
				oneMF+=".0";
			}
			reqXml+="<OpenRefundItemList><Refund>"+refunds.get(i)+"</Refund><ModifyRefund>"+oneMF+"</ModifyRefund>"+
				"<TicketNo>"+ticketNo+"</TicketNo><Sequence>"+sxhs.get(i)+"</Sequence><PassengerName>"+passengers.get(i)+"</PassengerName>"+
				"<UsedCost>0.0000</UsedCost><ModifiedUsedCost>0.0000</ModifiedUsedCost><UsedTax>0.0000</UsedTax><ModifiedUsedTax>0.0000</ModifiedUsedTax></OpenRefundItemList>";
		}
		reqXml+="</OpenRefundItemLists></OpenRefundItem></OpenRefundItems></OpenRefundProcessRequest>";
		String resXml=handle(reqXml, "4",ptlb);
		return ParseCtrip_tf.parseRefundFeeConfirm(resXml, ptlb);
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
		/*
		 * 接口名称 
		 * 1 退票查询接口
		 * 2 退票确认接口
		 * 3 退票费用待确认查询接口
		 * 4 退票费用确认接口
		 */
		try {
			String data="";
			if ("1".equals(method)) { //退票查询接口
				URL wsdlURL = new URL(url+"/RefundWaitConfirmList.asmx?WSDL");
		        RefundWaitConfirmList ss = new RefundWaitConfirmList(wsdlURL, new QName("http://tempuri.org/", "RefundWaitConfirmList"));
		        RefundWaitConfirmListSoap port = ss.getRefundWaitConfirmListSoap();  
		        data = port.handle(reqXml);
			}else if ("2".equals(method)) { //退票确认接口
				URL wsdlURL = new URL(url+"/RefundConfirm.asmx?WSDL");
				RefundConfirm ss = new RefundConfirm(wsdlURL, new QName("http://tempuri.org/", "RefundConfirm"));
				RefundConfirmSoap port = ss.getRefundConfirmSoap();  
		        data = port.handle(reqXml);
			}else if ("3".equals(method)){//退票费用待确认查询接口
				URL wsdlURL = new URL(url+"/RefundFeeWaitConfirmList.asmx?WSDL");
				RefundFeeWaitConfirmList ss = new RefundFeeWaitConfirmList(wsdlURL, new QName("http://tempuri.org/", "RefundFeeWaitConfirmList"));
				RefundFeeWaitConfirmListSoap port = ss.getRefundFeeWaitConfirmListSoap();
				data = port.handle(reqXml);
			}else if ("4".equals(method)){//退票费用确认接口
				URL wsdlURL = new URL(url+"/RefundFeeConfirm.asmx?WSDL");
				RefundFeeConfirm ss = new RefundFeeConfirm(wsdlURL, new QName("http://tempuri.org/", "RefundFeeConfirm"));
				RefundFeeConfirmSoap port = ss.getRefundFeeConfirmSoap();
				data = port.handle(reqXml);
			}
			ptlb.add("携程接口"+method+"返回原始数据:"+data);
			logger.error("携程接口"+method+"返回原始数据:"+data);
			Base64 base64 = new Base64();
			in = new ByteArrayInputStream(base64.decodeFast(data));
			gzin = new java.util.zip.GZIPInputStream(in);
			isr = new InputStreamReader(gzin, encode);
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

	/**
	 * 退票单复核操作，调用携程接口修改退票手续费
	 * @param ptlb
	 * @param fhParams
	 * @return
	 */
	public Map<String,String> fh(JpPtLog ptlb,Map<String,Object> fhParams){
		String gytfid = (String)fhParams.get("gytfdh");
		String wbddbh = (String)fhParams.get("WBDDBH");
		List<String> tknos = (List<String>)fhParams.get("tknos");
		List<String> cjrs = (List<String>)fhParams.get("cjrs");
		List<String> wbsxhs = (List<String>)fhParams.get("wbsxhs");
		List<String> ytpfs = (List<String>)fhParams.get("ytpfs");
		List<String> xsTpsxfs = (List<String>)fhParams.get("xsTpsxfs");
		String reqXml ="<OpenRefundProcessRequest><OpenRefundItems>OpenRefundItem>"+
		"<OrderId>"+wbddbh+"</OrderId><Prid>"+gytfid+"</Prid>"+
		"<AgencyModifyReason>退票手续费不正确。</AgencyModifyReason><OpenRefundItemLists>";
		for(int i=0;i<tknos.size();i++){
			//注 只传十位长度票号
			String tkno = tknos.get(i);
			if(tkno.split("-").length>1){
				tkno = tkno.split("-")[1];
			}
			String oneYtpf = ytpfs.get(i);
			if(oneYtpf.indexOf(".")==-1){
				oneYtpf+=".0";
			}
			String oneMF = xsTpsxfs.get(i);
			if(oneMF.indexOf(".")==-1){
				oneMF+=".0";
			}
			reqXml+="<OpenRefundItemList><Refund>"+oneYtpf+"</Refund><ModifyRefund>"+oneMF+"</ModifyRefund>"+
				"<TicketNo>"+tkno+"</TicketNo><Sequence>"+wbsxhs.get(i)+"</Sequence><PassengerName>"+cjrs.get(i)+"</PassengerName>"+
				"<UsedCost>0.0000</UsedCost><ModifiedUsedCost>0.0000</ModifiedUsedCost><UsedTax>0.0000</UsedTax><ModifiedUsedTax>0.0000</ModifiedUsedTax></OpenRefundItemList>";
		}
		reqXml+="</OpenRefundItemLists></OpenRefundItem></OpenRefundItems></OpenRefundProcessRequest>";
		String resXml=handle(reqXml, "4",ptlb);
//		String resXml="";
		return ParseCtrip_tf.parseRefundFeeConfirm(resXml, ptlb);
	}
	public static void main(String[] args) {
		String username = "携程外入API";
		String password = "365412Aa";
		System.out.println(MD5Tool.MD5Encode(username+"#"+password));
	}
	@Override
	public List<TicketRefundBean> getByWbtpdh(String wbtpdh, JpPtLog ptlb)
			throws Exception {
		throw new Exception("携程没有提供根据退票单号查询退票详情的接口");
	}
}
