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

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.displaytag.util.DisplayUtils;
import org.displaytag.util.LookupUtil;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;

public class MPageTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7417312908928777099L;

	public final static String URLNAME = "URL";

	public final static String ALLCOUNT = "ALLCOUNT";

	public final static String NEXTPAGE = "NEXTPAGE";

	public final static String DISP = "DISP";

	private boolean disp = false;

	private String actionFormName;

	private String displayCount = "5";
	
	private String page;

	private String params; // request的所有参数

	private String var;

	private String curl;

	private String pnot;

	private String align; // 分页条水平对齐 默认靠左

	private String divId; // 分页标签异步刷新
	
	private String exportUrl; // exportUrl定义一个页面的变量

	private String dtableid; // dtableid是display:table 的id
	
	private String callback; //异步刷新，翻页后执行的自动回调函数

	// --------------------------------------------------------- Public Methods
	public String getCallback() {
		return callback;
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
	public String getDisplayCount() {
		return displayCount;
	}
	public void setDisplayCount(String displayCount) {
		this.displayCount = displayCount;
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
		url = url.append(param);
		// 保存当前页面的url
		if (StringUtils.isNotBlank(curl)) {
			String _curl = url.toString();
			if (StringUtils.isNotBlank(request.getParameter("pageNum"))) {
				_curl = _curl + "&pageNum=" + request.getParameter("pageNum");
			}
			if (StringUtils.isNotBlank(request.getParameter("pageSize"))) {
				_curl = _curl + "&pageSize=" + request.getParameter("pageSize");
			}
			request.setAttribute(curl, _curl);
		}
		Page page = null;
		Object o = null;
		try {
			if(StringUtils.isBlank(actionFormName)){
				throw new JspException(" not found " + actionFormName);
			}
			String[] arr={actionFormName};
			if(actionFormName.indexOf(".") >-1){
				arr=actionFormName.split("\\.");
			}
			o = request.getAttribute(arr[0]);
			if(o!=null){
				for(int i=1;i<arr.length;i++){
					o=LookupUtil.getBeanProperty(o, arr[i]);
				}
			}
			if (o == null) {
				o = session.getAttribute(actionFormName);
			}
			if (o == null)
				return (EVAL_BODY_INCLUDE);

			if (o instanceof Page) {
				page = (Page) o;
			} else if (o instanceof List) {
				List list = (List) o;
				page = new Page(1,list.size());
				page.setTotalCount(list.size());
				page.setList(list);
			}
			if (page == null || page.getTotalCount() <= 0) {
				return (EVAL_BODY_INCLUDE);
			}
			// throw new Exception();
		} catch (Exception e) {
			throw new JspException(" not found " + actionFormName);
		}
		PageHelper ph = new PageHelper();
		int pageNum=page.getPageNum();
		int start = ph.getStart(page.getPageNum(), page.getPageSize());
		long allCount = page.getTotalCount();
		int count = page.getPageSize();
		if (count == 0) {
			count = 10;
		}
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
			request.setAttribute(exportUrl, param + "&" + DisplayUtils.export(dtableid));
		} else if (StringUtils.isNotBlank(exportUrl)) {
			request.setAttribute(exportUrl, param);
		}
		StringBuffer buf = new StringBuffer();
		buf.append("");
		buf.append("<span>共&nbsp;<b>").append(allCount).append("</b>&nbsp;条,");

		buf.append(" 每页</span>");
		if (allCount > onepages[0]) {
			if (StringUtils.isBlank(divId)) {
				buf.append("<select  "
						+ "onchange=\"try{new VeLayer().load(); }catch(e){};var h='" + url
						+ fgf(url.toString())
						+ "';h=h+'pageSize='+this.options[this.selectedIndex].value;window.location.href=h;\">");
				// <a href='" + url + fgf(url.toString()) + "count=" + onepages[i] +
				// "'>"
			} else {
				buf.append("<select  onchange=\"var pageurl='" + url
						+ fgf(url.toString()) + "pageSize='+this.options[this.selectedIndex].value;" + getAjaxFunStr()
						+ "\">");
			}
			if (!ArrayUtils.contains(onepages, count)) {
				if (allCount >= count) {
					buf.append("<option value=\"" + onepages[0] + "\">" + count + "</option>");
				} else {
					buf.append("<option value=\"" + onepages[0] + "\">" + allCount + "</option>");
				}
			}
			for (int i = 0; i < onepages.length; i++) {
				if (count != onepages[i]) {
					buf.append("<option value=\"" + onepages[i] + "\">" + onepages[i] + "</option>");
				} else {
					buf.append("<option value=\"" + onepages[i] + "\" selected>" + onepages[i] + "</option>");
				}
			}
			buf.append("</select >");
		}
		if (allCount <= onepages[0]) {
			buf.append("<b>" + count + " </b> ");
		}
		// if (count == allCount) {
		// buf.append("<b>" + "全部" + " </b> ");
		// } else {
		// buf.append("<a href='" + url + fgf(url.toString()) + "count=" +
		// allCount + "'>" + "全部" + "</a>");
		// }
		buf.append("<span>&nbsp;条&nbsp;|&nbsp;</span>");
		// 页数大于1的时候才能翻页
		if (allPage > 1) {
			url.append(fgf(url.toString())).append("pageSize=" + count);
			if (pageNum > 1) {
				if (StringUtils.isBlank(divId)) {
					buf.append("<a href=\"###\" onclick=\"javascript:try{new VeLayer().load();}catch(e){}" +
							" var pageurl='" + url + "&pageNum=" + (pageNum - 1) + "'; window.location.href=pageurl; \">前一页</a>");

				} else {
					buf.append("<a href=\"###\" onclick=\"var pageurl='" + url + "&pageNum="
							+ (pageNum - 1) + "';" + getAjaxFunStr() + "\">前一页</a>");
				}
			} else {
				buf.append("<a  disabled style='background:#ECECEC;color:#fff'>前一页</a>");
			}
			buf.append(indexUri(start, count, allCount, url.toString()));
			if ((start + count-1) < allCount) {
				if (StringUtils.isBlank(divId)) {
					buf.append("<a href=\"###\" onclick=\"try{new VeLayer().load();}catch(e){}" +
							" var pageurl='" + url + "&pageNum=" + (pageNum + 1) + "'; window.location.href=pageurl; \">后一页</a>");
				} else {
					buf.append("<a href=\"###\" onclick=\"var pageurl='" + url + "&pageNum="
							+ (pageNum + 1) + "';" + getAjaxFunStr() + ";\">后一页</a>");
				}
			} else {
				buf.append("<a disabled style='background:#ECECEC;color:#fff'>后一页</a>");
			}
		}
		buf.append("<span>&nbsp;|&nbsp;</span>");
		 
		buf.append("<span>共&nbsp;");
		buf.append(allPage);
		buf.append("&nbsp;页</span>");
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
								if (StringUtils.isNotBlank(valueone) && !"pageNum".equals(name) && !"pageSize".equals(name)
										&& !(pnot.indexOf(name + ",") >= 0)) {
									if (uri == null) {
										uri = new StringBuffer();
										uri.append(name).append("=").append(valueone);
									} else {
										uri.append("&").append(name).append("=").append(valueone);
									}
								}else if(StringUtils.isBlank(valueone)){
									if (uri == null) {
										uri = new StringBuffer();
										uri.append(name).append("=").append("");
									} else {
										uri.append("&").append(name).append("=").append("");
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

	private String indexUri(int start, int count, long allCount, String url) {

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
				buf.append("<a href=\"###\" onclick=\"try{new VeLayer().load();}catch(e){};var pageurl='").append(url+"&pageNum=1';window.location.href = pageurl;");
				buf.append("\"  >1</a> ... ");
			} else {
				buf.append("<a href=\"###\" onclick=\"var pageurl='" + url + "';" + getAjaxFunStr()
						+ "\"");
				buf.append("\"  >1</a> ... ");
			}
		}

		// Print the page numbers before the current page
		while (lo < currentPage) {
			if (StringUtils.isBlank(divId)) {
				buf.append("<a href=\"###\" onclick=\"").append("try{new VeLayer().load();}catch(e){};" +
						" var pageurl='" + url+ "&pageNum=" + lo + "'; window.location.href = pageurl;");
				buf.append("\" >");
			} else {
				buf.append("<a href=\"###\" onclick=\"var pageurl='" + url + "&pageNum="
						+ lo+ "';" + getAjaxFunStr() + "\"");
				buf.append("\" >");
			}
			buf.append("");
			buf.append(lo);
			buf.append("</a>");
			lo++;
		}

		// Print the current page
		buf.append("<span>");
		if (StringUtils.isBlank(divId)) {
			buf.append("<input style='text-align:center;border:1px solid #76C6FD;'  title='回车跳转' onkeydown=\"if(event.keyCode == 13){try{new VeLayer().load();}catch(e){}; var count=" + count + ";var h='" + url
					+ "&pageNum='+parseInt(this.value);window.location.href=h}\" "
					+ " class='w35'"
					+ " type='text' value='" + currentPage + "'>");
		} else {
			buf.append("<input style='text-align:center;border:1px solid #76C6FD;' title='回车跳转' onkeydown=\"if(event.keyCode == 13){var pageurl='" + url
					+ "&pageNum='+parseInt(this.value);" + getAjaxFunStr()
					+ "}\" class='w35' "
					+ " type='text' value='" + currentPage + "'>");
		}
		buf.append("</span>");

		currentPage++;

		// Print page numbers after the current page
		while ((currentPage <= hi) && (currentPage <= numPages)) {
			if (StringUtils.isBlank(divId)) {
				buf.append("<a href=\"###\" onclick=\"").append("javascript:try{new VeLayer().load();}catch(e){};" +
						" var pageurl='" + url+ "&pageNum=" + currentPage + "'; window.location.href = pageurl;");
			} else {
				buf.append("<a href=\"###\" onclick=\"var pageurl='" + url + "&pageNum="
						+ currentPage + "';" + getAjaxFunStr() + "\"");
			}
			buf.append("\" class=\"paginator_href\">");
			buf.append("");
			buf.append(currentPage);
			buf.append("</a>");
			currentPage++;
		}

		if (currentPage <= numPages) {
			buf.append(" ... ");
			if (StringUtils.isBlank(divId)) {
				buf.append("<a href=\"###\" onclick=\"try{new VeLayer().load();}catch(e){};var pageurl='").append(url);
				buf.append("&pageNum=");
				buf.append(numPages);
				buf.append("';window.location.href = pageurl;");
			} else {
				buf.append("<a href=\"###\" onclick=\"var pageurl='" + url + "&pageNum="
						+ numPages + "';" + getAjaxFunStr() + "\"");
			}
			buf.append("\" >");
			buf.append(numPages);
		}
		return buf.toString();
	}

	private String getAjaxFunStr() {
		StringBuilder str = new StringBuilder();
		str.append("if(pageurl.indexOf('?')>-1){pageurl+='&notitle=1';}else{pageurl+='?notitle=1';}try{var ii = new VeLayer();ii.load();}catch(e){};$('#" + divId + "').load(pageurl,function(){try{ii.close()}catch(e){}");
		if( StringUtils.isNotBlank(callback) ){
			str.append("try{").append(callback).append("();}catch(e){}");
		}
		str.append("});");
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	public String getExportUrl() {
		return exportUrl;
	}

	public void setExportUrl(String exportUrl) {
		this.exportUrl = exportUrl;
	}

	public String getDtableid() {
		return dtableid;
	}

	public void setDtableid(String dtableid) {
		this.dtableid = dtableid;
	}
}
