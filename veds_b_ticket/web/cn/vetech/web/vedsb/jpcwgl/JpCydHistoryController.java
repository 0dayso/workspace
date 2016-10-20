package cn.vetech.web.vedsb.jpcwgl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpbcdgl.JpCyd;
import cn.vetech.vedsb.jp.service.jpbcdgl.JpCydServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class JpCydHistoryController extends MBaseControl<JpCyd, JpCydServiceImpl>{

	@Override
	public void insertInitEntity(JpCyd t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInitEntity(JpCyd t) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 历史数据机票差异单调整列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping
	public String jpHistoryCyList(Model model,HttpServletRequest request){
		try {
			Map<String, Object> map = Servlets.getParametersStartingWith(request, "",true);
			String lx = (String)map.get("lx");
			Shyhb user = SessionUtils.getShshbSession();
			map.put("shbh", user.getShbh());
			List<Map<String, Object>> list = this.baseService.getHistoryCyList(map);
			model.addAttribute("list", list);
			model.addAttribute("pnrNo", request.getParameter("pnrNo"));
			model.addAttribute("lx", lx);
		} catch (Exception e) {
			logger.error("查询历史数据机票改签单失败!", e);
			e.printStackTrace();
		}
		return viewpath("cylist");
	}
	
	@RequestMapping
	public String jpHistoryCySave(HttpServletRequest request,Model model){
		try {
			Shyhb user = SessionUtils.getShshbSession();
			String shbh = user.getShbh();
			String[] ids = request.getParameterValues("ids");
			for(int i=0;i<ids.length;i++){
				String xttkje = request.getParameter("xttkje_"+ids[i]);//系统退款金额
				String wdtkje = request.getParameter("wdtkje_"+ids[i]);//销售费用
				String cyje  = request.getParameter("cyjes_"+ids[i]);
				JpCyd jpcyd = new JpCyd();
				jpcyd.setShbh(shbh);
				jpcyd.setId(ids[i]);
				jpcyd = this.baseService.getEntityById(jpcyd);
				jpcyd.setXtTkje(NumberUtils.toDouble(xttkje));
				jpcyd.setWdTkje(NumberUtils.toDouble(wdtkje));
				jpcyd.setCyje(NumberUtils.toDouble(cyje));
				this.baseService.updateCyHistory(jpcyd);
			}
			model.addAttribute("lx", "4");
			model.addAttribute("pnrNo", request.getParameter("pnrNo"));
			model.addAttribute("tkno", request.getParameter("tkno"));
			model.addAttribute("gqdh", request.getParameter("gqdh"));
		} catch (Exception e) {
			logger.error("保存历史数据修改机票改签单价格失败!", e);
			e.printStackTrace();
			model.addAttribute("lx", "4");
		}
		return view("cylist");
	}
}
