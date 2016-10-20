package cn.vetech.vedsb.jp.service.jptpgl.zdtp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.detrxml.DetrResult;
import org.vetech.core.business.pid.api.detrxml.DetrXmlParam;
import org.vetech.core.business.pid.api.detrxml.Segment;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.cpsz.JpZdtfpGzsz;
import cn.vetech.vedsb.jp.entity.cpsz.JpZdtpJk;
import cn.vetech.vedsb.jp.entity.jphbyd.JpHbyd;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpHd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdCzrz;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.entity.qxxgl.BQinfo;
import cn.vetech.vedsb.jp.service.cpsz.JpZdtfpGzszServiceImpl;
import cn.vetech.vedsb.jp.service.cpsz.JpZdtpJkServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jphbyd.JpHbydServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpHdServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdCzrzServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.vedsb.jp.service.qxxgl.BQinfoServiceImpl;
import cn.vetech.vedsb.utils.DictEnum;

/**
 * 不同票证父类, 不同票证类型的票有不同处理逻辑
 * @author wangshengliang
 */

public abstract  class PzSuper {
	@Autowired
	JpKhddServiceImpl jpKhddServiceImpl;

	@Autowired
	JpKhddCjrServiceImpl jpKhddCjrServiceImpl;

	@Autowired
	JpKhddHdServiceImpl jpKhddHdServiceImpl;

	@Autowired
	ProcedureServiceImpl procedureServiceImpl;

	@Autowired
	JpZdtpJkServiceImpl jpZdtpJkServiceImpl;

	@Autowired
	JpTpdServiceImpl jpTpdServiceImpl;

	@Autowired
	JpTpdMxServiceImpl jpTpdMxServiceImpl;

	@Autowired
	JpTpdCzrzServiceImpl jpTpdCzrzServiceImpl; 
	
	@Autowired
	JpZdtfpGzszServiceImpl jpZdtfpGzszServiceImpl;
	
	@Autowired
	JpHdServiceImpl jpHdServiceImpl;
	
	@Autowired
	ShyhbServiceImpl shyhbServiceImpl;
	
    @Autowired
    JpHbydServiceImpl  jpHbydServiceImpl;
    
    @Autowired
    BQinfoServiceImpl  bQinfoServiceImpl;
	
	public static final String PZLX_NON = "NON";//代表不存在的票证类型
	public static final String PZLX_BOP = "BOP";
	public static final String PZLX_BSP = "BSP";
	public static final String PZLX_BSPET = "BSPET";
	public static final String PZLX_BP = "BP";
	public static final String PZLX_BPET = "BPET";
	public static final String PZLX_OP = "OP";
	public static final String PZLX_GP = "GP";
	public static final String PZLX_ODT = "ODT";
	public static final String PZLX_TB = "TB";
	
	
	/**
	 * 对不同票证类型申请退废
	 * @param isAuto   true 自动提交退票  false 手动提交退票
	 * @param isHbyw   true 航班延误            false 航班未延误
	 * @param isYctj   true 延迟提交            false 直接提交
	 * @param sh_yhb   用户信息
	 * @param jptpd    退票单信息
	 * @param gzsz     自动退票规则
	 * @return
	 * @throws Exception
	 */
	 abstract String applyTicketRefund(boolean isAuto,boolean isHbyw,boolean isYctj,Shyhb sh_yhb, JpTpd jptpd,JpZdtfpGzsz gzsz) throws Exception;
	 
