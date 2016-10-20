package cn.vetech.web.vedsb.jpddgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCzrz;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddCzrzServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdServiceImpl;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpKhddCzrzController extends MBaseControl<JpKhddCzrz, JpKhddCzrzServiceImpl>{
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	
	@Autowired
	private JpTpdServiceImpl jpTpdServiceImpl;
	
	@Autowired
	private JpGqdServiceImpl jpGqdServiceImpl;
	
	@Autowired
	private AttachService attachService;
	/**
	 * 进入订单列表查询页面
	 */
	@RequestMapping(value = "enterLogPage_{ywdh}")
	public String enterLogPage(@PathVariable("ywdh") String ywdh,  @RequestParam(value = "ywlx", defaultValue  = "") String ywlx, ModelMap model){
		if(StringUtils.isBlank(ywlx)){
			return viewpath("jp_khdd_ydrz");
		}
		Shyhb yhb =SessionUtils.getShshbSession();
		String shbh=yhb.getShbh();
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("ywlx", ywlx);
		param.put("shbh", shbh);
		param.put("ywid", ywdh);
		
		List<Map<String,Object>> CzrzList = this.baseService.getCzRzByEntity(param);
		attachService.shyhb2("CZR","SHBH").execute(CzrzList);
		
		if("01".equals(ywlx)){
			JpKhdd jpkhdd=new JpKhdd();
			jpkhdd.setShbh(shbh);
			jpkhdd.setDdbh(ywdh);
			jpkhdd=jpKhddServiceImpl.getKhddByDdbh(jpkhdd);
			model.addAttribute("bean", jpkhdd);
		}else if("02".equals(ywlx)){
			JpTpd jptpd=jpTpdServiceImpl.getJpTpdByTpdh(ywdh, shbh);
			model.addAttribute("bean", jptpd);
		}else if("03".equals(ywlx)){
			JpGqd jpgqd=jpGqdServiceImpl.getJpGqdByGqdh(ywdh, shbh);
			model.addAttribute("bean", jpgqd);
		}
		model.addAttribute("ywlx", ywlx);
		model.addAttribute("ywdh", ywdh);
		model.addAttribute("logList", CzrzList);
		return viewpath("jp_khdd_ydrz");
	}
	
	
	@Override
	public void updateInitEntity(JpKhddCzrz t) {
	}
	@Override
	public void insertInitEntity(JpKhddCzrz t) {
	}
}
