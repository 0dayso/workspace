package cn.vetech.vedsb.jp.entity.b2bsz;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
/**
 * B2B支付方式
 * @author wangshengliang
 *
 */
@Table(name="JP_B2B_ZFFS")
public class JpB2bZffs extends AbstractPageEntity implements Serializable{

	private static final long serialVersionUID = 8543959333842238778L;
	@Id
	@GeneratedValue(generator="no")
	private String id;

    private String shbh;

    private String zffsB2b;

    private String zffsXt;

    private String zfkmXt;

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

    public String getZffsB2b() {
        return zffsB2b;
    }

    public void setZffsB2b(String zffsB2b) {
        this.zffsB2b = zffsB2b == null ? null : zffsB2b.trim();
    }

    public String getZffsXt() {
        return zffsXt;
    }

    public void setZffsXt(String zffsXt) {
        this.zffsXt = zffsXt == null ? null : zffsXt.trim();
    }

    public String getZfkmXt() {
        return zfkmXt;
    }

    public void setZfkmXt(String zfkmXt) {
        this.zfkmXt = zfkmXt == null ? null : zfkmXt.trim();
    }
}