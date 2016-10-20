package cn.vetech.vedsb.jp.dao.jpddgl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;

@JpMyBatisRepository
public interface JpKhddCjrDao extends Mapper<JpKhddCjr>{
	
	List<Map<String,Object>> query(JpKhddCjr jpkhddcjr);
	
	
	int getPageCount(JpKhddCjr jpkhddcjr);
	
	
	Map<String,Object> detail(JpKhddCjr jpkhddcjr);
	

	JpKhddCjr getJpKhddCjrById(JpKhddCjr jpkhddcjr);
	
	List<JpKhddCjr> selectJpByDdbh(Map<String,Object> param);
	
	/**
	 * 判断乘机人中是否包含儿童
	 * @param param
	 * @return
	 */
	int isCjrEt(Map<String,Object> param);
	
	@Select("delete jp_khdd_cjr where ddbh in(select ddbh from jp_khdd where wbdh = #{param1} and shbh = #{param2})")
	public void deleteByWbdh(String wbdh,String shbh);
}
