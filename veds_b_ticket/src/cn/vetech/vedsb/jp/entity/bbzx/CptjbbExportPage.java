package cn.vetech.vedsb.jp.entity.bbzx;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.business.export.ExportPage;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.Bean2Map;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;

public class CptjbbExportPage extends  ExportPage<Cptjbb>{
	
	private static final long serialVersionUID = 1L;
	
	private ProcedureServiceImpl procedureServiceImpl;
	
	private AttachService attachService;

	public CptjbbExportPage(Cptjbb t) {
		super(t);
	}

	

	@Override
	public Collection<?> getCollection(Cptjbb t) {
		if (procedureServiceImpl == null) {
			procedureServiceImpl = SpringContextUtil.getBean("procedureServiceImpl",ProcedureServiceImpl.class);
		}
		Map<String,Object> param=Bean2Map.getMap(t);
		
		procedureServiceImpl.cpTjbbReport(param);
		List<Map<String,Object>> list=(List<Map<String,Object>>)param.get("list");
		if(CollectionUtils.isNotEmpty(list)){
			if (attachService == null) {
				attachService = SpringContextUtil.getBean("attachService",AttachService.class);
			}
			if("1".equals(t.getTjfs())){
				attachService.wdzl("WDID").execute(list);
			}
		}
		
		return list;
	}
	
	@Override
	public void beforeExport(Object o){
		//统计方式
		String tjfs = t.getTjfs();
		
		Map<String,Object> m=(Map<String, Object>) o;
		
		//自动出票总订单数
		int zdcps = NumberUtils.toInt(VeStr.getValue(m, "ZDCPS"));
		//自动出票成功订单数
		int cpcgs = NumberUtils.toInt(VeStr.getValue(m, "CPCGS"));
		//失败订单数
		m.put("SBDDS", zdcps-cpcgs);
		
		NumberFormat fmt = NumberFormat.getPercentInstance();
		fmt.setMaximumFractionDigits(2);
		double cpcgbl = NumberUtils.toDouble(VeStr.getValue(m, "CPCGBL"));
		double sbddzb = 0.0;
		//失败订单占比
		if(cpcgbl != 0){
			sbddzb = 1 - cpcgbl;
		}
		m.put("SBDDZB", fmt.format(sbddzb));
		
		//网店
		if("1".equals(tjfs)){
			Wdzlsz zlsz= (Wdzlsz) ((Map)m.get("EX")).get("WDID");
			if(zlsz != null){
				m.put("WDID", zlsz.getWdmc());
			}
		}
	}

}
