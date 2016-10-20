package cn.vetech.web.vedsb.jpzdcp;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Wdzlsz;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.common.service.base.WdzlszServiceImpl;
import cn.vetech.vedsb.jp.entity.b2bsz.JpZdcpB2bzh;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpgz;
import cn.vetech.vedsb.jp.service.b2bsz.JpZdcpB2bzhServiceImpl;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.jpzdcp.JpZdcpgzServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

@Controller
public class JpzdcpgzSzController extends MBaseControl<JpZdcpgz, JpZdcpgzServiceImpl>{
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private JpZdcpB2bzhServiceImpl jpZdcpB2bzhServiceImpl;
	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
	@Autowired
	private WdzlszServiceImpl jpWdzlszServiceImpl;
	@Override
	public void insertInitEntity(JpZdcpgz t) {
		Shyhb user = SessionUtils.getShshbSession();
		t.setShbh(user.getShbh());
		t.setFbczy(user.getBh());
		t.setFbDatetime(new Date());
		t.setZt("1");
		t.setShczy(user.getBh());
		t.setXgczy(user.getBh());
		int maxyxj = NumberUtils.toInt(this.baseService.getMaxYxj(user.getShbh()));
		if(t.getBjB2bHf()!=null){
			t.setBjB2bHf(Arith.div(t.getBjB2bHf(), 100d));
		}
		if(t.getB2bHf()!=null){
			t.setB2bHf(Arith.div(t.getB2bHf(), 100d));
		}
		if(t.getBjCpfdwc()!=null){
			t.setBjCpfdwc(Arith.div(t.getBjCpfdwc(), 100d));
		}
		if(t.getPtbjPxhlfd()!=null){
			t.setPtbjPxhlfd(Arith.div(t.getPtbjPxhlfd(),100d));
		}
		t.setYxj(++maxyxj);
	}

	@Override
	public void updateInitEntity(JpZdcpgz t) {
		Shyhb user = SessionUtils.getShshbSession();
		t.setXgczy(user.getBh());
		t.setXgDatetime(new Date());
		if(t.getBjB2bHf()!=null){
			t.setBjB2bHf(Arith.div(t.getBjB2bHf(), 100d));
		}
		if(t.getB2bHf()!=null){
			t.setB2bHf(Arith.div(t.getB2bHf(), 100d));
		}
		if(t.getBjCpfdwc()!=null){
			t.setBjCpfdwc(Arith.div(t.getBjCpfdwc(), 100d));
		}
		if(t.getPtbjPxhlfd()!=null){
			t.setPtbjPxhlfd(Arith.div(t.getPtbjPxhlfd(),100d));
		}
	}
	
