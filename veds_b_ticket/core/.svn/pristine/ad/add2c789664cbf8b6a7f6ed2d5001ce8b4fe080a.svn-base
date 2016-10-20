package org.vetech.core.business.pid.api.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.vetech.core.business.pid.api.CommandAbstract;
import org.vetech.core.business.pid.pidbean.Command;
import org.vetech.core.business.pid.pidbean.ParseParam;
import org.vetech.core.business.pid.pidbean.PidAvhBean;
import org.vetech.core.business.pid.pidbean.TicketCabin;
import org.vetech.core.business.pid.pidbean.TicketFlight;
import org.vetech.core.business.pid.util.BookUtil;
import org.vetech.core.modules.utils.Arith;

/**
 * 中文航班查询解析类
 * @author  gengxianyan
 * @version  [版本号, Apr 18, 2012]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
@SuppressWarnings("unchecked")
public class ParseAvhAndFd implements Parse {

	/**
	 * 解析
	 * @param param 解析参数
	 * @param command 指令对象
	 * @return 解析结果
	 */
	@Override
	public ParseResult parse(ParseParam param, Command command) {
		ParseResult parseResult = new ParseResult();// 声明返回结果对象数据
		Map<String, String> paramMap = param.getJkData();// 取得数据集合
		
		String avhXml = paramMap.get(CommandAbstract.AVHNAME);// 获得 执行指令后返回的 xml
		String fdXml = paramMap.get(CommandAbstract.FDNAME);
		
		PidAvhBean avhBean = (PidAvhBean) command;
		
		String avhfdXml = ParseCommand.parseAvhAndFd(avhXml, fdXml, new StringBuffer(), parseResult);
		
		if(StringUtils.isEmpty(avhfdXml)) return null;
		
		
		if (StringUtils.isNotBlank(avhfdXml)) {
			parseResult.setAfterParseStr(avhfdXml);
			parse(parseResult, avhBean);
			return parseResult;
		}
		return null;
	}
	
	/**
	 * 直接解析XML返回结果
	 * @param parseResult 解析结果
	 * @param avhBean [参数说明]
	 */
	public void parse(ParseResult parseResult, PidAvhBean avhBean) {
		
		String xml = parseResult.getAfterParseStr();
		String flightNos = "";
		String cabins = "";
		String hkgss = "";
		List<String> legList = new ArrayList<String>();
		
		List<TicketFlight> flightList = new ArrayList<TicketFlight>();
		try {
			Document docavh = DocumentHelper.parseText(xml);
			Element r = docavh.getRootElement();
			List hblist = null;
			if (r != null) {
				hblist = r.elements("HB");
			}
			if (CollectionUtils.isEmpty(hblist)) {
				return;
			}
			String airways = avhBean.getHkgs();
			for (int i = 0; i < hblist.size(); i++) {
				Element hb = (Element) hblist.get(i);
				String hbh = StringUtils.trimToEmpty(hb.elementText("HBH"));
				String hc = StringUtils.trimToEmpty(hb.elementText("HC"));
				String cfsj = StringUtils.trimToEmpty(hb.elementText("CFSJ"));
				String ddsj = StringUtils.trimToEmpty(hb.elementText("DDSJ"));
				String jx = StringUtils.trimToEmpty(hb.elementText("JX"));
				String tlcy = StringUtils.trimToEmpty(hb.elementText("TLCY"));
				String e = StringUtils.trimToEmpty(hb.elementText("E"));
				String cyhbh = StringUtils.trimToEmpty(hb.elementText("GXHBH"));
				String other = StringUtils.trimToEmpty(hb.elementText("OTHER"));
				
				String hkgs = StringUtils.substring(StringUtils.replace(hbh,"*", ""),0,2);
				
				if(StringUtils.isNotEmpty(airways)){
					if(!airways.equals(hkgs)){//如果指定航空公司查询，则过滤只显示指定航空公司的航班
						continue;
					}
				}
				String flightNo = avhBean.getHbh();
				if(StringUtils.isNotBlank(flightNo)){
					if(!flightNo.equals(hbh)){
						continue;
					}
				}
				
				TicketFlight flight = new TicketFlight();
				
				flight.setFlightNo(hbh);
				flight.setCarrFlightNo(cyhbh);
				
				flight.setDepDate(BookUtil.getFormheadTodate(avhBean.getCfdate()));
				
				flight.setAirways(hkgs);
				flight.setDepCity(StringUtils.substring(hc, 0, 3));
				flight.setArrCity(StringUtils.substring(hc, 3, 6));
				
//				B_city depCityBean = getCityAirport(StringUtils.substring(hc, 0, 3));
//				B_city arrCityBean = getCityAirport(StringUtils.substring(hc, 3, 6));
				
//				String depCityMc = depCityBean.getJcmc();
//				String arrCityMc = arrCityBean.getJcmc();
//				
//				flight.setDepCityMc(depCityMc);
//				flight.setArrCityMc(arrCityMc);
				
				flight.setDepTime(StringUtils.substring(cfsj,0,2)+":"+StringUtils.substring(cfsj, 2));
				flight.setArrTime(StringUtils.substring(ddsj,0,2)+":"+StringUtils.substring(ddsj, 2));
				flight.setE(e);
				flight.setFlightMod(jx);
				flight.setMeal(StringUtils.substring(tlcy, 2, 3));
				flight.setStopOver(StringUtils.substring(tlcy, 0, 1));
				
				
//				B_air_price price = new B_air_price();
//				price.setCfcity(flight.getDepCity());
//				price.setDdcity(flight.getArrCity());
//				price.setHkgs(flight.getAirways());
//				price.setYxq(flight.getDepDate());
				
				//long begin = System.currentTimeMillis();
//				B_air_price bean = CabinConvertUtil.getValidB_air_price(price);
//				//System.out.println(flight.getFlightNo()+"机建税费信息耗时："+BookSearchUtil.getTimeCha(begin));
//				
//				if(bean != null){
//					flight.setFuelTax(String.valueOf(bean.getRys()));
//					flight.setAirConFee(String.valueOf(bean.getJsf()));
//				}
				
				//没有取到机建，默认赋值为50
				if(StringUtils.isBlank(flight.getAirConFee()) || "0".equals(flight.getAirConFee())){
					flight.setAirConFee("50");
				}
				
				Map<String, String> otherMap = parseOther(other);
				flight.setFlyTime(otherMap.get("fxsj"));
				flight.setDepTerm(otherMap.get("hzls1"));
				flight.setArrTerm(otherMap.get("hzls2"));
				
				String cws1 = StringUtils.trimToEmpty(hb.elementText("CW"));
				String cws2 = StringUtils.trimToEmpty(hb.elementText("CW2"));
				String subcw = "";
				if (hb.elementText("SUBCW") != null) {
					subcw = StringUtils.trimToEmpty(hb.elementText("SUBCW"));
				}
				String cws = StringUtils.trimToEmpty(cws1 + " " + cws2 + " " + subcw);
				flight.setCabins(cws);
				//parseCabin(flight,avhBean);
				
				//查询飞机机型
//				Map<String, B_comm_plane> fjjxMap = Application.FJJX_MAP;
//				if(MapUtils.isNotEmpty(fjjxMap) && null != fjjxMap.get(flight.getFlightMod())){
//					flight.setFlightModType(fjjxMap.get(flight.getFlightMod()).getTp4());
//				}
				
				flightList.add(flight);
				
				hbh = StringUtils.replace(hbh, "*", "");
				if(!flightNos.contains(hbh)){
					flightNos += hbh + " ";
				}
				
				
				if(!hkgss.contains(hkgs)){
					hkgss += hkgs + " ";
				}
				
				if(!legList.contains(hc)){
					legList.add(hc);
				}
				
			}
			
			parseResult.setFlightList(flightList);
			parseResult.setFlightNos(StringUtils.trimToEmpty(flightNos));
			
			parseResult.setHkgss(StringUtils.trimToEmpty(hkgss));
			
//			//组合舱位折扣数据
//			Map<String, List<CabinCacheBean>> cacheMap = compositeCacheData(legList, hkgss,avhBean.getCfdate());
//			
//			//解析舱位
//			if(CollectionUtils.isNotEmpty(flightList)){
//				for(TicketFlight flight : flightList){
//					parseCabin(flight, avhBean,cacheMap);
//					
//					for(TicketCabin cabinObj : flight.getCabinList()){
//						if(!cabins.contains(cabinObj.getCabin())){
//							cabins += cabinObj.getCabin() + " ";
//						}
//					}
//				}
//			}
			parseResult.setCabins(StringUtils.trimToEmpty(cabins));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * 解析舱位信息
	 * @param flight 航班Bean
	 * @param avhBean [参数说明]
	 * @param cacheMap 缓存数据
	 */
//	public void parseCabin(TicketFlight flight,PidAvhBean avhBean,Map<String, List<CabinCacheBean>> cacheMap){
//		
//		if(MapUtils.isEmpty(cacheMap)){
//			List<String> legList = new ArrayList<String>();
//			legList.add(flight.getDepCity() + flight.getArrCity());
//			cacheMap = compositeCacheData(legList, flight.getAirways(),flight.getDepDate());
//		}
//		
//		List<TicketCabin> cabinList = new ArrayList<TicketCabin>();
//		
//		String cabinNews = "";
//		
//		String cabins = flight.getCabins();
//		
//		String[] cabinxx = cabins.split("\\s");
//		B_air_price priceBean = new B_air_price();
//		priceBean.setCfcity(flight.getDepCity());
//		priceBean.setDdcity(flight.getArrCity());
//		priceBean.setHkgs(flight.getAirways());
//		priceBean.setYxq(flight.getDepDate());
//		
//		//long begin = System.currentTimeMillis();
//		B_air_price bean = CabinConvertUtil.getValidB_air_price(priceBean);
//		
//		//YA/1080
//		for(String cabinStr : cabinxx){
//			
//			if(StringUtils.isBlank(cabinStr)) continue;
//			
//			double legPrice = 0;
//			
//			String cabin = "";
//			String seatNum = "";
//			
//			if(cabinStr.contains("/")){
//				String[] temp = cabinStr.split("/");
//				
//				int len = temp[0].length();
//				
//				cabin = StringUtils.substring(temp[0], 0, len-1);
//				seatNum = StringUtils.substring(temp[0], len-1, len);
//				String price = temp.length > 1 ? temp[1] : "0";
//				
//				if(!"0".equals(price)){//以FD价格为准，如果没有FD价格，则取航段计算价格 [暂时不考虑FD价格]
//					legPrice = Double.parseDouble(price);
//				}
//				
//			}else{
//				int len = cabinStr.length();
//				cabin = StringUtils.substring(cabinStr, 0, len-1);
//				seatNum = StringUtils.substring(cabinStr, len-1, len);
//			}
//			
//			if(StringUtils.isBlank(cabin)) continue;
//			
//			if(avhBean != null){
//				String cabinReq = avhBean.getCw();
//				if(StringUtils.isNotBlank(cabinReq)){//如果指定舱位查询则进行控制【适用于订单详订儿童票】
//					if(!cabinReq.equals(cabin)){
//						continue;
//					}
//				}
//			}
//			
//			
//			
//			CabinCacheBean req = new CabinCacheBean();
//			req.setAirway(flight.getAirways());
//			req.setLeg(flight.getDepCity()+flight.getArrCity());
//			req.setDepDate(flight.getDepDate());
//			req.setCabin(cabin);
//			
//			CabinCacheBean res = CabinConvertUtil.getCabinCacheBeanH(req,bean,cacheMap);
//			
//			String cabinType = "";
//			
//			String cabinMc = ""; 
//			double discount = 0;
//			
//			String isPubTar = "";//是否公布运价1公布运价 0非公布运价
//			
//			if(0 != legPrice){//开启FD，并且有价格，则反算折扣
//				if(bean != null && 0 != bean.getYcj()){
//					discount = Arith.div(legPrice, bean.getYcj(), 2);
//				}
//			}
//			
//			if(res != null){
//				cabinMc = res.getCabinMc();
//				
//				if(0 == legPrice){
//					discount = res.getDiscount();
//					legPrice = res.getPrice();
//				}
//				
//				cabinType = res.getCabinType();
//				
//				isPubTar = res.getIsPubTar();
//				
//			}
//			
//			
//			if(avhBean != null){
//				if(StringUtils.isNotBlank(avhBean.getZk())){
//					double zkl = NumberUtils.toDouble(avhBean.getZk(),0);
//					if(Arith.mul(discount,100) <= zkl){
//						continue;
//					}
//				}
//				
//				if(StringUtils.isNotEmpty(avhBean.getIsXzCwlx())){//是否显示舱位类型
//					if(!"6".equals(cabinType) && !"7".equals(cabinType)){//过滤，只取公务舱/头等舱位
//						continue;
//					}
//				}
//			}
//			
//			
//			//是否只查询公务舱/头等舱
//			if(StringUtils.isNotBlank(flight.getIsNotEconomy())){
//				if(!"6".equals(cabinType) && !"7".equals(cabinType)){//过滤，只取公务舱/头等舱位
//					continue;
//				}
//			}
//			
//			if(StringUtils.isBlank(cabinNews)){
//				cabinNews = cabin+seatNum;
//			}else{
//				cabinNews += " " + cabin+seatNum;
//			}
//			
//			TicketCabin ticketCabin = new TicketCabin();
//			ticketCabin.setCabin(cabin);
//			ticketCabin.setCabType(cabinType);
//			ticketCabin.setIsPubTar(isPubTar);
//			ticketCabin.setCabName(cabinMc);
//			ticketCabin.setDiscount(Arith.mul(discount,100));
//			ticketCabin.setSeatNum(seatNum);
//			
//			
//			ticketCabin.setBillPrice(Arith.round((int)legPrice, 1));//个位数四舍五入，精确到10位数
//			ticketCabin.setBuyPrice(0);
//			
//			cabinList.add(ticketCabin);
//			
//			if(res != null && 0 == flight.getStandPrice()){
//				flight.setStandPrice(res.getStandPrice());
//			}
//		}
//		
//		//全部报价 和 头等商务舱切换时，必须进行单独赋值处理
//		if(avhBean == null){
//			cabinNews = cabins;
//		}else{
//			if(StringUtils.isEmpty(avhBean.getCw()) && StringUtils.isBlank(avhBean.getZk())){
//				if(StringUtils.isEmpty(avhBean.getIsXzCwlx())){//如果不是指定舱位类型查询，则舱位数据，取原始数据
//					cabinNews = cabins;
//				}
//			}
//		}
//		
//		
//		flight.setCabins(cabinNews);//取过滤后的舱位数据
//		flight.setCabinList(cabinList);
//		
//		setMinPubTar(flight);//提取最低公布运价舱位信息
//		
//	}
	
	/**
	 * 解析出飞行时间、起始抵达航站楼
	 * @param other 其他字符串
	 * @return [参数说明]
	 */
	public static Map<String, String> parseOther(String other){
		String hzls1 = "";// 起始 航站楼
		String hzls2 = "";// 到达 航站楼
		String fxsj = "";// 飞行时间
		if (StringUtils.isBlank(other)) {
			other = "";
		} else {// 解析航站楼信息
			// 解析原则 other 字符串长度大于2的 取出飞行时间fxsj
			fxsj = getValuesByReg(other, "(\\d{1,2}[:]\\d{2})");
			if (StringUtils.isNotEmpty(fxsj)) {
				fxsj = fxsj.split("\\$")[0];
				other = other.replace(fxsj, "");
			}
			if (other.length() >= 2) {
				String[] hzls = other.split("\\s+");
				if (hzls != null && hzls.length >= 1) {
					if (hzls[0] != null) {
						hzls1 = hzls[0].replace("-", "");// 起始 航站楼
					}
					if (hzls.length == 2) {
						if (hzls[1] != null) {
							hzls2 = hzls[1].replace("-", "");// 到达 航站楼
						}
					}
				}
			}
		}
		Map<String, String> map=new HashMap<String, String>();
		map.put("hzls1", hzls1);
		map.put("hzls2", hzls2);
		map.put("fxsj", fxsj);
		map.put("other", other);
		return map;
	}
	
	/**
	 * 根据正则表达式取值
	 * 
	 * @param value 值
	 * @param regEx 正则表达式
	 * @return [参数说明]
	 */
	public static String getValuesByReg(String value, String regEx) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(value);
		String result = "";
		while (matcher.find()) {
			String string = matcher.group().replaceAll(regEx, "$1");
			result += string + "$";
		}
		return result;
	}
	
//	/**
//	 * 获取机场名称
//	 * @param citySzm 城市三字码
//	 * @return [参数说明]
//	 */
//	public B_city getCityAirport(String citySzm){
//		return Application.gnjcMap.get(citySzm);
//	}
	
	/**
	 * 提取最低公布运价信息
	 * 1.必须有座
	 * 2.必须为公布运价舱位
	 * @param flight [参数说明]
	 */
	public void setMinPubTar(TicketFlight flight){
		
		if(CollectionUtils.isEmpty(flight.getCabinList())) return;
		
		List<TicketCabin> list = new ArrayList<TicketCabin>();
		
		for(TicketCabin cabin : flight.getCabinList()){
			
			if("0".equals(cabin.getSeatNumC())) continue;
			
			if(!"1".equals(cabin.getIsPubTar())) continue;
			
			list.add(cabin);
			
		}
		
		if(CollectionUtils.isEmpty(list)) return ;
		
		BookUtil.order("discount", list);
		TicketCabin cabin = list.get(0);
		flight.setDiscount(cabin.getDiscount());
		flight.setBillPrice(cabin.getBillPrice());
		
	}
	
//	/**
//	 * 组装特殊航线数据和主舱位表数据
//	 * @param legList 航程集合
//	 * @param hkgss 航空公司数组
//	 * @param cfdate 起飞日期
//	 * @return Map<String, List<CabinCacheBean>> 缓存集合
//	 */
//	public Map<String, List<CabinCacheBean>> compositeCacheData(List<String> legList,String hkgss,String cfdate){
//		
//		Map<String, List<CabinCacheBean>> map = new HashMap<String, List<CabinCacheBean>>();
//		
//		List<CabinCacheBean> cacheList = new ArrayList<CabinCacheBean>();
//		
//		try {
//			for(String leg : legList){
//				cacheList.addAll(B_airway_cw_hcCache.getB_airway_cw_hc(leg));
//			}
//		} catch (NeedsRefreshException e) {
//			e.printStackTrace();
//		}
//		
//		try {
//		    for(String hkgs : hkgss.split(" ")){
//		        cacheList.addAll(B_airway_cw_hcCache.getB_airway_cw_hc(hkgs));
//		    }
//		} catch (NeedsRefreshException e) {
//            e.printStackTrace();
//        }
//		
//		for(CabinCacheBean bean : cacheList){
//			String key = bean.getAirway() + "_" + bean.getCabin();
//			
//				
//			Date yxq = VeDate.strToDate(bean.getValidDate());
//			
//			String depDateStr = bean.getDepDate();
//			if(StringUtils.isEmpty(depDateStr)){
//				depDateStr = "1900-01-01";
//			}
//			
//			Date depDate = VeDate.strToDate(depDateStr);
//			
//			Date currDate = new Date();
//			Date flightDate = VeDate.strToDate(cfdate);
//			
//			double days = NumberUtils.toDouble(VeDate.getTwoDay(cfdate, VeDate.getStringDateShort()),0);//提前天数
//			double earlyDayBeg = NumberUtils.toDouble(bean.getEarlyDayBegin(),0);//提前出票时间始
//			double earlyDayEnd = NumberUtils.toDouble(bean.getEarlyDayEnd(),999);//提前出票时间止
//			
//			if(!yxq.after(currDate) && !depDate.after(flightDate) && (earlyDayBeg <= days && days <= earlyDayEnd)){
//				
//				if(CollectionUtils.isEmpty(map.get(key))){
//					List<CabinCacheBean> list = new ArrayList<CabinCacheBean>();
//					list.add(bean);
//					map.put(key, list);
//				}else{
//					map.get(key).add(bean);
//				}
//				
//			}
//			
//		}
//		
//		return map;
//	}
	
	/**
	 * Test
	 * @param args [参数说明]
	 */
	public static void main(String[] args) {
		System.out.println((int)1504.95);
		int price = (int) Arith.round((int)1505.95, 1);
		System.out.println(price);
	}
}