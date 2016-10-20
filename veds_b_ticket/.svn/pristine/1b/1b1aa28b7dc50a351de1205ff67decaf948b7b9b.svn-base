package cn.vetech.vedsb.jp.service.cpsz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.cpsz.JpZdtpJkDao;
import cn.vetech.vedsb.jp.entity.cpsz.JpZdtpJk;
import cn.vetech.vedsb.jp.service.attach.AttachService;
@Service
public class JpZdtpJkServiceImpl extends MBaseService<JpZdtpJk, JpZdtpJkDao>{
	@Autowired
	private AttachService attachService;

	/**
	 * 查询自动监控数据
	 * @param param
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page selectAllJpZdtpJk(Map<String,Object> param,int pageNum,int pageSize) throws Exception{
		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
		Page page=new Page(pageNum,pageSize);
		int count;
		try {
			//查询自动监控数据
			list=this.getMyBatisDao().selectAllJpZdtpJk(param);
			//替值
			attachService.shyhb("ZDCPY", (String)param.get("shbh")).wdzl("WDID").execute(list);
			//查询自动监控记录数
			count=this.getMyBatisDao().selectAllJpZdtpJkCount(param);
		} catch (Exception e) {
			throw new Exception("查询自动监控表出错"+e.getMessage());
		}
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	
	public JpZdtpJk getJpZdtpJkCreateInfo(String shbh,String tpdh){
		return this.getMyBatisDao().getJpZdtpJkCreateInfo(shbh, tpdh);
	}
	
	public JpZdtpJk getJpZdtpJkInfo(JpZdtpJk jpZdtpJk){
		return this.getMyBatisDao().getJpZdtpJkInfo(jpZdtpJk);
	}
}
