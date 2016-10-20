package cn.vetech.vedsb.jp.service.jpbcdgl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpbcd.JpBcdDao;
import cn.vetech.vedsb.jp.entity.jpbcdgl.JPBcd;
import cn.vetech.vedsb.jp.entity.jpbcdgl.JpCyd;
@Service
public class JpBcdServiceImpl extends MBaseService<JPBcd,JpBcdDao> {
	@Autowired
	private JpCydServiceImpl jpCydServiceImpl;
	/**
	 * 根据商户编号和业务单号获取补差单信息
	 * @param shbh
	 * @param ywdh
	 * @return
	 */
	public List<JPBcd> getbcdList(String shbh,String ywdh,String ywlx){
		return this.getMyBatisDao().getbcdList(shbh, ywdh,ywlx);
	}
	
	@Transactional
	public void creatBcd(JPBcd jpbcd ,JpCyd jpcyd,String bh) throws Exception{
		this.insert(jpbcd);//生成补差单记录
		jpcyd.setBcdh(jpbcd.getId());//增加补差单号
		jpcyd.setZt("2");//修改状态,改成确认支付
		jpcyd.setQysj(new Date());
		jpcyd.setQyyh(bh);
		this.jpCydServiceImpl.update(jpcyd);//更新
	}
}
