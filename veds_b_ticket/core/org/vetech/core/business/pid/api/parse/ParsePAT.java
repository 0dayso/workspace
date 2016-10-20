package org.vetech.core.business.pid.api.parse;

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

/**
 * PAT内容专用解析类
 * 
 * @author gengxianyan
 * @version [版本号, Jun 3, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class ParsePAT {
	
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
}
