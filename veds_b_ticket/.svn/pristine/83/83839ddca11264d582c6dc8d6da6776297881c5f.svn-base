package cn.vetech.web.vedsb.shbm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shbm;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.JpShbmServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class ShbmController extends MBaseControl<Shbm, JpShbmServiceImpl>{
	/**
	 * 根据商户编号查询部门集合
	 * @return
	 */
	@RequestMapping(value="query")
	@ResponseBody
	public Map<String,Object> query(){
		Shyhb user = SessionUtils.getShshbSession();
		Map<String,Object> param=new HashMap<String,Object>();
		try {
			List<Shbm> list=this.baseService.getMyBatisDao().query(user.getShbh());
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
	public void updateInitEntity(Shbm t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertInitEntity(Shbm t) {
		// TODO Auto-generated method stub
		
	}

}
