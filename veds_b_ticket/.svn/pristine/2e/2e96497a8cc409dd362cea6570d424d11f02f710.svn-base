package cn.vetech.vedsb.jp.service.procedures;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vetech.vedsb.jp.dao.procedures.PkgTicketTfDao;

@Service
public class PkgTicketTfServiceImpl extends ParamMap{
	 @Autowired
	 private PkgTicketTfDao pkgTicketTfDao;
	/**
	 * <平台退废票通知结果提交到后台处理>
	 * @param param
	 */
	public void ptCgProcess(Map<String, Object> param){
		super.xmlToJson(param);
		pkgTicketTfDao.ptCgProcess(param);
	}
}
