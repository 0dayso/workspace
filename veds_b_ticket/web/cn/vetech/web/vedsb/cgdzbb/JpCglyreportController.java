package cn.vetech.web.vedsb.cgdzbb;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vetech.core.business.export.Export2Xls;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.FileOperate;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.XmlUtils;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shbm;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.service.JpShbmServiceImpl;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.jp.entity.cgdzbb.Cgdzbb;
import cn.vetech.vedsb.jp.entity.cgdzbb.CgdzbbExportPage;
import cn.vetech.vedsb.jp.entity.cgdzbb.CgdzbbMx;
import cn.vetech.vedsb.jp.entity.cgdzbb.CgdzbbMxExportPage;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpCglyreportController extends MBaseControl<JpJp, JpJpServiceImpl>{
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;
	@Autowired
	private JpShbmServiceImpl shbmServiceImpl;
	@Autowired
	private Export2Xls export2Xls;
	@Override
	public void insertInitEntity(JpJp t) {
		
	}

	@Override
	public void updateInitEntity(JpJp t) {
		
	}
	
	private String getXml(HttpServletRequest request){
		Shyhb user=SessionUtils.getShshbSession();
		String ksrq = request.getParameter("kssj");
		String jsrq = request.getParameter("jssj");
		String hcgngj = request.getParameter("hcgngj");
		String tjfs = request.getParameter("tjfs");
		String shbh = user.getShbh();
		StringBuffer xml = new StringBuffer("<SQL>");
		xml.append(XmlUtils.xmlnode("SHBH",shbh));
		xml.append(XmlUtils.xmlnode("KSSJ",ksrq));
		xml.append(XmlUtils.xmlnode("JSSJ",jsrq));
		xml.append(XmlUtils.xmlnode("HCGLGJ",hcgngj));
		xml.append(XmlUtils.xmlnode("TJFS",tjfs));
		xml.append("</SQL>");
		return xml.toString();
	}
	
	/**
	 * 查询采购来源报表
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	public String getCgdzReport(Cgdzbb cgdzbb,HttpServletRequest request,Model model,HttpServletResponse response) throws Exception{
		model.addAttribute("startdate",VeDate.getStringDate());
		Shyhb user=SessionUtils.getShshbSession();
		List<Map<String, Object>> list = null;
		String export=request.getParameter("export");
		try {
			if(StringUtils.isNotBlank(export)){
				String displayName="采购对账报表";
				cgdzbb.setShbh(user.getShbh());
				try {
					CgdzbbExportPage exportPage = new CgdzbbExportPage(cgdzbb);
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
			String xml = this.getXml(request);
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("p_xml", xml);
			list = procedureServiceImpl.procgreport(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错请联系管理员");
		}
		String tjfs = StringUtils.trimToEmpty(request.getParameter("tjfs"));
		
		if(CollectionUtils.isNotEmpty(list)){
			if (!"3".equals(tjfs)){
				for (Map<String, Object> one : list) {
					String mapcplx = (String) one.get("CPLX");
					one.put("fz1", one.get("FZ1"));
					one.put("fz2", one.get("FZ2"));
					String zfkmmc = (String)one.get("fz1");
					if(StringUtils.isNotBlank(zfkmmc)){
						String[] s = {zfkmmc};
						Shzfkm zfkmmcobject = (Shzfkm)this.shzfkmServiceImpl.getBean(null, s);
						if(zfkmmcobject!=null&&!"BSP".equals(mapcplx)){
							zfkmmc = zfkmmcobject.getKmmc();
							one.put("fz1", zfkmmc);
						}
					}
					// 判断分组1是显示中文还是显示代码，因为FZ1返回的均为代码，当要显示中文时则给fz1变量赋值返回的中文字段
					if ("BPET".equals(mapcplx) || "BP".equals(mapcplx)) {
						one.put("fz1", one.get("ZFKMMC"));
					}
					if ("OP".equals(mapcplx) || "ODT".equals(mapcplx)) {
						one.put("fz1", one.get("ZFKMMC"));
						// 判断分组2是显示中文还是显示代码，因为FZ2返回的均为代码，当要显示中文时则给fz2变量赋值返回的中文字段
						one.put("fz2", one.get("HZDWMC"));
					}
				}
			}
		}
		model.addAttribute("leftlist",list);
		model.addAttribute("tjfs", tjfs);
		model.addAttribute("enddate",VeDate.getStringDate());
		return viewpath("list");
	}
	
	/**
	 * 查询采购对账报表明细
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping
	public String getCgdzMxReport(CgdzbbMx cgdzmx,HttpServletRequest request,Model model,HttpServletResponse response) throws Exception{
		Shyhb user = SessionUtils.getShshbSession();
		int pageNum = NumberUtils.toInt(request.getParameter("pageNum"),1);
		int pageSize = NumberUtils.toInt(request.getParameter("pageSize"),20);
		int start = (pageNum-1)*pageSize;
		int count = pageSize;
		Page page = new Page(pageNum,pageSize);
		Map<String, Object> sumMap = null;
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		int counts =0;
		String export=request.getParameter("export");
		try {
			if(StringUtils.isNotBlank(export)){
				String displayName="采购对账报表明细";
				cgdzmx.setDatetype("1");
				cgdzmx.setFx("left");
				cgdzmx.setLy("2");
				cgdzmx.setShbh(user.getShbh());
				cgdzmx.setCp_yhbh(user.getBh());
				cgdzmx.setStart(start);
				cgdzmx.setCount(count);
				try {
					CgdzbbMxExportPage exportPage = new CgdzbbMxExportPage(cgdzmx);
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
			String xml = this.getMxXml(request, start, count);
			System.out.print("采购报表明细XML=>"+xml);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("p_xml", xml);
			Map<String, Object> map = procedureServiceImpl.promxcgreport(param);
			if(map!=null){
				list = (List<Map<String, Object>>)map.get("list_cur");
				counts = (Integer)map.get("count");
				sumMap = (Map<String,Object>)map.get("sumMap");
			}
			//判断list是否非空
			if(CollectionUtils.isNotEmpty(list)){
				//遍历 将bmbh中存放的ID替换成名称
				for(int i=0;i<list.size();i++){
					Map<String,Object> maplist=list.get(i);
					//通过出票部门ID，商户编号在商户部门表中查询对应对象
					String cpbmbh = (String)maplist.get("CP_BMBH");
					Shbm shbm=shbmServiceImpl.getMyBatisDao().getShbmById(cpbmbh,user.getShbh());
					if(shbm != null){
						maplist.put("CP_BMBH", shbm.getMc());
					}
				}
			}
			page.setList(list);
			page.setTotalCount(counts);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询出错请联系管理员");
		}
		Shzfkm shzfkm = new Shzfkm();
		shzfkm.setShbh(user.getShbh());
		shzfkm.setSfqy("1");
		List<Shzfkm> zfkm = this.shzfkmServiceImpl.getShzfkmList(shzfkm);
		model.addAttribute("zfkm",zfkm);
		model.addAttribute("page",page);
		model.addAttribute("sumMap", sumMap);
		return viewpath("mxlist");
	}
	
	private String getMxXml(HttpServletRequest request,int start,int count){
		Shyhb user = SessionUtils.getShshbSession();
		String ksrq = StringUtils.trimToEmpty(request.getParameter("ksrq"));
		if(StringUtils.isBlank(ksrq)){
			ksrq = VeDate.getStringDateShort();
		}
		String jsrq = StringUtils.trimToEmpty(request.getParameter("jsrq"));
		String office = StringUtils.trimToEmpty(request.getParameter("office"));//office号
		String cp_pid = StringUtils.trimToEmpty(request.getParameter("cp_pid"));
		String pnrno = StringUtils.trimToEmpty(request.getParameter("pnrno"));
		String cjr = StringUtils.trimToEmpty(request.getParameter("cjr"));
		String hbh = StringUtils.trimToEmpty(request.getParameter("hbh"));
		String hc = StringUtils.trimToEmpty(request.getParameter("hc"));
		String cplx = StringUtils.trimToEmpty(StringUtils.trimToEmpty(request.getParameter("cplx")));
		// 1为正常票 3为废票 2为退票 4改签
		String type = StringUtils.trimToEmpty(request.getParameter("type"));
		String cg_zfkm = StringUtils.trimToEmpty(request.getParameter("cg_zfkm"));
		String printno = StringUtils.trimToEmpty(request.getParameter("printno"));
		String hkgs = StringUtils.trimToEmpty(request.getParameter("hkgs"));
		String hzdw = StringUtils.trimToEmpty(request.getParameter("hzdw"));
		// 1 汇总中 2 是明细中
		//String ly = StringUtils.trimToEmpty(request.getParameter("ly"));
		// 国内国际
		String hcglgj = StringUtils.trimToEmpty(request.getParameter("hcglgj"));
		String tjfs = StringUtils.trimToEmpty(request.getParameter("tjfs"));
		
		String zkfx = StringUtils.trimToEmpty(request.getParameter("zkfx")); //客户类型统计是  查明细
		
		String zf_fkf = StringUtils.trimToEmpty(request.getParameter("zf_fkf")); // 是否付款
		String hkgs_pnr = StringUtils.trimToEmpty(request.getParameter("hkgs_pnr"));//大编码
		String tkno=StringUtils.trimToEmpty(request.getParameter("tkno"));
		
		StringBuffer detailXml = new StringBuffer("<SQL>");
		detailXml.append(XmlUtils.xmlnode("START",start));
		detailXml.append(XmlUtils.xmlnode("COUNT",count));
		detailXml.append(XmlUtils.xmlnode("TYPE",type));
		detailXml.append(XmlUtils.xmlnode("KSRQ",ksrq));
		detailXml.append(XmlUtils.xmlnode("JSRQ",jsrq));
		detailXml.append(XmlUtils.xmlnode("OFFICE",office));
		detailXml.append(XmlUtils.xmlnode("CP_PID",cp_pid));
		detailXml.append(XmlUtils.xmlnode("DATETYPE","1"));
		detailXml.append(XmlUtils.xmlnode("SHBH",user.getShbh()));
		//detailXml.append(XmlUtils.xmlnode("CP_BMBH",user.getShbmid()));
		detailXml.append(XmlUtils.xmlnode("CP_YHBH",user.getBh()));
		detailXml.append(XmlUtils.xmlnode("PNRNO",pnrno));
		detailXml.append(XmlUtils.xmlnode("CJR",cjr));
		detailXml.append(XmlUtils.xmlnode("HBH",hbh));
		detailXml.append(XmlUtils.xmlnode("HC",hc));
		detailXml.append(XmlUtils.xmlnode("CPLX",cplx));
		detailXml.append(XmlUtils.xmlnode("CG_ZFKM",cg_zfkm));
		detailXml.append(XmlUtils.xmlnode("PRINTNO",printno));
		detailXml.append(XmlUtils.xmlnode("HKGS",hkgs));
		detailXml.append(XmlUtils.xmlnode("HZDW",hzdw));
		detailXml.append(XmlUtils.xmlnode("FX","left"));
		detailXml.append(XmlUtils.xmlnode("LY","2"));
		detailXml.append(XmlUtils.xmlnode("HCGLGJ",hcglgj));
		detailXml.append(XmlUtils.xmlnode("TJFS",tjfs));
		detailXml.append(XmlUtils.xmlnode("ZF_FKF",zf_fkf));
		detailXml.append(XmlUtils.xmlnode("ZKFX",zkfx));
		detailXml.append(XmlUtils.xmlnode("HKGS_PNR",hkgs_pnr));
		detailXml.append(XmlUtils.xmlnode("TKNO",tkno));
		detailXml.append("</SQL>");
		return detailXml.toString();
	}
}
