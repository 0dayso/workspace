package cn.vetech.vedsb.jp.entity.jpddgl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vetech.core.modules.utils.VeDate;


public class JpDdBean {
	private List<JpDdCjrBean> jpDdCjrList;
	private List<JpDdHdBean> jpDdHdList;
	/**订单编号主键（JPYYMMDD+序列）*/
	private String ddbh;
	/**国内国际1国内，0国际*/
	private String gngj;
	/**商户编号*/
	private String shbh;
	/**方案ID*/
	private String faid;
	/**网店平台*/
	private String wdpt;
	/**网店ID号*/
	private String wdid;
	/**网店产品类型普通、包机等*/
	private String wd_zclx;
	/**网店订单类型金牌服务等*/
	private String wd_ddlx;
	/**网店政策代码*/
	private String wd_zcdm;
	/**政策渠道Z自有，P平台，G官网等*/
	private String zc_qd;
	/**政策来源如果是P，存平台编号，G存航司二字码*/
	private String zc_ly;
	/**是否比价政策0否，1是*/
	private String zc_sfbj;
	/**政策投放ID*/
	private String zc_tfid;
	/**政策返点*/
	private Double zc_fd;
	/**政策留点*/
	private Double zc_ld;
	/**政策留钱*/
	private Double zc_lq;
	/**外部订单编号*/
	private String wbdh;
	/**NO位时间*/
	private String nosj;
	/**订单状态0待确认，1已订座，2出票中，3已完成,4客户消,5已取消*/
	private String ddzt;
	/**出票锁单人*/
	private String cp_sdr;
	/**出票锁单时间*/
	private String cp_sdsj;
	/**完成办理人*/
	private String wc_blr;
	/**完成办理时间*/
	private String wc_blsj;
	/**收款状态0未收款，1已收款*/
	private String skzt;
	/**收款科目*/
	private String skkm;
	/**联系人*/
	private String nxr;
	/**联系手机*/
	private String nxsj;
	/**联系电话*/
	private String nxdh;
	/**是否邮寄行程单0否，1是*/
	private String sfyjxcd;
	/**收件人*/
	private String sjr;
	/**邮政编码*/
	private String yzbm;
	/**邮寄地址*/
	private String xjdz;
	/**导单时间*/
	private String ddsj;
	/**导单用户*/
	private String ddyh;
	/**导单部门*/
	private String ddbm;
	/**销售PNR_NO*/
	private String xs_pnr_no;
	/**销售PNR状态*/
	private String xs_pnr_zt;
	/**销售航司大编码*/
	private String xs_hkgs_pnr;
	/**航程类型1单程，2往返*/
	private String hclx;
	/**航程*/
	private String hc;
	/**出发日期*/
	private String cfrq;
	/**出发时间*/
	private String cfsj;
	/**乘机人数*/
	private Short cjrs;
	/**乘机人*/
	private String cjr;
	/**销售航班号*/
	private String xs_hbh;
	/**销售舱位*/
	private String xs_cw;
	/**销售账单价*/
	private Long xs_zdj;
	/**销售价*/
	private Double xs_pj;
	/**销售机建*/
	private Long xs_jsf;
	/**销售税费*/
	private Long xs_tax;
	/**销售航意险份数*/
	private short xs_hyxfs;
	/**销售延误险份数*/
	private short xs_ywxfs;
	/**销售航意险利润*/
	private double xs_hyxlr;
	/**销售延误险利润*/
	private double xs_ywxlr;
	/**销售邮寄费*/
	private double xs_yjf;
	/**销售金额*/
	private double xs_je;
	/**采购PNR_NO*/
	private String cg_pnr_no;
	/**采购PNR状态*/
	private String cg_pnr_zt;
	/**采购航司大编码*/
	private String cg_hkgs_pnr;
	/**采购航班号*/
	private String cg_hbh;
	/**采购舱位*/
	private String cg_cw;
	/**采购账单价*/
	private Long cg_zdj;
	/**采购价*/
	private double cg_pj;
	/**采购机建*/
	private Long cg_jsf;
	/**采购税费*/
	private Long cg_tax;
	/**采购来源*/
	private String cgly;
	/**采购单位*/
	private String cgdw;
	/**采购状态*/
	private String cgzt;
	/**采购订单编号*/
	private String cg_ddbh;
	/**采购科目*/
	private String cgkm;
	/**是否主订单空或0是，1否*/
	private String sfzdd;
	/**主订单编号*/
	private String zddbh;
	/**主订单类型来自数据字典*/
	private String zddlx;
	/**降舱标识空或0未降舱，1出票前降舱，2出票后降舱*/
	private String jcbs;
	/**到账审核状态*/
	private String dzsh_zt;
	/**到账审核人*/
	private String dzsh_yhbh;
	/**到账审核部门*/
	private String dzsh_bmbh;
	/**到账审核时间*/
	private String dzsh_datetime;
	/**业务审核状态*/
	private String ywsh_zt;
	/**业务审核人*/
	private String ywsh_yhbh;
	/**业务审核部门*/
	private String ywsh_bmbh;
	/**业务审核时间*/
	private String ywsh_datetime;
	/**修改来源*/
	private String xgly;
	/**最后修改时间*/
	private String xgsj;
	/**最后修改用户*/
	private String xgyh;
	/**邮寄状态,0待打印，1待邮寄，2已邮寄*/
	private String yjzt;
	/**采购邮寄费用*/
	private Double cg_yjf;
	/**邮寄人*/
	private String yjr;
	/**邮寄时间*/
	private String yjsj;
	/**邮寄单号*/
	private String yjdh;
	/**备注*/
	private String bzbz;
	/**紧急程度，0普，1急，2特，3新*/
	private String jjcd;
	/**航司二字码*/
	private String hkgs;
	/**是否手工单，1为手工单，0为非手工单*/
	private String sfsgd;
	/**打印人*/
	private String dyr;
	/**打印时间*/
	private String dysj;
	/**短信状态0表示未发送 1表示已发送*/
	private String dxzt;
	
