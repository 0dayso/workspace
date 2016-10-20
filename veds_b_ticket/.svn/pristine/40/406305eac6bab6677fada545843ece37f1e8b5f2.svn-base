package cn.vetech.vedsb.jp.entity.bbzx;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.business.export.ExportPage;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.procedures.PkgQueryServiceImpl;
import cn.vetech.vedsb.utils.DictEnum.CGTFPZT;
import cn.vetech.vedsb.utils.DictEnum.XSTFPZT;

public class TpbbExportPage extends  ExportPage<Tpbb>{
	private static final long serialVersionUID = 7774283473989186572L;
	public TpbbExportPage(Tpbb t) {
		super(t);
	}
	
	private AttachService attachService;
	private PkgQueryServiceImpl  pkgQueryServiceImpl;
	

	@Override
	public Collection<?> getCollection(Tpbb t) {
		if (pkgQueryServiceImpl == null) {
			pkgQueryServiceImpl = SpringContextUtil.getBean("pkgQueryServiceImpl",PkgQueryServiceImpl.class);
		}
		Map<String,Object> param = pkgQueryServiceImpl.tfpReport(t);
		List<?> list = (List<?>) param.get("list");
		if(CollectionUtils.isNotEmpty(list)){
			if (attachService == null) {
				attachService = SpringContextUtil.getBean("attachService",AttachService.class);
			}
			attachService.zfkm("XS_TKKM").execute(list);
		}
		return list;
	}
	@Override
	public void beforeExport(Object o){
		Map<String,Object> m=(Map<String, Object>) o;
		double xs_tpsxf=NumberUtils.toDouble(VeStr.getValue(m, "XS_TPSXF"));
		double cg_tpf=NumberUtils.toDouble(VeStr.getValue(m, "CG_TPF"));
		double yk = xs_tpsxf - cg_tpf;
		String ykbs="";
		if(yk == 0.00){
			ykbs="平";
		}else if(yk>0.00){
			ykbs="盈";
		}else{
			ykbs="亏";
		}
		m.put("YKBS", ykbs);
		if(StringUtils.isNotBlank(VeStr.getValue(m, "XS_TPZT"))){
			m.put("XS_TPZT", XSTFPZT.dataMap.get(VeStr.getValue(m, "XS_TPZT")).getMc());
		}
		if(StringUtils.isNotBlank(VeStr.getValue(m, "CG_TPZT"))){
			m.put("CG_TPZT", CGTFPZT.dataMap.get(VeStr.getValue(m, "CG_TPZT")).getMc());
		}
		
		//乘机人类型
		String cjrlx=VeStr.getValue(m, "CJRLX");
		if("1".equals(cjrlx)){
			cjrlx="成人";
		}else if("2".equals(cjrlx)){
			cjrlx="儿童";
		}else if("3".equals(cjrlx)){
			cjrlx="婴儿";
		}
		m.put("CJRLX", cjrlx);
		
		//航程类型
		String hclx=VeStr.getValue(m, "HCLX");
		if("1".equals(hclx)){
			hclx="单程";
		}else if("2".equals(hclx)){
			hclx="往返";
		}else if("3".equals(hclx)){
			hclx="联程";
		}else if("4".equals(hclx)){
			hclx="缺口";
		}
		m.put("HCLX", hclx);
		
		//退款科目
		Shzfkm  shzfkm=(Shzfkm) ((Map)m.get("EX")).get("XS_TKKM");
		m.put("XS_TKKM", shzfkm.getKmmc());
	}
}
