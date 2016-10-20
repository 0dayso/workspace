package org.vetech.core.modules.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.AbstractControllerUrlHandlerMapping;
/**
 * 对URL约定方式进行了扩展
 * 支持多个前缀
 * @author 章磊
 *
 */
public class ControllerClassNameHandlerMappingEx extends AbstractControllerUrlHandlerMapping {
	private String[] pathPrefixArr = {null};
	private static final String CONTROLLER_SUFFIX = "Controller";

	private boolean caseSensitive = false;


	public static String basePackage;
	
	public static String pathPrefixs;
	
	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
		if (StringUtils.hasLength(this.basePackage) && !this.basePackage.endsWith(".")) {
			this.basePackage = this.basePackage + ".";
		}
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}
	public void setPathPrefixs(String pathPrefixs) {
		this.pathPrefixs = pathPrefixs;
		String[] pathPrefixArrTmp = pathPrefixs.split(",");
		List<String> list = new ArrayList<String>();
		
		for (String tmp : pathPrefixArrTmp) {
			String pathPrefixtmp =tmp;
			if (StringUtils.hasLength(pathPrefixtmp)) {
				if (!pathPrefixtmp.startsWith("/")) {
					pathPrefixtmp = ("/" + pathPrefixtmp);
				}
				if (pathPrefixtmp.endsWith("/")) {
					pathPrefixtmp = pathPrefixtmp.substring(0, pathPrefixtmp.length() - 1);
				}
			}
			list.add(pathPrefixtmp);
		}
		pathPrefixArr = list.toArray(new String[]{});
	}
	
	protected String[] generatePathMappings(Class<?> beanClass) {
		List<String> paths = new ArrayList<String>();
		for(String pathPrefix : pathPrefixArr){
			StringBuilder pathMapping = buildPathPrefix(beanClass,pathPrefix);
			String className = ClassUtils.getShortName(beanClass);
			String path = (className.endsWith(CONTROLLER_SUFFIX) ?
					className.substring(0, className.lastIndexOf(CONTROLLER_SUFFIX)) : className);
			if (path.length() > 0) {
				if (this.caseSensitive) {
					pathMapping.append(path.substring(0, 1).toLowerCase()).append(path.substring(1));
				}
				else {
					pathMapping.append(path.toLowerCase());
				}
			}
			if (isMultiActionControllerType(beanClass)) {
				paths.add(pathMapping.toString());
				paths.add(pathMapping.toString() + "/*");
			}
			else {
				paths.add(pathMapping.toString()+ "*");
			}
		}
		return paths.toArray(new String[]{});
		
	}

	private StringBuilder buildPathPrefix(Class<?> beanClass,String pathPrefix) {
		StringBuilder pathMapping = new StringBuilder();
		if (pathPrefix != null) {
			pathMapping.append(pathPrefix);
			pathMapping.append("/");
		}
		else {
			pathMapping.append("/");
		}
		if (this.basePackage != null) {
			String packageName = ClassUtils.getPackageName(beanClass);
			if (packageName.startsWith(this.basePackage)) {
				String subPackage = packageName.substring(this.basePackage.length()).replace('.', '/');
				pathMapping.append(this.caseSensitive ? subPackage : subPackage.toLowerCase());
				pathMapping.append("/");
			}
		}
		return pathMapping;
	}
	@Override
	protected String[] buildUrlsForHandler(String beanName, Class<?> beanClass) {
		return generatePathMappings(beanClass);
	}
}
