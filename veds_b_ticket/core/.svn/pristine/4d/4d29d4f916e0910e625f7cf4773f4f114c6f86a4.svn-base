package org.vetech.core.modules.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 实现了一些基本control的方法
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings({})
public abstract class BaseControl {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected static final String PAGE_SIZE = "10";
	
	@InitBinder
	public void initListBinder(WebDataBinder binder) {
		// 设置需要包裹的元素个数，默认为256
		binder.setAutoGrowCollectionLimit(1024);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
		
	}

	protected String prefix = "";
	private String url;

	/**
	 * 跳转页面
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "view{page}")
	public String view(@PathVariable("page") String page) {
		return viewpath(page);
	}

	/**
	 * 验证
	 * 
	 * @param bindingResult
	 */
	protected void valid(BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new RuntimeException(bindingResult.getFieldError().getField()
					+ bindingResult.getFieldError().getDefaultMessage());
		}

	}

	/**
	 * 重定向跳转
	 * 
	 * @param path
	 *            全路径
	 * @return
	 */
	protected String redirectPath(String path) {
		return "redirect:" + path;
	}

	/**
	 * 重定向，control的相对路径
	 * 
	 * @param jsp
	 * @return
	 */
	protected String redirectviewpath(String jsp) {
		return "redirect:" + viewpath(jsp);
	}

	/**
	 * 根据control的注解获得路径
	 * 
	 * @param jsp
	 * @return
	 */
	protected String viewpath(String jsp) {

		if (StringUtils.isBlank(url)) {

			String packageurl = this.getClass().getPackage().getName();
			// RequestMapping mapping =
			// Reflections.getAnnotation(this.getClass(), RequestMapping.class);
			// if(mapping!=null){
			packageurl = packageurl.replace(
					ControllerClassNameHandlerMappingEx.basePackage, "");
			packageurl = packageurl.replaceAll("\\.", "/");
			url = packageurl
					+ "/"
					+ this.getClass().getSimpleName().replace("Controller", "")
							.toLowerCase();
			url = prefix + url;
		}
		return url + "/" + jsp;
	}

	/**
	 * 通过url获得当前执行control的方法名
	 * 
	 * @param request
	 * @return
	 */
	protected String getMethodName(HttpServletRequest request) {
		String controlpath = viewpath("");
		String uri = request.getRequestURI();
		if (uri.indexOf("?") > -1) {
			uri = uri.substring(0, uri.indexOf("?"));
		}

		uri = uri.replace(controlpath, "");
		if (uri.indexOf("/") > -1) {
			String[] uris = uri.split("/");
			uri = uris[uris.length - 1];
			if (uri.indexOf("_") > -1) {
				uri = uri.substring(0, uri.indexOf("_"));
			}
		}

		return uri;
	}
}
