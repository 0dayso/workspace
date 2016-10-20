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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jphbyd.BHkgsHbgz;
import cn.vetech.vedsb.jp.service.jphbyd.BHkgsHbgzServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class BHkgsHbgzController extends MBaseControl<BHkgsHbgz,BHkgsHbgzServiceImpl>{

	@Override
	public void updateInitEntity(BHkgsHbgz t) {
		Shyhb user = SessionUtils.getShshbSession();
		t.setXguserid(user.getBh());
		t.setXgDatetime(VeDate.getNow());
	}

	@Override
	public void insertInitEntity(BHkgsHbgz t) {
		Shyhb user = SessionUtils.getShshbSession();
		t.setId(VeDate.getNo(4));
		t.setShbh(user.getShbh());
		t.setXguserid(user.getBh());
		t.setXgDatetime(VeDate.getNow());
	}

	/**
	 * 查询数据返回list界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "query")
	public String query(ModelMap model){
		Shyhb user = SessionUtils.getShshbSession();
		BHkgsHbgz bhkgshbgz = new BHkgsHbgz();
		bhkgshbgz.setShbh(user.getShbh());
		List<BHkgsHbgz> hbgzList = this.baseService.getMyBatisDao().select(bhkgshbgz);
		model.addAttribute("hbgzList", hbgzList);
		model.addAttribute("titlelx", "2");
		return viewpath("list");
	}
	/**
	 * 新增、编辑
	 * @param bz
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public Map<String,String> saveBean(@ModelAttribute("entity") BHkgsHbgz bz){
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
	 * 根据id删除航班异动规则表中数据
	 */
	@Override
	@RequestMapping(value = "delete_{id}")
	public String delete(@PathVariable("id") String id, ModelMap model){
		try{
			BHkgsHbgz bz = new BHkgsHbgz();
			bz.setId(id);
			this.baseService.deleteById(bz);
		}catch (Exception e) {
			logger.error("删除错误", e);
			return this.addError("删除错误"+e.getMessage(),e, "list",model);
		}
		return query(model);
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
		List<BHkgsHbgz> list = this.baseService.getMyBatisDao().getBeanByShbh(user.getShbh());
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
					for (BHkgsHbgz bz : list) {
						if(bz.getHkgs().contains(hs) && !id.equals(bz.getId())){
							flag = true;
						}
					}
				}else{//添加
					for (BHkgsHbgz bz : list) {
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
