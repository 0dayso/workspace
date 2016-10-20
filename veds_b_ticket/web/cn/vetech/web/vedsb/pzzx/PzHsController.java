package cn.vetech.web.vedsb.pzzx;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.mybatis.page.Page;
import org.vetech.core.modules.web.MBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.pzzx.JpXcd;
import cn.vetech.vedsb.jp.service.pzzx.JpPzPzJzServiceImpl;
import cn.vetech.vedsb.jp.service.pzzx.JpXcdServiceImpl;
import cn.vetech.web.vedsb.SessionUtils;
@Controller
public class PzHsController extends MBaseControl<JpXcd, JpXcdServiceImpl>{
	@Autowired
	private JpPzPzJzServiceImpl jpPzPzJzServiceImpl;
	
	/**
	 * 查询报废列表
	 * @param ksrq 日始
	 * @param jsrq 日止
	 * @param xcdNo 行程单号
	 * @param pztype 票证类型
	 * @param pzzt 状态
	 * @param bmbh 部门编号
	 * @param pageNum 当前页码
	 * @param pageSize 每页显示条目数
	 * @return
	 */
	@RequestMapping(value = "pzhs_list")
	@ResponseBody
	public Page getListByBfCX(@DateTimeFormat(pattern="yyyy-MM-dd") Date ksrq,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date jsrq,
			@RequestParam(value ="bmbh",defaultValue = "")String bmbh,
			@RequestParam(value ="xcdNo",defaultValue = "")String xcdNo,
			@RequestParam(value ="pzzt",defaultValue = "")String pzzt,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "pztype", defaultValue = "") String pztype,
			@RequestParam(value = "rqtj", defaultValue = "") String rqtj,
			@RequestParam(value = "tkNo", defaultValue = "") String tkNo){
		//获取用户登录信息
		Shyhb shyhb = SessionUtils.getShshbSession();
		try {
			return this.baseService.getListByAll(shyhb.getShbh(), bmbh, ksrq, jsrq, xcdNo, pztype, pzzt,rqtj,tkNo, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("查询行程单列表信息失败"+e.getMessage());
		}
		return null;	
	}
	
	@RequestMapping(value = "hs")
	@ResponseBody
	public Map<String,Object> xcdHs(@RequestParam(value ="pzzt_arr",defaultValue = "")String pzzt_arr,
			@RequestParam(value ="xcdNo_arr",defaultValue = "")String xcdNo_arr,
			@RequestParam(value ="pztype_arr",defaultValue = "")String pztype_arr){
		//获取用户登录信息
		Shyhb shyhb = SessionUtils.getShshbSession();
		Map<String,Object> ddMap=new HashMap<String,Object>();
		try {
			//批量更新
			this.baseService.batchUpdate(shyhb, pzzt_arr, xcdNo_arr);
			//记录日志
			jpPzPzJzServiceImpl.batchInsert(shyhb, pzzt_arr, xcdNo_arr, pztype_arr);
			ddMap.put("CODE", "0");
		} catch (Exception e) {
			logger.error("批量回收/取消回收失败", e);
			ddMap.put("CODE", "-1");
			ddMap.put("MSG", "批量操作失败，请稍后重试！");
		}
		
		return ddMap;
	}
	
	@Override
	public void updateInitEntity(JpXcd t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insertInitEntity(JpXcd t) {
		// TODO Auto-generated method stub
		
	}
}
