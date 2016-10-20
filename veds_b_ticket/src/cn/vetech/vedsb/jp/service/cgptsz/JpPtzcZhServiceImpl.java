package cn.vetech.vedsb.jp.service.cgptsz;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.dao.cgptsz.JpPtzcZhDao;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
@Service
public class JpPtzcZhServiceImpl extends MBaseService<JpPtzcZh,JpPtzcZhDao>{

	public List<JpPtzcZh> getJpPtzcZhForList(JpPtzcZh ptzh) throws Exception {
		List<JpPtzcZh> ptzhList = this.getMyBatisDao().select(ptzh);
		return ptzhList;
	}
	
	public int saveJpPtzcZh(JpPtzcZh ptzh) throws Exception {
		if (StringUtils.isNotBlank(ptzh.getId())) {
			return this.getMyBatisDao().updateByPrimaryKeySelective(ptzh);
		} else {
			String id = VeDate.getNo(6);
			ptzh.setId(id);
			return this.getMyBatisDao().insert(ptzh);
		}
	}
	
	public JpPtzcZh getPtzhByPtBs(String ptzcbs, String shbh) {
		return this.getMyBatisDao().getPtzhByPtBs(ptzcbs, shbh);
	}
	
	public List<JpPtzcZh> getPtzhByPtBsList(String ptzcbs) {
		return this.getMyBatisDao().getPtzhByPtBsList(ptzcbs);
	}
	/**
	 * <功能详细描述>
	 * 根据平台标识查询平台账号，多个用逗号拼接
	 * @param shbh
	 * @param platcodes
	 * @return List<JpPtzcZh> [返回类型说明]
	 */
	public List<JpPtzcZh> genPtzhByPtbss(String shbh,String platcodes){
		String[] plats=platcodes.split(",");
		String platcs="('"+StringUtils.join(plats,"','")+"')";
		return this.getMyBatisDao().genPtzhByPtbss(shbh, platcs);
	}
	
	/**
	 * 根据平台账号和平台标示查询相应平台
	 * @param shbh
	 * @param businessNo
	 * @param ptzcbs
	 * @return
	 */
	public JpPtzcZh getPtzhByPtzh(String shbh,String businessNo,String ptzcbs){
		return this.getMyBatisDao().getPtzhByPtzh(shbh, businessNo, ptzcbs);
	}
}