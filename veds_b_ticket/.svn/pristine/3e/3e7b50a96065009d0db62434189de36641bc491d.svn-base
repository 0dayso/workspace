package cn.vetech.vedsb.platpolicy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.utils.PlatCode;

public class PolicyItem implements Serializable{

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = -4128819033321279939L;

	private String id;// 政策id

	private String ptzcbs;// 平台代号
	
	private String ptmc;// 平台名称

	private Double rate;// 返点 这个返点数是乘以 100了的
	
	private Double backrate;
	
	private Double backfee;

	// 页面显示
	private Double rateshow;// 能看到的返点 这个返点数是乘以 100了的

	private Double showxjjlfl;// 分销下级奖励费率  最终实际的奖励费率，通过 rateshow ，留钱。返现后  反算得到

	private Double showsjdlfl;// 上级代理费率  最终实际的奖励费率，通过 rate ，留钱。返现后  反算得到

	private Double lq;// 服务费

	private Double payfee;// 支付金额 结算价+机建+税费


	private Double xsj;// 销售价

	private Double jsf;// 机建

	private Double tax;// 税费

	private String payconfig;// 今日 分销商出每张几元交易费=1, 分销商出百分比交易费=2,

	private String agentfee;// 今日 该值根据交易费配置不同而定，如：分销商出每张几元交易费=1元；分销商出百分比交易费=0.001

	private Double paypoundage;// 支付手续费

	private Double profit;// 下级利润  下级奖励   销售价*下级费率+客户留款
	
	private Double superProfit;// 上级佣金   账单价*上级费率-平台控润留钱
	

	//1.支付宝  2.汇付 6.IPS 7.德付通 1267支付宝、汇付、IPS和德付通
	private String paytype;// 支付种类

	private String isspecmark;// 特殊政策 =0 普通政策 =1 特殊政策 2是所有

	private String changerecord;// 是否更换编码出票 1需要换编码 0 不需要

	private String worktime;// 供应商上班时间

	private String chooseOutWorkTime;// 废票时间

	private String note;// 备注

	private String aircome;// 航空公司二字码

	private String cabin;// 舱位

	private String sdate, edate;// 出发日期，到达日期

	private String office;// 供应商office号

	private int showseq; //平台显示顺序

	private String recommen;// 是否推荐平台

	private String recommentxt;//推荐说明 

	private String jgxx;//特价或特殊政策时才返回。1价格以PAT为准，0价格以政策为

	private String zclx;//1 普通政策 2 K位政策 3 免票政策 4 其它政策 5 特价政策【含新版特价】 6精准营销价格类 7淘宝
	
	private Integer tszclx;//特殊政策类型 1K位(政策类型为特价时表示新版特价) 2免票 3其他 
	
	private String hclx;//CPS 时设置 

	private String shmc;// CPS商户名称  供应商名称saleBusinessName 实名时返回供应商名称，匿名时返回匿名
	private String shbh;// CPS商户编号

	private Double pj_sjjsj;// 上级结算价

	private Double pj_xjjsj;// 下级结算价

	private String billSaleMatch;//票面销售价是否相符 1不相符 0相符 

	private int bxfs;// 保险份数

	private double bxje;// 保险金额

	private String bxxz;// 保险险种

	private Double pj_cgj;// 对应客户订单上的账单价,如果以PAT为准的政策，且PJ_PAT>0,则等同于PJ_PAT
	
	private Double pj_cgj_et;// 如果国际票，表示国际票儿童账单价

	private Double pj_pat;// 对应客户订单上的PJ_PAT 放在页面，方便查看问题

	private String assurePlan;//保证计划编号，多个以“|”隔开

	private String assurePlanName;//保障计划名称

	private String assurePlanIcon;//保障计划图标 绝对地址

	private int appraiseCount;//评价总次数

	private int dealCount;//成交总次数
	
	
	private String zcpj;//政策票价 政策适用的票价 8000YI CPS
	
	private Double total_zcpj;//总政策票价 政策适用的票价 8000YI CPS
	
	private String smnm;//是否匿名 不为空表示匿名 为空表示实名
	
	private String tgqgd;//退改签规定 目前CPS有设置
	
	private int crs;//成人或儿童数  国际票表示成人数
	
	private int ets;//国际票儿童数
	
	private String facetype;//国际票接口类型
	
