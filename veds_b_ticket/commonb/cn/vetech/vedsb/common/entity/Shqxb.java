package cn.vetech.vedsb.common.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.vetech.core.modules.mybatis.entity.BusinessId;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

import cn.vetech.vedsb.common.service.BYdlog;

/**
 * 商户角色（用户）权限表
 */
@Table(name = "SH_QXB")
public class Shqxb extends AbstractPageEntity {

	private static final long serialVersionUID = -5764856056017596837L;

	@Id
	@GeneratedValue(generator = "no")
	private String id; // ID号主键，序列生成

	@NotBlank
	@BYdlog(name="用户编号",logView=1)
	private String yhbh; // 用户编号或角色编号

	@NotBlank
	@BYdlog(name="模块编号",logView=1)
	private String mkbh; // 模块编号
	@BYdlog(name="功能编号",logView=1)
	private String qxbh; // 功能编号多个用","分开
	@BYdlog(name="权限类别",logView=1)
	private String qxlb; // 权限类别0用户权限1角色权限

	@BusinessId
	@BYdlog(name="商户编号",logView=1)
	private String shbh; // 商户编号

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYhbh() {
		return yhbh;
	}

	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}

	public String getMkbh() {
		return mkbh;
	}

	public void setMkbh(String mkbh) {
		this.mkbh = mkbh;
	}

	public String getQxbh() {
		return qxbh;
	}

	public void setQxbh(String qxbh) {
		this.qxbh = qxbh;
	}

	public String getQxlb() {
		return qxlb;
	}

	public void setQxlb(String qxlb) {
		this.qxlb = qxlb;
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}

}
