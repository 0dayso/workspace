package cn.vetech.vedsb.common.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.dao.JpShwdkzDao;
import cn.vetech.vedsb.common.entity.JpShwdkz;


/**
 * 商户网店类型和数量控制表Service
 * @author lishanliang
 *
 */
@Service
public class JpShwdkzServiceImpl  extends MBaseService<JpShwdkz,JpShwdkzDao>{
	
	/**
	 * 根据商户编号来删除本商户下的所有网店控制数据
	 * @param shbh
	 * @throws Exception
	 */
	public void deleteByShbh(String shbh) throws Exception{
		this.getMyBatisDao().deleteByShbh(shbh);
	}
	/**
	 * 根据商户编号查找该商户下所有的网店控制类型
	 * @param shbh
	 * @return
	 */
	public List<JpShwdkz> getShwdkzByShbh(String shbh) throws Exception{
		return this.getMyBatisDao().getShwdkzByShbh(shbh);
	}
}
