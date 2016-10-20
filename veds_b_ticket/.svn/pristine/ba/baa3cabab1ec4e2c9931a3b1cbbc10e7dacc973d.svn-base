package cn.vetech.vedsb.pay.chinapay;

import javax.servlet.http.HttpServletRequest;
import org.vetech.core.modules.service.ServiceException;
import org.vetech.core.modules.utils.VeDate;
import com.airepay.yhb.security.Security;
import cn.vetech.vedsb.jp.entity.b2bsz.JpB2bZfzh;
import cn.vetech.vedsb.pay.ZfSign;
import cn.vetech.vedsb.pay.payUtil.B2bpayutil;
import cn.vetech.vedsb.utils.LogUtil;

public class ChinapayPaySign implements ZfSign {
	@Override
	public String sign(HttpServletRequest request, JpB2bZfzh jpB2bZfzh) {
		
		String OrgId = B2bpayutil.getPayparam("B2B_CHINAPAY_ORGID");
		String chinapayurl = B2bpayutil.getPayparam("B2B_CHINAPAY_SIGN_ADDRESS");
		String cer_file = B2bpayutil.getPayparam("B2B_CHINAPAY_SIGN_CER_FILE");
		String cer_pass = B2bpayutil.getPayparam("B2B_CHINAPAY_SIGN_CER_PASS");
		String ip = request.getServerName() + ":" + request.getServerPort();

		String Version = "1.2";
		String Reserved01 = jpB2bZfzh.getId() + "_" + jpB2bZfzh.getShbh();
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

		Security s = new Security();
		String SignMsg = ""; // 签名信息

		String pathtmp = ChinapayPaySign.class.getResource("/").getPath();
		String path = pathtmp + cer_file;

		if (s.signMsg(sb.toString(), path, cer_pass))
			SignMsg = s.getLastSignMsg();
		else {
			String info = "银联委托支付获得签名失败" + s.getLastErrorMsg();
			LogUtil.getZfzhQy("zfzhqy").error(info);
			throw new ServiceException(info);
		}

		String url = chinapayurl + "?OrgNo=" + OrgId + "&OrgUserNo=" + OrgUserNo + "&CallBackUrl=" + CallbackUrl
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
