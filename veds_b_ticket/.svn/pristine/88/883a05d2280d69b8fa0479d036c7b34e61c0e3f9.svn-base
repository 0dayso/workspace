package cn.vetech.vedsb.jp.service.cpsz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.dao.cpsz.JpZdtfpGzszDao;
import cn.vetech.vedsb.jp.entity.cpsz.JpZdtfpGzsz;

@Service
public class JpZdtfpGzszServiceImpl extends MBaseService<JpZdtfpGzsz, JpZdtfpGzszDao> {
	@Autowired
	private WdzlszServiceImpl wdzlszService;
	
	public Page queryPage(JpZdtfpGzsz zdtfp) {
		List<Map<String,Object>> tfplist=this.getMyBatisDao().getQueryList(zdtfp);
		if(tfplist!=null&&tfplist.size()>0){
			for(Map<String,Object> map:tfplist){
				/*String hkgsmc=(String)map.get("HKGS");
				HkgsCache hk=FunctionCode.getHkgs(hkgsmc);
				if(hk!=null){
					hkgsmc=hk.getShortname();
				}
				map.put("HKGSMC", hkgsmc);
				String ddcity=(String)map.get("DDCITY");
				String cfcity=(String)map.get("CFCITY");
				BcityCache ddcy=FunctionCode.getBcity(ddcity);
				BcityCache cfcy=FunctionCode.getBcity(cfcity);
				if(ddcy!=null){
					ddcity=ddcy.getMc();
				}
				if(cfcy!=null){
					cfcity=cfcy.getMc();
				}
				map.put("CFCY", cfcity);
				map.put("DDCY", ddcity);*/
				String wdid=(String)map.get("SYPTBS");
				String wdmc="";
				List<String> mc=wdzlszService.queryWdmcByIds(wdid);
				if(mc!=null&&mc.size()>0){
					for(String m:mc){
						wdmc+=m+",";
					}
				}
				if(wdmc.length()>1){
					wdmc=wdmc.substring(0,wdmc.length()-1);
				}
				map.put("WDMC", wdmc);
			}
		}
		int totalCount=this.getMyBatisDao().getTotalCount(zdtfp);
		Page page=new Page(zdtfp.getStart(),zdtfp.getCount());
		page.setList(tfplist);
		page.setTotalCount(totalCount);
		
		return page;
	}

	/**
	 * <审核或启用或暂停退废票>
	 * 
	 * @param param [参数说明]
	 * @author heqiwen
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void updateTfpZt(Map<String, Object> param) {
		this.getMyBatisDao().updateTfpZt(param);
	}

	/**
	 * <删除退废票>
	 * 
	 * @param tpfp [参数说明]
	 * @author heqiwen
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void deleteChange(Map<String,Object> tpfp) {
		this.getMyBatisDao().deleteChange(tpfp);
	}

	public List<JpZdtfpGzsz> getZdTfpGzsz(JpZdtfpGzsz zdtfp){
		return this.getMyBatisDao().getZdTfpGzsz(zdtfp);
	}
}
