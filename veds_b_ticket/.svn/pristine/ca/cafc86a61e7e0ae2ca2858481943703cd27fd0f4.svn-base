package cn.vetech.web.vedsb.jpgqgl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdCzrz;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdCzrzServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpGqdCzrzController extends MBaseControl<JpGqdCzrz, JpGqdCzrzServiceImpl>{
	
	@Autowired
	private JpGqdServiceImpl jpGqdServiceImpl;
	
	@Override
	public void updateInitEntity(JpGqdCzrz t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertInitEntity(JpGqdCzrz t) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 查询异动日志
	 * @param gqdh
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "enterLogPage_{gqdh}")
	public String enterLogPage(@PathVariable("gqdh") String gqdh, ModelMap model){
		try {
			Shyhb yhb =SessionUtils.getShshbSession();
			JpGqd jpGqd = jpGqdServiceImpl.getJpGqdByGqdh(gqdh, yhb.getShbh());
			model.addAttribute("jpGqd", jpGqd);
			JpGqdCzrz jpGqdCzrz = new JpGqdCzrz();
			jpGqdCzrz.setYwid(gqdh);
			jpGqdCzrz.setShbh(yhb.getShbh());
			List<JpGqdCzrz> jpGqdCzrzList = this.baseService.getCzRzByEntity(jpGqdCzrz);
			model.addAttribute("logList", jpGqdCzrzList);
			return viewpath("jp_gqd_ydrz");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询异动日志发生错误：", e);
			return this.addError("查询异动日志发生错误：" + e.getMessage(), e, "jp_gqd_ydrz", model);
		}
		
	}
	
}
