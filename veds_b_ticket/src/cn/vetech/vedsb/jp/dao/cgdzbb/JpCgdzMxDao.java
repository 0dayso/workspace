package cn.vetech.vedsb.jp.dao.cgdzbb;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpCgdzMx;

/**
 * 机票采购对账
 * 
 * @author houya
 * 
 */
@JpMyBatisRepository
public interface JpCgdzMxDao extends Mapper<JpCgdzMx> {
	/**
	 * 获取的数据用来判断重复
	 * 
	 * @param shbh
	 * @param zbid
	 * @return
	 */
	@Select(value = "select id, pnrno,bigpnrno,tkno,bdfs,cg_ddbh from JP_CGDZ_MX where shbh=#{param1} and zbid=#{param2} ")
	List<Map<String, String>> getCgdzMxByZbid(String shbh, String zbid);

	/**
	 * 清理数据
	 * 
	 * @param shbh
	 * @param zbid
	 */
	@Delete(value = "delete JP_CGDZ_MX where  shbh=#{param1} and zbid=#{param2}")
	void deleteByZbid(String shbh, String zbid);

	/**
	 * 获取序列
	 * 
	 * @return
	 */
	@Select(value = "select seq_jp_cgdd_mx_id.nextval from dual")
	long seq_jp_cgdd_mx_id();

	/**
	 * 获取的数据用来标签显示数量
	 * 
	 * @param shbh
	 * @param zbid
	 * @return
	 */
	@Select(value = "select jglx,count(1) count from JP_CGDZ_MX where shbh=#{param1} and zbid=#{param2} group by jglx ")
	List<Map<String, Object>> getCgdzMxGroup(String shbh, String zbid);

}
