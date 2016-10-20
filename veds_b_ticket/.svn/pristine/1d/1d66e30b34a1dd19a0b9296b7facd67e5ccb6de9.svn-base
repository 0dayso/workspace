package cn.vetech.vedsb.platpolicy.jzcp.b2b.client;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.vetech.core.modules.utils.HttpClientUtil;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.request.B2bRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.request.B2bStateRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.B2bLoginResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.B2bOrderResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.B2bPolicyResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.B2bStateResponse;
import cn.vetech.vedsb.utils.SysUtils;

/**
 *
 * @author liunansheng C站代购接口对接
 */
public class VetpsPurchasB2bClient {
	public static B2bPolicyResponse getB2bPolicy(B2bRequest request, String businessNo, String yhbh, Logger logger) throws Exception {
		String returnXml = send(request, businessNo, yhbh, "getB2bPolicy", logger);
		// String returnXml =
		// "<response><status>0</status><b2bPolicyBean><b2bpolicyid>0@@850.0@@866.0</b2bpolicyid><dlf>34.0</dlf><dlfl>0</dlfl><hfje>0.0</hfje><index>0</index><jcsyyfjcr>0.0</jcsyyfjcr><jcsyyfjet>0.0</jcsyyfjet><jcsyyfjhj>50.0</jcsyyfjhj><pjcr>0.0</pjcr><pjet>0.0</pjet><pjhj>850.0</pjhj><qtfl>4.0</qtfl><tgqsm></tgqsm><totalfee>866.0</totalfee><zcsm>明折明扣</zcsm></b2bPolicyBean></response>";
		B2bPolicyResponse response = (B2bPolicyResponse) XmlUtils.fromXml(returnXml, B2bPolicyResponse.class);
		return response;
	}

	/**
	 * 下单支付
	 * 
	 * @param request
	 * @param businessNo
	 * @param yhbh
	 * @param logger
	 * @return
	 * @throws Exception
	 */
	public static B2bOrderResponse b2bSubmitOrder(B2bRequest request, String businessNo, String yhbh, Logger logger) throws Exception {
		String returnXml = send(request, businessNo, yhbh, "b2bSubmitOrder", logger);
		B2bOrderResponse response = (B2bOrderResponse) XmlUtils.fromXml(returnXml, B2bOrderResponse.class);
		return response;
	}

	/**
	 * 状态列表
	 * 
	 * @param businessNo
	 * @param yhbh
	 * @param logger
	 * @return
	 * @throws Exception
	 */
	public static B2bStateResponse getB2bState(String businessNo, String yhbh, Logger logger) throws Exception {
		B2bStateRequest request = new B2bStateRequest();
		request.setBusinessNo(businessNo);
		request.setUserid(yhbh);
		String returnXml = send(request, businessNo, yhbh, "getB2bState", logger);
		B2bStateResponse response = (B2bStateResponse) XmlUtils.fromXml(returnXml, B2bStateResponse.class);
		return response;
	}

	/**
	 * 订单详细
	 * 
	 * @param businessNo
	 * @param yhbh
	 * @param outOrderNo
	 * @param logger
	 * @return
	 * @throws Exception
	 */
	public static B2bOrderResponse getB2bOrderInfo(String businessNo, String yhbh, String outOrderNo, Logger logger) throws Exception {
		if(StringUtils.isBlank(outOrderNo) ){
			throw new Exception("外部订单为空");
		}
		B2bStateRequest request = new B2bStateRequest();
		request.setBusinessNo(businessNo);
		request.setUserid(yhbh);
		request.setOutOrderNo(outOrderNo);
		String returnXml = send(request, businessNo, yhbh, "getB2bOrderInfo", logger);
		B2bOrderResponse response = (B2bOrderResponse) XmlUtils.fromXml(returnXml, B2bOrderResponse.class);
		return response;
	}

	public static B2bPolicyResponse getYzmImage(String businessNo, String yhbh, String outOrderNo, String hkgs, String hkgsusername, Logger logger) throws Exception {
		if(StringUtils.isBlank(hkgs)||StringUtils.isBlank(hkgsusername)){
			throw new Exception("航空公司或航空公司账号为空");
		}
		B2bRequest request = new B2bRequest();
		request.setBusinessNo(businessNo);
		request.setUserid(yhbh);
		request.setOutOrderNo(outOrderNo);
		request.setAirways(hkgs);
		request.setAirwaysAccount(hkgsusername);
		String returnXml = send(request, businessNo, yhbh, "getYzmImage", logger);
		B2bPolicyResponse response = (B2bPolicyResponse) XmlUtils.fromXml(returnXml, B2bPolicyResponse.class);
		return response;
	}

