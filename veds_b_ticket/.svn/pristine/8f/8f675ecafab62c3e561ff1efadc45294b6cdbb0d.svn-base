package cn.vetech.web.vedsb.cpsz;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.jp.entity.cpsz.JpZdtfpGzszCzrz;
import cn.vetech.vedsb.jp.service.cpsz.JpZdtfpGzszCzrzServiceImpl;


@Controller
public class JpZdtfpGzszCzrzController extends MBaseControl<JpZdtfpGzszCzrz,JpZdtfpGzszCzrzServiceImpl>{
	/**
	 * 进入异动日志查询
	 */
	@RequestMapping(value = "enterLogPage_{gzbh}")
	public String enterLogPage(@PathVariable("gzbh") String gzbh, ModelMap model){  
		JpZdtfpGzszCzrz jpZdtfpGzszCzrz = new JpZdtfpGzszCzrz();
		jpZdtfpGzszCzrz.setGzid(gzbh);
		List<JpZdtfpGzszCzrz> jpKhddCzrzList = null;
		try{
			jpKhddCzrzList = (List<JpZdtfpGzszCzrz>) this.baseService.getMyBatisDao().getCzRzByEntity(jpZdtfpGzszCzrz);
			model.addAttribute("logList", jpKhddCzrzList);
		} catch (Exception e){
			logger.error("提取自动退废票规则日志错误", e);
		}
		return viewpath("jp_zdtfp_gzsz_czrz");
	}
    @Override
    public void insertInitEntity(JpZdtfpGzszCzrz  t){
    }
	@Override
	public void updateInitEntity(JpZdtfpGzszCzrz t) {
	}
}
