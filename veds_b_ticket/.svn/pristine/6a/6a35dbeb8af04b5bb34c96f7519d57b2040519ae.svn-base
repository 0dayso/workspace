package cn.vetech.vedsb.jp.service.pzzx;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.dao.pzzx.JpXcdDao;
import cn.vetech.vedsb.jp.entity.pzzx.JpXcd;
import cn.vetech.vedsb.jp.entity.xcd.JpXcdTicket;
import cn.vetech.vedsb.jp.service.attach.AttachService;

@Service
public class JpXcdServiceImpl extends MBaseService<JpXcd, JpXcdDao> {
	@Autowired
	private AttachService attachService;
	@Autowired
	private JpXcdDao jpXcdDao;
	/**
	 * 根据商户编号、行程单号、起始码、终止码、状态、部门编号、票证类型查询,包含分页
	 * @param shbh商户编号
	 * @param startno起始码
	 * @param endno终止码
	 * @param pageNum当前页码
	 * @param pageSize每页显示数
	 * @return page
	 * @throws Exception 
	 */
	public Page getListByAll(String shbh,String bmbh,Date ksrq,Date jsrq,String xcdNo,String pztype,String pzzt,String rqtj,String tkNo,int pageNum,int pageSize) throws Exception{
		//创建Page对象，将当前页码和显示的条目数传入
		Page page=new Page(pageNum,pageSize);
		//创建一个Map并传入值
		Map<String,Object> param=new HashMap<String,Object>();
		//将日期加一天
		jsrq = VeDate.getPreDay(jsrq, 1);
		param.put("shbh", shbh);
		param.put("bmbh", bmbh);
		param.put("xcdNo", xcdNo);
		param.put("pageNum", pageNum);
		param.put("pageSize", pageSize);
		param.put("ksrq", ksrq);
		param.put("jsrq", jsrq);
		param.put("rqtj", rqtj);
		param.put("pztype", pztype);//默认查询机票 
		param.put("tkNo", tkNo);
		List<String> listPzzt=new ArrayList<String>();
		if("whs".equals(pzzt)){//未回收状态
			listPzzt.add("3");
			listPzzt.add("5");
			listPzzt.add("7");
		}else if("hs".equals(pzzt)){//已回收状态
			listPzzt.add("4");
			listPzzt.add("6");
			listPzzt.add("8");
		}else if("qb".equals(pzzt)){//全部状态
			listPzzt=null;
		}
		param.put("pzzt", listPzzt);
		try {
			//根据Map中的值查询
			List<JpXcd> list=this.getMyBatisDao().getListByAll(param);
			
			attachService.veclass("pztype").shyhb("yhbh", shbh).shyhb("outYhbh", shbh).shyhb("jbYhbh", shbh).shyhb("tfYhbh", shbh).execute(list);
			//获取总记录数
			int pageCount=this.getMyBatisDao().selectCountByAll(param);
			//向page中设置值
			page.setList(list);
			page.setTotalCount(pageCount);
		return page;
		} catch (Exception e) {
			throw new Exception("查询行程单信息出错"+e.getMessage());
		}
	}

	/**
	 * 批量更新数据
	 * @param shyhb 用户对象
	 * @param pzzt_arr 以逗号分隔的pzzt字符串
	 * @param xcd_arr 以逗号分隔xcdNo字符串
	 * @throws Exception 
	 */
	public void batchUpdate(Shyhb shyhb,String pzzt_arr,String xcdNo_arr) throws Exception{
		String[] pzzt=pzzt_arr.split(",");
		String[] xcdNo=xcdNo_arr.split(",");
		List<JpXcd> param=new ArrayList<JpXcd>();
		for(int i=0;i<pzzt.length;i++){
			JpXcd jd=new JpXcd();
			
			String xcd_pzzt = "";
			if ("5".equals(pzzt[i])) {// 回收
				xcd_pzzt = "6";
			} else if ("3".equals(pzzt[i])) {// 回收
				xcd_pzzt = "4";
			} else if ("8".equals(pzzt[i])) {// 回收
				xcd_pzzt = "7";
			} else if ("6".equals(pzzt[i])) {// 取消回收
				xcd_pzzt = "5";
			} else if ("4".equals(pzzt[i])) {// 取消回收
				xcd_pzzt = "3";
			} else if ("7".equals(pzzt[i])) {// 取消回收
				xcd_pzzt = "8";
			}
			jd.setPzzt(xcd_pzzt);
			jd.setCkBmbh(shyhb.getShbmid());
			jd.setCkYhbh(shyhb.getBh());
			jd.setCkDatetime(VeDate.getNow());
			jd.setXcdNo(xcdNo[i]);
			jd.setShbh(shyhb.getShbh());
			param.add(jd);
		}
		try {
			this.getMyBatisDao().batchUpdate(param);
		} catch (Exception e) {
			throw new Exception("批量更新行程单信息出错"+e.getMessage());
		}
	}
	
