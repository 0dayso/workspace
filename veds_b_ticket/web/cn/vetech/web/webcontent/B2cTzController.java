package cn.vetech.web.webcontent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.XmlUtils;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.service.base.ShyhbServiceImpl;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
import cn.vetech.vedsb.jp.service.cgptsz.JpCgddServiceImpl;
import cn.vetech.vedsb.jp.service.procedures.PkgZjpSgServiceImpl;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.bean.PassengerInfo;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.request.TicketNoRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.response.TicketNoResponse;
import cn.vetech.vedsb.utils.LogUtil;

@Controller
public class B2cTzController extends AbstractBaseControl {
	@Autowired
	private JpCgddServiceImpl jpCgddServiceImpl;
	@Autowired
	private PkgZjpSgServiceImpl pkgZjpSgServiceImpl;
	@Autowired
	private ShyhbServiceImpl shyhbServiceImpl;
	@RequestMapping
	@ResponseBody
    public String cptz(HttpServletRequest request, HttpServletResponse response) {
			String data=request.getParameter("data");
			String nowdate = VeDate.getStringDateShort();
			LogUtil.getDgrz("B2C",nowdate+"_DGTZ").error("B2C代购接收通知："+data);
			TicketNoRequest ticketNorequest=(TicketNoRequest) XmlUtils.fromXml(data, TicketNoRequest.class);
			TicketNoResponse b2cResponse=new TicketNoResponse();
			try {
				if (ticketNorequest==null) {
					b2cResponse.setErrorCode("XML_ERROR");
					b2cResponse.setErrorMessage("xml格式错误");
					throw new Exception("xml格式错误");
				}
				try {
					ticketNorequest.valid();
				} catch (Exception e) {
					b2cResponse.setErrorCode("PARAM_EMPTY_ERROR");
					b2cResponse.setErrorMessage(e.getMessage());
					throw new Exception(e.getMessage());
				}
				String ddbh = ticketNorequest.getOutOrderNo();//订单编号
				String dgdh = ticketNorequest.getOrderNo();//代购单号
				String hsddh = ticketNorequest.getAirwaysOrderNo();//航司订单号
				String payment = ticketNorequest.getPayment();//支付方式
				String zfzh = ticketNorequest.getPayAccount();//支付账号
				String zfje = ticketNorequest.getPaymentAmount();//支付金额
				String zflsh = ticketNorequest.getBuyPaymentTransaction();//支付流水号
				String office = ticketNorequest.getOffice();//获得office号

			    //TODO 查本地采购订单表 jp_cgdd
				JpCgdd jpCgdd = jpCgddServiceImpl.getJpCgddByCgly(ddbh, "B2C", dgdh);
				if(jpCgdd==null){
					throw new Exception("没有找到对应采购记录");
				}
				//开始转机票
				String rtnmsg = zjp(ticketNorequest, jpCgdd);
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
				jpCgddBean.setZfje(NumberUtils.toDouble(zfje));
				jpCgddBean.setTradeNo(zflsh);;
			    jpCgddBean.setJyzt("1");
			    jpCgddBean.setDgdh(dgdh);
			    jpCgddBean.setHkgsDdbh(hsddh);
				jpCgddServiceImpl.update(jpCgddBean);
			} catch (Exception e) {
				b2cResponse.setStatus(-1);
				b2cResponse.setErrorMessage(e.getMessage());
				e.printStackTrace();
			}
			String rtnMsg = XmlUtils.toXml(b2cResponse);
		return rtnMsg;
	}
	private String zjp(TicketNoRequest ticketNorequest,JpCgdd jpCgdd){
		Shyhb user = shyhbServiceImpl.getMyBatisDao().getShyhb(jpCgdd.getShbh(), jpCgdd.getCjUserid());
		List<PassengerInfo> cjrlist = ticketNorequest.getPassengerInfos();
		Map<String,Object> publicParam=new HashMap<String, Object>();
		Map<String,Object> publicMap=new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (PassengerInfo cjr : cjrlist) {// 乘机人信息段
			String cjrxm = StringUtils.trimToEmpty(cjr.getPassenger());
			String ph = StringUtils.trimToEmpty(cjr.getTicketNo());
			Map<String,Object> tk = new HashMap<String, Object>();
			tk.put("CJR", cjrxm);
			tk.put("TKNO", ph);
			list.add(tk);
		}
		publicParam.put("TK", list);
		publicMap.put("DDBH", jpCgdd.getDdbh());
		publicMap.put("CG_DDBH", jpCgdd.getDgdh());
		publicMap.put("CGZFKM", jpCgdd.getCgZfkm());
		publicMap.put("B2BZH", ticketNorequest.getAirwaysAccount());
		publicMap.put("OFFICEID", "");
		publicMap.put("AGENT", "");
//		publicMap.put("PJ_XSJ", ticketNorequest.getTotalPrice());
		publicMap.put("ZFJE", ticketNorequest.getPaymentAmount());
		publicMap.put("CP_USERID", user.getBh());
		publicMap.put("CP_DEPTID", user.getShbmid());
		publicMap.put("SHBH", user.getShbh());
		publicMap.put("WCPDW", jpCgdd.getCgdw());
		publicMap.put("FKDH", ticketNorequest.getBuyPaymentTransaction());
		publicMap.put("CPQD", "B2B");
		publicMap.put("CGLY", "B2B");
		publicMap.put("DATAFROM", "B2B代购出票");
		publicParam.put("PUBLIC", publicMap);
		LogUtil.getDgrz("B2C",jpCgdd.getDdbh()).error("B2C转机票入参:"+publicParam);
		pkgZjpSgServiceImpl.xmlToJson(publicParam);
		pkgZjpSgServiceImpl.fzjpb2b(publicParam);
		int result = (Integer)publicParam.get("result");
		if(-1 == result){
			String error = StringUtils.trimToEmpty((String)publicParam.get("perror"));
			if(StringUtils.isBlank(error)){
				error = "B2C转机票失败";
			}
			LogUtil.getDgrz("B2C",jpCgdd.getDdbh()).error("B2C转机票返回:"+result + "===" + error);
			return error;
		}
		return "";
	}
}
