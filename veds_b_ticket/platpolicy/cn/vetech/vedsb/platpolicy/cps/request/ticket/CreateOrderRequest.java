package cn.vetech.vedsb.platpolicy.cps.request.ticket;

import javax.xml.bind.annotation.XmlRootElement;

import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.platpolicy.cps.request.CpsRequest;


/**
 * 生成订单接口请求Bean
 * @author
 */
@XmlRootElement(name = "request")
public class CreateOrderRequest extends CpsRequest {
	private String pnrNo;//PNR
	private String bigPnrNo;//大编码
	private String productType;//产品类型 1 普通政策 2 K位政策 3 免票政策 4 其它政策 5 特价政策 6精准营销 7新版特价
	private String policyId;//政策ID
	private String travelType;//航程类型 1单程、2往返、3联程、4缺口
	private String travelRange;//航程 WUHPEK|PEKCAN|WUHPEK|PEKCAN
	private String flightNo;//航班号
	private String realFlightNo;//实际承运航班号
	private String planeType;//机型
	private String seatClass;//舱位
	private String subSeatClass;//子舱位
	private String discount;//折扣
	private String fromDatetime;//起飞时间 格式示例：2013-08-30 09:30| 2013-08-31 10:30
	private String toDatetime;//到达时间 格式示例：2013-08-30 10:30| 2013-08-31 11:30
	private String tofromterminal;//出发航站楼
	private String terminal;//到达航站楼
	private String passenger;//乘机人 格式示例： 乘机人名1｜乘机人名1｜乘机人名2｜乘机人名2
	private String passengerType;//乘机人类型
	private String cardType;//证件类型 格式示例：NI|ID|PP
	private String cardId;//证件号码
	private String passengerMobile;//手机号
	private String scny;//账单价
	private String yq;//税费
	private String tax;//机建
	private String settlementPrice;//结算价 不写值，只参与比较之用
	private String ifChangeOrder;//是否升航换开订单 1是升舱换开，0不是升舱换开
	private String oldOrderNo;//CPS原订单编号 升舱换开的订单，需要传入此值
	private String notifyAddress;//通知地址
	private String promotionId;//促销活动ID
	private String promotionType;//促销活动类型 1平台返利 ，与促销活动ID一一对应，格示示例：1|1
	private String ifNFDSpecial;//1为NFD全国特价，其他不是
	private String patContent;//PAT原始内容，执行PAT前解析之用
	private String pnrContent;//pnr内容
	private String payRates;// 平台贴点 
	private String payMoney;// 平台贴钱 
	private String payRuleId;// 贴点规则ID 
	private String catchPoint;//追点后的点
	private String sfyjzd;//是否已经追点
	private String sfxzbmd;//是否限制白名单
	private String contrPoint;//平台控点
    private String contrMoney;//平台控钱
    private String secretKey;//贴点明细,每种支付方式计算出的所贴点
    private String tgqgd;//CPSLINK下单参数 退改签规定
	private String productName;//淘宝代扣产品名称
	private String productCode;//采购
	private String filterWebsite;//淘宝代购过滤网站
	private String birthDate;//淘宝代购用出生日期，格式参照乘机人
	private String orderNo;//8000系统订单编号
	private String linker;// 联系人 淘宝代购订单用
	private String linkerEmail;//联系人Email  淘宝代购订单用
	private String linkMobile;// 联系手机 淘宝代购订单用
	private String policyType;//特殊政策(1 K位 2 免票 3特殊) 特价政策【新版】（0价格类 1退改签类）

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getContrPoint() {
        return this.contrPoint;
    }
    
    public void setContrPoint(String contrPoint) {
        this.contrPoint = contrPoint;
    }
    
    public String getContrMoney() {
        return this.contrMoney;
    }
    
