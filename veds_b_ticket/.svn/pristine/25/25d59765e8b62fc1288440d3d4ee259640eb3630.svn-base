package cn.vetech.web.vedsb.cgptsz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.cache.VeclassCache;
import org.vetech.core.business.cache.VeclassCacheService;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.RepJkList;
import cn.vetech.vedsb.common.service.base.RepJkListServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.utils.PlatCode;
import cn.vetech.web.vedsb.SessionUtils;


@Controller
public class JpPtzcZhController extends MBaseControl<JpPtzcZh, JpPtzcZhServiceImpl>{
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
		"e", "f" };
	private String url = "http://www2.yeexing.com/IBEIntf/services/IBEService";
	private String userName = "vetech";
	private String appUserName = "vetech001";
	private String sign = "123456";
	private String encode = "UTF-8";
	//chinapay代扣接口信用卡方式
	private String sign_notify_url = "http://59.175.227.78:20000/webcontent/agent/yxtx.shtml";
	@Autowired
	private RepJkListServiceImpl  repJkListServiceImpl;
	
	@Autowired
	private VeclassCacheService veclassCacheService;

	@Override
	public void updateInitEntity(JpPtzcZh t) {
	}

	@Override
	public void insertInitEntity(JpPtzcZh t) {
	}
	
	@RequestMapping(value = "viewlist")
	public String view(ModelMap model, String ptzcbs) throws Exception {
		try {
			List<RepJkList> repJkList = repJkListServiceImpl.getRepJkList();
			Shyhb user = SessionUtils.getShshbSession();
			if (StringUtils.isNotBlank(ptzcbs)) {
				JpPtzcZh ptzh = this.baseService.getPtzhByPtBs(ptzcbs, user.getShbh());
				//如果平台账号表里面没有数据时，则直接将平台政策标识传到页面，进行新增平台账号
				if (null == ptzh || StringUtils.isBlank(ptzh.getPtzcbs())) {
					ptzh = new JpPtzcZh();
					ptzh.setPtzcbs(ptzcbs);
				}
				model.addAttribute("ptzh", ptzh);
			}
			JpPtzcZh ptzh = new JpPtzcZh();
			ptzh.setShbh(user.getShbh());
			ptzh.setOrderBy("ptzcbs asc");
			List<JpPtzcZh> ptzhList = this.baseService.getJpPtzcZhForList(ptzh);
			Map<String, String> ptzhMap = new HashMap<String, String>();
			for (JpPtzcZh zczh : ptzhList) {
				ptzhMap.put(zczh.getPtzcbs(), zczh.getPtzcbs());
			}
			//采购平台
			String lb = "100021";
			List<VeclassCache> veclassList = veclassCacheService.getLb(lb);
			for (VeclassCache veclass : veclassList) {
				//将基础数据中维护的平台但是平台账号表里面没有的显示，在页面进行新增
				if ("100021".equals(veclass.getParid()) && StringUtils.isBlank(ptzhMap.get(veclass.getYwmc()))) {
					JpPtzcZh ptzczh = new JpPtzcZh();
					ptzczh.setPtzcbs(veclass.getYwmc());
					ptzhList.add(ptzczh);
				}
			}
				
			if (repJkList != null) {
				for (int i = 0; i < ptzhList.size(); i++) {
					for (int c = 0; c < repJkList.size(); c++) {
						RepJkList repJk = repJkList.get(c);
						String zfdjm = StringUtils.trimToEmpty(repJk.getZfdjm());
						String by1 = StringUtils.trimToEmpty(repJk.getBy1());
						for (int j = 0; j < ptzhList.size(); j++) {
							JpPtzcZh ptzczh = ptzhList.get(j);
							if (zfdjm.equals(ptzczh.getPtzcbs()) && "0".equals(by1)) {// 顺序为0的表示关闭
								ptzczh.setOpen1("-1");
							}
						}
					}
				}
			}
			model.addAttribute("list", ptzhList);
			return viewpath("list");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转平台账号设置页面错误：", e);
			return this.addError("跳转平台账号设置页面错误：" + e.getMessage(), e, "list", model);
		}
	}
	
	@RequestMapping(value = "getByPtbs")
	public String getByPtbs(ModelMap model, HttpServletRequest request, @RequestParam(value = "ptzcbs", defaultValue = "") String ptzcbs) throws Exception {
		try {
			Shyhb user = SessionUtils.getShshbSession();
			JpPtzcZh ptzh = this.baseService.getPtzhByPtBs(ptzcbs, user.getShbh());
			if (null == ptzh || StringUtils.isBlank(ptzh.getPtzcbs())) {
				ptzh = new JpPtzcZh();
				ptzh.setPtzcbs(ptzcbs);
			}
			model.addAttribute("ptzh", ptzh);
			return "/cpsz/jpcpymsz/lable/list";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转平台账号设置页面错误：", e);
			return "跳转平台账号设置页面错误：" + e.getMessage();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "saveZh")
	public String saveZh(ModelMap model, HttpServletRequest request, JpPtzcZh ptzh) throws Exception {
		try {
			Shyhb user = SessionUtils.getShshbSession();
			ptzh.setShbh(user.getShbh());
			ptzh.setZhxgr(user.getBh());
			ptzh.setZhxgrq(VeDate.getStringDate());
			int result = this.baseService.saveJpPtzcZh(ptzh);
			if (result >= 1) {
				return "1";
			} else {
				return "保存失败，未知错误！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转平台账号设置页面错误：", e);
			return "跳转平台账号设置页面错误：" + e.getMessage();
		}
	}
	
	// 获取八千翼支付宝账号签约地址
	@ResponseBody
	@RequestMapping(value = "getpaySignOnUrl")
	public Map<String,String> getpaySignOnUrl(ModelMap model, HttpServletRequest request) throws Exception {
		// 先签约
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", StringUtils.trimToEmpty(request.getParameter("name")));
		map.put("pwd", StringUtils.trimToEmpty(request.getParameter("pwd")));
		map.put("EmAccount", StringUtils.trimToEmpty(request.getParameter("emAccount")));
		String data = patchString(map);
		// "http://211.147.7.74/newPly/WebInterface/OrderService.asmx/PaySignOn"
		Map<String,String> result = new HashMap<String, String>();
		try {
			String urlString = StringUtils.trimToEmpty(request.getParameter("url")) + "/newPly/WebInterface/OrderService.asmx/PaySignOn";
			String returnData = getData(urlString, data, "UTF-8");
			returnData=StringUtils.replace(returnData, " ","");
			String paySignOnUrl =StringUtils.substringBetween(returnData, "<string", "</string>");
			paySignOnUrl=StringUtils.substringAfter(paySignOnUrl, ">");
			paySignOnUrl=StringUtils.trimToEmpty(paySignOnUrl);
			result.put("status", "1");
			result.put("result", paySignOnUrl);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取八千翼支付宝账号签约地址错误：" + e);
			result.put("status", "0");
			result.put("result", "获取八千翼支付宝账号签约地址错误：" + e.getMessage());
			return result;
		}
	}
	
	/**
	 * 易行签约
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "getyeexingUserSign")
	public Map<String,String> getyeexingUserSign(ModelMap model, HttpServletRequest request) throws Exception {
		Map<String,String> result = new HashMap<String,String>();
		try {
			Shyhb user = SessionUtils.getShshbSession();
			JpPtzcZh jpPtzcZh = new JpPtzcZh();
			jpPtzcZh = this.baseService.getPtzhByPtBs(PlatCode.YX, user.getShbh());
			if (jpPtzcZh == null) {
				result.put("status", "-1");
				result.put("result", "请先保存后在签约。");
				return result;
			}
			String url = this.getUserSignurl();
			result.put("status", "1");
			result.put("result", url);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取易行签约地址发生错误：" + e.getMessage());
			result.put("status", "-1");
			result.put("result", e.getMessage());
			return result;
		}
	}
	
	/**
	 * 八千翼拼接参数格式
	 */
	public String patchString(Map<String, String> map) {
		StringBuffer dataString = new StringBuffer("");
		if (map != null) {
			Set<String> set = map.keySet();
			if (set != null) {
				for (Iterator<String> iter = set.iterator(); iter.hasNext();) {
					String key = iter.next();
					String value = map.get(key);
					if (StringUtils.isBlank(dataString.toString())) {
						dataString.append(key + "=" + value);
					} else {
						dataString.append("&" + key + "=" + value);
					}
				}
			}
		}
		return dataString.toString();
	}

	/**
	 * 发送后台url data = "name=" +userName+"&pwd="+passWord+"&fromCity=" + fromCity+"&tocity="+tocity+"&airco=" +
	 * airco+"&airNo=" + airNo+"&airbunk=" + airbunk+"&airTime=" + airTime;
	 * 
	 * @param urlvalue
	 * @return
	 */
	public String getData(String urlString, String data, String encoding) throws Exception {
		if (StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}
		String returnData = "";
		OutputStreamWriter wr = null;
		BufferedReader rd = null;
		URL url = null;
		try {
			url = new URL(urlString);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(120000);
			conn.setDoOutput(true);
			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				sb.append(line.trim());
			}
			returnData = sb.toString();
		} finally {
			if (wr != null) {
				wr.close();
			}
			if (rd != null) {
				rd.close();
			}
			rd = null;
			url = null;
		}

		return returnData;
	}

	public String getUserSignurl() throws Exception {
		String md5 = md5(appUserName + sign_notify_url + userName);
		//http://service.yeexing.com.cn/CXF/UserSign
		//http://service.yeexing.com.cn/CXF/services/IBEService
		//http://service.yeexing.com.cn/IBEIntf/services/IBEService?wsdl
		int pos = url.indexOf("services") ;
		String urladdress = StringUtils.substring(url, 0, pos) + "UserSign";

		urladdress += "?userName=" + userName + "&appUserName=" + appUserName + "&sign=" + md5 + "&sign_notify_url="
				+ sign_notify_url;
		return urladdress;
	}
	
	private String md5(String str) {
		str = str + sign;
		try {
			String url = URLEncoder.encode(str, encode).toUpperCase();
			return MD5Encode(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes(encode)));
		} catch (Exception ex) {

		}
		return resultString;
	}
	
	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}
	
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
}
