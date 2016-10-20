package cn.vetech.vedsb.jp.dao.jpddgl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddYj;

@JpMyBatisRepository
public interface JpKhddDao extends Mapper<JpKhdd>{
	
	/**
	 * 改签单管理页面分页查询
	 * @param jpgqd
	 * @return
	 */
	List<Map<String,Object>> query(Map<String, Object> paramsMap);
	
	/**
	 * 获得总条数
	 * @param jpgqd
	 * @return
	 */
	int getPageCount(Map<String, Object> paramsMap);
	
	/**
	 * 获取订单详信息
	 * @param jpkhdd
	 * @return
	 */
	Map<String,Object> detail(Map<String, Object> paramMap);
	
	/**
	 * <查询待打印订单的状态>
	 * 
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	String findDDYcount(@Param(value = "jpKhdd")JpKhdd jpKhdd);
	
	/**
	 * <查询待邮寄订单的状态>
	 * 
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	String findDYJcount(@Param(value = "jpKhdd")JpKhdd jpKhdd);
	
	/**
	 * <查询已邮寄订单的状态>
	 * 
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	String findYYJcount(@Param(value = "jpKhdd")JpKhdd jpKhdd);

	/**
	 * <邮寄行程单：根据条件查询>
	 * 
	 * @param jpKhdd
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @return [参数说明]
	 * 
	 * @return List<JpKhdd> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	List<Map<String,Object>> queryXcdList(@Param(value = "jpKhdd") JpKhdd jpKhdd, @Param(value = "pageNum")int pageNum, 
			@Param(value = "pageSize")int pageSize,@Param(value = "sortType")String sortType);

	/**
	 * <功能详细描述>
	 * 
	 * @param jpKhdd
	 * @return [参数说明]
	 * 
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	int queryXcdListCount(@Param(value = "jpKhdd")JpKhdd jpKhdd);
	
	

	
	List<JpKhdd> selectJpByWbdh(Map<String,Object> param);

	/**
	 * <客户取消邮寄行程单>
	 * 
	 * @param bh
	 * @return [参数说明]
	 * 
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	int cancelYjdd(String bh);

	List<JpKhdd> getJpKhddForGqApply(Map<String,Object> param);
	
	JpKhdd getKhddByDdbh(JpKhdd jpKhdd);

	/**
	 * <打印快递单后，将订单的邮寄状态改为待邮寄 或 邮寄之后将邮寄状态改为已邮寄>
	 * 
	 * @param jpKhddYj [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	int updateYjzt(JpKhddYj jpKhddYj);

	/**
	 * <根据订单编号查找邮寄单和订单>
	 * 
	 * @param ddbh
	 * @return [参数说明]
	 * 
	 * @return Map<String,Object> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	Map<String, Object> queryYjdDdByDdbh(String ddbh);

	/**
	 * <当对订单发送短信时,对订单的短信状态修改>
	 * 
	 * @param jpKhdd
	 * @return [参数说明]
	 * 
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	int updateSmszt(JpKhdd jpKhdd);
	
	/**
	 * 采购对账异常新增查询
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> addcgdzycSearch(Map<String, Object> map);
	/**
	 * 根据外部单号找对账的业务订单
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> genDzDataByWbdh(Map<String, Object> map);
	
	/**
	 * 根据采购订单编号查询订单
	 * @param shbh
	 * @param cgddbh
	 * @param cgdw
	 * @return
	 */
	@Select(value="select * from jp_khdd where shbh=#{param1} and cg_ddbh=#{param2} and cgly = 'OP' and cgdw=#{param3} and ddzt<>'4' and ddzt<>'5'")
	JpKhdd getJpKhddByCgDdbh(String shbh,String cgddbh,String cgdw);

	int updateJpKhddDdztByWbdh(JpKhdd jpkhdd);
	/**
	 * 机票出票报表 出票信息更新 
	 * @param jpkhdd
	 */
	void updateCpbbXxgx(JpKhdd jpkhdd);
	/**
	 * 修改订单修改来源，修改用户信息。
	 * 用于记录异动的操作来源
	 * @param jpkhdd
	 */
	int updateXglyByDdbh(Map<String,Object> param);
	
	List<JpKhdd> getKhddBySfwbcpz(@Param(value = "kssj") String kssj,@Param(value = "jssj") String jssj,@Param(value = "wdid") String wdid);
	
	@Select("select t.wbdh from jp_khdd t,jp_khdd_kz a where cgly ='TB' and sfwbcpz is not null and ddzt = '2' and a.phhtzt = '1' and a.ddbh = t.ddbh")
	public List<String> getZjpWbdh();
	@Select("SELECT * FROM JP_KHDD a WHERE a.shbh = #{param1:VARCHAR} and a.wbdh = #{param2:VARCHAR}")
	public List<JpKhdd> getKhddByWbdh(String shbh,String wbdh);
	
	@Select("delete jp_khdd where wbdh = #{param1} and shbh = #{param2}")
	public void deleteByWbdh(String wbdh,String shbh);
}