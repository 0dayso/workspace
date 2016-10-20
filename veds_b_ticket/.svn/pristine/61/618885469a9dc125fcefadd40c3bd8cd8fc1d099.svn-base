package cn.vetech.web.vedsb.jpcwgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.vetech.core.business.export.Export2Xls;
import org.vetech.core.modules.cache.EhcacheManage;
import org.vetech.core.modules.excel.ExcelReaderUtil;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.FileOperate;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.XmlUtils;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.dao.jpcwgl.JpYsdzQnDao;
import cn.vetech.vedsb.jp.dao.jpcwgl.JpYsdzTbDao;
import cn.vetech.vedsb.jp.dao.jpcwgl.JpYsdzTcDao;
import cn.vetech.vedsb.jp.dao.jpcwgl.JpYsdzXcDao;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpDbParam;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpDzsz;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpYsdz;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpYsdzQn;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpYsdzTb;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpYsdzTc;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpYsdzXc;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpYsdzdc;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpysdzdcExportPage;
import cn.vetech.vedsb.jp.entity.jpcwgl.Ld;
import cn.vetech.vedsb.jp.entity.jpcwgl.LdExportPage;
import cn.vetech.vedsb.jp.entity.jpcwgl.Wdz;
import cn.vetech.vedsb.jp.entity.jpcwgl.WdzExportPage;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpcwgl.JpDzszServiceImpl;
import cn.vetech.vedsb.jp.service.jpcwgl.JpYsdzServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.vedsb.utils.PlatCode;
import cn.vetech.vedsb.utils.bankdb.BankCompareUtil;
import cn.vetech.vedsb.utils.bankdb.BankDataUtil;
import cn.vetech.vedsb.utils.bankdb.BankDbUtil;
import cn.vetech.web.vedsb.SessionUtils;

import com.alibaba.fastjson.JSONObject;
@Controller
public class JpYsdzController extends MBaseControl<JpYsdz, JpYsdzServiceImpl>{
	@Autowired
	private AttachService attachService;
	@Autowired
	private JpDzszServiceImpl jpDzszServiceImpl;
	@Autowired
	private SqlSessionTemplate sqlSessionJp;
	@Autowired
	private EhcacheManage ehcacheManage;
	
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	@Autowired
	private Export2Xls export2Xls;
	
