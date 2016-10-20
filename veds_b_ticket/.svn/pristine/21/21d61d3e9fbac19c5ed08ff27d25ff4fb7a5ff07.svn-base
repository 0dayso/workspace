package cn.vetech.vedsb.jp.dao.jpcwgl;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpYsdz;
@JpMyBatisRepository
public interface JpYsdzDao extends Mapper<JpYsdz>{

	/**
	 * 查询一个网店一段时间生成的对账数据
	 * @param shbh
	 * @param wdid
	 * @param dzrqs
	 * @param dzrqz
	 * @return
	 */
	@Select(value="select dzrq,dz_zt from jp_ysdz where shbh=#{param1} and WDID=#{param2} and DZRQ between #{param3} and #{param4}")
	public List<Map<String,Object>> checkFirstDaysCompare(String shbh,String wdid,String dzrqs,String dzrqz); 
	/**
	 * 更新漏单的补单状态
	 * @param param
	 */
	public void updateLdzt(Map<String,Object> param);
	/**
	 * 查询对账结果
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> genDbresult(Map<String,Object> param);
	/**
	 * 查询对账结果总条数
	 * @param param
	 * @return
	 */
	int genDbresultTotal(Map<String,Object> param);
	/**
	 * 查询金额总计
	 * @param param
	 * @return
	 */
	Map<String,Object> genDbresultSum(Map<String,Object> param);
	/**
	 * 统计各类型数量
	 * @param param
	 * @return
	 */
	Map<String,Object> genDbresultCount(Map<String,Object> param);
	
	public List<Map<String,Object>> searchWdz(Map<String,Object> map);
	public List<Map<String,Object>> searchLd(Map<String,Object> map);
	public int getTotalCount(Map<String,Object> map);
	
}