package org.vetech.core.business.pid.api.trfd;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.utils.XmlUtils;


public class Trfd {
	
	public static final String REFUND = "REFUND # ALREADY EXIST";

	public static final String PNR_NOT_XE = "5: 请先删除PNR,重新退票 ";

	public static final String NO_TICKET_DEVICE = "CAN NOT FOUND TICKET DEVICE";
	
	public Object trfd(TrfdParam param) throws EtermException {
		valid(param);
		String bstrInputXML = param.toXml();
		System.out.println("Input:"+bstrInputXML+"\r\n");
		WebEtermService etermService = new WebEtermService(param.getUrl());
		//String data = "<TRFD><OPERATION>AUTO</OPERATION><PREVIEW>1</PREVIEW><CMD>TRFD:D/731-9135132849</CMD><CONTENT>            AIRLINE/BSP   TICKET   REFUND   INFORMATION   FORM  REFUND NUMBER:0              REFUND TYPE:DOMESTIC                DEVICE-ID:  DATE:04AUG16                 AGENT:44867      IATA:08322705      OFFICE:SHA384  WAVER:NONE                   WAVER TYPE:      PASSENGER TYPE:ADT AIRLINE CODE:731             TICKET NO.:9135132849-35132849  CONJUNCTION NO.:1            COUPON NO.:1:1000    2:0000    3:0000    4:0000 PASSENGER NAME:LUOSHAOYUAN   GROSS  REFUND:760.00         PAYMENT  FORM:CASH      CURRENCY  CODE:CNY  SN CD AMOUNT(SN-SEQUENCY NUMBER : CD-TAX CODE)       ET-(Y/N):Y  TAX: 1:CN50.00     2:YQ0.00  COMMITMENT:0.00              OTHER DEDUCTION:38.00   NET REFUND:757.00            AIRLINE REFUND:772.00   COMMISSION AMOUNT:15.00  CREDIT CARD:NONE</CONTENT><RESULT><Header>AIRLINE/BSP   TICKET   REFUND   INFORMATION   FORM</Header><DateTime>04AUG16</DateTime><Agent>44867</Agent><IATA>08322705</IATA><OFFICE>SHA384</OFFICE><Waver>NONE</Waver><WaverType/><PassengerType>ADT</PassengerType><AirlineCode>731</AirlineCode><TKTNumber>9135132849,35132849</TKTNumber><ConjunctionNo>1</ConjunctionNo><CouponNo>1000,0000,0000,0000</CouponNo><PassengerName>LUOSHAOYUAN</PassengerName><GrossRefund>760.00</GrossRefund><FormOfPayment>CASH</FormOfPayment><CurrencyCode>CNY</CurrencyCode><SN_CD_AMOUNT>CD-TAX CODE</SN_CD_AMOUNT><ET>Y</ET><Tax>CN,50.00|YQ,0.00</Tax><Commission>0.00</Commission><Deduction>38.00</Deduction><NetRefund>757.00</NetRefund><AirlineRefund>772.00</AirlineRefund><CommissionAmount>15.00</CommissionAmount><CreditCard>NONE</CreditCard></RESULT></TRFD>";//etermService.trfd(bstrInputXML);
		String data=etermService.trfd(bstrInputXML);
		System.out.println("Output:"+data+"\r\n");
		if (StringUtils.isBlank(data)) {
			throw new EtermException("调用PID接口返回空");
		}
		if ("1".equals(param.getPreview())) {
			TrfdPreviewResult result= (TrfdPreviewResult) XmlUtils.fromXml(data, TrfdPreviewResult.class);
			if (result != null && result.getResult() != null) {
				
				//解析票号
				String[] tkno=result.getResult().getTktNumber().split(",");
				if (tkno.length == 2) {
					result.setTicketNo(tkno[0]);
					result.setSecondTicketNo(tkno[1]);
				}
				
				//解析税费
				String []taxArr=result.getResult().getTax().split("\\|");
				List<TrfdTax> taxs=new ArrayList<TrfdTax>();
				for(int i=0;i<taxArr.length;i++){
					String[] tax=taxArr[i].split(",");
					if (tkno.length == 2) {
						TrfdTax  trfdtax=new TrfdTax();
						trfdtax.setTaxType(tax[0]);
						trfdtax.setTax(tax[1]);
						taxs.add(trfdtax);
					}
				}
				result.setTaxs(taxs);
				
				//解析退票航段
				String[]  hds=result.getResult().getCouponNo().split(",");
				result.setCouponNo1(hds[0]);
				result.setCouponNo2(hds[1]);
				result.setCouponNo3(hds[2]);
				result.setCouponNo4(hds[3]);
			}
			return result;
		} else {
			return (TrfdSubmitResult) XmlUtils.fromXml(data,TrfdSubmitResult.class);
		}
	}
	
