package org.vetech.core.business.pid.util;

/**
 * VEPID接口方法常量
 * 
 * @author gengxianyan
 * @version [版本号, Apr 8, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class VepidConstants {

	/**
	 * avh接口
	 * 
	 * @param bstrHC
	 * @param bstrCFDate
	 * @param bstrHGKS
	 * @param bstrIFZD
	 * @param strUser
	 */
	public static String AVH = "avh";

	/**
	 * 小配置提小编码 rtPnr
	 * 
	 * @param pnrno
	 * @param bh
	 * @param office
	 */
	public static String RTPNR = "rtPnr";

	/**
	 * 解析PNR parsePnr
	 * 
	 * @param pnrnr
	 * @param bh
	 */
	public static String PARSEPNR = "parsePnr";

	/**
	 * 提取大编内容 rrtPnr
	 * 
	 * @param bigpnr
	 * @param hbh
	 * @param cfdate
	 * @param bh
	 * @param office
	 */
	public static String RRTPNR = "rrtPnr";

	/**
	 * 大配置提小编码,并返回PAT内容 veRtxPat
	 * 
	 * @param user
	 * @param pnrno
	 * @param pat
	 * @param office
	 */
	public static String VERTXPAT = "veRtxPat";

	/**
	 * 提取大编内容 pnrPat2
	 * 
	 * @param pnrno
	 * @param pat
	 * @param bh
	 * @param office
	 */
	public static String PNRPAT2 = "pnrPat2";

	/**
	 * avhXml
	 * 
	 * @param bstrHC
	 * @param bstrCFDate
	 * @param bstrHGKS
	 * @param bstrIFZD
	 * @param strUser
	 */
	public static String AVHXML = "avhXml";

	/**
	 * 执行fd
	 * 
	 * @param bstrCityPair
	 * @param bstrCFDate
	 * @param bstrHGKS
	 * @param bstrNeedParsed
	 * @param strUser
	 */
	public static String FD = "fd";

	/**
	 * 执行pat
	 * 
	 * @param bstrSS
	 * @param bstrPAT
	 * @param strUser
	 */
	public static String PAT = "pat";

	/**
	 * RTPNR并返回PAT价格内容
	 * 
	 * @param bstrPnrNO
	 * @param bstrPAT
	 * @param strUser
	 * @param bstrOffice
	 */
	public static String PNRPAT = "pnrPat";

	/**
	 * editPNR更改PNR内容
	 * 
	 * @param bstrPnrNO
	 * @param bstrCmds
	 * @param bstrNeedSealed
	 * @param strUser
	 */
	public static String EDITPNR = "editPNR";

	/**
	 * 执行nfd
	 * 
	 * @param bstrCityPair
	 * @param bstrDate
	 * @param bstrHGKS
	 * @param strUser
	 */
	public static String NFD = "nfd";

	/**
	 * 调用flp
	 * 
	 * @param bstrHBH
	 * @param bstrDate
	 * @param strUser
	 */
	public static String FLP = "flp";

	/**
	 * 取消PNR cancelPNR
	 * 
	 * @param bstrPnrNO
	 * @param strUser
	 */
	public static String CANCELPNR = "cancelPNR";

	/**
	 * 调用clrf
	 * 
	 * @param strUser
	 */
	public static String CLRF = "clrf";

	/**
	 * 调用tss
	 * 
	 * @param bstrTicketNO
	 * @param strUser
	 * @param strIsSuspended
	 * @param strOffice
	 */
	public static String TSS = "tss";

	/**
	 * 调用tksCan
	 * 
	 * @param strUser
	 * @param strDate
	 * @param strTickets
	 */
	public static String TKSCAN = "tksCan";

	/**
	 * 调用addUser
	 * 
	 * @param strCode
	 * @param strUserName
	 * @param strPassword
	 */
	public static String ADDUSER = "addUser";

	/**
	 * 调用an
	 * 
	 * @param strUser
	 * @param strNewPassword
	 */
	public static String AN = "an";

	/**
	 * 调用modifyUser
	 * 
	 * @param strCode
	 * @param strUserName
	 * @param strPassword
	 */
	public static String MODIFYUSER = "modifyUser";

	/**
	 * 调用delUser
	 * 
	 * @param strCode
	 */
	public static String DELUSER = "delUser";

	/**
	 * 调用onlyDetr
	 * 
	 * @param bstrDETR
	 * @param strUser
	 */
	public static String ONLYDETR = "onlyDetr";

	/**
	 * 直接输入票号detrXML
	 * 
	 * @param bstrDETR
	 * @param strUser
	 *            用户名
	 */
	public static String DETRXML = "detrXML";

	/**
	 * 获取所有的组
	 */
	public static String GETALLGROUPS = "getAllGroups";

	/**
	 * 获取所有的组及其用户
	 */
	public static String GETALLGROUPSANDUSERS = "getAllGroupsAndUsers";

	/**
	 * 将用户加入到指定的PID组 addUser2
	 * 
	 * @param strCode
	 * @param strUserName
	 * @param strPassword
	 * @param strGroupId
	 */
	public static String ADDUSER2 = "addUser2";

	/**
	 * 这个接口根据用户获取其所属的组的ID以及名称 getGroupByUser
	 * 
	 * @param strUser
	 *            用户编号
	 */
	public static String GETGROUPBYUSER = "getGroupByUser";

	/**
	 * pnr授权 航信将逐渐取消代理人提取其它OFFICE的PNR的权限 可由PNR的责任组OFFICE，对单个PNR做授权
	 * 
	 * @parma strPnrNO
	 * @param bstrOffice
	 * @parma bstrTargetOffices
	 * @parma bstrOfficesToDelete
	 * @param strUser
	 */
	public static String PNRAUTH = "pnrAuth";

	/**
	 * 修改乘机人证件号码 
	 * 
	 * @param strPnrNO
	 *            ：PNR编码 
	 * @param bstrName
	 *            ：旅客姓名
	 *            如果该名字在乘机人中重复，如果bstrOldFOID为空(也就是没有指定旧证件号),那么共享会报错；如果重复，但是指定了旧证件号而且PNR中能够找到旧证件号，那么共享也会正确执行，去变更证件号码。 
	 * @param bstrOldFOID
	 *            ：旧证件号码，可以输入，也可以为空 
	 * @param bstrNewFOID
	 *            ：新证件号码 
	 * @param bstrType
	 *            ：证件号码类型，可输入“PP”或“NI”,该参数也可以为空，如果为空，共享会默认当‘NI’处理。当前共享只支持NI类型的证件号变更。 
	 * @param bstrOffice
	 *            ：可选，如果该参数被指定，共享将会找到任意一个属于该office号的pid去执行指令 
	 * @param strUser
	 *            :用户ID，共享根据该用户的SSR指令的权限来选择恰当的PID去执行指令
	 */
	public static String SSRFOID = "ssrFoid";

	/**
	 * 调用detr2
	 * 
	 * @param bstrTicketNO
	 * @param bstrUserName
	 * @param bstrOffice
	 */
	public static String DETR2 = "detr2";

	/**
	 * 使用大配置RT编码 rtxPnr
	 * 
	 * @param pnrno
	 *            小编码
	 * @param userid
	 *            用户ID
	 * @param office
	 *            大配置上OFFICE号
	 */
	public static String RTXPNR = "rtxPnr";

	/**
	 * 使用大配置提小编码
	 * 
	 * @param pnrno
	 *            小编码
	 * @param userid
	 *            用户ID
	 * @param office
	 *            大配置上OFFICE号 可以不传
	 * @param pat
	 *            PAT格式，例如：PAT:A，不传默认PAT:A
	 */
	public static String RTXPNRNEW = "rtxPnrNew";

	public static String QTE = "qte";

	public static String BOOK = "book";

	public static final String XEPASSENGER = "xePassenger";

	/**
	 * 流量查询
	 */
	public static String FLOWQUERY = "flowQuery";

	/**
	 * VT
	 */
	public static final String VT = "vt";

	/**
	 * TRFD
	 */
	public static final String TRFD = "trfd";
	/**
	 * BSP自动出票 veetdz2
	 */
	public static final String VEETDZ2 = "veetdz2";
	
	/**
	 * 带OFFICE号XEPNR
     * @param strUser 用户编号
     * @param pnrno PNR编码
     * @param office OFFICE号
	 */
	public static final String XEPNRBYOFFICE = "xepnrByOffice";
	
	
	/**
	 * 根据乘机人的证件号码获取票号 detrNi
	 */
	public static final String DETRNI = "detrNi";
	
	/**
	 * 回填票号做DETR验证
	 */
	public static final String VEDETRFINAL = "veDetrFinal";
}
