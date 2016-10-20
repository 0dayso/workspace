package cn.vetech.vedsb.jp.dao.jpddgl;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddYj;

@JpMyBatisRepository
public interface JpKhddYjDao extends Mapper<JpKhddYj> {

	//根据订单编号查找邮寄单
	JpKhddYj queryJpKhddYjByDdbh(String ddbh);

	//邮寄行程单后改变邮寄单的状态，和增加邮寄单号
	void updateYjdhMore(JpKhddYj jpKhddYj);

	//通过订单编号查找邮寄单记录
	String queryYjlistByddbh(String ddbh);

	//修改邮寄单的邮寄状态
	int updateYjzt(JpKhddYj jpKhddYj);
	//根据订单编号删除邮寄单
	void deleteYjByDdbh(String bh);

}
