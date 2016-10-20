package cn.vetech.web.vedsb.jphbyd;

import java.io.UnsupportedEncodingException;
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
import org.vetech.core.business.cache.BcityCache;
import org.vetech.core.business.tag.FunctionCode;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.SmsMbSh;
import cn.vetech.vedsb.common.service.base.SmsServiceImpl;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddHd;
import cn.vetech.vedsb.jp.entity.jphbyd.JpHbyd;
import cn.vetech.vedsb.jp.entity.jphbyd.JpHbydMx;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddHdServiceImpl;
import cn.vetech.vedsb.jp.service.jphbyd.JpHbydMxServiceImpl;
import cn.vetech.vedsb.jp.service.jphbyd.JpHbydServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

/**
 * 航班异动表操作
 * @author win7
 *
 */
@Controller
public class JpHbydController extends MBaseControl<JpHbyd, JpHbydServiceImpl>{

	private static final String PAGE_SIZE = "20";
	
	@Autowired
	private JpKhddHdServiceImpl jpKhddHdServiceImpl;
	@Autowired
	private JpHbydMxServiceImpl jpHbydMxServiceImpl;
	@Autowired
	private SmsServiceImpl smsService;
	
	@Override
	public void updateInitEntity(JpHbyd t) {
		if("3".equals(t.getZt())){
			t.setBy1("");
			t.setYjCfsj("");
			t.setYjDdsj("");
		}
		t.setHc(t.getCfcity()+t.getDdcity());
	}

	@Override
	public void insertInitEntity(JpHbyd t) {
		Shyhb user = SessionUtils.getShshbSession();
		t.setZt("0");
		t.setId(VeDate.getNo(4));
		t.setShbh(user.getShbh());
		t.setYhbh(user.getBh());
		t.setBmbh(user.getShbmid());
		t.setHkgs(t.getHbh().substring(0,2));
		t.setHc(t.getCfcity()+t.getDdcity());
		t.setCjtime(VeDate.dateToStrLong(VeDate.getNow()));
		t.setCzly("手动发布");//手动生成
	}
	
	/**
	 * 根据界面传入条件进行分页查询
	 * @param hbyd
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @param sortName
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "queryPage",method = RequestMethod.POST)
	public @ResponseBody Page queryPage(@ModelAttribute() JpHbyd hbyd,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "desc") String sortType,
			HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		String rqtj = StringUtils.trimToEmpty(request.getParameter("rqtj"));
		String kssj = StringUtils.trimToEmpty(request.getParameter("kssj"));
		String jssj = StringUtils.trimToEmpty(request.getParameter("jssj"));
		String wdpt = StringUtils.trimToEmpty(request.getParameter("wdpt"));
		hbyd.setShbh(user.getShbh());
		hbyd.setRqtj(rqtj);
		hbyd.setKssj(kssj);
		hbyd.setJssj(jssj);
		hbyd.setWdpt(wdpt);
		Page page = null;
		try {
			page  = this.baseService.queryPage(hbyd, pageNum, pageSize, sortType);
		} catch (Exception e) {
			logger.error("查询失败",e);
		}
		return page;
	}
	
	/**
	 * 编辑航班异动时获取对应city的名称
	 */
	@Override
	@RequestMapping(value = "edit_{id}")
	public String edit(@PathVariable("id") String id, ModelMap model) {
		try{
			JpHbyd hbyd=this.baseService.getMyBatisDao().selectByPrimaryKey(id);
			if(hbyd != null){
				if(StringUtils.isNotBlank(hbyd.getHc()) && hbyd.getHc().length()>=6){
					String cfcity = StringUtils.substring(hbyd.getHc(), 0, 3);
					String ddcity = StringUtils.substring(hbyd.getHc(), 3, 6);
					BcityCache cache1 = FunctionCode.getBcity(cfcity);
					BcityCache cache2 = FunctionCode.getBcity(ddcity);
					hbyd.setCfcity(cfcity);
					hbyd.setDdcity(ddcity);
					hbyd.setCfcitymc(cache1.getMc());
					hbyd.setDdcitymc(cache2.getMc());
				}
			}
			model.addAttribute("entity", hbyd);
			return viewpath("edit");
		}catch (Exception e) {
			logger.error("编辑错误", e);
			return this.addError("编辑错误"+e.getMessage(), e, "edit", model);
		}
	}
	
