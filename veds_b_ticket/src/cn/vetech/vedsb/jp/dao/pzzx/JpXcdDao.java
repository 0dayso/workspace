package cn.vetech.vedsb.jp.dao.pzzx;
import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.pzzx.JpXcd;
import cn.vetech.vedsb.jp.entity.xcd.JpXcdTicket;
@JpMyBatisRepository
public interface JpXcdDao  extends Mapper<JpXcd> {
	/**
	 * 查询JpXcd集合
	 * @param param map集合  其中封装 商户编号，行程单号，日始，日止，票证类型，状态信息
	 * @return list集合
	 */
	public List<JpXcd> getListByAll(Map<String,Object> param);
	/**
	 * @param param map集合  其中封装 商户编号，行程单号，日始，日止，票证类型，状态信息
	 * @return  表示在当前条件下的记录数
	 */
	public int selectCountByAll(Map<String,Object> param);
	/**
	 * 批量更新
	 * @param param JpXcd对象集合
	 */
	public void batchUpdate(List<JpXcd> param);
	
	/**
	 * 根据参数查询详情
	 * @param jpXcd
	 * @param pageNum
	 * @param pageSize
	 * @param xcdNo_start
	 * @param xcdNo_end
	 * @param rkDatetime_start
	 * @param rkDatetime_end
	 * @return
	 */
	public List<JpXcd> getListByYhbh(Map<String,Object> param);
	/**
	 * 根据参数查询详情
	 * @param jpXcd
	 * @param pageNum
	 * @param pageSize
	 * @param xcdNo_start
	 * @param xcdNo_end
	 * @param rkDatetime_start
	 * @param rkDatetime_end
	 * @return 总条目数
	 */
	public int getListByYhbhCount(Map<String,Object> param);
	
	/**
	 * 根据行程单和商户编号查询
	 * @param param
	 * @return
	 */
	public JpXcd getBeanByXcdNo(Map<String,Object> param);
	
	public JpXcd getJpXcd(Map<String,Object> param);
	/**
	 *根据startno和endno和pzzt=0删除jpXcd表中对应的记录
	 * @param param
	 */
	public void deleteJpXcdByInNoAndPzzt(String pzzt,String startno, String endno,String shbh);
	/**
	 *根据startno和endno查询行程单表中是否存在pzzt！=0的记录，如果存在不等于0的记录，说明该号段的票证已经开始使用，不能进行反审核
	 * @param param
	 */
	public int queryJpXcdByStartnoEndnoPzzt(String startno, String endno,String pzzt2,String shbh);
	/**
	 * 根据startno和endno判断xcd表中是否存在已经有了该条记录
	 * @param startno
	 * @param endno
	 * @return
	 */
	public List<JpXcd> validateNo(String startno, String endno);
	
	/**
	 * 根据startno和endno判断xcd表中是否存在已经有了该条记录
	 * @param startno
	 * @param endno
	 * @return 返回值为int
	 */
	public int queryJpXcdByNo(String startno, String endno);
	/**
	 * 检查该段行程单是否使用
	 * @param map
	 * @return
	 */
	public int xcdIfSy(Map<String,Object> map);

	/**
	 * 获得行程单机票信息
	 * @param shbh
	 * @param xcdNo
	 * @param pztype
	 * @param tkno
	 * @return
	 * @throws Exception
	 */
	public JpXcdTicket getJpXcdTicket(String tkno);
}