	 /**
	  * 退票集中处理
	  * @param isAuto
	  * @param jptpd
	  * @param shyhb
	  * @param jpZdtpJkServiceImpl
	  */
	 public static void ticketRefundHandle(boolean isAuto,JpTpd jptpd,Shyhb shyhb,JpZdtpJkServiceImpl jpZdtpJkServiceImpl){
		PzSuper.zdtpJkCreate(jptpd, "开始采购提交退废单,tpdh:" + jptpd.getTpdh(), jpZdtpJkServiceImpl, shyhb);
		String pzlx = jptpd.getCgly();
		PzSuper.zdtpJkLog(isAuto,jptpd, "采购提交退费票,票证类型：" + pzlx, jpZdtpJkServiceImpl);
		PzSuper pzTicket = null;
		if (pzlx.equals(PzSuper.PZLX_OP) || pzlx.equals(PzSuper.PZLX_TB)) {
			pzTicket = SpringContextUtil.getBean(OpTicket.class);
		} else if (pzlx.equals(PzSuper.PZLX_BSPET) || pzlx.equals(PzSuper.PZLX_BOP) || pzlx.equals(PzSuper.PZLX_BSP)) {
			pzTicket = SpringContextUtil.getBean(BspTicket.class);
		} else if (pzlx.equals(PzSuper.PZLX_BPET) || pzlx.equals(PzSuper.PZLX_BP)) {
			pzTicket =SpringContextUtil.getBean(BpTicket.class);
		}
		if (pzTicket != null) {
			try {
				pzTicket.applyTicketRefund(isAuto,shyhb, jptpd.getTpdh());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.print("提交采购退票报错");
			}
		}
	 }

	/**
	 * 对不同票证类型申请退废并做判断是否申请退票
	 * @param sh_yhb
	 * @param tfid
	 * @param isAuto
	 * @return
	 * @throws Exception
	 */
	public String applyTicketRefund(boolean isAuto,Shyhb sh_yhb, String tpdh) throws Exception{
		JpTpd jptpd=jpTpdServiceImpl.getJpTpdByTpdh(tpdh, sh_yhb.getShbh());
		PzSuper.zdtpJkLog(isAuto,jptpd, "销售退票状态：<b>"+DictEnum.XSTFPZT.dataMap.get(jptpd.getXsTpzt()).getMc()+"</b>,采购退票状态：<b>"+DictEnum.CGTFPZT.dataMap.get(jptpd.getCgTpzt()).getMc()+"</b>", jpZdtpJkServiceImpl);
		//验证退废票状态   已取消 OR 已提交
		if(JpTpd.XS_TPZT_YQX.equals(jptpd.getXsTpzt()) || JpTpd.CG_TPZT_YTJ.equals(jptpd.getCgTpzt())){
			PzSuper.tpydrz(tpdh, "该退废单已取消或采购已提交！",jpTpdCzrzServiceImpl,sh_yhb);
			PzSuper.zdtpJkLog(isAuto,jptpd, "退废单已取消或采购已提交,不能提交采购退票", jpZdtpJkServiceImpl);
			return null;
		}
		JpZdtfpGzsz gzsz=null;
		
		
		/**
		 *   如果有航变则直接提交退票
		 *     1)匹配Q信息是否有航变延误
		 *     2)匹配航变异动表是有航班延误
		 */
		boolean isHbyw=false;
		isHbyw=PzSuper.isBqinfo(jptpd, bQinfoServiceImpl);
		if(isHbyw){
			PzSuper.zdtpJkLog(isAuto,jptpd,"PNR["+jptpd.getCgPnrNo()+"] 订单编号["+jptpd.getDdbh()+"]有Q信息", jpZdtpJkServiceImpl);
		}else{
			isHbyw=PzSuper.isHbyw(jptpd,jpHbydServiceImpl);
		}
		boolean isYctj=false;
		if(isAutoRefund(isAuto,isHbyw,jptpd, sh_yhb,gzsz)){
			return this.applyTicketRefund(isAuto,isHbyw,isYctj,sh_yhb,jptpd,gzsz);
		}
		return null;
	}
	
