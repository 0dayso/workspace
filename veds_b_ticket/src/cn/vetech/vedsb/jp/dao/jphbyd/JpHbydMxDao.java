package cn.vetech.vedsb.jp.dao.jphbyd;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jphbyd.JpHbydMx;

@JpMyBatisRepository
public interface JpHbydMxDao extends Mapper<JpHbydMx>{
	
	/**
	 * 根据ydid修改短信是否发送
	 * @param id
	 */
	@Select(value="update jp_hbyd_mx set sfyfdx='1' where ydid=#{param1 }")
	void updateByYdid(String id);

	/**
	 * 根据ydid修改航变异动明细数据
	 * @param id
	 */
	@Select(value="update jp_hbyd_mx set cl_zt=#{param1 },cl_datetime=#{param2},cl_userid=#{param3},cl_deptid=#{param4},cl_compid=#{param5},cl_bz=#{param6} where ydid=#{param7 }")
	void updateByydid(String clzt, String cldatetime, String cluserid, String cldeptid, String clcompid, String clbz, String ydid);

	/**
	 * 根据
	 * @param clcompid
	 * @param ydid
	 */
	@Select(value="update jp_hbyd_mx set cl_zt='0',cl_datetime=' ',cl_userid=' ',cl_deptid=' ',cl_compid=' ',cl_bz=' ' where ydid=#{param1 }")
	void updateQxClByYdid(String ydid);

}
