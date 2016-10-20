package cn.vetech.vedsb.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.common.entity.base.SmsFsdl;
import cn.vetech.vedsb.common.entity.base.SmsMb;
import cn.vetech.vedsb.common.entity.base.SmsMbSh;

public interface SmsDao extends Mapper<SmsFsdl>{

	/**
	 * <行程单中查找短信模板>
	 * 
	 * @param mb
	 * @return [参数说明]
	 * 
	 * @return List<SmsMb> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	List<SmsMb> querySmsmbByXcd(SmsMb mb);

	/**
	 * <查找短信模板>
	 * 
	 * @param mbsh
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<SmsMbSh> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	List<SmsMbSh> querySmsmbshByXcd(String shbh,String lx);

	/**
	 * 根据航班异动状态确定短信发送内容
	 * @param shbh
	 * @param mbbh
	 * @return
	 */
	@Select(value="select nrsz from sms_mb_sh where shbh=#{param1} and mbbh=#{param2}")
	SmsMbSh queryShByZt(String shbh, String mbbh);

	/**
	 * 根据商户编号查询短信模板集合
	 * @param shbh
	 * @return
	 */
	@Select(value="select * from sms_mb_sh where shbh=#{param1}")
	List<SmsMbSh> querySmsmbByShbh(String shbh);
	
	/**
	 * 根据商户编号和ID查询短信模板集合
	 * @param shbh
	 * @return
	 */
	@Select(value="select * from sms_mb_sh where shbh=#{param1} and id=#{param2}")
	SmsMbSh getSmsmbById(String shbh,String id);

}
