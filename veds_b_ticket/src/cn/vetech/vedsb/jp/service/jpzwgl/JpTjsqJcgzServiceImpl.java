package cn.vetech.vedsb.jp.service.jpzwgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpzwgl.JpTjsqJcgzDao;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqJcgz;
@Service
public class JpTjsqJcgzServiceImpl extends MBaseService<JpTjsqJcgz, JpTjsqJcgzDao>{
	/**
	 * 根据bean获取追位基础数据
	 * @param jpTJsqjcgz
	 * @return
	 */
	public List<JpTjsqJcgz> getTjsqJcgzList(JpTjsqJcgz jpTJsqjcgz){
		return getMyBatisDao().getTjsqJcgzList(jpTJsqjcgz);
	}
	/**
	 * 根据商户编号获取追位基础数据值map
	 * @param jpTJsqjcgz
	 * @return
	 */
	public Map<String, String> getTjsqJcgzMap(String shbh){
		JpTjsqJcgz jpTjsqJcgz=new JpTjsqJcgz();
		jpTjsqJcgz.setShbh(shbh);
		List<JpTjsqJcgz> list=getMyBatisDao().getTjsqJcgzList(jpTjsqJcgz);
		Map<String, String> map=new HashMap<String, String>();
		for (JpTjsqJcgz beanJcgz : list) {
			if (StringUtils.isBlank(beanJcgz.getZdz())) {
				beanJcgz.setZdz(beanJcgz.getMrz());
			}
			map.put(beanJcgz.getZdywm().toUpperCase(), beanJcgz.getZdz());
		}
		return map;
	}
}
