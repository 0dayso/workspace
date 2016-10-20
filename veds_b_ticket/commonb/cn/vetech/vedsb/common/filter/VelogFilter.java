package cn.vetech.vedsb.common.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.WebUtils;

import cn.vetech.vedsb.common.entity.Shmkb;
import cn.vetech.vedsb.common.entity.Shmkgn;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.Velog;
import cn.vetech.vedsb.common.service.VelogServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

public class VelogFilter implements Filter {
	protected final Logger logger = LoggerFactory.getLogger(VelogFilter.class);
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		long filterTime = System.currentTimeMillis();
		HttpServletRequest httprequest = (HttpServletRequest) request;
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(httprequest.getSession().getServletContext());
		Shyhb shyhb = SessionUtils.getShshbSession(httprequest);
		String ip  = httprequest.getRemoteAddr();
		VelogServiceImpl velogServiceImpl = new VelogServiceImpl(applicationContext);
		Velog velog = velogServiceImpl.createVelog();
		String mkbh = "";
		String mkmc = "";
		String mkgnbh = "";
		String mkgnmc = "";
		velog.setQqip(ip);
		velog.setMkbh(mkbh);
		velog.setMkgnmc(mkgnmc);
		velog.setMkmc(mkmc);
		velog.setMkgnbh(mkgnbh);
		String requestUrl = httprequest.getRequestURI();
		
		if (shyhb == null || requestUrl.indexOf("/vedsb/sso") > -1 || requestUrl.indexOf("/static/") > -1) {
			chain.doFilter(request, response);
		} else {
			
			//通过URL找模块信息和功能信息
			Map<String, Shmkb>  allMkbMap = (Map<String, Shmkb>) httprequest.getSession().getAttribute("bmklist");
			if (requestUrl.indexOf("?") > -1) {
				requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
			}
			if (requestUrl.indexOf("#") > -1) {
				requestUrl = requestUrl.substring(0, requestUrl.indexOf("#"));
			}
			if(requestUrl.indexOf("_")>-1){
				requestUrl = requestUrl.substring(0, requestUrl.indexOf("_"));
			}
			Cookie cookie = WebUtils.getCookie(httprequest, "BMKURL");
			Shmkb vemkb = null;
			Shmkgn currentvemkgn = null;
			if (allMkbMap != null && !allMkbMap.isEmpty()) {//先判断请求的URL是不是公共模块的URL，如果是说明登记了，不需要后面的判断了
				vemkb = allMkbMap.get("/vedsb/main/index");
				if(vemkb!=null){
					for(Shmkgn vemkgn : vemkb.getShmkgnList()){
						if(requestUrl.equals(vemkgn.getGnurl())){
							currentvemkgn=vemkgn;
							break;
						}
					}
				}
				
			}
			if (currentvemkgn==null && cookie != null && StringUtils.isNotBlank(cookie.getValue())) {
				if (allMkbMap != null && !allMkbMap.isEmpty()) {
					vemkb = allMkbMap.get(requestUrl);
					if (vemkb == null) {// 说明请求的URL不是模块URL,那么就是功能URL
						String mkurl = cookie.getValue();// 从cookie中获取当前模块URL
						vemkb = allMkbMap.get(mkurl);
						if (vemkb != null) {
							for (Shmkgn vemkgn : vemkb.getShmkgnList()) {
								if (requestUrl.equals(vemkgn.getGnurl())) {
									currentvemkgn = vemkgn;
									break;
								}
							}
						}
					}
				}
			}
			if(vemkb!=null){
				velog.setMkbh(vemkb.getBh());
				velog.setMkmc(vemkb.getMc());
				
			}
			if(currentvemkgn!=null){
				velog.setMkgnmc(currentvemkgn.getGnmc());
				velog.setMkgnbh(currentvemkgn.getBh());
			}
			//=======================
			
			velog.setShbh(shyhb.getShbh());
			velog.setShmc(shyhb.getShshb().getMc());
			velog.setShjc(shyhb.getShshb().getJc());
			velog.setMk_url(requestUrl);
			try {
				chain.doFilter(request, response);
				long filterEndTime = System.currentTimeMillis();
				velog.setFhdatetime(VeDate.getStringDate());
				velog.setZxhs((filterEndTime - filterTime) / 1000.0 + "");
			} catch (Exception e) {
				velog.setStatus("0");
				throw new ServletException(e);
			} finally {
				try {
					velogServiceImpl.sendToVelog(velog, "add");
				} catch (Exception e) {
					logger.error("发送日志异常：",e);
				}
			}

		}
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
