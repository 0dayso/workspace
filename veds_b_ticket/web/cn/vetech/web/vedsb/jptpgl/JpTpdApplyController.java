package cn.vetech.web.vedsb.jptpgl;


import java.math.BigDecimal;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.Bean2Map;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpHd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpHdServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpTpdApplyController extends MBaseControl<JpTpd, JpTpdServiceImpl>{
	
	
	@Autowired
	private JpHdServiceImpl  jpHdServiceImpl;
	
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	
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
	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getJptpdForApply",method = RequestMethod.POST)
	public String getJptpdForApply(Model model,HttpServletRequest request) {
		
		String forward="apply";
		try {
			Map<String, Object> param = Servlets.getParametersStartingWith(request, "", false);
			Shyhb user = SessionUtils.getShshbSession();
			String shbh = user.getShbh();
			param.put("shbh", shbh);
			List<Map<String, Object>> jpList=this.baseService.getJptpdForApply(param);
			String ddbh="";
			if(CollectionUtils.isEmpty(jpList)){
				model.addAttribute("errormsg", "没有匹配到机票信息");
				return viewpath(forward);
			}
			
			//存放订单编号，判断是否查询返回多个订单
			Map<String, Object> ddbhMap=new HashMap<String, Object>();
			for(Map<String, Object> jpMap:jpList){
				ddbh = VeStr.getValue(jpMap, "DDBH");
				ddbhMap.put(ddbh, ddbh);
			}
			
			//查询返回多个订单需要在申请页面进行选择，反之就跳转到退票单申请填写页面
			if (ddbhMap.size()== 1) {
				
				//通过票号获取对应的机票航段信息
				for(Map<String, Object> jpMap:jpList){
					String tkno=VeStr.getValue(jpMap,"TKNO");
					JpHd jphd=new JpHd();
					jphd.setShbh(shbh);
					jphd.setTkno(tkno);
					List<JpHd> jpHdList=jpHdServiceImpl.getJpHdByTkno(jphd);
					boolean flag=true;
					if(CollectionUtils.isNotEmpty(jpHdList)){
						//记录存在�?��明细ID的个�?
						for(JpHd j:jpHdList){
							if(StringUtils.isBlank(j.getTpmxid())){
								flag=false;
							}
						}
					}
					
					if(flag){
						model.addAttribute("errormsg", "没有可退票的航段");
						return viewpath(forward);
					}
					jpMap.put("TKHDLIST", jpHdList);
				}
				forward="tpd_add";
				JpKhdd jpkhdd = new JpKhdd();
				jpkhdd.setShbh(shbh);
				jpkhdd.setDdbh(ddbh);
				jpkhdd = jpKhddServiceImpl.getEntityById(jpkhdd);
				List<JpKhddHd> hdList = jpKhddHdServiceImpl.getKhddHdListByDDbh(ddbh,shbh);
				model.addAttribute("hdList", hdList);
				model.addAttribute("jpkhdd", jpkhdd);
			}
			model.addAttribute("jpList", jpList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewpath(forward);
	}
	
	@RequestMapping(value = "getTpInfo",method = RequestMethod.POST)
	public String getTpInfo(Model model,HttpServletRequest request){
	     System.out.println("*********create********");
	     //System.out.println(request.getParameter("jpList"));
	     System.out.println(request.getParameterMap());
	     
	    // String jpList=request.getParameter("jpList");
	     return "tpd_add_tpxx";
	}
	
	
	
	@RequestMapping(value = "createTpd",method = RequestMethod.POST)
	@ResponseBody
	public String createTpd(Model model,JpTpd jptpd,HttpServletRequest request){
		String state="error";
		Map<String, Object> param = Servlets.getParametersStartingWith(request, "", false);
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		String ddbh=VeStr.getValue(param, "ddbh");
		try {
			String [] tkno_arr=request.getParameterValues("tkno");
			String [] xsCw_arr=request.getParameterValues("xs_cw");
			String [] cgCw_arr=request.getParameterValues("cg_cw");
			String [] cjr_arr=request.getParameterValues("cjr");
			String [] cjrlx_arr=request.getParameterValues("cjrlx");
			String [] zjlx_arr=request.getParameterValues("zjlx");
			String [] zjhm_arr=request.getParameterValues("zjhm");
			int len=tkno_arr.length;
			List<JpTpdMx> mxList=new ArrayList<JpTpdMx>();
			for(int i=0;i<len;i++){
			    //生成明细
				JpTpdMx jptpdMx = new JpTpdMx();
				jptpdMx.setTkno(tkno_arr[i]);
				jptpdMx.setShbh(shbh);
				jptpdMx.setSxh((short)(i+1));
				jptpdMx.setCjr(cjr_arr[i]);
				jptpdMx.setCjrlx(cjrlx_arr[i]);
				jptpdMx.setZjlx(zjlx_arr[i]);
				jptpdMx.setZjhm(zjhm_arr[i]);
				
				jptpdMx.setCgCw(cgCw_arr[i]);
				jptpdMx.setCgZdj(NumberUtils.toLong(VeStr.getValue(param, "cgZdj"),0));
				jptpdMx.setCgPj(new BigDecimal(NumberUtils.toDouble(VeStr.getValue(param, "cgPj"),0)));
				jptpdMx.setCgJsf(NumberUtils.toLong(VeStr.getValue(param, "cgJsf"),0));
				jptpdMx.setCgTax(NumberUtils.toLong(VeStr.getValue(param, "cgTax"),0));
				jptpdMx.setCgTpfl(new BigDecimal(NumberUtils.toDouble(VeStr.getValue(param, "cgTpfl"),0)/100));
				jptpdMx.setCgTpf(new BigDecimal(NumberUtils.toDouble(VeStr.getValue(param, "cgTpf"),0)));
				jptpdMx.setCgTkje(new BigDecimal(NumberUtils.toDouble(VeStr.getValue(param, "cgTkje"),0)));
			
				jptpdMx.setXsCw(xsCw_arr[i]);
				jptpdMx.setXsZdj(NumberUtils.toLong(VeStr.getValue(param, "xsZdj"),0));
				jptpdMx.setXsPj(new BigDecimal(NumberUtils.toDouble(VeStr.getValue(param, "xsPj"),0)));
				jptpdMx.setXsJsf(NumberUtils.toLong(VeStr.getValue(param, "xsJsf"),0));
				jptpdMx.setXsTax(NumberUtils.toLong(VeStr.getValue(param, "xsTax"),0));
				jptpdMx.setXsTpfl(new BigDecimal(NumberUtils.toDouble(VeStr.getValue(param, "xsTpfl"),0)/100));
				jptpdMx.setXsTpsxf(new BigDecimal(NumberUtils.toDouble(VeStr.getValue(param, "xsTpsxf"),0)));
				jptpdMx.setXsTkje(new BigDecimal(NumberUtils.toDouble(VeStr.getValue(param, "xsTkje"),0)));
				jptpdMx.setXb("M");
				mxList.add(jptpdMx);
			}
			
			JpKhdd jpkhdd=new JpKhdd();
			jpkhdd.setDdbh(ddbh);
			jpkhdd.setShbh(shbh);
			jpkhdd=jpKhddServiceImpl.getEntityById(jpkhdd);

			//从数据库中查询明细汇总
			jptpd.setDdbh(jpkhdd.getDdbh());
			jptpd.setShbh(shbh);
			jptpd.setGngj(jpkhdd.getGngj());
			jptpd.setFaid(jpkhdd.getFaid());
			jptpd.setWdpt(jpkhdd.getWdpt());
			jptpd.setWdid(jpkhdd.getWdid());
			jptpd.setWdZclx(jpkhdd.getWdZclx());
			jptpd.setWdZcdm(jpkhdd.getWdZcdm());
			jptpd.setWdDdlx(jpkhdd.getWdDdlx());
			jptpd.setZcQd(jpkhdd.getZcQd());
			jptpd.setZcLy(jpkhdd.getZcLy());
			jptpd.setDdsj(VeDate.getNow());
			jptpd.setDdyh(user.getBh());
			jptpd.setDdbm(user.getBmmc());
			jptpd.setWbddbh(jpkhdd.getWbdh());
			jptpd.setXsPnrNo(jpkhdd.getXsPnrNo());
			jptpd.setXsPnrZt(jpkhdd.getXsPnrZt());
			jptpd.setXsHkgsPnr(jpkhdd.getXsHkgsPnr());
			jptpd.setHc(jpkhdd.getHc());
			jptpd.setCfrq(jpkhdd.getCfrq());
			jptpd.setCfsj(jpkhdd.getCfsj());
			jptpd.setCjrs(jpkhdd.getCjrs());
			jptpd.setCjr(jpkhdd.getCjr());
			jptpd.setXsHbh(jpkhdd.getXsHbh());
			
			jptpd.setXsTpfl(new BigDecimal(NumberUtils.toDouble(VeStr.getValue(param, "xsTpfl"),0)));
			jptpd.setCgTpfl(new BigDecimal(NumberUtils.toDouble(VeStr.getValue(param, "cgTpfl"),0)));
			jptpd.setCgStje(new BigDecimal(0));
			jptpd.setXsTkkm(VeStr.getValue(param, "tkkm"));
			jptpd.setXsTpzt(JpTpd.XS_TPZT_YSQ);
			jptpd.setXsTpzm("");
			jptpd.setXsTpbz(VeStr.getValue(param, "bzbz"));
			jptpd.setCgPnrNo(jpkhdd.getCgPnrNo());
			jptpd.setCgHkgsPnr(jpkhdd.getCgHkgsPnr());
			jptpd.setCgHbh(jpkhdd.getCgHbh());
			jptpd.setCgZwblsj(VeDate.getNow());
			jptpd.setCgTkkm("");
			jptpd.setCgTpzt(JpTpd.CG_TPZT_DTJ);
			jptpd.setCgSfzytp("1");
			jptpd.setSfzdd("1");
			jptpd.setYwshZt("0");
			jptpd.setDzshZt("1");
			
			Map<String,Object> param_sql = new HashMap<String,Object>();
			
			param_sql.put("TFD", Bean2Map.getMap(jptpd));
			List<Map<String,Object>> tfmxList=new ArrayList<Map<String,Object>>();
			if (CollectionUtils.isNotEmpty(mxList)) {
				for (JpTpdMx mx : mxList) {
					Map<String, Object> m=Bean2Map.getMap(mx);
					tfmxList.add(m);
				}
			}
			
			param_sql.put("TFDMX", tfmxList);
			System.out.println("【"+param_sql+"】");
			Map<String,Object> m=procedureServiceImpl.createJpTpd(param_sql);
			String result=m.get("result").toString();
			if("0".equals(result)){
				state="ok";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("生成退票单失败", e);
		}
	    return state;
	}

}