	/**
	 * 是否限制白名单 0不限制 1限制
	 */
	private String sfxzbmd;
    private String xzbmdlx;//限制白名单类型，此属性和sfxzbmd联合使用 为1表示100%白名单验证 为2表示ASMS白名单验证 0或者空等同于不限制白名单
	
	private String sp;//酷讯专用 跳转参数，用于回传
	
	private String siteid;//酷讯专用 站点号，用于回传
	
	private double cps_payRates;//cps贴点  乘以100后的结果
    
    private double cps_payMoney;//cps贴钱  （元）
    
    private String cps_tdgz_id;//贴点规则id   主表id_明细id
    
    private String payment;//匹配贴点后绑定的支付方式代号，多个以/隔开；为空表示不限制
    
    private String sfqyzd;//是否启用追点 1启用 0或空不启用
    private double zdfd;//最低返点
    private double zgfd;//最高返点
    private double fgfd;//覆盖返点
    
    private String sfyjzd;//是否已经追点 1表示是，空或0表示否 2表示是新加到缓存里的
    private String sfyjtd;//是否已经贴点 1表示旧政策已经贴点，2表示新政策已经贴点，空或0表示否
    
    private double catchPoint;//追点后的点
    
    private double contrPoint;//平台控点【贴点控下的返点】
    private double contrMoney;//平台控钱【贴点控下的返点】
    
    private double saleServicePrice;
    private String saleServiceWay;
    private String saleServiceCjr;
    private String saleServiceId;
    
    
    private String otherParam;//CPS参数
    
    private String pcNumber;//批次号 ,每次点出都是一样,平台政策收集有用
    
    private String cps_defPayMethod;//采购默认代扣支付方式代号 多个逗号隔开
    
    public Map<String, String[]> cps_payCompMsgMap=new HashMap<String, String[]>();//key 代扣支付方式的代号 str[0]代扣支付方式名称 str[1]代扣支付方式地址
    
    private String receiveMethod;//供应收款方式
    
    private Map<String, String> otherParamMap = new HashMap<String, String>();//针对CPS政策存放获取实时政策接口返回的参数信息
    
    private List<PolicyItem> otherPolicyList;//绑定不同支付方式的政策集合
    
    private String titleable;
    
    private Map<String,Object> otherMap;//保存其他参数 目前适用于CPSLINK-TB政策
    
    private String cpsSortNum = "2";//控制政策显示二次排序字段
    
    private String cjrlx;//适用乘机人类型【CPS政策收集报表使用】
    
    private String zcfd;//CPS政策原始返点【CPS政策收集报表使用】
    
    private String matchWL;//满足白名单情况【集中出票验证白名单后结果分类】 0完全满足 1部分满足 2完全不满足
    
    private String noMatchWLCjr;//matchWL为1部分满足时，部分满足的乘机人ID，多个以逗号隔开
    private String noMatchWLCjrxm;//matchWL为1部分满足时，部分满足的乘机人姓名，多个以逗号隔开
    
    private String noteStr;//退改签规定字符串【写入订单航段表/订单政策表】【退票规则编号/改签规则编号/退改签基准(1按票面 2按全价F/C/Y 3按舱位公布运价)/计算退票时对应的舱位】
    
    private String hide;//政策在页面展示时是否影藏: 1是 0否
    private double hfje;//后返金额
    public String getCjrlx() {
        return this.cjrlx;
    }
    
    public void setCjrlx(String cjrlx) {
        this.cjrlx = cjrlx;
    }
    
    public String getZcfd() {
        return this.zcfd;
    }
    
    public void setZcfd(String zcfd) {
        this.zcfd = zcfd;
    }

    public String getCpsSortNum() {
		return cpsSortNum;
	}
	public void setCpsSortNum(String cpsSortNum) {
		this.cpsSortNum = cpsSortNum;
	}
	
    public String getTitleable() {
		return titleable;
	}
	public void setTitleable(String titleable) {
		this.titleable = titleable;
	}
	public int getMultPayCnt() {
    	if(CollectionUtils.isEmpty(otherPolicyList)){
    		return 0;
    	}
    	return otherPolicyList.size();
    }
    
    private String cacheData;//缓存数据，用于存放各种支付方式贴点信息
    
    private String specialRequest;//精准营销特殊说明
    
