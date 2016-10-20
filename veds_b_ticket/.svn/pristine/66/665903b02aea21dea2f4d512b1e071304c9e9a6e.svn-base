package cn.vetech.vedsb.jp.service.pzzx;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.dao.pzzx.JpPzThDao;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzTh;
import cn.vetech.vedsb.jp.service.attach.AttachService;
@Service
public class JpPzThServiceImpl extends MBaseService<JpPzTh, JpPzThDao> {
	
	@Autowired
	private AttachService attachService;

	/**
	 * 批量保存数据
	 * @param list
	 */
	public void batchInsertJpPzTh(List<JpPzTh> list) throws Exception{
		try {
			this.getMyBatisDao().batchInsertJpPzTh(list);
		} catch (Exception e) {
			throw new Exception("批量保存退回信息出错"+e.getMessage());
		}
		
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
	public Page getListByAll(String shbh,String bmbh,Date ksrq,Date jsrq,String startno,String endno,String pztype,int pageNum,int pageSize) throws Exception{
		//创建Page对象，将当前页码和显示的条目数传入
		Page page=new Page(pageNum,pageSize);
		//创建一个Map并传入值
		Map<String,Object> param=new HashMap<String,Object>();
		//将日期加一天
		jsrq = VeDate.getPreDay(jsrq, 1);
		param.put("shbh", shbh);
		param.put("bmbh", bmbh);
		param.put("pageNum", pageNum);
		param.put("pageSize", pageSize);
		param.put("startno", startno);
		param.put("endno", endno);
		param.put("ksrq", ksrq);
		param.put("jsrq", jsrq);
		param.put("pztype", pztype);//默认查询机票 
		try {
			//根据Map中的值查询
			List<JpPzTh> list=this.getMyBatisDao().getListByAll(param);
			
			attachService.veclass("pztype").shyhb("yhbh", shbh).execute(list);
			//获取总记录数
			int pageCount=this.getMyBatisDao().selectCountByAll(param);
			//向page中设置值
			page.setList(list);
			page.setTotalCount(pageCount);
		} catch (Exception e) {
			throw new Exception("查询退回信息出错"+e.getMessage());
		}
		
		return page;
	}
}
