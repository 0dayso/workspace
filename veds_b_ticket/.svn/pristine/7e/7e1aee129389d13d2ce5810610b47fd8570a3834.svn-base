package cn.vetech.vedsb.jp.jpddsz.ctrip;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.TicketRefundBean;


/**
 * 携程供应退废改签单接口解析类
 * 
 * @author ZhangMing
 * @version [版本号, May 7, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ParseCtrip_tf {
	/**
	 * 解析退废单XML转换成本地格式（TicketRefundPendingNoticeReqeust）
	 * @param resXml 用于解析的XML
	 * @param ptlb 日志记录类
	 * @param type 过滤类型， 0只导入待确认退废单入库  默认0
	 * @return
	 */
	public static List<TicketRefundBean> parseRefundList(String resXml,JpDdsz jpDdsz,JpPtLog ptlb){
		List<TicketRefundBean> list = new ArrayList<TicketRefundBean>();
		Document doc = null;
		try {
			doc = XmlUtils.getDocument(resXml, CtripGy_tf.encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		if (doc == null) {
			ptlb.add("解析XML出错!");
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
		List<Element> openRefundAuditItemList = null;
		try {
			openRefundAuditItemList = root.element("OpenRefundAuditListResponse")
					.element("RefundAuditListItems").elements("OpenRefundAuditItem");
		} catch (Exception e) {	
		}
		if(CollectionUtils.isEmpty(openRefundAuditItemList)){
			ptlb.add("返回的XML中没有退废单信息（OpenRefundAuditItem）");
			return null;
		}
		Map<String,TicketRefundBean> tmpMap = new HashMap<String, TicketRefundBean>();
		for(int i=0;i<openRefundAuditItemList.size();i++){
			Element oneRI = openRefundAuditItemList.get(i);
			String prID = oneRI.elementText("Prid").trim();//退废单号
			String orderNo = oneRI.elementText("OrderId").trim();//关联正常单号
			String feeType = oneRI.elementText("FeeType").trim();//退票费状态： 待确认、待结算、  差异待审核、已结算
			if(!"待确认".equals(feeType)&&!"已结算".equals(feeType)){
				continue;
			}
			String isForceAudited = oneRI.elementText("IsForceAudited").trim();//是否强制审核 0审核 1不审核
			String sendTicketServerFee = oneRI.elementText("SendTicketServerFee").trim();//送票服务费
			String insuranceServerFee = oneRI.elementText("InsuranceServerFee").trim();//送票服务费
			String refundServerFee = oneRI.elementText("RefundServerFee").trim();//送票服务费
			List<Element> refundDetailItems = null;
			try {
				refundDetailItems = oneRI.element("RefundAuditDetailItems").elements("OpenRefundAuditDetailItem");//每个乘客的退废信息
			} catch (Exception e) {
			}
			if(CollectionUtils.isEmpty(refundDetailItems)){
				ptlb.add("第"+(i+1)+"条没有找到对应乘客的退废详细信息（OpenRefundAuditDetailItem）");
				continue;
			}
			
			
			Map<String,String> tpdMap = new HashMap<String, String>();
			tpdMap.put("SHBH", jpDdsz.getShbh());
			tpdMap.put("WBDH", prID);
			tpdMap.put("WBDDBH", orderNo);
			tpdMap.put("DDYH", jpDdsz.getDdUserid());

			for(int j=0;j<refundDetailItems.size();j++){
				Element oneRDI = refundDetailItems.get(j);
				String refundAuditDetailItemID = oneRDI.elementText("RefundAuditDetailItemID").trim();//
				String refundType = oneRDI.elementText("RefundType").trim(); //携程 退废类型， 自愿   非自愿
				String AirLineCode = oneRDI.elementText("AirLineCode").trim(); //航司结算三字码
				String recordNO = oneRDI.elementText("RecordNO").trim(); //携程 PNR
				String ticketNO = oneRDI.elementText("TicketNO").trim(); //携程 票号
				String dport = oneRDI.elementText("Dport").trim();//携程 起飞机场
				String aport = oneRDI.elementText("Aport").trim();//携程 降落机场
				String travelRange = dport+aport;
				String passengerName = oneRDI.elementText("PassengerName").trim(); //乘机人姓名
				String flight = oneRDI.elementText("Flight").trim();
				String sequence = oneRDI.elementText("Sequence").trim();
				
				TicketRefundBean trBean = tmpMap.get(recordNO);
				if(trBean==null){
					trBean = new TicketRefundBean();
					trBean.setTfdMxMap(new ArrayList<Map<String,String>>());
					trBean.setTfdMap(new HashMap<String, String>());
					trBean.getTfdMap().putAll(tpdMap);
					tmpMap.put(recordNO, trBean);
				}
				/**
				 * 我们系统
				 * 1001501 "客人自愿退票，按客规收取手续费"
				 * 1001502 "因航班取消延误，申请全退"
				 * 1001503 "升舱换开，申请全退"
				 * 1001504 "名字错换开重出，申请全退"
				 * 1001505 "客人因病无法乘机，申请全退"
				 * 1001506 "其它退票情况"
				 */
				String xs_sfzytp = "1";
				String xs_tply = "1001501";
				if("自愿".equals(refundType)){
					xs_sfzytp = "0";
					xs_tply = "1001501";
				}else{
					xs_sfzytp = "1";
					xs_tply = "1001506";
				}
				trBean.getTfdMap().put("XS_SFZYTP", xs_sfzytp); //0自愿  1非自愿
				trBean.getTfdMap().put("XS_TPLY", xs_tply); //退票原因
				trBean.getTfdMap().put("CG_TPLY", xs_tply); //退票原因
				trBean.getTfdMap().put("XS_TPBZ", refundType); //退票原因
				trBean.getTfdMap().put("HC",travelRange);
				trBean.getTfdMap().put("XS_HBH",flight);
				trBean.getTfdMap().put("XS_PNR_NO",recordNO);
				
				
				Double refundRate =  NumberUtils.toDouble(oneRDI.elementText("RefundRate").trim(), 0.0);//退票费率
				Double refundFee = NumberUtils.toDouble(oneRDI.elementText("RefundFee").trim(), 0.0);//携程 退票手续费
				Double PrintPrice = NumberUtils.toDouble(oneRDI.elementText("PrintPrice").trim(), 0.0);//携程 退票手续费
				Double modifiedRefundFee = NumberUtils.toDouble(oneRDI.elementText("ModifiedRefundFee").trim(), 0.0);//携程 修改后的退票费
				Double auditedRefundFee = NumberUtils.toDouble(oneRDI.elementText("AuditedRefundFee").trim(), 0.0);//携程 审核后的退票费

				String memo = oneRDI.elementText("Memo");
				Map<String,String> tpdMxMap = new HashMap<String, String>();
				tpdMxMap.put("CJR",passengerName);
				tpdMxMap.put("TKNO", AirLineCode+"-"+ticketNO);
				tpdMxMap.put("WBSXH", sequence);
				tpdMxMap.put("YTPF", refundFee+"");
				tpdMxMap.put("XS_TPSXF", refundFee+"");
				tpdMxMap.put("XS_TKJE", PrintPrice+"");
				tpdMxMap.put("XS_TPFL", refundRate+"");
				trBean.getTfdMxMap().add(tpdMxMap);
			}	
		}
		for(String key : tmpMap.keySet()){
			list.add(tmpMap.get(key));
		}
		ptlb.add("本次扫描共"+list.size()+"个退废单");
		return list;
	}
	/**
	 * 解析退票确认接口
	 * @return
	 */
	public static boolean parseRefundConfirm(String resXml,JpPtLog ptlb){
		Document doc = null;
		try {
			doc = XmlUtils.getDocument(resXml, CtripGy_tf.encode);
			Element root = doc.getRootElement();
			Element header = root.element("Header");
			String resultCode = header.attributeValue("ResultCode");
			String resultMsg = header.attributeValue("ResultMsg");
			if(!"Success".equals(resultCode)){
				ptlb.add("返回的XML中状态为失败，错误信息"+resultMsg);
				return false;
			}
			String refundConfirmResult = root.element("OpenRefundConfirmResponseType").elementText("RefundConfirmResult");
			if("Success".equals(refundConfirmResult)){
				return true;
			}
			String refundConfirmDescription = root.element("OpenRefundConfirmResponseType").elementText("RefundConfirmDescription");
			ptlb.add("退票确认接口返回失败，失败描述:"+refundConfirmDescription);
			return false;
		} catch (Exception e) {
			ptlb.add("解析返回的XML失败，错误信息");
		}
		return false;
	}
	/**
	 * 解析退票费用确认接口
	 * @return
	 */
	public static Map<String,String> parseRefundFeeConfirm(String resXml,JpPtLog ptlb){
		Document doc = null;
		Map<String,String> retMap = new HashMap<String, String>();
		try {
			doc = XmlUtils.getDocument(resXml, CtripGy_tf.encode);
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
			Element orcResponseType = root.element("OpenRefundConfirmResponseType");
			String refundConfirmResult = orcResponseType.elementText("RefundConfirmResult");
			if("Success".equals(refundConfirmResult)){
				ptlb.add("退票费用确认接口返回成功。");
				retMap.put("status", "true");
				return retMap;
			}
			String refundConfirmDescription = orcResponseType.elementText("RefundConfirmDescription");
			ptlb.add("退票费用确认接口返回失败，失败描述:"+refundConfirmDescription);
			retMap.put("status", "false");
			retMap.put("message", "退票费用确认接口返回失败，失败描述:"+refundConfirmDescription);
		} catch (Exception e) {
			e.printStackTrace();
			retMap.put("status", "false");
			retMap.put("message", "解析退票费用确认接口返回报错，错误原因:"+e.toString());
		}
		return retMap;
	}
}