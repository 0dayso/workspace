package cn.vetech.vedsb.jp.dao.b2bsz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.b2bsz.JpZdcpB2bzh;

@JpMyBatisRepository
public interface JpZdcpB2bzhDao extends Mapper<JpZdcpB2bzh> {
    int deleteById(String id);
    int insert(JpZdcpB2bzh record);
    int updateById(JpZdcpB2bzh record);
    JpZdcpB2bzh selectById(String id);
    
    /**
     * 根据传入的对象进行分页数据查询
     * @param jpZdcpB2bzh
     * @param pageNum
     * @param pageSize
     * @param sortType
     * @return
     */
	List<Map<String, String>> queryJpzdcpb2bzhList(@Param(value = "jpZdcpB2bzh") JpZdcpB2bzh jpZdcpB2bzh, @Param(value = "pageNum") int pageNum, 
			@Param(value = "pageSize")int pageSize,@Param(value = "sortType") String sortType);
	
	/**
	 * 根据对象统计记录
	 * @param jpZdcpB2bzh
	 * @return
	 */
	int queryJpzdcpb2bzhCount(@Param(value = "jpZdcpB2bzh") JpZdcpB2bzh jpZdcpB2bzh);
	
	/**
	 * 根据航司二字码查询航司对应账号
	 * @param shbh
	 * @param hkgs
	 * @return
	 */
	@Select(value="select * from JP_ZDCP_B2BZH where shbh=#{param1} and hkgs=#{param2}")
	List<JpZdcpB2bzh> getZdcpB2bzhByHkgs(String shbh,String hkgs);
}