/**
 *@Title:       
 *@Description: 
 *@Author:      
 *@Version:     1.1
 *@Date:        2015-04-09
 *@Modify:      
 */

package cn.vetech.vedsb.jp.service.jpddgl;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.airway.BairpriceDao;
import cn.vetech.vedsb.jp.entity.airway.Bairprice;

@Service
public class BairpriceServiceImpl extends MBaseService<Bairprice, BairpriceDao> {

	/**
	 * 获取Y舱位价格
	 * @param bairprice
	 * @return
	 */
	public Double getYcj(Bairprice bairprice){
		return this.getMyBatisDao().getYcj(bairprice);
	}
}
