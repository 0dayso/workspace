package cn.vetech.web.vedsb.cgptsz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcFzsz;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcFzszServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpPtzcFzszController extends MBaseControl<JpPtzcFzsz, JpPtzcFzszServiceImpl>{

	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	
	@Override
	public void updateInitEntity(JpPtzcFzsz t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertInitEntity(JpPtzcFzsz t) {
		// TODO Auto-generated method stub
		
	}
	
	@RequestMapping(value = "viewlist")
	public String view(ModelMap model, String ywlx) throws Exception {
		try {
			if (StringUtils.isBlank(ywlx)) {
				ywlx = "2";//采购
			}
			Shyhb user = SessionUtils.getShshbSession();
			List<JpPtzcFzsz> fzszList = this.baseService.getFzszListByYwlx(ywlx, user.getShbh());
			/**
			 * 支付科目
			 */
			Shzfkm zfkm = new Shzfkm();
			zfkm.setShbh(user.getShbh());
			zfkm.setSffkkm("1");
			zfkm.setSfqy("1");
			zfkm.setSycp("1001901");//机票产品
			List<Shzfkm> zfkmList = shzfkmServiceImpl.getShzfkmList(zfkm);
			model.addAttribute("zfkmList", zfkmList);
			model.addAttribute("fzszList", fzszList);
			return viewpath("list");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转平台账号设置页面错误：", e);
			return this.addError("跳转平台账号设置页面错误：" + e.getMessage(), e, "list", model);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "saveFzsz")
	public Map<String,String> saveFzsz(ModelMap model, HttpServletRequest request) {
		Map<String,String> result = new HashMap<String,String>();
		try { 
			String[] id = request.getParameterValues("id");
			String[] fzFzf = request.getParameterValues("fzFzf");
			String[] xtZfkm = request.getParameterValues("xtZfkm");
			String[] ptzcbs = request.getParameterValues("ptzcbs");
			String[] fzSxfl = request.getParameterValues("fz_sxfl");
			String ywlx = StringUtils.trimToEmpty(request.getParameter("ywlx"));
			Shyhb user = SessionUtils.getShshbSession();
			String xgrq = VeDate.getStringDate();
			for (int i = 0;i<id.length;i++) {
				JpPtzcFzsz fzsz = new JpPtzcFzsz();
				fzsz.setId(id[i]);
				fzsz.setFzFzf(fzFzf[i]);
				fzsz.setYwlx(ywlx);
				if ("1".equals(ywlx)) {
					fzsz.setFzSxfl(Arith.div(new Double(fzSxfl[i]), 1000.0));
				} else {
					fzsz.setFzSxfl(new Double(0));
				}
				fzsz.setXtZfkm(xtZfkm[i]);
				fzsz.setPtzcbs(ptzcbs[i]);
				fzsz.setXgr(user.getBh());
				fzsz.setShbh(user.getShbh());
				fzsz.setXgrq(xgrq);
				this.baseService.saveFzszById(fzsz);
			}
			result.put("status", "0");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存平台支付方式设置发生错误：", e);
			result.put("status", "-1");
			result.put("result", "保存平台支付方式设置发生错误：" + e.getMessage());
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "delFzsz_{id}")
	public Map<String,String> delFzsz(@PathVariable("id") String id, ModelMap model, HttpServletRequest request) {
		Map<String,String> result = new HashMap<String,String>();
		try {
			Shyhb user = SessionUtils.getShshbSession();
			this.baseService.delFzszById(id, user.getShbh());
			result.put("status", "0");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除平台支付设置发生错误：", e);
			result.put("status", "-1");
			result.put("result", "删除平台支付方式设置发生错误：" + e.getMessage());
		}
		return result;
	}
}
