package cn.vetech.vedsb.jp.service.jpgqgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpgqgl.JpGqdCjrDao;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdCjr;

@Service
public class JpGqdCjrServiceImpl extends MBaseService<JpGqdCjr, JpGqdCjrDao>{
	
	/**
	 * 通过改签单号获得改签单乘机人分组集合
	 * @param gqdh
	 * @return
	 */
	public Map<String,List<JpGqdCjr>> getCjrListByGqdhGroup(String gqdh, String shbh) {
		JpGqdCjr jpGqdCjr = new JpGqdCjr();
		jpGqdCjr.setGqdh(gqdh);
		jpGqdCjr.setShbh(shbh);
		List<JpGqdCjr> cjrList = this.getMyBatisDao().select(jpGqdCjr);
		Map<String,List<JpGqdCjr>> jpGqdCjrMap = groupCjr(cjrList);
		return jpGqdCjrMap;
	}
	
	/**
	 * 通过改签单号获得改签单乘机人集合
	 * @param gqdh
	 * @return
	 */
	public List<JpGqdCjr> getCjrListByGqdh(String gqdh, String shbh) {
		JpGqdCjr jpGqdCjr = new JpGqdCjr();
		jpGqdCjr.setGqdh(gqdh);
		jpGqdCjr.setShbh(shbh);
		List<JpGqdCjr> cjrList = this.getMyBatisDao().select(jpGqdCjr);
		return cjrList;
	}
	
	public int updateCjrById(JpGqdCjr jpGqdCjr) {
		return this.getMyBatisDao().updateByIdSelective(jpGqdCjr);
	}
	
	/**
	 * 将改签信息根据乘机人进行map分组
	 * @param MxList
	 * @return
	 */
	public Map<String,List<JpGqdCjr>> groupCjr(List<JpGqdCjr> MxList) {
		Map<String,List<JpGqdCjr>> jpGqdMxMap = new HashMap<String,List<JpGqdCjr>>();
		String key = null;
		List<JpGqdCjr> jpGqdMxList = null;
		for (JpGqdCjr jpGqdMx : MxList) {
			key = jpGqdMx.getCjr();
			if (jpGqdMxMap.get(key) == null) {
				jpGqdMxList = new ArrayList<JpGqdCjr>();
				jpGqdMxList.add(jpGqdMx);
			} else {
				jpGqdMxList = jpGqdMxMap.get(key);
				jpGqdMxList.add(jpGqdMx);
			}
			jpGqdMxMap.put(key, jpGqdMxList);
		}
		return jpGqdMxMap;
	}
}
