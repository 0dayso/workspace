package cn.vetech.vedsb.jp.service.jpcwgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.Bean2Map;

import cn.vetech.vedsb.jp.dao.jpbcd.JpBcdDao;
import cn.vetech.vedsb.jp.dao.jpcwgl.JpYsdzDao;
import cn.vetech.vedsb.jp.dao.jpddgl.JpKhddDao;
import cn.vetech.vedsb.jp.dao.jpgqgl.JpGqdDao;
import cn.vetech.vedsb.jp.dao.jptpgl.JpTpdDao;
import cn.vetech.vedsb.jp.dao.procedures.PkgWdCwDao;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpYsdz;
import cn.vetech.vedsb.jp.entity.jpcwgl.Ld;
import cn.vetech.vedsb.jp.entity.jpcwgl.Wdz;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.utils.PlatCode;
import cn.vetech.vedsb.utils.bankdb.BankDbUtil;
@Service
public class JpYsdzServiceImpl extends MBaseService<JpYsdz,JpYsdzDao> {
	@Autowired
	private JpYsdzQnServiceImpl jpYsdzQnServiceImpl;
	@Autowired
	private JpYsdzTbServiceImpl jpYsdzTbServiceImpl;
	@Autowired
	private JpYsdzXcServiceImpl jpYsdzXcServiceImpl;
	@Autowired
	private JpYsdzTcServiceImpl jpYsdzTcServiceImpl;
	@Autowired
	private AttachService attachService;
	@Autowired
	private JpKhddDao jpKhddDao;
	@Autowired
	private JpTpdDao jpTpdDao;
	@Autowired
	private JpGqdDao jpGqdDao;
	@Autowired
	private JpBcdDao jpBcdDao;
	@Autowired
	private PkgWdCwDao pkgWdCwDao;
	/**
	 * 更新网店对账主表
	 * @param param
	 */
	public String procupdateysdz(String wdpt,String wdid,String zwrq,String userid,String deptid,String shbh){
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("wdpt", wdpt);
		param.put("wdid", wdid);
		param.put("zwrq", zwrq);
		param.put("userid", userid);
		param.put("deptid", deptid);
		param.put("shbh", shbh);
		pkgWdCwDao.procupdateysdz(param);
		return param.get("error")==null ? null : String.valueOf(param.get("error"));
	}
	/**
	 * 检查是否已对账
	 * @param zbid
	 * @param shbh
	 * @return
	 */
	public int genCompareCount(String zbid,String shbh,String wdpt){
		JpYsdzMxService mxService=genMxService(wdpt);
		return mxService.genCompareCount(zbid, shbh);
	}
	/**
	 * 检查当前日期之后是否已对账
	 * @param syrq
	 * @param shbh
	 * @param wdid
	 * @return
	 */
	public int genAfterCompareCount(String syrq,String shbh,String wdid,String wdpt){
		return genMxService(wdpt).genAfterCompareCount(syrq, shbh, wdid);
	}
	/**
	 * 查询一个网店一段时间生成的对账数据
	 * @param shbh
	 * @param wdid
	 * @param dzrqs
	 * @param dzrqz
	 * @return
	 */
	public List<Map<String,Object>> checkFirstDaysCompare(String shbh,String wdid,String dzrqs,String dzrqz){
		return getMyBatisDao().checkFirstDaysCompare(shbh, wdid, dzrqs, dzrqz);
	}
	/**
	 * 获取需要对账的未到账数据或漏单数据
	 * @param wdpt
	 * @param shbh
	 * @param wdid
	 * @param jglx
	 * @param syrqs
	 * @param syrqz
	 * @return
	 */
	public List<Map<String, Object>> genWdzOrLd(String wdpt,String shbh,String wdid,String jglx,String syrqs,String syrqz){
		return genMxService(wdpt).genWdzOrLd(shbh, wdid, jglx, syrqs, syrqz);
	}
	/**
	 * 删除对账
	 * @param wdpt
	 * @param shbh
	 * @param zbid
	 */
	public void delResult(String wdpt,String shbh,String zbid,String wdid,String dzrq){
		JpYsdzMxService mxService=genMxService(wdpt);
		mxService.delResult(shbh, zbid);
		mxService.clearBdzt(shbh, wdid, dzrq);
		mxService.clearDzzt(shbh, wdid, dzrq);
	}
	/**
	 * 更新漏单的补单状态,业务订单没有完成的将漏单的补单状态改为2（即未完成）
	 * @param shbh
	 * @param zbid
	 * @param wdpt
	 * @param ddsjs
	 * @param ddsjz
	 */
	public void updateLdzt(String shbh,String zbid,String wdpt,String ddsjs,String ddsjz){
		String tableName=getTableNameByWd(wdpt);
		for(int i=1;i<=4;i++){
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("tablename", tableName);
			param.put("shbh", shbh);
			param.put("zbid", zbid);
			param.put("ddlx", i);
			param.put("ddsjs", ddsjs);
			param.put("ddsjz", ddsjz);
			getMyBatisDao().updateLdzt(param);
		}
	}
	/**
	 * 根据外部单号找对账的业务订单
	 * @param shbh
	 * @param ddlx
	 * @param wbdh
	 * @param dzrqs
	 * @param dzrqz
	 * @return
	 */
	public List<Map<String, Object>> genDzDataByWbdh(String shbh,String ddlx,String wbdh,String dzrqs,String dzrqz){
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("shbh", shbh);
		param.put("wbdh", wbdh);
		param.put("dzrqs", dzrqs);
		param.put("dzrqz", dzrqz);
		switch (NumberUtils.toInt(ddlx,0)) {
			case 1:
				return jpKhddDao.genDzDataByWbdh(param);
			case 2:
				return jpTpdDao.genDzDataByWbdh(param);
			case 3:
				return jpGqdDao.genDzDataByWbdh(param);
			case 4:
				return jpBcdDao.genDzDataByWbdh(param);
			default:
				return null;
		}
		
	}
	/**
	 * 查询对账结果
	 * @param param
	 * @return
	 */
	public Page genDbresult(Map<String,Object> param,String wdpt,int pageNum,int pageSize) {
		String tableName=getTableNameByWd(wdpt);
		param.put("tablename", tableName);
		Page page =new Page(pageNum,pageSize);
		PageHelper ph = new PageHelper();
		param.put("start", ph.getStart(pageNum, pageSize));
		param.put("count", ph.getCount(pageNum, pageSize));
		List<Map<String,Object>> list = getMyBatisDao().genDbresult(param);
		int totalCount = getMyBatisDao().genDbresultTotal(param);
		page.setTotalCount(totalCount);
		page.setList(list);
		return page;
	}
	/**
	 * 查询金额总计
	 * @param param
	 * @return
	 */
	public Map<String,Object> genDbresultSum(Map<String,Object> param,String wdpt){
		String tableName=getTableNameByWd(wdpt);
		param.put("tablename", tableName);
		return getMyBatisDao().genDbresultSum(param);
	}
	/**
	 * 统计各类型数量
	 * @param param
	 * @return
	 */
	public Map<String,Object> genDbresultCount(Map<String,Object> param,String wdpt){
		String tableName=getTableNameByWd(wdpt);
		param.put("tablename", tableName);
		return getMyBatisDao().genDbresultCount(param);
	}
	/**
	 * 生成今日对账
	 * @param dzrq
	 * @param shbh
	 * @return
	 */
	public int createTodayDz(String dzrq,String shbh) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("p_dzrq", dzrq);
		param.put("p_shbh", shbh);
		pkgWdCwDao.createTodayDz(param);
		String result=BankDbUtil.objToString(param.get("result"));
		return NumberUtils.toInt(result,0);
	}
	private JpYsdzMxService genMxService(String wdpt){
		if(PlatCode.QN.equals(wdpt)){
			return jpYsdzQnServiceImpl;
		}else if(PlatCode.TB.equals(wdpt)){
			return jpYsdzTbServiceImpl;
		}else if(PlatCode.XC.equals(wdpt)){
			return jpYsdzXcServiceImpl;
		}else if(PlatCode.TC.equals(wdpt)){
			return jpYsdzTcServiceImpl;
		}
		return null;
	}
	private String getTableNameByWd(String wdpt){
		String tableName="jp_ysdz_";
		if(PlatCode.QN.equals(wdpt)){
			tableName+="qn";
		}else if(PlatCode.TB.equals(wdpt)){
			tableName+="tb";
		}else if(PlatCode.XC.equals(wdpt)){
			tableName+="xc";
		}else if(PlatCode.TC.equals(wdpt)){
			tableName+="tc";
		}else {
			return null;
		}
		return tableName;
	}
	public Page wdzPage(Wdz wdz,int pageNum,int pageSize){
		Map<String,Object> map=Bean2Map.getMap(wdz);
		
		Page wdzpage = new Page(pageNum,pageSize);
		
		PageHelper ph=new PageHelper();
		map.put("start", ph.getStart(pageNum, pageSize));
		map.put("count", ph.getCount(pageNum, pageSize));
		
		List<Map<String,Object>> wdzlist = this.getMyBatisDao().searchWdz(map);
		
		if(CollectionUtils.isNotEmpty(wdzlist)){
			attachService.wdzl("WDID").execute(wdzlist);
		}
		
		int totalCount = this.getMyBatisDao().getTotalCount(map);
		
		wdzpage.setTotalCount(totalCount);
		wdzpage.setList(wdzlist);
		return wdzpage;
	}
	public Page ldPage(Ld ld,int pageNum,int pageSize){
		Map<String,Object> map=Bean2Map.getMap(ld);
		
		Page ldpage = new Page(pageNum,pageSize);
		PageHelper ph=new PageHelper();
		map.put("start", ph.getStart(pageNum, pageSize));
		map.put("count", ph.getCount(pageNum, pageSize));
		
		List<Map<String,Object>> ldlist = this.getMyBatisDao().searchLd(map);
		
		if(CollectionUtils.isNotEmpty(ldlist)){
			attachService.wdzl("WDID").execute(ldlist);
		}
		int totalCount = this.getMyBatisDao().getTotalCount(map);
		
		ldpage.setTotalCount(totalCount);
		ldpage.setList(ldlist);
		return ldpage;
	}
}