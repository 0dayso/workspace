package cn.vetech.vedsb.jp.entity.bbzx;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

public class Tpbb extends AbstractPageEntity {
	private static final long serialVersionUID = -3696298510614281041L;
	
		private int	start;                    
		private int	count;                    
		private String	export;                   
		private String	shbh;//商户编号                     
		private String	tjfs;//统计方式       
		private String	rqtj;//日期类型       
		private String	ksrq;//开始日期                     
		private String	jsrq;//结束日期                     
		private String	pnr;//PNR  
		private String  bigpnr;//大编码
		private String	cjr;//乘机人                      
		private String	tkno;//票号              
		private String	cgly; //采购来源                     
		private String	xs_tpzt;//销售退票状态                  
		private String	cg_tpzt;//采购退票状态                  
		private String	gngj;//国内国际                    
		private String	wbdh;//外部退单号
		private String  wbddbh;//外部订单编号
		private String	cg_tkkm;                 
		private String	tflx;  //退废类型                  
		private String	wd_zcdm;                 
		private String	wdid;                    
		private String[]	ykbs; //盈亏标识  
		private String	hbzt; //航班状态 
		private String	ddbh; //订单编号                   
		private String	tp_hclx;                 
		private String	orderBy;
		private String hc;//航程
		private String cg_blr;//采购办理人
		private String xs_blr;//销售办理人
		
		public String getHc() {
			return hc;
		}
		public void setHc(String hc) {
			this.hc = hc;
		}
		public String getBigpnr() {
			return bigpnr;
		}
		public void setBigpnr(String bigpnr) {
			this.bigpnr = bigpnr;
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
		public String getExport() {
			return export;
		}
		public void setExport(String export) {
			this.export = export;
		}
		public String getShbh() {
			return shbh;
		}
		public void setShbh(String shbh) {
			this.shbh = shbh;
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
		public String getPnr() {
			return pnr;
		}
		public void setPnr(String pnr) {
			this.pnr = pnr == null ? "" : StringUtils.trim(pnr);
		}
		public String getCjr() {
			return cjr;
		}
		public void setCjr(String cjr) {
			this.cjr = cjr == null ? "" : StringUtils.trim(cjr);
		}
		public String getTkno() {
			return tkno;
		}
		public void setTkno(String tkno) {
			this.tkno = tkno == null ? "" : StringUtils.trim(tkno);
		}
		public String getCgly() {
			return cgly;
		}
		public void setCgly(String cgly) {
			this.cgly = cgly == null ? "" : StringUtils.trim(cgly);
		}
		public String getXs_tpzt() {
			return xs_tpzt;
		}
		public void setXs_tpzt(String xs_tpzt) {
			this.xs_tpzt = xs_tpzt == null ? "" : StringUtils.trim(xs_tpzt);
		}
		public String getCg_tpzt() {
			return cg_tpzt;
		}
		public void setCg_tpzt(String cg_tpzt) {
			this.cg_tpzt = cg_tpzt == null ? "" : StringUtils.trim(cg_tpzt);
		}
		public String getGngj() {
			return gngj;
		}
		public void setGngj(String gngj) {
			this.gngj = gngj == null ? "" : StringUtils.trim(gngj);
		}
		public String getWbdh() {
			return wbdh;
		}
		public void setWbdh(String wbdh) {
			this.wbdh = wbdh == null ? "" : StringUtils.trim(wbdh);
		}
		
		public String getWbddbh() {
			return wbddbh;
		}
		public void setWbddbh(String wbddbh) {
			this.wbddbh = wbddbh == null ? "" : StringUtils.trim(wbddbh);
		}
		public String getCg_tkkm() {
			return cg_tkkm;
		}
		public void setCg_tkkm(String cg_tkkm) {
			this.cg_tkkm = cg_tkkm == null ? "" : StringUtils.trim(cg_tkkm);
		}
		public String getTflx() {
			return tflx;
		}
		public void setTflx(String tflx) {
			this.tflx = tflx == null ? "" : StringUtils.trim(tflx);
		}
		public String getWd_zcdm() {
			return wd_zcdm;
		}
		public void setWd_zcdm(String wd_zcdm) {
			this.wd_zcdm = wd_zcdm == null ? "" : StringUtils.trim(wd_zcdm);
		}
		public String getWdid() {
			return wdid;
		}
		public void setWdid(String wdid) {
			this.wdid = wdid == null ? "" : StringUtils.trim(wdid);
		}
		
		public String[] getYkbs() {
			return ykbs;
		}
		public void setYkbs(String[] ykbs) {
			this.ykbs = ykbs;
		}
		public String getHbzt() {
			return hbzt;
		}
		public void setHbzt(String hbzt) {
			this.hbzt = hbzt;
		}
		public String getDdbh() {
			return ddbh;
		}
		public void setDdbh(String ddbh) {
			this.ddbh = ddbh == null ? "" : StringUtils.trim(ddbh);
		}
		public String getTp_hclx() {
			return tp_hclx;
		}
		public void setTp_hclx(String tp_hclx) {
			this.tp_hclx = tp_hclx == null ? "" : StringUtils.trim(tp_hclx);
		}
		public String getCg_blr() {
			return cg_blr;
		}
		public void setCg_blr(String cg_blr) {
			this.cg_blr = cg_blr == null ? "" : StringUtils.trim(cg_blr);
		}
		public String getXs_blr() {
			return xs_blr;
		}
		public void setXs_blr(String xs_blr) {
			this.xs_blr = xs_blr == null ? "" : StringUtils.trim(xs_blr);
		} 
		
		@Override
		public String getOrderBy() {
			return orderBy;
		}
		@Override
		public void setOrderBy(String orderBy) {
			this.orderBy = orderBy;
		}
}
