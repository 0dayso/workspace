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
import org.vetech.core.modules.utils.MD5Tool;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.TicketChangeBean;
import cn.vetech.vedsb.jp.jpddsz.JpGqdGySuper;

import com.alibaba.fastjson.util.Base64;
import com.ctrip.ws.flights.ConfirmRebookAmountService;
import com.ctrip.ws.flights.ConfirmRebookAmountServiceSoap;
import com.ctrip.ws.flights.ModifyExchangeList;
import com.ctrip.ws.flights.ModifyExchangeListSoap;
import com.ctrip.ws.flights.QueryRebookConfirmService;
import com.ctrip.ws.flights.QueryRebookConfirmServiceSoap;
import com.ctrip.ws.flights.SearchExchangeList;
import com.ctrip.ws.flights.SearchExchangeListSoap;

public class CtripGy_gq extends JpGqdGySuper{
	public static String userid = "120225";
	public static final String encode = "utf-8";
	
	private String USER;//
	private String URL;
	private JpDdsz jpDdsz;
		
	public CtripGy_gq(JpDdsz jpDdsz){
		this.USER = jpDdsz.getDdJkdz();
		this.URL = jpDdsz.getDdJkdz();
		this.jpDdsz = jpDdsz;
	}
	// 按用户存储上一次执行时间
	private static Map<String, String> PRETIMEMAP = new HashMap<String, String>();
	/**
	 * 扫描改签单
	 */
	public List<TicketChangeBean> queryOrders_gq(JpPtLog ptlb){
		List<TicketChangeBean> gqList = new ArrayList<TicketChangeBean>();
		// 控制只能1分钟扫描一次 每次扫描30分钟内容
		String nowtime = VeDate.getStringDate();
		String pretime = PRETIMEMAP.get(USER+URL+"GQ");
		if (StringUtils.isNotBlank(pretime) && VeDate.getPreMin(pretime, 1).compareTo(nowtime) >= 0) {
			return gqList;
		}
		String ksrq=VeDate.getPreMin(nowtime, 30).replace(" ", "T");
		String jsrq=nowtime.replace(" ", "T");
//		Unhandle:未处理申请
//		Handled:已处理申请
//		All:所有
		String status = "Unhandle";
		String reqXml = "<OpenQueryExchangeRequest>"+
			"<FromDateTime>"+ksrq+"</FromDateTime>"+
			"<ToDateTime>"+jsrq+"</ToDateTime>"+
			"<RebookQueryOptionType>"+status+"</RebookQueryOptionType>"+
			"</OpenQueryExchangeRequest>";
		String resXml=handle(reqXml, "1",ptlb);
//		String resXml="";
		List<TicketChangeBean> _gqList = ParseCtrip_gq.parseTicketChangeDealList(resXml, jpDdsz, ptlb);
		gqList.addAll(_gqList);
		return gqList;
	}
	/**
	 * 扫描改签单
	 */
	public List<TicketChangeBean> queryOrders_gq(JpPtLog ptlb,String dateStr){
		List<TicketChangeBean> gqList = new ArrayList<TicketChangeBean>();
		String ksrq=VeDate.getPreMin(dateStr, 30).replace(" ", "T");
		String jsrq=dateStr.replace(" ", "T");
//		Unhandle:未处理申请
//		Handled:已处理申请
//		All:所有
		String status = "Unhandle";
		String reqXml = "<OpenQueryExchangeRequest>"+
			"<FromDateTime>"+ksrq+"</FromDateTime>"+
			"<ToDateTime>"+jsrq+"</ToDateTime>"+
			"<RebookQueryOptionType>"+status+"</RebookQueryOptionType>"+
			"</OpenQueryExchangeRequest>";
		String resXml=handle(reqXml, "1",ptlb);
//		String resXml="";
		List<TicketChangeBean> _gqList = ParseCtrip_gq.parseTicketChangeDealList(resXml, jpDdsz, ptlb);
		gqList.addAll(_gqList);
		return gqList;
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
		StringBuffer result = new StringBuffer();
		InputStream in = null;
		java.util.zip.GZIPInputStream gzin = null;
		InputStreamReader isr = null;
		/*
		 * 接口名称 
		 * 1 改签处理查询列表接口
		 * 2 改签操作接口
		 * 3 改签结算查询服务接口
		 * 4 改签确认接口
		 */
		try {
			String data="";
			if ("1".equals(method)) { //改签处理查询列表接口
				URL wsdlURL = new URL(url+"/SearchExchangeList.asmx?WSDL");
				SearchExchangeList ss = new SearchExchangeList(wsdlURL, new QName("http://tempuri.org/", "SearchExchangeList"));
				SearchExchangeListSoap port = ss.getSearchExchangeListSoap();  
		        data = port.handle(reqXml);
			}else if ("2".equals(method)) { //改签操作接口
				URL wsdlURL = new URL(url+"/ModifyExchangeList.asmx?WSDL");
				ModifyExchangeList ss = new ModifyExchangeList(wsdlURL, new QName("http://tempuri.org/", "ModifyExchangeList"));
				ModifyExchangeListSoap port = ss.getModifyExchangeListSoap();  
		        data = port.handle(reqXml);
			}else if ("3".equals(method)){//改签结算查询服务接口
				URL wsdlURL = new URL(url+"/QueryRebookConfirm.asmx?WSDL");
				QueryRebookConfirmService ss = new QueryRebookConfirmService(wsdlURL, new QName("http://tempuri.org/", "QueryRebookConfirm"));
				QueryRebookConfirmServiceSoap port = ss.getQueryRebookConfirmServiceSoap();
				data = port.handle(reqXml);
			}else if ("4".equals(method)){//退票费用确认接口
				URL wsdlURL = new URL(url+"/ConfirmRebookAmountService.asmx?WSDL");
				ConfirmRebookAmountService ss = new ConfirmRebookAmountService(wsdlURL, new QName("http://tempuri.org/", "ConfirmRebookAmountService"));
				ConfirmRebookAmountServiceSoap port = ss.getConfirmRebookAmountServiceSoap();
				data = port.handle(reqXml);
			}
			ptlb.add("携程接口"+method+"返回原始数据:"+data);
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
		return result.toString();
	}
//	/**
//	 * 改签操作接口
//	 * @param ptlb
//	 * @param gqReq
//	 */
//	public Map<String,String> gq(JpPtLog ptlb,Map<String,Object> gqParams){
//		JpGqd jpGqd = (JpGqd)gqParams.get("jpGqd");
//		List<JpGqdCjr> gqCjrList = jpGqd.getCjrList();
//		List<JpGqdHd> gqHdList = jpGqd.getHdList();
//		/*
//		 * RebookProcessStatusType
//		 * 改签处理成功:Success
//		 * 改签处理失败:Failure
//		 * 改签未处理:Unhandle
//		 */
//		String rebookProcessStatusType ="Success";
//		/*
//		 * RebookType
//		 * 从 Eterm 改签: Eterm
//		 * 航司网站提交: AirlinesSubmit
//         * 航司电话改签: AirlinesPhone
//		 */
//		String rebookType = "Eterm";
//		String reqXml = "<OpenModifyExchangeRequest><RebookProcessStatusType>"+rebookProcessStatusType+"</RebookProcessStatusType>"+
//			"<RebookType>"+rebookType+"</RebookType><RbkId>"+jpGqd.getGqdh()+"</RbkId><Remark>"+""+"</Remark>"+
//			"<OpenApiModifyExchangeItems>";
//		String npnrno = jpGqd.getGqXsPnrNo();
//		for(int i=0;i<gqCjrList.size();i++){
//			JpGqdCjr gqCjr = gqCjrList.get(i);
//			//注 只传十位长度票号
//			String tkno = StringUtils.trimToEmpty(gqCjr.getTkno());
//			String ticketNo = tkno.split("-")[1];
//			String airLineCode = tkno.split("-")[0];
//			String passenger = StringUtils.trimToEmpty(gqCjr.getCjr());
//			String gqxsfy = gqCjr.getGqXsfy()+"";
//			for(int j=0;j<gqHdList.size();j++){
//				String rebookingQueryFees = "0";
//				if(j==0){//改签费用放在第一段上面
//					rebookingQueryFees = gqxsfy;
//				}
//				JpGqdHd gqHd = gqHdList.get(j);
//				String wbsxh = StringUtils.trimToEmpty(gqHd.getWbsxh());
//				String cfcity = StringUtils.trimToEmpty(gqHd.getCfcity());
//				String ddcity = StringUtils.trimToEmpty(gqHd.getDdcity());
//				String nXsHbh = StringUtils.trimToEmpty(gqHd.getnXsHbh());
//				String airLine = nXsHbh.replaceAll("\\*", "").substring(0,2);
//				String nCfsj = StringUtils.trimToEmpty(gqHd.getnCfsj());
//				String nXsCw = StringUtils.trimToEmpty(gqHd.getnXsCw());
//				
//				reqXml+="<OpenApiModifyExchangeItem><Sequence>"+wbsxh+"</Sequence><AirLineCode>"+airLineCode+"</AirLineCode>"+
//				"<AirLine>"+airLine+"</AirLine><PassengerName>"+passenger+"</PassengerName><Flight>"+nXsHbh+"</Flight>"+
//				"<DPort>"+cfcity+"</DPort><APort>"+ddcity+"</APort><TakeOffTime>"+nCfsj+"</TakeOffTime>"+
//				"<SubClass>"+nXsCw+"</SubClass><TicketNO>"+ticketNo+"</TicketNO><RecordNo>"+npnrno+"</RecordNo>"+
//				"<RebookingQueryFee>"+rebookingQueryFees+"</RebookingQueryFee></OpenApiModifyExchangeItem>";
//			}
//		}
//		reqXml+="</OpenApiModifyExchangeItems></OpenModifyExchangeRequest>";
//		String resXml=handle(reqXml, "2",ptlb);	
//		return ParseCtrip_gq.parseModifyExchangeList(resXml, ptlb);
//	}
}
