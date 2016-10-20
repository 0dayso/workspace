package cn.vetech.vedsb.jp.dao.jpcwgl;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpYsdzTc;
@JpMyBatisRepository
public interface JpYsdzTcDao extends Mapper<JpYsdzTc>{
	/**
	 * 检查是否已对账
	 * @param zbid
	 * @param shbh
	 * @return
	 */
	@Select(value="select count(1) from jp_ysdz_tc where zbid=#{param1} and shbh=#{param2}")
	public int genCompareCount(String zbid,String shbh);
	/**
	 * 检查当前日期之后是否已对账
	 * @param syrq
	 * @param shbh
	 * @param wdid
	 * @return
	 */
	@Select(value="select count(1) from jp_ysdz_tc where syrq>=to_date(#{param1},'yyyy-mm-dd hh24:mi:ss') and shbh=#{param2} and wdid=#{param3}")
	public int genAfterCompareCount(String syrq,String shbh,String wdid);
	
	/**
	 * 获取需要对账的未到账数据或漏单数据
	 * @param shbh
	 * @param wdid
	 * @param jglx
	 * @param syrqs
	 * @param syrqz
	 * @return
	 */
	@Select(value="select * from jp_ysdz_tc where shbh=#{param1} and wdid=#{param2} and jglx=#{param3} and syrq between to_date(#{param4},'yyyy-mm-dd hh24:mi:ss') and to_date(#{param5},'yyyy-mm-dd hh24:mi:ss')")
	public List<Map<String, Object>> genWdzOrLd(String shbh,String wdid,String jglx,String syrqs,String syrqz);
	/**
	 * 清除到账状态
	 * @param shbh
	 * @param wdid
	 * @param dzrq
	 */
	@Update(value="update jp_ysdz_tc set DZ_DZRQ=null,DZ_SFDZ='0',DZ_DZR=null,BY6=null where DZ_DZRQ between to_date(#{param3},'yyyy-mm-dd hh24:mi:ss') and to_date(#{param3},'yyyy-mm-dd hh24:mi:ss') and wdid=#{param2} and shbh=#{param1}")
	public void clearDzzt(String shbh,String wdid,String dzrq);
	/**
	 * 清除补单状态
	 * @param shbh
	 * @param wdid
	 * @param dzrq
	 */
	@Update(value="update jp_ysdz_tc set BD_BDRQ=null,BD_SFYBD='0',BD_BDR=null where BD_BDRQ between to_date(#{param3},'yyyy-mm-dd hh24:mi:ss') and to_date(#{param3},'yyyy-mm-dd hh24:mi:ss') and wdid=#{param2} and shbh=#{param1}")
	public void clearBdzt(String shbh,String wdid,String dzrq);
}