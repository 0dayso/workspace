package org.vetech.core.business.pid.api.parse;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.vetech.core.business.pid.pidbean.Command;
import org.vetech.core.business.pid.pidbean.ParseParam;
import org.vetech.core.business.pid.pidbean.PidAvhBean;
import org.vetech.core.business.pid.util.ParseCommandUtil;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

/**
 * NFD解析器,将NFD数据解析成xml格式
 * 
 * 
 * @author 章磊 <VENFD><DataItem><NFD>01 CA 260.00 E/CA3T9215 E 00D/00D
 *         04SEP09-15SEP09 NFN:01 C3.00%+ 3.00%</NFD><NFN>***运价组合方式*** 允许单独销售
 *         允许与所有运价组合销售
 * 
 * **其他规定*** 1/签转,OPEN票,RQ票:不允许. 2/改期:不允许. 3/退票:在原出票地办理 (1)自愿退票:不允许. (2)非自愿退票:
 * A.客票全部未使用:按实收票面价退款. B.客票部分使用:按实收票面价的1/2RT票价退款. 4/子舱位变更,升舱:低舱位变高舱位收取舱位之间的差价.
 * 5/所有其他种类折扣:不适用.
 * 
 * **去程回程*** 儿童运价: 260.00
 * 
 * 去程适用航班号范围: 1342-1342 8205-8205
 * 
 * 仅适用于散客销售
 * 
 * 签注: BUDEQIANZHUANGAIQITUIPIAO不得签转改期退票 </NFN></DataItem></VENFD>
 */
public class ParseNFD implements Parse {

