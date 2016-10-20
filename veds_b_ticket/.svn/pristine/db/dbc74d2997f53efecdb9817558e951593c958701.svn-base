package cn.vetech.vedsb.jp.service.jpzdcp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpzdcp.JpZdcpgzDao;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpgz;
@Service
public class JpZdcpgzServiceImpl extends MBaseService<JpZdcpgz,JpZdcpgzDao>{
	/**
	 * 获取优先级值
	 * @param shbh
	 * @return
	 */
	public String getMaxYxj(String shbh){
		return this.getMyBatisDao().getMaxYxj(shbh);
	}
	
	/**
	 * 验证规则名称唯一性
	 * @param shbh
	 * @param gzmc
	 * @return
	 */
	public int verifyOnly(String shbh,String gzmc){
		return this.getMyBatisDao().verifyOnly(shbh, gzmc);
	}
	
	/**
	 * 根据商户编号查询自动出票规则
	 * @param shbh
	 * @return
	 */
	public List<JpZdcpgz> getzdcpidbyshbh(String shbh){
		return this.getMyBatisDao().getzdcpidbyshbh(shbh);
	}
	
	/*
	 * 不分页查询
	 */
	public List<JpZdcpgz> getJplist(JpZdcpgz jpZdcpgz){
		List<JpZdcpgz> list=this.getMyBatisDao().getCpgzList(jpZdcpgz);
	  	return list;
	}
	/*
	//批量删除
	@Transactional
	public void batchDelCpgz(List<JpZdcpgz> list) throws Exception{
		for (JpZdcpgz t : list) {
			JpZdcpgz deleteBean=this.getEntityById(t);
			if("1".equals(deleteBean.getZt())){//如果状态为开启则不删除
				continue;
			}else{
				this.deleteById(deleteBean);
			}
		}
	} */
	/**
	 * 批量删除(假删除)
	 */
	@Transactional
	public void batchDelCpgz(List<JpZdcpgz> list,String zt) throws Exception{
		for (JpZdcpgz jpZdcpgz : list) {
			jpZdcpgz.setZt(zt);
			if("1".equals(jpZdcpgz.getZt())){//如果状态为开启则不删除
				continue;
			}else{
			  this.getMyBatisDao().updateByPrimaryKeySelective(jpZdcpgz);
			}
		}
	}
	/**
	 * 批量启用或禁用
	 * @param zt
	 * @param cpgzid
	 * @param shbh
	 */
	@Transactional
	public void batchUpdateZt(List<JpZdcpgz> list,String zt) {
		for (JpZdcpgz jpZdcpgz : list) {
			jpZdcpgz.setZt(zt);
			this.getMyBatisDao().updateByPrimaryKeySelective(jpZdcpgz);
		}
	}
	/**
	 * 批量审核
	 * @param zt
	 * @param cpgzid
	 * @param shbh
	 */
	@Transactional
	public void batchShZt(List<JpZdcpgz> list,String zt) {
		for (JpZdcpgz jpZdcpgz : list) {
			jpZdcpgz.setZt(zt);
			this.getMyBatisDao().updateByPrimaryKeySelective(jpZdcpgz);
		}
	}
	
	/**
	 * 调整优先级
	 * @param id
	 * @param yxj
	 */
	public void updateYxj(String id, Integer yxj){
		JpZdcpgz jpZdcpgz=this.getMyBatisDao().selectByPrimaryKey(id);
		jpZdcpgz.setYxj(yxj);
		this.getMyBatisDao().updateByPrimaryKey(jpZdcpgz);
	}
}