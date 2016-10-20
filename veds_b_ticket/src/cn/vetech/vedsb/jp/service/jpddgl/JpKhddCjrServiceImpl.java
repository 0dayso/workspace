package cn.vetech.vedsb.jp.service.jpddgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpddgl.JpKhddCjrDao;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;


@Service
public class JpKhddCjrServiceImpl extends MBaseService<JpKhddCjr, JpKhddCjrDao>{
	/**
	 * 根据订单编号获取乘机人列表
	 */
	public List<JpKhddCjr> getKhddCjrListByDDbh(String ddbh, String shbh) {
		JpKhddCjr record=new JpKhddCjr();
		record.setDdbh(ddbh);
		record.setShbh(shbh);
		record.setOrderBy("sxh");
		List<JpKhddCjr> cjrList = this.getMyBatisDao().select(record);
		return cjrList;
	}
	/**
	 * 根据订单编号（订单编号用数组存放，支持查多个）查航段表数据，用于交票
	 * @param ddbhs String[];shbh String
	 * @return
	 */
	public List<JpKhddCjr> selectJpByDdbh(Map<String,Object> param){
		return this.getMyBatisDao().selectJpByDdbh(param);
	}
	
	/**
	 * 判断订单中是否包含儿童
	 * @param param
	 * @return
	 */
	public boolean isCjrEt(Map<String,Object> param){
		return this.getMyBatisDao().isCjrEt(param) > 0;
	}
	
	public void deleteByWbdh(String wbdh,String shbh){
		this.getMyBatisDao().deleteByWbdh(wbdh,shbh);
	}
}
