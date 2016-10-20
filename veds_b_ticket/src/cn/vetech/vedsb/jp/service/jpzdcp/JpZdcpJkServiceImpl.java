package cn.vetech.vedsb.jp.service.jpzdcp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.jp.dao.jpzdcp.JpZdcpJkDao;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpJk;
import cn.vetech.vedsb.jp.service.attach.AttachService;
@Service
public class JpZdcpJkServiceImpl extends MBaseService<JpZdcpJk,JpZdcpJkDao>{
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
	public Page selectAllJpZdcpJk(Map<String,Object> param,int pageNum,int pageSize) throws Exception{
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Page page=new Page(pageNum,pageSize);
		int count;
		try {
			//查询自动监控数据
			list=this.getMyBatisDao().selectAllJpZdcpJk(param);
			//替值
			attachService.shyhb("ZDCPY",VeStr.getValue(param, "shbh")).shyhb("CP_SDR",VeStr.getValue(param, "shbh")).wdzl("WDID").execute(list);
			//查询自动监控记录数
			count=this.getMyBatisDao().selectAllJpZdcpJkCount(param);
		} catch (Exception e) {
			throw new Exception("查询自动监控表出错"+e.getMessage());
		}
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	
	
	public List<Map<String,Object>> getZdcpDdByWdid(Map<String,Object> param){
		return this.getMyBatisDao().getZdcpDdByWdid(param);
	}
}