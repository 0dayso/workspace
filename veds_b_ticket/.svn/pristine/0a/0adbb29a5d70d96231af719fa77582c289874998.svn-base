package cn.vetech.web.vedsb.jppz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpPzController extends MBaseControl<JpPz, JpPzServiceImpl> {
	
	/**
	 * 根据商户编号查询配置集合
	 * @return
	 */
	@RequestMapping(value="queryJpPz")
	@ResponseBody
	public Map<String,Object> queryJpPz(){
		Shyhb user = SessionUtils.getShshbSession();
		Map<String,Object> param=new HashMap<String,Object>();
		try {
			List<JpPz> list=this.baseService.selectJpPzByShbh(user.getShbh());
			param.put("CODE", "0");
			param.put("DATA", list);
		} catch (Exception e) {
			logger.error("查询配置出错"+e.getMessage());
			param.put("CODE", "-1");
			param.put("MSG", "查询配置出错");
		}
		return param;
	}

	@Override
	public void updateInitEntity(JpPz t) {
	}

	@Override
	public void insertInitEntity(JpPz t) {
	}

}
