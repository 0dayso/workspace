package cn.vetech.web.vedsb.cpsz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.entity.cpsz.JpBspTpsz;
import cn.vetech.vedsb.jp.entity.cpsz.JpZdtfpGzsz;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.cpsz.JpBspTpszServiceImpl;
import cn.vetech.vedsb.jp.service.cpsz.JpZdtfpGzszServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpZdtfpGzszController extends MBaseControl<JpZdtfpGzsz, JpZdtfpGzszServiceImpl> {
	@Autowired
	private AttachService attachService;
	@Autowired
	private WdzlszServiceImpl wdzlszServiceImpl;
	
	@Autowired
	private JpBspTpszServiceImpl jpBspTpszServiceImpl;
	
	
	@Override
	public void updateInitEntity(JpZdtfpGzsz t) {
		
	}

	@Override
	public void insertInitEntity(JpZdtfpGzsz t) {
		
	}
	
	@RequestMapping(value = "viewlist")
	public String viewlist(ModelMap model,HttpServletRequest request){
		//查网店
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("zt", "1");//启用
		Shyhb user = SessionUtils.getShshbSession();
		param.put("shbh", user.getShbh());
		param.put("ywlxs", new String[]{"1001901","1001902"});
		List<Wdzlsz> wdzlszList = wdzlszServiceImpl.getWdZlszListByYwlx(param);
		model.addAttribute("wdzlszList", wdzlszList);
		
		return viewpath("list");
	}
	/**
 	 * 分页查询
	 */
	@RequestMapping(value = "queryPage",method = RequestMethod.POST)
	public @ResponseBody Page queryPage(@ModelAttribute()JpZdtfpGzsz zdtfp,ModelMap model,HttpServletRequest request) {
			Shyhb user = SessionUtils.getShshbSession();
			zdtfp.setShbh(user.getShbh());
			//String gngj=request.getParameter("gngj");
			Page page = null;
			try {
				page  = this.baseService.queryPage(zdtfp);
				if(page != null){
					List<?> list = page.getList();
					if(CollectionUtils.isNotEmpty(list)){
						attachService.hkgs("HKGS").execute(list);
					}
				}
			} catch (Exception e) {
				logger.error("查询自动退废票规则设置列表错误", e);
			}
			return page;
	}

	/**
	 * <进入增加或修改页面>
	 * 
	 * @param model
	 * @param request
	 * @return [参数说明]
	 * @author heqiwen
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "enterEditPage")
	public String enterEditPage(ModelMap model,HttpServletRequest request){
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		try {
			//查网店
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("zt", "1");//启用
			Shyhb user = SessionUtils.getShshbSession();
			param.put("shbh", user.getShbh());
			param.put("ywlxs", new String[]{"1001901","1001902"});
			List<Wdzlsz> wdzlszList = wdzlszServiceImpl.getWdZlszListByYwlx(param);
			model.addAttribute("wdzlszList", wdzlszList);
			if(StringUtils.isNotBlank(id)){
				JpZdtfpGzsz tfp=new JpZdtfpGzsz();
				tfp.setId(id);
				tfp=this.baseService.getEntityById(tfp);
				model.addAttribute("tfp", tfp);
				JpBspTpsz jpBspTpsz=this.jpBspTpszServiceImpl.selectByShbh(user.getShbh());
				model.addAttribute("bspsz", jpBspTpsz);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入增加或修改页面错误", e);
		}
		if("3".equals(flag)){//查看详情
			return viewpath("detailtfp");
		}
		return viewpath("edittfp");
	}
	
	/**
	 * <功能详细描述>
	 * 
	 * @param tpfp
	 * @param model
	 * @param request
	 * @return [参数说明]
	 * @author heqiwen
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "saveTfp")
	public @ResponseBody int saveTfp(@ModelAttribute()JpZdtfpGzsz tpfp,JpBspTpsz jpBspTpsz,String jpBspTpszId,ModelMap model,HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		String flag=request.getParameter("flag");//判断是否是复制的
		if(StringUtils.isNotBlank(flag)){
			tpfp.setId(null);
		}
		String shbh=user.getShbh();
		tpfp.setShbh(shbh);
		tpfp.setCzyhbh(user.getBh());
		tpfp.setCzDatetime(VeDate.getUserDate("yyyy-MM-dd"));
		
		String pzlx=tpfp.getPzlx();
		if(StringUtils.isNotBlank(pzlx)){
			if(!pzlx.startsWith("/")){
				pzlx="/"+pzlx;
			}
			if(!pzlx.endsWith("/")){
				pzlx=pzlx+"/";
			}
			tpfp.setPzlx(pzlx);
		}
		
		try {
			if(StringUtils.isBlank(tpfp.getId())){
				tpfp.setId(VeDate.getNo(4));
				this.baseService.insert(tpfp);
			}else{
				this.baseService.update(tpfp);
			}
			
			if(jpBspTpsz!=null){
				if (StringUtils.isBlank(jpBspTpszId)) {
					jpBspTpsz.setShbh(shbh);
					jpBspTpsz.setId(VeDate.getNo(6));
					jpBspTpszServiceImpl.insert(jpBspTpsz);
				}else{
					jpBspTpsz.setId(jpBspTpszId);
					jpBspTpsz.setShbh(shbh);
					jpBspTpszServiceImpl.update(jpBspTpsz);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("编辑自动退废票规则设置错误", e);
			return 0;
		}
		
		return 1;
	}
	
	
	/**
	 * <删除自动退废票规则>
	 * 
	 * @param ids
	 * @param model
	 * @param request
	 * @return [参数说明]
	 * @author heqiwen
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "deleteTfp")
	public @ResponseBody int deleteTfp(@RequestParam(value="ids")String ids,ModelMap model,HttpServletRequest request){
		try {
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("isdel", 1);//删除状态
			Shyhb user = SessionUtils.getShshbSession();
			param.put("shbh", user.getShbh());
			param.put("ids", ids.split(","));
			this.baseService.deleteChange(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除自动退废票规则设置错误", e);
			return 0;
		}
		return 1;
	}
	
	
	/**
	 * <改变退废票规则的状态,启用停用审核>
	 * 
	 * @param ids
	 * @param zt
	 * @param model
	 * @param request
	 * @return [参数说明]
	 * @author heqiwen
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "updateZt")
	public @ResponseBody int updateZt(@RequestParam(value="ids")String ids,@RequestParam(value="zt")String zt,
			ModelMap model,HttpServletRequest request){
		try {
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("zt", zt);//启用
			Shyhb user = SessionUtils.getShshbSession();
			param.put("shbh", user.getShbh());
			param.put("ids", ids.split(","));
			this.baseService.updateTfpZt(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除自动退废票规则设置错误", e);
			return 0;
		}
		return 1;
	}
}
