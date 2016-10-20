package cn.vetech.web.vedsb.jpcwgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.business.export.Export2Xls;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.FileOperate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.utils.XmlUtils;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpXsmlExportPage;
import cn.vetech.vedsb.jp.entity.jpcwgl.JpXsmlfx;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
/**
 * 销售毛利报表分析
 * @author vetech
 *
 */
@Controller
public class JpXsmlfxController extends AbstractBaseControl{
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	@Autowired
	private AttachService attachService;
	@Autowired
	private Export2Xls export2Xls;
	private String getxsmlXml(HttpServletRequest request,int start,int count){
		Shyhb user=SessionUtils.getShshbSession();
		String shbh =StringUtils.trimToEmpty(user.getShbh());
		String kssj =StringUtils.trimToEmpty(request.getParameter("kssj"));
		String jssj =StringUtils.trimToEmpty(request.getParameter("jssj"));
		String tjtype =StringUtils.trimToEmpty(request.getParameter("tjtype"));
		String orders =StringUtils.trimToEmpty(request.getParameter("orders"));
		String userid = StringUtils.trimToEmpty(request.getParameter("userid"));
		String ywlx =StringUtils.trimToEmpty(request.getParameter("ywlx"));
		String tjfs =StringUtils.trimToEmpty(request.getParameter("tjfs"));
		String rqtj =StringUtils.trimToEmpty(request.getParameter("rqtj"));
		String pzzt =StringUtils.trimToEmpty(request.getParameter("pzzt"));
		String wdid =StringUtils.trimToEmpty(request.getParameter("wdid"));
		String wdpt =StringUtils.trimToEmpty(request.getParameter("wdpt"));
		String faid =StringUtils.trimToEmpty(request.getParameter("faid"));
		String glgj =StringUtils.trimToEmpty(request.getParameter("glgj"));
		String cgly =StringUtils.trimToEmpty(request.getParameter("cgly"));
		String pnrno =StringUtils.trimToEmpty(request.getParameter("pnrno"));
		String lx =StringUtils.trimToEmpty(request.getParameter("lx"));
		String tkno =StringUtils.trimToEmpty(request.getParameter("tkno"));
		String tfbz =StringUtils.trimToEmpty(request.getParameter("tfbz"));
		String cjrlx =StringUtils.trimToEmpty(request.getParameter("cjrlx"));
		String cjr =StringUtils.trimToEmpty(request.getParameter("cjr"));
		String hkgs =StringUtils.trimToEmpty(request.getParameter("hkgs"));
		String cw =StringUtils.trimToEmpty(request.getParameter("cw"));
		String hb =StringUtils.trimToEmpty(request.getParameter("hb"));
		String shclx =StringUtils.trimToEmpty(request.getParameter("shclx"));
		String hc =StringUtils.trimToEmpty(request.getParameter("hc"));
		String xsdzzt = StringUtils.trimToEmpty(request.getParameter("xsdzzt"));
		StringBuffer xml = new StringBuffer("<SQL>");
		xml.append(XmlUtils.xmlnode("SHBH",shbh));
		xml.append(XmlUtils.xmlnode("START",start));
		xml.append(XmlUtils.xmlnode("COUNT",count));
		xml.append(XmlUtils.xmlnode("XSDZZT", xsdzzt));
		xml.append(XmlUtils.xmlnode("KSSJ",kssj));
		xml.append(XmlUtils.xmlnode("JSSJ",jssj));
		xml.append(XmlUtils.xmlnode("TJTYPE",tjtype));
		xml.append(XmlUtils.xmlnode("ORDERS",orders));
		xml.append(XmlUtils.xmlnode("USERID",userid));
		xml.append(XmlUtils.xmlnode("YWLX",ywlx));
	   //xml.append(XmlUtils.xmlnode("TP_USERID",tp_userid));
		xml.append(XmlUtils.xmlnode("TJFS",tjfs));
		xml.append(XmlUtils.xmlnode("RQTJ",rqtj));
		xml.append(XmlUtils.xmlnode("PZZT",pzzt));
		xml.append(XmlUtils.xmlnode("WDID",wdid));
		xml.append(XmlUtils.xmlnode("WDPT",wdpt));
		xml.append(XmlUtils.xmlnode("FAID",faid));
		xml.append(XmlUtils.xmlnode("GLGJ",glgj));
		xml.append(XmlUtils.xmlnode("CGLY",cgly));
		xml.append(XmlUtils.xmlnode("PNRNO",pnrno));
		xml.append(XmlUtils.xmlnode("LX",lx));
		xml.append(XmlUtils.xmlnode("TKNO",tkno));
		xml.append(XmlUtils.xmlnode("TFBZ",tfbz));
		xml.append(XmlUtils.xmlnode("CJRLX",cjrlx));
		xml.append(XmlUtils.xmlnode("CJR",cjr));
		xml.append(XmlUtils.xmlnode("HKGS",hkgs));
		xml.append(XmlUtils.xmlnode("CW",cw));
		xml.append(XmlUtils.xmlnode("HB",hb));
		xml.append(XmlUtils.xmlnode("SHCLX",shclx));
		xml.append(XmlUtils.xmlnode("HC",hc));
		xml.append("</SQL>");
		return xml.toString();
	}
	
	
	/**销售毛利分析**/
	@RequestMapping
	public String xsmlfx(JpXsmlfx jpXsmlfx ,HttpServletRequest request,Model model,HttpServletResponse response)throws Exception{
		Shyhb user = SessionUtils.getShshbSession();
		//user.setShbh("HZKFZX");
		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"),20);
		int pageNum = (NumberUtils.toInt(request.getParameter("pageNum"),1));
		int start = (pageNum-1)*pageSize+1;
		int count = pageSize;
		
		Page page = new Page(pageNum,pageSize);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		long counts =0;
		Map<String,Object> mapsum = null;
		String export=request.getParameter("export");
		try {
			if(StringUtils.isNotBlank(export)){
				String displayName="销售毛利分析";
//				jpXsmlfx.setStart(start);
//				jpXsmlfx.setCount(count);
				jpXsmlfx.setShbh(user.getShbh());
				jpXsmlfx.setDcbs("1");
				try {
					JpXsmlExportPage exportPage = new JpXsmlExportPage(jpXsmlfx);
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
			String xml = this.getxsmlXml(request, start, count);
			System.out.print("销售毛利XML=>"+xml);
			Map<String, Object> mapparam = new HashMap<String, Object>();
			mapparam.put("p_xml", xml);
			Map<String, Object> map = procedureServiceImpl.getMlfxList(mapparam);
			if(map!=null){
				list = (List<Map<String, Object>>)map.get("p_cur");
				attachService.shyhb2("CP_YHBH", user.getShbh()).execute(list);
				List listcount= (List) map.get("p_cur_allcount");
				mapsum =(Map<String,Object>)listcount.get(0);
				counts=NumberUtils.toLong(VeStr.getValue(mapsum, "ALLCOUNT"));
			}
			attachService.wdzl("WDID").execute(list);
			page.setList(list);
			page.setTotalCount(counts);
			model.addAttribute("page", page);
			model.addAttribute("allsum", mapsum);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错请联系管理员");
		}
		
		return viewpath("list");
	
	}

}
