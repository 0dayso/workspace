package cn.vetech.vedsb.common.dao.base;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.common.entity.base.VeEmailSz;

public interface VeEmailSzDao extends Mapper<VeEmailSz> {

	/**
	 * 通过商户编号获取邮件设置信息
	 * @param shbh
	 * @return
	 */
	@Select(value = "select * from VE_EMAIL_SZ where shbh=#{param1} and rownum=1 ")
	VeEmailSz getVeEmailSzByShbh(String shbh);

}
