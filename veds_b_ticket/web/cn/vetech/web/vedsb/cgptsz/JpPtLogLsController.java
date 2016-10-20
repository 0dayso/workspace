package cn.vetech.web.vedsb.cgptsz;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLogLs;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogLsServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpPtLogLsController extends MBaseControl<JpPtLogLs, JpPtLogLsServiceImpl>{
	
	@Override
	public void updateInitEntity(JpPtLogLs t) {
	}

	@Override
	public void insertInitEntity(JpPtLogLs t) {
	}
	
	public void selectInitEntity(Map<String, Object> param){
		Shyhb user = SessionUtils.getShshbSession();
		param.put("search_EQ_shbh", user.getShbh());
	}
}
