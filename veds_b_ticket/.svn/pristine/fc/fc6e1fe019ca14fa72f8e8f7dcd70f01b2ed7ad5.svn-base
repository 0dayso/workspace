package cn.vetech.vedsb.jp.entity.jpddsz;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.VeDate;
/**
 * 
 * <订单的签注>
 * <签注表>
 * 
 * @author  vetech
 * @version  [版本号, 2016-4-6]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
@Table(name="JP_QZ")
public class JpQz extends AbstractPageEntity {
	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="no")
    private String id;

    private String shbh;

    private String ywlx;

    private String ywdh;

    private String qzNr;

    private String qzYhbh;

    private String qzBmbh;

    private Date qzSj;
    
    @Transient
    private String qzSjstr;

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

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx == null ? null : ywlx.trim();
    }

    public String getYwdh() {
        return ywdh;
    }

    public void setYwdh(String ywdh) {
        this.ywdh = ywdh == null ? null : ywdh.trim();
    }

    public String getQzNr() {
        return qzNr;
    }

    public void setQzNr(String qzNr) {
        this.qzNr = qzNr == null ? null : qzNr.trim();
    }

    public String getQzYhbh() {
        return qzYhbh;
    }

    public void setQzYhbh(String qzYhbh) {
        this.qzYhbh = qzYhbh == null ? null : qzYhbh.trim();
    }

    public String getQzBmbh() {
        return qzBmbh;
    }

    public void setQzBmbh(String qzBmbh) {
        this.qzBmbh = qzBmbh == null ? null : qzBmbh.trim();
    }

    public Date getQzSj() {
        return qzSj;
    }
   
    public void setQzSj(Date qzSj) {
        this.qzSj = qzSj;
    }

	public String getQzSjstr() {
		qzSjstr = VeDate.dateToStrLong(this.qzSj);
    	Date today=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	String tod=format.format(today);
		if(StringUtils.isNotBlank(qzSjstr) && qzSjstr.length()>17){
			if(qzSjstr.indexOf(tod)>=0){
				qzSjstr ="今天";
			}else if((today.getTime()-qzSj.getTime())<40*60*60*1000){
				qzSjstr ="昨天";
			}else{
				qzSjstr = qzSjstr.substring(5,16);
			}
		}
		return qzSjstr;
	}

	public void setQzSjstr(String qzSjstr) {
		this.qzSjstr = qzSjstr;
	}
    
}