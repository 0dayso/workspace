package cn.vetech.web.vedsb.pzzx;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import cn.vetech.vedsb.jp.entity.pzzx.JpPzBf;
import cn.vetech.vedsb.jp.service.pzzx.JpPzBfServiceImpl;
import cn.vetech.vedsb.jp.service.pzzx.JpPzKcServiceImpl;
import cn.vetech.vedsb.jp.service.pzzx.JpXcdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class PzBfController extends MBaseControl<JpPzBf, JpPzBfServiceImpl>{
	
	
	@Autowired
	private JpPzKcServiceImpl jpPzKcServiceImpl;
	@Autowired
	private JpXcdServiceImpl jpXcdServiceImpl;
	/**
	 * 更新是否审核状态
	 * @param bfNo 报废ID
	 * @param sfsh 是否审核
	 * @return Map
	 */
	@RequestMapping(value = "update_sfsh")
	@ResponseBody
	public Map<String,String> updateSfSh(@RequestParam(value ="bfNo",defaultValue = "")String bfNo,@RequestParam(value ="sfsh",defaultValue = "")String sfsh){
		Map<String,String> map=new HashMap<String,String>();
		//判断bfNo和sfsh是否为空
		if(StringUtils.isNotBlank(bfNo) || StringUtils.isNotBlank(sfsh)){
			try {
				this.baseService.updateSfshBybfNo(bfNo, sfsh);
				map.put("CODE", "0");
			} catch (Exception e) {
				logger.error("票证修改状态失败", e);
				map.put("CODE", "-1");
				map.put("MSG", "修改状态失败，请稍后重试！");
			}
		}else{
			map.put("CODE", "-1");
			map.put("MSG", "无法获取报废ID和状态!");
		}
		return map;
	}
	/**
	 * 查询报废列表
	 * @param ksrq 日始
	 * @param jsrq 日止
	 * @param startno 起始码
	 * @param endno 终止码
	 * @param pageNum 当前页码
	 * @param pageSize 每页显示条目数
	 * @return
	 */
	@RequestMapping(value = "bf_list")
	@ResponseBody
	public Page getListByBfCX(@DateTimeFormat(pattern="yyyy-MM-dd") Date ksrq,@DateTimeFormat(pattern="yyyy-MM-dd")Date jsrq,
			@RequestParam(value ="startno",defaultValue = "")String startno,
			@RequestParam(value ="endno",defaultValue = "")String endno,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "pztype", defaultValue = "") String pztype){
		//获取用户登录信息
		Shyhb shyhb = SessionUtils.getShshbSession();
		Page page=null;
		try {
			page= this.baseService.getListByAll(shyhb.getShbh(), ksrq, jsrq, startno, endno,pztype, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("查询报废信息失败"+e.getMessage());
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
	@RequestMapping(value = "bf_xcd_add")
	public @ResponseBody Page getListByBfXcd(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			ModelMap model,HttpServletRequest request){
		//获取用户登录信息
		Shyhb shyhb = SessionUtils.getShshbSession();
		Page page=null;
		try {
			page=jpPzKcServiceImpl.getKcBfList(shyhb.getShbh(),pageNum, pageSize,request);
		} catch (Exception e) {
			logger.error("查询库存中信息失败"+e.getMessage());
		}
		return page;
	}
	
	/**
	 * 保存报废信息
	 * @param startno 起始码
	 * @param endno 终止码
	 * @param sysl 数量
	 * @param pztype 票证类型
	 * @return 异步请求返回一个JSON结果
	 */
	@RequestMapping(value = "save_list_jpPzbf")
	@ResponseBody
	public Map<String, Object> saveList_JpPzBf(@RequestParam(value = "startno")String[] startno,
			@RequestParam(value = "endno")String[] endno,
			@RequestParam(value="pztype")String[] pztype,
			@RequestParam(value="officeid")String[] officeid,
			@RequestParam(value="bzbz")String bzbz){
		//获取用户登录信息
		Shyhb shyhb = SessionUtils.getShshbSession();
		
		Map<String, Object> ddMap = new HashMap<String, Object>();
		List<JpPzBf> list = new ArrayList<JpPzBf>();
		boolean flag=true;//标识行程单是否使用   false未使用   true已使用
		for (int i = 0; i < startno.length; i++) {
			try {
				//检查行程单是否使用
				flag=jpXcdServiceImpl.xcdIfSy(shyhb.getShbh(), startno[i], endno[i]);
			} catch (Exception e) {
				logger.error("票证检查是否使用出错", e.getMessage());
			}
			//行程单已使用则不进行下面赋值操作
			if(flag){
				ddMap.put("CODE", "-2");
				ddMap.put("MSG", "存在已使用的号段，请重新选择！");
				break;
			}
			JpPzBf bf = new JpPzBf();
			bf.setBfNo(VeDate.getNo(6));
			bf.setBfDatetime(VeDate.getNow());
			bf.setYhbh(shyhb.getBh());
			bf.setBmbh(shyhb.getShbmid());
			bf.setShbh(shyhb.getShbh());
			bf.setPztype(pztype[i]);
			bf.setOfficeid(officeid[i]);
			bf.setStartno(startno[i]);
			bf.setEndno(endno[i]);
			bf.setSfsh("0");//默认0表示未审核sfsh
			bf.setBzbz(bzbz);
			list.add(bf);
		}
		try{
			//行程单未使用则进行退回
			if(!flag){
				//插入
				this.baseService.batchInsertJpPzBf(list);
				ddMap.put("CODE", "0");
			}
		}catch(Exception e){
			logger.error("票证报废保存失败", e);
			ddMap.put("CODE", "-1");
			ddMap.put("MSG", "保存失败，请稍后重试！");
		}
		return ddMap;
	}
	@Override
	public void updateInitEntity(JpPzBf t) {
		
	}
	@Override
	public void insertInitEntity(JpPzBf t) {
		
	}
}
