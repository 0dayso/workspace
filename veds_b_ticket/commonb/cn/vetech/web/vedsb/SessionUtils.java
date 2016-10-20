package cn.vetech.web.vedsb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.vetech.vedsb.common.entity.Shyhb;

public class SessionUtils {

	/**
	 * 获取B系统登录用户
	 */
	public static Shyhb getShshbSession() {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		return (Shyhb) session.getAttribute("BUSER");
	}
	
	public static Shyhb getShshbSession(HttpServletRequest httprequest) {
		HttpSession session = httprequest.getSession();
		return (Shyhb) session.getAttribute("BUSER");
	}
}
