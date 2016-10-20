package cn.vetech.web.vedsb.cgdzbb;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpdMx;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdMxServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpCgdzTpdhkController extends MBaseControl<JpTpdMx, JpTpdMxServiceImpl>{

	@Override
	public void insertInitEntity(JpTpdMx t) {
	}

	@Override
	public void updateInitEntity(JpTpdMx t) {
	}
	
	@RequestMapping
	public String getCgdzTpdhkList(HttpServletRequest request,Model model,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
		try {
			Map<String, Object> map = Servlets.getParametersStartingWith(request, "",true);
			Shyhb yhb = SessionUtils.getShshbSession();
			map.put("shbh", yhb.getShbh());
			PageHelper ph = new PageHelper();
			map.put("start", ph.getStart(pageNum, pageSize));
			map.put("count", ph.getCount(pageNum, pageSize));
			Page page = this.baseService.getcgdzDhkList(map,pageNum,pageSize);
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewpath("list");
	}
	
}
