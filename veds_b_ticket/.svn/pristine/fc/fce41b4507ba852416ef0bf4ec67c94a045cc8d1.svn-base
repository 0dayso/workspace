package cn.vetech.vedsb.jp.entity.pzzx;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_PZ_OUT")
public class JpPzOut  extends AbstractPageEntity implements Serializable{

	private static final long serialVersionUID = 9035999823888529606L;

	@Id
	@GeneratedValue(generator="no")
	private String outNo;//主键id

    private Date outDatetime;//发放时间

    private String yhbh;//用户编号

    private String bmbh;//部门编号

    private String shbh;//商户编号

    private String startno;//起始号码

    private String endno;//终止号码

    private String bzbz;//备注

    private String officeid;//office号

    private String pzfl;//票证分类，如机票保险等

    private int ffsl;//发放数量

    private int sysl;//剩余数量

    private int shsl;//审核数量

    private String lyBmbh;
    
    private String lyYhbh;
    
    public String getOutNo() {
        return outNo;
    }

    public void setOutNo(String outNo) {
        this.outNo = outNo == null ? null : outNo.trim();
    }

    public Date getOutDatetime() {
        return outDatetime;
    }

    public void setOutDatetime(Date outDatetime) {
        this.outDatetime = outDatetime;
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh == null ? null : yhbh.trim();
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh == null ? null : bmbh.trim();
    }

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh == null ? null : shbh.trim();
    }

    public String getStartno() {
        return startno;
    }

    public void setStartno(String startno) {
        this.startno = startno == null ? null : startno.trim();
    }

    public String getEndno() {
        return endno;
    }

    public void setEndno(String endno) {
        this.endno = endno == null ? null : endno.trim();
    }

    public String getBzbz() {
        return bzbz;
    }

    public void setBzbz(String bzbz) {
        this.bzbz = bzbz == null ? null : bzbz.trim();
    }

    public String getOfficeid() {
        return officeid;
    }

    public void setOfficeid(String officeid) {
        this.officeid = officeid == null ? null : officeid.trim();
    }

    public String getPzfl() {
        return pzfl;
    }

    public void setPzfl(String pzfl) {
        this.pzfl = pzfl == null ? null : pzfl.trim();
    }

	public int getFfsl() {
		return ffsl;
	}

	public void setFfsl(int ffsl) {
		this.ffsl = ffsl;
	}

	public int getSysl() {
		return sysl;
	}

	public void setSysl(int sysl) {
		this.sysl = sysl;
	}

	public int getShsl() {
		return shsl;
	}

	public void setShsl(int shsl) {
		this.shsl = shsl;
	}

	public String getLyBmbh() {
		return lyBmbh;
	}

	public void setLyBmbh(String lyBmbh) {
		this.lyBmbh = lyBmbh;
	}

	public String getLyYhbh() {
		return lyYhbh;
	}

	public void setLyYhbh(String lyYhbh) {
		this.lyYhbh = lyYhbh;
	}

   
}