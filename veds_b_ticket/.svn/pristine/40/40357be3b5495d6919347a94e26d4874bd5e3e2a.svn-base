package cn.vetech.vedsb.jp.entity.jpzdcp;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

/**
 * 机票自动出票失败后重试次数设置
 * @author wangshengliang
 *
 */
@Table(name="JP_ZDCP_CSSZ")
public class JpZdcpCssz extends AbstractPageEntity{
	
	private static final long serialVersionUID = 232411530199865764L;

	/**ID主键，序列生成*/
	@Id
	private String id;

	/**
	 * 采购来源
	 */
    private String cgly;

    /**
     * 错误代码
     */
    private String cwdm;

    /**
     * 错误信息
     */
    private String cwxx;

    /**
     * 重试次数
     */
    private Short cscs;

    /**
     * 时间间隔单位秒
     */
    private Short sjjg;
    
    /**
     * 创建时间
     */
    private Date cjsj;

    /**
     * 创建用户
     */
    private String cjUserid;

    /**
     * 修改时间
     */
    private Date xgsj;

    /**
     * 修改用户
     */
    private String xgyh;

    /**
     * 状态   0禁用 1启用 2删除  
     */
    private String zt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCgly() {
        return cgly;
    }

    public void setCgly(String cgly) {
        this.cgly = cgly == null ? null : cgly.trim();
    }

    public String getCwdm() {
        return cwdm;
    }

    public void setCwdm(String cwdm) {
        this.cwdm = cwdm == null ? null : cwdm.trim();
    }

    public String getCwxx() {
        return cwxx;
    }

    public void setCwxx(String cwxx) {
        this.cwxx = cwxx == null ? null : cwxx.trim();
    }

    public Short getCscs() {
        return cscs;
    }

    public void setCscs(Short cscs) {
        this.cscs = cscs;
    }

    public Short getSjjg() {
        return sjjg;
    }

    public void setSjjg(Short sjjg) {
        this.sjjg = sjjg;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getCjUserid() {
        return cjUserid;
    }

    public void setCjUserid(String cjUserid) {
        this.cjUserid = cjUserid == null ? null : cjUserid.trim();
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

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt == null ? null : zt.trim();
    }
}