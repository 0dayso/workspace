package cn.vetech.vedsb.jp.service.jpddgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpddgl.JpKhddHdDao;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;

@Service
public class JpKhddHdServiceImpl  extends MBaseService<JpKhddHd, JpKhddHdDao>{
	/**
	 * 根据订单编号获取航段列表
	 */
	public List<JpKhddHd> getKhddHdListByDDbh(String ddbh, String shbh) {
		JpKhddHd record=new JpKhddHd();
		record.setDdbh(ddbh);
		record.setShbh(shbh);
		record.setOrderBy("cfsj asc");
		List<JpKhddHd> ddhdList = this.getMyBatisDao().select(record);
		return ddhdList;
	}
	/**
	 * 根据订单编号（订单编号用数组存放，支持查多个）查航段表数据，用于交票
	 * @param ddbhs String[];shbh String
	 * @return
	 */
	public List<JpKhddHd> selectJpByDdbh(Map<String, Object> param){
		return this.getMyBatisDao().selectJpByDdbh(param);
	}
	
	public void deleteByWbdh(String wbdh,String shbh){
		this.getMyBatisDao().deleteByWbdh(wbdh,shbh);
	}
}
