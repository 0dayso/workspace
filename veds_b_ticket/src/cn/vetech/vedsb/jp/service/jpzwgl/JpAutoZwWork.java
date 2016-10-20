package cn.vetech.vedsb.jp.service.jpzwgl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.SmsFsdl;
import cn.vetech.vedsb.common.entity.base.VeEmailSz;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.common.service.base.SmsServiceImpl;
import cn.vetech.vedsb.common.service.base.VeEmailSzServiceImpl;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsq;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqCjr;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqDr;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqZdsc;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.vedsb.platpolicy.cps.response.pay.CpsShzhData;
import cn.vetech.vedsb.platpolicy.cps.service.CpsService;
import cn.vetech.vedsb.specialticket.request.KwCancelRequest;
import cn.vetech.vedsb.specialticket.request.KwOrderRequest;
import cn.vetech.vedsb.specialticket.request.KwStatusRequest;
import cn.vetech.vedsb.specialticket.response.KwCancelResponse;
import cn.vetech.vedsb.specialticket.response.KwOrderResponse;
import cn.vetech.vedsb.specialticket.response.KwResponse;
import cn.vetech.vedsb.specialticket.response.KwStatusResponse;
import cn.vetech.vedsb.specialticket.service.KwService;
import cn.vetech.vedsb.specialticket.util.KwUtil;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.ZwUtil;
import cn.vetech.vedsb.utils.mail.Email;
import cn.vetech.vedsb.utils.mail.EmailTask;

/**
 * 自动追位job
 * 1.取消已失效的订单
 * 2.将临时队列中可追位的写到正式队列
 * 3.将导入的追位单可追位的写到正式队列
 * 4.将正式队列的申请单提交到追位系统
 * 5.获取追位单的状态
 * 6.余额获取，不足短信或邮件提醒
 * @author liunansheng
 *
 */
@Service
public class JpAutoZwWork {
	@Autowired
	private JpTjsqServiceImpl jpTjsqServiceImpl;
	@Autowired
	private JpTjsqZdscServiceImpl jpTjsqZdscServiceImpl;
	@Autowired
	private JpTjsqDrServiceImpl jpTjsqDrServiceImpl;
	@Autowired
	private JpTjsqCjrServiceImpl jpTjsqCjrServiceImpl;
	@Autowired
	private JpTjsqJcgzServiceImpl jpTjsqJcgzServiceImpl;
	@Autowired
	private CpsService cpsService;
	@Autowired
	private KwService kwService;//请求追位系统service
	@Autowired
	private SmsServiceImpl smsServiceImpl;
	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
	@Autowired
	private VeEmailSzServiceImpl veEmailSzServiceImpl;
	
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	
	private static Map<String,String> SERCH_TIMEMAP=new HashMap<String, String>();
	
