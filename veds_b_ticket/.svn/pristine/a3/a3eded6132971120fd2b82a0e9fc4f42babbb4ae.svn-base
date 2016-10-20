package cn.vetech.vedsb.platpolicy.jzcp.b2c.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.math.NumberUtils;

/**
 * @author wangxin 封装舱位信息
 */
public class CabinSeats implements Serializable {

	private static final long serialVersionUID = 3093999641748862468L;
	/**
	 * 存放一些预订等需要的属性
	 */
	private Map<String, String> others = new HashMap<String, String>();
	private String id; // flightNo;flight index;cabinCode;cabin index
	private String cabin; // 舱位代码
	private String price; // 舱位价格
	private String promotionPrice; // 促销价格
	private String seats; // 舱位剩余座位数 充足是A 其他为座位数字
	private String integral; // 积分
	private String cabinRuleDescription; // 舱位预订规则描述
	private String fualTax; // 燃油
	private String buildTax; // 机建
	private String fualTaxCh;
	private String backCoins; // 返佣
	private String type; // 舱位类型
	private String state; // 舱位状态
	private String disCount; // 折扣
	private String promotionDisCount; // 促销折扣
	private Double nprice;//数字型价格
	private String cwdj;//舱位等级
	private String cplx;//产品类型
	private String cjrsxz;//乘机人人数限制，淘宝代购订单下单时判断使用
	private String ticket_price; //票面价
	//优惠信息
    private Double discountMoney;//优惠金额
    private String discountInfo;//优惠信息
    private String discountCode;//优惠代码
    private String discountName;//优惠名称
	
	
	
	
	

	public Double getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(Double discountMoney) {
		this.discountMoney = discountMoney;
	}

	public String getDiscountInfo() {
		return discountInfo;
	}

	public void setDiscountInfo(String discountInfo) {
		this.discountInfo = discountInfo;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public String getCwdj() {
		return cwdj;
	}

	public void setCwdj(String cwdj) {
		this.cwdj = cwdj;
	}

	public String getCplx() {
		return cplx;
	}

	public void setCplx(String cplx) {
		this.cplx = cplx;
	}

	public String getDisCount() {
		return this.disCount;
	}

	public void setDisCount(String disCount) {
		this.disCount = disCount;
	}

	public String getPromotionDisCount() {
		return this.promotionDisCount;
	}

	public void setPromotionDisCount(String promotionDisCount) {
		this.promotionDisCount = promotionDisCount;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBackCoins() {
		return this.backCoins;
	}

	public void setBackCoins(String backCoins) {
		this.backCoins = backCoins;
	}

	public String getCabin() {
		return this.cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPromotionPrice() {
		return this.promotionPrice;
	}

	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public String getSeats() {
		return this.seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public String getIntegral() {
		return this.integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public String getCabinRuleDescription() {
		return this.cabinRuleDescription;
	}

	public void setCabinRuleDescription(String cabinRuleDescription) {
		this.cabinRuleDescription = cabinRuleDescription;
	}

	public String getBuildTax() {
		return this.buildTax;
	}

	public void setBuildTax(String buildTax) {
		this.buildTax = buildTax;
	}

	public String getFualTax() {
		return this.fualTax;
	}

	public void setFualTax(String fualTax) {
		this.fualTax = fualTax;
	}

	public String getFualTaxCh() {
		return this.fualTaxCh;
	}

	public void setFualTaxCh(String fualTaxCh) {
		this.fualTaxCh = fualTaxCh;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlTransient
	public Map<String, String> getOthers() {
		return others;
	}

	public void setOthers(Map<String, String> others) {
		this.others = others;
	}
	public String getCjrsxz() {
		return cjrsxz;
	}

	public void setCjrsxz(String cjrsxz) {
		this.cjrsxz = cjrsxz;
	}
	
	public Double getNprice() {
		return NumberUtils.toDouble(price);
	}
	
	public void setNprice(Double nprice) {
		this.nprice = nprice;
	}

	public String getTicket_price() {
		return ticket_price;
	}

	public void setTicket_price(String ticket_price) {
		this.ticket_price = ticket_price;
	}
}