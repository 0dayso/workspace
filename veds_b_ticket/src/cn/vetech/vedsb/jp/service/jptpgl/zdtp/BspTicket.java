package cn.vetech.vedsb.jp.service.jptpgl.zdtp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.trfd.TrfdResult;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cpsz.JpZdtfpGzsz;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.service.jptpgl.zxzw.TpXePassengerTx;
import cn.vetech.vedsb.jp.service.jptpgl.zxzw.TpXeZw;
import cn.vetech.vedsb.utils.LogUtil;

/**
 * 对BSP及BPET票处理
 * @author wangshengliang
 */
//Spring注入的是无参的构造函数
@Component
public class BspTicket extends PzSuper{
	/**
	 * 对BSP及BPET票申请退废
	 */
	@Override
	public String applyTicketRefund(boolean isAuto,boolean isHbyw,boolean isYctj,Shyhb shyhb, JpTpd jptpd,JpZdtfpGzsz gzsz) throws Exception{
		String tpdh=jptpd.getTpdh();
		String shbh=jptpd.getShbh();
		boolean iszytp = "1".equals(jptpd.getCgSfzytp());
		// 自愿退票需要执行退票指令退票
		PzSuper.zdtpJkLog(isAuto,jptpd, jptpd.getCgly()+"开始"+(iszytp ? "自愿退票":"非自愿退票"), jpZdtpJkServiceImpl);
		if(iszytp || !isAuto){
			// 非连续客票&&国内机票
			if ("0".equals(jptpd.getLxkpzt()) && "1".equals(jptpd.getGngj())) {
				//开启自动退票
				//XEPNR
				JpKhdd jpKhdd=new JpKhdd();
				jpKhdd.setShbh(shbh);
				jpKhdd.setDdbh(jptpd.getDdbh());
			    jpKhdd=jpKhddServiceImpl.getKhddByDdbh(jpKhdd);
			    JpPz jppz=jpTpdMxServiceImpl.getJpPzByShbh(shbh);
			    
				//获取退票明细信息
				List<JpTpdMx> mxList=jpTpdMxServiceImpl.getJpTpdMxByTpdh(tpdh, shbh);
				
				if (CollectionUtils.isEmpty(mxList)) {
					PzSuper.zdtpJkLog(isAuto,jptpd, "未获取到退票单明细信息,全自动退票结束", jpZdtpJkServiceImpl);
					return null;
				}
				
				//检查客票状态
				for (JpTpdMx mx : mxList) {
				     Map<String, Object> map = jpTpdServiceImpl.checkKpzt(isAuto, jptpd, mx, shyhb, jppz);
				     String errMsg = VeStr.getValue(map, "ERROR");
				     String status = VeStr.getValue(map, "STATUS");
				     boolean isTpSuccess = !"-1".equals(status);
				     if(StringUtils.isNotBlank(errMsg)){
				    	 	PzSuper.zdtpJkLog(isAuto,jptpd, errMsg, jpZdtpJkServiceImpl);
							PzSuper.zdtpJkLog(isAuto,isTpSuccess,jptpd, "全自动退票失败!", jpZdtpJkServiceImpl);
							return null;
					 }
				}
				
				String cg_pnrno=jptpd.getCgPnrNo();
				String pnrlr="";
				//如果Q信息有航班延误，需要RT编码来再次确认是否有航班延误，如还有延误信息修改采购是否自愿退票
				if(isHbyw){
					//RT获取PNR内容
					WebEtermService etermService = new WebEtermService("http://"+jppz.getPzIp()+":"+jppz.getPzPort());
					pnrlr	= etermService.RTPNR(cg_pnrno, shyhb.getPidyh(), "");
		 		    if(StringUtils.isBlank(pnrlr)){
		 		    	PzSuper.zdtpJkLog(isAuto,jptpd, "RT["+cg_pnrno+"]返回结果为空!", jpZdtpJkServiceImpl);
		 		    	return null;
		 		    }
				}

				//取消座位
				TpXeZw tpxeZw =SpringContextUtil.getBean(TpXeZw.class);
				tpxeZw.xezw(isAuto, shyhb, jptpd,jpKhdd,jppz);
				
				//重新获取自动取消状态，判断XEPNR是否执行成功
				jptpd=jpTpdServiceImpl.getJpTpdByTpdh(tpdh, shbh);
				if (!JpTpd.CG_ZDQXZT_QXCG.equals(jptpd.getCgZdqxzt())) {
					return null;
				}
				
				//执行退票
				PzSuper.zdtpJkLog(isAuto,jptpd, "取消座位成功!", jpZdtpJkServiceImpl);	
				List<TrfdResult> trfdList=new ArrayList<TrfdResult>();
				JpTpd cg = null;String errMsg="";
				for (JpTpdMx mx : mxList) {
					/**
					 *当Q信息匹配到航变信息时, 检查PNR中是否也包含延误信息
					 */
					boolean pnr_isHbyw = false;
					if(isHbyw){
						pnr_isHbyw = isCheckPnrHbyw(isAuto, jptpd, mx, cg, shyhb.getBh(), pnrlr);
						if(!pnr_isHbyw){
							PzSuper.zdtpJkLog(isAuto,false,jptpd, "检查PNR未发现航变信息,停止退票", jpZdtpJkServiceImpl);
							return null;
						}
					}
				    /**
				     * 调用退票指令进行退票
				     */
					try {
						Map<String, Object> map = jpTpdServiceImpl.trfd(isAuto,pnr_isHbyw,jptpd, mx, shyhb,jppz);
						TrfdResult trfdresult = (TrfdResult) map.get("TrfdResult");
						if (trfdresult != null) {
							trfdList.add(trfdresult);
						}
						errMsg = (String) map.get("ERROR");
						if (StringUtils.isNotBlank(errMsg)) {
							PzSuper.zdtpJkLog(isAuto,jptpd, errMsg, jpZdtpJkServiceImpl);
							break;
						}else{
							PzSuper.zdtpJkLog(isAuto,jptpd,"票号:["+mx.getTkno()+"]退票成功!", jpZdtpJkServiceImpl);
						}
					} catch (Exception e) {
						e.printStackTrace();
						errMsg = e.getMessage();
					}
				}
				
				boolean isTpSuccess=StringUtils.isBlank(errMsg);
				String msg = isTpSuccess ? "全自动退票成功!" : "全自动退票失败!";
				if(!isTpSuccess && isAuto){
						TpXePassengerTx tpautotx =SpringContextUtil.getBean(TpXePassengerTx.class);// 提醒、
						tpautotx.msgtx(errMsg, errMsg,shyhb,jptpd);// 消息提醒
				}
				
				PzSuper.zdtpJkLog(isAuto,isTpSuccess,jptpd, msg, jpZdtpJkServiceImpl);
				
				/**
				 * 与采购办理
				 */
				cgBl(isAuto,jptpd,shyhb.getBh(),trfdList);

			}
		}else{
			PzSuper.zdtpJkLog(isAuto,false, jptpd, "自动退票不支持采购非自愿退票", jpZdtpJkServiceImpl);
		}
		return null;
	}
	
	
	
