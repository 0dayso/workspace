package cn.vetech.web.vedsb.jpddgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.editpnr.EditPnrParam;
import org.vetech.core.business.pid.api.pat.PatParam;
import org.vetech.core.business.pid.api.pat.PatParser;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.api.pnrauth.PnrAuth;
import org.vetech.core.business.pid.api.pnrauth.PnrAuthParam;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.api.rrtpnr.RrtPnrParam;
import org.vetech.core.business.pid.api.rtpnr.PNRParser;
import org.vetech.core.business.pid.api.rtpnr.PnrRtParam;
import org.vetech.core.business.pid.api.xepnr.XePnr;
import org.vetech.core.business.pid.api.xepnr.XePnrParam;
import org.vetech.core.business.pid.pidbean.AvCabinModel;
import org.vetech.core.business.pid.util.BookUtil;
import org.vetech.core.business.tag.Function;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shjcsj;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.base.ShjcsjServiceImpl;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpbcdgl.JPBcd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpDdBean;
import cn.vetech.vedsb.jp.entity.jpddgl.JpDdCjrBean;
import cn.vetech.vedsb.jp.entity.jpddgl.JpDdHdBean;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCjr;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpddsz.BookOrder;
import cn.vetech.vedsb.jp.entity.jpddsz.JpQz;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdHd;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpbcdgl.JpBcdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCjrServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpddsz.JpQzServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpzdbwServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpKhddController extends MBaseControl<JpKhdd, JpKhddServiceImpl>{
	private static final String PAGE_SIZE = "10";

	@Autowired
	private JpKhddCjrServiceImpl jpKhddCjrServiceImpl;
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired
	private JpJpServiceImpl jpJpServiceImpl;
	@Autowired
	private AttachService attachService;
	@Autowired
	private WdzlszServiceImpl wdzlszServiceImpl;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private JpBcdServiceImpl jpBcdServiceImpl;
	@Autowired
	private JpQzServiceImpl jpQzService;
	@Autowired
	private JpGqdHdServiceImpl jpGqdHdServiceImpl;
	@Autowired
	private ShjcsjServiceImpl shjcsjServiceImpl;
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
	@Autowired
	private JpzdbwServiceImpl jpzdbwServiceImpl;
	
	/**
 	 * 分页查询
	 */
	@RequestMapping(value = "query",method = RequestMethod.POST)
	public @ResponseBody Page quary(
			String[] cpzt,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue  = "desc") String sortType, 
			String sortName, 
			ModelMap model,
			HttpServletRequest request) {
			Shyhb user = SessionUtils.getShshbSession();
			Page page = null;
			try {
				Map<String, Object> params = Servlets.getParametersStartingWith(request, "",false);
				params.put("cpzt", cpzt);
				params.put("shbh", user.getShbh());
				params.put("start", pageSize*(pageNum-1));
				params.put("count", pageSize);
				model.addAttribute("sjrs", params.get("sjrs"));
				model.addAttribute("sjrz", params.get("sjrz"));
				page  = this.baseService.query(params, sortType);
				if(page!=null){
					List<?> list = page.getList();
					if(CollectionUtils.isNotEmpty(list)){
						attachService.wdzl("WDID").zfkm("SKKM").execute(list);
					}
				}
			} catch (Exception e) {
				logger.error("查询订单列表错误", e);
			}
			return page;
	}
	
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
		shzfkm.setSfqy("1");
		shzfkm.setShbh(user.getShbh());
		List<Shzfkm> zfkms=shzfkmServiceImpl.getShzfkmList(shzfkm);
		List<JpPz> pzList = jpPzServiceImpl.selectJpPzByShbh(user.getShbh());
		List<Shyhb> shyhb=shyhbServiceImpl.queryShyhbListBySh(user.getShbh());
		model.addAttribute("zfkms", zfkms);
		model.addAttribute("pzList", pzList);
		model.addAttribute("shyhbList", shyhb);
		return viewpath("wc_cpxx_cgly");
	}
	/**
	 * 锁单后继续操作
	 * @param ddbh
	 * @return
	 */
	@RequestMapping(value = "xgCpSdr",method = RequestMethod.POST)
	public @ResponseBody String xgCpSdr(@RequestParam(value = "ddbh") String ddbh,@RequestParam(value = "xgly")String xgly){
		return jiesuo(ddbh,"xgCpSdr",xgly);
	}
	/**
	 * 锁单后进行解锁
	 * @param ddbh
	 * @return
	 */
	@RequestMapping(value = "jpJieSuo",method = RequestMethod.POST)
	public @ResponseBody String jpJieSuo(@RequestParam(value = "ddbh") String ddbh,@RequestParam(value = "xgly")String xgly){
		return jiesuo(ddbh,"jpJieSuo",xgly);
	}
	
	/**
	 * 解锁方法
	 * @param ddbh
	 * @param user
	 * @param shyhb
	 * @return
	 */
	private String jiesuo(String ddbh,String ffm,String xgly){
		Shyhb user = SessionUtils.getShshbSession();
		String error="";
		String bh=user.getBh();
		try {
			JpKhdd jpkhdd=new JpKhdd();
			jpkhdd.setDdbh(ddbh);
			jpkhdd.setShbh(user.getShbh());
			jpkhdd=this.baseService.getKhddByDdbh(jpkhdd);
			if(jpkhdd != null){
				//当修改锁单人时将当前操作用户赋值
				if("xgCpSdr".equals(ffm)){
					jpkhdd.setCpSdr(bh);
				}else{
					jpkhdd.setCpSdr("");
				}
				//判断订单状态为已完成时，不改状态
				if(!DictEnum.JPDDZT.YWC.getValue().equals(jpkhdd.getDdzt())){
					jpkhdd.setDdzt(DictEnum.JPDDZT.YDZ.getValue());
					jpkhdd.setXgyh(bh);
					jpkhdd.setXgsj(VeDate.getNow());
					jpkhdd.setXgly(xgly);
					this.baseService.update(jpkhdd);
				}
			}
		} catch (Exception e) {
			logger.error("解除出票锁单人失败", e.getMessage());
			error="解锁失败";
		}
		return error;
	}
	/**
	 * 进入订单列表查询页面
	 */
	@RequestMapping(value = "viewlist")
	public String view(@RequestParam(value = "sjrs", defaultValue  = "") String sjrs, 
			@RequestParam(value = "sjrz", defaultValue  = "") String sjrz, 
			HttpServletRequest request,
			ModelMap model){
		String gngj = request.getParameter("gngj");
		if(StringUtils.isBlank(gngj)){//默认为国内
			gngj = "1";
		}
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("zt", "1");//启用
		Shyhb user = SessionUtils.getShshbSession();
		param.put("shbh", user.getShbh());
		
		if("0".equals(gngj)){
			param.put("ywlxs", new String[]{"1001902"});
		} else {
			param.put("ywlxs", new String[]{"1001901"});
		}
		List<Wdzlsz> wdzlszList = wdzlszServiceImpl.getWdZlszListByYwlx(param);
		
		model.addAttribute("wdzlszList", wdzlszList);
		
		if(StringUtils.isBlank(sjrs)){
			sjrs = VeDate.getStringDateShort();
		}
		if(StringUtils.isBlank(sjrz)){
			sjrz = VeDate.getStringDateShort();
		}
		model.addAttribute("sjrs", sjrs);
		model.addAttribute("sjrz", sjrz);
		return viewpath("list");
	}
	
	
	
	/**
	 * 跳转详页面
	 * @param bh
	 * @param zt
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail_{id}")
	public String detail(@PathVariable("id") String id, ModelMap model) {
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		try {
			if (StringUtils.isNotBlank(id)) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("ddbh", id);
				paramMap.put("shbh", shbh);
				//订单信息
				Map<String,Object> jpKhddMap=this.baseService.detail(paramMap);
				List<Map<String,Object>> jpKhddMapList=new ArrayList<Map<String,Object>>();
				jpKhddMapList.add(jpKhddMap);
				attachService.zfkm("SKKM").wdzl("WDID").execute(jpKhddMapList);
				model.addAttribute("jpkhdd", jpKhddMapList.get(0));
				
				//订单乘机人信息
				List<JpKhddCjr> khddCjrList = this.jpKhddCjrServiceImpl.getKhddCjrListByDDbh(id, shbh);
				model.addAttribute("jpkhddCjrList", khddCjrList);
				
				//订单航段信息
				List<JpKhddHd> khddHdList = this.jpKhddHdServiceImpl.getKhddHdListByDDbh(id, shbh);
				model.addAttribute("jpkhddHdList", khddHdList);
				
				//机票信息
				List<JpJp> jpJpList = this.jpJpServiceImpl.getJpListByDDbh(id, shbh);
				model.addAttribute("jpJpList", jpJpList);
				
				//补差单信息
				List<JPBcd> jpBcdList = this.jpBcdServiceImpl.getbcdList(shbh, id, "01");
				if(CollectionUtils.isNotEmpty(jpBcdList)){
					for(JPBcd jpbcd : jpBcdList){
						Shjcsj shjcsj = this.shjcsjServiceImpl.getMyBatisDao().getShjcsj(user.getShbh(),jpbcd.getBclx());
						if( shjcsj != null){
							jpbcd.setBclxname(shjcsj.getMc());
						}
					}
				}
				model.addAttribute("bcdList",jpBcdList);
				
				List<JpQz> list=jpQzService.queryListByYwdh(id);
				attachService.shyhb("qzYhbh",user.getShbh() ).execute(list);
				model.addAttribute("list", list);
				model.addAttribute("ywlx", "01");//01为正常订单
			}
			return viewpath("detail");
		} catch (Exception e) {
			logger.error("进入订单详错误", e);
			return this.addError("进入订单详错误" + e.getMessage(), e, "detail", model);
		}
	}
	
	/**
	 * 保存订单详
	 * @return
	 */
	@RequestMapping(value = "saveDetail",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveDetail(@ModelAttribute()JpDdBean jpDdBean, ModelMap model, HttpServletRequest request) {
		String[]  cjrIds = request.getParameterValues("cjrId");
		String[]  cjr_zdj = request.getParameterValues("cjr_zdj");
		String[]  cjr_cgj = request.getParameterValues("cjr_cgj");
		String[]  cjr_xsj = request.getParameterValues("cjr_xsj");
		String[]  cjr_jsf = request.getParameterValues("cjr_jsf");
		String[]  cjr_tax = request.getParameterValues("cjr_tax");
		String[]  cjr_hyxfs = request.getParameterValues("cjr_hyxfs");
		String[]  cjr_ywxfs = request.getParameterValues("cjr_ywxfs");
		String[]  cjr_zsje = request.getParameterValues("xsJe");
		
		Map<String, Object> ddMap = new HashMap<String, Object>();
		Shyhb user = SessionUtils.getShshbSession();
		List<JpDdCjrBean>  jpDdCjrList = new ArrayList<JpDdCjrBean>();
		try {
			//修改乘机人信息
			int cjrlen = cjrIds.length;
			for (int i=0;i<cjrlen;i++) {
				JpDdCjrBean jpDdCjrBean = new JpDdCjrBean();
				if(i==0){//邮寄费传到第一个乘机人上
					jpDdCjrBean.setXs_yjf(jpDdBean.getXs_yjf());
				}
				jpDdCjrBean.setId(cjrIds[i]);
				jpDdCjrBean.setShbh(user.getShbh());
				jpDdCjrBean.setXs_zdj(NumberUtils.toLong(cjr_zdj[i]));
				jpDdCjrBean.setXs_pj(NumberUtils.toDouble(cjr_xsj[i]));
				jpDdCjrBean.setXs_jsf(NumberUtils.toLong(cjr_jsf[i]));
				jpDdCjrBean.setXs_tax(NumberUtils.toLong(cjr_tax[i]));
				jpDdCjrBean.setXs_ywxfs(NumberUtils.toShort(cjr_ywxfs[i]));
				jpDdCjrBean.setXs_hyxfs(NumberUtils.toShort(cjr_hyxfs[i]));
				jpDdCjrBean.setCg_pj(NumberUtils.toDouble(cjr_cgj[i]));
				jpDdCjrBean.setXs_je(NumberUtils.toDouble(cjr_zsje[i]));
				jpDdCjrList.add(jpDdCjrBean);
			}
			jpDdBean.setXgly("订单详情");
			jpDdBean.setXgyh(user.getBh());
			jpDdBean.setShbh(user.getShbh());
			
			BookOrder bookOrder = this.baseService.saveOrder(user.getShbh(), jpDdBean, jpDdCjrList, null, logger);
			ddMap.put("CODE", bookOrder.getResult());
			ddMap.put("MSG", "保存订单详错误："+bookOrder.getErrmsg());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入订单详错误", e);
			ddMap.put("CODE", "-1");
			ddMap.put("MSG", "保存订单详错误："+e.getMessage());
		}
		return ddMap;
	}
	
	/**
	 * 保存手工单
	 * @return
	 */
	
	@RequestMapping(value = "saveSgd",method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveSgd(@ModelAttribute()JpDdBean jpDdBean, ModelMap model, HttpServletRequest request) {
		String[]  cjr = request.getParameterValues("cjr_cjrxm");
		String[]  cjr_lx =  request.getParameterValues("cjr_cjrlx");
		String[]  cjr_zjhm =  request.getParameterValues("cjr_zjhm");
		String[]  cjr_sjhm =  request.getParameterValues("cjr_sjhm");
		String[]  cjr_zdj = request.getParameterValues("cjr_zdj");
		String[]  cjr_cgj = request.getParameterValues("cjr_cgj");
		String[]  cjr_xsj = request.getParameterValues("cjr_xsj");
		String[]  cjr_jsf = request.getParameterValues("cjr_jsf");
		String[]  cjr_tax = request.getParameterValues("cjr_tax");
		String[]  cjr_zsje = request.getParameterValues("cjr_xsje");
		
		String[]  hd_hbh = request.getParameterValues("hd_hbh");
		String[]  hd_cfcity =  request.getParameterValues("hd_cfcity");
		String[]  hd_ddcity =  request.getParameterValues("hd_ddcity");
		String[]  hd_cfsj =  request.getParameterValues("hd_cfsj");
		String[]  hd_ddsj = request.getParameterValues("hd_ddsj");
		String[]  hd_cw = request.getParameterValues("hd_cw");
		String[]  hd_fjjx = request.getParameterValues("hd_fjjx");
		String[]  hd_cfhzl = request.getParameterValues("hd_cfhzl");
		String[]  hd_ddhzl = request.getParameterValues("hd_ddhzl");
		
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		List<JpDdCjrBean>  jpDdCjrList = new ArrayList<JpDdCjrBean>();
		List<JpDdHdBean>  jpDdHdList = new ArrayList<JpDdHdBean>();
		Map<String, Object> ddMap = new HashMap<String, Object>();
		try {
			//修改乘机人信息
			int cjrlen = cjr.length;
			for (int i=0;i<cjrlen;i++) {
				JpDdCjrBean jpDdCjrBean = new JpDdCjrBean();
				if(i==0){//邮寄费传到第一个乘机人上
					jpDdCjrBean.setXs_yjf(jpDdBean.getXs_yjf());
				}
				jpDdCjrBean.setShbh(shbh);
				jpDdCjrBean.setCjr(cjr[i]);
				jpDdCjrBean.setCjrlx(cjr_lx[i]);
				jpDdCjrBean.setZjhm(cjr_zjhm[i]);
				jpDdCjrBean.setSjhm(cjr_sjhm[i]);
				jpDdCjrBean.setShbh(user.getShbh());
				jpDdCjrBean.setXs_zdj(NumberUtils.toLong(cjr_zdj[i]));
				jpDdCjrBean.setXs_pj(NumberUtils.toDouble(cjr_xsj[i]));
				jpDdCjrBean.setXs_jsf(NumberUtils.toLong(cjr_jsf[i]));
				jpDdCjrBean.setXs_tax(NumberUtils.toLong(cjr_tax[i]));
				jpDdCjrBean.setCg_pj(NumberUtils.toDouble(cjr_cgj[i]));
				jpDdCjrBean.setXs_je(NumberUtils.toDouble(cjr_zsje[i]));
				jpDdCjrList.add(jpDdCjrBean);
			}
			
			//修改航段信息
			int hdlen = hd_hbh.length;
			for (int i=0;i<hdlen;i++) {
				JpDdHdBean jpDdCjrBean = new JpDdHdBean();
				jpDdCjrBean.setShbh(shbh);
				jpDdCjrBean.setXs_hbh(hd_hbh[i]);
				jpDdCjrBean.setCfcity(hd_cfcity[i]);
				jpDdCjrBean.setDdcity(hd_ddcity[i]);
				jpDdCjrBean.setCfsj(hd_cfsj[i]);
				jpDdCjrBean.setDdsj(hd_ddsj[i]);
				jpDdCjrBean.setXs_cw(hd_cw[i]);
				jpDdCjrBean.setFjjx(hd_fjjx[i]);
				jpDdCjrBean.setCfhzl(hd_cfhzl[i]);
				jpDdCjrBean.setDdhzl(hd_ddhzl[i]);
				jpDdHdList.add(jpDdCjrBean);
			}
			
			//根据晚点ID获取网店平台
			Wdzlsz wdzlsz = new Wdzlsz();
			wdzlsz.setId(jpDdBean.getWdid());
			wdzlsz.setShbh(shbh);
			wdzlsz = wdzlszServiceImpl.getEntityById(wdzlsz);
			if(wdzlsz != null){
				jpDdBean.setWdpt(wdzlsz.getWdpt());
			}
			
			jpDdBean.setXgly("机票手工单");
			jpDdBean.setXgyh(user.getBh());
			jpDdBean.setShbh(shbh);
			jpDdBean.setDdyh(user.getBh());
			jpDdBean.setDdbm(user.getBmmc());
			jpDdBean.setSfsgd("1");//手工订单
			BookOrder rtnBean = this.baseService.saveOrder(shbh, jpDdBean, jpDdCjrList, jpDdHdList, logger);
			ddMap.put("CODE", rtnBean.getResult());
			ddMap.put("MSG", "保存手工单错误："+rtnBean.getErrmsg());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入订单详错误", e);
			ddMap.put("CODE", -1);
			ddMap.put("MSG", "保存手工单错误："+e.getMessage());
		}
		return ddMap;
	}
	
	/**
	 * 手工订单
	 * @return
	 */
	@RequestMapping(value = "enterSgdPage")
	public String enterSgdPage(@RequestParam(value = "lx", defaultValue  = "1") String lx, @RequestParam(value = "gngj", defaultValue  = "1") String gngj, ModelMap model){
		model.addAttribute("lx", lx);
		//获取网店信息
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("zt", "1");//启用
		Shyhb user = SessionUtils.getShshbSession();
		param.put("shbh", user.getShbh());
		if("0".equals(gngj)){
			param.put("ywlxs", new String[]{"1001902"});
		} else {
			param.put("ywlxs", new String[]{"1001901"});
		}
		List<Wdzlsz> wdzlszList = wdzlszServiceImpl.getWdZlszListByYwlx(param);
		model.addAttribute("wdzlszList", wdzlszList);
		
		return viewpath("jpkhdd_sgd");
	}
	
	/**
	 * 完成页面
	 * @param bh
	 * @param zt
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "enterWcPage_{id}")
	public String enterWcPage(@PathVariable("id") String id, ModelMap model) {
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		try {
			if (StringUtils.isNotBlank(id)) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("ddbh", id);
				paramMap.put("shbh", shbh);
				//订单信息
				Map<String,Object> jpKhddMap=this.baseService.detail(paramMap);
				model.addAttribute("jpkhdd", jpKhddMap);
				System.out.println(jpKhddMap);
				
				//订单乘机人信息
				List<JpKhddCjr> khddCjrList = this.jpKhddCjrServiceImpl.getKhddCjrListByDDbh(id, shbh);
				if(CollectionUtils.isNotEmpty(khddCjrList)){
					JpKhddCjr cjr=khddCjrList.get(0);
					String tkno=cjr.getTkno();
					if(StringUtils.isBlank(tkno)){
					  //从PNR中提取票号
					}
				}
				
				model.addAttribute("jpkhddCjrList", khddCjrList);
				
				//订单航段信息
				List<JpKhddHd> khddHdList = this.jpKhddHdServiceImpl.getKhddHdListByDDbh(id, shbh);
				model.addAttribute("jpkhddHdList", khddHdList);
				
				//机票信息
				List<JpJp> jpJpList = this.jpJpServiceImpl.getJpListByDDbh(id, shbh);
				model.addAttribute("jpJpList", jpJpList);
			}
			String yh=user.getBh();
			model.addAttribute("yh", yh);
			return viewpath("jpkhdd_wc");
		} catch (Exception e) {
			logger.error("进入一键完成页面错误", e);
			return this.addError("进入一键完成页面错误" + e.getMessage(), e, "jpkhdd_wc", model);
		}
	}
	
	@RequestMapping(value = "enterCpPage_{id}")
	public @ResponseBody Map<String,Object> enterCpPage(@PathVariable("id") String id, ModelMap model) {
		Map<String, Object> info=new HashMap<String, Object>();
		info.put("result", "0");
		try {
			JpKhdd jpkhdd = new JpKhdd();
			Shyhb yhb =SessionUtils.getShshbSession();
			jpkhdd.setDdbh(id);
			jpkhdd.setShbh(yhb.getShbh());//商户编号必填
			jpkhdd = this.baseService.getEntityById(jpkhdd);//查询id对应的对象是否存在
			
			if(null != jpkhdd){
				if(StringUtils.isNotBlank(jpkhdd.getCpSdr()) && !yhb.getBh().equals(jpkhdd.getCpSdr())){
					info.put("msg",jpkhdd.getCpSdr());
					return info;
				}
				if(!DictEnum.JPDDZT.YWC.getValue().equals(jpkhdd.getDdzt())){
					jpkhdd.setDdzt(DictEnum.JPDDZT.CPZ.getValue());//出票中
					jpkhdd.setCpSdr(yhb.getBh());
					this.baseService.update(jpkhdd);
					info.put("result", "1");
					return info;
				}
			}
		} catch (Exception e) {
			logger.error("取消订单[id=" + id + "]失败", e);
			info.put("result", "-1");
			info.put("msg","取消订单[" + id + "]失败");
		}
		return info;
	}
	
	/**
	 * 手动补位,当pnr状态是NO时在原PNR上进行补位
	 * @param ddbh
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sgBw_{ddbh}")
	public @ResponseBody String sgBw(@PathVariable("ddbh") String ddbh, ModelMap model) {
		
		Shyhb shyhb = SessionUtils.getShshbSession();
		String shbh=shyhb.getShbh();
		
		/**
		 * 1.取订单信息
		 */
		JpKhdd jpkhdd = new JpKhdd();
		jpkhdd.setDdbh(ddbh);
		jpkhdd.setShbh(shbh);
		jpkhdd = this.baseService.getEntityById(jpkhdd);//查询id对应的对象是否存在
		if(jpkhdd == null){
			return "查询不到订单信息，请检查订单是否存在!";
		}
		
		String cg_pnrno=jpkhdd.getCgPnrNo();
		if(StringUtils.isBlank(cg_pnrno)){
			return "采购PNR为空不能进行补位操作";
		}
		
		if(cg_pnrno.startsWith("O")){
			return "采购PNR["+cg_pnrno+"]为假编码";
		}
		
		/**
		 * 2.取PID配置信息
		 */
		JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(shbh);
		if(jpPz == null){
			return "PID未配置,无法执行补位指令 !";
		}
		
		/**
		 * 3 RT获取PNR内容
		 */
		PnrRtParam pnrRtParam = new PnrRtParam();
		pnrRtParam.setShbh(shbh);
		pnrRtParam.setUserid(shyhb.getPidyh());
		pnrRtParam.setPassword(shyhb.getMm());
		pnrRtParam.setUrl("http://"+jpPz.getPzIp()+":"+jpPz.getPzPort());
		pnrRtParam.setPnrno(jpkhdd.getCgPnrNo());
		Pnr pnrObject = null;
		try {
			pnrObject = IbeService.rtPnr(pnrRtParam);
		} catch (Exception e) {
			e.printStackTrace();
			return "提取编码["+cg_pnrno+"]异常:"+e.getMessage();
		}
		
		if (pnrObject == null) {
			return "补位失败,RT编码返回信息为空!";
		}
		JpPtLog jpPtLog = new JpPtLog();
		String result=jpzdbwServiceImpl.jpzdbw(pnrObject, jpPz,jpPtLog, "", ddbh);
		
		if("2".equals(result)){
			return "执行补位指令进行补位失败";
		}
		return "1".equals(result) ? "" : result;
	
	}
	
	@RequestMapping(value = "saveWc")
	public @ResponseBody Map<String, Object> saveWc(@ModelAttribute()JpDdBean jpDdBean, ModelMap model) {
		Map<String, Object> ddMap = new HashMap<String, Object>();
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		try {
			JpKhdd jpkhdd = new JpKhdd();
			jpkhdd.setDdbh(jpDdBean.getDdbh());
			jpkhdd.setShbh(shbh);//商户编号必填
			jpkhdd = this.baseService.getEntityById(jpkhdd);//查询id对应的对象是否存在
			if(null != jpkhdd){
				jpkhdd.setDdzt(DictEnum.JPDDZT.YWC.getValue());//已取消
				this.baseService.update(jpkhdd);
				ddMap.put("CODE", "1");
			}
		} catch (Exception e) {
			logger.error("进入一键完成页面错误", e);
			ddMap.put("CODE", "0");
			ddMap.put("MSG", "一键完成失败："+e.getMessage());
		}
		return ddMap;
	}
	
	/**
	 * 取消订单操作
	 * @param id
	 * @return
	 */
	
	@RequestMapping
	public @ResponseBody String cancel(@RequestParam(value = "id", defaultValue = "") String id){
		String result="1";
		try {
			JpKhdd jpkhdd = new JpKhdd();
			Shyhb yhb =SessionUtils.getShshbSession();
			jpkhdd.setDdbh(id);
			jpkhdd.setShbh(yhb.getShbh());//商户编号必填
			jpkhdd = this.baseService.getEntityById(jpkhdd);//查询id对应的对象是否存在
			
			//调用XEPNR接口
			if(null != jpkhdd){
				String pnrno = jpkhdd.getXsPnrNo();
				String pnrno_head=pnrno.substring(0,1);
				//O开头的假PNR  假PNR不用进行XE
				if (!"O".equals(pnrno_head)) {
					JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(yhb.getShbh());
					XePnrParam param = new XePnrParam();
					param.setShbh(jpPz.getShbh());
					param.setUserid(yhb.getPidyh());
					param.setPassword(yhb.getMm());
					param.setUrl(jpPz.getPzIp() + ":" + jpPz.getPzPort());
					param.setOfficeId(jpPz.getOfficeid());
					param.setPnrNo(pnrno);
					String rtn = IbeService.xePnr(param);
					logger.error("取消订单[id=" + id + "], XEPNR返回结果：", rtn);
					if (XePnr.XE_SUCCESS.equals(rtn) || XePnr.HAS_XE.equals(rtn)) {
						jpkhdd.setXsPnrZt("XX");// 修改PNR状态
					}
				}
				jpkhdd.setDdzt(DictEnum.JPDDZT.YQX.getValue());// 已取消
				this.baseService.update(jpkhdd);
			}
		} catch (Exception e) {
			logger.error("取消订单[id=" + id + "]失败", e);
			result="0";
		}
		return result;
	}
	
	/**
	 * 根据PNR提取
	 * @param pnr
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "tqByPnr")
	public String tqByPnr(@RequestParam(value = "pnrno", defaultValue = "") String pnrno,
						  @RequestParam(value = "type", defaultValue = "") String type,
						  ModelMap model){
		Shyhb yhb =SessionUtils.getShshbSession();
		String shbh = yhb.getShbh();
		try{
			//获取网店信息
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("zt", "1");//启用
			param.put("shbh", shbh);
			param.put("ywlxs", new String[]{"1001901","1001902"});
			List<Wdzlsz> wdzlszList = wdzlszServiceImpl.getWdZlszListByYwlx(param);
			model.addAttribute("wdzlszList", wdzlszList);
			
			//调用RTPNR接口
			JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(shbh);
			if(jpPz == null ){
				model.addAttribute("error", "请检查PID配置！");
				return viewpath("jpkhdd_sgd");
			}
			Pnr pnrObject = null;
			if("2".equals(type)){
				RrtPnrParam  rrtPnrParam=new RrtPnrParam();
				rrtPnrParam.setBigpnrno(pnrno);
				rrtPnrParam.setShbh(shbh);
				rrtPnrParam.setUserid(yhb.getPidyh());
				rrtPnrParam.setUrl("http://"+jpPz.getPzIp()+":"+jpPz.getPzPort());
				rrtPnrParam.setOfficeId(jpPz.getOfficeid());
				pnrObject = IbeService.rrtPnr(rrtPnrParam);
				
			}else{
				PnrRtParam pnrRtParam = new PnrRtParam();
				pnrRtParam.setShbh(shbh);
				pnrRtParam.setUserid(yhb.getPidyh());
				pnrRtParam.setPassword(yhb.getMm());
				pnrRtParam.setUrl("http://"+jpPz.getPzIp()+":"+jpPz.getPzPort());
				pnrRtParam.setOfficeId(jpPz.getOfficeid());
				pnrRtParam.setPnrno(pnrno);//"HFHMP1"
				pnrObject = IbeService.rtPnr(pnrRtParam);
			}
			if(pnrObject == null){
				throw new Exception("无法提取出PNR内容");
			}
			this.baseService.setCityName(pnrObject);
			model.addAttribute("pnrObject", pnrObject);
		} catch (Exception e){
			logger.error("提取PNR错误", e);
		}
		return viewpath("jpkhdd_sgd");
	}
	
	/**
	 * 根据PNR内容提取
	 * @param pnrContent
	 * @param lx
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "tqByPnrContent")
	public String tqByPnrContent(@RequestParam(value = "pnrContent", defaultValue = "") String pnrContent,
						         @RequestParam(value = "lx", defaultValue = "") String lx,
						         ModelMap model){
		Shyhb yhb =SessionUtils.getShshbSession();
		String shbh = yhb.getShbh();
		
		try{
			//获取网店信息
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("zt", "1");//启用
			param.put("shbh", shbh);
			param.put("ywlxs", new String[]{"国内机票","国际机票"});
			List<Wdzlsz> wdzlszList = wdzlszServiceImpl.getWdZlszListByYwlx(param);
			model.addAttribute("wdzlszList", wdzlszList);
			model.addAttribute("lx", lx);
			
			//根据PNR内容提取
			//调用RTPNR接口
			JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(shbh);
			Pnr pnrObject = this.baseService.tqByPnrContent(pnrContent, jpPz);
			model.addAttribute("pnrObject", pnrObject);
		} catch (Exception e){
			logger.error("根据PNR内容提取错误", e);
		}
		
		return viewpath("jpkhdd_sgd");
	}
	
	/** 
	 * 进入预定婴儿票页面
	 * @return
	 */
	@RequestMapping(value = "enterYepPage_{id}")
	public String enterYepPage(@PathVariable("id") String id, ModelMap model){
		try{
			if (StringUtils.isNotBlank(id)) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				Shyhb yhb =SessionUtils.getShshbSession();
				String shbh = yhb.getShbh();
				paramMap.put("shbh", shbh);
				paramMap.put("ddbh", id);
				
				//订单信息
				Map<String,Object> jpKhddMap=this.baseService.detail(paramMap);
				model.addAttribute("jpkhdd", jpKhddMap);
				System.out.println(jpKhddMap);
				
				//订单乘机人信息
				List<JpKhddCjr> khddCjrList = this.jpKhddCjrServiceImpl.getKhddCjrListByDDbh(id, shbh);
				model.addAttribute("jpkhddCjrList", khddCjrList);
				
				//订单航段信息
				List<JpKhddHd> khddHdList = this.jpKhddHdServiceImpl.getKhddHdListByDDbh(id, shbh);
				model.addAttribute("jpkhddHdList", khddHdList);
			}
			return viewpath("detail_addye");
		} catch (Exception e) {
			logger.error("进入订婴儿票页面错误", e);
			return this.addError("进入订婴儿票页面错误" + e.getMessage(), e, "detail_addye", model);
		}
	}
	
	/**
	 * 添加婴儿票
	 * @return
	 */
	@RequestMapping(value = "addYe")
	@ResponseBody
	public Map<String, Object> addYe(ModelMap model, HttpServletRequest request){
		Shyhb sh = SessionUtils.getShshbSession();
		
		Map<String, Object> ddMap = new HashMap<String, Object>();
		try{
			String ddbh = request.getParameter("ddbh");
			JpKhdd jpkhdd = new JpKhdd();
			jpkhdd.setDdbh(ddbh);
			jpkhdd.setShbh(sh.getShbh());//商户编号必填
			jpkhdd = this.baseService.getEntityById(jpkhdd);//查询id对应的对象是否存在
			//订单航段信息
			List<JpKhddHd> khddHdList = this.jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh, sh.getShbh());
			
			// 添加婴儿
			String[] cjrs = request.getParameterValues("cjr");
			// String[] sxhs = request.getParameterValues("sxh");
			String[] cjrlxs = request.getParameterValues("cjrlx");
			String[] zjhms = request.getParameterValues("zjhm");
			String[] checkbs = request.getParameterValues("checkb");
			String[] erxms = request.getParameterValues("erxm");
			String[] csrqs = request.getParameterValues("csrq");
			String[] erywxms = request.getParameterValues("erywxm");
			if (erywxms == null || erywxms.length != erxms.length) {
				erywxms = new String[erxms.length];
			}
			// String pnr_hclx = request.getParameter("pnr_hclx");
			String hkgs = request.getParameter("pnr_hkgs");
			JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(sh.getShbh());
			PnrRtParam pnrRtParam = new PnrRtParam();
			pnrRtParam.setShbh(jpPz.getShbh());
			pnrRtParam.setUserid(sh.getPidyh());
			pnrRtParam.setPassword(sh.getMm());
			pnrRtParam.setUrl(jpPz.getPzIp()+":"+jpPz.getPzPort());
			pnrRtParam.setOfficeId(jpPz.getOfficeid());
			pnrRtParam.setPnrno(jpkhdd.getXsPnrNo());//"HFHMP1"
			Pnr pnrObject = IbeService.rtPnr(pnrRtParam);
			if (pnrObject == null) {
				throw new Exception("NO PNR 无效编码");
			}
			
			EditPnrParam editPnrParam = new EditPnrParam();
			editPnrParam.setShbh(jpPz.getShbh());
			editPnrParam.setUserid(sh.getPidyh());
			editPnrParam.setPassword(sh.getMm());
			editPnrParam.setUrl(jpPz.getPzIp()+":"+jpPz.getPzPort());
			editPnrParam.setOfficeId(jpPz.getOfficeid());
			
			editPnrParam.setPnrno(jpkhdd.getXsPnrNo());
			for (int i = 0; i < checkbs.length; i++) {
				String yexm = erxms[i];
				String erywxm = erywxms[i];
				if (StringUtils.isBlank(erywxm)) {
					erywxm = BookUtil.mhPingying(yexm);
				}
				String csrq = csrqs[i];
				String p = "p" + Integer.parseInt(checkbs[i]);
				if (StringUtils.isNotBlank(pnrObject.getTlno()) && "RR".equals(jpkhdd.getXsPnrZt())) {
					List<JpJp> jpList = jpJpServiceImpl.getJpListByDDbh(ddbh, sh.getShbh());
					if(jpList==null || jpList.isEmpty()){
						throw new Exception("无法获取机票信息!");
					}
					editPnrParam.delTimelimit(pnrObject.getTlno(), jpList.get(0).getTkno());
				}

				String ifinf = "";
				if (StringUtils.isNotEmpty(jpkhdd.getXsHkgsPnr()) && "MF".equals(jpkhdd.getXsHkgsPnr())) {
					ifinf = "NOINF"; // 如果航空公司为厦航，婴儿姓名后面不需要INF
				}

				editPnrParam.addInfant(BookUtil.dateCommandYear(csrq), p, yexm, ifinf); // XN指令婴儿姓名直接传入中文
				for (JpKhddHd jpKhddHd : khddHdList) {
					String cw = jpKhddHd.getXsCw();
					String pnr_cfrqsj = jpKhddHd.getCfsj();
					String hbh = jpKhddHd.getXsHbh();
					String cfcity = jpKhddHd.getCfcity();
					String ddcity = jpKhddHd.getDdcity();
					if (!"ARNK".equals(hbh)) {
						String cfdate = BookUtil.dateCommandDay(pnr_cfrqsj);
						editPnrParam.addInfoAirSeg(hkgs, cfcity, ddcity, hbh, cw, cfdate, StringUtils.upperCase(erywxm), BookUtil.dateCommandTime(csrq), p);// SSR指令婴儿姓名转换成拼音
					}
				}

				String editPnrData = IbeService.editPnr(editPnrParam);
				if (!"1".equals(StringUtils.trim(editPnrData))) {
					System.out.println("error：" + editPnrData);
					throw new Exception("航信添加婴儿失败，原因：" + editPnrData);
				}
				
				JpDdBean jpDdBean = new JpDdBean();
				jpDdBean.setDdbh(ddbh);
				List<JpDdCjrBean> jpDdCjrBeanList = new ArrayList<JpDdCjrBean>();
				for (int j = 0; j < cjrs.length; j++) {
					JpDdCjrBean jpDdCjrBean = new JpDdCjrBean();
					jpDdCjrBean.setCjr(cjrs[j]);
					jpDdCjrBean.setZjhm(zjhms[j]);
					jpDdCjrBean.setCjrlx(cjrlxs[j]);
					jpDdCjrBeanList.add(jpDdCjrBean);
				}
				
				for (int j = 0; j < checkbs.length; j++) {
					JpDdCjrBean jpDdCjrBean = new JpDdCjrBean();
					
					jpDdCjrBean.setZjhm(VeDate.dateCommandTime(csrqs[j]));
					jpDdCjrBean.setCjrlx("3");//婴儿票
					// 如果航空公司为厦航，婴儿姓名后面不需要INF
					if (StringUtils.isNotEmpty(jpkhdd.getXsHkgsPnr()) && "MF".equals(jpkhdd.getXsHkgsPnr())) {
						jpDdCjrBean.setCjr(erxms[j] + "(" + BookUtil.dateCommandYear(csrqs[j]) + ")");
					} else {
						jpDdCjrBean.setCjr(erxms[j] + "INF(" + BookUtil.dateCommandYear(csrqs[j]) + ")");
					}
					jpDdCjrBeanList.add(jpDdCjrBean);
				}
				
				//婴儿票入库
				BookOrder bookOrder = this.baseService.saveOrder(sh.getShbh(), jpDdBean, jpDdCjrBeanList, null, logger);
				if(1  != bookOrder.getResult()){//成功入库
					throw new Exception(bookOrder.getErrmsg());
				}
			}
			ddMap.put("CODE", "0");
		} catch (Exception e) {
			logger.error("添加婴儿票错误", e);
			ddMap.put("CODE", "-1");
			ddMap.put("MSG", "添加婴儿票错误："+e.getMessage());
		}
		return ddMap;
	}
	
	/**
	 * 出票前降舱
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "enterJcPage_{id}")
	public String enterJcPage(@PathVariable("id") String id, ModelMap model){
		try{
			if (StringUtils.isNotBlank(id)) {
				Shyhb yhb =SessionUtils.getShshbSession();
				String shbh = yhb.getShbh();
				
				//1. 订单信息
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("shbh", shbh);
				paramMap.put("ddbh", id);
				Map<String,Object> jpKhddMap=this.baseService.detail(paramMap);
				JpKhdd jpKhdd = new JpKhdd();
				jpKhdd.setShbh(shbh);
				jpKhdd.setDdbh(id);
				jpKhdd = this.baseService.getEntityById(jpKhdd);
				
				//2. 订单航段信息
				List<JpKhddHd> khddHdList = this.jpKhddHdServiceImpl.getKhddHdListByDDbh(id, shbh);
				if(khddHdList == null || khddHdList.isEmpty()){
					throw new Exception("无法获取订单航段信息");
				}
				
				//3. 舱位信息
				JpKhddHd jpKhddHd = khddHdList.get(0);
				List<AvCabinModel> cabinList = this.baseService.getCabinList(shbh, jpKhdd, jpKhddHd);
				
				model.addAttribute("jpkhdd", jpKhddMap);
				model.addAttribute("jpkhddHd", jpKhddHd);
				model.addAttribute("currentAvCabin", cabinList.remove(cabinList.size()-1));//获取当前舱位信息
				model.addAttribute("cabinList", cabinList);
			}
		} catch (Exception e) {
			logger.error("进入出票前降舱错误", e);
			model.addAttribute("error", e.getMessage());
		}
		return viewpath("detail_downgrading");
	}
	
	/**
	 * 换PNR及出票前降舱
	 * @param id
	 * @return
	 */
	
	@RequestMapping
	public @ResponseBody Map<String, Object> changePnrAndCw(@RequestParam(value = "id", defaultValue = "") String id, HttpServletRequest request){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String returnStr = "";
		
		String newCw = request.getParameter("new_cw");
		String newPrice = request.getParameter("new_price");
		Shyhb yhb =SessionUtils.getShshbSession();
		try {
			JpKhdd jpkhdd = new JpKhdd();
			jpkhdd.setDdbh(id);
			jpkhdd.setShbh(yhb.getShbh());//商户编号必填
			jpkhdd = this.baseService.getEntityById(jpkhdd);//查询id对应的对象是否存在
			
			String sfsq = StringUtils.trimToEmpty(request.getParameter("sfsq"));
			//是否授权
			if("1".equals(sfsq)){//授权
				PnrAuthParam pnrAuthParam = new PnrAuthParam();
				JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(yhb.getShbh());
				pnrAuthParam.setShbh(jpPz.getShbh());
				pnrAuthParam.setUserid(yhb.getPidyh());
				pnrAuthParam.setPassword(yhb.getMm());
				pnrAuthParam.setUrl(jpPz.getPzIp()+":"+jpPz.getPzPort());
				pnrAuthParam.setOfficeId(jpPz.getOfficeid());
				pnrAuthParam.setPnrno(jpkhdd.getXsPnrNo());
				PnrAuth pnrAuth = new PnrAuth(pnrAuthParam);
				boolean isSuccess = pnrAuth.pnrAuth();
				if (isSuccess) {
					returnStr = ",授权成功";
				} else {
					returnStr = ",授权失败";
				}
			}
			jpkhdd.setNewCw(newCw);
			jpkhdd.setNewPrice(newPrice);
			Map<String, Object> pnrMap = this.baseService.changePnr(jpkhdd);
			String newPnr = (String)pnrMap.get("PNRNO");
			
			//修改PNR,新舱位及新价格
			BookOrder bookOrder = this.baseService.saveOrder(yhb.getShbh(), (JpDdBean)pnrMap.get("jpdd"), (List<JpDdCjrBean>)pnrMap.get("jpddcjrlist"), (List<JpDdHdBean>)pnrMap.get("jpddhdlist"), logger);
			if (0 == bookOrder.getResult() && StringUtils.isNotBlank(newPnr)) {
				returnMap.put("code", "1");
				returnMap.put("msg",  "新PNR为："+jpkhdd.getXsPnrNo()+returnStr);
			} else {
				returnMap.put("code", "0");
			}
		} catch (Exception e) {
			logger.error("操作失败", e);
			returnMap.put("code", "0");
			returnMap.put("msg", "操作失败："+e.getMessage());
		}
		return returnMap;
	}
	
	
	@RequestMapping
	public @ResponseBody Map<String, Object> pat(HttpServletRequest request, ModelMap model){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Shyhb yhb =SessionUtils.getShshbSession();
		
		String[] hbh = request.getParameter("hbh").split(",");
		String[] cfdate = request.getParameter("cdate").split(",");
		String[] cw = request.getParameter("cw").split(",");
		String[] hc = request.getParameter("hc").split(",");
		
		StringBuffer ssCommand = new StringBuffer(64);
		for (int i = 0; i < hbh.length; i++) {
			String date = BookUtil.dateCommandTime(cfdate[i]);
			ssCommand.append(PatParser.spellSsCommand(StringUtils.replace(hbh[i], "*", ""), cw[i], StringUtils.substring(hc[i], 0, 3),StringUtils.substring(hc[i], 3, 6), "1", date, ""));
		}
		
		PatParam param = new PatParam();
		JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(yhb.getShbh());
		param.setShbh(jpPz.getShbh());
		param.setUserid(yhb.getPidyh());
		param.setPassword(yhb.getMm());
		param.setUrl(jpPz.getPzIp()+":"+jpPz.getPzPort());
		param.setOfficeId(jpPz.getOfficeid());
		param.setSs(ssCommand.toString());
		param.setPatCommand("PAT:A");
		String pj = "";
		try{
			String pat = IbeService.pat(param);
			if(StringUtils.isNotBlank(pat)){
				pj = pat.split("_")[0];
			}
			if (StringUtils.isBlank(pj)) {
				pj = "0";
			}
			double pjTemp = Double.parseDouble(Function.round(pj, "0"));
			returnMap.put("code", "1");
			returnMap.put("pj", pjTemp);
		} catch(Exception e){
			returnMap.put("code", "0");
			returnMap.put("msg", "Pat失败："+e.getMessage());
			logger.error("Pat失败", e);
		}
		return returnMap;
	}
	
	/**
	 * 换PNR出票
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "enterChangePnrPage_{id}")
	public String enterChangePnrPage(@PathVariable("id") String id, ModelMap model){
		Shyhb yhb =SessionUtils.getShshbSession();
		String shbh = yhb.getShbh();
		try{
			if (StringUtils.isNotBlank(id)) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("shbh", shbh);
				paramMap.put("ddbh", id);
				//订单信息
				Map<String,Object> jpKhddMap=this.baseService.detail(paramMap);
				model.addAttribute("jpkhdd", jpKhddMap);
			}
			JpPz jpPz = jpPzServiceImpl.getJpPzByShbh(shbh);
			model.addAttribute("jppz", jpPz);
			
			return viewpath("detail_changepnrcp");
		} catch (Exception e) {
			logger.error("进入换PNR出票页面错误", e);
			return this.addError("进入换PNR出票页面错误" + e.getMessage(), e, "detail_changepnrcp", model);
		}
	}

	/**
	 * 验证降舱
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "checkXgpnr_{ddbh}")
	public String checkXgpnr(@PathVariable("ddbh") String ddbh, ModelMap model){
		Shyhb yhb =SessionUtils.getShshbSession();
		String shbh = yhb.getShbh();
		try{
			if (StringUtils.isNotBlank(ddbh)) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("shbh", shbh);
				paramMap.put("ddbh", ddbh);
				//订单信息
				Map<String,Object> jpKhddMap=this.baseService.detail(paramMap);
				if (jpKhddMap == null || jpKhddMap.isEmpty()) {
					throw new Exception("取订单信息失败，未知错误！");
				}
	
				model.addAttribute("jpkhdd", jpKhddMap);
			}
			
			return viewpath("detail_changepnrcp");
		} catch (Exception e) {
			logger.error("进入降舱错误", e);
			return this.addError("进入降舱错误" + e.getMessage(), e, "detail_changepnrcp", model);
		}
	}
	
	/**
	 * 改签详降舱生成新订单
	 * @param id
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "generateNewOrder")
	public String generateNewOrder(@PathVariable("ddbh") String ddbh, ModelMap model, HttpServletRequest request){
		Shyhb yhb =SessionUtils.getShshbSession();
		String shbh = yhb.getShbh();
		String osi = StringUtils.trimToEmpty(request.getParameter("pnr_osi"));
		String dz_officeid = StringUtils.trimToEmpty(request.getParameter("dz_officeid"));
		String pnr_tldatetime = StringUtils.trimToEmpty(request.getParameter("pnr_tldatetime"));
		String gqdh = StringUtils.trimToEmpty(request.getParameter("gqdh"));
		
		JpKhdd jpkhdd = new JpKhdd();
		jpkhdd.setDdbh(ddbh);
		jpkhdd.setShbh(yhb.getShbh());//商户编号必填
		jpkhdd = this.baseService.getEntityById(jpkhdd);//查询id对应的对象是否存在
		String newPnr = "";
		try {
			Map<String, Object> pnrMap = this.baseService.changePnr(jpkhdd);
			newPnr = (String)pnrMap.get("PNRNO");
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("换编码错误", e1.getMessage());
			return "换编码出错，错误信息：" + e1.getMessage();
		}
		JpDdBean jpDdBean = this.getNewOrder(jpkhdd, request, yhb);
		jpDdBean.setCg_pnr_no(newPnr);
		jpDdBean.setXs_pnr_no(newPnr);
		this.baseService.saveOrder(shbh, jpDdBean, jpDdBean.getJpDdCjrList(), jpDdBean.getJpDdHdList(), logger);
		return "1";
	}
	
	@Override
	public void updateInitEntity(JpKhdd t) {
		
	}

	@Override
	public void insertInitEntity(JpKhdd t) {
		
	}
	
	/**
	 * 通过原订单信息获取新订单
	 * @return
	 */
	public JpDdBean getNewOrder(JpKhdd jpKhdd, HttpServletRequest request, Shyhb shyhb) {
		JpDdBean newOrder = new JpDdBean();
		List<JpDdCjrBean> cjrList = this.getJpDdCjrList(jpKhdd);
		int cjrnum = cjrList.size();
		newOrder.setJpDdCjrList(cjrList);
		List<JpDdHdBean> hdList = this.getJpDdHdList(request, shyhb.getShbh());
		newOrder.setJpDdHdList(hdList);
		String price = StringUtils.trimToEmpty(request.getParameter("price"));
		String fjjx = StringUtils.trimToEmpty(request.getParameter("fjjx"));
		String cfsj = StringUtils.trimToEmpty(request.getParameter("cfsj"));
		String ddsj = StringUtils.trimToEmpty(request.getParameter("ddsj"));
		String hzl = StringUtils.trimToEmpty(request.getParameter("hzl"));
		String hbh = StringUtils.trimToEmpty(request.getParameter("hbh"));
		String cw = StringUtils.trimToEmpty(request.getParameter("cw"));
		newOrder.copyDd(jpKhdd);
		newOrder.setCfrq(StringUtils.substring(cfsj, 0, 10));
		newOrder.setCfsj(StringUtils.substring(cfsj, 11, 16));
		newOrder.setDdsj(StringUtils.substring(ddsj, 11, 16));
		newOrder.setCg_hbh(hbh);
		newOrder.setXs_hbh(hbh);
		newOrder.setCg_cw(cw);
		newOrder.setXs_cw(cw);
		if (StringUtils.isNotBlank(price)) {
			newOrder.setCg_pj(NumberUtils.toLong(price)*cjrnum);
		}
		newOrder.setDdzt(JpKhdd.DDZT_YGZ);
		newOrder.setCg_pnr_zt("HK");
		newOrder.setXs_pnr_zt("HK");
		newOrder.setDdbm(shyhb.getBmmc());
		newOrder.setDdyh(shyhb.getBh());
		//改签详点降舱生成新订单，此单为子单
		newOrder.setSfzdd("0");
		newOrder.setZddbh(jpKhdd.getDdbh());
		newOrder.setZddlx(jpKhdd.getZddlx());
		newOrder.setJcbs("1");
		return newOrder;
	} 
	
	public List<JpDdHdBean> getJpDdHdList(HttpServletRequest request, String shbh) {
		String gqdh = StringUtils.trimToEmpty(request.getParameter("gqdh"));
		String fjjx = StringUtils.trimToEmpty(request.getParameter("fjjx"));
		String cfsj = StringUtils.trimToEmpty(request.getParameter("cfsj"));
		String ddsj = StringUtils.trimToEmpty(request.getParameter("ddsj"));
		String hzl = StringUtils.trimToEmpty(request.getParameter("hzl"));
		String hbh = StringUtils.trimToEmpty(request.getParameter("hbh"));
		String cw = StringUtils.trimToEmpty(request.getParameter("cw"));
	
		List<JpGqdHd> hdList = jpGqdHdServiceImpl.getHdListByGqdh(gqdh, shbh);
		List<JpDdHdBean> hdBeanList = new ArrayList<JpDdHdBean>();
		int i = 1;
		for (JpGqdHd hd : hdList) {
			JpDdHdBean hdBean = new JpDdHdBean();
			hdBean.setCfcity(hd.getCfcity());
			hdBean.setCfhzl(hzl);
			hdBean.setCfsj(cfsj);
			hdBean.setCg_cw(cw);
			hdBean.setCg_hbh(hbh);
			hdBean.setDdcity(hd.getDdcity());
			hdBean.setDdsj(ddsj);
			hdBean.setFjjx(fjjx);
			hdBean.setHdzt("HK");
			hdBean.setSxh(String.valueOf(i));
			hdBean.setWbsxh(String.valueOf(hd.getWbsxh()));
			hdBean.setXs_cw(cw);
			hdBean.setXs_hbh(hbh);
			hdBeanList.add(hdBean);
			i++;
		}
		return hdBeanList;
	}
	
	public List<JpDdCjrBean> getJpDdCjrList(JpKhdd jpKhdd) {
		List<JpKhddCjr> cjrList = jpKhddCjrServiceImpl.getKhddCjrListByDDbh(jpKhdd.getDdbh(), jpKhdd.getShbh());
		List<JpDdCjrBean> cjrBeanList = new ArrayList<JpDdCjrBean>();
		int i = 1;
		for (JpKhddCjr cjr : cjrList) {
			JpDdCjrBean cjrBean = new JpDdCjrBean();
			cjrBean.setCjr(cjr.getCjr());
			cjrBean.setCg_jsf(cjr.getCgJsf());
			cjrBean.setCg_tax(cjr.getCgTax());
			cjrBean.setCg_zdj(cjr.getCgZdj());
			cjrBean.setCjrlx(cjr.getCjrlx());
			cjrBean.setCpzt(cjr.getCpzt());
			cjrBean.setCsrq(cjr.getCsrq());
			cjrBean.setGj(cjr.getGj());
			cjrBean.setSjhm(cjr.getSjhm());
			cjrBean.setSxh(String.valueOf(i));
			cjrBean.setTkno(cjr.getTkno());
			cjrBean.setWbcjrid(cjr.getWbcjrid());
			cjrBean.setXb(cjr.getXb());
			cjrBean.setXcdh(cjr.getXcdh());
			cjrBean.setXs_hyxfs(cjr.getXsHyxfs());
			cjrBean.setXs_hyxlr(cjr.getXsHyxlr()==null ? 0 : cjr.getXsHyxlr().doubleValue());
			cjrBean.setXs_je(cjr.getXsJe()==null ? 0 : cjr.getXsJe().doubleValue());
			cjrBean.setXs_jsf(cjr.getXsJsf());
			cjrBean.setXs_pj(cjr.getXsPj()==null ? 0 : cjr.getXsPj().doubleValue());
			cjrBean.setXs_tax(cjr.getXsTax());
			cjrBean.setXs_yjf(cjr.getXsYjf().longValue());
			cjrBean.setXs_ywxfs(cjr.getXsYwxfs());
			cjrBean.setXs_ywxlr(cjr.getXsYwxlr()==null ? 0 : cjr.getXsYwxlr().doubleValue());
			cjrBean.setXs_zdj(cjr.getXsZdj()==null ? 0 : cjr.getXsZdj().longValue());
			cjrBean.setZjhm(cjr.getZjhm());
			cjrBean.setZjlx(cjr.getZjlx());
			cjrBean.setZjqfg(cjr.getZjqfg());
			cjrBean.setZjyxq(cjr.getZjyxq());
			cjrBeanList.add(cjrBean);
			i++;
		}
		return cjrBeanList;
	}

	
	public static void main(String[] args) {
		String result="<RESULT><STATUS>1</STATUS><DESCRIPTION><XEFLIGHTresult>  HU7840  N FR16SEP  SHETNA DK1   1700 1845  JXXM0Z -   航空公司使用自动出票时限, 请检查PNR   *** 预订酒店指令HC, 详情  HC:HELP   ***  </XEFLIGHTresult><Pnr><P><R>1</R><N>JXXM0Z</N><T>1</T><Q> 1.刘静 JXXM0Z    2.  HU7840 N   FR16SEP  SHETNA HK1   1700 1845          E T3--   3.SHA/T SHA/T0571-88136060/ASLAN TRAVEL(SHANGHAI)CO.,LTD /JIANGLING ABCDEFG  4.TL/0744/09SEP/SHA384   5.SSR FOID HU HK1 NI230231198701161523/P1    6.SSR OTHS 1E HU7840 /N/16SEP/SHETNA CANCELED DUE TO ATTL EXPIRED    7.OSI HU CTCT15857146178     8.OSI HU CTCT057156888688    9.RMK TJ AUTH HGH372    10.RMK TJ AUTH BJS000    11.RMK CA/NG64DE 12.SHA384   </Q><B>NG64DE</B><C>SHA384,1,,,,,2016-09-09 07:44:00,0,1,0</C><TL>4</TL><OFFICE>SHA384</OFFICE><AUTH>HGH372,BJS000</AUTH><CTCT>57156888688</CTCT><HD>1|0,HU7840,N,16SEP,SHE,TNA,HK,2016-09-16 17:00:00,2016-09-16 18:45:00,738,,T3-,1,</HD><CJR>1|0,刘静,230231198701161523,NI,1,,,,,,,,,,,,</CJR></P></Pnr></DESCRIPTION></RESULT>";
		int start=result.indexOf("<Pnr>");
	    int end=result.indexOf("</Pnr>");
		String etermdata = result.substring( start> -1 ? start + 5 : 0, end);
		if(StringUtils.isNotBlank(etermdata)){
			try {
				Pnr pnr=PNRParser.parser(etermdata);
				System.out.println(pnr.getPnr_zt());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