	/**
	 * 根据参数查询行程单详细信息
	 * @param jpXcd
	 * @param pageNum
	 * @param pageSize
	 * @param xcdNo_start
	 * @param xcdNo_end
	 * @param rkDatetime_start
	 * @param rkDatetime_end
	 * @return
	 */
	public Page getListByYhbh(String shbh,int pageNum,int pageSize,HttpServletRequest request) throws Exception {
		//创建Page对象，将当前页码和显示的条目数传入
		Page page=new Page(pageNum,pageSize);
		Map<String, Object> param = Servlets.getParametersStartingWith(request, "", false);
		param.put("shbh", shbh);
		List<JpXcd> list = null;
		int count;
		try {
			list = jpXcdDao.getListByYhbh(param);
			count=jpXcdDao.getListByYhbhCount(param);
		} catch (Exception e) {
			throw new Exception("查询行程单详情出错"+e.getMessage());
		}
		attachService.veclass("pztype").shyhb("ckYhbh", shbh).shyhb("tfYhbh", shbh).shyhb("outYhbh", shbh).shyhb("jbYhbh", shbh).execute(list);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	
	/**
	 * 获取领用未使用的行程信息
	 * @param shbh
	 * @param xcdNo
	 * @param pztype
	 * @param tkno
	 * @return
	 * @throws Exception
	 */
	public JpXcd getBeanByXcdNo(String shbh,String pztype) throws Exception{
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("shbh", shbh);
		param.put("pztype", pztype);
		param.put("pzzt", "1");//未使用 领用的xcd
		try {
			return this.getMyBatisDao().getBeanByXcdNo(param);
		} catch (Exception e) {
			throw new Exception("查询行程单信息出错"+e.getMessage());
		}
	}
	
	public JpXcd getJpXcd(String shbh,String xcdNo,String pztype,String tkno) throws Exception{
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("shbh", shbh);
		param.put("xcdNo", xcdNo);
		param.put("pztype", pztype);
		param.put("tkno", tkno);
		try {
			return this.getMyBatisDao().getJpXcd(param);
		} catch (Exception e) {
			throw new Exception("查询行程单信息出错"+e.getMessage());
		}
	}
	
	
	

	/**
	 * 根据库存编号和pzzt删除JpXcd表中的对应记录
	 * @param inNo  库存编号
	 * @param pzzt  票证状态
	 */
	public void deleteJpXcdByInNoAndPzzt(String pzzt,String startno,String endno,String shbh) throws Exception {
		try {
			//根据startno和endno和pzzt=0删除jpXcd表中对应的记录
			jpXcdDao.deleteJpXcdByInNoAndPzzt(pzzt,startno,endno,shbh);
		} catch (Exception e) {
			throw new Exception("根据startno和endno删除行程单记录出错"+e.getMessage());
		}
	}
	
	/**
	 * 根据startno和endno查询行程单表中是否存在pzzt！=0的记录，如果存在不等于0的记录，说明该号段的票证已经开始使用，不能进行反审核；
	 * @param startno  起始号码
	 * @param endno	       终止号码
	 * @param pzzt2    票证状态0：在库，1，未使用，2，出票...
	 * @return
	 */
	public int queryJpXcdByStartnoEndnoPzzt(String startno, String endno,String pzzt2,String shbh) {
		return jpXcdDao.queryJpXcdByStartnoEndnoPzzt(startno,endno,pzzt2,shbh);
	}

	/**
	 *  校验startno和endno是否在jp_Xcd表中已经存在
	 * @param startno
	 * @param endno
	 * @return
	 */
	public List<JpXcd> validateNo(String startno, String endno) {
		return jpXcdDao.validateNo(startno,endno);
	}

	/**
	 * 校验startno和endno是否在jp_Xcd表中已经存在
	 * @param startno 起始号码的字符数组
	 * @param endno
	 * @return
	 */
	public int queryJpXcdByNo(String[] startno, String[] endno) {
		int count = 0;
		for(int i=0;i<startno.length;i++){
			count = jpXcdDao.queryJpXcdByNo(startno[i],endno[i]);
			if(count>0){
				return count;
			}
		}
		return count;
	}
	
	/**
	 * 检查行程单是否使用
	 * @param shbh 商户编号
	 * @param startno  起始单号
	 * @param endno   终止编号
	 * @return   false未使用      true已使用
	 * @throws Exception
	 */
	public boolean xcdIfSy(String shbh,String startno,String endno) throws Exception{
		int count=0;
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("shbh", shbh);
			map.put("startno", startno);
			map.put("endno", endno);
			//该行程单对应的记录数
			count=jpXcdDao.xcdIfSy(map);
			//大于0表示该行程单已使用
		} catch (Exception e) {
			throw new Exception("查询行程单是否使用出错"+e.getMessage());
		}
		return count>0;
	}
	

	/**
	 * 获得行程单机票信息
	 * @param shbh
	 * @param xcdNo
	 * @param pztype
	 * @param tkno
	 * @return
	 * @throws Exception
	 */
	public JpXcdTicket getJpXcdTicket(String tkno) throws Exception{
		return jpXcdDao.getJpXcdTicket(tkno);
	}
}
