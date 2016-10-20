package cn.vetech.vedsb.jp.entity.jphbyd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.VeDate;

@Table(name="b_qinfo_hbydgz")
public class BQinfoHbydgz extends AbstractPageEntity implements Serializable{

	private static final long serialVersionUID = -7985071339232878101L;
	@Id
	@GeneratedValue(generator="no")
	private String id;
	//规则类型(1提前/2延误/3取消)
    private String lx;
    //航空公司(---标识所有适用，可多选)
    private String hkgs;
    //延误(提前)时长范围始
    private int fws;
    //延误(提前)时长范围止
    private int fwz;
    //自动电话通知:1是,0否
    private String telphone;
    //自动短信通知:1是,0否
    private String infomation;
    //商户编号
    private String shbh;
    //修改人
    private String xguserid;
    //修改时间
    private Date xgDatetime;
    //备用1
    private String by1;

    @Transient
    public String getLxName(){
    	String lxName=null;
    	if("1".equals(this.lx)){
    		lxName = "提前";
    	}else if("2".equals(this.lx)){
    		lxName = "延误";
    	}else{
    		lxName = "取消";
    	}
    	return lxName;
    }
    
    @Transient
    public String getTelphoneName(){
    	String telphoneName=null;
    	if("1".equals(this.telphone)){
    		telphoneName = "是";
    	}else{
    		telphoneName = "否";
    	}
    	return telphoneName;
    }
    
    @Transient
    public String getInfomationName(){
    	String infomationName=null;
    	if("1".equals(this.infomation)){
    		infomationName = "是";
    	}else{
    		infomationName = "否";
    	}
    	return infomationName;
    }
    
    @Transient
    public String getXgDatetimeStr(){
    	return VeDate.dateToStrLong(this.xgDatetime);
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx == null ? null : lx.trim();
    }

    public String getHkgs() {
        return hkgs;
    }

    public void setHkgs(String hkgs) {
        this.hkgs = hkgs == null ? null : hkgs.trim();
    }

    public int getFws() {
        return fws;
    }

    public void setFws(int fws) {
        this.fws = fws;
    }

    public int getFwz() {
        return fwz;
    }

    public void setFwz(int fwz) {
        this.fwz = fwz;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getInfomation() {
        return infomation;
    }

    public void setInfomation(String infomation) {
        this.infomation = infomation == null ? null : infomation.trim();
    }

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh == null ? null : shbh.trim();
    }

    public String getXguserid() {
        return xguserid;
    }

    public void setXguserid(String xguserid) {
        this.xguserid = xguserid == null ? null : xguserid.trim();
    }

    public Date getXgDatetime() {
        return xgDatetime;
    }

    public void setXgDatetime(Date xgDatetime) {
        this.xgDatetime = xgDatetime;
    }

    public String getBy1() {
        return by1;
    }

    public void setBy1(String by1) {
        this.by1 = by1 == null ? null : by1.trim();
    }
	
}
