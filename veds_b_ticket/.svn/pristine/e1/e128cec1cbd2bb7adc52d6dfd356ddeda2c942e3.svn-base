package cn.vetech.vedsb.jp.entity.jphbyd;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="jp_hbyd_mx")
public class JpHbydMx extends AbstractPageEntity implements Serializable{

	private static final long serialVersionUID = 1955397998316578341L;

	@Id
	@GeneratedValue(generator="no")
	private String id;
	//航班异动ID
    private String ydid;
    //订单编号
    private String ddbh;
    //处理状态0未处理，1已处理
    private String clZt;
    //处理时间
    private String clDatetime;
    //处理人
    private String clYhbh;
    //处理部门
    private String clBmbh;
    //处理公司
    private String clShsh;
    //是否已发短信0未发送，1已发送
    private String sfyfdx;
    //备用1
    private String by1;
    //备用2
    private String by2;
    //备用3
    private String by3;
    //处理备注
    private String clBz;
    //航程
    private String hc;
    //出发时间
    private String cfsj;
    
    
    public String getClYhbh() {
		return clYhbh;
	}

	public void setClYhbh(String clYhbh) {
		this.clYhbh = clYhbh == null ? null : clYhbh.trim();
	}

	public String getClBmbh() {
		return clBmbh;
	}

	public void setClBmbh(String clBmbh) {
		this.clBmbh = clBmbh == null ? null : clBmbh.trim();
	}

	public String getClShsh() {
		return clShsh;
	}

	public void setClShsh(String clShsh) {
		this.clShsh = clShsh == null ? null : clShsh.trim();
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getYdid() {
        return ydid;
    }

    public void setYdid(String ydid) {
        this.ydid = ydid == null ? null : ydid.trim();
    }

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh == null ? null : ddbh.trim();
    }

    public String getClZt() {
        return clZt;
    }

    public void setClZt(String clZt) {
        this.clZt = clZt == null ? null : clZt.trim();
    }

	public String getClDatetime() {
		return clDatetime;
	}

	public void setClDatetime(String clDatetime) {
		this.clDatetime = clDatetime;
	}


    public String getSfyfdx() {
        return sfyfdx;
    }

    public void setSfyfdx(String sfyfdx) {
        this.sfyfdx = sfyfdx == null ? null : sfyfdx.trim();
    }

    public String getBy1() {
        return by1;
    }

    public void setBy1(String by1) {
        this.by1 = by1 == null ? null : by1.trim();
    }

    public String getBy2() {
        return by2;
    }

    public void setBy2(String by2) {
        this.by2 = by2 == null ? null : by2.trim();
    }

    public String getBy3() {
        return by3;
    }

    public void setBy3(String by3) {
        this.by3 = by3 == null ? null : by3.trim();
    }

    public String getClBz() {
        return clBz;
    }

    public void setClBz(String clBz) {
        this.clBz = clBz == null ? null : clBz.trim();
    }

    public String getHc() {
        return hc;
    }

    public void setHc(String hc) {
        this.hc = hc == null ? null : hc.trim();
    }

    public String getCfsj() {
        return cfsj;
    }

    public void setCfsj(String cfsj) {
        this.cfsj = cfsj == null ? null : cfsj.trim();
    }
	
}
