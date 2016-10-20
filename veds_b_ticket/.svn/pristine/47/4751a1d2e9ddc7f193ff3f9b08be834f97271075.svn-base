package cn.vetech.web.webcontent;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.modules.utils.Encodes;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.utils.WebUtils;

import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.service.jpddsz.JpDdszServiceImpl;

/**
 *   session key
 * 
 * @author   http://114.55.151.86:10090/veds_ticket/webcontent/taobaosessionkey/wdsession
 */
@Controller
public class TaobaoSessionKey {
	private static Logger logger = LoggerFactory.getLogger(TaobaoSessionKey.class);
	@Autowired
	private JpDdszServiceImpl jpDdszServiceImpl;

	@RequestMapping
	public void wdsession(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.error("淘宝签约返回" + WebUtils.getRequest(request));
		String top_appkey = request.getParameter("top_appkey");
		String top_parameters = request.getParameter("top_parameters");
		String top_session = request.getParameter("top_session");
		String top_sign = request.getParameter("top_sign");

		if (StringUtils.isBlank(top_appkey) || StringUtils.isBlank(top_parameters) || StringUtils.isBlank(top_session) || StringUtils.isBlank(top_sign)) {
			return;
		}

		String parameters = new String(Encodes.decodeBase64(top_parameters), "GBK");
		String visitor_nick = StringUtils.trimToEmpty(VeStr.getParam(parameters, "visitor_nick")).toUpperCase();
		String expires_in = StringUtils.trimToEmpty(VeStr.getParam(parameters, "expires_in"));
		String d = VeDate.getPreSec(VeDate.getStringDate(), NumberUtils.toInt(expires_in));
		logger.error("淘宝签约返回,失效日期是" + d);
		JpDdsz jpDdsz = new JpDdsz();
		jpDdsz.setDdJkzh(top_appkey);
		List<JpDdsz> list = jpDdszServiceImpl.queryList(jpDdsz);
		if (list != null && list.size() > 0) {
			if (list.size() == 1) {
				JpDdsz one = list.get(0);
				JpDdsz t = new JpDdsz();
				t.setWdid(one.getWdid());
				t.setDdAqm1(top_session);
				t.setDdAqm3(d);
				jpDdszServiceImpl.updateSelective(t);
				logger.error("淘宝签约找到一个对象网店id" + one.getWdid() + ",网店名称" + one.getWdmc());
			} else {
				for (int i = 0; i < list.size(); i++) {
					JpDdsz one = list.get(i);
					String gqdlm = StringUtils.trimToEmpty(one.getGqdlzh()).toUpperCase();
					String wdmc = StringUtils.trimToEmpty(one.getWdmc()).toUpperCase();
					if (gqdlm.equals(visitor_nick) || wdmc.equals(visitor_nick)) {
						JpDdsz t = new JpDdsz();
						t.setWdid(one.getWdid());
						t.setDdAqm1(top_session);
						t.setDdAqm3(d);
						jpDdszServiceImpl.updateSelective(t);
						logger.error("淘宝签约找到多个对象网店id" + one.getWdid() + ",网店名称" + one.getWdmc());
					}
				}
			}
		} else {
			logger.error("淘宝签约,通过top_appkey" + top_appkey + "没有找到数据");
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String top_parameters = "ZXhwaXJlc19pbj0zMTUzNjAwMSZpZnJhbWU9MSZyMV9leHBpcmVzX2luPTMxNTM2MDAxJnIyX2V4cGlyZXNfaW49MzE1MzYwMDEmcmVfZXhwaXJlc19pbj0wJnJlZnJlc2hfdG9rZW49NjEwMDQxN2ZhNTI1MTFjOGI4MzgyN2U3NDExMWQ3MmRlNzg4ZTdiNGYwZGRhZTMyNzA4MTUxNjM3JnRzPTE0NTA2ODExMDk0NzImdmlzaXRvcl9pZD0yNzA4MTUxNjM3JnZpc2l0b3Jfbmljaz03Nzg4x+W359DswLQmdzFfZXhwaXJlc19pbj0zMTUzNjAwMSZ3Ml9leHBpcmVzX2luPTMxNTM2MDAx";
		String parameters = new String(Encodes.decodeBase64(top_parameters), "GBK");
		System.out.println(parameters);
		System.out.println(VeStr.getParam(parameters, "visitor_nick"));
	}
}
