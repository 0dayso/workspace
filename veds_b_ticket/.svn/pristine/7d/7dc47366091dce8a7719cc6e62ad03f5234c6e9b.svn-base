package cn.vetech.web.vedsb.jpcwgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class JpTpdHistoryController extends MBaseControl<JpTpd, JpTpdServiceImpl>{
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	@Override
	public void insertInitEntity(JpTpd t) {
		
	}

	@Override
	public void updateInitEntity(JpTpd t) {
		
	}

	/**
	 * 历史数据机票退票单调整列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping
	public String jpHistoryTpList(Model model,HttpServletRequest request){
		try {
			Map<String, Object> map = Servlets.getParametersStartingWith(request, "",true);
			String lx = (String)map.get("lx");
			Shyhb user = SessionUtils.getShshbSession();
			map.put("shbh", user.getShbh());
			List<Map<String, Object>> list = this.baseService.getHistoryTpList(map);
			Shzfkm shzfkm = new Shzfkm();
			shzfkm.setSfqy("1");
			shzfkm.setShbh(user.getShbh());
			List<Shzfkm> zfkm = this.shzfkmServiceImpl.getShzfkmList(shzfkm);
			model.addAttribute("zfkm",zfkm);
			model.addAttribute("list", list);
			model.addAttribute("lx", lx);
		} catch (Exception e) {
			logger.error("查询历史数据机票改签单失败!", e);
			e.printStackTrace();
		}
		return viewpath("tplist");
	}
	
	@RequestMapping
	public String jpHistoryTpSave(HttpServletRequest request,Model model){
		try {
			Shyhb user = SessionUtils.getShshbSession();
			String shbh = user.getShbh();
			String[] itkno = request.getParameterValues("itkno");
			for(int i=0;i<itkno.length;i++){
				String xstpsxf = request.getParameter("xstpsxf_"+itkno[i]);//手续费
				String zfkm = request.getParameter("zfkm_"+itkno[i]);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("shbh", shbh);
				map.put("tkno", itkno[i]);
				Map<String, Object> map0 = this.baseService.getjptpdobject(map);
				if(null!=map0){
					map0.put("xstpsxf", xstpsxf);
					map0.put("zfkm", zfkm);
				}
				this.baseService.saveTpdHistory(map0);
			}
			model.addAttribute("lx", "3");
			model.addAttribute("pnrNo", request.getParameter("pnrNo"));
			model.addAttribute("tkno", request.getParameter("tkno"));
		} catch (Exception e) {
			logger.error("保存历史数据修改机票改签单价格失败!", e);
			e.printStackTrace();
			model.addAttribute("lx", "3");
		}
		return view("tplist");
	}
}
