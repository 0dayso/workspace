package cn.vetech.vedsb.jp.entity.jpcwgl;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_DZSZ")
public class JpDzsz extends AbstractPageEntity{
	private static final long serialVersionUID = 7686869501881968300L;

	/**格式编号，表的主键*/
	@Id
	@GeneratedValue(generator="no")
	private String gsbh;
	/**格式类型：1-EXCEL,0-TXT*/
	private String gslx;
	/**格式名称*/
	private String gsmc;
	/**起始行：表头开始行数*/
	private Integer qsh;
	/**末尾行：末尾去掉行数*/
	private Integer mwh;
	/**收入列表头名字*/
	private String sr1;
	/**支出列表头名字*/
	private String zc1;
	/**当收入和支出是同一列时 收入项的区分条件*/
	private String tjmc;
	/**收入项的条件值*/
	private String tjnr;
	/**tjz, tjmc1当收入和支出是同一列时 支出项的区分条件*/
	private String tjz;
	/**tjz, tjmc1当收入和支出是同一列时 支出项的区分条件*/
	private String tjmc1;
	/**支出项的条件值*/
	private String tjnr1;
	/**条件值列*/
	private String tjz1;
	/**格式说明*/
	private String bzbz;
	/**创建日期*/
	private Date cjDatetime;
	/**创建人*/
	private String cjUserid;
	/**商户编号*/
	private String shbh;
	/**如果是txt文本格式 那表头列间的分隔符号*/
	private String splitfh;
	/**支付流水号*/
	private String cglsh;
	/**大编码*/
	private String dbm;
	/**是否为淘宝非航旅版账单，1是，0否*/
	private Integer ifsplit;
	/**代扣款账户前缀*/
	private String startchar;
	/**商户订单号*/
	private String shddh;
	/**分给符号*/
	private String splitchar;
	/**代扣款编号顺序*/
	private Integer splitno;
	/**是否系统,1 是,0 不是*/
	private String ifxt;
	/**对账类型，1，采购，2营收*/
	private String dzlx;
	/**合并列*/
	private String hbl;
	/**合并条件，1值相等，0值为空*/
	private String hbtj;
	/**过滤列，多个列时以‘|’隔开*/
	private String gll;
	/**过滤内容*/
	private String gltj;
	/**需合并的金额和数字列*/
	private String szjel;
	/**需合并的文字列*/
	private String wzl;
	/**备注列，淘宝非航旅版用*/
	private String bz;
	/**订单号列*/
	private String ddhl;
	/**网店账单列名与表字段对应，按顺序存*/
	private String wdgsdy;

	public void setGsbh(String gsbh){
		this.gsbh=StringUtils.trim(gsbh);
	}
	public String getGsbh(){
		return this.gsbh;
	}

	public void setGslx(String gslx){
		this.gslx=StringUtils.trim(gslx);
	}
	public String getGslx(){
		return this.gslx;
	}

	public void setGsmc(String gsmc){
		this.gsmc=StringUtils.trim(gsmc);
	}
	public String getGsmc(){
		return this.gsmc;
	}

	public void setQsh(Integer qsh){
		this.qsh=qsh;
	}
	public Integer getQsh(){
		return this.qsh;
	}

	public void setMwh(Integer mwh){
		this.mwh=mwh;
	}
	public Integer getMwh(){
		return this.mwh;
	}

	public void setSr1(String sr1){
		this.sr1=StringUtils.trim(sr1);
	}
	public String getSr1(){
		return this.sr1;
	}

	public void setZc1(String zc1){
		this.zc1=StringUtils.trim(zc1);
	}
	public String getZc1(){
		return this.zc1;
	}

	public void setTjmc(String tjmc){
		this.tjmc=StringUtils.trim(tjmc);
	}
	public String getTjmc(){
		return this.tjmc;
	}

	public void setTjnr(String tjnr){
		this.tjnr=StringUtils.trim(tjnr);
	}
	public String getTjnr(){
		return this.tjnr;
	}

	public void setTjz(String tjz){
		this.tjz=StringUtils.trim(tjz);
	}
	public String getTjz(){
		return this.tjz;
	}

