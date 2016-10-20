package org.vetech.core.business.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vetech.core.modules.cache.CacheManage;

@Component
public class BcityCacheService implements AttachInf {
	@Autowired
	private CacheManage cacheManage;
	private String keyPre = "BCITY:";
	
	private String keyPrecsbh = "BCITYCSBH:";

	public BcityCache get(String nbbh) {
		if (StringUtils.isBlank(nbbh)) {
			return null;
		}
		return (BcityCache) cacheManage.get(keyPre, nbbh, BcityCache.class);
	}

	public void put(String nbbh, Object o) {
		cacheManage.put(keyPre, nbbh, BcityCache.class, o, 0);
	}
	
	//城市
	public BcityCache getBycsbh(String csbh) {
		if (StringUtils.isBlank(csbh)) {
			return null;
		}
		return (BcityCache) cacheManage.get(keyPrecsbh, csbh, BcityCache.class);
	}

	public void putBycsbh(String csbh, Object o) {
		cacheManage.put(keyPrecsbh, csbh, BcityCache.class, o, 0);
	}
	
	

	@Override
	public BcityCache getBean(Object[] fixedvalue, Object[] attrObject) {
		if (attrObject == null || attrObject.length < 1) {
			return new BcityCache();
		}
		BcityCache b = get(attrObject[0].toString());
		if (b == null) {
			return new BcityCache();
		} else {
			return b;
		}
	}
}
