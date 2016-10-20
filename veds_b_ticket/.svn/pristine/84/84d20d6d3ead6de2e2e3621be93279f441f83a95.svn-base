package cn.vetech.web.vedsb.b2bsz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgs;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgsxx;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzh;
import cn.vetech.vedsb.jp.entity.b2bsz.JpZdcpB2bzh;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bHkgsServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bHkgsxxServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bZfzhServiceImpl;
import cn.vetech.vedsb.jp.service.b2bsz.JpZdcpB2bzhServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class Jpzdcpb2bzhController extends MBaseControl<JpZdcpB2bzh, JpZdcpB2bzhServiceImpl>{

	private static final String PAGE_SIZE = "20";
	
	@Autowired
	private JpB2bHkgsServiceImpl jpB2bHkgsServiceImpl;
	@Autowired
	private JpB2bHkgsxxServiceImpl jpB2bHkgsxxServiceImpl;
	@Autowired
	private JpB2bZfzhServiceImpl jpB2bZfzhServiceImpl;
	
	@Override
	public void updateInitEntity(JpZdcpB2bzh t) {
		
	}

	@Override
	public void insertInitEntity(JpZdcpB2bzh t) {
		Shyhb user = SessionUtils.getShshbSession();
		t.setShbh(user.getShbh());
		t.setYhbh(user.getBh());
		t.setCzdatetime(VeDate.dateToStr(VeDate.getNow()));
	}
	
	/**
	 * B2B自动出票分页查询
	 * @param jpZdcpB2bzh
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "queryPage",method = RequestMethod.POST)
	public @ResponseBody Page queryPage(@ModelAttribute() JpZdcpB2bzh jpZdcpB2bzh,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "desc") String sortType,
			HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		jpZdcpB2bzh.setShbh(user.getShbh());
		Page page = null;
		try {
			page  = this.baseService.queryPage(jpZdcpB2bzh, pageNum, pageSize, sortType);
		} catch (Exception e) {
			logger.error("查询失败",e);
		}
		return page;
	}
	
	/**
	 * 航空公司查询对应支付账号
	 * @param hkgs
	 * @param bca
	 * @param zfid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getZfzhxx")
	public String getZfzhxx(@RequestParam(value = "hkgs", defaultValue = "") String hkgs,
			@RequestParam(value = "bca", defaultValue = "") String bca,
			ModelMap model){
		Shyhb user = SessionUtils.getShshbSession();
		mutual(hkgs,bca,user.getShbh(),model);
		return viewpath("zfzhList");
	}
	
	/** 修改界面 */
	@RequestMapping(value = "edit_{id}")
	public String edit(@PathVariable("id") String id, ModelMap model) {
		Shyhb user = SessionUtils.getShshbSession();
		try{
			JpZdcpB2bzh jpZdcpB2bzh = this.baseService.getMyBatisDao().selectById(id);
			if(jpZdcpB2bzh != null && StringUtils.isNotBlank(jpZdcpB2bzh.getZfid())){
				String[] ids  = StringUtils.split(jpZdcpB2bzh.getZfid(), ",");
				if(ids.length == 1){
					model.addAttribute("zzh", ids[0]);
				}else if(ids.length == 2){
					model.addAttribute("zzh", ids[0]);
					model.addAttribute("byzh", ids[1]);
				}
			    mutual(jpZdcpB2bzh.getHkgs(),jpZdcpB2bzh.getBy1(),user.getShbh(),model);
			}
			model.addAttribute("entity", jpZdcpB2bzh);
			return viewpath("edit");
		}catch (Exception e) {
			logger.error("编辑错误", e);
			return this.addError("编辑错误"+e.getMessage(), e, "edit", model);
		}
	}
	
	/** 公共代码 */
	private void mutual(String hkgs, String bca, String shbh, ModelMap model) {
		JpB2bHkgs jpB2bHkgs = jpB2bHkgsServiceImpl.getMyBatisDao().selectByHkgs_Bca(shbh,hkgs,bca);
		if(jpB2bHkgs != null && StringUtils.isNotBlank(jpB2bHkgs.getBzbz())){ 
			//是否有替代航空公司
			hkgs = jpB2bHkgs.getBzbz();
		}
		//根据航空公司取出航空公司支持的支付方式
		JpB2bHkgsxx JpB2bHkgsxx = jpB2bHkgsxxServiceImpl.getMyBatisDao().selectByHkgs_Bca(hkgs, bca);
		if(JpB2bHkgsxx != null){
			List<String> zffsList = JpB2bHkgsxx.getZffsList();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("shbh", shbh);
			map.put("sfkq", "1");
			map.put("zflxList", zffsList);
			try {
				List<JpB2bZfzh> zfzhList = jpB2bZfzhServiceImpl.getMyBatisDao().queryByMap(map);
				model.addAttribute("zfzhList", zfzhList);
			} catch (Exception e) {
				logger.error("查询失败",e);
			}
		}
	}
	
	/** 新增,修改保存  */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("entity") JpZdcpB2bzh t,BindingResult bindingResult, ModelMap model){
		try{
			this.valid(bindingResult);
			String edit = (String)model.get("EDIT");
			if("1".equals(edit)){
				updateInitEntity(t);
				this.baseService.update(t);
			}else{
				insertInitEntity(t);
				this.baseService.insert(t);
			}
		}catch (Exception e) {
			logger.error("保存失败", e);
			return this.addError("保存失败"+e.getMessage(),e, "edit",model);
		}
		return "/common/turning";
	}

}