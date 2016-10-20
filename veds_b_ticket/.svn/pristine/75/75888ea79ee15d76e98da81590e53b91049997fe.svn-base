package cn.vetech.vedsb.platpolicy.cps.request.ticket;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 平台采购退废单提交到平台及接收平台办理通知后的处理调用后台函数pkg_ticket_tf.f_pt_cg_process的参数
 * 
 * @author vetech
 */
@XmlRootElement(name = "SQL")
public class FPtCgProcessBean {

	/**
	 * 退废单号
	 */
	private String tfid;

	/**
	 * 操作用户
	 */
	private String userid;

	/**
	 * 类型 1表示提交到平台 2表示平台办理通知
	 */
	private String type;

	/**
	 * 状态 0表示成功 -1表示失败
	 */
	private String status;

	/**
	 * 失败的错误信息
	 */
	private String error;

	/**
	 * 平台采购退废单号 type为1时提交到平台成功后返回平台退废单号
	 */
	private String pt_cgdh;

	/**
	 * 平台实退金额
	 */
	private String pt_stje;

	/**
	 * type为2时平台办理通知时，是否按机票传入退票费，1表示按票传入，0表示不是按票传入
	 */
	private String rtnType;

	/*
	 * 以下参数为rtnType为1时按机票传入的票号及票价，多个用“|分隔”
	 */

	/**
	 * 票号，票号格式为999-1234567890
	 */
	private String tkno;

	/**
	 * 机票账单价
	 */
	private String cj_xsj;

	/**
	 * 机票建设费
	 */
	private String cj_jsf;

	/**
	 * 机票税费
	 */
	private String cj_tax;

	/**
	 * 退票手续费/废票工本费
	 */
	private String jp_pjhk;

	/**
	 * 总改签退款金额
	 */
	private String hj_cj_gqfy;
	/**
	 * 改签退款金额明细  格式 1,2,3|3,4,5|||
	 */
	private String cj_gqfy;

	/**
	 * 平台改签单号
	 */
	private String pt_gqdh;
	
	/**
     * 退款支付方式
     */
    private String payment;

    /**
     * 退款支付方式代码
     */
    private String paymentCode;

	@XmlElement(name = "TFID")
	public String getTfid() {
		return tfid;
	}

	public void setTfid(String tfid) {
		this.tfid = tfid;
	}

	@XmlElement(name = "USERID")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@XmlElement(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@XmlElement(name = "ERROR")
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@XmlElement(name = "PT_CGDH")
	public String getPt_cgdh() {
		return pt_cgdh;
	}

	public void setPt_cgdh(String pt_cgdh) {
		this.pt_cgdh = pt_cgdh;
	}

	@XmlElement(name = "PT_STJE")
	public String getPt_stje() {
		return pt_stje;
	}

	public void setPt_stje(String pt_stje) {
		this.pt_stje = pt_stje;
	}

	@XmlElement(name = "RTNTYPE")
	public String getRtnType() {
		return rtnType;
	}

	public void setRtnType(String rtnType) {
		this.rtnType = rtnType;
	}

	@XmlElement(name = "TKNO")
	public String getTkno() {
		return tkno;
	}

	public void setTkno(String tkno) {
		this.tkno = tkno;
	}

	@XmlElement(name = "CJ_XSJ")
	public String getCj_xsj() {
		return cj_xsj;
	}

	public void setCj_xsj(String cj_xsj) {
		this.cj_xsj = cj_xsj;
	}

	@XmlElement(name = "CJ_JSF")
	public String getCj_jsf() {
		return cj_jsf;
	}

	public void setCj_jsf(String cj_jsf) {
		this.cj_jsf = cj_jsf;
	}

	@XmlElement(name = "CJ_TAX")
	public String getCj_tax() {
		return cj_tax;
	}

	public void setCj_tax(String cj_tax) {
		this.cj_tax = cj_tax;
	}

	@XmlElement(name = "JP_PJHK")
	public String getJp_pjhk() {
		return jp_pjhk;
	}

	public void setJp_pjhk(String jp_pjhk) {
		this.jp_pjhk = jp_pjhk;
	}

	@XmlElement(name = "CJ_GQFY")
	public String getCj_gqfy() {
		return cj_gqfy;
	}

	
	public void setCj_gqfy(String cj_gqfy) {
		this.cj_gqfy = cj_gqfy;
	}

	@XmlElement(name = "PT_GQDH")
	public String getPt_gqdh() {
		return pt_gqdh;
	}

	
	public void setPt_gqdh(String pt_gqdh) {
		this.pt_gqdh = pt_gqdh;
	}

	public String getHj_cj_gqfy() {
		return hj_cj_gqfy;
	}

	public void setHj_cj_gqfy(String hj_cj_gqfy) {
		this.hj_cj_gqfy = hj_cj_gqfy;
	}

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
    
    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }
	
}
