package cn.vetech.vedsb.common.sso;

import java.io.Serializable;

import javax.persistence.Transient;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

public class SsoUser extends AbstractPageEntity implements Serializable {
	private static final long serialVersionUID = -7876957941345471422L;
	@Transient
	private String ip;
	private String bh; // 用户编号主键，商户设置，验证商户内不重复
	private String shbh; // 商户编号主键
	private String xm; // 姓名

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

}
