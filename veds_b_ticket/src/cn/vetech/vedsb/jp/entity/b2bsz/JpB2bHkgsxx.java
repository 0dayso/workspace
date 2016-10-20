package cn.vetech.vedsb.jp.entity.b2bsz;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_B2B_HKGSXX")
public class JpB2bHkgsxx extends AbstractPageEntity implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1664323442465219648L;
	@Id
	@GeneratedValue(generator="no")
	private String id;

    private String hkgs;

    private String mc;

    private String sfzc;

    private String fs0;

    private String fs1;

    private String fs2;

    private String fs3;

    private String fs4;

    private String fs5;

    private String fs6;

    private String fs7;

    private String fs8;

    private String fs9;

    private String sm0;

    private String sm1;

    private String sm2;

    private String sm3;

    private String sm4;

    private String sm5;

    private String sm6;

    private String sm7;

    private String sm8;

    private String sm9;

    private String by1;

    private String by2;

    private String by3;

    private BigDecimal by4;

    private BigDecimal by5;

    private BigDecimal sxh;

    private String qzzffs;

    private String bca;

    private String zdzfzc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHkgs() {
        return hkgs;
    }

    public void setHkgs(String hkgs) {
        this.hkgs = hkgs == null ? null : hkgs.trim();
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc == null ? null : mc.trim();
    }

    public String getSfzc() {
        return sfzc;
    }

    public void setSfzc(String sfzc) {
        this.sfzc = sfzc == null ? null : sfzc.trim();
    }

    public String getFs0() {
        return fs0;
    }

    public void setFs0(String fs0) {
        this.fs0 = fs0 == null ? null : fs0.trim();
    }

    public String getFs1() {
        return fs1;
    }

    public void setFs1(String fs1) {
        this.fs1 = fs1 == null ? null : fs1.trim();
    }

    public String getFs2() {
        return fs2;
    }

    public void setFs2(String fs2) {
        this.fs2 = fs2 == null ? null : fs2.trim();
    }

    public String getFs3() {
        return fs3;
    }

    public void setFs3(String fs3) {
        this.fs3 = fs3 == null ? null : fs3.trim();
    }

    public String getFs4() {
        return fs4;
    }

    public void setFs4(String fs4) {
        this.fs4 = fs4 == null ? null : fs4.trim();
    }

    public String getFs5() {
        return fs5;
    }

    public void setFs5(String fs5) {
        this.fs5 = fs5 == null ? null : fs5.trim();
    }

    public String getFs6() {
        return fs6;
    }

    public void setFs6(String fs6) {
        this.fs6 = fs6 == null ? null : fs6.trim();
    }

    public String getFs7() {
        return fs7;
    }

    public void setFs7(String fs7) {
        this.fs7 = fs7 == null ? null : fs7.trim();
    }

    public String getFs8() {
        return fs8;
    }

    public void setFs8(String fs8) {
        this.fs8 = fs8 == null ? null : fs8.trim();
    }

    public String getFs9() {
        return fs9;
    }

    public void setFs9(String fs9) {
        this.fs9 = fs9 == null ? null : fs9.trim();
    }

    public String getSm0() {
        return sm0;
    }

    public void setSm0(String sm0) {
        this.sm0 = sm0 == null ? null : sm0.trim();
    }

    public String getSm1() {
        return sm1;
    }

    public void setSm1(String sm1) {
        this.sm1 = sm1 == null ? null : sm1.trim();
    }

    public String getSm2() {
        return sm2;
    }

    public void setSm2(String sm2) {
        this.sm2 = sm2 == null ? null : sm2.trim();
    }

    public String getSm3() {
        return sm3;
    }

    public void setSm3(String sm3) {
        this.sm3 = sm3 == null ? null : sm3.trim();
    }

    public String getSm4() {
        return sm4;
    }

    public void setSm4(String sm4) {
        this.sm4 = sm4 == null ? null : sm4.trim();
    }

    public String getSm5() {
        return sm5;
    }

    public void setSm5(String sm5) {
        this.sm5 = sm5 == null ? null : sm5.trim();
    }

    public String getSm6() {
        return sm6;
    }

    public void setSm6(String sm6) {
        this.sm6 = sm6 == null ? null : sm6.trim();
    }

    public String getSm7() {
        return sm7;
    }

    public void setSm7(String sm7) {
        this.sm7 = sm7 == null ? null : sm7.trim();
    }

    public String getSm8() {
        return sm8;
    }

    public void setSm8(String sm8) {
        this.sm8 = sm8 == null ? null : sm8.trim();
    }

    public String getSm9() {
        return sm9;
    }

    public void setSm9(String sm9) {
        this.sm9 = sm9 == null ? null : sm9.trim();
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

    public BigDecimal getBy4() {
        return by4;
    }

    public void setBy4(BigDecimal by4) {
        this.by4 = by4;
    }

    public BigDecimal getBy5() {
        return by5;
    }

    public void setBy5(BigDecimal by5) {
        this.by5 = by5;
    }

    public BigDecimal getSxh() {
        return sxh;
    }

    public void setSxh(BigDecimal sxh) {
        this.sxh = sxh;
    }

    public String getQzzffs() {
        return qzzffs;
    }

    public void setQzzffs(String qzzffs) {
        this.qzzffs = qzzffs == null ? null : qzzffs.trim();
    }

    public String getBca() {
        return bca;
    }

    public void setBca(String bca) {
        this.bca = bca == null ? null : bca.trim();
    }

    public String getZdzfzc() {
        return zdzfzc;
    }

    public void setZdzfzc(String zdzfzc) {
        this.zdzfzc = zdzfzc == null ? null : zdzfzc.trim();
    }
    
	@Transient
	public List<String> getZffsList() {
		List<String> zffsList = new ArrayList<String>();
		if ("1".equals(this.getFs0())) {
			zffsList.add("0");
		}
		if ("1".equals(this.getFs1())) {
			zffsList.add("1");
		}
		if ("1".equals(this.getFs2())) {
			zffsList.add("2");
		}
		if ("1".equals(this.getFs3())) {
			zffsList.add("3");
		}
		if ("1".equals(this.getFs4())) {
			zffsList.add("4");
		}
		if ("1".equals(this.getFs5())) {
			zffsList.add("5");
		}
		if ("1".equals(this.getFs6())) {
			zffsList.add("6");
		}
		if ("1".equals(this.getFs7())) {
			zffsList.add("7");
		}
		if ("1".equals(this.getFs8())) {
			zffsList.add("8");
		}
		if ("1".equals(this.getFs9())) {
			zffsList.add("9");
		}
		return zffsList;
	}
	
}