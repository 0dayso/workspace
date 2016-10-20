package cn.vetech.web.vedsb.xcdgl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.api.createXcd.CreateXcd2Param;
import org.vetech.core.business.pid.api.createXcd.CreateXcd2Parser;
import org.vetech.core.business.pid.api.detrxml.DetrResult;
import org.vetech.core.business.pid.api.detrxml.DetrXmlParam;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.api.pnrpat2.PnrCjr;
import org.vetech.core.business.pid.api.pnrpat2.PnrHd;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.utils.FileOperate;
import org.vetech.core.modules.utils.JsonUtil;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.utils.XmlUtils;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.SmsMbSh;
import cn.vetech.vedsb.common.service.base.SmsServiceImpl;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddYj;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.entity.pzzx.JpXcd;
import cn.vetech.vedsb.jp.entity.xcd.JpXcdTicket;
import cn.vetech.vedsb.jp.entity.xcd.JpXcdTickethd;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpkhddYjServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.pzzx.JpXcdServiceImpl;
import cn.vetech.vedsb.utils.XcdUtils;
import cn.vetech.web.vedsb.SessionUtils;

/**
 * 
 * <行程单打印处理>
 * <功能详细描述>
 * 
 * @author  vetech
 * @version  [版本号, 2016-3-31]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
@Controller
public class PrintXcdController extends MBaseControl<JpKhddYj, JpkhddYjServiceImpl>{

	@Autowired
	private JpKhddServiceImpl jpkhddService;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired
	private JpJpServiceImpl jpJpServiceImpl;
	@Autowired
	private SmsServiceImpl smsService;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	
	@Autowired
	private JpXcdServiceImpl jpXcdServiceImpl;
	
	
	@Override
	public void updateInitEntity(JpKhddYj t) {
	}
	
	@Override
	public void insertInitEntity(JpKhddYj t) {
	}
	
	/**
	 * <打印快递单时，预览打印>
	 * 
	 * @param model
	 * @param request
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "viewprint")
	public String ComeToViewprint(ModelMap model,HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		String ddbh=request.getParameter("ddbh");
		String ddbhs=request.getParameter("ddbhs");
		String dytype=request.getParameter("dytype");
		String mbid=request.getParameter("mbid");
		JpKhddYj yjd=null;
		
		String dycs="0";
		List<JpKhdd> khddList=new ArrayList<JpKhdd>();
		try {
			JpKhdd khdd=null;
			//如果是打印快递单
			if(StringUtils.isNotBlank(ddbh)){
				yjd=this.baseService.queryJpKhddYjByDdbh(ddbh);
				khdd=new JpKhdd();
				khdd.setDdbh(ddbh);
				khdd.setShbh(user.getShbh());
				khdd=jpkhddService.getEntityById(khdd);
				
			}
			if (yjd != null) {
				dycs=yjd.getDycs()==0?"0":""+yjd.getDycs();
			}
			if(StringUtils.isBlank(mbid)){
				mbid = "1001700";
			}
			model.addAttribute("mbid",mbid);
			model.addAttribute("dycs", dycs);
			//如果是批量打印或者是合并打印
			if(StringUtils.isNotBlank(ddbhs)){
				String[] ddbharr = ddbhs.split(",");
				for (int i = 0; i < ddbharr.length; i++) {
					String one = ddbharr[i];
					JpKhdd khdds=new JpKhdd();
					if (StringUtils.isNotBlank(one)) {
						//合并打印时，只将第一条记录存放到list，并记录每个ddbh
						if("2".equals(dytype)) {
							if(i == 0) {
								khdds.setDdbh(one);
								khdds=jpkhddService.getEntityById(khdds);
								khddList.add(khdds);
							}
						}else if("1".equals(dytype)){//批量打印
							khdds.setDdbh(one);
							khdds=jpkhddService.getEntityById(khdds);
							khddList.add(khdds);
						}
					}
				}
			}else{
				khddList.add(khdd);
			}
		} catch (Exception e) {
			logger.error("进入打印页面失败", e);
			e.printStackTrace();
			return this.addError("进入打印页面失败" + e.getMessage(), e, "ComeToViewprint", model);
		}
		model.addAttribute("size", khddList.size());
		model.addAttribute("khddList", khddList);
		return "/kdgs/print_main";
	}
	
	/**
	 * <直接打印，做两个操作一个是插入邮寄表记录，一个是修改订单的邮寄状态>
	 * 
	 * @param jpKhddYj
	 * @param model
	 * @param request
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "addPrintRecord")
	@ResponseBody
	public int printToAdd(@ModelAttribute()JpKhddYj jpKhddYj,Model model,HttpServletRequest request){
		//如果是合并打印
		String ddbhs=request.getParameter("ddbhs");
		String mbid=request.getParameter("mbid");
		String flag=request.getParameter("flag");//用来区分是从待打印,还是待邮寄出来的动作.
		jpKhddYj.setDycs(jpKhddYj.getDycs()+1);
		jpKhddYj.setCg_yjf(Double.valueOf("0"));
		if(jpKhddYj.getYjzt().equals("0")){//把邮寄状态改为已打印（待邮寄）
			jpKhddYj.setYjzt("1");
		}
		jpKhddYj.setKdlx(mbid);
		jpKhddYj.setDysj(new Date());
		int result=0;
		try {
			result=this.baseService.dochangeYjAndDd(jpKhddYj,ddbhs,flag);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("打印邮寄单错误", e);
		}
		return result;
	}
	
	/**
	 * <点击批量短信时,弹出对短信设置的页面>
	 * 
	 * @param model
	 * @param request
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "batchMessage")
	public String batchMessage(Model model,HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		String ddbhs=request.getParameter("ddbhs");
		List<Map<String,Object>> printList=new ArrayList<Map<String, Object>>();
		StringBuffer ids=new StringBuffer();
		StringBuffer mobles=new StringBuffer();
		if(StringUtils.isNotBlank(ddbhs)){
			String arr[] = ddbhs.split(",");
			for(int i=0;i<arr.length;i++){
				Map<String,Object> yjdDd=jpkhddService.queryYjdDdByDdbh(arr[i]);
				printList.add(yjdDd);
				if(i==0){
					ids.append(yjdDd.get("YJID"));
					mobles.append(yjdDd.get("NXDH"));
				}else{
					ids.append(","+yjdDd.get("YJID"));
					mobles.append(","+yjdDd.get("NXDH"));
				}
			}
		}
		String lx="1001803";//应用场景是邮寄行程单的短信通知
		List<SmsMbSh> smsmbshlist=smsService.querySmsmbshByXcd(user.getShbh(),lx);
		model.addAttribute("ids", ids.toString());
		model.addAttribute("ddbhs", ddbhs);
		model.addAttribute("mobiles", mobles);
		model.addAttribute("mblist", smsmbshlist);
		return "/xcdgl/yjxcd/batchmessage";
	}
	
	/**
	 * 打印调试
	 */
	@RequestMapping(value = "printXcdTest")
	public void printXcdTest(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String gngj = StringUtils.trimToEmpty(request.getParameter("gngj"));
		String hcTemp="7845582937711_4351061012.txt";
		if("0".equals(gngj)){
			hcTemp="7845582937711_4351061014.txt";
		}
		String path = PrintXcdController.class.getResource("/").getPath();
		String ml = getUpperPath(getUpperPath(getUpperPath(path))) +"/static/js/xcdprint/";

		String info = FileOperate.readTxt(ml+hcTemp, "UTF-8");
		JpXcdTicket ticket=ParseXcd.parseXcd(info);
		List<JpXcdTickethd> hdList=ticket.getHdlist();
		for(int i=0;i<4-hdList.size();i++){
		  hdList.add(new JpXcdTickethd());
		}
		
		List<JpXcdTicket> list = new ArrayList<JpXcdTicket>();
		list.add(ticket);
		String json = JsonUtil.listToJson(list);
		response.getWriter().print(json);
	}
	
	
	/**
	 * 1.打印数据格式化
	 * 2.保存打印记录
	 */
	@RequestMapping(value = "printXcd")
	public String printXcd(JpXcdTicket jpXcdTicket, @RequestParam(value = "tkno") String tkno,Model model,HttpServletRequest request) throws Exception {
		try{
			Shyhb user = SessionUtils.getShshbSession();
			jpXcdTicket = jpXcdServiceImpl.getJpXcdTicket(tkno);
			String ip = request.getParameter("ip");
			JpPz jpPz = new JpPz();
			jpPz = jpPzServiceImpl.getJpPzByShbh(user.getShbh());
			if (StringUtils.isBlank(ip)) {
				ip = jpPz.getServerAddr();
			}
			setTicketByParameter(request, jpXcdTicket);
			XcdUtils.doSavePrintXcdInfo(jpXcdTicket, user,ip);
			List<JpXcdTicket> list = new ArrayList<JpXcdTicket>();
			list.add(jpXcdTicket);
			String json = JsonUtil.listToJson(list);
			//AsmsRender.renderJson(response, json);
		}catch (Exception e) {
			e.printStackTrace();
			//response.getWriter().write("操作失败,原因:系统整合打印数据出现错误！");
			//AsmsRender.renderText(response, "操作失败,原因:系统整合打印数据出现错误！");
		}
		return null;
	}
	
	@RequestMapping(value = "enterPrintPage1")
	public String enterPrintPage1(Model model,HttpServletRequest request){
		return viewpath("printxcd_main");
	}
	
	/**
	 * 行程单取值规则：
	 * 1） ASMS后台，一律是按领用部门取，没取到，根据部门设置那里控制是否可以修改，与出票类型无关。 
	 * 2） 分销平台，开启了子账户领用的，则只能显示领用到本子账号的。没有开启了子账户领用的，则显示领用到本分销部门的行程单号。
	 */
	@RequestMapping(value = "enterPrintPage")
	public String enterPrintPage(Model model, HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		String tkno = request.getParameter("tkno");
		model.addAttribute("tk_pnr_no", tkno);//提取传得值设置后放到页面复原
		String ifgq ="";
		JpJp jpJp = new JpJp();
		jpJp.setShbh(user.getShbh());
		jpJp.setTkno(tkno);
		jpJp = jpJpServiceImpl.getJpByTkno(jpJp);
		try{
			ifgq = showXcdinfo(jpJp, model, request);
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "非常抱歉!"+e.getMessage());
		}
	
		Map<String, Object> xcdListMap=getXcdListMap(1,100, null);
		request.setAttribute("xcdlist", xcdListMap.get("xcdlist"));
		request.setAttribute("ifgq", ifgq);
		request.setAttribute("xcdcnt", xcdListMap.get("xcdcnt"));
		request.setAttribute("lystartxcdh", xcdListMap.get("lystartxcdh"));
		request.setAttribute("xcdddqx","0");
		return viewpath("printxcd_main");
	}
	
	/**
	 * 
	 * @param ddbh
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "enterXcdListPage")
	public String enterXcdListPage(Model model, HttpServletRequest request){
		String ddbh = StringUtils.trimToEmpty(request.getParameter("ddbh"));
		String tkno = StringUtils.trimToEmpty(request.getParameter("tkno"));
		Shyhb user = SessionUtils.getShshbSession();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shbh", user.getShbh());
		map.put("ddbh", ddbh);
		List<Map<String, Object>> cjrlist = jpJpServiceImpl.getXcdListByDdbh(map);
		//按PNR提取提取机票信息
		List<Map<String, Object>> jpjp_list = jpJpServiceImpl.queryJpListByXspnr(map);
		String ifgq ="";
		if(StringUtils.isNotBlank(tkno)){
			JpJp jpJp = new JpJp();
			jpJp.setShbh(user.getShbh());
			if(tkno.length() < 13){//例如：7814552588565
				request.setAttribute("error", "您输入的票号小于13位!");
			}
			if(tkno.length() == 13){//例如：7814552588565,但是数据库的票号存值是781-4552588565
				tkno = StringUtils.substring(tkno, 0, 3) + "-" +StringUtils.substring(tkno, 3, 13);
			}
			jpJp.setTkno(tkno);
			jpJp = jpJpServiceImpl.getJpByTkno(jpJp);
			try{
				ifgq = showXcdinfo(jpJp, model, request);
			}catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error", "非常抱歉!"+e.getMessage());
			}
		}
		
		
		String pztype = "720201";//机票产品
		JpXcd jpxcd =null;
		try {
			jpxcd = jpXcdServiceImpl.getBeanByXcdNo(user.getShbh(),pztype);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("jpxcd", jpxcd);
		model.addAttribute("jpjp_list", jpjp_list);
		model.addAttribute("cjrList", cjrlist);
		model.addAttribute("ifgq", ifgq);
		
		
		
		return viewpath("createXcd");
	}
	
	/**
	 * 获取部门或分销子账户领用的行程单和行程单总数
	 * 如果传了xcdh不为空，则查询行程单数量时会将此参数带入条件，查看是否有领用此行程单
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getXcdListMap(int start,int end,String xcdh){
		/*
		StringBuffer xcdsql = new StringBuffer(" SELECT * FROM (SELECT A.*, ROWNUM as RN FROM ( ");// 获得行程单记录集合
		String lyqsxcdhsql="";//领用的起始行程单号sql
		StringBuffer xcdcountsql = new StringBuffer();// 获得行程单记录集合
		List<Object> paramxcdList=new ArrayList<Object>();
		List<Object> paramxcdcntList=new ArrayList<Object>();
		List<Object> paramlyxcdstartList=new ArrayList<Object>();*/
