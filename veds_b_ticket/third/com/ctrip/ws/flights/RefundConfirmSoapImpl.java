
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.ctrip.ws.flights;

import java.util.logging.Logger;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2016-03-29T10:20:38.332+08:00
 * Generated source version: 2.7.11
 * 
 */

@javax.jws.WebService(
                      serviceName = "RefundConfirm",
                      portName = "RefundConfirmSoap",
                      targetNamespace = "http://tempuri.org/",
                      wsdlLocation = "http://flights.ws.ctrip.com/Flight.Order.SupplierOpenAPI/RefundConfirm.asmx?WSDL",
                      endpointInterface = "com.ctrip.ws.flights.RefundConfirmSoap")
                      
public class RefundConfirmSoapImpl implements RefundConfirmSoap {

    private static final Logger LOG = Logger.getLogger(RefundConfirmSoapImpl.class.getName());

    /* (non-Javadoc)
     * @see com.ctrip.ws.flights.RefundConfirmSoap#handle(java.lang.String  requestXML )*
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
