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
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class JpHistoryController extends MBaseControl<JpJp, JpJpServiceImpl>{
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	@Override
	public void insertInitEntity(JpJp t) {
		
	}

	@Override
	public void updateInitEntity(JpJp t) {
		
	}
	
	/**
	 * 历史数据机票正常单调整列表
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String jpHistoryList(Model model,HttpServletRequest request){
		try {
			Map<String, Object> map = Servlets.getParametersStartingWith(request, "",true);
			Shyhb user = SessionUtils.getShshbSession();
			map.put("shbh", user.getShbh());
			List<Map<String, Object>> list = this.baseService.getHistoryList(map);
			Shzfkm shzfkm = new Shzfkm();
			shzfkm.setSfqy("1");
			shzfkm.setShbh(user.getShbh());
			List<Shzfkm> zfkm = this.shzfkmServiceImpl.getShzfkmList(shzfkm);
			model.addAttribute("zfkm",zfkm);
			model.addAttribute("list", list);
		} catch (Exception e) {
			logger.error("查询历史数据机票正常单失败!", e);
			e.printStackTrace();
		}
		return viewpath("list");
	}
	
//	/**
//	 *  历史数据调整机票正常单出票时间修改
//	 * @param request
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping
//	public String jpCpsjUpdate(HttpServletRequest request,Model model){
//		try {
//			Shyhb user = SessionUtils.getShshbSession();
//			String pnr = request.getParameter("pnr");
//			String ddbh = request.getParameter("ddbh");
//			String tkno = request.getParameter("tkno");
//			String zt = request.getParameter("zt");
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("ddbh", ddbh);
//			map.put("tkno", tkno);
//			map.put("shbh", user.getShbh());
//			List<Map<String, Object>> list = this.baseService.getHistoryCpsjEdit(map);
//			model.addAttribute("list", list);
//			model.addAttribute("ddbh", ddbh);
//		} catch (Exception e) {
//			logger.error("修改历史数据时间调整失败!", e);
//			e.printStackTrace();
//		}
//		return viewpath("edit");
//	}
	
	@RequestMapping
	public String jpHistorySave(HttpServletRequest request,Model model){
		try {
			Shyhb user = SessionUtils.getShshbSession();
			String shbh = user.getShbh();
			String[] itkno = request.getParameterValues("itkno");
			for(int i =0 ; i<itkno.length;i++){
				String cpsj  = request.getParameter("cpsj_"+itkno[i]);
				String jpzdj = request.getParameter("jpzdj_"+itkno[i]);
				String jpxsj = request.getParameter("jpxsj_"+itkno[i]);
				String jpjjf = request.getParameter("jpjjf_"+itkno[i]);
				String jptax = request.getParameter("jptax_"+itkno[i]);
				String zfkm = request.getParameter("zfkm_"+itkno[i]);
				String ddbh = request.getParameter("ddbh_"+itkno[i]);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("shbh", shbh);
				map.put("ddbh", ddbh);
				map.put("tkno", itkno[i]);
				Map<String , Object> map0 = this.baseService.getjpobject(map);
				if(null!=map0){
					map0.put("cpsj", cpsj);
					map0.put("jpzdj", jpzdj);
					map0.put("jpxsj", jpxsj);
					map0.put("jpjjf", jpjjf);
					map0.put("jptax", jptax);
					map0.put("zfkm", zfkm);
				}
				this.baseService.saveHistoryKhdd(map0);
				model.addAttribute("pnrNo", request.getParameter("pnrNo"));
				model.addAttribute("tkno", request.getParameter("tkno"));
				model.addAttribute("saveflag", "1");
			}
		} catch (Exception e) {
			logger.error("保存历史数据修改机票订单价格失败!", e);
			e.printStackTrace();
			model.addAttribute("saveflag", "1");
		}
		return viewpath("list");
	}
}
