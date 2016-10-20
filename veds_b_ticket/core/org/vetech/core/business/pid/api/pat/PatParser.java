package org.vetech.core.business.pid.api.pat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.business.pid.api.book.SpellBookCommand;
import org.vetech.core.business.pid.exception.EtermException;

/**
 * Pat解析
 * @author wangxuexing
 */
public class PatParser {

	/**
	 * 拼装SS指令项，以便执行PAT
	 * @param hbh 航班号
	 * @param cw 舱位
	 * @param cfcity 出发城市
	 * @param ddcity 到达城市
	 * @param rs 人数，默认为1
	 * @param cfdate 出发日期 格式：13JUN
	 * @param ifkw 是否Ｋ位 不为空，标示此舱位无座[如果无座位的话 拼装指令需要增加LL行动代码]
	 * @return ss指令项
	 */
	public static String spellSsCommand(String hbh,String cw, String cfcity, String ddcity, String rs, String cfdate, String ifkw){
		SpellBookCommand command = new SpellBookCommand();
		try {
			
			hbh = StringUtils.replace(hbh, "*","");
			if("ARNK".equals(hbh)){
			    return "";
			}
			command.addAirSeg(hbh, cw, cfcity, ddcity, rs, cfdate, ifkw);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return command.getSs();
	}

	/**
	 * 解析PAT内容，或者PAT价格集合
	 * @param patInfo PAT内容
	 * @return [参数说明]
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,String>> parsePat(String patInfo) {
		
		String[] patLine = patInfo.split("SFC:\\d{2}");
		
		String patternStr = "(\\d{2})\\s+(\\S+)\\s+FARE:[a-zA-Z]{3}(\\S+)\\s+TAX:[a-zA-Z]{3}(\\S+)\\s+YQ:[a-zA-Z]{3}(\\S+)\\s+TOTAL:(\\S+)";
		
		List<Map<String,String>> patList = new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < patLine.length; i++) {
			
			String[] pjLine = StringUtils.split(patLine[i], "\r|\n");
			
			for (int j = 0; j < pjLine.length; j++) {
				
				Pattern pattern = Pattern.compile(patternStr);
				Matcher matcher = pattern.matcher(pjLine[j]);
				
				if (matcher.find()) {
					Map<String,String> m = new HashMap<String,String>();
					
					String pj = matcher.group(3);
					String jsf = matcher.group(4);
					String tax = matcher.group(5);
					String total = matcher.group(6);
					
					String lx = matcher.group(2);// 获取 pat中含乘机人类型的信息
					if (StringUtils.isBlank(lx)) {
						lx = "";
					}
					if (lx.contains("ZS")) { // 含 ZS青年票
						lx = "ZS";
					} else if (lx.contains("CD")) { // 含 CD老年票
						lx = "CD";
					} else { // 成人票
						lx = "";
					}
					
					if(!NumberUtils.isNumber(jsf)){
						jsf = "0";
					}
					if(!NumberUtils.isNumber(tax)){
						tax = "0";
					}
					
					m.put("PJLX", lx);
					m.put("PJ", pj);
					m.put("TAX", tax);
					m.put("JSF", jsf);
					m.put("TOTAL", total);
					
					patList.add(m);
				}
			}
		}
		
		Collections.sort(patList,new BeanComparator("PJ"));
		
		return patList;
	}
	
	/*
	 * >PAT:A                                                                          
		01 YX35 FARE:CNY200.00 TAX:CNY50.00 YQ:CNY40.00  TOTAL:290.00   
		SFC:01 
		02 YX14ZS FARE:CNY270.00 TAX:CNY50.00 YQ:CNY40.00  TOTAL:360.00   //含 ZS青年票
		SFC:02 
		03 YX14CD FARE:CNY270.00 TAX:CNY50.00 YQ:CNY40.00  TOTAL:360.00   //含 CD老年票
		SFC:03
	 */
	public static String parsePatTopjNormal(String patInfo) throws EtermException{//返回 除青年票价 老年票价的最低票价 及 相应的 燃油税 和 机场建设费
		String []patInfoLine=patInfo.split("SFC:\\d{2}");
		String strPattern="(\\d{2})\\s+(\\S+)\\s+FARE:[a-zA-Z]{3}(\\S+)\\s+TAX:[a-zA-Z]{3}(\\S+)\\s+YQ:[a-zA-Z]{3}(\\S+)\\s+TOTAL\\s*:(\\S+)";
		List<Double> pjList=new ArrayList<Double>();
		Map jginfo = new HashMap();//存储价格信息 key：票价，value：tax_yq
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
				} else {
					if(i==0 && j+1 == pjLine.length){
						throw new EtermException(patInfo);
					}
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
