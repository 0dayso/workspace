package cn.vetech.vedsb.common.tag;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.modules.utils.WebUtils;

import cn.vetech.vedsb.common.entity.Shmkb;
import cn.vetech.vedsb.common.entity.Shmkgn;

/**
 * 显示帮助信息和BUG提报和推送信息
 * 
 * @author zhanglei
 * 
 */
public class BHelpTag extends BodyTagSupport {
	private String var;
	
	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpServletResponse response =  (HttpServletResponse)pageContext.getResponse();
		String requestUrl = request.getRequestURI();
		if (requestUrl.indexOf("?") > -1) {
			requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
		}
		if (requestUrl.indexOf("#") > -1) {
			requestUrl = requestUrl.substring(0, requestUrl.indexOf("#"));
		}
		if(requestUrl.indexOf("_")>-1){
			requestUrl = requestUrl.substring(0, requestUrl.indexOf("_"));
		}
		Map<String, Shmkb> allMkbMap = (Map<String, Shmkb>) request.getSession().getAttribute("bmklist");
		Cookie cookie = WebUtils.getCookie(request, "BMKURL");
		Shmkb vemkb = null;
		Shmkgn currentvemkgn = null;
		boolean mk =false;
		boolean isGGUrl =false;
		if (allMkbMap != null && !allMkbMap.isEmpty()) {//先判断请求的URL是不是公共模块的URL，如果是说明登记了，不需要后面的判断了
			vemkb = allMkbMap.get("/vedsb/main/index");
			if(vemkb!=null){
				for(Shmkgn vemkgn : vemkb.getShmkgnList()){
					if(requestUrl.equals(vemkgn.getGnurl())){
						if ("1".equals(vemkgn.getSfxsbzxx())) {
							currentvemkgn = vemkgn;
						}
						isGGUrl = true; //公共的功能
						break;
					}
				}
			}
			
		}
		//不是公共的URL，那么就去取其他模块的URL
		if (!isGGUrl && cookie != null && StringUtils.isNotBlank(cookie.getValue())) {
			if (allMkbMap != null && !allMkbMap.isEmpty()) {
				vemkb = allMkbMap.get(requestUrl);
				if (vemkb == null) {// 说明请求的URL不是模块URL,那么就是功能URL
					String mkurl = cookie.getValue();// 从cookie中获取当前模块URL
					vemkb = allMkbMap.get(mkurl);
					if (vemkb == null) {
						return (EVAL_BODY_INCLUDE);
					}
					for (Shmkgn vemkgn : vemkb.getShmkgnList()) {
						if (requestUrl.equals(vemkgn.getGnurl())) {
							if ("1".equals(vemkgn.getSfxsbzxx())) {
								currentvemkgn = vemkgn;
							}else{
								mk = true;
							}
							break;
						}
					}
				}
			}
		}
	
		if(!isGGUrl && vemkb!=null && !mk){ //模块URL,不包括公共的url
			StringBuffer html = new StringBuffer();
			html.append("<script type=\"text/javascript\">");
			
			html.append("$(function(){");
			html.append("var firebughtml = '<div class=\"firebug\">");
			html.append("<div class=\"bug_l\"></div>");
			html.append("<div class=\"bug_r\"><a href=\"#").append("\" onclick=\"openWin()\" class=\"help_i\">帮助中心</a><a href=\"#\" class=\"bug_i\">问题提交</a><a href=\"#\" class=\"news_i\">系统消息</a><a href=\"#\" class=\"news_hover\">系统消息</a></div>");
			
			html.append("</div>';\n");
			html.append("$(\"body\").prepend(firebughtml)\n");
			html.append("})");
			html.append("\n");	
			html.append("function openWin(){");
			html.append("var url='/vedsb/shmkbz/shmkbz/findMkbz?mkbh=").append(vemkb.getBh()).append("';");
			html.append("var iWidth=500;");
			html.append("var iHeight=600;");
			html.append("var iTop = (window.screen.availHeight - 30 - iHeight) / 2;");
			html.append("var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;");
			html.append("window.open(url, name, 'height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no','_blank');");
			html.append("}");
			html.append("</script>");
			request.setAttribute(var, html.toString());
		}else if(vemkb!=null && currentvemkgn!=null){ //功能URL
			StringBuffer html = new StringBuffer();
			html.append("<script type=\"text/javascript\">");
			
			html.append("$(function(){");
			html.append("var firebughtml = '<div class=\"firebug\">");
			html.append("<div class=\"bug_l\"></div>");
			html.append("<div class=\"bug_r\"><a href=\"#").append("\" onclick=\"openWin()\" class=\"help_i\">帮助中心</a><a href=\"#\" class=\"bug_i\">问题提交</a><a href=\"#\" class=\"news_i\">系统消息</a><a href=\"#\" class=\"news_hover\">系统消息</a></div>");
			html.append("</div>';\n");
			html.append("$(\"body\").prepend(firebughtml)\n");
			html.append("})");
			html.append("\n");
			html.append("function openWin(){");
			html.append("var url='/vedsb/shmkbz/shmkbz/findMkgnbz?mkbh=").append(vemkb.getBh()).append("&gnbh=").append(currentvemkgn.getBh()).append("';");
			html.append("var iWidth=500;");
			html.append("var iHeight=600;");
			html.append("var iTop = (window.screen.availHeight - 30 - iHeight) / 2;");
			html.append("var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;");
			html.append("window.open(url, name, 'height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');");
			html.append("}");
			html.append("</script>");
			request.setAttribute(var, html.toString());
		}
		  
		return (EVAL_BODY_INCLUDE);
	}

}
