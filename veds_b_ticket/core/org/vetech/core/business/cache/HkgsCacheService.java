package org.vetech.core.business.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vetech.core.modules.cache.CacheManage;

@Component
public class HkgsCacheService implements AttachInf {
	@Autowired
	private CacheManage cacheManage;
	private String keyPre = "HKGS:";

	public HkgsCache get(String ezdh) {
		return (HkgsCache) cacheManage.get(keyPre, ezdh, HkgsCache.class);
	}

	public void put(String ezdh, Object o) {
		cacheManage.put(keyPre, ezdh, HkgsCache.class, o, 0);
	}

	@Override
	public HkgsCache getBean(Object[] fixedvalue, Object[] attrObject) {
		if (attrObject == null || attrObject.length < 1) {
			return new HkgsCache();
		}
		HkgsCache h = get(attrObject[0].toString());
		if (h == null) {
			return new HkgsCache();
		} else {
			return h;
		}
	}
}
