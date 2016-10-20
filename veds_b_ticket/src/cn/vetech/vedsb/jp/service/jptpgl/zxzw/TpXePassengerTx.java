package cn.vetech.vedsb.jp.service.jptpgl.zxzw;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cpsz.JpBspTpsz;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jpxepnr.JpXepnr;
import cn.vetech.vedsb.jp.service.cpsz.JpBspTpszServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.jp.service.jpxepnr.JpXepnrServiceImpl;

/**
 * 自动取消座位提醒
 * 
 * @author wangshengliang
 */
@Service
public class TpXePassengerTx {

	
	@Autowired
	private  JpKhddServiceImpl jpKhddServiceImpl;
	
	@Autowired
	JpBspTpszServiceImpl jpBspTpszServiceImpl;
	
	/**
	 * 错误消息提醒
	 */
	public void msgtx(String smsMsg, String rtxAndEmailMsg,Shyhb shyhb,JpTpd jptpd) {
		
		String shbh=jptpd.getShbh();
		JpBspTpsz bspTpsz=new JpBspTpsz();
		bspTpsz.setShbh(shbh);
		bspTpsz=jpBspTpszServiceImpl.getEntityById(bspTpsz);
		
		if (null != bspTpsz) {
			if ("1".equals(bspTpsz.getSfsbtx())) {
				CommXesbTx tx = new CommXesbTx();// xe失败后或者异常统一的处理接口
				if (StringUtils.isNotBlank(bspTpsz.getDxtx())) {
					try {
						tx.smsCall(bspTpsz.getDxtx().split("/"), smsMsg, shbh, shyhb,jptpd.getTpdh(), jptpd.getXsPnrNo());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (StringUtils.isNotBlank(bspTpsz.getYjtx())) {
					String title = "PNR为"+jptpd.getCgPnrNo()+"的退票单自动取消座位提醒";
					tx.sendForEmail(bspTpsz.getYjtx().replace("/", ","), rtxAndEmailMsg, title, shyhb);
				}
			}
		}
		/*
		// 103156本参数用于控制退票复核后自动取消座位失败后提醒，参数1 为1表示开启，0表示不开启，默认是0，按总公司设置
		String cs103156 = Function.acsb("103156", shbh, "YDHM", "0");
		// 本参数用于自动取消座位失败预警，选择短信提醒的方式。参数1，默认为0不启用，1开启；参数2为短信提醒的手机号码，多个号码时，用','隔开，按总公司设置
		String cs103158 = Function.acsb("103158", shbh, "YDHM", "0");
		String dxhm = Function.acsb("103158", shbh, "YW", "0");
		// 本参数用于自动取消座位失败预警，选择邮件提醒的方式。参数1，默认为0不启用，1开启；参数2为邮件提醒的邮件地址，按总公司设置
		String cs103159 = Function.acsb("103159", shbh, "YDHM", "0");
		String yjhm = Function.acsb("103159", shbh, "YW", "0");
		if ("1".equals(cs103156)) {
			if ("1".equals(cs103158)) {
				tx.smsCall(dxhm.split(","), smsMsg, shbh, sh_yhb,jptpd.getTpdh(), jptpd.getXsPnrNo());
			}
			if ("1".equals(cs103159)) {
				String title = "PNR为"+jptpd.getCgPnrNo()+"的退票单自动取消座位提醒";
				tx.sendForEmail(yjhm, rtxAndEmailMsg, title, sh_yhb);
			}
		}*/
	}
	
	
	public void updateKhddPnrzt(boolean isAuto, JpTpd jptpd,Shyhb sh_yhb){
		TpXePassengerTx.updateKhddPnrzt(isAuto, jptpd, sh_yhb,jpKhddServiceImpl);
	}
	
	/**
	 * 成功XEPNR后修改原订单PNR状态
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public static void updateKhddPnrzt(boolean isAuto, JpTpd jptpd, Shyhb sh_yhb,JpKhddServiceImpl jpKhddServiceImpl) {
		JpKhdd jpkhdd=new JpKhdd();
		jpkhdd.setDdbh(jptpd.getDdbh());
		jpkhdd.setShbh(sh_yhb.getShbh());
		jpkhdd.setXgyh(sh_yhb.getBh());
		jpkhdd.setXsPnrZt("XX");
		jpkhdd.setCgPnrZt("XX");
		jpkhdd.setXgly((isAuto ? "自动" : "手动")+"取消座位");
		try {
			jpKhddServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(jpkhdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新退票单主表
	 * 
	 * @throws Exception
	 */
	public static void updateJpTpd(boolean isAuto, String qxzwzt, JpTpd jptpd, String msg, String yhbh, JpTpdServiceImpl jpTpdServiceImpl) throws Exception {
		JpTpd update_jptpd=new JpTpd();
		update_jptpd.setShbh(jptpd.getShbh());
		update_jptpd.setTpdh(jptpd.getTpdh());
		update_jptpd.setXsZdqxzt(qxzwzt);
		update_jptpd.setCgZdqxzt(qxzwzt);
		update_jptpd.setXgyh(yhbh);
		update_jptpd.setXgly((isAuto ? "自动" : "手动")+"取消座位");
		if (JpTpd.CG_ZDQXZT_QXSB.equals(qxzwzt)) {
			jptpd.setXsZdqxsbyy(VeStr.substrByte(msg, 4000));
			jptpd.setCgZdqxsbyy(VeStr.substrByte(msg, 4000));
		}
		jpTpdServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(update_jptpd);
		
	}
	
	/**
	 * 更新JP_XEPNR表
	 */
	public static void updateJpXepnr(String qxzwzt,String msg,String yhbh,String pnrlr,String officeid, JpTpd jptpd,JpXepnrServiceImpl jpXepnrServiceImpl) throws Exception{
		JpXepnr xepnr=new JpXepnr();
		xepnr.setShbh(jptpd.getShbh());
		xepnr.setDdbh(jptpd.getDdbh());
		xepnr.setOfficeid(officeid);
		xepnr.setPnrNo(jptpd.getCgPnrNo());
		xepnr.setCjr(jptpd.getCjr());
		xepnr.setHd(jptpd.getHc());
		xepnr.setSfyzzj("0");
		xepnr.setSfyzhd("0");
		xepnr.setXelx("XECJR");
		xepnr.setXesy("申请退票");
		xepnr.setXezt(qxzwzt);
		if (JpTpd.CG_ZDQXZT_QXSB.equals(qxzwzt)) {
			xepnr.setXesbyy(VeStr.substrByte(msg, 200));
		}
		xepnr.setXeyh(yhbh);
		xepnr.setXesj(VeDate.getNow());
		xepnr.setCzly("退票");
		xepnr.setCjyh(yhbh);
		xepnr.setCjsj(VeDate.getNow());
		xepnr.setPnrLr(pnrlr);
		jpXepnrServiceImpl.insert(xepnr);
	}
}
