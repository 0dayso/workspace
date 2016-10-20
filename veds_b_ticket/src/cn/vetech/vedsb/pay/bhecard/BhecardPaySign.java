package cn.vetech.vedsb.pay.bhecard;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzh;
import cn.vetech.vedsb.pay.ZfSign;
import cn.vetech.vedsb.pay.payUtil.B2bpayutil;
import cn.vetech.vedsb.pay.payUtil.Md5Encrypt;
import cn.vetech.vedsb.pay.zfb.ZfbUtil;

/**
 * 
 * 海航易生卡
 * 
 * @author zhanglei
 * @version [版本号, Apr 22, 2011]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class BhecardPaySign implements ZfSign {
	private String B2B_BHECARD_PARTNER = B2bpayutil.getPayparam("B2B_BHECARD_PARTNER");
	private String B2B_BHECARD_MAIP = B2bpayutil.getPayparam("B2B_BHECARD_MAIP");
	private String B2B_BHECARD_KEY = B2bpayutil.getPayparam("B2B_BHECARD_KEY");
	private String biz_type = "14";
	private String sign_type = "MD5";
	private String root = B2bpayutil.getPayparam("RTN_URL");

	/**
	 * 返回签约连接
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String sign(HttpServletRequest request, JpB2bZfzh jpB2bZfzh) {
		String return_url = "";
		// 签约后返回地址 支付宝没有返回，是通过查询获得安全码的
		if (StringUtils.isNotBlank(root)) {
			return_url = root + "/webcontent/signReturn/returnResult?cpsid="+jpB2bZfzh.getId()+"_"+ jpB2bZfzh.getShbh();
		}
		// 协议参数
		String url = B2B_BHECARD_MAIP + "/selfservice/selfSign";
		Map m = new HashMap();
		m.put("partner", B2B_BHECARD_PARTNER);
		m.put("email", jpB2bZfzh.getZfzh());
		m.put("biz_type", biz_type);
		m.put("return_url", return_url);
		String sign = Md5Encrypt.md5(ZfbUtil.getContent(m, B2B_BHECARD_KEY));
		String params = "partner=" + B2B_BHECARD_PARTNER + "&email=" + jpB2bZfzh.getZfzh() + "&biz_type=" + biz_type
				+ "&return_url=" + return_url;

		params = params + "&sign=" + sign + "&sign_type=" + sign_type;
		url = url + "?" + params;
		return url;
	}

	/**
	 * 用户修改代扣协议
	 */
	@SuppressWarnings("unchecked")
	public String updatesign(HttpServletRequest request, JpB2bZfzh jpB2bZfzh) {
		return null;
	}

	/**
	 * 取消签约
	 */
	@SuppressWarnings("unchecked")
	public String unsign(JpB2bZfzh jpB2bZfzh) {

		return null;
	}

	/**
	 * 用户签约查询
	 */
	@SuppressWarnings("unchecked")
	public String searchsign(JpB2bZfzh jpB2bZfzh) {
		String zfzh = jpB2bZfzh.getZfzh();
		String url = B2B_BHECARD_MAIP + "/selfservice/signSearch";
		String params = "partner=" + B2B_BHECARD_PARTNER + "&biz_type=" + biz_type + "&email=" + zfzh;
		Map m = new HashMap();
		m.put("partner", B2B_BHECARD_PARTNER);
		m.put("biz_type", biz_type);
		m.put("email", zfzh);
		String sign = Md5Encrypt.md5(ZfbUtil.getContent(m, B2B_BHECARD_KEY));
		params = params + "&sign=" + sign + "&sign_type=" + sign_type;
		url = url + "?" + params;
		return url;
	}

}
