package org.vetech.core.business.pid.api.qte3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.utils.Arith;
/**
 * 国际票白屏接口QTE数据解析封装javaBean
 * 
 * @author wangshengliang
 */
public class Qte3Result {
	
	
	/**
	 * 解析票面价
	 */
	private static Pattern patternXsfsqfare=Pattern.compile("FARE\\s+(((?!CNY).)+)+CNY\\s+(\\d{1,})");
	
	/**
	 * 解析税费项
	 */
	private static Pattern patternXsfsqTax=Pattern.compile("TAX[\\s]*([CNY]+[\\s]*([\\d]{1,})[\\w]{2}[\\s]*)+");
    /**
     * 解析税费
     */
	private static Pattern patternXsfsqTaxCny=Pattern.compile("CNY\\s+(\\d{1,})");
	
	/**
	 * 解析总价(包含税费和票价)
	 */
	private static Pattern patternXsfsqTotalCny=Pattern.compile("TOTAL\\s+CNY\\s+(\\d{1,})");
	
	/**
	 * 解析C值
	 */
	private static Pattern patternCOMMISSION=Pattern.compile("COMMISSION\\s+(\\d+\\.\\d+)");
	
	private String initData;// 原始数据

	private String pj;
	private String tax;
	private String total;
	
	private Double commission;//代理费率

	public String getPj() {
		return pj;
	}

	public void setPj(String pj) {
		this.pj = pj;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getInitData() {
		return initData;
	}

	public void setInitData(String initData) {
		this.initData = initData;
	}
	
	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	/**
	 * QTE接口返回的数据进行解析封装
	 * @throws EtermException 
	 */
	public static Qte3Result parseQte(String result) throws EtermException{
		Qte3Result qteResult = new Qte3Result();
		qteResult.setInitData(result);
		
		//舱位不能销售
		if(result.indexOf("UNABLE TO SELL.PLEASE CHECK THE AVAILABILITY WITH \"AV\" AGAIN") > -1){
			return qteResult;
		}
		
		Matcher matcher=patternXsfsqfare.matcher(result);
		if (matcher.find()) {
			qteResult.setPj(matcher.group(3));
		}
		
		matcher=patternXsfsqTax.matcher(result);
		while (matcher.find()) {
			String taxStr = matcher.group(0);
			double taxTotal=0;
			boolean matched = false;
			Matcher matcher_taxcny=patternXsfsqTaxCny.matcher(taxStr);
			while(matcher_taxcny.find()){
				matched = true;
				String tax = matcher_taxcny.group(1);
				double d = NumberUtils.toDouble(tax);
				if(d==0){
					throw new EtermException("解析"+taxStr+"错误，得到了0数据");
				}
				taxTotal = Arith.add(taxTotal, d);
			}
			qteResult.setTax(taxTotal+"");
			if(matched){
				break;
			}
		}
		
		matcher=patternXsfsqTotalCny.matcher(result);
		if (matcher.find()) {
			qteResult.setTotal(matcher.group(1));
		}
		
		matcher=patternCOMMISSION.matcher(result);
		if (matcher.find()) {
			 qteResult.setCommission(NumberUtils.toDouble(matcher.group(1))/100.00);
		}else{
			qteResult.setCommission(0.00);
		}
		return qteResult;
	}
	
	public static void main(String[] args) {
		String data="<VEQTE><Office>URC177</Office><QTE><Command>QTE /AA</Command><Content>FSI/AA  S AA   186Q12DEC PEK1010 0910ORD0X    788    S AA  2220Q12DEC ORD1200 1443LAX0S    738    01 QLX0C7D1             3393 CNY                    INCL TAX *SYSTEM DEFAULT-CHECK OPERATING CARRIER  *US FLT SGMNT TAX MAY APPLY. SEE FXT/US/ZP   *仅限电子票  *ATTN PRICED ON 08OCT16*1847  BJS XCHI QLX0C7D1                          NVB12DEC16 NVA12DEC16 2PC  LAX QLX0C7D1                          NVB12DEC16 NVA12DEC16 2PC FARE  CNY    2010    TAX   CNY      90CN CNY      38AY CNY    1255XT  TOTAL CNY    3393  COMMISSION 3.00   12DEC16BJS AA X/E/CHI AA LAX M301.26NUC301.26END ROE6.671940 XT CNY 119US CNY 27XA CNY 47XY CNY 37YC CNY 1025YR   ENDOS *NONREF/FEE ON CHANGE  *AUTO BAGGAGE INFORMATION AVAILABLE - SEE FSB    TKT/TL11OCT16    RFSONLN/1E /EFEP_38/FCC=T/  </Content></QTE></VEQTE>";
		try {
			parseQte(data);
		} catch (EtermException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
