package cn.vetech.web.webcontent;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.ParseXml;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.platpolicy.cpslink.request.EtdzNotifyRequest;
import cn.vetech.vedsb.platpolicy.cpslink.request.PayNotifyRequest;
import cn.vetech.vedsb.platpolicy.cpslink.request.RefundNotifyRequest;
import cn.vetech.vedsb.platpolicy.cpslink.response.NoticeRes;
import cn.vetech.vedsb.platpolicy.cpslink.service.CpslinkEtdzNoticeService;
import cn.vetech.vedsb.platpolicy.cpslink.service.CpslinkPayNoticeService;
import cn.vetech.vedsb.platpolicy.cpslink.service.CpslinkRefundNoticeService;

@Controller
public class CpslinkBuyerNoticeController {
	@Autowired
	private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
	@Autowired
	private CpslinkEtdzNoticeService cpslinkEtdzNoticeService;
	@Autowired
	private CpslinkPayNoticeService cpslinkPayNoticeService;
	@Autowired
	private CpslinkRefundNoticeService cpslinkRefundNoticeService;
	@RequestMapping
	public @ResponseBody String cpslinkNotice(HttpServletRequest req){
		String data = req.getParameter("data");//link返回的内容信息
		//Shyhb user = SessionUtils.getShshbSession();
		NoticeRes res = new NoticeRes();
		JpPtLog ptLog=new JpPtLog();
		ptLog.add("从link返回的平台通知信息："+data);
		try {
			ParseXml parsexml = new ParseXml(data);
			Element root = parsexml.getRoot();
			String shbh = root.elementText("shbh");
			String type = root.elementText("noticeType");//通知类型(0:出票通知,1:退废票通知,2:支付通知)
			String platcode = root.elementText("platcode");
			// 验证商户
			JpPtzcZh jpPtzcZh = this.jpPtzcZhServiceImpl.getPtzhByPtBs(platcode,shbh);
			if (jpPtzcZh == null) {
				throw new Exception("商户不存在");
			}
			if("0".equals(type)){
				EtdzNotifyRequest erequest = (EtdzNotifyRequest) XmlUtils.fromXml(data, EtdzNotifyRequest.class);
				if(erequest == null){
					throw new Exception("link通知电商的出票通知为空");
				}
				res = this.cpslinkEtdzNoticeService.execute(ptLog, erequest, jpPtzcZh,shbh);
			}else if("1".equals(type)){
				RefundNotifyRequest rrequest = (RefundNotifyRequest) XmlUtils.fromXml(data, RefundNotifyRequest.class);
				if(rrequest == null){
					throw new Exception("link通知电商的退废票通知为空");
				}
				res = this.cpslinkRefundNoticeService.execute(ptLog, rrequest, jpPtzcZh,shbh);
			}else if("2".equals(type)){
				PayNotifyRequest prequest = (PayNotifyRequest) XmlUtils.fromXml(data, PayNotifyRequest.class);
				if(prequest == null){
					throw new Exception("link通知电商的支付通知为空");
				}
				res = this.cpslinkPayNoticeService.execute(ptLog, prequest, jpPtzcZh,shbh);
			}else{
				ptLog.add("不支持的消息处理类型"+type);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(NoticeRes.FAILL);
			res.setMsg(e.getMessage());
		}finally{
			return res.toString();
		}
	}
}