	/**
	 * 当Q信息有航班延误信息时，检查PNR内容是否也包含延误信息
	 * @return
	 */
	public boolean  isCheckPnrHbyw(boolean isAuto,JpTpd jptpd,JpTpdMx mx,JpTpd cg,String bh,String pnrlr){
		
		// 匹配到航变信息 再次确认PNR中是否存在航变信息
		if (StringUtils.isNotBlank(pnrlr)) {
			String cg_pnrno = jptpd.getCgPnrNo();
			String cfrq = "";// 格式:TU27SEP16
			String cfrq1 = "";// 格式TU04OCT
			SimpleDateFormat sdf = new SimpleDateFormat("EddMMMyy", Locale.US);
			cfrq = sdf.format(jptpd.getCfrq()).toUpperCase();
			cfrq = cfrq.substring(0, 2) + cfrq.substring(3);

			SimpleDateFormat sdf1 = new SimpleDateFormat("EddMMM", Locale.US);
			cfrq1 = sdf1.format(jptpd.getCfrq()).toUpperCase();
			cfrq1 = cfrq1.substring(0, 2) + cfrq1.substring(3) + "  ";
			LogUtil.getTrfd().error("PNR[" + cg_pnrno + "]内容:" + pnrlr);
			String hbh_cw_cfrq_hc = String.format("%s %s   %s%s ",jptpd.getCgHbh(), mx.getCgCw(), cfrq, jptpd.getHc());  //JD5376 V TU27SEP16XMNPEK UN1
			String hbh_cw_cfrq1_hc = String.format("%s %s   %s%s ",jptpd.getCgHbh(), mx.getCgCw(), cfrq1, jptpd.getHc());//JD5376 V TU27SEP  XMNPEK UN1

			LogUtil.getTrfd().error("PNR[" + cg_pnrno + "]检查内容:[" + hbh_cw_cfrq_hc+"]["+hbh_cw_cfrq1_hc+"]");
			PzSuper.zdtpJkLog(isAuto, jptpd,"PNR[" + cg_pnrno + "]检查内容:[" + hbh_cw_cfrq_hc+"]["+hbh_cw_cfrq1_hc+"]",jpZdtpJkServiceImpl);
			
			String [] yw_pnrzt_arr =new String[]{"UN","HX","DL"};//PNR状态是UN,HX,DL表示航班有延误信息
			for(String yw_pnrzt:yw_pnrzt_arr){
				if(pnrlr.contains(hbh_cw_cfrq_hc + yw_pnrzt)|| pnrlr.contains(hbh_cw_cfrq1_hc + yw_pnrzt)){
					if (cg == null) {
						cg = new JpTpd();
						cg.setTpdh(jptpd.getTpdh());
						cg.setShbh(jptpd.getShbh());
						cg.setCgSfzytp("0");
						cg.setXgly("自动退票");
						cg.setXgyh(bh);
						cg.setXgsj(VeDate.getNow());
						jpTpdServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(cg);
					}
					PzSuper.zdtpJkLog(isAuto, jptpd,"检查PNR[" + cg_pnrno + "]状态为:<font color='RED'>"+yw_pnrzt+"</font>,存在航班延误",jpZdtpJkServiceImpl);
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	
	/**
	 * 与采购办理
	 * @param isAuto
	 * @param jptpd
	 * @param bh
	 * @param trfdList
	 */
	private void cgBl(boolean isAuto,JpTpd jptpd,String bh,List<TrfdResult> trfdList){
		
		if(CollectionUtils.isEmpty(trfdList)){
			PzSuper.zdtpJkLog(isAuto,jptpd, "没有退票价格信息,全自动退票结束", jpZdtpJkServiceImpl);
			return ;
		}
		
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("TPDH", jptpd.getTpdh());
		param.put("USERID",bh);
		param.put("TJZT", "");
		param.put("SBYY", "");
		param.put("CZLX", "2");
		List<Map<String, Object>> tklist = new ArrayList<Map<String, Object>>();
		for (TrfdResult tr : trfdList) {
			Map<String, Object> tkMap = new HashMap<String, Object>();
			String tkno = tr.getTktNumber().split(",")[0];
			tkMap.put("TKNO", tr.getAirlineCode() + "-" + tkno);
			tkMap.put("TK_PJ", tr.getGrossRefund());
			String[] taxs = tr.getTax().split("\\|");
			for (String tax : taxs) {
				String[] taxArr = tax.split(",");
				if ("CN".equals(taxArr[0])) {
					tkMap.put("TK_JSF", taxArr[1]);
				}
				if ("YQ".equals(taxArr[0])) {
					tkMap.put("TK_TAX", taxArr[1]);
				}
			}
			tkMap.put("TK_SXF", tr.getDeduction());
			tkMap.put("TK_DLF", tr.getCommissionAmount());
			tkMap.put("TK_JE", tr.getNetRefund());
			tkMap.put("TK_HSTPF", tr.getAirlineRefund());
			tklist.add(tkMap);
		}
		param.put("TK", tklist);
		try {
			procedureServiceImpl.f_cgtp(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PzSuper.zdtpJkLog(isAuto, jptpd,"采购办理f_cgtp入参=><div style=\"word-wrap:break-word; word-break:break-all;\">"+ VeStr.getValue(param, "p_xml") + "</div>",jpZdtpJkServiceImpl);
		PzSuper.zdtpJkLog(isAuto, jptpd, "全自动退票结束", jpZdtpJkServiceImpl);
	}
}