	private String[] tknoArr;//在一键完成时,保存多个乘机人的票号
	private String[] cjrArr;//在一键完成时,保存多个乘机人的信息
	/*
	public String toXmlString(){
		StringBuffer khddXml = new StringBuffer();
		StringBuffer khddCjrXml = new StringBuffer();
		StringBuffer khddHdXml = new StringBuffer();
		
		Class<?> _class = this.getClass();
		Field[] fields = _class.getDeclaredFields();
		try{
			//1. 获取订单XML
			khddXml.append("<"+BookOrder.KHDDTAG+">");
			for (Field f : fields) {
				Class<?> c = f.getType();
				String typeName = c.getSimpleName();
				if ("String".equals(typeName)) {
					String name = f.getName();
					String value = BeanUtils.getProperty(this, name);
				    if (null != value) {
						khddXml.append(XmlUtils.xmlnode(name.toUpperCase(), value));
					}
				}
			}
			khddXml.append("</"+BookOrder.KHDDTAG+">");
			
			//2. 获取乘机人XML
			Method getCjrListMethod = this.getClass().getMethod("getJpDdCjrList");
	        // 调用getter方法获取属性值
	        List cjrList = (List) getCjrListMethod.invoke(this);
	        if(cjrList != null && !cjrList.isEmpty()){
	        	 for(int i=0; i<cjrList.size(); i++){
	        		JpDdCjrBean jpDdCjrBean = (JpDdCjrBean)cjrList.get(i);
	        		khddCjrXml.append("<"+BookOrder.CJRTAG+">");
	 	        	khddCjrXml.append(jpDdCjrBean.toXmlString());
	 	        	khddCjrXml.append("</"+BookOrder.CJRTAG+">");
	 	        }
	        }
	        
			//3. 获取航段XML
			Method gethdListMethod = this.getClass().getMethod("getJpDdHdList");
	        // 调用getter方法获取属性值
	        List hdList = (List) gethdListMethod.invoke(this);
	        if(hdList != null && !hdList.isEmpty()){
	        	 for(int i=0; i<hdList.size(); i++){
	 	        	JpDdHdBean jpDdHdBean = (JpDdHdBean)hdList.get(i);
	 	        	khddHdXml.append("<"+BookOrder.HDTAG+">");
	 	        	khddHdXml.append(jpDdHdBean.toXmlString());
	 	        	khddHdXml.append("</"+BookOrder.HDTAG+">");
	 	        }
	        }
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return BookOrder.getInsertXml(khddXml.toString(), khddCjrXml.toString(), khddHdXml.toString());
	}
*/
	
