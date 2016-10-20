package cn.vetech.vedsb.jp.dao.jpzwgl;
import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqZdsc;
@JpMyBatisRepository
public interface JpTjsqZdscDao extends Mapper<JpTjsqZdsc>{
	/**
	 * 未进入追位队列的订单
	 * @param wjzwdl
	 * @return
	 */
	List<Map<String, Object>> getwjzwdlList(Map<String, Object> wjzwdl);
	
	/**
	 * 未进追位队列的总结果
	 * @param wj
	 * @return
	 */
	int getwjTotalCount(Map<String, Object> wj);
	
	List<JpTjsqZdsc> getDdrDlList(JpTjsqZdsc jpTjsqZdsc);
}