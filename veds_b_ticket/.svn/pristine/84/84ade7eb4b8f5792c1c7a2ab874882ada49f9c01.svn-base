package cn.vetech.web.vedsb.jptpgl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.service.SpringContextUtil;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shjcsj;
import cn.vetech.vedsb.common.service.base.ShjcsjServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpbcdgl.JPBcd;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.JpQz;
import cn.vetech.vedsb.jp.entity.jphbyd.JpHbyd;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpHd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.jpddsz.taobao.TaobaoGy_tf;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.cpsz.JpZdtpJkServiceImpl;
import cn.vetech.vedsb.jp.service.jpbcdgl.JpBcdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddsz.JpDdszServiceImpl;
import cn.vetech.vedsb.jp.service.jpddsz.JpQzServiceImpl;
import cn.vetech.vedsb.jp.service.jpddsz.JpddWork_tpd;
import cn.vetech.vedsb.jp.service.jphbyd.JpHbydServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpHdServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.zdtp.PzSuper;
import cn.vetech.vedsb.jp.service.jptpgl.zdtp.TaobaoCg;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpTpdController extends MBaseControl<JpTpd, JpTpdServiceImpl>{
	
	@Autowired
	private JpHdServiceImpl  jpHdServiceImpl;
	
	@Autowired
	private JpTpdMxServiceImpl jpTpdMxServiceImpl;
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private JpBcdServiceImpl jpBcdServiceImpl;
	
	@Autowired
	private JpQzServiceImpl jpQzService;
	
	@Autowired
	private ShjcsjServiceImpl shjcsjServiceImpl;
	
	@Autowired
	private JpZdtpJkServiceImpl jpZdtpJkServiceImpl;
	
	@Autowired
	private JpDdszServiceImpl  jpDdszServiceImpl;
	
	@Autowired
	private JpHbydServiceImpl jpHbydServiceImpl;
	
	@Override
	public void updateInitEntity(JpTpd t) {
		
	}

	@Override
	public void insertInitEntity(JpTpd t) {
		
	}
	
	/**
	 * 分页查询
	 * @param jpgqd
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @param sortName
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "query",method = RequestMethod.POST)
	public @ResponseBody Page query(Model model,HttpServletRequest request) {
			Page page = null;
			try {
				Shyhb user = SessionUtils.getShshbSession();
				Map<String, Object> param = Servlets.getParametersStartingWith(request, "", false);
				param.put("shbh", user.getShbh());
				page  = this.baseService.query(param);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return page;
	}
	
	
	/**
	 * 编辑跳转
	 * @param bh
	 * @param zt
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "tpedit_{tpdh}")
	public String tpedit(@PathVariable("tpdh") String tpdh, ModelMap model) {
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		try {
			if (StringUtils.isNotBlank(tpdh)) {
				JpTpd jptpd=new JpTpd();
				jptpd.setTpdh(tpdh);
				jptpd.setShbh(shbh);
				Map<String,Object> jptpdMap=this.baseService.detail(jptpd);
				model.addAttribute("jptpd", jptpdMap);
				System.out.println(jptpdMap);
			}
			return viewpath("edit");
		} catch (Exception e) {
			logger.error("编辑退票单错误", e);
			return this.addError("编辑退票单错误" + e.getMessage(), e, "edit", model);
		}
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "tpdCancel",method = RequestMethod.POST)
	@ResponseBody
	public String tpdCancel(JpTpd jptpd, ModelMap model) {
		String state="error";
		if(jptpd == null){
			return state;
		}
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
	    jptpd=this.baseService.getJpTpdByTpdh(jptpd.getTpdh(),shbh);
	    
	     String version="";
		
		String errorMessage=this.baseService.cancelCheck(jptpd,version);
		
	
		if(StringUtils.isNotBlank(errorMessage)){
			//return "";
		}
		try{
			
			 //取消原因
			this.baseService.cancelJptpd(jptpd,"veds_b_ticket取消退票单");
			state="ok";
		}catch(Exception e){
			e.printStackTrace();
		}
		return state;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "getJpTpd_{tpdh}")
	public String getJpTpd(@PathVariable("tpdh") String tpdh,
			@RequestParam(value = "forward") String forward, ModelMap model) {
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		JpTpd jptpd=this.baseService.getJpTpdByTpdh(tpdh,shbh);
		model.addAttribute("jptpd",jptpd);
		
		//机票信息
		List<Map<String, Object>> mxList=jpTpdMxServiceImpl.getJpTpdMxList(tpdh, shbh);
		model.addAttribute("mxList", mxList);
		return viewpath(forward);
	}
	
	/**
	 * 获取订单详细信息
	 * @param tpdh 退票单号
	 * @param forward 返回页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "tpdDetail_{tpdh}")
	public String detail(@PathVariable("tpdh") String tpdh,@RequestParam(value = "forward",defaultValue="detail") String forward, ModelMap model) {
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		
		JpTpd jptpd=this.baseService.getJpTpdByTpdh(tpdh,shbh);
		List<JpTpd> jptpdList=new ArrayList<JpTpd>();
		jptpdList.add(jptpd);
		//取用户资料
		attachService.shyhb("ddyh", shbh).shyhb("xsShr", shbh).shyhb("xsBlr", shbh).shyhb("cgTjr", shbh).zfkm("xsTkkm").execute(jptpdList);
		
		//航段信息
		List<JpHd>  hdList=jpHdServiceImpl.getJpHdByTpdh(tpdh, shbh);
		
		//机票信息
		List<Map<String, Object>> jpList=jpTpdMxServiceImpl.getJpTpdJpInfo(tpdh, shbh);
		
		
		List<Map<String, Object>> mxList=jpTpdMxServiceImpl.getJpTpdMxList(tpdh, shbh);
		
		//补差单信息
		List<JPBcd> bcdList = this.jpBcdServiceImpl.getbcdList(shbh, tpdh, "02");
		for(JPBcd jpbcd : bcdList){
			Shjcsj shjcsj = this.shjcsjServiceImpl.getMyBatisDao().getShjcsj(user.getShbh(),jpbcd.getBclx());
			jpbcd.setBclxname(shjcsj.getMc());
		}
		List<JpQz> list=jpQzService.queryListByYwdh(tpdh);
		attachService.shyhb("qzYhbh",user.getShbh() ).execute(list);
		model.addAttribute("list", list);
		model.addAttribute("ywlx", "02");//02为退票单
		
		model.addAttribute("bcdList",bcdList);
		model.addAttribute("jptpd", jptpdList.get(0));
		model.addAttribute("hdList", hdList);
		model.addAttribute("mxList", mxList);
		model.addAttribute("jpList", jpList);
		return viewpath(forward);
	}
	/**
	 * 销售审核
	 * @return
	 */
	@RequestMapping(value = "tpdXsSh_{tpdh}")
	@ResponseBody
	public Map<String,String> xsSh(@PathVariable("tpdh") String tpdh,JpTpdMx mx,ModelMap model,HttpServletRequest request){
		Map<String,String> retMap=new HashMap<String, String>();
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		String cgtj=request.getParameter("cgtj");
		String ztpt=request.getParameter("ztpt");
		String xsqrtp=request.getParameter("xsqrtp");
		JpTpd jptpd=this.baseService.getJpTpdByTpdh(tpdh,shbh);
		List<JpTpdMx>  mxList=jpTpdMxServiceImpl.getJpTpdMxByTpdh(tpdh, shbh);
		
		if(CollectionUtils.isNotEmpty(mxList)){
			long cgZdj=0;
			BigDecimal cgPj=new BigDecimal(0);
			long cgJsf=0;
			long cgTax=0;
			BigDecimal cgTpf=new BigDecimal(0);
			
			long xsZdj=0;
			BigDecimal xsPj=new BigDecimal(0);
			long xsJsf=0;
			long xsTax=0;
			BigDecimal xsTpsxf=new BigDecimal(0);
			
			int mxLength=mxList.size();
			for(JpTpdMx jtm:mxList){
				jtm.setShbh(shbh);
				cgZdj=mx.getCgZdj();
				cgPj=mx.getCgPj();
				cgJsf=mx.getCgJsf();
				cgTax=mx.getCgTax();
				cgTpf=mx.getCgTpf();
				
				xsZdj=mx.getXsZdj();
				xsPj=mx.getXsPj();
				xsJsf=mx.getXsJsf();
				xsTax=mx.getXsTax();
				xsTpsxf=mx.getXsTpsxf();
				
				jtm.setCgZdj(cgZdj);
				jtm.setCgPj(cgPj);
				jtm.setCgJsf(cgJsf);
				jtm.setCgTax(cgTax);
				jtm.setCgTpfl(new BigDecimal(mx.getCgTpfl().doubleValue()/100.00));
				jtm.setCgTpf(cgTpf);
				
				jtm.setXsZdj(xsZdj);
				jtm.setXsPj(xsPj);
				jtm.setXsJsf(xsJsf);
				jtm.setXsTax(xsTax);
				jtm.setXsTpfl(new BigDecimal(mx.getXsTpfl().doubleValue()/100.00));
				jtm.setXsTpsxf(xsTpsxf);
				try {
					jpTpdMxServiceImpl.update(jtm);
				} catch (Exception e) {
					e.printStackTrace();
					retMap.put("xssh", "false");
					retMap.put("message", "审核失败:"+e.getMessage());
					return retMap;
				}
				
			}
			
			if (null != jptpd) {
				jptpd.setXgyh(user.getBh());
				jptpd.setXgly("销售完成");
				jptpd.setXgsj(VeDate.getNow());
				jptpd.setXsTpzt(JpTpd.XS_TPZT_YSH);
				jptpd.setXsShr(user.getBh());
				jptpd.setXsShsj(VeDate.getNow());
				jptpd.setCgZdj(cgZdj*mxLength);
				jptpd.setCgPj(new BigDecimal(cgPj.doubleValue()*mxLength));
				jptpd.setCgJsf(cgJsf*mxLength);
				jptpd.setCgTax(cgTax*mxLength);
				jptpd.setCgTpfl(new BigDecimal(mx.getCgTpfl().doubleValue()/100.00));
				jptpd.setCgTpf(new BigDecimal(cgTpf.doubleValue()*mxLength));
				
				jptpd.setXsZdj(xsZdj*mxLength);
				jptpd.setXsPj(new BigDecimal(xsPj.doubleValue()*mxLength));
				jptpd.setXsJsf(xsJsf*mxLength);
				jptpd.setXsTax(xsTax*mxLength);
				jptpd.setXsTpsxf(new BigDecimal(xsTpsxf.doubleValue()*mxLength));
				jptpd.setXsTpfl(new BigDecimal(mx.getXsTpfl().doubleValue()/100.00));
				try {
					this.baseService.getMyBatisDao().updateByPrimaryKeySelective(jptpd);
					if(jptpd.getWdpt().equals(TaobaoCg.plat) && "1".equals(xsqrtp)){
						retMap = taobaoGyConfirm(jptpd,user);
					}
					retMap.put("xssh", "true");
				} catch (Exception e) {
					logger.error("审核失败", e);
					retMap.put("xssh", "false");
					retMap.put("message", "审核失败:"+e.getMessage());
					return retMap;
				}
				
				//采购提交
				if ("1".equals(ztpt)||(("BSP".equals(jptpd.getCgly()) || "BOP".equals(jptpd.getCgly())) && "1".equals(cgtj))) {
					PzSuper.ticketRefundHandle(false, jptpd, user, jpZdtpJkServiceImpl);
				}
			}
		}
		return retMap;
	}
	
	@RequestMapping(value = "batchXsWc")
	public @ResponseBody String batchXsWc(String tpdhs,ModelMap model) throws Exception {
		String [] tpdh_arr=tpdhs.split(",");
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		String msg="";
		for (String tpdh : tpdh_arr) {
			JpTpd jptpd=this.baseService.getJpTpdByTpdh(tpdh, shbh);
			if (null != jptpd) {
			    String xsTpzt=jptpd.getXsTpzt();
			    if(JpTpd.XS_TPZT_YQX.equals(xsTpzt)||JpTpd.XS_TPZT_YBL.equals(xsTpzt)){
			    	continue;
			    }
				jptpd.setXsTpzt(JpTpd.XS_TPZT_YBL);
				jptpd.setXsBlr(user.getBh());
				jptpd.setXsBlsj(VeDate.getNow());
				try {
					this.baseService.update(jptpd);
				} catch (Exception e) {
					logger.error("销售完成失败", e);
					msg+="退票单号["+tpdh+"]与销售完成失败<br>";
					//return this.addError("销售办理失败"+e.getMessage(),e, "edit",model);
				}
			}
		}
		return msg;
	}
	
	/**
	 * 销售完成
	 * @return
	 */
	@RequestMapping(value = "tpdXsWc_{tpdh}")
	@ResponseBody
	public Map<String, String> xsWc(@PathVariable("tpdh") String tpdh,JpTpdMx mx, ModelMap model,
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<String, String>();
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		JpTpd jptpd = this.baseService.getJpTpdByTpdh(tpdh, shbh);
		List<JpTpdMx> mxList = jpTpdMxServiceImpl.getJpTpdMxByTpdh(tpdh, shbh);

		if (CollectionUtils.isNotEmpty(mxList)) {
			long xsZdj = 0;
			BigDecimal xsPj = new BigDecimal(0);
			long xsJsf = 0;
			long xsTax = 0;
			BigDecimal xsTpsxf = new BigDecimal(0);

			int mxLength = mxList.size();
			for (JpTpdMx jtm : mxList) {
				jtm.setShbh(shbh);
				xsZdj = mx.getXsZdj();
				xsPj = mx.getXsPj();
				xsJsf = mx.getXsJsf();
				xsTax = mx.getXsTax();
				xsTpsxf = mx.getXsTpsxf();

				jtm.setXsZdj(xsZdj);
				jtm.setXsPj(xsPj);
				jtm.setXsJsf(xsJsf);
				jtm.setXsTax(xsTax);
				jtm.setXsTpfl(new BigDecimal(mx.getXsTpfl().doubleValue() / 100.00));
				jtm.setXsTpsxf(xsTpsxf);
				try {
					jpTpdMxServiceImpl.update(jtm);
				} catch (Exception e) {
					e.printStackTrace();
					retMap.put("xswc", "false");
					retMap.put("message", e.getMessage());
					return retMap;
				}
				if (null != jptpd) {
					jptpd.setXsTpzt(JpTpd.XS_TPZT_YBL); 
					jptpd.setXsBlr(user.getBh());
					jptpd.setXsBlsj(VeDate.getNow());	
					jptpd.setXgyh(user.getBh());
					jptpd.setXgly("销售完成");
					jptpd.setXgsj(VeDate.getNow());
					jptpd.setXsZdj(xsZdj * mxLength);
					jptpd.setXsPj(new BigDecimal(xsPj.doubleValue() * mxLength));
					jptpd.setXsJsf(xsJsf * mxLength);
					jptpd.setXsTax(xsTax * mxLength);
					jptpd.setXsTpsxf(new BigDecimal(xsTpsxf.doubleValue()* mxLength));
					jptpd.setXsTpfl(new BigDecimal(mx.getXsTpfl().doubleValue() / 100.00));
					try {
						this.baseService.getMyBatisDao().updateByPrimaryKeySelective(jptpd);
						retMap.put("xswc", "true");
					} catch (Exception e) {
						logger.error("销售办理失败", e);
						retMap.put("xswc", "false");
						retMap.put("message", e.getMessage());
						return retMap;
					}
				}
			}
		}
		return retMap;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "getHbyd_{tpdh}")
	public @ResponseBody Map<String,Object> getHbyd(@PathVariable("tpdh") String tpdh){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("hblx", "0");
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		JpTpd jptpd=this.baseService.getJpTpdByTpdh(tpdh,shbh);
		try {
			JpHbyd hbyd = new JpHbyd();
			hbyd.setShbh(shbh);
			hbyd.setHbh(jptpd.getCgHbh());
			hbyd.setCfrq(VeDate.formatToStr(jptpd.getCfrq(), "yyyy-MM-dd"));
			hbyd.setBy3(jptpd.getDdbh());
			hbyd = jpHbydServiceImpl.getHbydByHbh(hbyd);
			if (hbyd != null) {
				map.put("hblx", hbyd.getHblx());
				//int m = Math.abs(VeDate.getTwoMin(VeDate.dateToStrLong(jptpd.getCfrq()), hbyd.getYjCfsj()+ ":00"));
				map.put("hbsj",hbyd.getYwsc().intValue());
			}
		} catch (Exception e) {
            
		}
		return map;
	}
	
	
	
	/**
	 * 淘宝订单审核需要与供应回填手续费并确认退票
	 * @param tpdh
	 * @param shyhb
	 * @return
	 */
	private  Map<String, String>  taobaoGyConfirm(JpTpd jptpd,Shyhb shyhb){
		String tpdh=jptpd.getTpdh();
		String wdid=jptpd.getWdid();
		String shbh=jptpd.getShbh();
		JpDdsz jpDdsz = new JpDdsz();
		jpDdsz.setWdid(wdid);
		jpDdsz.setShbh(shbh);
		jpDdsz = jpDdszServiceImpl.getEntityById(jpDdsz);
		JpPtLog ptlb = new JpPtLog();
		JpddWork_tpd jwd = SpringContextUtil.getBean(JpddWork_tpd.class);
		TaobaoGy_tf tbgy = new TaobaoGy_tf(jpDdsz);
		Map<String, String> retMap = tbgy.fillfee(tpdh, shyhb, ptlb, jwd);
		if ("true".equals(VeStr.getValue(retMap, "status"))) {
			retMap= tbgy.confirmreturn(tpdh, shyhb, ptlb, jwd);
		}
		return retMap;
	}
}

