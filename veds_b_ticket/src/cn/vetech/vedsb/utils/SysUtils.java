package cn.vetech.vedsb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.business.cache.CsbCacheService;
import org.vetech.core.business.cache.VecsbCache;
import org.vetech.core.modules.service.SpringContextUtil;

/**
 * 系统工具
 * 
 * @author zhanglei
 *
 */
public class SysUtils {
	protected static final Logger logger = LoggerFactory.getLogger(SysUtils.class);

 
	/**
	 * 取得机票系统的地址 http://alidsb.abctrip.net:10090/veds_ticket
	 * 
	 * @return
	 */
	public static String getTicketUrl() {
		CsbCacheService cacheService = SpringContextUtil.getBean(CsbCacheService.class);
		VecsbCache vecsbCache = cacheService.get("9016");
		if (vecsbCache != null) {
			String url = vecsbCache.getCsz1();
			logger.info("取得机票系统的外网地址是" + url);
			return url;
		}
		return null;
	}

	/**
	 * 取得B2B出票系统的地址 http://alidsb.abctrip.net:10090/veds_b2b_cp
	 * 
	 * @return
	 */
	public static String getB2bCpUrl() {
		CsbCacheService cacheService = SpringContextUtil.getBean(CsbCacheService.class);
		VecsbCache vecsbCache = cacheService.get("1005");
		if (vecsbCache != null) {
			String url = vecsbCache.getCsz1();
			logger.info("取得B2B出票系统的外网地址" + url);
			return url;
		}
		return null;
	}

	/**
	 * 平台出票link系统的外网地址 http://alidsb.abctrip.net:10090/veds_plat_cp
	 * 
	 * @return
	 */
	public static String getPlatLinkCpUrl() {
		CsbCacheService cacheService = SpringContextUtil.getBean(CsbCacheService.class);
		VecsbCache vecsbCache = cacheService.get("1003");
		if (vecsbCache != null) {
			String url = vecsbCache.getCsz1();
			logger.info("取得平台出票系统的外网地址" + url);
			return url;
		}
		return null;
	}

}
