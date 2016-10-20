package cn.vetech.vedsb.platpolicy.jzcp.b2c.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.modules.utils.XmlUtils;

@XmlRootElement(name = "Segment")
public class Segment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8316984656006103208L;
	// 0是航空公司，1是非航空公司的其他平台
	private String airlineType;
	private String airline;
	private String depCity;
	private String arrCity;
	private String flightDate;
	private String redirect;
	
	/**
	 * 错误信息 成功时错误信息 为空
	 */
	private String error;

	public String toXml() {
		String xml = XmlUtils.toXml(this);
		return xml;
	}

	public String toJson() {
		if (flights == null) {
			return "";
		}
		StringBuffer segment = new StringBuffer();
		segment.append("[");
		for (Flight flight : flights) {
			if (flight.getCabins() == null) {
				continue;
			}
			CabinSeats cabin = flight.getCabins().get(0);
			if (cabin == null) {
				continue;
			}
			segment.append("{");
			segment.append("\"flightNo\":\"").append(flight.getFlightNo()).append("\",");
			segment.append("\"airline\":\"").append(airline).append("\",");
			segment.append("\"price\":").append(cabin.getPrice()).append(",");
			segment.append("\"source\":\"").append(source).append("\",");
			segment.append("\"shareNo\":\"").append(flight.getShareNo()).append("\",");
			segment.append("\"id\":\"").append(cabin.getId()).append("\",");
			segment.append("\"cabin\":\"").append(cabin.getCabin()).append("\",");
			segment.append("\"seats\":\"").append(cabin.getSeats()).append("\",");
			segment.append("\"buildTax\":\"").append(cabin.getBuildTax()).append("\",");
			segment.append("\"fualTax\":\"").append(cabin.getFualTax()).append("\",");
			String desc = StringUtils.trimToEmpty(cabin.getCabinRuleDescription()).replaceAll("\\s", "");
			segment.append("\"cabinDesc\":\"").append(desc).append("\",");
			segment.append("\"updateTime\":\"").append(datetime).append("\",");
			segment.append("\"flightDate\":\"").append(flightDate).append("\",");
			segment.append("\"depTime\":\"").append(flight.getDepTime()).append("\",");
			segment.append("\"arrTime\":\"").append(flight.getArrTime()).append("\",");
			segment.append("\"bookUrl\":\"").append("/modata.shtml?method=redirect").append(
					"&range=" + depCity + arrCity + "&flightDate=" + flightDate + "&airline=" + airline).append("\"");
			segment.append("},");
		}
		return segment.substring(0, segment.length() - 1) + "]";
	}

	/**
	 * 生成航班的数据的时间
	 */
	String datetime;
	/**
	 * 来源 ASMS CPS
	 */
	String source;
	/**
	 * 总公司或客户编号
	 */
	String account;
	/**
	 * 存储下一步需要的信息
	 */
	private Map<String, String> others = new HashMap<String, String>();
	/**
	 * 航班信息
	 */
	private List<Flight> flights;

	public List<Flight> getFlights() {
		return this.flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
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

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getAirlineType() {
		return airlineType;
	}

	public void setAirlineType(String airlineType) {
		this.airlineType = airlineType;
	}

	/**
	 * 通过航班号 舱位名称 和 价格 找到舱位对象
	 * 
	 * @param flightNo
	 * @param cabin
	 * @param price
	 * @return
	 */
	public Segment check(String flightNo, String cabin, String price) {
		cabin = StringUtils.trimToEmpty(cabin).replace(" ", "");
		if (flights == null || flights.size() < 1) {
			return null;
		}
		Segment snew = new Segment();
		try {
			BeanUtils.copyProperties(snew, this);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Flight> fsnew = new ArrayList<Flight>();
		snew.setFlights(fsnew);

		for (Flight f : flights) {
			if (flightNo.equalsIgnoreCase(f.getFlightNo())) {
				List<CabinSeats> cs = f.getCabins();
				if (cs == null || cs.size() < 1) {
					return null;
				}
				Flight fnew = new Flight();
				try {
					BeanUtils.copyProperties(fnew, f);
				} catch (Exception e) {
					e.printStackTrace();
				}
				fsnew.add(fnew);

				List<CabinSeats> csnew = new ArrayList<CabinSeats>();
				fnew.setCabins(csnew);

				for (CabinSeats c : cs) {
					if (cabin.equalsIgnoreCase(c.getCabin())) {
						if (NumberUtils.toDouble(price) == NumberUtils.toDouble(c.getPrice())) {
							csnew.add(c);
							return snew;
						} else if (flightNo.startsWith("CA")||flightNo.startsWith("3U")) {// ca有20的价差 3U折扣舱和经济舱登录后查询有20的价差
							if ((NumberUtils.toDouble(price) - 20) == NumberUtils.toDouble(c.getPrice())) {
								csnew.add(c);
								return snew;
							}
						}
					}
				}
			}
		}
		return null;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@XmlTransient
	public Map<String, String> getOthers() {
		return others;
	}

	public void setOthers(Map<String, String> others) {
		this.others = others;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
	
}
