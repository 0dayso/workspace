
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.ctrip.ws.flights;

import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2016-03-22T18:21:48.933+08:00
 * Generated source version: 2.7.11
 * 
 */

@javax.jws.WebService(
                      serviceName = "IssueBillAssignTo",
                      portName = "IssueBillAssignToSoap12",
                      targetNamespace = "http://tempuri.org/",
                      wsdlLocation = "http://flights.ws.ctrip.com/Flight.Order.SupplierOpenAPI/IssueBillAssignTo.asmx?WSDL",
                      endpointInterface = "cn.vetech.vedsb.jp.jpddsz.ctrip.api.IssueBillAssignToSoap")
                      
public class IssueBillAssignToSoapImpl1 implements IssueBillAssignToSoap {

    private static final Logger LOG = Logger.getLogger(IssueBillAssignToSoapImpl1.class.getName());

    /* (non-Javadoc)
     * @see cn.vetech.vedsb.jp.jpddsz.ctrip.api.IssueBillAssignToSoap#handle(java.lang.String  requestXML )*
     */
    public java.lang.String handle(java.lang.String requestXML) { 
        LOG.info("Executing operation handle");
        System.out.println(requestXML);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}