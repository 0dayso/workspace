package org.vetech.core.business.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vetech.core.modules.utils.HttpClientUtil;
/**
 * 刷新缓存公共service
 * @author 李善良
 *
 */
@Component
public class FlushCacheService {
	protected final Logger logger = LoggerFactory.getLogger(FlushCacheService.class);
	@Autowired
	private CsbCacheService csbCache;
	/**
	 * 缓存枚举类
	 */
	public enum CacheCode{
		
		ALL("ALL"), //刷新所有缓存
		CSB("CSB"), //参数表
		SHB("SHB"), //商户表
		USER("USER"), //用户表
		HKGS("HKGS"), //航空公司
		FWZ("FWZ"), //服务组
		CITY("CITY"), //城市和机场
		BCLASS("BCLASS"); //部分数据字典
		
		private String code;
		private CacheCode(String code){
			this.code = code;
		}
		public String getCode() {
			return code;
		}
		
	}
	/**
	 * 刷新缓存
	 * @param cacheCode
	 * @return
	 */
	public String flush(FlushCacheService.CacheCode cacheCode){
		VecsbCache resIp = (VecsbCache)csbCache.get("9003" );
		String url =resIp.getCsz1()+"/veds/flushcache/flush?param="+cacheCode.getCode();
		String message ="";
		try {
			message = HttpClientUtil.doGet(url, null, "utf-8");
		} catch (Exception e) {
			logger.error("刷新缓存失败!",e);
			return message;
		}
		return message;
	}
	
	
}
