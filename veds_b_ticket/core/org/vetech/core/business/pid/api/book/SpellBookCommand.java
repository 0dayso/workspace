package org.vetech.core.business.pid.api.book;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpException;
import org.vetech.core.business.pid.api.Seat;
import org.vetech.core.business.pid.pidbean.PidResult;
import org.vetech.core.business.pid.util.VepidConstants;
import org.vetech.core.business.pid.util.VepidWebHandler;

/**
 * 拼装预订指令公共类
 */
public class SpellBookCommand implements Seat {
	
	private String url;//接口完整地址
	private String userid;//用户编号
	private String office;//OFFICE号
	private String plat;//平台 A/B
	
	private String ss = "";
	private String foid = "";
	private String fqtv = "";
	private String dpr = "";
	private String ye = "";
	private String inft = "";// SSR INFT CZ NN1/PEKCAN CZ3102 Y12JUL TEST/BABY(必须是拼音) 07DEC07/P1
	private String tlTime = "";
	private String gn = "";
	private String rmkic = "";
	private String chld = "";
	private String osi = ""; // 添加其他信息组
	private String ssr = ""; // 添加特殊服务组
	
	private String ssr_oths = "";//儿童关联成人

	private String ct = "";

	/**
	 * 初始化
	 */
	public SpellBookCommand(String url,String userid,String office,String plat) {
		this.url = url;
		this.userid = userid;
		this.office = office;
		this.plat = plat;
	}

	public SpellBookCommand() {
	}

	/**
	 * 添加成人信息
	 */
	public void addAdult(String cjrxm) throws Exception {
		if (StringUtils.isBlank(dpr)) {
			dpr = "NM1" + cjrxm;
		} else {
			dpr = dpr + "1" + cjrxm;
		}
	}

	/**
	 * 生成指令
	 * 
	 * @return 指令
	 */
	public String command() {
		return ss + dpr + gn + "\r" + foid + fqtv + ye + inft + ct + chld + rmkic + osi + ssr + ssr_oths + tlTime + "\r@";
	}
	
	/**
	 * 生成指令强制封口【针对国际票】
	 * 
	 * @return 指令
	 */
	public String commandGj() {
		return ss + dpr + gn + "\r" + foid + fqtv + ye + inft + ct + chld + rmkic + osi + ssr + ssr_oths + tlTime + "\r\\k";
	}

	public String cjrinfo() {
		return dpr + "\r" + foid + fqtv + ye + inft + chld + tlTime + "\r@";
	}

	/**
	 * 添加儿童信息 2011-05-07 王飞 修改此处，儿童后面不用再加出生年月信息，姓名后只需要添加CHD标识即可
	 */
	public void addChild(String etxm, String ifCZ) throws Exception {
		if (StringUtils.isNotBlank(ifCZ)) {// 非南航
			ifCZ = " CHD";
		}
		if (StringUtils.isBlank(dpr)) {
			dpr = "NM1" + etxm + ifCZ;
		} else {
			dpr = dpr + "1" + etxm + ifCZ;
		}
	}

	/**
	 * 添加婴儿信息
	 */
	public void addInfant(String csrq, String crxm, String yexm) throws Exception {
		ye = ye + "XN:IN/" + yexm + "INF(" + csrq + ")/" + crxm + "\r";
	}
	
	/**
	 * 添加婴儿信息
	 */
	public void addInfant(String csrq, String crxm, String yexm,String ifINF) throws Exception {
		if(StringUtils.isEmpty(ifINF)){
			ye = ye + "XN:IN/" + yexm + "INF(" + csrq + ")/" + crxm + "\r";
		}else{
			ye = ye + "XN:IN/" + yexm + "(" + csrq + ")/" + crxm + "\r";
		}
	}

	/**
	 * 添加证件项
	 */
	public void addSSR_FOID(String hkgs, String zjlx, String zjhm, String cjrxm) throws Exception {
		foid = foid + "SSR FOID " + hkgs + " HK" + "/" + zjlx + zjhm + "/" + cjrxm + "\r";
	}

	/**
	 * 添加常旅客项
	 */
	public void addSSR_FQTV(String hkgs, String clk, String cjrxm) throws Exception {
		fqtv = fqtv + "SSR FQTV " + hkgs + " HK" + "/" + hkgs + clk + "/" + cjrxm + "\r";
	}

	/**
	 * 添加航班项
	 */
	public void addAirSeg(String hbh, String cw, String cfcity, String ddcity, String rs, String cfsj, String ifkw)
			throws Exception {
		String ll = StringUtils.isEmpty(ifkw) ? "" : "LL";// 如果无座位的话 拼装指令需要增加LL行动代码
		if (StringUtils.isNotEmpty(ll)) {
			if (StringUtils.isNotEmpty(rs) && rs.indexOf("nn") > -1) {
				rs = rs.replace("nn", "");
			}
		}
		
		String cwStr = StringUtils.trimToEmpty(cw);
        
        Pattern pattern = Pattern.compile("[A-Z][0-9]");
        Matcher matcher = pattern.matcher(cwStr);
        if(matcher.find()){
            cwStr = String.valueOf(cwStr.charAt(0));
        }
		
		ss = ss + "SS:" + hbh + "/" + cwStr + "/" + cfsj + "/" + cfcity + ddcity + "/" + ll + rs + "\r";
	}

	/**
	 * 设置留票时间
	 */
	public void setTimelimit(String time) throws Exception {
		tlTime = "TKTL/" + time;
	}

