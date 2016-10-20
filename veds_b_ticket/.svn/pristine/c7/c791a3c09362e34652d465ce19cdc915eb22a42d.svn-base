package cn.vetech.web.vedsb.jptpgl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cpsz.JpZdtpJk;
import cn.vetech.vedsb.jp.service.cpsz.JpZdtpJkServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;


@Controller
public class JpZdtpJkController extends MBaseControl<JpZdtpJk,JpZdtpJkServiceImpl>{
	@Override
	public void insertInitEntity(JpZdtpJk t) {
	}

	@Override
	public void updateInitEntity(JpZdtpJk t) {
	}
	
	
	@RequestMapping(value = "query",method = RequestMethod.POST)
	public @ResponseBody Page query(String[] tpzt,Model model,HttpServletRequest request) {
		Page page = null;
		try {
			Shyhb user = SessionUtils.getShshbSession();
			Map<String, Object> param = Servlets.getParametersStartingWith(request, "", false);
			param.put("shbh", user.getShbh());
			int pageNum=NumberUtils.toInt(VeStr.getValue(param, "pageNum"), 0);
			int pageSize=NumberUtils.toInt(VeStr.getValue(param, "pageSize"), 10);
			page=this.baseService.selectAllJpZdtpJk(param, pageNum, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询自动监控列表出错"+e.getMessage());
		}
		return page;
	}
	
	
	@RequestMapping(value = "getSm",method = RequestMethod.POST)
	public @ResponseBody String getSm(String id){
		Shyhb user = SessionUtils.getShshbSession();
		JpZdtpJk  jk=new JpZdtpJk();
		jk.setId(id);
		jk.setShbh(user.getShbh());
		jk = this.baseService.getMyBatisDao().selectByPrimaryKey(jk);
		return jk == null ? "" : jk.getSm();
		
	}

}
