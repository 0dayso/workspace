package cn.vetech.vedsb.jp.entity.jpcwgl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.vetech.core.business.export.ExportPage;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.Bean2Map;

import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpcwgl.JpYsdzServiceImpl;

public class JpysdzdcExportPage extends ExportPage<JpYsdzdc>{
	private static final long serialVersionUID = -1268726032176884181L;
	private JpYsdzServiceImpl jpYsdzServiceImpl;
	private AttachService attachService;
	public JpysdzdcExportPage(JpYsdzdc t) {
		super(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<?> getCollection(JpYsdzdc t) {
		if (jpYsdzServiceImpl == null) {
			jpYsdzServiceImpl = SpringContextUtil.getBean("jpYsdzServiceImpl",JpYsdzServiceImpl.class);
		}
		
		List<Map<String, Object>> list=null;
		Map<String,Object> param=Bean2Map.getMapKeyLowerCase(t);
		
		try {
			Page page = jpYsdzServiceImpl.genDbresult(param,t.getWdpt(),t.getStart(),t.getCount());
			list = page.getList();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		Map<String, Object> map = (Map<String, Object>)o;
		//国内国际
		String ddlx = (String)map.get("DDLX");
		if("1".equals(ddlx)){
			ddlx = "正常单";
		}else if("2".equals(ddlx)){
			ddlx = "退废单";
		}else if("3".equals(ddlx)){
			ddlx = "改签单";
		}else if("4".equals(ddlx)){
			ddlx = "补差单";
		}
		map.put("DDLX", ddlx);
		
		String bd_sfybd = (String)map.get("BD_SFYBD");
		if("0".equals(bd_sfybd)){
			bd_sfybd = "未补单";
		}else if("1".equals(bd_sfybd)){
			bd_sfybd = "已补单";
		}else if("2".equals(bd_sfybd)){
			bd_sfybd = "未完成";
		}
		map.put("BD_SFYBD", bd_sfybd);
		
		String dz_sfdz = (String)map.get("DZ_SFDZ");
		if("0".equals(dz_sfdz)){
			dz_sfdz = "未到账";
		}else if("1".equals(dz_sfdz)){
			dz_sfdz = "已到账";
		}
		map.put("DZ_SFDZ", dz_sfdz);
		
		String jesfzq = (String)map.get("JESFZQ");
		if("1".equals(jesfzq)){
			jesfzq = "金额正确";
		}else if("2".equals(jesfzq)){
			jesfzq = "金额错误";
		}
		map.put("JESFZQ", jesfzq);
	}
	
}
