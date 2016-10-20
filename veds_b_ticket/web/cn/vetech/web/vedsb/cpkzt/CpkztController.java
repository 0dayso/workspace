package cn.vetech.web.vedsb.cpkzt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.api.pnrpat2.PnrCjr;
import org.vetech.core.business.pid.api.rtpnr.PnrRtParam;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.utils.XmlUtils;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shcsb;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.service.ShcsbServiceImpl;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpJk;
import cn.vetech.vedsb.jp.entity.office.JpOffice;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpddsz.JpDdszServiceImpl;
import cn.vetech.vedsb.jp.service.jpddsz.JpddWork_jp;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.zxzw.TpXeZw;
import cn.vetech.vedsb.jp.service.jpzdcp.JpZdcpJkServiceImpl;
import cn.vetech.vedsb.jp.service.jpzdcp.JpzdcpWork;
import cn.vetech.vedsb.jp.service.office.JpOfficeServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.PkgZjpSgServiceImpl;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.web.vedsb.SessionUtils;
import cn.vetech.web.vedsb.common.HtmlRender;

@Controller
public class CpkztController extends MBaseControl<JpKhdd, JpKhddServiceImpl>{
	private static final String PAGE_SIZE = "10";
	@Autowired
	private AttachService attachService;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private PkgZjpSgServiceImpl pkgZjpSgServiceImpl;
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
	@Autowired
	private JpJpServiceImpl jpJpServiceImpl;
	@Autowired
	private JpDdszServiceImpl jpDdszServiceImpl;
	@Autowired
	private JpOfficeServiceImpl jpOfficeServiceImpl;
	@Autowired
	private ShcsbServiceImpl shcsbServiceImpl;
	@Autowired
	private JpZdcpJkServiceImpl jpZdcpJkServiceImpl;

	/**
	 * 获取采购科目  office号
	 * @param cgly
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cglyValue")
	public String cglyValue(String cgly,ModelMap model){
		Shyhb user = SessionUtils.getShshbSession();
		Shzfkm shzfkm=new Shzfkm();
		shzfkm.setShbh(user.getShbh());
		shzfkm.setSfqy("1");
		List<Shzfkm> zfkms=shzfkmServiceImpl.getShzfkmList(shzfkm);
		List<JpPz> pzList = jpPzServiceImpl.selectJpPzByShbh(user.getShbh());
		List<Shyhb> shyhb=shyhbServiceImpl.queryShyhbListBySh(user.getShbh());
		List<JpOffice> officelist = null;
		List<JpOffice> officelistNew = new ArrayList<JpOffice>();
		if("BOP".equals(cgly)){
			officelist= jpOfficeServiceImpl.selectJpOfficeByShbh(user.getShbh());
			if(CollectionUtils.isNotEmpty(officelist)){
				attachService.zfkm("bopcgkm").execute(officelist);
				for(JpOffice j : officelist){
					if("1".equals(j.getSfbopcp())){
						officelistNew.add(j);
					}
				}
			}
		}
		model.addAttribute("bopcgkmlist",officelistNew);
		model.addAttribute("zfkms", zfkms);
		model.addAttribute("pzList", pzList);
		model.addAttribute("shyhbList", shyhb);
		return viewpath("zjp_cpxx_cgly");
	}
	
	/**
	 * 进入订单列表查询页面
	 */
	@RequestMapping(value = "viewlist")
	public String view(@RequestParam(value = "lx", defaultValue  = "1") String lx,
					   ModelMap model){
		//获取已设置的排序字段及方式
		Shyhb user = SessionUtils.getShshbSession();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("shbh", user.getShbh());
		param.put("bh", "2025");//2025在参数表对应设置排序方式
		List<Shcsb> list=shcsbServiceImpl.getListByShbhAndBhs(param);
		if(CollectionUtils.isNotEmpty(list)){
			Shcsb shcsb = list.get(0);
			model.addAttribute("orderBy", shcsb.getCsz1()+" "+shcsb.getCsz2());
		}else{
			model.addAttribute("orderBy", "cfrq asc");
		}
		model.addAttribute("lx", lx);
		return viewpath("list");
	}
	
