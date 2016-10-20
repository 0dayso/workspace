package com.etrip8.airpay;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2016-07-08T21:11:44.274+08:00
 * Generated source version: 2.7.11
 * 
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "PayServiceSoap")
@XmlSeeAlso({ObjectFactory.class})
public interface PayServiceSoap {

    /**
     * 操作员认证
     */
    @WebResult(name = "setOperatorContractResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "setOperatorContract", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetOperatorContract")
    @WebMethod(action = "http://tempuri.org/setOperatorContract")
    @ResponseWrapper(localName = "setOperatorContractResponse", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetOperatorContractResponse")
    public java.lang.String setOperatorContract(
        @WebParam(name = "sAccountNo", targetNamespace = "http://tempuri.org/")
        java.lang.String sAccountNo,
        @WebParam(name = "sOperatorName", targetNamespace = "http://tempuri.org/")
        java.lang.String sOperatorName,
        @WebParam(name = "sPwd", targetNamespace = "http://tempuri.org/")
        java.lang.String sPwd,
        @WebParam(name = "iDayNum", targetNamespace = "http://tempuri.org/")
        int iDayNum,
        @WebParam(name = "sMaCode", targetNamespace = "http://tempuri.org/")
        java.lang.String sMaCode,
        @WebParam(name = "bMaCode", targetNamespace = "http://tempuri.org/")
        boolean bMaCode,
        @WebParam(name = "sSign", targetNamespace = "http://tempuri.org/")
        java.lang.String sSign
    );

    /**
     * 改变订单
     */
    @WebResult(name = "setTicketOrderToOrderXMLResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "setTicketOrderToOrderXML", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetTicketOrderToOrderXML")
    @WebMethod(action = "http://tempuri.org/setTicketOrderToOrderXML")
    @ResponseWrapper(localName = "setTicketOrderToOrderXMLResponse", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetTicketOrderToOrderXMLResponse")
    public java.lang.String setTicketOrderToOrderXML(
        @WebParam(name = "ticketorderByxml", targetNamespace = "http://tempuri.org/")
        java.lang.String ticketorderByxml,
        @WebParam(name = "sign", targetNamespace = "http://tempuri.org/")
        java.lang.String sign
    );

    /**
     * 支付服务
     */
    @WebResult(name = "setPayResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "setPay", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetPay")
    @WebMethod(action = "http://tempuri.org/setPay")
    @ResponseWrapper(localName = "setPayResponse", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetPayResponse")
    public java.lang.String setPay(
        @WebParam(name = "Version", targetNamespace = "http://tempuri.org/")
        java.lang.String version,
        @WebParam(name = "MerId", targetNamespace = "http://tempuri.org/")
        java.lang.String merId,
        @WebParam(name = "OrdId", targetNamespace = "http://tempuri.org/")
        java.lang.String ordId,
        @WebParam(name = "GateId", targetNamespace = "http://tempuri.org/")
        java.lang.String gateId,
        @WebParam(name = "Priv1", targetNamespace = "http://tempuri.org/")
        java.lang.String priv1,
        @WebParam(name = "TransAmt", targetNamespace = "http://tempuri.org/")
        java.lang.String transAmt,
        @WebParam(name = "CuryId", targetNamespace = "http://tempuri.org/")
        java.lang.String curyId,
        @WebParam(name = "TransDate", targetNamespace = "http://tempuri.org/")
        java.lang.String transDate,
        @WebParam(name = "TransType", targetNamespace = "http://tempuri.org/")
        java.lang.String transType,
        @WebParam(name = "BgRetUrl", targetNamespace = "http://tempuri.org/")
        java.lang.String bgRetUrl,
        @WebParam(name = "PageRetUrl", targetNamespace = "http://tempuri.org/")
        java.lang.String pageRetUrl,
        @WebParam(name = "ChkValue", targetNamespace = "http://tempuri.org/")
        java.lang.String chkValue,
        @WebParam(name = "ChinaPayUrl", targetNamespace = "http://tempuri.org/")
        java.lang.String chinaPayUrl,
        @WebParam(name = "sign", targetNamespace = "http://tempuri.org/")
        java.lang.String sign,
        @WebParam(name = "certSerial", targetNamespace = "http://tempuri.org/")
        java.lang.String certSerial,
        @WebParam(name = "ticketorder", targetNamespace = "http://tempuri.org/")
        com.etrip8.airpay.SetPay.Ticketorder ticketorder
    );

    @WebResult(name = "HelloWorldResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "HelloWorld", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.HelloWorld")
    @WebMethod(operationName = "HelloWorld", action = "http://tempuri.org/HelloWorld")
    @ResponseWrapper(localName = "HelloWorldResponse", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.HelloWorldResponse")
    public java.lang.String helloWorld();

    /**
     * 海航系支付服务-Xml订单
     */
    @WebResult(name = "SetEPTEasyPayToOrderXMLResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "SetEPTEasyPayToOrderXML", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetEPTEasyPayToOrderXML")
    @WebMethod(operationName = "SetEPTEasyPayToOrderXML", action = "http://tempuri.org/SetEPTEasyPayToOrderXML")
    @ResponseWrapper(localName = "SetEPTEasyPayToOrderXMLResponse", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetEPTEasyPayToOrderXMLResponse")
    public java.lang.String setEPTEasyPayToOrderXML(
        @WebParam(name = "airCode", targetNamespace = "http://tempuri.org/")
        java.lang.String airCode,
        @WebParam(name = "b2bUser", targetNamespace = "http://tempuri.org/")
        java.lang.String b2BUser,
        @WebParam(name = "b2bPwd", targetNamespace = "http://tempuri.org/")
        java.lang.String b2BPwd,
        @WebParam(name = "pnr", targetNamespace = "http://tempuri.org/")
        java.lang.String pnr,
        @WebParam(name = "orderID", targetNamespace = "http://tempuri.org/")
        java.lang.String orderID,
        @WebParam(name = "orderAmount", targetNamespace = "http://tempuri.org/")
        java.lang.String orderAmount,
        @WebParam(name = "sign", targetNamespace = "http://tempuri.org/")
        java.lang.String sign,
        @WebParam(name = "certSerial", targetNamespace = "http://tempuri.org/")
        java.lang.String certSerial,
        @WebParam(name = "ticketorderByxml", targetNamespace = "http://tempuri.org/")
        java.lang.String ticketorderByxml
    );

    /**
     * epiaotong客户端UnionAutoPay支付服务
     */
    @WebResult(name = "setPayToOrderXMLForCZResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "setPayToOrderXMLForCZ", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetPayToOrderXMLForCZ")
    @WebMethod(action = "http://tempuri.org/setPayToOrderXMLForCZ")
    @ResponseWrapper(localName = "setPayToOrderXMLForCZResponse", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetPayToOrderXMLForCZResponse")
    public java.lang.String setPayToOrderXMLForCZ(
        @WebParam(name = "merid", targetNamespace = "http://tempuri.org/")
        java.lang.String merid,
        @WebParam(name = "orderno", targetNamespace = "http://tempuri.org/")
        java.lang.String orderno,
        @WebParam(name = "pnr", targetNamespace = "http://tempuri.org/")
        java.lang.String pnr,
        @WebParam(name = "billno", targetNamespace = "http://tempuri.org/")
        java.lang.String billno,
        @WebParam(name = "bankcode", targetNamespace = "http://tempuri.org/")
        java.lang.String bankcode,
        @WebParam(name = "currcode", targetNamespace = "http://tempuri.org/")
        java.lang.String currcode,
        @WebParam(name = "orderamount", targetNamespace = "http://tempuri.org/")
        java.lang.String orderamount,
        @WebParam(name = "ordertype", targetNamespace = "http://tempuri.org/")
        java.lang.String ordertype,
        @WebParam(name = "langtype", targetNamespace = "http://tempuri.org/")
        java.lang.String langtype,
        @WebParam(name = "bgreturl", targetNamespace = "http://tempuri.org/")
        java.lang.String bgreturl,
        @WebParam(name = "encodemsg", targetNamespace = "http://tempuri.org/")
        java.lang.String encodemsg,
        @WebParam(name = "signmsg", targetNamespace = "http://tempuri.org/")
        java.lang.String signmsg,
        @WebParam(name = "sign", targetNamespace = "http://tempuri.org/")
        java.lang.String sign,
        @WebParam(name = "certSerial", targetNamespace = "http://tempuri.org/")
        java.lang.String certSerial,
        @WebParam(name = "ticketorderByxml", targetNamespace = "http://tempuri.org/")
        java.lang.String ticketorderByxml
    );

    /**
     * 改变订单
     */
    @WebResult(name = "setTicketOrderResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "setTicketOrder", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetTicketOrder")
    @WebMethod(action = "http://tempuri.org/setTicketOrder")
    @ResponseWrapper(localName = "setTicketOrderResponse", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetTicketOrderResponse")
    public java.lang.String setTicketOrder(
        @WebParam(name = "ticketorder", targetNamespace = "http://tempuri.org/")
        com.etrip8.airpay.SetTicketOrder.Ticketorder ticketorder,
        @WebParam(name = "sign", targetNamespace = "http://tempuri.org/")
        java.lang.String sign
    );

    /**
     * 支付服务
     */
    @WebResult(name = "setPayToOrderXMLResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "setPayToOrderXML", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetPayToOrderXML")
    @WebMethod(action = "http://tempuri.org/setPayToOrderXML")
    @ResponseWrapper(localName = "setPayToOrderXMLResponse", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetPayToOrderXMLResponse")
    public java.lang.String setPayToOrderXML(
        @WebParam(name = "Version", targetNamespace = "http://tempuri.org/")
        java.lang.String version,
        @WebParam(name = "MerId", targetNamespace = "http://tempuri.org/")
        java.lang.String merId,
        @WebParam(name = "OrdId", targetNamespace = "http://tempuri.org/")
        java.lang.String ordId,
        @WebParam(name = "GateId", targetNamespace = "http://tempuri.org/")
        java.lang.String gateId,
        @WebParam(name = "Priv1", targetNamespace = "http://tempuri.org/")
        java.lang.String priv1,
        @WebParam(name = "TransAmt", targetNamespace = "http://tempuri.org/")
        java.lang.String transAmt,
        @WebParam(name = "CuryId", targetNamespace = "http://tempuri.org/")
        java.lang.String curyId,
        @WebParam(name = "TransDate", targetNamespace = "http://tempuri.org/")
        java.lang.String transDate,
        @WebParam(name = "TransType", targetNamespace = "http://tempuri.org/")
        java.lang.String transType,
        @WebParam(name = "BgRetUrl", targetNamespace = "http://tempuri.org/")
        java.lang.String bgRetUrl,
        @WebParam(name = "PageRetUrl", targetNamespace = "http://tempuri.org/")
        java.lang.String pageRetUrl,
        @WebParam(name = "ChkValue", targetNamespace = "http://tempuri.org/")
        java.lang.String chkValue,
        @WebParam(name = "ChinaPayUrl", targetNamespace = "http://tempuri.org/")
        java.lang.String chinaPayUrl,
        @WebParam(name = "sign", targetNamespace = "http://tempuri.org/")
        java.lang.String sign,
        @WebParam(name = "certSerial", targetNamespace = "http://tempuri.org/")
        java.lang.String certSerial,
        @WebParam(name = "ticketorderByxml", targetNamespace = "http://tempuri.org/")
        java.lang.String ticketorderByxml
    );

    /**
     * 账户认证
     */
    @WebResult(name = "setAccountNoContractResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "setAccountNoContract", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetAccountNoContract")
    @WebMethod(action = "http://tempuri.org/setAccountNoContract")
    @ResponseWrapper(localName = "setAccountNoContractResponse", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetAccountNoContractResponse")
    public java.lang.String setAccountNoContract(
        @WebParam(name = "sAccountNo", targetNamespace = "http://tempuri.org/")
        java.lang.String sAccountNo,
        @WebParam(name = "sPwd", targetNamespace = "http://tempuri.org/")
        java.lang.String sPwd,
        @WebParam(name = "iDayNum", targetNamespace = "http://tempuri.org/")
        int iDayNum,
        @WebParam(name = "sMaCode", targetNamespace = "http://tempuri.org/")
        java.lang.String sMaCode,
        @WebParam(name = "bMaCode", targetNamespace = "http://tempuri.org/")
        boolean bMaCode,
        @WebParam(name = "sSign", targetNamespace = "http://tempuri.org/")
        java.lang.String sSign
    );

    /**
     * 海航系支付服务
     */
    @WebResult(name = "SetEPTEasyPayResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "SetEPTEasyPay", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetEPTEasyPay")
    @WebMethod(operationName = "SetEPTEasyPay", action = "http://tempuri.org/SetEPTEasyPay")
    @ResponseWrapper(localName = "SetEPTEasyPayResponse", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetEPTEasyPayResponse")
    public java.lang.String setEPTEasyPay(
        @WebParam(name = "airCode", targetNamespace = "http://tempuri.org/")
        java.lang.String airCode,
        @WebParam(name = "b2bUser", targetNamespace = "http://tempuri.org/")
        java.lang.String b2BUser,
        @WebParam(name = "b2bPwd", targetNamespace = "http://tempuri.org/")
        java.lang.String b2BPwd,
        @WebParam(name = "pnr", targetNamespace = "http://tempuri.org/")
        java.lang.String pnr,
        @WebParam(name = "orderID", targetNamespace = "http://tempuri.org/")
        java.lang.String orderID,
        @WebParam(name = "orderAmount", targetNamespace = "http://tempuri.org/")
        java.lang.String orderAmount,
        @WebParam(name = "sign", targetNamespace = "http://tempuri.org/")
        java.lang.String sign,
        @WebParam(name = "certSerial", targetNamespace = "http://tempuri.org/")
        java.lang.String certSerial,
        @WebParam(name = "ticketorder", targetNamespace = "http://tempuri.org/")
        com.etrip8.airpay.SetEPTEasyPay.Ticketorder ticketorder
    );

    /**
     * epiaotong客户端UnionAutoPay支付服务
     */
    @WebResult(name = "setPayForCZResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "setPayForCZ", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetPayForCZ")
    @WebMethod(action = "http://tempuri.org/setPayForCZ")
    @ResponseWrapper(localName = "setPayForCZResponse", targetNamespace = "http://tempuri.org/", className = "com.etrip8.airpay.SetPayForCZResponse")
    public java.lang.String setPayForCZ(
        @WebParam(name = "merid", targetNamespace = "http://tempuri.org/")
        java.lang.String merid,
        @WebParam(name = "orderno", targetNamespace = "http://tempuri.org/")
        java.lang.String orderno,
        @WebParam(name = "pnr", targetNamespace = "http://tempuri.org/")
        java.lang.String pnr,
        @WebParam(name = "billno", targetNamespace = "http://tempuri.org/")
        java.lang.String billno,
        @WebParam(name = "bankcode", targetNamespace = "http://tempuri.org/")
        java.lang.String bankcode,
        @WebParam(name = "currcode", targetNamespace = "http://tempuri.org/")
        java.lang.String currcode,
        @WebParam(name = "orderamount", targetNamespace = "http://tempuri.org/")
        java.lang.String orderamount,
        @WebParam(name = "ordertype", targetNamespace = "http://tempuri.org/")
        java.lang.String ordertype,
        @WebParam(name = "langtype", targetNamespace = "http://tempuri.org/")
        java.lang.String langtype,
        @WebParam(name = "bgreturl", targetNamespace = "http://tempuri.org/")
        java.lang.String bgreturl,
        @WebParam(name = "encodemsg", targetNamespace = "http://tempuri.org/")
        java.lang.String encodemsg,
        @WebParam(name = "signmsg", targetNamespace = "http://tempuri.org/")
        java.lang.String signmsg,
        @WebParam(name = "sign", targetNamespace = "http://tempuri.org/")
        java.lang.String sign,
        @WebParam(name = "certSerial", targetNamespace = "http://tempuri.org/")
        java.lang.String certSerial,
        @WebParam(name = "ticketorder", targetNamespace = "http://tempuri.org/")
        com.etrip8.airpay.SetPayForCZ.Ticketorder ticketorder
    );
}