	/**
	 * 保存出票规则设置
	 * @param jpZdcpgz
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping
	public String saveCpgz(@ModelAttribute("entity")JpZdcpgz jpZdcpgz, ModelMap model,HttpServletRequest request){
		String zdcpgzmc = request.getParameter("gzmc");
		String bnzdcp = request.getParameter("bnzdcp");
		String id = jpZdcpgz.getId();
		try {
			zdcpgzmc = URLDecoder.decode(URLDecoder.decode(zdcpgzmc,"UTF-8"), "UTF-8"); 
			bnzdcp =URLDecoder.decode(URLDecoder.decode(bnzdcp,"UTF-8"), "UTF-8");
			jpZdcpgz.setGzmc(zdcpgzmc);
			jpZdcpgz.setBnzdcp(bnzdcp);
			if(StringUtils.isBlank(id)){//新增
				insertInitEntity(jpZdcpgz);
				this.baseService.insert(jpZdcpgz);
			}else{
				updateInitEntity(jpZdcpgz);
				this.baseService.update(jpZdcpgz);
			}
		} catch (Exception e) {
			logger.error("出票规则设置保存失败", e);
			return this.addError("出票规则设置保存失败"+e.getMessage(),e, "edit",model);
		}
		return "/common/turning";
	}
	
	/**
	 * 打开新增或编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String cpgzEdit(ModelMap model,String id,String index){//如果index为2怎为复制 3自动出票监控查看规则详细
		String[] ptbjPtyxjArray = null;
		String[] ptbjZdxjyxjArray = null;
		JpZdcpgz jpZdcpgz=new JpZdcpgz();
		if(StringUtils.isNotBlank(id)){
			jpZdcpgz.setId(id);
			jpZdcpgz=this.baseService.getEntityById(jpZdcpgz);
			String ptbjPtyxj = jpZdcpgz.getPtbjPtyxj();
			
			if(StringUtils.isNotBlank(ptbjPtyxj)){
				ptbjPtyxjArray = ptbjPtyxj.split("\\/");
			}
			
			String ptbjZdxjyxj = jpZdcpgz.getPtbjZdxjyxj();
			if(StringUtils.isNotBlank(ptbjZdxjyxj)){
				ptbjZdxjyxjArray = ptbjZdxjyxj.split("\\/");
			}
			
			if(jpZdcpgz.getBjB2bHf()!=null){
				jpZdcpgz.setBjB2bHf(Arith.mul(jpZdcpgz.getBjB2bHf(), 100));
			}
			
			if(jpZdcpgz.getB2bHf()!=null){
				jpZdcpgz.setB2bHf(Arith.mul(jpZdcpgz.getB2bHf(), 100));
			}
			
			if(jpZdcpgz.getBjCpfdwc()!=null){
				jpZdcpgz.setBjCpfdwc(Arith.mul(jpZdcpgz.getBjCpfdwc(), 100));
			}
			
			if(jpZdcpgz.getPtbjPxhlfd()!=null){
				jpZdcpgz.setPtbjPxhlfd(Arith.mul(jpZdcpgz.getPtbjPxhlfd(),100));
			}
		}
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		// 查询网店数据,启用(zt=1)
		List<Wdzlsz> wdList = this.jpWdzlszServiceImpl.getWdZlszListByBean(shbh,"1","1001901");//查询国内网店
		int wdsize =0;
		if(CollectionUtils.isNotEmpty(wdList)){
			wdsize = wdList.size();
		}
		if(!"2".equals(index)){
			List<JpZdcpgz> ids = this.baseService.getzdcpidbyshbh(shbh);
			model.addAttribute("ids", ids);
		}
		List<JpPz> list = this.jpPzServiceImpl.selectJpPzByShbh(shbh);
		model.addAttribute("list", list);
		model.addAttribute("entity", jpZdcpgz);
		model.addAttribute("wdsize",wdsize);
		model.addAttribute("ptbjPtyxjArray", ptbjPtyxjArray);
		model.addAttribute("ptbjZdxjyxjArray",ptbjZdxjyxjArray);
		model.addAttribute("jpWdZlsz",wdList);
		if(StringUtils.isNotBlank(jpZdcpgz.getId())&&StringUtils.isNotBlank(jpZdcpgz.getZdcpy())){
			model.addAttribute("cpgzid", jpZdcpgz.getId());
			Shyhb  sh = new Shyhb();
			sh.setBh(jpZdcpgz.getZdcpy());
			sh.setShbh(shbh);
			sh = this.shyhbServiceImpl.getEntityById(sh);
			model.addAttribute("zdcpyname",sh.getXm());
		}
		if("2".equals(index)){
			jpZdcpgz.setId(null);
		}
		if("3".equals(index)){
			return viewpath("../../cpkzt/cpkzt/cpgzEdit");
		}
		return viewpath("cpgzEdit");
	}
	
	/**
	 * 验证规则名称唯一性
	 * @param gzmc
	 * @return
	 */
	@RequestMapping
	public @ResponseBody String verifyOnly(String gzmc,String id){
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		try {
			gzmc = new String(gzmc.getBytes("iso-8859-1"),"utf-8");
			int vernum = this.baseService.verifyOnly(shbh, gzmc);
			if(StringUtils.isNotBlank(id)){
				JpZdcpgz jpZdcpgz = new JpZdcpgz();
				jpZdcpgz.setId(id);
				jpZdcpgz.setShbh(shbh);
				jpZdcpgz = this.baseService.getEntityById(jpZdcpgz);
				if(gzmc.equals(jpZdcpgz.getGzmc())){
					return "1";
				}else{
					if(vernum == 0){
						return "1";
					}else{
						return "0";
					}
				}
			}else{
				if(vernum == 0){
					return "1";
				}else{
					return "0";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}
	
	/**
	 * 选择航司b2b账号
	 * @param hgks
	 * @param model
	 * @param index
	 * @param b2bCpzh
	 * @return
	 */
	@RequestMapping
	public String getb2bzh(String hgks,ModelMap model,String index,String b2bCpzh){
		List<JpZdcpB2bzh> list = new ArrayList<JpZdcpB2bzh>();
		if(StringUtils.isNotBlank(b2bCpzh)){
			String[] b2bCpzharray = b2bCpzh.split("\\/");
			String[] strid = new String[b2bCpzharray.length];
			for(int i=0; i<b2bCpzharray.length;i++){
				String[] sarray = b2bCpzharray[i].split("\\:");
				strid[i] = sarray[1];
			}
			model.addAttribute("strid", strid);
		}
		try {
			Shyhb user = SessionUtils.getShshbSession();
			String shbh = user.getShbh();
			String [] hggss = hgks.split("\\/");
			String firsthkgs = hggss[0];
			StringBuffer jpzdcphs = new StringBuffer();
			for(int i=0;i<hggss.length;i++){
				List<JpZdcpB2bzh> listb2b = this.jpZdcpB2bzhServiceImpl.getZdcpB2bzhByHkgs(shbh, hggss[i]);
				if(listb2b!=null && listb2b.size()>0){
					for(JpZdcpB2bzh pZdcpB2bzh : listb2b){
						jpzdcphs.append(pZdcpB2bzh.getHkgs());
					}
					list.addAll(listb2b);
				}
			}
			model.addAttribute("list",list);
			model.addAttribute("hggsarray",hggss);
			model.addAttribute("index",index);
			model.addAttribute("firsthkgs",firsthkgs);
			model.addAttribute("hsstr",jpzdcphs.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewpath("b2bzhlable");
	}
	
	//不分页查询
	@RequestMapping
	public String getList(JpZdcpgz jpZdcpgz,HttpServletRequest request,Model model){
		Shyhb yhb = SessionUtils.getShshbSession();
	    jpZdcpgz.setShbh(yhb.getShbh());
	      List<JpZdcpgz> list1=this.baseService.getJplist(jpZdcpgz);    
		  model.addAttribute("list", list1);
		 return viewpath("list");
	}
	
	/*
	//  批量删除
	@RequestMapping
	@ResponseBody
	public String batchDelCpgz(@RequestParam(value = "id", defaultValue = "") String id) {
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			if (StringUtils.isBlank(id)) {
				throw new Exception("规则id不能为空");
			}
			String[] ids=id.split(",");
			List<JpZdcpgz> list=new ArrayList<JpZdcpgz>();
			for (String s : ids) {
				JpZdcpgz cpgz=new JpZdcpgz();
				cpgz.setShbh(yhb.getShbh());
				cpgz.setId(s);
				list.add(cpgz);
			}
			this.baseService.batchDelCpgz(list);
			return "0";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除出票规则状态失败，规则id为 " + id, e);
			return "-1";
		}
	}
	*/
	/**
	 *  批量删除（假删除）
	 */
	@RequestMapping
	@ResponseBody
	public String batchDelCpgz(@RequestParam(value = "gzids", defaultValue = "") String gzids, @RequestParam(value = "zt", defaultValue = "") String zt) {
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			if (StringUtils.isBlank(gzids)) {
				throw new Exception("规则id不能为空");
			}
			String[] ids=gzids.split(",");
			List<JpZdcpgz> list=new ArrayList<JpZdcpgz>();
			for (String s : ids) {
				JpZdcpgz jpZdcpgz=new JpZdcpgz();
				jpZdcpgz.setShbh(yhb.getShbh());
				jpZdcpgz.setId(s);
				updateInitEntity(jpZdcpgz);
				list.add(jpZdcpgz);
			}
			this.baseService.batchUpdateZt(list,zt);
			return "0";
		} catch (Exception e) {
			logger.error("删除出票规则状态失败，规则id为 " + gzids, e);
			return "-1";
		}
	}
	/**
	 *  批量启用/停用
	 */
	@RequestMapping
	@ResponseBody
	public String batchUptZt(@RequestParam(value = "gzids", defaultValue = "") String gzids, @RequestParam(value = "zt", defaultValue = "") String zt) {
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			if (StringUtils.isBlank(gzids)) {
				throw new Exception("规则id不能为空");
			}
			String[] ids=gzids.split(",");
			List<JpZdcpgz> list=new ArrayList<JpZdcpgz>();
			for (String s : ids) {
				JpZdcpgz jpZdcpgz=new JpZdcpgz();
				jpZdcpgz.setShbh(yhb.getShbh());
				jpZdcpgz.setId(s);
				updateInitEntity(jpZdcpgz);
				list.add(jpZdcpgz);
			}
			this.baseService.batchUpdateZt(list,zt);
			return "0";
		} catch (Exception e) {
			logger.error("修改出票规则状态失败，规则id为 " + gzids, e);
			return "-1";
		}
	}

	/**
	 *  全部启用/停用
	 */
	@RequestMapping
	@ResponseBody
	public String batchAllUptZt(@RequestParam(value = "zwzt", defaultValue = "") String zwzt, JpZdcpgz jpZdcpgz) {
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			jpZdcpgz.setShbh(yhb.getShbh());
			List<JpZdcpgz> list=baseService.getJplist(jpZdcpgz);
			this.baseService.batchUpdateZt(list,zwzt);
			return "0";
		} catch (Exception e) {
			logger.error("修改出票规则状态失败", e);
			return "-1";
		}
	}
	
	/*
	//全部删除
	@RequestMapping
	@ResponseBody
	public String batchDelAllCpgz(@ModelAttribute() JpZdcpgz jpZdcpgz) {
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			jpZdcpgz.setShbh(yhb.getShbh());
			List<JpZdcpgz> list=baseService.getJplist(jpZdcpgz);
			for (JpZdcpgz jpGz : list) {
				if("1".equals(jpGz.getZt())){
					return "2";
				}
			}
			this.baseService.batchDelCpgz(list);
			return "0";
		} catch (Exception e) {
			logger.error("删除出票规则状态失败 ", e);
			return "-1";
		}
	} */
	/**
	 * 全部删除(假删除)
	 */
	@RequestMapping
	@ResponseBody
	public String batchDelAllCpgz(@RequestParam(value = "zwzt", defaultValue = "") String zwzt, JpZdcpgz jpZdcpgz) {
		try {
			Shyhb yhb = SessionUtils.getShshbSession();
			jpZdcpgz.setShbh(yhb.getShbh());
			List<JpZdcpgz> list=baseService.getJplist(jpZdcpgz);
			for (JpZdcpgz jpGz : list) {
				if("1".equals(jpGz.getZt())){
					return "2";
				}
			}
			this.baseService.batchDelCpgz(list,zwzt);
			return "0";
		} catch (Exception e) {
			logger.error("删除出票规则状态失败", e);
			return "-1";
		}
	}
	/**
	 * 
	 *优先级排序
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "updateYxj",method = RequestMethod.POST )
    public String updateYxj(@RequestParam(value = "gid") String gid,@RequestParam(value = "bj") String bj,
    		                @RequestParam(value = "dygYxj") Integer dygYxj,@RequestParam(value = "zhgYxj") Integer zhgYxj,
    		                @RequestParam(value = "qygId") String qygId,@RequestParam(value = "hygId") String hygId ){ 
	    try {      
	    	Shyhb yhb = SessionUtils.getShshbSession();
	    	if(StringUtils.isNotBlank(gid)){
	    		JpZdcpgz jpZdcpgz=this.baseService.getMyBatisDao().selectByPrimaryKey(gid);
	    		jpZdcpgz.setShbh(yhb.getShbh());
	    		Integer yxj=jpZdcpgz.getYxj();
		    
	    		if("1".equals(bj)){ //上移一个	
	    			JpZdcpgz jpZdcpgzQ=this.baseService.getMyBatisDao().selectByPrimaryKey(qygId);
	    			jpZdcpgzQ.setShbh(yhb.getShbh());
	    			Integer yxjQ=jpZdcpgzQ.getYxj();
	    			this.baseService.updateYxj(gid, yxjQ);
	    			this.baseService.updateYxj(qygId, yxj);
	    		}
	    		if("3".equals(bj)){ //下移一个
	    			JpZdcpgz jpZdcpgzH=this.baseService.getMyBatisDao().selectByPrimaryKey(hygId);
	    			jpZdcpgzH.setShbh(yhb.getShbh());
	    			Integer yxjH=jpZdcpgzH.getYxj();
	    			this.baseService.updateYxj(gid, yxjH);
	    			this.baseService.updateYxj(hygId, yxj);	
	    		}
	    		if("2".equals(bj)){ //置顶
	    			if(yxj<dygYxj){
	    				this.baseService.updateYxj(gid, dygYxj+1);
	    			}
	    		}
	    		if("4".equals(bj)){
	    			if(yxj>zhgYxj){//置底
	    				this.baseService.updateYxj(gid, zhgYxj-1);
	    			}
	    		}
	    	}
	    	return "0";
			} catch (Exception e) {
				logger.error("操作失败 ", e);
				return "-1";
			}
	}
}
