package cn.vetech.web.vedsb.pzzx;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shbm;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.JpShbmServiceImpl;
import cn.vetech.vedsb.jp.entity.pzzx.JpXcd;
import cn.vetech.vedsb.jp.service.procedures.PkgPzBbServiceImpl;
import cn.vetech.vedsb.jp.service.pzzx.JpXcdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

//销存报表
@Controller
public class XcbbController extends MBaseControl<JpXcd, JpXcdServiceImpl>{
	@Autowired
	private JpXcdServiceImpl jpXcdServiceImpl;
	@Autowired
	private PkgPzBbServiceImpl pkgPzBbServiceImpl;
	@Autowired
	private JpShbmServiceImpl shbmServiceImpl;
	
	@RequestMapping(value = "queryXcbb")
	public String queryXcbb(Model model,HttpServletRequest request) {
		try {
			Shyhb user = SessionUtils.getShshbSession();
			Map<String, Object> param = Servlets.getParametersStartingWith(request, "", false);
			param.put("shbh", user.getShbh());
			List<Map<String,Object>> list=pkgPzBbServiceImpl.queryXcbb(param);
			if(CollectionUtils.isNotEmpty(list)){
				//遍历 将bmbh中存放的ID替换成名称
				for(int i=0;i<list.size();i++){
					Map<String,Object> map=list.get(i);
					Shbm shbm=shbmServiceImpl.getMyBatisDao().getShbmById(map.get("BMBH").toString(),user.getShbh());
					if(shbm != null){
						map.put("BMBH", shbm.getMc());
					}
				}
			}
			model.addAttribute("list",list);
		} catch (Exception e) {
			logger.error("销存报表查询失败"+e.getMessage());
		}
		
		return viewpath("list");
	}

	/**
	 * 查询行程单明细
	 * @param pageSize 每页显示的条目数
	 * @param model
	 * @return Page对象
	 */
	@RequestMapping(value = "getListByYhbh")
	@ResponseBody
	public Page getListByYhbh(HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,ModelMap model){
		//获取用户登录信息
		Shyhb shyhb = SessionUtils.getShshbSession();
		Page page = null;
		try {
			page = jpXcdServiceImpl.getListByYhbh(shyhb.getShbh(),pageNum,pageSize,request);
		} catch (Exception e) {
			logger.error("查询行程单明细失败"+e.getMessage());
		}
		return page;
	}
	
	@Override
	public void updateInitEntity(JpXcd t) {
	}

	@Override
	public void insertInitEntity(JpXcd t) {
	}
}
