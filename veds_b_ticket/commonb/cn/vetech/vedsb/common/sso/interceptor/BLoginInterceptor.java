package cn.vetech.vedsb.common.sso.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.vetech.vedsb.common.entity.Shshb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.sso.BSsoService;
import cn.vetech.vedsb.common.sso.SsoUser;


public class BLoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private  BSsoService ssoService;
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
		long startTime = (Long)request.getAttribute("startTime");
		 
        long endTime = System.currentTimeMillis();
 
        long executeTime = endTime - startTime;
        request.setAttribute(request.getRequestURI()+"_executeTime",executeTime);
	}
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
		String ip = request.getRemoteAddr();
		SsoUser user = (SsoUser)request.getSession().getAttribute("BUSER");
		if(user==null){
			Shyhb user1 = new Shyhb();
			user1.setBh("CS02");
			user1.setShbh("HZKFZX");
			user1.setShbmid("XIAOXINGLDEPT");
			user1.setXm("胜意测试");
			user1.setShshb(new Shshb());
			request.getSession().setAttribute("BUSER", user1);
			return true;
		}
		if(user==null){
			String bh = getBh(request);
			if(StringUtils.isBlank(bh)){
				response.sendRedirect(ssoService.getLoginIp()+"/login");
				return false;
			}
			user = ssoService.get(bh);
			if(user==null){
				response.sendRedirect(ssoService.getLoginIp()+"/login");
				return false;
			}else{
				request.getSession().setAttribute("BUSER", user);
			}
		}
//		if(!"127.0.0.1".equals(ip) && !ip.equals(user.getIp())){
//			response.sendRedirect(ssoip+"/login");
//			return false;
//		}
		return true;
		
	}
	
	private String getBh(HttpServletRequest request){
		//读cookie    
		Cookie[] cookies = request.getCookies();  
		String name = "";    
		if(cookies!=null)    
		{    
		    String password = "";    
		    String option = "";    
		    for (int i = 0; i < cookies.length; i++)     
		    {    
		       Cookie c = cookies[i];         
		       if(c.getName().equalsIgnoreCase("buserid"))    
		       {    
		          name = c.getValue();    
		          break;
		        }
		    }
		 }  
		return name;
	}

}
