package cn.vetech.web.vedsb.bbzx;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vetech.core.business.export.Export2Xls;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;
import org.vetech.core.modules.utils.FileOperate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shbm;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.JpShbmServiceImpl;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.entity.bbzx.Gqbb;
import cn.vetech.vedsb.jp.entity.bbzx.GqbbExportPage;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpgqgl.JpGqdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class GqBbController extends MBaseControl<JpGqd, JpGqdServiceImpl>{

	
	@Autowired
	private WdzlszServiceImpl wdzlszServiceImpl;
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private Export2Xls export2Xls;
	
	@Autowired
	private JpShbmServiceImpl shbmServiceImpl;
	
	@Override
	public void updateInitEntity(JpGqd t) {
	}

	@Override
	public void insertInitEntity(JpGqd t) {
	}
	
	/**
	 * 进入改签报表查询页面
	 */
	@RequestMapping(value = "viewlist")
	public String view( ModelMap model, String gngj) throws Exception {
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
			logger.error("进入改签报表查询页面错误：：", e);
			return this.addError("进入改签报表查询页面错误：" + e.getMessage(), e, "list", model);
		}
	}
	
	/**
	 * 进入改签报表查询明细页面
	 */
	@RequestMapping(value = "viewmx_list")
	public String viewmx( ModelMap model, String gngj) throws Exception {
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
			return viewpath("mx_list");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入改签报表查询明细页面错误：：", e);
			return this.addError("进入改签报表查询明细页面错误：" + e.getMessage(), e, "mx_list", model);
		}
	}
	
	/**
	 * 改签报表查询
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "query")
	public String query (Gqbb t,Model model,HttpServletRequest request,HttpServletResponse response) {
		String export=request.getParameter("export");
		
		Page page = null;
		try {
			int pageNum=NumberUtils.toInt(request.getParameter("pageNum"),1);
			int pageSize=NumberUtils.toInt(request.getParameter("pageSize"),10);
			Shyhb user = SessionUtils.getShshbSession();
			t.setYhbh(user.getBh());
			t.setShbmid(user.getShbmid());
			t.setShbh(user.getShbh());
			PageHelper ph = new PageHelper();
			t.setCount(ph.getCount(pageNum, pageSize));
			t.setStart(ph.getStart(pageNum, pageSize));
			
			//导出
			if(StringUtils.isNotBlank(export)){
				t.setExport("");
				String displayName="改签报表";
				
				try {
					GqbbExportPage exportPage = new GqbbExportPage(t);
					String sdf = export2Xls.export(exportPage, export, displayName, "");
					File df = new File(sdf);
					String parent = df.getParent();
					String file = df.getName();
					FileOperate.todownfile(response, displayName, parent, file);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("导出Excel文件异常，" + e.getMessage());
				}
				return null;	
			}
			
			page = this.baseService.queryForGqbb(t,pageNum, pageSize);
			if(page != null){
				List<?> list = page.getList();
				if(CollectionUtils.isNotEmpty(list)){
					attachService.wdzl("WDID").zfkm("SKKM").zfkm("GQ_CGKM").execute(list);
					//遍历 将bmbh中存放的ID替换成名称
					for(int i=0;i<list.size();i++){
						Map<String,Object> map=(Map<String, Object>) list.get(i);
						if(StringUtils.isNotBlank(VeStr.getValue(map, "GQ_CPBM"))){
							Shbm shbm=shbmServiceImpl.getMyBatisDao().getShbmById(VeStr.getValue(map, "GQ_CPBM").toString(),user.getShbh());
							if(shbm != null){
								map.put("GQ_CPBM", shbm.getMc());
							}
						}
					}
				}
			}
			String gngj = t.getGngj();
			if (StringUtils.isBlank(gngj)) {
				gngj = "1"; //国内
			}
			
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
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("改签报表查询发生错误：", e);
			model.addAttribute("error",e.getMessage());
		}
		return viewpath("list");
	}
	
	/**
	 * 改签报表明细查询
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryMx", method = RequestMethod.POST)
	public String queryMx(Gqbb t,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Page page = null;
		try {
			int pageNum=NumberUtils.toInt(request.getParameter("pageNum"),1);
			int pageSize=NumberUtils.toInt(request.getParameter("pageSize"),10);
			Shyhb user = SessionUtils.getShshbSession();
			t.setYhbh(user.getBh());
			t.setShbmid(user.getShbmid());
			t.setShbh(user.getShbh());
			PageHelper ph = new PageHelper();
			t.setCount(ph.getCount(pageNum, pageSize));
			t.setStart(ph.getStart(pageNum, pageSize));
			page = this.baseService.queryForGqbb(t,pageNum, pageSize);
			if(page != null){
				List<?> list = page.getList();
				if(CollectionUtils.isNotEmpty(list)){
					attachService.wdzl("wdid").zfkm("skkm").zfkm("gqCgkm").execute(list);
				}
			}
			String gngj = t.getGngj();
			if (StringUtils.isBlank(gngj)) {
				gngj = "1"; //国内
			}
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
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("改签报表明细查询发生错误：", e);
			//return this.addError("改签报表明细查询发生错误：" + e.getMessage(), e, "mx_list", model);
		}
		return viewpath("mx_list");
	}
}
