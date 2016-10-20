package cn.vetech.vedsb.jp.entity.jpddgl;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_KHDD_KZ")
public class JpKhddKz extends AbstractPageEntity implements Serializable{
	
	private static final long serialVersionUID = -321273015730764749L;

	@Id
	@GeneratedValue(generator="no")
    private String ddbh;

    private String shbh;

    private String jjd;

    private Integer cpcs;

    private String zdcpzt;

    private String zdcpsbyy;

    private String phhtzt;

    private String phhtsbyy;

    private Integer phhtsbcs;

    private String cpdh;
    
    private String iftbcp;

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh;
    }

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh;
    }

    public String getJjd() {
        return jjd;
    }

    public void setJjd(String jjd) {
        this.jjd = jjd;
    }

    public Integer getCpcs() {
		return cpcs;
	}

	public void setCpcs(Integer cpcs) {
		this.cpcs = cpcs;
	}

	public String getZdcpzt() {
        return zdcpzt;
    }

    public void setZdcpzt(String zdcpzt) {
        this.zdcpzt = zdcpzt;
    }

    public String getZdcpsbyy() {
        return zdcpsbyy;
    }

    public void setZdcpsbyy(String zdcpsbyy) {
        this.zdcpsbyy = zdcpsbyy;
    }

    public String getPhhtzt() {
        return phhtzt;
    }

    public void setPhhtzt(String phhtzt) {
        this.phhtzt = phhtzt;
    }

    public String getPhhtsbyy() {
        return phhtsbyy;
    }

    public void setPhhtsbyy(String phhtsbyy) {
        this.phhtsbyy = phhtsbyy;
    }

    public Integer getPhhtsbcs() {
		return phhtsbcs;
	}

	public void setPhhtsbcs(Integer phhtsbcs) {
		this.phhtsbcs = phhtsbcs;
	}

	public String getCpdh() {
        return cpdh;
    }

    public void setCpdh(String cpdh) {
        this.cpdh = cpdh;
    }

	public String getIftbcp() {
		return iftbcp;
	}

	public void setIftbcp(String iftbcp) {
		this.iftbcp = iftbcp;
	}
}