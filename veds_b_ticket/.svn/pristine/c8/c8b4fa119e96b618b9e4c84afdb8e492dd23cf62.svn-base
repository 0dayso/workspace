package cn.vetech.vedsb.specialticket.service;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.common.entity.base.Vecsb;
import cn.vetech.vedsb.common.service.base.VecsbServiceImpl;
import cn.vetech.vedsb.specialticket.request.KwRequest;
import cn.vetech.vedsb.specialticket.response.KwResponse;
@Service
public class KwClient {
	@Autowired
	private VecsbServiceImpl vecsbServiceImpl; 
	private String charset = "UTF-8";
	private static int connectTimeout = 12000;
	@SuppressWarnings("unchecked")
	public <T> T send(KwRequest request,String method,String compid,Class<? extends KwResponse> T) throws Exception{
		String xml= XmlUtils.toXmlWithHead(request, charset);
		String returnXml=send(method, compid, xml);
		return (T) XmlUtils.fromXml(returnXml, T);
	}
	/**
	 * 调用直K系统接口公用方法
	 * @throws Exception 
	 */
	public String send(String method, final String compid, String xml) throws Exception {
		Vecsb vecsb = new Vecsb();
		vecsb.setBh("1004");
		vecsb = this.vecsbServiceImpl.getVecsbByBh(vecsb);
		String serviceURL = vecsb.getCsz1();
		java.net.URL url = new java.net.URL(serviceURL);
		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		Call call=(Call) service.createCall();
		call.setTargetEndpointAddress(url);
		call.setOperationName(new QName("http://www.vetech.com", method));
		call.addParameter("compid", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("xml", XMLType.XSD_STRING, ParameterMode.IN);
		call.setUseSOAPAction(true);
		call.setTimeout(connectTimeout);
		call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
		call.setSOAPActionURI(method);
		return (String) call.invoke(new Object[] { compid, xml });
	}
}
