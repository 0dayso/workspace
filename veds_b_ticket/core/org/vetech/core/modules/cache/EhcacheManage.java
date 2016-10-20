package org.vetech.core.modules.cache;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ehcache缓存
 * 
 * @author zhanglei
 * 
 */
@Component
public class EhcacheManage {
	/**
	 * applicationContext-ehcahe.xml配置
	 */
	@Autowired
	private CacheManager ehcacheManager;
	/**
	 * 
	 * @param cachename
	 *            缓存名称
	 * @param key
	 * @param value
	 */
	public void put(String cachename, String key, Object value) {
		Element element = new Element(key, value);
		Cache cache = ehcacheManager.getCache(cachename);
		cache.put(element);
	}
	/**
	 * 
	 * @param cachename
	 *            缓存名称
	 * @param key
	 * @param value
	 */
	public void put(String cachename, String key, Object value, int timeToLiveSeconds) {
		Element element = new Element(key, value);
		element.setTimeToLive(timeToLiveSeconds);
		Cache cache = ehcacheManager.getCache(cachename);
		cache.put(element);
	}

	public Object get(String cachename, String key) {
		Cache cache = ehcacheManager.getCache(cachename);
		Element element = cache.get(key);
		if (element == null) {
			return null;
		}
		return element.getObjectValue();
	}

	public List<Object> removeAllAndReturn(String cachename) {
		Cache cache = ehcacheManager.getCache(cachename);
		List<Object> valueList = new ArrayList();
		List<String> keyList = cache.getKeys();
		if (keyList != null && keyList.size() > 0) {
			for (String key : keyList) {
				Element element = cache.removeAndReturnElement(key);
				valueList.add(element.getObjectValue());
			}
		}
		return valueList;
	}

	public boolean remove(String cachename, String key) {
		return ehcacheManager.getCache(cachename).remove(key);
	}

	public Object removeAndReturn(String cachename, String key) {
		Element element = ehcacheManager.getCache(cachename).removeAndReturnElement(key);
		if (element == null) {
			return null;
		}
		return element.getObjectValue();
	}

	public void removeAll(String cachename) {
		ehcacheManager.getCache(cachename).removeAll();
	}

	/**
	 * 得到缓存对象占用内存的数量
	 * 
	 * @param cachename
	 * @return
	 */
	public long getMemoryStoreSize(String cachename) {
		return ehcacheManager.getCache(cachename).getMemoryStoreSize();
	}

	/**
	 * 得到缓存的对象数量；
	 * 
	 * @param cachename
	 * @return
	 */
	public int getSize(String cachename) {
		return ehcacheManager.getCache(cachename).getSize();
	}

	public Long getExpirationTime(String cachename, String key) {
		Element element = ehcacheManager.getCache(cachename).get(key);
		if (element == null) {
			return null;
		}
		return element.getExpirationTime();
	}
	/**
	 * 得到所有缓存的名称
	 * @return [参数说明]
	 * 
	 * @return String[] [返回类型说明]
	 */
	public String[] getCacheNames() {
		return ehcacheManager.getCacheNames();
	}
	/**
	 * 清除指定的缓存中过期的元素
	 * 
	 * @param cacheName [参数说明]
	 */
	public void clearExpiredByName(String cacheName){
		Cache cache=ehcacheManager.getCache(cacheName);
		if(cache !=null){
			cache.evictExpiredElements();
		}
	}
	/**
	 * 清除所有缓存中过期的元素
	 * 
	 * @param cacheName [参数说明]
	 */
	public void clearExpired(){
		String[] names=ehcacheManager.getCacheNames();
		if(names != null){
			for (String oneName : names) {
				clearExpiredByName(oneName);
			}
		}
	}
	public Cache getCache(String cacheName){
		return ehcacheManager.getCache(cacheName);
	}
}