	/**
	 * sms_hbyd和sms_hbyd_mx 新增修改保存
	 */
	@Override
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("entity") JpHbyd t,BindingResult bindingResult, ModelMap model){
		try{
			this.valid(bindingResult);
			String edit = (String)model.get("EDIT");
			JpHbydMx hbydMx = new JpHbydMx();
			if("1".equals(edit)){
				updateInitEntity(t);
				this.baseService.update(t);
				hbydMx.setYdid(t.getId());
				jpHbydMxServiceImpl.getMyBatisDao().delete(hbydMx);
			}else{
				insertInitEntity(t);
				this.baseService.insert(t);
				hbydMx.setYdid(t.getId());
			}
			List<JpKhddHd> hdList = jpKhddHdServiceImpl.getMyBatisDao().queryHbxxDdbh(t.getHbh(),t.getCfrq(),t.getHc());
			if(hdList != null){
				for(JpKhddHd hd : hdList){
					hbydMx.setId(VeDate.getNo(4));
					hbydMx.setDdbh(hd.getDdbh());
					hbydMx.setClZt("0");//未处理
					hbydMx.setSfyfdx("0");//短信未发送
					hbydMx.setHc(t.getHc());
					hbydMx.setCfsj(t.getCfsj());
					jpHbydMxServiceImpl.getMyBatisDao().insert(hbydMx);
				}
			}
		}catch (Exception e) {
			logger.error("保存失败", e);
			return this.addError("保存失败"+e.getMessage(),e, "edit",model);
		}
		return "/common/turning";
	}
	
	/**
	 * 根据id删除航班异动和航班异动明细中的数据
	 */
    @Override
	@ResponseBody
    @RequestMapping
	public String delete(@RequestParam(value = "id", defaultValue = "") String id, ModelMap model){
		try{
			if(StringUtils.isNotBlank(id)){
				JpHbydMx hbydMx = new JpHbydMx();
				hbydMx.setYdid(id);
				jpHbydMxServiceImpl.getMyBatisDao().delete(hbydMx);
				JpHbyd hbyd = new JpHbyd();
				hbyd.setId(id);
				this.baseService.getMyBatisDao().delete(hbyd);
			}
			return "0";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除失败，id为 " + id, e);
			return "-1";
		}
	}
	/**
	 *  更新航班异动状态
	 */
	@RequestMapping
	@ResponseBody
	public String upZt(@RequestParam(value = "id", defaultValue = "") String id, @RequestParam(value = "zt", defaultValue = "") String zt) {
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			JpHbyd jHbyd=new JpHbyd();
			jHbyd.setId(id);
			jHbyd.setShYhbh(yhb.getBh());
			jHbyd.setShBmbh(yhb.getBmmc());
			jHbyd.setShbh(yhb.getShbh());
			updateInitEntity(jHbyd);
			this.baseService.upZt(jHbyd,zt);
			return "0";
		} catch (Exception e) {
			logger.error("修改出票规则状态失败，规则id为 " + id, e);
			return "-1";
		}
	}
	/**
	 * 根据hbh和cfsj获取该航班信息
	 * @param hbh
	 * @param cfrq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getHbxx",method = RequestMethod.POST)
	public Map<String, String> getHbxx(@RequestParam(value = "hbh", defaultValue = "") String hbh,
			@RequestParam(value = "cfrq", defaultValue = "") String cfrq){
		if (StringUtils.isBlank(hbh) || StringUtils.isBlank(cfrq)) {
			return null;
		}
		Map<String,String> map = new HashMap<String, String>();
		JpKhddHd hd = new JpKhddHd();
		try {
			hd = jpKhddHdServiceImpl.getMyBatisDao().queryHbxxByHbhCfrq(hbh,cfrq);
			if(hd != null){
				if(StringUtils.isNotBlank(hd.getCfcity())){
					map.put("cfcity", hd.getCfcity());
					BcityCache cache = FunctionCode.getBcity(hd.getCfcity());
					map.put("cfcitymc", cache.getMc());
				}
				if(StringUtils.isNotBlank(hd.getDdcity())){
					map.put("ddcity", hd.getDdcity());
					BcityCache cache = FunctionCode.getBcity(hd.getDdcity());
					map.put("ddcitymc", cache.getMc());
				}
				map.put("ddsj", hd.getDdsj());
			}else{
				map.put("error", "系统中无此订单,不能保存!");
			}
		} catch (Exception e) {
			logger.error("查询出错",e);
			return null;
		}
		return map;
	}
	
	/**
	 * 根据条件查询受影响订单是否处理
	 * @param hbyd
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "jphbydClAll",method = RequestMethod.POST)
	public @ResponseBody Page jphbydClAll(@ModelAttribute() JpHbyd hbyd,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize,
			HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		String ddzt = StringUtils.trimToEmpty(request.getParameter("ddzt"));
		String sfyfdx = StringUtils.trimToEmpty(request.getParameter("sfyfdx"));
		String lx = StringUtils.trimToEmpty(request.getParameter("lx"));
		if("2".equals(lx)){
			//2表示已处理
			hbyd.setClzt("1");
		}else if("3".equals(lx)){
			//3表示未处理
			hbyd.setClzt("0");
		}
		hbyd.setShbh(user.getShbh());
		hbyd.setDdzt(ddzt);
		hbyd.setSfyfdx(sfyfdx);
		Page page = null;
		try {
			page  = this.baseService.queryJpHbydCl(hbyd, pageNum, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	/**
	 * 根据航班状态填充对应的短信模板并替换
	 * @param hbh
	 * @param hc
	 * @param cfrq
	 * @param zt
	 * @param yjcfsj
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewsendMessage")
	public String viewsendMessage(@RequestParam(value = "hbh", defaultValue = "") String hbh,
			@RequestParam(value = "hc", defaultValue = "") String hc,
			@RequestParam(value = "cfrq", defaultValue = "") String cfrq,
			@RequestParam(value = "zt", defaultValue = "") String zt,
			@RequestParam(value = "yjcfsj", defaultValue = "") String yjcfsj,ModelMap model){
		Shyhb user = SessionUtils.getShshbSession();
		String mbbh = "";
		if("1".equals(zt) || "2".equals(zt)){
			//1表示航班提前,2表示延误
			mbbh="4";
		}else{
			//3表示航班取消
			mbbh="7";
		}
		SmsMbSh sms = smsService.getMyBatisDao().queryShByZt(user.getShbh(),mbbh);
		if(sms != null && StringUtils.isNotBlank(hc)){
			String fsnr = sms.getNrsz();
			BcityCache cfcity = FunctionCode.getBcity(hc.substring(0,3));
			if(cfcity != null){
				fsnr = fsnr.replace("[cfcity]", cfcity.getMc());
			}
			BcityCache ddcity = FunctionCode.getBcity(hc.substring(3,6));
			if(ddcity != null){
				fsnr = fsnr.replace("[ddcity]", ddcity.getMc());
			}
			fsnr = fsnr.replace("[cfrq]", cfrq);
			fsnr = fsnr.replace("[hbh]", hbh);
			fsnr = fsnr.replace("[cfsj2]", yjcfsj);
			model.addAttribute("nrsz", fsnr);
		}
		return viewpath("sendM");
	}
	
	/**
	 * 航班异动明细表处理修改
	 * @param ydid
	 * @param clbz
	 * @return
	 */
	@RequestMapping(value = "jphbydmxEditSave", method = RequestMethod.POST)
	public int jphbydmxEditSave(@RequestParam(value = "ydid", defaultValue = "") String ydid,
			@RequestParam(value = "clbz", defaultValue = "") String clbz){
		int result = 0;
		try {
			clbz = new String(clbz.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} 
		Shyhb user = SessionUtils.getShshbSession();
		String clcompid = user.getShbh();
		String cluserid = user.getBh();
		String cldeptid = user.getShbmid();
		String clzt = "1";
		String cldatetime = VeDate.dateToStr(VeDate.getNow());
		try {
			jpHbydMxServiceImpl.getMyBatisDao().updateByydid(clzt,cldatetime,cluserid,cldeptid,clcompid,clbz,ydid);
			result = 1;
		} catch (Exception e) {
			result = 2;
		}
		return result;
	}
	
	/**
	 * 航班异动明细表取消处理修改
	 * @param ydid
	 * @return
	 */
	@RequestMapping(value = "jphbydmxQxClSave", method = RequestMethod.POST)
	public int jphbydmxQxClSave(@RequestParam(value = "ydid", defaultValue = "") String ydid){
		int result = 0;
		try {
			jpHbydMxServiceImpl.getMyBatisDao().updateQxClByYdid(ydid);
			result = 1;
		} catch (Exception e) {
			result = 2;
		}
		return result;
	}
	
	/**
	 * 跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "viewhbydcllist")
	public String view(@RequestParam(value = "ydyy", defaultValue = "") String ydyy,ModelMap model){
		model.addAttribute("ydyy", ydyy);
		return viewpath("hbydcllist");
	}
}
