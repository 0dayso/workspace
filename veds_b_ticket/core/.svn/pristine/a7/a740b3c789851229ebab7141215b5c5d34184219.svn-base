package org.vetech.core.business.tag;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.vetech.core.business.cache.BcityCache;
import org.vetech.core.business.cache.BcityCacheService;
import org.vetech.core.business.cache.HkgsCache;
import org.vetech.core.business.cache.HkgsCacheService;
import org.vetech.core.business.cache.VeclassCache;
import org.vetech.core.business.cache.VeclassCacheService;

/**
 * 参数表标签 根据编号直接返回参数表对象,没有取到则返回空对象
 * 
 *
 */
public class FunctionCode {
	/**
	 * 获取服务类
	 * 
	 * @param paramClass
	 * @return
	 */
	private static <T> T getBean(Class<T> paramClass) {
		ServletContext sct = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sct);
		return wac.getBean(paramClass);
	}

	/**
	 * 通过航空公司二字代码获取航空公司对象
	 * 
	 * @param ezdh
	 * @return
	 */
	public static HkgsCache getHkgs(String ezdh) {
		HkgsCacheService hcs = getBean(HkgsCacheService.class);
		return hcs.get(ezdh);
	}

	/**
	 * 通过城市三字码获取城市对象
	 * 
	 * @param nbbh
	 * @return
	 */
	public static BcityCache getBcity(String nbbh) {
		BcityCacheService bcs = getBean(BcityCacheService.class);
		return bcs.get(nbbh);
	}

	/**
	 * 通过id获取数据字典中的一个对象
	 * 
	 * @param id
	 * @return
	 */
	public static VeclassCache getVeclass(String id) {
		VeclassCacheService vec = getBean(VeclassCacheService.class);
		return vec.get(id);
	}

	/**
	 * 通过类别获取一个数据字典的集合
	 * 
	 * @param lb
	 * @return
	 */
	public static List<VeclassCache> getVeclassLb(String lb) {
		VeclassCacheService vec = getBean(VeclassCacheService.class);
		return vec.getLb(lb);
	}

}
