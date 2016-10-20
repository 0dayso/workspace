package cn.vetech.web.vedsb.pidgl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.vetech.core.business.pid.api.pidgl.Yhz;
import org.vetech.core.business.pid.api.pidgl.YhzServiceImpl;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.web.vedsb.SessionUtils;


@Controller
public class ZglController extends AbstractBaseControl{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	protected YhzServiceImpl yhzServiceImpl;
	
	/**
	 * 修改和新增用户组
	 * @param yhz
	 * @param model
	 * @param method
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save( Yhz yhz,ModelMap model,
						@RequestParam(value = "method") String method){
		try{
			if(yhz.getEtermmsg()==null){
				yhz.setEtermmsg("0");
			}
			Shyhb yh = SessionUtils.getShshbSession();
			String msg = this.yhzServiceImpl.ModifyOrAddGroup(yhz,method,"WEB01",yh.getShbh());
			if(StringUtils.isNotBlank(msg)){
				throw new Exception(msg);
			}
		}catch (Exception e) {
			logger.error("保存失败", e);
			return this.addError("保存失败:"+e.getMessage(),e, "edit",model);
		}
		return "/common/turning";
	}
	
	/**
	 * 查询用户组
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public String list(ModelMap model){
		try{
			Shyhb yh = SessionUtils.getShshbSession();
			model.addAttribute("list", yhzServiceImpl.getAllGroups(yh.getShbh(),"WEB01",true));
		}catch(Exception e){
			logger.error("获取用户组信息失败", e);
			return this.addError("获取用户组信息失败"+e.getMessage(),e, "edit",model);
		}
		return viewpath("list");
	}
	
	/**
	 * 
	 * @param yhzbh
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit_{yhzbh}")
	public String edit(@PathVariable("yhzbh") String yhzbh, ModelMap model) {
		try{
			model.addAttribute("entity", this.yhzServiceImpl.GetGroupPropertiesByYhzbh(yhzbh,"WEB01"));
			return viewpath("edit");
		}catch (Exception e) {
			logger.error("编辑错误", e);
			return this.addError("编辑错误"+e.getMessage(), e, "edit", model);
		}
	}
}
