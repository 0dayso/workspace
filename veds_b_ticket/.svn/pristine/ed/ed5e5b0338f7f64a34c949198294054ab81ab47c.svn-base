package cn.vetech.vedsb.jp.entity.jpcwgl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.vetech.core.business.export.ExportPage;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.jp.service.jpcwgl.JpYsdzServiceImpl;
/**
 * 未到账报表  查询导出使用
 * @author vetech
 *
 */
public class WdzExportPage extends  ExportPage<Wdz>{

	private static final long serialVersionUID = 1L;
	
	private JpYsdzServiceImpl  jpYsdzServiceImpl;
	

	public WdzExportPage(Wdz wdz) {
		super(wdz);
	}
	@Override
	public Collection<?> getCollection(Wdz wdz) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		if(jpYsdzServiceImpl == null ){
			jpYsdzServiceImpl = SpringContextUtil.getBean("jpYsdzServiceImpl",JpYsdzServiceImpl.class);
		}
		Page page = jpYsdzServiceImpl.wdzPage(wdz,wdz.getStart(),wdz.getCount());
		if(page != null ){
			list = page.getList();
		}
		return list;
	}
	
	@Override
	public void beforeExport(Object o){
		Map<String,Object> m = (Map<String, Object>) o;
		//网店平台
		Wdzlsz zlsz = (Wdzlsz) ((Map)m.get("EX")).get("WDID");
		m.put("WDID", zlsz.getWdmc());
		
		//订单类型
		String ddlx = VeStr.getValue(m, "DDLX");//1 正常单 2 退票单 3改签单  其他 补差单
		String ddlxName = "";
		if("1".equals(ddlx)){
			ddlxName = "正常单";
		}else if("2".equals(ddlx)){
			ddlxName = "退票单";
		}else if("1".equals(ddlx)){
			ddlxName = "改签单";
		}else{
			ddlxName = "补差单";
		}
		m.put("DDLX", ddlxName);
		
		String DZ_SFDZ = VeStr.getValue(m, "DZ_SFDZ");
		m.put("DZ_SFDZ", "0".equals(DZ_SFDZ) ? "未到账" : "已到账");
		
		
	}

}
