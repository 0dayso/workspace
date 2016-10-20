package cn.vetech.vedsb.jp.service.jpxepnr;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.jp.dao.jpxepnr.JpXepnrDao;
import cn.vetech.vedsb.jp.entity.jpxepnr.JpXepnr;
@Service
public class JpXepnrServiceImpl extends MBaseService<JpXepnr,JpXepnrDao>{

	public Page selectAllJpXepnr(Map<String,Object> param) throws Exception{
		
		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
		int pageNum=NumberUtils.toInt(VeStr.getValue(param, "pageNum"),0);
		int pageSize=NumberUtils.toInt(VeStr.getValue(param, "pageSize"),10);
		Page page=new Page(pageNum,pageSize);
		PageHelper.start(page);
		int count=0;
		try {
			//查询自动监控数据
			list=this.getMyBatisDao().selectAllJpXepnr(param);
		    //attachService.shyhb("ZDCPY", (String)param.get("shbh")).wdzl("WDID").execute(list);
			//查询自动监控记录数
			count=this.getMyBatisDao().selectAllJpXepnrCount(param);
		} catch (Exception e) {
			throw new Exception("查询自动监控表出错"+e.getMessage());
		}
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
}