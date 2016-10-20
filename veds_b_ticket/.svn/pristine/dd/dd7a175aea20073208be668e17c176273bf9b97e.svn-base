package cn.vetech.web.vedsb.cpkzt;

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
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpJk;
import cn.vetech.vedsb.jp.service.jpzdcp.JpZdcpJkServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;


/**
 * 
 * @author wangshengliang
 *
 */
@Controller
public class JpZdcpJkController extends MBaseControl<JpZdcpJk,JpZdcpJkServiceImpl>{

	@Override
	public void updateInitEntity(JpZdcpJk t) {
		
	}

	@Override
	public void insertInitEntity(JpZdcpJk t) {
		
	}
	
	@RequestMapping(value = "query",method = RequestMethod.POST)
	public @ResponseBody Page query(String[] cpzt,Model model,HttpServletRequest request) {
			Page page = null;
			try {
				Shyhb user = SessionUtils.getShshbSession();
				Map<String, Object> param = Servlets.getParametersStartingWith(request, "", false);
				param.put("cpzt", cpzt);
				param.put("shbh", user.getShbh());
				int pageNum=NumberUtils.toInt(VeStr.getValue(param, "pageNum"), 0);
				int pageSize=NumberUtils.toInt(VeStr.getValue(param, "pageSize"), 10);
				page=this.baseService.selectAllJpZdcpJk(param, pageNum, pageSize);
			} catch (Exception e) {
				logger.error("查询自动监控列表出错"+e.getMessage());
			}
			return page;
	}
	@RequestMapping(value = "getSm",method = RequestMethod.POST)
	public @ResponseBody String getSm(String id){
		Shyhb user = SessionUtils.getShshbSession();
		JpZdcpJk  jk=new JpZdcpJk();
		jk.setId(id);
		jk.setShbh(user.getShbh());
		jk = this.baseService.getMyBatisDao().selectByPrimaryKey(jk);
		return jk == null ? "" : jk.getSm();
		
	}

}
