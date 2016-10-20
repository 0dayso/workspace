package cn.vetech.vedsb.jp.dao.pzzx;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzOut;

@JpMyBatisRepository
public interface JpPzOutDao extends Mapper<JpPzOut>{

    int insert(JpPzOut record);

	List<JpPzOut> getListByAll(Map<String, Object> param);

	int selectCountByAll(Map<String, Object> param);

	void batchInsertJpPzOut(List<JpPzOut> list);

	int queryJpPzOutByNo(String startno, String endno);


}
