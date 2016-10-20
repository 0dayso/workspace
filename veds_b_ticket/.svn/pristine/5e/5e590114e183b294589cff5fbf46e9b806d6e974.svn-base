package cn.vetech.vedsb.jp.service.procedures;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vetech.vedsb.jp.dao.procedures.PkgPsChildDao;
@Service
public class PkgPsChildServiceImpl {
	@Autowired
	private PkgPsChildDao pkgPsChildDao;
	/**
	 * 批量订单完成
	 * @param paramsXml
	 * @return
	 */
	public List<Object> batchWc(String paramsXml){
		return this.pkgPsChildDao.batchWc(paramsXml);
	}
}
