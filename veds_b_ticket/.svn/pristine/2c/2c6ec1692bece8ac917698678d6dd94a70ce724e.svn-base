package cn.vetech.vedsb.jp.service.jptpgl.zxzw;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.SmsFsdl;
import cn.vetech.vedsb.common.entity.base.VeEmailSz;
import cn.vetech.vedsb.common.service.base.SmsServiceImpl;
import cn.vetech.vedsb.common.service.base.VeEmailSzServiceImpl;
import cn.vetech.vedsb.utils.mail.Email;
import cn.vetech.vedsb.utils.mail.EmailTask;


/**
 * Xe失败或异常统一的提醒类
 * @author wangshengliang
 *
 */
@Component
public class CommXesbTx {
	
	@Autowired
	private SmsServiceImpl smsServiceImpl;
	
	@Autowired
	private VeEmailSzServiceImpl veEmailSzServiceImpl;
	
	public CommXesbTx() {
		super();
		
	}
	
	
	/**
	 * 邮件提醒
	 * 
	 * @return [参数说明]
	 * 
	 * @return boolean [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void sendForEmail(String yjhm,String msg,String title,Shyhb sh_yhb) {
		EmailTask et = null;
		//从数据库中读取邮件配置信息
		VeEmailSz email_sz=new VeEmailSz();
		try{
			email_sz = veEmailSzServiceImpl.getVeEmailSzByShbh(sh_yhb.getShbh());
		}catch(Exception e){
			e.printStackTrace();
		}
		//如果设置了公共邮箱就取公共邮箱的数据,如果没有设置就取配置文件的设置
		if(null != email_sz){
			et = EmailTask.getInstance(email_sz);
		}else{
			et = EmailTask.getInstance();
		}
		
		try {
			Email _email = new Email(yjhm, title, msg, true, null);
			EmailTask.addMail(_email);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new Exception("邮件发送失败，错误:" + e.getMessage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		while (!EmailTask.isEmptyMailTask()) {
			et.send();
		}
	}
	
	/**
	 *写数据入短信表 
	 * @param yhs
	 * @param msg [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @throws Exception 
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void smsCall(String [] yhs,String msg,String zgs,Shyhb sh_yhb,String ddbh,String pnr_no) throws Exception{
		SmsFsdl sms_fsdl = new SmsFsdl();
		int len=yhs.length;
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				sms_fsdl.setFl("2");//分类1表示平台发送的2表示商户发送的
				sms_fsdl.setFsCzr(sh_yhb.getBh()+sh_yhb.getXm());
				sms_fsdl.setFsDw(sh_yhb.getShbh());
				sms_fsdl.setCjsj(new Date());
				sms_fsdl.setFsfs("1");//发送方式1自动发送0手动发送
				sms_fsdl.setDxlx("4");//短信类型
				sms_fsdl.setYqfssj(VeDate.getNow());//要求时间
				sms_fsdl.setJsShbh(sh_yhb.getShbh());
				sms_fsdl.setDdbh(ddbh);
				sms_fsdl.setJshm(yhs[i]);
				sms_fsdl.setFsnr(msg);//发送内容
				sms_fsdl.setDxfsts((short)0);
				sms_fsdl.setIfqm("0");
				sms_fsdl.setTxfsid("");
				smsServiceImpl.insert(sms_fsdl);
			}
		}
	}

}
