package cn.vetech.vedsb.jp.dao.cpsz;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.cpsz.JpZdtfpGzsz;

@JpMyBatisRepository
public interface JpZdtfpGzszDao extends Mapper<JpZdtfpGzsz> {

	/**
	 * <分页查询退废票的list>
	 * 
	 * @param zdtfp
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<JpZdtfpGzsz> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	List<Map<String,Object>> getQueryList(JpZdtfpGzsz zdtfp);

	/**
	 * <查询自动退废票的总记录数>
	 * 
	 * @param zdtfp
	 * @return [参数说明]
	 * @author heqiwen
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	int getTotalCount(JpZdtfpGzsz zdtfp);

	/**
	 * <审核或启用或暂停退废票>
	 * 
	 * @param param [参数说明]
	 * @author heqiwen
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void updateTfpZt(Map<String, Object> param);

	/**
	 * <删除退废票规则>
	 * 
	 * @param tpfp [参数说明]
	 * @author heqiwen
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void deleteChange(Map<String,Object> tpfp);

	/**
	 * 获取自动退票规则
	 * @param zdtfp
	 * @return
	 */
	public List<JpZdtfpGzsz> getZdTfpGzsz(JpZdtfpGzsz zdtfp);
	
}
