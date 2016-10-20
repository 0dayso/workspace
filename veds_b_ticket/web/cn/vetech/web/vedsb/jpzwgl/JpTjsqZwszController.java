package cn.vetech.web.vedsb.jpzwgl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.cache.VeclassCacheService;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.JpShwdkz;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.JpShwdkzServiceImpl;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqZwsz;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpzwgl.JpTjsqZwszServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpTjsqZwszController extends MBaseControl<JpTjsqZwsz, JpTjsqZwszServiceImpl>{
	@Autowired
	private AttachService attachService;
	@Autowired
	private VeclassCacheService veclassCacheService;
	@Autowired
	private JpShwdkzServiceImpl shwdkzServiceImpl;
	@Override
	public void insertInitEntity(JpTjsqZwsz t) {
		Shyhb user = SessionUtils.getShshbSession();
		Date now=new Date();
		t.setCjyh(user.getBh());
		t.setShbh(user.getShbh());
		t.setCjsj(now);
		t.setZt("1");
		t.setXgsj(now);
		t.setXgyh(user.getBh());
	}

	@Override
	public void updateInitEntity(JpTjsqZwsz t) {
		Shyhb user = SessionUtils.getShshbSession();//获取登录时的用户对象
		t.setXgsj(new Date());
		t.setXgyh(user.getBh());
	}
	/**
	 * 初始化页面
	 * @throws Exception 
	 */
	@RequestMapping(value = "init{page}")
	public String init(@PathVariable("page") String page,Model model,HttpServletRequest request) throws Exception{
		if("edit".equals(page)){
			Shyhb user = SessionUtils.getShshbSession();//获取登录时的用户对象
			String id=StringUtils.trimToEmpty(request.getParameter("id"));
			String shbh = user.getShbh();
			if(StringUtils.isNotBlank(id)){
				String czlx=StringUtils.trimToEmpty(request.getParameter("czlx"));
				JpTjsqZwsz t=new JpTjsqZwsz();
				t.setId(id);
				t.setShbh(shbh);
				JpTjsqZwsz zwsz=baseService.getEntityById(t);
				qdQhFgf(zwsz);
				if("2".equals(czlx)){//复制
					zwsz.setId(null);
				}
				
				model.addAttribute("entity",zwsz);
			}
			List<JpShwdkz> shwhkzList = this.shwdkzServiceImpl.getShwdkzByShbh(shbh);
			model.addAttribute("shwhkzList", shwhkzList);
		}
		return viewpath(page);
	}
	/**
	 * 分页查询追位设置
	 */
	@RequestMapping
	public @ResponseBody Page zwgzlist(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@ModelAttribute() JpTjsqZwsz jpTjsqZwsz, @RequestParam(value = "sortType", defaultValue = "desc") String sortType, String sortName, Model model, HttpServletRequest request) {
		Shyhb yhb = SessionUtils.getShshbSession();
		PageHelper ph = new PageHelper();
		jpTjsqZwsz.setStart(ph.getStart(pageNum, pageSize));
		jpTjsqZwsz.setCount(ph.getCount(pageNum, pageSize));
		jpTjsqZwsz.setShbh(yhb.getShbh());
		Page page= this.baseService.queryPage(jpTjsqZwsz, pageNum, pageSize);
		pageAfter(page);
		return page;
	}
	@Override
	public void pageAfter(Page page) {
		Shyhb yhb = SessionUtils.getShshbSession();
		List list = page.getList();
		attachService.shyhb("xgyh", yhb.getShbh()).execute(list);
		for(Object obj : list){
			JpTjsqZwsz zwsz=(JpTjsqZwsz) obj;
			String wdpt=zwsz.getWdpt();
			qdQhFgf(zwsz);
			if("---".equals(wdpt)){
				zwsz.getEx().put("WDPTMC", "---");
			}else {
				String[] wdpts=wdpt.split("/");
				String wdptmc="";
				for (String pt : wdpts) {
					wdptmc+="/"+veclassCacheService.get(pt).getMc();
				}
				wdptmc=wdptmc.substring(1);
				zwsz.getEx().put("WDPTMC", wdptmc);
			}
		}
	}
	/**
	 * 追位设置保存
	 */
	@RequestMapping
	public String saveZwgz(@ModelAttribute("entity")JpTjsqZwsz zwgz,ModelMap model){
		String id = zwgz.getId();
		try{
			fgfChuli(zwgz);
			//新增
			if(StringUtils.isBlank(id)){
				insertInitEntity(zwgz);
				this.baseService.insert(zwgz);
			}else{
				updateInitEntity(zwgz);
				this.baseService.getMyBatisDao().updateByPrimaryKeySelective(zwgz);
			}
		}catch (Exception e) {
			logger.error("保存失败", e);
			return this.addError("保存失败"+e.getMessage(),e, "edit",model);
		}
		return "/common/turning";
	}
	/**
	 * 存入时在前后加上"/"
	 * @param val
	 * @return
	 */
	private void fgfChuli(JpTjsqZwsz zwgz){
		String cw=fgfChuli(zwgz.getCw());
		String mbcw=fgfChuli(zwgz.getMbcw());
		String zclx=fgfChuli(zwgz.getWdZclx());
		zwgz.setCw(cw);
		zwgz.setMbcw(mbcw);
		zwgz.setWdZclx(zclx);
	}
	/**
	 * 去掉前后分隔符
	 * @param zwsz
	 */
	private void qdQhFgf(JpTjsqZwsz zwsz){
		String cw=qdQhFgf(zwsz.getCw());
		String mbcw=qdQhFgf(zwsz.getMbcw());
		String zclx=qdQhFgf(zwsz.getWdZclx());
		zwsz.setCw(cw);
		zwsz.setMbcw(mbcw);
		zwsz.setWdZclx(zclx);
	}
	/**
	 * 存入时在前后加上"/"
	 * @param val
	 * @return
	 */
	private String fgfChuli(String val){
		if(StringUtils.isNotBlank(val) && val.contains("/")){
			String[] arr=val.split("/");
			return "/"+StringUtils.join(arr,"/")+"/";
		}
		return val;
	}
	
	/**
	 * 存入时去掉前后"/"
	 * @param val
	 * @return
	 */
	private String qdQhFgf(String val){
		if(StringUtils.isNotBlank(val) && val.contains("/")){
			if(val.startsWith("/")){
				val=val.substring(1);
			}
			String[] arr=val.split("/");
			return StringUtils.join(arr,"/");
		}
		return val;
	}
	/**
	 *  批量启用/停用
	 */
	@RequestMapping
	@ResponseBody
	public String batchUptZt(@RequestParam(value = "zwgzids", defaultValue = "") String zwgzids, @RequestParam(value = "zt", defaultValue = "") String zt) {
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			if (StringUtils.isBlank(zwgzids)) {
				throw new Exception("规则id不能为空");
			}
			String[] ids=zwgzids.split(",");
			List<JpTjsqZwsz> list=new ArrayList<JpTjsqZwsz>();
			for (String s : ids) {
				JpTjsqZwsz zwsz=new JpTjsqZwsz();
				zwsz.setId(s);
				updateInitEntity(zwsz);
				list.add(zwsz);
			}
			this.baseService.batchUpdateZt(list,zt);
			return "0";
		} catch (Exception e) {
			logger.error("修改追位规则状态失败，规则id为 " + zwgzids, e);
			return "-1";
		}
	}
	/**
	 *  批量删除
	 */
	@RequestMapping
	@ResponseBody
	public String batchDelZwgz(@RequestParam(value = "zwgzids", defaultValue = "") String zwgzids) {
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			if (StringUtils.isBlank(zwgzids)) {
				throw new Exception("规则id不能为空");
			}
			String[] ids=zwgzids.split(",");
			List<JpTjsqZwsz> list=new ArrayList<JpTjsqZwsz>();
			for (String s : ids) {
				JpTjsqZwsz zwsz=new JpTjsqZwsz();
				zwsz.setShbh(yhb.getShbh());
				zwsz.setId(s);
				list.add(zwsz);
			}
			baseService.batchDelZwgz(list);
			return "0";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除追位规则状态失败，规则id为 " + zwgzids, e);
			return "-1";
		}
	}
	/**
	 *  全部启用/停用
	 */
	@RequestMapping
	@ResponseBody
	public String batchAllUptZt(@RequestParam(value = "zwzt", defaultValue = "") String zwzt,@ModelAttribute() JpTjsqZwsz jpTjsqZwsz) {
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			jpTjsqZwsz.setShbh(yhb.getShbh());
			List<JpTjsqZwsz> list=baseService.getZwgzList(jpTjsqZwsz);
			this.baseService.batchUpdateZt(list,zwzt);
			return "0";
		} catch (Exception e) {
			logger.error("修改追位规则状态失败", e);
			return "-1";
		}
	}
	/**
	 *  全部删除
	 */
	@RequestMapping
	@ResponseBody
	public String batchDelAllZwgz(@ModelAttribute() JpTjsqZwsz jpTjsqZwsz) {
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			jpTjsqZwsz.setShbh(yhb.getShbh());
			List<JpTjsqZwsz> list=baseService.getZwgzList(jpTjsqZwsz);
			for (JpTjsqZwsz zwsz : list) {
				if("1".equals(zwsz.getZt())){
					return "2";
				}
			}
			this.baseService.batchDelZwgz(list);
			return "0";
		} catch (Exception e) {
			logger.error("删除追位规则状态失败 ", e);
			return "-1";
		}
	}
}
