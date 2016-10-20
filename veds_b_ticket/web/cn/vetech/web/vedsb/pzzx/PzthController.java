package cn.vetech.web.vedsb.pzzx;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzTh;
import cn.vetech.vedsb.jp.service.pzzx.JpPzKcServiceImpl;
import cn.vetech.vedsb.jp.service.pzzx.JpPzThServiceImpl;
import cn.vetech.vedsb.jp.service.pzzx.JpXcdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class PzthController extends MBaseControl<JpPzTh, JpPzThServiceImpl> {
	@Autowired
	private JpPzKcServiceImpl jpPzKcServiceImpl;
	@Autowired
	private JpXcdServiceImpl jpXcdServiceImpl;
	
	/**
	 * 查询退回列表
	 * @param ksrq 日始
	 * @param jsrq 日止
	 * @param bmbh 部门编号
	 * @param startno 起始码
	 * @param endno 终止码
	 * @param pageNum 当前页码
	 * @param pageSize 每页显示的条目数
	 * @return Page
	 */
	@RequestMapping(value = "th_list")
	@ResponseBody
	public Page getListByThCX(@DateTimeFormat(pattern="yyyy-MM-dd") Date ksrq,@DateTimeFormat(pattern="yyyy-MM-dd")Date jsrq,
			@RequestParam(value ="bmbh",defaultValue = "")String bmbh,@RequestParam(value ="startno",defaultValue = "")String startno,
			@RequestParam(value ="endno",defaultValue = "")String endno,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "pztype", defaultValue = "") String pztype){
		//获取用户登录信息
		Shyhb shyhb = SessionUtils.getShshbSession();
		Page page=null;
		try {
			page= this.baseService.getListByAll(shyhb.getShbh(), bmbh, ksrq, jsrq, startno, endno,pztype, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("查询退回列表失败"+e.getMessage());
		}
		return page;
	}

	/**
	 * 根据商户的编号查询已发放的票证信息
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条目数
	 * @param model
	 * @return Page对象
	 */
	@RequestMapping(value = "th_xcd_add")
	public @ResponseBody Page getListByYhbh(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			HttpServletRequest request,ModelMap model){
		//获取用户登录信息
		Shyhb shyhb = SessionUtils.getShshbSession();
		Page page=null;
		try {
			page=jpPzKcServiceImpl.getKcThList(shyhb.getShbh(), pageNum, pageSize,request);
		} catch (Exception e) {
			logger.error("查询已发放的票证信息失败"+e.getMessage());
		}
		return page;
	}
	/**
	 * 保存退票信息
	 * @param startno 起始码
	 * @param endno 终止码
	 * @param sysl 数量
	 * @param pztype 票证类型
	 * @param bzbz 备注
	 * @return 异步请求返回一个JSON结果
	 */
	@RequestMapping(value = "save_list_jpPzth")
	@ResponseBody
	public Map<String, Object> saveList_JpPzTh(@RequestParam(value = "startno")String[] startno,
			@RequestParam(value = "endno")String[] endno,
			@RequestParam(value = "sysl")Long[] sysl,
			@RequestParam(value="pztype")String[] pztype,
			@RequestParam(value="officeid")String[] officeid,
			@RequestParam(value="bzbz")String bzbz) {
		//获取用户登录信息
		Shyhb shyhb = SessionUtils.getShshbSession();
		
		Map<String, Object> ddMap = new HashMap<String, Object>();
		
			List<JpPzTh> list = new ArrayList<JpPzTh>();
			//标识行程单是否使用   false未使用   true已使用
			boolean flag=false;
			for (int i = 0; i < startno.length; i++) {
				try {
					//检查行程单是否使用
					flag=jpXcdServiceImpl.xcdIfSy(shyhb.getShbh(), startno[i], endno[i]);
				} catch (Exception e) {
				    flag=true;
					logger.error("票证检查是否使用出错", e.getMessage());
				}
				//行程单已使用则不进行下面赋值操作
				if(flag){
					ddMap.put("CODE", "-2");
					ddMap.put("MSG", "存在已使用的号段，请重新选择！");
					break;
				}
				JpPzTh th = new JpPzTh();
				//设值
				th.setThNo(VeDate.getNo(6));
				th.setThDatetime(VeDate.getNow());
				th.setYhbh(shyhb.getBh());
				th.setBmbh(shyhb.getShbmid());
				th.setShbh(shyhb.getShbh());
				th.setPztype(pztype[i]);
				th.setStartno(startno[i]);
				th.setEndno(endno[i]);
				th.setShsl(sysl[i]);
				th.setOfficeid(officeid[i]);
				th.setBzbz(bzbz);
				list.add(th);
			}
			try{
				//行程单未使用则进行退回
				if(!flag){
					//批量插入
					this.baseService.batchInsertJpPzTh(list);
					ddMap.put("CODE", "0");
				}
			}catch(Exception e){
				logger.error("票证退回保存失败", e.getMessage());
				ddMap.put("CODE", "-1");
				ddMap.put("MSG", "保存失败，请稍后重试！");
			}
		return ddMap;
	}

	@Override
	public void updateInitEntity(JpPzTh t) {
		
	}

	@Override
	public void insertInitEntity(JpPzTh t) {
		
	}

}
