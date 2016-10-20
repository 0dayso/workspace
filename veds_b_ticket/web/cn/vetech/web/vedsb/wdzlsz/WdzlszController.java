package cn.vetech.web.vedsb.wdzlsz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class WdzlszController extends MBaseControl<Wdzlsz, WdzlszServiceImpl>{
	
	@RequestMapping(value = "getWdzlszList")
	@ResponseBody
	public Map<String,Object> getWdzlszList(@RequestParam(value = "gngj", defaultValue  = "") String gngj){
		Map<String,Object> reparam=new HashMap<String,Object>();
		try {
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
			List<Wdzlsz> wdzlszList = this.baseService.getWdZlszListByYwlx(param);
			reparam.put("CODE", "0");
			reparam.put("DATA", wdzlszList);
		} catch (Exception e) {
			logger.error("查询网店集合失败"+e.getMessage());
			reparam.put("CODE", "-1");
			reparam.put("MSG", "查询网店集合失败");
		}
		return reparam;
	}

	@Override
	public void updateInitEntity(Wdzlsz t) {
		
	}

	@Override
	public void insertInitEntity(Wdzlsz t) {
	}

}