	public static B2bLoginResponse b2bLogin(String businessNo, String yhbh, String outOrderNo, String hkgs, String hkgsusername,String hsmm,String yzm, Logger logger)  throws Exception {
		if(StringUtils.isBlank(hkgs)||StringUtils.isBlank(hkgsusername)){
			throw new Exception("航空公司或航空公司账号为空");
		}
		if(StringUtils.isBlank(yzm)||StringUtils.isBlank(hsmm)){
			throw new Exception("密码或验证码为空");
		}
		B2bRequest request = new B2bRequest();
		request.setBusinessNo(businessNo);
		request.setUserid(yhbh);
		request.setOutOrderNo(outOrderNo);
		request.setAirways(hkgs);
		request.setAirwaysAccount(hkgsusername);
		request.setAirwaysPwd(hsmm);
		request.setYzm(yzm);
		String returnXml = send(request, businessNo, yhbh, "b2bLogin", logger);
		B2bLoginResponse response = (B2bLoginResponse) XmlUtils.fromXml(returnXml, B2bLoginResponse.class);
		return response;
	}
	
	public static B2bLoginResponse b2bLogout(String businessNo, String yhbh, String outOrderNo, String hkgs, String hkgsusername, Logger logger)  throws Exception {
		B2bRequest request = new B2bRequest();
		request.setBusinessNo(businessNo);
		request.setUserid(yhbh);
		request.setOutOrderNo(outOrderNo);
		request.setAirways(hkgs);
		request.setAirwaysAccount(hkgsusername);
		String returnXml = send(request, businessNo, yhbh, "b2bLogout", logger);
		B2bLoginResponse response = (B2bLoginResponse) XmlUtils.fromXml(returnXml, B2bLoginResponse.class);
		return response;
	}

	/**
	 * @description:访问tps所有请求通过veopen过滤
	 * @author:longm
	 * @date:2015-8-28 下午03:21:40 businessNo:SYSCB key:63a606ce83247724e86056ad3d1adfcc http://veopen.vetech.cn:40006/veopen
	 */
	private static String send(VetpsPurchasB2bRequest request, String businessNo, String yhbh, String service, Logger logger) throws Exception {
		int connectTimeout = 10000, soTimeout = 180000;
		// 测试环境用
		request.setSystemId("VETECH");
		request.setUserid(yhbh);
		request.setBusinessNo(businessNo);
		request.setOperateTime(VeDate.getStringDate());
		String sign = "empty";// MD5Tool.MD5Encode(request.getSystemId() + request.getBusinessNo() + request.getUserid() + request.getOperateTime());
		request.setSign(sign);
		request.setService(service);
		String data = XmlUtils.toXmlWithHead(request, null);
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", data);
		String httphost = SysUtils.getB2bCpUrl();
		// httphost = "http://127.0.0.1:8081";
		String url = httphost + "/openapi/b2b/execute";
		logger.error("B2B代购系统请求[" + service + "]接口:" + url + ",参数:" + XmlUtils.toXml(request));
		String renXml = HttpClientUtil.doPost(url, params, "UTF-8", connectTimeout, soTimeout);
		logger.error("B2B代购系统[" + service + "]接口返回:" + renXml);
		return renXml;

		// 正式环境用
		// String systemId = "VETECH";
		// String businessNo = StringUtils.trimToEmpty(t_ptzc_zh.getUser1());
		// String userid = request.getUserid();//操作员id
		// String url = StringUtils.trimToEmpty(t_ptzc_zh.getUrl1());
		// String key = StringUtils.trimToEmpty(t_ptzc_zh.getPwd1());
		//
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		// String operateTime = sdf.format(new Date());
		// String sign = AirsUtils.MD5Encode(systemId + businessNo + userid + operateTime + key);
		//
		// request.setBusinessNo(businessNo);
		// request.setOperateTime(operateTime);
		// request.setSign(sign);
		// request.setSystemId(systemId);
		// request.setService(service);
		//
		// String data = XmlUtils.toXmlWithHead(request, null);
		// NameValuePair nameValuePair = new NameValuePair("data", data);
		// String result = sendHttpClient(url, new NameValuePair[] {nameValuePair}, null);
		// return result;
	}

	public static void main(String[] args) throws IOException {
		String returnXml = FileUtils.readFileToString(new File("d:/new.xml"));
		B2bStateResponse response = (B2bStateResponse) XmlUtils.fromXml(returnXml, B2bStateResponse.class);

	}
}
