package org.displaytag.tags;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.displaytag.util.DisplayUtils;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;
/**
 * 用于精准营销方案查询列表
 * 没有跳转用的页数和每页显示条数
 * @author yichen
 *
 */
public class JzyxPageTagOne extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7417312908928777099L;

	private final static String module = MPageTag.class.getName();

	public final static String URLNAME = "URL";

	public final static String COUNT = "COUNT";

	public final static String START = "START";

	public final static String ALLCOUNT = "ALLCOUNT";

	public final static String NEXTPAGE = "NEXTPAGE";

	public final static String DISP = "DISP";

	private boolean disp = false;

	private String actionFormName;

	private String displayCount = "5";

	private String exportUrl; // exportUrl定义一个页面的变量
	private String page;
	
	private String dtableid; // dtableid是display:table 的id

	private String params; // request的所有参数

	private String var;

	private String curl;

	private String pnot;

	private String align; // 分页条水平对齐 默认靠左

	private String divId; // 分页标签异步刷新
	
	private String callback; //异步刷新，翻页后执行的自动回调函数

	// --------------------------------------------------------- Public Methods
	
	public String getCallback() {
		return callback;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}
	
	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getCurl() {
		return curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getDtableid() {
		return dtableid;
	}

	public void setDtableid(String dtableid) {
		this.dtableid = dtableid;
	}

	public String getExportUrl() {
		return exportUrl;
	}

	public void setExportUrl(String exportUrl) {
		this.exportUrl = exportUrl;
	}

	/**
	 * Render the beginning of the hyperlink.
	 * 
	 * @exception JspException
	 *                if a JSP exception has occurred
	 */
	public int doStartTag() throws JspException {
		int[] onepages = { 10, 20, 30, 50, 100, 200 };
		// Generate the URL to be encoded
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpSession session = request.getSession();
		String pageUrl = page;
		pageUrl = pageUrl==null?"":pageUrl;
		if(pageUrl.startsWith("//")){
			pageUrl = StringUtils.substring(pageUrl, 1);
		}
		StringBuffer url = new StringBuffer(pageUrl);
		// --------------//
		if (StringUtils.isBlank(align)) {
			align = "left";
		}
		if (pageUrl.indexOf("?") < 0) {
			url.append("?");
		} else {
			url.append("&");
		}
		String param = getUri();
		if (StringUtils.isNotBlank(params)) {
			request.setAttribute(params, param);
		}
//		url = url.append(param);
		// 保存当前页面的url
		if (StringUtils.isNotBlank(curl)) {
			String _curl = url.toString();
			if (StringUtils.isNotBlank(request.getParameter("start"))) {
				_curl = _curl + "&start=" + request.getParameter("start");
			}
			if (StringUtils.isNotBlank(request.getParameter("count"))) {
				_curl = _curl + "&count=" + request.getParameter("count");
			}

			request.setAttribute(curl, _curl);
		}
		Page form = null;
		Object o = null;
		try {

			o = request.getAttribute(actionFormName);
			if (o == null) {
				o = session.getAttribute(actionFormName);
			}
			if (o == null)
				return (EVAL_BODY_INCLUDE);

			if (o instanceof Page) {
				form = (Page) o;
			} else if (o instanceof List) {
				List list = (List) o;
				form = new Page(1,list.size());
				form = new Page(1,list.size());
				form.setTotalCount(list.size());
				form.setList(list);
			}

			if (form == null || form.getTotalCount() <= 0) {
				return (EVAL_BODY_INCLUDE);
			}
			// throw new Exception();
		} catch (Exception e) {
			throw new JspException(" not found " + actionFormName);
		}
		PageHelper ph = new PageHelper();
		int pageNum = form.getPageNum();
		int start = ph.getStart(form.getPageNum(), form.getPageSize());
		long allCount = form.getTotalCount();
		int count = form.getPageSize();
		if (count == 0) {
			count = 10;
		}
		// url.append("count=").append(count);

		String nextPage = "";
		if ((allCount > (start + count)))
			nextPage = NEXTPAGE;

		int currentPage = 1;
		if (count > 0) {
			currentPage = (start / count) + 1;
		}
		if ((currentPage == 1) && (nextPage.length() == 0)) {
			pageContext.setAttribute(DISP, "off");
		} else
			pageContext.setAttribute(DISP, "on");
		long allPage = (allCount / count + (allCount % count > 0 ? 1 : 0));

		// 生成导出的url
		// exportUrl定义一个页面的变量，dtableid是display:table 的id
		if (StringUtils.isNotBlank(exportUrl) && StringUtils.isNotBlank(dtableid)) {
			request.setAttribute(exportUrl, param + "&start=0&count=" + allCount + "&" + DisplayUtils.export(dtableid));
		} else if (StringUtils.isNotBlank(exportUrl)) {
			request.setAttribute(exportUrl, param + "&start=0&count=" + allCount);
		}

		StringBuffer buf = new StringBuffer();
		buf.append("");
		buf.append("【共&nbsp;<b>").append(allCount).append("</b>&nbsp;条】");
		
		// 页数大于1的时候才能翻页
		if (allPage > 1) {
			url.append(fgf(url.toString())).append("count=" + count);
			if (start > 0) {
				if (StringUtils.isBlank(divId)) {
					buf.append("【<a href=\"###\" onclick=\"javascript:try{lockScreen('系统正在处理您的操作,请稍候!');}catch(e){}" +
							" var pageurl='" + url + "&start=" + (start - count) + "&opr=previous'; window.location.href=pageurl; \">前一页</a>");

				} else {
					buf.append("【<a href=\"###\" onclick=\"var pageurl='" + url + "&start="
							+ (start - count) + "&opr=previous';" + getAjaxFunStr() + "\">前一页</a>");
				}
			} else {
				buf.append("【前一页");
			}
			//buf.append(indexUri(start, count, allCount, url.toString()));
			buf.append("&nbsp;第"+currentPage+"页&nbsp;");
			if ((start + count) < allCount) {
				if (StringUtils.isBlank(divId)) {
					buf.append("<a href=\"###\" onclick=\"try{lockScreen('系统正在处理您的操作,请稍候!');}catch(e){}" +
							" var pageurl='" + url + "&start=" + (start + count) + "&opr=next'; window.location.href=pageurl; \">后一页</a>】");
				} else {
					buf.append("<a href=\"###\" onclick=\"var pageurl='" + url + "&start="
							+ (start + count) + "&opr=next';" + getAjaxFunStr() + ";\">后一页</a>】");
				}
			} else {
				buf.append("后一页】");
			}
		}
		buf.append(" 【共&nbsp;<b>" + allPage + "</b>&nbsp;页】");
//		if (sqlTime > 0) {
//			buf.append("【本次查询总耗时：<b><i style=\"color:#009900\">").append(sqlTime).append("</i></b>&nbsp;毫秒】");
//		}
		String tbegin = "<table border=\"0\" class=\"pagination\"> <tr> <td align=\"" + align + "\">";
		String tend = "&nbsp;</td> </tr></table>";
		if (StringUtils.isNotBlank(var)) {
			request.setAttribute(var, tbegin + buf.toString() + tend);
		}
		// Evaluate the body of this tag
		return (EVAL_BODY_INCLUDE);

	}

	/**
	 * Render the end of the hyperlink.
	 * 
	 * @exception JspException
	 *                if a JSP exception has occurred
	 */
	public int doEndTag() throws JspException {

		return (EVAL_PAGE);

	}

	/**
	 * Release any acquired resources.
	 */
	public void release() {

		super.release();

	}

	public String getActionFormName() {
		return actionFormName;
	}

	public void setActionFormName(String actionFormName) {
		this.actionFormName = actionFormName;
	}

	@SuppressWarnings("unused")
	private void output(String s) throws JspException {
		JspWriter writer = pageContext.getOut();
		String dispStrs = (String) pageContext.getAttribute(MPageTag.DISP);
		if ((dispStrs != null) && (!dispStrs.equals(""))) {
			if (dispStrs.equals("on"))
				disp = true;
			else if (dispStrs.equals("off"))
				disp = false;
		}
		try {
			disp = true;
			if (disp)
				writer.print(s);
		} catch (IOException e) {
			throw new JspException("NextTag error");
		}

	}

	private String getUri() {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		Enumeration em = request.getParameterNames();
		StringBuffer uri = null;
		if (StringUtils.isBlank(pnot)) {
			pnot = "";
		} else {
			pnot = pnot + ",";
		}

		if (em != null) {
			while (em.hasMoreElements()) {
				String name = (String) em.nextElement();
				if (name != null) {
					String[] value = request.getParameterValues(name);
					if (value != null && value.length > 0) {
						for (int d = 0; d < value.length; d++) {
							// 字符编码专为utf 处理汉字翻页时的问题
							try {
								String valueone = URLEncoder.encode(value[d], "UTF-8");
								if (StringUtils.isNotBlank(valueone) && !"start".equals(name) && !"count".equals(name)
										&& !(pnot.indexOf(name + ",") >= 0)) {
									if (uri == null) {
										uri = new StringBuffer();
										uri.append(name).append("=").append(valueone);
									} else {
										uri.append("&").append(name).append("=").append(valueone);
									}
								}

							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}
					}

				}
			}
		}
		if (uri == null) {
			return "";
		}
		return uri.toString();
	}

	private String indexUri(int start, int count, int allCount, String url) {

		StringBuffer buf = new StringBuffer(100);

		int numPages = 0;
		if (allCount != count) {
			numPages = (int) Math.ceil((double) allCount / (double) count);
		} else {
			numPages = 1;
		}

		// Calculate the starting point & end points (the count of pages to
		// display)
		int currentPage = 1;
		if (count > 0) {
			currentPage = (start / count) + 1;
		}

		if ((displayCount == null) || (displayCount.length() == 0))
			this.displayCount = "5"; // default 5
		int dispCount = Integer.parseInt(displayCount);
		int lo = currentPage - dispCount;
		if (lo <= 0) {
			lo = 1;
		}
		int hi = currentPage + dispCount;

		// print out a link to the first page if we're beyond that page
		if (lo > 2) {
			if (StringUtils.isBlank(divId)) {
				buf.append("<a href=\"###\" onclick=\"try{lockScreen('系统正在处理您的操作,请稍候!');}catch(e){};").append(url);
				buf.append("\" class=\"paginator_href\" >1</a> ... ");
			} else {
				buf.append("<a href=\"###\" onclick=\"var pageurl='" + url + "';" + getAjaxFunStr()
						+ "\"");
				buf.append("\" class=\"paginator_href\" >1</a> ... ");
			}
		}

		// Print the page numbers before the current page
		while (lo < currentPage) {
			if (StringUtils.isBlank(divId)) {
				buf.append("<a href=\"###\" onclick=\"").append("try{lockScreen('系统正在处理您的操作,请稍候!');}catch(e){};" +
						" var pageurl='" + url+ "&start=" + ((lo - 1) * count) + "'; window.location.href = pageurl;");
				buf.append("\" class=\"paginator_href\">");
			} else {
				buf.append("<a href=\"###\" onclick=\"var pageurl='" + url + "&start="
						+ ((lo - 1) * count) + "';" + getAjaxFunStr() + "\"");
				buf.append("\" class=\"paginator_href\">");
			}
			buf.append("<b>");
			buf.append(lo);
			buf.append("</b></a>&nbsp;");
			lo++;
		}

		// Print the current page
		buf.append("<b><span class=\"paginator_currentPage\">");
		if (StringUtils.isBlank(divId)) {
			buf.append("<input title='回车跳转' onkeydown=\"if(event.keyCode == 13){try{lockScreen('系统正在处理您的操作,请稍候!');}catch(e){}; var count=" + count + ";var h='" + url
					+ "&start='+(parseInt(this.value)-1)*parseInt(count);window.location.href=h}\" "
					+ "style='width:20px;height:16px;border:1px solid #ccc; font-size: 12px;"
					+ "font-weight: bold;' type='text' value='" + currentPage + "'>");
		} else {
			buf.append("<input title='回车跳转' onkeydown=\"if(event.keyCode == 13){var pageurl='" + url
					+ "&start='+(parseInt(this.value)-1)*parseInt(" + count + ");" + getAjaxFunStr()
					+ "}\" style='width:20px;height:16px;border:1px solid #ccc; "
					+ "font-size: 12px;font-weight: bold;' type='text' value='" + currentPage + "'>");
		}
		buf.append("</span></b>");

		currentPage++;

		// Print page numbers after the current page
		while ((currentPage <= hi) && (currentPage <= numPages)) {
			buf.append("&nbsp;");
			if (StringUtils.isBlank(divId)) {
				buf.append("<a href=\"###\" onclick=\"").append("javascript:try{lockScreen('系统正在处理您的操作,请稍候!');}catch(e){};" +
						" var pageurl='" + url+ "&start=" + ((currentPage - 1) * count) + "'; window.location.href = pageurl;");
			} else {
				buf.append("<a href=\"###\" onclick=\"var pageurl='" + url + "&start="
						+ ((currentPage - 1) * count) + "';" + getAjaxFunStr() + "\"");
			}
			buf.append("\" class=\"paginator_href\">");
			buf.append("<b>");
			buf.append(currentPage);
			buf.append("</b></a>");
			currentPage++;
		}

		if (currentPage <= numPages) {
			buf.append(" ... ");
			if (StringUtils.isBlank(divId)) {
				buf.append("<a href=\"").append(url);
				buf.append("&start=");
				buf.append(((numPages - 1) * count));
			} else {
				buf.append("<a href=\"###\" onclick=\"var pageurl='" + url + "&start="
						+ ((numPages - 1) * count) + "';" + getAjaxFunStr() + "\"");
			}
			buf.append("\" class=\"paginator_href\">");
			buf.append(numPages);
		}
		return buf.toString();
	}

	private String getAjaxFunStr() {
		StringBuilder str = new StringBuilder();
		str.append("try{lockScreen('系统正在处理您的操作,请稍候!');}catch(e){};new Ajax.Updater('" + divId + "',pageurl,{method: 'post',evalScripts: true,onComplete:function(){");
		if( StringUtils.isNotBlank(callback) ){
			str.append("try{").append(callback).append("();}catch(e){}");
		}
		str.append("try{unlockScreen()}catch(e){}}});");
		
		return str.toString();
	}
	
	private String fgf(String url) {
		if (url != null) {
			if (url.indexOf("?") < 0)
				return "?";
			else
				return "&";
		} else {
			return "";
		}
	}

	public String getPnot() {
		return pnot;
	}

	public void setPnot(String pnot) {
		this.pnot = pnot;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}
}
