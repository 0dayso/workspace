package org.vetech.core.business.pid.api;

import java.util.Date;

/**
 * 机票预订父类接口
 * @author  gengxianyan
 * @version  [版本号, Apr 9, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public interface Seat {
	/**
	 * 添加成人信息
	 */
	public void addAdult(String cjrxm) throws Exception;

	/**
	 * 添加儿童信息
	 * @param ifCZ 是否南航
	 */
	public void addChild(String etxm, String ifCZ) throws Exception;

	/**
	 * 添加婴儿信息
	 */
	public void addInfant(String csrq, String crxm, String yexm) throws Exception;
	/**
	 * 添加婴儿信息(MF航空公司姓名后面不需要INF)
	 * @param ifINF 姓名后面是否带INF，空包含，否则不包含
	 */
	public void addInfant(String csrq, String crxm, String yexm,String ifINF) throws Exception;

	/**
	 * 添加证件项
	 */
	public void addSSR_FOID(String hkgs, String zjlx, String zjhm, String cjrxm) throws Exception;

	/**
	 * 添加常旅客项
	 */
	public void addSSR_FQTV(String hkgs, String clk, String cjrxm) throws Exception;

	/**
	 * 添加航班项
	 * [IBE]
	 */
	public void addAirSeg(String hbh, String cw, String cfcity, String ddcity,String rs, String cfsj,String ifkw)throws Exception;
	public void addInfoAirSeg(String hkgs,String cfcity, String ddcity,String hbh,String cw,String cfdate,String xm,String csrq,String p)throws Exception;


	/**
	 * 设置留票时间
	 */
	public void setTimelimit(String time) throws Exception;

	public void setTimelimitSimple(String time) throws Exception;

	public void gn(String num, String group);

	public void rmkic(String param1, String param2) throws Exception;

	public void addSSR(String serveCode, String airCode, String actionCode, int person, String cityPair, String airNo,
			char fltClass, Date departureTime, String serveInfo, String ppsgrName) throws Exception;

	/** 添加特殊服务组 VIP+* */
	public void addSSR(String ssr);

	public void addOSI(String param1, String param2) throws Exception;

	/**
	 * 南航需要多加一条指令 如：SSR CHLD CZ HK1/19900909/P1
	 */
	public void addSSR_CHLD(String hkgs, String csdate, String p) throws Exception;

	/**
	 * 提交指令
	 */
	public String commit() throws Exception;
	/**
	 * 提交指令【国际】
	 */
	public String commitGj() throws Exception;
	
	/**
	 * 添加地面运输段
	 * @param hc
	 */
	public void addSA(String hc) throws Exception;
	
	/**
	 * 国际航段护照信息
	 * @param zjlx 证件类型 P护照
	 * @param fzNational 发证国家
	 * @param gj 国籍
	 * @param sex 性别M男,F女,MI男婴,FI女婴;
	 * @param zjyxq 证件有效期
	 */
	public void addSSR_DOCS(String hkgs,String zjlx,String fzNational,String zjhm,String gj,String csrq,String sex,String zjyxq,String firstName,String lastName,String midName,String p) throws Exception;
	
	/**
	 * 添加儿童信息【国际】
	 * @param otherCode 其他指令代码
	 */
	public void addChildGj(String etxm, String otherCode) throws Exception;
	
	/**
	 * 添加儿童关联成人PNR格式
	 * 格式：ssr oths CA with adult pnr is XXX
	 */
	public void addSSR_OTHS(String hkgs, String pnrno) throws Exception;
}
