package cn.vetech.vedsb.jp.dao.jptpgl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;

@JpMyBatisRepository
public interface JpTpdDao extends Mapper<JpTpd>{
	/**
	 * 退票单管理页面分页查询
	 * @param jpgqd
	 * @return
	 */
	List<Map<String,Object>> query(Map<String, Object> param);
	
	/**
	 * 获得总条数
	 * @param jpgqd
	 * @return
	 */
	int getPageCount(Map<String, Object> param);
	
	
	Map<String,Object> detail(JpTpd jptpd);
	
	
	JpTpd getJpTpdByTpdh(String tpdh,String shbh);
	
	/**
	 * 获取淘宝代购订单信息
	 * @param tpdh
	 * @param shbh
	 * @return
	 */
	Map<String,Object> getTaobaoDg(String shbh,String tpdh);
	
	/**
	 * 通过商户编号获取淘宝代购订单信息
	 * @param tpdh
	 * @param shbh
	 * @return
	 */
	List<JpTpd> getTaobaoDgByShbh(Map<String, Object> param);
	
	/**
	 * <根据订单编号和商户编号查找退票单>
	 * 
	 * @param map
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<JpTpd> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	List<JpTpd> queryTpdByDdbh(String ddbh,String shbh);
	
	/**
	 * 查询退票申请信息
	 * @param paramsMap
	 * @return
	 */
	List<Map<String,Object>> getJptpdForApply(Map<String, Object> paramsMap);
	
	/**
	 * 通过退票单号获取明细汇总
	 * @param tpdh  退票单号
	 * @param shbh  商户编号
	 * @return
	 */
	JpTpd getJpTpdMxSum(String tpdh,String shbh);
	/**
	 * 用于查询（网店）退票单复核操作所需参数
	 * @param tpdh  退票单号
	 * @param shbh  商户编号
	 * @return
	 */
	Map<String,Object> selectTpFhByTfdh(String tpdh,String shbh);
	
	
	/**
	 * 查询历史退票数据
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getHistoryTpList(Map<String, Object> map);
	
	/**
	 * 根据条件查询历史退票数据
	 * @param map
	 * @return
	 */
	Map<String, Object> getjptpdobject(Map<String, Object> map);
	
	/**
	 * 修改保存退票单数据
	 * @param map
	 */
	void saveTpdHistory(Map<String, Object> map);

	/**
	 * 退票出票控制台，待处理，已提交未完成，已完成的数量
	 * @param param
	 * @return
	 */
	Map<String,Object> tpCpkztCount(Map<String, Object> param);
	
	/**
	 * 退票出票控制台，采购来源Count(BSP,OP...)
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> tpCpkztCgLyCount(Map<String, Object> param);
	
	/**
	 * 退票出票控制台，查询
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> tpCpkztQuery(Map<String, Object> param);
	/**
	 * 退票出票控制台，查询记录数
	 * @param param
	 * @return
	 */
	int tpCpkztQueryCount(Map<String,Object> param);
	
	/**
	 * 退票报表查询
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> TpBbQuery(Map<String, Object> param);

	/**
	 * 根据外部单号找对账的业务订单
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> genDzDataByWbdh(Map<String, Object> map);
	
	/**
	 * 根据平台退票单号查询退票单
	 * @param shbh
	 * @param cgtpdh
	 * @param cgdw
	 * @return
	 */
	@Select(value="select * from jp_tpd where cg_tpdh=#{param1} and cgdw=#{param2} and cgly = 'OP' and cg_tpzt <> '9' ")
	JpTpd getTpdByCgtpdh(String cgtpdh,String cgdw);
	
	/**
	 * 根据订单号,采购退票状态以及票号查询退废票单
	 * @param param
	 * @return
	 */
	JpTpd getJpTpdByddbh(Map<String, Object> param);
	
	/**
	 * 退废单入库前根据票号检查是否重复
	 * @param param
	 * @return
	 */
	Map<String,String> checkTpdByPh(Map<String,Object> param);
	
	/**
	 * 获取wbtpzt=1,cgTpzt=3(采购办理完成)退票单
	 * @param wdid 网店id
	 * @param shbh 商户编号
	 * @return
	 */
	@Select(value="select * from jp_tpd t where wdid=#{param1} and shbh=#{param2} and cg_tpzt='3' and wbtpzt in ('1','2','3') and xs_tpzt in ('0','1') and ddsj > sysdate-2 and not exists (select 1 from jp_pt_log l where t.wbdh = l.tfid and ywlx=#{param3} )")
	List<JpTpd> getCgblwcTpd(String wdid, String shbh, String ywlx);
	
	/**
	 * 获取未审核、未办理；需要同步淘宝退票状态的退票单集合
	 * @param wdid 网店id
	 * @param shbh 商户编号
	 * @return
	 */
	@Select(value="select distinct(wbdh) from jp_tpd where wdid=#{param1} and shbh=#{param2} and xs_tpzt in ('0','1') and ddsj > sysdate-1")
	List<String> getTbztTpdWbdh(String wdid, String shbh);
	/**
	 * 根据退票单wbdh修改退票单信息
	 * @return
	 */
	int updateTpdByWbdh(JpTpd jpTpd);
	
	List<JpTpd> getTpdByWbdh(Map<String, Object> param);
}
