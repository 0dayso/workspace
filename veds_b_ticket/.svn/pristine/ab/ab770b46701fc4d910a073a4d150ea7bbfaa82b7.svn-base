package cn.vetech.web.vedsb.jptpgl;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.api.rtpnr.PnrRtParam;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.service.cpsz.JpZdtpJkServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.zdtp.PzSuper;
import cn.vetech.vedsb.jp.service.jptpgl.zxzw.TpXePassengerTx;
import cn.vetech.vedsb.jp.service.jptpgl.zxzw.TpXeZw;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpTpdCgController extends MBaseControl<JpTpd, JpTpdServiceImpl>{
	
	
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	
	@Autowired
	private JpTpdMxServiceImpl jpTpdMxServiceImpl;
	
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	
	@Autowired
	private JpZdtpJkServiceImpl jpZdtpJkServiceImpl;
	
	@Override
	public void updateInitEntity(JpTpd t) {
	}

	@Override
	public void insertInitEntity(JpTpd t) {
	}

	
	@RequestMapping(value = "tpdCgTj_{tpdh}")
	@ResponseBody
	public Map<String,String> cgTj(@PathVariable("tpdh") String tpdh,JpTpdMx mx,String ztpt,ModelMap model){
		Map<String,String> retMap=new HashMap<String, String>();
		retMap.put("status", "fail");
		Shyhb shyhb = SessionUtils.getShshbSession();
		String shbh = shyhb.getShbh();
		JpTpd jptpd=this.baseService.getJpTpdByTpdh(tpdh,shbh);
		if (null == jptpd) {
			retMap.put("message", "采购提交失败,取退票单信息为空");
			return retMap;
		}
		List<JpTpdMx>  mxList=jpTpdMxServiceImpl.getJpTpdMxByTpdh(tpdh, shbh);
		if(CollectionUtils.isEmpty(mxList)){
			retMap.put("message", "采购提交失败,取退票单明细信息为空");
			return retMap;
		}
		long cgZdj=0;
		BigDecimal cgPj=new BigDecimal(0);
		long cgJsf=0;
		long cgTax=0;
		BigDecimal cgTpf=new BigDecimal(0);
		int mxLength=mxList.size();
		for(JpTpdMx jtm:mxList){
			jtm.setShbh(shbh);
			cgZdj=mx.getCgZdj();
			cgPj=mx.getCgPj();
			cgJsf=mx.getCgJsf();
			cgTax=mx.getCgTax();
			cgTpf=mx.getCgTpf();
			
			jtm.setCgZdj(cgZdj);
			jtm.setCgPj(cgPj);
			jtm.setCgJsf(cgJsf);
			jtm.setCgTax(cgTax);
			jtm.setCgTpfl(new BigDecimal(mx.getCgTpfl().doubleValue()/100.00));
			jtm.setCgTpf(cgTpf);
			
			try {
				jpTpdMxServiceImpl.update(jtm);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("采购提交失败", e);
				retMap.put("message", "修改退票单明细信息异常:"+e.getMessage());
				return retMap;
			}
		}
		
		jptpd.setXgyh(shyhb.getBh());
		jptpd.setXgly("采购提交");
		jptpd.setXgsj(VeDate.getNow());
		jptpd.setCgTpzt(JpTpd.CG_TPZT_YTJ);
		jptpd.setCgTjr(shyhb.getBh());
		jptpd.setCgTjsj(VeDate.getNow());
		jptpd.setCgZdj(cgZdj*mxLength);
		jptpd.setCgPj(new BigDecimal(cgPj.doubleValue()*mxLength));
		jptpd.setCgJsf(cgJsf*mxLength);
		jptpd.setCgTax(cgTax*mxLength);
		jptpd.setCgTpfl(new BigDecimal(mx.getCgTpfl().doubleValue()/100.00));
		jptpd.setCgTpf(new BigDecimal(cgTpf.doubleValue()*mxLength));
		
		try {
			this.baseService.getMyBatisDao().updateByPrimaryKeySelective(jptpd);
		} catch (Exception e) {
			logger.error("采购提交失败", e);
			retMap.put("message", "修改退票单信息异常:"+e.getMessage());
			return retMap;
		}
		
		//直通平台 并且是CPS订单
		if ("1".equals(ztpt)) {
			PzSuper.ticketRefundHandle(false, jptpd, shyhb, jpZdtpJkServiceImpl);
		}
		retMap.put("status", "ok");
		return retMap;
	}
	
	@RequestMapping(value = "batchCgTp")
	public @ResponseBody String batchCgTp(String tpdhs,ModelMap model) throws Exception {
		String [] tpdh_arr=tpdhs.split(",");
		Shyhb shyhb = SessionUtils.getShshbSession();
		String shbh = shyhb.getShbh();
		String msg="";
		for (String tpdh : tpdh_arr) {
			JpTpd jptpd=this.baseService.getJpTpdByTpdh(tpdh, shbh);
			if (null != jptpd) {
			    String xsTpzt=jptpd.getXsTpzt();
			    String cgTpzt=jptpd.getCgTpzt();
			    if(!JpTpd.XS_TPZT_YQX.equals(xsTpzt) && !JpTpd.CG_TPZT_YBL.equals(cgTpzt) && !JpTpd.CG_TPZT_YTJ.equals(cgTpzt)){
				    try {
					    PzSuper.ticketRefundHandle(false, jptpd, shyhb, jpZdtpJkServiceImpl);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("手动退票失败", e);
						msg+="退票单号["+tpdh+"]手动退票失败<br>";
					}
			    }
			}
		}
		return msg;
	}
	
	@RequestMapping(value = "batchCgWc")
	public @ResponseBody String batchCgWc(String tpdhs,ModelMap model) throws Exception {
		String [] tpdh_arr=tpdhs.split(",");
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		String msg="";
		for (String tpdh : tpdh_arr) {
			JpTpd jptpd=this.baseService.getJpTpdByTpdh(tpdh, shbh);
			if (null != jptpd) {
			    String xsTpzt=jptpd.getXsTpzt();
			    String cgTpzt=jptpd.getCgTpzt();
			    if(JpTpd.XS_TPZT_YQX.equals(xsTpzt)||JpTpd.CG_TPZT_YBL.equals(cgTpzt)){
			    	continue;
			    }
			    try {
					List<JpTpdMx> mxList = jpTpdMxServiceImpl.getJpTpdMxByTpdh(tpdh,shbh);
					if (CollectionUtils.isNotEmpty(mxList)) {
						for (JpTpdMx t : mxList) {
							t.setCgStje(t.getCgTkje());
							jpTpdMxServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(t);
						}
					}
					jptpd.setCgStje(jptpd.getCgTkje());
					jptpd.setCgTpzt(JpTpd.CG_TPZT_YBL);
					jptpd.setCgBlr(user.getBh());
					jptpd.setCgBlsj(VeDate.getNow());
					this.baseService.getMyBatisDao().updateByPrimaryKeySelective(jptpd);
				} catch (Exception e) {
					logger.error("采购办理失败", e);
					msg+="退票单号["+tpdh+"]与采购完成失败<br>";
					//return this.addError("采购办理失败" + e.getMessage(), e, "edit", model);
				}
			}
		}
		return msg;
	}
	/**
	 * 采购完成
	 * @return
	 */
	@RequestMapping(value = "tpdCgWc_{tpdh}")
	public @ResponseBody Map<String,String> cgWc(@PathVariable("tpdh") String tpdh,JpTpdMx mx,ModelMap model){
		Map<String,String> retMap=new HashMap<String, String>();
		retMap.put("status", "fail");
		Shyhb shyhb = SessionUtils.getShshbSession();
		String shbh = shyhb.getShbh();
		try {
			
			JpTpd jptpd=this.baseService.getJpTpdByTpdh(tpdh,shbh);
			if (jptpd == null) {
				retMap.put("message", "采购办理失败,取退票单信息为空");
				return retMap;
			}
			
			List<JpTpdMx> mxList = jpTpdMxServiceImpl.getJpTpdMxByTpdh(tpdh,shbh);
			if (CollectionUtils.isEmpty(mxList)) {
				retMap.put("message", "采购办理失败,取退票单明细信息为空");
				return retMap;
			}
		
			for (JpTpdMx t : mxList) {
				t.setCgZdj(mx.getCgZdj());
				t.setCgPj(mx.getCgPj());
				t.setCgTax(mx.getCgTax());
				t.setCgJsf(mx.getCgJsf());
				t.setCgTkje(mx.getCgTkje());
				jpTpdMxServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(t);
			}
			
			jptpd.setCgTpzt(JpTpd.CG_TPZT_YBL);
			jptpd.setCgBlr(shyhb.getBh());
			jptpd.setCgBlsj(VeDate.getNow());
			jptpd.setXgyh(shyhb.getBh());
			jptpd.setXgly("采购完成");
			jptpd.setXgsj(VeDate.getNow());
			this.baseService.getMyBatisDao().updateByPrimaryKeySelective(jptpd);
			retMap.put("status", "ok");
		} catch (Exception e) {
			logger.error("采购办理失败", e);
			retMap.put("message", "采购办理失败:"+e.getMessage());
		}
		return retMap;
	}
	
	/**
	 * 网店宝取消座位
	 */
	/**
	 * @param tpdh
	 * @return
	 */
	@RequestMapping(value = "cancelSet",method = RequestMethod.POST)
	public @ResponseBody String cancelSet(String tpdh) {
		
		Shyhb shyhb=SessionUtils.getShshbSession();
		String shbh=shyhb.getShbh();
		JpPz  jppz=jpPzServiceImpl.getJpPzByShbh(shbh);
		//shyhb.setPidyh("16072718360531");
		//shyhb.setPidyh("8635");
		//jppz.setPzIp("192.168.1.69");
		//jppz.setPzPort("8088");
		JpTpd jptpd=this.baseService.getJpTpdByTpdh(tpdh, shbh);
		JpKhdd jpkhdd=new JpKhdd();
		jpkhdd.setShbh(shbh);
		jpkhdd.setDdbh(jptpd.getDdbh());
		jpkhdd=jpKhddServiceImpl.getKhddByDdbh(jpkhdd);
		
		if(jpkhdd == null){
			//系统中找不到订单
			return "系统中找不到退票订单对应的正常订单";
		}
		
		String pnrno=jpkhdd.getCgPnrNo();
		if (StringUtils.isBlank(pnrno) || "0".equals(pnrno)) {
		   //PNR不能为空或者0
		   return "PNR:["+pnrno+"]不合法";
		}
		String ifxx = "";
		try {
			PnrRtParam rtparam = new PnrRtParam();
			rtparam.setUserid(shyhb.getPidyh());
			rtparam.setPnrno(pnrno);
			rtparam.setUrl("http://" + jppz.getPzIp() + ":"+ jppz.getPzPort());
			Pnr pnr = IbeService.rtPnr(rtparam);
			//pnr_lx：0 解析失败，1 解析内容正常  2 编码已取消
			if("0".equals(pnr.getPnr_lx())){
				return "RTPNR解析出错";
			}else if("2".equals(pnr.getPnr_lx())){
				ifxx="XX";
			}else{
				ifxx = pnr.getPnr_zt();
			}
			String[] xxStrings = ifxx.split(",");
			for (int ixx = 0; ixx < xxStrings.length; ixx++) {
				if ("XX".equals(xxStrings[ixx])) {
					// 若已经黑屏XE，需更新取消状态，及PNR状态
					TpXePassengerTx.updateJpTpd(false,JpTpd.CG_ZDQXZT_QXCG, jptpd, "", shyhb.getBh(),this.baseService);
					return "PNR:【"+pnrno+"】已被XE，座位已取消！";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ifxx = e.getMessage();
			// XE乘机人失败后修改退废票主表
			try {
				TpXePassengerTx.updateJpTpd(false, JpTpd.CG_ZDQXZT_QXSB,jptpd, "", shyhb.getBh(), this.baseService);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return "PNR:【"+pnrno+"】XE失败</br>";
		}
		
		//PNR未被XE才能取消座位
		try {
			TpXeZw tpxeZw =SpringContextUtil.getBean(TpXeZw.class);
			tpxeZw.xezw(false, shyhb, jptpd,jpkhdd,jppz);
		} catch (Exception e) {
			e.printStackTrace();
			return "PNR:【"+pnrno+"】XE失败</br>";
		}
		return "PNR:【"+pnrno+"】XE成功";
	}
	

	
	/**
	 * 批量退票票证提交
	 * @param tpdhs
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "batchTppzTj")
	public @ResponseBody String batchTppzTj(String tpdhs,ModelMap model) throws Exception {
		String [] tpdh_arr=tpdhs.split(",");
		Shyhb shyhb = SessionUtils.getShshbSession();
		String shbh = shyhb.getShbh();
		String msg="";
		for (String tpdh : tpdh_arr) {
			JpTpd jptpd=this.baseService.getJpTpdByTpdh(tpdh, shbh);
			if (null != jptpd) {
			    if(JpTpd.CG_TPZT_YBL.equals(jptpd.getCgTpzt()) && !"1".equals(jptpd.getCgTppzTjzt())){
				    try {
				    	jptpd.setCgTppzTjzt("1");//0 未提交 1 已提交
						jptpd.setCgTppzTjyh(shyhb.getBh());
						jptpd.setCgTppzTjsj(VeDate.getNow());
						jptpd.setXgly("批量退票凭证提交");
						jptpd.setXgyh(shyhb.getBh());
						jptpd.setXgsj(VeDate.getNow());
						this.baseService.update(jptpd);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("凭证提交失败", e);
						msg+="退票单号["+tpdh+"]退票凭证提交失败<br>";
					}
			    }
			}
		}
		return msg;
	}
	
}

