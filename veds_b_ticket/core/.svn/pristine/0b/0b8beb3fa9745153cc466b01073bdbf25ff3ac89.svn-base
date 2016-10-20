package org.vetech.core.business.pid.api.rtpnr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.api.pnrpat2.PnrCjr;
import org.vetech.core.business.pid.api.pnrpat2.PnrHd;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.utils.VeDate;

public class PNRParser {
	
	public static final String RTPNR = "RtPnr";
	public static final String PARSEPNR = "ParsePnr";
	public static final String RRTPNR = "RRTPNR";
	public static final String RTXPNR = "RTXPNR";
	public static final String PNRPAT2 = "PNRPAT2";
	public static final String RTXPAT = "RTXPAT";
	
	private PnrRtParam rtBean;
	
	public PNRParser(PnrRtParam rtBean){
		this.rtBean = rtBean;
	}
	
	public static Pnr parser(String etermdata) throws Exception{
		Document db = null;
		try {
			db = DocumentHelper.parseText(etermdata);
		} catch (DocumentException e) {
			throw new EtermException("解析PNRPAT2返回内容失败，返回内容为:"+etermdata);
		}
		Element root = db.getRootElement();
		String r = root.elementText("R");//1 成功返回1，失败返回0
		String n = root.elementText("N");//pnr编码
		String t = root.elementText("T");//如果pnr已被取消，返回2，否则，返回1
		String q = root.elementText("Q");//pnr内容
		String c = root.elementText("C");// office信息,PNR的类型(1为正常票 2为团队票),团队总人数,团队人数,团队的名称,会员卡号,预留时间,儿童数,大人数,婴儿数
		String hd = root.elementText("HD");//航段数|顺序号,航班号,舱位,出发日期,出发城市,到达城市,状态位,出发时间,到达时间,飞机型号,实际承运人,航站楼,航段状态后的人数,子仓位
		String b = root.elementText("B");//>人数|顺序号,乘机人姓名,证件号码,证件类型,乘机人类型,票价,建设费,燃油税,分号分隔的票号列表,XCNY数据项
		String cjr = root.elementText("CJR");//pat内容
		String patnr = root.elementText("PATNR");// 大编码
		
		if ("0".equals(r)){
			throw new Exception("无法通过PID获取到完整的PNR信息: "+c);	
		}
		
		if (hd != null) {
			hd = hd.replaceAll("\n", "");
		}
		if (cjr != null) {
			cjr = cjr.replaceAll("\n", "");
		}
		Pnr pnr = new Pnr();
		try{	
			pnr.setFlag(r);
			pnr.setPnr_lx(t);
			pnr.setPat(patnr);
			 //时限组序号
			 String reg = "\\s*(\\d{1,2})\\.(TL|TT|AT|WC|MT|T)\\/";
			 Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
			 Matcher mp = p.matcher(q);
			 if (mp.find()) {
				 String tlno = mp.group(1);
				 pnr.setTlno(tlno);
			 }
			 pnr.setPnr_no(n);
			 pnr.setPnr_lr(q);
			 pnr.setBigPnrno(b);
			 if ("2".equals(t)) {//2表示PNR已取消
				 return pnr;
			 }
			 String pnrState = hd.split(",")[6];
			 if (StringUtils.isNotBlank(pnrState)) {
				 pnrState = pnrState.substring(0, 2);
			 }
			 pnr.setPnr_zt(pnrState);
			 String[] clength = c.split(",");
			 String[] hdlength = hd.split("\\|");
			 String[] cjrlength = cjr.split("\\|");
			 //为空转换成空格
			 hdlength[1] = hdlength[1].replace(",,", ", ,");
			 if(hdlength[1].lastIndexOf(",") == (hdlength[1].length() - 1)){
				 hdlength[1] += " ";
			 }
			 int length2=hdlength[1].split(",").length;
				
			String[][] hdArr = new String[Integer.valueOf(hdlength[0])][length2];
	
			for (int i = 0; i < hdlength.length - 1; i++) {
				String[] tmp = (hdlength[i + 1] + " ").split(",");
				for (int j = 0; j < length2; j++) {
					hdArr[i][j] = tmp[j];
				}
			}
			String cjrStr = "";
			for (int i = 0; i < cjrlength.length - 1; i++) {
				String[] tmp = (cjrlength[i + 1] + " ").split(",");
				cjrStr = cjrStr + tmp[1] + ",";
			}
			pnr.setDp_officeid(clength[0]);
			pnr.setPnr_hc("");
			String fjjx = hdArr[0].length < 10 ? "" : hdArr[0][9];
			pnr.setFjjx(fjjx);
			pnr.setPnr_hclx("");
			pnr.setPnr_hb(hdArr[0][1]);
			pnr.setPnr_cw(hdArr[0][2]);
			pnr.setPnr_hkgs(StringUtils.substring(hdArr[0][1], 0, 2));
			pnr.setPnr_cfrqsj(hdArr[0][7]);
			pnr.setPnr_tldatetime(clength[6]);
			pnr.setPnr_cjr(cjr);
			pnr.setPnr_adultno(clength[8]);
			pnr.setPnr_chdno(clength[7]);
			pnr.setPnr_infno(clength[9]);
			pnr.setTd_zrs(clength[2]);
			pnr.setBy01(clength[1]);
			pnr.setTd_name(clength[4]);
	
			List<PnrHd> hdblist = new ArrayList<PnrHd>();
			for (int i = 0; i < hdArr.length; i++) {
				PnrHd pnrhd = new PnrHd();
				pnrhd.setSxh(NumberUtils.toInt(hdArr[i][0]) + 1 + "");
				pnrhd.setHbh(hdArr[i][1]);// 航班号
				pnrhd.setHdzt(hdArr[i][6]);
				String jx = hdArr[i].length < 10 ? "" : hdArr[i][9];
				pnrhd.setFjjx(jx);// 飞机机型
				pnrhd.setCw(hdArr[i][2]);// 舱位
				pnrhd.setCfsj(hdArr[i][7]);// 出发时间
				pnrhd.setCfcity(hdArr[i][4]);// 出发城市
				pnrhd.setDdcity(hdArr[i][5]);// 到达城市
				pnrhd.setBzbz(hdArr[i][8]);// 到达时间
				pnrhd.setCwjb(hdArr[i][6]);// 客票级别
				String zcw = hdArr[i].length < 14 ? "" : hdArr[i][13];
				pnrhd.setZcw(StringUtils.trimToEmpty(zcw));
				String hzl = hdArr[i].length < 12 ? "" : hdArr[i][11];
				String[] hzlarr = getHzl(hzl);
				pnrhd.setCfhzl(hzlarr[0]);
				pnrhd.setDdhzl(hzlarr[1]);
				hdblist.add(pnrhd);
			}
			int cr = 0;
			int et = 0;
			int inf = 0;
	
			List<PnrCjr> cjrlist = new ArrayList<PnrCjr>();
			for (int i = 0; i < cjrlength.length - 1; i++) {
				String[] cjrs = (cjrlength[i + 1] + " ").split(",");
				PnrCjr pnrCjr = new PnrCjr();
				pnrCjr.setSxh(NumberUtils.toInt(cjrs[0]) + 1 + "");
				pnrCjr.setCjr(cjrs[1]);// 乘机人姓名
				pnrCjr.setZjhm(cjrs[2]);// 证件号码
				pnrCjr.setCjrlx(cjrs[4]);// 乘机人类型
				if ("1".equals(cjrs[4])) {
					cr++;
				} else if ("2".equals(cjrs[4])) {
					et++;
				} else if ("3".equals(cjrs[4])) {
					inf++;
				}
	
				pnrCjr.setCpbz("0");
				if (StringUtils.isBlank(cjrs[5])) {
					cjrs[5] = "0";
				}
				if (StringUtils.isBlank(cjrs[6])) {
					cjrs[6] = "0";
				}
				pnrCjr.setPj_mj(StringUtils.trim(cjrs[5]));// 票价
				pnrCjr.setPj_cgj(StringUtils.trim(cjrs[5]));
				pnrCjr.setPj_xsj(StringUtils.trim(cjrs[5]));
				pnrCjr.setPj_jsf(StringUtils.trim(cjrs[6]));// 机建
				pnrCjr.setPj_tax(StringUtils.trim(cjrs[7]));// 税费
				if(StringUtils.isBlank(cjrs[7])){//国际税
					pnrCjr.setPj_tax(cjrs[9]);
				}
				if (StringUtils.isBlank(pnrCjr.getPj_tax())) {
					pnrCjr.setPj_tax("0");
				}
				pnrCjr.setCs(cjrs[3]);// //证件类型
				String tknos=cjrs[8];//以分号分隔的票号
				TreeSet<String> tknoSet=null;
				if(StringUtils.isNotBlank(tknos)){
					tknoSet=new TreeSet<String>();
					String[] tknoarr=tknos.split(";");
					pnrCjr.setTkno(tknoarr[0]);
					for (int j = 0; j < tknoarr.length; j++) {
						 tknoSet.add(tknoarr[j]);
						((TreeSet) tknoSet).comparator();
					}
				}
				Set s = tknoSet;
				if(s!=null){
					Iterator it = s.iterator();
					TreeSet tm = new TreeSet();
					while (it.hasNext()) {
						String tkno = it.next() + "";
						if (tkno.length() == 12) {
							tm.add("0" + tkno);
						} else if (tkno.length() == 11) {
							tm.add("00" + tkno);
						} else {
							tm.add(tkno);
						}
		
					}
					pnrCjr.setTknoSet(tm);
				}
				
				//ISO OSI ZH CTCT   
				if(cjrs.length>10){
					pnrCjr.setSjhm(StringUtils.trimToEmpty(cjrs[10]));
				}
				if(cjrs.length>13) {
					pnrCjr.setCsrq(convertCsrq(cjrs[13], false)); //出生日期
				}
				if(cjrs.length>14) {
					pnrCjr.setZjyxq(convertCsrq(cjrs[14], true)); //证件有效期
				}
				cjrlist.add(pnrCjr);
			}
			if(StringUtils.isNotBlank(etermdata)&&etermdata.indexOf("TIMEOFNOSEAT")>-1){//如果返回的内容包含NO位时限节点,则取出来存放进kh_khdd对象的nowsx节点里面
				String nowsx = root.elementText("TIMEOFNOSEAT");
				pnr.setNowsx(nowsx);
			}
			pnr.setPnr_adultno(String.valueOf(cr));
			pnr.setPnr_chdno(String.valueOf(et));
			pnr.setPnr_infno(String.valueOf(inf));
			pnr.setHdblist(hdblist);
			pnr.setCjrlist(cjrlist);
			return pnr;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("系统解析编码内容出现异常,请检查该编码内容是否正确有效或联系管理员,异常信息如下:"+e.getMessage());
		}
	}

