package cn.vetech.vedsb.jp.dao.b2bsz;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bDlzh;


@JpMyBatisRepository
public interface JpB2bDlzhDao extends Mapper<JpB2bDlzh> {
    int deleteById(String id);
    int insert(JpB2bDlzh record);
    int updateByIdAndBca(JpB2bDlzh record);
    
    @Delete(value="delete JP_B2B_DLZH where id=#{param1 } and bca=#{param2 } and shbh = #{param3}")
    void delDlzh(String id, String bca, String shbh);
    
    @Select(value="select * from JP_B2B_DLZH where upper(zh)=upper(#{param1}) and hkgs = #{param2} and bca = #{param3} and shbh = #{param4} and yhbh = #{param5}")
    List<JpB2bDlzh> getDlzhListByZh(String zh, String hkgs, String bca, String shbh,String yhbh);
    
    
    List<JpB2bDlzh> getDlzhListByBean(JpB2bDlzh record);
    
    /**
     * 登录账号分页查询
     * @param record
     * @return
     */
    List<JpB2bDlzh> query(JpB2bDlzh record); 
} 