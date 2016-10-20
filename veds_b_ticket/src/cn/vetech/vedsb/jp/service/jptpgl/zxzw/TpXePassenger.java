package cn.vetech.vedsb.jp.service.jptpgl.zxzw;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.xepassenger.XePassenger;
import org.vetech.core.business.pid.api.xepassenger.XePassengerParam;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.service.cpsz.JpZdtpJkServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdCzrzServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.zdtp.PzSuper;
import cn.vetech.vedsb.jp.service.jpxepnr.JpXepnrServiceImpl;
import cn.vetech.vedsb.utils.MapToXML;

/**
 * 自动取消座位
 * @author wangshengliang
 * 2016-06-21
 */
@Service
public class TpXePassenger {

	@Autowired
	private JpTpdServiceImpl jpTpdServiceImpl;

	@Autowired
	private JpTpdCzrzServiceImpl jpTpdCzrzServiceImpl;
	
	@Autowired
	private JpXepnrServiceImpl jpXepnrServiceImpl;
	
	@Autowired
	JpZdtpJkServiceImpl jpZdtpJkServiceImpl;
	
	public static void log(String info) {
		System.out.println(VeDate.getStringDate() + "==>退废票自动取消座位  " + info);
	}
	/**
	 * 执行自动取消座位
	 * 
	 * @throws Exception
	 */
	public void excute(Shyhb sh_yhb, JpKhdd khdd, JpTpd jptpd,JpPz jppz, String hd,String cjr, int ignoreCjrZjhm, boolean isAuto) throws Exception {
		
		TpXePassengerTx TpXePassengerTx =SpringContextUtil.getBean(TpXePassengerTx.class);
		String pnrno = jptpd.getCgPnrNo();
		XePassengerParam param = new XePassengerParam();
		param.setPnrno(pnrno);
		param.setUserid(sh_yhb.getPidyh());
		param.setUrl("http://"+jppz.getPzIp()+":"+jppz.getPzPort());
		param.setHd(hd);
		param.setCjr(cjr);
		param.setIgnoreCjrZjhm(ignoreCjrZjhm);
		String data = "";
		boolean flag = false;
		String autoOrNot = isAuto ? "自动" : "手动";
		String rtxAndEmailMsg = "PNR为" + pnrno + "的退票" + autoOrNot+ "取消座位失败,";
		try {
			data = IbeService.xePassenger(param);
		} catch (Exception e) {
			e.printStackTrace();
			rtxAndEmailMsg = "调用xe乘机人接口异常，PNR为" + pnrno + "的退票" + autoOrNot+ "取消座位失败。具体原因请查看异动日志。";
			if (isAuto) {
				rtxAndEmailMsg = rtxAndEmailMsg + "请及时处理需要取消座位的退废单。";
			} 
		}
		
		flag = StringUtils.isNotBlank(data);
		String status = "";
		String  description="";
		if (flag) {
			System.out.println("XECJR=>"+data);
			Map m=null;
			try {
				m= MapToXML.xmlBody2map(data, "RESULT");
				
				status = VeStr.getValue(m, "STATUS");
				description = VeStr.getValue(m, "DESCRIPTION");
				rtxAndEmailMsg += description;
				
				boolean isAlreadyXe=description.contains("THIS PNR WAS ENTIRELY CANCELLED");
				if(!isAlreadyXe){
					isAlreadyXe=description.contains("PNR CANCELLED "+pnrno.toUpperCase());
				}
				
				//-3已取消编码
				if (XePassenger.XECJR_SUCCESS.equals(status) || XePassenger.XEPNR_SUCCESS.equals(status)|| isAlreadyXe) {
					if (XePassenger.XEPNR_SUCCESS.equals(status) || isAlreadyXe) {
						// XEPNR成功后修改订单PNR状态
						TpXePassengerTx.updateKhddPnrzt(isAuto, jptpd,sh_yhb);
					}
				} else {
					flag = false;
					rtxAndEmailMsg = "退票" + autoOrNot + "取消座位失败！具体原因请查看异动日志。";
					if (isAuto) {
						rtxAndEmailMsg = "PNR:" + pnrno + "，退票单号："+ jptpd.getTpdh() + "，" + jptpd.getCjr() + "，"+ khdd.getHc() + "，" + khdd.getCfrqStr()+ "起飞，退票自动取消座位失败，请及时处理。";
					}
				}
			} catch (DocumentException e) {
				flag = false;
				rtxAndEmailMsg = "退票" + autoOrNot + "取消座位失败<br>"+data;
			}
			
		}

		String tpdh = jptpd.getTpdh();
		String zdqxzt_status = flag ? JpTpd.CG_ZDQXZT_QXCG : JpTpd.CG_ZDQXZT_QXSB;
		rtxAndEmailMsg = flag ? description : rtxAndEmailMsg;
		
		// XE乘机人后修改退票单主表
		TpXePassengerTx.updateJpTpd(isAuto,zdqxzt_status,jptpd, rtxAndEmailMsg, sh_yhb.getBh(), jpTpdServiceImpl);
		TpXePassengerTx.updateJpXepnr(zdqxzt_status, rtxAndEmailMsg, sh_yhb.getBh(), description, jppz.getOfficeid(), jptpd, jpXepnrServiceImpl);
		TpXeZw.writeXezwOperationLog(isAuto,jpTpdCzrzServiceImpl, tpdh, rtxAndEmailMsg, sh_yhb);
		
		//取消座位失败更新监控表退票状态
		if(!flag){
			String zdqxsbyy = rtxAndEmailMsg;
			if (StringUtils.isNotBlank(zdqxsbyy)) {
				int index=zdqxsbyy.indexOf("!");
				index = index < 0 ? 0 : index;
				zdqxsbyy = zdqxsbyy.substring(0, index);
				zdqxsbyy = "<font color='red'>取消座位失败!</font><br><xmp>" + zdqxsbyy+"</xmp>";
			} else {
				zdqxsbyy = "<font color='red'>取消座位失败!</font>";
			}
			PzSuper.zdtpJkLog(isAuto,false,jptpd, zdqxsbyy, jpZdtpJkServiceImpl);
		}
		
		if (!flag) {
			log(rtxAndEmailMsg);
			if (isAuto) {// 手动取消不需要发送信息
				TpXePassengerTx.msgtx(rtxAndEmailMsg, rtxAndEmailMsg,sh_yhb,jptpd);
			} else {
				throw new Exception(rtxAndEmailMsg);
			}
		}
	}
	public static void main(String[] args) {
		String data="'妺'是非有效的GB2312,请使用拼音代替,原始输入(VEGeneral:<INPUT><COMMAND>VEXEPASSENGER</COMMAND><PARA><USER>16081515475158</USER><PNRNO>JNTBC0</PNRNO><CJR>hemohan何妺晗,</CJR><HD>MU2197,LHW,CSX,2016-09-12,R</HD><IGNORECJRZJHM>1</IGNORECJRZJHM><IGNOREHD>0</IGNOREHD></PARA></INPUT> )!";
		try {
			Map m= MapToXML.xmlBody2map(data, "RESULT");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
