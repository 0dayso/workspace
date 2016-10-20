package org.vetech.core.business.pid.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.business.pid.pidbean.PidPnrnrBean;
import org.vetech.core.business.pid.pidbean.PidPnrnrPatBean;
import org.vetech.core.business.pid.pidbean.PnrPassengerBean;
import org.vetech.core.business.pid.pidbean.PnrSegmentBean;
import org.vetech.core.business.pid.pidbean.PnrnrBean;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.XmlUtils;

/**
 * 解析PNR内容信息工具类
 * @author  gengxianyan
 * @version  [版本号, Apr 8, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class ParsePnrUtil {

	/**
	 * 解析PNR内容转换成指定JavaBean
	 * @param resultXml 接口返回PNR内容 
	 * @return [参数说明]
	 */
	public PnrnrBean parsePnrnr(String resultXml){
		
		PidPnrnrBean bean = (PidPnrnrBean) XmlUtils.fromXml(resultXml, PidPnrnrBean.class);
		if(bean==null){
			return null;
		}
		
		return parsePidPnrnrBean(bean);
	}
	
	/**
	 * 解析PNR内容带PAT内容转换成JavaBean
	 * @param resultXml 接口返回PNR内容 
	 * @return [参数说明]
	 */
	public PnrnrBean parsePnrnrPat(String resultXml){
		
		PidPnrnrPatBean bean = (PidPnrnrPatBean) XmlUtils.fromXml(resultXml, PidPnrnrPatBean.class);
		
		PnrnrBean pnrnrBean = parsePidPnrnrBean(bean.getPnrBean());
		pnrnrBean.setPatnr(bean.getPat());
		
		return pnrnrBean;
	}
	
	/**
	 * 接口原始Bean转换成标准PNRBean
	 * @param bean 传入Bean
	 * @return [参数说明]
	 */
	private PnrnrBean parsePidPnrnrBean(PidPnrnrBean bean){
		PnrnrBean pnrnrBean = new PnrnrBean();
		
		//基本信息
		pnrnrBean.setPnrno(bean.getPnrno());
		pnrnrBean.setBigpnr(bean.getBigpnr());
		pnrnrBean.setOffice(bean.getOffice());
		pnrnrBean.setAuth(bean.getAuth());//PNR授权信息

		pnrnrBean.setJkzt("1".equals(bean.getJkzt()) ? "0" : "1");//接口状态
		pnrnrBean.setBigpnr(bean.getBigpnr());
		pnrnrBean.setPnrnr(bean.getPnrnr());
		pnrnrBean.setTimeofnoseat(bean.getTimeofnoseat());//NO位时限
		
		String other = bean.getOther();
		other = other.replaceAll(",", " ,");
		String[] otherArr = StringUtils.split(other+" ",",");
		if(!ArrayUtils.isEmpty(otherArr) && otherArr.length > 6){
			pnrnrBean.setOffice(trim(otherArr[0]));
			pnrnrBean.setPnrlx(trim(otherArr[1]));
			pnrnrBean.setTeamRsCount(trim(otherArr[2]));//团队总人数
			pnrnrBean.setTeamName(trim(otherArr[4]));//团队名称
			pnrnrBean.setPnryltime(trim(otherArr[6]));//预订保留时限
		}
		
		String pnrzt = "";
		String hdxx = bean.getHdxx();
		if(StringUtils.isNotBlank(hdxx)){
			hdxx = hdxx.replaceAll(",", " ,");
			pnrzt = trim(StringUtils.split(hdxx+" ",",")[6]);
			if (StringUtils.isNotBlank(pnrzt)) {
				pnrzt = pnrzt.substring(0, 2);
			}
		}else{
			pnrnrBean.setErrorMsg(bean.getOther());
		}
		pnrnrBean.setPnrzt(pnrzt);
		
		//航段信息
		List<List<String>> hdList = convertToList(bean.getHdxx());
		
		List<PnrSegmentBean> segmentList = new ArrayList<PnrSegmentBean>();
		for(List<String> list : hdList){
			
			PnrSegmentBean segment = new PnrSegmentBean();
			segment.setId(VeDate.getNo(4));
			segment.setSxh(String.valueOf(NumberUtils.toInt(list.get(0)+1)));
			segment.setHbh(list.get(1));
			segment.setCw(list.get(2));
			segment.setCfcity(list.get(4));
			segment.setDdcity(list.get(5));
			segment.setCfsj(list.get(7));
			segment.setDdsj(list.get(8));
			
			String fjjx = list.size() > 10 ? list.get(9) : "";
			segment.setFjjx(fjjx);
			
			String hzl  = list.size() > 11 ? list.get(11) : "";
			if(StringUtils.isNotBlank(hzl)){
				String[] hzlArr = parseHzl(hzl);
				segment.setCfhzl(hzlArr[0]);
				segment.setDdhzl(hzlArr[1]);
			}
			
			if(list.size() >= 14){//14最后一位为子舱位
			    segment.setZcw(StringUtils.trimToEmpty(list.get(13)));
			}
			
			segmentList.add(segment);
		}
		pnrnrBean.setSegmentList(segmentList);
		
		
		//乘机人信息
		List<List<String>> cjrList = convertToList(bean.getCjrxx());
		
		int crCount = 0;
		int etCount = 0;
		int yeCount = 0;
		
		List<PnrPassengerBean> passengerList = new ArrayList<PnrPassengerBean>();
		for(List<String> list : cjrList){
			
			String cjrlx = list.get(4);
			if("1".equals(cjrlx)){
				crCount ++;
			}else if("2".equals(cjrlx)){
				etCount ++;
			}else if("3".equals(cjrlx)){
				yeCount ++;
			}
			
			PnrPassengerBean passenger = new PnrPassengerBean();
			passenger.setId(VeDate.getNo(4));
			passenger.setSxh(String.valueOf(NumberUtils.toInt(list.get(0)+1)));
			passenger.setCjrxm(list.get(1));
			passenger.setZjhm(list.get(2));
			passenger.setZjlx(list.get(3));
			passenger.setCjrlx(list.get(4));
			
			passenger.setPj(StringUtils.defaultIfEmpty(list.get(5),"0"));
			passenger.setJsf(StringUtils.defaultIfEmpty(list.get(6),"0"));// 机建
			String tax = StringUtils.defaultIfEmpty(list.get(7),"0");// 税费
			if("0".equals(tax)){
				tax = StringUtils.defaultIfEmpty(list.get(9),"0");//国际税
			}
			passenger.setTax(tax);
			
			String tknos = list.get(8);//以分号分隔的票号
			if(StringUtils.isNotBlank(tknos)){
				String[] tknoArr = StringUtils.split(tknos,";");
				passenger.setTkno(tknoArr[0]);
				
				List<String> tknoList = new ArrayList<String>();
				for(String tkno : tknoArr){
					
					if (tkno.length() == 12) {
						tkno = "0" + tkno;
					} else if (tkno.length() == 11) {
						tkno = "00" + tkno;
					}
					
					tknoList.add(tkno);
				}
				
				passenger.setTknoList(tknoList);
			}

			String sjhm = list.size() > 10 ? list.get(10) : "";
			passenger.setSjhm(sjhm);
			
			passengerList.add(passenger);
		}
		pnrnrBean.setEtCount(String.valueOf(etCount));//儿童人数
		pnrnrBean.setCrCount(String.valueOf(crCount));//成人人数
		pnrnrBean.setYeCount(String.valueOf(yeCount));//婴儿人数
		
		pnrnrBean.setPassengerList(passengerList);
		
		return pnrnrBean;
	}
	
	/**
	 * 原始航段和乘机人信息转换成集合
	 * @param str 原始信息
	 * @return 集合
	 */
	public List<List<String>> convertToList(String str){
		str = str.replaceAll(",", " ,");
		
		String[] arr = StringUtils.split(str,"\\|");
		
		List<List<String>> resultList = new ArrayList<List<String>>();
		
		for(int i=1;i<arr.length;i++){
			
			List<String> list = new ArrayList<String>();
			
			String tempStr = trim(arr[i]);
			String[] tempArr = StringUtils.split(tempStr + " ",",");
			for(int j=0;j<tempArr.length;j++){
				list.add(trim(tempArr[j]));
			}
			
			resultList.add(list);
		}
		return resultList;
	}
	
	/**
	 * 去除前后空格
	 * @param str 字符
	 * @return [参数说明]
	 */
	private String trim(String str){
		return StringUtils.trimToEmpty(str);
	}
	
	/**
	 * 解析航站楼字符串
	 * @param hzl 原始航站楼信息
	 * @return [参数说明]
	 */
	private String[] parseHzl(String hzl) {
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
					
					if(NumberUtils.isDigits(String.valueOf(hzl.charAt(1)))){//T1B
						cfhzl = hzl.substring(0, 2);
						ddhzl = hzl.substring(2);
					}
					
					if(NumberUtils.isDigits(String.valueOf(hzl.charAt(2)))){//BT1
						cfhzl = hzl.substring(0, 1);
						ddhzl = hzl.substring(1);
					}
					
				}
			} else if (hzl.length() == 2) {//两个暂时不处理，后续碰到再进行处理
				//待处理
			}
		}
		String[] hzlarr = new String[2];
		hzlarr[0] = cfhzl;
		hzlarr[1] = ddhzl;
		return hzlarr;
	}
	
	/**
	 * Test
	 * @param args [参数说明]
	 */
	public static void main(String[] args) {
		//String string = ",,,,,";
		
		String str = "T1B";
		
		System.out.println(str.charAt(2));
		System.out.println(NumberUtils.isDigits(String.valueOf(str.charAt(1))));
		
		//System.out.println(string.split(",").length);
	}
	
	
}
