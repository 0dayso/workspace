package cn.vetech.vedsb.jp.service.procedures;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vetech.vedsb.jp.dao.procedures.PkgExtendDao;

@Service
public class PkgExtendServiceImpl extends ParamMap{
	@Autowired
	private PkgExtendDao pkgExtendDao;
	/**
	 * 根据商户编号获取本地扫描需要扫描的OPEN票
	 * @param param
	 * @throws Exception
	 */
	public void getTksForCompanies(Map<String,Object> param) throws Exception{
		try {
			this.pkgExtendDao.getTksForCompanies(param);
		} catch (Exception e) {
			throw new Exception("根据商户编号获取本地扫描需要扫描的OPEN票出错"+e.getMessage());
		}
	}
	
	/**
	 * DETR回写
	 * @throws Exception 
	 */
	public void fUpdateTkState(Map<String,Object> param) throws Exception{
		try {
			super.xmlToJson(param);
			this.pkgExtendDao.fUpdateTkState(param);
		} catch (Exception e) {
			throw new Exception("DETR回写出错"+e.getMessage());
		}
	}
}
