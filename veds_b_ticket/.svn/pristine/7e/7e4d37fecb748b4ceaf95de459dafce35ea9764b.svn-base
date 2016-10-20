package cn.vetech.web.vedsb.cgdzbb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.modules.cache.EhcacheManage;
import org.vetech.core.modules.web.AbstractBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.service.cgdzbb.JpCgdzServiceImpl;
import cn.vetech.vedsb.utils.bankdb.BankDbUtil;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class JpCgYhdzController extends AbstractBaseControl{
	@Autowired
	private JpCgdzServiceImpl jpCgdzServiceImpl;
	@Autowired
	private EhcacheManage ehcacheManage;
	private final static int CACHE_TIME=1800;//缓存秒数 30分钟
	/**
	 * 对账查询系统数据
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	public String genSystemData(Model model,HttpServletRequest request) throws Exception {
		String code=StringUtils.trimToEmpty(request.getParameter("code"));
		String zctp=StringUtils.trimToEmpty(request.getParameter("zctp"));
		Shyhb user=SessionUtils.getShshbSession();
		String shbh=user.getShbh();
		String kssj="";
		String jssj="";
		if("3".equals(zctp)){
			String rqs=StringUtils.trimToEmpty(request.getParameter("rqs"));
			String rqz=StringUtils.trimToEmpty(request.getParameter("rqz"));
			kssj=rqs+" 00:00:00";
			jssj=rqz+" 23:59:59";
		}else{
			String cprq=StringUtils.trimToEmpty(request.getParameter("cprq"));
			kssj=cprq+" 00:00:00";
			jssj=cprq+" 23:59:59";
		}
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "",false);
		searchParams.put("shbh", shbh);
		searchParams.put("kssj", kssj);
		searchParams.put("jssj", jssj);
		List<Map<String,Object>> detailList=jpCgdzServiceImpl.genCgdzData(searchParams);
		if(CollectionUtils.isNotEmpty(detailList)){
			List<Map<String,Object>> zcList=new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> tfList=new ArrayList<Map<String,Object>>();
			for (Map<String,Object> m : detailList) {
				String pzzt=BankDbUtil.objToString(m.get("PZZT"));
				m.put("CGJE", BankDbUtil.obj2Double(m.get("CGJE")));
				if("0".equals(pzzt)){
					zcList.add(m);
				}else {
					tfList.add(m);
				}
			}
			zcList=BankDbUtil.hbPnr(zcList);
			detailList.clear();
			detailList.addAll(zcList);
			detailList.addAll(tfList);
			BankDbUtil.toSortData(detailList, "CGJE");
			//存入缓存
			String key=genKey(user.getBh(), code, "DETAILLIST");
			putCache(key, detailList);
		}
		model.addAttribute("detailList", detailList);
		return view("sysdata");
	}
	/**
	 * 获取缓存key
	 * @param userBh
	 * @param wdid
	 * @param lx
	 * @return
	 */
	private String genKey(String userBh,String code,String lx){
		return userBh+code+lx;
	}
	/**
	 * 存入缓存
	 * @param key
	 * @param value
	 */
	private void putCache(String key,Object value){
		ehcacheManage.put("Cache", key, value, CACHE_TIME);
	}
	/**
	 * 移除缓存
	 * @param key
	 */
	private void removeCache(String key){
		ehcacheManage.remove("Cache", key);
	}
	/**
	 * 从缓存取数据
	 * @param key
	 * @return
	 */
	private Object genCache(String key){
		return ehcacheManage.get("Cache", key);
	}
}
