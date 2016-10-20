package cn.vetech.vedsb.jp.service.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.cache.VeclassCache;
import org.vetech.core.business.cache.VeclassCacheService;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.detrxml.DetrResult;
import org.vetech.core.business.pid.api.detrxml.DetrXmlParam;
import org.vetech.core.business.pid.api.detrxml.Segment;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.jp.service.jpjpgl.JpSmfsSzServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.PkgExtendServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.vedsb.platpolicy.cps.request.CpsAccount;
import cn.vetech.vedsb.platpolicy.cps.response.ticket.GetTicketByDetrResponse;
import cn.vetech.vedsb.platpolicy.cps.service.CpsService;
/**
 * Open票自动扫描
 */
@Service
public class ExpireOpenTicketScanJobService  {
	protected final Logger logger = LoggerFactory.getLogger(ExpireOpenTicketScanJobService.class);
	@Autowired
	private CpsService cpsService;
	@Autowired
	private JpSmfsSzServiceImpl jpSmfsSzServiceImpl;
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private VeclassCacheService vec;
	@Autowired
	private PkgExtendServiceImpl pkgExtendServiceImpl;

	/**
	 * 记录今天已经扫描过的票
	 */
	private static Map<String, String> SCANMAP = new HashMap<String, String>();

	public void doJob() throws JobExecutionException {
		// 1.移除昨天已经扫描过的记录
		SCANMAP.remove(VeDate.getNextDay(VeDate.getStringDateShort(), "-1"));
		/**
		 * 2.获取采购类型
		 */
		List<VeclassCache> list = vec.getLb("10014");
		for (VeclassCache ve : list) {
			/**
			 * 获取到期OPEN票、DETR方式为调用CPS的所有JPSMFSSZ 过滤条件中SMLX 扫描类型0到期OPEN票扫描
			 * 1未乘机2异常改签 采购来源 DETER方式0调用本地PIDDETR，1调用CPS接口DETR
			 */
			// 2CPS
			List<Map<String,Object>> jsList = null;
			try {
				jsList = jpSmfsSzServiceImpl.queryJpSmfsszListBySmlx("0", ve.getId(), "1");
			} catch (Exception e) {
				log("查询总公司异常" + e.getMessage());
			}
			if (CollectionUtils.isNotEmpty(jsList)) {
				try {
					for (int i = 0; i < jsList.size(); i++) {
						String shbh=VeStr.getValue(jsList.get(i), "SHBH");
						// 在机票扫描时间设置表中获取时间相关设置
						int tqts=NumberUtils.toInt(VeStr.getValue(jsList.get(i), "TQTS"),0);
						// 设置需要扫描的日期
						String CPRQ = VeDate.formatToStr(VeDate.getPreDay(VeDate.getNow(), tqts),"yyyy-MM-dd");
						// DETR类型，0调用本地PIDDETR，1调用CPS接口DETR
						// 走CPS
						/**
						 * 3后台会根据商户编号自动匹配走CPS的票
						 */
						detrTicket(shbh, "1", CPRQ, null, null);
					}
				} catch (Exception e) {
					log("机票扫描时间设置表中获取时间相关设置出错" + e.getMessage());
				}

			}
			
			/**
			 * 获取到期Open票、deter方式为调本地的所有JpSmfsSz
			 */
			// 2.2本地
			try {
				jsList = jpSmfsSzServiceImpl.queryJpSmfsszListBySmlx("0", ve.getId(), "0");
			} catch (Exception e) {
				log("查询总公司异常" + e.getMessage());
				return;
			}
			if (CollectionUtils.isEmpty(jsList)) {
				log("没有设置到期Open票DETR方式");
				return;
			}
			try {
				for (int i = 0; i < jsList.size(); i++) {
					String shbh=VeStr.getValue(jsList.get(i), "SHBH");
					// 在机票扫描时间设置表中获取时间相关设置
					int tqts=NumberUtils.toInt(VeStr.getValue(jsList.get(i), "TQTS"),0);
					// 设置需要扫描的日期
					String CPRQ = VeDate.formatToStr(VeDate.getPreDay(VeDate.getNow(), tqts),"yyyy-MM-dd");
					//获取pid账号和pid密码
					String pidyhbh=VeStr.getValue(jsList.get(i), "PIDYHBH");
					String pidmm=VeStr.getValue(jsList.get(i), "PIDMM");
							
					// DETR类型，0调用本地PIDDETR，1调用CPS接口DETR
					// 走本地
					/**
					 * 3.2后台会根据商户编号自动匹配走CPS的票
					 */
					detrTicket(shbh, "0", CPRQ,pidyhbh,pidmm);
				}
			} catch (Exception e) {
				log("机票扫描时间设置表中获取时间相关设置出错" + e.getMessage());
			}
		}
	}

