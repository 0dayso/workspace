package cn.vetech.vedsb.jp.service.procedures;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vetech.vedsb.jp.dao.procedures.PkgZjpSgDao;

@Service
public class PkgZjpSgServiceImpl extends ParamMap{
	@Autowired
	private PkgZjpSgDao pkgZjpSgDao;
	
	/**
	 * 调用手工转机票后台过程
	 * @param param
	 * @return
	 */
	public void sgZjp(Map<String, Object> param){
		super.xmlToJson(param);
		this.pkgZjpSgDao.sgZjp(param);
	}
	
	/**
	 * cps出票通知给电商转机票(调后台过程)
	 * @param param
	 */
	public void fzdzjpCps(Map<String, Object> param){
		super.xmlToJson(param);
		this.pkgZjpSgDao.fzdzjpCps(param);
	}
	
	/**
	 * 平台出票通知给电商转机票(调后台过程)
	 * @param param
	 */
	public void fzjppt(Map<String, Object> param){
		super.xmlToJson(param);
		this.pkgZjpSgDao.fzjppt(param);
	}
	/**
	 * 淘宝派单转机票
	 * @param param
	 */
	public void fzjppd(Map<String, Object> param){
		super.xmlToJson(param);
		this.pkgZjpSgDao.fzjppd(param);
	}
	/**
	 * 航司官网订单转机票
	 * @param param
	 */
	public void fzjpb2b(Map<String, Object> param){
		super.xmlToJson(param);
		this.pkgZjpSgDao.fzjpb2b(param);
	}
}