	/**
	 * 判断是否可自动申请退废
	 * @param sh_yhb
	 * @param ticket_return
	 * @param isAuto
	 * @return
	 * @throws Exception
	 */
	public  boolean isAutoRefund(boolean isAuto,boolean isHbyw,JpTpd jptpd, Shyhb sh_yhb,JpZdtfpGzsz gzsz) throws Exception{
		/**
		 *  1. 如果手动退票则直接提交退票
		 */
		if (!isAuto) {
			return true;
		}
		
		if(isHbyw){
			PzSuper.zdtpJkLog(isAuto,jptpd,"航班["+jptpd.getCgHbh()+"] "+jptpd.getHc()+" "+VeDate.formatToStr(jptpd.getCfrq(), "yyyy-MM-dd")+"有航变信息", jpZdtpJkServiceImpl);
			return true;
		}
		
		PzSuper.zdtpJkLog(isAuto,jptpd, "开始匹配自动退废票规则", jpZdtpJkServiceImpl);
		
		/**
		 * 2.查询自动退废票规则
		 */
		String tpdh = jptpd.getTpdh();
		String shbh = jptpd.getShbh();
		gzsz=new JpZdtfpGzsz();
		gzsz.setShbh(shbh);//商户编号
		gzsz.setPzlx(jptpd.getCgly());//采购来源
		gzsz.setSyptbs(jptpd.getWdid());//所属网店
		gzsz.setZt("1");// 状态：0未审核，1已审核，2停用，3已取消
		gzsz.setIsdel("0");// 0表示正常状态 1表示已经是删除状态了
		gzsz.setCfrqs(VeDate.dateToStr(jptpd.getCfrq()));//起飞日期始
		gzsz.setCfrqz(VeDate.dateToStr(jptpd.getCfrq()));//起飞日期止
		String hbh=jptpd.getCgHbh();
		if(StringUtils.isNotBlank(hbh)){
			String hkgs=hbh.replace("*", "").substring(0,2);
			gzsz.setHkgs(hkgs);//航空公司
		}
		
		List<JpZdtfpGzsz> list = jpZdtfpGzszServiceImpl.getZdTfpGzsz(gzsz);
		if (CollectionUtils.isEmpty(list)) {
			PzSuper.tpydrz(tpdh, "请设置自动退废票规则",jpTpdCzrzServiceImpl, sh_yhb);
			PzSuper.zdtpJkLog(isAuto,jptpd, "请设置自动退废票规则", jpZdtpJkServiceImpl);
			return false;
		}
		gzsz=null;
		//匹配规则
		for(JpZdtfpGzsz gz:list){
			//废票
			if("2".equals(gz.getTpfp())){
				gzsz=gz;
				break;
			}else{//退票
				//是否自愿退票
				if(jptpd.getCgSfzytp().equals(gz.getTplx())){
					gzsz=gz;
					break;
				}
			}
		}
		
		if (gzsz == null) {
			PzSuper.zdtpJkLog(isAuto,jptpd, "未匹配到自动退废票规则", jpZdtpJkServiceImpl);
			return false;
		}
		
		/**
		 * 3.取退票单明细匹配退废规则
		 */
		List<JpTpdMx> mxList = jpTpdMxServiceImpl.getJpTpdMxByTpdh(tpdh, shbh);
		if (CollectionUtils.isEmpty(mxList)) {
			PzSuper.tpydrz(tpdh, "无退票单明细信息",jpTpdCzrzServiceImpl, sh_yhb);
			PzSuper.zdtpJkLog(isAuto,jptpd,"无退票单明细信息", jpZdtpJkServiceImpl);
			return false;
		}
		
		String SELECTED_ALL = "---";
		String operationLog = "该退费单不满足自动退费票规则设置,";

		for (JpTpdMx jtm : mxList) {
			//1)获取退票航段信息
			List<JpHd> hdList = jpHdServiceImpl.getJpHdByTpMxId(jtm.getId(),shbh);
			if (CollectionUtils.isEmpty(hdList)) {
				PzSuper.tpydrz(tpdh,"该退票单无对应航段信息", jpTpdCzrzServiceImpl, sh_yhb);
				return false;
			}

			for (JpHd hd : hdList) {
				//2)舱位
				String cw = hd.getCgCw();
				if (!SELECTED_ALL.equals(gzsz.getCw())) {
					List<String> cwList = getListByStr(gzsz.getCw(), "/");
					if (!cwList.contains(cw)) {
						PzSuper.tpydrz(tpdh, operationLog + "当舱位为:" + cw + ";设置为：" + cwList, jpTpdCzrzServiceImpl, sh_yhb);
						return false;
					}
				}

				//3) 出发城市到达城市
				String cfcity = hd.getCfcity();
				String ddcity = hd.getDdcity();
				if (!SELECTED_ALL.equals(gzsz.getCfcity())) {
					List<String> cfCityList = getListByStr(gzsz.getCfcity(),"/");
					if (!cfCityList.contains(cfcity)) {
						PzSuper.tpydrz(tpdh, operationLog+ "当出发城市为:" + cfcity + ";设置内容为：" + cfCityList,jpTpdCzrzServiceImpl, sh_yhb);
						return false;
					}
				}
				if (!SELECTED_ALL.equals(gzsz.getDdcity())) {
					List<String> ddCityList = getListByStr(gzsz.getDdcity(),"/");
					if (!ddCityList.contains(ddcity)) {
						PzSuper.tpydrz(tpdh, operationLog+ "当到达城市为:" + ddcity + ";设置内容为：" + ddCityList,jpTpdCzrzServiceImpl, sh_yhb);
						return false;
					}
				}

			}
		}
		
		/**
		 * 4.废票不赌航变
		 */
		if("2".equals(gzsz.getTpfp())){
			//jptpd.setCgBllx("2");
			jptpd.setXgly("自动退票");
			jptpd.setXgsj(VeDate.getNow());
			jptpd.setXgyh(sh_yhb.getBh());
			//jpTpdServiceImpl.updateSelective(jptpd);
			PzSuper.zdtpJkLog(jptpd,"匹配自动退废票规则ID:["+gzsz.getId()+"]",gzsz, jpZdtpJkServiceImpl);
			return true;
		}
		
		
		/**
		 * 5.退票是否赌航变  0赌航变   1不赌航变
		 */
		if("1".equals(gzsz.getIshb())){
			PzSuper.zdtpJkLog(jptpd,"匹配自动退废票规则ID:["+gzsz.getId()+"]不赌航变,直接提交退票",gzsz, jpZdtpJkServiceImpl);
			return true;
		}
		
		
		/**
		 * 赌航变
		 * 1)提前起飞时间量
		 * 2)判断最小退票费
		 * 3)判断最小利润率
		 * 4)判断最小利润值
		 */
		
		//1)判断提前起飞时间量
		Double  gzsz_hbtqsj=gzsz.getHbTqsj().doubleValue();
		Double  hbtqsj=VeDate.getTwoMin(jptpd.getCfrq(),VeDate.getNow())/60.00;
		
		if(hbtqsj - gzsz_hbtqsj <= 0){
			return true;
		}
		
		//2)判断最小退票费
		if((jptpd.getXsTpsxf().doubleValue()-gzsz.getHbZxtpf().doubleValue())<0){
		    return true;
		}
		
		//3)判断最小利润率    票面价*退票费率
		if(jptpd.getXsTpfl().doubleValue()-gzsz.getHbZxlrl().doubleValue()<0){
			return true;
		}
		
		//4)判断最小利润    
		if(jptpd.getXsTpfl().doubleValue()-gzsz.getHbZxlr().doubleValue()<0){
			return true;
		}
		
		PzSuper.zdtpJkLog(jptpd,"匹配自动退废票规则ID:["+gzsz.getId()+"]",gzsz, jpZdtpJkServiceImpl);
		
		
		/**
		 * 延迟采购提交 最晚采购办理时间
		 */
		//jptpd.setCgZwblsj(VeDate.getPreMin(jptpd.getCfrq(), 0-(int)(gzsz_hbtqsj.doubleValue()*60)));
		//jpTpdServiceImpl.updateSelective(jptpd);
		
		
		
		
		
		/**
		 * 4.检查客票状态
		 * 
		 String tknos=VeStr.getValue(tknoMap, "TKNO");
			if (gzsz != null && gzsz.getIsjckpzt().equals("0")) {//0检查客票状态  ；1不检查客票状态
			String[] tknoarr=tknos.split(",");
			if (tknoarr.length == 0) {
				log(jptpd.getDdbh(),"没有找到退票单在系统中对应的票号");
				return "没有找到退票单在系统中对应的票号";
			}
			boolean isOpen = false;
			for (int i = 0; i < tknoarr.length; i++) {
				GetTicketByDetrResponse response = null;
				CpsAccount account = new CpsAccount();
				account.setBusinessNo(jptpd.getShbh());
				CpsService cpsService = new CpsService();
				try {
					response = cpsService.callCpsDetr(tknoarr[i], account);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if (null != response) {
					for (Segment sg : response.getSegments()) {
						isOpen = sg.getKpzt().equals("OPEN");
						if (!isOpen) {
							log(jptpd.getDdbh(),"票号:["+tknoarr[i]+"]的客票状态为["+sg.getKpzt()+"]非OPEN状态");
							return "票号:["+tknoarr[i]+"]的客票状态为["+sg.getKpzt()+"]非OPEN状态";
						}
					}
				} else {
					log(jptpd.getDdbh(),"获取票号:["+tknoarr[i]+"]的客票状态异常");
					return "获取票号:["+tknoarr[i]+"]的客票状态异常";
				}
			}
		}
		*/
		
		return false;
	}
	
	

