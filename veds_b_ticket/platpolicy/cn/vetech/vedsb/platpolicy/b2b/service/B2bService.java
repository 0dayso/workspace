package cn.vetech.vedsb.platpolicy.b2b.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.modules.utils.ObjectUtil;

import cn.vetech.framework.bookticket.b2cnew.action.PaymentInfo;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bHkgs;
import cn.vetech.vedsb.jp.entity.b2bsz.JpZdcpB2bzh;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhdd;
import cn.vetech.vedsb.platpolicy.jzcp.B2bnewException;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.bean.B2bpolicyBean;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.VetpsPurchasB2bClient;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.request.B2bRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.B2bOrderResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.B2bPolicyResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response.PayInfo;
import cn.vetech.vedsb.utils.LogUtil;

@Service
public class B2bService {
	@Autowired
	private B2bsession b2bsession;
	
	//下单到代购系统
    public String order2dgxt(JpKhdd jpKhdd,Shyhb user,JpZdcpB2bzh dlzh,String sfxyh,String office,B2bpolicyBean b2bPolicy) {
    	LogUtil.getDgrz("B2B", jpKhdd.getDdbh()).error("[B2B全自动]开始提交订单");
    	String ddbh = jpKhdd.getDdbh();//订单编号
		String b2bpolicyid = StringUtils.trimToEmpty(b2bPolicy.getB2bpolicyid());
		String ifzhzg = "";//南航纵横中国
		String payhand = "1";// 支付方式 1自动  其他手动
		String asmszfzhid = "";
		
		try {
			if(dlzh==null ||StringUtils.isBlank(dlzh.getZh())){
				throw new B2bnewException("登录航空公司账号没有获取到");
			}
			asmszfzhid=dlzh.getZfid();
			//提取大编码
			jpKhdd = 	b2bsession.getjpKhdd(user, ddbh);
			B2bRequest brequest = b2bsession.getB2bRequest(user, jpKhdd);
			
	    	brequest.setAirwaysAccount(dlzh.getZh());
	    	brequest.setAirwaysPwd(dlzh.getMm());
			
			PaymentInfo paymentinfo = b2bsession.getPaymentInfo(user, asmszfzhid, payhand, "");
			brequest.setPayInfo(ObjectUtil.obj2Byte(paymentinfo));// 支付平台 1-9 对应 支付宝 财付通 。。。。易航宝
			brequest.setB2bpolicyid(b2bpolicyid);
			B2bOrderResponse orderResponse = VetpsPurchasB2bClient.b2bSubmitOrder(brequest, user.getShbh(), user.getBh(), LogUtil.getDgrz("B2B", brequest.getOutOrderNo()));
			int status = orderResponse.getStatus();// 0成功 -1失败
			if(status==0){
				PayInfo payInfo = orderResponse.getOrderInfo().getPayInfo();
				if("T".equals(payInfo.getSuccess())){//返回支付成功页面
					b2bsession.saveJpCgdd(ddbh,user,orderResponse);
					LogUtil.getDgrz("B2B", jpKhdd.getDdbh()).error("[B2B全自动]下单并支付成功");
					return "";
				}else{
					String error = payInfo.getError();
					LogUtil.getDgrz("B2B", jpKhdd.getDdbh()).error("B2B下单成功支付失败，失败原因："+error);
					return "B2B下单成功支付失败，失败原因："+error;
				}
			}else{
				LogUtil.getDgrz("B2B", jpKhdd.getDdbh()).error("B2B下单失败，失败原因："+orderResponse.getErrorMessage());
				return "B2B下单失败，失败原因："+orderResponse.getErrorMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.getDgrz("B2B", jpKhdd.getDdbh()).error("B2B下单失败，失败原因：",e);
			return "B2B下单失败，失败原因："+e.getMessage();
		}
	}
	/**
	 * @description:通过代购获取政策信息	
	 * @author:longm
	 * @date:2015-8-27 上午08:56:40
	 */
    public B2bPolicyResponse getB2bPolicy(String shbh,String ddbh,JpB2bHkgs jpB2bHkgs, JpZdcpB2bzh dlzh,Shyhb user, String sfxyh,JpPz jpPz) throws B2bnewException {
    	LogUtil.getDgrz("B2B", ddbh).error("[B2B全自动]开始提取政策");
    	JpKhdd jpKhdd = b2bsession.getjpKhdd(user, ddbh);
		B2bRequest brequest = b2bsession.getB2bRequest(user, jpKhdd);
		brequest.setDkhbh(sfxyh);// 大客户编码
		if(dlzh==null ||StringUtils.isBlank(dlzh.getZh())){
			throw new B2bnewException("登录航空公司账号没有获取到");
		}
		
		brequest.setAirwaysAccount(dlzh.getZh());
    	brequest.setAirwaysPwd(dlzh.getMm());

		B2bPolicyResponse bpresponse = null;
		try {
			bpresponse = VetpsPurchasB2bClient.getB2bPolicy(brequest, user.getShbh(), user.getBh(), LogUtil.getDgrz("B2B", ddbh));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (bpresponse == null || bpresponse.getStatus() == -1) {
			LogUtil.getDgrz("B2B", jpKhdd.getDdbh()).error("没有提取到B2B政策");
			throw new B2bnewException("没有提取到B2B政策");
		}
		if( bpresponse.getStatus() == -1){
			LogUtil.getDgrz("B2B", jpKhdd.getDdbh()).error("提取B2B政策失败，失败原因："+bpresponse.getErrorMessage());
			throw new B2bnewException("提取B2B政策失败，失败原因："+bpresponse.getErrorMessage());
		}
	    return bpresponse;
	}
}
