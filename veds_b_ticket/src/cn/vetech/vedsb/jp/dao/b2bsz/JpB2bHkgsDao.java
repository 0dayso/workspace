package cn.vetech.vedsb.jp.dao.b2bsz;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgs;


@JpMyBatisRepository
public interface JpB2bHkgsDao extends Mapper<JpB2bHkgs> {
	
    int insert(JpB2bHkgs record);
    
	JpB2bHkgs selectByHkgs_Bca(String shbh, String hkgs, String bca);
	
	void updateByhkgs(JpB2bHkgs record);
}