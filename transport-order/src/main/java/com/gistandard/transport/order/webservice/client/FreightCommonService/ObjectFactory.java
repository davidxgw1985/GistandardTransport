
package com.gistandard.transport.order.webservice.client.FreightCommonService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gistandard.transport.order.webservice.client.FreightCommonService package. 
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

    private final static QName _Fault_QNAME = new QName("http://wss.cpic.com.cn/propertyinsurance/commonservice/freight", "fault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gistandard.transport.order.webservice.client.FreightCommonService
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Approval }
     * 
     */
    public Approval createApproval() {
        return new Approval();
    }

    /**
     * Create an instance of {@link ApprovalRequest }
     * 
     */
    public ApprovalRequest createApprovalRequest() {
        return new ApprovalRequest();
    }

    /**
     * Create an instance of {@link ApprovalResponse }
     * 
     */
    public ApprovalResponse createApprovalResponse() {
        return new ApprovalResponse();
    }

    /**
     * Create an instance of {@link ApprovalResponse2 }
     * 
     */
    public ApprovalResponse2 createApprovalResponse2() {
        return new ApprovalResponse2();
    }

    /**
     * Create an instance of {@link QueryPolicyStatus }
     * 
     */
    public QueryPolicyStatus createQueryPolicyStatus() {
        return new QueryPolicyStatus();
    }

    /**
     * Create an instance of {@link QueryPolicyStatusResponse }
     * 
     */
    public QueryPolicyStatusResponse createQueryPolicyStatusResponse() {
        return new QueryPolicyStatusResponse();
    }

    /**
     * Create an instance of {@link Endorse }
     * 
     */
    public Endorse createEndorse() {
        return new Endorse();
    }

    /**
     * Create an instance of {@link EndorseRequest }
     * 
     */
    public EndorseRequest createEndorseRequest() {
        return new EndorseRequest();
    }

    /**
     * Create an instance of {@link EndorseResponse }
     * 
     */
    public EndorseResponse createEndorseResponse() {
        return new EndorseResponse();
    }

    /**
     * Create an instance of {@link EndorseResponse2 }
     * 
     */
    public EndorseResponse2 createEndorseResponse2() {
        return new EndorseResponse2();
    }

    /**
     * Create an instance of {@link DoIssuedPaymentPolicy }
     * 
     */
    public DoIssuedPaymentPolicy createDoIssuedPaymentPolicy() {
        return new DoIssuedPaymentPolicy();
    }

    /**
     * Create an instance of {@link DoIssuedPaymentPolicyResponse }
     * 
     */
    public DoIssuedPaymentPolicyResponse createDoIssuedPaymentPolicyResponse() {
        return new DoIssuedPaymentPolicyResponse();
    }

    /**
     * Create an instance of {@link DoQueryBasicData }
     * 
     */
    public DoQueryBasicData createDoQueryBasicData() {
        return new DoQueryBasicData();
    }

    /**
     * Create an instance of {@link DoQueryBasicDataResponse }
     * 
     */
    public DoQueryBasicDataResponse createDoQueryBasicDataResponse() {
        return new DoQueryBasicDataResponse();
    }

    /**
     * Create an instance of {@link PostApplication }
     * 
     */
    public PostApplication createPostApplication() {
        return new PostApplication();
    }

    /**
     * Create an instance of {@link PostApplicationResponse }
     * 
     */
    public PostApplicationResponse createPostApplicationResponse() {
        return new PostApplicationResponse();
    }

    /**
     * Create an instance of {@link QueryApplicationStatus }
     * 
     */
    public QueryApplicationStatus createQueryApplicationStatus() {
        return new QueryApplicationStatus();
    }

    /**
     * Create an instance of {@link QueryApplicationStatusResponse }
     * 
     */
    public QueryApplicationStatusResponse createQueryApplicationStatusResponse() {
        return new QueryApplicationStatusResponse();
    }

    /**
     * Create an instance of {@link CancelApplication }
     * 
     */
    public CancelApplication createCancelApplication() {
        return new CancelApplication();
    }

    /**
     * Create an instance of {@link CancelApplicationResponse }
     * 
     */
    public CancelApplicationResponse createCancelApplicationResponse() {
        return new CancelApplicationResponse();
    }

    /**
     * Create an instance of {@link DoClaimSettl }
     * 
     */
    public DoClaimSettl createDoClaimSettl() {
        return new DoClaimSettl();
    }

    /**
     * Create an instance of {@link DoClaimSettlResponse }
     * 
     */
    public DoClaimSettlResponse createDoClaimSettlResponse() {
        return new DoClaimSettlResponse();
    }

    /**
     * Create an instance of {@link ClaimReportInfo }
     * 
     */
    public ClaimReportInfo createClaimReportInfo() {
        return new ClaimReportInfo();
    }

    /**
     * Create an instance of {@link ClaimReportInfoResponse }
     * 
     */
    public ClaimReportInfoResponse createClaimReportInfoResponse() {
        return new ClaimReportInfoResponse();
    }

    /**
     * Create an instance of {@link ApprovalProduct }
     * 
     */
    public ApprovalProduct createApprovalProduct() {
        return new ApprovalProduct();
    }

    /**
     * Create an instance of {@link LoginUser }
     * 
     */
    public LoginUser createLoginUser() {
        return new LoginUser();
    }

    /**
     * Create an instance of {@link SysMessage }
     * 
     */
    public SysMessage createSysMessage() {
        return new SysMessage();
    }

    /**
     * Create an instance of {@link WssException }
     * 
     */
    public WssException createWssException() {
        return new WssException();
    }

    /**
     * Create an instance of {@link EndorseType }
     * 
     */
    public EndorseType createEndorseType() {
        return new EndorseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WssException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wss.cpic.com.cn/propertyinsurance/commonservice/freight", name = "fault")
    public JAXBElement<WssException> createFault(WssException value) {
        return new JAXBElement<WssException>(_Fault_QNAME, WssException.class, null, value);
    }

}
