package cn.vetech.web.vedsb.b2bsz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgsxx;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZffs;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bHkgsxxServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bZffsServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class Jpb2bzffsController extends MBaseControl<JpB2bZffs, JpB2bZffsServiceImpl>{
	
	@Autowired
	private JpB2bHkgsxxServiceImpl jpB2bHkgsxxServiceImpl;

	@Override
	public void updateInitEntity(JpB2bZffs t) {
		
	}

	@Override
	public void insertInitEntity(JpB2bZffs t) {
		
	}
	
	/**
	 * 跳转支付设置tree页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewtree")
	public String view(ModelMap model){
		Shyhb user = SessionUtils.getShshbSession();
		JpB2bZffs jpB2bZffs = new JpB2bZffs();
		jpB2bZffs.setShbh(user.getShbh());
		List<JpB2bZffs> zfList = this.baseService.getMyBatisDao().select(jpB2bZffs);
		model.addAttribute("zfList", zfList);
		
		//B2B	720102
		//B2C   720104
		List<JpB2bHkgsxx>  hkgsxxList720102=jpB2bHkgsxxServiceImpl.selectByBca("720102");
		model.addAttribute("hkgsxxList720102", hkgsxxList720102);
		
		List<JpB2bHkgsxx>  hkgsxxList720104=jpB2bHkgsxxServiceImpl.selectByBca("720104");
		model.addAttribute("hkgsxxList720104", hkgsxxList720104);
		
		return viewpath("tree");
	}
}
