package cn.vetech.vedsb.platpolicy.cps.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.HttpClientUtil;
import org.vetech.core.modules.utils.MD5Tool;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.platpolicy.cps.request.CpsAccount;
import cn.vetech.vedsb.platpolicy.cps.request.CpsRequest;
import cn.vetech.vedsb.platpolicy.cps.request.pay.IsAvailableRequest;
import cn.vetech.vedsb.platpolicy.cps.response.CpsResponse;

/**
 * 与CPS通讯
 * 
 * @author zhanglei
 *
 */

@Service
public class CpsInvoke {
	private String charset = "UTF-8";
	private static int connectTimeout = 10000, soTimeout = 60000;

	public <T> T send(CpsRequest request, CpsAccount account, Class<? extends CpsResponse> T) throws Exception {
		String retXml = send(request, account);
		T res = (T) XmlUtils.fromXml(retXml, T);
		return res;
	}

	public String send(CpsRequest request, CpsAccount account) throws Exception {
		account.setOperateTime(VeDate.getStringDate());
		String sign = MD5Tool.MD5Encode(account.getSystemId() + account.getBusinessNo() + StringUtils.trimToEmpty(account.getUserid()) + account.getOperateTime() + account.getPwd());
		account.setSign(sign);

		String data = XmlUtils.toXmlWithHead(request, charset);
		int ins = data.lastIndexOf("</");
		if (ins <= 0) {
			throw new Exception("请求参数转换错误");
		}
		// 插入账号签名信息
		data = data.substring(0, ins) + account.toXml() + "</request>";

		Map<String, String> params = new HashMap<String, String>();
		params.put("data", data);
		String retXml = HttpClientUtil.doPost(account.getUrl(), params, charset, connectTimeout, soTimeout);
		return retXml;
	}

	public static void main(String[] args) {
		IsAvailableRequest iar = new IsAvailableRequest();
		iar.setService("isAvailable");
		String data = XmlUtils.toXmlWithHead(iar, null);
		System.out.println(data);

		CpsAccount ca = new CpsAccount();
		ca.setBusinessNo("VETECH");
		System.out.println(ca.toXml());

		int ins = data.lastIndexOf("</");
		data = data.substring(0, ins) + ca.toXml() + "</root>";
		System.out.println(data);

	}
}
