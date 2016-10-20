package cn.vetech.vedsb.jp.service.jpddgl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpddgl.JpKhddKzDao;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddKz;

@Service
public class JpKhddKzServiceImpl extends MBaseService<JpKhddKz, JpKhddKzDao>{
	/**
	 * 分页查询改签单和改签单明细
	 * @param jpgqd
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	
	public Page query(JpKhddKz jpkhddkz, String sortType) throws Exception {
		Page page = new Page(jpkhddkz.getStart(),jpkhddkz.getCount());
		List<Map<String,Object>> jpgqdList = this.getMyBatisDao().query(jpkhddkz);
		int pagecount = this.getMyBatisDao().getPageCount(jpkhddkz);
		page.setList(jpgqdList);
		page.setTotalCount(pagecount);
		return page;
	}
	
	
	/**
	 * 取退票单详
	 */
	public Map<String, Object> detail(JpKhddKz jpkhddkz) {
		return this.getMyBatisDao().detail(jpkhddkz);
	}
	
    /**
     * 通过ID取退票单主表信息
     */
	public JpKhddKz getJpKhddKzByDdbh(JpKhddKz jpkhddkz){
		
		return this.getMyBatisDao().getJpKhddKzByDdbh(jpkhddkz);
	}
	/**
	 * 根据订单编号（订单编号用数组存放，支持查多个）查航段表数据，用于交票
	 * @param ddbhs String[];shbh String
	 * @return
	 */
	public List<JpKhddKz> selectJpByDdbh(Map<String,Object> param) {
		return this.getMyBatisDao().selectJpByDdbh(param);
	}
	/**
	 * 票号回填后，修改扩展表交票状态
	 * @param 查询条件 param ddbhs String[];shbh String  要修改的字段phhtzt String;phhtsbyy String
	 * @return
	 */
	public int updateJpZtByDdbh(Map<String,Object> param){
		return this.getMyBatisDao().updateJpZtByDdbh(param);
	}
	/**
	 * 根据出票单号修改扩展表字段（目前携程用）
	 * @param kz
	 * @return
	 */
	public int updateKzByCpdh(JpKhddKz kz){
		return this.getMyBatisDao().updateKzByCpdh(kz);
	}
	
	public void deleteByWbdh(String wbdh,String shbh){
		this.getMyBatisDao().deleteByWbdh(wbdh,shbh);
	}
}
