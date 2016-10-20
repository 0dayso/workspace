package cn.vetech.vedsb.jp.dao.procedures;

import java.util.Map;

import cn.vetech.vedsb.common.entity.base.WdXsfa;
import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpddsz.BookTicketChange;

@JpMyBatisRepository
public interface ProcedureDao {
	/**
	 * 调取后台过程查看采购对账报表
	 * @param param
	 */
	public void procgreport(Map<String,Object> param);
	
	/**
	 * 调取后台过程查看采购对账明细
	 * @param param
	 */
	public void promxcgreport(Map<String,Object> param);
	
	/**
	 * 采购对账
	 * @param param
	 */
	void proc_cgdz_zddb(Map<String,Object> param);
	
	/**
	 * <全自动出票匹配出票规则>
	 * 
	 * @param param [参数说明]
	 */
	void qzdcp(Map<String, String> param);
	
	
	/**
	 * <功能详细描述>
	 * 
	 * @param param [参数说明]
	 * @author heqiwen
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void doJpAction(Map<String,Object> param);
	
	
	/**
	 * 调用过程获取折扣率
	 * @param hkgs
	 * @param cw
	 * @param hc
	 * @param date
	 * @param hbh
	 * @return
	 */
	void getZkl(Map<String, Object> map);
	
	
	/**
	 * 调用存储过程根据商户编号获取需要扫描的OPEN票
	 * @param param
	 */
	void getOpenTicket(Map<String,Object> param);
	
	
	/**
	 * <调用后台生成新的追位队列>
	 * 
	 * @param param [参数说明]
	 * @return void [返回类型说明]
	 */
	void creatZwdl(Map<String, Object> param);
	
	
	/**
	 * 获取原舱位
	 * @param param
	 * @return
	 *p_ezdh varchar2,--航空公司
	 *p_cwdj varchar2,--舱位等级
	 *p_hc varchar2,--航程
	 *p_date varchar2,--航班日期
	 *p_hbh VARCHAR2,--航班号
	 *p_zdj VARCHAR2 --传入的账单价
	 */
	String getOriginalCabin(Map<String,Object> param);
	
	
	Map<String,Object> checkCanzw(Map<String,Object> param);
	/**
	 * 调用后台函数检查否可以追位
	 * @param param
	 * @return
	 */
	Map<String,Object> checkCanzwDr(Map<String,Object> param);
	/**
	 * 调用后台函数检查否可以追位
	 * @param param
	 * @return
	 */
	
	/**
	 * 调用后台函数检查是否可以追位
	 * @param param
	 * @return
	 */
	
	Map<String,Object> genZwddTpf(Map<String,Object> param);
	
	/**
	 * 查询对账系统数据
	 * @param param
	 */
	public void procysdz(Map<String,Object> param);
	
	public String copyXsfa(WdXsfa bean);
	

	/**
	 * 生成退票单，调用方法 f_add_tfp_jp
	 * @param jptpd
	 * @return
	 */
	void createJpTpd(Map<String,Object> param);
	
	
	void insertJpGqd(BookTicketChange bookTicketChange);
	

	/**
	 * 利润分析明细
	 */
	void getLrfxMxList(Map<String,Object> param);
	
	/**
	 * 利润分析
	 * @param param
	 */
	void getLrfxList(Map<String,Object> param);
	
	
	/**
	 * 采购提交退票
	 * @param param
	 */
	void f_cgtp(Map<String,Object> param);
	
	/**
	 * 销售毛利分析明细
	 */
	void getMlfxmxList(Map<String, Object> param);
    
	/**
	 * 销售毛利分析
	 */
	void getMlfxList(Map<String, Object> param);
	
	/**
	 * 
	 * @param param
	 */
	void proc_get_qinfo_fsdx();
	/**
	 * 出票统计报表
	 * @param param
	 */
	void p_get_cptjbb(Map<String,Object> param);
}
    