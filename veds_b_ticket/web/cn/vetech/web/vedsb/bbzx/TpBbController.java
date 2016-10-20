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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.export.Export2Xls;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.FileOperate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.web.MBaseControl;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.entity.bbzx.Tpbb;
import cn.vetech.vedsb.jp.entity.bbzx.TpbbExportPage;
import cn.vetech.vedsb.jp.entity.jptpgl.JpTpd;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jptpgl.JpTpdServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.PkgQueryServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class TpBbController extends MBaseControl<JpTpd, JpTpdServiceImpl>{
	
	
	@Autowired
	private PkgQueryServiceImpl pkgQueryServiceImpl;
	
	@Autowired
	private WdzlszServiceImpl wdzlszServiceImpl;
	
	@Autowired
	private ShzfkmServiceImpl shzfkmService;
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private Export2Xls export2Xls;
	
	@Override
	public void updateInitEntity(JpTpd t) {
		
	}

	@Override
	public void insertInitEntity(JpTpd t) {
		
	}
	
	/**
	 * 分页查询
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
	public  String query(Tpbb t,Model model,HttpServletRequest request,HttpServletResponse response) {
			try {
				Shyhb user = SessionUtils.getShshbSession();
				t.setShbh(user.getShbh());
				String export=request.getParameter("export");
				
				//查网店
				Map<String,Object> param_wd = new HashMap<String, Object>();
				param_wd.put("zt", "1");//启用
				param_wd.put("shbh", user.getShbh());
				param_wd.put("ywlxs", new String[]{"1001901","1001902"});
				List<Wdzlsz> wdzlszList = wdzlszServiceImpl.getWdZlszListByYwlx(param_wd);
				model.addAttribute("wdzlszList", wdzlszList);
				
				model.addAttribute("ykbs", request.getParameterValues("ykbs"));
				model.addAttribute("hbzt", request.getParameterValues("hbzt"));
				
				int pageNum=NumberUtils.toInt(request.getParameter("pageNum"),1);
				int pageSize=NumberUtils.toInt(request.getParameter("pageSize"),10);
				
				if(StringUtils.isNotBlank(export)){
					t.setExport("");
					String displayName="退票报表";
					
					try {
						TpbbExportPage exportPage = new TpbbExportPage(t);
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
				
				t.setStart((pageNum - 1) * pageSize);
				t.setCount(pageSize);
				
				Map<String,Object> param = pkgQueryServiceImpl.tfpReport(t);
			    List<?> list= (List<?>) param.get("list");
			    if(CollectionUtils.isNotEmpty(list)){
			    	attachService.shyhb("XS_BLR", user.getShbh()).shyhb("CG_BLR", user.getShbh()).shyhb("DDYH", user.getShbh()).wdzl("WDID").zfkm("XS_TKKM").execute(list);
			    }
				List<?> list_sum= (List<?>) param.get("list_sum");
				if(CollectionUtils.isNotEmpty(list_sum)){
					model.addAttribute("hjlist", list_sum.get(0));
				}
			 	Page page =new Page(pageNum,pageSize);
			 	page.setList(list);
			 	page.setTotalCount(NumberUtils.toInt(VeStr.getValue(param, "p_allCount")));
		    	model.addAttribute("list", list);
				model.addAttribute("page", page);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return view("jptpd_report");
	}
	
	@RequestMapping(value = "queryWdList",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryWdList(){
		Map<String,Object> reparam=new HashMap<String,Object>();
		Shyhb user = SessionUtils.getShshbSession();
		try {
			//List<VeclassCache> zfkm=FunctionCode.getVeclassLb("100023");
			
			Shzfkm shzfkm = new Shzfkm();
			shzfkm.setShbh(user.getShbh());	
			shzfkm.setSycp("1001901");//国内机票产品
			shzfkm.setSffkkm("1");
			shzfkm.setSfqy("1");
			List<Shzfkm> Shzfkm=shzfkmService.getShzfkmList(shzfkm);
			
			reparam.put("CODE", "0");
			reparam.put("SHZFKMDATA", Shzfkm);
		} catch (Exception e) {
			logger.error("获取网店失败:"+e.getMessage());
			reparam.put("CODE", "-1");
			reparam.put("MSG", "获取网店失败");
		}
		return reparam;
	}
}

