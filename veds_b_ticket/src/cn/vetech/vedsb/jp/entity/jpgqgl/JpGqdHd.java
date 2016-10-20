package cn.vetech.vedsb.jp.entity.jpgqgl;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="jp_gqd_hd")
public class JpGqdHd extends AbstractPageEntity implements Serializable{
	private static final long serialVersionUID = -4217895707632356763L;

	/**
	 * 	ID号主键
	 */
	@Id
	@GeneratedValue(generator="no")
    private String id;
	
	/**
	 * 改签单号
	 */
    private String gqdh;

    /**
	 * 商户编号
	 */
    private String shbh;

    /**
	 * 顺序号
	 */
    private Short sxh;

    /**
	 * 出发城市
	 */
    private String cfcity;

    /**
	 * 到达城市
	 */
    private String ddcity;

    /**
   	 * 机票航段id
   	 */
    private String jphdid;

    /**
   	 * 原出发时间
   	 */
    private String oCfsj;

    /**
   	 * 原到达时间
   	 */
    private String oDdsj;

    /**
   	 * 原出发航站楼
   	 */
    private String oCfhzl;

    /**
   	 * 原到达航站楼
   	 */
    private String oDdhzl;

    /**
   	 * 原飞机机型
   	 */
    private String oFjjx;

    /**
   	 * 原航段状态
   	 */
    private String oHdzt;

    /**
   	 * 原销售航班号
   	 */
    private String oXsHbh;

    /**
   	 * 原销售舱位
   	 */
    private String oXsCw;

    /**
   	 * 原销售退改签
   	 */
    private String oXsTgq;

    /**
   	 * 原采购航班号
   	 */
    private String oCgHbh;

    /**
   	 * 原采购舱位
   	 */
    private String oCgCw;

    /**
   	 * 原采购退改签
   	 */
    private String oCgTgq;

    /**
     * 新出发时间
     */
    private String nCfsj;

    /**
     * 新到达时间
     */
    private String nDdsj;

    /**
     * 新出发航站楼
     */
    private String nCfhzl;

    /**
     * 新到达航站楼
     */
    private String nDdhzl;

    /**
     * 新飞机机型
     */
    private String nFjjx;

    /**
     * 新航段状态
     */
    private String nHdzt;

    /**
     * 新销售航班号
     */
    private String nXsHbh;

    /**
     * 新销售舱位
     */
    private String nXsCw;

    /**
     * 新销售退改签
     */
    private String nXsTgq;

    /**
     * 新采购航班号
     */
    private String nCgHbh;

    /**
     * 新采购舱位
     */
    private String nCgCw;

    /**
     * 新采购退改签
     */
    private String nCgTgq;
    
    /**
     * 外部顺序号
     */
    private String wbsxh;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGqdh() {
		return gqdh;
	}

	public void setGqdh(String gqdh) {
		this.gqdh = gqdh;
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}

	public Short getSxh() {
		return sxh;
	}

	public void setSxh(Short sxh) {
		this.sxh = sxh;
	}

	public String getCfcity() {
		return cfcity;
	}

	public void setCfcity(String cfcity) {
		this.cfcity = cfcity;
	}

	public String getDdcity() {
		return ddcity;
	}

	public void setDdcity(String ddcity) {
		this.ddcity = ddcity;
	}

	public String getJphdid() {
		return jphdid;
	}

	public void setJphdid(String jphdid) {
		this.jphdid = jphdid;
	}

	public String getoCfsj() {
		return oCfsj;
	}

	public void setoCfsj(String oCfsj) {
		this.oCfsj = oCfsj;
	}

	public String getoDdsj() {
		return oDdsj;
	}

	public void setoDdsj(String oDdsj) {
		this.oDdsj = oDdsj;
	}

	public String getoCfhzl() {
		return oCfhzl;
	}

	public void setoCfhzl(String oCfhzl) {
		this.oCfhzl = oCfhzl;
	}

	public String getoDdhzl() {
		return oDdhzl;
	}

	public void setoDdhzl(String oDdhzl) {
		this.oDdhzl = oDdhzl;
	}

	public String getoFjjx() {
		return oFjjx;
	}

	public void setoFjjx(String oFjjx) {
		this.oFjjx = oFjjx;
	}

	public String getoHdzt() {
		return oHdzt;
	}

	public void setoHdzt(String oHdzt) {
		this.oHdzt = oHdzt;
	}

	public String getoXsHbh() {
		return oXsHbh;
	}

	public void setoXsHbh(String oXsHbh) {
		this.oXsHbh = oXsHbh;
	}

	public String getoXsCw() {
		return oXsCw;
	}

	public void setoXsCw(String oXsCw) {
		this.oXsCw = oXsCw;
	}

	public String getoXsTgq() {
		return oXsTgq;
	}

	public void setoXsTgq(String oXsTgq) {
		this.oXsTgq = oXsTgq;
	}

	public String getoCgHbh() {
		return oCgHbh;
	}

	public void setoCgHbh(String oCgHbh) {
		this.oCgHbh = oCgHbh;
	}

	public String getoCgCw() {
		return oCgCw;
	}

	public void setoCgCw(String oCgCw) {
		this.oCgCw = oCgCw;
	}

	public String getoCgTgq() {
		return oCgTgq;
	}

	public void setoCgTgq(String oCgTgq) {
		this.oCgTgq = oCgTgq;
	}

	public String getnCfsj() {
		return nCfsj;
	}

	public void setnCfsj(String nCfsj) {
		this.nCfsj = nCfsj;
	}

	public String getnDdsj() {
		return nDdsj;
	}

	public void setnDdsj(String nDdsj) {
		this.nDdsj = nDdsj;
	}

	public String getnCfhzl() {
		return nCfhzl;
	}

	public void setnCfhzl(String nCfhzl) {
		this.nCfhzl = nCfhzl;
	}

	public String getnDdhzl() {
		return nDdhzl;
	}

	public void setnDdhzl(String nDdhzl) {
		this.nDdhzl = nDdhzl;
	}

	public String getnFjjx() {
		return nFjjx;
	}

	public void setnFjjx(String nFjjx) {
		this.nFjjx = nFjjx;
	}

	public String getnHdzt() {
		return nHdzt;
	}

	public void setnHdzt(String nHdzt) {
		this.nHdzt = nHdzt;
	}

	public String getnXsHbh() {
		return nXsHbh;
	}

	public void setnXsHbh(String nXsHbh) {
		this.nXsHbh = nXsHbh;
	}

	public String getnXsCw() {
		return nXsCw;
	}

	public void setnXsCw(String nXsCw) {
		this.nXsCw = nXsCw;
	}

	public String getnXsTgq() {
		return nXsTgq;
	}

	public void setnXsTgq(String nXsTgq) {
		this.nXsTgq = nXsTgq;
	}

	public String getnCgHbh() {
		return nCgHbh;
	}

	public void setnCgHbh(String nCgHbh) {
		this.nCgHbh = nCgHbh;
	}

	public String getnCgCw() {
		return nCgCw;
	}

	public void setnCgCw(String nCgCw) {
		this.nCgCw = nCgCw;
	}

	public String getnCgTgq() {
		return nCgTgq;
	}

	public void setnCgTgq(String nCgTgq) {
		this.nCgTgq = nCgTgq;
	}

	public String getWbsxh() {
		return wbsxh;
	}

	public void setWbsxh(String wbsxh) {
		this.wbsxh = wbsxh;
	}
    
    
}