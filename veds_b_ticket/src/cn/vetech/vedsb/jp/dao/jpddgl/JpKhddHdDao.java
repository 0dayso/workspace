package cn.vetech.vedsb.jp.dao.jpddgl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;

@JpMyBatisRepository
public interface JpKhddHdDao extends Mapper<JpKhddHd>{
	/**
	 * 改签单管理页面分页查询
	 * @param jpgqd
	 * @return
	 */
	List<Map<String,Object>> query(JpKhddHd jpkhddhd);
	
	/**
	 * 获得总条数
	 * @param jpgqd
	 * @return
	 */
	int getPageCount(JpKhddHd jpkhddhd);
	
	
	Map<String,Object> detail(JpKhddHd jpkhddhd);
	
	/**
	 * 
	 * @param jptpd
	 * @return
	 */
	JpKhddHd getJpHdById(JpKhddHd jpkhddhd);
	
	List<JpKhddHd> selectJpByDdbh(Map<String, Object> param);

	/**
	 * 根据航班号和出发日期查询航班信息
	 * @param hbh
	 * @param cfrq
	 * @return
	 */
	@Select(value="select distinct cfsj,cfcity,ddcity,ddsj from JP_KHDD_HD where (xs_hbh=#{param1 } or cg_hbh=#{param1 }) and cfsj=#{param2 }")
	JpKhddHd queryHbxxByHbhCfrq(String hbh, String cfrq);

	/**
	 * 根据航班号,出发日期和航程查询
	 * @param hbh
	 * @param cfrq
	 * @param hc
	 * @return
	 */
	@Select(value="select distinct ddbh,cfsj,cfcity,ddcity,ddsj from JP_KHDD_HD where (xs_hbh=#{param1 } or cg_hbh=#{param1 }) and cfsj =#{param2 } and cfcity||ddcity = #{param3 }")
	List<JpKhddHd> queryHbxxDdbh(String hbh, String cfrq, String hc);
	
	@Select("delete jp_khdd_hd where ddbh in(select ddbh from jp_khdd where wbdh = #{param1} and shbh = #{param2})")
	public void deleteByWbdh(String wbdh,String shbh);
}
