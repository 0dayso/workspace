package cn.vetech.vedsb.jp.entity.cpsz;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_BSP_TPSZ")
public class JpBspTpsz  extends AbstractPageEntity implements Serializable {
	private static final long serialVersionUID = -7639177995170512954L;

	@Id
	@GeneratedValue(generator="no")
    private String id;

    private String shbh;

    private String yzzjhm;

    private String sfsbtx;

    private String dxtx;

    private String yjtx;

    private String zl;

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

    public String getYzzjhm() {
        return yzzjhm;
    }

    public void setYzzjhm(String yzzjhm) {
        this.yzzjhm = yzzjhm == null ? null : yzzjhm.trim();
    }

    public String getSfsbtx() {
        return sfsbtx;
    }

    public void setSfsbtx(String sfsbtx) {
        this.sfsbtx = sfsbtx == null ? null : sfsbtx.trim();
    }

    public String getDxtx() {
        return dxtx;
    }

    public void setDxtx(String dxtx) {
        this.dxtx = dxtx == null ? null : dxtx.trim();
    }

    public String getYjtx() {
        return yjtx;
    }

    public void setYjtx(String yjtx) {
        this.yjtx = yjtx == null ? null : yjtx.trim();
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl == null ? null : zl.trim();
    }
}