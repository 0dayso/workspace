package cn.vetech.vedsb.jp.dao.jpjpgl;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpSmfsSz;

@JpMyBatisRepository
public interface JpSmfsSzDao extends Mapper<JpSmfsSz>{

	/**
	 * <根据商户编号查找open票DETR方式列表>
	 * 
	 * @param shbh
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<JpSmfsSz> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	List<JpSmfsSz> queryJpSmfsszListByShbh(String shbh);

	/**
	 * <根据扫描类型删除>
	 * 
	 * @param smlx [参数说明]
	 * @author heqiwen
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void deleteBySmlx(String smlx,String shbh);

	/**
	 * <批量插入>
	 * 
	 * @param list0 [参数说明]
	 * @author heqiwen
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void batchInsert(List<JpSmfsSz> list);
	
	/**
	 * 查询已开启扫描设置的商户信息
	 * @param smlx 扫描类型0到期OPEN票扫描 1未乘机2异常改签
	 * @return JpSmfsSz 机票扫描方式数值 
	 */
	List<Map<String,Object>>queryJpSmfsszListBySmlx(String smlx,String cgly,String detrLx);
	/**
	 * 根据采购来源 、扫描方式查找所有对应的JpSmFsSz
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> queryJpSmfsszListByCgly(Map<String,Object> param);
	
	/**
	 * 调用存储过程根据商户编号获取需要扫描的OPEN票
	 * @param param
	 */
	void getOpenTicket(Map<String,Object> param);
}
