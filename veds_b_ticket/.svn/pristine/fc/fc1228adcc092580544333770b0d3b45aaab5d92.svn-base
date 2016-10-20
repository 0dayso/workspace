package cn.vetech.web.vedsb.bbzx;

import java.io.File;
import java.util.ArrayList;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.export.Export2Xls;
import org.vetech.core.business.pid.api.IbeService;
import org.vetech.core.business.pid.api.detrf.DetrFParam;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.FileOperate;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shbm;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.JpShbmServiceImpl;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.entity.bbzx.Cpbb;
import cn.vetech.vedsb.jp.entity.bbzx.CpbbExportPage;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpHd;
import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;
import cn.vetech.vedsb.jp.entity.office.JpOffice;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpHdServiceImpl;
import cn.vetech.vedsb.jp.service.jpjpgl.JpJpServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.office.JpOfficeServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class CpbbController extends MBaseControl<JpJp, JpJpServiceImpl>{

	@Autowired
	private AttachService attachService;
	@Autowired
	private WdzlszServiceImpl wdzlszServiceImpl;
	@Autowired
	private JpHdServiceImpl jphdservice;
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	@Autowired
	private JpShbmServiceImpl shbmServiceImpl;
	
	@Autowired
	private Export2Xls export2Xls;
	
	@Autowired
	private JpOfficeServiceImpl jpOfficeServiceImpl;
	
	@Override
	public void updateInitEntity(JpJp t) {
		
	}

	@Override
	public void insertInitEntity(JpJp t) {
		
	}
	/**
	 * <进入报表中心的出票报表页面>
	 * 
	 * @param page
	 * @param model
	 * @return [参数说明]
	 * @author heqiwen
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "viewlist")
	public String view(Cpbb t,ModelMap model,HttpServletRequest request,HttpServletResponse response){
		Shyhb user = SessionUtils.getShshbSession();
		t.setShbh(user.getShbh());
		int pageNum=NumberUtils.toInt(request.getParameter("pageNum"),1);
		int pageSize=NumberUtils.toInt(request.getParameter("pageSize"),10);
		String tjfs=request.getParameter("tjfs");
		tjfs = StringUtils.isBlank(tjfs) ? "1" : tjfs;
		t.setTjfs(tjfs);
		String orderBy=request.getParameter("orderBy");
		if(StringUtils.isNotBlank(orderBy)){
			t.setOrderBy(orderBy.replace("+", " "));
		}
		if(StringUtils.isBlank(t.getKsrq())){
			t.setKsrq(VeDate.getStringDateShort());
		}
		if(StringUtils.isBlank(t.getJsrq())){
			t.setJsrq(VeDate.getStringDateShort());
		}
		
		String export=request.getParameter("export");
		try {
			if(StringUtils.isNotBlank(export)){
				String displayName="出票报表";
				try {
					CpbbExportPage exportPage = new CpbbExportPage(t);
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
			
			Page page = new Page(pageNum,pageSize);
			//查网店
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("zt", "1");//启用
			param.put("shbh", user.getShbh());
			param.put("ywlxs", new String[]{"1001901","1001902"});
			List<Wdzlsz> wdzlszList = wdzlszServiceImpl.getWdZlszListByYwlx(param);
			model.addAttribute("wdzlszList", wdzlszList);
			
			Map<String,Object> map  = this.baseService.queryCpbbPage(t);
			if(map.get("list")!=null){
				List<Map<String,Object>> list=(List<Map<String,Object>>)map.get("list");
				//判断list是否非空
				if(CollectionUtils.isNotEmpty(list)){
					//遍历 将bmbh中存放的ID替换成名称
					for(int i=0;i<list.size();i++){
						Map<String,Object> maplist=list.get(i);
						//通过出票部门ID，商户编号在商户部门表中查询对应对象
						if(maplist.get("CP_BMBH") != null ){
							Shbm shbm=shbmServiceImpl.getMyBatisDao().getShbmById(maplist.get("CP_BMBH").toString(),user.getShbh());
							if(shbm != null){
								maplist.put("CP_BMBH", shbm.getMc());
							}
						}
					}
					if("1".equals(tjfs)){
						attachService.zfkm("SKKM").wdzl("WDID").hkgs("HKGS").shyhb("CJR",user.getShbh()).execute(list);
					}else if("3".equals(tjfs)){
						attachService.wdzl("WDID").execute(list);
					}else if("6".equals(tjfs)){
						attachService.hkgs("HKGS").execute(list);
					}else if("7".equals(tjfs)){
						attachService.shyhb("CP_YHBH", user.getShbh()).execute(list);
					}
				}
				
				page.setList(list);
				page.setTotalCount(NumberUtils.toInt(VeStr.getValue(map, "allCount")));
				List<Map<String,Object>> cpsumlist = (List<Map<String,Object>>)map.get("list_sum");
				model.addAttribute("list", list);
				if(CollectionUtils.isNotEmpty(cpsumlist)){
					model.addAttribute("cbsumList", cpsumlist.get(0));
				}
			}
			
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("分页查询出票报表失败", e);
		}
		return viewpath("list");
	}
	/**
	 * <进入修改机票信息页面>
	 * 
	 * @param model
	 * @param id
	 * @param ddbh
	 * @param request
	 * @return [参数说明]
	 * @author heqiwen
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "updateJp",method = RequestMethod.GET)
	public String updateJp(ModelMap model,@RequestParam(value = "id") String id,
			@RequestParam(value = "ddbh") String ddbh,HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();	
		JpJp jp= new JpJp();
		jp.setDdbh(ddbh);
		jp.setShbh(user.getShbh());	
		jp.setId(id);
		try {
			List<JpJp> list=this.baseService.queryJpListByDdbh(jp);
			jp=this.baseService.getEntityById(jp);
			model.addAttribute("tklist", list);
			model.addAttribute("ticket", jp);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("分页查询出票报表失败", e);
			return this.addError("进入修改机票信息错误" + e.getMessage(), e, "updateJp", model);
		}
		return viewpath("updatejpcp");
	}
	
	/**
	 * <确认修改出票信息>
	 * 
	 * @param map
	 * @param model
	 * @param request
	 * @return [参数说明]
	 * @author heqiwen
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "updateTicket",method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateTicket(HttpServletRequest request){
		String ddbh=request.getParameter("ddbh");
		String[] tkno=request.getParameterValues("tkno");
		String cgly=request.getParameter("cgly");
		String[] id=request.getParameterValues("id");
		String[] ei=request.getParameterValues("ei");
		String cgkm=request.getParameter("cgkm");
		String cpOfficeid=request.getParameter("cpOfficeid");
		String cgDdbh=request.getParameter("cgDdbh");
		String workno=request.getParameter("workno");
		String wbdh=request.getParameter("wbdh");
		String printno=request.getParameter("printno");
		Map<String,Object> reparam=new HashMap<String,Object>();
		Shyhb user = SessionUtils.getShshbSession();
		String shbh=user.getShbh();
		try {
			if(StringUtils.isNoneBlank(tkno)){
				for(int i=0;i<tkno.length;i++){
					JpJp jp=new JpJp();
					jp.setId(id[i]);
					jp.setShbh(shbh);
					jp.setTkno(tkno[i]);
					jp.setEi(ei[i]);
					jp.setDdbh(cgDdbh);
					jp.setCgly(cgly);
					jp.setCpOfficeid(cpOfficeid);
					jp.setWorkno(workno);
					jp.setWbdh(wbdh);
					jp.setCgkm(cgkm);
					jp.setCgDdbh(cgDdbh);
					jp.setPrintno(printno);
					//更新机票表中的数据
					this.baseService.updateJpcp(jp);
				}
			}
			
			JpKhdd jpkhdd=new JpKhdd();
			jpkhdd.setDdbh(ddbh);
			jpkhdd.setCgly(cgly);
			jpkhdd.setCgkm(cgkm);
			jpkhdd.setCgDdbh(cgDdbh);
			jpkhdd.setWbdh(wbdh);
			jpkhdd.setShbh(shbh);
			//更新机票客户表中的数据
			jpKhddServiceImpl.getMyBatisDao().updateCpbbXxgx(jpkhdd);
			
			reparam.put("CODE", "0");
			reparam.put("MSG", "修改成功");
		} catch (Exception e) {
			logger.error("出票报票 修改出票信息错误", e.getMessage());
			reparam.put("CODE", "-1");
			reparam.put("MSG", "出票报票 修改出票信息错误");
		}
		return reparam;
	}
	
	/**
	 * <出票报表删除未收款状态的出票>
	 * 
	 * @param id
	 * @param model
	 * @param request
	 * @return [参数说明]
	 * @author heqiwen
	 * @return int [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "deleteTicket",method = RequestMethod.POST)
	public @ResponseBody int deleteTicket(@RequestParam(value = "id") String id, ModelMap model,
			HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		JpJp jp=new JpJp();
		jp.setShbh(user.getShbh());
		jp.setId(id);
		try {
			this.baseService.deleteById(jp);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("出票报票 删除出票信息错误", e);
			return 0;
		}
	}
	
	/**
	 * <出票报表 明细统计时点击票号 根据id查询机票信息>
	 * 
	 * @param model
	 * @param id
	 * @param request
	 * @return [参数说明]
	 * @author heqiwen
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "seeMoreJp",method = RequestMethod.GET)
	public String seeMoreJp(ModelMap model,@RequestParam(value = "id") String id,
			@RequestParam(value = "tkno") String tkno,HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		try {
			JpJp jp=new JpJp();
			jp.setId(id);
			jp.setShbh(user.getShbh());
			jp=this.baseService.getEntityById(jp);
			JpKhdd jpkhdd=null;
			if (jp != null) {
				attachService.zfkm("skkm").shyhb2("cpYhbh", "shbh").execute(jp);
				jpkhdd=new JpKhdd();
				jpkhdd.setDdbh(jp.getDdbh());
				jpkhdd.setShbh(user.getShbh());
				jpkhdd=jpKhddServiceImpl.getKhddByDdbh(jpkhdd);
			}
			
			if (jpkhdd != null) {
				attachService.shyhb2("ddyh","shbh").wdzl("wdid").execute(jpkhdd);
			}
			
			//航段信息
			JpHd jphd=new JpHd();
			jphd.setShbh(user.getShbh());
			jphd.setTkno(tkno);
			List<JpHd> hdlist=jphdservice.queryJpHdByTkno(jphd);
			if(hdlist.size()>0){
				for(JpHd hd:hdlist){
					hd.setCfcs(this.baseService.getCityNameByszm(hd.getCfcity()));
					hd.setDdcs(this.baseService.getCityNameByszm(hd.getDdcity()));
				}
			}
			model.addAttribute("hdlist", hdlist);
			model.addAttribute("ticket", jp);
			model.addAttribute("jpkhdd", jpkhdd);
		} catch (Exception e) {
			logger.error("出票报票 点击票号根据id查询机票信息", e);
			e.printStackTrace();
			return this.addError("进入点击票号根据id查询机票信息页面失败" + e.getMessage(), e, "seemoreJp", model);
		}
		
		return viewpath("seejpcp");
	}
	
	/**
	 * <加载不同的采购来源后,显示不同的页面>
	 * 
	 * @param model
	 * @param request
	 * @return [参数说明]
	 * @author heqiwen
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "loadcgly")
	public String loadcgly(ModelMap model,HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		try {
			Shzfkm shzfkm=new Shzfkm();
			shzfkm.setSfqy("1");
			shzfkm.setShbh(user.getShbh());
			List<Shzfkm> zfkms=shzfkmServiceImpl.getShzfkmList(shzfkm);
			List<JpPz> pzList = jpPzServiceImpl.selectJpPzByShbh(user.getShbh());
			List<JpOffice> officelist= jpOfficeServiceImpl.selectJpOfficeByShbh(user.getShbh());
			List<JpOffice> officelistNew = new ArrayList<JpOffice>();
			if(CollectionUtils.isNotEmpty(officelist)){
				attachService.zfkm("bopcgkm").execute(officelist);
				for(JpOffice j : officelist){
					if("1".equals(j.getSfbopcp())){
						officelistNew.add(j);
					}
				}
			}
			model.addAttribute("bopcgkmlist",officelistNew);
			model.addAttribute("zfkms", zfkms);
			model.addAttribute("pzList", pzList);
		} catch (Exception e) {
			logger.error("查询支付科目出错"+e.getMessage());
		}
		
		return viewpath("zjp_cpxx_cgly");
	}
	
	@RequestMapping(value="detr")
	public @ResponseBody String detr(String command ,ModelMap model,HttpServletRequest request){
		String result = "指令为空！";
		Shyhb user = SessionUtils.getShshbSession();
		if (StringUtils.isNotBlank(command)) {
			JpPz jppz = jpPzServiceImpl.getJpPzByShbh(user.getShbh());
			DetrFParam param = new DetrFParam();
			param.setUserid(user.getPidyh());
			param.setCommand(command);
			param.setUrl("http://" + jppz.getPzIp() + ":" + jppz.getPzPort());
			try {
				result = IbeService.detrF(param);
			} catch (EtermException e) {
				e.printStackTrace();
				result = "错误：EtermException" + e.getMessage();
			}
		}
		return "</pre>><font color=red>"+command.toUpperCase()+"</font><br>"+result.replace("\r", "<br>")+"</pre>";
	}
}
