
package com.gistandard.transport.order.webservice.client.order.transport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.valueplus.system.webservice.client.hub.order.transport package. 
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

    private final static QName _ComfirmedBroadcastOrder_QNAME = new QName("http://service.server.WebService.HubPadServer.gsct.com/", "comfirmedBroadcastOrder");
    private final static QName _ComfirmedBroadcastOrderResponse_QNAME = new QName("http://service.server.WebService.HubPadServer.gsct.com/", "comfirmedBroadcastOrderResponse");
    private final static QName _EmergentSchedu_QNAME = new QName("http://service.server.WebService.HubPadServer.gsct.com/", "emergentSchedu");
    private final static QName _EmergentScheduResponse_QNAME = new QName("http://service.server.WebService.HubPadServer.gsct.com/", "emergentScheduResponse");
    private final static QName _QueryBroadcastOrderList_QNAME = new QName("http://service.server.WebService.HubPadServer.gsct.com/", "queryBroadcastOrderList");
    private final static QName _QueryBroadcastOrderListResponse_QNAME = new QName("http://service.server.WebService.HubPadServer.gsct.com/", "queryBroadcastOrderListResponse");
    private final static QName _ScheduOrder_QNAME = new QName("http://service.server.WebService.HubPadServer.gsct.com/", "scheduOrder");
    private final static QName _ScheduOrderResponse_QNAME = new QName("http://service.server.WebService.HubPadServer.gsct.com/", "scheduOrderResponse");
    private final static QName _UpdateScheduCarStatus_QNAME = new QName("http://service.server.WebService.HubPadServer.gsct.com/", "updateScheduCarStatus");
    private final static QName _UpdateScheduCarStatusResponse_QNAME = new QName("http://service.server.WebService.HubPadServer.gsct.com/", "updateScheduCarStatusResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.valueplus.system.webservice.client.hub.order.transport
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ComfirmedBroadcastOrder }
     * 
     */
    public ComfirmedBroadcastOrder createComfirmedBroadcastOrder() {
        return new ComfirmedBroadcastOrder();
    }

    /**
     * Create an instance of {@link ComfirmedBroadcastOrderResponse }
     * 
     */
    public ComfirmedBroadcastOrderResponse createComfirmedBroadcastOrderResponse() {
        return new ComfirmedBroadcastOrderResponse();
    }

    /**
     * Create an instance of {@link EmergentSchedu }
     * 
     */
    public EmergentSchedu createEmergentSchedu() {
        return new EmergentSchedu();
    }

    /**
     * Create an instance of {@link EmergentScheduResponse }
     * 
     */
    public EmergentScheduResponse createEmergentScheduResponse() {
        return new EmergentScheduResponse();
    }

    /**
     * Create an instance of {@link QueryBroadcastOrderList }
     * 
     */
    public QueryBroadcastOrderList createQueryBroadcastOrderList() {
        return new QueryBroadcastOrderList();
    }

    /**
     * Create an instance of {@link QueryBroadcastOrderListResponse }
     * 
     */
    public QueryBroadcastOrderListResponse createQueryBroadcastOrderListResponse() {
        return new QueryBroadcastOrderListResponse();
    }

    /**
     * Create an instance of {@link ScheduOrder }
     * 
     */
    public ScheduOrder createScheduOrder() {
        return new ScheduOrder();
    }

    /**
     * Create an instance of {@link ScheduOrderResponse }
     * 
     */
    public ScheduOrderResponse createScheduOrderResponse() {
        return new ScheduOrderResponse();
    }

    /**
     * Create an instance of {@link UpdateScheduCarStatus }
     * 
     */
    public UpdateScheduCarStatus createUpdateScheduCarStatus() {
        return new UpdateScheduCarStatus();
    }

    /**
     * Create an instance of {@link UpdateScheduCarStatusResponse }
     * 
     */
    public UpdateScheduCarStatusResponse createUpdateScheduCarStatusResponse() {
        return new UpdateScheduCarStatusResponse();
    }

    /**
     * Create an instance of {@link BroadcastOrderQueryParam }
     * 
     */
    public BroadcastOrderQueryParam createBroadcastOrderQueryParam() {
        return new BroadcastOrderQueryParam();
    }

    /**
     * Create an instance of {@link ResultBean }
     * 
     */
    public ResultBean createResultBean() {
        return new ResultBean();
    }

    /**
     * Create an instance of {@link EmergentScheduBean }
     * 
     */
    public EmergentScheduBean createEmergentScheduBean() {
        return new EmergentScheduBean();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComfirmedBroadcastOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.WebService.HubPadServer.gsct.com/", name = "comfirmedBroadcastOrder")
    public JAXBElement<ComfirmedBroadcastOrder> createComfirmedBroadcastOrder(ComfirmedBroadcastOrder value) {
        return new JAXBElement<ComfirmedBroadcastOrder>(_ComfirmedBroadcastOrder_QNAME, ComfirmedBroadcastOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComfirmedBroadcastOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.WebService.HubPadServer.gsct.com/", name = "comfirmedBroadcastOrderResponse")
    public JAXBElement<ComfirmedBroadcastOrderResponse> createComfirmedBroadcastOrderResponse(ComfirmedBroadcastOrderResponse value) {
        return new JAXBElement<ComfirmedBroadcastOrderResponse>(_ComfirmedBroadcastOrderResponse_QNAME, ComfirmedBroadcastOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmergentSchedu }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.WebService.HubPadServer.gsct.com/", name = "emergentSchedu")
    public JAXBElement<EmergentSchedu> createEmergentSchedu(EmergentSchedu value) {
        return new JAXBElement<EmergentSchedu>(_EmergentSchedu_QNAME, EmergentSchedu.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmergentScheduResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.WebService.HubPadServer.gsct.com/", name = "emergentScheduResponse")
    public JAXBElement<EmergentScheduResponse> createEmergentScheduResponse(EmergentScheduResponse value) {
        return new JAXBElement<EmergentScheduResponse>(_EmergentScheduResponse_QNAME, EmergentScheduResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryBroadcastOrderList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.WebService.HubPadServer.gsct.com/", name = "queryBroadcastOrderList")
    public JAXBElement<QueryBroadcastOrderList> createQueryBroadcastOrderList(QueryBroadcastOrderList value) {
        return new JAXBElement<QueryBroadcastOrderList>(_QueryBroadcastOrderList_QNAME, QueryBroadcastOrderList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryBroadcastOrderListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.WebService.HubPadServer.gsct.com/", name = "queryBroadcastOrderListResponse")
    public JAXBElement<QueryBroadcastOrderListResponse> createQueryBroadcastOrderListResponse(QueryBroadcastOrderListResponse value) {
        return new JAXBElement<QueryBroadcastOrderListResponse>(_QueryBroadcastOrderListResponse_QNAME, QueryBroadcastOrderListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ScheduOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.WebService.HubPadServer.gsct.com/", name = "scheduOrder")
    public JAXBElement<ScheduOrder> createScheduOrder(ScheduOrder value) {
        return new JAXBElement<ScheduOrder>(_ScheduOrder_QNAME, ScheduOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ScheduOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.WebService.HubPadServer.gsct.com/", name = "scheduOrderResponse")
    public JAXBElement<ScheduOrderResponse> createScheduOrderResponse(ScheduOrderResponse value) {
        return new JAXBElement<ScheduOrderResponse>(_ScheduOrderResponse_QNAME, ScheduOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateScheduCarStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.WebService.HubPadServer.gsct.com/", name = "updateScheduCarStatus")
    public JAXBElement<UpdateScheduCarStatus> createUpdateScheduCarStatus(UpdateScheduCarStatus value) {
        return new JAXBElement<UpdateScheduCarStatus>(_UpdateScheduCarStatus_QNAME, UpdateScheduCarStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateScheduCarStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.WebService.HubPadServer.gsct.com/", name = "updateScheduCarStatusResponse")
    public JAXBElement<UpdateScheduCarStatusResponse> createUpdateScheduCarStatusResponse(UpdateScheduCarStatusResponse value) {
        return new JAXBElement<UpdateScheduCarStatusResponse>(_UpdateScheduCarStatusResponse_QNAME, UpdateScheduCarStatusResponse.class, null, value);
    }

}
