package cn.vetech.vedsb.jp.service.cgptsz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.cgptsz.JpPtbjHfszDao;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtbjHfsz;
@Service
public class JpPtbjHfszServiceImpl extends MBaseService<JpPtbjHfsz,JpPtbjHfszDao>{
	/**
	 * 通过商户编号获取后返集合
	 * @param shbh
	 * @return
	 */
	public List<JpPtbjHfsz> getHfszListByShbh(String shbh) {
		return this.getMyBatisDao().getHfszListByShbh(shbh);
	}
	
	public void updateHfszById(JpPtbjHfsz hfsz) {
		this.getMyBatisDao().updateHfszById(hfsz);
	}
	/**
	 * <获取商户后返设置>
	 * 
	 * @param shbh
	 * @return [参数说明]
	 * @return Map<String,JpPtbjHfsz> [返回类型说明]
	 */
	public Map<String,JpPtbjHfsz> genPthf(String shbh){
		JpPtbjHfsz t=new JpPtbjHfsz();
		t.setShbh(shbh);
		t.setSfkqhf("1");
		List<JpPtbjHfsz> hfList=getMyBatisDao().select(t);
		if(CollectionUtils.isEmpty(hfList)){
			return null;
		}
		Map<String,JpPtbjHfsz> map=new HashMap<String, JpPtbjHfsz>();
		for(JpPtbjHfsz hf : hfList){
			map.put(hf.getId(), hf);
		}
		return map;
	}
}