	private final static int CACHE_TIME=1800;//缓存秒数 30分钟
	@Override
	public void insertInitEntity(JpYsdz t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInitEntity(JpYsdz t) {
		// TODO Auto-generated method stub
		
	}
	/***
	 * 网店工作台
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping
	public String wdgzt(Model model,HttpServletRequest request){
		String dzrq=request.getParameter("dzrq");
		if(StringUtils.isBlank(dzrq)){
			dzrq=VeDate.getNextDay(VeDate.getStringDate(),"-1");
		}
		Shyhb user=SessionUtils.getShshbSession();
		JpYsdz t=new JpYsdz();
		t.setDzrq(dzrq);
		t.setShbh(user.getShbh());
		List<JpYsdz> list=baseService.getMyBatisDao().select(t);
		List<JpYsdz> tb10100011=new ArrayList<JpYsdz>();
		List<JpYsdz> xc10100050=new ArrayList<JpYsdz>();
		List<JpYsdz> qnr10100010=new ArrayList<JpYsdz>();
		List<JpYsdz> kx10100012=new ArrayList<JpYsdz>();
		List<JpYsdz> tc10100024=new ArrayList<JpYsdz>();
		int count=0;
		attachService.wdzl("wdid").shyhb("dzUserid", user.getShbh()).shyhb("shUserid", user.getShbh()).execute(list);
		for(JpYsdz bean : list){
			String wdpt=bean.getWdpt();
			if("10100011".equals(wdpt)){
				tb10100011.add(bean);
				count++;
			}else if("10100050".equals(wdpt)){
				xc10100050.add(bean);
				count++;
			}else if("10100010".equals(wdpt)){
				qnr10100010.add(bean);
				count++;
			}else if("10100012".equals(wdpt)){
				kx10100012.add(bean);
				count++;
			}else if("10100024".equals(wdpt)){
				tc10100024.add(bean);
				count++;
			}
		}
		model.addAttribute("tb10100011", tb10100011);
		model.addAttribute("xc10100050", xc10100050);
		model.addAttribute("qnr10100010", qnr10100010);
		model.addAttribute("tc10100024", tc10100024);
		//model.addAttribute("kx10100012", kx10100012);
		model.addAttribute("count", count);
		return view("list");
	}
	/**
	 * 对账查询系统数据
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping
	public String genSystemData(Model model,HttpServletRequest request) throws Exception {
		List<Map<String,Object>> detaillist=procedureServiceImpl.procysdz(getXml(request));
		String wdid = StringUtils.trimToEmpty(request.getParameter("wdid"));
		Shyhb user=SessionUtils.getShshbSession();
		List<Map> newList = new ArrayList<Map>();// 存储处理过的数据 ③
		try {
			if(CollectionUtils.isNotEmpty(detaillist)){
				List<Map<String, Object>> zcdList=new ArrayList<Map<String, Object>>();//正常单
				List<Map<String, Object>> tpdList=new ArrayList<Map<String, Object>>();//退票单
				List<Map<String, Object>> gqdList=new ArrayList<Map<String, Object>>();//改签单
				List<Map<String, Object>> bcdList=new ArrayList<Map<String, Object>>();//补差单
				for (Map map : detaillist) {
					map.put("ZFJE", BankDbUtil.obj2Double(map.get("ZFJE")));
					if("1".equals(String.valueOf(map.get("DDLX")))){
						zcdList.add(map);
					}else if ("2".equals(String.valueOf(map.get("DDLX")))) {
						tpdList.add(map);
					}else if ("3".equals(String.valueOf(map.get("DDLX")))) {
						gqdList.add(map);
					}else if ("4".equals(String.valueOf(map.get("DDLX")))) {
						bcdList.add(map);
					}
				}
				if(!zcdList.isEmpty()){
					zcdList=BankDbUtil.hbWbdh(zcdList);
					newList.addAll(zcdList);
				}
				if(!tpdList.isEmpty()){
					tpdList=BankDbUtil.hbWbdh(tpdList);
					newList.addAll(tpdList);
				}
				if(!gqdList.isEmpty()){
					gqdList=BankDbUtil.hbWbdh(gqdList);
					newList.addAll(gqdList);
				}
				if(!bcdList.isEmpty()){
					bcdList=BankDbUtil.hbWbdh(bcdList);
					newList.addAll(bcdList);
				}
				BankDbUtil.toSortData(newList, "ZFJE");//按支付金额排序
				//存入缓存
				String key=genKey(user.getBh(), wdid, "DETAILLIST");
				putCache(key, newList);
				model.addAttribute("detailList", newList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return view("sysdata");
	}
	/**
	 * 上传网店银行账单
	 * @param model
	 * @param file
	 * @param wdpt
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping
	public String bankup(Model model,
			@RequestParam(value="file") MultipartFile file,
			@RequestParam(value="wdpt") String wdpt,HttpServletRequest request) throws Exception {
		String wdid = StringUtils.trimToEmpty(request.getParameter("wdid"));
		String dzrq=StringUtils.trimToEmpty(request.getParameter("dzrq"));
		Shyhb user=SessionUtils.getShshbSession();
		JpDzsz t=new JpDzsz();
		t.setGsbh(wdpt);
		t.setShbh("0000");//系统格式商户编号为0000
		JpDzsz jpDzsz=jpDzszServiceImpl.getEntityById(t);
		BankImport bankImport=new BankImport(jpDzsz.getQsh()-1);
		String fileName = file.getOriginalFilename();
		String tempId = VeDate.getNo(4);
		String path ="updownFiles"+File.separator+"tmpfiles"+File.separator + tempId + File.separator;
		path += fileName;
		File file2 = new File(path);
		FileUtils.writeByteArrayToFile(file2, file.getBytes());
		//file.transferTo(file2);
		ExcelReaderUtil.readExcel(bankImport, jpDzsz.getQsh()-1,jpDzsz.getQsh()-1, path, null);
		List<Map<String, Object>> bankList=bankImport.getBankList();
		if(CollectionUtils.isNotEmpty(bankList) && jpDzsz.getMwh()!=null && jpDzsz.getMwh()>0){
			for (int i = 0, size=bankList.size(); i < jpDzsz.getMwh() && size > 0; i++) {
				size--;
				bankList.remove(size);
			}
		}
		List<String> headList=bankImport.getHeadRow();
		/**
		 * 开始解析账单
		 */
		BankDataUtil bankDataUtil=new BankDataUtil();
		String errorFileName="";
		String mbfileName="";
		if("10100050".equals(wdpt)){
			bankDataUtil.xcZdCl(bankList, headList, jpDzsz);//携程账单
			errorFileName="xiecheng"+wdpt+dzrq;
			mbfileName="wddz_xcerrormb.xls";
		}else if ("10100024".equals(wdpt)) {//同程
			bankDataUtil.tcZdCl(bankList, headList, jpDzsz);
			errorFileName="tongcheng"+wdpt+dzrq;
			mbfileName="wddz_tcerrormb.xls";
		}else if("10100010".equals(wdpt)){//去哪儿
			bankDataUtil.qnrYhZdCl(bankList, headList, jpDzsz);
			errorFileName="qunaer"+wdpt+dzrq;
			mbfileName="wddz_qnrerrormb.xls";
		}else if ("10100011".equals(wdpt)) {//淘宝
			bankDataUtil.tbZdCl(bankList, headList, jpDzsz);
			errorFileName="taobao"+wdpt+dzrq;
			mbfileName="wddz_tberrormb.xls";
		}else if ("10100012".equals(wdpt)) {//酷讯
			bankDataUtil.kxZfbZdCl(bankList, headList, jpDzsz);
			errorFileName="kuxun"+wdpt+dzrq;
		}
		if(StringUtils.isNotBlank(jpDzsz.getBzbz())){//去掉不显示的列
			String[] lms=jpDzsz.getBzbz().split("\\|");
			for(String l : lms){
				headList.remove(l);
			}
		}
		BankDbUtil.toSortData(bankList, "发生金额");
		//存入缓存
		String key=genKey(user.getBh(), wdid, "BANKLIST");
		putCache(key, bankList);
		//不能识别的账单导出
		List<Map<String,Object>> errorList=bankDataUtil.getErrorList();
		if(errorList!=null && !errorList.isEmpty()){
			errorFileName+="_"+VeDate.getRandom(5)+".xls";
			String sourcePath="/updownFiles/mb/"+mbfileName;
			try {
				String fileerror=BankDbUtil.exportExcelByModel(sourcePath, 1, errorFileName, errorList);
				model.addAttribute("fileerror", fileerror);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("headList", headList);
		model.addAttribute("banklist", bankList);
		return view("bankdata");
	}
	/**
	 * 对比数据
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	@ResponseBody
	public String dbdata(HttpServletRequest request){
		String wdpt=StringUtils.trimToEmpty(request.getParameter("wdpt"));
		String wdid = StringUtils.trimToEmpty(request.getParameter("wdid"));
		String dzrq=StringUtils.trimToEmpty(request.getParameter("dzrq"));
		String zbid=StringUtils.trimToEmpty(request.getParameter("dzzbid"));
		Shyhb user=SessionUtils.getShshbSession();
		String shbh=user.getShbh();
		Map<String,String> result=new HashMap<String,String>();
		result.put("status", "1");
		try {
			String error=isCanCompare(dzrq, wdpt, zbid, wdid);
			if(StringUtils.isNotBlank(error)){
				throw new Exception(error);
			}
			//取缓存
			String key=genKey(user.getBh(), wdid, "DETAILLIST");
			List<Map<String, Object>> detaillist=(List)genCache(key);
			if(detaillist==null){
				throw new Exception("操作时间过长，请刷新页面重试！");
			}
			key=genKey(user.getBh(), wdid, "BANKLIST");
			List<Map<String, Object>> banklist =(List)genCache(key);
			if(banklist== null){
				throw new Exception("请重新导入银行对账单数据。");
			}
			JpDzsz t=new JpDzsz();
			t.setGsbh(wdpt);
			t.setShbh("0000");//系统格式商户编号为0000
			JpDzsz jpDzsz=jpDzszServiceImpl.getEntityById(t);
			JpDbParam param=new JpDbParam();
			param.setDdhl(jpDzsz.getDdhl());
			param.setDeptid(user.getShbmid());
			param.setDzrq(dzrq);
			String gsdy=jpDzsz.getWdgsdy();
			if(StringUtils.isNotBlank(gsdy)){
				param.setGsdy(gsdy.split("\\|"));
			}
			param.setShbh(shbh);
			param.setUser(user.getBh());
			param.setWdid(wdid);
			param.setWdpt(wdpt);
			param.setZbid(zbid);
			if(PlatCode.QN.equals(wdpt)){
				BankCompareUtil<JpYsdzQn, JpYsdzQnDao> compareUtil=new BankCompareUtil<JpYsdzQn, JpYsdzQnDao>(param,JpYsdzQn.class,JpYsdzQnDao.class, baseService, sqlSessionJp);
				compareUtil.qnryhdz(detaillist, banklist);
			}else if(PlatCode.TB.equals(wdpt)){
				BankCompareUtil<JpYsdzTb, JpYsdzTbDao> compareUtil=new BankCompareUtil<JpYsdzTb, JpYsdzTbDao>(param, JpYsdzTb.class,JpYsdzTbDao.class,baseService, sqlSessionJp);
				compareUtil.tbdz(detaillist, banklist);
			}else if(PlatCode.XC.equals(wdpt)){
				BankCompareUtil<JpYsdzXc, JpYsdzXcDao> compareUtil=new BankCompareUtil<JpYsdzXc, JpYsdzXcDao>(param,JpYsdzXc.class,JpYsdzXcDao.class, baseService, sqlSessionJp);
				compareUtil.xcdz(detaillist, banklist);
			}else if(PlatCode.TC.equals(wdpt)){
				BankCompareUtil<JpYsdzTc, JpYsdzTcDao> compareUtil=new BankCompareUtil<JpYsdzTc, JpYsdzTcDao>(param,JpYsdzTc.class,JpYsdzTcDao.class, baseService, sqlSessionJp);
				compareUtil.tcdz(detaillist, banklist);
			}
			key=genKey(user.getBh(), wdid, "DETAILLIST");
			removeCache(key);
			key=genKey(user.getBh(), wdid, "BANKLIST");
			removeCache(key);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "-1");
			result.put("error", "对比异常:"+e.getMessage());
		}
		return JSONObject.toJSONString(result);
	}
	/**
	 * 检查是否能对账
	 * @param wdid
	 * @param wdpt
	 * @param dzrq
	 * @param zbid
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public String checkDzFlag(@RequestParam String wdid,@RequestParam String wdpt,@RequestParam String dzrq,@RequestParam String zbid){
		Shyhb user=SessionUtils.getShshbSession();
		String shbh=user.getShbh();
		int compareCount=baseService.genCompareCount(zbid, shbh, wdpt);
		if(compareCount>0){
			return "1";
		}else {
			String syrq=VeDate.getNextDay(dzrq, "1")+" 00:00:00";
			compareCount=baseService.genAfterCompareCount(syrq, shbh, wdid, wdpt);
			if(compareCount>0){
				return "-1";
			}
		}
		return "0";
	}
	/**
	 * 查询对账结果
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping
	public String genDbResult(Model model,HttpServletRequest request,JpYsdzdc jpYsdzdc,HttpServletResponse response){
		int pageNum=NumberUtils.toInt(request.getParameter("pageNum"),1);
		int pageSize=NumberUtils.toInt(request.getParameter("pageSize"),20);
		String zbid=StringUtils.trimToEmpty(request.getParameter("zbid"));
		String wdpt=StringUtils.trimToEmpty(request.getParameter("wdpt"));
		String dzrq=StringUtils.trimToEmpty(request.getParameter("dzrq"));
		String bdrq=StringUtils.trimToEmpty(request.getParameter("bdrq"));
		String export=request.getParameter("export");
		Shyhb user=SessionUtils.getShshbSession();
		String shbh=user.getShbh();
		JpYsdz t=new JpYsdz();
		t.setShbh(shbh);
		t.setId(zbid);
		JpYsdz jpYsdz=baseService.getEntityById(t);
		attachService.wdzl("wdid").shyhb("dzUserid", shbh).execute(jpYsdz);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "",false);
		searchParams.put("shbh", shbh);
		if(StringUtils.isNotBlank(dzrq)){
			searchParams.put("dzrqs", dzrq);
			searchParams.put("dzrqz", dzrq);
		}
		if(StringUtils.isNotBlank(bdrq)){
			searchParams.put("bdrqs", bdrq);
			searchParams.put("bdrqz", bdrq);
		}
		if(StringUtils.isNotBlank(export)){
			jpYsdzdc.setShbh(shbh);
			jpYsdzdc.setStart(1);
			String displayName="营收对账报表";
			try {
				JpysdzdcExportPage exportPage = new JpysdzdcExportPage(jpYsdzdc);
				String sdf = export2Xls.export(exportPage, export, displayName, "");
				File df = new File(sdf);
				String parent = df.getParent();
				String file = df.getName();
				FileOperate.todownfile(response, displayName, parent, file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		Page page=baseService.genDbresult(searchParams, wdpt, pageNum, pageSize);
		Map hjMap=baseService.genDbresultSum(searchParams, wdpt);
		Map countMap=baseService.genDbresultCount(searchParams, wdpt);
		model.addAttribute("counts", countMap);
		model.addAttribute("wddzzb", jpYsdz);
		model.addAttribute("page", page);
		model.addAttribute("sumMap",hjMap);
		return view("dbreult_main");
	}
	/**
	 * 删除对账结果
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public String delDbresult(HttpServletRequest request){
		String zbid=StringUtils.trimToEmpty(request.getParameter("zbid"));
		String wdpt=StringUtils.trimToEmpty(request.getParameter("wdpt"));
		String wdid=StringUtils.trimToEmpty(request.getParameter("wdid"));
		String dzrq=StringUtils.trimToEmpty(request.getParameter("dzrq"));
		Shyhb user=SessionUtils.getShshbSession();
		String shbh=user.getShbh();
		Map<String,String> result=new HashMap<String,String>();
		result.put("status", "0");
		try {
			String syrq=VeDate.getNextDay(dzrq, "1")+" 00:00:00";
			int compareCount=baseService.genAfterCompareCount(syrq, shbh, wdid, wdpt);
			if(compareCount>0){
				throw new Exception(dzrq+"之后已存在对账记录，对账记录不可删除!");
			}
			baseService.delResult(wdpt, shbh, zbid, wdid, dzrq);
			JpYsdz t=new JpYsdz();
			t.setId(zbid);
			t.setShbh(shbh);
			JpYsdz ysdz=baseService.getEntityById(t);
			ysdz.setDzZt("0");
			ysdz.setDzUserid(null);
			ysdz.setDzDatetime(null);
			ysdz.setDzDeptid(null);
			baseService.getMyBatisDao().updateByPrimaryKey(ysdz);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "-1");
			result.put("error", e.getMessage());
		}
		return JSONObject.toJSONString(result);
	}
	/**
	 * 生成今日对账数据
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public String creatTodayDz() {
		Map<String,String> result=new HashMap<String,String>();
		result.put("status", "0");
		try {
			String dzrq=VeDate.getStringDateShort();
			Shyhb user=SessionUtils.getShshbSession();
			String shbh=user.getShbh();
			int status=baseService.createTodayDz(dzrq, shbh);
			if(-1==status){
				throw new Exception("生成对账数据异常!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "-1");
			result.put("error", e.getMessage());
		}
		return JSONObject.toJSONString(result);
	}
	/**
	 * 检查是否能对账
	 * @param dzrq
	 * @param wdpt
	 * @param zbid
	 * @param wdid
	 * @return error
	 */
	private String isCanCompare(String dzrq,String wdpt,String zbid,String wdid){
		Shyhb user=SessionUtils.getShshbSession();
		String shbh=user.getShbh();
		int compareCount=baseService.genCompareCount(zbid, shbh, wdpt);
		if(compareCount>0){
			return "当天的账单已对账，请刷新网店财务工作台页面，查看对账结果!";
		}
		String syrq=VeDate.getNextDay(dzrq, "1")+" 00:00:00";
		compareCount=baseService.genAfterCompareCount(syrq, shbh, wdid, wdpt);
		if(compareCount>0){
			return dzrq+"之后已存在对账记录，不可对账，对账必须按日期前后顺序对账!";
		}
		return null;
	}
	/**
	 * 获取查询系统数据xml
	 * @param request
	 * @return
	 */
	private String getXml(HttpServletRequest request){
		Shyhb user=SessionUtils.getShshbSession();
		StringBuffer xml=new StringBuffer("<SQL>");
		String dzrq=StringUtils.trimToEmpty(request.getParameter("dzrq"));
		String ddlx=StringUtils.trimToEmpty(request.getParameter("ddlx"));
		String zfkm=StringUtils.trimToEmpty(request.getParameter("zfkm"));
		String wdpt=StringUtils.trimToEmpty(request.getParameter("wdpt"));
		String whatbank = StringUtils.trimToEmpty(request.getParameter("wdid"));
		xml.append(XmlUtils.xmlnode("DZRQS", dzrq));
		xml.append(XmlUtils.xmlnode("DZRQZ", dzrq));
		xml.append(XmlUtils.xmlnode("DDLX", ddlx));
		xml.append(XmlUtils.xmlnode("ZFKM", zfkm));
		xml.append(XmlUtils.xmlnode("WDPT",wdpt));
		xml.append(XmlUtils.xmlnode("WDID", whatbank));
		xml.append(XmlUtils.xmlnode("SHBH",user.getShbh()));
		xml.append("</SQL>");
		return xml.toString();
	}
	/**
	 * 获取缓存key
	 * @param userBh
	 * @param wdid
	 * @param lx
	 * @return
	 */
	private String genKey(String userBh,String wdid,String lx){
		return userBh+wdid+lx;
	}
	/**
	 * 存入缓存
	 * @param key
	 * @param value
	 */
	private void putCache(String key,Object value){
		ehcacheManage.put("Cache", key, value, CACHE_TIME);
	}
	/**
	 * 移除缓存
	 * @param key
	 */
	private void removeCache(String key){
		ehcacheManage.remove("Cache", key);
	}
	/**
	 * 从缓存取数据
	 * @param key
	 * @return
	 */
	private Object genCache(String key){
		return ehcacheManage.get("Cache", key);
	}
	@RequestMapping(value="wdzList")
	public String wdzList(Wdz wdz,ModelMap model,HttpServletRequest request,HttpServletResponse response){
		String export=request.getParameter("export");
 		int pageNum=NumberUtils.toInt(request.getParameter("pageNum"),1);
		int pageSize=NumberUtils.toInt(request.getParameter("pageSize"),10);
 		Shyhb user = SessionUtils.getShshbSession();
 		wdz.setShbh(user.getShbh());
 		try {
 			//导出
 			if(StringUtils.isNotBlank(export)){
				String displayName="未到账报表";
				wdz.setStart(1);
				try {
					WdzExportPage exportPage = new WdzExportPage(wdz);
					String sdf = export2Xls.export(exportPage, export, displayName, "");
					File df = new File(sdf);
					String parent = df.getParent();
					String file = df.getName();
					FileOperate.todownfile(response, displayName, parent, file);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("导出Excel文件异常，" + e.getMessage());
				}
				return null;	
			}
 			//查询
 			Page page = this.baseService.wdzPage(wdz,pageNum,pageSize);
 			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("未到账报表查询出错"+e.getMessage());
		}
		return viewpath("wdzList");
	}
	
	@RequestMapping(value = "ldList")
	public String ldList(Ld ld, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		String lx = request.getParameter("lx");
		String export = request.getParameter("export");
		int pageNum = NumberUtils.toInt(request.getParameter("pageNum"), 1);
		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), 10);
		Shyhb user = SessionUtils.getShshbSession();
		ld.setShbh(user.getShbh());
		try {
			// 导出
			if (StringUtils.isNotBlank(export)) {
				String displayName = "漏单报表";
				ld.setStart(1);
				try {
					LdExportPage exportPage = new LdExportPage(ld);
					String sdf = export2Xls.export(exportPage, export, displayName, "");
					File df = new File(sdf);
					String parent = df.getParent();
					String file = df.getName();
					FileOperate.todownfile(response, displayName, parent, file);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("导出Excel文件异常，" + e.getMessage());
				}
				return null;
			}
			// 查询
			Page page = this.baseService.ldPage(ld, pageNum, pageSize);
			model.addAttribute("page", page);

		} catch (Exception e) {
			logger.error("漏单报表查询出错" + e.getMessage());
		}
		return viewpath("ldList");
	}
}	
