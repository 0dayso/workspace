
package com.ctrip.ws.flights;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2016-03-29T10:16:32.092+08:00
 * Generated source version: 2.7.11
 * 
 */
 
public class RefundWaitConfirmListSoap_RefundWaitConfirmListSoap_Server{

    protected RefundWaitConfirmListSoap_RefundWaitConfirmListSoap_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new RefundWaitConfirmListSoapImpl();
        String address = "http://flights.ws.ctrip.com/Flight.Order.SupplierOpenAPI/RefundWaitConfirmList.asmx";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new RefundWaitConfirmListSoap_RefundWaitConfirmListSoap_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
