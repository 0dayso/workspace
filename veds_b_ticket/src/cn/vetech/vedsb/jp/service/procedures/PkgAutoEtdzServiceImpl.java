package cn.vetech.vedsb.jp.service.procedures;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vetech.vedsb.jp.dao.procedures.PkgAutoEtdzDao;

@Service
public class PkgAutoEtdzServiceImpl extends ParamMap{
	@Autowired
	private PkgAutoEtdzDao pkgAutoEtdzDao;
	
	/**
	 * <调用后台检查能否ETDZ>
	 * 
	 * @param param [参数说明]
	 */
	public void checkEtdz(Map<String, Object> param){
		this.pkgAutoEtdzDao.checkEtdz(param);
	}
	
	/**
	 * <自动转机票>
	 * 
	 * @param param [参数说明]
	 */
	public void autoEtdz(Map<String, Object> param){
		super.xmlToJson(param);
		this.pkgAutoEtdzDao.autoEtdz(param);
	}
}
