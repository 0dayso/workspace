package cn.vetech.vedsb.jp.service.jpzwgl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpzwgl.JpTjsqZdscDao;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqZdsc;
@Service
public class JpTjsqZdscServiceImpl extends MBaseService<JpTjsqZdsc,JpTjsqZdscDao>{
	
	/**
	 * 未进入追位队列查询分页
	 * @param sh
	 * @param pageNum
	 * @param pageSize
	 * @param lb
	 * @return
	 */
	public Page wjdlQueryPage(Map<String, Object> sh, int pageNum, int pageSize) {
		Page page =new Page(pageNum,pageSize);
		List<Map<String, Object>>  list = this.getMyBatisDao().getwjzwdlList(sh);
		int totalCount  = this.getMyBatisDao().getwjTotalCount(sh);
		page.setTotalCount(totalCount);
		page.setList(list);
		return page;
	}
	/**
	 * 查询需要导入正式队列的临时队列 
	 * @param jpTjsqZdsc
	 * @return
	 */
	public List<JpTjsqZdsc> getDdrDlList(JpTjsqZdsc jpTjsqZdsc){
		return getMyBatisDao().getDdrDlList(jpTjsqZdsc);
	}
}