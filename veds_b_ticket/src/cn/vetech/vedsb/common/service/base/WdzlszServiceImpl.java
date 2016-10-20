package cn.vetech.vedsb.common.service.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.vetech.core.business.cache.AttachInf;
import org.vetech.core.business.cache.VeclassCache;
import org.vetech.core.business.tag.FunctionCode;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.dao.base.WdzlszDao;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
@Service
public class WdzlszServiceImpl extends MBaseService<Wdzlsz,WdzlszDao> implements AttachInf {
	/**
	 * 根据业务类型集合查询wdzlsz，这里主要用于导单设置，查询"国内机票";"国际机票"网店设置集合。
	 * @param param zt String; shbh String; ywlx String[]
	 * @return
	 */
	public List<Wdzlsz> getWdZlszListByYwlx(Map<String,Object> param){
		return this.getMyBatisDao().getWdZlszListByYwlx(param);
	}

	@Override
	public Object getBean(Object[] fixedvalue, Object[] attrObject) {
		String wdid = (String) VeStr.getArray(attrObject, 0);
		if (StringUtils.isBlank(wdid)) {
			return new Wdzlsz();
		}
		Wdzlsz t = new Wdzlsz();
		t.setId(wdid);
		Wdzlsz t2 = this.getEntityById(t);
		if (t2 != null) {
			return t2;
		}
		return new Wdzlsz();
	}

	/**
	 * <自动出票路由设置中选择网店>
	 * 
	 * @param wd
	 * @return [参数说明]
	 * @author heqiwen
	 * @return Page [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Page queryWdPage(Wdzlsz wd) {
		List<Wdzlsz> wdzlszList=this.getMyBatisDao().queryWdList(wd);
		int total=this.getMyBatisDao().getTotalCount(wd);
		Page page=new Page(wd.getStart(),wd.getCount());
		if(wdzlszList!=null&&wdzlszList.size()>0){
			for(Wdzlsz onewd:wdzlszList){
				VeclassCache ve=FunctionCode.getVeclass(onewd.getWdpt());
				onewd.setWdptmc(ve.getMc());
			}
		}
		page.setList(wdzlszList);
		page.setTotalCount(total);
		return page;
	}

	/**
	 * <功能详细描述>
	 * 
	 * @param wds
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<Map<String,String>> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public List<String> queryWdmcByIds(String wds) {
		if(StringUtils.isBlank(wds)){
			return null;
		}
		String[] wdids=wds.split(",");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("wds", wdids);
		return this.getMyBatisDao().queryWdmcByIds(map);
	}
	
	public List<Wdzlsz> getWdZlszListByBean(String shbh,String zt,String gngj){
		return this.getMyBatisDao().getWdZlszListByBean(shbh, zt,gngj);
	}
}
