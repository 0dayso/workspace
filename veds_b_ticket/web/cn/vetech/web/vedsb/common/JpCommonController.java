package cn.vetech.web.vedsb.common;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpddsz.JpQz;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpddsz.JpQzServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpCommonController {
	@Autowired
	private JpQzServiceImpl jpQzService;
	@Autowired
	private AttachService attachService;
	/**
	 * 保存订单的签注
	 */
	@ResponseBody
	@RequestMapping
	public int saveQz(@ModelAttribute()JpQz jpQz){
		Shyhb user = SessionUtils.getShshbSession();
		jpQz.setQzSj(new Date());
		jpQz.setShbh(user.getShbh());
		jpQz.setQzYhbh(user.getBh());
		jpQz.setQzBmbh(user.getShbmid());
		try {
			jpQzService.insert(jpQz);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	
	/**
	 * <查询订单的签注>
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "qzlist",method = RequestMethod.GET)
	public String qzlist(@RequestParam(value = "ywdh", defaultValue  = "") String ywdh, 
			             @RequestParam(value = "ywlx", defaultValue  = "") String ywlx,
			             @RequestParam(value = "from", defaultValue  = "") String from,
			             Model model){
		Shyhb user = SessionUtils.getShshbSession();
		List<JpQz> list=jpQzService.queryListByYwdh(ywdh);
		attachService.shyhb("qzYhbh",user.getShbh() ).execute(list);
		model.addAttribute("list", list);
		model.addAttribute("ywlx", ywlx);
		model.addAttribute("from", from);
		return "/common/jpddqz";
	}
}