	private void valid(TrfdParam param) throws EtermException {
		if (StringUtils.isBlank(param.getPreview())) {
			throw new EtermException("指令模式【preview】不能为空");
		}
		
		if (StringUtils.isBlank(param.getPrinter())) {
			throw new EtermException("打票机【printer】不能为空");
		}
		/*
		if(StringUtils.isBlank(param.getConjunction())){
			throw new EtermException("关联号【Conjunction】不能为空");
		}
		
		if(StringUtils.isBlank(param.getEt())){
			throw new EtermException("是否是电子票【et】不能为空");
		}
		
		if(StringUtils.isBlank(param.getCommission())){
			throw new EtermException("代理费【commission】不能为空");
		}*/
		
		if(StringUtils.isBlank(param.getCountry())){
			throw new EtermException("国内国际【country】不能为空");
		}
	}
	
	
	private TrfdPreviewResult parsePreviewXml(String rtnXml) {
		TrfdPreviewResult result = (TrfdPreviewResult) XmlUtils.fromXml(rtnXml, TrfdPreviewResult.class);
		//result.setInitData(rtnXml);
/*
		VeTrfdPreviewResponse response = (VeTrfdPreviewResponse) XmlUtils.fromXml(rtnXml, VeTrfdPreviewResponse.class);
		if (response == null) {
			result.setResultXml("-1");
			result.setResultError(rtnXml);
		} else {
			result.setOperation(response.getOperation());
			result.setPreview(response.getPreview());
			result.setCmd(response.getCmd());
			String content = response.getContent();
			result.setContent(content);
			//
			VeTrfdResult _veTrfdResult = response.getResult();
			if (content.startsWith(REFUND)) {
				result.setResultXml("-1");
				result.setResultError("退票已存在");
			} else if (content.indexOf(PNR_NOT_XE) != -1) {
				result.setResultXml("-1");
				result.setResultError("请先删除PNR,重新退票");
			} else if (content.startsWith(NO_TICKET_DEVICE)) {
				result.setResultXml("-1");
				result.setResultError("未发现有效的打票机");
			} else {
				if (_veTrfdResult == null) {
					result.setResultXml("-1");
					result.setResultError(rtnXml);
				} else {
					result.setResultXml("0");
					result.setTrfu(_veTrfdResult.getTrfu());
					result.setHeader(_veTrfdResult.getHeader());
					result.setRfdNumber(_veTrfdResult.getRfdNumber());
					result.setRefundType(_veTrfdResult.getRefundType());
					result.setDeviceId(_veTrfdResult.getDeviceId());
					result.setDateTime(_veTrfdResult.getDateTime());
					result.setAgent(_veTrfdResult.getAgent());
					result.setIata(_veTrfdResult.getIata());
					result.setOffice(_veTrfdResult.getOffice());
					result.setAirlineCode(_veTrfdResult.getAirlineCode());
					String tktNumber = _veTrfdResult.getTktNumber();
					if (StringUtils.isNotBlank(tktNumber)) {
						String[] tktNumbers = tktNumber.split(",");
						if (tktNumbers != null) {
							if (tktNumbers.length >= 1) {
								result.setTktNumber(tktNumbers[0]);
							}
							if (tktNumbers.length >= 2) {
								result.setSecondTicketNo(tktNumbers[1]);
							}
						}
					}
					result.setCheck(_veTrfdResult.getCheck());
					result.setConjunctionNo(_veTrfdResult.getConjunctionNo());

					String couponNo = _veTrfdResult.getCouponNo();
					if (StringUtils.isNotBlank(couponNo)) {
						String[] couponNos = couponNo.split(",");
						if (couponNos != null && couponNos.length == 4) {
							result.setCouponNo1(couponNos[0]);
							result.setCouponNo2(couponNos[1]);
							result.setCouponNo3(couponNos[2]);
							result.setCouponNo4(couponNos[3]);
						}
					}
					result.setPassengerName(_veTrfdResult.getPassengerName());
					result.setCurrencyCode(_veTrfdResult.getCurrencyCode());
					result.setFormOfPayment(_veTrfdResult.getFormOfPayment());
					result.setGrossRefund(_veTrfdResult.getGrossRefund());
					result.setEt(_veTrfdResult.getEt());
					result.setDeduction(_veTrfdResult.getDeduction());

					String commission = _veTrfdResult.getCommission();
					if (StringUtils.isNotBlank(commission)) {
						String[] commissions = commission.split(",");
						if (commissions != null) {
							if (commissions.length == 1) {
								result.setCommission(commissions[0]);
							} else if (commissions.length == 2) {
								result.setCommissionRate(commissions[0]);
								result.setCommission(commissions[1]);
							}
						}
					}

					String tax = _veTrfdResult.getTax();
					if (StringUtils.isNotBlank(tax)) {
						String[] taxs = tax.split("\\|");
						if (taxs != null) {
							List<TrfdTax> taxList = new ArrayList<TrfdTax>();
							for (String one : taxs) {
								String[] arrOne = one.split(",");
								if (arrOne != null && arrOne.length == 2) {
									TrfdTax veTrfdTax = new TrfdTax();
									veTrfdTax.setTaxType(arrOne[0]);
									veTrfdTax.setTax(arrOne[1]);
									taxList.add(veTrfdTax);
								}
							}
							result.setTaxs(taxList);
						}
					}

					String remark = _veTrfdResult.getRemark();
					if (StringUtils.isNotBlank(remark)) {
						String[] remarks = remark.split("\\|");
						if (remarks != null && remarks.length == 2) {
							result.setRemark1(remarks[0]);
							result.setRemark2(remarks[1]);
						}
					}
					result.setCreditCard(_veTrfdResult.getCreditCard());

					String netRefund = StringUtils.trimToEmpty(_veTrfdResult.getNetRefund());
					if (StringUtils.isNotBlank(netRefund) && !",".equals(netRefund)) {
						if (netRefund.startsWith(",")) {
							netRefund = " " + netRefund;
						}
						String[] netRefunds = netRefund.split(",");
						if (netRefunds != null) {
							if (netRefunds.length == 1) {
								result.setNetRefund1(StringUtils.trimToEmpty(netRefunds[0]));
							} else if (netRefunds.length == 2) {
								result.setNetRefund1(StringUtils.trimToEmpty(netRefunds[0]));
								result.setNetRefund2(StringUtils.trimToEmpty(netRefunds[1]));
							}
						}
					}

				}
			}

		}
		*/
		return result;
	}

