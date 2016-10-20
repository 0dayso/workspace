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
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class JpGqdHistoryController extends MBaseControl<JpGqd, JpGqdServiceImpl>{
	
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	@Override
	public void insertInitEntity(JpGqd t) {
		
	}

	@Override
	public void updateInitEntity(JpGqd t) {
		
	}

	/**
	 * 历史数据机票改签单调整列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping
	public String jpHistoryGqList(Model model,HttpServletRequest request){
		try {
			Map<String, Object> map = Servlets.getParametersStartingWith(request, "",true);
			String lx = (String)map.get("lx");
			Shyhb user = SessionUtils.getShshbSession();
			map.put("shbh", user.getShbh());
			List<Map<String, Object>> list = this.baseService.getHistoryGqList(map);
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
		return viewpath("gqlist");
	}
	
	@RequestMapping
	public String jpHistoryGqSave(HttpServletRequest request,Model model){
		try {
			Shyhb user = SessionUtils.getShshbSession();
			String shbh = user.getShbh();
			String[] itkno = request.getParameterValues("itkno");
			for(int i=0;i<itkno.length;i++){
				String jpgqcgfy = request.getParameter("jpgqcgfy_"+itkno[i]);//采购费用
				String jpgqxsfy = request.getParameter("jpgqxsfy_"+itkno[i]);//销售费用
				String zfkm = request.getParameter("zfkm_"+itkno[i]);
				String gqdh = request.getParameter("gqdh_"+itkno[i]);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("shbh", shbh);
				map.put("gqdh", gqdh);
				map.put("tkno", itkno[i]);
				Map<String, Object> map0 = this.baseService.getjpgqdobject(map);
				if(null!=map0){
					map0.put("jpgqcgfy", jpgqcgfy);
					map0.put("jpgqxsfy", jpgqxsfy);
					map0.put("zfkm", zfkm);
				}
				this.baseService.saveGqdHistory(map0);
			}
			model.addAttribute("lx", "2");
			model.addAttribute("pnrNo", request.getParameter("pnrNo"));
			model.addAttribute("tkno", request.getParameter("tkno"));
			model.addAttribute("gqdh", request.getParameter("gqdh"));
		} catch (Exception e) {
			logger.error("保存历史数据修改机票改签单价格失败!", e);
			e.printStackTrace();
			model.addAttribute("lx", "2");
		}
		return view("gqlist");
	}
}
