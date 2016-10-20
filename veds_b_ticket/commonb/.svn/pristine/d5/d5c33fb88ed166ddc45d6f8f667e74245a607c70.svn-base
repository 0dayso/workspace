package cn.vetech.vedsb.common.service;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.dao.BYdrzCzrzMxDao;
import cn.vetech.vedsb.common.entity.BYdrzCzrzMx;
/**
 * 批量操作日志service
 */
@Service
public class BYdrzCzrzMxServiceImpl extends MBaseService<BYdrzCzrzMx,BYdrzCzrzMxDao>{
	/**
	 * @param xmbm   表明
	 * @param id     日志表id 
	 * @param ywids  传回的业务id，数组形
	 */
	public void xzrzMx(String xmbm,String id,String[] ywids){
		BYdrzCzrzMx ydrzCzrzMx =new BYdrzCzrzMx();
		for(int i =0; i<ywids.length;i++){
			ydrzCzrzMx.setBm(xmbm+"_mx");
			ydrzCzrzMx.setZbid(id);
			ydrzCzrzMx.setYwid(ywids[i]);//业务id
			this.getMyBatisDao().xzrzmx(ydrzCzrzMx);
		}
		
	}
	
}
