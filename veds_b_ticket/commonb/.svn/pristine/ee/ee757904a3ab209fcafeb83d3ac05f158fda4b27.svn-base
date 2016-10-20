package cn.vetech.vedsb.common.sso;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vetech.core.modules.cache.RedisManage;
import org.vetech.core.modules.mapper.JsonMapper;
@Component
public class BSsoService {
	JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();
	@Autowired
	private RedisManage redisManage;
	private String keyPre = "BLOGIN:";

	public SsoUser get(String bh){
		 SsoUser user = (SsoUser)redisManage.get(keyPre,bh);
		 return user;
	}
	public void set(String bh,SsoUser user){
		//60*60*24=24小时
		redisManage.put(keyPre,bh, user,86400);
	}
	public void remove(String bh){
		redisManage.remove(keyPre, bh);
	}
	public String getLoginIp(){
		String json = (String)redisManage.get("CSB:", "9002");
		
		LoginIp t = jsonMapper.fromJson(json, LoginIp.class);
		if(t!=null){
			return t.getCsz1();
		}else{
			return "";
		}
	}
}
