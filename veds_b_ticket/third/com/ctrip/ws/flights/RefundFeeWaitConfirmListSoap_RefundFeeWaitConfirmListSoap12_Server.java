
package com.ctrip.ws.flights;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2016-03-29T10:21:44.803+08:00
 * Generated source version: 2.7.11
 * 
 */
 
public class RefundFeeWaitConfirmListSoap_RefundFeeWaitConfirmListSoap12_Server{

    protected RefundFeeWaitConfirmListSoap_RefundFeeWaitConfirmListSoap12_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new RefundFeeWaitConfirmListSoapImpl();
        String address = "http://flights.ws.ctrip.com/Flight.Order.SupplierOpenAPI/RefundFeeWaitConfirmList.asmx";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new RefundFeeWaitConfirmListSoap_RefundFeeWaitConfirmListSoap12_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}