	private TrfdSubmitResult parseSubmitXml(String rtnXml) {
		TrfdSubmitResult result = (TrfdSubmitResult) XmlUtils.fromXml(rtnXml, TrfdSubmitResult.class);
		return result;
	}
	
	public static void main(String[] args) throws EtermException {
		String data="<TRFD><OPERATION>AUTO</OPERATION><PREVIEW>0</PREVIEW><SUCESSED>1</SUCESSED><CMD1>TRFD:Z/731-9135132849/1</CMD1><RESULT1>            AIRLINE/BSP   TICKET   REFUND   INFORMATION   FORM  REFUND NUMBER:0              REFUND TYPE:DOMESTIC                DEVICE-ID:1 DATE:04AUG16                 AGENT:44867      IATA:08322705      OFFICE:SHA384  WAVER:NONE                   WAVER TYPE:      PASSENGER TYPE:ADT AIRLINE CODE:731             TICKET NO.:9135132849-35132849  CONJUNCTION NO.:1            COUPON NO.:1:1000    2:0000    3:0000    4:0000 PASSENGER NAME:LUOSHAOYUAN   GROSS  REFUND:760.00         PAYMENT  FORM:CASH      CURRENCY  CODE:CNY  SN CD AMOUNT(SN-SEQUENCY NUMBER : CD-TAX CODE)       ET-(Y/N):Y  TAX: 1:CN50.00     2:YQ0.00  COMMITMENT:0.00              OTHER DEDUCTION:38.00   NET REFUND:757.00            AIRLINE REFUND:772.00   COMMISSION AMOUNT:15.00  CREDIT CARD:NONE CONFIRM  REFUND              RFIS:731-9135132849   </RESULT1><RESULT1XML><Header>AIRLINE/BSP   TICKET   REFUND   INFORMATION   FORM</Header><RfdNumber>0</RfdNumber><RefundTYPE>DOMESTIC</RefundTYPE><DeviceID>1</DeviceID><DateTime>04AUG16</DateTime><Agent>44867</Agent><IATA>08322705</IATA><OFFICE>SHA384</OFFICE><Waver>NONE</Waver><WaverType/><PassengerType>ADT</PassengerType><AirlineCode>731</AirlineCode><TKTNumber>9135132849,35132849</TKTNumber><ConjunctionNo>1</ConjunctionNo><CouponNo>1000,0000,0000,0000</CouponNo><PassengerName>LUOSHAOYUAN</PassengerName><GrossRefund>760.00</GrossRefund><FormOfPayment>CASH</FormOfPayment><CurrencyCode>CNY</CurrencyCode><SN_CD_AMOUNT>CD-TAX CODE</SN_CD_AMOUNT><ET>Y</ET><Tax>CN,50.00|YQ,0.00</Tax><Commission>0.00</Commission><Deduction>38.00</Deduction><NetRefund>757.00</NetRefund><AirlineRefund>772.00</AirlineRefund><CommissionAmount>15.00</CommissionAmount><CreditCard>NONE</CreditCard></RESULT1XML><CMD2>RFIS:731-9135132849</CMD2><RESULT2>TRFD:1  /D/155998896</RESULT2><REFUNDNO>155998896</REFUNDNO><RESULT>ET TRANSACTION SUCCESS IF NECESSARY, PLEASE CANCEL PNR  </RESULT></TRFD>";
		TrfdSubmitResult result=(TrfdSubmitResult) XmlUtils.fromXml(data,TrfdSubmitResult.class);
		
		System.out.println(result.getSucessed());
		/*
		TrfdParam param = new TrfdParam();
		param.setUrl("http://192.168.1.69:8088");
		param.setUserid("8636");// DFCX123  8402
		// AUTO,HALFAUTO,MANUAL,MODIFY，TRFX
		param.setOperationType("MANUAL");
	//	param.setRefundNo("122556889");
		param.setPreview("1");
		param.setPrinter("9");
		param.setCountry("D");
		param.setEt("Y");
		// 784-3992614607 784-3955476561 999-3993697254 999-3993697244(多人)
		// etrf 1/tkno/prnt/2/open
		param.setTicketNo("784-5221445963");

		System.out.println(IbeService.trfd(param));
		*/
	}
}
