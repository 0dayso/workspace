package cn.vetech.vedsb.jp.service.jptpgl.zdtp;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cpsz.JpZdtfpGzsz;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpHd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpHdServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.vedsb.platpolicy.cps.request.CpsAccount;
import cn.vetech.vedsb.platpolicy.cps.request.ticket.TicketRefundCpsRequest;
import cn.vetech.vedsb.platpolicy.cps.response.ticket.TicketRefundResponse;
import cn.vetech.vedsb.platpolicy.cps.service.CpsService;
import cn.vetech.vedsb.platpolicy.cpslink.service.CpslinkService;
import cn.vetech.vedsb.utils.PlatCode;

/**
 * OP票处理
 * @author 王学兴
 *
 */
@Component
public class OpTicket extends PzSuper{

	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	
	@Autowired
	private JpHdServiceImpl jpHdServiceImpl;
	
	@Autowired
	JpTpdMxServiceImpl jpTpdMxServiceImpl;
	

	/**
	 * 对OP票申请退票
	 * @author wangshengliang
	 *
	 */
	@Override
	public String applyTicketRefund(boolean isAuto,boolean isHbyw,boolean isYctj,Shyhb sh_yhb, JpTpd jptpd,JpZdtfpGzsz gzsz) throws Exception{
		//淘宝
		if(PlatCode.TB.equals(jptpd.getWdpt())){
			TaobaoCg taobaocg=SpringContextUtil.getBean(TaobaoCg.class);
			taobaocg.applyTicketRefund(isAuto,isHbyw,sh_yhb, jptpd,gzsz);
		}else if(PlatCode.CPS.equals(jptpd.getWdpt())){// CPS和酷讯
			tjCps(sh_yhb, jptpd);
		}else{//其他平台
			CpslinkService cpslink=SpringContextUtil.getBean(CpslinkService.class);
			cpslink.ticketRefundRequest(jptpd.getTpdh(), sh_yhb, jptpd.getWdpt(), jptpd.getCgTpdh());
		}
		return null;
	}
	
	
	private void tjCps(Shyhb sh_yhb, JpTpd jptpd){
		 	TicketRefundCpsRequest  request=new TicketRefundCpsRequest();
		 	String shbh=sh_yhb.getShbh();
		 	String tpdh=jptpd.getTpdh();
			/**
			 * 1.取正常订单信息
			 */
			JpKhdd jpKhdd=new JpKhdd();
			jpKhdd.setDdbh(jptpd.getDdbh());
			jpKhdd.setShbh(shbh);
			jpKhdd=jpKhddServiceImpl.getKhddByDdbh(jpKhdd);
			request.setOrderNo(jpKhdd.getCgDdbh());
			
			/**
			 * 2.取退票航段信息
			 */
			List<JpHd>  hdList=jpHdServiceImpl.getJpHdByTpdh(tpdh, shbh);
			if(CollectionUtils.isEmpty(hdList)){
				//logger.error("退废单航程信息为空");
				return ;
			}
			StringBuffer travelRange = new StringBuffer();
			for(JpHd jphd:hdList){
				travelRange.append(jphd.getCfcity()).append(jphd.getDdcity()).append("|");
			}
			request.setTravelRange(travelRange.toString());
			
			
			/**
			 * 3.取乘机人信息
			 */
			List<Map<String, Object>> mxList = jpTpdMxServiceImpl.getJpTpdMxList(tpdh, shbh);
			if (CollectionUtils.isEmpty(mxList)) {
				//logger.error("退票单明细信息为空");
				return ;
			}
			
			String passenger="";
			String ticketNo="";
			for(Map<String,Object> m:mxList){
				passenger+=VeStr.getValue(m, "CJR")+"|";
				ticketNo+=VeStr.getValue(m, "TKNO")+"|";
			}
			request.setPassenger(passenger);
			request.setTicketNo(ticketNo);
			
			String IsCancelSeat="2";//1.委托平台取消;2.保留座位;已取消
			if (!"1".equals(jptpd.getSfqxzw())) {//0 不保留座位; 1保留座位
				if ("1".equals(jptpd.getCgZdqxzt())) {
					// 已取消
					IsCancelSeat="3";
				} else {
					// 委托平台取消
					IsCancelSeat="1";
				}
			}
			
			request.setIsCancelSeat(IsCancelSeat);
			String otherReason = "";
			request.setRefundType(jptpd.getCgSfzytp());
			request.setRefundReason(jptpd.getCgTply());//原因编号
			request.setOtherReason(otherReason);//原因内容
			
            //获取上传附件路径和地址
			String sqfj = jptpd.getCgTpzm();
			if (StringUtils.isNotBlank(sqfj)) {
				/*
				Ve_csb ve_csb10029 = ve_csb_service.getCsbZgsBh(this.getVe_yhb().getZgscompid(), "10029");
				if (ve_csb10029 != null && StringUtils.isNotBlank(ve_csb10029.getYdhm()) && StringUtils.isNotBlank(ve_csb10029.getYw())) {
					String ip = ve_csb10029.getYdhm();
					String port = ve_csb10029.getYw();
					sqfj = "http://" + ip + ":" + port + sqfj;
					p2r.setCertificateUrl(sqfj);
				}*/
			}
			//request.setCertificateUrl(p2r.getCertificateUrl());
			
			/*
			String sqfj = ticket_return.getSqfj();
			if (StringUtils.isNotBlank(sqfj)) {
				Ve_csb ve_csb10029 = ve_csb_service.getCsbZgsBh(this.getVe_yhb().getZgscompid(), "10029");
				if (ve_csb10029 != null && StringUtils.isNotBlank(ve_csb10029.getYdhm()) && StringUtils.isNotBlank(ve_csb10029.getYw())) {
					String ip = ve_csb10029.getYdhm();
					String port = ve_csb10029.getYw();
					sqfj = "http://" + ip + ":" + port + sqfj;
					p2r.setCertificateUrl(sqfj);
				}
			}
			request.setCertificateUrl(p2r.getCertificateUrl());
			*/
			
			TicketRefundResponse response=new TicketRefundResponse();
			CpsAccount account = new CpsAccount();
			account.setBusinessNo(jptpd.getShbh());
			CpsService  cpsService=new CpsService();
			try {
				response=cpsService.RefundApply(request, account);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}


}
