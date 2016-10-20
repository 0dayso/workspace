package cn.vetech.vedsb.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.common.entity.JpShwdkz;


/**
 * 商户网店类型和数量控制表Dao
 * @author lishanliang
 *
 */
public interface JpShwdkzDao extends Mapper<JpShwdkz>{
	
	/**
	 * 根据商户编号来删除本商户下的所有网店控制数据
	 * @param shbh
	 */
	@Delete(value="delete from sh_wdkz where shbh=#{param1}")
	public void deleteByShbh(String shbh);
	/**
	 * 根据商户编号查找该商户下所有的网店控制类型
	 * @param shbh
	 * @return
	 */
	@Select(value="select * from sh_wdkz where shbh=#{param1}")
	public List<JpShwdkz> getShwdkzByShbh(String shbh);
}
