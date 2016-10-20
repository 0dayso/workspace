package cn.vetech.vedsb.jp.service.procedures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vetech.vedsb.jp.dao.procedures.ProcedureDao;
import cn.vetech.vedsb.jp.entity.jpddsz.BookTicketChange;

@Service
public class ProcedureServiceImpl extends ParamMap{
	
	@Autowired
	private ProcedureDao  procedureDao;
	/**
	 * 调取后台过程查看采购对账报表
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> procgreport(Map<String, Object> param){
		super.xmlToJson(param);
		procedureDao.procgreport(param);
		if(param.get("p_cur")==null){
			return null;
		}
		return (List<Map<String, Object>>)param.get("p_cur");
	}
	
	/**
	 * 调取后台过程查看采购对账报表明细
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> promxcgreport(Map<String, Object> param){
		super.xmlToJson(param);
		procedureDao.promxcgreport(param);
		if(param.get("p_cur")==null){
			return null;
		}
		if(param.get("allrowcount") == null){
			return null;
		}
		List<Map<String,Object>> list = (List<Map<String,Object>>)param.get("p_cur");
		List<Map<String, Object>> listcount = (List<Map<String, Object>>)param.get("p_count_cur");
		Map<String, Object> sumMap = new HashMap<String,Object>();
		if(CollectionUtils.isNotEmpty(list)){
			sumMap = listcount.get(0);
		}
		int count = (Integer)param.get("allrowcount");
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap.put("list_cur", list);
		resmap.put("count", count);
		resmap.put("sumMap", sumMap);
		return resmap;
	}

	/**
	 * 采购对账
	 * 
	 * @param p_shbh
	 * @param p_dzrq
	 * @param p_zbid
	 * @param p_dzr
	 * @throws Exception
	 */
	public void proc_cgdz_zddb(String p_shbh, String p_dzrq, String p_zbid, String p_dzr) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("p_shbh", p_shbh);
		param.put("p_dzrq", p_dzrq);
		param.put("p_zbid", p_zbid);
		param.put("p_dzr", p_dzr);
		procedureDao.proc_cgdz_zddb(param);
		String p_error = (String) param.get("p_error");
		if (StringUtils.isNotBlank(p_error)) {
			throw new Exception(p_error);
		}
	}
	
	/**
	 * <全自动出票匹配出票规则>
	 */
	public Map<String, String> qzdcp(String ddbh,String shbh,String cpy) {
		Map<String,String> param=new HashMap<String, String>();
		param.put("p_ddbh", ddbh);
		param.put("p_shbh", shbh);
		param.put("p_userid", cpy);
		procedureDao.qzdcp(param);
		return param;
	}
	
	/**
	 * 调用过程获取折扣率
	 * @param hkgs
	 * @param cw
	 * @param hc
	 * @param cfsj
	 * @param hbh
	 * @return
	 */
	public Map<String,Object> getZkl(Map<String, Object> map){
		super.xmlToJson(map);
		procedureDao.getZkl(map);
		return map;
	}
	
	/**
	 * 调用存储过程根据商户编号获取需要扫描的OPEN票
	 * @param param
	 * @throws Exception 
	 */
	public void getOpenTicket(Map<String,Object> param) throws Exception{
		try {
			procedureDao.getOpenTicket(param);
		} catch (Exception e) {
			throw new Exception("调用存储过程根据商户编号获取需要扫描的OPEN票出错"+e.getMessage());
		}
	}
	
	/**
	 * 查询对账系统数据
	 * @param param
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> procysdz(String xml){
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("p_xml", xml);
		super.xmlToJson(param);
		procedureDao.procysdz(param);
		if(param.get("p_cur")== null){
			return null;
		}
		return (List<Map<String,Object>>) param.get("p_cur");
	}

	/**
	 * <调用后台生成新的追位队列>
	 * 
	 * @return void [返回类型说明]
	 */
	public void creatZwdl(String shbh,String ddbh){
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("p_ddbh", ddbh);
		param.put("p_shbh", shbh);
		procedureDao.creatZwdl(param);
	}
	
	/**
	 * 调用后台函数检查是否可以追位
	 * @param param
	 * @return 
	 * result 0不追位 1追位
	 * p_sqdh 入参
	 * p_out_cw 目标舱位
	 * p_out_zwfs 追位方式
	 * p_out_jg 目标价格
	 * p_error error
	 * f_check_sfzw
	 */
	public Map<String, Object> checkCanzw(String sqdh){
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("p_sqdh", sqdh);
		procedureDao.checkCanzw(param);
		return param;
	}
	/**
	 * 调用后台函数检查导入的申请单是否可以追位
	 * @param param
	 * @return 
	 * result 0不追位 1追位
	 * p_id 入参
	 * p_out_cw 目标舱位
	 * p_out_zwfs 追位方式
	 * p_out_jg 目标价格
	 * p_error error
	 * f_check_sfzw
	 */
	public Map<String, Object> checkCanzwDr(String wrid){
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("p_id", wrid);
		procedureDao.checkCanzwDr(param);
		return param;
	}
	/**
	 * 调用后台函数检查否可以追位proc_get_zwdd_tpf
	 * @param param
	 * @return
	 * p_ddbh 入参
	 * p_tpfl 退票费率
	 * p_tpf 退票费
	 */
	public Map<String,Object> genZwddTpf(String ddbh,String shbh){
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("p_ddbh", ddbh);
		param.put("p_shbh", shbh);
		procedureDao.genZwddTpf(param);
		return param;
	}
	
	/**
	 * 获取原舱位
	 * 
	 * @param param
	 * @return p_ezdh varchar2,--航空公司 p_cwdj varchar2,--舱位等级 p_hc varchar2,--航程 p_date varchar2,--航班日期 p_hbh
	 *         VARCHAR2,--航班号 p_zdj VARCHAR2 --传入的账单价
	 */
	public String getOriginalCabin(Map<String,Object> param) {
		return procedureDao.getOriginalCabin(param);
	}

	
	
	public Map<String,Object> createJpTpd(Map<String,Object> param) {
		super.xmlToJson(param);
		procedureDao.createJpTpd(param);
		return param;
	}

	public void insertJpGqd(BookTicketChange bookTicketChange){
		Map<String,Object> map = super.xmlToJson(bookTicketChange.getParam());
		bookTicketChange.setP_xml(StringUtils.trimToEmpty((String)map.get("p_xml")));
		procedureDao.insertJpGqd(bookTicketChange);
	}
	
	/**
	 * 利润分析明细
	 * @param paramXml
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getLrfxMxList(String paramXml) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p_xml", paramXml);
		super.xmlToJson(map);
		try {
			procedureDao.getLrfxMxList(map);
			if(map.get("p_cur")==null){
				return null;
			}
			if(map.get("p_cur_sum")==null){
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return map;
	}
	
	/**
	 * 利润分析
	 * @param paramXml
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getLrfxList(String paramXml) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p_xml", paramXml);
		super.xmlToJson(map);
		try {
			procedureDao.getLrfxList(map);
			if(map.get("p_cur_zcp")==null){
				return null;
			}
			if(map.get("p_cur_hbzd")==null){
				return null;
			}
			if(map.get("p_cur_lr")==null){
				return null;
			}
			if(map.get("p_cur_hj")==null){
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return map;
	}

	public Map<String,Object> f_cgtp(Map<String,Object> param){
		super.xmlToJson(param);
		procedureDao.f_cgtp(param);
		return param;
	}
	public Map<String, Object> getMlfxList(Map<String, Object> param)throws Exception{
		super.xmlToJson(param);
		try {
			procedureDao.getMlfxList(param);
			if(param.get("p_cur")==null){
				return null;
			}
			if(param.get("p_cur_allcount")==null){
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return param;
	}
	
	public void proc_get_qinfo_fsdx(){
		procedureDao.proc_get_qinfo_fsdx();
	}
	
	public void cpTjbbReport(Map<String,Object> param){
		super.xmlToJson(param);
		procedureDao.p_get_cptjbb(param);
	}
}