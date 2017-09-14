
package com.gistandard.transport.calculate.webservice.generalAcctService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gistandard.transport.calculate.webservice.generalAcctService package. 
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

    private final static QName _ConfirmPay_QNAME = new QName("http://server.webservice.gistandard.com/", "confirmPay");
    private final static QName _ConfirmPayAtMS_QNAME = new QName("http://server.webservice.gistandard.com/", "confirmPayAtMS");
    private final static QName _ConfirmPayAtMSResponse_QNAME = new QName("http://server.webservice.gistandard.com/", "confirmPayAtMSResponse");
    private final static QName _ConfirmPayResponse_QNAME = new QName("http://server.webservice.gistandard.com/", "confirmPayResponse");
    private final static QName _ConfirmPayment_QNAME = new QName("http://server.webservice.gistandard.com/", "confirmPayment");
    private final static QName _ConfirmPayment1_QNAME = new QName("http://server.webservice.gistandard.com/", "confirmPayment1");
    private final static QName _ConfirmPayment1Response_QNAME = new QName("http://server.webservice.gistandard.com/", "confirmPayment1Response");
    private final static QName _ConfirmPaymentResponse_QNAME = new QName("http://server.webservice.gistandard.com/", "confirmPaymentResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gistandard.transport.calculate.webservice.generalAcctService
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConfirmPay }
     * 
     */
    public ConfirmPay createConfirmPay() {
        return new ConfirmPay();
    }

    /**
     * Create an instance of {@link ConfirmPayAtMS }
     * 
     */
    public ConfirmPayAtMS createConfirmPayAtMS() {
        return new ConfirmPayAtMS();
    }

    /**
     * Create an instance of {@link ConfirmPayAtMSResponse }
     * 
     */
    public ConfirmPayAtMSResponse createConfirmPayAtMSResponse() {
        return new ConfirmPayAtMSResponse();
    }

    /**
     * Create an instance of {@link ConfirmPayResponse }
     * 
     */
    public ConfirmPayResponse createConfirmPayResponse() {
        return new ConfirmPayResponse();
    }

    /**
     * Create an instance of {@link ConfirmPayment }
     * 
     */
    public ConfirmPayment createConfirmPayment() {
        return new ConfirmPayment();
    }

    /**
     * Create an instance of {@link ConfirmPayment1 }
     * 
     */
    public ConfirmPayment1 createConfirmPayment1() {
        return new ConfirmPayment1();
    }

    /**
     * Create an instance of {@link ConfirmPayment1Response }
     * 
     */
    public ConfirmPayment1Response createConfirmPayment1Response() {
        return new ConfirmPayment1Response();
    }

    /**
     * Create an instance of {@link ConfirmPaymentResponse }
     * 
     */
    public ConfirmPaymentResponse createConfirmPaymentResponse() {
        return new ConfirmPaymentResponse();
    }

    /**
     * Create an instance of {@link OrderConfirmPayBean }
     * 
     */
    public OrderConfirmPayBean createOrderConfirmPayBean() {
        return new OrderConfirmPayBean();
    }

    /**
     * Create an instance of {@link ConfirmPayOrderInfoBean }
     * 
     */
    public ConfirmPayOrderInfoBean createConfirmPayOrderInfoBean() {
        return new ConfirmPayOrderInfoBean();
    }

    /**
     * Create an instance of {@link BaseOrderInfoBean }
     * 
     */
    public BaseOrderInfoBean createBaseOrderInfoBean() {
        return new BaseOrderInfoBean();
    }

    /**
     * Create an instance of {@link ConfirmPayRequestBean }
     * 
     */
    public ConfirmPayRequestBean createConfirmPayRequestBean() {
        return new ConfirmPayRequestBean();
    }

    /**
     * Create an instance of {@link GatrxMstRequestBean }
     * 
     */
    public GatrxMstRequestBean createGatrxMstRequestBean() {
        return new GatrxMstRequestBean();
    }

    /**
     * Create an instance of {@link DivideConnectWithAmount }
     * 
     */
    public DivideConnectWithAmount createDivideConnectWithAmount() {
        return new DivideConnectWithAmount();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmPay }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.gistandard.com/", name = "confirmPay")
    public JAXBElement<ConfirmPay> createConfirmPay(ConfirmPay value) {
        return new JAXBElement<ConfirmPay>(_ConfirmPay_QNAME, ConfirmPay.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmPayAtMS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.gistandard.com/", name = "confirmPayAtMS")
    public JAXBElement<ConfirmPayAtMS> createConfirmPayAtMS(ConfirmPayAtMS value) {
        return new JAXBElement<ConfirmPayAtMS>(_ConfirmPayAtMS_QNAME, ConfirmPayAtMS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmPayAtMSResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.gistandard.com/", name = "confirmPayAtMSResponse")
    public JAXBElement<ConfirmPayAtMSResponse> createConfirmPayAtMSResponse(ConfirmPayAtMSResponse value) {
        return new JAXBElement<ConfirmPayAtMSResponse>(_ConfirmPayAtMSResponse_QNAME, ConfirmPayAtMSResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmPayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.gistandard.com/", name = "confirmPayResponse")
    public JAXBElement<ConfirmPayResponse> createConfirmPayResponse(ConfirmPayResponse value) {
        return new JAXBElement<ConfirmPayResponse>(_ConfirmPayResponse_QNAME, ConfirmPayResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmPayment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.gistandard.com/", name = "confirmPayment")
    public JAXBElement<ConfirmPayment> createConfirmPayment(ConfirmPayment value) {
        return new JAXBElement<ConfirmPayment>(_ConfirmPayment_QNAME, ConfirmPayment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmPayment1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.gistandard.com/", name = "confirmPayment1")
    public JAXBElement<ConfirmPayment1> createConfirmPayment1(ConfirmPayment1 value) {
        return new JAXBElement<ConfirmPayment1>(_ConfirmPayment1_QNAME, ConfirmPayment1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmPayment1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.gistandard.com/", name = "confirmPayment1Response")
    public JAXBElement<ConfirmPayment1Response> createConfirmPayment1Response(ConfirmPayment1Response value) {
        return new JAXBElement<ConfirmPayment1Response>(_ConfirmPayment1Response_QNAME, ConfirmPayment1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmPaymentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.gistandard.com/", name = "confirmPaymentResponse")
    public JAXBElement<ConfirmPaymentResponse> createConfirmPaymentResponse(ConfirmPaymentResponse value) {
        return new JAXBElement<ConfirmPaymentResponse>(_ConfirmPaymentResponse_QNAME, ConfirmPaymentResponse.class, null, value);
    }

}
