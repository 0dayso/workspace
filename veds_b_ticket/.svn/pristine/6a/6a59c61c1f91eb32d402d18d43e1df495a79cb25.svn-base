package cn.vetech.web.webcontent;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.XmlUtils;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.platpolicy.NoticeStatus;
import cn.vetech.vedsb.platpolicy.b2b.service.B2bsession;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.B2bOrderResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.OrderInfo;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.PayInfo;
import cn.vetech.vedsb.utils.LogUtil;

@Controller
public class B2bTzController extends AbstractBaseControl {
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private B2bsession b2bsession;
	@RequestMapping
	@ResponseBody
    public String cptz(HttpServletRequest request, HttpServletResponse response) {
			String data=request.getParameter("data");
			String nowdate = VeDate.getStringDateShort();
			LogUtil.getDgrz("B2B",nowdate+"_DGTZ").error("B2B代购接收通知："+data);
			B2bOrderResponse b2borderreponse=(B2bOrderResponse) XmlUtils.fromXml(data, B2bOrderResponse.class);
			NoticeStatus res = new NoticeStatus();
			InetAddress addr = null;
			try {
				addr = InetAddress.getLocalHost();
				if (b2borderreponse==null) {
					throw new Exception("xml格式错误");
				}
				OrderInfo b2bOrder = b2borderreponse.getOrderInfo();
				
				String ddbh = b2bOrder.getOutOrderNo();//订单编号
				String dgdh = b2bOrder.getOrderNo();//代购单号
				String hsddh = b2bOrder.getAirwaysOrderNo();//航司订单号
				PayInfo payInfo = b2bOrder.getPayInfo();
				String payment = payInfo.getZflx();//支付方式
				String zfzh = payInfo.getZfzh();//支付账号
				double zfje = payInfo.getPaytotal();//支付金额
				String zflsh = payInfo.getTradeno();//支付流水号
//				String office = "";
//				String office = ticketNorequest.getOffice();//获得office号

			    //TODO 查本地采购订单表 jp_cgdd
				JpCgdd jpCgdd = jpCgddServiceImpl.getJpCgddByCgly(ddbh, "B2B", hsddh);
				if(jpCgdd==null){
					throw new Exception("没有找到对应采购记录");
				}
				//开始转机票
				String rtnmsg = b2bsession.zjp(b2bOrder, jpCgdd);
				if(StringUtils.isNotBlank(rtnmsg)){
					throw new Exception(rtnmsg);
				}
				//转机票成功修改jpCgdd表
				JpCgdd jpCgddBean = new JpCgdd();
				jpCgddBean.setId(jpCgdd.getId());
				jpCgddBean.setDdbh(ddbh);
				jpCgddBean.setZt("1");//下单C站代购时，修改采购订单状态为等待支付
				jpCgddBean.setPaykind(payment);
				jpCgddBean.setZfzh(zfzh);
				jpCgddBean.setZfje(zfje);
				jpCgddBean.setTradeNo(zflsh);
			    jpCgddBean.setJyzt("1");
			    jpCgddBean.setDgdh(hsddh);
			    jpCgddBean.setHkgsDdbh(hsddh);
				jpCgddServiceImpl.update(jpCgddBean);
			} catch (Exception e) {
				res.setStatus(-1);
				res.setMsg(e.getMessage());
				if (addr != null) {
					res.setHostAddress(addr.getHostAddress());
					res.setHostName(addr.getHostName());
				}
				e.printStackTrace();
			}
		String rtnMsg = XmlUtils.toXml(res);
		return rtnMsg;
	}
}
