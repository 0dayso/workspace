package cn.vetech.vedsb.jp.dao.jptpgl;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;

@JpMyBatisRepository
public interface JpTpdMxDao extends Mapper<JpTpdMx>{
	/**
	 * 改签单管理页面分页查询
	 * @param jpgqd
	 * @return
	 */
	List<Map<String,Object>> query(JpTpdMx jptpdmx);
	
	/**
	 * 获得总条数
	 * @param jpgqd
	 * @return
	 */
	int getPageCount(JpTpdMx jptpdmx);
	
	
	List<Map<String, Object>> getJpTpdMxList(String tpdh,String shbh);
	
	List<Map<String, Object>> getJpTpdJpInfo(String tpdh,String shbh);
	
	/**
	 * 获取tpdh对应的明细信息，做更新明细用
	 * @param tpdh
	 * @param shbh
	 * @return
	 */
	List<JpTpdMx>  getJpTpdMxByTpdh(String tpdh,String shbh);
	
	/**
	 * 通过退废明细id获取退废记录，0 没有 ， 大于0 表示有
	 * @param id
	 * @param shbh
	 * @return
	 */
	int getJpTpdMxJl(String id, String shbh);
	
	/**
	 * 采购对账退票待回款查询
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getcgdzDhkList(Map<String, Object> map);
	
	int getTotalCount(Map<String, Object> map);
}
