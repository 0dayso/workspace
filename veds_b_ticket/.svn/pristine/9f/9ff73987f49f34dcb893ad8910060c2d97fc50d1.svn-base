package cn.vetech.vedsb.jp.entity.office;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_OFFICE")//票证库存
public class JpOffice extends AbstractPageEntity implements Serializable{
	
	private static final long serialVersionUID = 5482550859468613821L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(generator="no")
	private String id;

	/**
	 * 商户编号
	 */
    private String shbh;
     
    /**
     * office号
     */
    private String officeid;

    /**
     * 德付通支付密码
     */
    private String bopmm;

    /**
     * 是否支持BOP出票 1支持0不支持
     */
    private String sfbopcp;

    /**
     * 创建用户自动创建固定为0000
     */
    private String cjyh;

    /**
     * 创建时间
     */
    private Date cjsj;

    /**
     * 
     */
    private Date xgsj;

    /**
     * 
     */
    private String xgyh;
    
    
    private String bopcgkm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh == null ? null : shbh.trim();
    }

    public String getOfficeid() {
        return officeid;
    }

    public void setOfficeid(String officeid) {
        this.officeid = officeid == null ? null : officeid.trim();
    }

    public String getBopmm() {
        return bopmm;
    }

    public void setBopmm(String bopmm) {
        this.bopmm = bopmm == null ? null : bopmm.trim();
    }

    public String getSfbopcp() {
        return sfbopcp;
    }

    public void setSfbopcp(String sfbopcp) {
        this.sfbopcp = sfbopcp == null ? null : sfbopcp.trim();
    }

    public String getCjyh() {
        return cjyh;
    }

    public void setCjyh(String cjyh) {
        this.cjyh = cjyh == null ? null : cjyh.trim();
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Date getXgsj() {
        return xgsj;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public String getXgyh() {
        return xgyh;
    }

    public void setXgyh(String xgyh) {
        this.xgyh = xgyh == null ? null : xgyh.trim();
    }

	public String getBopcgkm() {
		return bopcgkm;
	}

	public void setBopcgkm(String bopcgkm) {
		 this.bopcgkm = bopcgkm == null ? null : bopcgkm.trim();
	}
    
    
}