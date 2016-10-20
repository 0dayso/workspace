package cn.vetech.web.vedsb.pzzx;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cn.vetech.vedsb.jp.entity.pzzx.JpPzKc;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzOut;
import cn.vetech.vedsb.jp.service.pzzx.JpPzKcServiceImpl;
import cn.vetech.vedsb.jp.service.pzzx.JpPzOutServiceImpl;
import cn.vetech.vedsb.jp.service.pzzx.JpXcdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class PzffController extends MBaseControl<JpPzOut, JpPzOutServiceImpl>{
	@Autowired
	private JpPzOutServiceImpl jpPzOutServiceImpl;
	@Autowired
	private JpPzKcServiceImpl jpPzKcServiceImpl;
	@Autowired
	private JpXcdServiceImpl jpXcdServiceImpl;
	/**
	 * 查询票证发放表中的信息
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条目数
	 * @param out_datetime 发放日始
	 * @param ffrz 发放日止
	 * @param model
	 * @return Page对象
	 */
	@RequestMapping(value = "pzffcx")
	public @ResponseBody Page getList(@ModelAttribute()JpPzOut jpPzOut,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "out_datetime", defaultValue = "") String out_datetime,
			@RequestParam(value = "ffrz", defaultValue = "") String ffrz,
			ModelMap model){
			Date out_datetime2 = null;
			Date ffrz2 = null;
			if(StringUtils.isNotBlank(out_datetime)){
				out_datetime2 = VeDate.formatToDate(out_datetime, "yyyy-MM-dd");
			}
			if(StringUtils.isNotBlank(ffrz)){
				ffrz2 = VeDate.formatToDate(ffrz, "yyyy-MM-dd");
				ffrz2 = VeDate.getPreDay(ffrz2, 1);
			}
			//获取用户登录信息
			Shyhb shyhb = SessionUtils.getShshbSession();
			jpPzOut.setShbh(shyhb.getShbh());
			Page page = null;
			try {
				page = this.baseService.getListByAll(out_datetime2,ffrz2,pageNum, pageSize,jpPzOut);
			} catch (Exception e) {
				logger.error("查询票证发放表出错:", e.getMessage());
			}
			return page;
	}
	
	/**
	 * 从库存表中查询可以发放的记录
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的条目数
	 * @param model
	 * @return Page对象
	 */
	@RequestMapping(value = "queryFromKC")
	public @ResponseBody Page queryFromKC(@ModelAttribute()JpPzKc jpPzKc,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, 
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			ModelMap model){
		//获取用户登录信息
		Shyhb shyhb = SessionUtils.getShshbSession();
		jpPzKc.setShbh(shyhb.getShbh());
		Page page = null;
		try {
			page = jpPzKcServiceImpl.queryFromKC(pageNum, pageSize,jpPzKc);
		} catch (Exception e) {
			logger.error("查询库存表中可发放的记录出错:", e.getMessage());
		}
		return page;
	}

	/**
	 * 保存已发放票证信息到jp_pz_out表中
	 * @param startno 起始码
	 * @param endno 终止码
	 * @param ffsl 数量
	 * @param bmbh 部门编号
	 * @param id 库存id
	 * @param bzbz 备注
	 */
	@RequestMapping(value = "saveToJpPzOut",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> saveToJpPzOut(
			@RequestParam(value = "startno")String[] startno,
			@RequestParam(value = "endno")String[] endno,
			@RequestParam(value = "ffsl")int[] ffsl,
			@RequestParam(value = "bmbh")String bmbh,
			@RequestParam(value = "officeid")String[] officeid,
			@RequestParam(value = "pztype")String[] pztype,
			@RequestParam(value = "id")String[] id,
			@RequestParam(value="bzbz")String bzbz){
			Map<String,Object> map = new HashMap<String,Object>();
			try {
				//获取用户登录信息
				Shyhb shyhb = SessionUtils.getShshbSession();
				//根据startno和endno判断jp_xcd表中是否存在已使用的改号段，即pzzt!=0(在库)或pzzt!=1(未使用)
				int count2 = jpXcdServiceImpl.queryJpXcdByNo(startno,endno);
				if(count2>0){
					map.put("msg", "您发放的行程单号有已经使用的了，请刷新页面重新发放。");
					map.put("code","2");
					return map;
				}
				List<JpPzOut> list = new ArrayList<JpPzOut>();
				for (int i = 0; i < startno.length; i++) {
					JpPzOut jpPzOut = new JpPzOut();
					jpPzOut.setOutNo(VeDate.getNo(6));
					jpPzOut.setOutDatetime(VeDate.getNow());
					jpPzOut.setYhbh(shyhb.getBh());
					jpPzOut.setShbh(shyhb.getShbh());
					jpPzOut.setBmbh(bmbh);
					jpPzOut.setLyYhbh(shyhb.getBh());
					jpPzOut.setLyBmbh(shyhb.getShbmid());
					jpPzOut.setStartno(startno[i]);
					jpPzOut.setEndno(endno[i]);
					jpPzOut.setBzbz(bzbz);
					jpPzOut.setPzfl(pztype[i]);
					jpPzOut.setFfsl(ffsl[i]);
					jpPzOut.setOfficeid(officeid[i]);
					list.add(jpPzOut);
				}
				//批量插入记录到票证发放表
				jpPzOutServiceImpl.batchInsertJpPzOut(list);
				map.put("msg", "发放成功");
				map.put("code","1");
				//从jp_pz_kc表中发放数据到jp_pz_out表中后，要把kc表中的数据的状态改为发放，0:入库，2:发放,7:报废
			} catch (Exception e) {
				logger.error("批量发放失败", e.getMessage());
				map.put("msg", "发放失败");
				map.put("code","2");
			}
			return map;
	}

	/**
	 * 根据id更改jp_pz_kc表中的pzzt，0:入库，1:未使用,9:报废
	 * @param id 库存表中的id
	 */
	public void updatePzztById(String id){
		try {
			jpPzKcServiceImpl.updatePzztById(id);
		} catch (Exception e) {
			logger.error("根据id修改库存表状态失败", e.getMessage());
		}
	}
	
	@Override
	public void updateInitEntity(JpPzOut t) {
	}

	@Override
	public void insertInitEntity(JpPzOut t) {
	}
}