	private static int DRCOUNT=200;
	/**
	 *开始追位任务处理 
	 */
	public void startWork(String shbh) {
		//取消已失效的订单
		cancelOrder(shbh);
		//将临时队列中可追位的写到正式队列
		boolean yeFlag=checkYe(shbh,0d);
		if(!yeFlag){//余额不足则不导入,不提交追位
			lsDlToTjsq(shbh);
			//将导入的追位单可追位的写到正式队列
			drToTjsq(shbh);
			//将正式队列的申请单提交到追位系统
			sendOrder(shbh);
		}
		//获取追位单的状态
		genOrderZt(shbh);
		//余额获取，不足短信或邮件提醒
		checkAvailable(shbh);
	}
	/**
	 * 取消已失效的订单
	 */
	private void cancelOrder(String shbh){
		try {
			Date oneMothAgo=VeDate.getPreDay(VeDate.getNowDateShort(), -30);
			String qxyyHz="[" + VeDate.getUserDate("MM-dd HH:mm") + "]";
			/**原订单已取消自动取消追位*/
			List<JpTjsq> ddqxList=jpTjsqServiceImpl.getDqxForJpddqx(oneMothAgo, shbh);
			String qxyy="原订单已取消"+qxyyHz;
			cancelOrderList(ddqxList, shbh, qxyy,false);
			
			/**原订单已出票自动取消追位,并生成新的追位单*/
			List<JpTjsq> cphqxList=jpTjsqServiceImpl.getDqxForYcp(oneMothAgo, shbh);
			qxyy="原订单已出票,需重新匹配规则"+qxyyHz;
			cancelOrderList(cphqxList, shbh, qxyy, true);
			
			/**原订单退废自动取消追位*/
			List<JpTjsq> ddtfList=jpTjsqServiceImpl.getDqxForTp(oneMothAgo, shbh);
			qxyy="原订单已申请退废"+qxyyHz;
			cancelOrderList(ddtfList, shbh, qxyy,false);
			
			/**原订单改签自动取消追位*/
			List<JpTjsq> ddgqList=jpTjsqServiceImpl.getDqxForGq(oneMothAgo, shbh);
			qxyy="原订单已申请改签"+qxyyHz;
			cancelOrderList(ddgqList, shbh, qxyy,false);
			
			/**追位单失效自动取消追位*/
			List<JpTjsq> sxZwList=jpTjsqServiceImpl.getDqxForGq(oneMothAgo, shbh);
			qxyy="已经过了追位单的有效期"+qxyyHz;
			cancelOrderList(sxZwList, shbh, qxyy,false);
			
			/**未出票过了指定时间未追到位自动取消追位*/
			Map<String, String> zwParamMap=jpTjsqJcgzServiceImpl.getTjsqJcgzMap(shbh);
			String wcpqfsk = ZwUtil.getValueByMap(zwParamMap, "WCPQFSK", "0");//未出票截止起飞多久未追到自动取消
			Date now=VeDate.getNow();
			Date wcpqfsj=VeDate.getPreMin(now, NumberUtils.toInt(wcpqfsk));
			List<JpTjsq> wcpQxList=jpTjsqServiceImpl.getDqxForYdQf(oneMothAgo,wcpqfsj, shbh);
			qxyy="未出票截止航班起飞前"+wcpqfsk+"小时未追到自动取消"+qxyyHz;
			cancelOrderList(wcpQxList, shbh, qxyy,false);
			
			/**已出票过了指定时间未追到位自动取消追位*/
			String ycpqfsk = ZwUtil.getValueByMap(zwParamMap, "YCPQFSK", "0");//已出票截止起飞多久未追到自动取消
			Date ycpqfsj=VeDate.getPreMin(now, NumberUtils.toInt(ycpqfsk));
			List<JpTjsq> ycpQxList=jpTjsqServiceImpl.getDqxForCpqf(oneMothAgo,ycpqfsj, shbh);
			qxyy="已出票截止航班起飞前"+wcpqfsk+"小时未追到自动取消"+qxyyHz;
			cancelOrderList(ycpQxList, shbh, qxyy,false);
			
			/** 检查退票费和退票费率是否发生变化*/
			String key=shbh+"lastCheckTime";
			String lastCheckTime=SERCH_TIMEMAP.get(key);
			String nowStr=VeDate.getStringDate();
			/**间隔24小时检查退票费是否变化*/
			if(StringUtils.isNotBlank(lastCheckTime) 
					&& VeDate.getTwoMin(nowStr, lastCheckTime)<60*24){
				return;//间隔24小时
			}else {
				SERCH_TIMEMAP.put(key, nowStr);
			}
			List<JpTjsq> ycpList=jpTjsqServiceImpl.getYcpWwc(oneMothAgo, shbh);
			qxyy="退票费变高"+qxyyHz;
			for(JpTjsq ycpSq : ycpList){
				if(StringUtils.isBlank(ycpSq.getDdbh())){
					continue;
				}
				Map tpXx=procedureServiceImpl.genZwddTpf(ycpSq.getDdbh(),ycpSq.getShbh());
				Object tpfObj=tpXx.get("p_tpf");
				double tpf=tpfObj==null ? 0 : NumberUtils.toDouble(tpfObj.toString());
				double ytpf=ycpSq.getTpf() == null ? 0 : ycpSq.getTpf();
				if(tpf>ytpf){//退票费比原计算的退票费高则重新生成追位单
					cancelOneOrder(ycpSq, shbh, qxyy, true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 批量取消追位
	 * @param list
	 * @param shbh
	 * @param qxyy
	 */
	private void cancelOrderList(List<JpTjsq> list,String shbh,String qxyy,boolean sfscXdd){
		if(CollectionUtils.isEmpty(list)){
			return;
		}
		for (JpTjsq jpTjsq : list) {
			cancelOneOrder(jpTjsq, shbh, qxyy, sfscXdd);
		}
	}
	/**
	 * 取消单个订单
	 * @param jpTjsq
	 * @param shbh
	 * @param qxyy
	 * @param sfscXdd
	 */
	private void cancelOneOrder(JpTjsq jpTjsq,String shbh,String qxyy,boolean sfscXdd){
		try {
			String error=cancelZw(jpTjsq, shbh);
			if(StringUtils.isNotBlank(error)){
				jpTjsqServiceImpl.cancelBdzwd(jpTjsq, shbh, qxyy);
				if(sfscXdd){
					//调用后台生成新的追位单到临时队列
					procedureServiceImpl.creatZwdl(shbh, jpTjsq.getDdbh());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将临时队列中可追位的写到正式队列
	 */
	private void lsDlToTjsq(String shbh){
		try {
			JpTjsqZdsc jpTjsqZdsc=new JpTjsqZdsc();
			jpTjsqZdsc.setShbh(shbh);
			jpTjsqZdsc.setCount(DRCOUNT);
			List<JpTjsqZdsc> ddrList=jpTjsqZdscServiceImpl.getDdrDlList(jpTjsqZdsc);
			if(CollectionUtils.isEmpty(ddrList)){
				return;//没有需要导入正式队列的数据
			}
			for (JpTjsqZdsc oneBean : ddrList) {
				jpTjsqServiceImpl.lsdlToTjsq(jpTjsqZdsc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将导入的追位单可追位的写到正式队列
	 */
	private void drToTjsq(String shbh){
		try {
			JpTjsqDr jpTjsqDr=new JpTjsqDr();
			jpTjsqDr.setShbh(shbh);
			jpTjsqDr.setCount(DRCOUNT);
			jpTjsqDrServiceImpl.getDdrDlList(jpTjsqDr);
			List<JpTjsqDr> drList=jpTjsqDrServiceImpl.getDdrDlList(jpTjsqDr);
			for (JpTjsqDr dr : drList) {
				jpTjsqServiceImpl.drToTjsq(jpTjsqDr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将正式队列的申请单提交到追位系统
	 */
	private void sendOrder(String shbh){
		try {
			JpTjsq jpTjsq=new JpTjsq();
			jpTjsq.setShbh(shbh);
			Date now=VeDate.getNowDateShort();
			jpTjsq.setSqDatetime(VeDate.getPreDay(now, -3));
			jpTjsq.setCount(DRCOUNT*2);
			List<JpTjsq> list=jpTjsqServiceImpl.getDtjDlList(jpTjsq); 
			for (JpTjsq bean : list) {
				JpTjsqCjr searchCjr=new JpTjsqCjr();
				String sqdh=bean.getSqdh();
				searchCjr.setSqdh(sqdh);
				searchCjr.setShbh(shbh);
				List<JpTjsqCjr> cjrList=jpTjsqCjrServiceImpl.getMyBatisDao().select(searchCjr);
				/** 请求追位系统申请追位*/
				KwOrderRequest kwRequest=KwUtil.tjsqToKwRequest(jpTjsq, cjrList);
				KwOrderResponse kwOrderResponse=kwService.sendOrder(shbh, kwRequest);
				if(KwResponse.SUCCESS.equals(kwOrderResponse.getCode())){//追位成功
					JpTjsq zwcgBean=new JpTjsq();
					zwcgBean.setShbh(shbh);
					zwcgBean.setSqdh(sqdh);
					zwcgBean.setSftjzw("1");
					zwcgBean.setSqZt(DictEnum.ZWDLSQZT.YSH.getValue());
					jpTjsqServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(zwcgBean);
				}else {
					JpTjsq zwcgBean=new JpTjsq();
					zwcgBean.setShbh(shbh);
					zwcgBean.setSqdh(sqdh);
					zwcgBean.setSftjzw("1");
					zwcgBean.setSqZt(DictEnum.ZWDLSQZT.ZSB.getValue());
					jpTjsqServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(zwcgBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取追位单的状态
	 */
	private void genOrderZt(String shbh){
		try {
			String zwsj = SERCH_TIMEMAP.get(shbh);
			if(StringUtils.isBlank(zwsj)){
				zwsj="";
			}
			/** 请求追位系统，获取追位单状态*/
			KwStatusRequest kwStatusRequest=new KwStatusRequest();
			KwStatusResponse response=kwService.genOrderStauts(shbh, kwStatusRequest);
			if(KwResponse.FAILL.equals(response.getCode()) || CollectionUtils.isEmpty(response.getOrders())){
				return;//请求追位系统失败或没有获取到成功的追位单
			}
			SERCH_TIMEMAP.put(shbh, response.getDatetime());
			jpTjsqServiceImpl.updateZwcg(shbh, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 余额获取，不足短信或邮件提醒
	 */
	private void checkAvailable(String shbh){
		try {
			Map<String, String> zwParamMap=jpTjsqJcgzServiceImpl.getTjsqJcgzMap(shbh);
			String sfkqtx = ZwUtil.getValueByMap(zwParamMap, "SFKQTX", "0");//1提醒
			if(!"1".equals(sfkqtx)){//没开启提醒
				return;
			}
			String txed =ZwUtil.getValueByMap(zwParamMap, "TXED", "0");// 提醒额度
			boolean yeCz=checkYe(shbh, NumberUtils.toDouble(txed));
			if(yeCz){
				return;//余额充足
			}
			String key=shbh+"lastNoticeTime";
			String lastNoticeTime=SERCH_TIMEMAP.get(key);
			String now=VeDate.getStringDate();
			/**控制提醒平率*/
			if(StringUtils.isNotBlank(lastNoticeTime) 
					&& VeDate.getTwoMin(now, lastNoticeTime)<30){
				return;//间隔30分钟
			}else {
				SERCH_TIMEMAP.put(key, now);
			}
			Shyhb t=new Shyhb();
			t.setShbh(shbh);
			t.setQxjb("9");
			List<Shyhb> users=shyhbServiceImpl.getMyBatisDao().select(t);
			Shyhb sh_yhb=users.get(0);
			String txmobile =ZwUtil.getValueByMap(zwParamMap, "TXMOBILE", "");// 提醒的手机号
			String msg="您的追位账户余额不足，请及时充值！";
			if(StringUtils.isNotBlank(txmobile)){
				//发送短信
				String[] yhs=txmobile.split("/");
				this.sendMessage(yhs, msg, sh_yhb);
				
			}
			String txemail =ZwUtil.getValueByMap(zwParamMap, "TXEMAIL", "");// 提醒的手机号
			if(StringUtils.isNotBlank(txemail)){
				//发送邮件
				txemail=txemail.replaceAll("/", ",");
				sendForEmail(txemail, msg, "追位余额不足提醒", sh_yhb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 检查追位余额是否大于指定的金额
	 * @return
	 */
	private boolean checkYe(String shbh,Double compareYe){
		try {
			CpsShzhData zh=cpsService.getZwZh(shbh, "");
			if(zh==null){
				return false;
			}
			double yeDx = compareYe==null ? 0 : compareYe;
			double zye=Arith.add(NumberUtils.toDouble(zh.getKyye(),0), NumberUtils.toDouble(zh.getZszhye(),0));
			return zye > yeDx;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 *取消追位
	 */
	private String cancelZw(JpTjsq jpTjsq,String shbh){
		if(!"1".equals(jpTjsq.getSftjzw())){
			return null;//还没提交追位系统
		}
		KwCancelRequest request=new KwCancelRequest();
		request.setSqdh(jpTjsq.getSqdh());
		KwCancelResponse response=kwService.cancelOrder(shbh, request);
		if(KwResponse.FAILL.equals(response)){
			return "追位失败："+response.getError();
		}
		return null;
	}
	private void sendMessage(String[] yhs,String msg,Shyhb sh_yhb) throws Exception{
		SmsFsdl sms_fsdl = new SmsFsdl();
		int len=yhs.length;
		Date now=new Date();
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				sms_fsdl.setFl("2");//分类1表示平台发送的2表示商户发送的
				sms_fsdl.setFsCzr(sh_yhb.getBh()+sh_yhb.getXm());
				sms_fsdl.setFsDw(sh_yhb.getShbh());
				sms_fsdl.setCjsj(now);
				sms_fsdl.setFsfs("1");//发送方式1自动发送0手动发送
				sms_fsdl.setYqfssj(now);//要求时间
				sms_fsdl.setJsShbh(sh_yhb.getShbh());
				sms_fsdl.setJshm(yhs[i]);
				sms_fsdl.setFsnr(msg);//发送内容
				sms_fsdl.setIfqm("0");
				sms_fsdl.setTxfsid("");
				smsServiceImpl.insert(sms_fsdl);
			}
		}
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
}
