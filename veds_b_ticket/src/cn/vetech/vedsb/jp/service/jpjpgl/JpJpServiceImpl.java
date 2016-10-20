package cn.vetech.vedsb.jp.service.jpjpgl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vetech.core.business.tag.FunctionCode;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.Bean2Map;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.dao.jpjpgl.JpJpDao;
import cn.vetech.vedsb.jp.entity.bbzx.Cpbb;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.PkgQueryServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Service
public class JpJpServiceImpl extends MBaseService<JpJp, JpJpDao>{
	
	@Autowired
	private PkgQueryServiceImpl  pkgQueryServiceImpl;
	
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	
	
	/**
	 * 分页查询改签单和改签单明细
	 * @param jpgqd
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page query(JpJp jpjp, String sortType) throws Exception {
		Page page = new Page(jpjp.getStart(),jpjp.getCount());
		List<Map<String,Object>> jpjpList = this.getMyBatisDao().query(jpjp);
		int pagecount = this.getMyBatisDao().getPageCount(jpjp);
		page.setList(jpjpList);
		page.setTotalCount(pagecount);
		return page;
	}
	
	public Map<String, Object> detail(JpJp jpjp) {
		return this.getMyBatisDao().detail(jpjp);
	}
	
    
	public JpJp getJpTpdById(JpJp jpjp){
		return this.getMyBatisDao().getJpJpById(jpjp);
	}
	
	/**
	 * 获得改签申请机票列表
	 * @param jpjp
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryForGqsq(JpJp jpjp) throws Exception {
		return this.getMyBatisDao().queryForGqsq(jpjp);
	}

	public JpJp getJpByTkno(JpJp jpjp) {
		return this.getMyBatisDao().getJpByTkno(jpjp);
	}
	
	public List<JpJp> getJpByCjr(JpJp jpjp) {
		return this.getMyBatisDao().getJpByCjr(jpjp);
	}
	
	/**
	 * 根据订单编号获取机票列表
	 */
	public List<JpJp> getJpListByDDbh(String ddbh, String shbh) {
		JpJp record=new JpJp();
		record.setDdbh(ddbh);
		record.setShbh(shbh);
		List<JpJp> jpList = this.getMyBatisDao().select(record);
		return jpList;
	}
	/**
	 * 根据订单编号（订单编号用数组存放，支持查多个）查航段表数据，用于交票
	 * @param ddbhs String[];shbh String
	 * @return
	 */
	public List<JpJp> selectJpByDdbh(Map<String,Object> param) {
		return this.getMyBatisDao().selectJpByDdbh(param);
	}
	/**
	 * 根据订单编号和出票时间止找已出票的票数
	 * @param ddbh
	 * @param shbh
	 * @param cpsjz
	 * @return
	 */
	public int countYcpByCptmDdbh(String ddbh,String shbh,String cpsjz){
		return this.getMyBatisDao().countYcpByCptmDdbh(ddbh, shbh, cpsjz);
	}

	/**
	 * <功能详细描述>
	 * 
	 * @param jp
	 * @return [参数说明]
	 * @author heqiwen
	 * @return Page [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Map<String,Object> queryCpbbPage(Cpbb cpbb) {
		Map<String,Object> param=Bean2Map.getMap(cpbb);
		pkgQueryServiceImpl.cpReport(param);
		return param;
	}
	
	//通过三字码查找城市
	public String getCityNameByszm(String szm){
		if(FunctionCode.getBcity(szm)!=null){
			return FunctionCode.getBcity(szm).getMc();
		}else{
			return null;
		}
	}
	//通过id查找数据字典对象名称
	public String getCityNameById(String id){
		if(FunctionCode.getVeclass(id)!=null){
			return FunctionCode.getVeclass(id).getMc();
		}else{
			return id;
		}
	}
	
	/**
	 * 历史数据调整机票正常单出票时间调整列表
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getHistoryList(Map<String, Object> map){
		return this.getMyBatisDao().getHistoryList(map);
	}
//	/**
//	 * 历史数据调整机票正常单出票时间修改
//	 * @param ddbh
//	 * @param tkno
//	 * @return
//	 */
//	public List<Map<String, Object>> getHistoryCpsjEdit(Map<String, Object> map){
//		return this.getMyBatisDao().getHistoryCpsjEdit(map);
//	}
	
