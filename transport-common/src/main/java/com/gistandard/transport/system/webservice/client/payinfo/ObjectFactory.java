
package com.gistandard.transport.system.webservice.client.payinfo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gistandard.transport.system.webservice.client.payinfo package. 
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

    private final static QName _GetQuote_QNAME = new QName("http://webService.external.giifiCalc.transport.gistandard.com/", "GetQuote");
    private final static QName _GetQuoteResponse_QNAME = new QName("http://webService.external.giifiCalc.transport.gistandard.com/", "GetQuoteResponse");
    private final static QName _QueryExpressTransportPayMoney_QNAME = new QName("http://webService.external.giifiCalc.transport.gistandard.com/", "QueryExpressTransportPayMoney");
    private final static QName _QueryExpressTransportPayMoneyResponse_QNAME = new QName("http://webService.external.giifiCalc.transport.gistandard.com/", "QueryExpressTransportPayMoneyResponse");
    private final static QName _Exception_QNAME = new QName("http://webService.external.giifiCalc.transport.gistandard.com/", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gistandard.transport.system.webservice.client.payinfo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetQuote }
     * 
     */
    public GetQuote createGetQuote() {
        return new GetQuote();
    }

    /**
     * Create an instance of {@link GetQuoteResponse }
     * 
     */
    public GetQuoteResponse createGetQuoteResponse() {
        return new GetQuoteResponse();
    }

    /**
     * Create an instance of {@link QueryExpressTransportPayMoney }
     * 
     */
    public QueryExpressTransportPayMoney createQueryExpressTransportPayMoney() {
        return new QueryExpressTransportPayMoney();
    }

    /**
     * Create an instance of {@link QueryExpressTransportPayMoneyResponse }
     * 
     */
    public QueryExpressTransportPayMoneyResponse createQueryExpressTransportPayMoneyResponse() {
        return new QueryExpressTransportPayMoneyResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link ValidBillMst }
     * 
     */
    public ValidBillMst createValidBillMst() {
        return new ValidBillMst();
    }

    /**
     * Create an instance of {@link ValidBillDtl }
     * 
     */
    public ValidBillDtl createValidBillDtl() {
        return new ValidBillDtl();
    }

    /**
     * Create an instance of {@link PlatFormInModel }
     * 
     */
    public PlatFormInModel createPlatFormInModel() {
        return new PlatFormInModel();
    }

    /**
     * Create an instance of {@link PlatFormOutModel }
     * 
     */
    public PlatFormOutModel createPlatFormOutModel() {
        return new PlatFormOutModel();
    }

    /**
     * Create an instance of {@link PlatFormDetailModel }
     * 
     */
    public PlatFormDetailModel createPlatFormDetailModel() {
        return new PlatFormDetailModel();
    }

    /**
     * Create an instance of {@link PlatFormPriceModel }
     * 
     */
    public PlatFormPriceModel createPlatFormPriceModel() {
        return new PlatFormPriceModel();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.external.giifiCalc.transport.gistandard.com/", name = "GetQuote")
    public JAXBElement<GetQuote> createGetQuote(GetQuote value) {
        return new JAXBElement<GetQuote>(_GetQuote_QNAME, GetQuote.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQuoteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.external.giifiCalc.transport.gistandard.com/", name = "GetQuoteResponse")
    public JAXBElement<GetQuoteResponse> createGetQuoteResponse(GetQuoteResponse value) {
        return new JAXBElement<GetQuoteResponse>(_GetQuoteResponse_QNAME, GetQuoteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryExpressTransportPayMoney }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.external.giifiCalc.transport.gistandard.com/", name = "QueryExpressTransportPayMoney")
    public JAXBElement<QueryExpressTransportPayMoney> createQueryExpressTransportPayMoney(QueryExpressTransportPayMoney value) {
        return new JAXBElement<QueryExpressTransportPayMoney>(_QueryExpressTransportPayMoney_QNAME, QueryExpressTransportPayMoney.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryExpressTransportPayMoneyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.external.giifiCalc.transport.gistandard.com/", name = "QueryExpressTransportPayMoneyResponse")
    public JAXBElement<QueryExpressTransportPayMoneyResponse> createQueryExpressTransportPayMoneyResponse(QueryExpressTransportPayMoneyResponse value) {
        return new JAXBElement<QueryExpressTransportPayMoneyResponse>(_QueryExpressTransportPayMoneyResponse_QNAME, QueryExpressTransportPayMoneyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.external.giifiCalc.transport.gistandard.com/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

}
