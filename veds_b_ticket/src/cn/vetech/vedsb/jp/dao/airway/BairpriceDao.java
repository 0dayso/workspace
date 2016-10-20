/**
*@Title:       
*@Description: 
*@Author:      
*@Version:     1.1
*@Date:        2015-04-09
*@Modify:      
*/

package cn.vetech.vedsb.jp.dao.airway;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.airway.Bairprice;

@JpMyBatisRepository
public interface BairpriceDao extends Mapper<Bairprice>{
	
	/**
	 * 获取Y舱位价格
	 * @param bairprice
	 * @return
	 */
	Double getYcj(Bairprice bairprice);
}
