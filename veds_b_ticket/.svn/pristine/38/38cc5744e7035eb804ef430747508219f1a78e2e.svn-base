package cn.vetech.vedsb.jp.entity.jpcwgl;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_YSDZ_QN")
public class JpYsdzQn extends AbstractPageEntity{
	private static final long serialVersionUID = 7686869501881968300L;

	/**null*/
	@Id
	private String id;
	/**主表ID   WD_YSDZ表id*/
	private String zbid;
	/**网店id*/
	private String wdid;
	/**结果类型   1当日到账金额正确2当日到账金额不正确3当日未到账4历史票今日到账金额正确5历史票今日到账金额不正确6今日漏单7今日票历史到账*/
	private String jglx;
	/**订单类型   1正常单2退票单3改签单4补差单*/
	private String ddlx;
	/**订单编号*/
	private String ddbh;
	/**收银日期*/
	private Date syrq;
	/**供应订单号*/
	private String gyddh;
	/**编码*/
	private String pnrno;
	/**票号*/
	private String tkno;
	/**null*/
	private Double zfje;
	/**金额是否正确   1正确2不正确*/
	private String jesfzq;
	/**是否已出票   0未出1已出 只有今日到账未出票的才可能出现未出，出票后，在对今日票历史对账时将未出改成已出*/
	private String cpSfycp;
	/**出票对账日期*/
	private Date cpDzrq;
	/**出票对账人   存对账人工号加姓名*/
	private String cpDzr;
	/**是否已补单   0未补1已补只有今日漏单的才可能出现已补的情况。*/
	private String bdSfybd;
	/**补单对账日期*/
	private Date bdBdrq;
	/**补单对账人   存对账人工号加姓名*/
	private String bdBdr;
	/**到账状态   0未到账1已到账 当日未到账时为0 到账后再对账时要修改这里的状态*/
	private String dzSfdz;
	/**到账日期*/
	private Date dzDzrq;
	/**到账对账人   存对账人工号加姓名*/
	private String dzDzr;
	/**网店订单号*/
	private String wdDdh;
	/**网店发生金额*/
	private Double wdFsje;
	/**网店账户余额*/
	private String wdZhye;
	/**网店商户id*/
	private String wdShid;
	/**网店入账金额*/
	private String wdRzje;
	/**网店出账金额*/
	private String wdCzje;
	/**网店账务类型*/
	private String wdZwlx;
	/**网店技术服务费*/
	private String wdJsfwf;
	/**网店订单金额*/
	private String wdDdje;
	/**网店业务名称*/
	private String wdYwmc;
	/**null*/
	private String by1;
	/**null*/
	private String by2;
	/**null*/
	private String by3;
	/**null*/
	private String by4;
	/**null*/
	private String by5;
	/**null*/
	private String by6;
	/**商户编号*/
	private String shbh;

	public void setId(String id){
		this.id=StringUtils.trim(id);
	}
	public String getId(){
		return this.id;
	}

	public void setZbid(String zbid){
		this.zbid=StringUtils.trim(zbid);
	}
	public String getZbid(){
		return this.zbid;
	}

	public void setWdid(String wdid){
		this.wdid=StringUtils.trim(wdid);
	}
	public String getWdid(){
		return this.wdid;
	}

	public void setJglx(String jglx){
		this.jglx=StringUtils.trim(jglx);
	}
	public String getJglx(){
		return this.jglx;
	}

	public void setDdlx(String ddlx){
		this.ddlx=StringUtils.trim(ddlx);
	}
	public String getDdlx(){
		return this.ddlx;
	}

	public void setDdbh(String ddbh){
		this.ddbh=StringUtils.trim(ddbh);
	}
	public String getDdbh(){
		return this.ddbh;
	}

	public void setSyrq(Date syrq){
		this.syrq=syrq;
	}
	public Date getSyrq(){
		return this.syrq;
	}

	public void setGyddh(String gyddh){
		this.gyddh=StringUtils.trim(gyddh);
	}
	public String getGyddh(){
		return this.gyddh;
	}

	public void setPnrno(String pnrno){
		this.pnrno=StringUtils.trim(pnrno);
	}
	public String getPnrno(){
		return this.pnrno;
	}

	public void setTkno(String tkno){
		this.tkno=StringUtils.trim(tkno);
	}
	public String getTkno(){
		return this.tkno;
	}

	public void setZfje(Double zfje){
		this.zfje=zfje;
	}
	public Double getZfje(){
		return this.zfje;
	}

	public void setJesfzq(String jesfzq){
		this.jesfzq=StringUtils.trim(jesfzq);
	}
	public String getJesfzq(){
		return this.jesfzq;
	}

