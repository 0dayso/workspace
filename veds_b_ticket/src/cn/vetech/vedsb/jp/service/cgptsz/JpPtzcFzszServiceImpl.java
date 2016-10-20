package cn.vetech.vedsb.jp.service.cgptsz;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.dao.cgptsz.JpPtzcFzszDao;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcFzsz;
@Service
public class JpPtzcFzszServiceImpl extends MBaseService<JpPtzcFzsz,JpPtzcFzszDao>{
	
	/**
	 * 根据业务类型获取分账设置
	 * @param fzsz
	 * @return
	 */
	public List<JpPtzcFzsz> getFzszListByYwlx(String ywlx, String shbh) throws Exception{
		return this.getMyBatisDao().getFzszListByYwlx(ywlx, shbh);
	}
	public JpPtzcFzsz genZfkmByDjm(String ywlx,String shhb,String djm,String platcode){
		return this.getMyBatisDao().genZfkmByDjm(ywlx, shhb, djm, platcode);
	}
	public void delFzszById(String id, String shbh) throws Exception{
		this.getMyBatisDao().delFzszById(id, shbh);
	}
	
	public void saveFzszById(JpPtzcFzsz fzsz) throws Exception {
		if (StringUtils.isBlank(fzsz.getId())) {
			String id = VeDate.getNo(6);
			fzsz.setId(id);
			this.getMyBatisDao().insert(fzsz);
		} else {
			this.getMyBatisDao().updateFzszById(fzsz);
		}
	}
}