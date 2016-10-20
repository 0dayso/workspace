package cn.vetech.vedsb.utils.zdcp;

import java.util.HashMap;
import java.util.Map;
public enum AutoCpErrorCode {
	
	/**
	 * BSPET自动出票失败原因分类
	 */
	BSPET_HBMSB("3120124011","HBMSB","换编码失败"),
	BSPET_YJBF("3120124012","YJBF","运价不符"),
	BSPET_CPSB("3120124013","CPSB","出票失败"),
	BSPET_QTYY("3120124014","QTYY","其它原因"),
	BSPET_RKCW("3120124017","RKCW","入库失败"),
	BSPET_ZJPSB("3120124018","ZJPSB","转机票失败"),
	B2B_LRPDBF("3120124112","LRPDBF","利润判断不符"),
	B2B_ZHDLSB("3120124113","ZHDLSB","账号问题登录失败"),
	B2B_YZMDLSB("3120124114","YZMDLSB","验证码问题登录失败"),
	B2B_XDSB("3120124116","XDSB","下单失败"),
	B2B_ZFSB("3120124117","ZFSB","支付失败"),
	B2B_WLWT("3120124120","WLWT","网络问题"),
	B2B_HQPNRSB("3120124121","HQPNRSB","获取大编码失败")
	;
	/**
	 * b_class表对应主键
	 */
	private String codeid;
	/**
	 * 错误代码
	 */
	private String errorcode;
	/**
	 * 错误代码信息
	 */
	private String msg;
	
	AutoCpErrorCode(String codeid,String errorcode,String msg) {
		this.codeid = codeid;
		this.errorcode = errorcode;
		this.msg = msg;
	}

	public String getCodeid() {
		return codeid;
	}
	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 获取指定value值的枚举实例
	 */
	public static AutoCpErrorCode instance(String msgcode){
		return map.get(msgcode);
	}
	private static Map<String, AutoCpErrorCode> map = new HashMap<String, AutoCpErrorCode>();
	static{
		for(AutoCpErrorCode c : AutoCpErrorCode.values()){
			map.put(c.errorcode, c);
		}
	}
}
