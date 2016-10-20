package cn.vetech.vedsb.common.cache;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vetech.core.modules.cache.RedisManage;

import cn.vetech.vedsb.common.entity.Shmkb;
@Component
public class BQxCache {
	@Autowired
	private RedisManage redisManage;
	private String keyPre = "BQX:";
	public Map<String, Shmkb>  getMk(String shbh){
		Map<String, Shmkb> allMkbMap = (Map<String, Shmkb> )redisManage.get(keyPre+shbh+"MKNEW","");
		return allMkbMap;
	}
	public void setmk(String shbh,Map<String, Shmkb> allMkbMap){
		//60*60*24=24小时
		redisManage.put(keyPre+shbh+"MKNEW","", allMkbMap,3600);
	}
}