//		
//		xcdsql.append("SELECT xcd_no,rk_compid from Ticket_xcd where pzzt = '2' and Out_deptid=? ORDER BY xcd_no");
//		paramxcdList.add(ve_yhb.getDeptid());
//		xcdcountsql.append("SELECT count(1) from Ticket_xcd where pzzt = '2' and Out_deptid=? ");
//		paramxcdcntList.add(ve_yhb.getDeptid());
//		lyqsxcdhsql="select * from (select xcd_no from Ticket_xcd where pzzt = '2' and Out_deptid= ? ORDER BY xcd_no) where rownum=1  ";
//		paramlyxcdstartList.add(ve_yhb.getDeptid());
//		
//		xcdsql.append(" ) A WHERE ROWNUM <= ?) WHERE RN >= ?");
//		paramxcdList.add(end);
//		paramxcdList.add(start);
//		
		List<Object> xcdList=new ArrayList<Object>();
		BigDecimal xcdcnt=new BigDecimal(0);
		String lystartxcdh="";
//		try {
//			if(StringUtils.isNotBlank(xcdh)){//只查询行程单号是否已领用
//				xcdcountsql.append(" and xcd_no=? ");
//				paramxcdcntList.add(xcdh);
//			}else{
//				xcdList = UtilComp.extractList(tmp_service.getJdbcTempSource().getDataSource(), xcdsql.toString(), paramxcdList, null);				
//			}
//			xcdcnt=(BigDecimal)UtilComp.OneValue(tmp_service.getJdbcTempSource().getDataSource(), xcdcountsql.toString(), paramxcdcntList);
//			lystartxcdh=(String)UtilComp.OneValue(tmp_service.getJdbcTempSource().getDataSource(), lyqsxcdhsql, paramlyxcdstartList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("xcdlist", xcdList);
		map.put("xcdcnt", xcdcnt);
		map.put("lystartxcdh", lystartxcdh);
		return map;
	}
	
	/**  
	 * 	 回车信息提取
	 * 1.票号提取：
	 *   从机票表获取票号列表信息和行程单信息
	 *   获取不到时，通过DETR获取票号列表信息和行程单信息
	 * 2.PNR提取：
	 * 	 从机票表获取票号列表信息和行程单信息
	 *   获取不到时，通过RT获取票号列表信息和行程单信息，已出票的乘机人，行程单状态为??
	 */
	@SuppressWarnings("unchecked")
	private String showXcdinfo(JpJp jpJp, Model model, HttpServletRequest request) throws Exception {
		String tqtype = "1"; // 1 票号提取 2 PNR提取
		String tkno = jpJp.getTkno();
		if(StringUtils.isBlank(tkno)){
			return null;
		}
		//票号列表
		List<Map<String, Object>> tknoList=new ArrayList<Map<String,Object>>();
		Map<String, JpXcdTicket> xcdMap=null;
		String ddbh="";
		String ifgq_str = "";
		if("1".equals(tqtype)){//票号提取
			String [] tknos=getTknos(tkno);
			for(String tknoTemp : tknos){
				if(tknoTemp.length()==13){
					tknoTemp = tknoTemp.substring(3);
				}
				//查询是否有改签费用
//				Double gqfy = getGqfy(tmp_service, "tk", tknoTemp);
//				request.setAttribute("gqfy", gqfy);

				ddbh=jpJp.getDdbh();
				Map<String, Object> map=this.getKhddInfoByddbh(ddbh);
				JpKhdd kh_khdd =(JpKhdd)map.get("khdd");
				List<JpKhddCjr> cjrList=(List<JpKhddCjr>)map.get("cjrlist");
				List<JpKhddHd> hdList=(List<JpKhddHd>)map.get("hdlist");
				request.setAttribute("sfcp", "Y");
				try{
					xcdMap=XcdUtils.convertKhddToJpXcdTicket(request, kh_khdd, cjrList, hdList,"1");
				}catch (Exception e) {
					throw new Exception("获取行程单信息出错："+e.getMessage());
				}
			}
		}
		if(xcdMap != null){
			model.addAttribute("xcdinfo", xcdMap.get(jpJp.getTkno()));
		}
		model.addAttribute("tknoList", tknoList);
		return ifgq_str;
	}
	
	/**
	 * 1.遍历票号
	 * 2.DETR 
	 * 3.解析DETR封装成票号列表和行程单信息列表
	 */
	private Map<String, Object> getDetrTiketList(HttpServletRequest request, String[] tknos) throws Exception{
		Shyhb yhb =SessionUtils.getShshbSession();
		//调用XEPNR接口
		JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(yhb.getShbh());
		//票号列表集合
		List<Map<String, Object>> tknoList=new ArrayList<Map<String,Object>>();
		List<JpXcdTicket> xcdinfoList=new ArrayList<JpXcdTicket>();
		if(tknos!=null&&tknos.length>0){
			for(String tkno:tknos){
				DetrXmlParam detrXmlParam = new DetrXmlParam();
				detrXmlParam.setShbh(jpPz.getShbh());
				detrXmlParam.setUserid(yhb.getPidyh());
				detrXmlParam.setPassword(yhb.getMm());
				detrXmlParam.setUrl(jpPz.getPzIp()+":"+jpPz.getPzPort());
				detrXmlParam.setOfficeId(jpPz.getOfficeid());
				detrXmlParam.setTkno(tkno);
				DetrResult detrResult=IbeService.detrXml(detrXmlParam);
				//将RT信息封装到JpXcdTicket
			//	Map<String, Object> mapTemp=getTiketlistByCjrList(pnr,request,"3");
			//	List<Map<String, Object>> tknoListTemp=(List<Map<String, Object>>)mapTemp.get("tknoList");
			//	List<JpXcdTicket> xcdinfoListTemp=(List<JpXcdTicket>)mapTemp.get("xcdinfoList");
			//	if(tknoListTemp!=null&&tknoListTemp.size()>0){
			//		tknoList.addAll(tknoListTemp);
			//	}
			//	if(xcdinfoListTemp!=null&&xcdinfoListTemp.size()>0){
			//		xcdinfoList.addAll(xcdinfoListTemp);
			//	}
			}
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("tknoList", tknoList);
		map.put("xcdinfoList", xcdinfoList);
		return map;
	}
	
	/**
	 * 根据PNR对象获取票号集合和行程单信息集合
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getTiketlistByCjrList(Pnr pnr,HttpServletRequest request,String datafrom) throws Exception{
		List<Map<String, Object>> tknoList=new ArrayList<Map<String,Object>>();
		String gngj=pnr.getGngj();
		if(StringUtils.isBlank(gngj)){
			String segmentStr="";
			List<PnrHd> hdblist=pnr.getHdblist();
			for (PnrHd hdb : hdblist) {
				segmentStr+=hdb.getCfcity()+hdb.getDdcity();
			}
			String gngjSql="select f_gngj('"+segmentStr+"') from dual";
			gngj="";//TODO
		}
		List<PnrCjr> cjrList = pnr.getCjrlist();
		try{
			if(cjrList!=null){
				for(PnrCjr cjr : cjrList){
					Set<String> set=cjr.getTknoSet();
					if(set!=null){//已出票的 有票号
						Iterator<String> iter=set.iterator();	
						while(iter.hasNext()){
							String tkno=iter.next();
							if(StringUtils.isNotBlank(tkno)&&tkno.length()==13){
								Map<String, Object> map=new HashMap<String, Object>();
								String szdm=tkno.substring(0,3);
								String tknoTemp=tkno.substring(3);
								map.put("SZDH", szdm);
								map.put("TKNO", tknoTemp);
								map.put("JP_CJR", cjr.getCjr());
//								String xcdh=cjr.getXcdh();
//								//获取行程单状态
//								Map<String,String> xcdStatemap=getTknoXcdState(request, tkno);
//								String xcdzt=xcdStatemap.get("xcdzt");
//								if("3".equals(xcdzt)){
//									xcdh=xcdStatemap.get("xcdh");
//								}
//								map.put("XCDH", xcdh);
//								map.put("XCDZT",xcdzt);
								
								map.put("PRINT_NUM", "0");
								map.put("PNRZT", pnr.getPnr_zt());
								map.put("JPPZZT", "3");
								map.put("JP_HCGLGJ", gngj);
								tknoList.add(map);
							}
						}
					}
				}
				if(CollectionUtils.isEmpty(tknoList)){//未出票的 遍历乘机人
					for(PnrCjr cjr : cjrList){
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("SZDH", "");
						map.put("TKNO", "");
						map.put("JP_CJR", cjr.getCjr());
						map.put("XCDH", "");
						map.put("XCDZT", "3");
						map.put("PRINT_NUM", "0");
						map.put("PNRZT", pnr.getPnr_zt());
						map.put("JPPZZT", "3");
						map.put("JP_HCGLGJ", gngj);
//						map.put("ID", cjr.getId());
						tknoList.add(map);
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("获取票号列表出错："+e.getMessage());
		}
		
		//将PNR信息封装成行程单信息
		List<PnrHd> hdList=pnr.getHdblist();
		List<JpXcdTicket> xcdinfoList=null;
		try{
//			xcdinfoList=XcdUtils.convertKhddToJpXcdTicket(request, pnr, cjrList, hdList,datafrom);
		}catch (Exception e) {
			throw new Exception("获取行程单信息出错了："+e.getMessage());
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("tknoList", tknoList);
		map.put("xcdinfoList", xcdinfoList);
		return map;
	}
	
	/**
	 * 根据订单编号获取订单相关信息
	 */
	public Map<String, Object> getKhddInfoByddbh(String ddbh)  {
		Map<String, Object> map=new HashMap<String, Object>();
		Shyhb shyhb = SessionUtils.getShshbSession();
		String shbh = shyhb.getShbh();
		try {
			JpKhdd jp_khdd = new JpKhdd();
			jp_khdd.setShbh(shbh);
			jp_khdd.setDdbh(ddbh);
			jp_khdd = jpkhddService.getEntityById(jp_khdd);
			List<JpKhddCjr> jpKhddCjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, shbh);
			List<JpKhddHd> jpKhddHd = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
			map.put("khdd", jp_khdd);
			map.put("cjrlist", jpKhddCjrList);
			map.put("hdlist", jpKhddHd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("根据订单编号取订单信息报错", e);
		}
		return map;
	}
	
	/**
	 * 根据票号或pnr获取改签费用
	 * @param type 为 tk 或  pnr 字符串
	 * @param tk_or_pnr 为票号或pnr
	 * @return Double
	 * */
	private Double getGqfy(String type, String tk_or_pnr){
		BigDecimal gqfy = null;
		try {
			StringBuffer gqSql = new StringBuffer("select sum(t.gqfy) gqfy from t_gqsqb t, t_gqsqb_mx m where t.gqdh = m.gqdh and (t.gqzt='4' or t.gqzt='5' or t.gqzt='6' or t.gqzt='9') ");
			List<String> gqList = new ArrayList<String>();
			if("pnr".equals(type)){
				gqSql.append("and m.o_pnr = ? ");
			}else{
				gqSql.append("and m.n_tkno = ? ");
			}
			gqList.add(tk_or_pnr);
//			gqfy = (BigDecimal)UtilComp.OneValue(tmp_service.getJdbcTempSource().getDataSource(), gqSql.toString(), gqList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(gqfy == null ){//改签费用
			return null;
		}else{	
			return gqfy.doubleValue();
		}
	}
	
	//获取票号数组
	private String[] getTknos(String tkno) throws Exception{
		tkno=tkno.replace("-", "");
		String [] tknos=new String[2];
		if(tkno.length()>=10&&tkno.indexOf("/")>0){//连续客票提取 格式eg:7815582937704/05
			tknos[0]=tkno.substring(0,tkno.indexOf("/"));
			tknos[1]=tkno.substring(0,tkno.indexOf("/")-2)+tkno.substring(tkno.indexOf("/")+1);
		}else if(tkno.length()==13||tkno.length()==10){
			tknos=new String[1];
			tknos[0]=tkno;
		}else{
			throw new Exception("请确认票号是否正确!");
		}
		return tknos;
	}
	
	/**
	 * 创建行程单新接口
	 * 1.创建行程单
	 * 2.保存创建返回数据
	 * 3.更新相关表
	 */
	@RequestMapping(value = "createXcd2", method = RequestMethod.POST)
	public String createXcd2(@RequestParam(value = "tkno") String tkno,
			@RequestParam(value = "xcdh") String xcdh,
			@RequestParam(value = "gngj") String gngj,
			@RequestParam(value = "pj_zdj") String pj_zdj, Model model,
			HttpServletRequest request) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();
		String status = "";
		if (StringUtils.isBlank(tkno) && StringUtils.isBlank(xcdh)) {
			status = "创建失败！票号或行程单号为空";
			return status;
		}
		//xcdh="4759118796";
		//user.setPidyh("16080216125937");
		//user.setMm("a123456");
		JpPz jppz = jpPzServiceImpl.getJpPzByShbh(user.getShbh());
		if (jppz == null) {
			status = "PID未配置";
			return status;
		}
		
		Map<String,String> m=new HashMap<String, String>();
		if ("0".equals(gngj)) {// 国际
			m=createXcd2_Gj(pj_zdj, tkno, xcdh, jppz, user);
		} else {
			m=createXcd2_Gn(tkno, xcdh, jppz, user);
		}
		status=VeStr.getValue(m, status);
		if ("OK".equals(status)) {
			XcdUtils xcdUtils = new XcdUtils();
			// 保存创建行程单接口原始返回数据
			xcdUtils.doSaveCreateXcdInfo(VeStr.getValue(m, "result"), tkno, xcdh);
			// 创建行程单后更新相关数据表
			xcdUtils.createxcd_update(user, tkno, xcdh);
		}
		return status;
	}
	
	/**
	 * 
	 * @param pj_zdj
	 * @param tkno
	 * @param xcdh
	 * @param jppz
	 * @param user
	 * @return
	 */
	private Map<String,String> createXcd2_Gn(String tkno,String xcdh,JpPz jppz,Shyhb user){
		
		CreateXcd2Param cxd2Param = new CreateXcd2Param();
		cxd2Param.setShbh(user.getShbh());
		cxd2Param.setUserid(user.getPidyh());
		cxd2Param.setPassword(user.getMm());
		cxd2Param.setTkno(tkno);
		cxd2Param.setXcdno(xcdh);
		cxd2Param.setUrl("http://"+jppz.getPzIp()+":"+jppz.getPzPort());
		cxd2Param.setOfficeId(jppz.getOfficeid());
		String status="";
		Map<String,String> m=new HashMap<String, String>();
		try {
			CreateXcd2Parser createXcd2Parser = IbeService.createXcd2(cxd2Param);
			if("S".equals(createXcd2Parser.getState())){//创建成功
				status="OK";
				m.put("result",createXcd2Parser.getData());
			}else{
			    status=createXcd2Parser.getErrormassage();
				if(StringUtils.isNotBlank(status)){
					if(status.indexOf("该票号一年前已使用")>=0){
						status="行程单号:"+xcdh+"已经被使用";
					}
					if(status.indexOf("HAVING PRINTED!NUMBER")>=0){
						
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			status=e.getMessage();
		}
		m.put("status",status);
		return m;
	}
	/**
	 * 
	 * @param pj_zdj
	 * @param tkno
	 * @param xcdh
	 * @param jppz
	 * @param user
	 * @return
	 */
	private Map<String,String> createXcd2_Gj(String pj_zdj,String tkno,String xcdh,JpPz jppz,Shyhb user){
		String status="";
		Map<String,String> m=new HashMap<String, String>();
		//String pj_zdj=Param.getString(request, "pj_zdj");
		if(StringUtils.isBlank(pj_zdj)||!pj_zdj.endsWith(".00")){
			status="票价SCNY项格式不对,eg:980.00";
			m.put("status", status);
			return m;
		}
		try {
			/**
			 * 该指令用来处理更国际行程单创建、作废以及获取SCNY信息相关的操作，传入参数格式是：
				<INPUT>
				<COMMAND>VEGJXCD</COMMAND>
				<PARA>
				<USER>用户名</USER> 不能为空，根据该用户的PRINV或PRIINV指令权限执行指令。
				<OFFICE>office号</ OFFICE> 可以为空，非空的话，将在该office的配置上执行指令。
				<XCDH>行程单号,当指令类别为CREATE和VOID时，非空；否则为空</ XCDH>
				<TICKETNO>票号，当指令类别为CREATE和VOID时，非空；当指令类别为SCNY是，可以为空，但此时PNR编码不能为空</TICKETNO> 
				<RETURNPLAINTEXT>是否返回明码数据</RETURNPLAINTEXT> 
				<TEST>是否是测试</TEST > 
				<SCNY>SCNY数据，当指令列别为CREATE时，非空；否则为空</SCNY> 	
				<PNRNO>PNR编码,只有当指令类别为SCNY是，才有效，但如果票号非空，那么次参数将被忽略</PNRNO> 
				<CMDCATALOG>指令类别，非空，为CREATE、VOID、SCNY三者之一；CREATE表示创建行程单、VOID表示作废行程单、SCNY表示根据票号或PNR编码获取SCNY数据</CMDCATALOG> 	
				</PARA>
				</INPUT>
			 */
			String url="http://"+jppz.getPzIp()+":"+jppz.getPzPort();
			WebEtermService wes=new WebEtermService(url);
			StringBuffer xml=new StringBuffer();
			xml.append("<INPUT>");
			xml.append(XmlUtils.xmlnode("COMMAND", "VEGJXCD"));
			xml.append("<PARA>");
			xml.append(XmlUtils.xmlnode("USER", jppz.getShbh()));
			xml.append(XmlUtils.xmlnode("OFFICE", jppz.getOfficeid()));
			xml.append(XmlUtils.xmlnode("XCDH", xcdh));
			xml.append(XmlUtils.xmlnode("TICKETNO", tkno));
			xml.append(XmlUtils.xmlnode("RETURNPLAINTEXT", "1"));
			xml.append(XmlUtils.xmlnode("TEST", "0"));
			xml.append(XmlUtils.xmlnode("SCNY", pj_zdj));
			xml.append(XmlUtils.xmlnode("PNRNO", ""));
			xml.append(XmlUtils.xmlnode("CMDCATALOG", "CREATE"));
			xml.append("</PARA>");
			xml.append("</INPUT>");
			
			System.out.println(xml);
			String retxml=wes.generalCmdProcess(xml.toString());
			//<Response><Flag>E</Flag><ErrorReason>VE:任务超时!</ErrorReason></Response>
			Document document= DocumentHelper.parseText(retxml);
			Element r = document.getRootElement();
			String flag = r.elementText("Flag");
			if("S".equals(flag)){
				status="OK";
				m.put("result", retxml);
			}else{
				status = StringUtils.trimToEmpty(r.elementText("Flag"));
			}
		} catch (EtermException e) {
			e.printStackTrace();
			status=e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			status=e.getMessage();
		}
		m.put("status", status);
		return m;
	}
	/**
	 * 将页面信息收集格式化
	 */
	private void setTicketByParameter(HttpServletRequest request,JpXcdTicket ticket){
		String realtkno = request.getParameter("realtkno");
		String tkno = request.getParameter("tkno");
		String datafrom = request.getParameter("datafrom");
		String lxkp=tkno;
		String xcdh = request.getParameter("xcdh");
		String lkxm = request.getParameter("lkxm");
		String gp_no = request.getParameter("gp_no");
		String cplx = request.getParameter("cplx");
		String zjhm = request.getParameter("zjhm");
		String qz = request.getParameter("qz");
		String pnr_no = request.getParameter("pnr_no");
		String pj_zdj = request.getParameter("pj_zdj");
		String pj_jsf = request.getParameter("pj_jsf");
		String pj_tax = request.getParameter("pj_tax");
		String pj_qt = request.getParameter("pj_qt");
		String pj_hj = request.getParameter("pj_hj");
		String yzm = request.getParameter("yzm");
		String office = request.getParameter("office");
		String dwdh = request.getParameter("dwdh");
		String tkdw = request.getParameter("tkdw");
		String tkrq = request.getParameter("tkrq");
		String tsxx = request.getParameter("tsxx");
		String pj_bx = request.getParameter("pj_bx");
		//符号
		String pj_fh = request.getParameter("pj_fh");
		String jsf_fh = request.getParameter("jsf_fh");
		String tax_fh = request.getParameter("tax_fh");
		String qt_fh = request.getParameter("qt_fh");
		String hj_fh = request.getParameter("hj_fh");
		
		ticket.setDatafrom(datafrom);
		ticket.setRealtkno(realtkno);
		ticket.setTkno(tkno);
		ticket.setLxkp(lxkp);
		ticket.setXcdh(xcdh);
		ticket.setLkxm(lkxm);
		ticket.setZjhm(zjhm);
		ticket.setQz(qz);
		ticket.setPnrNo(pnr_no);
		ticket.setPjZdj(pj_zdj);
		ticket.setPjJsf(pj_jsf);
		ticket.setPjTax(pj_tax);
		ticket.setPjQt(pj_qt);
		ticket.setPjHj(pj_hj); 
		ticket.setYzm(yzm);
		ticket.setOffice(office);
		ticket.setDwdh(dwdh);
		ticket.setTkdw(tkdw);
		ticket.setTkrq(tkrq);
		ticket.setTsxx(tsxx);
		ticket.setPjBx(pj_bx);
		
		ticket.setPj_fh(pj_fh);
		ticket.setJsf_fh(jsf_fh);
		ticket.setTax_fh(tax_fh);
		ticket.setQt_fh(qt_fh);
		ticket.setHj_fh(hj_fh);
		ticket.setGp_no(gp_no);
		ticket.setCplx(cplx);
		
		List<JpXcdTickethd> hdList=new ArrayList<JpXcdTickethd>();
		String [] jcmcs=request.getParameterValues("jcmc");
		String [] cfhzls=request.getParameterValues("cfhzl");
		String [] ddhzls=request.getParameterValues("ddhzl");
		String [] hkgsjcs=request.getParameterValues("hkgsjc");
		String [] hbhs=request.getParameterValues("hbh");
		String [] zwdjs=request.getParameterValues("zwdj");
		String [] cfrqs=request.getParameterValues("cfrq");
		String [] cfsjs=request.getParameterValues("cfsj");
		String [] kpjbs=request.getParameterValues("kpjb");
		String [] yxrqs=request.getParameterValues("yxrq");
		String [] jzrqs=request.getParameterValues("jzrq");
		String [] mfxls=request.getParameterValues("mfxl");
		for(int i=0;i<jcmcs.length;i++){
			JpXcdTickethd hd=new JpXcdTickethd();
			hd.setJcmc(getStr(jcmcs[i]));
			if(i<4){
				hd.setCfhzl(getStr(cfhzls[i]));
				hd.setDdhzl(getStr(ddhzls[i]));
				hd.setHkgsjc(getStr(hkgsjcs[i]));
				hd.setHbh(getStr(hbhs[i]));
				hd.setZwdj(getStr(zwdjs[i]));
				hd.setCfrq(getStr(cfrqs[i]));
				hd.setCfsj(getStr(cfsjs[i]));
				hd.setKpjb(getStr(kpjbs[i]));
				hd.setYxrq(getStr(yxrqs[i]));
				hd.setJzrq(getStr(jzrqs[i]));
				hd.setMfxl(getStr(mfxls[i]));
			}
			hdList.add(hd);
		}
		ticket.setHdlist(hdList);
	}
	
	private String getStr(String str){
	    str =  StringUtils.trimToEmpty(str);
	    if(str.equalsIgnoreCase("null")){
	        str="";
	    }
	    return str;
	}
	
	
	@RequestMapping(value = "enterFontTestPage")
	public String enterFontTestPage(Model model,HttpServletRequest request){
		return viewpath("fontTest");
	}
	
	@RequestMapping(value = "enterPrintSmPage")
	public String enterPrintSmPage(Model model,HttpServletRequest request){
		return viewpath("instructions");
	}
	
	public static String getUpperPath(String currentPath){
		return currentPath.substring(0, currentPath.lastIndexOf("/"));
	}
	
	public static void main(String args[]){
		String tknoaa = "7814552588565";
//		String tknos = "781-4552588565";
//		String tknoa = "4552588565";
//		System.out.println(tkno.length()+"a");
//		System.out.println(tknos.length()+"aa");
//		System.out.println(tknoa.length()+"aaa");
		tknoaa = StringUtils.substring(tknoaa, 0, 3) + "-" +StringUtils.substring(tknoaa, 3, 13);
		System.out.println(tknoaa);
	}
}