    public String getSpecialRequest() {
		return specialRequest;
	}
	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}
	//CPS平台按指定代扣方式的贴点规则能否代扣 1不能 其他能
    public String getCps_tdzc_zddkfs_nfdk() {
    	try{
			if("2".equals(sfyjtd)&&StringUtils.isNotBlank(payment)){//已经贴点且 贴点有指定支付方式
				if(StringUtils.isBlank(cps_defPayMethod)){//如果商户没有开启代扣，则不能代扣
					return "1";
				}else{//商户开启了代扣
					boolean b=false;
					String [] payment_arr=payment.split("/");
					String [] cps_defPayMethod_arr=cps_defPayMethod.split(",");
					for (String payment_tmp:payment_arr) {
						if(!b){
							for (String cps_defPayMethod_tmp:cps_defPayMethod_arr) {
								if(cps_defPayMethod_tmp.indexOf(payment_tmp)>-1){//商户代扣方式包含政策上代扣方式
									b=true;
									break;
								}
								
							}
						}
					}
					if(!b){
						return "1";
					}
				}
			}
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
	}
    //CPS指定代扣方式的贴点政策中所指定的支付方式名称
    public String getCps_tdzc_zddkfs_mc() {
    	String dkfsmc="";
    	try{
	    	if("2".equals(sfyjtd)&&StringUtils.isNotBlank(payment)){//已经贴点且 贴点有指定支付方式
	    		String [] payment_arr=payment.split("/");
	    		for (String payment_tmp:payment_arr) {
	    			dkfsmc+=cps_payCompMsgMap.get(payment_tmp)[0]+",";
	    		}
	    		if(StringUtils.isNotBlank(dkfsmc)){
	    			dkfsmc=dkfsmc.substring(0,dkfsmc.length()-1);
	    		}
	    	}
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return dkfsmc;
    }
    //CPS指定代扣方式的贴点政策中所指定的支付方式名称
    public String getCps_tdzc_zddkfs_tb() {
    	String dkfstb="";
    	try{
	    	if("2".equals(sfyjtd)&&StringUtils.isNotBlank(payment)){//已经贴点且 贴点有指定支付方式
	    		String [] payment_arr=payment.split("/");
	    		for (String payment_tmp:payment_arr) {
	    		    if(ArrayUtils.isNotEmpty(cps_payCompMsgMap.get(payment_tmp))){
	    		        dkfstb+=cps_payCompMsgMap.get(payment_tmp)[1]+",";
	    		    }
	    		}
	    		if(StringUtils.isNotBlank(dkfstb)){
	    			dkfstb=dkfstb.substring(0,dkfstb.length()-1);
	    		}
	    	}
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return dkfstb;
    }
  //CPS指定代扣方式的贴点政策中所指定的支付方式名称
    public String getCps_tdzc_shdkfs_mc() {
    	String dkfsmc="";
    	try{
	    	if("2".equals(sfyjtd)&&StringUtils.isNotBlank(cps_defPayMethod)){//已经贴点且 贴点有指定支付方式
	    		String [] cps_defPayMethod_arr=cps_defPayMethod.split(",");
	    		for (String cps_defPayMethod_tmp:cps_defPayMethod_arr) {
	    			dkfsmc+=cps_payCompMsgMap.get(cps_defPayMethod_tmp)[0]+",";
	    		}
	    		if(StringUtils.isNotBlank(dkfsmc)){
	    			dkfsmc=dkfsmc.substring(0,dkfsmc.length()-1);
	    		}
	    	}
	    }catch (Exception e) {
			e.printStackTrace();
		}
    	return dkfsmc;
    }
    
    public String getPcNumber() {
		return pcNumber;
	}

	public void setPcNumber(String pcNumber) {
		this.pcNumber = pcNumber;
	}

	public int getShowseq() {
		return showseq;
	}

	public void setShowseq(int showseq) {
		this.showseq = showseq;
	}

	@Override
	public String toString() {
		return "政策标示：" + getPtzcbs()+";政策id：" + getId() + ";票价："+getZcpj()+";返点：" + getRate()+";留钱:"+(null == getLq() ? 0.0 : getLq()) +";代理费率："+getShowsjdlfl()+";代理费："+getSuperProfit()+";采购净价：" + (null == getPj_sjjsj() ? 0.0 : getPj_sjjsj()) +";工作时间：" + getWorktime() + ";废票时间：" + getChooseOutWorkTime()
				+ ";换编码：" + getChangerecord() + ";特殊政策：" + getIsspecmark() + ";政策类型：" + getPolicytype() + ";备注：" + getNote();
	}

	// 今日

	// 517na
	private String guid;// 子政策编号

	// 517na

	private String policytype;// 政策类型 B2B-ET BSP-ET

	public String getPolicytype() {
		return policytype;
	}

	public void setPolicytype(String policytype) {
		this.policytype = policytype;
	}

	public String getAircome() {
		return aircome;
	}

	public void setAircome(String aircome) {
		this.aircome = aircome;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorktime() {
		return worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getPayfee() {
		return payfee;
	}

	public void setPayfee(Double payfee) {
		this.payfee = payfee;
	}

	public Double getPaypoundage() {
		return paypoundage;
	}

	public void setPaypoundage(Double paypoundage) {
		this.paypoundage = paypoundage;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getIsspecmark() {
		return isspecmark;
	}

	public void setIsspecmark(String isspecmark) {
		this.isspecmark = isspecmark;
	}

	public String getChangerecord() {
		return changerecord;
	}

	public void setChangerecord(String changerecord) {
		this.changerecord = changerecord;
	}

	public String getNote() {
		return VeStr.toHtml((note == null ? "" : note));
	}

	public void setNote(String note) {
		this.note = note;
	}


	public Double getRateshow() {
		return rateshow;
	}

	public void setRateshow(Double rateshow) {
		this.rateshow = rateshow;
	}

	public Double getXsj() {
		return xsj;
	}

	public void setXsj(Double xsj) {
		this.xsj = xsj;
	}

	public Double getJsf() {
		return jsf;
	}

	public void setJsf(Double jsf) {
		this.jsf = jsf;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getPtzcbs() {
		return ptzcbs;
	}

	public void setPtzcbs(String ptzcbs) {
		this.ptzcbs = ptzcbs;
	}

	public String getChooseOutWorkTime() {
		return chooseOutWorkTime;
	}

	public void setChooseOutWorkTime(String chooseOutWorkTime) {
		this.chooseOutWorkTime = chooseOutWorkTime;
	}

	public String getPayconfig() {
		return payconfig;
	}

	public void setPayconfig(String payconfig) {
		this.payconfig = payconfig;
	}

	public String getAgentfee() {
		return agentfee;
	}

	public void setAgentfee(String agentfee) {
		this.agentfee = agentfee;
	}

	public Double getLq() {
		return lq;
	}

	public void setLq(Double lq) {
		this.lq = lq;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = StringUtils.trimToEmpty(office).toUpperCase();
	}

	public String getRecommen() {
		return recommen;
	}

	public void setRecommen(String recommen) {
		this.recommen = recommen;
	}

	public String getRecommentxt() {
		return recommentxt;
	}

	public void setRecommentxt(String recommentxt) {
		this.recommentxt = recommentxt;
	}

	public String getJgxx() {
		return jgxx;
	}

	public void setJgxx(String jgxx) {
		this.jgxx = jgxx;
	}

	public String getZclx() {
		if (StringUtils.isBlank(zclx))
			zclx = "1";
		return zclx;
	}

	public void setZclx(String zclx) {
		this.zclx = zclx;
	}

	public String getShmc() {
		return shmc;
	}

	public void setShmc(String shmc) {
		this.shmc = shmc;
	}

	public int getBxfs() {
		return bxfs;
	}

	public void setBxfs(int bxfs) {
		this.bxfs = bxfs;
	}

	public double getBxje() {
		return bxje;
	}

	public void setBxje(double bxje) {
		this.bxje = bxje;
	}

	public String getBxxz() {
		return bxxz;
	}

	public void setBxxz(String bxxz) {
		this.bxxz = bxxz;
	}

	public Double getShowxjjlfl() {
		return showxjjlfl;
	}

	public void setShowxjjlfl(Double showxjjlfl) {
		this.showxjjlfl = showxjjlfl;
	}

	public Double getPj_sjjsj() {
		return pj_sjjsj;
	}

	public void setPj_sjjsj(Double pj_sjjsj) {
		this.pj_sjjsj = pj_sjjsj;
	}

	public Double getPj_xjjsj() {
		return pj_xjjsj;
	}

	public void setPj_xjjsj(Double pj_xjjsj) {
		this.pj_xjjsj = pj_xjjsj;
	}

	public String getBillSaleMatch() {
		return billSaleMatch;
	}

	public void setBillSaleMatch(String billSaleMatch) {
		this.billSaleMatch = billSaleMatch;
	}

	public String getHclx() {
		return hclx;
	}

	public void setHclx(String hclx) {
		this.hclx = hclx;
	}

	public String getAssurePlan() {
		return assurePlan;
	}

	public void setAssurePlan(String assurePlan) {
		this.assurePlan = assurePlan;
	}

	public String getAssurePlanName() {
		return assurePlanName;
	}

	public void setAssurePlanName(String assurePlanName) {
		this.assurePlanName = assurePlanName;
	}

	public String getAssurePlanIcon() {
		return assurePlanIcon;
	}

	public void setAssurePlanIcon(String assurePlanIcon) {
		this.assurePlanIcon = assurePlanIcon;
	}

	public int getAppraiseCount() {
		return appraiseCount;
	}

	public void setAppraiseCount(int appraiseCount) {
		this.appraiseCount = appraiseCount;
	}

	public int getDealCount() {
		return dealCount;
	}

	public void setDealCount(int dealCount) {
		this.dealCount = dealCount;
	}

	public Double getShowsjdlfl() {
		return showsjdlfl;
	}

	public void setShowsjdlfl(Double showsjdlfl) {
		this.showsjdlfl = showsjdlfl;
	}

	public Double getPj_cgj() {
		return pj_cgj;
	}

	public void setPj_cgj(Double pj_cgj) {
		this.pj_cgj = pj_cgj;
	}

	public Double getPj_pat() {
		return pj_pat;
	}

	public void setPj_pat(Double pj_pat) {
		this.pj_pat = pj_pat;
	}

	private String str(String obj){
		return StringUtils.trimToEmpty((String)obj);
	}
	private String str(Double obj){
		if(obj==null)return "";
		return obj+"";
	}
	
	
	public static void test(Double aa) {
		System.out.println(aa);
		List<Object> list=new ArrayList<Object>();
		list.add(aa);
		System.out.println(list.toString());
	}
	public static void main(String[] args) {
		System.out.println(NumberUtils.toDouble(null));
		test(null);
	}

	public String getZcpj() {
		//if(StringUtils.isEmpty(zcpj)||"0".equals(zcpj))return pj_cgj+"";
		if(StringUtils.isBlank(zcpj))zcpj="0";
		return zcpj;
	}

	public void setZcpj(String zcpj) {
		this.zcpj = zcpj;
	}

	public String getSmnm() {
		return smnm;
	}

	public void setSmnm(String smnm) {
		this.smnm = smnm;
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}

	public String getTgqgd() {
		return tgqgd;
	}

	public void setTgqgd(String tgqgd) {
		this.tgqgd = tgqgd;
	}

	public Double getSuperProfit() {
		return superProfit;
	}

	public void setSuperProfit(Double superProfit) {
		this.superProfit = superProfit;
	}

	public int getCrs() {
		return crs;
	}

	public void setCrs(int crs) {
		this.crs = crs;
	}

	public String getSfxzbmd() {
		return StringUtils.trimToEmpty(sfxzbmd);
	}

	public void setSfxzbmd(String sfxzbmd) {
		this.sfxzbmd = sfxzbmd;
	}

	public int getEts() {
		return ets;
	}

	public void setEts(int ets) {
		this.ets = ets;
	}

	public Double getPj_cgj_et() {
		return pj_cgj_et;
	}

	public void setPj_cgj_et(Double pj_cgj_et) {
		this.pj_cgj_et = pj_cgj_et;
	}

	public Double getTotal_zcpj() {
		return total_zcpj;
	}

	public void setTotal_zcpj(Double total_zcpj) {
		this.total_zcpj = total_zcpj;
	}

    public String getFacetype() {
        return this.facetype;
    }

    public void setFacetype(String facetype) {
        this.facetype = facetype;
    }

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

    public double getCps_payRates() {
        return this.cps_payRates;
    }

    public void setCps_payRates(double cps_payRates) {
        this.cps_payRates = cps_payRates;
    }

    public double getCps_payMoney() {
        return this.cps_payMoney;
    }

    public void setCps_payMoney(double cps_payMoney) {
        this.cps_payMoney = cps_payMoney;
    }

    public String getCps_tdgz_id() {
        return this.cps_tdgz_id;
    }

    public void setCps_tdgz_id(String cps_tdgz_id) {
        this.cps_tdgz_id = cps_tdgz_id;
    }

    public String getOtherParam() {
        return this.otherParam;
    }

    public void setOtherParam(String otherParam) {
        this.otherParam = otherParam;
    }

	public String getSfqyzd() {
		return sfqyzd;
	}

	public void setSfqyzd(String sfqyzd) {
		this.sfqyzd = sfqyzd;
	}

	public double getZdfd() {
		return zdfd;
	}

	public void setZdfd(double zdfd) {
		this.zdfd = zdfd;
	}

	public double getZgfd() {
		if(zgfd != 0){
			return zgfd;
		}else{
			return Arith.div(rate,100);
		}
	}

	public void setZgfd(double zgfd) {
		this.zgfd = zgfd;
	}

	public double getFgfd() {
		return fgfd;
	}

	public void setFgfd(double fgfd) {
		this.fgfd = fgfd;
	}

	public String getSfyjzd() {
		return sfyjzd;
	}

	public void setSfyjzd(String sfyjzd) {
		this.sfyjzd = sfyjzd;
	}

	public String getSfyjtd() {
		return sfyjtd;
	}

	public void setSfyjtd(String sfyjtd) {
		this.sfyjtd = sfyjtd;
	}

	public double getCatchPoint() {
		return catchPoint;
	}

	public void setCatchPoint(double catchPoint) {
		this.catchPoint = catchPoint;
	}

	public String getPtmc() {
		return ptmc;
	}

	public void setPtmc(String ptmc) {
		this.ptmc = ptmc;
	}
	
	/**
	 * 集中出票页面显示的销售说明
	 */
	public String getAsmsSaleNoteTitle() {
		String str=getAsmsSaleNote();
		if(str.length()>45){
			str=str.substring(0,45)+"...";
		}
		str=str.replaceAll("\n","");
		return str;
	}
	/**
	 * 集中出票页面显示的销售说明
	 */
	public String getAsmsSaleNoteTitle90() {
		String str=getAsmsSaleNote();
		if(str.length()>90){
			str=str.substring(0,90)+"...";
		}
		str=str.replaceAll("\n\n","\n");
		str=str.replaceAll("\n","<br>");
		return str;
	}
	public String getAsmsSaleNote() {
		StringBuffer saleNote=new StringBuffer();
		boolean cps_ptzc=false;//非CPS普通政策
		if(PlatCode.CPS.equals(ptzcbs)&&"1".equals(zclx)){
			cps_ptzc=true;
		}
		if(!cps_ptzc){
			if(StringUtils.isNotBlank(getTgqgd())){
				saleNote.append("退改签规定："+getTgqgd()+"；\n\n");
			}
		}
		if(StringUtils.isNotBlank(note)){
			saleNote.append("销售说明："+note+"；\n\n");
		}
		
		saleNote.append("工作时间："+VeStr.toXmlFormat(getWorktime())+"；");
		if(StringUtils.isNotBlank(getChooseOutWorkTime())){
			saleNote.append("废票时间："+VeStr.toXmlFormat(getChooseOutWorkTime())+"；\n\n");
		}
		if(total_zcpj!=null&&total_zcpj>0){
			saleNote.append("政策适用账单价："+total_zcpj+"；\n\n");
		}
		
		if("0".equals(billSaleMatch)){
			saleNote.append("票面不相符；");
		}else if("1".equals(billSaleMatch)){
			saleNote.append("票面相符；");
		}
		if("1".equals(changerecord)){
			saleNote.append("需要换编码；");
		}
		if(StringUtils.isNotBlank(getOffice())){
			saleNote.append("OFFICE："+getOffice());
		}
		
		if(cps_ptzc){
			if(StringUtils.isNotBlank(getTgqgd())){
				saleNote.append("\n\n退改签规定："+getTgqgd());
			}
		}
		
		if(PlatCode.CPS.equals(ptzcbs)&&StringUtils.isNotBlank(getSpecialRequest())){
			saleNote.append("\n\n特殊说明："+getSpecialRequest());
		}
		
		String str=saleNote.toString();
		str=VeStr.toHtml(saleNote.toString());
		str=str.replaceAll("&nbsp;","  ");
		str=str.replaceAll("<br>","\n");
		return str;
	}

    
    public String getPayment() {
        return this.payment;
    }

    
    public void setPayment(String payment) {
        this.payment = payment;
    }
	public String getCps_defPayMethod() {
		return cps_defPayMethod;
	}
	public void setCps_defPayMethod(String cps_defPayMethod) {
		this.cps_defPayMethod = cps_defPayMethod;
	}
    public double getSaleServicePrice()
    {
        return saleServicePrice;
    }
    public void setSaleServicePrice(double saleServicePrice)
    {
        this.saleServicePrice = saleServicePrice;
    }
    public String getSaleServiceWay()
    {
        return saleServiceWay;
    }
    public void setSaleServiceWay(String saleServiceWay)
    {
        this.saleServiceWay = saleServiceWay;
    }
    public String getSaleServiceCjr()
    {
        return saleServiceCjr;
    }
    public void setSaleServiceCjr(String saleServiceCjr)
    {
        this.saleServiceCjr = saleServiceCjr;
    }
	public String getSaleServiceId() {
		return saleServiceId;
	}
	public void setSaleServiceId(String saleServiceId) {
		this.saleServiceId = saleServiceId;
	}
    
    public double getContrPoint() {
        return this.contrPoint;
    }
    
    public void setContrPoint(double contrPoint) {
        this.contrPoint = contrPoint;
    }
    
    public double getContrMoney() {
        return this.contrMoney;
    }
    
    public void setContrMoney(double contrMoney) {
        this.contrMoney = contrMoney;
    }
    
    public Map<String, String> getOtherParamMap() {
        return this.otherParamMap;
    }
	public Double getBackrate() {
		return backrate;
	}
	public void setBackrate(Double backrate) {
		this.backrate = backrate;
	}
	public Double getBackfee() {
		return backfee;
	}
	public void setBackfee(Double backfee) {
		this.backfee = backfee;
	}
    
    public List<PolicyItem> getOtherPolicyList() {
        return this.otherPolicyList;
    }
    
    public void setOtherPolicyList(List<PolicyItem> otherPolicyList) {
        this.otherPolicyList = otherPolicyList;
    }
    
    public String getCacheData() {
        return this.cacheData;
    }
    
    public void setCacheData(String cacheData) {
        this.cacheData = cacheData;
    }
	public Integer getTszclx() {
		return tszclx;
	}
	public void setTszclx(Integer tszclx) {
		this.tszclx = tszclx;
	}
	public Map<String, Object> getOtherMap() {
		return otherMap;
	}
	public void setOtherMap(Map<String, Object> otherMap) {
		this.otherMap = otherMap;
	}
    
    public String getReceiveMethod() {
        return this.receiveMethod;
    }
    
    public void setReceiveMethod(String receiveMethod) {
        this.receiveMethod = receiveMethod;
    }
    
    public String getXzbmdlx() {
        return this.xzbmdlx;
    }
    
    public void setXzbmdlx(String xzbmdlx) {
        this.xzbmdlx = xzbmdlx;
    }

    
    public String getMatchWL() {
        return this.matchWL;
    }

    
    public void setMatchWL(String matchWL) {
        this.matchWL = matchWL;
    }

    
    public String getNoMatchWLCjr() {
        return this.noMatchWLCjr;
    }

    
    public void setNoMatchWLCjr(String noMatchWLCjr) {
        this.noMatchWLCjr = noMatchWLCjr;
    }

    
    public String getNoteStr() {
        return this.noteStr;
    }

    
    public void setNoteStr(String noteStr) {
        this.noteStr = noteStr;
    }

    
    public String getNoMatchWLCjrxm() {
        return this.noMatchWLCjrxm;
    }

    
    public void setNoMatchWLCjrxm(String noMatchWLCjrxm) {
        this.noMatchWLCjrxm = noMatchWLCjrxm;
    }

	public String getHide() {
		return hide;
	}

	public void setHide(String hide) {
		this.hide = hide;
	}

	public double getHfje() {
		return hfje;
	}

	public void setHfje(double hfje) {
		this.hfje = hfje;
	}
}
