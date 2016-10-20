package org.vetech.core.business.pid.api.trfd;

import org.vetech.core.business.pid.api.Param;
import org.vetech.core.modules.utils.XmlUtils;

public class TrfdParam extends Param{
	/*
	 * 能为空，包括：AUTO,HALFAUTO,MANUAL,MODIFY，TRFX分别对应：全自动出票（Z选项），半自动出票（L选项），手工出票，
	 * 根据退票单修改退票信息及取消退票单号
	 */
	private String operationType;

	/*
	 * 必填项。1表示以预览模式调用接口，0表示命令提交模式。在不同模式下，接口所需要的参数是不一样的。如果为1，入参只需要能够拼凑出TRFD指令的数据即可
	 * ；如果为0，则要传入全部的退票时所需要的信息项
	 */
	private String preview;

	/*
	 * 打票机，不能为空
	 */
	private String printer;

	/*
	 * 退票单号，在操作类型为MODIFY时，该项为必填项
	 */
	private String refundNo;

	/*
	 * 票号，在操作类型为非MODIFY时，该项为必填项；即使操作类型为MODIFY，在PREVIEW为0时，也必须传入票号，因为此时票号是退票所必需信息项
	 */
	private String ticketNo;

	/*
	 * 7位连续票号，可为空
	 */
	private String secondTicketNo;

	/*
	 * 校验位
	 */
	private String check;

	/*
	 * 连续客票标记项，0或1，非空项
	 */
	private String conjunction;

	/*
	 * 票联级次，非空项
	 */
	private String couponNo;

	/*
	 * 票面价格，非空项
	 */
	private String crossRefound;

	/*
	 * 是否是电子票， 非空项
	 */
	private String et;

	/*
	 * 退票费 可空
	 */
	private String deduction;

	/*
	 * 代理费率， 非空项
	 */
	private String commission;
	
	/**
	 * 代理费
	 */
	private String agencyfee;

	/*
	 * 信用卡卡号 可空
	 */
	private String creditcard;

	/*
	 * 可空，格式 ‘二字税控代码:金额|二字税控代码|金额…’
	 */
	private String tax;

	/*
	 * 净退款
	 */
	private String netRefound;

	/*
	 * 退票费 可空,默认为CNY-2
	 */
	private String moneyType;

	/*
	 * D或I 非空，指明是国际还是国内
	 */
	private String country;

	/*
	 * 旅客姓名, 一定要是拼音 可空
	 */
	private String passenger;

	/*
	 * 支付形式，默认是CASH 可空
	 */
	private String formofPayMent;

	/*
	 * 旅客姓名, 一定要是拼音 可空
	 */
	private String remark;

