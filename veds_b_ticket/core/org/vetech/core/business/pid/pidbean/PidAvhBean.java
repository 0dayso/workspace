package org.vetech.core.business.pid.pidbean;

import java.io.Serializable;
import java.util.Map;


/**
 * 航班查询专属JavaBean
 * @author  gengxianyan
 * @version  [版本号, Apr 11, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
@SuppressWarnings("unchecked")
public class PidAvhBean extends Command implements Serializable {

	private static final long serialVersionUID = 5507337973595345308L;
	
	private String hc;
	private String cfdate;
	private String hkgs;
	private String searchType; 	// 查询方式:ETERM IBEAVH
	private String fdType;//FD方式： ETERM IBEFD
	private String hbh;
	private String cw;
	
	private String zk;//折扣，如果不为空，则表示需要过滤比此折扣小的舱位，格式：80[目前适用于升舱换开]
	
	private String cjrlx;//乘机人类型 1成人 2儿童 3婴儿
	private String isXzCwlx;//是否限制舱位类型 1只查询头等/商务舱 为空表示查询所有
	private String selfProduct;//查询包含自己投放的产品 不为空则显示自己的产品 为空表示不显示自己的产品，默认为空
	
	private boolean kqfd = true; 	// 是否开启FD true开启

	private String hclx;

	private String sort;	//排序
	
	private boolean avhSuccess;//AVH执行成功
	private boolean fdSuccess;//FD执行成功
	
	private Map<String, String> otherMap;	// 存放其他临时字段，传递参数时，直接传递AvhBean

	public String getHc() {
		return hc;
	}

	public void setHc(String hc) {
		this.hc = hc;
	}

	public String getCfdate() {
		return cfdate;
	}

	public void setCfdate(String cfdate) {
		this.cfdate = cfdate;
	}

	public String getHkgs() {
		return hkgs;
	}

	public void setHkgs(String hkgs) {
		this.hkgs = hkgs;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getHbh() {
		return hbh;
	}

	public void setHbh(String hbh) {
		this.hbh = hbh;
	}

	public String getCw() {
		return cw;
	}

	public void setCw(String cw) {
		this.cw = cw;
	}

	public boolean isKqfd() {
		return kqfd;
	}

	public void setKqfd(boolean kqfd) {
		this.kqfd = kqfd;
	}

	public String getHclx() {
		return hclx;
	}

	public void setHclx(String hclx) {
		this.hclx = hclx;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Map<String, String> getOtherMap() {
		return otherMap;
	}

	public void setOtherMap(Map<String, String> otherMap) {
		this.otherMap = otherMap;
	}

	public String getFdType() {
		return fdType;
	}

	public void setFdType(String fdType) {
		this.fdType = fdType;
	}

	public boolean isAvhSuccess() {
		return avhSuccess;
	}

	public void setAvhSuccess(boolean avhSuccess) {
		this.avhSuccess = avhSuccess;
	}

	public boolean isFdSuccess() {
		return fdSuccess;
	}

	public void setFdSuccess(boolean fdSuccess) {
		this.fdSuccess = fdSuccess;
	}

	public String getCjrlx() {
		return cjrlx;
	}

	public void setCjrlx(String cjrlx) {
		this.cjrlx = cjrlx;
	}

	

	public String getIsXzCwlx() {
		return isXzCwlx;
	}

	public void setIsXzCwlx(String isXzCwlx) {
		this.isXzCwlx = isXzCwlx;
	}

	public String getSelfProduct() {
		return selfProduct;
	}

	public void setSelfProduct(String selfProduct) {
		this.selfProduct = selfProduct;
	}

	public String getZk() {
		return zk;
	}

	public void setZk(String zk) {
		this.zk = zk;
	}
}
