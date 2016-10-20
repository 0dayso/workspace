package cn.vetech.web.vedsb.jpgqgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpGqdCpkztController extends MBaseControl<JpGqd, JpGqdServiceImpl> {
	
	private static final String PAGE_SIZE = "10";
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private WdzlszServiceImpl wdzlszServiceImpl;
	
	@Override
	public void updateInitEntity(JpGqd t) {
	}

	@Override
	public void insertInitEntity(JpGqd t) {
	}
	
	/**
	 * 进入改签订单出票控制台页面
	 */
	@RequestMapping(value = "viewlist")
	public String view( ModelMap model, String gngj){
		try {
			if (StringUtils.isBlank(gngj)) {
				gngj = "1"; //国内
			}
			Shyhb user = SessionUtils.getShshbSession();
			//获取网店信息
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("zt", "1");//启用
			param.put("shbh", user.getShbh());
			if ("1".equals(gngj)) {
				param.put("ywlxs", new String[]{"1001901"});
			} else if ("0".equals(gngj)){
				param.put("ywlxs", new String[]{"1001902"});
			}
			List<Wdzlsz> wdzlszList = wdzlszServiceImpl.getWdZlszListByYwlx(param);
			model.addAttribute("wdzlszList", wdzlszList);
			return viewpath("list");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入改签订单出票控制台页面发生错误：", e);
			return this.addError("进入改签订单出票控制台页面发生错误：" + e.getMessage(), e, "list", model);
		}
	}
	
	/**
	 * 分页查询出票控制台改签列表
	 * @param jpgqd
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @param sortName
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "query")
	public @ResponseBody Page query( @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize, Model model,
			HttpServletRequest request) throws Exception {
			Page page = null;
			Map<String,Object> jpgqdMap = null;
			try {
				Shyhb user = SessionUtils.getShshbSession();
				jpgqdMap = Servlets.getParametersStartingWith(request, "", false);
				PageHelper ph = new PageHelper();
				jpgqdMap.put("start", ph.getStart(pageNum, pageSize));
				jpgqdMap.put("count", ph.getCount(pageNum, pageSize));
				jpgqdMap.put("pageNum", pageNum);
				jpgqdMap.put("pageSize", pageSize);
				jpgqdMap.put("shbh", user.getShbh()); 
				page  = this.baseService.query(jpgqdMap);
				if(page != null){
					List<?> list = page.getList();
					attachService.wdzl("wdid").zfkm("skkm").zfkm("gqCgkm").execute(list);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("查询改签列表页面数据错误：", e);
				throw new Exception("查询改签列表页面数据错误：" + e.getMessage());
			}
			return page;
	}
	
}