	/**
	 * <INPUT> <COMMAND>VETRFD</COMMAND> <PARA> <USER>用户名</USER>
	 * 不能为空，将根据该用户的TRFD权限确定配置 <OPERATIONTYPE>操作类型</OPERATIONTYPE>
	 * 不能为空，包括：AUTO,HALFAUTO
	 * ,MANUAL,MODIFY，TRFX分别对应：全自动出票（Z选项），半自动出票（L选项），手工出票，根据退票单修改退票信息及取消退票单号。
	 * <PREVIEW>0或1</ PREVIEW>
	 * 必填项。1表示以预览模式调用接口，0表示命令提交模式。在不同模式下，接口所需要的参数是不一样的。如果为1
	 * ，入参只需要能够拼凑出TRFD指令的数据即可；如果为0，则要传入全部的退票时所需要的信息项。 <PRINTER>打印机号</PRINTER>
	 * 不能为空。 <REFUNDNO>退票单号</REFUNDNO> 在操作类型为MODIFY时，该项为必填项。 <TICKETNO>退票单号</
	 * TICKETNO>
	 * 在操作类型为非MODIFY时，该项为必填项；即使操作类型为MODIFY，在PREVIEW为0时，也必须传入票号，因为此时票号是退票所必需信息项。
	 * <SECONDTICKETNO>7位连续票号</SECONDTICKETNO> 可空 <CHECK>校验位</CHECK> 可空
	 * <OFFICE>校验位</OFFICE>，当指定了office时，将在该office下的配置上执行TRFD指令。
	 * <CONJUNCTION>连续客票标记项，0或1</CONJUNCTION> 非空项 <COUPONNO>票联级次</COUPONNO> 非空项
	 * <CROSSREFOUND>票面价格</CROSSREFOUND> 非空项 <ET>是否是电子票</ET> 非空项
	 * <DEDUCTION>退票费</DEDUCTION> 可空 <COMMISSION>代理费</COMMISSION> 非空
	 * <CREDITCARD>信用卡卡号</CREDITCARD > 可空 <TAX>税费</ TAX> 可空，格式
	 * ‘二字税控代码:金额|二字税控代码|金额…’ <NETREFOUND>净退款</NETREFOUND> 可空
	 * <MONEYTYPE>退票费</MONEYTYPE> 可空,默认为CNY-2 <COUNTRY>D或I</COUNTRY>
	 * 非空，指明是国际还是国内 <PASSENGER>旅客姓名, 一定要是拼音</PASSENGER> 可空
	 * <FORMOFPAYMENT>支付形式，默认是CASH</FORMOFPAYMENT> 可空 <REMARK>旅客姓名,
	 * 一定要是拼音</REMARK> 可空 </PARA> </INPUT>
	 */
	public String toXml() {
		StringBuffer xml = new StringBuffer("<INPUT>");
		xml.append("<COMMAND>VETRFD</COMMAND>");
		xml.append("<PARA>");
		xml.append(XmlUtils.xmlnode("USER", getUserid()));
		xml.append(XmlUtils.xmlnode("OPERATIONTYPE", operationType));
		xml.append(XmlUtils.xmlnode("PREVIEW", preview));
		xml.append(XmlUtils.xmlnode("PRINTER", printer));
		xml.append(XmlUtils.xmlnode("REFUNDNO", refundNo));
		xml.append(XmlUtils.xmlnode("TICKETNO", ticketNo));
		xml.append(XmlUtils.xmlnode("SECONDTICKETNO", secondTicketNo));
		xml.append(XmlUtils.xmlnode("CHECK", this.check));
		xml.append(XmlUtils.xmlnode("OFFICE", super.getOfficeId()));
		xml.append(XmlUtils.xmlnode("CONJUNCTION", this.conjunction));
		xml.append(XmlUtils.xmlnode("COUPONNO", this.couponNo));
		xml.append(XmlUtils.xmlnode("CROSSREFOUND", this.crossRefound));
		xml.append(XmlUtils.xmlnode("ET", this.et));
		xml.append(XmlUtils.xmlnode("DEDUCTION", this.deduction));
		xml.append(XmlUtils.xmlnode("COMMISSION", this.commission));
		xml.append(XmlUtils.xmlnode("AGENCYFEE", this.agencyfee));
		xml.append(XmlUtils.xmlnode("CREDITCARD", this.creditcard));
		xml.append(XmlUtils.xmlnode("TAX", this.tax));
		xml.append(XmlUtils.xmlnode("NETREFOUND", this.netRefound));
		xml.append(XmlUtils.xmlnode("MONEYTYPE", this.moneyType));
		xml.append(XmlUtils.xmlnode("COUNTRY", this.country));
		xml.append(XmlUtils.xmlnode("PASSENGER", this.passenger));
		xml.append(XmlUtils.xmlnode("FORMOFPAYMENT", this.formofPayMent));
		xml.append(XmlUtils.xmlnode("REMARK", this.remark));
		xml.append("</PARA>");
		xml.append("</INPUT>");

		return xml.toString();
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getPrinter() {
		return printer;
	}

	public void setPrinter(String printer) {
		this.printer = printer;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	public String getSecondTicketNo() {
		return secondTicketNo;
	}

	public void setSecondTicketNo(String secondTicketNo) {
		this.secondTicketNo = secondTicketNo;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getConjunction() {
		return conjunction;
	}

	public void setConjunction(String conjunction) {
		this.conjunction = conjunction;
	}

	public String getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(String couponNo) {
		this.couponNo = couponNo;
	}

	public String getCrossRefound() {
		return crossRefound;
	}

	public void setCrossRefound(String crossRefound) {
		this.crossRefound = crossRefound;
	}

	public String getEt() {
		return et;
	}

	public void setEt(String et) {
		this.et = et;
	}

	public String getDeduction() {
		return deduction;
	}

	public void setDeduction(String deduction) {
		this.deduction = deduction;
	}

	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	public String getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getNetRefound() {
		return netRefound;
	}

	public void setNetRefound(String netRefound) {
		this.netRefound = netRefound;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassenger() {
		return passenger;
	}

	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}

	public String getFormofPayMent() {
		return formofPayMent;
	}

	public void setFormofPayMent(String formofPayMent) {
		this.formofPayMent = formofPayMent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getAgencyfee() {
		return agencyfee;
	}

	public void setAgencyfee(String agencyfee) {
		this.agencyfee = agencyfee;
	}

	public static void main(String[] args) {
		//VeTrfdCommand cmd = new VeTrfdCommand();
		//cmd.setUrl("");
		//System.out.println(cmd.toXml());
	}
}
