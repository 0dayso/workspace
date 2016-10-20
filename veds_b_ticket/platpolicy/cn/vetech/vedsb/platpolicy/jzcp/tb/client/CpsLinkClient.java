package cn.vetech.vedsb.platpolicy.jzcp.tb.client;
import java.util.HashMap;
import java.util.Map;

import org.vetech.core.modules.utils.HttpClientUtil;
import org.vetech.core.modules.utils.MD5Tool;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;

/**
 *
 * @author liunansheng
 *C站代购接口对接
 */
public class CpsLinkClient {
	/**
	 * @description:访问tps所有请求通过veopen过滤	
	 * @author:longm
	 * @date:2015-8-28 下午03:21:40
	 * businessNo:SYSCB
	 * key:63a606ce83247724e86056ad3d1adfcc
	 * http://veopen.vetech.cn:40006/veopen
	 */
	public static String send(CpsLinkRequest request,JpPtzcZh ptzh) throws Exception {
	    //测试环境用
		// 签名:systemid+businessNo+userId+operateTime+key
		String url = ptzh.getUrl1();
	    request.setSystemId("VETECH");
	    request.setBusinessNo(ptzh.getUser1());
	    request.setUserid(ptzh.getUser2());
	    request.setOperateTime(VeDate.getStringDate());
	    String sign = MD5Tool.MD5Encode(request.getSystemId() + request.getBusinessNo() + request.getUserid()
	                + request.getOperateTime());
	    request.setSign(sign);
	    String data = XmlUtils.toXmlWithHead(request, null);
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("data", data);
	    return HttpClientUtil.doPost(url, params, null);
	   
	    
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
