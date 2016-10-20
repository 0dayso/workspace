package cn.vetech.web.webcontent;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.modules.utils.MD5Tool;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.jpzwgl.JpTjsqServiceImpl;
import cn.vetech.vedsb.specialticket.response.KwStatusResponse;

@Controller
public class JpZwNoticeController {
	@Autowired
	private JpTjsqServiceImpl jpTjsqServiceImpl;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	private final static String SUCCESS = "1"; //成功状态
	private final static String FAILURE = "-1"; //失败状态
	
	public @ResponseBody String zwnotice(HttpServletRequest request){
		try {
			String zgs = request.getParameter("zgs");
			String xml = request.getParameter("xml");
			if(StringUtils.isBlank(zgs)){
				return renderXml(FAILURE, "zgs不能为空！");
			}
			if(StringUtils.isBlank(xml)){
				return renderXml(FAILURE, "xml不能为空！");
			}
			String method=XmlUtils.getNode(xml, "service");
			if("getPIDProcess".equals(method)){
				String error=checkSign(xml,zgs);
				if(StringUtils.isNotBlank(error)){
					return renderXml(FAILURE, error);
				}
				String pidmsg=getPIDProcess(zgs,xml);
				//转义pidmsg
				pidmsg = pidmsg.replaceAll("<", "&lt;");
				pidmsg = pidmsg.replaceAll(">", "&gt;");
				return renderXml(SUCCESS, pidmsg);
			}
			KwStatusResponse kwStatusResponse=(KwStatusResponse) XmlUtils.fromXml(xml, KwStatusResponse.class);
			jpTjsqServiceImpl.updateZwcg(zgs, kwStatusResponse);
			return renderXml(SUCCESS, "特价申请单追位成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return renderXml(FAILURE, "接口调用异常");
		}
	}
	/**
	 * 输出反馈
	 */
	private String renderXml(String code, String msg){
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<response>");
		sb.append("<code>").append(code).append("</code>");
		sb.append("<error>").append(msg).append("</error>");
		sb.append("</response>");
		return sb.toString();
	}
	/**
	 * 掉用接口检查PID
	 * @param zgs
	 * @param xml
	 * @return
	 */
	private String getPIDProcess(String zgs,String xml) throws Exception{
		String xmlString = "<INPUT><COMMAND>VEQUERYLIC</COMMAND><PARA><USER></USER></PARA></INPUT>";
		JpPz jppz = jpPzServiceImpl.getJpPzByShbh(zgs);
		if(jppz==null){
			return "【"+zgs+"】没有PID配置";
		}
		String url =url=jppz.getServerAddr()+":"+jppz.getServerPort();
		
		WebEtermService service = new WebEtermService("http://"+url);
		return service.generalCmdProcess(xmlString);
	}
	/**
	 * 验证sign是否正确
	 * @param xml
	 * @param zgs
	 * @return
	 */
	private String checkSign(String xml,String zgs) {
		try {
			String key = "VEQUERYLIC";
			String data = XmlUtils.getNode(xml, "sendtime");
			String xmlsign = XmlUtils.getNode(xml, "sign");
			String sign = MD5Tool.MD5Encode(zgs+data+key, "utf-8");
			if(StringUtils.isNotBlank(xmlsign) && xmlsign.equalsIgnoreCase(sign)){
				return "";
			}else {
				return "sign验证失败";
			}
		} catch (Exception e) {
			return "sign验证异常";
		} 
	}
}
