package com.etrip8.airpay;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2016-07-08T21:11:44.321+08:00
 * Generated source version: 2.7.11
 * 
 */
@WebServiceClient(name = "PayService", 
                  wsdlLocation = "http://airpay.etrip8.com/PayService.asmx?WSDL",
                  targetNamespace = "http://tempuri.org/") 
public class PayService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "PayService");
    public final static QName PayServiceSoap = new QName("http://tempuri.org/", "PayServiceSoap");
    public final static QName PayServiceSoap12 = new QName("http://tempuri.org/", "PayServiceSoap12");
    static {
        URL url = null;
        try {
            url = new URL("http://airpay.etrip8.com/PayService.asmx?WSDL");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PayService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://airpay.etrip8.com/PayService.asmx?WSDL");
        }
        WSDL_LOCATION = url;
    }

    public PayService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PayService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PayService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns PayServiceSoap
     */
    @WebEndpoint(name = "PayServiceSoap")
    public PayServiceSoap getPayServiceSoap() {
        return super.getPort(PayServiceSoap, PayServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PayServiceSoap
     */
    @WebEndpoint(name = "PayServiceSoap")
    public PayServiceSoap getPayServiceSoap(WebServiceFeature... features) {
        return super.getPort(PayServiceSoap, PayServiceSoap.class, features);
    }
    /**
     *
     * @return
     *     returns PayServiceSoap
     */
    @WebEndpoint(name = "PayServiceSoap12")
    public PayServiceSoap getPayServiceSoap12() {
        return super.getPort(PayServiceSoap12, PayServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PayServiceSoap
     */
    @WebEndpoint(name = "PayServiceSoap12")
    public PayServiceSoap getPayServiceSoap12(WebServiceFeature... features) {
        return super.getPort(PayServiceSoap12, PayServiceSoap.class, features);
    }

}