	/**
 	 * 分页查询
	 */
	@RequestMapping(value = "query",method = RequestMethod.POST)
	public @ResponseBody Page quary(String[] cpzt,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue  = "desc") String sortType, 
			@RequestParam(value = "lx", defaultValue  = "1") String lx,
			String sortName,ModelMap model,HttpServletRequest request) {
			Shyhb user = SessionUtils.getShshbSession();
			Page page = null;
			try {
				Map<String, Object> params = Servlets.getParametersStartingWith(request, "",false);
				params.put("shbh", user.getShbh());
				params.put("cpzt", cpzt);
				params.put("start", pageSize*(pageNum-1));
				params.put("count", pageSize);
				model.addAttribute("sjrs", params.get("sjrs"));
				model.addAttribute("sjrz", params.get("sjrz"));
				model.addAttribute("lx", lx);
				page  = this.baseService.query(params, sortType);
				if(page != null){
					List<?> list = page.getList();
					attachService.wdzl("WDID").zfkm("SKKM").shyhb("CP_SDR",user.getShbh()).veclass("CGLY").execute(list);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("查询订单列表错误", e);
			}
			return page;
	}
	
	/**
	 * 进入转机票页面
	 * @param ddbh
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "enterZjpPage_{ddbh}")
	public String enterZjpPage(@PathVariable("ddbh") String ddbh, ModelMap model) {
		Shyhb yhb = SessionUtils.getShshbSession();
		String shbh = yhb.getShbh();
		try {
			if (StringUtils.isNotBlank(ddbh)) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("ddbh", ddbh);
				paramMap.put("shbh", shbh);
				
				//订单信息
				Map<String,Object> jpKhddMap=this.baseService.detail(paramMap);
				model.addAttribute("jpkhdd", jpKhddMap);
				
				//订单乘机人信息
				List<JpKhddCjr> khddCjrList = this.jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, shbh);
				
				//调用RTPNR接口  从PNR中获取票号信息
				JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(shbh);
				PnrRtParam pnrRtParam = new PnrRtParam();
				if(jpPz != null ){
					pnrRtParam.setShbh(shbh);
					pnrRtParam.setUserid(yhb.getPidyh());
					pnrRtParam.setPassword(yhb.getMm());
					pnrRtParam.setUrl("http://"+jpPz.getPzIp()+":"+jpPz.getPzPort());
					pnrRtParam.setOfficeId(jpPz.getOfficeid());
					pnrRtParam.setPnrno(VeStr.getValue(jpKhddMap, "CG_PNR_NO"));//"HFHMP1"
					Pnr pnrObject = null;
					try{
						pnrObject = IbeService.rtPnr(pnrRtParam);
					}catch (Exception e) {
						e.printStackTrace();
					}
					
					if (pnrObject != null) {
						List<PnrCjr>  pnrcjrList=pnrObject.getCjrlist();
						if(CollectionUtils.isNotEmpty(pnrcjrList)){
							Map<String,String> cjrTkno=new HashMap<String, String>();
							for(PnrCjr pc:pnrcjrList){
								String Tk=StringUtils.trimToEmpty(pc.getTkno());
								if(StringUtils.isNotBlank(Tk)&&Tk.length()==13){
									pc.setTkno(Tk.substring(0,3)+"-"+Tk.substring(3, 13));
								}
								String key=pc.getCjr();
								String tkno=cjrTkno.get(key);
								if(StringUtils.isBlank(tkno)){
									cjrTkno.put(key, pc.getTkno());
								}else{
									cjrTkno.put(key, tkno+","+pc.getTkno());
								}
							}
							System.out.println("PNR:"+pnrRtParam.getPnrno()+"=>解析票号内容：["+cjrTkno+"]");
							
							if(CollectionUtils.isNotEmpty(khddCjrList)){
								for(int i=0;i<khddCjrList.size();i++){
									JpKhddCjr  jkc=khddCjrList.get(i);
									String key=TpXeZw.getCjrlx(jkc.getCjr(), jkc.getCjrlx());
									jkc.setTkno(VeStr.getValue(cjrTkno, key));
								}
							}
						}
					}
				}
				model.addAttribute("jpkhddCjrList", khddCjrList);
				
				//订单航段信息
				List<JpKhddHd> khddHdList = this.jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
				model.addAttribute("jpkhddHdList", khddHdList);
			}
			return viewpath("ticket_zjp");
		} catch (Exception e) {
			logger.error("进入转机票页面错误", e);
			return this.addError("进入一键完成页面错误" + e.getMessage(), e, "ticket_zjp", model);
		}
	}
	
	/**
	 * 批量票号回填
	 * @param wbdhs
	 * @param wdids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "batchPhht")
	public @ResponseBody String batchPhht(String wbdhs,String ddbhs,String wdids, ModelMap model) {
		
		Map<String,JpDdsz>  jpddszMap=new HashMap<String, JpDdsz>();
		Map<String,String>  wdidMap=new HashMap<String, String>();
		String[] wddh_arr = wbdhs.split(",");
		String[] wdid_arr= wdids.split(",");
		String[] ddbh_arr= ddbhs.split(",");
		//去除重复的网店
		for(int i=0;i<wdid_arr.length;i++){
			String wdid=wdid_arr[i];
			wdidMap.put(wdid, wdid);
		}
		
		//取网店导单设置
		for (Map.Entry<String,String> entry : wdidMap.entrySet()) {  
		    String wdid=entry.getKey();
		    jpddszMap.put(wdid, getJpDdsz(wdid));
		} 
		
		JpddWork_jp jpddwork_jp = null;
		String msg = "";
		if (wddh_arr.length > 0) {
			if (jpddwork_jp == null) {
				jpddwork_jp = SpringContextUtil.getBean(JpddWork_jp.class);
			}
			for (int i=0;i<wddh_arr.length;i++) {
				String wbdh=wddh_arr[i];
				String wdid=wdid_arr[i];
				String ddbh=ddbh_arr[i];
				JpDdsz jpDdsz=jpddszMap.get(wdid);
				if (jpDdsz == null) {
					msg +="网店["+wdid+"] 请检查导单设置<br>";
				}else{
					String returnmsg = jpddwork_jp.ptjp_sd(wbdh,ddbh, jpDdsz);
					msg += "外部单号["+wbdh + "]:<font " + (StringUtils.isNotBlank(returnmsg)? " color='red'>"+returnmsg:" color='green'>回填成功")+"</font><br>";
				}
			}
		}
		return msg;
	}
	
	/**
	 * 批量手工触发全自动出票
	 * @param wbdhs
	 * @param ddbhs
	 * @param wdids
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "batchSgcp")
	public @ResponseBody String batchSgcp(String ddbhs, ModelMap model) {
		String[] ddbh_arr= ddbhs.split(",");
		Shyhb yhb =SessionUtils.getShshbSession();
		String shbh=yhb.getShbh();
		JpzdcpWork jpddwork = null;
		String msg = "";
		if (ddbh_arr.length > 0) {
			if (jpddwork == null) {
				jpddwork = SpringContextUtil.getBean(JpzdcpWork.class);
			}
			for (String ddbh : ddbh_arr) {
				try {
					jpddwork.start(ddbh, shbh);
				} catch (Exception e) {
					e.printStackTrace();
					msg += "订单编号["+ddbh + "]出票失败!<br>";
				}
			}
		}
		return msg;
	}
	
	/**
	 * 根据PNR内容提取
	 * @param pnrContent
	 * @param lx
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "tqByPnrContent")
	public String tqByPnrContent(@RequestParam(value = "ddbh", defaultValue = "") String ddbh, @RequestParam(value = "pnrContent", defaultValue = "") String pnrContent, ModelMap model){
		Shyhb yhb =SessionUtils.getShshbSession();
		String shbh = yhb.getShbh();
		try{
			//根据PNR内容提取
			//调用RTPNR接口
			JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(shbh);
			Pnr pnrObject = this.baseService.tqByPnrContent(pnrContent, jpPz);
			model.addAttribute("pnrObject", pnrObject);
			
			if (StringUtils.isNotBlank(ddbh)) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("ddbh", ddbh);
				paramMap.put("shbh", shbh);
				//订单信息
				Map<String,Object> jpKhddMap=this.baseService.detail(paramMap);
				model.addAttribute("jpkhdd", jpKhddMap);
				//订单乘机人信息
				List<JpKhddCjr> khddCjrList = this.jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, shbh);
				model.addAttribute("jpkhddCjrList", khddCjrList);
				//订单航段信息
				List<JpKhddHd> khddHdList = this.jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
				model.addAttribute("jpkhddHdList", khddHdList);
			}
		} catch (Exception e){
			logger.error("根据PNR内容提取错误", e);
			return this.addError("根据PNR内容提取错误" + e.getMessage(), e, "ticket_zjp", model);
		}
		return viewpath("ticket_zjp");
	}
	
	
	/**
	 * 转机票保保存
	 */
	@RequestMapping(value = "saveZjp")
	@ResponseBody
	public Map<String, Object> saveZjp(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		Shyhb yhb =SessionUtils.getShshbSession();
		//String type = request.getParameter("type");// 1只转机票 �?转机票并交票
		Map<String, Object> ddMap = new HashMap<String, Object>();
		try{
			String ddbh = StringUtils.trimToEmpty(request.getParameter("ddbh"));
			if(StringUtils.isBlank(ddbh)){
				throw new Exception("无法获取订单编号！");
			}
			JpKhdd jpKhdd = new JpKhdd();
			jpKhdd.setShbh(yhb.getShbh());
			jpKhdd.setDdbh(ddbh);
			jpKhdd = jpKhddServiceImpl.getEntityById(jpKhdd);
			if(StringUtils.isBlank(ddbh)){
				throw new Exception("无法获取订单！");
			}
			String cgly = StringUtils.trimToEmpty(request.getParameter("cgly"));
			String cpy = StringUtils.trimToEmpty(request.getParameter("cpy"));
			String cgkm = StringUtils.trimToEmpty(request.getParameter("cgkm"));
			String cgje = StringUtils.trimToEmpty(request.getParameter("cgje"));
			//String newPnr = StringUtils.trimToEmpty(request.getParameter("newpnr"));
			//String newHkgsPnr = StringUtils.trimToEmpty(request.getParameter("newhkgs_pnr"));
			String cgdw = StringUtils.trimToEmpty(request.getParameter("cgdw"));
			String officeid = StringUtils.trimToEmpty(request.getParameter("officeid"));
			String cgDddbh = StringUtils.trimToEmpty(request.getParameter("cgddbh"));
			String cpj = StringUtils.trimToEmpty(request.getParameter("cpj"));
			String workid = StringUtils.trimToEmpty(request.getParameter("workid"));
			
			
			// 订单节点
			Map<String,Object> paramMap=new HashMap<String,Object>();
			Map<String,Object> publicMap=new HashMap<String,Object>();
			publicMap.put("DATAFROM", "手工转机票");
			if(StringUtils.isNotBlank(cgje)){
				publicMap.put("CGJE", cgje);
			}
	        publicMap.put("DDBH", ddbh);
	        publicMap.put("SHBH", yhb.getShbh());
	        publicMap.put("CP_YHBH", cpy);
	        publicMap.put("CP_BMBH", yhb.getShbmid());
	        publicMap.put("GNGJ", "1");
	        publicMap.put("WORKNO", workid);
	        publicMap.put("PRINTNO", cpj);
	        publicMap.put("CP_OFFICEID", officeid);
	        publicMap.put("OP_USERID", cgdw);
	        publicMap.put("CGLY", cgly);
	        publicMap.put("CG_PNR_NO", jpKhdd.getCgPnrNo());
	        publicMap.put("CG_HKGS_PNR", jpKhdd.getCgHkgsPnr());
	        publicMap.put("CGKM", cgkm);
	        publicMap.put("CG_DDBH", cgDddbh);
	        publicMap.put("CGDW", cgdw);
	        paramMap.put("PUBLIC", publicMap);
	        
			//乘机人信息
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("shbh", yhb.getShbh());
			List<String> ddbhList = new ArrayList<String>();
			ddbhList.add(ddbh);
			param.put("ddbhs", ddbhList);
			List<JpKhddCjr> jpKhddCjrList = jpKhddCjrServiceImpl.selectJpByDdbh(param);
			String[] tknos = request.getParameterValues("tkno");
			String[] hcids = request.getParameterValues("hcid");
			
			if(tknos != null && tknos.length > 0){
				for(int i=0;i<tknos.length;i++){
					JpJp jpjp=jpJpServiceImpl.getMyBatisDao().queryJpjpByTkno(yhb.getShbh(), tknos[i]);
					if(jpjp != null){
						ddMap.put("CODE", "0");
						ddMap.put("MSG", "票号已存在，请重新填写票号！");
						return ddMap;
					}
				}
			}
			
			String[] cgZdjs = request.getParameterValues("cgZdj");
			String[] cgJsfs = request.getParameterValues("cgJsf");
			String[] cgTaxs= request.getParameterValues("cgTax");
			
//			if(cgZdjs == null || cgZdjs.length == 0){
//				throw new Exception("采购账单价必填！");
//			}
			if(jpKhddCjrList == null || jpKhddCjrList.isEmpty()){
				throw new Exception("无法获取乘机人信息！");
			}
//			if(cgZdjs.length!=jpKhddCjrList.size()){
//				throw new Exception("乘机人个数不匹配！");
//			}
			//乘机人节点
			List<Map<String,Object>> cjrList=new ArrayList<Map<String,Object>>();
			for(int i=0; i<jpKhddCjrList.size(); i++){
				Map<String,Object> cjrMap=new HashMap<String,Object>();
				JpKhddCjr cjr = jpKhddCjrList.get(i);
				cjrMap.put("CJRID", cjr.getId());
				
				List<Map<String,Object>> hcList=new ArrayList<Map<String,Object>>();
				Map<String,Object> hcMap=new HashMap<String,Object>();
				hcMap.put("HC", StringUtils.join(hcids,","));
				hcMap.put("TKNO", tknos[i]);
				hcMap.put("CG_ZDJ", cgZdjs[i]);
				hcMap.put("CG_PJ", cjr.getCgPj());
				hcMap.put("CG_JSF", cgJsfs[i]);
				hcMap.put("CG_TAX", cgTaxs[i]);
				hcMap.put("XS_PJ", cjr.getXsPj());
				hcMap.put("XS_JSF", cjr.getXsJsf());
				hcMap.put("XS_TAX", cjr.getXsTax());
				hcMap.put("XS_ZDJ", cjr.getXsZdj());
				hcMap.put("XS_JE", cjr.getXsJe());
				hcMap.put("LXKP", "0");
				hcList.add(hcMap);
				cjrMap.put("TK", hcList);
				
				cjrList.add(cjrMap);
			}
			paramMap.put("CJR", cjrList);
			this.pkgZjpSgServiceImpl.sgZjp(paramMap);
			
			int result = (Integer)paramMap.get("result");
			if(result == 0){
				ddMap.put("CODE", "1");
			} else {
				ddMap.put("CODE", "0");
				ddMap.put("MSG", paramMap.get("error"));
			}
		} catch (Exception e){
			e.printStackTrace();
			logger.error("手工转机票出错", e);
			ddMap.put("CODE", "0");
			ddMap.put("MSG", e.getMessage());
		}
		
		return ddMap;
	}
	
	
	/**
	 * 批量订单完成
	 * 
	 * @param paramsXml
	 * @return
	 */

	@RequestMapping
	public @ResponseBody Map<String, String> batchWc(@RequestParam(value = "ddbhs", defaultValue = "") String ddbhs,@RequestParam(value = "zdcpjk_ids", defaultValue = "") String zdcpjk_ids) {
		Map<String, String> ddMap = new HashMap<String, String>();
		ddMap.put("CODE", "-1");
		try {
			if (StringUtils.isBlank(ddbhs)) {
				ddMap.put("MSG", "订单编号不能为空");
				return ddMap;
			}
			
			Shyhb yhb = SessionUtils.getShshbSession();
			String shbh = yhb.getShbh();
			String cgly = "BOP";
			/**
			 * 取Office配置
			 */
			List<JpOffice> officelist = jpOfficeServiceImpl.selectJpOfficeByShbh(shbh);
			if (CollectionUtils.isEmpty(officelist)) {
				ddMap.put("MSG", "请先设置OFFICE信息");
				return ddMap;
			}
			JpOffice jpoffice = officelist.get(0);
			
			/**
			 * 取PID配置
			 */
			JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(shbh);
			if (jpPz == null) {
				ddMap.put("MSG", "请先设置PID信息");
				return ddMap;
			}
			
			String[] ddbh_arr = ddbhs.split(",");
			String[] zdcpjkid_arr=zdcpjk_ids.split(",");
			for (int m = 0, len = ddbh_arr.length; m < len; m++) {
				String ddbh=ddbh_arr[m];
				String zdcpjkid=zdcpjkid_arr[m];
				
				if (StringUtils.isNotBlank(zdcpjkid)) {
					JpZdcpJk jk = new JpZdcpJk();
					jk.setId(zdcpjkid);
					jk.setShbh(shbh);
					jk = jpZdcpJkServiceImpl.getEntityById(jk);
					if (jk != null && !jk.getSm().contains("PNR已经被出过票")) {
						continue;
					}
				}
				// 订单信息
				JpKhdd jpKhdd = new JpKhdd();
				jpKhdd.setShbh(yhb.getShbh());
				jpKhdd.setDdbh(ddbh);
				jpKhdd = jpKhddServiceImpl.getEntityById(jpKhdd);

				// 订单乘机人信息
				List<JpKhddCjr> jpKhddCjrList = this.jpKhddCjrServiceImpl.getKhddCjrListByDDbh(ddbh, shbh);
				if (CollectionUtils.isEmpty(jpKhddCjrList)) {
					continue;
				}
				
				//航段信息
				List<JpKhddHd> khddHdList = this.jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, shbh);
				if(CollectionUtils.isEmpty(khddHdList)){
					continue;
				}
				/**
				 * RTPNR 提取票号
				 */
				PnrRtParam pnrRtParam = new PnrRtParam();
				pnrRtParam.setShbh(shbh);
				pnrRtParam.setUserid(yhb.getPidyh());
				pnrRtParam.setPassword(yhb.getMm());
				pnrRtParam.setUrl("http://" + jpPz.getPzIp() + ":"+ jpPz.getPzPort());
				pnrRtParam.setOfficeId(jpPz.getOfficeid());
				pnrRtParam.setPnrno(jpKhdd.getCgPnrNo());
				
				Pnr pnrObject = null;
				try {
					pnrObject = IbeService.rtPnr(pnrRtParam);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (pnrObject == null) {
					continue;
				}

				List<PnrCjr> pnrcjrList = pnrObject.getCjrlist();
				if (CollectionUtils.isEmpty(pnrcjrList)) {
					continue;
				}
				
				Map<String, String> cjrTkno = new HashMap<String, String>();
				
				for (PnrCjr pc : pnrcjrList) {
					String Tk = StringUtils.trimToEmpty(pc.getTkno());
					if (StringUtils.isNotBlank(Tk) && Tk.length() == 13) {
						pc.setTkno(Tk.substring(0, 3) + "-"+ Tk.substring(3, 13));
					}
					String key = pc.getCjr();
					String tkno = cjrTkno.get(key);
					cjrTkno.put(key,(StringUtils.isBlank(tkno) ? "" : tkno+"," )+pc.getTkno());
				}
				
				System.out.println("PNR:" + pnrRtParam.getPnrno()+ "=>解析票号内容：[" + cjrTkno + "]");
				for (int i = 0, cjrsize = jpKhddCjrList.size(); i < cjrsize; i++) {
					JpKhddCjr jkc = jpKhddCjrList.get(i);
					String key = TpXeZw.getCjrlx(jkc.getCjr(),jkc.getCjrlx());
					jkc.setTkno(VeStr.getValue(cjrTkno, key));
				}
			
				// 订单节点
				Map<String, Object> paramMap = new HashMap<String, Object>();
				Map<String, Object> publicMap = new HashMap<String, Object>();
				publicMap.put("DATAFROM", "手工转机票");
				publicMap.put("DDBH", ddbh);
				publicMap.put("SHBH", yhb.getShbh());
				publicMap.put("CP_YHBH", yhb.getBh());
				publicMap.put("CP_BMBH", yhb.getShbmid());
				publicMap.put("GNGJ", "1");
				publicMap.put("CP_OFFICEID", jpoffice.getOfficeid());
				publicMap.put("CGLY", cgly);
				publicMap.put("CG_PNR_NO", jpKhdd.getCgPnrNo());
				publicMap.put("CG_HKGS_PNR", jpKhdd.getCgHkgsPnr());
				publicMap.put("CGKM", jpoffice.getBopcgkm());
				paramMap.put("PUBLIC", publicMap);
				String hcids="";
				for(JpKhddHd hd :khddHdList){
					hcids += (StringUtils.isNotBlank(hcids) ? "," : "")+ hd.getId();
				}
				// 乘机人节点
				List<Map<String, Object>> cjrList = new ArrayList<Map<String, Object>>();
				for (int i = 0, cjrsize = jpKhddCjrList.size(); i < cjrsize; i++) {
					Map<String, Object> cjrMap = new HashMap<String, Object>();
					JpKhddCjr cjr = jpKhddCjrList.get(i);
					cjrMap.put("CJRID", cjr.getId());
					List<Map<String, Object>> hcList = new ArrayList<Map<String, Object>>();
					Map<String, Object> hcMap = new HashMap<String, Object>();
					hcMap.put("HC", hcids);
					hcMap.put("TKNO", cjr.getTkno());
					hcMap.put("CG_ZDJ", cjr.getCgZdj());
					hcMap.put("CG_PJ", cjr.getCgPj());
					hcMap.put("CG_JSF", cjr.getCgJsf());
					hcMap.put("CG_TAX", cjr.getCgTax());
					hcMap.put("XS_PJ", cjr.getXsPj());
					hcMap.put("XS_JSF", cjr.getXsJsf());
					hcMap.put("XS_TAX", cjr.getXsTax());
					hcMap.put("XS_ZDJ", cjr.getXsZdj());
					hcMap.put("XS_JE", cjr.getXsJe());
					hcMap.put("LXKP", "0");
					hcList.add(hcMap);
					cjrMap.put("TK", hcList);
					cjrList.add(cjrMap);
				}
				paramMap.put("CJR", cjrList);
				this.pkgZjpSgServiceImpl.sgZjp(paramMap);
			}
			ddMap.put("CODE", "0");
			ddMap.put("MSG","批量订单处理完成!");
		} catch (Exception e) {
			logger.error("批量完成订单失败", e);
			e.printStackTrace();
			ddMap.put("CODE", "-1");
			ddMap.put("MSG", "批量完成订单失败! "+e.getMessage());
		}
		return ddMap;
	}
	/**
	 * 更新订单状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping
	public String updateDdzt(@RequestParam(value = "id", defaultValue = "") String id,  @RequestParam(value = "ddzt", defaultValue = "") String ddzt){
		try{
			JpKhdd jpkhdd = new JpKhdd();
			Shyhb yhb =SessionUtils.getShshbSession();
			jpkhdd.setDdbh(id);
			jpkhdd.setShbh(yhb.getShbh());//商户编号必填
			jpkhdd = jpKhddServiceImpl.getEntityById(jpkhdd);//查询id对应的对象是否存在
			if(!DictEnum.JPDDZT.YWC.getValue().equals(jpkhdd.getDdzt())){
				jpkhdd.setDdzt(ddzt);
				jpKhddServiceImpl.update(jpkhdd);
			}
			return "1";
		}catch(Exception e){
			logger.error("更新订单状态失败", e);
			return "0";
		}
	}
	
	/**
	 * 转机票保存
	 */
	@RequestMapping(value = "saveZjp1")
	public String saveZjp1(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		StringBuffer html = new StringBuffer();
		html.append("<html><head>");
		html.append("</head><body>");
		HtmlRender.renderHtml(response, html.toString());
		try{
			// service
			String type = request.getParameter("type");// 1只转机票 �?转机票并交票
			String ddbh = StringUtils.trimToEmpty(request.getParameter("ddbh"));
			Shyhb yhb =SessionUtils.getShshbSession();
			JpKhdd jp_khdd = new JpKhdd();
			jp_khdd.setShbh(yhb.getShbh());
			jp_khdd.setDdbh(ddbh);
			
			if ("1".equals(type) || "2".equals(type)) {
				html.append("<br>");
				html.append("<div style=\"float:left;padding-left:10px; width:100%;font-size:15;\">");
				html.append("<span id=\"msg\"><img src='/static/images/wdimages/load.gif' /> 正在转机票，请稍�?..</span>");
				html.append("</div>");
				HtmlRender.renderHtml(response, html.toString());
	
				// 订单参数
				String tbddpj = StringUtils.trimToEmpty(request.getParameter("tbddpj"));
				String pnr_hcglgj = StringUtils.trimToEmpty(request.getParameter("pnr_hcglgj"));
				String sjjlfs = StringUtils.trimToEmpty(request.getParameter("sjjlfs"));
				String cplx = StringUtils.trimToEmpty(request.getParameter("cplx"));
				String zffs = StringUtils.trimToEmpty(request.getParameter("zffs"));
				String zfkm = StringUtils.trimToEmpty(request.getParameter("zfkm"));
				String wcpdw = StringUtils.trimToEmpty(request.getParameter("wcpdw"));
				String cpyid = StringUtils.trimToEmpty(request.getParameter("cpyid"));
				String jsfl = StringUtils.trimToEmpty(request.getParameter("jsfl"));
				String cgje = StringUtils.trimToEmpty(request.getParameter("cgje"));
				if (StringUtils.isNotBlank(jsfl) && StringUtils.isNotBlank(cgje)) {
					if (!NumberUtils.isNumber(cgje) || !NumberUtils.isNumber(jsfl)) {
						throw new Exception("转机票，订单号【" + ddbh + "】 ,传入采购金额：【" + cgje + "】，结算费率：" + "【"+ jsfl + "】");
					}
				}
				String gjtax = StringUtils.trimToEmpty(request.getParameter("gjtax"));
				String opzdj = StringUtils.trimToEmpty(request.getParameter("opzdj"));
				String workno = StringUtils.trimToEmpty(request.getParameter("workno"));
				String printno = StringUtils.trimToEmpty(request.getParameter("printno"));
				//直销整改 增加币种
				String ybbz = StringUtils.trimToEmpty(request.getParameter("wb_bz"));
				String ybcgj = StringUtils.trimToEmpty(request.getParameter("ybcgj"));
				
				String ybtax = StringUtils.trimToEmpty(request.getParameter("ybtax"));
				String airkhh = StringUtils.trimToEmpty(request.getParameter("airkhh"));
				if(StringUtils.isBlank(airkhh)){
					if("BPET".equals(cplx) || "OP".equals(cplx)){
						airkhh = StringUtils.trimToEmpty(request.getParameter("airkhh1"));
					} else {
						airkhh = StringUtils.trimToEmpty(request.getParameter("airkhh2"));
					}
				}
				String officeid = StringUtils.trimToEmpty(request.getParameter("officeid"));
				if(StringUtils.isBlank(officeid)){
					if("BPET".equals(cplx)){
						officeid = StringUtils.trimToEmpty(request.getParameter("officeid1"));
					} else if ("GP".equals(cplx)){
						officeid = StringUtils.trimToEmpty(request.getParameter("officeid2"));
					}
				}
				String ekyh_bs = StringUtils.trimToEmpty(request.getParameter("ekyh_bs"));
				String hkgs_pnr = StringUtils.trimToEmpty(request.getParameter("hkgs_pnr"));
				String ekkh = StringUtils.trimToEmpty(request.getParameter("ekkh"));
				String cfcity = StringUtils.trimToEmpty(request.getParameter("cfcity"));
				String ddcity = StringUtils.trimToEmpty(request.getParameter("ddcity"));
				String hbh = StringUtils.trimToEmpty(request.getParameter("hbh"));
				String cw = StringUtils.trimToEmpty(request.getParameter("cw"));
				String cfrq = StringUtils.trimToEmpty(request.getParameter("cfrq"));
				String cfsj = StringUtils.trimToEmpty(request.getParameter("cfsj"));
				String pj = StringUtils.trimToEmpty(request.getParameter("pj"));
				String fop = StringUtils.trimToEmpty(request.getParameter("fop"));
	
				String ddly = StringUtils.trimToEmpty(request.getParameter("ddly"));
				String newpnr = StringUtils.trimToEmpty(request.getParameter("newpnr")).toUpperCase();
				String newhkgs_pnr = StringUtils.trimToEmpty(request.getParameter("newhkgs_pnr")).toUpperCase();
				String jsfl_ec = StringUtils.trimToEmpty(request.getParameter("jsfl_ec"));
				String bzbz=StringUtils.trimToEmpty(request.getParameter("bzbz"));//增加备注
				String fkdh=StringUtils.trimToEmpty(request.getParameter("zflsh"));//支付流水�?
				String hkgs_ddbh=StringUtils.trimToEmpty(request.getParameter("hsddh"));//航司订单�?
				// 校验数据准确性
				if ("OP".equals(cplx) || "BPET".equals(cplx) || "GP".equals(cplx)) {
					if (StringUtils.isBlank(zffs) || StringUtils.isBlank(zfkm)) {
						this.closeErrorToPage(response, "支付方式或科目不能为空");
						return null;
					}
				}
				if ("OP".equals(cplx)) {
					if (StringUtils.isBlank(wcpdw)) {
						this.closeErrorToPage(response, "外出票单位不能为空");
						return null;
					}
				}
				if ("BSPET".equals(cplx)) { // TODO 解决网店导单手工转机票时票证类型为BSPET，可能会传入支付科目和支付方�?
					zffs = ""; zfkm = "";
				}
	
				// 页面费率为带%号，数据库要去掉
				Double pj_xjjsfl = NumberUtils.toDouble(jsfl);
				jsfl = Arith.div(pj_xjjsfl, 100, 4) + "";
	
				// 订单节点
				StringBuffer xml = new StringBuffer("<SQL><PUBLIC>");
				xml.append(XmlUtils.xmlnode("DDBH", ddbh));
				xml.append(XmlUtils.xmlnode("PNR_HCGLGJ", pnr_hcglgj));
				xml.append(XmlUtils.xmlnode("SJJLFS", sjjlfs));
				xml.append(XmlUtils.xmlnode("CPLX", cplx));
				if(StringUtils.isNotBlank(bzbz)){
					xml.append(XmlUtils.xmlnode("BZBZ", bzbz));
				}
				if (StringUtils.isNotBlank(zffs)) {
					xml.append(XmlUtils.xmlnode("CGZFFS", zffs));
				}
				if (StringUtils.isNotBlank(zfkm)) {
					xml.append(XmlUtils.xmlnode("CGZFKM", zfkm));
				}
				if (StringUtils.isNotBlank(wcpdw)) {
					xml.append(XmlUtils.xmlnode("WCPDW", wcpdw));
				}
				if (StringUtils.isNotBlank(cpyid)) {
					xml.append(XmlUtils.xmlnode("CPYID", cpyid));
				}
				if(StringUtils.isNotBlank(jsfl_ec)){
					xml.append(XmlUtils.xmlnode("JS_HXFL_EC", Arith.div(NumberUtils.toDouble(jsfl_ec), 100)));
				}
				if(!"BSPET".equals(cplx)){
					xml.append(XmlUtils.xmlnode("JSFL", jsfl));				
				}
				if("BPET".equals(cplx) ){
					xml.append(XmlUtils.xmlnode("FKDH", fkdh));
					xml.append(XmlUtils.xmlnode("HKGS_DDBH", hkgs_ddbh));
				}
				
				if("OP".equals(cplx) ){
					xml.append(XmlUtils.xmlnode("FKDH", fkdh));
					xml.append(XmlUtils.xmlnode("HKGS_DDBH", hkgs_ddbh));
				}
				
				if (StringUtils.isNotBlank(cgje)) {
					xml.append(XmlUtils.xmlnode("CGJE", cgje));
				}
				if (StringUtils.isNotBlank(gjtax)) {
					xml.append(XmlUtils.xmlnode("GJTAX", gjtax));
				}
				if (StringUtils.isNotBlank(opzdj)) {
					xml.append(XmlUtils.xmlnode("OPZDJ", opzdj));
				}
				if (StringUtils.isNotBlank(workno)) {
					xml.append(XmlUtils.xmlnode("WORKNO", workno));
				}
				if (StringUtils.isNotBlank(printno)) {
					xml.append(XmlUtils.xmlnode("PRINTNO", printno));
				}
				xml.append(XmlUtils.xmlnode("TBDDPJ", tbddpj));
				//直销整改 传入外币 币种
				xml.append(XmlUtils.xmlnode("YBBZ", ybbz));
				if (StringUtils.isNotBlank(ybcgj)) {
					xml.append(XmlUtils.xmlnode("YBCGJ", ybcgj));
				}
				if (StringUtils.isNotBlank(ybtax)) {
					xml.append(XmlUtils.xmlnode("YBTAX", ybtax));
				}
			
				if (StringUtils.isNotBlank(airkhh)) {
					xml.append(XmlUtils.xmlnode("AIRKHH", airkhh));
				}
				if (StringUtils.isNotBlank(officeid)) {
					xml.append(XmlUtils.xmlnode("OFFICEID", officeid));
				}
				if ("BPET".equals(cplx) || ("GP".equals(cplx))) {
					if ("1".equals(ekyh_bs)) {
						xml.append(XmlUtils.xmlnode("EKYH_BS", "1"));
						xml.append(XmlUtils.xmlnode("HKGS_PNR", StringUtils.trimToEmpty(hkgs_pnr)));
						xml.append(XmlUtils.xmlnode("EKYH_KH", StringUtils.trimToEmpty(ekkh)));
						xml.append(XmlUtils.xmlnode("EKYH_HC", StringUtils.trimToEmpty(cfcity)
								+ StringUtils.trimToEmpty(ddcity)));
						xml.append(XmlUtils.xmlnode("EKYH_HBH", StringUtils.trimToEmpty(hbh)));
						xml.append(XmlUtils.xmlnode("EKYH_CW", StringUtils.trimToEmpty(cw)));
						xml.append(XmlUtils.xmlnode("EKYH_CFSJ", cfrq + " " + cfsj.substring(0, 2) + ":"
								+ cfsj.substring(2)));
						xml.append(XmlUtils.xmlnode("EKYH_PJ", StringUtils.trimToEmpty(pj)));
					} else {
						xml.append(XmlUtils.xmlnode("EKYH_BS", "0"));
					}
				}
				xml.append(XmlUtils.xmlnode("OP_USERID", yhb.getBh()));
				if ("7".equals(ddly)) {
					if (StringUtils.isBlank(newpnr)) {
						this.closeErrorToPage(response, "您�?择了换PNR出票，请输入新PNR");
						return null;
					}
					xml.append(XmlUtils.xmlnode("DDLY", ddly));
					xml.append(XmlUtils.xmlnode("NEWPNR", newpnr));
					xml.append(XmlUtils.xmlnode("NEWHKGS_PNR",newhkgs_pnr));
				}
				xml.append(XmlUtils.xmlnode("FOP", fop));
				xml.append("</PUBLIC>");
	
				// 判断国内国际
				String zjptype = StringUtils.trimToEmpty(request.getParameter("zjptype"));
				if ("gjzjp".equals(zjptype)) {
					xml.append(this.getGjZjpXml(request));
				} else {
					try {
						xml.append(this.getZjpXml(request));
					} catch (Exception e) {
						e.printStackTrace();
						this.closeErrorToPage(response, e.getMessage());
						return null;
					}
				}
				xml.append("</SQL>");
	
				// 黑屏检查同步机票价格
				String procname = "pkg_getdata.f_zjp_sg";
				if ("true".equals(tbddpj)) {
					procname = "pkg_getdata.f_zjp_xg";
				}
//				String sql = "{?o" + OracleTypes.INTEGER + "=call " + procname + "(?" + OracleTypes.CLOB + ",?o"
//						+ OracleTypes.VARCHAR + ")}";
				List<Object> param = new ArrayList<Object>();
				param.add(xml.toString());
				List slist = null;
				logger.error("转机票，订单号【" + ddbh + "】 调用后台接口【" + procname + "】");
				try {
//					slist = tmp_service.executeProcedure(sql, param);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("转机票错误，订单号【" + ddbh + "】 调用后台接口【" + procname + "】", e);
					this.closeErrorToPage(response, "转机票失败，调用转机票接口错误，请联系管理员！");
					return null;
				}
				if (slist == null || slist.size() != 2) {
					logger.error("转机票错误，订单号【" + ddbh + "】 调用后台接口【" + procname + "】");
					this.closeErrorToPage(response, "转机票失败，请联系管理员！");
					return null;
				}
				int rtn = Integer.parseInt(String.valueOf(slist.get(0)));
				if (rtn != 0) {
					this.closeErrorToPage(response, "转机票失败，原因：" + slist.get(1));
					return null;
				}
				html = new StringBuffer();
				html.append("<script type=\"text/javascript\">");
				html.append("document.getElementById(\"msg\").innerHTML = \"转机票成功\";");
				html.append("document.getElementById(\"msg\").style.fontSize = \"15\";");
				html.append("</script>");
				HtmlRender.renderHtml(response, html.toString());
				// 取订单信息 下面用订单判断是否交票 移动地方需要注意//2012-10-27 记录detr异动日志时有用到订单bean对象
				try {
					jp_khdd = this.baseService.getEntityById(jp_khdd);
				} catch (Exception e) {
					e.printStackTrace();
					this.closeErrorToPage(response, "获取订单信息出错，请联系管理员！");
					return null;
				}
				// 2012-10-26转机票成功后记录异动日志（记录detr信息和页面传入后台的相关信息�?
				// 有调用修改的过程才记录
	//			if ("true".equals(tbddpj)) {
	//				saveYdrz(request, jp_khdd);
	//			}
			}
	
			String error = "";
			String dd_ddbh = StringUtils.trimToEmpty(request.getParameter("dd_ddbh")); // 调度用到订单编号
	
			//交票
			if (StringUtils.isBlank(type) || "2".equals(type)) {
	
				html = new StringBuffer();
				if (StringUtils.isBlank(type)) {
					html.append("<br>");
					html.append("<div style=\"float:left;padding-left:10px; width:100%;font-size:15;\">");
					html.append("<span id=\"msg\"><img src='/static/images/wdimages/load.gif' /> 正在交票，请稍候...</span>");
					html.append("</div>");
				} else {
					html.append("<script type=\"text/javascript\">");
					html.append("document.getElementById(\"msg\").innerHTML = \"<img src='/static/images/wdimages/load.gif' />"
							+ " 转机票成功，正在交票，请稍候...\";");
					html.append("document.getElementById(\"msg\").style.fontSize = \"15\";");
					html.append("</script>");
				}
				HtmlRender.renderHtml(response, html.toString());
	
				String jpxml = "";
				if (jp_khdd != null) {// 转机票只能对一个订单交票
					jpxml = this.getXML(jp_khdd, yhb);
				} else {// 交票可以对多个订单交票
					jpxml = this.getXML(request, yhb);
				}
	
//				String sql = "{call PKG_QUERY_NEW_5000.p_yy_jp(?" + OracleTypes.VARCHAR + ",?o" + OracleTypes.CURSOR
//						+ ",?o" + OracleTypes.VARCHAR + ")}";
				List param = new ArrayList<Object>();
				param.add(jpxml);
				logger.error("交票错误，订单号【无关键字】 调用后台接口【PKG_QUERY_NEW_5000.p_yy_jp】", jpxml);
				List slist = null;
				try {
//					slist = tmp_service.executeProcedure(sql, param);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("交票错误，订单号【无关键字】 调用后台接口【PKG_QUERY_NEW_5000.p_yy_jp】",e);
					this.closeErrorToPage(response, "交票失败！");
				}
				List elist = null;
				if (slist != null && !slist.isEmpty()) {
					elist = (List) slist.get(0); // 得到返回没有交票成功的集合；
					error = (String) slist.get(1);
				}
	
				String tddbh = StringUtils.trimToEmpty(request.getParameter("tddbh")); // 团订单监控转机票
				String sddlx = StringUtils.trimToEmpty(request.getParameter("sddlx")); // 团类型标�?
				// 将没有交票的pnr返回
				String jp_fail = "";
				if (StringUtils.isNotBlank(error)) {
					html = new StringBuffer();
					html.append("<br><div style=\"float:left;padding-left:10px; width:100%;font-size:15;\">");
					html.append("<script type=\"text/javascript\">");
					if (!"OK".equals(error)) {
						logger.error("交票未成功，订单号【无关键字】 调用后台接口【PKG_QUERY_NEW_5000.p_yy_jp】");
						html.append("document.getElementById(\"msg\").innerHTML = \"以下为交票不成功PNR\";");
					} else if ("OK".equals(error)) {
						html.append("document.getElementById(\"msg\").innerHTML = \"交票成功\";");
					}
					html.append("document.getElementById(\"msg\").style.fontSize = \"15\";");
					html.append("</script>");
					Iterator itr = elist.iterator();
					while (itr != null && itr.hasNext()) {
						Map m = (Map) itr.next();
						ddbh = (String) m.get("DDBH");
						String zt = (String) m.get("ZT");
						String pnrno = (String) m.get("PNRNO");
						String errors = StringUtils.trimToEmpty((String) m.get("ERROR"));
	
						if ("A".equals(zt)) {
							// 未出票或部分出票
							html.append("<span><a href=\"/asms/cpzx/ticket_zjp.shtml?&ddbh=" + ddbh + "&tddbh=" + tddbh
									+ "&sddlx=" + sddlx + "\" target=\"_parent\" title=\"点击链接转机票页面\">" + pnrno
									+ "</a>   <font color='red'>" + errors + "</font></span><br>");
							jp_fail = jp_fail + "," + ddbh;
						} else if ("B".equals(zt)) {
							// 订单上的金额与其对应的所有机票总金额不一致
							html.append("<span>" + pnrno + "   <font color='red'>" + errors + "</font></span><br>");
							jp_fail = jp_fail + "," + ddbh;
						} else if ("C".equals(zt)) {
							// 版本冲突
							html.append("<span>" + pnrno + "   <font color='red'>" + errors + "</font></span><br>");
							jp_fail = jp_fail + "," + ddbh;
						} else if ("D".equals(zt)) {
							// 未知错误
							html.append("<span>" + pnrno + "   <font color='red'>" + errors + "</font></span><br>");
							jp_fail = jp_fail + "," + ddbh;
						} else if ("W".equals(zt)) {
							html.append("<span>" + pnrno + "   <font color='green'>" + errors + "</font></span><br>");
						}
					}
					html.append("</div><br>");
					HtmlRender.renderHtml(response, html.toString());
				}
				// 团订单交票 跳转
				if (StringUtils.isNotBlank(tddbh)) {
					html = new StringBuffer();
					html.append("<br>");
					html.append("<div style=\"float:left;padding-left:10px;padding-top:10px; width:100%;font-size:15;\">");
					html.append("<span style=\"float:right;padding-right:10px;\">"
							+ "<a href=\"/asms/ydzx/ddgl/kh_khdd_tddjk_list.shtml?p=getZddList&sddlx=" + sddlx + "&tddbh="
							+ tddbh + "\"  target=\"_parent\"> 确认并返回 </a></span>");
					html.append("</div>");
					HtmlRender.renderHtml(response, html.toString());
					return null;
				}

			}
	
			String firstddbh = "";
			if (jp_khdd != null) {
				firstddbh = jp_khdd.getDdbh();
			} else {
				firstddbh = getFirstDDBH(request);
			}
	
			// 其他地方跳转
			html = new StringBuffer();
			html.append("<br>");
			html.append("<div style=\"float:left;padding-left:10px;padding-top:10px; width:100%;font-size:15;\">");
			html.append("<span style=\"float:right;padding-right:10px;\">请仔细核对以上信息</span>");
			html.append("</div>");
			html.append("<br>");
			html.append("<div style=\"float:left;padding-left:10px;padding-top:10px; width:100%;font-size:15;\">");
			HttpSession session = request.getSession();
			String cpkzurl = (String) session.getAttribute("cpkzurl");
			cpkzurl = VeStr.Replace(cpkzurl, "dd_ddbh", "ss_ddbh");
			html.append("<span style=\"float:right;padding-right:10px;\">");
			if (StringUtils.isNotBlank(cpkzurl)) {
				html.append("<a href=\"" + cpkzurl + "\"  target=\"_parent\">确认并返回出票控制台</a>");
			} else {
				html.append("<a href=\"/asms/cpzx/ticket_cpkz.shtml?tag=1&yw_type=b1\" target=\"_parent\">确认并返回出票控制台</a>");
			}
			html.append("</span>");
			html.append("</div>");
			if (StringUtils.isBlank(dd_ddbh)) {
				dd_ddbh = ddbh;
			}
			if ("OK".equals(error) && StringUtils.isNotBlank(dd_ddbh)) {
				html.append("<br>");
				html.append("<div style=\"float:left;padding-left:10px;padding-top:10px; width:100%;font-size:15;\">");
				html.append("<span style=\"float:right;padding-right:10px;\">");
				if (StringUtils.isNotBlank(cpkzurl)) {
					html
							.append("<a href=\"" + cpkzurl + "&dd_ddbh=" + dd_ddbh
									+ "\"  target=\"_parent\">确认，返回出票控制台并调�?/a>");
				} else {
					html.append("<a href=\"/asms/cpzx/ticket_cpkz.shtml?tag=1&yw_type=b1&dd_ddbh=" + dd_ddbh
							+ "\" target=\"_parent\">确认，返回出票控制台并调�?/a>");
				}
				html.append("</span>");
				html.append("</div>");
			}
			html.append("<br>");
			html.append("<div style=\"float:left;padding-left:10px;padding-top:10px; width:100%;font-size:15;\">");
			html
					.append("<span style=\"float:right;padding-right:10px;\"><a href=\"/asms/crawl/operator.shtml?crawloperator=CpOrder&crawlsiteName=Jinri&ddbh="
							+ firstddbh + "\"  target=\"_blank\"> 平台交票 </a></span>");
			html.append("</div>");
			html.append("</body></html>");
			HtmlRender.renderHtml(response, html.toString());
		} catch (Exception e){
			logger.error("", e);
		}
		return null;
	}
	
	private String getZjpXml(HttpServletRequest request) throws Exception {
		StringBuffer xml = new StringBuffer();
		String[] cjrid = request.getParameterValues("cjrid");
		String lxkp = StringUtils.trimToEmpty(request.getParameter("lxkp"));
		String ddbh = StringUtils.trimToEmpty(request.getParameter("ddbh"));
		if (cjrid == null || cjrid.length == 0) {
			throw new Exception("转机票失败，参数错误，请联系管理员！");
		}
		for (String one_cjrid : cjrid) {
			String[] tkno = request.getParameterValues("tkno");
			if (tkno == null) {
				continue;
			}
			xml.append("<CJR>");
			xml.append(XmlUtils.xmlnode("CJRID", one_cjrid));
			String[] hc = request.getParameterValues("hc" + one_cjrid);
			String[] hcid = request.getParameterValues("hcid_" + one_cjrid);
			String[] pj_cgj = request.getParameterValues("pj_cgj_" + one_cjrid);
			String[] pj_tax = request.getParameterValues("pj_tax_" + one_cjrid);
			String[] pj_jsf = request.getParameterValues("pj_jsf_" + one_cjrid);
			String[] bxfs = request.getParameterValues("bxfs_" + one_cjrid);
			String[] pj_khlk = request.getParameterValues("pj_khlk_" + one_cjrid);
			String[] pj_jj = request.getParameterValues("pj_jj_" + one_cjrid);
			String[] detr = request.getParameterValues("detr_" + one_cjrid);

			if (tkno == null) {
				throw new Exception("转机票失败，票号不正确，请联系管理员！");
			}
			int len = tkno.length;
			if (hc == null || hc.length != len || pj_cgj == null || pj_cgj.length != len || pj_tax == null
					|| pj_tax.length != len || pj_jsf == null || pj_jsf.length != len || bxfs == null
					|| bxfs.length != len) {
				throw new Exception("转机票失败，参数错误，请联系管理员！");
			}
			String firstTkno = tkno[0];
			String lastTkno = tkno[tkno.length - 1];
			boolean ck1 = false;
			boolean ck2 = false;
			for (int i = 0; i < tkno.length; i++) {
				String onetkno = StringUtils.trimToEmpty(tkno[i]);
				if (StringUtils.isNotBlank(onetkno) && onetkno.length() > 3) {
					if (i == 0) {
						firstTkno = StringUtils.trimToEmpty(onetkno);
					}
					xml.append("<TK>");
					xml.append(XmlUtils.xmlnode("TKNO", onetkno));

					xml.append(XmlUtils.xmlnode("HC", hcid[i]));
					xml.append(XmlUtils.xmlnode("PJ_CGJ", StringUtils.trimToEmpty(pj_cgj[i])));
//					xml.append(XmlUtils.xmlnode("PJ_XSJ", xsj));
					xml.append(XmlUtils.xmlnode("PJ_TAX", NumberUtils.toDouble(StringUtils.trimToEmpty(pj_tax[i]))));
					xml.append(XmlUtils.xmlnode("PJ_JSF", NumberUtils.toDouble(StringUtils.trimToEmpty(pj_jsf[i]))));
					xml.append(XmlUtils.xmlnode("BXFS", NumberUtils.toInt(StringUtils.trimToEmpty(bxfs[i]))));
					if (!"1".equals(lxkp)) {
						xml.append(XmlUtils.xmlnode("LXKP", ""));
					} else {
						xml.append(XmlUtils.xmlnode("LXKP", firstTkno.substring(3) + "/" + lastTkno.substring(11)));
					}
					xml.append(XmlUtils.xmlnode("CONTENT", detr[i]));
					xml.append("</TK>");

					if (!ck1 && NumberUtils.toDouble(pj_cgj[i]) > 0) {
						ck1 = true;
					}

					if (ck1 && NumberUtils.toDouble(pj_cgj[i]) == 0) {
						ck2 = true;
					}

					if (!"1".equals(lxkp)) {
						if (ck1 && ck2) {
							throw new Exception("<font size=4>分离航段出票时，请填写每张票的账单价�?/font>");
						}
					}
				}
			}
			xml.append("</CJR>");
		}
		return xml.toString();
	}
	
	private String getGjZjpXml(HttpServletRequest request) throws Exception {
		StringBuffer xml = new StringBuffer();
		String lxkp = StringUtils.trimToEmpty(request.getParameter("lxkp"));
		String[] cjrid = request.getParameterValues("cjrid");
		String[] wcpcount = request.getParameterValues("wcpcount");
		if (cjrid == null || cjrid.length == 0) {
			throw new Exception("转机票失败，参数错误，请联系管理员！");
		}
		int index = 0;
		for (String one_cjrid : cjrid) {
			if (wcpcount != null) {
				if ("0".equals(wcpcount[index])) {
					index++;
					continue;
				}
			}
			xml.append("<CJR>");
			xml.append(XmlUtils.xmlnode("CJRID", one_cjrid));
			String[] tkno = request.getParameterValues("tkno_" + one_cjrid);
			String[] hc = request.getParameterValues("hc_" + one_cjrid);
			String[] hcid = request.getParameterValues("hcid_" + one_cjrid);
			String[] pj_cgj = request.getParameterValues("pj_cgj_" + one_cjrid);
			String[] pj_tax = request.getParameterValues("pj_tax_" + one_cjrid);
			String[] bxfs = request.getParameterValues("bxfs_" + one_cjrid);
			String[] pj_bx = request.getParameterValues("pj_bx_" + one_cjrid);
			String[] pj_khlk = request.getParameterValues("pj_khlk_" + one_cjrid);
			String[] pj_jj = request.getParameterValues("pj_jj_" + one_cjrid);
			String[] pj_cgje = request.getParameterValues("pj_cgje_" + one_cjrid);
			String[] pj_cghf = request.getParameterValues("pj_cghf_" + one_cjrid);
			String[] pj_spa = request.getParameterValues("pj_spa_" + one_cjrid);
			String[] pj_mj = request.getParameterValues("pj_mj_" + one_cjrid);
			String[] returntoinvalidate = request.getParameterValues("returntoinvalidate_" + one_cjrid);
			String[] pj_sjjsfl = request.getParameterValues("pj_sjjsfl_" + one_cjrid);
			String[] detr = request.getParameterValues("detr_" + one_cjrid);
			String[] pj_hidediscount = request.getParameterValues("pj_hidediscount_" + one_cjrid);
			String[] qt2 = request.getParameterValues("qt2_" + one_cjrid);
			if (tkno == null) {
				throw new Exception("转机票失败，票号不正确，请联系管理员！");
			}
			int len = tkno.length;
			if (hc == null || hc.length != len || pj_cgj == null || pj_cgj.length != len || pj_tax == null
					|| pj_tax.length != len || bxfs == null || bxfs.length != len || pj_bx == null
					|| pj_bx.length != len) {
				throw new Exception("转机票失败，参数错误，请联系管理员！");
			}
			String firstTkno = tkno[0];
			String lastTkno = tkno[tkno.length - 1];
			for (int i = 0; i < tkno.length; i++) {
				String onetkno = StringUtils.trimToEmpty(tkno[i]);
				if (StringUtils.isNotBlank(onetkno) && onetkno.length() > 3) {
					if (i == 0) {
						firstTkno = StringUtils.trimToEmpty(onetkno);
					}
					xml.append("<TK>");
					xml.append(XmlUtils.xmlnode("TKNO", onetkno));
					// <HC>PEKWUH,WUHSZX</HC><CFSJ>2011-11-30 07:15,2011-12-01 07:30</CFSJ>
					xml.append(XmlUtils.xmlnode("HC", hcid[i]));
					xml.append(XmlUtils.xmlnode("PJ_CGJ", StringUtils.trimToEmpty(pj_cgj[i])));
					xml.append(XmlUtils.xmlnode("PJ_JSF", "0"));
					xml.append(XmlUtils.xmlnode("PJ_TAX", NumberUtils.toDouble(StringUtils.trimToEmpty(pj_tax[i]))));
					xml.append(XmlUtils.xmlnode("PJ_BX", NumberUtils.toDouble(StringUtils.trimToEmpty(pj_bx[i]))));
					xml.append(XmlUtils.xmlnode("BXFS", NumberUtils.toInt(StringUtils.trimToEmpty(bxfs[i]))));
					xml.append(XmlUtils.xmlnode("JJ", NumberUtils.toDouble(StringUtils.trimToEmpty(pj_jj[i]))));
					xml.append(XmlUtils.xmlnode("KHLK", NumberUtils.toDouble(StringUtils.trimToEmpty(pj_khlk[i]))));
					xml.append(XmlUtils.xmlnode("HIDEDISCOUNT", StringUtils.trimToEmpty(pj_hidediscount[i])));
					xml.append(XmlUtils.xmlnode("CGJE", StringUtils.trimToEmpty(pj_cgje[i])));
					if (pj_mj != null && pj_mj.length > 0) {
						xml.append(XmlUtils.xmlnode("PJ_MJ", StringUtils.trimToEmpty(pj_mj[i])));
					}
					if (pj_spa != null) {
						xml.append(XmlUtils.xmlnode("SPA", StringUtils.trimToEmpty(pj_spa[i])));
					}
					if (returntoinvalidate != null) {
						xml.append(XmlUtils.xmlnode("RETURNTOINVALIDATE", StringUtils
								.trimToEmpty(returntoinvalidate[i])));
					}
					// 代理费率
					Double sjjsfl = NumberUtils.toDouble(StringUtils.trimToEmpty(pj_sjjsfl[i]));
					sjjsfl = Arith.div(sjjsfl, 100, 3);
					xml.append(XmlUtils.xmlnode("SJJSFL", sjjsfl));
					// 後返
					Double js_hxfl_ec = NumberUtils.toDouble(StringUtils.trimToEmpty(pj_cghf[i]));
					js_hxfl_ec = Arith.div(js_hxfl_ec, 100, 3);
					xml.append(XmlUtils.xmlnode("JS_HXFL_EC", js_hxfl_ec));
					if (qt2 != null && qt2.length == len) {
						xml.append(XmlUtils.xmlnode("QT2", NumberUtils.toDouble(StringUtils.trimToEmpty(qt2[i]))));
					}
					if (!"1".equals(lxkp)) {
						xml.append(XmlUtils.xmlnode("LXKP", ""));
					} else {
						xml.append(XmlUtils.xmlnode("LXKP", firstTkno.substring(3) + "/" + lastTkno.substring(11)));
					}
					xml.append(XmlUtils.xmlnode("CONTENT", detr[i]));
					xml.append("</TK>");
				}
			}
			xml.append("</CJR>");
			index++;
		}
		return xml.toString();
	}
	
	public String getFirstDDBH(HttpServletRequest request) {
		try {
			String jpparam = request.getParameter("jpparam");
			String[] param = jpparam.split(",");
			for (String one : param) {
				String[] t = one.split("\\|");
				return t[0];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	// 得到查询的SQL
		public String getXML(HttpServletRequest request, Shyhb user) {
			StringBuffer xml = new StringBuffer("<SQL>");
			String jpparam = request.getParameter("jpparam");
			String[] param = jpparam.split(",");
			for (String one : param) {
				String[] t = one.split("\\|");
				xml.append("<DH>");
				xml.append(XmlUtils.xmlnode("DDBH", t[0]));
				xml.append(XmlUtils.xmlnode("DDZT", t[1]));
				xml.append(XmlUtils.xmlnode("VERSION", t[2]));
				xml.append("</DH>");
			}
			xml.append(XmlUtils.xmlnode("COMPID", user.getShbh()));
			xml.append(XmlUtils.xmlnode("DEPTID", user.getBmmc()));
			xml.append(XmlUtils.xmlnode("USERID", user.getBh()));
			xml.append("</SQL>");

			return xml.toString();
		}

		// 得到查询的SQL
		public String getXML(JpKhdd jpKhdd, Shyhb user) {
			StringBuffer xml = new StringBuffer("<SQL>");
			xml.append("<DH>");
			xml.append(XmlUtils.xmlnode("DDBH", jpKhdd.getDdbh()));
			xml.append(XmlUtils.xmlnode("DDZT", jpKhdd.getDdzt()));
			xml.append("</DH>");
			xml.append(XmlUtils.xmlnode("COMPID", user.getShbh()));
			xml.append(XmlUtils.xmlnode("DEPTID", user.getBmmc()));
			xml.append(XmlUtils.xmlnode("USERID", user.getBh()));
			xml.append("</SQL>");
			return xml.toString();
		}
	
	public void closeErrorToPage(HttpServletResponse response, String content) {
		StringBuffer html = new StringBuffer();
		html.append("<script type=\"text/javascript\">");
		html.append("document.getElementById(\"msg\").innerHTML = \"");
		html.append(content).append("\";");
		html.append("document.getElementById(\"msg\").style.fontSize = \"15\";");
		html.append("</script>");
		html.append("<br>");
		html.append("<div style=\"float:left;padding-left:10px;padding-top:10px; width:100%;font-size:15;\">");
		html.append("<span style=\"float:right;padding-right:10px;\">请仔细核对以上信�?/span>");
		html.append("</div>");
		html.append("<br>");
		html.append("<div style=\"float:left;padding-left:10px;padding-top:10px; width:100%;font-size:15;\">");
		html.append("<span style=\"float:right;padding-right:10px;\">"
				+ "<a href=\"javascript:parent.layer.close(parent.layer.getFrameIndex());\" > �?�?</a></span>");
		html.append("</div>");
		HtmlRender.renderHtml(response, html.toString());
	}

	
    private  JpDdsz getJpDdsz(String wdid){
    	JpDdsz jpDdsz = new JpDdsz();
		jpDdsz.setWdid(wdid);
		List<JpDdsz> jpDdszList = jpDdszServiceImpl.getMyBatisDao().select(jpDdsz);
		if (CollectionUtils.isEmpty(jpDdszList)) {
			return null;//"没有找到导单设置信息";
		}
		jpDdsz = jpDdszList.get(0);
		if(!"1".equals(jpDdsz.getDdKqzcdd())){//如果查不到导单设置，或者导单设置没有开启
			return null;//"请检查导单设置是否开启";
		}
    	return jpDdsz;
    }
	
	
	@Override
	public void updateInitEntity(JpKhdd t) {}
	@Override
	public void insertInitEntity(JpKhdd t) {}
}
