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

import cn.vetech.vedsb.jp.dao.pzzx.JpPzBfDao;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzBf;
import cn.vetech.vedsb.jp.service.attach.AttachService;
@Service
public class JpPzBfServiceImpl extends MBaseService<JpPzBf, JpPzBfDao> {
	
	@Autowired
	private AttachService attachService;

	/**
	 * 批量保存数据
	 * @param list
	 * @throws Exception 
	 */
	public void batchInsertJpPzBf(List<JpPzBf> list) throws Exception{
		try {
			this.getMyBatisDao().batchInsertJpPzBf(list);
		} catch (Exception e) {
			throw new Exception("批量保存报废信息出错"+e.getMessage());
		}
		
	}
	
	/**
	 * 根据商户编号、起始码、终止码、票证类型查询,包含分页
	 * @param shbh商户编号
	 * @param startno起始码
	 * @param endno终止码
	 * @param pageNum当前页码
	 * @param pageSize每页显示数
	 * @return page
	 * @throws Exception 
	 */
	public Page getListByAll(String shbh,Date ksrq,Date jsrq,String startno,String endno,String pztype,int pageNum,int pageSize) throws Exception{
		//创建Page对象，将当前页码和显示的条目数传入
		Page page=new Page(pageNum,pageSize);
		//创建一个Map并传入值
		Map<String,Object> param=new HashMap<String,Object>();
		//将日期加一天
		jsrq = VeDate.getPreDay(jsrq, 1);
		param.put("shbh", shbh);
		param.put("pageNum", pageNum);
		param.put("pageSize", pageSize);
		param.put("startno", startno);
		param.put("endno", endno);
		param.put("ksrq", ksrq);
		param.put("jsrq", jsrq);
		param.put("pztype", pztype);//默认查询机票 
		try {
			//根据Map中的值查询
			List<JpPzBf> list=this.getMyBatisDao().getListByAll(param);
			
			//在数据字典中根据pztype获取相应的对象
			attachService.veclass("pztype").shyhb("yhbh", shbh).execute(list);
			//获取总记录数
			int pageCount=this.getMyBatisDao().selectCountByAll(param);
			//向page中设置值
			page.setList(list);
			page.setTotalCount(pageCount);
		} catch (Exception e) {
			throw new Exception("查询报废信息出错"+e.getMessage());
		}
		return page;
	}
	/**
	 * 根据bfNo修改sfsh
	 * @param bfNo 报废ID
	 * @param sfsh 是否审核
	 * @throws Exception 
	 */
	public void updateSfshBybfNo(String bfNo,String sfsh) throws Exception{
		try {
			//创建一个Map并传入值
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("bfNo", bfNo);
			param.put("sfsh", sfsh);
			param.put("shDatetime", VeDate.getNow());//设置审核时间为当前时间
			this.getMyBatisDao().updateSfshBybfNo(param);
		} catch (Exception e) {
			throw new Exception("修改报废信息出错"+e.getMessage());
		}
		
	}
}
