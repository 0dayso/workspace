package cn.vetech.vedsb.jp.dao.b2bsz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzh;

@JpMyBatisRepository
public interface JpB2bZfzhDao extends Mapper<JpB2bZfzh> {
    int deleteById(String id);
    @Override
	int insert(JpB2bZfzh record);
    int updateById(JpB2bZfzh record);
    
    /**
     * 根据id和shbh查询数据
     * @param id
     * @param shbh
     * @return
     */
    @Select(value="select * from JP_B2B_ZFZH where id=#{param1 } and shbh=#{param2 }")
	JpB2bZfzh queryByIdAndShbh(String id, String shbh);
	
	/**
	 * 根据传入的参数查询数据
	 * @param zfzh
	 * @param shbh
	 * @param string
	 * @return
	 */
	@Select(value="select * from JP_B2B_ZFZH where zfzh=#{param1 } and shbh=#{param2 } and zflx=#{param3 }")
	List<JpB2bZfzh> queryByZflxAndZfzh(String zfzh, String shbh, String zflx);
	
	/**
	 * 根据传入的map查询数据
	 * @param params
	 * @return
	 */
	List<JpB2bZfzh> queryByMap(Map<String, Object> params);
	/**
	 * 根据传入的商户编号和支付类型查询
	 * @param param
	 * @return
	 */
	List<JpB2bZfzh> queryListByShbhAndZflx(String shbh,String zflx);
	
}