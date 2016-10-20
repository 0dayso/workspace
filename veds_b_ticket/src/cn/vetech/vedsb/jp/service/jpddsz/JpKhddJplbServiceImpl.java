package cn.vetech.vedsb.jp.service.jpddsz;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpddsz.JpKhddJplbDao;
import cn.vetech.vedsb.jp.entity.jpddsz.JpKhddJplb;

@Service
public class JpKhddJplbServiceImpl extends MBaseService<JpKhddJplb, JpKhddJplbDao> {
	/**
	 * wdid 
	 * @param param
	 */
	public List<JpKhddJplb> selectByJp(HashMap<String,String> param){
		return this.getMyBatisDao().selectByJp(param);
	}
	
	public int updateJplbByBean(JpKhddJplb jplb){
		return this.getMyBatisDao().updateJplbByBean(jplb);
	}
}
