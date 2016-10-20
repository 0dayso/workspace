package cn.vetech.web.vedsb.cgptsz;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtLogServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpPtLogController extends MBaseControl<JpPtLog, JpPtLogServiceImpl>{
	
	@Override
	public void updateInitEntity(JpPtLog t) {
	}

	@Override
	public void insertInitEntity(JpPtLog t) {
	}
	
	public void selectInitEntity(Map<String, Object> param){
		Shyhb user = SessionUtils.getShshbSession();
		param.put("search_EQ_shbh", user.getShbh());
	}
	
}
