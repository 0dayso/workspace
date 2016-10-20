package cn.vetech.web.vedsb.jptpgl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.detrf.DetrFParam;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.trfd.TrfdParam;
import org.vetech.core.business.pid.api.trfd.TrfdPreviewResult;
import org.vetech.core.business.pid.api.trfd.TrfdSubmitResult;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.utils.ToPinYin;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpHd;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
/**
 * BSP 白屏退票
 * TRFD VT
 * @author wangshengliang
 *
 */
@Controller
public class JpTpdHxtpController extends MBaseControl<JpTpd, JpTpdServiceImpl>{
	
	private static final String PAGE_SIZE = "10";
	
	@Autowired
	private JpHdServiceImpl  jpHdServiceImpl;
	
	@Autowired
	private JpTpdMxServiceImpl jpTpdMxServiceImpl;
	
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	
	@Autowired
	private JpJpServiceImpl jpJpServiceImpl;
	
	
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	
	@Override
	public void updateInitEntity(JpTpd t) {
		
	}

	@Override
	public void insertInitEntity(JpTpd t) {
		
	}
	
	/**
	 * @param tpdh
	 * @param printno
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getTicket_{tpdh}")
	public String getTicket(@PathVariable("tpdh") String tpdh,String printno, ModelMap model){
		
/*		
		String forward = Param.getParam(request, "forward", "trfd");
		String id = request.getParameter("id");
		String printno = StringUtils.trimToEmpty(Param.getParam(request, "printno"));
		Ticket_return_service ticket_return_service = (Ticket_return_service) WebAppUtil.getService(
				"ticket_return_service", request);
		Ticket_returnrecord_service ticket_returnrecord_service = (Ticket_returnrecord_service) WebAppUtil.getService(
				"ticket_returnrecord_service", request);
		if (StringUtils.isNotBlank(printno)) { // BPET 德付通退票保存打票机
			Ticket_returnrecord tr = new Ticket_returnrecord();
			tr.setTfid(id); 
			tr.setTp_printno(printno);
			ticket_returnrecord_service.updateTp_printno(tr);
		}
		Ticket_return ticket_return = ticket_return_service.getTicket_returnById(id);
		List<Map<String, Object>> mxList = ticket_returnrecord_service.getTicket_returnrecordByTfid(id);

	//	request.setAttribute("ticket_return", ticket_return);
	//	request.setAttribute("mxList", mxList);
	 * */
		Shyhb user = SessionUtils.getShshbSession();
		String shbh=user.getShbh();
		JpTpd jptpd=this.baseService.getJpTpdByTpdh(tpdh, shbh);
		List<Map<String, Object>> mxList=jpTpdMxServiceImpl.getJpTpdMxList(tpdh, shbh);
		model.addAttribute("jptpd", jptpd);
		model.addAttribute("mxList", mxList);
		return viewpath("trfd");
	}
	
	
	
	public String checkXcd(ModelMap model, HttpServletRequest request) {
		String tkno = request.getParameter("tkno");
		String userid="";
		String url="";
		String cmd="detr:tn/" + tkno + ",f";
		
		DetrFParam  param=new DetrFParam();
		Map<String, String> map = new HashMap<String, String>();
		param.setUserid(userid);
		param.setUrl(url);
		param.setCommand(cmd);
		String detr ="";
		try {
			detr=IbeService.detrF(param);
		} catch (EtermException e) {
			e.printStackTrace();
			map.put("STATUS", "-1");
			map.put("ERROR", "DETR失败，" + e.getMessage());
			//AsmsRender.renderJson(response, map);
			return null;
		}
		if (StringUtils.isBlank(detr)) {
			map.put("STATUS", "-1");
			map.put("ERROR", "DETR失败，未知错误");
		} else {
			if ((detr.startsWith("DETR") || detr.startsWith("detr"))) {
				if (detr.indexOf("TKTN") == -1) {
					map.put("STATUS", "-1");
					map.put("ERROR", "DETR失败，DETR返回【" + detr + "】");
				} else {
					String strPattern = "[R][P][0-9]{10}";
					Pattern pattern = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE);
					Matcher matcher = pattern.matcher(detr);
					String xcd = "";
					if (matcher.find()) {
						xcd = matcher.group(0);
						xcd = xcd.replace("RP", "");
					}
					if (StringUtils.isBlank(xcd)) {
						map.put("STATUS", "0");
					} else {
						map.put("STATUS", "-1");
						map.put("ERROR", "行程单未作废，行程单号【" + xcd + "】");
					}
				}
				map.put("DETR", detr);
			} else {
				map.put("STATUS", "-1");
				map.put("ERROR", "DETR失败，DETR返回【" + (StringUtils.isBlank(detr) ? "空" : detr) + "】");
				map.put("DETR", detr);
			}
		}
		//AsmsRender.renderJson(response, map);
		return null;
	}
	
	
	public String checkKpzt(ModelMap model, HttpServletRequest request) {
		/*
		Map<String, String> map = new HashMap<String, String>();
		String mxid = request.getParameter("mxid");
		String szdh = request.getParameter("szdh");
		String tkno = request.getParameter("tkno");
		List<Map<String, Object>> tphdList = null;
		try {
		//	tphdList = this.getTphdList(mxid, tmp_service);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("STATUS", "-1");
			map.put("ERROR", "取退票航段失败，" + e.getMessage());
			//AsmsRender.renderJson(response, map);
			return null;
		}
		if (tphdList == null || tphdList.isEmpty()) {
			map.put("STATUS", "-1");
			map.put("ERROR", "取退票航段失败，未知错误");
		} else {

		
			TicketDETR detr = new TicketDETR(szdh + tkno, "http://" + ve_yhb.getJretermip() + ":"+ ve_yhb.getJretermport(), ve_yhb.getBh());
			detr.excute();
			if ("-1".equals(detr.getErrorCode())) {
				map.put("STATUS", "-1");
				map.put("ERROR", detr.getError());
			} else {
				List<JpHd> hdbList = detr.getHdList();
				if (hdbList == null || hdbList.isEmpty()) {
					map.put("STATUS", "-1");
					map.put("ERROR", "DETR未得到机票航程信息");
				} else {
					Iterator<Map<String, Object>> tphdItr = tphdList.iterator();
					while (tphdItr.hasNext()) {
						Map<String, Object> tphdMap = tphdItr.next();
						int sxh = NumberUtils.toInt(VeStr.getValue(tphdMap, "SXH"));
						if (hdbList.size() < sxh) {
							map.put("STATUS", "-1");
							map.put("ERROR", "DETR得到机票航程信息不正确");
							break;
						} else {
							JpHd hdb = hdbList.get(sxh - 1);
							String kpzt = hdb.getKpzt();
							map.put("KPZT", kpzt);
							if (!"OPEN FOR USE".equals(kpzt)) {
								map.put("STATUS", "-2");
								map.put("ERROR", "客票状态为【" + kpzt + "】");
								break;
							}
						}
					}
				}
				if (map.get("STATUS") == null) {
					map.put("STATUS", "0");
				}
				map.put("DETR", detr.getDetr());
			}
		}

		//AsmsRender.renderJson(response, map);
		 */
		return null;
		
	}
	
	/**
	 * 预览TRFD指令
	 * @return
	 */
	@RequestMapping(value = "previewTrfd")
	public String previewTrfd(JpTpdMx jptpdmx, ModelMap model,HttpServletRequest request){

	//	ModelCacheManager mcm = (ModelCacheManager) WebAppUtil.getComponentInstance("modelCacheManager", request);
		String tkno = request.getParameter("tkno");
		String id = request.getParameter("id");
		String cgZdj = request.getParameter("cgZdj");
		String cgJsf = request.getParameter("cgJsf");
		String cgTax = request.getParameter("cgTax");
		String cj_dlf = request.getParameter("cj_dlf");
		String jp_pjhk = request.getParameter("jp_pjhk");
		String tp_cgst = request.getParameter("tp_cgst");
		String sjjsfl = "0";
		String operation =request.getParameter("operation"); // 退票指令
		Shyhb user = SessionUtils.getShshbSession();
		String shbh=user.getShbh();
		jptpdmx.setShbh(shbh);
		String forward="trfd_preview";
		String errormessage="";
		/**
		 * 先检查机票配置
		 */
	    JpPz jppz=jpPzServiceImpl.getJpPzByShbh(shbh);
	    if(jppz == null){
	    	model.addAttribute("error", "请先设置机票配置信息[PID的访问地址和端口号]");
	    	return viewpath(forward);
	    }
		
		/**
		 *  1.取退票明细信息
		 */
	
		try {
			jptpdmx=jpTpdMxServiceImpl.getEntityById(jptpdmx);
		} catch (Exception e) {
			e.printStackTrace();
			errormessage="错误：" + e.getMessage();
		}
		
		if(jptpdmx == null){
			model.addAttribute("error", "取退票信息失败,"+errormessage);
			return viewpath(forward);
		}

		/**
		 *  2.取退票信息
		 */
		JpTpd jptpd = null;
		try {
			jptpd = this.baseService.getJpTpdByTpdh(jptpdmx.getTpdh(), shbh);
		} catch (Exception e) {
			e.printStackTrace();
			errormessage="错误：" + e.getMessage();
		}
		if (jptpd == null) {
			model.addAttribute("error", "取退单信息失败,"+errormessage);
			return viewpath(forward);
		} 
		
		/**
		 *  3.取机票价格信息
		 */
		
		if (!"1".equals(operation)) {// 非自动
			JpJp jp = new JpJp();
			jp.setTkno(tkno);
			try {
				jp = jpJpServiceImpl.getJpByTkno(jp);//;ticket_all_service.getTicket_allById(tkno);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("error", "取机票信息失败，错误：" + e.getMessage());
				return viewpath(forward);
			}
			
			if (jp != null) {
				sjjsfl ="0";// ticket_all.getJs_hxfl();
			}
			if (jp != null && "1".equals(jptpd.getLxkpzt())) {
				// 连续客票全退时，票价为0
				String lxkp = jp.getLxkp();
				if (StringUtils.isNotBlank(lxkp) && lxkp.startsWith(tkno)) {
					cgZdj = "0";
					cgJsf = "0";
					cgTax = "0";
					cj_dlf = "0";
					jp_pjhk = "0";
					tp_cgst = "0";
				}
			}
			cgZdj = StringUtils.isBlank(cgZdj) ? jptpdmx.getCgZdj() + "" : cgZdj;
			cgJsf = StringUtils.isBlank(cgJsf) ? jptpdmx.getCgJsf() + "" : cgJsf;
			cgTax = StringUtils.isBlank(cgTax) ? jptpdmx.getCgTax() + "" : cgTax;
			//cj_dlf = StringUtils.isBlank(cj_dlf) ? jptpdmx.getCj_dlf() + "" : cj_dlf;
			//jp_pjhk = StringUtils.isBlank(jp_pjhk) ? jptpdmx.getJp_pjhk() + "" : jp_pjhk;
			//tp_cgst = StringUtils.isBlank(tp_cgst) ? jptpdmx.getTp_cgst() + "" : tp_cgst;
		}
		
		
		/**
		 * 4. 执行退票预览指令
		 */
	
		TrfdParam param = new TrfdParam();
		if ("1".equals(operation)) { // 自动
			param.setOperationType("AUTO");
			param.setTicketNo(tkno);
		} else if ("2".equals(operation)) {// 半自动
			param.setOperationType("HALFAUTO");
			param.setTicketNo(tkno);
		} else if ("3".equals(operation)) {// 手动
			param.setOperationType("MANUAL");
		} else {
			model.addAttribute("error", "不支持的退票指令类型");
			return viewpath(forward);
		}
		

	    String userid=user.getPidyh();
		String url="http://"+jppz.getPzIp()+":"+jppz.getPzPort();
		//userid="16072718360531";
		param.setUrl(url);
		param.setUserid(userid);
		param.setPreview("1");
		param.setPrinter(getPrintno(jptpdmx.getPrintno(),jptpdmx.getCpOfficeid(),shbh));

		if ("1".equals(jptpd.getGngj())) {
			param.setCountry("D");
		} else {
			param.setCountry("I");
		}
		/*
		CommandAbstract _veTrfd = new VeTrfd();
		_veTrfd.setCommand(command);
		VeTrfdPreviewCommandResult result = null;
		try {
			result = (VeTrfdPreviewCommandResult) _veTrfd.excute();
		} catch (EtermException e) {
			e.printStackTrace();
			request.setAttribute("error", "提取退票确认信息失败，错误：" + e.getMessage());
			return viewpath(forward);
		}
		*/
		
		TrfdPreviewResult result=null;
		try {
			result = (TrfdPreviewResult)IbeService.trfd(param);
		} catch (EtermException e2) {
			e2.printStackTrace();
			request.setAttribute("error", e2.getMessage());
			return viewpath(forward);
		}
		if (result == null) {
			request.setAttribute("error", "提取退票确认信息失败，错误：PID返回NULL");
			return viewpath(forward);
		} 
		/*
		if ("-1".equals(result.getResultXml())) {
			request.setAttribute("error", result.getResultError());
			return viewpath(forward);
		}*/
		// 修改本地数据
		JpTpdMx ntfp = new JpTpdMx();
		ntfp.setId(id);
		ntfp.setHx_trfd_ylnr(result.getContent());
		try {
			jpTpdMxServiceImpl.update(ntfp);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			//mcm.clearCache();
		}

		if (!"1".equals(operation)) {// 非自动
			// 根据系统数据组织数据
			String airlineCode = result.getResult().getAirlineCode();
			if (StringUtils.isBlank(airlineCode)) {
				result.getResult().setAirlineCode(jptpdmx.getTkno().substring(0, 3));
			}

			String tktNumber = result.getResult().getTktNumber();
			if (StringUtils.isBlank(tktNumber)) {
				result.getResult().setTktNumber(tkno);
			}
			//String secondTicketNo = result.getResult().getSecondTicketNo();
			//if (StringUtils.isBlank(secondTicketNo)) {
			//	result.getResult().setSecondTicketNo(StringUtils.substring(tkno, 3));
			//}
			String jp_hclx = jptpdmx.getHclx();
			if ("1".equals(jp_hclx)) {
				// 单程
				result.getResult().setCouponNo("1000");
			} else {
				List<JpHd> tphdList = null;
				try {
					tphdList = jpHdServiceImpl.getJpHdByTpMxId(id, shbh);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (CollectionUtils.isNotEmpty(tphdList)) {
					String one = "0";
					String two = "0";
					String three = "0";
					String four = "0";
					
					for (JpHd jphd : tphdList) {
						switch (jphd.getSxh()) {
						case 1: {
							one = "1";
							break;
						}
						case 2: {
							two = "2";
							break;
						}
						case 3: {
							three = "3";
							break;
						}
						case 4: {
							four = "4";
							break;
						}
						default:
							break;
						}
					}
					result.getResult().setCouponNo(one + two + three + four);
				}
			}

			String passenger = result.getResult().getPassengerName();
			if (StringUtils.isBlank(passenger) || passenger.endsWith("*")) {
				result.getResult().setPassengerName(
						StringUtils.upperCase(ToPinYin.getPingYinString(jptpdmx.getCjr())));
			}

			String deduction = result.getResult().getDeduction();
			if (StringUtils.isBlank(deduction) || NumberUtils.toDouble(deduction) == 0) {
				result.getResult().setDeduction(jptpd.getCgTpf() + "");
			}
			JpTpdMx tfd = new JpTpdMx();// 系统退废单，页面显示价格取自系统
			tfd.setCgZdj(Math.abs(NumberUtils.toLong(cgZdj)));
			tfd.setCgJsf(Math.abs(NumberUtils.toLong(cgJsf)));
			tfd.setCgTax(Math.abs(NumberUtils.toLong(cgTax)));
			//tfd.setJp_pjhk(NumberUtils.toDouble(jp_pjhk));
			//tfd.setBy1(Arith.mul(NumberUtils.toDouble(sjjsfl), 100) + "");// 代理费率
			//tfd.setCj_dlf(NumberUtils.toDouble(cj_dlf));
			
			tfd.setCgTkje(BigDecimal.valueOf(NumberUtils.toDouble(tp_cgst)));
			model.addAttribute("tfd", tfd);
		}
		model.addAttribute("trfd", result);// 航信数据
		return viewpath(forward);
	}
	
	
	/**
	 * 
	 * @param jptpdmx
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "submitTrfd")
	public String submitTrfd(ModelMap model,HttpServletRequest request) {
		
		Shyhb user = SessionUtils.getShshbSession();
		String shbh=user.getShbh();
		String mxid=request.getParameter("mxid");
		String tkno=request.getParameter("tkno");
		String operation=request.getParameter("operation");
		
		JpTpdMx jptpdmx=new JpTpdMx();
		jptpdmx.setShbh(shbh);
		jptpdmx.setId(mxid);
	    jptpdmx = jpTpdMxServiceImpl.getEntityById(jptpdmx);
	    
		if (jptpdmx == null || StringUtils.isBlank(jptpdmx.getId())) {
			return "取退票信息失败，未知错误";
		}
		
		JpTpd  jptpd=this.baseService.getJpTpdByTpdh(jptpdmx.getTpdh(), shbh);
		
	    JpPz jppz=jpPzServiceImpl.getJpPzByShbh(shbh);
	    String userid=user.getPidyh();
		String url="http://"+jppz.getPzIp()+":"+jppz.getPzPort();
		TrfdParam param = new TrfdParam();
		param.setUrl(url);
		param.setUserid(userid);
		param.setPreview("0");
		param.setPrinter(getPrintno(jptpdmx.getPrintno(),jptpdmx.getCpOfficeid(),shbh));
		param.setOfficeId(jptpdmx.getCpOfficeid());
		if("1".equals(operation)){
			param.setOperationType("AUTO");
			param.setTicketNo(tkno);
		}else{
			return "";
		}
		if ("1".equals(jptpd.getGngj())) {
			param.setCountry("D");
		} else {
			param.setCountry("I");
		}
		
		TrfdSubmitResult result=null;
		try {
			result = (TrfdSubmitResult)IbeService.trfd(param);
		} catch (EtermException e) {
			e.printStackTrace();
			return "退票操作失败，错误：" + e.getMessage();
		}
		if (result == null) {
			return "提取退票确认信息失败，错误：PID返回NULL";
		} 
		/*
		VeTrfdCommand cmd = new VeTrfdCommand();
		this.copyToCmd(_trfdForm, cmd);

		CommandAbstract ca = new VeTrfd();

		cmd.setUrl("http://" + ve_yhb.getJretermip() + ":" + ve_yhb.getJretermport());
		cmd.setUserid(ve_yhb.getBh());
		cmd.setPreview("0");
		cmd.setPrinter(ticket_returnrecord.getTp_printno());
		cmd.setOffice(ticket_returnrecord.getTp_officeid());
		if ("1".equals(ticket_returnrecord.getJp_hcglgj())) {
			cmd.setCountry("D");
		} else {
			cmd.setCountry("I");
		}

		ca.setCommand(cmd);

		VeTrfdSubmitCommandResult result = null;
		try {
			result = (VeTrfdSubmitCommandResult) ca.excute();
		} catch (EtermException e) {
			e.printStackTrace();
			request.setAttribute("error", "退票操作失败，错误：" + e.getMessage());
			return mapping.findForward("trfd_success");
		}
		if (result == null) {
			request.setAttribute("error", "退票操作失败，未知错误");
		} else {
			if ("-1".equals(result.getResultXml())) {
				request.setAttribute("error", result.getResultError());
			} else {
				request.setAttribute("trfd", result);

				// 调用修改接口取回退废单信息保存
				String hx_trfd_nr = null;
				cmd = new VeTrfdCommand();
				cmd.setUrl("http://" + ve_yhb.getJretermip() + ":" + ve_yhb.getJretermport());
				cmd.setUserid(ve_yhb.getBh());
				cmd.setPreview("1");
				cmd.setOffice(ticket_returnrecord.getTp_officeid());
				cmd.setTicketNo(_trfdForm.getSzdh() + "-" + _trfdForm.getTkno());
				cmd.setOperationType("MODIFY");
				cmd.setRefundNo(result.getRefundNo());
				if ("1".equals(ticket_returnrecord.getJp_hcglgj())) {
					cmd.setCountry("D");
				} else {
					cmd.setCountry("I");
				}
				ca.setCommand(cmd);
				VeTrfdPreviewCommandResult previewResult = null;
				try {
					previewResult = (VeTrfdPreviewCommandResult) ca.excute();
					hx_trfd_nr = previewResult.getContent();
				} catch (EtermException e) {
					e.printStackTrace();
				}

				// 修改本地数据
				Ticket_returnrecord ntfp = new Ticket_returnrecord();
				ntfp.setId(_trfdForm.getMxid());
				ntfp.setHx_tfzt("1");
				ntfp.setHx_tfdh(result.getRefundNo());
				ntfp.setHx_trfd_nr(hx_trfd_nr);
				ntfp.setHx_tf_datetime(VeDate.getStringDate());
				ntfp.setHx_tf_userid(ve_yhb.getBh());
				try {
					ticket_returnrecord_service.updateTicket_returnrecord(new EventModel(ntfp));
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					mcm.clearCache();
				}
			}
		}
		*/
		return "";
	}
	
	private String getPrintno(String printno, String officeid,String shbh) {
		if (StringUtils.isNotBlank(printno)) {
			return printno;
		}
		JpPz jppz = jpPzServiceImpl.getJpPzByOfficeid(officeid, shbh);
		return jppz == null ? "" : jppz.getPrintno();
	}

	/*
	private void copyToCmd(TrfdForm f, VeTrfdCommand cmd) {
		String operation = f.getOperation();
		if ("1".equals(operation)) {
			cmd.setOperationType("AUTO");
		} else if ("2".equals(operation)) {
			cmd.setOperationType("HALFAUTO");
		} else if ("3".equals(operation)) {
			cmd.setOperationType("MANUAL");
		}
		if ("1".equals(operation)) {
			cmd.setTicketNo(f.getSzdh() + "-" + f.getTkno());
		} else {
			cmd.setTicketNo(StringUtils.trimToEmpty(f.getAirlineCode()) + "-"
					+ StringUtils.trimToEmpty(f.getTicketNo()));
			cmd.setSecondTicketNo(StringUtils.trimToEmpty(f.getSecondTicketNo()));
			cmd.setCheck(StringUtils.trimToEmpty(f.getCheck()));
			cmd.setConjunction(StringUtils.trimToEmpty(f.getConjunctionNo()));
			cmd.setCouponNo(StringUtils.join(f.getCouponNo(), ","));
			cmd.setCrossRefound(StringUtils.trimToEmpty(f.getGrossRefund()));
			cmd.setEt(StringUtils.trimToEmpty(f.getEt()));
			cmd.setDeduction(StringUtils.trimToEmpty(f.getDeduction()));
			//cmd.setCommission(StringUtils.trimToEmpty(f.getCommissionRate()));
			cmd.setCommission(StringUtils.trimToEmpty(f.getCommission()));
			cmd.setCreditcard(StringUtils.trimToEmpty(f.getCreditCard()));

			String[] taxType = f.getTaxType();
			String[] tax = f.getTax();
			int len = taxType.length;
			StringBuffer strTax = null;
			for (int i = 0; i < len; i++) {
				if (StringUtils.isNotBlank(taxType[i])) {
					if (strTax == null) {
						strTax = new StringBuffer();
					} else {
						strTax.append("|");
					}
					strTax.append(StringUtils.trimToEmpty(taxType[i]) + "," + StringUtils.trimToEmpty(tax[i]));
				}
			}
			if (strTax != null) {
				cmd.setTax(strTax.toString());
			}
			cmd.setNetRefound(StringUtils.trimToEmpty(f.getNetRefund()));
			cmd.setMoneyType(StringUtils.trimToEmpty(f.getCurrencyCode()));
			cmd.setPassenger(StringUtils.trimToEmpty(f.getPassengerName()));
			cmd.setFormofPayMent(StringUtils.trimToEmpty(f.getFormOfPayment()));
			cmd.setRemark(StringUtils.trimToEmpty(f.getRemark1()) + "|" + StringUtils.trimToEmpty(f.getRemark2()));
		}
	}
	*/
}
