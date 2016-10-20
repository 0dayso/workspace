package cn.vetech.vedsb.jp.entity.b2bsz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

/**
 * B2B支付账号
 * @author wangshengliang
 *
 */
@Table(name="JP_B2B_ZFZH")
public class JpB2bZfzh extends AbstractPageEntity implements Serializable{

	private static final long serialVersionUID = 7769527860545674134L;
	@Id
	@GeneratedValue(generator="no")
	private String id;
	//创建时间
    private String cjtime;
    //商户编号
    private String shbh;
    //用户编号
    private String yhbh;
    //支付类型:1=支付宝 2=财付通 3=汇付  4=易宝 5=快钱 6=御航宝 7=易商旅 8=易生卡 9=易航宝
    private String zflx;
    //支付账号
    private String zfzh;
    //是否签约
    private String sfqy;
    //支付的操作员账号(子账号)
    private String zfzzh;
    //支付方式类型
    private String zfxx1;
    //支付密码
    private String zfxx2;
    //其他
    private String zfxx3;
    //其他
    private String zfxx4;
    //为1的时候表示支付宝使操作员账号 易商旅使用操作员方式
    private String sfsyzzh ;
    //1 在交易时不显示账号 0或其他在支付时
    private String sfxs;
    //是否开启
    private String sfkq;
    //1 表示所有航空公司  否则用逗号隔开的航空公司二字码 默认1
    private String zchkgs;
    //商户系统操作员子账号
    private String czykz;
    //其他
    private String zfxx5;
    //其他
    private String zfxx6;
    //其他
    private String zfxx7;
    //其他
    private String zfxx8;
    //其他
    private String zfxx9;
    //其他
    private String zfxx10;
    //其他
    private String zfxx11;
    
    @Transient
    private List<String> zflxList = new ArrayList<String>();

    public String getSfqyName(){
    	String qyName;
    	if("1".equals(this.sfqy)){
    		qyName="<font color='green'>已签约</font>";
    	}else{
    		qyName="<font color='red'>未签约</font>";
    	}
    	return qyName;
    }

    public String getZflxName(){
    	String zflxName = "";
    	if("1".equals(this.zflx)){
    		zflxName = "支付宝";
    	}else if("2".equals(this.zflx)){
    		zflxName = "财付通";
    	}else if("3".equals(this.zflx)){
    		zflxName = "汇付";
    	}else if("4".equals(this.zflx)){
    		zflxName = "易宝";
    	}else if("5".equals(this.zflx)){
    		zflxName = "快钱";
    	}else if("6".equals(this.zflx)){
    		zflxName = "御航宝";
    	}else if("7".equals(this.zflx)){
    		zflxName = "易商旅";
    	}else if("8".equals(this.zflx)){
    		zflxName = "易生卡";
    	}else if("9".equals(this.zflx)){
    		zflxName = "易航宝";
    	}
    	return zflxName;
    }
    
    public String getZfxx1Name(){
    	String zfxx1Name;
    	if("1".equals(this.zfxx1)){
    		zfxx1Name="钱管家信用账户";
    	}else if("2".equals(this.zfxx1)){
    		zfxx1Name="钱管家付款账户";
    	}else if("3".equals(this.zfxx1)){
    		zfxx1Name="易宝会员";
    	}else if("4".equals(this.zfxx1)){
    		zfxx1Name="易宝信用账户";
    	}else if("00".equals(this.zfxx1)){
    		zfxx1Name="默认账户";
    	}else if("01".equals(this.zfxx1)){
    		zfxx1Name="额度账户";
    	}else if("02".equals(this.zfxx1)){
    		zfxx1Name="现金账户";
    	}else{
    		zfxx1Name="";
    	}
    	return zfxx1Name;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCjtime() {
        return cjtime;
    }

    public void setCjtime(String cjtime) {
        this.cjtime = cjtime == null ? null : cjtime.trim();
    }

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh == null ? null : shbh.trim();
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh == null ? null : yhbh.trim();
    }