	@SuppressWarnings("unchecked")
	public ParseResult parse(ParseParam param, Command command) {
		ParseResult parseResult=new ParseResult();
		PidAvhBean avhBean = (PidAvhBean)command;
		
		Document doc = null;
		try {
			doc = ParseCommandUtil.parseXml(param.getReturnXml());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(doc == null){
			parseResult.setAfterParseStr("");
			return parseResult;
		}
		
		Element items = doc.getRootElement();
		Element officeEle =items.element("Office");
		String office="";
		if(officeEle!=null){
			office=StringUtils.trimToEmpty(officeEle.getText());
			office=office.toUpperCase();
		}
		List list=items.elements("DataItem");
		if(list==null || list.isEmpty() ){
			return null;
		}
		String pp="(\\d\\d)\\s+(\\w{2})\\s{1,5}(\\d+.\\d+){0,1}\\s+(\\d+.\\d+){0,1}\\s+([^\\s]*)\\s+([^\\s]+)\\s+(\\w{3}/\\w{3}){0,1}\\s+([^\\s]+)\\s+NFN:\\d\\d\\s*(AP\\d\\d){0,1}C{0,1}(\\d.\\d+){0,1}%{0,1}\\+{0,1}\\s*(\\d.\\d+){0,1}%{0,1}\\s*(\\w{1,9}){0,1}\\s+([^\\s]*)";
		String sygd="\\*\\*\\*适用规定\\*\\*\\*([^\\*]+)";
		String cjz="\\*\\*\\*乘机者\\*\\*\\*([^\\*]+)";
		String ydgd="\\*\\*\\*预订规定\\*\\*\\*([^\\*]+)";
		String yjzhfs="\\*\\*\\*运价组合方式\\*\\*\\*([^\\*]+)";
		String tdgd="\\*\\*\\*团队规定\\*\\*\\*([^\\*]+)";
		String zfcp="\\*\\*\\*支付/出票\\*\\*\\*([^\\*]+)";
		String bggd="\\*\\*\\*变更规定\\*\\*\\*([^\\*]+)";
		String tpgd="\\*\\*\\*退票规定\\*\\*\\*([^\\*]+)";
		String kcgd="\\*\\*\\*扩充规定\\*\\*\\*([^\\*]+)";
		String qtgd="\\*\\*\\*其他规定\\*\\*\\*([^\\*]+)";
		String qchc="\\*\\*\\*去程回程\\*\\*\\*([^\\*]+)";
		String hc=StringUtils.upperCase(avhBean.getHc());
		StringBuffer nfdxml=new StringBuffer();
		nfdxml.append("<NFD>");
		nfdxml.append("<REQUEST>");
		nfdxml.append("<FROM>").append(StringUtils.substring(avhBean.getHc(),0,3)).append("</FROM>");
		nfdxml.append("<DEST>").append(StringUtils.substring(avhBean.getHc(),3,6)).append("</DEST>");
		nfdxml.append("<DATE>").append(avhBean.getCfdate()).append("</DATE>");
		nfdxml.append("<REDATE>").append(VeDate.getStringDate()).append("</REDATE>");
		nfdxml.append("</REQUEST>");
		for(int i=0;i<list.size();i++){
			Element e=(Element)list.get(i);
			String nfd=e.elementText("NFD");
			nfd=ParseCommandUtil.formatCommand(nfd);
			Pattern p = Pattern.compile(pp);
			Matcher m = p.matcher(nfd);
			nfdxml.append("<LINE id='"+(i+1)+"'>");
			if(m.find()){
				String rt=StringUtils.trimToEmpty(m.group(4));
				String cw=StringUtils.trimToEmpty(m.group(6));
				String cw1="";
				String cw2="";
				if(cw.indexOf("/")>-1){
					cw1=cw.split("/")[0];
					cw2=cw.split("/")[1];
				}else{
					cw1=cw;
					cw2=cw;
				}
				nfdxml.append("<OFFICE>").append(office).append("</OFFICE>");//LN
				nfdxml.append("<LN>").append(StringUtils.trimToEmpty(m.group(1))).append("</LN>");//LN
				nfdxml.append("<CXR>").append(StringUtils.trimToEmpty(m.group(2))).append("</CXR>");//CXR 航空公司
				nfdxml.append("<OW>").append(StringUtils.trimToEmpty(m.group(3))).append("</OW>");//OW 单程票价
				nfdxml.append("<RT>").append(rt).append("</RT>");//RT 多段票价
				nfdxml.append("<FBCTC>").append(StringUtils.trimToEmpty(m.group(5))).append("</FBCTC>");//FBC/TC
				nfdxml.append("<RBD>").append(cw).append("</RBD>");//RBD 舱位
				nfdxml.append("<MINMAX>").append(StringUtils.trimToEmpty(m.group(7))).append("</MINMAX>");//MIN/MAX
				
				String cjrq = StringUtils.trimToEmpty(m.group(8));
				if(cjrq.length() > 15){
					String cjrqReg = "([\\d]{1,2}[a-zA-Z]{3}[\\d][2|4](-[\\d]{1,2}[a-zA-Z]{3}[\\d][2|4])?)";
					Pattern p1 = Pattern.compile(cjrqReg);
					Matcher m1 = p1.matcher(cjrq);
					if(m1.find()){
						cjrq = StringUtils.trimToEmpty(m1.group(0));
					}
					
				}
				nfdxml.append("<TRVDATE>").append(cjrq).append("</TRVDATE>");//TRVDATE
				nfdxml.append("<AP>").append(StringUtils.trimToEmpty(m.group(9))).append("</AP>");//TRVDATE 提前出票天数始
				String c1=StringUtils.trimToEmpty(m.group(10));
				String c2=StringUtils.trimToEmpty(m.group(11));
				if(StringUtils.isNotBlank(c2)){
					nfdxml.append("<C>").append(Arith.div(Arith.add(NumberUtils.toDouble(c1,0),NumberUtils.toDouble(c2,0)),100)).append("</C>");//C
				}else{
					nfdxml.append("<C>").append("").append("</C>");//C
				}
				String m12=m.group(12);
				String m13=m.group(13);
				if(m13.indexOf("-")==0){
					m13=m12+m13;
				}
				String tmp_hc=StringUtils.trimToEmpty(m13).replaceAll("-\\w/","").replaceAll("-","");
				if(StringUtils.isBlank(tmp_hc)){
					tmp_hc=hc;
				}else if(tmp_hc.length()==9){
					if(StringUtils.substring(tmp_hc, 0,3).equals(StringUtils.substring(tmp_hc, 6,9))){
						tmp_hc=StringUtils.substring(tmp_hc, 0,6);
					}
				}
				if(tmp_hc.length()==6 && StringUtils.isBlank(rt)){
					nfdxml.append("<HCLX>").append("1").append("</HCLX>");//单程
				}else if(tmp_hc.length()==6 && StringUtils.isNotBlank(rt) && cw1.equals(cw2)){
					nfdxml.append("<HCLX>").append("2").append("</HCLX>");//往返程
				}else if(tmp_hc.length()==9 && StringUtils.isBlank(rt) && cw1.equals(cw2)){//联程
					nfdxml.append("<HCLX>").append("3").append("</HCLX>");//联程
				}else if(tmp_hc.length()==15 && StringUtils.isNotBlank(rt) && cw1.equals(cw2)){//联成往返
					nfdxml.append("<HCLX>").append("4").append("</HCLX>");//联成往返
				}else{
					nfdxml.append("<HCLX>").append("5").append("</HCLX>");//未知
				}
				nfdxml.append("<HC>").append(tmp_hc).append("</HC>");//TRVDATE
			}
			String nfn=e.elementText("NFN");
			nfn=VeStr.toXmlFormat(ParseCommandUtil.formatCommand(nfn));
			//System.out.println(nfn);
			//***乘机者*** 
			p = Pattern.compile(sygd);
			m = p.matcher(nfn);
			if(m.find()){
				nfdxml.append("<SYGD>").append(StringUtils.trimToEmpty(m.group(1))).append("</SYGD>");
			}
			
//			***乘机者*** 
			p = Pattern.compile(cjz);
			m = p.matcher(nfn);
			if(m.find()){
				nfdxml.append("<CJZ>").append(StringUtils.trimToEmpty(m.group(1))).append("</CJZ>");
			}
//			***预订规定*** 
			p = Pattern.compile(ydgd);
			m = p.matcher(nfn);
			if(m.find()){
				nfdxml.append("<YDGD>").append(StringUtils.trimToEmpty(m.group(1))).append("</YDGD>");
			}
			
//			***运价组合方式*** 
			p = Pattern.compile(yjzhfs);
			m = p.matcher(nfn);
			if(m.find()){
				nfdxml.append("<YJZHFS>").append(StringUtils.trimToEmpty(m.group(1))).append("</YJZHFS>");
			}
			
//			***团队规定*** 
			p = Pattern.compile(tdgd);
			m = p.matcher(nfn);
			if(m.find()){
				nfdxml.append("<TDGD>").append(StringUtils.trimToEmpty(m.group(1))).append("</TDGD>");
			}
			
			//***支付/出票*** 
			p = Pattern.compile(zfcp);
			m = p.matcher(nfn);
			if(m.find()){
				nfdxml.append("<ZFCP>").append(StringUtils.trimToEmpty(m.group(1))).append("</ZFCP>");
			}
//			***变更规定*** 
			p = Pattern.compile(bggd);
			m = p.matcher(nfn);
			if(m.find()){
				nfdxml.append("<BGGD>").append(StringUtils.trimToEmpty(m.group(1))).append("</BGGD>");
			}
			
//			***退票规定***  
			p = Pattern.compile(tpgd);
			m = p.matcher(nfn);
			if(m.find()){
				nfdxml.append("<TPGD>").append(StringUtils.trimToEmpty(m.group(1))).append("</TPGD>");
			}
			
//			***扩充规定***  
			p = Pattern.compile(kcgd);
			m = p.matcher(nfn);
			if(m.find()){
				nfdxml.append("<KCGD>").append(StringUtils.trimToEmpty(m.group(1))).append("</KCGD>");
			}
//			***其他规定***  
			p = Pattern.compile(qtgd);
			m = p.matcher(nfn);
			if(m.find()){
				nfdxml.append("<QTGD>").append(StringUtils.trimToEmpty(m.group(1))).append("</QTGD>");
			}
//			***去程回程***  
			p = Pattern.compile(qchc);
			m = p.matcher(nfn);
			if(m.find()){
				String qchctext=StringUtils.trimToEmpty(m.group(1));
				if(StringUtils.isNotBlank(qchctext)){
					String hctrvdate="回程航班季节:\\s*([^\\r]+)";
					p = Pattern.compile(hctrvdate);
					m = p.matcher(qchctext);
					if(m.find()){
						nfdxml.append("<HCTRVDATE>").append(StringUtils.trimToEmpty(m.group(1))).append("</HCTRVDATE>");
					}
					String cprqs="最早出票日期:\\s*([^\u4e00-\u9fa5]+)";
					p = Pattern.compile(cprqs);
					m = p.matcher(qchctext);
					if(m.find()){
						nfdxml.append("<CPRQS>").append(StringUtils.trimToEmpty(m.group(1))).append("</CPRQS>");
					}
					String cprqz="最晚出票日期:\\s*([^\u4e00-\u9fa5]+)";
					p = Pattern.compile(cprqz);
					m = p.matcher(qchctext);
					if(m.find()){
						nfdxml.append("<CPRQZ>").append(StringUtils.trimToEmpty(m.group(1))).append("</CPRQZ>");
					}
					String tqcptsz="最早\\s*([^\u4e00-\u9fa5]+)";
					p = Pattern.compile(tqcptsz);
					m = p.matcher(qchctext);
					if(m.find()){
						nfdxml.append("<TQCPTSZ>").append(StringUtils.trimToEmpty(m.group(1))).append("</TQCPTSZ>");
					}
					String qhbh="去程适用航班号范围:\\s*([^\u4e00-\u9fa5]+)";
					p = Pattern.compile(qhbh);
					m = p.matcher(qchctext);
					if(m.find()){
						nfdxml.append("<QHBH>").append(StringUtils.trimToEmpty(m.group(1))).append("</QHBH>");
					}
					String hhbh="回程适用航班号范围:\\s*([^\u4e00-\u9fa5]+)";
					p = Pattern.compile(hhbh);
					m = p.matcher(qchctext);
					if(m.find()){
						nfdxml.append("<HHBH>").append(StringUtils.trimToEmpty(m.group(1))).append("</HHBH>");
					}
					String nqhbh="去程除外航班号范围:\\s*([^\u4e00-\u9fa5]+)";
					p = Pattern.compile(nqhbh);
					m = p.matcher(qchctext);
					if(m.find()){
						nfdxml.append("<NQHBH>").append(StringUtils.trimToEmpty(m.group(1))).append("</NQHBH>");
					}
					String nhhbh="回程除外航班号范围:\\s*([^\u4e00-\u9fa5]+)";
					p = Pattern.compile(nhhbh);
					m = p.matcher(qchctext);
					if(m.find()){
						nfdxml.append("<NHHBH>").append(StringUtils.trimToEmpty(m.group(1))).append("</NHHBH>");
					}
					String week="去程适用星期限制:\\s*([^\\r]+)";
					p = Pattern.compile(week);
					m = p.matcher(qchctext);
					if(m.find()){
						nfdxml.append("<WEEK>").append(StringUtils.trimToEmpty(m.group(1))).append("</WEEK>");
					}
				}
				nfdxml.append("<QCHC>").append(qchctext).append("</QCHC>");
			}
			nfdxml.append("</LINE>");
			
		}
		nfdxml.append("</NFD>");
		//组合联程和联程往返程
		Document doctmp;
		String xml="";
		try {
			doctmp = DocumentHelper.parseText(nfdxml.toString());
			Element e = doctmp.getRootElement();
			
			List es=e.elements("LINE");
			for(int i=0;i<es.size();i++){
				Element tmpe=(Element)es.get(i);
				String hclx = tmpe.elementText("HCLX");
				if("3".equals(hclx)){//判断是否是联程
					for(int j=0;j<es.size();j++){
						Element tmpe1=(Element)es.get(j);
						String hclx1=tmpe1.elementText("HCLX");
						if("4".equals(hclx1)){
							String lchc=tmpe.elementText("HC");
							String lchc1=StringUtils.substring(lchc, 6,9)+StringUtils.substring(lchc, 3,6)+StringUtils.substring(lchc, 0,3);
							String lcwfchc=tmpe1.elementText("HC");
							String lccw=tmpe.elementText("RBD");
							String lcwfccw=tmpe1.elementText("RBD");
							if(lchc.equals(StringUtils.substring(lcwfchc, 0,9)) && lchc1.equals(StringUtils.substring(lcwfchc, 6,15))
									&& lccw.equals(lcwfccw)){
								tmpe.element("RT").setText(tmpe1.elementText("RT"));//往返程票价
							}
						}
					}
				}
			}
			xml = ParseCommandUtil.outXml(doctmp);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(nfdxml.toString());
			return null;
		}
		
		parseResult.setAfterParseStr(xml);
		return parseResult;
	}
	public static void main(String []args) throws Exception{
		/*FileReader fr = new FileReader("c:/t.txt");
		BufferedReader br = new BufferedReader(fr);
		StringBuffer buffer=new StringBuffer();
		String s="";
		while ((s = br.readLine()) != null) {
			buffer.append(s+"\r");
		}
		fr.close();
		ParseNFD parseNFD=new ParseNFD();
		AvhBean avhBean=new AvhBean();
		avhBean.setHc("DLCHGH");
		avhBean.setDate("2009-09-17");
		avhBean.setOffice("SIA666");
		SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(buffer.toString());
		Document doc;
		try {
			doc = builder.build(in);
		}catch (Exception e) {
			throw new EtermXmlException("共享传的不是xml"+e.getMessage());
		}
		EtermResult etermResult=new EtermResult(doc);
		System.out.println(parseNFD.parse(etermResult, avhBean, null).getResultXml());*/
		
		
		String reg = "([\\d]{1,2}[a-zA-Z]{3}[\\d][2|4](-[\\d]{1,2}[a-zA-Z]{3}[\\d][2|4])?)";
		String string = "00D/00D 01JUL12-31AUG12";
		//String result = string.replaceAll(reg, "");
		//System.out.println(result);
		
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(string);
		if(m.find()){
			System.out.println(m.group(0));
		}
	}
}