	/**
	 * 检查客票状态
	 * @param jptpd
	 * @param shyhb
	 * @param jpTpdMxServiceImpl
	 * @param log
	 * @return
	 */
	public static boolean getKpzt(JpTpd jptpd,Shyhb shyhb,JpTpdMxServiceImpl jpTpdMxServiceImpl,JpPtLog log){
		String shbh = shyhb.getShbh();
		/**
		 * 检查采购来源 为BOP BSP BSPET才进行客票检查
		 */
		String cgly=jptpd.getCgly();
		if(!"BSP".equals(cgly) && !"BSPET".equals(cgly) && !"BOP".equals(cgly)){
			log.add("采购来源为：["+cgly+"]的客票不需要检查客票状态");
			return true;
		}
		
		/**
		 * 1.检查PID机票配置信息
		 */
		JpPz jppz = jpTpdMxServiceImpl.getJpPzByShbh(shbh);
		if (jppz == null) {
			log.add("请检查PID机票配置信息,不能与客户办理自动退票");
			return false;
		}

		/**
		 * 2.取退票单明细信息
		 */
		List<JpTpdMx> mxList = jpTpdMxServiceImpl.getJpTpdMxByTpdh(jptpd.getTpdh(),shyhb.getShbh());
		if (CollectionUtils.isEmpty(mxList)) {
			log.add("找不到退票明细信息,不能与客户办理自动退票");
			return false;
		}
		
		for (JpTpdMx mx : mxList) {
			/**
			 * 3.检查航段信息
			 */
			
			String  tkno= mx.getTkno();
			List<JpHd> tphdList = jpTpdMxServiceImpl.getJpHdByTpMxId(mx.getId(), shbh);
			if (CollectionUtils.isEmpty(tphdList)) {
				log.add("查询不到退票航段信息,不能与客户办理自动退票");
				return false;
			}

			/**
			 * 4.执行Detr指令检查客票状态
			 */
			DetrXmlParam detrXmlParam = new DetrXmlParam();
			detrXmlParam.setTkno(mx.getTkno());
			detrXmlParam.setUserid(shyhb.getPidyh());
			detrXmlParam.setUrl("http://" + jppz.getPzIp() + ":"+ jppz.getPzPort());
			DetrResult detrResult = null;
			try {
				detrResult = IbeService.detrXml(detrXmlParam);
			} catch (EtermException e) {
				log.add("票号["+tkno+"]Detr客票信息异常,不能与客户办理自动退票:"+e.getMessage());
				e.printStackTrace();
				return false;
			}
			if (detrResult == null) {
				log.add("票号["+tkno+"]Detr客票信息返回为空,不能与客户办理自动退票");
				return false;
			}

			List<Segment> segments = detrResult.getSegments();
			if (CollectionUtils.isEmpty(segments)) {
				log.add("票号["+tkno+"]Detr客票信息返回航段信息为空,不能与客户办理自动退票");
				return false;
			}
			for (Segment s : segments) {
				String kpzt = s.getKpzt();
				if (!("OPEN FOR USE".equals(kpzt) || "SUSPENDED".equals(kpzt))) {
					log.add("票号["+tkno+"]客票状态为:["+kpzt+"],不能与客户办理自动退票");
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	
	/**
	 * 判断Q信息是否有延误
	 * @param jptpd
	 * @param jpHbydServiceImpl
	 * @return
	 */
	public static boolean isBqinfo(JpTpd jptpd,BQinfoServiceImpl bQinfoServiceImpl){
		BQinfo  bq=new BQinfo();
		bq.setShbh(jptpd.getShbh());
		bq.setDdbh(jptpd.getDdbh());
	//	bq.setPnrHc(jptpd.getHc());
	//	bq.setPnrHbh(jptpd.getCgHbh());
		bq.setPnrCjr(jptpd.getCjr());
		bq.setPnrno(jptpd.getCgPnrNo());
		return bQinfoServiceImpl.isBqinfo(bq);
	}
	/**
	 * 判断航班是否延误
	 * @param jptpd
	 * @param jpHbydServiceImpl
	 * @return
	 */
	public static boolean isHbyw(JpTpd jptpd,JpHbydServiceImpl jpHbydServiceImpl){
		JpHbyd hbyd=new JpHbyd();
		hbyd.setShbh(jptpd.getShbh());
		hbyd.setHc(jptpd.getHc());
		hbyd.setHbh(jptpd.getCgHbh());
		String hbh=jptpd.getCgHbh();
	    String hkgs=hbh.replace("*", "").substring(0,2);
	    hbyd.setHkgs(hkgs);
	    hbyd.setBy3(jptpd.getDdbh());
		hbyd.setCfrq(VeDate.formatToStr(jptpd.getCfrq(), "yyyy-MM-dd"));
		return jpHbydServiceImpl.isJpHbyd(hbyd);
	}
	
	
	
	
	/**
	 * 异动日志记录
	 * @param tfid
	 * @param cznr
	 * @param jpTpdCzrzServiceImpl
	 * @param sh_yhb
	 * @throws Exception
	 */
	
	public static void tpydrz(String tpdh, String cznr,JpTpdCzrzServiceImpl jpTpdCzrzServiceImpl,Shyhb sh_yhb) throws Exception{
		JpTpdCzrz czrz=new JpTpdCzrz();
		czrz.setYwid(tpdh);
		czrz.setShbh(sh_yhb.getShbh());
		czrz.setCzsj(VeDate.getNow());
		czrz.setCzly("自动退票采购提交失败");
		czrz.setCzr(sh_yhb.getBh());
		czrz.setCznr(VeStr.substrByte(cznr, 4000));//操作内容
		try {
			jpTpdCzrzServiceImpl.update(czrz);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 记录自动退票监控日志
	 * @param tpdh
	 * @param cznr
	 * @param jpTpdCzrzServiceImpl
	 * @param sh_yhb
	 * @throws Exception
	 */
	public static void zdtpJkLog(boolean isAuto,JpTpd jptpd, String sm,JpZdtpJkServiceImpl jpZdtpJkServiceImpl){
	   sm=log(sm);
	   JpZdtpJk  tpjk=new JpZdtpJk();
	   tpjk.setShbh(jptpd.getShbh());
	   tpjk.setTpdh(jptpd.getTpdh());
	   tpjk=jpZdtpJkServiceImpl.getJpZdtpJkInfo(tpjk);
	   sm=tpjk.getSm()+sm;
	   tpjk.setSm(VeStr.substrByte(sm,4000));
	   try {
		   jpZdtpJkServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(tpjk);
	   }catch (Exception e) {
			e.printStackTrace();
			System.out.print("自动退票监控日志记录异常:"+e.getMessage());
		}
	}
	
	/**
	 * 记录自动退票监控日志
	 * @param tpdh
	 * @param cznr
	 * @param jpTpdCzrzServiceImpl
	 * @param sh_yhb
	 * @throws Exception
	 */
	public static void zdtpJkLog(boolean isAuto,boolean isTpSuccess,JpTpd jptpd, String sm,JpZdtpJkServiceImpl jpZdtpJkServiceImpl){
	   sm=log(sm);
	   JpZdtpJk  tpjk=new JpZdtpJk();
	   tpjk.setShbh(jptpd.getShbh());
	   tpjk.setTpdh(jptpd.getTpdh());
	   tpjk=jpZdtpJkServiceImpl.getJpZdtpJkInfo(tpjk);
	   String tpzt=tpjk.getTpzt();
	   sm=tpjk.getSm()+sm;
	   tpjk.setSm(VeStr.substrByte(sm,4000));
	   tpjk.setTpzt("2");
	   if(isTpSuccess){
			if("TB".equals(jptpd.getCgly())){
				tpjk.setTpzt("3");//退票中
				if("3".equals(tpzt)){
					tpjk.setTpzt(isAuto ? "1" : "4");//退票成功
				}
			}else{
				tpjk.setTpzt(isAuto ? "1" : "4");
			}
	   }
	   tpjk.setTpwcsj(VeDate.getNow());
	   tpjk.setZxhs(VeDate.getTwoSec(tpjk.getTpwcsj(), tpjk.getCjsj()));
	   try {
		   jpZdtpJkServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(tpjk);
	   }catch (Exception e) {
			e.printStackTrace();
			System.out.print("自动退票监控日志记录异常:"+e.getMessage());
		}
	}
	
	public static void zdtpJkLog(JpTpd jptpd, String sm,JpZdtfpGzsz gzsz,JpZdtpJkServiceImpl jpZdtpJkServiceImpl){
		   sm=log(sm);
		   JpZdtpJk  tpjk=new JpZdtpJk();
		   tpjk.setShbh(jptpd.getShbh());
		   tpjk.setTpdh(jptpd.getTpdh());
		   tpjk=jpZdtpJkServiceImpl.getJpZdtpJkInfo(tpjk);
		   sm=tpjk.getSm()+sm;
		   tpjk.setSm(VeStr.substrByte(sm,4000));
		   if(StringUtils.isNotBlank(gzsz.getId())){
			   tpjk.setZdtpSffh("1");
			   tpjk.setGzid(gzsz.getId());
		   }
		   try {
			   jpZdtpJkServiceImpl.getMyBatisDao().updateByPrimaryKeySelective(tpjk);
		   }catch (Exception e) {
				e.printStackTrace();
				System.out.print("自动退票监控日志记录异常:"+e.getMessage());
			}
		}
	
	/**
	 * 创建退票监控记录
	 * @param jptpd
	 * @param sm
	 * @param jpZdtpJkServiceImpl
	 * @param sh_yhb
	 */
	public static void zdtpJkCreate(JpTpd jptpd, String sm,JpZdtpJkServiceImpl jpZdtpJkServiceImpl,Shyhb sh_yhb){
		sm=log(sm);
		JpZdtpJk tpjk = new JpZdtpJk();
		tpjk.setShbh(jptpd.getShbh());
		tpjk.setTpdh(jptpd.getTpdh());
		tpjk = jpZdtpJkServiceImpl.getJpZdtpJkInfo(tpjk);
		if(tpjk==null){
			tpjk=jpZdtpJkServiceImpl.getJpZdtpJkCreateInfo(jptpd.getShbh(),jptpd.getTpdh());
			tpjk.setCjsj(VeDate.getNow());
			tpjk.setZdtpy(sh_yhb.getBh());
			tpjk.setSm(VeStr.substrByte(sm,4000));
			try {
				jpZdtpJkServiceImpl.insert(tpjk);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.print("自动退票监控日志记录异常:"+e.getMessage());
			}
		}
	}
	
	public static List<String> getListByStr(String str, String separator){
		if(str == null){
			return new ArrayList<String>();
		}
		return Arrays.asList(str.split(separator));
	}
	
	public static String log(String content) {
	     return "【"+VeDate.getStringDate()+"】"+StringUtils.trimToEmpty(content)+"\t<br>";
	}
	
	
	public static void main(String[] args) {
		//System.out.println(VeDate.getTwoMin(VeDate.strToDateLong("2016-08-12 18:00:00"),VeDate.getNow())/60.00);
		System.out.println(VeDate.getNow());
	}
}
