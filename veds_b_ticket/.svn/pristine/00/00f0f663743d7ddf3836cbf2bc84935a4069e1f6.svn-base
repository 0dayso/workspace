package cn.vetech.vedsb.jp.service.jpztjk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.vetech.core.business.cache.VeclassCache;
import org.vetech.core.business.tag.FunctionCode;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpztjk.JpkpztDao;
import cn.vetech.vedsb.jp.entity.jpztjk.Jpkpzt;

@Service
public class JpkpztServiceImpl extends MBaseService<Jpkpzt, JpkpztDao>{

	/**
	 * 到期OPEN票分页查询
	 * @param jpkpzt
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page queryPage(Jpkpzt jpkpzt, int pageNum, int pageSize, String sortType) {
		Page page=new Page(pageNum, pageSize);
		List<Map<String, String>> list = this.getMyBatisDao().queryJpkpztList(jpkpzt,pageNum,pageSize,sortType);
		if(CollectionUtils.isNotEmpty(list)){
			for(Map<String, String> map : list){
				if(StringUtils.isNotBlank(map.get("CGLY"))){
					List<VeclassCache> veList = FunctionCode.getVeclassLb(map.get("CGLY"));
					if(veList != null){
						map.put("CGLYNAME", veList.get(0).getMc());
					}
				}
			}
			List<Map<String, String>> slist = this.getMyBatisDao().queryJpkpztSlist(jpkpzt,pageNum,pageSize,sortType);
			Map<String, String> m = new HashMap<String, String>();
			if(slist != null && slist.size()>0){
				Map<String, String> sumMap = (Map<String, String>)slist.get(0);
				m.put("JPZDJS", sumMap.get("JPZDJS").toString());
				m.put("JPJSFS", sumMap.get("JPJSFS").toString());
				m.put("JPTAXS", sumMap.get("JPTAXS").toString());
				m.put("XJS", sumMap.get("XJS").toString());
			}
			list.add(m);
		}
		
		int count = this.getMyBatisDao().queryJpkpztCount(jpkpzt);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}

}
