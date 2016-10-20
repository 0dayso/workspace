package cn.vetech.vedsb.jp.dao.jpztjk;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpztjk.Jpkpzt;

@JpMyBatisRepository
public interface JpkpztDao extends Mapper<Jpkpzt>{

	/**
	 * 查询到期OPEN票数据
	 * @param jpkpzt
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	List<Map<String, String>> queryJpkpztList(@Param(value = "jpkpzt") Jpkpzt jpkpzt, @Param(value = "pageNum")int pageNum, 
			@Param(value = "pageSize")int pageSize,@Param(value = "sortType")String sortType);

	List<Map<String, String>> queryJpkpztSlist(@Param(value = "jpkpzt") Jpkpzt jpkpzt, @Param(value = "pageNum")int pageNum, 
			@Param(value = "pageSize")int pageSize,@Param(value = "sortType")String sortType);
	
	/**
	 * 统计到期OPEN票记录
	 * @param jpkpzt
	 * @return
	 */
	int queryJpkpztCount(@Param(value = "jpkpzt") Jpkpzt jpkpzt);

	/**
	 * 根据传入的对象进行批量办理
	 * @param jpkpzt
	 * @return
	 */
	int updateByTkno(Jpkpzt jpkpzt);

	/**
	 * 根据票号和商户编号批量删除
	 * @param tkno
	 * @param shbh
	 */
	@Select(value="delete from jp_kpzt where tkno=#{param1 } and shbh=#{param2 }")
	void delByTknoAndShbh(String tkno, String shbh);
	
}