	public List<JpDdCjrBean> getJpDdCjrList() {
		return jpDdCjrList;
	}
	public void setJpDdCjrList(List<JpDdCjrBean> jpDdCjrList) {
		this.jpDdCjrList = jpDdCjrList;
	}
	public List<JpDdHdBean> getJpDdHdList() {
		return jpDdHdList;
	}
	public void setJpDdHdList(List<JpDdHdBean> jpDdHdList) {
		this.jpDdHdList = jpDdHdList;
	}
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public String getGngj() {
		return gngj;
	}
	public void setGngj(String gngj) {
		this.gngj = gngj;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String getFaid() {
		return faid;
	}
	public void setFaid(String faid) {
		this.faid = faid;
	}
	public String getWdpt() {
		return wdpt;
	}
	public void setWdpt(String wdpt) {
		this.wdpt = wdpt;
	}
	public String getWdid() {
		return wdid;
	}
	public void setWdid(String wdid) {
		this.wdid = wdid;
	}
	public String getWd_zclx() {
		return wd_zclx;
	}
	public void setWd_zclx(String wd_zclx) {
		this.wd_zclx = wd_zclx;
	}
	public String getWd_ddlx() {
		return wd_ddlx;
	}
	public void setWd_ddlx(String wd_ddlx) {
		this.wd_ddlx = wd_ddlx;
	}
	public String getWd_zcdm() {
		return wd_zcdm;
	}
	public void setWd_zcdm(String wd_zcdm) {
		this.wd_zcdm = wd_zcdm;
	}
	public String getZc_qd() {
		return zc_qd;
	}
	public void setZc_qd(String zc_qd) {
		this.zc_qd = zc_qd;
	}
	public String getZc_ly() {
		return zc_ly;
	}
	public void setZc_ly(String zc_ly) {
		this.zc_ly = zc_ly;
	}
	public String getZc_sfbj() {
		return zc_sfbj;
	}
	public void setZc_sfbj(String zc_sfbj) {
		this.zc_sfbj = zc_sfbj;
	}
	public String getZc_tfid() {
		return zc_tfid;
	}
	public void setZc_tfid(String zc_tfid) {
		this.zc_tfid = zc_tfid;
	}
	public String getWbdh() {
		return wbdh;
	}
	public void setWbdh(String wbdh) {
		this.wbdh = wbdh;
	}
	public String getNosj() {
		return nosj;
	}
	public void setNosj(String nosj) {
		this.nosj = nosj;
	}
	public String getDdzt() {
		return ddzt;
	}
	public void setDdzt(String ddzt) {
		this.ddzt = ddzt;
	}
	public String getCp_sdr() {
		return cp_sdr;
	}
	public void setCp_sdr(String cp_sdr) {
		this.cp_sdr = cp_sdr;
	}
	public String getCp_sdsj() {
		return cp_sdsj;
	}
	public void setCp_sdsj(String cp_sdsj) {
		this.cp_sdsj = cp_sdsj;
	}
	public String getWc_blr() {
		return wc_blr;
	}
	public void setWc_blr(String wc_blr) {
		this.wc_blr = wc_blr;
	}
	public String getWc_blsj() {
		return wc_blsj;
	}
	public void setWc_blsj(String wc_blsj) {
		this.wc_blsj = wc_blsj;
	}
	public String getSkzt() {
		return skzt;
	}
	public void setSkzt(String skzt) {
		this.skzt = skzt;
	}
	public String getSkkm() {
		return skkm;
	}
	public void setSkkm(String skkm) {
		this.skkm = skkm;
	}
	public String getNxr() {
		return nxr;
	}
	public void setNxr(String nxr) {
		this.nxr = nxr;
	}
	public String getNxsj() {
		return nxsj;
	}
	public void setNxsj(String nxsj) {
		this.nxsj = nxsj;
	}
	public String getNxdh() {
		return nxdh;
	}
	public void setNxdh(String nxdh) {
		this.nxdh = nxdh;
	}
	public String getSfyjxcd() {
		return sfyjxcd;
	}
	public void setSfyjxcd(String sfyjxcd) {
		this.sfyjxcd = sfyjxcd;
	}
	public String getSjr() {
		return sjr;
	}
	public void setSjr(String sjr) {
		this.sjr = sjr;
	}
	public String getYzbm() {
		return yzbm;
	}
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	public String getXjdz() {
		return xjdz;
	}
	public void setXjdz(String xjdz) {
		this.xjdz = xjdz;
	}
	public String getDdsj() {
		return ddsj;
	}
	public void setDdsj(String ddsj) {
		this.ddsj = ddsj;
	}
	public String getDdyh() {
		return ddyh;
	}
	public void setDdyh(String ddyh) {
		this.ddyh = ddyh;
	}
	public String getDdbm() {
		return ddbm;
	}
	public void setDdbm(String ddbm) {
		this.ddbm = ddbm;
	}
	public String getXs_pnr_no() {
		return xs_pnr_no;
	}
	public void setXs_pnr_no(String xs_pnr_no) {
		this.xs_pnr_no = xs_pnr_no;
	}
	public String getXs_pnr_zt() {
		return xs_pnr_zt;
	}
	public void setXs_pnr_zt(String xs_pnr_zt) {
		this.xs_pnr_zt = xs_pnr_zt;
	}
	public String getXs_hkgs_pnr() {
		return xs_hkgs_pnr;
	}
	public void setXs_hkgs_pnr(String xs_hkgs_pnr) {
		this.xs_hkgs_pnr = xs_hkgs_pnr;
	}
	public String getHclx() {
		return hclx;
	}
	public void setHclx(String hclx) {
		this.hclx = hclx;
	}
	public String getHc() {
		return hc;
	}
	public void setHc(String hc) {
		this.hc = hc;
	}
	public String getCfrq() {
		return cfrq;
	}
	public void setCfrq(String cfrq) {
		this.cfrq = cfrq;
	}
	public String getCfsj() {
		return cfsj;
	}
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getXs_hbh() {
		return xs_hbh;
	}
	public void setXs_hbh(String xs_hbh) {
		this.xs_hbh = xs_hbh;
	}
	public String getXs_cw() {
		return xs_cw;
	}
	public void setXs_cw(String xs_cw) {
		this.xs_cw = xs_cw;
	}
	public String getCg_pnr_no() {
		return cg_pnr_no;
	}
	public void setCg_pnr_no(String cg_pnr_no) {
		this.cg_pnr_no = cg_pnr_no;
	}
	public String getCg_pnr_zt() {
		return cg_pnr_zt;
	}
	public void setCg_pnr_zt(String cg_pnr_zt) {
		this.cg_pnr_zt = cg_pnr_zt;
	}
	public String getCg_hkgs_pnr() {
		return cg_hkgs_pnr;
	}
	public void setCg_hkgs_pnr(String cg_hkgs_pnr) {
		this.cg_hkgs_pnr = cg_hkgs_pnr;
	}
	public String getCg_hbh() {
		return cg_hbh;
	}
	public void setCg_hbh(String cg_hbh) {
		this.cg_hbh = cg_hbh;
	}
	public String getCg_cw() {
		return cg_cw;
	}
	public void setCg_cw(String cg_cw) {
		this.cg_cw = cg_cw;
	}
	public String getCgly() {
		return cgly;
	}
	public void setCgly(String cgly) {
		this.cgly = cgly;
	}
	public String getCgdw() {
		return cgdw;
	}
	public void setCgdw(String cgdw) {
		this.cgdw = cgdw;
	}
	public String getCgzt() {
		return cgzt;
	}
	public void setCgzt(String cgzt) {
		this.cgzt = cgzt;
	}
	public String getCg_ddbh() {
		return cg_ddbh;
	}
	public void setCg_ddbh(String cg_ddbh) {
		this.cg_ddbh = cg_ddbh;
	}
	public String getCgkm() {
		return cgkm;
	}
	public void setCgkm(String cgkm) {
		this.cgkm = cgkm;
	}
	public String getSfzdd() {
		return sfzdd;
	}
	public void setSfzdd(String sfzdd) {
		this.sfzdd = sfzdd;
	}
	public String getZddbh() {
		return zddbh;
	}
	public void setZddbh(String zddbh) {
		this.zddbh = zddbh;
	}
	public String getZddlx() {
		return zddlx;
	}
	public void setZddlx(String zddlx) {
		this.zddlx = zddlx;
	}
	public String getJcbs() {
		return jcbs;
	}
	public void setJcbs(String jcbs) {
		this.jcbs = jcbs;
	}
	public String getDzsh_zt() {
		return dzsh_zt;
	}
	public void setDzsh_zt(String dzsh_zt) {
		this.dzsh_zt = dzsh_zt;
	}
	public String getDzsh_yhbh() {
		return dzsh_yhbh;
	}
	public void setDzsh_yhbh(String dzsh_yhbh) {
		this.dzsh_yhbh = dzsh_yhbh;
	}
	public String getDzsh_bmbh() {
		return dzsh_bmbh;
	}
	public void setDzsh_bmbh(String dzsh_bmbh) {
		this.dzsh_bmbh = dzsh_bmbh;
	}
	public String getDzsh_datetime() {
		return dzsh_datetime;
	}
	public void setDzsh_datetime(String dzsh_datetime) {
		this.dzsh_datetime = dzsh_datetime;
	}
	public String getYwsh_zt() {
		return ywsh_zt;
	}
	public void setYwsh_zt(String ywsh_zt) {
		this.ywsh_zt = ywsh_zt;
	}
	public String getYwsh_yhbh() {
		return ywsh_yhbh;
	}
	public void setYwsh_yhbh(String ywsh_yhbh) {
		this.ywsh_yhbh = ywsh_yhbh;
	}
	public String getYwsh_bmbh() {
		return ywsh_bmbh;
	}
	public void setYwsh_bmbh(String ywsh_bmbh) {
		this.ywsh_bmbh = ywsh_bmbh;
	}
	public String getYwsh_datetime() {
		return ywsh_datetime;
	}
	public void setYwsh_datetime(String ywsh_datetime) {
		this.ywsh_datetime = ywsh_datetime;
	}
	public String getXgly() {
		return xgly;
	}
	public void setXgly(String xgly) {
		this.xgly = xgly;
	}
	public String getXgsj() {
		return xgsj;
	}
	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}
	public String getXgyh() {
		return xgyh;
	}
	public void setXgyh(String xgyh) {
		this.xgyh = xgyh;
	}
	public String getYjzt() {
		return yjzt;
	}
	public void setYjzt(String yjzt) {
		this.yjzt = yjzt;
	}
	
