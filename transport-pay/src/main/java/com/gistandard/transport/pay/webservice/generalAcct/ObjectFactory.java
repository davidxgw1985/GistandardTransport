
package com.gistandard.transport.pay.webservice.generalAcct;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.valueplus.system.webservice.client.generalAcct package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _QueryMSInAndOut_QNAME = new QName("http://webService.external.giifiCalc.transport.gistandard.com/", "queryMSInAndOut");
    private final static QName _QueryMSInAndOutResponse_QNAME = new QName("http://webService.external.giifiCalc.transport.gistandard.com/", "queryMSInAndOutResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.valueplus.system.webservice.client.generalAcct
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryMSInAndOut }
     * 
     */
    public QueryMSInAndOut createQueryMSInAndOut() {
        return new QueryMSInAndOut();
    }

    /**
     * Create an instance of {@link QueryMSInAndOutResponse }
     * 
     */
    public QueryMSInAndOutResponse createQueryMSInAndOutResponse() {
        return new QueryMSInAndOutResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryMSInAndOut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.external.giifiCalc.transport.gistandard.com/", name = "queryMSInAndOut")
    public JAXBElement<QueryMSInAndOut> createQueryMSInAndOut(QueryMSInAndOut value) {
        return new JAXBElement<QueryMSInAndOut>(_QueryMSInAndOut_QNAME, QueryMSInAndOut.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryMSInAndOutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.external.giifiCalc.transport.gistandard.com/", name = "queryMSInAndOutResponse")
    public JAXBElement<QueryMSInAndOutResponse> createQueryMSInAndOutResponse(QueryMSInAndOutResponse value) {
        return new JAXBElement<QueryMSInAndOutResponse>(_QueryMSInAndOutResponse_QNAME, QueryMSInAndOutResponse.class, null, value);
    }

}
