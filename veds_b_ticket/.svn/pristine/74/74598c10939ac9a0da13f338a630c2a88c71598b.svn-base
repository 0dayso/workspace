package cn.vetech.vedsb.jp.entity.cgdzbb;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.vetech.core.business.export.ExportPage;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.Bean2Map;

import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;

public class CgdzbbMxExportPage extends ExportPage<CgdzbbMx>{
	private ProcedureServiceImpl procedureServiceImpl;
	public CgdzbbMxExportPage(CgdzbbMx t) {
		super(t);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -4879212038674479610L;

	@Override
	public Collection<?> getCollection(CgdzbbMx t) {
		if (procedureServiceImpl == null) {
			procedureServiceImpl = SpringContextUtil.getBean("procedureServiceImpl",ProcedureServiceImpl.class);
		}
		Map<String,Object> param=Bean2Map.getMap(t);
		procedureServiceImpl.promxcgreport(param);
		List<?> list = (List<?>) param.get("p_cur");
		return list;
	}
	
	@Override
	public void beforeExport(Object o){
		Map<String, Object> map = (Map<String, Object>)o;
		//国内国际
		String gngj = (String)map.get("GNGJ");
		if("0".equals(gngj)){
			gngj = "国际";
		}else if("1".equals(gngj)){
			gngj = "国内";
		}
		map.put("GNGJ", gngj);
		
	}
}
