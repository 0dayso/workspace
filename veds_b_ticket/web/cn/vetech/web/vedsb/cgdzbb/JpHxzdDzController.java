package cn.vetech.web.vedsb.cgdzbb;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.vetech.core.modules.cache.EhcacheManage;
import org.vetech.core.modules.excel.ExcelReaderUtil;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpDbParam;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpDzsz;
import cn.vetech.vedsb.jp.service.jpcwgl.JpDzszServiceImpl;
import cn.vetech.vedsb.utils.bankdb.BankDataUtil;
import cn.vetech.vedsb.utils.bankdb.BankDbUtil;
import cn.vetech.web.vedsb.SessionUtils;
import cn.vetech.web.vedsb.jpcwgl.BankImport;

import com.alibaba.fastjson.JSONObject;

/**
 * 航协账单对账
 * 
 * @author houya
 *
 */
@Controller
public class JpHxzdDzController extends AbstractBaseControl {
	@Autowired
	private JpDzszServiceImpl jpDzszServiceImpl;
	@Autowired
	private EhcacheManage ehcacheManage;
	private final static int CACHE_TIME = 1800;// 缓存秒数 30分钟
	
	private String KEYSYS= "HXDETAILLIST";
	private String KEYBANK = "HXBANKLIST";

	/**
	 * 对账查询系统数据
	 */
	@SuppressWarnings("null")
	@RequestMapping
	public String genSystemData(Model model, HttpServletRequest request) throws Exception {
		String zctp = StringUtils.trimToEmpty(request.getParameter("zctp"));
		String rqtj = StringUtils.trimToEmpty(request.getParameter("rqtj"));
		String[] cglys = request.getParameterValues("cgly");
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		String ksrq = "";
		String jsrq = "";

		if ("3".equals(zctp)) {
			ksrq = StringUtils.trimToEmpty(request.getParameter("rqs"));
			jsrq = StringUtils.trimToEmpty(request.getParameter("rqz"));
		} else {
			ksrq = StringUtils.trimToEmpty(request.getParameter("cprq"));
		}

		List<Map<String, Object>> detailList = null;//jpHxdzServiceImpl.genHxdzData(shbh, zctp, cglys, ksrq, jsrq, rqtj);
		if (CollectionUtils.isNotEmpty(detailList)) {
			for (Map<String, Object> m : detailList) {
				m.put("CGJE", BankDbUtil.obj2Double(m.get("CGJE")));
			}
			// 存入缓存
			String key = genKey(user.getBh(), "", KEYSYS);
			putCache(key, detailList);
		}
		model.addAttribute("detailList", detailList);
		return view("sysdata");
	}

