package cn.vetech.web.vedsb;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class BCookiesController {
	/**
	 * 设置当前模块编号到cookies中
	 * @param request
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping
	public @ResponseBody String setCurrentMkBh(HttpServletRequest request, HttpServletResponse httpServletResponse){
		//只设置真正的url 去除上下文 ctx
		String ctx = request.getContextPath();
		String mkurl = request.getParameter("mkurl");
		if (StringUtils.isNotBlank(ctx) && StringUtils.isNotBlank(mkurl)) {
			int p = mkurl.indexOf(ctx);
			if (p > 0) {
				mkurl = mkurl.substring(p + ctx.length());
			}
		}
		Cookie bhcookie = new Cookie("BMKURL",mkurl);
		bhcookie.setMaxAge(10 * 60 * 60);
		bhcookie.setPath("/");
		httpServletResponse.addCookie(bhcookie);
		httpServletResponse.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
		return "";
	}
	@RequestMapping
	public @ResponseBody
	String set(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		// 读cookie
		Cookie bhcookie = new Cookie("buserid", request.getParameter("buserid"));
		bhcookie.setMaxAge(10 * 60 * 60);
		bhcookie.setPath("/");
		httpServletResponse.addCookie(bhcookie);
		httpServletResponse.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
		request.getSession().removeAttribute("BUSER");
		return "";
	}
	@RequestMapping
	public @ResponseBody
	String del(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies && cookies.length > 0) {
			String cname = "";
			for (int i = 0; i < cookies.length; i++) {
				cname = cookies[i].getName();
				if ("buserid".equals(cname)) {
					cookies[i].setMaxAge(0);
					cookies[i].setPath("/");
					httpServletResponse.addCookie(cookies[i]);
					break;
				}
			}
		}
		request.getSession().invalidate();// 清除当前用户相关的session对象
		httpServletResponse.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
		return "";
	}
}
