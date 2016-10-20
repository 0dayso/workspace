package cn.vetech.web.vedsb.cgdzbb;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.vedsb.common.service.base.ShzfkmServiceImpl;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpCgdzCyb;
import cn.vetech.vedsb.jp.service.cgdzbb.JpCgdzCybServiceImpl;
import cn.vetech.vedsb.jp.service.jpddgl.JpKhddServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpCgdzYcController extends MBaseControl<JpCgdzCyb, JpCgdzCybServiceImpl>{
	@Autowired
	private JpKhddServiceImpl jpKhddServiceImpl;
	
	@Autowired
	private ShzfkmServiceImpl shzfkmServiceImpl;
	
	
	@Override
	public void insertInitEntity(JpCgdzCyb t) {
	}

	@Override
	public void updateInitEntity(JpCgdzCyb t) {
	}
	
	@Override
	public void selectInitEntity(Map<String,Object> param){
		Shyhb user = SessionUtils.getShshbSession();
		param.put("search_EQ_shbh", user.getShbh());
	}
	/**
	 * 采购异常查询列表
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @param request
	 * @param jpcgdzcyb
	 * @return
	 */
	@RequestMapping
	public @ResponseBody Page getCgdzYcList(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum
			,@RequestParam(value = "pageSize", defaultValue = "10") int pageSize
			,@RequestParam(value = "sortType", defaultValue = "desc") String sortType
			,HttpServletRequest request,@ModelAttribute("entity") JpCgdzCyb jpcgdzcyb){
		Page page = null;
		try {
			Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_",true);
			String clzt = (String)searchParams.get("search_EQ_cl_zt");
			if(StringUtils.isBlank(clzt)){
				searchParams.put("search_NOT_cl_zt", "2");
			}
			String ksrq = request.getParameter("ksrq");
			String jsrq = request.getParameter("jsrq");
			String rqtj = request.getParameter("rqtj");
			if("0".equals(rqtj)){
				searchParams.put("search_GTdate_cj_Datetime", ksrq);
				searchParams.put("search_LTdate_cj_Datetime", jsrq);
			}else if("1".equals(rqtj)){
				searchParams.put("search_GTdate_cl_Datetime", ksrq);
				searchParams.put("search_LTdate_cl_Datetime", jsrq);
			}else if("2".equals(rqtj)){
				searchParams.put("search_GTdate_cp_Datetime", ksrq);
				searchParams.put("search_LTdate_cp_Datetime", jsrq);
			}
			selectInitEntity(searchParams);
			page = this.baseService.queryPage(searchParams, pageNum, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	/**
	 * 处理采购异常
	 * @param id
	 * @return
	 */
	@RequestMapping
	public String cgycClEdit(String id){
		return viewpath("cgyccl");
	}
	
	/**
	 * 处理采购异常并保存
	 * @param id
	 * @param clsm
	 * @return
	 */
	@RequestMapping
	public String cgycClSave(String id,String clsm){
		Shyhb user = SessionUtils.getShshbSession();
		try {
			JpCgdzCyb jpCgdzCyb = new JpCgdzCyb();
			jpCgdzCyb.setId(id);
			jpCgdzCyb.setShbh(user.getShbh());
			jpCgdzCyb = this.baseService.getEntityById(jpCgdzCyb);
			if(null!=jpCgdzCyb){
				jpCgdzCyb.setClSm(clsm);
				jpCgdzCyb.setClZt("1");
				jpCgdzCyb.setClDeptid(user.getShbmid());
				jpCgdzCyb.setClUserid(user.getShbh());
				jpCgdzCyb.setClDatetime(new Date());
			}
			this.baseService.update(jpCgdzCyb);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("处理采购异常失败!", e);
		}
		return "/common/turning";
	}
	
	/**
	 * 取消采购异常
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping
	public String qxcgdz(String id){
		Shyhb user = SessionUtils.getShshbSession();
		try {
			JpCgdzCyb jpCgdzCyb = new JpCgdzCyb();
			jpCgdzCyb.setShbh(user.getShbh());
			jpCgdzCyb.setId(id);
			jpCgdzCyb = this.baseService.getEntityById(jpCgdzCyb);
			if(null!=jpCgdzCyb){
				jpCgdzCyb.setClZt("2");//取消
				jpCgdzCyb.setClDeptid(user.getShbmid());
				jpCgdzCyb.setClUserid(user.getShbh());
				jpCgdzCyb.setClDatetime(new Date());
				this.baseService.update(jpCgdzCyb);
				return "1";
			}
			return "0";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("取消采购异常失败!", e);
			return "0";
		}
	}
	
	/**
	 * 新增采购异常
	 * @return
	 */
	@RequestMapping
	public String addcgyc(HttpServletRequest request,Model model){
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			JpCgdzCyb cyb = new JpCgdzCyb();
			cyb.setId(id);
			cyb = this.baseService.getEntityById(cyb);
			model.addAttribute("cyb", cyb);
			model.addAttribute("flag","1");
			Shzfkm shzfkm = new Shzfkm();
			shzfkm.setSfqy("1");
			shzfkm.setShbh(shbh);	
			List<Shzfkm> zfkmlist = this.shzfkmServiceImpl.getShzfkmList(shzfkm);
			model.addAttribute("zfkmlist", zfkmlist);
		}
		return viewpath("addcgdzyc");
	}
	
	/**
	 * 新增采购异常查询
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String addcgycSearch(HttpServletRequest request,Model model){
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		try {
			String pnrno = request.getParameter("pnrno");
			String ddbh = request.getParameter("ddbh");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pnrno", pnrno);
			map.put("ddbh", ddbh);
			map.put("shbh", shbh);
			List<Map<String, Object>> list = this.jpKhddServiceImpl.addcgdzycSearch(map);
			model.addAttribute("list",list);
			Shzfkm shzfkm = new Shzfkm();
			shzfkm.setSfqy("1");
			shzfkm.setShbh(shbh);	
			List<Shzfkm> zfkmlist = this.shzfkmServiceImpl.getShzfkmList(shzfkm);
			model.addAttribute("zfkmlist", zfkmlist);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增采购异常查询!", e);
		}
		return viewpath("addcgdzyc");
	}
	
	/**
	 * 新增采购异常保存
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String addcgdzSave(HttpServletRequest request,Model model) throws Exception{
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		try {
			String id = request.getParameter("id");
			String zfkm = request.getParameter("zfkm");
			String ddbh = request.getParameter("ddbh");
			String cgly = request.getParameter("cgly");//采购来源
			String yclx = request.getParameter("yclx");//异常类型
			String ycsm = request.getParameter("ycsm");//异常说明
			String cgje = request.getParameter("cgje");//采购金额
			if(StringUtils.isNotBlank(id)){
				JpCgdzCyb jp = new JpCgdzCyb();
				jp.setId(id);
				jp = this.baseService.getEntityById(jp);
				jp.setCgZfkm(zfkm);
				jp.setCplx(cgly);
				jp.setCylx(yclx);
				jp.setBy3(ycsm);
				jp.setZfjeAsms(NumberUtils.toDouble(cgje));
				this.baseService.update(jp);
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				Map<String, Object> mapResult = null;
				map.put("shbh", shbh);
				map.put("ddbh", ddbh);
				JpCgdzCyb j = new JpCgdzCyb();
				j.setShbh(shbh);
				j.setDdbh(ddbh);
				j = this.baseService.searchcgdzObject(ddbh, shbh);
				if(j!=null){
					throw new Exception("订单："+ddbh+"已生成采购异常记录，不可重复生成！");
				}
				List<Map<String, Object>> list = this.jpKhddServiceImpl.addcgdzycSearch(map);//根据订单编号查询只有一个结果,取第一个
				if(CollectionUtils.isNotEmpty(list)){
					mapResult = list.get(0);//取第一个
				}
				JpCgdzCyb jpCgdzCyb = new JpCgdzCyb();
				jpCgdzCyb.setId(VeDate.getNo(4));
				if(null == mapResult){//出票日期
					jpCgdzCyb.setDzrq(VeDate.getStringDateShort());
				}else{
					jpCgdzCyb.setDzrq(VeDate.dateToStr((Date)mapResult.get("CP_DATETIME")));
					Timestamp t = (Timestamp)mapResult.get("CP_DATETIME");
					jpCgdzCyb.setCpDatetime(t);
					jpCgdzCyb.setTkno(VeStr.getValue(mapResult, "TKNO"));
					jpCgdzCyb.setHkgs(VeStr.getValue(mapResult, "HKGS"));
					jpCgdzCyb.setHkgsPnr(VeStr.getValue(mapResult, "CG_HKGS_PNR"));
					jpCgdzCyb.setPnrno(VeStr.getValue(mapResult, "CG_PNR_NO"));
				}
				jpCgdzCyb.setZfjeAsms(NumberUtils.toDouble(cgje));
				jpCgdzCyb.setDdbh(ddbh);
				jpCgdzCyb.setCplx(cgly);
				jpCgdzCyb.setCgZfkm(zfkm);
				jpCgdzCyb.setClZt("0");
				jpCgdzCyb.setCjUserid(shbh);
				jpCgdzCyb.setCjDatetime(new Date());
				jpCgdzCyb.setCjDeptid(user.getShbmid());
				jpCgdzCyb.setBy3(ycsm);
				jpCgdzCyb.setCylx(yclx);
				jpCgdzCyb.setShbh(shbh);
				this.baseService.insert(jpCgdzCyb);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增采购异常保存失败!", e);
		}
		return "/common/turning";
	}
	
	
	@RequestMapping
	public @ResponseBody String getzffs(){
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		Shzfkm shzfkkm=new Shzfkm();
		shzfkkm.setShbh(shbh);
		shzfkkm.setSfqy("1");
		List<Shzfkm> zfkmlist = this.shzfkmServiceImpl.getShzfkmList(shzfkkm);
		StringBuffer sbr = null;
		String str ="";
		if(CollectionUtils.isNotEmpty(zfkmlist)){
			sbr = new StringBuffer();
			for (Shzfkm s : zfkmlist) {
				sbr.append("<option value=''>==选择科目==</option>");
				sbr.append("<option value=" + s.getKmbh() + ">" + s.getKmmc()
						+ "</option>");
			}
			str = sbr.toString();
		}
		return str;
	}
}
