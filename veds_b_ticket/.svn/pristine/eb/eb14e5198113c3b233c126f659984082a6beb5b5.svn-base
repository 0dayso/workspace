package cn.vetech.web.vedsb.b2bsz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgsxx;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bHkgsxxServiceImpl;


/**
 * 
 * @author wangshengliang
 *
 */
@Controller
public class JpB2bHkgsxxController extends MBaseControl<JpB2bHkgsxx,JpB2bHkgsxxServiceImpl>{

	@Override
	public void updateInitEntity(JpB2bHkgsxx t) {
	}

	@Override
	public void insertInitEntity(JpB2bHkgsxx t) {
	}
	
	@RequestMapping(value = "query")
	public @ResponseBody Map<String,Object> query(String bca){
		//B2B	720102   //B2C   720104
		Map<String,Object> param=new HashMap<String,Object>();
		if("720102".equals(bca) || "720104".equals(bca)){
			List<JpB2bHkgsxx>  hkgsxxList=this.baseService.selectByBca(bca);
			param.put("DATA", hkgsxxList);
		}
		param.put("CODE", "0");
		return param;
	}
	
   
}
