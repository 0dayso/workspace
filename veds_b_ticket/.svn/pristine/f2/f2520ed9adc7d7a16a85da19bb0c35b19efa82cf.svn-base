package cn.vetech.vedsb.specialticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vetech.vedsb.specialticket.request.KwCancelRequest;
import cn.vetech.vedsb.specialticket.request.KwOrderRequest;
import cn.vetech.vedsb.specialticket.request.KwStatusRequest;
import cn.vetech.vedsb.specialticket.response.KwCancelResponse;
import cn.vetech.vedsb.specialticket.response.KwOrderResponse;
import cn.vetech.vedsb.specialticket.response.KwResponse;
import cn.vetech.vedsb.specialticket.response.KwStatusResponse;

@Service
public class KwService {
	@Autowired
	private KwClient kwClient;
	/**
	 * 发送申请单接口
	 * @param compId 总公司id
	 * @param xml 请求xml
	 *	response:<?xml version="1.0" encoding="utf-8"?>
	 *				<response>
	 *					<code>状态 1成功 -1失败</code>
	 *					<message>反馈信息,反馈状态为1时可不传</message>
	 *				</response>
	 */
	public KwOrderResponse sendOrder(String compId ,KwOrderRequest request){
		KwOrderResponse response=new KwOrderResponse();
		try {
			response = kwClient.send(request, "sendOrder", compId, KwOrderResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(KwResponse.FAILL);
			response.setError("调用追位接口异常");
		}
		return response;
	}
	/**
	 * 获取追位单追位状态
	 * @param shbh
	 * @param request
	 * @return
	 */
	public KwStatusResponse genOrderStauts(String shbh,KwStatusRequest request) {
		KwStatusResponse response=new KwStatusResponse();
		try {
			response = kwClient.send(request, "getOrderResults", shbh, KwStatusResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(KwResponse.FAILL);
			response.setError("调用追位接口异常");
		}
		return response;
	}
	/**
	 * 获取追位单追位状态
	 * @param shbh
	 * @param request
	 * @return
	 */
	public KwCancelResponse cancelOrder(String shbh,KwCancelRequest request) {
		KwCancelResponse response=new KwCancelResponse();
		try {
			response = kwClient.send(request, "cancleOrder", shbh, KwCancelResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(KwResponse.FAILL);
			response.setError("调用追位接口异常");
		}
		return response;
	}
}
