package cn.vetech.vedsb.platpolicy.cps.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.PkgTicketTfServiceImpl;
import cn.vetech.vedsb.platpolicy.NoticeStatus;
import cn.vetech.vedsb.platpolicy.cps.request.ticket.FPtCgProcessBean;
import cn.vetech.vedsb.platpolicy.cps.request.ticket.TicketRefundNoticeRequest;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.PlatCode;
@Service
public class CpsRefundNoticeService {
	@Autowired
	private CpsService cpsService;
	@Autowired
	private JpTpdServiceImpl jpTpdServiceImpl;
	@Autowired
	private JpPtLogServiceImpl jpPtLogServiceImpl;
	@Autowired
	private PkgTicketTfServiceImpl pkgTicketTfServiceImpl;
	public NoticeStatus execute(JpCgdd jpCgdd, Map<String, String[]> params){
		NoticeStatus status = new NoticeStatus();
		JpPtLog ptLog=new JpPtLog();
		ptLog.add("退废票办理通知"+PlatCode.CPS);
		ptLog.setDdlx(DictEnum.PTLOGDDLX.TF.getValue());
		ptLog.setBy2(DictEnum.PTLOGCGGY.CG.getValue());
		ptLog.setBy1("1001901");//国内机票
		ptLog.setYwlx(DictEnum.PTLOGYWLX.TFTZ.getValue());
		ptLog.setCzsm("退废单办理通知");
		try {
			TicketRefundNoticeRequest request = this.cpsService.refundNotify(params);
			String refundNo = request.getRefundNo();
			String orderNo = request.getOrderNo();
			String refundStatus = request.getRefundStatus();
			String tkno = request.getTicketNo();
			
			ptLog.add("平台订单号:"+orderNo);
			ptLog.add("平台退废单号:"+refundNo);
			JpTpd jptpd = this.jpTpdServiceImpl.getTpdByCgtpdh(refundNo,PlatCode.CPS);
			if (jptpd == null) {
				throw new Exception("未获得对应的系统退废单");
			}
			String id = jptpd.getTpdh();
			String ddbh = jptpd.getDdbh();
			ptLog.setTfid(id);
			ptLog.setDdbh(ddbh);
			String cgtpzt = jptpd.getCgTpzt();
			if(!"1".equals(cgtpzt)){
				throw new Exception("接收通知处理失败，系统退废单状态与采购办理状态不为提交中，当前退单状态【" + DictEnum.CGTFPZT.dataMap.get(cgtpzt).getValue() + "】");
			}
			String userid = jptpd.getCgTjr();
			ptLog.setYhbh(userid);
			
			FPtCgProcessBean bean = new FPtCgProcessBean();
			bean.setTfid(id);
			bean.setUserid(userid);
			bean.setType("2");
			
			if ("230".equals(refundStatus)) {
				// 供应拒单
				bean.setStatus("-1");
			} else if ("241".equals(refundStatus)) {
				// 退款
				bean.setStatus("0");
			} else {
				throw new Exception("不支持的通知状态【" + refundStatus + "】");
			}
			bean.setError(request.getReason());
			bean.setPt_stje(request.getActualRefund());
			if (StringUtils.isBlank(tkno)) {
				bean.setRtnType("0");
			} else {
				bean.setRtnType("1");
				bean.setTkno(tkno);
				bean.setCj_xsj(request.getScny());
				bean.setCj_jsf(request.getYq());
				bean.setCj_tax(request.getTax());
				bean.setJp_pjhk(request.getRefundFee());
				bean.setCj_gqfy(request.getActualChangeFeeDetail());
				bean.setPt_gqdh(request.getChangeNoDetail());
				bean.setHj_cj_gqfy(request.getActualChangeFee());
				bean.setPayment(request.getPayment());
				bean.setPaymentCode(request.getPaymentCode());
			}
			//调过程
			String xml = XmlUtils.toXml(bean);
			Map<String, Object> paramXml = new HashMap<String, Object>();
			paramXml.put("pxml", xml);
			this.pkgTicketTfServiceImpl.ptCgProcess(paramXml);
			int result = (Integer)paramXml.get("result");
			if(result ==-1){
				status.setStatus(-1);
				String error = StringUtils.trimToEmpty((String)paramXml.get("perror"));
				status.setMsg(error);
				ptLog.add("平台退废票通知到后台返回:"+result + "===" + error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ptLog.add(e.getMessage());
			status.setStatus(-1);
			status.setMsg(e.getMessage());
		} finally{
			try {
				jpPtLogServiceImpl.insertLog(ptLog);//写日志
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
	}
}
