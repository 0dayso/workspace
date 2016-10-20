package org.vetech.core.business.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vetech.core.modules.cache.CacheManage;
import org.vetech.core.modules.mapper.JsonMapper;

import com.fasterxml.jackson.databind.JavaType;

/**
 * 航空公司和其对应的舱位集合
 * 
 * @author zhanglei
 *
 */
@Component
public class BairwaycwCacheService implements AttachInf {
	@Autowired
	private CacheManage cacheManage;
	private String keyPre = "BAIRWAYCW:";

	public List<BairwaycwCache> getEzdh(String ezdh) {
		JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();
		JavaType jt = jsonMapper.contructCollectionType(List.class, BairwaycwCache.class);
		return (List<BairwaycwCache>) cacheManage.get(keyPre, ezdh, jt);
	}

	public void putEzdh(String ezdh, Object o) {
		cacheManage.put(keyPre, ezdh, BairwaycwCache.class, o, 0);
	}

	@Override
	public Object getBean(Object[] fixedvalue, Object[] attrObject) {
		List<BairwaycwCache> l = new ArrayList<BairwaycwCache>();
		if (attrObject == null || attrObject.length < 1) {
			return l;
		}
		List<BairwaycwCache> l2 = getEzdh(attrObject[0].toString());
		if (l2 == null) {
			return l;
		} else {
			return l2;
		}
	}
}