	/**
	 * 提取并解析PNR
	 */
	public Pnr parserPNR() throws Exception {
		String etermdata = callWebService(rtBean.getMethodType());
		if(StringUtils.isBlank(etermdata)){
			throw new Exception("无法从PID主机获取信息,请检查网络连接是否正常或联系网络管理员");
		}
		if("1".equals(rtBean.getIfthrowexception())){
			if(etermdata.indexOf("流量检查失败,可能是流量配额已经用尽")>-1){
				throw new Exception(etermdata+",请充值");
			}
		}
		return PNRParser.parser(etermdata);
	}
	
	/**
	 * 调用各种WebService接口添加
	 */
	private String callWebService(String methodName) throws Exception {
		String etermdata = "";
		WebEtermService wes = new WebEtermService(rtBean.getUrl());
		if ("ParsePnr".equals(methodName)) {// 根据PNR内容提取
			etermdata = wes.PARSEPNR(rtBean.getPnrnr(), rtBean.getUserid());
		} else if ("RRTPNR".equals(methodName)) {// 根据大编码提取
			etermdata = wes.RRTPNR(rtBean.getBigpnrno(), rtBean.getHbh(), rtBean.getCfdate(), rtBean.getUserid(), rtBean
					.getOffice());
		} else if ("PNRPAT2".equals(methodName)) {// 根据PNR做PAT
			etermdata = wes.PNRPAT2(rtBean.getPnrno(), rtBean.getPat(), rtBean.getUserid(), rtBean.getOffice());
		} else if ("RTXPNR".equals(methodName)){//大配置提小编码
			etermdata = wes.RTXPNR(rtBean.getPnrno(), rtBean.getUserid(), rtBean.getOffice());
		} else if ("RTXPAT".equals(methodName)){//大配置提小编码,并返回PAT内容
			etermdata = wes.generalCmdProcess(rtBean.getInputXml());
		}else {// 根据小编码提取
			etermdata = wes.RTPNR(rtBean.getPnrno(), rtBean.getUserid(), rtBean.getOffice());
		}
        System.out.println(etermdata);
		return etermdata;
	}
	/**
	 * 解析航站楼
	 */
	private static String[] getHzl(String hzl) {
		String cfhzl = "";
		String ddhzl = "";
		if (StringUtils.isNotBlank(hzl) && hzl.length() >= 2 && hzl.length() <= 4) {
			if (hzl.length() == 4) {// T1T2
				cfhzl = hzl.substring(0, 2);
				ddhzl = hzl.substring(2);
			} else if (hzl.length() == 3) {
				if (hzl.indexOf("-") == 0) {// -T1
					cfhzl = "";
					ddhzl = hzl.substring(1);
				} else if (hzl.indexOf("-") == 2) {// T1-
					cfhzl = hzl.substring(0, 2);
					ddhzl = "";
				} else if (hzl.indexOf("-") < 0) {

				}
			} else if (hzl.length() == 2) {

			}
		}
		String[] hzlarr = new String[2];
		hzlarr[0] = cfhzl;
		hzlarr[1] = ddhzl;
		return hzlarr;
	}
	/**
	 * 将日期格式20JUN99 转换成 2099-07-20
	 */
	public static String convertCsrq(String csrq, boolean sfzj) {
		try{
			Map<String, String> monthMap = new HashMap<String, String>();
			monthMap.put("JAN", "01");
			monthMap.put("FEB", "02");
			monthMap.put("MAR", "03");
			monthMap.put("APR", "04");
			monthMap.put("MAY", "05");
			monthMap.put("JUN", "06");
			monthMap.put("JUL", "07");
			monthMap.put("AUG", "08");
			monthMap.put("SEP", "09");
			monthMap.put("OCT", "10");
			monthMap.put("NOV", "11");
			monthMap.put("DEC", "12");
			if(StringUtils.isBlank(csrq)){
				return "";
			}
			if(StringUtils.trim(csrq).length()!=7){
				return csrq;
			}
			String m=StringUtils.trimToEmpty(monthMap.get(csrq.substring(2,5)));
			String r=csrq.substring(0,2);
			String n=csrq.substring(5);
			if(!sfzj){
				int now_year=NumberUtils.toInt(VeDate.getStringDateShort().substring(2,4));
				if(NumberUtils.toInt(n)>=now_year){
					return "19"+n+"-"+m+"-"+r;
				}
			}
			return "20"+n+"-"+m+"-"+r;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return csrq;
	}
	public static String parsePatTopjNormal(String patInfo){//返回 除青年票价 老年票价的最低票价 及 相应的 燃油税 和 机场建设费
		String []patInfoLine=patInfo.split("SFC:\\d{2}");
		String strPattern="(\\d{2})\\s+(\\S+)\\s+FARE:[a-zA-Z]{3}(\\S+)\\s+TAX:[a-zA-Z]{3}(\\S+)\\s+YQ:[a-zA-Z]{3}(\\S+)\\s+TOTAL\\s*:(\\S+)";
		List<Double> pjList=new ArrayList<Double>();
		Map<String,String> jginfo = new HashMap<String,String>();//存储价格信息 key：票价，value：tax_yq
		for(int i=0;i<patInfoLine.length;i++){
			String []pjLine=StringUtils.split(patInfoLine[i],"\r|\n");
			for(int j=0;j<pjLine.length;j++){
				Pattern pattern=Pattern.compile(strPattern);
				Matcher matcher=pattern.matcher(pjLine[j]);
				if (matcher.find()) {
					String lx = matcher.group(2);//获取 pat中含乘机人类型的信息
					if(StringUtils.isBlank(lx)){
						lx="";
					}
					if(lx.length()>3&&(lx.contains("ZS")||lx.contains("CD"))){//此种类型的票价 不做为pat价格的参考
						//continue;暂时取消限制
					}
					double pj=NumberUtils.toDouble(matcher.group(3));
					String tax=matcher.group(4);
					String yq=matcher.group(5);
					if(!NumberUtils.isNumber(tax)){
						tax = "0";
					}
					if(!NumberUtils.isNumber(yq)){
						yq = "0";
					}
					jginfo.put(pj+"", tax+"_"+yq);
					pjList.add(pj);
				}
			}
		}
		Collections.sort(pjList);//升序排序
		if(!pjList.isEmpty()){
			return String.valueOf(pjList.get(0))+"_"+jginfo.get(String.valueOf(pjList.get(0)));//获取PAT的最低价格。//pj_tax_yq
		}
		return "";
	}
}
