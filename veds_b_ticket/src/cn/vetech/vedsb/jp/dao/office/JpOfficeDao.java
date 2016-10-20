package cn.vetech.vedsb.jp.dao.office;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.office.JpOffice;

@JpMyBatisRepository
public interface JpOfficeDao extends Mapper<JpOffice> {

	JpOffice getJpOffice(JpOffice t);

	List<JpOffice> selectJpOfficeByShbh(String shbh);

	/**
	 *  通过Office找到记录
	 * @param officeid
	 * @return
	 */
	List<JpOffice> selectJpOfficeByOffice(@Param("officeid") String officeid);
	
	
	/**
	 * 通过商户编号获取对应的OFFICEID
	 */
	@Select("select distinct(officeid) from jp_office where shbh=#{param1}")
	List<String> selectOfficeIdByShbh(String shbh);
}