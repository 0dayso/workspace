package cn.vetech.vedsb.jp.dao.jpjpgl;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpSmSjSz;

@JpMyBatisRepository
public interface JpSmSjSzDao extends Mapper<JpSmSjSz> {
	/**
	 * 根据商户编号查询对应的扫描时间设置
	 * @param shbh
	 * @return
	 */
	JpSmSjSz queryByShbh(String shbh);
}
