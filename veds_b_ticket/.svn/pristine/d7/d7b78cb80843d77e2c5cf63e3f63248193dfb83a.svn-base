package cn.vetech.vedsb.pay.cft;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzh;
import cn.vetech.vedsb.pay.ZfSign;
import cn.vetech.vedsb.pay.payUtil.B2bpayutil;
import cn.vetech.vedsb.utils.LogUtil;

public class CftPaySign implements ZfSign {
	
	private String partner = B2bpayutil.getPayparam("B2B_TENPAY_PARTNER");
	private String rsaprikey = B2bpayutil.getPayparam("B2B_TENPAY_RSAPRIKEY");
	private String rsapubkey = B2bpayutil.getPayparam("B2B_TENPAY_RSAPUBKEY");
	private String key = B2bpayutil.getPayparam("B2B_TENPAY_KEY");
	private String root = B2bpayutil.getPayparam("RTN_URL");

	public String sign(HttpServletRequest request, JpB2bZfzh jpB2bZfzh) {
		String return_url = "";
		// 签约后返回地址
		if (StringUtils.isNotBlank(root)) {
			return_url = root + "/webcontent/signReturn/returnResult";
		}

		String pathtmp = CftPaySign.class.getResource("/").getPath();
		String rsaPrivateKeyFile = pathtmp + rsaprikey;

		// 创建支付请求对象
		RequestHandler reqHandler = new RequestHandler(request, null);
		// 通信对象
		TenpayHttpClient httpClient = new TenpayHttpClient();
		// 应答对象
		ClientResponseHandler resHandler = new ClientResponseHandler();

		reqHandler.init();
		// 设置密钥
		// reqHandler.setKey(key);
		reqHandler.setRsaPrivateKeyFile(rsaPrivateKeyFile);
		reqHandler.setGateUrl("https://www.tenpay.com/cgi-bin/v4.0/DPayAgentAgreement.cgi");

		// -----------------------------
		// 设置支付参数
		// -----------------------------
		String payer = jpB2bZfzh.getZfzh();

		reqHandler.setParameter("partner", partner); // 合作方ID
		reqHandler.setParameter("payer", payer); // 代理人支付帐号
		reqHandler.setParameter("sp_user_id", "vetech");
		reqHandler.setParameter("return_url", return_url);
		// reqHandler.setParameter("notify_url", "http://127.0.0.1:8099/PNRSignNotifyUrl");
		reqHandler.setParameter("sign_contract_type", "1");
		reqHandler.setParameter("attach", jpB2bZfzh.getId()+"_"+ jpB2bZfzh.getShbh());
		reqHandler.setParameter("sp_user_name", "vetech");

		// 系统可选参数
		reqHandler.setParameter("sign_type", "RSA");
		reqHandler.setParameter("service_version", "1.0");
		reqHandler.setParameter("input_charset", "GBK");
		reqHandler.setParameter("sign_key_index", "1");

		// 请求的url
		String requestUrl = "";
		try {
			requestUrl = reqHandler.getRequestURL();
		} catch (UnsupportedEncodingException e) {
			LogUtil.getZfzhQy("zfzhqy").error("财付通获得签约地址失败:" + e.getMessage());
			e.printStackTrace();
		}

		// 获取debug信息,建议把请求和debug信息写入日志，方便定位问题
		String debuginfo = reqHandler.getDebugInfo();
		
		LogUtil.getZfzhQy("zfzhqy").error("财付通签约地址:" + requestUrl);
		LogUtil.getZfzhQy("zfzhqy").error("财付通签约信息:" + debuginfo);

		return requestUrl;
	}

	@Override
	public String searchsign(JpB2bZfzh jpB2bZfzh) throws Exception {
		
		String pathtmp = CftPaySign.class.getResource("/").getPath();
		String rsaPrivateKeyFile = pathtmp + rsaprikey;
		String payer = jpB2bZfzh.getZfzh();
		if (StringUtils.isNotBlank(jpB2bZfzh.getCzykz())) {
			payer = jpB2bZfzh.getCzykz();
		}

		// 财付通rsa公钥
		String rsaPublicKeyFile = pathtmp + rsapubkey;

		// 创建支付请求对象
		RequestHandler reqHandler = new RequestHandler(null, null);
		// 通信对象
		TenpayHttpClient httpClient = new TenpayHttpClient();
		// 应答对象
		// XMLClientResponseHandler resHandler = new XMLClientResponseHandler();
		ClientResponseHandler resHandler = new ClientResponseHandler();

		reqHandler.init();
		// 设置密钥
		// reqHandler.setKey(key);
		reqHandler.setRsaPrivateKeyFile(rsaPrivateKeyFile);
		reqHandler.setGateUrl("http://fast.pnr.tenpay.com/app/api/pnr/query_sign");
		// -----------------------------
		// 设置支付参数
		// -----------------------------
		reqHandler.setParameter("partner", partner); // 合作方ID

		reqHandler.setParameter("payer", payer); // 代理人支付帐号1301198212
		reqHandler.setParameter("sp_user_id", "");
		reqHandler.setParameter("sign_contract_type", "");
		reqHandler.setParameter("attach", "");

		// 系统可选参数
		reqHandler.setParameter("sign_type", "RSA");
		reqHandler.setParameter("service_version", "1.0");
		reqHandler.setParameter("input_charset", "GBK");
		reqHandler.setParameter("sign_key_index", "1");

		// 请求的url
		String requestUrl = reqHandler.getRequestURL();

		// 获取debug信息,建议把请求和debug信息写入日志，方便定位问题
		String debuginfo = reqHandler.getDebugInfo();

		LogUtil.getZfzhQy("zfzhqy").error("财付通查询签约地址:" + requestUrl);
		LogUtil.getZfzhQy("zfzhqy").error("财付通查询签约信息:" + debuginfo);
		httpClient.setReqContent(requestUrl);

		if (httpClient.call()) {
			String rescontent = httpClient.getResContent();
			LogUtil.getZfzhQy("zfzhqy").error("财付通查询签约返回:" + rescontent);
			// 解析返回的结果
			resHandler.setContent(rescontent);
			// resHandler.setKey(key);
			resHandler.setRsaPublicKeyFile(rsaPublicKeyFile);
			// 获取返回参数
			String retcode = resHandler.getParameter("retcode");

			String sign_contract_state = resHandler.getParameter("sign_contract_state");

			// 判断签名及结果
			if (resHandler.isTenpaySign() && "0".equals(retcode) && "1".equals(sign_contract_state)) {
				return "T";
			} else {
				// 错误时，返回结果未签名，记录retcode、retmsg看失败详情。
				LogUtil.getZfzhQy("zfzhqy").error("验证签名失败或业务错误");
				LogUtil.getZfzhQy("zfzhqy").error("retcode:" + resHandler.getParameter("retcode") + " retmsg:"
						+ resHandler.getParameter("retmsg"));
			}
		} else {
			LogUtil.getZfzhQy("zfzhqy").error("后台调用通信失败");
			LogUtil.getZfzhQy("zfzhqy").error("call err:" + httpClient.getErrInfo() + httpClient.getResponseCode());
		}
		return "F";
	}

	@Override
	public String unsign(JpB2bZfzh jpB2bZfzh) {
		
		return null;
	}

	@Override
	public String updatesign(HttpServletRequest request, JpB2bZfzh jpB2bZfzh) {

		return null;
	}
}
