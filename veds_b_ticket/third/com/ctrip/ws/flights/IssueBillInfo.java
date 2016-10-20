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
 * 2016-03-22T18:16:47.651+08:00
 * Generated source version: 2.7.11
 * 
 */
@WebServiceClient(name = "IssueBillInfo", 
                  wsdlLocation = "http://flights.ws.ctrip.com/Flight.Order.SupplierOpenAPI/IssueBillInfo.asmx?WSDL",
                  targetNamespace = "http://tempuri.org/") 
public class IssueBillInfo extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "IssueBillInfo");
    public final static QName IssueBillInfoSoap12 = new QName("http://tempuri.org/", "IssueBillInfoSoap12");
    public final static QName IssueBillInfoSoap = new QName("http://tempuri.org/", "IssueBillInfoSoap");
    static {
        URL url = null;
        try {
            url = new URL("http://flights.ws.ctrip.com/Flight.Order.SupplierOpenAPI/IssueBillInfo.asmx?WSDL");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(IssueBillInfo.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://flights.ws.ctrip.com/Flight.Order.SupplierOpenAPI/IssueBillInfo.asmx?WSDL");
        }
        WSDL_LOCATION = url;
    }

    public IssueBillInfo(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public IssueBillInfo(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IssueBillInfo() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns IssueBillInfoSoap
     */
    @WebEndpoint(name = "IssueBillInfoSoap12")
    public IssueBillInfoSoap getIssueBillInfoSoap12() {
        return super.getPort(IssueBillInfoSoap12, IssueBillInfoSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IssueBillInfoSoap
     */
    @WebEndpoint(name = "IssueBillInfoSoap12")
    public IssueBillInfoSoap getIssueBillInfoSoap12(WebServiceFeature... features) {
        return super.getPort(IssueBillInfoSoap12, IssueBillInfoSoap.class, features);
    }
    /**
     *
     * @return
     *     returns IssueBillInfoSoap
     */
    @WebEndpoint(name = "IssueBillInfoSoap")
    public IssueBillInfoSoap getIssueBillInfoSoap() {
        return super.getPort(IssueBillInfoSoap, IssueBillInfoSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IssueBillInfoSoap
     */
    @WebEndpoint(name = "IssueBillInfoSoap")
    public IssueBillInfoSoap getIssueBillInfoSoap(WebServiceFeature... features) {
        return super.getPort(IssueBillInfoSoap, IssueBillInfoSoap.class, features);
    }

}