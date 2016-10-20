package org.vetech.core.business.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.vetech.core.business.pid.api.pidgl.Yhz;
import org.vetech.core.business.pid.api.pidgl.YhzServiceImpl;


 


public class GroupListTag extends BodyTagSupport{
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private String var;//为返回值取一个名字

	private String shbh;
	
	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}

	@Override
	public int doEndTag() throws JspException {
		return (EVAL_PAGE);
	}

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		YhzServiceImpl gs= applicationContext.getBean(YhzServiceImpl.class);
		
		List list = new ArrayList<Yhz>();
		list = gs.getAllGroups(shbh,"WEB01",true);
		request.setAttribute(var, list);
		return (EVAL_BODY_INCLUDE);
	}

	@Override
	public void release() {
		var = null;
	}
	
	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}


}