	public Double getCg_yjf() {
		return cg_yjf;
	}
	
	
	public String getYjr() {
		return yjr;
	}
	public void setYjr(String yjr) {
		this.yjr = yjr;
	}
	public String getYjsj() {
		return yjsj;
	}
	public void setYjsj(String yjsj) {
		this.yjsj = yjsj;
	}
	public String getYjdh() {
		return yjdh;
	}
	public void setYjdh(String yjdh) {
		this.yjdh = yjdh;
	}
	public String getBzbz() {
		return bzbz;
	}
	public void setBzbz(String bzbz) {
		this.bzbz = bzbz;
	}
	public String getJjcd() {
		return jjcd;
	}
	public void setJjcd(String jjcd) {
		this.jjcd = jjcd;
	}
	public String getHkgs() {
		return hkgs;
	}
	public void setHkgs(String hkgs) {
		this.hkgs = hkgs;
	}
	public String getSfsgd() {
		return sfsgd;
	}
	public void setSfsgd(String sfsgd) {
		this.sfsgd = sfsgd;
	}
	public String getDyr() {
		return dyr;
	}
	public void setDyr(String dyr) {
		this.dyr = dyr;
	}
	public String getDysj() {
		return dysj;
	}
	public void setDysj(String dysj) {
		this.dysj = dysj;
	}
	public String getDxzt() {
		return dxzt;
	}
	public void setDxzt(String dxzt) {
		this.dxzt = dxzt;
	}
	
