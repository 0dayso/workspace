package cn.vetech.web.vedsb.b2bsz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.ConnectUtil;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzh;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzhEnum;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.b2bsz.JpB2bZfzhServiceImpl;
import cn.vetech.vedsb.pay.ZfSign;
import cn.vetech.vedsb.pay.bhecard.BhecardPaySign;
import cn.vetech.vedsb.pay.cft.CftPaySign;
import cn.vetech.vedsb.pay.chinapay.AirepaySign;
import cn.vetech.vedsb.pay.chinapay.ChinapayPaySign;
import cn.vetech.vedsb.pay.etrip8.Etrip8;
import cn.vetech.vedsb.pay.etrip8.Etrip8Util;
import cn.vetech.vedsb.pay.zfb.ZfbPaySign;
import cn.vetech.vedsb.utils.DataUtils;
import cn.vetech.vedsb.utils.DictItem;
import cn.vetech.vedsb.utils.LogUtil;
import cn.vetech.web.vedsb.SessionUtils;

import com.alibaba.fastjson.JSONObject;

@Controller
public class Jpb2bzfzhController extends MBaseControl<JpB2bZfzh, JpB2bZfzhServiceImpl>{
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	@Autowired
	private AttachService attachService;

	@Override
	public void updateInitEntity(JpB2bZfzh t) {
		
	}

	@Override
	public void insertInitEntity(JpB2bZfzh t) {
		
	}
	/**
	 * 汇付天下
	 */
	final  String  HFLX="3";
	/**
	 * 易宝
	 */
	final  String  YBLX="4";
	/**
	 * 御航宝
	 */
	final  String  YHBLX="6";
	/**
	 * 易航宝
	 */
	final  String  YHBLX2="9";
	
