package org.vetech.core.modules.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vetech.core.modules.mapper.JsonMapper;

import com.fasterxml.jackson.databind.JavaType;

@Component
public class CacheManage {
	@Autowired
	private EhcacheManage ehcacheManage;
	@Autowired
	private RedisManage redisManage;
	JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();

	public void put(String cachename, String key, Class cls, Object value, long m) {
		if (cls != null) {
			redisManage.put(cachename, key, jsonMapper.toJson(value), m);
		} else {
			redisManage.put(cachename, key, value, m);
		}
	}

	public Object get(String cachename, String key, Class cls) {
		String localKey = cachename+key;
		Object t = ehcacheManage.get("TmpCache", localKey);
		if (t != null) {
			return t;
		}
		String json = (String) redisManage.get(cachename, key);
		if (json != null) {
			if (cls != null) {
				t = jsonMapper.fromJson(json, cls);
				ehcacheManage.put("TmpCache", localKey, t);
			} else {
				t = json;
				ehcacheManage.put("TmpCache", localKey, json);
			}
		}
		return t;
	}

	public Object get(String cachename, String key, JavaType javaType) {
		String localKey = cachename+key;
		Object t = ehcacheManage.get("TmpCache", localKey);
		if (t != null) {
			return t;
		}
		String json = (String) redisManage.get(cachename, key);
		if (json != null) {
			t = jsonMapper.fromJson(json, javaType);
			ehcacheManage.put("TmpCache", localKey, t);
		}
		return t;
	}
}
