package org.vetech.core.modules.excel;

public class TestBean {
	@ExcelTitle("订单号")
	private String ddbh;
	@ExcelTitle("出票日期")
	private String cprq;
	@ExcelTitle("票号")
	private String tkno;

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getCprq() {
		return cprq;
	}

	public void setCprq(String cprq) {
		this.cprq = cprq;
	}

	public String getTkno() {
		return tkno;
	}

	public void setTkno(String tkno) {
		this.tkno = tkno;
	}

	@Override
	public String toString() {
		return "TestBean [ddbh=" + ddbh + ", cprq=" + cprq + ", tkno=" + tkno + "]";
	}
	
	
}
