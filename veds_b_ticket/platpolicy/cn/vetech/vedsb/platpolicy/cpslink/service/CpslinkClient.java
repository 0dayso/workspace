package cn.vetech.vedsb.platpolicy.cpslink.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.HttpClientUtil;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.platpolicy.cpslink.DsRequest;
import cn.vetech.vedsb.platpolicy.cpslink.DsResponse;
import cn.vetech.vedsb.utils.SysUtils;

@Service
public class CpslinkClient {
	private String charset = "UTF-8";
	private static int connectTimeout = 10000, soTimeout = 60000;

	public <T> T send(DsRequest request, Class<? extends DsResponse> T) throws Exception {
		String xml = send(request);
		return (T) XmlUtils.fromXml(xml, T);
	}

	public String send(DsRequest request) throws Exception {
		String linkUrl = SysUtils.getPlatLinkCpUrl() + "/openapi/platbuyer/platorder";
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", request.toString());
		return HttpClientUtil.doPost(linkUrl, params, charset, connectTimeout, soTimeout);
	}
}
