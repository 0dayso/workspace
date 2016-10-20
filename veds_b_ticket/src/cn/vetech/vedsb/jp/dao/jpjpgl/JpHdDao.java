package cn.vetech.vedsb.jp.dao.jpjpgl;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpHd;

@JpMyBatisRepository
public interface JpHdDao extends Mapper<JpHd>{
	/**
	 * 改签单管理页面分页查询
	 * @param jpgqd
	 * @return
	 */
	List<Map<String,Object>> query(JpHd jphd);
	
	/**
	 * 获得总条数
	 * @param jpgqd
	 * @return
	 */
	int getPageCount(JpHd jphd);
	
	
	Map<String,Object> detail(JpHd jphd);
	
	/**
	 * 
	 * @param jptpd
	 * @return
	 */
	JpHd getJpHdById(JpHd jphd);
	
	/**
	 * 通过退票单号取退票航段信息
	 * @param tpdh
	 * @param shbh
	 * @return
	 */
	List<JpHd> getJpHdByTpdh(String tpdh,String shbh);
	

	/**
	 * 通过退票明细ID取退票航段信息
	 * @param tpmxid
	 * @param shbh
	 * @return
	 */
	List<JpHd> getJpHdByTpMxId(String tpmxid,String shbh);
	
	
	JpHd getJpHdByDdhdid(JpHd jphd);

	JpHd getJpHdByDdhdidAndTkno(JpHd jphd);

	/**
	 * <根据商户编号和票号查找航段>
	 * 
	 * @param jphd
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<JpHd> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	List<JpHd> queryJpHdByTkno(JpHd jphd);

}