	/**
	 * @param shbh 商户编号
	 * @param detrLx detr方式
	 * @param cprq 日期
	 * @param pidyhbh pid账号
	 * @param pidmm pid密码
	 */
	public void detrTicket(String shbh, String detrLx, String cprq,String pidyhbh,String pidmm) {
		/**
		 * 根据商户编号的获取Open票
		 */
		List<Map<String, Object>> openTicketList = this.getDetrData(shbh, detrLx, cprq);
		if(CollectionUtils.isEmpty(openTicketList)){
			return;
		}
		/**
		 * 遍历openTicketList
		 */
		for (int j = 0; j < openTicketList.size(); j++) {
			Map<String, Object> m = openTicketList.get(j);
			String szmtkno = (String) m.get("TKNO");// "7848542048767";
			String cpDate = (String) m.get("CP_DATE");// "2016-02-16";

			// 当天重复的票不需要调用CPS接口DETR
			String intkno = StringUtils.trimToEmpty(SCANMAP.get(VeDate.getStringDateShort()));
			if (intkno.indexOf(szmtkno) >= 0) {
				log("到期Open票扫描，当天重复执行的票号：" + szmtkno + "跳过");
				continue;
			}
			
			SCANMAP.put(VeDate.getStringDateShort(), intkno + "," + szmtkno);
			
			/**
			 * DETR后调用后台过程入库
			 */
			Map<String,Object> paramMap = new HashMap<String,Object>();
			// DETER方式0调用本地PIDDETR，1调用CPS接口DETR
			if ("1".equals(detrLx)) {
				/**
				 * 根据商户编号获取CpsAccount
				 */
				CpsAccount ccount = cpsService.getCpsAccount(shbh);
				if (ccount == null) {
					return;
				}
				try {
					/**
					 * 调用CPS DETR接口,同步数据
					 */
					GetTicketByDetrResponse cpsRresponse = cpsService.callCpsDetr(szmtkno, ccount);// "OPEN FOR USE"
					if (cpsRresponse == null) {
						log("到期Open票[" + szmtkno + "]调用CPSDETR返回信息为空！");
						continue;
					}
					if ("-1".equals(cpsRresponse.getStatus())) {// 打印输出错误信息(此种错误快捷游不会收取流量费)
						log("到期Open票[" + szmtkno + "]调用CPSDETR返回错误：" + cpsRresponse.getErrorCode() + ":" + cpsRresponse.getErrorMessage());
						continue;
					}
					String state=null;//状态
					if (cpsRresponse != null) {
						if ("-1".equals(cpsRresponse.getStatus())) {// 返回错误信息
							state=cpsRresponse.getErrorMessage();
						} else {
							if (cpsRresponse.getSegments() != null && cpsRresponse.getSegments().get(0) != null && StringUtils.isNotBlank(cpsRresponse .getSegments().get(0).getKpzt())) {
								state=cpsRresponse.getSegments().get(0).getKpzt();
							} else {
								state=cpsRresponse.getErrorMessage();
							}
						}
					}
					paramMap = setXMLState(szmtkno, cpDate,"1", state,shbh);// DETR方式，0代表调本地PID，1代表调CPS接口
				} catch (Exception e) {
					log("走CPS获取状态出错"+e.getMessage());
				}
			} else {
				try {
					// 调用XEPNR接口
					JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(shbh);
					if(jpPz==null){
						return;
					}
					DetrXmlParam param = new DetrXmlParam();
					param.setShbh(shbh);
					param.setTkno(szmtkno);
					param.setUserid(pidyhbh);
					param.setPassword(pidmm);
					param.setUrl(jpPz.getPzIp() + ":" + jpPz.getPzPort());
					param.setOfficeId(jpPz.getOfficeid());
					DetrResult detrResult=IbeService.detrXml(param);
					
					List<Segment> segments=detrResult.getSegments();
					//List<PnrHd> list=pnr.getHdblist();
					if(CollectionUtils.isNotEmpty(segments)){
						continue;
					}
					String state=segments.get(0).getKpzt();
					paramMap = setXMLState(szmtkno, cpDate,"0", state,shbh);// DETR方式，0代表调本地PID，1代表调CPS接口
				} catch (Exception e) {
					log("走本地获取状态出错" + e.getMessage());
				}
			}
			if (paramMap.size()>0) {
				detrToDb(paramMap);
			}
		}

	}

