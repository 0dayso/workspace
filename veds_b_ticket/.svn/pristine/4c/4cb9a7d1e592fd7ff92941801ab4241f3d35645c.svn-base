package cn.vetech.vedsb.jp.service.jpzwgl;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpzwgl.JpTjsqCjrDao;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqCjr;

@Service
public class JpTjsqCjrServiceImpl extends MBaseService<JpTjsqCjr, JpTjsqCjrDao>{
	
	/**
	 * 根据商户编号和申请单号获取乘机人
	 * @param shbh
	 * @param sqdh
	 * @return
	 */
	public JpTjsqCjr getjptjsqcjr(String shbh,String sqdh){
		return this.getMyBatisDao().gettjsqcjr(shbh, sqdh);
	}
}
