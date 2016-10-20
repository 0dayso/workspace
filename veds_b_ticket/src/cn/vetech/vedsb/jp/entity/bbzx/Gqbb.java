package cn.vetech.vedsb.jp.entity.bbzx;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

public class Gqbb extends AbstractPageEntity {
	private static final long serialVersionUID = 1L;
	private String tjfs;// 统计方式
	private String rqtj;//日期条件
	private String ksrq;// 开始日期
	private String jsrq;// 结束日期
	private String xspnrno;// 销售pnr
	private String tkno;// 票号
	private String cjr;//乘机人
	private String cgly;// 采购来源
	private String gqzt;//改签状态
	private String gqlx;//改签类型
	private String hc;// 航程
	private String xshbh;//航班号
	private String wdid;// 网店
	private String wbdh;// 外部单号
	private String wbddbh;//外部订单号
	private String wdzcdm;// 网店政策代码
	private String gngj;// 国内国际
	private String shbh;// 商户编号
	private String yhbh;//用户编号
	private String shbmid;//商户部门ID
	private int start;// 分页起始记录
	private int count;// 分页条数
	
	public String getWbddbh() {
		return wbddbh;
	}
	public void setWbddbh(String wbddbh) {
		this.wbddbh = wbddbh == null ? "" : StringUtils.trim(wbddbh);
	}
	public String getYhbh() {
		return yhbh;
	}
	public void setYhbh(String yhbh) {
		this.yhbh = yhbh == null ? "" : StringUtils.trim(yhbh);
	}
	public String getShbmid() {
		return shbmid;
	}
	public void setShbmid(String shbmid) {
		this.shbmid = shbmid == null ? "" : StringUtils.trim(shbmid);
	}
	private String	export;
	
	public String getExport() {
		return export;
	}
	public void setExport(String export) {
		this.export = export;
	}
	public String getTjfs() {
		return tjfs;
	}
	public void setTjfs(String tjfs) {
		this.tjfs = tjfs == null ? "" : StringUtils.trim(tjfs);
	}
	public String getRqtj() {
		return rqtj;
	}
	public void setRqtj(String rqtj) {
		this.rqtj = rqtj == null ? "" : StringUtils.trim(rqtj);
	}
	public String getKsrq() {
		return ksrq;
	}
	public void setKsrq(String ksrq) {
		this.ksrq = ksrq == null ? "" : StringUtils.trim(ksrq);
	}
	public String getJsrq() {
		return jsrq;
	}
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq == null ? "" : StringUtils.trim(jsrq);
	}
	public String getXspnrno() {
		return xspnrno;
	}
	public void setXspnrno(String xspnrno) {
		this.xspnrno = xspnrno == null ? "" : StringUtils.trim(xspnrno);
	}
	public String getTkno() {
		return tkno;
	}
	public void setTkno(String tkno) {
		this.tkno = tkno == null ? "" : StringUtils.trim(tkno);
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr == null ? "" : StringUtils.trim(cjr);
	}
	public String getCgly() {
		return cgly;
	}
	public void setCgly(String cgly) {
		this.cgly = cgly == null ? "" : StringUtils.trim(cgly);
	}
	public String getGqzt() {
		return gqzt;
	}
	public void setGqzt(String gqzt) {
		this.gqzt = gqzt == null ? "" : StringUtils.trim(gqzt);
	}
	public String getGqlx() {
		return gqlx;
	}
	public void setGqlx(String gqlx) {
		this.gqlx = gqlx == null ? "" : StringUtils.trim(gqlx);
	}
	public String getHc() {
		return hc;
	}
	public void setHc(String hc) {
		this.hc = hc == null ? "" : StringUtils.trim(hc);
	}
	public String getXshbh() {
		return xshbh;
	}
	public void setXshbh(String xshbh) {
		this.xshbh = xshbh == null ? "" : StringUtils.trim(xshbh);
	}
	public String getWdid() {
		return wdid;
	}
	public void setWdid(String wdid) {
		this.wdid = wdid == null ? "" : StringUtils.trim(wdid);
	}
	public String getWbdh() {
		return wbdh;
	}
	public void setWbdh(String wbdh) {
		this.wbdh = wbdh == null ? "" : StringUtils.trim(wbdh);
	}
	public String getWdzcdm() {
		return wdzcdm;
	}
	public void setWdzcdm(String wdzcdm) {
		this.wdzcdm = wdzcdm == null ? "" : StringUtils.trim(wdzcdm);
	}
	public String getGngj() {
		return gngj;
	}
	public void setGngj(String gngj) {
		this.gngj = gngj == null ? "" : StringUtils.trim(gngj);
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh == null ? "" : StringUtils.trim(shbh);
	}
	@Override
	public int getStart() {
		return start;
	}
	@Override
	public void setStart(int start) {
		this.start = start;
	}
	@Override
	public int getCount() {
		return count;
	}
	@Override
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
