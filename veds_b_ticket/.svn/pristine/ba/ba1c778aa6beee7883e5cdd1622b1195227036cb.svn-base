package cn.vetech.vedsb.jp.dao.jpddgl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddKz;

@JpMyBatisRepository
public interface JpKhddKzDao extends Mapper<JpKhddKz>{
	/**
	 * 改签单管理页面分页查询
	 * @param jpgqd
	 * @return
	 */
	List<Map<String,Object>> query(JpKhddKz jpkhddkz);
	
	/**
	 * 获得总条数
	 * @param jpgqd
	 * @return
	 */
	int getPageCount(JpKhddKz jpkhddkz);
	
	
	Map<String,Object> detail(JpKhddKz jpkhddkz);
	
	/**
	 * 
	 * @param jptpd
	 * @return
	 */
	JpKhddKz getJpKhddKzByDdbh(JpKhddKz jpkhddkz);
	
	List<JpKhddKz> selectJpByDdbh(Map<String,Object> param);
	
	int updateJpZtByDdbh(Map<String,Object> param);
	
	int updateKzByCpdh(JpKhddKz kz);
	
	@Select("delete jp_khdd_kz where ddbh in(select ddbh from jp_khdd where wbdh = #{param1} and shbh = #{param2})")
	public void deleteByWbdh(String wbdh,String shbh);
}
