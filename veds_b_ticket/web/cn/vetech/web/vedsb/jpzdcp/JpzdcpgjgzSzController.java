package cn.vetech.web.vedsb.jpzdcp;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpGjGz;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpGjGzYxjsz;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpgjgzCzrz;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.jppz.JpPzServiceImpl;
import cn.vetech.vedsb.jp.service.jpzdcp.JpZdcpGjGzServiceImpl;
import cn.vetech.vedsb.jp.service.jpzdcp.JpZdcpGjGzYxjszServiceImpl;
import cn.vetech.vedsb.jp.service.jpzdcp.JpZdcpgjgzCzrzServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;

/**
 * 自动出票国际规则设置
 *
 */
@Controller
public class JpzdcpgjgzSzController extends MBaseControl<JpZdcpGjGz, JpZdcpGjGzServiceImpl>{
	@Autowired
	private JpPzServiceImpl jpPzServiceImpl;
	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
	@Autowired
	private JpZdcpGjGzYxjszServiceImpl jpZdcpGjGzYxjszServiceImpl;
	@Autowired
	private AttachService attachService;
	@Autowired
	private JpZdcpgjgzCzrzServiceImpl jpZdcpgjgzCzrzServiceImpl;

	@Override
	public void updateInitEntity(JpZdcpGjGz t) {
		Shyhb user = SessionUtils.getShshbSession();
		t.setXgczy(user.getBh());
		t.setXgDatetime(new Date());
		//换编码出票 1是 0否
		if("0".equals(t.getSfhbmcp())){
			t.setHbmosix(null);
			t.setHbmoffice(null);
		}
		//开启原编码重出 1开启 0不开启
		if(StringUtils.isBlank(t.getKqybmcc())){
			t.setKqybmcc("0");
		}
	}

