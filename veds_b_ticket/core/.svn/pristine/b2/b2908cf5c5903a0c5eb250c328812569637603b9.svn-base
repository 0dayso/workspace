package org.vetech.core.modules.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.modules.utils.Exceptions;
/**
 * 实现了一些基本control的方法
 * @author Administrator
 *
 */
public abstract class AbstractBaseControl {
	@InitBinder
	public void initListBinder(WebDataBinder binder) {
	    // 设置需要包裹的元素个数，默认为256
	    binder.setAutoGrowCollectionLimit(1024);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));   
	}
	protected String prefix="";
	private String url;
	
	/**
	 * 跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "view{page}")
	public String view(@PathVariable("page") String page){
		return viewpath(page);
	}
	/**
	 * 验证
	 * @param bindingResult
	 */
	protected void valid(BindingResult bindingResult) {

		if(bindingResult.hasErrors()){
			throw new RuntimeException(bindingResult.getFieldError().getField()+bindingResult.getFieldError().getDefaultMessage());
		}
		
	}
	/**
	 * 这个json返回提供了一些错误信息
	 * @param o
	 * @return
	 */
	protected JsonBean json(Object o){
		return new JsonBean(o);
	}
	/**
	 * 这个json返回提供了一些错误信息
	 * @param o
	 * @return
	 */
	protected JsonBean json(Object o,String error){
		return new JsonBean(o,error);
	}
	/**
	 * 重定向跳转
	 * @param path 全路径
	 * @return
	 */
	protected String redirectPath(String path){
		return "redirect:"+path;
	}
	/**
	 * 添加错误信息
	 * @param error
	 * @param model
	 */
	protected void addError(String error,ModelMap model){
		model.addAttribute("ERRORMESSAGE", error);
		
	}
	/**
	 * 添加成功信息
	 * @param message
	 * @param model
	 */
	protected void addSuccess(String message,ModelMap model){
		model.addAttribute("SUCCESSMESSAGE", message);
	}

	/**
	 * 添加成功信息并跳转页面
	 * @param message
	 * @param jsp
	 * @param model
	 * @return
	 */
	protected String addSuccess(String message,String jsp,ModelMap model){
		model.addAttribute("SUCCESSMESSAGE", message);
		return viewpath(jsp);
		
	}
	/**
	 * 添加错误信息
	 * @param error
	 * @param e
	 * @param jsp
	 * @param model
	 * @return
	 */
	protected String addError(String error,Exception e,String jsp,ModelMap model){
		model.addAttribute("ERRORMESSAGE", error);
		model.addAttribute("ERRORMESSAGE_DETAIL", Exceptions.getStackTraceAsString(e));
		return viewpath(jsp);
		
	}
	
	protected String addError(String error,String jsp,ModelMap model){
		return viewpath(jsp);
		
	}
	/**
	 * 重定向，control的相对路径
	 * @param jsp
	 * @return
	 */
	protected String redirectviewpath(String jsp){
		return "redirect:"+viewpath(jsp);
	}
	/**
	 * 根据control的注解获得路径
	 * @param jsp
	 * @return
	 */
	protected String viewpath(String jsp){
		
		if(StringUtils.isBlank(url)){
			String packageurl = this.getClass().getPackage().getName();
//			RequestMapping mapping = Reflections.getAnnotation(this.getClass(), RequestMapping.class);
//			if(mapping!=null){
			packageurl = packageurl.substring(packageurl.lastIndexOf(".")+1);
			url = packageurl+"/"+this.getClass().getSimpleName().replace("Controller", "").toLowerCase();
			url = prefix+url;
		}
		return url+"/"+jsp;
	}
	/**
	 * 通过url获得当前执行control的方法名
	 * @param request
	 * @return
	 */
	protected String getMethodName(HttpServletRequest request){
		String controlpath = viewpath("");
		String uri = request.getRequestURI();
		if(uri.indexOf("?")>-1){
			uri=uri.substring(0,uri.indexOf("?"));
		}
		
		uri = uri.replace(controlpath, "");
		if(uri.indexOf("/")>-1){
			String []uris = uri.split("/");
			uri = uris[uris.length-1];
			if(uri.indexOf("_")>-1){
				uri=uri.substring(0,uri.indexOf("_"));
			}
		}

		return uri;
	}
}
