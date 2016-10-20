package cn.vetech.web.vedsb.qxxgl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.web.MBaseControl;
import org.vetech.core.modules.web.Servlets;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.SmsFsdl;
import cn.vetech.vedsb.common.entity.base.SmsMbSh;
import cn.vetech.vedsb.common.service.base.SmsServiceImpl;
import cn.vetech.vedsb.jp.entity.qxxgl.BQinfo;
import cn.vetech.vedsb.jp.entity.qxxgl.BQinfoRz;
import cn.vetech.vedsb.jp.service.attach.AttachService;
import cn.vetech.vedsb.jp.service.qxxgl.BQinfoRzServiceImpl;
import cn.vetech.vedsb.jp.service.qxxgl.BQinfoServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class BQinfoController extends MBaseControl<BQinfo, BQinfoServiceImpl>{
	@Autowired
	private SmsServiceImpl smsServiceImpl;
	@Autowired
	private BQinfoRzServiceImpl bQinfoRzServiceImpl;
	@Autowired
	private AttachService attachService;
	
	/**
	 * 根据条件查询
	 * @param request
	 * @param qtypes
	 * @return
	 */
	@RequestMapping(value="query")
	@ResponseBody
	public Page query(HttpServletRequest request,String[] qtypes){
		Map<String, Object> params = Servlets.getParametersStartingWith(request, "",false);
		int pageNum = NumberUtils.toInt(VeStr.getValue(params, "pageNum"));
		int pageSize = NumberUtils.toInt(VeStr.getValue(params, "pageSize"));
		Shyhb user = SessionUtils.getShshbSession();
		params.put("shbh", user.getShbh());
		params.put("qtypes", qtypes);
		List<Map<String,Object>> list=this.baseService.getMyBatisDao().query(params);
		int count=this.baseService.getMyBatisDao().queryCount(params);
		Page page=new Page(pageNum,pageSize);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	/**
	 * 手动批量处理
	 * @param ids
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="sdplcl")
	@ResponseBody
	public Map<String,Object> sdplcl(String ids,HttpServletRequest request,ModelMap model){
		Map<String,Object> param = new HashMap<String,Object>();
		Shyhb user = SessionUtils.getShshbSession();
		try {
			Map<String,Object> infoMap=new HashMap<String,Object>();
			String[] BQinfoIds=ids.split(",");
			infoMap.put("ids", BQinfoIds);
			infoMap.put("shbh", user.getShbh());
			infoMap.put("clzt", "1");//已完成
			infoMap.put("clqk", "手动批量处理");
			infoMap.put("cluserid", user.getBh());
			infoMap.put("cldatetime", VeDate.getNow());
			//更新BQinfo表中的信息
			this.baseService.getMyBatisDao().updateBatchBQinfo(infoMap);
			for(String BQinfoId : BQinfoIds){
				//记录处理日志
				jlrz(BQinfoId,user,"手动批量处理","1");
			}
			param.put("CODE", "0");
			param.put("MSG", "手动处理成功");
		} catch (Exception e) {
			logger.error("手动处理出错"+e.getMessage());
			param.put("CODE", "-1");
			param.put("MSG", "手动处理出错");
		}
		
		return param;
	}
	
	@RequestMapping(value="toEdit")
	public String toEdit(String ids,HttpServletRequest request,ModelMap model){
		Shyhb user = SessionUtils.getShshbSession();
		List<SmsMbSh> smsList = smsServiceImpl.getMyBatisDao().querySmsmbByShbh(user.getShbh());
		model.addAttribute("smsList", smsList);
		if(StringUtils.isNotBlank(ids)){
			String[] bQinfoId = ids.split(",");
			for(int i=0;i<bQinfoId.length;i++){
				try {
					//记录日志  1 处理
					jlrz(bQinfoId[i],user,"锁定Q信息","1");
				} catch (Exception e) {
					logger.error("记录日志出错"+e.getMessage());
				}
			}
		}
		return viewpath("qinfo_edit");
	}
	/**
	 * 根据短信模板ID获取短信内容
	 * @return
	 */
	@RequestMapping(value="getSmsmbById")
	@ResponseBody
	public Map<String,Object> getSmsmbById(String id){
		Map<String,Object> param=new HashMap<String,Object>();
		try {
			Shyhb user = SessionUtils.getShshbSession();
			SmsMbSh sms = smsServiceImpl.getMyBatisDao().getSmsmbById(user.getShbh(), id);
			param.put("CODE", "0");
			param.put("DATA", sms);
		} catch (Exception e) {
			logger.error("根据短信模板ID获取短信内容出错"+e.getMessage());
			param.put("CODE", "-1");
			param.put("MSG", "根据短信模板ID获取短信内容出错");
		}
		return param;
	}
	/**
	 * 保存
	 * @return
	 */
	@RequestMapping(value="bqinfoSave",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> bqinfoSave(String ids,String clQk,String clZt,String clDatetime,
			String[] dtype,String dxlx,String fsnr,String mbbh){
		Map<String,Object> param=new HashMap<String,Object>();
		Shyhb user = SessionUtils.getShshbSession();
		String shbh = user.getShbh();
		String[] BQinfoIds=ids.split(",");
		
		StringBuffer dTypeStr = new StringBuffer();
		if(dtype==null){
			dTypeStr.append("无操作");
		}else{
			for(String one:dtype){
				if("1".equals(one)){
					dTypeStr.append("发短信给乘机人,");
				}
				if("2".equals(one)){
					dTypeStr.append("发短信给联系人,");
				}
				if("3".equals(one)){
					dTypeStr.append("电话通知,");
				}
			}
		}
		try {
			Date yqfssj= VeDate.formatToDate(clDatetime, "yyyy-MM-dd HH:mm:ss");
			Map<String,Object> infoMap=new HashMap<String,Object>();
			infoMap.put("ids", BQinfoIds);
			infoMap.put("shbh", shbh);
			infoMap.put("clzt", clZt);
			infoMap.put("clqk", clQk);
			infoMap.put("cluserid", user.getBh());
			infoMap.put("cldatetime", yqfssj);
			//更新BQinfo表中的信息
			this.baseService.getMyBatisDao().updateBatchBQinfo(infoMap);
			
			if(!(ArrayUtils.contains(dtype, "1") || ArrayUtils.contains(dtype, "2"))){ //发短信
				for(String BQinfoId : BQinfoIds){
					//记录处理日志
					if("1".equals(clZt)){//页面选择了处理完成
						jlrz(BQinfoId,user,dTypeStr.toString(),"2");
					}else{
						jlrz(BQinfoId,user,dTypeStr.toString(),"1");
					}
				}
				param.put("CODE", "0");
				param.put("MSG", "保存成功");
				return param;
			}
			
			//在发送清单中新建记录
			if(BQinfoIds != null && BQinfoIds.length > 0){
				for(String id:BQinfoIds){
					
					//记录处理日志
					if("1".equals(clZt)){//页面选择了处理完成
						jlrz(id,user,dTypeStr.toString(),"2");
					}else{
						jlrz(id,user,dTypeStr.toString(),"1");
					}
					
					//根据ID、商户编号获取对应的BQinfo信息
					BQinfo info=this.baseService.getMyBatisDao().getBeanByShbhAndId(shbh, id);
					if(info != null){
						SmsFsdl smsfsdl = new SmsFsdl();
						smsfsdl.setId(VeDate.getNo(6));
						smsfsdl.setFl("2");//分类1表示平台发送的2表示商户发送的
						smsfsdl.setFsCzr(user.getBh()+user.getXm());
						smsfsdl.setFsDw(shbh);
						smsfsdl.setFsnr(fsnr);
						smsfsdl.setMbbh(mbbh);
						smsfsdl.setJshm(info.getMsmobilno());
						smsfsdl.setJsShbh(info.getShbh());
						smsfsdl.setYqfssj(yqfssj);
						smsfsdl.setFsfs("1");//发送方式1自动发送0手动发送
						smsfsdl.setDxlx(dxlx);
						smsfsdl.setDdbh(info.getDdbh());
						smsfsdl.setPnrNo(info.getPnrno());
						smsfsdl.setCjsj(VeDate.getNow());
						//创建发送清单
						smsServiceImpl.getMyBatisDao().insert(smsfsdl);
					}
					
				}
			}
			param.put("CODE", "0");
			param.put("MSG", "保存成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Q信息处理失败"+e.getMessage());
			param.put("CODE", "-1");
			param.put("MSG", "Q信息处理失败");
		}
		return param;
	}
	/**
	 * 详
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="detail")
	public String detail(String id,ModelMap model,HttpServletRequest request){
		Shyhb user = SessionUtils.getShshbSession();
		BQinfo info=this.baseService.getMyBatisDao().getBeanByShbhAndId(user.getShbh(), id);
		if(info == null){
			
		}else{
			
			String pnr_hbh = StringUtils.isNotBlank(info.getPnrHbh()) ? info.getPnrHbh() : "";
			String pnr_hc = StringUtils.isNotBlank(info.getPnrHc()) ? info.getPnrHc(): "" ;
			String pnr_cfdatetime1 = StringUtils.isNotBlank(info.getPnrCfdatetime1()) ? info.getPnrCfdatetime1():"";
			String pnr_hdzt = StringUtils.isNotBlank(info.getPnrHdzt()) ? info.getPnrHdzt(): "";
			List<Map<String,Object>> exlist = new ArrayList<Map<String,Object>>();
			if(StringUtils.isNotBlank(pnr_hbh)){	//多段的情况
				if(pnr_hbh.indexOf(",") > -1){
					String [] hbhArr = pnr_hbh.split(",");
					String [] hcArr = pnr_hc.split(",");
					String [] cfdatetimeArr = pnr_cfdatetime1.split(",");
					String [] hdztArr = pnr_hdzt.split(",");
					for(int i=0;i < hbhArr.length; i++){
						Map<String,Object> exMap = new HashMap<String,Object>();
						
						exMap.put("PNR_HBH", hbhArr[i]);
						
						if(i< hcArr.length){
							exMap.put("PNR_HC", hcArr[i]);
						}
						
						if(i< cfdatetimeArr.length){
							exMap.put("PNR_CFDATETIME1", cfdatetimeArr[i]);
						}
						
						if(i< hdztArr.length){
							exMap.put("PNR_HDZT", hdztArr[i]);
						}
						exlist.add(exMap);
					}
				}else{
					Map<String,Object> exMap = new HashMap<String,Object>();
					exMap.put("PNR_HBH",pnr_hbh);
					exMap.put("PNR_HC", pnr_hc);
					exMap.put("PNR_CFDATETIME1", pnr_cfdatetime1);
					exMap.put("PNR_HDZT",pnr_hdzt);
					exlist.add(exMap);
				}
			}
			request.setAttribute("exlist", exlist);
			System.out.println(info.getContent());
			info.setContent(info.getContent().replace("\r\n", "<br/>"));
			info.setContent(info.getContent().replace("\n", "<br/>"));
			info.setContent(info.getContent().replace("\r", "<br/>"));
			
			request.setAttribute("b_qinfo", info);
		}
		model.addAttribute("info", info);
		try {
			//记录日志
			jlrz(id,user,"查看Q信息","0");
		} catch (Exception e) {
			logger.error("记录日志出错"+e.getMessage());
		}
		return viewpath("detail");
	}
	/**
	 * 查看日志
	 * @param id
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value="showLog")
	public String showLog(String id,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,ModelMap model){
		Shyhb user = SessionUtils.getShshbSession();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("shbh", user.getShbh());
		param.put("qinfobh", id);
		param.put("pageNum", pageNum);
		param.put("pageSize", pageSize);
		List<BQinfoRz> rzList = bQinfoRzServiceImpl.getMyBatisDao().queryByShbhAndId(param);
		attachService.shyhb("yhbh", user.getShbh()).execute(rzList);
		int count = bQinfoRzServiceImpl.getMyBatisDao().queryCount(user.getShbh(), id);
		Page page = new Page(pageNum,pageSize);
		page.setList(rzList);
		page.setTotalCount(count);
		model.addAttribute("page", page);
		return viewpath("showLog");
	}
	/**
	 * 记录日志
	 * @param BQinfoId
	 * @param user
	 * @param arrDtype
	 * @param type
	 * @throws Exception
	 */
	private void jlrz(String BQinfoId,Shyhb user,String arrDtype,String type) throws Exception {
		try {
			BQinfoRz rz = new BQinfoRz();
			rz.setId(VeDate.getNo(6));
			rz.setqInfoBh(BQinfoId);
			rz.setShbh(user.getShbh());
			rz.setBmbh(user.getShbmid());
			rz.setYhbh(user.getBh());
			rz.setCkDatetime(VeDate.getStringDate());
			rz.setType(type);
			rz.setBy1(arrDtype);
			bQinfoRzServiceImpl.insert(rz);
		} catch (Exception e) {
			logger.error("记录日志出错"+e.getMessage());
			throw new Exception("记录日志出错"+e.getMessage());
		}
	}

	@Override
	public void updateInitEntity(BQinfo t) {
	}

	@Override
	public void insertInitEntity(BQinfo t) {
	}

}