	@Override
	public void insertInitEntity(JpZdcpGjGz t) {
		Shyhb user = SessionUtils.getShshbSession();
		t.setShbh(user.getShbh());
		t.setFbczy(user.getBh());
		t.setFbDatetime(new Date());
		t.setZt("1");
		t.setXgczy(user.getBh());
		int maxyxj = NumberUtils.toInt(this.baseService.getMyBatisDao().getMaxYxj(user.getShbh()));
		t.setYxj(++maxyxj);
		//换编码出票 1是 0否
		if("0".equals(t.getSfhbmcp())){
			t.setHbmosix(null);
			t.setHbmoffice(null);
		}
		//开启原编码重出 1开启 0不开启
		if(StringUtils.isBlank(t.getKqybmcc())){
			t.setKqybmcc("0");
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
	public String saveCpgz(@ModelAttribute("entity")JpZdcpGjGz jpzdcpgjgz,String[] pjs,String[] pjz,String[] cplx,String[] pjlx, String kqybmcc,ModelMap model,HttpServletRequest request){
		String zdcpgzmc = request.getParameter("gzmc");
		String id = jpzdcpgjgz.getId();
		try {
			zdcpgzmc = URLDecoder.decode(URLDecoder.decode(zdcpgzmc,"UTF-8"), "UTF-8"); 
			jpzdcpgjgz.setGzmc(zdcpgzmc);
			if(StringUtils.isBlank(id)){//新增
				insertInitEntity(jpzdcpgjgz);
				id = VeDate.getNo(6);
				jpzdcpgjgz.setId(id);
				this.baseService.insert(jpzdcpgjgz);
			}else{
				updateInitEntity(jpzdcpgjgz);
				jpZdcpGjGzYxjszServiceImpl.getMyBatisDao().delByGjgzid(id);
				this.baseService.update(jpzdcpgjgz);
			}
			if(pjs != null && pjs.length > 0  && cplx != null && cplx.length > 0 
			&& pjlx != null && pjlx.length > 0  && pjz != null && pjz.length > 0){
				for (int i = 0; i < pjs.length; i++) {
					//判断优先级设置是否为空
					if(StringUtils.isNotBlank(cplx[i]) && StringUtils.isNotBlank(pjlx[i]) 
						&& StringUtils.isNotBlank(pjs[i]) && StringUtils.isNotBlank(pjz[i])){
						JpZdcpGjGzYxjsz yxjsz = new JpZdcpGjGzYxjsz();
						yxjsz.setCplx(cplx[i]);
						yxjsz.setPjlx(pjlx[i]);
						yxjsz.setPjs(NumberUtils.toInt(pjs[i]));
						yxjsz.setPjz(NumberUtils.toInt(pjz[i]));
						yxjsz.setSxh(i);
						yxjsz.setGjgzid(id);
						jpZdcpGjGzYxjszServiceImpl.insert(yxjsz);
					}
				}
			}
		} catch (Exception e) {
			logger.error("出票规则设置保存失败", e);
			return this.addError("出票规则设置保存失败"+e.getMessage(),e, "edit",model);
		}
		return "/common/turning";
	}
	
	/**
	 * 打开新增或编辑页面
	 * index为2复制 3自动出票监控查看规则详细
	 * @param model
	 * @return
	 */
	@RequestMapping(value="cpgjgzedit")
	public String cpgjgzedit(ModelMap model,String id,String index){
		JpZdcpGjGz jpzdcpgjgz = new JpZdcpGjGz();
		if(StringUtils.isNotBlank(id)){
			jpzdcpgjgz.setId(id);
			jpzdcpgjgz=this.baseService.getEntityById(jpzdcpgjgz);
			model.addAttribute("entity", jpzdcpgjgz);
			List<JpZdcpGjGzYxjsz> yxjszList = jpZdcpGjGzYxjszServiceImpl.getMyBatisDao().getListByGjgzid(id);
			model.addAttribute("yxjszList", yxjszList);
		}
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		List<JpPz> list = jpPzServiceImpl.selectJpPzByShbh(shbh);
		model.addAttribute("list", list);
		if(!"2".equals(index)){
			List<JpZdcpGjGz> ids = this.baseService.getMyBatisDao().getzdcpidbyshbh(shbh);
			model.addAttribute("ids", ids);
		}
		if(StringUtils.isNotBlank(jpzdcpgjgz.getId())){
			model.addAttribute("cpgzid", jpzdcpgjgz.getId());
		}
		if(StringUtils.isNotBlank(jpzdcpgjgz.getZdcpy())){
			Shyhb  sh = new Shyhb();
			sh.setBh(jpzdcpgjgz.getZdcpy());
			sh.setShbh(shbh);
			sh = this.shyhbServiceImpl.getEntityById(sh);
			model.addAttribute("zdcpyname",sh.getXm());
		}
		
		if("2".equals(index)){
			jpzdcpgjgz.setId(null);
		}
		/*
		if("3".equals(index)){
			return viewpath("../../cpkzt/cpkzt/cpgzEdit");
		}
		*/
		return viewpath("cpgjgzedit");
	}
	
	/**
	 * 验证规则名称唯一性
	 * @param gzmc
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public String verifyOnly(String gzmc,String id){
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		try {
			gzmc = new String(gzmc.getBytes("iso-8859-1"),"utf-8");
			int vernum = this.baseService.getMyBatisDao().verifyOnly(shbh, gzmc);
			if(StringUtils.isNotBlank(id)){
				JpZdcpGjGz jpzdcpgjgz = new JpZdcpGjGz();
				jpzdcpgjgz.setId(id);
				jpzdcpgjgz.setShbh(shbh);
				jpzdcpgjgz = this.baseService.getEntityById(jpzdcpgjgz);
				if(gzmc.equals(jpzdcpgjgz.getGzmc())){
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
			logger.error("国际出票规则设置，验证规则名称唯一性"+e);
			return "0";
		}
	}

	@RequestMapping(value = "saveyxjsz")
	@ResponseBody
	public Map<String,String> saveYxjsz(@ModelAttribute("entity")JpZdcpGjGzYxjsz yxjsz){
		Map<String,String> param = new HashMap<String,String>();
		try {
			String id = yxjsz.getId();
			if(StringUtils.isNotBlank(id)){//不为空，则修改
				jpZdcpGjGzYxjszServiceImpl.update(yxjsz);
			}else{//新增
				jpZdcpGjGzYxjszServiceImpl.insert(yxjsz);
			}
			param.put("CODE", "0");
			param.put("MSG", "保存成功");
		} catch (Exception e) {
			param.put("CODE", "-1");
			param.put("MSG", "保存失败");
		}
		return param;
	}
	/**
	 * 根据条件查询集合
	 * @param jpzdcpgjgz
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "query")
	@ResponseBody
	public Page query(@ModelAttribute("entity")JpZdcpGjGz jpzdcpgjgz,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
		Shyhb user = SessionUtils.getShshbSession();
		jpzdcpgjgz.setShbh(user.getShbh());
		Page page = new Page(pageNum, pageSize);
		try {
			jpzdcpgjgz.setStart((pageNum - 1) * pageSize);
			jpzdcpgjgz.setCount(pageSize);
			List<JpZdcpGjGz> list =this.baseService.getMyBatisDao().queryPage(jpzdcpgjgz);
			int count = this.baseService.getMyBatisDao().getTotalCount(jpzdcpgjgz);
			page.setList(list);
			page.setTotalCount(count);
		} catch (Exception e) {
			logger.error("根据条件查询国际出票规则集合失败"+e);
		}
		 return page;
	}
	
	/**
	 *  规则启用/停用
	 */
	@RequestMapping(value="updatezt")
	@ResponseBody
	public String updateZt(@RequestParam(value = "gzid", defaultValue = "") String gzid, @RequestParam(value = "zt", defaultValue = "") String zt) {
		try {
			if (StringUtils.isBlank(gzid)) {
				throw new Exception("规则id不能为空");
			}
			JpZdcpGjGz jpzdcpgjgz = new JpZdcpGjGz();
			
			jpzdcpgjgz.setId(gzid);
			jpzdcpgjgz.setZt(zt);
			
			Shyhb user = SessionUtils.getShshbSession();
			jpzdcpgjgz.setShbh(user.getShbh());
			jpzdcpgjgz.setXgczy(user.getBh());
			jpzdcpgjgz.setXgDatetime(VeDate.getNow());
			
			this.baseService.updateSelective(jpzdcpgjgz);
			
			return "0";
		} catch (Exception e) {
			logger.error("修改国际出票规则状态失败，规则id为 " + gzid, e);
			return "-1";
		}
	}
	
	/**
	 *  批量启用/停用
	 */
	@RequestMapping
	@ResponseBody
	public String batchUptZt(@RequestParam(value = "zt", defaultValue = "") String zt,
			@RequestParam(value = "ids", defaultValue = "") String ids) {
		try {
			Shyhb user = SessionUtils.getShshbSession();
			Map<String,Object> param = new HashMap<String,Object>();
			//ids为空，则为全部启用/停用
			if(StringUtils.isNotBlank(ids)){
				String[] idArr = ids.split(",");
				param.put("ids", idArr);
			}
			param.put("zt", zt);
			param.put("shbh", user.getShbh());
			param.put("xgczy", user.getBh());
			param.put("xg_datetime", VeDate.getNow());
			this.baseService.getMyBatisDao().batchUpdateZt(param);
			return "0";
		} catch (Exception e) {
			logger.error("修改国际出票规则状态失败", e);
			return "-1";
		}
	}
	
	/**
	 * 根据商户编号查询全部记录中是否存在已启用的规则
	 */
	@RequestMapping(value="ifczyqybyshbh")
	@ResponseBody
	public String ifczyqyByShbh(){
		String status = "1";
		try {
			Shyhb user = SessionUtils.getShshbSession();
			//1 启用
			int count = this.baseService.getMyBatisDao().getCountByShbhAndZt(user.getShbh(), "1");
			if(count > 0){
				//存在已启用
				status = "0";
			}
		} catch (Exception e) {
			logger.error("根据商户编号查询全部记录中是否存在已启用的规则", e);
			status = "1";
		}
		return status;
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
	    		JpZdcpGjGz jpzdcpgjgz=this.baseService.getMyBatisDao().selectByPrimaryKey(gid);
	    		jpzdcpgjgz.setShbh(yhb.getShbh());
	    		Integer yxj=jpzdcpgjgz.getYxj();
		    
	    		if("1".equals(bj)){ //上移一个	
	    			JpZdcpGjGz jpZdcpgzQ=this.baseService.getMyBatisDao().selectByPrimaryKey(qygId);
	    			jpZdcpgzQ.setShbh(yhb.getShbh());
	    			Integer yxjQ=jpZdcpgzQ.getYxj();
	    			
	    			JpZdcpGjGz t1 = new JpZdcpGjGz();
	    			t1.setId(gid);
	    			t1.setYxj(yxjQ);
	    			this.baseService.updateSelective(t1);
	    			
	    			JpZdcpGjGz t2 = new JpZdcpGjGz();
	    			t2.setId(qygId);
	    			t2.setYxj(yxj);
	    			this.baseService.updateSelective(t2);
	    		}else if("3".equals(bj)){ //下移一个
	    			JpZdcpGjGz jpZdcpgzH=this.baseService.getMyBatisDao().selectByPrimaryKey(hygId);
	    			jpZdcpgzH.setShbh(yhb.getShbh());
	    			Integer yxjH=jpZdcpgzH.getYxj();
	    			
	    			JpZdcpGjGz t1 = new JpZdcpGjGz();
	    			t1.setId(gid);
	    			t1.setYxj(yxjH);
	    			this.baseService.updateSelective(t1);
	    			
	    			JpZdcpGjGz t2 = new JpZdcpGjGz();
	    			t2.setId(hygId);
	    			t2.setYxj(yxj);
	    			this.baseService.updateSelective(t2);	
	    		}else if("2".equals(bj)){ //置顶
	    			if(yxj<dygYxj){
	    				JpZdcpGjGz t = new JpZdcpGjGz();
	    				t.setId(gid);
	    				t.setYxj(dygYxj+1);
	    				this.baseService.updateSelective(t);
	    			}
	    		}else if("4".equals(bj)){
	    			if(yxj>zhgYxj){//置底
	    				JpZdcpGjGz t = new JpZdcpGjGz();
	    				t.setId(gid);
	    				t.setYxj(zhgYxj-1);
	    				this.baseService.updateSelective(t);
	    			}
	    		}
	    	}
	    	return "0";
			} catch (Exception e) {
				logger.error("操作失败 ", e);
				return "-1";
			}
	}
	/**
	 * 根据规则编号获取操作日志
	 * @param gzbh
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "enterLogPage_{gzbh}")
	public String enterLogPage(@PathVariable("gzbh") String gzbh,ModelMap model){
		Shyhb user =SessionUtils.getShshbSession();
		String shbh=user.getShbh();
		JpZdcpgjgzCzrz czrz = new JpZdcpgjgzCzrz();
		czrz.setCzshbh(shbh);
		if(StringUtils.isNotBlank(gzbh)){
			czrz.setGzbh(gzbh);
		}
		try {
			List<JpZdcpgjgzCzrz> list = jpZdcpgjgzCzrzServiceImpl.queryList(czrz);
			attachService.shyhb("czyhbh",shbh).execute(list);
			model.addAttribute("gzbh", gzbh);
			model.addAttribute("logList", list);
		} catch (Exception e) {
			logger.error("根据规则编号获取操作日志失败"+e);
			e.printStackTrace();
		}
		return viewpath("cpgjgzczydrz");
	}
}
