package cn.vetech.vedsb.jp.entity.bbzx;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.cache.VeclassCache;
import org.vetech.core.business.cache.VeclassCacheService;
import org.vetech.core.business.export.ExportPage;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.Bean2Map;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.procedures.PkgQueryServiceImpl;
import cn.vetech.vedsb.utils.DictEnum.JPGQZT;

public class GqbbExportPage extends  ExportPage<Gqbb>{
	private static final long serialVersionUID = 7774283473989186572L;
	public GqbbExportPage(Gqbb t) {
		super(t);
	}
	
	private PkgQueryServiceImpl  pkgQueryServiceImpl;
	private AttachService attachService;
	private VeclassCacheService veclassCacheService;


	@Override
	public Collection<?> getCollection(Gqbb t) {
		if (pkgQueryServiceImpl == null) {
			pkgQueryServiceImpl = SpringContextUtil.getBean("pkgQueryServiceImpl",PkgQueryServiceImpl.class);
		}
		Map<String,Object> param=Bean2Map.getMap(t);
		param = pkgQueryServiceImpl.gqReport(t);
		List<?> list = (List<?>) param.get("list");
		if(CollectionUtils.isNotEmpty(list)){
			if (attachService == null) {
				attachService = SpringContextUtil.getBean("attachService",AttachService.class);
			}
			attachService.wdzl("WDID").zfkm("GQ_CGKM").zfkm("SKKM").execute(list);
		}
		return list;
	}
	
	@Override
	public void beforeExport(Object o){
		
		Map<String,Object> m=(Map<String, Object>) o;
		//国内国际
		String gngj="1".equals(VeStr.getValue(m, "GNGJ")) ? "国内" : "国际";
		m.put("GNGJ", gngj);
		
		if(StringUtils.isNotBlank(VeStr.getValue(m, "GQZT"))){
			m.put("GQZT", JPGQZT.dataMap.get(VeStr.getValue(m, "GQZT")).getMc());
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
		
		//证件类型
		String zjlx=VeStr.getValue(m, "ZJLX");
		if("NI".equals(zjlx)){
			zjlx="身份证";
		}else if("ID".equals(zjlx)){
			zjlx="护照";
		}
		m.put("ZJLX", zjlx);
		//支付科目
		Shzfkm  shzfkm=(Shzfkm) ((Map)m.get("EX")).get("GQ_CGKM");
		m.put("GQ_CGKM", shzfkm.getKmmc());
		//收款科目
		Shzfkm SKKM = (Shzfkm) ((Map)m.get("EX")).get("SKKM");
		m.put("SKKM", SKKM.getKmmc());
		//支付状态
		String skzt="1".equals(VeStr.getValue(m, "SKZT")) ? "已支付" : "未支付";
		m.put("SKZT", skzt);
		//改签类型
		String gqlx="1".equals(VeStr.getValue(m, "GQLX")) ? "改期" : "升舱";
		m.put("GQLX", gqlx);
		
		//网店平台  网店
		Wdzlsz zlsz= (Wdzlsz) ((Map)m.get("EX")).get("WDID");
		m.put("WDID", zlsz.getWdmc());
		//zlsz.getWdptmc()为空则,不赋值为网店平台
		if(StringUtils.isNotBlank(zlsz.getWdptmc())){
			m.put("WDPT",zlsz.getWdptmc());
		}else{
			String wdpt = VeStr.getValue(m, "WDPT");
			//网店平台不为空则进行匹配
			if(StringUtils.isNotBlank(wdpt)){
				veclassCacheService = SpringContextUtil.getBean("veclassCacheService",VeclassCacheService.class);
				List<VeclassCache> list = veclassCacheService.getLb("10100");
				if(CollectionUtils.isNotEmpty(list)){
					for (VeclassCache ve : list) {
						if(ve.getId().equals(wdpt)){
							m.put("WDPT", ve.getMc());
						}
					}
				}
			}
		}
	}
}
