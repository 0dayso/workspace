package cn.vetech.vedsb.jp.service.jpzdcp;

import java.util.List;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpzdcp.JpZdcpCsszDao;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpCssz;

@Service
public class JpZdcpCsszServiceImpl extends MBaseService<JpZdcpCssz,JpZdcpCsszDao>{

	/**
	 * 查询自动监控数据
	 * @param param
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page selectAllJpZdcpCssz(JpZdcpCssz jzc,int pageNum,int pageSize) throws Exception{
		Page page=new Page(pageNum,pageSize);
		try {
			//查询自动监控数据
			List<JpZdcpCssz> list=this.getMyBatisDao().selectAllJpZdcpCssz(jzc);
			//查询自动监控记录数
			int count=this.getMyBatisDao().selectAllJpZdcpCsszCount(jzc);
			page.setList(list);
			page.setTotalCount(count);
		} catch (Exception e) {
			throw new Exception("查询自动监控表出错"+e.getMessage());
		}
		return page;
	}
}