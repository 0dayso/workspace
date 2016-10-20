package cn.vetech.vedsb.jp.service.jptpgl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jptpgl.JpTpdMxDao;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpHd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.service.jpjpgl.JpHdServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;

@Service
public class JpTpdMxServiceImpl extends MBaseService<JpTpdMx, JpTpdMxDao>{
	/**
	 * 分页查询改签单和改签单明细
	 * @param jpgqd
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	
	@Autowired
	private JpHdServiceImpl jpHdServiceImpl;
	
	public Page query(JpTpdMx jptpdmx, String sortType) throws Exception {
		Page page = new Page(jptpdmx.getStart(),jptpdmx.getCount());
		List<Map<String,Object>> jpgqdList = this.getMyBatisDao().query(jptpdmx);
		int pagecount = this.getMyBatisDao().getPageCount(jptpdmx);
		page.setList(jpgqdList);
		page.setTotalCount(pagecount);
		return page;
	}
	
	
	/**
	 * 取退票单详
	 */
	public Map<String, Object> detail(String id,String shbh) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		
		map.put("bmfz", null);
		return map;
	}
	
	
	public List<Map<String, Object>> getJpTpdMxList(String tpdh,String shbh){
		
		return this.getMyBatisDao().getJpTpdMxList(tpdh,shbh);
	}
	
	public List<Map<String, Object>> getJpTpdJpInfo(String tpdh,String shbh){
		return this.getMyBatisDao().getJpTpdJpInfo(tpdh,shbh);
	}
	
     public List<JpTpdMx> getJpTpdMxByTpdh(String tpdh,String shbh){
		
		return this.getMyBatisDao().getJpTpdMxByTpdh(tpdh, shbh);
	}
     
     /**
 	 * 通过退废明细id获取退废记录，0 没有 ， 大于0 表示有
 	 * @param id
 	 * @param shbh
 	 * @return
 	 */
    public int getJpTpdMxJl(String id, String shbh) {
    	return this.getMyBatisDao().getJpTpdMxJl(id, shbh);
    }
    
    /**
     * 采购对账退票待回款查询
     * @param map
     * @return
     */
    public Page getcgdzDhkList(Map<String, Object> map,int pageNum, int pageSize){
    	Page page =new Page(pageNum,pageSize);
    	int totalCount  = this.getMyBatisDao().getTotalCount(map);
    	List<Map<String, Object>> list = this.getMyBatisDao().getcgdzDhkList(map);
    	page.setTotalCount(totalCount);
		page.setList(list);
    	return page;
    }
    
    public JpPz getJpPzByShbh(String shbh) {
		return jpPzServiceImpl.getJpPzByShbh(shbh);
	}
    
    /**
	 * 通过退票明细ID取退票航段信息
	 * @param tpmxid
	 * @param shbh
	 * @return
	 */
	public List<JpHd> getJpHdByTpMxId(String tpmxid,String shbh){
		return jpHdServiceImpl.getJpHdByTpMxId(tpmxid, shbh);
	}
}
