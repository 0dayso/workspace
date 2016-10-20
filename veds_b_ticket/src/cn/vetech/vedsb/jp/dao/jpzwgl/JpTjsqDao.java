package cn.vetech.vedsb.jp.dao.jpzwgl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsq;
@JpMyBatisRepository
public interface JpTjsqDao extends Mapper<JpTjsq>{
	/**
	 * 根据机票订单号和商户编号查询没有取消的追位申请单数量
	 * @return
	 */
	@Select(value="select count(1) all_num from jp_tjsq where ddbh=#{param1} and shbh =#{param2} and sq_zt <> '5' and sq_zt<> '6'")
	int countWqxTjsq(String ddbh,String shbh);
	
	
	/**
	 * 查询追位成功订单(jp_tjsq和jp_tjsq_cjr表连表查询,返回Map)
	 * @param jptjsq
	 * @return
	 */
	List<Map<String, Object>> getzwSuccList(Map<String, Object> jptjsq);
	
	/**
	 * 查询待审核的追位订单
	 * @param shzw
	 * @return
	 */
	List<Map<String, Object>> getshZwddList(Map<String, Object> shzw);
	
	/**
	 * 追位成功未处理
	 * @param cgwcl
	 * @return
	 */
	List<Map<String, Object>> getzwcgwclList(Map<String, Object> cgwcl);
	
	/**
	 * 追位成功采用
	 * @param cgcy
	 * @return
	 */
	List<Map<String, Object>> getzwcgcyList(Map<String, Object> cgcy);
	
//	/**
//	 * 查询所有追位成功已采用订单
//	 * @param cgcy
//	 * @return
//	 */
//	List<Map<String, Object>> getAllzwcgcyList(Map<String, Object> cgcy);
	
	/**
	 * 追位成功取消
	 * @param cgqx
	 * @return
	 */
	List<Map<String, Object>> getzwcgqxList(Map<String, Object> cgqx);
	
	/**
	 * 追位成功预留
	 * @param cgyl
	 * @return
	 */
	List<Map<String, Object>> getzwcgylList(Map<String, Object> cgyl);
	
	/**
	 * 追位成功预留返回结果数
	 * @param total
	 * @return
	 */
	int getzwcgylTotalCount(Map<String, Object> total);
	/**
	 * 追位成功取消返回结果数
	 * @param total
	 * @return
	 */
	int getzwcgqxTotalCount(Map<String, Object> total);
	/**
	 * 追位成功采用返回结果数
	 * @param total
	 * @return
	 */
	int getzwcgcyTotalCount(Map<String, Object> total);
	/**
	 * 追位成功未处理返回结果数
	 * @param total
	 * @return
	 */
	int getcgwclTotalCount(Map<String, Object> total);
	/**
	 * 追位成功订单返回结果数
	 * @param jptjsq
	 * @return
	 */
	int getTotalCount(Map<String, Object> total);
	
	/**
	 * 审核追位订单返回结果数
	 * @param jptjsq
	 * @return
	 */
	int getshTotalCount(Map<String, Object> total);

