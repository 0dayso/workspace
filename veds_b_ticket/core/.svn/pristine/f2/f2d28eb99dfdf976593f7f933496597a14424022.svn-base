package org.vetech.core.business.pid.api.avh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.vetech.core.modules.utils.ParseXml;
import org.vetech.core.modules.utils.VeDate;

public class AvhParser {
	public static String head(String eterm_str) {
		StringBuffer format = new StringBuffer();
		String pp = "([^\r\n]+)"; // 匹配换行符前面的字符串，主要是把头部取出来少
		Pattern p1 = Pattern.compile(pp);
		Matcher m1 = p1.matcher(eterm_str);
		if (m1.find()) {
			format.append(m1.group());
		}
		return format.toString();
	}
	/**
	 * 特殊(** M1A K1A L1A Q1A V1A) 04SEP(FRI) CTUSZX                                                              
		1-  CZ3434  DS# F8 AQ P4 YA TA KA HA MA GA SA  CTUSZX 1250   1450   319 0^L  E  
		>               LA QA UA EA VA BA XA NA RS                                      
		2   CA4313  DS# F8 AS YA BA MA HA KA LA QA GA  CTUSZX 1305   1500   757 0^   E  
		>               SA NS VA UA W5 TA EA                                            
		               ** M1A K1A L1A Q1A V1A                                           
		3   3U8703  DS# F8 A2 PS YA TA HA MA GA SA LA  CTUSZX 1330   1530   320 0 S  E  
		>               QA EA VS RA KS IS B8 XS UA WA NS ZS O3                          
		4   CZ3454  DS# F8 P4 YA TA KA HA MA GA SA LA  CTUSZX 1550   1740   320 0^C  E  
		>               QA UA EA VA BA XA NA RS                                         
		5   3U8705 AS#FA A2 PS YA TA HA MA GA SA LA  CTUSZX 1715   1915   321 0 D  E  
		>               QA EA VS RA KS IS BA XS UA WA N5 ZS O3                          
		6   CZ3458  DS# F8 P4 YA TA KA HA MA GA SA LA  CTUSZX 1730   1930   319 0^D  E  
		>               QA UA EA VA BA XA NA RS                                         
		7+  CA4311  DS# F8 AS YA BA MA HA KA LA QA GA  CTUSZX 1755   1950   757 0^   E  
		>               SA NS VA UA WS T7 EA                                            
		               ** M1A K1A L1A Q1A V1A 
	 * @param eterm_str
	 * @return
	 */
	public static String EtermToXml(String eterm_str) {
	    
	    if("1".equals(System.getProperty("HPFLAG"))){
	        System.out.println("黑屏小配置AVH解析前:"+eterm_str);
        }
		// long ks=System.currentTimeMillis();
	    //eterm_str = StringUtils.replace(eterm_str, "\n", "\r");
	    //eterm_str = StringUtils.replace(eterm_str, ">", "\r>");
		eterm_str = StringUtils.replace(eterm_str, "&gt;", ">");
		//**  PLEASE CHECK YI:CZ/TZ144 FOR ET SELF-SERVICE CHECK-IN KIOS 
		//String pp1 = "((\\s*\\*\\*\\s*){1,2}[^\r]+)";// root
		String pp1 = "^((\\s(\\*\\*)?\\s*){1,2}[^\r]+)$";// root
		//** M1A V1S 
		//String sub_cw="((\\s*\\*\\*\\s*)([A-Z]\\d\\w\\s+){1,20})";
//		String sub_cw="((\\s*\\*\\*\\s*)(([A-Z]\\d\\w\\s+)*)?)"; 
//		String sub_cw="((\\s*\\*\\*\\s*)([A-Z]\\d\\w\\s*){1,20})|((\\s{10,}\\*\\*)([^\\d]))";
		String sub_cw="((\\s*\\*\\*\\s*)([A-Z][0-9][A-Z0-9]\\s+){1,20})|((\\s{10,}\\*\\*)([^\\d]))$";//南航A380处理
		//小张磊原来的匹配
		//String pp_1 = "((\\d{0,1})((\\-|\\+|\\s){0,1})\\s{0,1}((\\*|\\s){0,1}))(\\w{5,7})\\s{0,3}(\\w{0,2}(\\#|\\*|\\s|!))\\s*((\\w{2}\\s+){0,10})\\s+(\\w{3,6})\\s+(([0-9]{4}(\\+\\d|\\s+))?)\\s*(([0-9]{4}(\\+\\d|\\s+))?)\\s*(\\w+)\\s+(\\d{0,1})(\\^|\\s|\\')(\\s*|\\w|\\s*)\\s+(E?)\\s*";
		//为了加AA指令将序号放为2位 pengjun
		//String pp_1 = "((\\d{0,2})((\\-|\\+|\\s){0,1})\\s{0,1}((\\*|\\s){0,1}))(\\w{5,7})\\s{0,3}(\\w{0,2}(\\#|\\*|\\s|!))\\s*((\\w{2}\\s+){0,10})\\s+(\\w{3,6})\\s+(([0-9]{4}(\\+\\d|\\s+))?)\\s*(([0-9]{4}(\\+\\d|\\s+))?)\\s*(\\w+)\\s+(\\d{0,1})(\\^|\\s|\\')(\\s*|\\w|\\s*)\\s+(E?)\\s*";
		//为了匹配连接方式LCC   shenjianxin 2+  FD3649  LCC VZ PZ LZ UZ TZ QZ MZ KZ HZ YZ  CANBKK 2355   0145+1 320 0
		//2012-03-03 针对3  *UA7570    * FA AL YA BA EA MA UA HA QA VA  XIYPEK 1025   1220   737 0 S  E  这种航班号和舱位之间空格长度大于3进行修正
		//String pp_1 = "((\\d{0,2})((\\-|\\+|\\s){0,1})\\s{0,1}((\\*|\\s){0,1}))(\\w{5,7})\\s{0,5}(\\w{0,2}(\\#|\\*|\\s|!|C))\\s*((\\w{2}\\s+){0,10})\\s+(\\w{3,6})\\s+(([0-9]{4}(\\+\\d|\\s+))?)\\s*(([0-9]{4}(\\+\\d|\\s+))?)\\s*(\\w+)\\s+(\\d{0,1})(\\^|\\s|\\')(\\s*|\\w|\\s*)\\s+(E?)\\s*";
		//针对国际航程AVH时有些航线到达时间解析不全
		String pp_1 = "((\\d{0,2})((\\-|\\+|\\s){0,1})\\s{0,1}((\\*|\\s){0,1}))(\\w{5,7})\\s{0,5}(\\w{0,2}(\\#|\\*|\\s|!|C))\\s*((\\w{2}\\s+){0,10})\\s+(\\w{3,6})\\s+(([0-9]{4}([\\+|\\-]\\d|\\s+))?)\\s*(([0-9]{4}([\\+|\\-]\\d|\\s+))?)\\s*(\\w+)\\s+(\\d{0,1})(\\^|\\s|\\')?([\\s*]|[\\w]|[\\s*])?\\s*([A-Z]?)\\s*";
		String pp_1_1 = "^(((\\d{0,2})((\\-|\\+|\\s){0,1})\\s{0,1})*\\s{0,1}((\\*|\\s){0,1}))(\\w{5,7})\\s{0,5}(\\w{0,2}(\\#|\\*|\\s|!|C))\\s*((\\w{2}\\s+){0,10})\\s+(\\w{3,6})\\s+(([0-9]{4}([\\+|\\-]\\d|\\s+))?)\\s*(([0-9]{4}([\\+|\\-]\\d|\\s+))?)\\s*(\\w+)\\s+(\\d{0,1})(\\^|\\s|\\')?([\\s*]|[\\w]|[\\s*])?\\s*([A-Z]?)\\s*$";
//		String pp_2 = "(>){1}\\s+(\\w{5,6}|\\s+)\\s{0,6}((\\w{2}\\s{0,1}){0,20})\\s*((\\w+\\s*(\\-){0,2}(\\w+)?)|((\\-){0,2}(\\w+)?\\s*\\w+))?((\\[ZZZZ\\]){0,1})";
//		String pp_2 = "(>){1}\\s+(\\w{5,6}|\\s+)\\s{0,6}((\\w{2}\\s{0,1}){0,20})\\s*(([A-Z]{1}([0-9]{1})?\\s*(\\-){0,2}([A-Z]{1}([0-9]{1})?)?(\\s*[0-9]{1,2}[:][0-9]{2})?)|(((\\-){0,2}([A-Z]{1}([0-9]{1})?)?[A-Z]{1,2})?([0-9]{1,2}[:][0-9]{2})?))?((\\[ZZZZ\\]){0,1})";//新版
//		String pp_2 = "(>){1}\\s+(\\w{5,6}|\\s+)\\s{0,7}((\\w{2}\\s{0,1}){0,20})(\\s{5,}((\\-){0,2}\\s+([\\d\\w]{0,2}|[\\w\\d]{0,2})|((\\-){2}\\s+(\\-){2})|(([\\d\\w]{0,2}|[\\w\\d]{0,2})\\s+)(\\-){2}|(([\\d\\w]{0,2}|[\\w\\d]{0,2})\\s+([\\d\\w]{0,2}|[\\w\\d]{0,2})))\\s+([\\d]{1,2}[:][\\d]{2})?(\\[ZZZZ\\]{0,1})?)?";//新版2 子舱位解析不全
		//String pp_2 = "(>){1}\\s+(\\w{5,6}|\\s+)\\s{0,7}((\\w{2}\\s{0,1}){0,20})(\\s{5,}(((\\-){2}\\s?((\\w{1,2})|(\\-){2})?)|(([\\w]{1,2})\\s+(\\-){2})|(([\\w]{1,2})\\s+([\\w]{1,2})))?\\s*([\\d]{1,2}[:][\\d]{2})?(\\[ZZZZ\\]{0,1})?)?";//新版2 子舱位解析不全
		String pp_2 = "(>){1}\\s{0,3}(\\w{5,6}|\\s{5,6})\\s{0,7}((\\w{2}\\s{0,1}|\\s{2,3}){0,17})(\\s{0,}(((\\-){2}\\s?((\\w{1,2})|(\\-){2})?)|(([\\w]{1,2})\\s+(\\-){2})|(([\\w]{1,2})\\s+([\\w]{1,2})))?\\s*([\\d]{1,2}[:][\\d]{2})?(\\[SUBCABIN\\]{0,1})?(\\[PACKCABIN\\]{0,1})?)?";//针对部分航站楼解析成舱位，造成子舱位解析问题。2013-05-13
		
		String cw_hzl = "(>){1}\\s+(\\w{5,6}|\\s+)\\s{0,7}(\\s{50,})((((\\-){2}\\s?((\\w{1,2})|(\\-){2})?)|(([\\w]{1,2})\\s+(\\-){2})|(([\\w]{1,2})\\s+([\\w]{1,2})))?\\s*([\\d]{1,2}[:][\\d]{2})?)(\\[ZZZZ\\]{0,1})?";//针对部分航站楼解析成仓位问题增加判断
		// String pp =
		// "((\\d{0,1})((\\-|\\+|\\s){0,1})\\s{0,1}((\\*|\\s){0,1}))(\\w{5,7})\\s{0,3}(\\w{0,2}(\\#|\\*|\\s))\\s*((\\w{2}\\s+){0,10})\\s+(\\w{3,6})\\s+(([0-9]{4}(\\+1|\\s+))?)\\s+(([0-9]{4}(\\+1|\\s+))?)\\s+(\\w+)\\s+(\\d{0,1})(\\^|\\s)(\\s*|\\w|\\s*)\\s+(E?)\\s+(>){1}\\s+(\\w{5,6}|\\s+)\\s+((\\w{2}\\s{0,1}){0,20})(\\s*((\\-){2}\\s(\\d{1}))?)";
		// 1 序号2 +- 3,4 * 5,6 7 8，9 10，11 12 13,14,15 16，17，18 19 20 21 22 23 24
		// 25 26，27 28，29，30，31
		
		String pack_cw = "(\\s{10,}\\*\\*\\s([A-Z][A-Z|0-9]{2}\\s)+[\\+]?([\\\r]*[A-Z][\\\r]*[A-Z|0-9][\\\r]*[A-Z|0-9]?[\\s]?[\\\r]*([:][\\\r]?[A-Z|0-9][\\\r]?)?)*)";//打包舱位产品
		
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?><HBS>");

        //打包舱位解析
		List<String> packCwList = new ArrayList<String>();
        Pattern packPat = Pattern.compile(pack_cw);
        Matcher packMatch = packPat.matcher(eterm_str);
        
        while (packMatch.find()) {
            //packCwMap.put(packCount + "[PACKCABIN]", packMatch.group(1));
            packCwList.add(packMatch.group(1));
        }
        
        eterm_str=eterm_str.replaceAll(pack_cw, "[PACKCABIN]\r");
        eterm_str=eterm_str.replaceAll(sub_cw, "[SUBCABIN]\r");
		
		Pattern p = Pattern.compile(pp1);
		Pattern turnPattern = Pattern.compile(pp_1_1);
		xml.append("<ROOT>");
		
		StringBuffer rootStr = new StringBuffer();
		String eterm_strs[] = eterm_str.split("\r");
		
		boolean isNotFirstFlight = false;
		
		int packCount = 0;//原来的顺序号
		
		StringBuffer tmp = new StringBuffer();
		for(int i=0;i<eterm_strs.length;i++){
		    String str = eterm_strs[i];
		    
		    if(StringUtils.isBlank(str)) continue;//前面子舱位/打包舱位替换后会产生空行出来，这个地方进行判断，如果为空则跳过循环，循环下一行
		    
		    boolean isMatch = false;
		    if(i>0){//从Head下一行开始处理
		        
	            if(StringUtils.contains(str, "[PACKCABIN]")){
	                if(!isNotFirstFlight && StringUtils.isNotBlank(packCwList.get(packCount))){
	                    packCwList.remove(packCount);
	                    packCount ++;
	                    continue;
	                }
	            }
		        
		        String tmpStr = "";
		        Matcher mt = p.matcher(str);
		        while (mt.find()) {
		            tmpStr = mt.group(1) + "\n";
		        }
		        
		        Matcher turnMatcher = turnPattern.matcher(str);
		        if(StringUtils.isNotBlank(tmpStr) && !turnMatcher.find()){//匹配Root但是不匹配航班
		            rootStr.append(tmpStr);
		            isMatch = true;
		        }
		        
		        
		        if(!isNotFirstFlight){
		            if(!turnMatcher.find()){
		                isMatch = true;
		                
		            }else{
		                isNotFirstFlight = true;
		            }
		        }
		    }
		    
		    if(!isMatch){//不是Root节点存放的内容
	            tmp.append(str+"\r");
	        }
		}
		
		xml.append(rootStr);
		
		xml.append("</ROOT>");
		
		if(StringUtils.isNotBlank(tmp.toString())){
		    eterm_str = tmp.toString();
		}
		
		eterm_strs = eterm_str.split("\r");
	
		int length = eterm_strs.length;
		if (eterm_strs.length >= 1 && StringUtils.trim(eterm_strs[eterm_strs.length - 1]).equals(">")) {
			length = eterm_strs.length - 1;
		}
		
		packCount = 0;//打包舱位替换所在序号
		
		try {
			for (int i = 0; i < length; i++) {
				if (i == 0) {
					String head = head(eterm_strs[i]);
					xml.append("<HEAD>");
					xml.append(StringUtils.trim(head));
					xml.append("</HEAD>");
				}else{
					if (eterm_strs[i].indexOf(">") > -1) {
					    
					    int indexHB = StringUtils.lastIndexOf(xml.toString(), "<HB>");
					    int indexHBE = StringUtils.lastIndexOf(xml.toString(), "</HB>");
					    
					    if(indexHB <= indexHBE) continue;//过滤第二行开始，不是正常的航班行，而是航班下面的一行（舱位或承运航班号行，例如：>   CA1411      Q4 V4 W4 KL                                         T3 T2  2:55）
					    
						Pattern p1 = Pattern.compile(pp_2);
						Matcher m1 = p1.matcher(eterm_strs[i]);
						if (m1.find()) {
							
							String cw = StringUtils.trimToEmpty(m1.group(3));
							String other = StringUtils.trimToEmpty(m1.group(5));
							
							//针对这种格式>                                                                  T3 --  2:35
							Pattern p2 = Pattern.compile(cw_hzl);
							Matcher m2 = p2.matcher(eterm_strs[i]);
							if(m2.find()){
								cw = StringUtils.trimToEmpty(m2.group(3));
								other = StringUtils.trimToEmpty(m2.group(4));
							}
							
							xml.append("<FH>").append(StringUtils.trimToEmpty(m1.group(1))).append("</FH>");
							xml.append("<GXHBH>").append(StringUtils.trimToEmpty(m1.group(2))).append("</GXHBH>");
							xml.append("<CW2>").append(StringUtils.trimToEmpty(cw)).append("</CW2>");
							
							if(StringUtils.isNotBlank(m1.group(20)) && m1.group(20).indexOf("[PACKCABIN]")>-1) {//打包舱位处理
                                String packCwStr = StringUtils.replace(packCwList.get(packCount),"**","");
                                if(StringUtils.isNotBlank(packCwStr)){
                                    packCwStr = StringUtils.replace(packCwStr, "\n", "");
                                    packCwStr = StringUtils.replace(packCwStr, "\r", "");
                                    packCwStr = StringUtils.replace(packCwStr, "+", "");
                                    //packCwStr = StringUtils.trimToEmpty(packCwStr);
                                    
                                    Pattern pattern = Pattern.compile("(([A-Z][0-9][A-Z0-9]\\s+){1,20})");
                                    Matcher matcher = pattern.matcher(packCwStr);
                                    String subCw = "";
                                    String packCw = "";
                                    while(matcher.find()){
                                        subCw = matcher.group(0);
                                    }
                                    
                                    packCw = StringUtils.replace(packCwStr, subCw, "");
                                    
                                    subCw = StringUtils.trimToEmpty(subCw);
                                    packCw = StringUtils.trimToEmpty(packCw);
                                    
                                    if(StringUtils.isNotBlank(subCw)){
                                        xml.append("<SUBCW>").append(subCw).append("</SUBCW>");
                                    }
                                    if(StringUtils.isNotBlank(packCw)){
                                        xml.append("<DBCW>").append(packCw).append("</DBCW>");//, ":", "<![CDATA[:]]>")
                                    }
                                }
                                packCount++;
                            }
							
							other = StringUtils.trimToEmpty(other);
							other = other.replace("[SUBCABIN]", "");
							other = other.replace("[PACKCABIN]", "");
							xml.append("<OTHER>").append(other).append("</OTHER>");
						} else {
							// 解析不成功，会导致后面生成html报错。所以返回null,这样直接返回航信的数据
							return null;
						}
						xml.append("</HB>");
					} else {
						Pattern p1 = Pattern.compile(pp_1);
						Matcher m1 = p1.matcher(eterm_strs[i]);
						if (m1.find()) {
							xml.append("<HB>");
							xml.append("<XH>").append(StringUtils.trimToEmpty(m1.group(2))).append(
									StringUtils.trimToEmpty(m1.group(3))).append("</XH>"); // 序号
							xml.append("<HBH>").append(StringUtils.trimToEmpty(m1.group(5))).append(
									StringUtils.trimToEmpty(m1.group(7))).append("</HBH>"); // *
							xml.append("<DS>").append(StringUtils.trimToEmpty(m1.group(8))).append("</DS>"); // ds#
							xml.append("<CW>").append(StringUtils.trimToEmpty(m1.group(10))).append("</CW>"); // cangwei
							xml.append("<HC>").append(StringUtils.trimToEmpty(m1.group(12))).append("</HC>");
							xml.append("<CFSJ>").append(StringUtils.trimToEmpty(m1.group(13))).append("</CFSJ>");
							xml.append("<DDSJ>").append(StringUtils.trimToEmpty(m1.group(16))).append("</DDSJ>");
							xml.append("<JX>").append(StringUtils.trimToEmpty(m1.group(19))).append("</JX>");
							xml.append("<TLCY>").append(StringUtils.trimToEmpty(m1.group(20))).append(
									ObjectUtils.toString(m1.group(21)," ")).append(StringUtils.trimToEmpty(m1.group(22))).append("</TLCY>");
							xml.append("<E>").append(StringUtils.trimToEmpty(m1.group(23))).append("</E>");
							if (i == length - 1) {// 当到了最后一个判断，最后少了一个>，因为不可能在进入上面一个判断
								xml.append("<FH>").append("").append("</FH>");
								xml.append("<GXHBH>").append("").append("</GXHBH>");
								xml.append("<CW2>").append("").append("</CW2>");
								xml.append("<OTHER>").append("").append("</OTHER>");
								xml.append("</HB>");
							}
							// 判断航班后面有没有>,如果没有就就加入空的标记。因为有可能航班后面没有>
							if (i + 1 <= length - 1 && eterm_strs[i + 1].indexOf(">") <= -1) {
								xml.append("<FH>").append("").append("</FH>");
								xml.append("<GXHBH>").append("").append("</GXHBH>");
								xml.append("<CW2>").append("").append("</CW2>");
								xml.append("<OTHER>").append("").append("</OTHER>");
								xml.append("</HB>");

							}
						} else {
						    if(i != length -1){
						        p1 = Pattern.compile(pp_1);
		                        m1 = p1.matcher(eterm_strs[i+1]);
	                            //解析这种格式存放到ROOT节点里面: 8月1号开始,为回馈同行,三四季度MU/FM国际扣率全面调高,详情:www.twflight.com 
	                            xml = new StringBuffer(StringUtils.replace(xml.toString(), "<ROOT>"+rootStr+"</ROOT>", "<ROOT>"+rootStr+"\n"+eterm_strs[i]+"\n</ROOT>"));
	                            rootStr.append("\n"+eterm_strs[i]+"\n");
						    }else{
						        if(StringUtils.isNotBlank(eterm_strs[i]) && !rootStr.toString().contains(eterm_strs[i])){
						            //解析这种格式存放到ROOT节点里面: 8月1号开始,为回馈同行,三四季度MU/FM国际扣率全面调高,详情:www.twflight.com 
						            xml = new StringBuffer(StringUtils.replace(xml.toString(), "<ROOT>"+rootStr+"</ROOT>", "<ROOT>"+rootStr+"\n"+eterm_strs[i]+"\n</ROOT>"));
						        }
						    }
						}
					}
				}
			}
			xml.append("</HBS>");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		if("1".equals(System.getProperty("HPFLAG"))){
		    System.out.println("黑屏小配置AVH解析后xml:"+xml.toString());
        }
		
		return xml.toString();
	}
	/**
	 * 1.分割换行付 2.80个字符换行 3.去掉空行
	 * 
	 * @param eterm
	 * @return
	 * 格式化AVH返回的文本数据
	 */
	public static String parseEterm(String eterm) {
		if (StringUtils.isBlank(eterm)) {
			return "";
		}
		String[] s = eterm.split("\\r|\\n");
		StringBuffer tmp = new StringBuffer();

		for (int i = 0; i < s.length; i++) {
			if (s[i].length() > 80) {
				for (int j = 0; j < s[i].length() / 80; j++) {
					String tmp80 = StringUtils.substring(s[i], 2 * j * 80,
							(2 * j + 1) * 80);
					String tmp80after = StringUtils.substring(s[i],
							(2 * j + 1) * 80, (2 * j + 2) * 80);
					if (StringUtils.isNotBlank(tmp80)) {
						tmp.append(StringUtils.replace(tmp80, "\r", "") + "\r");
					}
					if (StringUtils.isNotBlank(tmp80after)) {
						tmp.append(StringUtils.replace(tmp80after, "\r", "")
								+ "\r");
					}
				}
			} else {
				if (StringUtils.isNotBlank(StringUtils.replace(s[i], "\r", ""))) {
					tmp.append(s[i] + "\r");
				}
			}
		}
		return tmp.toString();
	}
	/*
	 09AUG(SUN) SZXWUH                                                              
	1   CZ6655  SZXWUH 1500   1630   319 0^          EFL AX PL CX DX                
	                   IX JX YL TQ KQ HQ MQ GQ SQ LQ QQ UL EQ VQ BQ XQ NQ RQ OL     
	2   MU2634  SZXWUH 1600   1740   733 0           EF3 AS YL KL BL                
	                   EL HL LL ML NL RL SL VL TL WL XL GL QL UL IL ZQ              
	3   CZ3356  SZXWUH 1730   1900   738 0^C         EF5 AQ P2 CX DX                
	                   IX JX YA TQ K4 HQ MQ GQ SQ LQ QQ US EQ VQ BS XQ NQ RQ O3     
	4   ZH9897  SZXWUH 1840   2020   319 0^          EF2 AS DS YA GS<B -->          
	                   KS HS TS QS LS SS MS ES BS US NS RS JS VS ZS IS P5 XA WS     
	5+  ZH9893  SZXWUH 1920   2045   738 0^          EFL AL DS YA GS<B -->          
	                   KS HS TS QS LS SS MS ES BS US NS RS JS VS ZS IS P5 XA W2 
	*/
	/**
	 30SEP(FRI) BJSWUH DIRECT ONLY  
	 1-  CZ6606  PEKWUH 0645   0900   738 0^C  E   AS# FL PQ WL ZQ YL TQ KQ HQ MQ GQ*
	 2  *ZH1333  PEKWUH 0735   0940   330 0^B  E   DS# FL YL BQ MQ HQ KQ LQ QQ GQ SL*
	 3   CA1333  PEKWUH 0735   0940   330 0^B  E   DS# FL AQ PQ YL BQ MQ HQ KQ LQ QQ*
	 4   CZ6366  PEKWUH 0740   1000   733 0^C  E   AS# YL TQ KQ HQ MQ GQ SQ LQ QQ UL*
	 5   MU2460  PEKWUH 0825   1035   738 0    E   DS# FL PL AL YL KL BL EL HL LL ML*
	 6   CZ3118  PEKWUH 1035   1255   738 0^L  E   AS# FL PQ WL ZQ YL TQ KQ HQ MQ GQ*
	 7   CA1475  PEKWUH 1110   1315   738 0^L  E   DS# FL AQ PQ YL BQ MQ HQ KQ LQ QQ*
	 8  *SC1475  PEKWUH 1110   1315   738 0^L  E   DS# FL YL BQ HQ KQ LQ QQ VQ SL UQ*
	 9+ *ZH1475  PEKWUH 1110   1315   738 0^L  E   DS# FL YL BQ MQ HQ KQ LQ QQ GQ SL*
	 **  PLEASE CHECK YI:CZ/TZ144 FOR ET SELF-SERVICE CHECK-IN KIOS
	 ***/
	public static String BigPidEtermToXml(String eterm_str) {
		eterm_str = StringUtils.replace(eterm_str, "&gt;", ">");
		String []eterm_strs=eterm_str.split("\r");
		
		//航班表达式
//		String hbmatch="(\\d?[\\+\\-]?)\\s+(\\*)?((?:[0-9][A-Z]|[A-Z][0-9]|[A-Z]{2})\\d{3,4}[A-Z]?)\\s*([A-Z]{3})?([A-Z]{3})\\s+(\\d{4}(?:[\\+-]\\d+)?)\\s*(\\d{4}(?:\\+\\d+)?)\\s*(\\w+)?\\s*((\\d)(\\^|\\s|')([A-Z]|\\s))\\s+([A-Z])?\\s+([A-Z])?\\s+E?(.*)";
		String hbmatch="(\\d?[\\+\\-]?)\\s+(\\*)?((?:[0-9][A-Z]|[A-Z][0-9]|[A-Z]{2})\\d{3,4}[A-Z]?)\\s*([A-Z]{3})?([A-Z]{3})\\s+(\\d{4}(?:[\\+-]\\d+)?)\\s*(\\d{4}(?:\\+\\d+)?)\\s*(\\w+)?\\s*((\\d)(\\^|\\s|')([A-Z]|\\s))\\s+([A-Z])?\\s+([A-Z])?\\s+([E?.*E]{0,1}[E$]){0,1}(([\\w]{2}[#]?\\s{0,})?([\\w{2}\\s{0,}]+)([&lt;](.*)[>$]){0,1})(([<]{0,1}[\\S]{0,4}[>]{0,1}))?";
//		String cwmatch="\\s{3}\\s+((?:[0-9][A-Z]|[A-Z][0-9]|[A-Z]{2})\\d{3,4}[A-Z]?)?\\s+((?:\\s+\\w{2})+)";
		String cwmatch="^[^0-9]{0,1}\\s{3}\\s+((?:[0-9][A-Z]|[A-Z][0-9]|[A-Z]{2})\\d{3,4}[A-Z]?)?\\s+((\\w{2}\\s{0,})*)";
		String sub_cw="((\\s*\\*\\*\\s*)([A-Z]\\d\\w\\s){1,20})";
		//获得头部信息	09AUG(SUN) SZXWUH
		String head="";
		if(eterm_strs.length>=0){
			head=eterm_strs[0];
		}
		StringBuffer xml=new StringBuffer();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><HBS>");
		xml.append("<HEAD>");
		xml.append(StringUtils.trim(head));
		xml.append("</HEAD>");
		StringBuffer root=new StringBuffer(); 
		xml.append("<ROOT>").append("##########").append("</ROOT>");
		if(eterm_strs.length>=1){
			for(int i=1;i<eterm_strs.length;i++){
				Pattern p = Pattern.compile(hbmatch);
				Matcher m = p.matcher(eterm_strs[i]);
				if(m.find()){		//解析到
					/*for(int j=0;j<m.groupCount();j++){
						System.out.println(j+"="+m.group(j));
					}*/
					xml.append("<HB>");
					String xh=StringUtils.trimToEmpty(m.group(1));
					String hbh=StringUtils.trimToEmpty(m.group(2))+StringUtils.trimToEmpty(m.group(3));
					String cfcity=StringUtils.trimToEmpty(m.group(4));
					String ddcity=StringUtils.trimToEmpty(m.group(5));
					String cfsj=StringUtils.trimToEmpty(m.group(6));
					String ddsj=StringUtils.trimToEmpty(m.group(7));
					String jx=StringUtils.trimToEmpty(m.group(8));
					String jtcy=StringUtils.trimToEmpty(m.group(9));//0^C
					String ds=StringUtils.trimToEmpty(m.group(17));
					String e=StringUtils.trimToEmpty(m.group(13));
					//String cw_other=StringUtils.trimToEmpty(m.group(16));//    EFL AX PQ CX DX&lt;T2-->
					String cw = StringUtils.trimToEmpty(m.group(18));
					String other=StringUtils.trimToEmpty(m.group(19));
					if(StringUtils.isNotEmpty(other)){//T3--
					    other = other.replace("&lt;", "").replace(">", "");
						other = other.replace("lt;", "").replace(">", "");
						other = other.replace("<", "").replace(">", "");
						if(StringUtils.isNotEmpty(other)){
							if(4 == other.length()){
								other = other.substring(0,2) + " " + other.substring(2);
							}
						}
					}
					//Pattern p1 = Pattern.compile("(\\w{0,2}(\\#|\\*|\\s|!|C))\\s*((\\w{2}\\s+){0,10})\\s+");
					//Pattern p1 = Pattern.compile("((?:\\s+\\w{2})+)(.*)");
					//Matcher m1 = p1.matcher(" "+cw_other);
					//if(m1.find()){
						//cw=StringUtils.trimToEmpty(m1.group(1));//AS
						//other=StringUtils.trimToEmpty(m1.group(2));//# FL ...
					//}
					/*if(StringUtils.isNotEmpty(cw_other)){
						cw_other = cw_other.replace("*", "");
						if(StringUtils.isNotEmpty(cw_other)){
							if(cw_other.indexOf(" ") > -1){
								ds = cw_other.substring(0,cw_other.indexOf(" "));
							}
							ds = ds == null ? "" : ds;
							cw = cw_other.replace(ds, "").trim();
						}
					}*/
					xml.append("<XH>").append(xh).append("</XH>"); // 序号
					xml.append("<HBH>").append(hbh).append("</HBH>"); // *
					xml.append("<DS>").append(ds).append("</DS>"); // ds#
					xml.append("<CW>").append(cw).append("</CW>"); 
					xml.append("<HC>").append(cfcity).append(ddcity).append(
							"</HC>");
					xml.append("<CFSJ>").append(cfsj).append(
							"</CFSJ>");
					xml.append("<DDSJ>").append(ddsj).append(
							"</DDSJ>");
					xml.append("<JX>").append(jx).append(
							"</JX>");
					xml.append("<TLCY>").append(jtcy).append(
							"</TLCY>");
					xml.append("<E>").append(e).append("</E>");
					xml.append("<OTHER>").append(other).append("</OTHER>");
					if(eterm_strs.length-1>=i+1){	//判断舱位
						p = Pattern.compile(cwmatch);
						m = p.matcher(eterm_strs[i+1]);
						if(m.find()){		//匹配到舱位
							/*for(int j=0;j<m.groupCount();j++){
								System.out.println(j+"========"+m.group(j));
							}*/
							i=i+1;
							xml.append("<FH>").append("&gt;").append("</FH>");
							String gxhb=StringUtils.trimToEmpty(m.group(1));
							String cw2=StringUtils.trimToEmpty(m.group(2));
							xml.append("<GXHBH>").append(gxhb).append("</GXHBH>");
							xml.append("<CW2>").append(StringUtils.trim(cw2)).append("</CW2>");
							
						}else{
							xml.append("<FH>").append("</FH>");
							xml.append("<GXHBH>").append("</GXHBH>");
							xml.append("<CW2>").append("</CW2>");
						}
					}else{
						xml.append("<FH>").append("</FH>");
						xml.append("<GXHBH>").append("</GXHBH>");
						xml.append("<CW2>").append("</CW2>");
					}
					if(eterm_strs.length-1>=i+1){	//判断舱位
						p = Pattern.compile(sub_cw);
						m = p.matcher(eterm_strs[i+1]);
						if(m.find()){		//匹配到舱位
							i=i+1;
							String subcw=StringUtils.trimToEmpty(m.group(1));
							xml.append("<SUBCW>").append(StringUtils.replace(subcw,"**","")).append("</SUBCW>");
						}
					}
					xml.append("</HB>");
					//
				}else{
					if(StringUtils.isNotBlank(eterm_strs[i])){
						if(eterm_strs[i].indexOf(">")<=-1){
							root.append(eterm_strs[i]+"\n");
						}
					}
				}
				
			}
		}
		xml.append("</HBS>");
		String xml_str=xml.toString().replace("##########", root);
		
		
		if("1".equals(System.getProperty("HPFLAG"))){
			System.out.println("黑屏大配置AV解析之后xml:"+xml_str);
		}
		return xml_str;
	}
	
	/**
	 * 解析航班DATA，返回有座舱位，格式 F8,A2,YA,BA,MA
	 */
	public static List<String[]> parseAvData(String avData, String currentHbh) {
		List<String[]> cwList = new ArrayList<String[]>();
		try {
			currentHbh = StringUtils.trimToEmpty(currentHbh).replace("*", "");
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
				if (hbh.equals(currentHbh)) {
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
								String[] cwTmpArr = new String[7];
								cwTmpArr[0] = cwStr;
								cwTmpArr[1] = zwStr;
								cwTmpArr[2] = jx;
								cwTmpArr[3] = cfTime;
								cwTmpArr[4] = ddTime;
								cwTmpArr[5] = hzl;
								cwTmpArr[6] = hbh;
								cwList.add(cwTmpArr);
							}
						}
					}
					break;
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
