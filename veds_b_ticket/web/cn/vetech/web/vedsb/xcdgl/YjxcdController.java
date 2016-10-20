package cn.vetech.web.vedsb.xcdgl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Vecsb;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.base.VecsbServiceImpl;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddYj;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class YjxcdController extends MBaseControl<JpKhdd, JpKhddServiceImpl>{
	
	private static final String PAGE_SIZE = "10";
	@Autowired
	private AttachService attachService;
	@Autowired
	private WdzlszServiceImpl wdzlszServiceImpl;
	@Autowired
	private VecsbServiceImpl vecsbService;
	
	public void updateInitEntity(JpKhdd t) {
		
	}
	
	public void insertInitEntity(JpKhdd t) {
		
	}
	
	/**
	 * <显示页面时查找待打印，待邮寄，已邮寄的数量>
	 * 
	 * @param page
	 * @param model
	 * @return [参数说明]
	 * @author heqiwen
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "viewlist")
	public String view(@ModelAttribute()JpKhdd jpKhdd,HttpServletRequest request,ModelMap model){
		Shyhb user = SessionUtils.getShshbSession();
		Map<String, String> countMap = new HashMap<String,String>();
		String type=request.getParameter("type");
		String rqtj=request.getParameter("rqtj");
		String gngj=request.getParameter("gngj");
		if(jpKhdd==null){
			jpKhdd=new JpKhdd();
		}
		jpKhdd.setGngj(gngj);
		jpKhdd.setShbh(user.getShbh());
		if(StringUtils.isBlank(type)){
			jpKhdd.setFaid("1");//用来判断从url无参数进入
			String ddy=this.baseService.findDDYcount(jpKhdd);//查询待打印订单的状态
			String dyj=this.baseService.findDYJcount(jpKhdd);//查询待邮寄订单的状态
			String yyj=this.baseService.findYYJcount(jpKhdd);//查询已邮寄订单的状态
			countMap.put("DDY", ddy);
			countMap.put("DYJ", dyj);
			countMap.put("YYJ", yyj);
			model.addAttribute("countMap",countMap);
		}else{
			if(Integer.parseInt(rqtj)==1){
				jpKhdd.setDdsj(new Date());
			}else if(Integer.parseInt(rqtj)==2){
				jpKhdd.setCfsj("1");
			}else if(Integer.parseInt(rqtj)==3){
				jpKhdd.setDysj(new Date());
			}else{
				jpKhdd.setYjsj(new Date());
			}
			if(type.equals("0")){
				String ddy=this.baseService.findDDYcount(jpKhdd);//查询待打印订单的状态
				countMap.put("DDY", ddy);
			}else if(type.equals("1")){
				String dyj=this.baseService.findDYJcount(jpKhdd);//查询待邮寄订单的状态
				countMap.put("DYJ", dyj);
			}else if(type.equals("2")){
				String yyj=this.baseService.findYYJcount(jpKhdd);//查询已邮寄订单的状态
				countMap.put("YYJ", yyj);
			}
			model.addAttribute("countMap",countMap);
		}
		
		//查网店
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("zt", "1");//启用
		param.put("shbh", user.getShbh());
		param.put("ywlxs", new String[]{"1001901","1001902"});
		/*if ("1".equals(gngj)) {
			param.put("ywlxs", new String[]{"1001901"});
		} else if ("0".equals(gngj)){
			param.put("ywlxs", new String[]{"1001902"});
		}*/
		List<Wdzlsz> wdzlszList = wdzlszServiceImpl.getWdZlszListByYwlx(param);
		model.addAttribute("wdzlszList", wdzlszList);
		//查默认模板
		Vecsb csb=new Vecsb();
		csb.setBh("5555");//5555代表默认模板
		csb.setCompid(user.getShbh());
		try {
			csb = vecsbService.getVecsbByBh(csb);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入邮寄单管理失败 查询默认模板错误", e);
			return this.addError("进入邮寄单管理失败 查询默认模板错误" + e.getMessage(), e, "ComeToViewprint", model);
		}
		if(csb==null||csb.getCsz1()==null){
			model.addAttribute("mbid", "1001700");
		}else{
			model.addAttribute("mbid", csb.getCsz1());
		}
		return viewpath("list");
	}
	/**
	 * <条件查询邮寄行程单>
	 * 
	 * @param jpKhdd
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @param sortName
	 * @param model
	 * @param request
	 * @return [参数说明]
	 * 
	 * @return Page [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "queryPage",method = RequestMethod.POST)
	public @ResponseBody Page queryPage(@ModelAttribute()JpKhdd jpKhdd, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue  = "ddsj desc") String sortType, String sortName, ModelMap model,
			HttpServletRequest request){
		//Map<String, Object> params = Servlets.getParametersStartingWith(request, "",false);
		String type=request.getParameter("type");
		String rqtj=request.getParameter("rqtj");
		String gngj=request.getParameter("gngj");
		if(StringUtils.isBlank(type)){
			type="0";
		}
		jpKhdd.setYjzt(type);//邮寄状态,0待打印，1待邮寄，2已邮寄
		if(Integer.parseInt(rqtj)==1){
			jpKhdd.setDdsj(new Date());
		}else if(Integer.parseInt(rqtj)==2){
			jpKhdd.setCfsj("1");
		}else if(Integer.parseInt(rqtj)==3){
			jpKhdd.setDysj(new Date());
		}else{
			jpKhdd.setYjsj(new Date());
		}
		Page page = null;
		try {
			Shyhb user = SessionUtils.getShshbSession();
			jpKhdd.setShbh(user.getShbh());
			jpKhdd.setGngj(gngj);
			page  = this.baseService.queryPage(jpKhdd, pageNum,pageSize,sortType);
			if(page != null){
				List<?> list = page.getList();
				attachService.wdzl("WDID").hkgs("HKGS").shyhb("CJR",user.getShbh()).shyhb("SJR",user.getShbh()).execute(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("分页条件查询邮寄单失败", e);
		}
		return page;
		
	}
	
	/**
	 * <客户取消邮寄>
	 * 
	 * @param ddbhs
	 * @param model
	 * @param request
	 * @return [参数说明]
	 * 
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "cancelYj",method = RequestMethod.POST)
	public @ResponseBody int cancelYj(@RequestParam(value = "ddbhs") String ddbhs, Model model,@RequestParam(value = "flag") String flag){
		int result=0;
		try {
			if(StringUtils.isNotBlank(ddbhs)){
				if(ddbhs.indexOf(",")!=-1){
					String[] ddbh=ddbhs.split(",");
					for(String bh:ddbh){
						result=this.baseService.cancelYjdd(bh,flag);
					}
				}else{
					result=this.baseService.cancelYjdd(ddbhs,flag);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("邮寄单  客户取消邮寄失败", e);
		}
		return result;
	}
	
	/**
	 * <邮寄行程单>
	 * 
	 * @param ddbhs
	 * @param id
	 * @param model
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "youYj")
	public String youYjXcd(Model model,HttpServletRequest request){
		String flag=request.getParameter("flag");
		String ddbh=request.getParameter("ddbh");
		Map<String, Object> jpkhdd=this.baseService.queryYjdDdByDdbh(ddbh);
		model.addAttribute("jpkhdd", jpkhdd);
		if("1".equals(flag)){
			return "/xcdgl/yjxcd/yjxcd";
		}else{
			return "/xcdgl/yjxcd/cxyjxcd";
		}
	}

	/**
	 * <邮寄行程单>
	 * 
	 * @param jpKhddYj
	 * @param model
	 * @param request
	 * @return [参数说明]
	 * 
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "saveYj",method = RequestMethod.POST)
	public @ResponseBody int saveYj( @ModelAttribute()JpKhddYj jpKhddYj,Model model,HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		jpKhddYj.setYjzt("2");   //2表示已邮寄的状态
		jpKhddYj.setYjsj(new Date());
		jpKhddYj.setYjr(user.getBh());
		jpKhddYj.setShbh(user.getShbh());
		try {
			return this.baseService.updateYjdAndDd(jpKhddYj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("邮寄单  邮寄行程单保存失败", e);
			return 0;
		}
	}
}
