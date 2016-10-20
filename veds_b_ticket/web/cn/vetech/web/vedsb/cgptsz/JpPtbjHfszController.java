package cn.vetech.web.vedsb.cgptsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.cache.VeclassCache;
import org.vetech.core.business.cache.VeclassCacheService;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtbjHfsz;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtbjHfszServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class JpPtbjHfszController extends MBaseControl<JpPtbjHfsz, JpPtbjHfszServiceImpl>{

	@Autowired
	private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
	
	@Autowired
	private VeclassCacheService veclassCacheService;
	
	@Override
	public void updateInitEntity(JpPtbjHfsz t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertInitEntity(JpPtbjHfsz t) {
		// TODO Auto-generated method stub
		
	}
	
	@RequestMapping(value = "viewlist")
	public String view(ModelMap model) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();
		JpPtzcZh ptzh = new JpPtzcZh();
		ptzh.setShbh(user.getShbh());
		ptzh.setOrderBy("ptzcbs asc");
		List<JpPtzcZh> ptzhList = jpPtzcZhServiceImpl.getJpPtzcZhForList(ptzh);
		//采购平台
		String lb = "100021";
		List<VeclassCache> veclassList = veclassCacheService.getLb(lb);
		List<JpPtbjHfsz> hfList = this.baseService.getHfszListByShbh(user.getShbh());
		List<String> veClassList = new ArrayList<String>();
		for (VeclassCache veclass : veclassList) {
			if (!lb.equals(veclass.getId())) {
				veClassList.add(veclass.getYwmc());
			}
		}
		for (int i=0;i<hfList.size();i++) {
			JpPtbjHfsz hfsz = hfList.get(i);
			if (!veClassList.contains(hfsz.getId())) {
				hfList.remove(i);
			}
		}
		for (int i=0;i<ptzhList.size();i++) {
			JpPtzcZh ptzczh = ptzhList.get(i);
			if (!veClassList.contains(ptzczh.getPtzcbs())) {
				ptzhList.remove(i);
			}
		}
		model.addAttribute("ptzhList", ptzhList);
		model.addAttribute("hfList", hfList);
		return viewpath("list");
	}
	
	@ResponseBody
	@RequestMapping(value = "savePthf")
	public Map<String,String> savePthf(ModelMap model, HttpServletRequest request) {
		Map<String,String> result = new HashMap<String,String>();
		try {
			Shyhb user = SessionUtils.getShshbSession();
			String[] ptid = request.getParameterValues("ptid");
			String[] pthfds = request.getParameterValues("pthfds");
			String[] pthfje = request.getParameterValues("pthfje");
			for(int i=0;i<ptid.length;i++){
				JpPtbjHfsz hfsz = new JpPtbjHfsz();
				hfsz.setId(ptid[i]);
				hfsz.setPthfds(NumberUtils.toDouble(pthfds[i]));
				hfsz.setPthfje(NumberUtils.toDouble(pthfje[i]));
				hfsz.setSfkqbj(request.getParameter("sfkqbj_"+ptid[i]));
				hfsz.setSfkqhf(request.getParameter("sfkqhf_"+ptid[i]));
				hfsz.setShbh(user.getShbh());
				hfsz.setXgr(user.getBh());
				hfsz.setXgrq(VeDate.getStringDate());
				JpPtbjHfsz bean = new JpPtbjHfsz();
				bean.setId(ptid[i]);
				bean = this.baseService.getEntityById(bean);
				if(null != bean){
					this.baseService.update(hfsz);
				}else{
					this.baseService.insert(hfsz);
				}
			}
			result.put("status", "0");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存平台后返设置发生错误：", e);
			result.put("status", "-1");
			result.put("result", "保存平台后返设置发生错误：" + e.getMessage());
		}
		return result;
	}
}
