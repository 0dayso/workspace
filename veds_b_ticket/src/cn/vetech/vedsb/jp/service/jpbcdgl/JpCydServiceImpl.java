package cn.vetech.vedsb.jp.service.jpbcdgl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.dao.jpbcd.JpCydDao;
import cn.vetech.vedsb.jp.entity.jpbcdgl.JPBcd;
import cn.vetech.vedsb.jp.entity.jpbcdgl.JpCyd;
import cn.vetech.web.vedsb.SessionUtils;
@Service
public class JpCydServiceImpl extends MBaseService<JpCyd, JpCydDao>{
	@Autowired
	private JpBcdServiceImpl jpBcdServiceImpl;
	/**
	 * 差异单查询机票订单
	 * @param shbh
	 * @param jpPnr
	 * @param jpWbdh
	 * @return
	 */
	public List<Map<String,Object>> searchJpKhdd(JpCyd jpcyd){
		return this.getMyBatisDao().getCydKhdd(jpcyd);
	}
	
	/**
	 * 根据订单编号查询
	 * @param shbh
	 * @param ddbh
	 * @return
	 */
	public List<Map<String,Object>> searchJpKhddDdbh(JpCyd jpcyd){
		return this.getMyBatisDao().getCydKhddDdbh(jpcyd);
	}
	/**
	 * 差异单查询退废订单
	 * @param shbh
	 * @param jpPnr
	 * @param jpWbdh
	 * @return
	 */
	public List<Map<String,Object>> searchJpTpdd(JpCyd jpcyd){
		return this.getMyBatisDao().getCydTfdd(jpcyd);
	}
	
	/**
	 * 根据退票单号查询退票订单
	 * @param jpcyd
	 * @return
	 */
	public List<Map<String,Object>> searchJpTpddTpdh(JpCyd jpcyd){
		return this.getMyBatisDao().getCydTfddTpdh(jpcyd);
	}
	/**
	 * 差异单查询改签订单
	 * @param shbh
	 * @param jpPnr
	 * @param jpWbdh
	 * @return
	 */
	public List<Map<String,Object>> searchJpGqdd(JpCyd jpcyd){
		return this.getMyBatisDao().getCydgqdd(jpcyd);
	}
	
	/**
	 * 根据改签单号查询改签单
	 * @param jpcyd
	 * @return
	 */
	public List<Map<String,Object>> searchJpTpddGqdh(JpCyd jpcyd){
		return this.getMyBatisDao().getCydgqddGqdd(jpcyd);
	}
	/**
	 * 修改保存差异单
	 * @param jpcyd
	 */
	public void updateCyd(JpCyd jpcyd){
		this.getMyBatisDao().updateCyd(jpcyd);
	}
	
	public List<Map<String, Object>> getHistoryCyList(Map<String, Object> map){
		return this.getMyBatisDao().getHistoryCyList(map);
		
	}
	
	@Transactional
	public void updateCyHistory(JpCyd jpcyd) throws  Exception{
		try {
			Shyhb user = SessionUtils.getShshbSession();
			this.update(jpcyd);
			String bcdh = jpcyd.getBcdh();
			Double cyje = jpcyd.getCyje();
			if(StringUtils.isNotBlank(bcdh)){
				JPBcd bcd = new JPBcd();
				bcd.setId(bcdh);
				bcd.setShbh(user.getShbh());
				bcd = this.jpBcdServiceImpl.getEntityById(bcd);
				bcd.setBcje(cyje);
				this.jpBcdServiceImpl.update(bcd);
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
