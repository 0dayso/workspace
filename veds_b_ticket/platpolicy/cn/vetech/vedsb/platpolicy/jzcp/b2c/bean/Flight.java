package cn.vetech.vedsb.platpolicy.jzcp.b2c.bean;


import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;

public class Flight implements Serializable {

    private static final long serialVersionUID = -861494946031825442L;
    private String flightNo;
    private String depTime;
    private String arrTime;
    private List<CabinSeats> cabins;
    private String shareNo;
    private String shareStatus;
    private String mile;
    private String yPrice;

    public String getyPrice() {
        return this.yPrice;
    }

    public void setyPrice(String yPrice) {
        this.yPrice = yPrice;
    }

    public String getShareNo() {
        return this.shareNo;
    }

    public void setShareNo(String shareNo) {
        this.shareNo = shareNo;
    }

    public String getShareStatus() {
        return this.shareStatus;
    }

    public void setShareStatus(String shareStatus) {
        this.shareStatus = shareStatus;
    }

    public String getMile() {
        return this.mile;
    }

    public void setMile(String mile) {
        this.mile = mile;
    }

    public String getArrTime() {
        return this.arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getDepTime() {
        return this.depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getFlightNo() {
        return this.flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public List<CabinSeats> getCabins() {
        return this.cabins;
    }

    public void setCabins(List<CabinSeats> cabins) {
        this.cabins = cabins;
    }

    /**
     * 舱位按价格正序排序
     */
    public void sort() {
        Collections.sort(this.getCabins(), new Comparator<CabinSeats>() {
            public int compare(CabinSeats o1, CabinSeats o2) {
                return NumberUtils.toDouble(o1.getPrice()) > NumberUtils.toDouble(o2.getPrice()) ? 1 : -1;
            }
        });
    }
}