	public void copyDd(JpKhdd jpKhdd) {
		this.setGngj(jpKhdd.getGngj());
		this.setShbh(jpKhdd.getShbh());
		this.setFaid(jpKhdd.getFaid());
		this.setWdpt(jpKhdd.getWdpt());
		this.setWdid(jpKhdd.getWdid());
		this.setWd_zclx(jpKhdd.getWdZclx());
		this.setWd_ddlx(jpKhdd.getWdDdlx());
		this.setWd_zcdm(jpKhdd.getWdZcdm());
		this.setZc_qd(jpKhdd.getZcQd());
		this.setZc_ly(jpKhdd.getZcLy());
		this.setZc_sfbj(jpKhdd.getZcSfbj());
		this.setZc_tfid(jpKhdd.getZcTfid());
		this.setZc_fd(jpKhdd.getZcFd());
		this.setZc_ld(jpKhdd.getZcLd());
		this.setZc_lq(jpKhdd.getZcLq());
		this.setWbdh(jpKhdd.getWbdh());
		this.setNosj(VeDate.dateToStr(jpKhdd.getNosj()));
		this.setDdzt(jpKhdd.getDdzt());
		this.setCp_sdr(jpKhdd.getCpSdr());
		this.setCp_sdsj(VeDate.dateToStr(jpKhdd.getCpSdsj()));
		this.setWc_blr(jpKhdd.getWcBlr());
		this.setWc_blsj(VeDate.dateToStr(jpKhdd.getWcBlsj()));
		this.setSkzt(jpKhdd.getSkzt());
		this.setSkkm(jpKhdd.getSkkm());
		this.setNxr(jpKhdd.getNxr());
		this.setNxsj(jpKhdd.getNxsj());
		this.setNxdh(jpKhdd.getNxdh());
		this.setSfyjxcd(jpKhdd.getSfyjxcd());
		this.setSjr(jpKhdd.getSjr());
		this.setYzbm(jpKhdd.getYzbm());
		this.setXjdz(jpKhdd.getXjdz());
		this.setDdyh(jpKhdd.getDdyh());
		this.setDdbm(jpKhdd.getDdbm());
		this.setXs_pnr_no(jpKhdd.getXsPnrNo());
		this.setXs_pnr_zt(jpKhdd.getXsPnrZt());
		this.setXs_hkgs_pnr(jpKhdd.getXsHkgsPnr());
		this.setHclx(jpKhdd.getHclx());
		this.setHc(jpKhdd.getHc());
		this.setCfrq(VeDate.dateToStr(jpKhdd.getCfrq()));
		this.setCfsj(jpKhdd.getCfsj());
		this.setCjrs(jpKhdd.getCjrs());
		this.setCjr(jpKhdd.getCjr());
		this.setXs_hbh(jpKhdd.getXsHbh());
		this.setXs_cw(jpKhdd.getXsCw());
		this.setXs_zdj(jpKhdd.getXsZdj());
		this.setXs_pj(jpKhdd.getXsPj());
		this.setXs_jsf(jpKhdd.getXsJsf());
		this.setXs_tax(jpKhdd.getXsTax());
		this.setXs_hyxfs(jpKhdd.getXsHyxfs());
		this.setXs_ywxfs(jpKhdd.getXsYwxfs());
		this.setXs_hyxlr(jpKhdd.getXsHyxlr());
		this.setXs_ywxlr(jpKhdd.getXsYwxlr());
		this.setXs_yjf(jpKhdd.getXsYjf());
		this.setXs_je(jpKhdd.getXsJe());
		this.setCg_pnr_no(jpKhdd.getCgPnrNo());
		this.setCg_pnr_zt(jpKhdd.getCgPnrZt());
		this.setCg_hkgs_pnr(jpKhdd.getCgHkgsPnr());
		this.setCg_hbh(jpKhdd.getCgHbh());
		this.setCg_cw(jpKhdd.getCgCw());
		this.setCg_zdj(jpKhdd.getCgZdj());
		this.setCg_pj(jpKhdd.getCgPj());
		this.setCg_jsf(jpKhdd.getCgJsf());
		this.setCg_tax(jpKhdd.getCgTax());
		this.setCgly(jpKhdd.getCgly());
		this.setCgdw(jpKhdd.getCgdw());
		this.setCgzt(jpKhdd.getCgzt());
		this.setCg_ddbh(jpKhdd.getCgDdbh());
		this.setCgkm(jpKhdd.getCgkm());
		this.setSfzdd(jpKhdd.getSfzdd());
		this.setZddbh(jpKhdd.getZddbh());
		this.setZddlx(jpKhdd.getZddlx());
		this.setJcbs(jpKhdd.getJcbs());
		this.setDzsh_zt(jpKhdd.getDzshZt());
		this.setDzsh_yhbh(jpKhdd.getDzshYhbh());
		this.setDzsh_bmbh(jpKhdd.getDzshBmbh());
		this.setDzsh_datetime(VeDate.dateToStr(jpKhdd.getDzshDatetime()));
		this.setYwsh_zt(jpKhdd.getYwshZt());
		this.setYwsh_yhbh(jpKhdd.getYwshYhbh());
		this.setYwsh_bmbh(jpKhdd.getYwshBmbh());
		this.setYwsh_datetime(VeDate.dateToStr(jpKhdd.getYwshDatetime()));
		this.setXgsj(VeDate.dateToStr(jpKhdd.getXgsj()));
		this.setXgyh(jpKhdd.getXgyh());
		this.setYjzt(jpKhdd.getYjzt());
		this.setCg_yjf(jpKhdd.getCgYjf());
		this.setYjr(jpKhdd.getYjr());
		this.setYjsj(VeDate.dateToStr(jpKhdd.getYjsj()));
		this.setDysj(VeDate.dateToStr(jpKhdd.getDysj()));
		this.setYjdh(jpKhdd.getYjdh());
		this.setJjcd(jpKhdd.getJjcd());
		this.setHkgs(jpKhdd.getHkgs());
		this.setSfsgd(jpKhdd.getSfsgd());
		this.setBzbz(jpKhdd.getBzbz());
		this.setDyr(jpKhdd.getDyr());
		this.setDxzt(jpKhdd.getDxzt());
	}
	