    public void setContrMoney(String contrMoney) {
        this.contrMoney = contrMoney;
    }
    public String getCatchPoint() {
		return catchPoint;
	}
	public void setCatchPoint(String catchPoint) {
		this.catchPoint = catchPoint;
	}
	public String getSfyjzd() {
		return sfyjzd;
	}
	public void setSfyjzd(String sfyjzd) {
		this.sfyjzd = sfyjzd;
	}
	public String getSfxzbmd() {
		return sfxzbmd;
	}
	public void setSfxzbmd(String sfxzbmd) {
		this.sfxzbmd = sfxzbmd;
	}
	public String getPayRates() {
		return payRates;
	}
	public void setPayRates(String payRates) {
		this.payRates = payRates;
	}
	public String getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	public String getPayRuleId() {
		return payRuleId;
	}
	public void setPayRuleId(String payRuleId) {
		this.payRuleId = payRuleId;
	}
	public String getSubSeatClass() {
        return subSeatClass;
    }
    public void setSubSeatClass(String subSeatClass) {
        this.subSeatClass = subSeatClass;
    }
    public String getPatContent() {
        return patContent;
    }
    public void setPatContent(String patContent) {
        this.patContent = patContent;
    }
    public String getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}
	public String getBigPnrNo() {
		return bigPnrNo;
	}
	public void setBigPnrNo(String bigPnrNo) {
		this.bigPnrNo = bigPnrNo;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getTravelType() {
		return travelType;
	}
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	public String getTravelRange() {
		return travelRange;
	}
	public void setTravelRange(String travelRange) {
		this.travelRange = travelRange;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getRealFlightNo() {
		return realFlightNo;
	}
	public void setRealFlightNo(String realFlightNo) {
		this.realFlightNo = realFlightNo;
	}
	public String getPlaneType() {
		return planeType;
	}
	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
	public String getSeatClass() {
		return seatClass;
	}
	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getFromDatetime() {
		return fromDatetime;
	}
	public void setFromDatetime(String fromDatetime) {
		this.fromDatetime = fromDatetime;
	}
	public String getToDatetime() {
		return toDatetime;
	}
	public void setToDatetime(String toDatetime) {
		this.toDatetime = toDatetime;
	}
	public String getTofromterminal() {
		return tofromterminal;
	}
	public void setTofromterminal(String tofromterminal) {
		this.tofromterminal = tofromterminal;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getPassenger() {
		return passenger;
	}
	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}
	public String getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getPassengerMobile() {
		return passengerMobile;
	}
	public void setPassengerMobile(String passengerMobile) {
		this.passengerMobile = passengerMobile;
	}
	public String getScny() {
		return scny;
	}
	public void setScny(String scny) {
		this.scny = scny;
	}
	public String getYq() {
		return yq;
	}
	public void setYq(String yq) {
		this.yq = yq;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getSettlementPrice() {
		return settlementPrice;
	}
	public void setSettlementPrice(String settlementPrice) {
		this.settlementPrice = settlementPrice;
	}
	public String getIfChangeOrder() {
		return ifChangeOrder;
	}
	public void setIfChangeOrder(String ifChangeOrder) {
		this.ifChangeOrder = ifChangeOrder;
	}
	public String getOldOrderNo() {
		return oldOrderNo;
	}
	public void setOldOrderNo(String oldOrderNo) {
		this.oldOrderNo = oldOrderNo;
	}
	public String getNotifyAddress() {
		return notifyAddress;
	}
	public void setNotifyAddress(String notifyAddress) {
		this.notifyAddress = notifyAddress;
	}
	public String getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
	public String getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}
    public String getIfNFDSpecial() {
        return ifNFDSpecial;
    }
    public void setIfNFDSpecial(String ifNFDSpecial) {
        this.ifNFDSpecial = ifNFDSpecial;
    }
	public String getPnrContent() {
		return pnrContent;
	}
	public void setPnrContent(String pnrContent) {
		this.pnrContent = pnrContent;
	}
	public String getSecretKey() {
        return secretKey;
    }
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
	public String getTgqgd() {
		return tgqgd;
	}
	public void setTgqgd(String tgqgd) {
		this.tgqgd = tgqgd;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getFilterWebsite() {
		return filterWebsite;
	}
	public void setFilterWebsite(String filterWebsite) {
		this.filterWebsite = filterWebsite;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getLinker() {
		return linker;
	}

	public void setLinker(String linker) {
		this.linker = linker;
	}

	public String getLinkerEmail() {
		return linkerEmail;
	}

	public void setLinkerEmail(String linkerEmail) {
		this.linkerEmail = linkerEmail;
	}

	public String getLinkMobile() {
		return linkMobile;
	}

	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}
	public String toString() {
		return XmlUtils.toXmlWithHead(this, "UTF-8");
	}
}
