package cn.vetech.vedsb.pay.chinapay;

import javax.servlet.http.HttpServletRequest;

import org.vetech.core.modules.service.ServiceException;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.WebUtils;

import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzh;
import cn.vetech.vedsb.pay.ZfSign;
import cn.vetech.vedsb.pay.payUtil.B2bpayutil;
import cn.vetech.vedsb.utils.LogUtil;

/**
 * 银联易航宝签约
 * @author  WANGQI
 * @version  [版本号, apr 9, 2016]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class AirepaySign implements ZfSign {
	@Override
	public String sign(HttpServletRequest request, JpB2bZfzh jpB2bZfzh) {

		String OrgId = B2bpayutil.getPayparam("B2B_AIREPAY_ORGID");
		String chinapayurl = B2bpayutil.getPayparam("B2B_AIREPAY_SIGN_ADDRESS");
		String cer_file = B2bpayutil.getPayparam("B2B_AIREPAY_ENC_CER_FILE");
		String cer_pass = B2bpayutil.getPayparam("B2B_AIREPAY_SIGN_CER_PASS");

		String ip = request.getServerName() + ":" + request.getServerPort();

		String Version = "1.2";
		String Reserved01 = "";
		String Reserved02 = "";
		String EncodeMsg = "";
		String CallbackUrl = "";
		String OrgUserNo = Reserved01 + VeDate.getUserDate("yMdHms");// 随机生成代理人的机构码
		// 签约后返回地址
		CallbackUrl = "http://" + ip + "/webcontent/signReturn/returnResult";
		StringBuffer sb = new StringBuffer();
		sb.append("OrgNo=").append(OrgId);
		sb.append("&OrgUserNo=").append(OrgUserNo);
		sb.append("&CallbackUrl=").append(CallbackUrl);
		sb.append("&Version=").append(Version);
		sb.append("&Reserved01=").append(Reserved01);
		sb.append("&Reserved02=").append(Reserved02);
		
		com.airepay.yhb.security.Security s = new com.airepay.yhb.security.Security();
		String SignMsg = ""; // 签名信息
		String path = WebUtils.getAbsoluteFile(cer_file);
		
		if (s.signMsg(sb.toString(), path, cer_pass))
			SignMsg=s.getLastSignMsg().toLowerCase();
		else {
			String info = "银联委托支付获得签名失败" + s.getLastErrorMsg();
			LogUtil.getZfzhQy("zfzhqy").error(info);
			throw new ServiceException(info);
		}
		
		String url = chinapayurl + "?OrgNo=" + OrgId + "&OrgUserNo=" + OrgUserNo + "&CallbackUrl=" + CallbackUrl
				+ "&Version=" + Version + "&Reserved01=" + Reserved01 + "&Reserved02=" + Reserved02 + "&EncodeMsg="
				+ EncodeMsg + "&SignMsg=" + SignMsg;

		return url;
		
	}

	@Override
	public String searchsign(JpB2bZfzh jpB2bZfzh) throws Exception {

		return null;
	}

	@Override
	public String unsign(JpB2bZfzh jpB2bZfzh) {

		return null;
	}

	@Override
	public String updatesign(HttpServletRequest request, JpB2bZfzh jpB2bZfzh) {

		return null;
	}
}