    public String getZflx() {
        return zflx;
    }

    public void setZflx(String zflx) {
        this.zflx = zflx == null ? null : zflx.trim();
    }

    public String getZfzh() {
        return zfzh;
    }

    public void setZfzh(String zfzh) {
        this.zfzh = zfzh == null ? null : zfzh.trim();
    }

    public String getSfqy() {
        return sfqy;
    }

    public void setSfqy(String sfqy) {
        this.sfqy = sfqy == null ? null : sfqy.trim();
    }

    public String getZfxx1() {
        return zfxx1;
    }

    public void setZfxx1(String zfxx1) {
        this.zfxx1 = zfxx1 == null ? null : zfxx1.trim();
    }

    public String getZfxx2() {
        return zfxx2;
    }

    public void setZfxx2(String zfxx2) {
        this.zfxx2 = zfxx2 == null ? null : zfxx2.trim();
    }

    public String getZfxx3() {
        return zfxx3;
    }

    public void setZfxx3(String zfxx3) {
        this.zfxx3 = zfxx3 == null ? null : zfxx3.trim();
    }

    public String getZfxx4() {
        return zfxx4;
    }

    public void setZfxx4(String zfxx4) {
        this.zfxx4 = zfxx4 == null ? null : zfxx4.trim();
    }

    public String getSfkq() {
        return sfkq;
    }

    public String getZfzzh() {
		return zfzzh;
	}

	public void setZfzzh(String zfzzh) {
		this.zfzzh = zfzzh;
	}

	public String getSfsyzzh() {
		return sfsyzzh;
	}

	public void setSfsyzzh(String sfsyzzh) {
		this.sfsyzzh = sfsyzzh;
	}

	public String getSfxs() {
		return sfxs;
	}

	public void setSfxs(String sfxs) {
		this.sfxs = sfxs;
	}

	public void setSfkq(String sfkq) {
        this.sfkq = sfkq == null ? null : sfkq.trim();
    }

    public String getZchkgs() {
        return zchkgs;
    }

    public void setZchkgs(String zchkgs) {
        this.zchkgs = zchkgs == null ? null : zchkgs.trim();
    }

    public String getCzykz() {
        return czykz;
    }

    public void setCzykz(String czykz) {
        this.czykz = czykz == null ? null : czykz.trim();
    }

    public String getZfxx5() {
        return zfxx5;
    }

    public void setZfxx5(String zfxx5) {
        this.zfxx5 = zfxx5 == null ? null : zfxx5.trim();
    }

    public String getZfxx6() {
        return zfxx6;
    }

    public void setZfxx6(String zfxx6) {
        this.zfxx6 = zfxx6 == null ? null : zfxx6.trim();
    }

    public String getZfxx7() {
        return zfxx7;
    }

    public void setZfxx7(String zfxx7) {
        this.zfxx7 = zfxx7 == null ? null : zfxx7.trim();
    }

    public String getZfxx8() {
        return zfxx8;
    }

    public void setZfxx8(String zfxx8) {
        this.zfxx8 = zfxx8 == null ? null : zfxx8.trim();
    }

    public String getZfxx9() {
        return zfxx9;
    }

    public void setZfxx9(String zfxx9) {
        this.zfxx9 = zfxx9 == null ? null : zfxx9.trim();
    }

    public String getZfxx10() {
        return zfxx10;
    }

    public void setZfxx10(String zfxx10) {
        this.zfxx10 = zfxx10 == null ? null : zfxx10.trim();
    }

    public String getZfxx11() {
        return zfxx11;
    }

    public void setZfxx11(String zfxx11) {
        this.zfxx11 = zfxx11 == null ? null : zfxx11.trim();
    }

	public List<String> getZflxList() {
		return zflxList;
	}

	public void setZflxList(List<String> zflxList) {
		this.zflxList = zflxList;
	}
    
}