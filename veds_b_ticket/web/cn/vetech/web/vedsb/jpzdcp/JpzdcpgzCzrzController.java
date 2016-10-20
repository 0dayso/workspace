package cn.vetech.web.vedsb.jpzdcp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.JpShbmServiceImpl;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpgzCzrz;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpzdcp.JpZdcpgzCzrzServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class JpzdcpgzCzrzController extends MBaseControl<JpZdcpgzCzrz, JpZdcpgzCzrzServiceImpl>{
	@Autowired
	private JpShbmServiceImpl shbmServiceImpl;
	@Autowired
	private AttachService attachService;
	@Override
	public void insertInitEntity(JpZdcpgzCzrz t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInitEntity(JpZdcpgzCzrz t) {
		// TODO Auto-generated method stub
		
	}
	@RequestMapping(value = "enterLogPage_{gzbh}")
	public String enterLogPage(@PathVariable("gzbh") String gzbh,JpZdcpgzCzrz bean,ModelMap model){
		Shyhb yhb =SessionUtils.getShshbSession();
		String shbh=yhb.getShbh();
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("czshbh", shbh);
		param.put("gzbh", gzbh);
		List<Map<String,Object>> list = this.baseService.getCzRzByEntity(param);
		attachService.shyhb2("CZYHBH","CZSHBH").execute(list);
		model.addAttribute("gzbh", gzbh);
		model.addAttribute("logList", list);
		return viewpath("jpczrzydrz");
	}
}
