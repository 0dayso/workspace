package cn.vetech.vedsb.jp.entity.bbzx;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.cache.HkgsCache;
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

public class CpbbExportPage extends  ExportPage<Cpbb>{
	private static final long serialVersionUID = 7774283473989186572L;
	public CpbbExportPage(Cpbb t) {
		super(t);
	}
	
	private PkgQueryServiceImpl  pkgQueryServiceImpl;
	private AttachService attachService;


	@Override
	public Collection<?> getCollection(Cpbb t) {
		if (pkgQueryServiceImpl == null) {
			pkgQueryServiceImpl = SpringContextUtil.getBean("pkgQueryServiceImpl",PkgQueryServiceImpl.class);
		}
		Map<String,Object> param=Bean2Map.getMap(t);
		pkgQueryServiceImpl.cpReport(param);
		List<?> list = (List<?>) param.get("list");
		if(CollectionUtils.isNotEmpty(list)){
			if (attachService == null) {
				attachService = SpringContextUtil.getBean("attachService",AttachService.class);
			}
			//attachService.wdzl("WDID").hkgs("HKGS").zfkm("SKKM").execute(list);
			String tjfs = t.getTjfs();
			if("1".equals(tjfs)){
				attachService.zfkm("SKKM").wdzl("WDID").hkgs("HKGS").execute(list);
			}else if("3".equals(tjfs)){
				attachService.wdzl("WDID").execute(list);
			}else if("6".equals(tjfs)){
				attachService.hkgs("HKGS").execute(list);
			}
		}
		return list;
	}
	
	@Override
	public void beforeExport(Object o){
		//统计方式
		String tjfs = t.getTjfs();
		
		Map<String,Object> m=(Map<String, Object>) o;
		String jpzt=VeStr.getValue(m, "JPZT");//1 正常票 2 退票 3 废票
		//机票类型
		if("1".equals(jpzt)){
			jpzt="正常票";
		}else if("2".equals(jpzt)){
			jpzt="退票";
		}else if("3".equals(jpzt)){
			jpzt="废票";
		}
		m.put("JPZT", jpzt);
		
		//国内国际
		String gngj="1".equals(VeStr.getValue(m, "GNGJ")) ? "国内" : "国际";
		m.put("GNGJ", gngj);
		
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
		
		//网店  网店平台
		if("1".equals(tjfs) || "3".equals(tjfs)){
			Wdzlsz zlsz= (Wdzlsz) ((Map)m.get("EX")).get("WDID");
			m.put("WDPT",zlsz.getWdptmc());
			m.put("WDID", zlsz.getWdmc());
		}
		//网店平台
		if("2".equals(tjfs)){
			String wdpt = VeStr.getValue(m, "WDPT");
			//网店平台不为空则进行匹配
			if(StringUtils.isNotBlank(wdpt)){
				VeclassCacheService veclassCacheService = SpringContextUtil.getBean("veclassCacheService",VeclassCacheService.class);
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
		
		//航空公司
		if("6".equals(tjfs)){
			HkgsCache hkgscache= (HkgsCache) ((Map)m.get("EX")).get("HKGS");
			m.put("HKGS",hkgscache.getShortname());
		}
		
		//支付状态
		String skzt="1".equals(VeStr.getValue(m, "SKZT")) ? "已支付" : "未支付";
		m.put("SKZT", skzt);
		
		//支付科目
		if("1".equals(tjfs)){
			Shzfkm  shzfkm=(Shzfkm) ((Map)m.get("EX")).get("SKKM");
			m.put("SKKM", shzfkm.getKmmc());
		}
		
		//支付金额
		m.put("ZFJE", m.get("XS_XJ"));
	}
}
