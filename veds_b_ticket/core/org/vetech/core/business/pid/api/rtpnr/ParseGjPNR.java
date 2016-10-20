package org.vetech.core.business.pid.api.rtpnr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.api.pnrpat2.PnrCjr;
import org.vetech.core.business.pid.api.pnrpat2.PnrHd;
import org.vetech.core.modules.utils.VeDate;

/**
 * 
 * 解析国际PNR
 */
@SuppressWarnings("unchecked")
public class ParseGjPNR {
	private static String pnrno="";
	public static Pnr parseSJPNR(String pnrnr) {
		Pnr pnr = new Pnr();
		StringBuffer parseinfo = new StringBuffer();
		parseinfo.append("乘机人信息：\r\n");
		pnr.setPnr_lr(pnrnr);

		//PNR内容是否含有国际票标识
		if (pnrnr.indexOf("-ETK-") == -1) {
			parseinfo.append("报文中未包含[-ETK-]标识，需要调用PID接口解析");
			pnr.setFlag("-1");
			return pnr;
		}
		
		pnr.setGngj("0");//国际
		List<PnrCjr> cjrList = new ArrayList<PnrCjr>();
		List<PnrHd> hdList = new ArrayList<PnrHd>();
		//解析国际票PNR
		try {
			String reg = "NM[0-9]{1,2}\\s{1,3}([A-Z0-9]{5,7})\\s{1,3}-ETK-";
			Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
			Matcher mp = p.matcher(pnrnr);
			if (mp.find()) {
				pnrno = mp.group(1);
				pnr.setPnr_no(pnrno);
				parseinfo.append("PNRNO：" + pnrno + "\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			parseinfo.append("PNRNO解析异常：" + e.getMessage());
		}

		Map<String, PnrCjr> cjrMap = new HashMap<String, PnrCjr>();
		Map<String, PnrHd> hdMap = new HashMap<String, PnrHd>();

		// 解析乘机人序号姓名
		parseinfo.append("\r\n乘机人信息：\r\n");
		try {
			String reg = "([1-9]{1}[0-9]?)\\.([A-Z]{1,}/[A-Z]{1,})\\s{1,5}((CHD|MR|MS|MISS|MSTR))?";
			Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
			Matcher mp = p.matcher(pnrnr);
			while (mp.find()) {
				String xh = mp.group(1);
				String xm = mp.group(2);
				String ch = StringUtils.trimToEmpty(mp.group(3));
				parseinfo.append("乘机人序号：" + xh + ",姓名：" + xm +"["+ch+"]" +"\r\n");

				PnrCjr cjr = new PnrCjr();
				cjr.setCjr(xm);
				cjr.setSxh(xh);
				if(ch.indexOf("CHD")>-1){
					cjr.setCjrlx("2");
				}else if(ch.indexOf("CHD")>-1){
					cjr.setCjrlx("3");
				}else{
					cjr.setCjrlx("1");
				}
					
				cjrMap.put(xh, cjr);
				cjrList.add(cjr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			parseinfo.append("乘机人序号和姓名解析异常：" + e.getMessage());
		}
		
		//解析乘机人证件，出生等信息
		try {
			List<String> cjrxxStrList=new ArrayList<String>();
			String[] lines=pnrnr.split("\n");
			int len=lines.length;
			for (int i = 0; i < lines.length; i++) {
				String line=lines[i];
				String reg = "[0-9]{2}\\.SSR\\s{1,3}DOCS\\s{1,3}[A-Z]{2}\\s{1,3}[A-Z]{2}[0-9]{1}\\s{1,3}[A-Z]{6}\\s{1,3}[A-Z0-9]{10}";
				Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
				Matcher mp = p.matcher(line);
				if (mp.find()) {
					line=StringUtils.trimToEmpty(line);
					if(line.endsWith("-")){
						line=StringUtils.trimToEmpty(line.substring(0,line.length()-1));
					}
					if(len>(i+1)){
						String line2=lines[i+1];
						String reg2 = "[0-9]{2}\\.";
						Pattern p2 = Pattern.compile(reg2, Pattern.CASE_INSENSITIVE);
						Matcher mp2 = p2.matcher(line2);
						if (!mp2.find()) {
							line+=StringUtils.trimToEmpty(line2);
						}
					}
					cjrxxStrList.add(line);
				}
			}
			
			for (String str:cjrxxStrList) {
				String reg = "[0-9]{2}\\.SSR\\s{1,3}DOCS\\s{1,3}[A-Z]{2}\\s{1,3}[A-Z]{2}[0-9]{1}\\s{1,3}[A-Z]{6}\\s{1,3}[A-Z0-9]{10}/([A-Z]{1,2})/([A-Z]{3})/([A-Z0-9]{3,30})/([A-Z]{3})/([0-9]{2}[A-Z]{3}[0-9]{2})/([A-Z]{1})/([0-9]{2}[A-Z]{3}[0-9]{2})/([A-Z/]{3,50})/P([0-9]{1,2})";
				Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
				Matcher mp = p.matcher(str);
				if (mp.find()) {
					String zjlx = mp.group(1);
					String zjqfg = mp.group(2);
					String zjhm = mp.group(3);
					String gj = mp.group(4);
					String csrq = mp.group(5);
					String xb = mp.group(6);
					String zjyxq = mp.group(7);
					String xm = mp.group(8);
					String xh = mp.group(9);
					parseinfo.append("乘机人序号姓名：" + xh + "["+xm+"],证件类型：" + zjlx + ",证件签发国："+zjqfg+",证件号码："+zjhm+",国籍："+gj+",出生日期："+csrq+",性别："+xb+",证件有效期："+zjyxq);
					
					PnrCjr cjr=cjrMap.get(xh);
					if(cjr!=null){
						csrq=convertCsrq(csrq,false);
						zjyxq=convertCsrq(zjyxq,true);
						parseinfo.append(",转换后出生日期："+csrq+",证件有效期："+zjyxq+"\r\n");
						cjr.setZjhm(zjhm);
						cjr.setXb(xb);
						cjr.setGj(gj);
						cjr.setCsrq(csrq);
						cjr.setZjlx(zjlx);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			parseinfo.append("乘机人详细信息解析异常：" + e.getMessage());
		}
		
		parseinfo.append("\r\n航段信息：\r\n");
		// 航段解析
		try {
			//20.   SJ 1185 Y   MO11AUG14HGHDPS HK19  1710 2310
			
			String reg = "[0-9]{2}\\.\\s{1,3}([A-Z]{2})\\s{1,3}([0-9]{3,6})\\s{1,3}([A-Z]{1,2})\\s{1,3}([A-Z]{2}[0-9]{2}[A-Z]{3})([0-9]{2}|\\s{0,3})([A-Z]{6})\\s{1,3}([A-Z]{2})[0-9]{1,2}\\s{1,3}([0-9]{4})\\s{1,3}([0-9]{4})";
			Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
			Matcher mp = p.matcher(pnrnr);
			int i = 0;
			while (mp.find()) {
				int sxh = ++i;
				String hkgs = mp.group(1);
				String hbh = mp.group(2);
				String cw = mp.group(3);
				String qfrq = mp.group(4);
				String hc = mp.group(6);
				String zt = mp.group(7);
				String qfsj = mp.group(8);
				String ddsj = mp.group(9);
				parseinfo.append("航空公司：" + hkgs + ",航班号：" + hbh + ",舱位：" + cw + ",起飞日期：" + qfrq + ",航程：" + hc + ",PNR状态：" + zt + ",起飞时间：" + qfsj + ",到达时间：" + ddsj);
				PnrHd hdb = new PnrHd();
				hdb.setHkgs(hkgs);
				hdb.setSxh(sxh+"");
				hdb.setHbh(hkgs + hbh);
				hdb.setCw(cw);
				if (hc.length() >= 3) {
					hdb.setCfcity(hc.substring(0, 3));
				}
				if (hc.length() >= 6) {
					hdb.setDdcity(hc.substring(3, 6));
				}
				qfrq=convertQfrq(qfrq);
				qfsj=convertTime(qfsj);
				ddsj=convertTime(ddsj);
				hdb.setHdzt(zt);
				hdb.setCfsj(qfrq + " " + qfsj);
				hdb.setBzbz(qfrq + " " + ddsj);
				hdMap.put(sxh + "", hdb);
				hdList.add(hdb);
				parseinfo.append(",中文起飞日期时间：" + qfrq + " "+ qfsj + ",到达时间：" + ddsj+"\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			parseinfo.append("航段信息解析异常：" + e.getMessage());
		}

		// 票号解析
		//54.SSR TKNE SJ  HK1 HGHDPS1185Y13AUG.9772411175251C1/P1        
		parseinfo.append("\r\n票号信息：\r\n");
		try {
			String reg = "TKNE\\s{1,3}[A-Z]{2}\\s{1,3}[A-Z]{2}[0-9]{1}\\s{1,3}[A-Z]{6}[0-9]{3,6}[A-Z][0-9]{2}[A-Z]{3}\\.([0-9]{13})C([0-9]{1})/P([0-9]{1})";
			Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
			Matcher mp = p.matcher(pnrnr);
			while (mp.find()) {
				String ph = mp.group(1);
				String hdh = mp.group(2);
				String cjrxh = mp.group(3);
				parseinfo.append("乘机人序号：" + cjrxh + ",航段号：" + hdh + ",对应的票号为：" + ph + "\r\n");

				PnrCjr cjr = cjrMap.get(cjrxh);
				if (cjr != null) {
					TreeSet tknoSet = (TreeSet) cjr.getTknoSet();
					if (tknoSet == null) {
						tknoSet = new TreeSet<String>();
						cjr.setTknoSet(tknoSet);
					}
					tknoSet.add(ph);
					String tkno = cjr.getTkno();
					if (StringUtils.isNotBlank(tkno)) {
						if(tkno.indexOf(ph)==-1){
							tkno += "," + ph;	
						}	
					} else {
						tkno = ph;
					}
					cjr.setTkno(tkno);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			parseinfo.append("票号信息解析异常：" + e.getMessage());
		}

		pnr.setCjrlist(cjrList);
		pnr.setHdblist(hdList);
		pnr.setFlag("1");
	
		return pnr;
	}
	private static Map<String, String> monthMap=new HashMap<String, String>();
	//转换起飞日期 FR15AUG
	private static String convertQfrq(String qfrq) {
		if(monthMap.size()==0){
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
		}
		if(StringUtils.isBlank(qfrq)){
			return "";
		}
		if(qfrq.length()!=7){
			return "";
		}
		
		String m=StringUtils.trimToEmpty(monthMap.get(qfrq.substring(4)));
		String r=qfrq.substring(2,4);
		return VeDate.getStringDateShort().substring(0,4)+"-"+m+"-"+r;
		
	}
	
	/**
	 * 将日期格式20JUN99 转换成 2099-07-20
	 */
	private static String convertCsrq(String csrq,boolean sfzj) {
		try{
			if(monthMap.size()==0){
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
			}
			if(StringUtils.isBlank(csrq)){
				return "";
			}
			if(csrq.length()!=7){
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
	
	/**
	 * 将两个时间0800 2400 转换成08:00-24:00
	 */
	private static String convertTime(String time1) {
		String time = "";
		try {
			time = StringUtils.trimToEmpty(time1);

			if (time1.length() == 4) {
				time = time1.substring(0, 2) + ":" + time1.substring(2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}
}
