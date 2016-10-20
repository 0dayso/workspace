package cn.vetech.vedsb.jp.service.jpcwgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.vetech.vedsb.jp.dao.jpcwgl.JpYsdzXcDao;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpYsdzXc;
@Service
public class JpYsdzXcServiceImpl extends JpYsdzMxService<JpYsdzXc,JpYsdzXcDao>{
	/**
	 * 检查是否已对账
	 * @param zbid
	 * @param shbh
	 * @return
	 */
	public int genCompareCount(String zbid,String shbh){
		return getMyBatisDao().genCompareCount(zbid, shbh);
	}
	/**
	 * 检查当前日期之后是否已对账
	 * @param syrq
	 * @param shbh
	 * @param wdid
	 * @return
	 */
	public int genAfterCompareCount(String syrq,String shbh,String wdid){
		return getMyBatisDao().genAfterCompareCount(syrq, shbh, wdid);
	}
	/**
	 * 获取需要对账的未到账数据或漏单数据
	 * @param wdpt
	 * @param shbh
	 * @param wdid
	 * @param jglx
	 * @param syrqs
	 * @param syrqz
	 * @return
	 */
	/**
	 * 获取需要对账的未到账数据或漏单数据
	 * @param shbh
	 * @param wdid
	 * @param jglx
	 * @param syrqs
	 * @param syrqz
	 * @return
	 */
	@Override
	public List<Map<String, Object>> genWdzOrLd(String shbh, String wdid, String jglx, String syrqs,String syrqz) {
		return getMyBatisDao().genWdzOrLd(shbh, wdid, jglx, syrqs, syrqz);
	}
	@Override
	public void delResult(String shbh, String zbid) {
		JpYsdzXc t=new JpYsdzXc();
		t.setShbh(shbh);
		t.setZbid(zbid);
		getMyBatisDao().delete(t);
	}
	/**
	 * 清除到账状态
	 * @param shbh
	 * @param wdid
	 * @param dzrq
	 */
	@Override
	public void clearBdzt(String shbh, String wdid, String dzrq) {
		getMyBatisDao().clearBdzt(shbh, wdid, dzrq);
	}
	/**
	 * 清除补单状态
	 * @param shbh
	 * @param wdid
	 * @param dzrq
	 */
	@Override
	public void clearDzzt(String shbh, String wdid, String dzrq) {
		getMyBatisDao().clearDzzt(shbh, wdid, dzrq);
		
	}
}