package cn.vetech.vedsb.jp.service.pzzx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shbm;
import cn.vetech.vedsb.common.service.JpShbmServiceImpl;
import cn.vetech.vedsb.jp.dao.pzzx.JpPzKcDao;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzKc;
import cn.vetech.vedsb.jp.service.attach.AttachService;


@Service
public class JpPzKcServiceImpl extends MBaseService<JpPzKc, JpPzKcDao>{
	@Autowired
	private JpPzKcDao jpPzKcDao;
	@Autowired
	private AttachService attachService;
	@Autowired
	private JpShbmServiceImpl shbmServiceImpl;
	
	/**
	 * 根据、起始码、终止码,状态查询库存表中可以方法的记录，包含分页
	 * @param pageNum当前页码
	 * @param pageSize每页显示数
	 * @return page
	 */
	public Page queryFromKC(int pageNum,int pageSize,JpPzKc jpPzKc){
		//创建Page对象，将当前页码和显示的条目数传入
		Page page=new Page(pageNum,pageSize);
		//创建一个Map并传入值
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("shbh", jpPzKc.getShbh());
		param.put("pageNum", pageNum);
		param.put("pageSize", pageSize);
		param.put("startno", jpPzKc.getStartno());
		param.put("endno", jpPzKc.getEndno());
		param.put("pzzt", jpPzKc.getPzzt());
		param.put("pztype", jpPzKc.getPztype());
		param.put("officeid", jpPzKc.getOfficeid());
		//根据Map中的值查询
		List<JpPzKc> list=jpPzKcDao.queryFromKC(param);
		//在数据字典中根据pztype获取相应的对象
		attachService.veclass("pztype").shyhb("czYhbh", jpPzKc.getShbh()).execute(list);
		//获取总记录数
		int pageCount=jpPzKcDao.selectCountByAll(param);		
		//向page中设置值
		page.setList(list);
		page.setTotalCount(pageCount);
		return page;
	}
	
	/**
	 * 根据id主键更新库存表中的票证状态
	 * @param id主键id
	 */
	public void updatePzztById(String id) {
		jpPzKcDao.updatePzztById(id);
	}
	
	/**
	 * 根据InNo删除jpPzKc表中的该条记录
	 * @param jpPzKc
	 */
	public void deleteByInNo(JpPzKc jpPzKc) {
		jpPzKcDao.deleteByInNo(jpPzKc);
	}
	
	/**
	 * 根据商户编号、起始码、终止码、OFFICEID、票证类型查询,包含分页
	 * @param shbh商户编号
	 * @param startno起始码
	 * @param endno终止码
	 * @param pageNum当前页码
	 * @param pageSize每页显示数
	 * @return page
	 */
	public Page getListByAll(String shbh,String startno,String endno,String pztype,int pageNum,int pageSize) throws Exception{
		//创建Page对象，将当前页码和显示的条目数传入
		Page page=new Page(pageNum,pageSize);
		//创建一个Map并传入值
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("shbh", shbh);
		param.put("pageNum", pageNum);
		param.put("pageSize", pageSize);
		param.put("startno", startno);
		param.put("endno", endno);
		param.put("pztype", pztype);//默认查询机票
		try {
			//根据Map中的值查询
			List<JpPzKc> list=this.getMyBatisDao().getListByAll(param);
			//在数据字典中根据pztype获取相应的对象
			attachService.veclass("pztype").shyhb("czYhbh", shbh).execute(list);
			//获取总记录数
			int pageCount=this.getMyBatisDao().selectCountByAll(param);
			//向page中设置值
			page.setList(list);
			page.setTotalCount(pageCount);
		} catch (Exception e) {
			throw new Exception("查询库存信息出错"+e.getMessage());
		}
		return page;
	}

	/**
	 * 在票证退回区显示列表
	 * @param shbh
	 * @param startno
	 * @param endno
	 * @param pztype
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page getKcThList(String shbh,int pageNum,int pageSize,HttpServletRequest request) throws Exception{
		//创建Page对象，将当前页码和显示的条目数传入
		Page page=new Page(pageNum,pageSize);
		//创建一个Map并传入值
		Map<String, Object> param = Servlets.getParametersStartingWith(request, "", false);
		param.put("shbh", shbh);
		try {
			//根据Map中的值查询
			List<JpPzKc> list=this.getMyBatisDao().getKcThList(param);
			//在数据字典中根据pztype获取相应的对象
			attachService.veclass("pztype").shyhb("czYhbh", shbh).execute(list);
			if(CollectionUtils.isNotEmpty(list)){
				//遍历 将bmbh中存放的ID替换成名称
				for(int i=0;i<list.size();i++){
					JpPzKc jp=list.get(i);
					Shbm shbm=shbmServiceImpl.getMyBatisDao().getShbmById(jp.getCzBmbh(),shbh);
					if(shbm != null){
						jp.setCzBmbh(shbm.getMc());
					}
				}
			}
			//获取总记录数
			int pageCount=this.getMyBatisDao().getKcThCount(param);
			//向page中设置值
			page.setList(list);
			page.setTotalCount(pageCount);
		} catch (Exception e) {
			throw new Exception("查询在退回模块显示库存信息出错"+e.getMessage());
		}
		return page;
	}
	/**
	 * 在票证报废区显示列表
	 * @param shbh
	 * @param startno
	 * @param endno
	 * @param pztype
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page getKcBfList(String shbh,int pageNum,int pageSize,HttpServletRequest request) throws Exception{
		//创建Page对象，将当前页码和显示的条目数传入
		Page page=new Page(pageNum,pageSize);
		//创建一个Map并传入值
		Map<String, Object> param = Servlets.getParametersStartingWith(request, "", false);
		param.put("shbh", shbh);
		try {
			//根据Map中的值查询
			List<JpPzKc> list=this.getMyBatisDao().getKcBfList(param);
			//在数据字典中根据pztype获取相应的对象
			attachService.veclass("pztype").shyhb("czYhbh", shbh).execute(list);
			//获取总记录数
			int pageCount=this.getMyBatisDao().getKcBfCount(param);
			//向page中设置值
			page.setList(list);
			page.setTotalCount(pageCount);
		} catch (Exception e) {
			throw new Exception("查询在报废模块区显示库存信息出错"+e.getMessage());
		}
		return page;
	}
}
