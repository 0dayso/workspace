package cn.vetech.vedsb.jp.dao.jpzdcp;

import java.util.List;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpCssz;

@JpMyBatisRepository
public interface JpZdcpCsszDao extends Mapper<JpZdcpCssz>{
	List<JpZdcpCssz> selectAllJpZdcpCssz(JpZdcpCssz t);
	
    int selectAllJpZdcpCsszCount(JpZdcpCssz t);
}