package cn.vetech.vedsb.jp.service.jpzwgl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpzwgl.JpTjsqZwszDao;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqZwsz;

@Service
public class JpTjsqZwszServiceImpl extends MBaseService<JpTjsqZwsz, JpTjsqZwszDao>{
	/**
	 * 分页查询追位规则
	 * @param jpTjsqZwsz
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page queryPage(JpTjsqZwsz jpTjsqZwsz,int pageNum,int pageSize){
		Page page =new Page(pageNum,pageSize);
		List<JpTjsqZwsz> list = this.getMyBatisDao().queryPage(jpTjsqZwsz);
		int totalCount = this.getMyBatisDao().getTotalCount(jpTjsqZwsz);
		page.setTotalCount(totalCount);
		page.setList(list);
		return page;
	}
	/**
	 * 批量删除
	 * @param zwgzids
	 * @param shbh
	 * @throws Exception
	 */
	@Transactional
	public void batchDelZwgz(List<JpTjsqZwsz> list) throws Exception{
		for (JpTjsqZwsz t : list) {
			JpTjsqZwsz deleteBean=this.getEntityById(t);
			if("1".equals(deleteBean.getZt())){//如果状态为开启则不删除
				continue;
			}else{
				this.deleteById(deleteBean);
			}
		}
	}
	/**
	 * 批量启用或禁用
	 * @param zt
	 * @param zwgzids
	 * @param shbh
	 */
	@Transactional
	public void batchUpdateZt(List<JpTjsqZwsz> list,String zt) {
		for (JpTjsqZwsz zwsz : list) {
			zwsz.setZt(zt);
			this.getMyBatisDao().updateByPrimaryKeySelective(zwsz);
		}
	}
	/**
	 * 不分页查询列表
	 * @param jpTjsqZwsz
	 * @return
	 */
	public List<JpTjsqZwsz> getZwgzList(JpTjsqZwsz jpTjsqZwsz){
		return this.getMyBatisDao().getZwgzList(jpTjsqZwsz);
	}
	
	/**
	 * 获取已启用的追位规则条数
	 */
	public int getQygzCount(String shbh){
		JpTjsqZwsz jpTjsqZwsz=new JpTjsqZwsz();
		jpTjsqZwsz.setShbh(shbh);
		jpTjsqZwsz.setZt("1");
		return getMyBatisDao().getTotalCount(jpTjsqZwsz);
	}
}
