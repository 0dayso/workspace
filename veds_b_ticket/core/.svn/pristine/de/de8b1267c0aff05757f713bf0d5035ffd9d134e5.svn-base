package org.displaytag.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.displaytag.model.HeaderCell;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.vetech.core.modules.utils.VeStr;

public class DisplayUtils {
	public static String export(String dtableid) {
		String export = new ParamEncoder(dtableid).encodeParameterName(TableTagParameters.PARAMETER_EXPORTTYPE);
		return export + "=2&" + TableTagParameters.PARAMETER_EXPORTING + "=1";
	}

	public static String sortStr(HttpServletRequest request) {
		// display的requestURI后面带的参数，对应的是display:table id 的值
		String tableid = request.getParameter("dtableid");
		if (StringUtils.isBlank(tableid)) {
			return "";
		}
		// display的加密，例如d-XXXX-s=XX
		String pColumn = new ParamEncoder(tableid).encodeParameterName(TableTagParameters.PARAMETER_SORT);
		String pOrder = new ParamEncoder(tableid).encodeParameterName(TableTagParameters.PARAMETER_ORDER);
		String column = request.getParameter(pColumn);
		String order = request.getParameter(pOrder);
		if (StringUtils.isNotBlank(column)) {
			if ("2".equals(order)) {
				return column + "  DESC";
			} else {
				return column + " ASC";
			}
		}
		return "";
	}

	public static String sortStr(HttpServletRequest request, String defaultValue) {
		String sort = sortStr(request);
		if (StringUtils.isBlank(sort)) {
			return defaultValue;
		}
		return sort;
	}

	public static String getExp(List<HeaderCell> list) {
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() > 0) {
			for (HeaderCell headerCell : list) {
				String title = VeStr.clearHtml(headerCell.getTitle());
				String expfield = headerCell.getExpfield();
				if (StringUtils.isNotBlank(expfield)) {
					expfield = expfield.toUpperCase();
					if (StringUtils.isBlank(sb.toString())) {
						sb.append(expfield + "," + title);
					} else {
						sb.append(";" + expfield + "," + title);
					}
				}
			}
		}
		String ss = sb.toString();
		try {
			ss = URLEncoder.encode(ss, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ss;
	}

	public static String getExp2(String alltitle, String allexpfield) {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotBlank(alltitle) && StringUtils.isNotBlank(allexpfield)) {
			String[] tt = alltitle.split(",");
			String[] ft = allexpfield.split(";");
			if (tt.length == ft.length) {
				for (int i = 0; i < tt.length; i++) {
					String expfield = ft[i];
					String title = tt[i];
					if (StringUtils.isNotBlank(expfield)) {
						expfield = expfield.toUpperCase();
						if (StringUtils.isBlank(sb.toString())) {
							sb.append(expfield + "," + title);
						} else {
							sb.append(";" + expfield + "," + title);
						}
					}
				}
			}
		}

		String ss = sb.toString();
		try {
			ss = URLEncoder.encode(ss, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ss;
	}

	public static List<String> getTitle(String uri) {
		List<String> list = new ArrayList<String>();
		if (StringUtils.isNotBlank(uri)) {
			String[] field = uri.split(";");
			for (String s : field) {
				list.add(s);
			}
		}
		return list;
	}
	/**
	 * 去掉表头内容
	 * 
	 * @param s
	 * @return
	 */
	public static String unTitle(String str) {
		String s = str;
		s = StringUtils.replace(s, "</span>", "");
		int i = StringUtils.indexOf(s, ">");
		s = StringUtils.substring(s, i + 1);
		return s;
	}
	public static void main(String[] args) {
		DisplayUtils.export("vc");
		// 6578706f7274=1&toDate=${param.toDate}&d-2698876-e=2

	}
}
