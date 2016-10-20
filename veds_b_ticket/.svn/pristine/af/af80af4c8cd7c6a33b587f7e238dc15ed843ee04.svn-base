package cn.vetech.vedsb.platpolicy.jzcp.b2c.client;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.vetech.core.modules.utils.HttpClientUtil;
import org.vetech.core.modules.utils.MD5Tool;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.pay.payUtil.B2bpayutil;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.bean.Segment;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.request.B2cCheckPendingOrderRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.request.B2cGetSecretKeyRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.request.B2cOrderRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.request.OrderStatusRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.request.SegmentRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.response.B2cCheckPendingOrderResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.response.B2cGetSecretKeyResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.response.B2cOrderInfoResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.response.B2cSubmitOrderResponse;

/**
 *
 * @author liunansheng
 *C站代购接口对接
 */
public class VetpsPurchasClient {
	public static B2cSubmitOrderResponse b2cSubmitOrder(B2cOrderRequest request,JpPtzcZh ptzh,Logger logger) throws Exception{
		String returnXml=send(request, "b2cSubmitOrder",ptzh,logger);
		return (B2cSubmitOrderResponse) XmlUtils.fromXml(returnXml, B2cSubmitOrderResponse.class);
	}
	public static B2cGetSecretKeyResponse b2cGetSecretKey(B2cGetSecretKeyRequest request,JpPtzcZh ptzh) throws Exception{
		String returnXml= send(request, "b2cGetSecretKey",ptzh,null);
		return (B2cGetSecretKeyResponse) XmlUtils.fromXml(returnXml, B2cGetSecretKeyResponse.class);
	}
	public static B2cCheckPendingOrderResponse b2cCheckPendingOrder(B2cCheckPendingOrderRequest request,JpPtzcZh ptzh) throws Exception{
		String returnXml= send(request, "b2cCheckPendingOrder",ptzh,null);
		return (B2cCheckPendingOrderResponse) XmlUtils.fromXml(returnXml, B2cCheckPendingOrderResponse.class);
	}
	public static Segment getSegment(SegmentRequest request, JpPtzcZh ptzh,Logger logger) throws Exception{
	    String returnXml= send(request, "getSegment",ptzh,logger);
	    Segment segment = (Segment)XmlUtils.fromXml(returnXml, Segment.class);
	    return segment;
	}
	
	/** 
     * 官网代购订单信息
	 * @param request
	 * @param t_ptzc_zh
	 * @return
	 * @throws Exception [参数说明]
	 * 
	 * @return B2cOrderInfoResponse [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static B2cOrderInfoResponse getOrderStatus(OrderStatusRequest request, JpPtzcZh ptzh) throws Exception {
        String returnXml = send(request, "b2cGetOrderInfo", ptzh,null);
        B2cOrderInfoResponse orderResponse = (B2cOrderInfoResponse) XmlUtils.fromXml(returnXml, B2cOrderInfoResponse.class);
        return orderResponse;
    }
	
	/**
	 * @description:访问tps所有请求通过veopen过滤	
	 * @author:longm
	 * @date:2015-8-28 下午03:21:40
	 * businessNo:SYSCB
	 * key:63a606ce83247724e86056ad3d1adfcc
	 * http://veopen.vetech.cn:40006/veopen
	 */
	public static String send(VetpsPurchasRequest request,String service,JpPtzcZh ptzh,Logger logger) throws Exception {
	    //测试环境用
	    request.setSystemId("VETECH");
	    request.setBusinessNo(ptzh.getUser1());
	    request.setOperateTime(VeDate.getStringDate());
//	    request.setService(service);
        String sign = MD5Tool.MD5Encode(request.getSystemId() + request.getBusinessNo() + request.getUserid()
                + request.getOperateTime());
        request.setSign(sign);
	    String data = XmlUtils.toXmlWithHead(request, null);
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("data", data);
	   
//	    String url = "http://192.168.110.99/openapi/purchas/b2corder/"+service;
	    String url = B2bpayutil.getPayparam("VETPS_URL") + "/openapi/purchas/b2corder/" + service;
	    if(logger!=null){
	    	logger.error("请求["+service+"]接口:"+url+",参数:"+XmlUtils.toXml(request));
	    }
	    String rtnxml = HttpClientUtil.doPost(url, params, "utf-8");
	    if(logger!=null){
	    	logger.error("["+service+"]接口返回:"+rtnxml);
	    }
	    return rtnxml;
	   
	    
	    //正式环境用
//		String systemId = "VETECH";
//		String businessNo = StringUtils.trimToEmpty(t_ptzc_zh.getUser1());
//        String userid = request.getUserid();//操作员id
//        String url = StringUtils.trimToEmpty(t_ptzc_zh.getUrl1());
//        String key = StringUtils.trimToEmpty(t_ptzc_zh.getPwd1());
//        
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
//        String operateTime = sdf.format(new Date());
//        String sign = AirsUtils.MD5Encode(systemId + businessNo + userid + operateTime + key);
//        
//        request.setBusinessNo(businessNo);
//        request.setOperateTime(operateTime);
//        request.setSign(sign);
//        request.setSystemId(systemId);
//		request.setService(service);
//		
//		String data = XmlUtils.toXmlWithHead(request, null);
//		NameValuePair nameValuePair = new NameValuePair("data", data);
//		String result = sendHttpClient(url, new NameValuePair[] {nameValuePair}, null);
//		return result;
	}

}
