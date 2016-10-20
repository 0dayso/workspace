package cn.vetech.web.webcontent;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.MD5Tool;
import org.vetech.core.modules.utils.ParseXml;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.platpolicy.NoticeStatus;
import cn.vetech.vedsb.platpolicy.cps.service.CpsEtdzNoticeService;
import cn.vetech.vedsb.platpolicy.cps.service.CpsPayNoticeService;
import cn.vetech.vedsb.platpolicy.cps.service.CpsRefundNoticeService;
import cn.vetech.vedsb.utils.PlatCode;

/**
 * CPS通知回调
 * @author vetech
 *
 */
@Controller
public class CpsBuyerNoticeController {
	@Autowired
	private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
	@Autowired
	private CpsEtdzNoticeService cpsEtdzNoticeService;
	@Autowired
	private CpsPayNoticeService cpsPayNoticeService;
	@Autowired
	private CpsRefundNoticeService cpsRefundNoticeService;
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@SuppressWarnings("unchecked")
	@RequestMapping
	@ResponseBody
	public String cpsNotice(HttpServletRequest request){
		NoticeStatus status = new NoticeStatus();
		InetAddress addr = null;
//		Shyhb user = SessionUtils.getShshbSession();
//		String shbh = user.getShbh();
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		try {
			Map<String, String[]> params = request.getParameterMap();
			String content = StringUtils.trimToEmpty(request.getParameter("content"));
			String type = StringUtils.trimToEmpty(request.getParameter("type"));
			String businessNo = StringUtils.trimToEmpty(request.getParameter("businessNo"));
			String operateTime = StringUtils.trimToEmpty(request.getParameter("operateTime"));
			String sign = StringUtils.trimToEmpty(request.getParameter("sign"));
			status.setBusinessType("1");
			if(addr != null){
				status.setHostAddress(addr.getHostAddress());
				status.setHostName(addr.getHostName());
			}
			if (StringUtils.isBlank(businessNo) || StringUtils.isBlank(type) || StringUtils.isBlank(operateTime)
					|| StringUtils.isBlank(sign) || StringUtils.isBlank(content)) {//参数为空当作处理失败
				status.setStatus(-1);
				status.setMsg("参数为空");
				return XmlUtils.toXml(status);
			}
			ParseXml parsexml = new ParseXml(content);
			Element root = parsexml.getRoot();
			String orderId = root.elementText("orderNo");
			JpCgdd jpCgdd = this.jpCgddServiceImpl.getDdByZflsh(orderId);//查支付记录根据平台订单编号查
			if(jpCgdd == null){
                throw new Exception("根据平台订单编号查询平台采购记录为空!");
			}
			String shbh = jpCgdd.getShbh();
			// 验证商户
			JpPtzcZh jpPtzcZh = this.jpPtzcZhServiceImpl.getPtzhByPtzh(shbh, businessNo, PlatCode.CPS);
			if (jpPtzcZh == null) {
				throw new Exception(businessNo + "账号不存在");
			}
			
			// 签名:systemid+businessNo+operateTime+key
			String eSign = MD5Tool.MD5Encode("VETHCH" + jpPtzcZh.getUser1() + operateTime + jpPtzcZh.getPwd1());
			if (!sign.equals(eSign)) {
				throw new Exception("签名验证失败");
			}
			if("111".equals(type)){//出票通知
				status = this.cpsEtdzNoticeService.execute(jpCgdd, params);
			}else if("114".equals(type)){//支付通知
				status = this.cpsPayNoticeService.execute(jpCgdd, params);
			}else if("120".equals(type)){//退废票通知
				status = this.cpsRefundNoticeService.execute(jpCgdd, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.setStatus(-1);
			status.setMsg(e.getMessage());
		}finally{
			status.setBusinessType("1");
			if (addr != null) {
				status.setHostAddress(addr.getHostAddress());
				status.setHostName(addr.getHostName());
			}
			return XmlUtils.toXml(status);
		}
	}
	
	
}
