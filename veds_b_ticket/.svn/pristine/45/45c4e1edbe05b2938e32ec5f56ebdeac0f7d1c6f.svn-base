package cn.vetech.vedsb.jp.entity.qxxgl;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="B_QINFO_RZ")
public class BQinfoRz extends AbstractPageEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="no")
	private String id;
	/**Q信息编号*/
	private String qInfoBh;
	/**查看者商户编号 */
	private String shbh;
	/**查看者部门编号*/
	private String bmbh;
	/**查看者编号*/
	private String yhbh;
	/**查看时间*/
	private String ckDatetime;
	/**操作方式：0查看，1处理，2处理完成*/
	private String type;
	
	private String by1;
	
	private String by2;
	public String getBmbh() {
		return bmbh;
	}

	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getqInfoBh() {
		return qInfoBh;
	}

	public void setqInfoBh(String qInfoBh) {
		this.qInfoBh = qInfoBh;
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}

	public String getYhbh() {
		return yhbh;
	}

	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}

	public String getCkDatetime() {
		return ckDatetime;
	}

	public void setCkDatetime(String ckDatetime) {
		this.ckDatetime = ckDatetime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBy1() {
		return by1;
	}

	public void setBy1(String by1) {
		this.by1 = by1;
	}

	public String getBy2() {
		return by2;
	}

	public void setBy2(String by2) {
		this.by2 = by2;
	}
}
