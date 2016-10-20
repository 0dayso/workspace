package org.vetech.core.business.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vetech.core.modules.cache.CacheManage;
import org.vetech.core.modules.mapper.JsonMapper;

import com.fasterxml.jackson.databind.JavaType;

/**
 * B系统veclass表
 * 
 * @author zhanglei
 *
 */
@Component
public class VeclassCacheService implements AttachInf {
	@Autowired
	private CacheManage cacheManage;
	private String keyPre = "VECLASS:";
	private String keyPrelb = "VECLASSLB:";

	public VeclassCache get(String id) {
		return (VeclassCache) cacheManage.get(keyPre, id, VeclassCache.class);
	}

	public void put(String id, Object o) {
		cacheManage.put(keyPre, id, VeclassCache.class, o, 0);
	}

	public List<VeclassCache> getLb(String lb) {
		JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();
		JavaType jt = jsonMapper.contructCollectionType(List.class, VeclassCache.class);
		return (List<VeclassCache>) cacheManage.get(keyPrelb, lb, jt);
	}

	public void putLb(String lb, Object o) {
		cacheManage.put(keyPrelb, lb, VeclassCache.class, o, 0);
	}

	@Override
	public Object getBean(Object[] fixedvalue, Object[] attrObject) {
		VeclassCache o = new VeclassCache();
		if (attrObject == null || attrObject.length < 1) {
			return o;
		}
		if(attrObject[0]==null){
			return o;
		}
		VeclassCache onew = get(attrObject[0].toString());
		if (onew == null) {
			return o;
		}
		return onew;
	}

}