	public static void main(String[] args){
//		JpDdBean dd = new JpDdBean();
//		dd.setBzbz("2432");
//		dd.setShbh("XIAOXIN");
//		dd.setCfrq("2016-02-23 23:12:23");
//		
//		List<JpDdCjrBean> l1 = new ArrayList<JpDdCjrBean>();
//		JpDdCjrBean a = new JpDdCjrBean();
//		a.setCg_jsf("1");
//		l1.add(a);
//		
//		JpDdCjrBean a1 = new JpDdCjrBean();
//		a1.setCg_jsf("1");
//		l1.add(a1);
//		dd.setJpDdCjrList(l1);
//		
//		List<JpDdHdBean> l2 = new ArrayList<JpDdHdBean>();
//		JpDdHdBean b = new JpDdHdBean();
//		b.setCfcity("SHA");
//		l2.add(b);
//		
//		JpDdHdBean b1 = new JpDdHdBean();
//		b1.setCfcity("WUH");
//		l2.add(b1);
//		dd.setJpDdHdList(l2);
//		System.out.println(dd.toXmlString());
	}


	public String[] getTknoArr() {
		return tknoArr;
	}
	public void setTknoArr(String[] tknoArr) {
		this.tknoArr = tknoArr;
	}

	public String[] getCjrArr() {
		return cjrArr;
	}
	public void setCjrArr(String[] cjrArr) {
		this.cjrArr = cjrArr;
	}
	