	/**
	 * 跳转支付账号页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewlist")
	public String viewlist(@RequestParam(value = "zflx", defaultValue = "") String zflx,ModelMap model){
		Shyhb user = SessionUtils.getShshbSession();
		
		List<JpB2bZfzh> zfzhList = this.baseService.getMyBatisDao().queryListByShbhAndZflx(user.getShbh(),zflx);
		if(CollectionUtils.isNotEmpty(zfzhList)){
			attachService.zfkm("zfxx7").execute(zfzhList);
			for(JpB2bZfzh jpB2bZfzh:zfzhList){
				String zfxx3=jpB2bZfzh.getZfxx3();
				if(StringUtils.isNotBlank(zfxx3)){
					StringBuilder sb=new StringBuilder();
					sb.append(zfxx3.substring(0, 3));
					sb.append(zfxx3.substring(3, zfxx3.length()-3).replaceAll("[0-9a-zA-Z]", "*"));
					sb.append(zfxx3.subSequence(zfxx3.length()-3, zfxx3.length()));
					jpB2bZfzh.setZfxx3(sb.toString());
				}
				
			}
		}
		Map<String, DictItem> map = null;
		if(HFLX.equals(zflx)){
			map = JpB2bZfzhEnum.HFZT.dataMap;
		}else if(YBLX.equals(zflx)){
			map = JpB2bZfzhEnum.YBZT.dataMap;
		}else if(YHBLX.equals(zflx)){
			map = JpB2bZfzhEnum.YHZT.dataMap;
		}else if(YHBLX2.equals(zflx)){
			map = JpB2bZfzhEnum.YHZT.dataMap;
		}
		model.addAttribute("zfzhList", zfzhList);
		model.addAttribute("map", map);
		return viewpath("list");
	}
	
	/**
	 * 根据传入的id判断是新增还是修改
	 * @param zfzh
	 * @param czyzzh
	 * @param zflx
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "zfzhSave", method = RequestMethod.POST)
	@ResponseBody
	public int zfzhSave(@RequestParam(value = "zfzh", defaultValue = "") String zfzh,
			@RequestParam(value = "sfsyzzh", defaultValue = "") String sfsyzzh,
			@RequestParam(value = "zfzzh", defaultValue = "") String zfzzh,
			@RequestParam(value = "zflx", defaultValue = "") String zflx,
			@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "zfxx7", defaultValue = "") String zfxx7){
		int result = 0;
		Shyhb user = SessionUtils.getShshbSession();
		JpB2bZfzh jpB2bZfzh = new JpB2bZfzh();
		jpB2bZfzh.setShbh(user.getShbh());
		jpB2bZfzh.setZfzh(zfzh);
		jpB2bZfzh.setZfxx7(zfxx7);
		
		//是否使用子账号
		if("1".equals(sfsyzzh)){
			jpB2bZfzh.setZfzzh(zfzzh);
		}
		jpB2bZfzh.setSfsyzzh(sfsyzzh);
		try {
			if(StringUtils.isBlank(id)){
				//通过zfzh和shbh判断,保存的zfzh不能重复
				JpB2bZfzh record = new JpB2bZfzh();
				record.setZfzh(zfzh);
				record.setZflx(zflx);
				record.setShbh(user.getShbh());
				//判断重复 带入子账号。
				if("1".equals(sfsyzzh)){
					record.setZfzzh(zfzzh);
				}
				int count = this.baseService.getMyBatisDao().selectCount(record);
				if(count>0){
					logger.error("保存失败", "支付账号不能重复!");
					return -2; 
				}
				
				jpB2bZfzh.setId(VeDate.getNo(4));
				jpB2bZfzh.setYhbh(user.getBh());
				jpB2bZfzh.setZflx(zflx);
				jpB2bZfzh.setSfqy("0");
				jpB2bZfzh.setCjtime(VeDate.getStringDate());
				result = this.baseService.getMyBatisDao().insert(jpB2bZfzh);
			}else{
				jpB2bZfzh.setId(id);
				result = this.baseService.getMyBatisDao().updateById(jpB2bZfzh);
			}
		} catch (Exception e) {
			logger.error("保存失败", e);
			result = -1;
		}
		return result;
	}
	
	/**
	 * 是否启用账号
	 * @param id
	 * @param sfkq
	 * @return
	 */
	@RequestMapping(value = "updateToSfqy")
	public String updateToSfqy(@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "sfkq", defaultValue = "") String sfkq,
			@RequestParam(value = "zflx", defaultValue = "") String zflx,ModelMap model){
		Shyhb user = SessionUtils.getShshbSession();
		JpB2bZfzh jpB2bZfzh = new JpB2bZfzh();
		jpB2bZfzh.setId(id);
		jpB2bZfzh.setShbh(user.getShbh());
		jpB2bZfzh.setSfkq(sfkq);
		try {
			this.baseService.getMyBatisDao().updateById(jpB2bZfzh);
		} catch (Exception e) {
			logger.error("修改失败",e);
		}
		return viewlist(zflx,model);
	}
	
	/**
	 * 签约
	 * @throws Exception 
	 */
	@RequestMapping(value = "qy")
	public @ResponseBody Map<String,Object> qy(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> param=new HashMap<String,Object>();
		
		String id = request.getParameter("id");
		Shyhb user = SessionUtils.getShshbSession();
		JpB2bZfzh jpB2bZfzh =  this.baseService.getMyBatisDao().queryByIdAndShbh(id,user.getShbh());
		if (jpB2bZfzh == null) {
			//throw new Exception("请先选择一个需要签约的账号");
			param.put("CODE", "-1");
			param.put("MSG", "请先选择一个需要签约的账号");
			return param;
			
		}
		String url = "";
		String zflx = jpB2bZfzh.getZflx();
		ZfSign paySign = null;
		
		JpB2bZfzh upbean = new JpB2bZfzh();
		upbean.setId(id);
		upbean.setShbh(user.getShbh());
		//支付类型:1=支付宝 2=财付通 3=汇付  4=易宝 5=快钱 6=御航宝 7=易商旅 8=易生卡 9=易航宝
		if ("1".equals(zflx)) {
			paySign = new ZfbPaySign();
			// 查询是否签约
			String urlsearchsign = paySign.searchsign(jpB2bZfzh);
			LogUtil.getZfzhQy("zhzhqy").error("查询签约地址：" + urlsearchsign);
			String is_success = "";
			Document db = null;
			try {
				String rtn = ConnectUtil.doGet(urlsearchsign, null, "UTF-8", 120000, 120000, null);
				LogUtil.getZfzhQy("zhzhqy").error("查询签约返回：" + rtn);
				db = DocumentHelper.parseText(rtn);
				is_success = db.getRootElement().elementText("is_success");
			} catch (Exception e) {
				LogUtil.getZfzhQy("zhzhqy").error("查询签约失败：" + e.getMessage());
				param.put("CODE", "-1");
				param.put("MSG", "查询签约失败");
			}

			if ("T".equals(is_success)) {
				String status = db.getRootElement().element("response").element("userSignInfo").element("status").getTextTrim();
				if (!"S".equals(status)) {// 没有签约 就返回签约地址
					url = paySign.sign(request, jpB2bZfzh);
					upbean.setSfqy("0");
					param.put("CODE", "0");
					param.put("MSG", "没有签约,是否继续签约？");
					param.put("URL", url);
				} else if ("S".equals(status)) {// 已经签约就返回修改签约信息地址
					Element e = db.getRootElement().element("response").element("userSignInfo");
					upbean.setSfqy("1");
					String user_pay_type = e.element("user_pay_type").getTextTrim();
					String user_account_no = e.element("user_account_no").getTextTrim();
					upbean.setZfxx3(user_account_no);
					upbean.setZfxx1(user_pay_type);// 先信用后余额
					url = paySign.updatesign(request, jpB2bZfzh);
					param.put("CODE", "0");
					param.put("MSG", "已签约成功，是否需要修改签约信息？");
					param.put("URL", url);
				}
			} else {
				url = paySign.sign(request, jpB2bZfzh);
				upbean.setSfqy("0");
				upbean.setSfqy("0");
				param.put("CODE", "0");
				param.put("MSG", "没有签约,是否继续签约?");
				param.put("URL", url);
			}
		} else if ("2".equals(zflx)) {
			paySign = new CftPaySign();
			String rtnquery = paySign.searchsign(jpB2bZfzh);
			if ("T".equals(rtnquery)) {
				upbean.setSfqy("1");
				upbean.setSfkq("1");
				param.put("MSG", "已签约成功，是否需要修改签约信息？");
			} else {
				upbean.setSfqy("0");
				param.put("MSG", "没有签约,是否继续签约?");
			}
			url = paySign.sign(request, jpB2bZfzh);
			param.put("CODE", "0");
			param.put("URL", url);
		} else if ("6".equals(zflx)) {
			String OrgUserNo = user.getShbh() + VeDate.getUserDate("yMdHms");
			ChinapayPaySign cps = new ChinapayPaySign();
			url = cps.sign(request, jpB2bZfzh);
			upbean.setZfxx3(OrgUserNo);
		} else if ("8".equals(zflx)) {
			paySign = new BhecardPaySign();
			String urlsearchsign = paySign.searchsign(jpB2bZfzh);
			LogUtil.getZfzhQy("zhzhqy").error("查询签约地址：" + urlsearchsign);
			String is_success = "";
			//Element resp = null;
			Document db = null;
			try {
				String rtn = ConnectUtil.doGet(urlsearchsign, null, "UTF-8", 120000, 120000, null);
				LogUtil.getZfzhQy("zhzhqy").error("查询签约返回：" + rtn);
				
				db = DocumentHelper.parseText(rtn);
				Element root = db.getRootElement();
				is_success = root.elementText("is_success");
				
//				SAXBuilder builder1 = new SAXBuilder();
//				Reader xmlred = new StringReader(rtn);
//				Document docavh = builder1.build(xmlred);
//				resp = docavh.getRootElement();
//				is_success = resp.getChildTextTrim("is_success");
			} catch (Exception e) {
				LogUtil.getZfzhQy("zhzhqy").error("查询签约失败：" + e.getMessage());
			}
			if ("T".equals(is_success)) {
				
				//Element e = resp.getChild("trades").getChild("trade");
				Element e = db.getRootElement().element("trades").element("trade");
				upbean.setSfqy("1");
				String customer_no = e.element("customer_no").getTextTrim();
				String nopasskey = e.element("nopasskey").getTextTrim();
				//String customer_no = e.getChildTextTrim("customer_no");
				//String nopasskey = e.getChildTextTrim("nopasskey");
				upbean.setZfxx2(customer_no);
				upbean.setZfxx3(nopasskey);
			} else {
				url = paySign.sign(request, jpB2bZfzh);
				upbean.setSfqy("0");
			}
		}else if ("9".equals(zflx)) {
			String OrgUserNo = user.getShbh() + VeDate.getUserDate("yMdHms");
			AirepaySign aps = new AirepaySign();
			url = aps.sign(request, jpB2bZfzh);
			upbean.setZfxx3(OrgUserNo);
		}
		this.baseService.getMyBatisDao().updateById(upbean);
		LogUtil.getZfzhQy("zhzhqy").error("发起签约：" + url);
		return param;
	}
	/**
	 * 在航空公司出票时进行支付账号检查是否合法的账号
	 */
	@RequestMapping
	public @ResponseBody String check(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();
		String zflx = request.getParameter("zflx");// 支付方式 0=手动支付 1=支付宝 2=财付通 3=汇付天下 4=易宝 5=快钱
		String zfzhstr = request.getParameter("zfzh");// 支付账号
//		String hkgs = request.getParameter("hkgs");// 航空公司
//		String ddbh = request.getParameter("ddbh");
		
		JpB2bZfzh zfzhbean = new JpB2bZfzh();
		zfzhbean.setZflx(zflx);
		zfzhbean.setZfzh(zfzhstr);
		zfzhbean.setShbh(user.getShbh());
		List<JpB2bZfzh> zfzhList = this.baseService.getMyBatisDao().select(zfzhbean);
		JpB2bZfzh zfzh = null;
		String info = "";
		String zfzhid = "";
		if (CollectionUtils.isNotEmpty(zfzhList)) {
			zfzh = zfzhList.get(0);
			info = DataUtils.checkZfzh(zfzh);
			zfzhid = zfzh.getId();
		} else {
			zfzh = new JpB2bZfzh();
			info = "没有配置请先配置该账号相关信息。";
		}
		JSONObject jsonObj = (JSONObject)JSONObject.toJSON(zfzh);
		jsonObj.put("info", info);
		jsonObj.put("zfzhid", zfzhid);
		return jsonObj.toJSONString();
	}
	/**
	 * 易商旅读取帐号
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "viewetrip")
	public String viewetrip(@RequestParam(value = "id", defaultValue = "") String id, ModelMap model) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();
		JpB2bZfzh b2bZfzh = this.baseService.getMyBatisDao().queryByIdAndShbh(id, user.getShbh());
		if (b2bZfzh == null) {
			throw new Exception("请先选择一个需要签约的账号");
		}
		String zfzh = b2bZfzh.getZfzh();
		String czykz = b2bZfzh.getCzykz();
		if (StringUtils.isNotBlank(czykz)) {
			zfzh = czykz;
		}
		if (StringUtils.isBlank(zfzh)) {
			throw new Exception("支付帐号没有设置，请重新新增。");
		}
		model.addAttribute("zhBean", b2bZfzh);
		return viewpath("etrip");
	}
	
	/**
	 * 易商旅验证
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "etripAuth", method=RequestMethod.POST)
	public String etripAuth(HttpServletRequest request) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();
		String id = request.getParameter("id");
		//String zfzh = request.getParameter("zfzh");
		String zfzhmm = request.getParameter("zfzhmm");
		String czykz = request.getParameter("czykz");
		String czykzmm = request.getParameter("czykzmm");
		String error = "";
		if (StringUtils.isBlank(zfzhmm) && StringUtils.isBlank(czykzmm)) {
			error = "失败：请输入密码。";
			LogUtil.getZfzhQy("zhzhqy").error(error);
			return null;
		}
		try {
			JpB2bZfzh b2bZfzh = this.baseService.getMyBatisDao().queryByIdAndShbh(id, user.getShbh());
			String zfzh = b2bZfzh.getZfzh();
			if (StringUtils.isBlank(zfzh)) {
				error = "失败：支付帐号没有设置，请重新新增。";
				LogUtil.getZfzhQy("zhzhqy").error(error);
				return null;
			}
			// 获得这个帐号是否已经授权
			Etrip8Util e8 = new Etrip8Util();
			if (StringUtils.isNotBlank(zfzhmm)) {// 账号身份
				Etrip8 et8 = e8.setAccountNoContract(zfzh, zfzhmm);
				String account = StringUtils.trimToEmpty(et8.getAccoutno());
				String keycode_gen = StringUtils.trimToEmpty(et8.getKeycode());
				if (!account.equals(zfzh) || StringUtils.isBlank(keycode_gen)) {
					error = "失败：账号不一致，接口返回的是" + account + " 系统登记的是" + zfzh + " 或返回的身份认证密钥为空";
					LogUtil.getZfzhQy("zhzhqy").error(error);
					return null;
				}
				JpB2bZfzh upbean = new JpB2bZfzh();
				upbean.setShbh(user.getShbh());
				upbean.setZfzh(zfzh);
				upbean.setZfxx2(keycode_gen);
				this.baseService.getMyBatisDao().updateById(upbean);
			}
			List<JpB2bZfzh> list = this.baseService.getMyBatisDao().queryByZflxAndZfzh(zfzh, user.getShbh(), "7");
			String keycode = "";
			if (CollectionUtils.isNotEmpty(list)) {
				keycode = list.get(0).getZfxx2();
			}
			if (StringUtils.isBlank(keycode)) {
				error = "失败：账户密匙获取失败。";
				LogUtil.getZfzhQy("zhzhqy").error(error);
				return null;
			}

			if (StringUtils.isNotBlank(czykz) && StringUtils.isNotBlank(czykzmm)) {
				Etrip8 et8 = e8.setOperatorContract(zfzh, czykz, czykzmm);
				//String account = StringUtils.trimToEmpty(et8.getAccoutno());
				String keycode_gen = StringUtils.trimToEmpty(et8.getKeycode());
				String operatorname = StringUtils.trimToEmpty(et8.getOperatorname());
				String operatorkeycode = StringUtils.trimToEmpty(et8.getOperatorkeycode());
				if (!operatorname.equals(czykz) || StringUtils.isBlank(operatorkeycode)) {
					error = "失败：操作员账号不一致，接口返回的是" + operatorname + " 系统登记的是" + czykz + " 或返回的身份认证密钥为空";
					LogUtil.getZfzhQy("zhzhqy").error(error);
					return null;
				}

				JpB2bZfzh upzfzh = new JpB2bZfzh();
				upzfzh.setId(b2bZfzh.getId());
				upzfzh.setShbh(user.getShbh());
				upzfzh.setZfxx3(operatorkeycode);
				upzfzh.setZfxx2(keycode_gen);
				try {
					this.baseService.getMyBatisDao().updateById(upzfzh);
				} catch (Exception e) {
					LogUtil.getZfzhQy("zhzhqy").error("修改保存失败",e);
				}
			}
			JpB2bZfzh upzfzh = new JpB2bZfzh();
			upzfzh.setId(b2bZfzh.getId());
			upzfzh.setShbh(user.getShbh());
			upzfzh.setSfqy("1");
			try {
				this.baseService.getMyBatisDao().updateById(upzfzh);
			} catch (Exception e) {
				LogUtil.getZfzhQy("zhzhqy").error("修改保存失败",e);
			}
			logger.error("成功");
		} catch (Exception e) {
			LogUtil.getZfzhQy("zhzhqy").error("失败",e);
		}
		return null;
	}
	
	/**
	 *  通过商户编号获取支付科目
	 * @return
	 */
	@RequestMapping(value="queryZfkm")
	@ResponseBody
	public Map<String,Object> queryZfkm(){
		
		Shyhb user = SessionUtils.getShshbSession();
		Map<String,Object> param=new HashMap<String,Object>();
		try {
			Shzfkm shzfkm=new Shzfkm();
			shzfkm.setShbh(user.getShbh());
			shzfkm.setSfqy("1");
			List<Shzfkm> list=shzfkmServiceImpl.getShzfkmList(shzfkm);
			
			param.put("CODE", "0");
			param.put("DATA", list);
		} catch (Exception e) {
			logger.error("查询支付科目出错"+e.getMessage());
			param.put("CODE", "-1");
		}
		return param;
	}
}
