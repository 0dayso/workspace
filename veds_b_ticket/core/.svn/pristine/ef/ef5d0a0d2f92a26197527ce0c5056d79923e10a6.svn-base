package org.displaytag.tags;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;

public class MergeTag extends BodyTagSupport {
	private List<Integer> list = new ArrayList<Integer>();

	private String title;

	private String var;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void add(Integer col) {
		list.add(col);
	}

	public int doEndTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		TableTag tableTag = (TableTag) findAncestorWithClass(this, TableTag.class);

		if (tableTag != null && tableTag.isFirstIteration()) {
			String col = "";
			for (int i = 0; i < list.size(); i++) {
				if (StringUtils.isBlank(col)) {
					col = list.get(i) + "";
				} else {
					col = col + "," + list.get(i);
				}
			}
			col = "{col:'" + col + "',title:'" + getTitle() + "'}";

			String uid = StringUtils.trimToEmpty(tableTag.getUid()) + "mergeid";

			String allmerge = StringUtils.trimToEmpty((String) request.getAttribute(uid));
			if (StringUtils.isBlank(allmerge)) {
				request.setAttribute(uid, col);
			} else {
				request.setAttribute(uid, allmerge + "," + col);
			}
		}
		release();
		return EVAL_PAGE;
	}

	public int doStartTag() throws JspException {

		return EVAL_PAGE;
	}

	public void release() {
		list.clear();
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}
}
