
package com.ctrip.ws.flights;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2016-03-29T10:20:38.316+08:00
 * Generated source version: 2.7.11
 * 
 */
public final class RefundConfirmSoap_RefundConfirmSoap12_Client {

    private static final QName SERVICE_NAME = new QName("http://tempuri.org/", "RefundConfirm");

    private RefundConfirmSoap_RefundConfirmSoap12_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = RefundConfirm.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        RefundConfirm ss = new RefundConfirm(wsdlURL, SERVICE_NAME);
        RefundConfirmSoap port = ss.getRefundConfirmSoap12();  
        
        {
        System.out.println("Invoking handle...");
        java.lang.String _handle_requestXML = "";
        java.lang.String _handle__return = port.handle(_handle_requestXML);
        System.out.println("handle.result=" + _handle__return);


        }

        System.exit(0);
    }

}