	/**
	 * 获取需要提交追位系统的队列
	 * @param jpTjsq
	 * @return
	 */
	List<JpTjsq> getDtjDlList(JpTjsq jpTjsq);
	/**
	 * 获取机票订单已取消且未取消的追位单
	 * @param sqdate
	 * @return
	 */
	@Select(value="select * from jp_tjsq t where exists(select 1 from jp_khdd where ddbh=t.ddbh and (ddzt='4' or ddzt='5')) and t.sq_datetime>#{param1} and t.shbh=#{param2} and t.sq_zt='1'")
	List<JpTjsq> getDqxForJpddqx(Date sqdate,String shbh);
	/**
	 * 出票后取消原追位单(申请时间<出票时间)
	 * @param sqdate
	 * @return
	 */
	@Select(value="select * from jp_tjsq t where exists(select 1 from jp_jp where ddbh=t.ddbh and CP_DATETIME>t.sq_datetime) and t.shbh=#{param2} and t.sq_datetime>#{param1} and t.sq_zt='1'")
	List<JpTjsq> getDqxForYcp(Date sqdate,String shbh);
	/**
	 * 申请退废后取消原追位单
	 * @param sqdate
	 * @param tfzt
	 * @return
	 */
	@Select(value="select * from jp_tjsq t where exists(select 1 from jp_tpd where ddbh=t.ddbh and XS_TPZT<>'9'}) and t.shbh=#{param2} and t.sq_datetime>#{param1} and t.sq_zt='1'")
	List<JpTjsq> getDqxForTpsq(Date sqdate,String shbh);
	/**
	 * 申请退废并复核后取消原追位单
	 * @param sqdate
	 * @param tfzt
	 * @return
	 */
	@Select(value="select * from jp_tjsq t where exists(select 1 from jp_tpd where ddbh=t.ddbh and XS_TPZT<>'0' and XS_TPZT<>'9'}) and t.shbh=#{param2} and t.sq_datetime>#{param1} and t.sq_zt='1'")
	List<JpTjsq> getDqxForTpfh(Date sqdate,String shbh);
	/**
	 * 申请改签后取消原追位单
	 * @param sqdate
	 * @param tfzt
	 * @return
	 */
	@Select(value="select * from jp_tjsq t where exists(select 1 from jp_gqd where ddbh=t.ddbh and GQZT<>'7' and GQZT<>'8') and t.shbh=#{param2} and t.sq_datetime>#{param1} and t.sq_zt='1'")
	List<JpTjsq> getDqxForGqsq(Date sqdate,String shbh);
	/**
	 * 确认改签后取消原追位单
	 * @param sqdate
	 * @param tfzt
	 * @return
	 */
	@Select(value="select * from jp_tjsq t where exists(select 1 from jp_gqd where ddbh=t.ddbh and GQZT<>'0' and GQZT<>'7' and GQZT<>'8') and t.shbh=#{param2} and t.sq_datetime>#{param1} and t.sq_zt='1'")
	List<JpTjsq> getDqxForGqfh(Date sqdate,String shbh);
	/**
	 * 获取已失效的追位单
	 * @param sqdate
	 * @return
	 */
	@Select(value="select * from jp_tjsq t where t.yxq>#{param1} and t.shbh=#{param2} and t.sq_zt='1'")
	List<JpTjsq> getDqxForYsx(Date sqdate,String shbh);
	/**
	 * 未出票过了指定时间后取消原追位单
	 * @param sqdate
	 * @param tfzt
	 * @return
	 */
	@Select(value="select * from jp_tjsq t where exists(select 1 from jp_khdd where ddbh=t.ddbh and (ddzt='0' or ddzt='1') and cfrq<=#{param2) and t.shbh=#{param3} and t.sq_datetime>#{param1} and t.sq_zt='1'")
	List<JpTjsq> getDqxForYdQf(Date sqdate,Date cfsj,String shbh);
	
	/**
	 * 已出票过了指定时间后取消原追位单
	 * @param sqdate
	 * @param tfzt
	 * @return
	 */
	@Select(value="select * from jp_tjsq t where exists(select 1 from jp_jp where ddbh=t.ddbh and cfrq<=#{param2) and t.shbh=#{param3} and t.sq_datetime>#{param1} and t.sq_zt='1'")
	List<JpTjsq> getDqxForCpqf(Date sqdate,Date cfsj,String shbh);
	/**
	 * 获取未追位完成的且已出票的追位单
	 * @param sqdate
	 * @param shbh
	 * @return
	 */
	@Select(value="select * from jp_tjsq t where exists(select 1 from jp_jp where ddbh=t.ddbh) and t.shbh=#{param2} and t.sq_datetime>#{param1} and t.sq_zt='1'")
	List<JpTjsq> getYcpWwc(Date sqdate,String shbh);
	
	/**
	 * 根据商户编号和申请单号获取追位订单
	 * @param shbh
	 * @param sqdh
	 * @return
	 */
	@Select(value="select * from jp_tjsq where shbh=#{param1} and sqdh=#{param2}")
	JpTjsq gettjsq(String shbh,String sqdh);
	
	/**
	 * 批量取消时根据查询条件返回追位订单list
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> getzwdlList(Map<String, Object> map);

}
