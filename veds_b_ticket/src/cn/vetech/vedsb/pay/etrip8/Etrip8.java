package cn.vetech.vedsb.pay.etrip8;
/**
 * 
 * 易商旅返回信息
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, Oct 18, 2012]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class Etrip8 {
	String accoutno;
	String keycode;
	String effectivedate;
	String operationdate;

	String operatorname;
	String operatorkeycode;
	String operatoreffectivedate;
	String operatoroperationdate;

	public String toString() {
		return "accoutno:=" + accoutno + "  keycode:=" + keycode + "  effectivedate:=" + effectivedate
				+ "  operationdate:=" + operationdate + " operatoreffectivedate:=" + operatoreffectivedate
				+ " operatoroperationdate:=" + operatoroperationdate;
	}

	public String getAccoutno() {
		return accoutno;
	}

	public void setAccoutno(String accoutno) {
		this.accoutno = accoutno;
	}

	public String getKeycode() {
		return keycode;
	}

	public void setKeycode(String keycode) {
		this.keycode = keycode;
	}

	public String getOperatorkeycode() {
		return operatorkeycode;
	}

	public void setOperatorkeycode(String operatorkeycode) {
		this.operatorkeycode = operatorkeycode;
	}

	public String getEffectivedate() {
		return effectivedate;
	}

	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}

	public String getOperationdate() {
		return operationdate;
	}

	public void setOperationdate(String operationdate) {
		this.operationdate = operationdate;
	}

	public String getOperatoreffectivedate() {
		return operatoreffectivedate;
	}

	public void setOperatoreffectivedate(String operatoreffectivedate) {
		this.operatoreffectivedate = operatoreffectivedate;
	}

	public String getOperatoroperationdate() {
		return operatoroperationdate;
	}

	public void setOperatoroperationdate(String operatoroperationdate) {
		this.operatoroperationdate = operatoroperationdate;
	}

	public String getOperatorname() {
		return operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

}
