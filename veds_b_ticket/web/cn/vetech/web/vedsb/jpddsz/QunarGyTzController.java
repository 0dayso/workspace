package cn.vetech.web.vedsb.jpddsz;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.MD5Tool;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.entity.jpddsz.OrderBean;
import cn.vetech.vedsb.jp.jpddsz.qunar.QunarGy;
import cn.vetech.vedsb.jp.service.jpddsz.JpDdszServiceImpl;
import cn.vetech.vedsb.jp.service.jpddsz.JpKhddHandleServiceImpl;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.PlatCode;

import com.alibaba.fastjson.JSONObject;

/**
 * 网店设置
 * 
 * @author wangfeng
 * 
 */
@Controller
public class QunarGyTzController extends AbstractBaseControl {
	@Autowired
	private JpDdszServiceImpl jdsImpl;
	@Autowired
	private JpKhddHandleServiceImpl jkhsImpl;
	/**
	 * 打开新增（编辑）页面
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "target{shbh}")
    @ResponseBody
	public String target(@PathVariable("shbh") String shbh, HttpServletRequest request, HttpServletResponse response){
		try {
			response.setContentType("application/x-download;charset=UTF-8");
			response.setHeader("content-disposition", "attachment;filename=123.zip");
			OutputStream out = response.getOutputStream();
			String result = "";
			//获取本地的安全码
			String strCode = "secCode=";
			// 接受通知内容
			ServletInputStream in = request.getInputStream();
			byte[] buf = new byte[4048];
			int len = in.readLine(buf, 0, buf.length); 
			String f = new String(buf, 0, len,"UTF-8");
			
			JSONObject object = JSONObject.parseObject(f);
			String version = object.getString("version");
			String notifyType = object.getString("notifyType");
			String sign = object.getString("sign");
			String objectStr = object.getString("data");
			JSONObject data = JSONObject.parseObject(objectStr);
			String changeCode = data.getString("changeCode");
			String orderNo = StringUtils.trimToEmpty(data.getString("orderNo"));
			String transactionId = data.getString("transactionId");
			String orderDesc = data.getString("orderDesc");
			String time = data.getString("time");
			
			
			
			JpDdsz record = new JpDdsz();
			record.setWdpt(PlatCode.QN);
			record.setShbh(shbh);
			record.setDdKqzcdd("1");
			List<JpDdsz> ddszList = jdsImpl.getMyBatisDao().select(record);
			JpDdsz jpDdsz = null;
			if(null!=ddszList && !ddszList.isEmpty()) {
				for(JpDdsz one : ddszList) {
					String aqm = one.getDdAqm1();
					//制作本地加密串
					String locaSign = MD5Tool.MD5Encode(objectStr+strCode+aqm);
					if(locaSign.equalsIgnoreCase(sign)) {
						jpDdsz = one;
						break;
					}
				}
			}
			if(jpDdsz==null){
				result = "{\"transactionId\":\""+transactionId+"\",\"result\":\"FAILED\",\"errMsg\":\"未找到对应的网店，签名验证失败\"}";
				return "";
			}
			JpPtLog ptlb = new JpPtLog(); 
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYDD.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
			ptlb.setPtzcbs(jpDdsz.getWdpt());
			ptlb.setCzsm(jpDdsz.getDdJkzh() + "供应订单状态变化通知");
			ptlb.setShbh(jpDdsz.getShbh());
			ptlb.add("去哪通知内容" + f);
			//设置参数Map
			Map<String,Object> tempMap = new HashMap<String, Object>();
			tempMap.put("jpDdsz", jpDdsz);//网店平台设置
			tempMap.put("rtnJson", f);//去哪请求json
			if("0101,0102".indexOf(changeCode)>-1){
				return ddrk(request,response,tempMap,ptlb);
			}else if("0201,0202".indexOf(changeCode)>-1){
				return xg_gyptzt(request, response,tempMap,ptlb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 处理0101已支付待出票订单通知业务
	 * @param request
	 * @param response
	 * @param tempMap
	 * @param ptlb
	 * @return
	 * @throws Exception
	 */
	private String ddrk(HttpServletRequest request,HttpServletResponse response,Map<String,Object> tempMap,JpPtLog ptlb) throws Exception{
		String result ="";		
		OutputStream out = response.getOutputStream();
		JpDdsz jpDdsz = (JpDdsz)tempMap.get("jpDdsz");
		String rtnJsdon = (String)tempMap.get("rtnJson");	
		JSONObject object = JSONObject.parseObject(rtnJsdon);
		String objectStr = object.getString("data");
		JSONObject data = JSONObject.parseObject(objectStr);
		String orderNo = StringUtils.trimToEmpty(data.getString("orderNo"));
		String transactionId = data.getString("transactionId");
		//进行导单
		QunarGy qunar = new QunarGy(jpDdsz);			
		List<OrderBean> list = new ArrayList<OrderBean>();
		try {
			list = qunar.getByWbdh(orderNo,ptlb);
		} catch (Exception e) {
			ptlb.add("根据去哪通知获取订单失败：" + e.getMessage());
			if(StringUtils.isNotBlank(orderNo) && orderNo.startsWith("tes")){
				result = "{\"transactionId\":\""+transactionId+"\",\"result\":\"SUCCESS\",\"errMsg\":\"\"}";
			}else {
				result = "{\"transactionId\":\""+transactionId+"\",\"result\":\"FAILED\",\"errMsg\":\"根据订单编号获取订单信息失败："+e.getMessage()+"\"}";
			}
			out.write(result.getBytes("UTF-8"));
			out.flush();
			return null;
		}
		
		if (list != null && list.size() > 0) {
			try {
				result = "{\"transactionId\":\""+transactionId+"\",\"result\":\"SUCCESS\",\"errMsg\":\"\"}";
				for(int i=0;i<list.size();i++){
					OrderBean o = list.get(i);
					jkhsImpl.toDb(o,jpDdsz,ptlb.getInfo());
				}
			} catch (Exception e) {
				result = "{\"transactionId\":\""+transactionId+"\",\"result\":\"FAILED\",\"errMsg\":\"订单入库失败\"}";
			}
		}else if(StringUtils.isNotBlank(orderNo) && orderNo.startsWith("tes")) {
			result = "{\"transactionId\":\""+transactionId+"\",\"result\":\"SUCCESS\",\"errMsg\":\"\"}";
		}else {
			result = "{\"transactionId\":\""+transactionId+"\",\"result\":\"FAILED\",\"errMsg\":\"根据订单编号获取订单信息失败,订单号："+orderNo+"返回失败\"}";
		}
		ptlb.add("去哪通知系统返回:"+result);
		out.write(result.getBytes("UTF-8"));
		out.flush();
		return null;
	}
	private String xg_gyptzt(HttpServletRequest request,HttpServletResponse response,Map<String,Object> tempMap,JpPtLog ptlb) throws Exception{
		String result ="";
		
		OutputStream out = response.getOutputStream();
		JpDdsz jpDdsz = (JpDdsz)tempMap.get("jpDdsz");
		String rtnJsdon = (String)tempMap.get("rtnJson");	
		
		JSONObject object = JSONObject.parseObject(rtnJsdon);
		String objectStr = object.getString("data");
		JSONObject data = JSONObject.parseObject(objectStr);
		String orderNo = StringUtils.trimToEmpty(data.getString("orderNo"));
		String transactionId = data.getString("transactionId");

		//还缺少大订单中gy_ptzt的修改
		result = "{\"transactionId\":\""+transactionId+"\",\"result\":\"SUCCESS\",\"errMsg\":\"\"}";
		out.write(result.getBytes("UTF-8"));
		out.flush();
		//发送提醒
		String ddqxtx = jpDdsz.getDdqxtx();
		String msg = "去哪供应订单【"+orderNo+"】，编码【】，去哪发送了取消申请，请及时处理。";
		return null;
	}
}