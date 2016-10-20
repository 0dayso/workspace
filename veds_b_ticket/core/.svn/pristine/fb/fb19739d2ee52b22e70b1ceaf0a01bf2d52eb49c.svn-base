package org.vetech.core.business.pid.api.avh2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.vetech.core.business.pid.pidbean.AvCabinModel;
import org.vetech.core.modules.utils.ParseXml;
import org.vetech.core.modules.utils.VeDate;

public class Avh2Parser {
	/**
	 * 解析航班DATA，返回有座舱位，格式 F8,A2,YA,BA,MA
	 */
	public static List<AvCabinModel> parseAvData(String avData) {
		List<AvCabinModel> cwList = new ArrayList<AvCabinModel>();
		try {
			ParseXml parse = new ParseXml(avData);
			List<Element> hbEleList = parse.get("HB");
			String head = parse.text("HEAD");
			String dateStr = "";
			try{
				dateStr = getDateStr(head);
			} catch(ParseException e){
				return cwList;
			}
			
			for (Element hbEle : hbEleList) {
				String hbh = parse.textTrim("HBH", hbEle);
				hbh = hbh.replace("*", "");
				String cyhbh =parse.textTrim("GXHBH", hbEle);
				String cw = parse.textTrim("CW", hbEle);
				String cw2 = parse.textTrim("CW2", hbEle);
				String subcw = parse.textTrim("SUBCW", hbEle);
				String cfsj = parse.textTrim("CFSJ", hbEle);
				String ddsj = parse.textTrim("DDSJ", hbEle);
				String jx = parse.textTrim("JX", hbEle);
				String[] hzlAndTime = parse.textTrim("OTHER", hbEle).split(" ");
				String cfTime = dateStr+" "+cfsj.substring(0, 2)+":"+cfsj.substring(2,4);
				String ddTime = dateStr+" "+ddsj.substring(0, 2)+":"+ddsj.substring(2,4);
				String hzl ="";
				if(hzlAndTime.length > 2){
					hzl =  hzlAndTime[0]+hzlAndTime[1];
				} else {
					hzl =  hzlAndTime[0];
				}
				
				if (StringUtils.isNotBlank(cw2)) {
					cw += " " + cw2;
				}
				if (StringUtils.isNotBlank(subcw)) {
					cw += " " + subcw;
				}
				if (StringUtils.isNotBlank(cw)) {
					String[] cwArr = cw.split(" ");
					for (String cwzw : cwArr) {
						String cwStr = cwzw.substring(0, cwzw.length() - 1);
						String zwStr = cwzw.substring(cwzw.length() - 1);
						if (zwStr.indexOf("A") != -1 || NumberUtils.toInt(zwStr) > 0) {
							AvCabinModel cabinModel=new AvCabinModel();
							cabinModel.setCw(cwStr);
							cabinModel.setCwNum(zwStr);
							cabinModel.setFjjx(jx);
							cabinModel.setCfsj(cfTime);
							cabinModel.setHzl(hzl);
							cabinModel.setCyhbh(cyhbh);
							cabinModel.setHbh(hbh);
							cwList.add(cabinModel);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cwList;
	}
	private static String getDateStr(String head) throws ParseException{
		String date2 = head.substring(0, 5);
		SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMM", Locale.ENGLISH);
        Date date = sdf1.parse(date2);
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd");
        int year = VeDate.getYear();
        return year+"-"+sdf2.format(date);
	}
}