	/**
	 * 
	 */
	public void setTimelimitSimple(String time) throws Exception {
		tlTime = "tm" + time;
	}

	/**
	 * 在封口前 加入RMK 指令 param1为指令 param2为指令数据
	 */
	public void rmkic(String param1, String param2) {
		rmkic = "rmk " + param1 + " " + param2 + "\r";
	}

	/**
	 * 在封口前 加入OSI 指令 param1为指令 param2为指令数据
	 */
	public void addOSI(String param1, String param2) {
		osi = "osi " + param1 + param2 + "\r";
	}

	/**
	 * 在封口前 加入SSR 添加特殊服务组 param1为指令 param2为指令数据
	 */
	public void addSSR(String serveCode, String airCode, String actionCode, int person, String cityPair, String airNo,
			char fltClass, Date departureTime, String serveInfo, String ppsgrName) {
		ssr = "ssr " + serveCode + " " + airCode + " " + actionCode + " " + person + " " + cityPair + " " + airNo + " "
				+ fltClass + " " + departureTime + " " + serveInfo + " " + ppsgrName + "\r";
	}

	/**
	 * 在封口前 加入SSR 添加特殊服务组 VIP+
	 */
	public void addSSR(String ssrparam) {
		ssr += "ssr " + ssrparam + "\r";
	}

	/**
	 * 提交航信
	 */
	public String commit() throws Exception {
		Object[] command = new Object[]{command(),userid,office};
		VepidWebHandler handler = new VepidWebHandler(url,VepidConstants.BOOK,command,plat);
		PidResult result = handler.excute();
		if(!"0".equals(result.getJkzt())){
			throw new HttpException(result.getErrorMsg());
		}
		return result.getResultXml();
	}
	
//	/**
//	 * 提交航信【国际】
//	 */
//	public String commitGj() throws HttpException, IOException {
//		Object[] command = new Object[]{command(),userid,office};
//		VepidWebHandler handler = new VepidWebHandler(url,VepidConstants.BOOK,command,plat);
//		PidResult result = handler.excute();
//		if(!"0".equals(result.getJkzt())){
//			throw new HttpException(result.getErrorMsg());
//		}
//		return result.getResultXml();
//	}

	/**
	 * 添加联系人，暂无
	 */
	public void addContact(String lxdh) throws Exception {
		ct = ct + "ct " + lxdh + "\r";
	}

	/**
	 * 加团申请
	 */
	public void gn(String num, String group) {
		gn = "gn:" + num + group ;
	}

	/**
	 * 南航需要多加一条指令 如：SSR CHLD CZ HK1/19900909/P1
	 */
	public void addSSR_CHLD(String hkgs, String csdate, String p) throws Exception {
		chld = chld + "SSR CHLD " + hkgs + " HK1/" + csdate + "/" + p + "\r";
	}

	public void addInfoAirSeg(String hkgs, String cfcity, String ddcity, String hbh, String cw, String cfdate,
			String xm, String csrq, String p) throws Exception {
	    
	    String cwStr = StringUtils.trimToEmpty(cw);
        
        Pattern pattern = Pattern.compile("[A-Z][0-9]");
        Matcher matcher = pattern.matcher(cwStr);
        if(matcher.find()){
            cwStr = String.valueOf(cwStr.charAt(0));
        }
	    
		inft = inft + "SSR INFT " + hkgs + " NN1/" + cfcity + ddcity + " " + hbh + " " + cwStr + cfdate + " "
				+ xm + " " + csrq + "/" + p + "\r";
	}

	/**
	 * 添加地面运输段【缺口】
	 */
	@Override
	public void addSA(String hc) throws Exception {
		ss = ss + "SA:" + hc + "\r";
	}
	
	/**
	 * SSR:DOCS 承运人HK1 证件类型/发证国家/证件号码 -/国籍/出生日期/性别/证件有效期限/姓/名/中间名
	 */
	@Override
	public void addSSR_DOCS(String hkgs,String zjlx,String fzNational,String zjhm,String gj,String csrq,String sex,String zjyxq,String firstName,String lastName,String midName,String p) throws Exception {
		foid = foid + "SSR DOCS " + hkgs + " HK1" + " " + zjlx + "/" +fzNational+ "/" +zjhm + "/" + gj + "/" + csrq + "/" +sex+ "/" + zjyxq+ "/" + firstName + "/" + lastName + "/";
		if(StringUtils.isNotEmpty(midName)){
			foid += midName + "/";
		}
		foid += p + "\r";
	}
	
	@Override
	public void addChildGj(String etxm, String otherCode) throws Exception {
		otherCode = StringUtils.trimToEmpty(otherCode);
		if (StringUtils.isBlank(dpr)) {
			dpr = "NM1" + etxm + otherCode;
		} else {
			dpr = dpr + "1" + etxm + otherCode;
		}
	}
	
	
	/**
	 * 添加儿童关联成人PNR格式
	 * 格式：ssr oths CA with adult pnr is XXX
	 */
	public void addSSR_OTHS(String hkgs, String pnrno) throws Exception {
		ssr_oths = ssr_oths + "SSR OTHS " + hkgs + " with adult pnr is " + pnrno + "\r";
	}

//	@Override
//	public void addContact(BookContact bc) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
	
	public String getSs() {
		return ss;
	}
	
	public void setSs(String ss) {
		this.ss = ss;
	}

	@Override
	public String commitGj() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
