package cn.vetech.vedsb.jp.entity.jpcwgl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.vetech.core.business.cache.VeclassCache;
import org.vetech.core.business.export.ExportPage;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.Bean2Map;

import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;

public class JpXsmlExportPage extends ExportPage<JpXsmlfx>{
	private ProcedureServiceImpl procedureServiceImpl;
	private AttachService attachService;
	public JpXsmlExportPage(JpXsmlfx t) {
		super(t);
	}

	private static final long serialVersionUID = -68941376629615476L;

	@Override
	public Collection<?> getCollection(JpXsmlfx t) {
		if (procedureServiceImpl == null) {
			procedureServiceImpl = SpringContextUtil.getBean("procedureServiceImpl",ProcedureServiceImpl.class);
		}
		Map<String,Object> param=Bean2Map.getMap(t);
		try {
			procedureServiceImpl.getMlfxList(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<?> list = (List<?>) param.get("p_cur");
		if(CollectionUtils.isNotEmpty(list)){
			if (attachService == null) {
				attachService = SpringContextUtil.getBean("attachService",AttachService.class);
			}
			attachService.wdzl("WDID").veclass("WDPT").execute(list);
		}
		return list;
	}
	
	@Override
	public void beforeExport(Object o){
		Map<String,Object> m=(Map<String, Object>) o;
		String cjrlx = (String)m.get("CJRLX");
		if("1".equals(cjrlx)){
			cjrlx = "成人";
		}else if("2".equals(cjrlx)){
			cjrlx = "儿童";
		}else if("3".equals(cjrlx)){
			cjrlx = "婴儿";
		}
		m.put("CJRLX", cjrlx);
		
		String hclx = (String)m.get("HCLX");
		if("1".equals(hclx)){
			hclx = "单程";
		}else if("2".equals(hclx)){
			hclx = "往返程";
		}else if("3".equals(hclx)){
			hclx = "联程";
		}else if("4".equals(hclx)){
			hclx = "缺口程";
		}
		
		String pzzt = (String)m.get("PZZT");
		if("1".equals(pzzt)){
			pzzt = "正常票";
		}else if("2".equals(pzzt)){
			pzzt = "退票";
		}else if("3".equals(pzzt)){
			pzzt = "废票";
		}else if("4".equals(pzzt)){
			pzzt = "改签";
		}
		m.put("PZZT", pzzt);
		m.put("HCLX", hclx);
		//网店平台
		Wdzlsz zlsz= (Wdzlsz) ((Map)m.get("EX")).get("WDID");
		m.put("WDID", zlsz.getWdmc());
		
		VeclassCache veclass = (VeclassCache) ((Map)m.get("EX")).get("WDPT");
		m.put("WDPT", veclass.getMc());
	}
	
}
