package org.vetech.core.business.pid.api.patbyoffice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.business.pid.util.PidUtils;
import org.vetech.core.modules.utils.VeDate;

public class PatByOffice {
	/**
	 * <获取PAT内容>
	 */
	public static String patByOffice(PatByOfficeParam param) throws EtermException{
		validParam(param);
		String hbh=param.getHbh().replaceAll("\\*", "");
		String depatDate=PidUtils.dateToCommandDate(param.getQfrq());
		//SS:MU5904/B/27JUN16/JHGKMG/1
		String ss="SS:"+hbh+"/"+param.getCw()+"/"+depatDate+"/"+param.getHc()+"/1";
		String pat="PAT:A";
		if("2".equals(param.getCjrlx())){
			pat="PAT:A*CH";
		}else if("3".equals(param.getCjrlx())){
			pat="PAT:A*IN";
		}
		WebEtermService etermService=new WebEtermService(param.getUrl());
		return etermService.patByOffice(param.getUserid(), ss, pat, param.getOffice());
	}
	/**
	 * <解析PAT>
	 */
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
	private static void validParam(PatByOfficeParam param) throws EtermException{
		if (StringUtils.isBlank(param.getHc())) {
			throw new EtermException("出发城市不能为空");
		}
		if (StringUtils.isBlank(param.getQfrq()) && !VeDate.isDate(param.getQfrq(), "yyyy-MM-dd") && param.getQfrq().length()==10) {
			throw new EtermException("出发日期格式必须是yyyy-mm-dd");
		}
		if (StringUtils.isBlank(param.getUserid())) {
			throw new EtermException("用户编号不能为空");
		}
		if (StringUtils.isBlank(param.getOffice())) {
			throw new EtermException("OFFICE不能为空");
		}
		if (StringUtils.isBlank(param.getUrl())) {
			throw new EtermException("PID地址不能为空");
		}
		if (StringUtils.isBlank(param.getHbh())) {
			throw new EtermException("航班号不能为空");
		}
		if (StringUtils.isBlank(param.getCw())) {
			throw new EtermException("舱位不能为空");
		}
	}
}
