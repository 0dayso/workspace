package cn.vetech.web.vedsb.jpzdcp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpGjdocaSz;
import cn.vetech.vedsb.jp.service.jpzdcp.JpGjdocaSzServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
/**
 * 国际出票规则DOCA设置
 *
 */
@Controller
public class JpGjdocaSzController extends MBaseControl<JpGjdocaSz, JpGjdocaSzServiceImpl>{

	@Override
	public void updateInitEntity(JpGjdocaSz t) {
		String csszm = t.getCsszm();
		if(!t.getCsszm().startsWith("/")){
			csszm = "/"+csszm;
		}
		if(!t.getCsszm().endsWith("/")){
			csszm = csszm + "/";
		}
		t.setCsszm(csszm);
		Shyhb user =SessionUtils.getShshbSession();
		t.setShbh(user.getShbh());
		t.setXgyhbh(user.getBh());
		t.setXgDatetime(VeDate.getNow());
	}

	@Override
	public void insertInitEntity(JpGjdocaSz t) {
		String csszm = t.getCsszm();
		if(!t.getCsszm().startsWith("/")){
			csszm = "/"+csszm;
		}
		if(!t.getCsszm().endsWith("/")){
			csszm = csszm + "/";
		}
		t.setCsszm(csszm);
		Shyhb user =SessionUtils.getShshbSession();
		t.setShbh(user.getShbh());
		t.setXgyhbh(user.getBh());
		t.setXgDatetime(VeDate.getNow());
	}
	/**
	 * 根据商户编号查询所有记录
	 * @param model
	 * @return
	 */
	@RequestMapping(value="query")
	public String query(ModelMap model){
		try {
			Shyhb user =SessionUtils.getShshbSession();
			JpGjdocaSz t = new JpGjdocaSz();
			t.setShbh(user.getShbh());
			t.setOrderBy("id desc");
			List<JpGjdocaSz> list = this.baseService.queryList(t);
			model.addAttribute("list",list);
		} catch (Exception e) {
			logger.error("国际DOCA设置失败"+e);
		}
		return viewpath("list");
	}
	/**
	 * 新增/编辑
	 * @param t
	 * @return
	 */
	@RequestMapping(value="savesz")
	@ResponseBody
	public String saveSz(@ModelAttribute("entity")JpGjdocaSz t){
		try {
			String id = t.getId();
			//验证是否设置过
			if("-1".equals(yzCsszm(t.getCsszm(),id))){
				return "-1";
			}
			if(StringUtils.isBlank(id)){
				insertInitEntity(t);
				this.baseService.insert(t);
			}else{
				updateInitEntity(t);
				this.baseService.updateSelective(t);
			}
			return "0";
		} catch (Exception e) {
			logger.error("保存DOCA设置报错:"+e);
			return "-1";
		}
	}
	@RequestMapping(value="toedit")
	public String toEdit(String id,ModelMap model){
		if(StringUtils.isNotBlank(id)){
			JpGjdocaSz t = new JpGjdocaSz();
			t.setId(id);
			t=this.baseService.getEntityById(t);
			model.addAttribute("entity", t);
		}
		return viewpath("edit");
	}
	/**
	 * 单条记录删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="todel")
	@ResponseBody
	public String toDel(String id){
		try {
			JpGjdocaSz t = new JpGjdocaSz();
			t.setId(id);
			this.baseService.deleteById(t);
			return "0";
		} catch (Exception e) {
			logger.error("DOCA删除失败"+e);
			return "-1";
		}
	}
	/**
	 * 批量删除
	 */
	@RequestMapping(value="batchDel")
	@ResponseBody
	public String batchDel(String ids){
		if(StringUtils.isBlank(ids)){
			return "-1";
		}
		try {
			String[] idArr = ids.split(",");
			for (String id:idArr) {
				JpGjdocaSz t = new JpGjdocaSz();
				t.setId(id);
				this.baseService.deleteById(t);
			}
			return "0";
		} catch (Exception e) {
			logger.error("DOCA批量删除"+e);
			return "-1";
		}
	}
	/**
	 * 验证csszm是否已经设置
	 */
	@RequestMapping(value="yzCsszm")
	@ResponseBody
	public String yzCsszm(String csszms,String id){
		if(StringUtils.isBlank(csszms)){
			return "-1";
		}
		try {
			Map<String,Object> param = new HashMap<String,Object>();
			
			Shyhb user =SessionUtils.getShshbSession();
			param.put("shbh", user.getShbh());
			
			if(csszms.startsWith("/")){
				csszms = csszms.replaceFirst("/", "");
			}
			if(csszms.endsWith("/")){
				csszms = csszms.substring(0, csszms.length()-2);
			}
			String[] csszmArr = csszms.split("\\/");
			param.put("csszms", csszmArr);
			
			int count = this.baseService.getMyBatisDao().getCountByShbhAndCsszm(param);
			if(StringUtils.isBlank(id) && count > 0){
				return "-1";
			}
			if(StringUtils.isNotBlank(id) && count>1){
				return "-1";
			}
			return "0";
		} catch (Exception e) {
			logger.error("DOCA设置验证csszm是否已经设置失败"+e);
			return "-1";
		}
	}
}
