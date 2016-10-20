package cn.vetech.web.vedsb.pzzx;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzIn;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzKc;
import cn.vetech.vedsb.jp.entity.pzzx.JpXcd;
import cn.vetech.vedsb.jp.service.pzzx.JpPzInServiceImpl;
import cn.vetech.vedsb.jp.service.pzzx.JpPzKcServiceImpl;
import cn.vetech.vedsb.jp.service.pzzx.JpPzPzJzServiceImpl;
import cn.vetech.vedsb.jp.service.pzzx.JpXcdServiceImpl;
import cn.vetech.vedsb.utils.DictEnum.PZZZCZLX;
import cn.vetech.vedsb.utils.DictEnum.XCDZT;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class PzrkController extends MBaseControl<JpPzIn, JpPzInServiceImpl>{

	@Autowired
	private JpPzKcServiceImpl jpPzKcServiceImpl;
	@Autowired
	private JpXcdServiceImpl jpXcdServiceImpl;
	@Autowired
	private JpPzPzJzServiceImpl jpPzPzJzServiceImpl;
	
	/**
	 * 保存票证
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "saveJpPzIn",method = RequestMethod.POST)
	public String saveJpPzIn(@ModelAttribute()JpPzIn jpPzIn, HttpServletRequest request, ModelMap model) throws Exception {
		try {
			jpPzIn.setInNo(VeDate.getNo(6));
			//设置入库时间
			jpPzIn.setInDatetime(VeDate.getNow());
			//是否审核 0为未审核
			jpPzIn.setSfsh("0");
			//发放数量
			jpPzIn.setFfsl(0L);
			if(StringUtils.isBlank(jpPzIn.getRksl().toString())){
				//入库数量，默认值0
				jpPzIn.setRksl(0L);
			}
			this.baseService.insert(jpPzIn);
		} catch (Exception e) {
			logger.error("票证入库错误:", e.getMessage());
		}
		return redirectPath("/vedsb/pzzx/pzrk/viewpzrkcx?title=2");
	}

	/**
	 * 更改票证的状态为已审核  0未审核，1已审核，2已作废
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "changeSH")
	public String changeSH(HttpServletRequest request,
			@RequestParam(value = "inNo", defaultValue = "") String inNo,
			@RequestParam(value = "sfsh2", defaultValue = "") String sfsh2,
			ModelMap model) throws Exception {
		try {
			Shyhb user = SessionUtils.getShshbSession();
			if("0".equals(sfsh2)){//未审核
				sfsh2 = "1";
				Date sh_datetime = VeDate.getNow();
				JpPzIn jpPzIn = new JpPzIn();
				jpPzIn.setInNo(inNo);
				jpPzIn.setSfsh(sfsh2);
				jpPzIn.setShYhbh(user.getBh());
				jpPzIn.setShBmbh(user.getShbmid());
				jpPzIn.setShDatetime(sh_datetime);
				jpPzIn.setShbh(user.getShbh());
				this.baseService.updateByInNo(jpPzIn);
			}
		} catch (Exception e) {
			logger.error("票证审核错误：", e.getMessage());
		}
		return redirectPath("/vedsb/pzzx/pzrk/viewpzrkcx?title=2");
	}
	
	/**
	 * 更改票证的状态为未审核  0未审核，1已审核，2已作废：1变为0
	 * @param inNo  主键:入库号
	 * @param sfsh2  主键:是否审核
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "changeFSH")
	public String changeFSH(HttpServletRequest request,
			@RequestParam(value = "inNo", defaultValue = "") String inNo,
			@RequestParam(value = "sfsh2", defaultValue = "") String sfsh2,
			@RequestParam(value = "startno", defaultValue = "") String startno,
			@RequestParam(value = "endno", defaultValue = "") String endno,
			ModelMap model) throws Exception {
		//根据startno和endno查询行程单表中是否存在pzzt！=0的记录，如果存在不等于0的记录，说明该号段的票证已经开始使用，不能进行反审核；
		String pzzt2 = "0";
		int count = 0;
		Shyhb user = SessionUtils.getShshbSession();
		String msg = "1";//返回页面，弹出对话框，提示不能进行反审核
		String path="/vedsb/pzzx/pzrk/viewpzrkcx?title=2&msg="+msg;
		try {
			count = jpXcdServiceImpl.queryJpXcdByStartnoEndnoPzzt(startno,endno,pzzt2,user.getShbh());
		} catch (Exception e1) {
			logger.error("查询行程单表中票证是否使用出错：", e1.getMessage());
		}
		
		if(count != 0 ){
			return redirectPath(path);
		}

		try {
				if("1".equals(sfsh2)){//已审核
					sfsh2 = "0";
					//将jp_pz_in表中的状态从已审核改为未审核
					this.baseService.updateByInNo2(inNo,sfsh2,user.getShbh());
					//将jp_pz_kc表中的已经审核过的记录删除,根据inNo和startno和endno删除该条记录
					JpPzKc jpPzKc = new JpPzKc();
					jpPzKc.setInNo(inNo);
					jpPzKc.setStartno(startno);
					jpPzKc.setEndno(endno);
					//根据startno和endno删除jp_pz_kc表中对应的号段记录
					deleteJpPzKcByInNo(jpPzKc);
					//删除jp_xcd表中对应的号段记录并且判断条件为pzzt=0
					String pzzt = "0";
					jpXcdServiceImpl.deleteJpXcdByInNoAndPzzt(pzzt,startno,endno,user.getShbh());
					//记录日志
					jpPzPzJzServiceImpl.pzrkrz(PZZZCZLX.QXRK.toString(),"720201",startno,endno,user);//9.取消入库   720201 机票
					msg = "2";
				}
			} catch (Exception e) {
				logger.error("票证反审核错误：", e.getMessage());
		}
		return redirectPath(path);
	}
	
	/**
	 * 根据InNo删除jpPzKc表中的该条记录
	 */
	public void deleteJpPzKcByInNo(JpPzKc jpPzKc){
		try {
			jpPzKcServiceImpl.deleteByInNo(jpPzKc);
		} catch (Exception e) {
			logger.error("根据InNo删除库存表记录错误：", e.getMessage());
		}
	}
	
	/**
	 * 更改票证的状态为作废  0未审核，1已审核，2已作废
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "changeZF")
	public String changeZF(HttpServletRequest request,
			@RequestParam(value = "inNo", defaultValue = "") String inNo,
			@RequestParam(value = "sfsh2", defaultValue = "") String sfsh2,
			@RequestParam(value = "startno", defaultValue = "") String startno,
			@RequestParam(value = "endno", defaultValue = "") String endno,
			ModelMap model) throws Exception {
		try {
			Shyhb user = SessionUtils.getShshbSession();
			if("0".equals(sfsh2)){//未审核
				sfsh2 = "2";
				this.baseService.updateByInNo2(inNo,sfsh2,user.getShbh());
				jpXcdServiceImpl.deleteJpXcdByInNoAndPzzt(XCDZT.WFF.toString(),startno,endno,user.getShbh());
			}
		} catch (Exception e) {
			logger.error("票证作废错误：", e.getMessage());
		}
		return redirectPath("/vedsb/pzzx/pzrk/viewpzrkcx?title=2");
	}
	
	/**
	 * 根据商户的编号,入库时间，入库日止，起始号码，终止号码，页码大小，当前页码，是否审核查询票证信息
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条目数
	 * @param in_datetime 入库时间
	 * @param rkrz 入库日止
	 * @param model
	 * @return Page对象
	 */
	@RequestMapping(value = "pzrkcx")
	public @ResponseBody Page getListByYhbh(@ModelAttribute()JpPzIn jpPzIn,HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = "2") int pageSize,
			@RequestParam(value = "in_datetime", defaultValue = "") String in_datetime,
			@RequestParam(value = "rkrz", defaultValue = "") String rkrz,
			ModelMap model){
		Date in_datetime2 = null;
		Date rkrz2 = null;
		//查询时入库日期不为空，转换为Date类型
		if(StringUtils.isNotBlank(in_datetime)){
			in_datetime2 = VeDate.formatToDate(in_datetime, "yyyy-MM-dd");
		}
		if(StringUtils.isNotBlank(rkrz)){
			rkrz2 = VeDate.formatToDate(rkrz, "yyyy-MM-dd");
			//将得到的入库日止的日期的后面一天
			rkrz2 = VeDate.getPreDay(rkrz2, 1);
		}
		//获取用户登录信息
		Shyhb shyhb = SessionUtils.getShshbSession();
		jpPzIn.setShbh(shyhb.getShbh());
		Page page = null;
		try {
			page = this.baseService.getListByAll(in_datetime2, rkrz2, pageNum, pageSize,jpPzIn);
		} catch (Exception e) {
			logger.error("查询入库票证信息出错：", e.getMessage());
		}
		return page;
	}

	/**
	 * 校验startno和endno是否在jp_Xcd表中已经存在
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "validateNo")
	public @ResponseBody Map<String,String> validateNo(HttpServletRequest request,
			@RequestParam(value = "startno", defaultValue = "") String startno,
			@RequestParam(value = "endno", defaultValue = "") String endno,
			ModelMap model) throws Exception {
			List<JpXcd> list = null;
			try {
				list = jpXcdServiceImpl.validateNo(startno,endno);
			} catch (Exception e) {
				logger.error("校验入库号段是否存在出错：", e.getMessage());
			}
			Map<String,String> map = new HashMap<String,String>();
			map.put("msg", CollectionUtils.isNotEmpty(list) ? "false" : "true");
			return map;
	}
	
	
	
	@Override
	public void updateInitEntity(JpPzIn t) {
		
	}
	
	@Override
	public void insertInitEntity(JpPzIn t) {
		
	}
}