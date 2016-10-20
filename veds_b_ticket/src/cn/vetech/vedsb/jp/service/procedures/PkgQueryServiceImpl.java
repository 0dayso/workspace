package cn.vetech.vedsb.jp.service.procedures;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.Bean2Map;

import cn.vetech.vedsb.jp.dao.procedures.PkgQueryDao;
import cn.vetech.vedsb.jp.entity.bbzx.Gqbb;
import cn.vetech.vedsb.jp.entity.bbzx.Tpbb;
@Service
public class PkgQueryServiceImpl extends ParamMap{

	@Autowired
	private PkgQueryDao  pkgQueryDao;
	/**
	 * 退票报表查询
	 * @param param
	 * @return
	 */
	public Map<String,Object> tfpReport(Tpbb t){
		Map<String,Object> param=Bean2Map.getMap(t);
		super.xmlToJson(param);
	    pkgQueryDao.tfpReport(param);
	    return param;
	}
	
	/**
	 * 改签报表查询
	 * @param param
	 * @return
	 */
	public Map<String,Object> gqReport(Gqbb gqbb){
		Map<String,Object> param=Bean2Map.getMap(gqbb);
		super.xmlToJson(param);
	    pkgQueryDao.gqReport(param);
	    return param;
	}
	
	/**
	 * 出票报表查询
	 * @param param
	 * @return
	 */
	public void cpReport(Map<String,Object> param){
		super.xmlToJson(param);
	    pkgQueryDao.cpReport(param);
	}
	
}
