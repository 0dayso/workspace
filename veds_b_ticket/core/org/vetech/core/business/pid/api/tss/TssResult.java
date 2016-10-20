package org.vetech.core.business.pid.api.tss;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.vetech.core.modules.utils.XmlUtils;

/**
	<TSSResult><TSS>TSS:TN/8473687965113/S ACCEPTED </TSS><DETR>DETR:TN/847-3687965113 ISSUED BY: WEST AIR CO. LTD.
* ORG/DST: WUH/FOC BSP-D E/R: 不得签转 TOUR CODE: RECEIPT PRINTED PASSENGER: 蔡大九 EXCH: CONJ TKT: O FM:1WUH PN 6301 M
* 16NOV 1010 OK YM 20K SUSPENDED RL:TDMVR /SK2FZ 1E TO: FOC FC: 16NOV09WUH PN FOC550.00CNY550.00END FARE: CNY
* 550.00|FOP:CASH TAX: CNY 50.00CN|OI: TAX: CNYEXEMPTYQ| TOTAL: CNY 600.00|TKTN: 847-3687965113
* 
* </DETR></TSSResult>
*/
@XmlRootElement(name="TSSResult")  
public class TssResult {

	private String tss;
	private String detr;
	
	@XmlElement(name="TSS")
	public String getTss() {
		return tss;
	}
	public void setTss(String tss) {
		this.tss = tss;
	}
	
	@XmlElement(name="DETR")
	public String getDetr() {
		return detr;
	}
	public void setDetr(String detr) {
		this.detr = detr;
	}
	
	public static void main(String[] args) {
		String data="<TSSResult> <TSS>TSS:TN/8473687965113/S ACCEPTED</TSS>  <DETR>DETR:TN/847-3687965113 ISSUED BY: WEST AIR CO. LTD. ORG/DST: WUH/FOC BSP-D E/R: 不得签转 TOUR CODE: RECEIPT PRINTED PASSENGER: 蔡大九 EXCH: CONJ TKT: O FM:1WUH PN 6301 M 16NOV 1010 OK YM 20K SUSPENDED RL:TDMVR /SK2FZ 1E TO: FOC FC: 16NOV09WUH PN FOC550.00CNY550.00END FARE: CNY 550.00|FOP:CASH TAX: CNY 50.00CN|OI: TAX: CNYEXEMPTYQ| TOTAL: CNY 600.00|TKTN: 847-3687965113</DETR> </TSSResult>";
		TssResult a=(TssResult) XmlUtils.fromXml(data, TssResult.class);
		System.out.print(a.getTss());
	}
}
