package cn.vetech.vedsb.jp.service.jpddgl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Vecsb;
import cn.vetech.vedsb.common.service.base.VecsbServiceImpl;
import cn.vetech.vedsb.jp.dao.jpddgl.JpKhddYjDao;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddYj;
import cn.vetech.web.vedsb.SessionUtils;

@Service
public class JpkhddYjServiceImpl extends MBaseService<JpKhddYj, JpKhddYjDao> {
	@Autowired
	private JpKhddServiceImpl jpkhddService;
	@Autowired
	private VecsbServiceImpl vecsbService;
	/**
	 * <根据订单编号查找邮寄单>
	 * 
	 * @param ddbh
	 * @return [参数说明]
	 * 
	 * @return JpKhddYj [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public JpKhddYj queryJpKhddYjByDdbh(String ddbh) {
		return this.getMyBatisDao().queryJpKhddYjByDdbh(ddbh);
	}

	/**
	 * <邮寄行程单后改变邮寄单的状态，和增加邮寄单号>
	 * 
	 * @param jpKhddYj [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void updateYjdhMore(JpKhddYj jpKhddYj) {
		this.getMyBatisDao().updateYjdhMore(jpKhddYj);
	}

	/**
	 * <通过订单编号查找邮寄单记录>
	 * 
	 * @param ddbh
	 * @return [参数说明]
	 * 
	 * @return List<String> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public String queryYjlistByddbh(String ddbh) {
		return this.getMyBatisDao().queryYjlistByddbh(ddbh);
	}

	/**
	 * <修改邮寄单的邮寄状态>
	 * 
	 * @param jpKhddYj
	 * @return [参数说明]
	 * 
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public int updateYjzt(JpKhddYj jpKhddYj) {
		return this.getMyBatisDao().updateYjzt(jpKhddYj);
	}

	/**
	 * <打印后修改订单表的几个状态和增加一个邮寄单>
	 * 
	 * @param jpKhddYj
	 * @param ddbhs
	 * @param flag
	 * @return [参数说明]
	 * 
	 * @return int [返回类型说明]
	 * @throws Exception 
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public int dochangeYjAndDd(JpKhddYj jpKhddYj, String ddbhs, String flag) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();
		int result=0;
		//查默认模板
		Vecsb csb=new Vecsb();
		csb.setBh("5555");//5555代表默认模板
		csb.setCompid(user.getShbh());
		Vecsb csb1=vecsbService.getVecsbByBh(csb);
		if(csb1==null){
			csb.setCsms("这个参数代表着打印时设置的默认模板");
		    csb.setCsz1(jpKhddYj.getKdlx());
		    csb.setCj_userid(user.getBh());
		    csb.setCjsj(new Date());
		    csb.setSfxs("0");
		    csb.setCslxmc("打印模板");
		    csb.setCslxbh("100110301");
			vecsbService.insert(csb);
		}
		if(StringUtils.isNotBlank(ddbhs)){//如果是合并打印
			String[] ddh=ddbhs.split(",");
			for(int i=0;i<ddh.length;i++){
				jpKhddYj.setDdbh(ddh[i]);
				jpkhddService.updateYjzt(jpKhddYj);
			}
			jpKhddYj.setDdbh(ddbhs);
		}else{
			jpkhddService.updateYjzt(jpKhddYj);
		}
		if(StringUtils.isBlank(flag)||!flag.equals("1")){//如果是待打印中的打印操作,就新增邮寄单
			this.insert(jpKhddYj);
		}else if(StringUtils.isBlank(ddbhs)){//如果是待邮寄中的重新打印操作,并且不是合并打印.就修改邮寄单
			this.updateYjzt(jpKhddYj);
		}
		result=1;
		return result;
	}


}
