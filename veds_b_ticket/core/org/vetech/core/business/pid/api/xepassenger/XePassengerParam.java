package org.vetech.core.business.pid.api.xepassenger;
import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.Param;
import org.vetech.core.modules.utils.XmlUtils;

public class XePassengerParam extends Param{
	/*
	 * 需要操作的PNR，不能为空
	 */
	private String pnrno;

	/*
	 * 可选，如果指定了，XE将在属于该office的pid上执行
	 */
	private String office;

	/*
	 * 操作类型，值为XEPNR或XECJR之一，可选，如果未指定，共享会默认操作类型是XECJR
	 */
	private String operation;

	/*
	 * 乘机人姓名,证件号|… 指定待删除的乘机人信息，如果有多个乘机人，中间用’|’分隔开。每个乘机人信息包括乘机人姓名,证件号码和票号，
	 * 用‘,’分隔开,如果乘机人有多个票号，那么票号之间用‘^’分隔开,输入票号的原因是因为在XE乘机人之前，要把票号项先XE掉。如果在乘机人中指定了同名乘机人， 那么必须都输入互不相同的证件号码
	 */
	private String cjr;

	/*
	 * 航班号,出发城市,到达城市,起飞日期(yyyy-mm-dd)，仓位|…
	 * 指定航段信息，如果有多个航段，中间用’|’分隔开。每个航段信息包括航班号,出发城市、到达城市、起飞日期(yyyy-mm-dd)和仓位，中间用‘,’分隔开。如果IGNOREHD节点的值为1，那么该节点可以取消
	 */
	private String hd;

	/*
	 * 0或1，可选，默认值是0。 指定在验证PNR的时候是否忽略乘机人证件号码，也就是说证件号码是否参与到PNR验证中。1表示不参与。当值为1的时候CJR节点中的证件号码可以为空，即使指定了值，在PNR有效性验证中也会被忽略。
	 */
	private int ignoreCjrZjhm = 0;

	/*
	 * 0或1，可选，默认值是0。 指定在验证PNR的时候是否忽略航段信息，也就是说航段信息是否参与到PNR验证中。1表示不参与。如果值为1，那么HD节点可以不输入
	 */
	private int ignoreHd = 0;
	
	
	/**
	 * 根据传入数据生成接口需要的XML
	 */
	public String toXml() {
		StringBuffer xml = new StringBuffer("<INPUT>");
		xml.append("<COMMAND>VEXEPASSENGER</COMMAND>");
		xml.append("<PARA>");
		xml.append(XmlUtils.xmlnode("USER", getUserid()));
		xml.append(XmlUtils.xmlnode("PNRNO", pnrno));
		if (StringUtils.isNotBlank(office)) {
			xml.append(XmlUtils.xmlnode("OFFICE", office));
		}
		if (StringUtils.isNotBlank(operation)) {
			xml.append(XmlUtils.xmlnode("OPERATION", operation));
		}
		xml.append(XmlUtils.xmlnode("CJR", cjr));
		xml.append(XmlUtils.xmlnode("HD", hd));
		xml.append(XmlUtils.xmlnode("IGNORECJRZJHM", ignoreCjrZjhm));
		xml.append(XmlUtils.xmlnode("IGNOREHD", ignoreHd));
		xml.append("</PARA>");
		xml.append("</INPUT>");

		return xml.toString();
	}


	public String getPnrno() {
		return pnrno;
	}

	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getHd() {
		return hd;
	}

	public void setHd(String hd) {
		this.hd = hd;
	}

	public int getIgnoreCjrZjhm() {
		return ignoreCjrZjhm;
	}

	public void setIgnoreCjrZjhm(int ignoreCjrZjhm) {
		this.ignoreCjrZjhm = ignoreCjrZjhm;
	}

	public int getIgnoreHd() {
		return ignoreHd;
	}

	public void setIgnoreHd(int ignoreHd) {
		this.ignoreHd = ignoreHd;
	}
}
