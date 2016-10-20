package cn.vetech.web.vedsb.bbzx;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.business.export.Export2Xls;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.mybatis.page.PageHelper;
import org.vetech.core.modules.utils.Bean2Map;
import org.vetech.core.modules.utils.FileOperate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.web.MBaseControl;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.bbzx.Cptjbb;
import cn.vetech.vedsb.jp.entity.bbzx.CptjbbExportPage;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class CptjbbController extends MBaseControl<JpJp, JpJpServiceImpl>{
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	@Autowired
	private Export2Xls export2Xls;
	@Autowired
	private AttachService attachService;
	
	@RequestMapping(value = "query")
	public String view(Cptjbb t,ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String export=request.getParameter("export");
		
		String gngj = request.getParameter("gngj");
		if(StringUtils.isBlank(gngj)){
			t.setGngj("1");
		}
		
		String cplx = request.getParameter("cplx");
		if(StringUtils.isBlank(cplx)){
			t.setCplx("1");
		}
		
		int pageNum=NumberUtils.toInt(request.getParameter("pageNum"),1);
		int pageSize=NumberUtils.toInt(request.getParameter("pageSize"),10);
		Shyhb user = SessionUtils.getShshbSession();
		t.setShbh(user.getShbh());
		
		
		//导出
		if(StringUtils.isNotBlank(export)){
			t.setExport("");
			String displayName="出票统计报表";
			
			try {
				CptjbbExportPage exportPage = new CptjbbExportPage(t);
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
		//查询结果
		PageHelper ph = new PageHelper();
		t.setCount(ph.getCount(pageNum, pageSize));
		t.setStart(ph.getStart(pageNum, pageSize));
		
		Map<String,Object> param=Bean2Map.getMap(t);
		procedureServiceImpl.cpTjbbReport(param);
		
		List<Map<String,Object>> list=(List<Map<String,Object>>)param.get("list");
		if("1".equals(t.getTjfs())){
			attachService.wdzl("WDID").execute(list);
		}else if("4".equals(t.getTjfs())){
			attachService.shyhb("CPYH", user.getShbh()).execute(list);
		}
		List<?> list_sum= (List<?>) param.get("list_sum");
		if(CollectionUtils.isNotEmpty(list_sum)){
			model.addAttribute("list_sum", list_sum.get(0));
		}
	 	Page page =new Page(pageNum,pageSize);
	 	page.setList(list);
	 	page.setTotalCount(NumberUtils.toInt(VeStr.getValue(param, "p_allCount")));
    	model.addAttribute("list", list);
		model.addAttribute("page", page);
		
		
		return viewpath("list");
	}

	@Override
	public void updateInitEntity(JpJp t) {
	}

	@Override
	public void insertInitEntity(JpJp t) {
	}

}
