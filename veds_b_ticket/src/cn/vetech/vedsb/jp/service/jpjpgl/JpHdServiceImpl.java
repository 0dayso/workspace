package cn.vetech.vedsb.jp.service.jpjpgl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpjpgl.JpHdDao;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpHd;

@Service
public class JpHdServiceImpl extends MBaseService<JpHd, JpHdDao>{
	/**
	 * 分页查询改签单和改签单明细
	 * @param jpgqd
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	
	public Page query(JpHd jphd, String sortType) throws Exception {
		Page page = new Page(jphd.getStart(),jphd.getCount());
		List<Map<String,Object>> jphdList = this.getMyBatisDao().query(jphd);
		int pagecount = this.getMyBatisDao().getPageCount(jphd);
		page.setList(jphdList);
		page.setTotalCount(pagecount);
		return page;
	}
	
	
	
	public Map<String, Object> detail(JpHd jphd) {
		return this.getMyBatisDao().detail(jphd);
	}
	
    
	public JpHd getJpHdById(JpHd jphd){
		
		return this.getMyBatisDao().getJpHdById(jphd);
	}
	
	/**
	 * 通过票号和明细改签id获取航段信息
	 * @param jphd
	 * @return
	 */
	public List<JpHd> getJpHdByTkno(JpHd jphd) {
		return this.getMyBatisDao().select(jphd);
	}
	
	/**
	 * 根据订单航段id查询机票航段
	 * @return
	 */
	public JpHd getJpHdByDdhdidAndTkno(JpHd jphd) {
		return this.getMyBatisDao().getJpHdByDdhdidAndTkno(jphd);
	}
	
	/**
	 * 通过退票单号取退票航段信息
	 * @param tpdh
	 * @param shbh
	 * @return
	 */
	public List<JpHd> getJpHdByTpdh(String tpdh,String shbh){
		return this.getMyBatisDao().getJpHdByTpdh(tpdh, shbh);
	}


	/**
	 * <通过票号和商户查找航段>
	 * 
	 * @param jphd
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<JpHd> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public List<JpHd> queryJpHdByTkno(JpHd jphd) {
		return this.getMyBatisDao().queryJpHdByTkno(jphd);
	}
	
	/**
	 * 通过退票明细ID取退票航段信息
	 * @param tpmxid
	 * @param shbh
	 * @return
	 */
	public List<JpHd> getJpHdByTpMxId(String tpmxid,String shbh){
		return this.getMyBatisDao().getJpHdByTpMxId(tpmxid, shbh);
	}
	
}
