package cn.vetech.web.vedsb.jpddsz;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.ParseXml;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.JpDdsz;
import cn.vetech.vedsb.jp.service.jpddsz.JpDdszServiceImpl;
import cn.vetech.vedsb.utils.DictEnum;
import cn.vetech.vedsb.utils.PlatCode;

/**
 * 网店设置
 * 
 * @author wangfeng
 * 
 */
@Controller
public class CtripGyTzController extends AbstractBaseControl {
	@Autowired
	private JpDdszServiceImpl jdsImpl;
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
			response.setContentType("appliction/xml;charset=UTF-8");
			String result = "";
			ServletInputStream in;
			in = request.getInputStream();
			byte[] buf = new byte[4048];
			int len = 0;
			String f = "";
			while((len = in.readLine(buf, 0, buf.length))!=-1){
				f += new String(buf, 0, len,"UTF-8");
			}
			String orderId = "";
			String actualOrderStatus = "";
			String userName = "";
			String userPassword = "";
			try {
				ParseXml pXml = new ParseXml(f);
				userName = pXml.attr("UserName");
				userPassword = pXml.attr("UserPassword");
				Element el = pXml.ele("OpenOrderStatusCallBackRequest");
				orderId = el.element("OrderId").getStringValue();
				actualOrderStatus = el.element("ActualOrderStatus").getStringValue().toUpperCase();		
			} catch (Exception e) {
				e.printStackTrace();
			}		
			if(StringUtils.isBlank(orderId)||StringUtils.isBlank(actualOrderStatus)){
				return "";
			}
			if("C,D".indexOf(actualOrderStatus)==-1){
				return "";
			}
			if(StringUtils.isBlank("userName")){
				return "";
			}
			JpDdsz record = new JpDdsz();
			record.setWdpt(PlatCode.QN);
			record.setShbh(shbh);
			record.setDdKqzcdd("1");
			record.setDdJkzh(userName);
			List<JpDdsz> ddszList = jdsImpl.getMyBatisDao().select(record);
			if(CollectionUtils.isEmpty(ddszList)){
				return "";
			}
			String tempMsg = "";
			if("C".equalsIgnoreCase(actualOrderStatus)){
				tempMsg = "，已被携程取消。";
			}else if("D".equalsIgnoreCase(actualOrderStatus)){
				tempMsg = "，携程发送了取消申请，请及时处理";
			}
			JpDdsz jpDdsz = ddszList.get(0);
			JpPtLog ptlb = new JpPtLog();
			ptlb.setYwlx(DictEnum.PTLOGYWLX.GYDDTZ.getValue());
			ptlb.setDdlx(DictEnum.PTLOGDDLX.ZC.getValue());
			ptlb.setPtzcbs(jpDdsz.getWdpt());
			ptlb.setCzsm(jpDdsz.getDdJkdz()+ "供应订单状态变化通知");
			ptlb.setShbh(jpDdsz.getShbh());
			ptlb.add("携程通知内容" + f);
			//发送提醒
			String ddqxtx = jpDdsz.getDdqxtx();
			String msg = "携程供应订单【】，编码【】"+tempMsg;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
}