	public void setTjmc1(String tjmc1){
		this.tjmc1=StringUtils.trim(tjmc1);
	}
	public String getTjmc1(){
		return this.tjmc1;
	}

	public void setTjnr1(String tjnr1){
		this.tjnr1=StringUtils.trim(tjnr1);
	}
	public String getTjnr1(){
		return this.tjnr1;
	}

	public void setTjz1(String tjz1){
		this.tjz1=StringUtils.trim(tjz1);
	}
	public String getTjz1(){
		return this.tjz1;
	}

	public void setBzbz(String bzbz){
		this.bzbz=StringUtils.trim(bzbz);
	}
	public String getBzbz(){
		return this.bzbz;
	}

	public void setCjDatetime(Date cjDatetime){
		this.cjDatetime=cjDatetime;
	}
	public Date getCjDatetime(){
		return this.cjDatetime;
	}

	public void setCjUserid(String cjUserid){
		this.cjUserid=StringUtils.trim(cjUserid);
	}
	public String getCjUserid(){
		return this.cjUserid;
	}

	public void setShbh(String shbh){
		this.shbh=StringUtils.trim(shbh);
	}
	public String getShbh(){
		return this.shbh;
	}

	public void setSplitfh(String splitfh){
		this.splitfh=StringUtils.trim(splitfh);
	}
	public String getSplitfh(){
		return this.splitfh;
	}

	public void setCglsh(String cglsh){
		this.cglsh=StringUtils.trim(cglsh);
	}
	public String getCglsh(){
		return this.cglsh;
	}

	public void setDbm(String dbm){
		this.dbm=StringUtils.trim(dbm);
	}
	public String getDbm(){
		return this.dbm;
	}

	public void setIfsplit(Integer ifsplit){
		this.ifsplit=ifsplit;
	}
	public Integer getIfsplit(){
		return this.ifsplit;
	}

	public void setStartchar(String startchar){
		this.startchar=StringUtils.trim(startchar);
	}
	public String getStartchar(){
		return this.startchar;
	}

	public void setShddh(String shddh){
		this.shddh=StringUtils.trim(shddh);
	}
	public String getShddh(){
		return this.shddh;
	}

	public void setSplitchar(String splitchar){
		this.splitchar=StringUtils.trim(splitchar);
	}
	public String getSplitchar(){
		return this.splitchar;
	}

	public void setSplitno(Integer splitno){
		this.splitno=splitno;
	}
	public Integer getSplitno(){
		return this.splitno;
	}

	public void setIfxt(String ifxt){
		this.ifxt=StringUtils.trim(ifxt);
	}
	public String getIfxt(){
		return this.ifxt;
	}

	public void setDzlx(String dzlx){
		this.dzlx=StringUtils.trim(dzlx);
	}
	public String getDzlx(){
		return this.dzlx;
	}

	public void setHbl(String hbl){
		this.hbl=StringUtils.trim(hbl);
	}
	public String getHbl(){
		return this.hbl;
	}

	public void setHbtj(String hbtj){
		this.hbtj=StringUtils.trim(hbtj);
	}
	public String getHbtj(){
		return this.hbtj;
	}

	public void setGll(String gll){
		this.gll=StringUtils.trim(gll);
	}
	public String getGll(){
		return this.gll;
	}

	public void setGltj(String gltj){
		this.gltj=StringUtils.trim(gltj);
	}
	public String getGltj(){
		return this.gltj;
	}

	public void setSzjel(String szjel){
		this.szjel=StringUtils.trim(szjel);
	}
	public String getSzjel(){
		return this.szjel;
	}

	public void setWzl(String wzl){
		this.wzl=StringUtils.trim(wzl);
	}
	public String getWzl(){
		return this.wzl;
	}

	public void setBz(String bz){
		this.bz=StringUtils.trim(bz);
	}
	public String getBz(){
		return this.bz;
	}

	public void setDdhl(String ddhl){
		this.ddhl=StringUtils.trim(ddhl);
	}
	public String getDdhl(){
		return this.ddhl;
	}

	public void setWdgsdy(String wdgsdy){
		this.wdgsdy=StringUtils.trim(wdgsdy);
	}
	public String getWdgsdy(){
		return this.wdgsdy;
	}

}