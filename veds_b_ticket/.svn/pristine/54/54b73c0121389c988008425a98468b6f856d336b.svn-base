package cn.vetech.vedsb.jp.dao.b2bsz;

import java.util.List;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgsxx;
@JpMyBatisRepository
public interface JpB2bHkgsxxDao extends Mapper<JpB2bHkgsxx> {
	
    int deleteById(String id);
    int updateById(JpB2bHkgsxx record);
    JpB2bHkgsxx selectById(String id);
    /**
     * /根据航空公司取出航空公司支持的支付方式
     * @param hkgs
     * @param bca
     * @return
     */
    JpB2bHkgsxx selectByHkgs_Bca(String hkgs,String bca);
    List<JpB2bHkgsxx>  selectByBca(String bca);
   
}