	@SuppressWarnings("unchecked")  
    public static <K, V> Map<K, V> Bean2Map(Object javaBean) {  
        Map<K, V> ret = new HashMap<K, V>();  
        try {  
            Method[] methods = javaBean.getClass().getDeclaredMethods();  
            for (Method method : methods) {  
                if (method.getName().startsWith("get")) {  
                    String field = method.getName();  
                    field = field.substring(field.indexOf("get") + 3);  
                    field = field.toLowerCase().charAt(0) + field.substring(1);  
                    Object value = method.invoke(javaBean, (Object[]) null);
                    if(value != null){
                    	ret.put((K) field, (V) (null == value ? "" : value));
                    }
                }  
            }  
        } catch (Exception e) {  
        }  
        return ret;  
    }


	public Double getZc_fd() {
		return zc_fd;
	}


	public void setZc_fd(Double zc_fd) {
		this.zc_fd = zc_fd;
	}


	public Double getZc_ld() {
		return zc_ld;
	}


	public void setZc_ld(Double zc_ld) {
		this.zc_ld = zc_ld;
	}


	public Double getZc_lq() {
		return zc_lq;
	}


	public void setZc_lq(Double zc_lq) {
		this.zc_lq = zc_lq;
	}

	public Short getCjrs() {
		return cjrs;
	}


