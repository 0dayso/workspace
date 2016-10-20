package cn.vetech.vedsb.jp.jpddsz.ctrip;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.TicketChangeBean;


/**
 * 携程供应退废改签单接口解析类
 * 
 * @author ZhangMing
 * @version [版本号, May 7, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ParseCtrip_gq {
	/**
	 * 解析改签单XML转换成本地格式（TicketRefundPendingNoticeReqeust）
	 * @param resXml
	 * @param ptlb
	 * @return
	 */
	public static List<TicketChangeBean> parseTicketChangeDealList(String resXml,JpDdsz jpDdsz, JpPtLog ptlb){
		Document doc = null;
		try {
			doc = XmlUtils.getDocument(resXml, CtripGy_gq.encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		if (doc == null) {
			ptlb.add("解析XML出现异常");
			return null;
		}
		Element root = doc.getRootElement();
		Element header = root.element("Header");
		String resultCode = header.attributeValue("ResultCode");
		String resultMsg = header.attributeValue("ResultMsg");
		if(!"Success".equals(resultCode)){
			ptlb.add("返回的XML中状态为失败，错误信息"+resultMsg);
			return null;
		}
		List<Element> exchangeItems = null;

		try {
			exchangeItems = root.element("OpenQueryExchangeResponse")
					.element("OpenApiQueryExchangeItems").elements("OpenQueryExchangeItem");
		} catch (Exception e) {
		}
		if(CollectionUtils.isEmpty(exchangeItems)){
			ptlb.add("返回的XML中没有改签单信息（OpenQueryExchangeItem）");
			return null;
		}
		Map<String,TicketChangeBean> tmpMap = new HashMap<String, TicketChangeBean>();
		String ticketNoStr = "";
		List<Map<String,Object>> hdMapList = new ArrayList<Map<String,Object>>();
		String travelRangeTmp = "";
		for(int i=0;i<exchangeItems.size();i++){
			Element oneItem = exchangeItems.get(i);
			String orderID =  oneItem.elementText("OrderID").trim();//原订单编号
			String sequence = oneItem.elementText("Sequence").trim();//航程序号
			String processStatus = oneItem.elementText("ProcessStatus").trim();//改签申请处理类型  改签、 升舱
			String remark = oneItem.elementText("Remark").trim(); //改签备注
			String passengerName = oneItem.elementText("PassengerName").trim();//乘客姓名
			String oldFlight = oneItem.elementText("OldFlight").trim();//原航班号
			String oldDPort = oneItem.elementText("OldDPort").trim();//原出发机场
			String oldAPort = oneItem.elementText("OldAPort").trim();//原到达机场
			String oldTakeOffTime = oneItem.elementText("OldTakeOffTime").trim();//原起飞时间
			String oldSubClass = oneItem.elementText("OldSubClass").trim();//原舱位
			String oldRecordNo = oneItem.elementText("OldRecordNo").trim();//原PNR
			String oldTicketNO = oneItem.elementText("OldTicketNO").trim();//原票号（10位）
			
			String flight = oneItem.elementText("Flight").trim();//航班号
			String dPort = oneItem.elementText("DPort").trim();//出发机场
			String aPort = oneItem.elementText("APort").trim();//到达机场
			String takeOffTime = oneItem.elementText("TakeOffTime").trim();//起飞时间
			String subClass = oneItem.elementText("SubClass").trim();//舱位
			String recordNo = oneItem.elementText("RecordNo").trim();//PNR
			String priceDifferential = oneItem.elementText("PriceDifferential").trim();//差价
			String changeFee = oneItem.elementText("ChangeFee").trim();//更改费用
			String rebookingQueryFee = oneItem.elementText("RebookingQueryFee").trim();//重新预定金额
			String rebookingDate = oneItem.elementText("RebookingDate").trim();//重新预定时间
			String rbkId = oneItem.elementText("RbkId").trim();//改签单号
			String travelRange = dPort+aPort;
			String fromDatetime = "";
			if(travelRangeTmp.indexOf(travelRange)==-1){
				Map<String,Object> hdMap = new HashMap<String,Object>();
				hdMap.put("O_XS_HBH", oldFlight);
				hdMap.put("N_XS_HBH", flight);
				hdMap.put("O_XS_CW", oldSubClass);
				hdMap.put("N_XS_CW", subClass);
				hdMap.put("O_CFSJ", oldTakeOffTime.replaceAll("T", " ").substring(0, 16));
				hdMap.put("N_CFSJ", takeOffTime.replaceAll("T", " ").substring(0, 16));
				hdMap.put("CFCITY", oldDPort);
				hdMap.put("DDCITY", oldAPort);
				hdMap.put("WBSXH", sequence);
				hdMapList.add(hdMap);
			}
			ticketNoStr += oldTicketNO;
			TicketChangeBean tcBean = tmpMap.get(oldRecordNo);
			if(tcBean==null){
				tcBean = new TicketChangeBean();		
				Map<String,Object> gqdMap = new HashMap<String, Object>();
				gqdMap.put("SHBH", jpDdsz.getShbh());
				gqdMap.put("GQZT", "0");
				gqdMap.put("WBDH", rbkId);
				gqdMap.put("DDYH", jpDdsz.getDdUserid());
				gqdMap.put("XS_PNR_NO",oldRecordNo);
				String changeType = "2";//升舱
				if(!oldTakeOffTime.equals(takeOffTime)){
					changeType = "1";//改期
				}
				gqdMap.put("GQLX", changeType);
				gqdMap.put("GQYY", remark);
				if(StringUtils.isBlank(remark)){
					gqdMap.put("GQYY", "其他改签原因");
				}
				//改签原因  不明 所以取改签备注信息

				gqdMap.put("GQ_XS_PNR_NO", recordNo);			
				tcBean.setGqdMap(gqdMap);
				tcBean.setGqdMxMapList(new ArrayList<Map<String,Object>>());
				tmpMap.put(oldRecordNo, tcBean);
			}
			Map<String,Object> gqdMxMap = new HashMap<String, Object>();
			gqdMxMap.put("CJR", passengerName);
			gqdMxMap.put("TKNO", oldTicketNO);
			
			gqdMxMap.put("GQ_XSFY", changeFee);
			tcBean.getGqdMxMapList().add(gqdMxMap);
		}
		List<TicketChangeBean> tcdBeanList = new ArrayList<TicketChangeBean>();
		for(String key:tmpMap.keySet()){
			TicketChangeBean one = tmpMap.get(key);
			one.setHdMapList(hdMapList);
			tcdBeanList.add(one);
		}
		//查询出机票结算三字代号
		ptlb.add("本次扫描共"+tcdBeanList.size()+"个改签单");
		return tcdBeanList;
	}
	/**
	 * 改签操作接口返回解析
	 * @param resXml
	 * @param ptlb
	 * @return
	 */
	public static Map<String,String> parseModifyExchangeList(String resXml,JpPtLog ptlb){
		Map<String,String> retMap = new HashMap<String, String>();
		Document doc = null;
		try {
			doc = XmlUtils.getDocument(resXml, CtripGy_gq.encode);	
			Element root = doc.getRootElement();
			Element header = root.element("Header");
			String resultCode = header.attributeValue("ResultCode");
			String resultMsg = header.attributeValue("ResultMsg");
			if(!"Success".equals(resultCode)){
				ptlb.add("返回的XML中状态为失败，错误信息"+resultMsg);
				retMap.put("status", "false");
				retMap.put("message", "返回的XML中状态为失败，错误信息"+resultMsg);
				return retMap;
			}
			Element omeResEl = root.element("OpenModifyExchangeResponse");
			String resultCode_jg = omeResEl.elementText("ResultCode").trim();
			if(!"0".equals(resultCode_jg)){
				String message = omeResEl.elementText("Message").trim();
				ptlb.add("改签操作接口返回失败，失败描述"+message);
				retMap.put("status", "false");
				retMap.put("message", "改签操作接口返回失败，失败描述"+message);
				return retMap;
			}
			retMap.put("status", "true");
		}catch (Exception e) {
			e.printStackTrace();
			retMap.put("status", "false");
			retMap.put("message", "解析改签操作接口返回失败，失败原因"+e.toString());
		}
		return retMap;
	}
}