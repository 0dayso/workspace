package cn.vetech.vedsb.jp.service.cgdzbb;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.cgdzbb.JpTslCheckDao;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpTslCheck;
@Service
public class JpTslCheckServiceImpl extends MBaseService<JpTslCheck,JpTslCheckDao>{
	/**
	 * 查询数据库中是否存在对应的TSL数据
	 * @param date
	 * @param office
	 * @param agent
	 * @param printno
	 * @param shbh
	 * @return
	 */
	public JpTslCheck selTslCheck(String date,String office,String agent,String printno,String shbh){
		return this.getMyBatisDao().selTslCheck(date, office, agent, printno, shbh);
	}
	
	public void deleteTslCheck(String cp_date,String officeid,String agent,String printno,String shbh){
		this.getMyBatisDao().deleteTslCheck(cp_date, officeid, agent, printno, shbh);
	}
}