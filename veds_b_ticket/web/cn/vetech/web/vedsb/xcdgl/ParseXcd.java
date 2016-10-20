package cn.vetech.web.vedsb.xcdgl;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.vetech.core.modules.utils.FileOperate;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.jp.entity.xcd.JpXcdTicket;
import cn.vetech.vedsb.jp.entity.xcd.JpXcdTickethd;

/**
 * 解析行程单
 * @author  shenjx
 * @version  [版本号, Oct 22, 2011]
 */
public class ParseXcd {
	

	private static String encode="GB2312";
	private static String pathTemp="D:/xcd/";
	
	public static void main(String[] args) {
		
	}
	
	public static JpXcdTicket parseXcd(String xcdinfo) {
		if(xcdinfo==null){
			return null;
		}
		JpXcdTicket xcdInfo=new JpXcdTicket();
		xcdInfo.setJkfhsj(xcdinfo.replaceAll("\n{1,}", "[此处为空行]").replaceAll("\n", "<br>"));
		Document doc;
		try {
			doc = DocumentHelper.parseText(xcdinfo);
			Element items=doc.getRootElement();
			Element tkts=items.element("TKTS");
			parseTkts(tkts,xcdInfo);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		if(xcdInfo!=null&&xcdInfo.getHdlist()!=null){
			int hdsize=xcdInfo.getHdlist().size();
			if(hdsize<=4){
				JpXcdTickethd hdtmp=xcdInfo.getHdlist().get(hdsize-1);
				if(hdtmp!=null&&!"VOID".equals(hdtmp.getJcmc())){
					JpXcdTickethd hd=new JpXcdTickethd();
					hd.setJcmc("VOID");
					xcdInfo.getHdlist().add(hd);
				}
			}
			int cnt=0;
			for(JpXcdTickethd hd:xcdInfo.getHdlist()){
				cnt++;
				if(cnt<5&&StringUtils.isNotBlank(hd.getJcmc())&&!"VOID".equals(hd.getJcmc())&&StringUtils.isBlank(hd.getHbh())){
					hd.setHbh("VOID");
				}
				setHdNullToEmpty(hd);
			}
		}
		return xcdInfo;
	}
	
	
	public static void parseTkts(Element tkts,JpXcdTicket xcdInfo) {
		if(tkts==null){
			return;
		}
		String tkno="";
		String xcdh="";
		String command = tkts.elementText("Command");//行程单号，票号
		command=StringUtils.trimToEmpty(command);
		String reg="(\\d{13})\\,";
		Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher mp = p.matcher(command);
		if (mp.find()) {
			tkno = mp.group(1);
			xcdInfo.setTkno(tkno);
		}
		reg="T\\/P\\/(\\d{10})";
		p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		mp = p.matcher(command);
		if (mp.find()) {
			xcdh = mp.group(1);
			xcdInfo.setXcdh(xcdh);
		}
		
		String tkt = tkts.elementText("TKT");
		tkt=tkt.replace("国际(I)", "");
		tkt=tkt.trim();//去掉首位空行空格
		tkt=tkt.replaceAll("\n{1,}", "\n");//去掉空行
		String [] line=tkt.split("\n");
		
		List<JpXcdTickethd> hdlist=new ArrayList<JpXcdTickethd>();
		xcdInfo.setHdlist(hdlist);
		boolean tkinfobool=false;//票号信息行
		boolean officebool=false;//提示信息或者 office和填开日期行
		boolean dwdhbool=false;//销售单位代号 和填开单位行
		boolean cznopnrno=false;//南航无PNR处理
		boolean cjrbool=false;//乘机人是否已解析成功
		boolean gobool=false;//是否发现gp
		for (int i = 0; i < line.length; i++) {
			String lineString=line[i];
			if(StringUtils.isNotBlank(lineString)){
				lineString=lineString.trim();
				if(!gobool){
					//判断是否GP
					reg="^GP[0-9]{12}(\\([a-zA-Z]\\))?$";
					p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
					mp = p.matcher(lineString);
					if (mp.find()) {
						xcdInfo.setGp_no(mp.group(0));
						xcdInfo.setCplx("GP");
						lineString=line[i+1];
						gobool=true;
					}
				}
				if(i==0){//乘机人、证件号、退改签  儿童姓名：SHEN/XINJIAN CHD(25DEC05)   婴儿姓名：SHEN/XIMINF
					//fang/wei            330302196711044011           免费更改一次
					String [] arr=lineString.split("\\s{1,}");
					if(arr.length>=4){
						/** 需要区分这两种情况
						 * 孙成芳          320923195111250047           不得签转/BIANGE        YNZ/C
 							孙成芳 (CHD)    320923195111250047           不得签转/BIANGE  
						 */
						if(hasDigit(arr[1])){
							xcdInfo.setLkxm(arr[0]);
							xcdInfo.setZjhm(arr[1]);
							xcdInfo.setQz(arr[2]);
						}else{
							xcdInfo.setLkxm(arr[0]+arr[1]);
							xcdInfo.setZjhm(arr[2]);
							xcdInfo.setQz(arr[3]);
						}
					}else if(arr.length>=3){
						xcdInfo.setLkxm(arr[0]);
						xcdInfo.setZjhm(arr[1]);
						xcdInfo.setQz(arr[2]);
					}else if(arr.length==2){
						//BONNEVILLE/SYLVAHD309393 护照和旅客姓名连在一起的情况
						reg="HD[0-9]{6}";
						p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
						mp = p.matcher(arr[0]);
						if (mp.find()) {
							String zjhm = mp.group(0);
							String lkxm = arr[0].replace(zjhm, "");
							xcdInfo.setLkxm(lkxm);
							xcdInfo.setZjhm(zjhm);
							xcdInfo.setQz(arr[1]);
						}else{
							xcdInfo.setLkxm(arr[0]);
							xcdInfo.setZjhm(arr[1]);
							xcdInfo.setQz("");
						}
					}else if(arr.length==1){
						xcdInfo.setLkxm(arr[0]);
						xcdInfo.setZjhm("");
						xcdInfo.setQz("");
					}
					cjrbool=true;
					if(gobool){
						i++;
					}
				}else if(cjrbool&&!cznopnrno){//PNR CZ可能并无PNR，需要作为票号重新解析此行
					reg="^[A-Z0-9]{5,8}$";
					p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
					mp = p.matcher(lineString);
					if (mp.find()) {
						xcdInfo.setPnrNo(lineString);
					}else{
						cznopnrno=true;
						i--;
					}
					cjrbool=false;
				}else if(dwdhbool){//销售单位代号 和填开单位行  
					/**
					 * eg    08064335            广西华通网络服务有限公司南宁分公
                     *							 司
                    **/
					String [] arr=lineString.split("\\s{2,}");
					if(arr.length>=2){//eg: 08318505
						xcdInfo.setDwdh(StringUtils.trim(arr[0]));
						xcdInfo.setTkdw(StringUtils.trim(arr[1]));
					}else{
						xcdInfo.setDwdh("");
						xcdInfo.setTkdw("");
					}
					if(i==line.length-2){//最后一行
						xcdInfo.setTkdw(xcdInfo.getTkdw()+StringUtils.trim(line[line.length-1]));
					}
					dwdhbool=false;
					break;
				}else if(officebool){//提示信息  或者  office 填开日期  
					String [] arr=lineString.split("\\s{2,}");
					if(arr.length==1){//eg: 白云机场起飞前45分停办值机
						reg="\\w{1,}";
						p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
						mp = p.matcher(lineString);
						if (mp.find()) {
							xcdInfo.setTsxx(StringUtils.trim(arr[0]));
						}else{
							xcdInfo.setTsxx("");
						}
						officebool=true;
					}else if(arr.length==2){//eg: SHA666      2011-12-06
						String of=StringUtils.trim(arr[0]);
						reg="[A-Z]{3}[0-9]{3}|201[1-9]{1}-[0-9]{2}-[0-9]{2}";
						p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
						mp = p.matcher(lineString);
						if (mp.find()) {
							xcdInfo.setOffice(of);
							xcdInfo.setTkrq(StringUtils.trim(arr[1]));
							officebool=false;
							dwdhbool=true;
						}else{//提示信息  eg:CONJ00/01                           深圳始发请提前四十分钟办   [提示信息比较特殊]
							//并且不是8位数字结算码
							reg="[0-9]{8}";
							p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
							mp = p.matcher(arr[0]);
							if (!mp.find()) {
								xcdInfo.setTsxx(StringUtils.trim(arr[0])+" "+StringUtils.trim(arr[1]));
								officebool=true;
							}
						}
					}
				}else if(tkinfobool){//票号信息 eg 7845067277715       1005      784-5067277714/15 或7819438139140       2981      781-9438139140-42                  XXX                 XXX
					String [] arr=lineString.split("\\s{2,}");
					if(arr.length>=2){
						if(StringUtils.isBlank(xcdInfo.getTkno())){
							xcdInfo.setTkno(arr[0]);
						}
						String yzm=StringUtils.trim(arr[1]);
						xcdInfo.setYzm(yzm);
					}
					if(arr.length>=3){
						if(arr.length==3){
							xcdInfo.setPjBx(arr[2]);
						}else{
							if(StringUtils.isBlank(xcdInfo.getTkno())){
								xcdInfo.setTkno(arr[0]);
							}
							xcdInfo.setLxkp(arr[2].replaceFirst("-", ""));
							xcdInfo.setPjBx(arr[3]);
						}
					}
					tkinfobool=false;
					officebool=true;
				}else if(i>=2||cznopnrno){
					reg="CNY(\\s{1,5}\\d{1,5}(\\.00)?)[A-Z]{2}\\s{1,10}";//eg: 票价   CNY  950.00CN  
					p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
					mp = p.matcher(lineString);
					if (mp.find()) {//价格信息  其他税费暂不考虑
						String pj_zdj = mp.group(1);
						xcdInfo.setPjZdj(StringUtils.trimToEmpty(pj_zdj));
						if(lineString.indexOf("PD")>0){
							xcdInfo.setIfgq("1");
						}
						//第一个是否是机场名称 eg 武汉       CNY 9280.00CN   190.00OO    39.00XT     1230CNY10739.00
						// CNY  890.00CN    50.00YQ   EXEMPT           CNY  940.00
						if(hdlist.size()==4){//第五航段后面是价格
							reg="[\u4e00-\u9fa5]{2,6}";
							p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
							mp = p.matcher(lineString);
							if(mp.find()) {//
								JpXcdTickethd xhi=new JpXcdTickethd();
								xhi.setJcmc(mp.group(0));
								hdlist.add(xhi);
							}
						}
						reg="CN\\s{1,5}(\\d{1,5}(\\.00){0,1})";
						p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
						mp = p.matcher(lineString);
						if (mp.find()) {//价格信息  其他税费暂不考虑
							String pj_jsf = mp.group(1);
							xcdInfo.setPjJsf(pj_jsf);
						}else{
							xcdInfo.setPjJsf("");
						}
						
						reg="YQ\\s{1,5}(\\d{1,5}(\\.00){0,1})";
						p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
						mp = p.matcher(lineString);
						if (mp.find()) {//价格信息  其他税费暂不考虑
							String pj_tax = mp.group(1);
							xcdInfo.setPjTax(pj_tax);
						}else{
							xcdInfo.setPjTax("");
						}
						
						
						reg="\\d{1,5}\\.00{0,1}.*CNY\\s{1,5}(\\d{1,5}(\\.00){0,1})";
						p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
						mp = p.matcher(lineString);
						if (mp.find()) {//价格信息  其他税费暂不考虑
							String pj_hj = mp.group(1);
							xcdInfo.setPjHj(pj_hj);
						}else{
							xcdInfo.setPjHj("");
						}
						
						
						xcdInfo.setPjQt("");
						
						tkinfobool=true;
					}else{//航段信息
						//B 深圳         深航ZH9877  Y 2012-01-06 13:55  Y                       20K
						reg="^[A-Z]{1}\\s?[\u4e00-\u9fa5]+";
						p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
						mp = p.matcher(lineString);
						if (mp.find()) {
							String tmpVal = mp.group(0);
							lineString=lineString.replace(tmpVal, "");
							tmpVal=tmpVal.replace(" ", "");
							lineString=tmpVal+lineString;
						}
						
						
						reg="([A-Z]{3})\\s+([A-Z]{3})\\s+[\u4e00-\u9fa5]+";
						p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
						mp = p.matcher(lineString);
						if(mp.find()){
							if(mp.groupCount()>=2){
								String tmpVal1 = mp.group(1);
								String tmpVal2 = mp.group(2);
								if(tmpVal1.equals(tmpVal2)){
									lineString=lineString.replaceAll("\\s+"+tmpVal1, "");
								}
							}
						}
						//太原       B 深航ZH9886  L 2012-02-08 11:10  L                       20K
						reg="[\u4e00-\u9fa5]+\\s+([A-Z]{1}\\s?[\u4e00-\u9fa5]+)";
						p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
						mp = p.matcher(lineString);
						if (mp.find()) {
							String tmpVal = mp.group(1);
							lineString=lineString.replace(tmpVal, tmpVal.replaceAll("\\s*", ""));
						}
						//GMP     GMP  KE  KE2815  G 2015-10-27 16:10  GLGVC      27OCT  27OCT 1PC
						String arr_tmp[] = lineString.split("\\s{1,}");
						if(arr_tmp.length>=2&&arr_tmp[0].equals(arr_tmp[1])&&"GMP".equals(arr_tmp[0])){
							lineString = lineString.substring(4, lineString.length()-1).trim();
						}
						
						JpXcdTickethd xhi=new JpXcdTickethd();
						String [] arr=lineString.split("\\s{1,}");
						String a0=arr[0];//eg T1温州
						if(!"VOID".equals(a0)){
							reg="([A-Z0-9]{1,2})[\u4e00-\u9fa5]+";
							p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
							mp = p.matcher(a0);
							if (mp.find()) {
								String cfhzl = mp.group(1);
								xhi.setCfhzl(cfhzl);
							}
							reg="([\u4e00-\u9fa5]+)|([A-Z]{3})";
							p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
							mp = p.matcher(a0);
							if (mp.find()) {
								String jcmc = mp.group(0);
								xhi.setJcmc(jcmc);
							}
						}else{
							xhi.setJcmc("VOID");
						}
						if(arr.length>=2){
							String a1=arr[1];//eg T2吉祥HO1102
							//到达航站楼
							reg="([A-Z0-9]{1,2})[\u4e00-\u9fa5]+";
							p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
							mp = p.matcher(a1);
							if (mp.find()) {
								String ddhzl = mp.group(1);
								xhi.setDdhzl(ddhzl);
							}
							//航空公司中文简称
							reg="[\u4e00-\u9fa5]+";
							p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
							mp = p.matcher(a1);
							if (mp.find()) {
								String hkgsjc = mp.group(0);
								xhi.setHkgsjc(hkgsjc);
							}
							//航司代码  2015-11-17 add by ffh
							reg="[A-Z]{2}";
							p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
							mp = p.matcher(a1);
							if (mp.find()) {
								String hkgsrzm = mp.group(0);
								xhi.setHkgsrzm(hkgsrzm);
							}
							
							//航班号
							reg="[\u4e00-\u9fa5]+([A-Z0-9]{5,7})";
							p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
							mp = p.matcher(a1);
							if (mp.find()) {
								String hbh = mp.group(1);
								xhi.setHbh(hbh);
							}
							if(arr.length==2&&lineString.indexOf("VOID")>0){//eg: 温州             VOID
								xhi.setHbh("VOID");
							}
						}
						
						if(arr.length>=3){
							String a2=arr[2];//eg K
							//航班号
							reg="([A-Z0-9]{5,7})";
							p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
							mp = p.matcher(a2);
							if (mp.find()) {
								String hbh = mp.group(1);
								xhi.setHbh(hbh);
							}else{
								//座位等级
								xhi.setZwdj(a2);
							}
						}
						boolean ifzwdj = false;
						if(arr.length>=4){
							//出发日期
							String a3=arr[3];//eg 2011-12-06
							if(a3.length()<=2){
								xhi.setZwdj(a3);
								ifzwdj = true;
							}else if(a3.length()==10){
								xhi.setCfrq(a3);
							}
							
						}
						
						if(arr.length>=5){
							//出发时间
							String a4=arr[4];//eg 18:30
							if(ifzwdj){
								xhi.setCfrq(a4);
							}else if(a4.contains(":")){
								xhi.setCfsj(a4);
							}
						}
						
						if(arr.length>=6){
							//客票类别/票价级别
							String a5=arr[5];//eg RT/Y  或 MGV8 或Q
							if(ifzwdj){
								xhi.setCfsj(a5);
							}else{
								//免费行李
								reg="[0-9]{1,2}K";
								p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
								mp = p.matcher(a5);
								if (mp.find()) {
									String mfxl = mp.group(0);
									xhi.setMfxl(mfxl);
								}else{
									xhi.setKpjb(a5);
								}
							}
							
						}
						
						if(arr.length>=7){
							String a6=lineString.replace(arr[0]+" ", "").replace(arr[1]+" ", "").replace(arr[2]+" ", "").replace(arr[3]+" ", "").replace(arr[4]+" ", "").replace(arr[5]+" ", "");//生效日期，截止日期,免费行李
							//票价类别
							if(ifzwdj){
								reg="[A-Z]{1,5}[0-9]{0,2}";
								p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
								mp = p.matcher(a6);
								if (mp.find()) {
									String kpbj = mp.group(0);
									xhi.setKpjb(kpbj);
								}
							}
							
							//免费行李
							reg="[0-9]{1,2}K";
							p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
							mp = p.matcher(a6);
							if (mp.find()) {
								String mfxl = mp.group(0);
								xhi.setMfxl(mfxl);
							}
							
							//客票生效日期
							reg="[0-9]{2}[A-Z]{3}\\s{2}";
							p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
							mp = p.matcher(a6);
							if (mp.find()) {
								String yxrq = mp.group(0);
								xhi.setYxrq(yxrq);
							}
							//客票截止日期
							reg="[0-9]{2}[A-Z]{3}\\s{1}";
							p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
							mp = p.matcher(a6);
							if (mp.find()) {
								String jzrq = mp.group(0);
								xhi.setJzrq(jzrq);
							}
						}
						
						hdlist.add(xhi);
					}
				}
			}
		}
		//logXcdException(xcdInfo.toString()+"\r\n\r\n\r\n\r\n"+tkts.getChildText("TKT"),tkno);
	}
	/**
	 * 将航段中null值设置为空字符窜
	 */
	private static void setHdNullToEmpty(JpXcdTickethd hd){
		if(hd!=null){
			if(hd.getCfhzl()==null){
				hd.setCfhzl("");
			}
			if(hd.getJcmc()==null){
				hd.setJcmc("");
			}
			if(hd.getDdhzl()==null){
				hd.setDdhzl("");
			}
			if(hd.getHkgsjc()==null){
				hd.setHkgsjc("");
			}
			if(hd.getHbh()==null){
				hd.setHbh("");
			}
			if(hd.getHkgsrzm()==null){
				hd.setHkgsrzm("");
			}
			if(hd.getZwdj()==null){
				hd.setZwdj("");
			}
			if(hd.getCfrq()==null){
				hd.setCfrq("");
			}
			if(hd.getCfsj()==null){
				hd.setCfsj("");
			}
			if(hd.getKpjb()==null){
				hd.setKpjb("");
			}
			if(hd.getYxrq()==null){
				hd.setYxrq("");
			}
			if(hd.getJzrq()==null){
				hd.setJzrq("");
			}
			if(hd.getMfxl()==null){
				hd.setMfxl("");
			}
		}
		
	}
	/**
	 * 判断给定字符串中是否含有数字
	 * @param str
	 * @return
	 */
	private static boolean hasDigit(String str){
		boolean flag = false;
		for(int i=0;i<str.length();i++){
			if(Character.isDigit(str.charAt(i))){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public static void write(String info,String tkno,String xcdno) {
		System.out.println("行程单信息："+info);
		try {
			if (true) {
				String ml = System.getProperty("catalina.home");
				if (StringUtils.isBlank(ml)) {
					ml=pathTemp;
				}else{
					ml = ml + "/logs/xcd";	
				}
				File f = new File(ml);
				if (!f.exists()) {
					f.mkdir();
				}
				FileOperate.writeTxt(ml, tkno+"_"+xcdno+".txt", info, encode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//记录行程单创建打印中异常信息
	public static void logXcdException(String info,String tkno) {
		try {
			if (true) {
				String ml = System.getProperty("catalina.home");
				if (StringUtils.isBlank(ml)) {
					ml=pathTemp;
				}else{
					ml = ml + "/logs/xcd/";	
				}	
				File f = new File(ml);
				if (!f.exists()) {
					f.mkdir();
				}
				FileWriter fileWriter  = new FileWriter(ml + tkno+".log", true);
				fileWriter.write(VeDate.getStringDate()+":"+info + "\r\n");
				fileWriter.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
