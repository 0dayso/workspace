package cn.vetech.vedsb.jp.dao.jpzwgl;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqDr;
@JpMyBatisRepository
public interface JpTjsqDrDao extends Mapper<JpTjsqDr>{
	
	public List<JpTjsqDr> getjpTjsqDrList(JpTjsqDr jptjsqdr);
	/**
	 * 查询待导入正式队列的申请单
	 * @return
	 */
	List<JpTjsqDr> getDdrDlList(JpTjsqDr jpTjsqDr);
	/**
	 * 根据申请单号和商户编号获取追位导入对象
	 * @param shbh
	 * @param sqdh
	 * @return
	 */
	@Select(value="select * from jp_tjsq_dr where shbh=#{param1} and sqdh=#{param2}")
	JpTjsqDr getjptjsqdrList(String shbh,String sqdh);
}
