package com.ctrip.ws.flights;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2016-03-22T18:21:48.953+08:00
 * Generated source version: 2.7.11
 * 
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "IssueBillAssignToSoap")
@XmlSeeAlso({ObjectFactory.class})
public interface IssueBillAssignToSoap {

    @WebResult(name = "HandleResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "Handle", targetNamespace = "http://tempuri.org/", className = "cn.vetech.vedsb.jp.jpddsz.ctrip.api.Handle")
    @WebMethod(operationName = "Handle", action = "http://tempuri.org/Handle")
    @ResponseWrapper(localName = "HandleResponse", targetNamespace = "http://tempuri.org/", className = "cn.vetech.vedsb.jp.jpddsz.ctrip.api.HandleResponse")
    public java.lang.String handle(
        @WebParam(name = "requestXML", targetNamespace = "http://tempuri.org/")
        java.lang.String requestXML
    );
}