	/**
	 * 
	 * @param szmtkno 票号
	 * @param cpDate 出票时间
	 * @param detrLx DETR方式
	 * @param state 状态
	 * @return
	 */
	private Map<String,Object> setXMLState(String szmtkno, String cpDate,String detrLx, String state,String shbh) {
	 	/*
		StringBuffer paramXml = new StringBuffer();
		paramXml.append("<TICKETSSTATE>");
		paramXml.append(XmlUtils.xmlnode("DATE", cpDate));
		paramXml.append(XmlUtils.xmlnode("TICKETNO", szmtkno));
		paramXml.append(XmlUtils.xmlnode("SHBH", shbh));
		paramXml.append(XmlUtils.xmlnode("STATE",state));
		paramXml.append(XmlUtils.xmlnode("DETRFS", detrLx));// DETR方式，0代表调本地PID，1代表调CPS接口
		paramXml.append("</TICKETSSTATE>");
		*/
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("DATE", cpDate);
		param.put("TICKETNO", szmtkno);
		param.put("SHBH", shbh);
		param.put("STATE", state);
		param.put("DETRFS", detrLx);//// DETR方式，0代表调本地PID，1代表调CPS接口
		return param;
	}

	/**
	 * 获取需要调CPS DETR接口的票 DETR方式，0代表调本地PID，1代表调CPS接口
	 * @param shbh 商户编号
	 * @param detrLx detr方式
	 * @param cprq 日期
	 * @return
	 */
	public List<Map<String, Object>> getDetrData(String shbh, String detrLx, String cprq) {
		List<Map<String, Object>> list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("SHBH", shbh);
		param.put("CPRQ", cprq);
		try {
			if ("0".equals(detrLx)) {
				// 走本地
				pkgExtendServiceImpl.getTksForCompanies(param);
			} else {
				// 走CPS
				procedureServiceImpl.getOpenTicket(param);
			}

			list = (List<Map<String, Object>>) param.get("list");
		} catch (Exception e) {
			log("获取需要调CPS DETR接口的票" + e.getMessage());
		}
		return list;
	}

	/**
	 * DETR后调用后台过程入库
	 * 
	 * @param xml
	 */
	private void detrToDb(Map<String,Object> param) {
		log("到期Open票DETR后入库XML：" + param);
		try {
			pkgExtendServiceImpl.fUpdateTkState(param);
			//int result= NumberUtils.toInt(VeStr.getValue(param, "result"), 0);
			String error = VeStr.getValue(param, "ERROR");
			if (StringUtils.isNotBlank(error)) {
				log(error);
			}
		} catch (Exception e) {
			log("DETR后调用后台过程入库出错" + e.getMessage());
		}
	}

	/**
	 * 记录日志
	 * @param info 
	 */
	private void log(String info) {
		logger.error("到期Open票扫描出错==>" + info);
	}
}
