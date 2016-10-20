package cn.vetech.web.vedsb.jptpgl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdCpkztServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpTpdCpkztController extends MBaseControl<JpTpd, JpTpdCpkztServiceImpl>{
	
	
	@Override
	public void updateInitEntity(JpTpd t) {
	}

	@Override
	public void insertInitEntity(JpTpd t) {
	}
	
	/**
	 * 分页查询
	 * @param jpgqd
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @param sortName
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "query",method = RequestMethod.POST)
	public @ResponseBody Page query(Model model,HttpServletRequest request) {
		   Page page=null;
			try {
				Shyhb user = SessionUtils.getShshbSession();
				Map<String, Object> param = Servlets.getParametersStartingWith(request, "", false);
				param.put("shbh", user.getShbh());
				page=this.baseService.tpCpkztQuery(param);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return page;
	}
	
	@RequestMapping(value = "titlePage")
	public String titlePage(Model model,HttpServletRequest request) {
			try {
				Shyhb user = SessionUtils.getShshbSession();
				Map<String, Object> param = Servlets.getParametersStartingWith(request, "", false);
				param.put("shbh", user.getShbh());
				
				System.out.println(param);
				
				Map<String,Object> totalMap=this.baseService.tpCpkztCount(param);
				model.addAttribute("totalMap", totalMap);
				
				List<Map<String,Object>> cglyMap=this.baseService.tpCpkztCgLyCount(param);
				model.addAttribute("cglyMap", cglyMap);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return viewpath("list_title");
	}
	
}
