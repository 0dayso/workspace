package cn.vetech.vedsb.pay.etrip8;

import java.net.URL;
import java.security.MessageDigest;

import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.vetech.core.modules.utils.ConnectUtil;
import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.pay.payUtil.B2bpayutil;
import cn.vetech.vedsb.utils.LogUtil;

import com.etrip8.airpay.PayService;
import com.etrip8.airpay.PayServiceSoap;

/**
 * 
 * 易商旅通讯
 * 
 * @author Administrator
 * @version [版本号, Oct 18, 2012]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class Etrip8Util {
	// TicketOrderDateSet对象中的Source需要设置为“shengyi”，key为airpaycometripb2b
	// pay.etrip8.com // 账号 shengyi 密码shengyi
	private static final QName SERVICE_NAME = new QName("http://tempuri.org/", "PayService");
	String ENCODE = "gb2312";
	private String B2B_ETRIP8_URL = B2bpayutil.getPayparam("B2B_ETRIP8_URL");
	private String B2B_ETRIP8_SOURCE = B2bpayutil.getPayparam("B2B_ETRIP8_SOURCE");
	private String B2B_ETRIP8_SAFEKEY = B2bpayutil.getPayparam("B2B_ETRIP8_SAFEKEY");
	int iDayNum = 365 * 2;
	String sMaCode = "11";
	boolean bMaCode = false;

	public Etrip8Util() {
		B2B_ETRIP8_URL = StringUtils.isBlank(B2B_ETRIP8_URL) ? "http://airpay.etrip8.com/PayService.asmx" : B2B_ETRIP8_URL;
		B2B_ETRIP8_SOURCE = StringUtils.isBlank(B2B_ETRIP8_SOURCE) ? "shengyi" : B2B_ETRIP8_SOURCE;
		B2B_ETRIP8_SAFEKEY = StringUtils.isBlank(B2B_ETRIP8_SAFEKEY) ? "airpaycometripb2b" : B2B_ETRIP8_SAFEKEY;

	}

	/**
	 * 账户身份认证。 [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Etrip8 setAccountNoContract(String sAccountNo, String sPwd) throws Exception {
		PayService ss = new PayService(new URL(B2B_ETRIP8_URL + "?WSDL"), SERVICE_NAME);
		PayServiceSoap port = ss.getPayServiceSoap();
		ConnectUtil.setCxfTime(port, null, false);
		// "AccountNo=" + sAccountNo + "&Pwd=" + sPwd + "&DayNum=" + iDayNum.ToString() + "&MaCode=" + sMaCode +
		// "&bMaCode=" + bMaCode.ToString().ToLower() + 指定的密钥 的32位md5大写
		String sign = "AccountNo=" + sAccountNo + "&Pwd=" + sPwd + "&DayNum=" + iDayNum + "&MaCode=" + sMaCode + "&bMaCode=" + bMaCode;
		sign = getSign(sign);
		LogUtil.getZfzhQy("zfzhqy").error("易商旅账户身份认证请求：" + sign);
		try {
			String xml = port.setAccountNoContract(sAccountNo, sPwd, iDayNum, sPwd, bMaCode, sign);
			LogUtil.getZfzhQy("zfzhqy").error("易商旅账户身份认证返回：" + xml);
			return parseXml(xml);
		} catch (Exception e) {
			throw new Exception("没有开通IP授权，或者给易商旅的ip不对,或者[" + e.getMessage() + "]");
		}
	}

	/**
	 * 操作员身份认证
	 * 
	 * @param sAccountNo
	 * @param sOperatorName
	 * @param sPwd
	 * @return
	 * @throws Exception
	 *             [参数说明]
	 * 
	 * @return Etrip8 [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Etrip8 setOperatorContract(String sAccountNo, String sOperatorName, String sPwd) throws Exception {
		PayService ss = new PayService(new URL(B2B_ETRIP8_URL + "?WSDL"), SERVICE_NAME);
		PayServiceSoap port = ss.getPayServiceSoap();
		ConnectUtil.setCxfTime(port, null, false);
		// "AccountNo=" + sAccountNo + "&OperatorName=" + sOperatorName + "&Pwd=" + sPwd +
		// "&DayNum=" + iDayNum.ToString() + "&MaCode=" + sMaCode + "&bMaCode=" + bMaCode.ToString().ToLower() + 指定的密钥
		// 的32位md5大写
		String sign = "AccountNo=" + sAccountNo + "&OperatorName=" + sOperatorName + "&Pwd=" + sPwd + "&DayNum=" + iDayNum + "&MaCode=" + sMaCode + "&bMaCode=" + bMaCode;
		sign = getSign(sign);
		LogUtil.getZfzhQy("zfzhqy").error("易商旅操作员身份认证请求：" + sign);
		try {
			String xml = port.setOperatorContract(sAccountNo, sOperatorName, sPwd, iDayNum, sPwd, bMaCode, sign); 
			LogUtil.getZfzhQy("zfzhqy").error("易商旅操作员身份认证返回：" + sign);
			return parseXml(xml);
		} catch (Exception e) {
			throw new Exception("没有开通IP授权，或者给易商旅的IP不对,[" + e.getMessage() + "]");
		}
	}

	private Etrip8 parseXml(String xml) throws Exception {
		if (StringUtils.isNotBlank(xml)) {

			// SAXBuilder builder1 = new SAXBuilder();
			// Reader avhred = new StringReader(xml);
			Document db = null;
			try {
				// docavh = builder1.build(avhred);
				db = DocumentHelper.parseText(xml);
			} catch (Exception e1) {
				e1.printStackTrace();
				throw e1;
			}
			// Element root = docavh.getRootElement();
			// Element ResultInfoE = root.getChild("ResultInfo");
			Element root = db.getRootElement();
			Element ResultInfoE = root.element("ResultInfo");
			if (ResultInfoE != null) {
				String ResultCode = ResultInfoE.elementText("ResultCode");
				if ("T".equals(ResultCode)) {
					Element InfoE = root.element("Info");
					if (InfoE != null) {
						String accoutno = InfoE.elementText("AccoutNo");
						String keycode = InfoE.elementText("KeyCode");
						String effectivedate = InfoE.elementText("EffectiveDate");
						String operationdate = InfoE.elementText("OperationDate");

						String operatorname = InfoE.elementText("OperatorName");
						String operatorkeycode = InfoE.elementText("OperatorKeyCode");
						String operatoreffectivedate = InfoE.elementText("OperatorEffectiveDate");
						String operatoroperationdate = InfoE.elementText("OperatorOperationDate");

						Etrip8 etrip8 = new Etrip8();
						etrip8.setAccoutno(accoutno);
						etrip8.setKeycode(keycode);
						etrip8.setEffectivedate(effectivedate);
						etrip8.setOperationdate(operationdate);

						etrip8.setOperatorname(operatorname);
						etrip8.setOperatorkeycode(operatorkeycode);
						etrip8.setOperatoreffectivedate(operatoreffectivedate);
						etrip8.setOperatoroperationdate(operatoroperationdate);
						return etrip8;
					}
				} else {
					throw new Exception(ResultInfoE.elementText("Description"));
				}
			}
		}
		return null;
	}

	private String getSign(String str) {
		str = MD5Encode(str + B2B_ETRIP8_SAFEKEY, ENCODE);
		str = StringUtils.upperCase(str);
		return str;
	}

	public static void main(String[] args) {
		System.out.println(NumberUtils.toDouble("000000122250") / 100);
		Etrip8Util e = new Etrip8Util();
		// Runner 11:01:42
		https: // payment.chinapay.com/pay/TransGet?BgRetUrl=http://mu.travelsky.com/pay/servlet/asp.pub.payment.chpay.MuChPayResB2B&ChkValue=BFDC5D9F67EA7C0917EE6A4E76F52C2EBCE53BBE65ED8EA120C1CA75EBB0ABFEBD6F0C931C0DCBEC300CDE0A07B04BDB0723184AEDD71F6F3D66A47F7470836C7CF29821A9B0D8153AFC7BC827F874BAA065327AB5FCB490F6C3EE96379A582E58866100A02D36B9E9B5137BBB9A9A9965750216E3496A8F5CB1741CEB4983C8&CuryId=156&GateId=&MerId=808080002100313&OrdId=1210003134968508&PageRetUrl=http://mu.travelsky.com/pay/bank/mu/chinapay/b2bChinaPayRes.jsp&Priv1=1349751393257&TransAmt=000000122250&TransDate=20121009&TransType=0001&Version=20040916
		try {
			String httpuri = "https://payment.chinapay.com/pay/TransGet?BgRetUrl=http://easypay.travelsky.com/easypay/ChpResServer.servlet?appname=TU&ChkValue=B2CB0E3E2DF1323DDC5A648244E28608CDF9BB2E0AA5D9F6E0298E1F1B6CEAC1CBF2F4C13DEB3240DCE51B6EB3CBB8403E9A571CC71317225AFB60A6103853B41101E773A49FE0CA4B83F7104FE3575EBDE769D2E1BC57AAE58865B2C2E31F711131F3235489E9ACF6438A0458C934500E92F4B8C90CC374B056D1AD85F40400&CuryId=156&GateId=&MerId=808080112700618&OrdId=1022006180031732&PageRetUrl=http://easypay.travelsky.com/easypay/ChpResPage.servlet?appname=TU&Priv1=PA201210221830138492&TransAmt=000000111150&TransDate=20121022&TransType=0001&Version=20040916";
			String a = XmlUtils.xmlnode("OrderInfo", httpuri);
			System.out.println(a);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}
