package cn.vetech.vedsb.pay.zfb;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzh;
import cn.vetech.vedsb.pay.ZfSign;
import cn.vetech.vedsb.pay.payUtil.B2bpayutil;
import cn.vetech.vedsb.pay.payUtil.Md5Encrypt;

/**
 * 
 * 支付宝工具类
 * 
 * @author houya
 */
public class ZfbPaySign implements ZfSign {
	
	private final String partner = B2bpayutil.getPayparam("B2B_ALIPAY_PARTNER");
	private final String sign_type = B2bpayutil.getPayparam("B2B_ALIPAY_SIGN_TYPE");
	private final String _input_charset = B2bpayutil.getPayparam("B2B_ALIPAY_INPUT_CHARSET");
	private final String protocol_code = B2bpayutil.getPayparam("B2B_ALIPAY_PROTOCOL_CODE");
	private final String item_code = B2bpayutil.getPayparam("B2B_ALIPAY_ITEM_CODE");
	private final String key = B2bpayutil.getPayparam("B2B_ALIPAY_KEY");
	private final String URL = "https://mapi.alipay.com/gateway.do";
	private final String root = B2bpayutil.getPayparam("RTN_URL");
	
	/**
	 * 返回签约连接
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String sign(HttpServletRequest request,JpB2bZfzh jpB2bZfzh) {
		
		String return_url = "";
		// 签约后返回地址 支付宝没有返回，是通过查询获得安全码的
		if (StringUtils.isNotBlank(root)) {
			return_url = root + "/webcontent/signReturn/returnResult";
		}
		// 协议参数
		String url = URL;
		String service = "dut.customer.sign";
		String params = "service=" + service + "&partner=" + partner + "&_input_charset=" + _input_charset
				+ "&item_code=" + item_code + "&protocol_code=" + protocol_code + "&return_url=" + return_url;
		Map m = new HashMap();
		m.put("service", service);
		m.put("partner", partner);
		m.put("item_code", item_code);
		m.put("protocol_code", protocol_code);
		m.put("_input_charset", _input_charset);
		m.put("return_url", return_url);
		String sign = Md5Encrypt.md5(ZfbUtil.getContent(m, key));
		params = params + "&sign=" + sign + "&sign_type=" + sign_type;
		url = url + "?" + params;
		return url;
	}

	/**
	 * 用户修改代扣协议
	 */
	@SuppressWarnings("unchecked")
	public String updatesign(HttpServletRequest request, JpB2bZfzh jpB2bZfzh) {
		String zfzh = jpB2bZfzh.getZfzh();
		if (StringUtils.isNotBlank(jpB2bZfzh.getCzykz())) {
			zfzh = jpB2bZfzh.getCzykz();
		}
		
		String return_url = "";
		// 签约后返回地址
		if (StringUtils.isNotBlank(root)) {
			return_url = root + "/webcontent/signReturn/returnResult";
		}
		// 协议参数
		String url = URL;
		String service = "dut.customer.sign.modify";
		String params = "service=" + service + "&partner=" + partner + "&_input_charset=" + _input_charset
				+ "&item_code=" + item_code + "&protocol_code=" + protocol_code + "&alipay_logon_id=" + zfzh
				+ "&return_url=" + return_url + "&notify_url=" + return_url;
		Map m = new HashMap();
		m.put("service", service);
		m.put("partner", partner);
		m.put("item_code", item_code);
		m.put("protocol_code", protocol_code);
		m.put("_input_charset", _input_charset);
		m.put("alipay_logon_id", zfzh);
		m.put("return_url", return_url);
		m.put("notify_url", return_url);
		String sign = Md5Encrypt.md5(ZfbUtil.getContent(m, key));
		params = params + "&sign=" + sign + "&sign_type=" + sign_type;
		url = url + "?" + params;
		return url;
	}

	/**
	 * 取消签约
	 */
	@SuppressWarnings("unchecked")
	public String unsign(JpB2bZfzh jpB2bZfzh) {
		// 协议参数
		String url = URL;
		String service = "dut.customer.page.unsign";
		String params = "service=" + service + "&partner=" + partner + "&_input_charset=" + _input_charset
				+ "&item_code=" + item_code + "&protocol_code=" + protocol_code;
		Map m = new HashMap();
		m.put("service", service);
		m.put("partner", partner);
		m.put("item_code", item_code);
		m.put("protocol_code", protocol_code);
		m.put("_input_charset", _input_charset);
		String sign = Md5Encrypt.md5(ZfbUtil.getContent(m, key));
		params = params + "&sign=" + sign + "&sign_type=" + sign_type;
		url = url + "?" + params;
		return url;
	}

	/**
	 * 用户签约查询
	 */
	@SuppressWarnings("unchecked")
	public String searchsign(JpB2bZfzh jpB2bZfzh) {
		String zfzh = jpB2bZfzh.getZfzh();
		//判断是否使用子账号
		if("1".equals(jpB2bZfzh.getSfsyzzh())){
			zfzh = jpB2bZfzh.getZfzzh();
		}
		// 协议参数
		String url = URL;
		String service = "dut.customer.sign.query";
		String params = "service=" + service + "&partner=" + partner + "&_input_charset=" + _input_charset
				+ "&item_code=" + item_code + "&protocol_code=" + protocol_code + "&alipay_logon_id=" + zfzh;
		Map m = new HashMap();
		m.put("service", service);
		m.put("partner", partner);
		m.put("item_code", item_code);
		m.put("protocol_code", protocol_code);
		m.put("_input_charset", _input_charset);
		m.put("alipay_logon_id", zfzh);
		String sign = Md5Encrypt.md5(ZfbUtil.getContent(m, key));
		params = params + "&sign=" + sign + "&sign_type=" + sign_type;
		url = url + "?" + params;
		return url;
	}
	
	public static void main(String[] args) {
		ZfbPaySign z=new ZfbPaySign();
		System.out.println(z.unsign(null));
	}
}
