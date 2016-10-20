package cn.vetech.vedsb.common.service.cache;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.VeDate;

@Service
public class ShsysCache {
	@Cacheable("Cache")
	public String getUser(String bh){
		System.out.println("我来获取数据了");
		return VeDate.getNo(10)+"__"+bh;
	}
	
}
