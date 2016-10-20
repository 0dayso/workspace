package org.vetech.core.business.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vetech.core.modules.cache.CacheManage;
/**
 * 参数表缓存
 * @author 章磊
 *
 */
@Component
public class CsbCacheService {
	@Autowired
	private  CacheManage  cacheManage;
	private String keyPre = "CSB:";
	
	public VecsbCache get(String bh){
		return (VecsbCache)cacheManage.get(keyPre, bh,VecsbCache.class);
		
	}
	public void put(String bh,Object object){
		cacheManage.put(keyPre, bh,VecsbCache.class, object, 0);
	}
}