	public Map<String, Object> getjpobject(Map<String, Object> map){
		return this.getMyBatisDao().getjpobject(map);
	}
	
	@Transactional
	public void saveHistoryKhdd(Map<String, Object> map) throws Exception {
		try {
			String jpzdj = (String)map.get("jpzdj");
			Long djpzdj = NumberUtils.toLong(jpzdj);
			String jpxsj = (String)map.get("jpxsj");
			Double djpxsj = NumberUtils.toDouble(jpxsj);
			String jpjjf = (String)map.get("jpjjf");
			Long djpjjf = NumberUtils.toLong(jpjjf);
			String jptax = (String)map.get("jptax");
			Long djptax = NumberUtils.toLong(jptax);
			Double xsje = Arith.add(djpzdj, Arith.add(djpxsj, Arith.add(djpjjf, djptax)));
			map.put("xsje", xsje);
			this.getMyBatisDao().saveHistory(map);
			String zfkm = (String)map.get("zfkm");
			String ddbh = (String)map.get("DDBH");
			Shyhb user = SessionUtils.getShshbSession();
			JpKhdd khdd = new JpKhdd();
			khdd.setShbh(user.getShbh());
			khdd.setDdbh(ddbh);
			khdd = this.jpKhddServiceImpl.getKhddByDdbh(khdd);
			if(null!=khdd){
				khdd.setXsZdj(djpzdj);
				khdd.setXsPj(djpxsj);
				khdd.setXsJsf(djpjjf);
				khdd.setXsTax(djptax);
				khdd.setSkkm(zfkm);
				khdd.setXsJe(xsje);
			}
			this.jpKhddServiceImpl.update(khdd);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 采购对账查询bspet票电商数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> searchBspet(Map<String, Object> map){
		return this.getMyBatisDao().searchBspet(map);
	}

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
	public List<JpJp> queryJpListByDdbh(JpJp jp) {
		return this.getMyBatisDao().queryJpListByDdbh(jp);
	}

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
	public void updateJpcp(JpJp jp) {
		 this.getMyBatisDao().updateJpcp(jp);
	}
	
	public List<Map<String, Object>> getXcdListByDdbh(Map<String, Object> map){
		return this.getMyBatisDao().getXcdListByDdbh(map);
	}
	
	/**
	 * 根据订单编号查询机票
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getjpjpByddbh(Map<String, Object> map){
		return this.getMyBatisDao().getjpjpByddbh(map);
	}
	/**
	 * 根据外部单号查询机票
	 * @param map
	 * @return
	 */
	public List<JpJp> getjpjpBywbbh(String wbdh,String shbh){
		return this.getMyBatisDao().getjpjpBywbbh(wbdh,shbh);
	}
	/**
	 * <全自动出票匹配出票规则>
	 */
	public String qzdcp(String ddbh,String shbh,String cpy) {
		Map<String,String> param=new HashMap<String, String>();
		param.put("p_ddbh", ddbh);
		param.put("p_shbh", shbh);
		param.put("p_userid", cpy);
		this.getMyBatisDao().qzdcp(param);
		return param.get("result");
	}
	
	/**
	 * <根据销售pnr查找机票>
	 * 
	 * @param jp
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<JpJp> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public List<Map<String, Object>> queryJpListByXspnr(Map<String, Object> map) {
		return this.getMyBatisDao().queryJpListByXspnr(map);
	}
	/**
	 * 根据wbdh 外部正常单号 wdid 网店ID  shbh 商户编号  查JpJp表集合
	 * @param wbdh
	 * @param wdid
	 * @param shbh
	 * @return
	 */
	public List<JpJp> getJpjpListByWbdh(String wbdh,String wdid,String shbh){
		return this.getMyBatisDao().getJpjpListByWbdh(wbdh,wdid,shbh);
	}
}
