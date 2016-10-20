package cn.vetech.web.vedsb.jzcp;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vetech.core.modules.web.AbstractBaseControl;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
import cn.vetech.vedsb.jp.service.cgptsz.JpPtzcZhServiceImpl;
import cn.vetech.vedsb.pay.payUtil.B2bpayutil;
import cn.vetech.vedsb.platpolicy.cps.service.CpsService;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.VetpsPurchasClient;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.request.B2cCheckPendingOrderRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.request.B2cGetSecretKeyRequest;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.response.B2cCheckPendingOrderResponse;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.response.B2cGetSecretKeyResponse;
import cn.vetech.vedsb.utils.PlatCode;
import cn.vetech.web.vedsb.SessionUtils;

import com.alibaba.fastjson.JSONObject;

@Controller
public class B2cPurchasController extends AbstractBaseControl {
	@Autowired
	private JpPtzcZhServiceImpl jpPtzcZhServiceImpl;
	@Autowired
	private CpsService cpsService;
	
	@RequestMapping
	@ResponseBody
	public String checkPendingOrder(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Shyhb user = SessionUtils.getShshbSession();
		Map<String, String> jsonMap=new HashMap<String, String>();
		jsonMap.put("status","0");
		try {
			B2cCheckPendingOrderRequest checkPendingOrderRequest=new B2cCheckPendingOrderRequest();
			checkPendingOrderRequest.setUserid("HBQMADMIN");
			B2cCheckPendingOrderResponse checkPendingOrderResponse=VetpsPurchasClient.b2cCheckPendingOrder(checkPendingOrderRequest,getJpPtzcZh(user));
			if(-1==checkPendingOrderResponse.getStatus()){
				throw new Exception(checkPendingOrderResponse.getErrMsg());
			}else {
				jsonMap.put("yzmFlag",checkPendingOrderResponse.getCodeFlag()+"");
				jsonMap.put("dzfFlag",checkPendingOrderResponse.getPayFlag()+"");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("status","-1");
		}
		return JSONObject.toJSONString(jsonMap);
	}
	@RequestMapping
	@ResponseBody
	public String getSecretKey( HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		B2cGetSecretKeyRequest secretKeyRequest=new B2cGetSecretKeyRequest();
		Shyhb user = SessionUtils.getShshbSession();
		secretKeyRequest.setUserid(user.getBh());
		JpPtzcZh ptzh = getJpPtzcZh(user);
		Map<String, String> jsonMap=new HashMap<String, String>();
		jsonMap.put("status","0");
		try {
			B2cGetSecretKeyResponse secretKeyResponse=VetpsPurchasClient.b2cGetSecretKey(secretKeyRequest,ptzh);
			if(-1==secretKeyResponse.getStatus()){
				throw new Exception(secretKeyResponse.getErrMsg());
			}else {
				jsonMap.put("secretkey", secretKeyResponse.getSecretKey());
				jsonMap.put("shbh",ptzh.getUser1());
				jsonMap.put("userid",user.getBh());
				jsonMap.put("czurl", B2bpayutil.getPayparam("VETPS_URL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("status","-1");
		}
		return JSONObject.toJSONString(jsonMap);
	}
	
	/**
	 * @description:获取cps的可用余额	
	 * @author:longm
	 * @date:2015-8-14 下午03:39:24
	 */
	@RequestMapping
	@ResponseBody
    public String getCpsMoney(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	Shyhb user = SessionUtils.getShshbSession();
    	String B2C_BOOK_CPBH = "312012411";//这里是写死的
        Map<String, String> jsonMap = new HashMap<String, String>();
        jsonMap.put("status","0");
        try{
            String kyye = cpsService.getCpsMoney(B2C_BOOK_CPBH, 0, user.getShbh());
            jsonMap.put("kyye", kyye);
        }catch (Exception e) {
            e.printStackTrace();
            jsonMap.put("status","-1");
        }
        return JSONObject.toJSONString(jsonMap);
    }
	/**
	 * @description:在线冲值
	 * @author:longm
	 * @date:2015-8-14 下午03:39:24
	 */
	@RequestMapping
	@ResponseBody
    public String toZxcz(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		return "";
	}
	
	/**
	 * 获取采购平台信息
	 * @param ve_yhb
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private JpPtzcZh getJpPtzcZh(Shyhb user) throws Exception{
		//取JpPtzcZh表设置
		JpPtzcZh ptzh = jpPtzcZhServiceImpl.getPtzhByPtBs(PlatCode.CPS, user.getShbh());
		return ptzh;
	}
}
