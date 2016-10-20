package cn.vetech.vedsb.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.dao.ShcsbDao;
import cn.vetech.vedsb.common.entity.Shcsb;

@Service
public class ShcsbServiceImpl extends MBaseService<Shcsb, ShcsbDao> {

	
	/**
	 * 注入异动Service
	 */
	@Autowired
	private BYdrzCzrzServiceImpl ydrzCzrzServiceImpl;
	
	/**
	 * 单条操作异动
	 */
	public void jlydrz(Shcsb Shcsbo,Shcsb Shcsbt,String id,String zts,String czr, String czid,String ckfw,String shbh,String bz){
		ydrzCzrzServiceImpl.xzrz(Shcsbo,Shcsbt,id, zts, czr, czid,ckfw,shbh,bz);
	}

	
	/**
	 * 根据参数编号和商户编号查询参数信息
	 */
	public Shcsb findCs(String bh,String shbh) {
		return this.getMyBatisDao().findCs(bh,shbh);
	}
	
	/**
	 * 根据商户编号查询
	 */
	public List<Shcsb> findbyShbh(String shbh) {
		return this.getMyBatisDao().findbyShbh(shbh);
	}
	
	/**
	 * 分页方法
	 */
	public Page queryPage(Shcsb shcsb,int pageNum,int pageSize){
		Page page =new Page(pageNum,pageSize);
		List<Shcsb> list = this.getMyBatisDao().getShcsbList(shcsb);
		int totalCount = this.getMyBatisDao().getTotalCount(shcsb);
		page.setTotalCount(totalCount);
		page.setList(list);
		return page;
	}
	/**
	 * 获取该商户下，机票订单类颜色提醒集合
	 * @param param
	 * @return
	 */
	public List<Shcsb> getListByShbhAndBhs(Map<String,Object> param){
		return this.getMyBatisDao().getListByShbhAndBhs(param);
	}
	/**
	 * 批量更新机票订单类颜色提醒
	 * @param param
	 */
	public void updateColorRemind(List<Shcsb> param){
		this.getMyBatisDao().updateColorRemind(param);
	}
	/**
	 * 更新机票订单默认排序
	 * @param param
	 */
	public void updateMrpx(Map<String,Object> param){
		this.getMyBatisDao().updateMrpx(param);
	}
	/**
	 * 插入机票订单默认排序
	 */
	public void insertMrpx(Shcsb shcsb){
		this.getMyBatisDao().insertMrpx(shcsb);
	}
}
