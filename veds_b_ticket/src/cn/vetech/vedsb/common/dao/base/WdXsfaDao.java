package cn.vetech.vedsb.common.dao.base;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.common.entity.base.WdXsfa;

public interface WdXsfaDao extends Mapper<WdXsfa> {
	@Select(value="select * from wd_xsfa where wdid=#{param1} and shbh=#{param2}")
	public List<WdXsfa> findByWdidAndShbh(String wdid,String shbh);
	
	public List<WdXsfa> queryPage(WdXsfa wd_xsfa);

	public List<?> getWdXsfaList(WdXsfa wd_xsfa);

	public int getTotalCount(WdXsfa wd_xsfa);
	/**
	 * 修改方案状态
	 * @param uptBean
	 */
	public void uptZt(WdXsfa uptBean);
	
	public WdXsfa getWdXsfaByBean(WdXsfa xsfa);

	public List<WdXsfa> getWdXsfaList2(WdXsfa temp);

	/**
	 * 修改方案密码
	 * @param faid
	 * @param newpswd
	 * @param xgsj 
	 * @param xgyh 
	 */
	@Update(value="update wd_xsfa set famm=#{param2},xgyh=#{param3},xgsj=#{param4},czpt='B' where id=#{param1}")
	public void updateFamm(String faid, String newpswd, String xgyh, Date xgsj);

	public void batchAllUptZt(WdXsfa wdxsfa);

}
