package com.ctrip.ws.flights;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2016-03-29T10:16:32.092+08:00
 * Generated source version: 2.7.11
 * 
 */
@WebServiceClient(name = "RefundWaitConfirmList", 
                  wsdlLocation = "http://flights.ws.ctrip.com/Flight.Order.SupplierOpenAPI/RefundWaitConfirmList.asmx?WSDL",
                  targetNamespace = "http://tempuri.org/") 
public class RefundWaitConfirmList extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "RefundWaitConfirmList");
    public final static QName RefundWaitConfirmListSoap = new QName("http://tempuri.org/", "RefundWaitConfirmListSoap");
    public final static QName RefundWaitConfirmListSoap12 = new QName("http://tempuri.org/", "RefundWaitConfirmListSoap12");
    static {
        URL url = null;
        try {
            url = new URL("http://flights.ws.ctrip.com/Flight.Order.SupplierOpenAPI/RefundWaitConfirmList.asmx?WSDL");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(RefundWaitConfirmList.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://flights.ws.ctrip.com/Flight.Order.SupplierOpenAPI/RefundWaitConfirmList.asmx?WSDL");
        }
        WSDL_LOCATION = url;
    }

    public RefundWaitConfirmList(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public RefundWaitConfirmList(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RefundWaitConfirmList() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns RefundWaitConfirmListSoap
     */
    @WebEndpoint(name = "RefundWaitConfirmListSoap")
    public RefundWaitConfirmListSoap getRefundWaitConfirmListSoap() {
        return super.getPort(RefundWaitConfirmListSoap, RefundWaitConfirmListSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RefundWaitConfirmListSoap
     */
    @WebEndpoint(name = "RefundWaitConfirmListSoap")
    public RefundWaitConfirmListSoap getRefundWaitConfirmListSoap(WebServiceFeature... features) {
        return super.getPort(RefundWaitConfirmListSoap, RefundWaitConfirmListSoap.class, features);
    }
    /**
     *
     * @return
     *     returns RefundWaitConfirmListSoap
     */
    @WebEndpoint(name = "RefundWaitConfirmListSoap12")
    public RefundWaitConfirmListSoap getRefundWaitConfirmListSoap12() {
        return super.getPort(RefundWaitConfirmListSoap12, RefundWaitConfirmListSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RefundWaitConfirmListSoap
     */
    @WebEndpoint(name = "RefundWaitConfirmListSoap12")
    public RefundWaitConfirmListSoap getRefundWaitConfirmListSoap12(WebServiceFeature... features) {
        return super.getPort(RefundWaitConfirmListSoap12, RefundWaitConfirmListSoap.class, features);
    }

}
