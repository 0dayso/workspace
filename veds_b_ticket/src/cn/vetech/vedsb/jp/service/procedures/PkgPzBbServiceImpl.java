package cn.vetech.vedsb.jp.service.procedures;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vetech.vedsb.jp.dao.procedures.PkgPzBbDao;

@Service
public class PkgPzBbServiceImpl extends ParamMap{

	@Autowired
	private PkgPzBbDao pkgPzBbDao;
	
	
	/**
	 * 销存报表
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String,Object>> queryXcbb(Map<String,Object> param) throws Exception{
		try {
			super.xmlToJson(param);
			pkgPzBbDao.queryXcbb(param);
			List<Map<String,Object>> list = (List<Map<String,Object>>)param.get("list");
			return list;
		} catch (Exception e) {
			throw new Exception("行程单报表查询错误"+e.getMessage());
		}
	}
}
