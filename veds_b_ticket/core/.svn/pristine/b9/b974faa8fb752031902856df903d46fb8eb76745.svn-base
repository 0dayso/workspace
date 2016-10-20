package org.vetech.core.business.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.vetech.core.business.cache.CsbCacheService;
import org.vetech.core.business.cache.VecsbCache;


/**
 * 参数表标签
 * 根据编号直接返回参数表对象,没有取到则返回空对象
 * @author 李善良
 *
 */
@Component
public class ACsbTag extends BodyTagSupport{
	
	
	private static final long serialVersionUID = 7978558243892443122L;

	private String bh;//参数编号

	private String var;//为返回值取一个名字

	@Override
	public int doEndTag() throws JspException {
		return (EVAL_PAGE);
	}

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		CsbCacheService csbCache = applicationContext.getBean(CsbCacheService.class);
		if(StringUtils.isNotBlank(bh)){
			try {
				VecsbCache resIp = (VecsbCache)csbCache.get(bh);
				if(null != resIp){
					request.setAttribute(var, resIp);
				}else{
					resIp = new VecsbCache();
					request.setAttribute(var, resIp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return (EVAL_BODY_INCLUDE);
	}

	@Override
	public void release() {
		bh = null;
		var = null;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

}
