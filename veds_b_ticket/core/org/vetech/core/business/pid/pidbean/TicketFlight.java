package org.vetech.core.business.pid.pidbean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 机票航班查询--航班信息Bean
 * @author  gengxianyan
 * @version  [版本号, Dec 24, 2012]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class TicketFlight implements Serializable{
	
	/**
	 *  序列化
	 */
	private static final long serialVersionUID = -8598328562741514450L;

	private String depDate;
	
	private String depCity;
	
	private String arrCity;
	
	private String depTime;
	
	private String arrTime;
	
	private String flightNo;		// 航班号
	
	private String carrFlightNo;	// 实际承运航班号
	
	private String airways;
	
	private String flightMod;	// 飞机机型
	private String flyTime;		// 飞行时间
	private String flightModType;//飞机机型类型 1大型飞机 2中型飞机 3小型飞机 4轻型飞机
	
	private String airConFee;	// 机场建设费
	private String fuelTax;		// 燃油费
	
	private String meal;		// 餐食
	private String e;			// 电子客票
	private String stopOver;	// 经停
	
	private String depTerm;		// 起始 航站楼
	private String arrTerm;		// 到达 航站楼
	private String protNum;		// 三方协议号
	
	private int standPrice;		//标准运价
	
	private int billPrice;		//账单价
	private Double buyPrice;	//采购净价
	
	private double discount;	//折扣
	private String policyId;	//最低价对应的产品编号
	
	private String cabins;		//舱位多个以空格隔开，例如：YA/1200 T7/650
	
	private int supplyCount;	//供应商报价数量
	private double minBuyPrice;	//最低采购净价
	private double minBuyPriceHigh;//头等舱/商务舱最低采购净价
	private double maxBuyPrice;	//最高采购净价
	private String minCabin;//最低价舱位

	private List<TicketCabin> cabinList;	//该航班所有舱位信息
	
	private Map<String, TicketCabin> zdcabinMap = new HashMap<String, TicketCabin>();//匹配上追点政策的最低价舱位
	
	private String depCityMc;//起飞机场中文名称
	private String arrCityMc;//抵达机场中文名称
	
	private String cx_ptfls;//促销Ids
	
	private String isNotEconomy;//是否查询公务/头等舱报价
	
	private String jsonParam;//json数据
	private String policyType;	//政策类型 TJZC/特价 PTZC/普通 TSZC_QT/特殊 TSZC_KW/K位 TSZC_MP/免票 NFDTJ/NFD特价
	//以下参数为传值之用
	private TicketCabin cabin;//舱位数据
	
	private String cabinMinPubTar;//最低公布运价舱位
	private double discountMinPubTar;//最低公布运价舱位折扣
	private double billPriceMinPubTar;//最低公布运价舱位折扣
	
	private String zws;//座位数
	
	private int cabinTotal;//舱位总共条数
	private int cabinStart;//当前起始序号
	private int cabinPage = 10;//每页条数
	private boolean isnoPjSpecia = false;////false表示该航班有舱位没有匹配上 多舱位特价，为 true 则 不应该过滤掉该航班

   private List<TicketCabin> cabinListT;    //该航班所有舱位信息

	
	public boolean isIsnoPjSpecia() {
		return isnoPjSpecia;
	}

	public void setIsnoPjSpecia(boolean isnoPjSpecia) {
		this.isnoPjSpecia = isnoPjSpecia;
	}

	public String getDepDate() {
		return depDate;
	}

	/**
	 * @return the policyType
	 */
	public String getPolicyType() {
		return policyType;
	}

	/**
	 * @param policyType the policyType to set
	 */
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}

	public String getDepCity() {
		return depCity;
	}

	public void setDepCity(String depCity) {
		this.depCity = depCity;
	}

	public String getArrCity() {
		return arrCity;
	}

	public void setArrCity(String arrCity) {
		this.arrCity = arrCity;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getCarrFlightNo() {
		return carrFlightNo;
	}

	public void setCarrFlightNo(String carrFlightNo) {
		this.carrFlightNo = carrFlightNo;
	}

	public String getAirways() {
		return airways;
	}

	public void setAirways(String airways) {
		this.airways = airways;
	}

	public String getFlightMod() {
		return flightMod;
	}

	public void setFlightMod(String flightMod) {
		this.flightMod = flightMod;
	}

	public String getFlyTime() {
		return flyTime;
	}

	public void setFlyTime(String flyTime) {
		this.flyTime = flyTime;
	}

	public String getAirConFee() {
		return airConFee;
	}

	public void setAirConFee(String airConFee) {
		this.airConFee = airConFee;
	}

	public String getFuelTax() {
		return fuelTax;
	}

	public void setFuelTax(String fuelTax) {
		this.fuelTax = fuelTax;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}
	
	/**
	 * <功能详细描述>
	 * 
	 * 
	 * @return String [返回类型说明]
	 * @see [类、类#方法、类#成员]
	 */
	public String getMealZn() {
		if (StringUtils.isBlank(meal)) {
			return "无餐";
		} else if (meal.equals("B")) {
			return "早餐";
		} else if (meal.equals("C")) {
			return "快餐";
		} else if (meal.equals("D")) {
			return "正餐";
		} else if (meal.equals("M")) {
			return "餐食";
		} else if (meal.equals("S")) {
			return "小吃";
		} else if (meal.equals("L")) {
			return "午餐";
		} else if (meal.equals("O")) {
			return "冷食餐";
		}
		return "无餐";
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getStopOver() {
		return stopOver;
	}

	public void setStopOver(String stopOver) {
		this.stopOver = stopOver;
	}

	public String getDepTerm() {
		return depTerm;
	}

	public void setDepTerm(String depTerm) {
		this.depTerm = depTerm;
	}

	public String getArrTerm() {
		return arrTerm;
	}

	public void setArrTerm(String arrTerm) {
		this.arrTerm = arrTerm;
	}

	public String getProtNum() {
		return protNum;
	}

	public void setProtNum(String protNum) {
		this.protNum = protNum;
	}

	public int getStandPrice() {
		return standPrice;
	}

	public void setStandPrice(int standPrice) {
		this.standPrice = standPrice;
	}

	public String getCabins() {
		return cabins;
	}

	public void setCabins(String cabins) {
		this.cabins = cabins;
	}

	public List<TicketCabin> getCabinList() {
		return cabinList;
	}

	public void setCabinList(List<TicketCabin> cabinList) {
		this.cabinList = cabinList;
	}

	public int getBillPrice() {
		return billPrice;
	}

	public void setBillPrice(int billPrice) {
		this.billPrice = billPrice;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getSupplyCount() {
		return supplyCount;
	}

	public void setSupplyCount(int supplyCount) {
		this.supplyCount = supplyCount;
	}

	public double getMinBuyPrice() {
		return minBuyPrice;
	}

	public void setMinBuyPrice(double minBuyPrice) {
		this.minBuyPrice = minBuyPrice;
	}

	public double getMaxBuyPrice() {
		return maxBuyPrice;
	}

	public void setMaxBuyPrice(double maxBuyPrice) {
		this.maxBuyPrice = maxBuyPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDepCityMc() {
		return depCityMc;
	}

	public void setDepCityMc(String depCityMc) {
		this.depCityMc = depCityMc;
	}

	public String getArrCityMc() {
		return arrCityMc;
	}

	public void setArrCityMc(String arrCityMc) {
		this.arrCityMc = arrCityMc;
	}

	public String getMinCabin() {
		return minCabin;
	}

	public void setMinCabin(String minCabin) {
		this.minCabin = minCabin;
	}

	public String getCx_ptfls() {
		return cx_ptfls;
	}

	public void setCx_ptfls(String cx_ptfls) {
		this.cx_ptfls = cx_ptfls;
	}

	public double getMinBuyPriceHigh() {
		return minBuyPriceHigh;
	}

	public void setMinBuyPriceHigh(double minBuyPriceHigh) {
		this.minBuyPriceHigh = minBuyPriceHigh;
	}

	public String getIsNotEconomy() {
		return isNotEconomy;
	}

	public void setIsNotEconomy(String isNotEconomy) {
		this.isNotEconomy = isNotEconomy;
	}

	public String getFlightModType() {
		return flightModType;
	}

	public void setFlightModType(String flightModType) {
		this.flightModType = flightModType;
	}

	public String getJsonParam() {
		return jsonParam;
	}

	public void setJsonParam(String jsonParam) {
		this.jsonParam = jsonParam;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public TicketCabin getCabin() {
		return cabin;
	}

	public void setCabin(TicketCabin cabin) {
		this.cabin = cabin;
	}

	public String getZws() {
		return zws;
	}

	public void setZws(String zws) {
		this.zws = zws;
	}

	public int getCabinTotal() {
		return cabinTotal;
	}

	public void setCabinTotal(int cabinTotal) {
		this.cabinTotal = cabinTotal;
	}

	public int getCabinStart() {
		return cabinStart;
	}

	public void setCabinStart(int cabinStart) {
		this.cabinStart = cabinStart;
	}

	public int getCabinPage() {
		return cabinPage;
	}

	public void setCabinPage(int cabinPage) {
		this.cabinPage = cabinPage;
	}

	public String getCabinMinPubTar() {
		return cabinMinPubTar;
	}

	public void setCabinMinPubTar(String cabinMinPubTar) {
		this.cabinMinPubTar = cabinMinPubTar;
	}

	public double getDiscountMinPubTar() {
		return discountMinPubTar;
	}

	public void setDiscountMinPubTar(double discountMinPubTar) {
		this.discountMinPubTar = discountMinPubTar;
	}

	public double getBillPriceMinPubTar() {
		return billPriceMinPubTar;
	}

	public void setBillPriceMinPubTar(double billPriceMinPubTar) {
		this.billPriceMinPubTar = billPriceMinPubTar;
	}

	public Map<String, TicketCabin> getZdcabinMap() {
		return zdcabinMap;
	}

	public void setZdcabinMap(Map<String, TicketCabin> zdcabinMap) {
		this.zdcabinMap = zdcabinMap;
	}

    
    public List<TicketCabin> getCabinListT() {
        return this.cabinListT;
    }

    
    public void setCabinListT(List<TicketCabin> cabinListT) {
        this.cabinListT = cabinListT;
    }
}