	public void setCpSfycp(String cpSfycp){
		this.cpSfycp=StringUtils.trim(cpSfycp);
	}
	public String getCpSfycp(){
		return this.cpSfycp;
	}

	public void setCpDzrq(Date cpDzrq){
		this.cpDzrq=cpDzrq;
	}
	public Date getCpDzrq(){
		return this.cpDzrq;
	}

	public void setCpDzr(String cpDzr){
		this.cpDzr=StringUtils.trim(cpDzr);
	}
	public String getCpDzr(){
		return this.cpDzr;
	}

	public void setBdSfybd(String bdSfybd){
		this.bdSfybd=StringUtils.trim(bdSfybd);
	}
	public String getBdSfybd(){
		return this.bdSfybd;
	}

	public void setBdBdrq(Date bdBdrq){
		this.bdBdrq=bdBdrq;
	}
	public Date getBdBdrq(){
		return this.bdBdrq;
	}

	public void setBdBdr(String bdBdr){
		this.bdBdr=StringUtils.trim(bdBdr);
	}
	public String getBdBdr(){
		return this.bdBdr;
	}

	public void setDzSfdz(String dzSfdz){
		this.dzSfdz=StringUtils.trim(dzSfdz);
	}
	public String getDzSfdz(){
		return this.dzSfdz;
	}

	public void setDzDzrq(Date dzDzrq){
		this.dzDzrq=dzDzrq;
	}
	public Date getDzDzrq(){
		return this.dzDzrq;
	}

	public void setDzDzr(String dzDzr){
		this.dzDzr=StringUtils.trim(dzDzr);
	}
	public String getDzDzr(){
		return this.dzDzr;
	}

	public void setWdDdh(String wdDdh){
		this.wdDdh=StringUtils.trim(wdDdh);
	}
	public String getWdDdh(){
		return this.wdDdh;
	}

	public void setWdFsje(Double wdFsje){
		this.wdFsje=wdFsje;
	}
	public Double getWdFsje(){
		return this.wdFsje;
	}

	public void setWdZhye(String wdZhye){
		this.wdZhye=StringUtils.trim(wdZhye);
	}
	public String getWdZhye(){
		return this.wdZhye;
	}

	public void setWdShid(String wdShid){
		this.wdShid=StringUtils.trim(wdShid);
	}
	public String getWdShid(){
		return this.wdShid;
	}

	public void setWdRzje(String wdRzje){
		this.wdRzje=StringUtils.trim(wdRzje);
	}
	public String getWdRzje(){
		return this.wdRzje;
	}

	public void setWdCzje(String wdCzje){
		this.wdCzje=StringUtils.trim(wdCzje);
	}
	public String getWdCzje(){
		return this.wdCzje;
	}

	public void setWdZwlx(String wdZwlx){
		this.wdZwlx=StringUtils.trim(wdZwlx);
	}
	public String getWdZwlx(){
		return this.wdZwlx;
	}

	public void setWdJsfwf(String wdJsfwf){
		this.wdJsfwf=StringUtils.trim(wdJsfwf);
	}
	public String getWdJsfwf(){
		return this.wdJsfwf;
	}

	public void setWdDdje(String wdDdje){
		this.wdDdje=StringUtils.trim(wdDdje);
	}
	public String getWdDdje(){
		return this.wdDdje;
	}

	public void setWdYwmc(String wdYwmc){
		this.wdYwmc=StringUtils.trim(wdYwmc);
	}
	public String getWdYwmc(){
		return this.wdYwmc;
	}

	public void setBy1(String by1){
		this.by1=StringUtils.trim(by1);
	}
	public String getBy1(){
		return this.by1;
	}

	public void setBy2(String by2){
		this.by2=StringUtils.trim(by2);
	}
	public String getBy2(){
		return this.by2;
	}

	public void setBy3(String by3){
		this.by3=StringUtils.trim(by3);
	}
	public String getBy3(){
		return this.by3;
	}

	public void setBy4(String by4){
		this.by4=StringUtils.trim(by4);
	}
	public String getBy4(){
		return this.by4;
	}

	public void setBy5(String by5){
		this.by5=StringUtils.trim(by5);
	}
	public String getBy5(){
		return this.by5;
	}

	public void setBy6(String by6){
		this.by6=StringUtils.trim(by6);
	}
	public String getBy6(){
		return this.by6;
	}

	public void setShbh(String shbh){
		this.shbh=StringUtils.trim(shbh);
	}
	public String getShbh(){
		return this.shbh;
	}

}