package cn.vetech.vedsb.jp.dao.jpjpgl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;

@JpMyBatisRepository
public interface JpJpDao extends Mapper<JpJp>{
	/**
	 * 改签单管理页面分页查询
	 * @param jpgqd
	 * @return
	 */
	List<Map<String,Object>> query(JpJp jpjp);
	
	/**
	 * 获得总条数
	 * @param jpgqd
	 * @return
	 */
	int getPageCount(JpJp jpjp);
	
	
	Map<String,Object> detail(JpJp jpjp);
	
	/**
	 * 
	 * @param jptpd
	 * @return
	 */
	JpJp getJpJpById(JpJp jpjp);
	
	List<JpJp> selectJpByDdbh(Map<String,Object> param);
	
	/**
	 * 获得改签申请机票列表
	 * @param jpjp
	 * @return
	 */
	List<Map<String,Object>> queryForGqsq(JpJp jpjp);
	
	JpJp getJpByTkno(JpJp jpjp);
	
	List<JpJp> getJpByCjr(JpJp jpjp);
	
	/**
	 * 根据订单编号和出票时间止找已出票的票数
	 * @param ddbh
	 * @param shbh
	 * @param cpsjz
	 * @return
	 */
	@Select(value="select count(1) count  from jp_jp where ddbh= #{param1} and shbh = #{param2} and CP_DATETIME<to_date(#{param3}, 'yyyy-mm-dd hh24:mi:ss')")
	int countYcpByCptmDdbh(String ddbh,String shbh,String cpsjz);

	/**
	 * <分页查询 报表中心 出票报表的数据集合>
	 * 
	 * @param jp
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<Map<String,Object>> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	List<Map<String, Object>> queryCpbbList(@Param(value="jp")JpJp jp,@Param(value="hkgs")String hkgs,@Param(value="wbdh")String wbdh,
			@Param(value="skzt")String skzt);

	/**
	 * <分页查询 报表中心  出票报表的数据总个数>
	 * 
	 * @param jp
	 * @return [参数说明]
	 * @author heqiwen
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	int queryCpbbListCount(@Param(value="jp")JpJp jp,@Param(value="hkgs")String hkgs,@Param(value="wbdh")String wbdh,
			@Param(value="skzt")String skzt);
	
	/**
	 * 历史数据调整机票正常单调整列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getHistoryList(Map<String, Object> map);
	
	
//	/**
//	 * 历史数据调整机票正常单出票时间修改
//	 * @param map
//	 * @return
//	 */
//	List<Map<String, Object>> getHistoryCpsjEdit(Map<String, Object> map);
	
	Map<String, Object> getjpobject(Map<String, Object> map);
	
	void saveHistory(Map<String, Object> map);
	
	/**
	 * 采购对账查询bspet票电商数据
	 */
	List<Map<String, Object>> searchBspet(Map<String, Object> map);

	/**
	 * <根据订单编号查找机票>
	 * 
	 * @param jp
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<JpJp> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	List<JpJp> queryJpListByDdbh(JpJp jp);

	/**
	 * <确认修改出票信息>
	 * 
	 * @param jp
	 * @return [参数说明]
	 * @author heqiwen
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void updateJpcp(JpJp jp);
	
	public List<Map<String, Object>> getXcdListByDdbh(Map<String, Object> map);
	
	List<Map<String, Object>> getjpjpByddbh(Map<String, Object> map);
	
	@Select(value="select * from jp_jp jp where jp.ddbh in(select jk.ddbh from jp_khdd jk where jk.wbdh=#{param1} and jk.shbh=#{param2})")
	public List<JpJp> getjpjpBywbbh(String wbdh,String shbh);
	
	/**
	 * <全自动出票匹配出票规则>
	 * 
	 * @param param [参数说明]
	 */
	void qzdcp(Map<String, String> param);

	/**
	 * <根据销售PNR查找机票>
	 * 
	 * @param jp
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<JpJp> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	List<Map<String, Object>> queryJpListByXspnr(Map<String, Object> map);
	
	
	@Select(value="select ddbh,tkno,hc,cjr,xs_pnr_no from Jp_Jp where wbdh=#{param1} and wdid=#{param2} and shbh=#{param3}")
	List<JpJp> getJpjpListByWbdh(String wbdh,String wdid,String shbh);
	/**
	 * 根据商户编号和票号查询对应的jpjp
	 * @param shbh
	 * @param tkno
	 * @return
	 */
	JpJp queryJpjpByTkno(String shbh,String tkno);
}
