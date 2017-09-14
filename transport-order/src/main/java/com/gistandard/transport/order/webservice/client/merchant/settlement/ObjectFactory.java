
package com.gistandard.transport.order.webservice.client.merchant.settlement;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.valueplus.system.webservice.client.merchant.settlement package. 
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

    private final static QName _InsertPreCalc_QNAME = new QName("http://webService.external.giifiCalc.transport.gistandard.com/", "InsertPreCalc");
    private final static QName _InsertPreCalcResponse_QNAME = new QName("http://webService.external.giifiCalc.transport.gistandard.com/", "InsertPreCalcResponse");
    private final static QName _DeletePreCalc_QNAME = new QName("http://webService.external.giifiCalc.transport.gistandard.com/", "DeletePreCalc");
    private final static QName _DeletePreCalcResponse_QNAME = new QName("http://webService.external.giifiCalc.transport.gistandard.com/", "DeletePreCalcResponse");
    private final static QName _Exception_QNAME = new QName("http://webService.external.giifiCalc.transport.gistandard.com/", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.valueplus.system.webservice.client.merchant.settlement
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InsertPreCalc }
     * 
     */
    public InsertPreCalc createInsertPreCalc() {
        return new InsertPreCalc();
    }

    /**
     * Create an instance of {@link InsertPreCalcResponse }
     * 
     */
    public InsertPreCalcResponse createInsertPreCalcResponse() {
        return new InsertPreCalcResponse();
    }


    /**
     * Create an instance of {@link InsertPreCalc }
     *
     */
    public DeletePreCalc createDeletePreCalc() {
        return new DeletePreCalc();
    }

    /**
     * Create an instance of {@link InsertPreCalcResponse }
     *
     */
    public DeletePreCalcResponse createDeletePreCalcResponse() {
        return new DeletePreCalcResponse();
    }


    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link PreCalcModel }
     * 
     */
    public PreCalcModel createPreCalcModel() {
        return new PreCalcModel();
    }

    /**
     * Create an instance of {@link ReturnResultData }
     * 
     */
    public ReturnResultData createReturnResultData() {
        return new ReturnResultData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertPreCalc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.external.giifiCalc.transport.gistandard.com/", name = "InsertPreCalc")
    public JAXBElement<InsertPreCalc> createInsertPreCalc(InsertPreCalc value) {
        return new JAXBElement<InsertPreCalc>(_InsertPreCalc_QNAME, InsertPreCalc.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertPreCalcResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webService.external.giifiCalc.transport.gistandard.com/", name = "InsertPreCalcResponse")
    public JAXBElement<InsertPreCalcResponse> createInsertPreCalcResponse(InsertPreCalcResponse value) {
        return new JAXBElement<InsertPreCalcResponse>(_InsertPreCalcResponse_QNAME, InsertPreCalcResponse.class, null, value);
    }


    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertPreCalc }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://webService.external.giifiCalc.transport.gistandard.com/", name = "DeletePreCalc")
    public JAXBElement<DeletePreCalc> createDeletePreCalc(DeletePreCalc value) {
        return new JAXBElement<DeletePreCalc>(_DeletePreCalc_QNAME, DeletePreCalc.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertPreCalcResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://webService.external.giifiCalc.transport.gistandard.com/", name = "DeletePreCalcResponse")
    public JAXBElement<DeletePreCalcResponse> createDeletePreCalcResponse(DeletePreCalcResponse value) {
        return new JAXBElement<DeletePreCalcResponse>(_DeletePreCalcResponse_QNAME, DeletePreCalcResponse.class, null, value);
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
