package cn.vetech.vedsb.jp.entity.cgdzbb;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.vetech.core.business.export.ExportPage;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.Bean2Map;

import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;

public class CgdzbbExportPage extends ExportPage<Cgdzbb>{
	private ProcedureServiceImpl procedureServiceImpl;
	public CgdzbbExportPage(Cgdzbb t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 2850601579826269688L;

	@Override
	public Collection<?> getCollection(Cgdzbb t) {
		if (procedureServiceImpl == null) {
			procedureServiceImpl = SpringContextUtil.getBean("procedureServiceImpl",ProcedureServiceImpl.class);
		}
		Map<String,Object> param=Bean2Map.getMap(t);
		procedureServiceImpl.procgreport(param);
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