	/**
	 * 上传直通车数据
	 */
	@RequestMapping
	public String bankup(Model model, @RequestParam(value = "file") MultipartFile file, @RequestParam(value = "gsbh") String gsbh, HttpServletRequest request) throws Exception {
		String wdid = StringUtils.trimToEmpty(request.getParameter("wdid"));
		String dzrq = StringUtils.trimToEmpty(request.getParameter("dzrq"));
		Shyhb user = SessionUtils.getShshbSession();
		JpDzsz t = new JpDzsz();
		t.setGsbh(gsbh);
		t.setShbh("0000");// 系统格式商户编号为0000
		JpDzsz jpDzsz = jpDzszServiceImpl.getEntityById(t);
		BankImport bankImport = new BankImport(jpDzsz.getQsh() - 1);
		String fileName = file.getOriginalFilename();
		String tempId = VeDate.getNo(4);
		String path = "updownFiles" + File.separator + "tmpfiles" + File.separator + tempId + File.separator;
		path += fileName;
		File file2 = new File(path);
		FileUtils.writeByteArrayToFile(file2, file.getBytes());
		// file.transferTo(file2);
		ExcelReaderUtil.readExcel(bankImport, jpDzsz.getQsh() -1, jpDzsz.getQsh()-1, path, null);
		List<Map<String, Object>> bankList = bankImport.getBankList();
		if (CollectionUtils.isNotEmpty(bankList) && jpDzsz.getMwh() != null && jpDzsz.getMwh() > 0) {
			for (int i = 0, size = bankList.size(); i < jpDzsz.getMwh() && size > 0; i++) {
				size--;
				bankList.remove(size);
			}
		}
		List<String> headList = bankImport.getHeadRow();
		/**
		 * 开始解析账单
		 */
		BankDataUtil bankDataUtil = new BankDataUtil();
		String errorFileName = "";
		String mbfileName = "";
		if ("10100073".equals(gsbh)) {// 淘宝
			bankDataUtil.ztcZdCl(bankList, headList, jpDzsz);
			errorFileName = "ztc" + gsbh + dzrq;
			mbfileName = "wddz_ztcerrormb.xls";
		}
		if (StringUtils.isNotBlank(jpDzsz.getBzbz())) {// 去掉不显示的列
			String[] lms = jpDzsz.getBzbz().split("\\|");
			for (String l : lms) {
				headList.remove(l);
			}
		}
		BankDbUtil.toSortData(bankList, "发生金额");
		// 存入缓存
		String key = genKey(user.getBh(), "", KEYBANK);
		putCache(key, bankList);
		// 不能识别的账单导出
		List<Map<String, Object>> errorList = bankDataUtil.getErrorList();
		if (errorList != null && !errorList.isEmpty()) {
			errorFileName += "_" + VeDate.getRandom(5) + ".xls";
			String sourcePath = "/updownFiles/mb/" + mbfileName;
			try {
				String fileerror = BankDbUtil.exportExcelByModel(sourcePath, 1, errorFileName, errorList);
				model.addAttribute("fileerror", fileerror);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("headList", headList);
		model.addAttribute("banklist", bankList);
		return view("bankdata");
	}

	/**
	 * 对比数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	@ResponseBody
	public String dbdata(HttpServletRequest request) {
		String wdpt = StringUtils.trimToEmpty(request.getParameter("wdpt"));
		String wdid = StringUtils.trimToEmpty(request.getParameter("wdid"));
		String dzrq = StringUtils.trimToEmpty(request.getParameter("dzrq"));
		String zbid = StringUtils.trimToEmpty(request.getParameter("dzzbid"));
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		Map<String, String> result = new HashMap<String, String>();
		result.put("status", "1");
		try {
			String error = "";// isCanCompare(dzrq, wdpt, zbid, wdid);
			if (StringUtils.isNotBlank(error)) {
				throw new Exception(error);
			}
			// 取缓存
			String key = genKey(user.getBh(), "", KEYSYS);
			List<Map<String, Object>> detaillist = (List) genCache(key);
			if (detaillist == null) {
				throw new Exception("操作时间过长，请重新查询系统数据！");
			}
			key = genKey(user.getBh(), "", KEYBANK);
			List<Map<String, Object>> banklist = (List) genCache(key);
			if (banklist == null) {
				throw new Exception("请重新导入航信TSL数据。");
			}
			JpDzsz t = new JpDzsz();
			t.setGsbh(wdpt);
			t.setShbh("0000");// 系统格式商户编号为0000
			JpDzsz jpDzsz = jpDzszServiceImpl.getEntityById(t);
			JpDbParam param = new JpDbParam();
			param.setDdhl(jpDzsz.getDdhl());
			param.setDeptid(user.getShbmid());
			param.setDzrq(dzrq);
			String gsdy = jpDzsz.getWdgsdy();
			if (StringUtils.isNotBlank(gsdy)) {
				param.setGsdy(gsdy.split("\\|"));
			}
			param.setShbh(shbh);
			param.setUser(user.getBh());
			param.setWdid(wdid);
			param.setWdpt(wdpt);
			param.setZbid(zbid);
			// if(PlatCode.QN.equals(wdpt)){
			// BankCompareUtil<JpYsdzQn, JpYsdzQnDao> compareUtil=new BankCompareUtil<JpYsdzQn, JpYsdzQnDao>(param,JpYsdzQn.class,JpYsdzQnDao.class, baseService, sqlSessionJp);
			// compareUtil.qnryhdz(detaillist, banklist);
			// }else if(PlatCode.TB.equals(wdpt)){
			// BankCompareUtil<JpYsdzTb, JpYsdzTbDao> compareUtil=new BankCompareUtil<JpYsdzTb, JpYsdzTbDao>(param, JpYsdzTb.class,JpYsdzTbDao.class,baseService, sqlSessionJp);
			// compareUtil.tbdz(detaillist, banklist);
			// }else if(PlatCode.XC.equals(wdpt)){
			// BankCompareUtil<JpYsdzXc, JpYsdzXcDao> compareUtil=new BankCompareUtil<JpYsdzXc, JpYsdzXcDao>(param,JpYsdzXc.class,JpYsdzXcDao.class, baseService, sqlSessionJp);
			// compareUtil.xcdz(detaillist, banklist);
			// }else if(PlatCode.TC.equals(wdpt)){
			// BankCompareUtil<JpYsdzTc, JpYsdzTcDao> compareUtil=new BankCompareUtil<JpYsdzTc, JpYsdzTcDao>(param,JpYsdzTc.class,JpYsdzTcDao.class, baseService, sqlSessionJp);
			// compareUtil.tcdz(detaillist, banklist);
			// }
			key = genKey(user.getBh(), wdid, "DETAILLIST");
			removeCache(key);
			key = genKey(user.getBh(), wdid, "BANKLIST");
			removeCache(key);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "-1");
			result.put("error", "对比异常:" + e.getMessage());
		}
		return JSONObject.toJSONString(result);
	}

	/**
	 * 获取缓存key
	 * 
	 * @param userBh
	 * @param wdid
	 * @param lx
	 * @return
	 */
	private String genKey(String userBh, String code, String lx) {
		return userBh + code + lx;
	}

	/**
	 * 存入缓存
	 * 
	 * @param key
	 * @param value
	 */
	private void putCache(String key, Object value) {
		ehcacheManage.put("Cache", key, value, CACHE_TIME);
	}

	/**
	 * 移除缓存
	 * 
	 * @param key
	 */
	private void removeCache(String key) {
		ehcacheManage.remove("Cache", key);
	}

	/**
	 * 从缓存取数据
	 * 
	 * @param key
	 * @return
	 */
	private Object genCache(String key) {
		return ehcacheManage.get("Cache", key);
	}
}