	public void setCjrs(Short cjrs) {
		this.cjrs = cjrs;
	}

	public Long getCg_zdj() {
		return cg_zdj;
	}


	public void setCg_zdj(Long cg_zdj) {
		this.cg_zdj = cg_zdj;
	}


	public double getCg_pj() {
		return cg_pj;
	}


	public void setCg_pj(double cg_pj) {
		this.cg_pj = cg_pj;
	}


	public Long getCg_jsf() {
		return cg_jsf;
	}


	public void setCg_jsf(Long cg_jsf) {
		this.cg_jsf = cg_jsf;
	}


	public Long getCg_tax() {
		return cg_tax;
	}


	public void setCg_tax(Long cg_tax) {
		this.cg_tax = cg_tax;
	}


	public Long getXs_zdj() {
		return xs_zdj;
	}


	public void setXs_zdj(Long xs_zdj) {
		this.xs_zdj = xs_zdj;
	}

	public Long getXs_jsf() {
		return xs_jsf;
	}


	public void setXs_jsf(Long xs_jsf) {
		this.xs_jsf = xs_jsf;
	}


	public Long getXs_tax() {
		return xs_tax;
	}


	public void setXs_tax(Long xs_tax) {
		this.xs_tax = xs_tax;
	}


	public double getXs_hyxlr() {
		return xs_hyxlr;
	}


	public void setXs_hyxlr(double xs_hyxlr) {
		this.xs_hyxlr = xs_hyxlr;
	}


	public double getXs_ywxlr() {
		return xs_ywxlr;
	}


	public void setXs_ywxlr(double xs_ywxlr) {
		this.xs_ywxlr = xs_ywxlr;
	}


	public double getXs_yjf() {
		return xs_yjf;
	}


	public void setXs_yjf(double xs_yjf) {
		this.xs_yjf = xs_yjf;
	}


	public double getXs_je() {
		return xs_je;
	}


	public void setXs_je(double xs_je) {
		this.xs_je = xs_je;
	}


	public short getXs_hyxfs() {
		return xs_hyxfs;
	}


	public void setXs_hyxfs(short xs_hyxfs) {
		this.xs_hyxfs = xs_hyxfs;
	}


	public short getXs_ywxfs() {
		return xs_ywxfs;
	}


	public void setXs_ywxfs(short xs_ywxfs) {
		this.xs_ywxfs = xs_ywxfs;
	}
	public Double getXs_pj() {
		return xs_pj;
	}
	public void setXs_pj(Double xs_pj) {
		this.xs_pj = xs_pj;
	}
	public void setCg_yjf(Double cg_yjf) {
		this.cg_yjf = cg_yjf;
	}


}
