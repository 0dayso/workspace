package cn.vetech.web.vedsb.jphbyd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jphbyd.BQinfoHbydgz;
import cn.vetech.vedsb.jp.service.jphbyd.BQinfoHbydgzServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

/**
 * 航班异动规则表操作
 * @author win7
 *
 */
@Controller
public class BQinfoHbydgzController extends MBaseControl<BQinfoHbydgz,BQinfoHbydgzServiceImpl>{

	@Override
	public void updateInitEntity(BQinfoHbydgz t) {
		Shyhb user = SessionUtils.getShshbSession();
		t.setXguserid(user.getBh());
		t.setXgDatetime(VeDate.getNow());
		//返回值如果不为空,则表示勾选 1表示勾选自动短信通知 0表示非自动短信通知
		if("on".equals(t.getInfomation())){
			t.setInfomation("1");
		}else{
			t.setInfomation("0");
		}
		//返回值如果不为空,则表示勾选  1表示勾选自动电话通知 0表示非自动电话通知
		if("on".equals(t.getTelphone())){
			t.setTelphone("1");
		}else{
			t.setTelphone("0");
		}
	}

	@Override
	public void insertInitEntity(BQinfoHbydgz t) {
		Shyhb user = SessionUtils.getShshbSession();
		t.setId(VeDate.getNo(4));
		t.setXguserid(user.getBh());
		t.setXgDatetime(VeDate.getNow());
		t.setShbh(user.getShbh());
		//返回值如果不为空,则表示勾选 1表示勾选自动短信通知 0表示非自动短信通知
		if(StringUtils.isNotBlank(t.getInfomation())){
			t.setInfomation("1");
		}else{
			t.setInfomation("0");
		}
		//返回值如果不为空,则表示勾选  1表示勾选自动电话通知 0表示非自动电话通知
		if(StringUtils.isNotBlank(t.getTelphone())){
			t.setTelphone("1");
		}else{
			t.setTelphone("0");
		}
	}
	
	/**
	 * 根据id删除航班异动规则表中数据
	 */
	@Override
	@RequestMapping(value = "deleteBQinfoHbydgz_{id}")
	public String delete(@PathVariable("id") String id, ModelMap model){
		try{
			BQinfoHbydgz BQinfoHbydgz = new BQinfoHbydgz();
			BQinfoHbydgz.setId(id);
			this.baseService.deleteById(BQinfoHbydgz);
			return queryList(model);
		}catch (Exception e) {
			logger.error("删除错误", e);
			return this.addError("删除错误"+e.getMessage(),e, "list",model);
		}
	}
	
//	/**
//	 * 新增,编辑数据保存
//	 */
//	@Override
//	@RequestMapping(value = "save", method = RequestMethod.POST)
//	public String save(@Valid @ModelAttribute("entity") BQinfoHbydgz t,BindingResult bindingResult, ModelMap model){
//		try{
//			this.valid(bindingResult);
//			String edit = (String)model.get("EDIT");
//			if("1".equals(edit)){
//				updateInitEntity(t);
//				this.baseService.update(t);
//			}else{
//				insertInitEntity(t);
//				this.baseService.insert(t);
//			}
//		}catch (Exception e) {
//			logger.error("保存失败", e);
//			return this.addError("保存失败"+e.getMessage(),e, "list",model);
//		}
//		return queryList(model);
//	}
	
	/**
	 * 新增、编辑
	 * @param bz
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public Map<String,String> saveBean(@ModelAttribute("entity") BQinfoHbydgz bz){
		Map<String,String> param = new HashMap<String,String>();
		try {
			//对航空公司非空检查
			if(StringUtils.isBlank(bz.getHkgs())){
				param.put("CODE", "-1");
				param.put("MSG", "航空公司不能为空");
				return param;
			}
			if(StringUtils.isNotBlank(bz.getId())){//ID不为空，则为编辑
				//检查该航司是否已设置过
				if(checkhkgssfsz(bz.getHkgs(),bz.getId())){
					param.put("CODE", "-1");
					param.put("MSG", "该航空公司已经设置过");
					return param;
				}
				updateInitEntity(bz);
				this.baseService.update(bz);
			}else{//新增
				//检查该航司是否已设置过
				if(checkhkgssfsz(bz.getHkgs(),null)){
					param.put("CODE", "-1");
					param.put("MSG", "该航空公司已经设置过");
					return param;
				}
				insertInitEntity(bz);
				this.baseService.insert(bz);
			}
			param.put("CODE", "0");
			param.put("MSG", "保存成功");
			return param;
		} catch (Exception e) {
			logger.error("保存失败", e);
			param.put("CODE", "-1");
			param.put("MSG", "保存失败");
			return param;
		}
	}
	
	/**
	 * 查询数据返回list界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "queryList", method = RequestMethod.GET)
	public String queryList(ModelMap model){
		Shyhb user = SessionUtils.getShshbSession();
		BQinfoHbydgz t = new BQinfoHbydgz();
		t.setShbh(user.getShbh());
		List<BQinfoHbydgz> hbList = this.baseService.getMyBatisDao().select(t);
		model.addAttribute("hbList", hbList);
		return viewpath("list");
	}
	
	@RequestMapping(value = "checkhkgs")
	@ResponseBody
	public Map<String,Object> checkhkgs(@RequestParam String hkgs,@RequestParam String id){
		Map<String,Object> reparam = new HashMap<String,Object>();
		boolean flag = checkhkgssfsz(hkgs,id);
		if(flag){
			reparam.put("CODE", "-1");
			reparam.put("MSG", "该航空公司已经设置过");
		}else{
			reparam.put("CODE", "0");
		}
		return reparam;
	}
	
	/**
	 * 检查hkgs是否设置过
	 * @param hkgs
	 * @return true:已设置过  false:未设置过
	 */
	private boolean checkhkgssfsz(String hkgs,String id){
		Shyhb user = SessionUtils.getShshbSession();
		//通过商户编号获取所有已经设置过的集合
		BQinfoHbydgz t = new BQinfoHbydgz();
		t.setShbh(user.getShbh());
		List<BQinfoHbydgz> list = this.baseService.getMyBatisDao().select(t);
		//---表示所有航司
		if("---".equals(hkgs) && CollectionUtils.isNotEmpty(list)){
			return true;
		}
		boolean flag = false;
		if(CollectionUtils.isNotEmpty(list)){
			//hkgs航空公司(---标识所有适用，可多选/分隔)
			String[] hksgs = hkgs.split("\\/");
			for(String hs : hksgs){
				if(StringUtils.isNotBlank(id)){//id不为空，则为更新
					for (BQinfoHbydgz bz : list) {
						if(bz.getHkgs().contains(hs) && !id.equals(bz.getId())){
							flag = true;
						}
					}
				}else{//添加
					for (BQinfoHbydgz bz : list) {
						if("---".equals(bz.getHkgs()) || bz.getHkgs().contains(hs)){
							flag = true;
						}
					}
				}
			}
		}
		return flag;
	}
}
