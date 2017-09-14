
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.valueplus.system.webservice.client.smsService package. 
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

    private final static QName _PolicyException_QNAME = new QName("http://www.csapi.org/schema/common/v2_0", "PolicyException");
    private final static QName _ServiceException_QNAME = new QName("http://www.csapi.org/schema/common/v2_0", "ServiceException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.valueplus.system.webservice.client.smsService
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PolicyException }
     * 
     */
    public PolicyException createPolicyException() {
        return new PolicyException();
    }

    /**
     * Create an instance of {@link ServiceException }
     * 
     */
    public ServiceException createServiceException() {
        return new ServiceException();
    }

    /**
     * Create an instance of {@link MessageNotificationType }
     * 
     */
    public MessageNotificationType createMessageNotificationType() {
        return new MessageNotificationType();
    }

    /**
     * Create an instance of {@link TimeMetric }
     * 
     */
    public TimeMetric createTimeMetric() {
        return new TimeMetric();
    }

    /**
     * Create an instance of {@link ServiceError }
     * 
     */
    public ServiceError createServiceError() {
        return new ServiceError();
    }

    /**
     * Create an instance of {@link APRegistrationReq }
     * 
     */
    public APRegistrationReq createAPRegistrationReq() {
        return new APRegistrationReq();
    }

    /**
     * Create an instance of {@link APRegistrationRsp }
     * 
     */
    public APRegistrationRsp createAPRegistrationRsp() {
        return new APRegistrationRsp();
    }

    /**
     * Create an instance of {@link APStatusRepReq }
     * 
     */
    public APStatusRepReq createAPStatusRepReq() {
        return new APStatusRepReq();
    }

    /**
     * Create an instance of {@link APStatusRepRsp }
     * 
     */
    public APStatusRepRsp createAPStatusRepRsp() {
        return new APStatusRepRsp();
    }

    /**
     * Create an instance of {@link APLogOutReq }
     * 
     */
    public APLogOutReq createAPLogOutReq() {
        return new APLogOutReq();
    }

    /**
     * Create an instance of {@link APLogOutRsp }
     * 
     */
    public APLogOutRsp createAPLogOutRsp() {
        return new APLogOutRsp();
    }

    /**
     * Create an instance of {@link PauseAPReq }
     * 
     */
    public PauseAPReq createPauseAPReq() {
        return new PauseAPReq();
    }

    /**
     * Create an instance of {@link PauseAPRsp }
     * 
     */
    public PauseAPRsp createPauseAPRsp() {
        return new PauseAPRsp();
    }

    /**
     * Create an instance of {@link RecoveryAPReq }
     * 
     */
    public RecoveryAPReq createRecoveryAPReq() {
        return new RecoveryAPReq();
    }

    /**
     * Create an instance of {@link RecoveryAPRsp }
     * 
     */
    public RecoveryAPRsp createRecoveryAPRsp() {
        return new RecoveryAPRsp();
    }

    /**
     * Create an instance of {@link APSvcAuthenticReq }
     * 
     */
    public APSvcAuthenticReq createAPSvcAuthenticReq() {
        return new APSvcAuthenticReq();
    }

    /**
     * Create an instance of {@link APSvcAuthenticRsp }
     * 
     */
    public APSvcAuthenticRsp createAPSvcAuthenticRsp() {
        return new APSvcAuthenticRsp();
    }

    /**
     * Create an instance of {@link APSvcPerfCmdReq }
     * 
     */
    public APSvcPerfCmdReq createAPSvcPerfCmdReq() {
        return new APSvcPerfCmdReq();
    }

    /**
     * Create an instance of {@link AlarmReq }
     * 
     */
    public AlarmReq createAlarmReq() {
        return new AlarmReq();
    }

    /**
     * Create an instance of {@link AlarmRsp }
     * 
     */
    public AlarmRsp createAlarmRsp() {
        return new AlarmRsp();
    }

    /**
     * Create an instance of {@link APSvcPerfReportReq }
     * 
     */
    public APSvcPerfReportReq createAPSvcPerfReportReq() {
        return new APSvcPerfReportReq();
    }

    /**
     * Create an instance of {@link StartNotificationRequest }
     * 
     */
    public StartNotificationRequest createStartNotificationRequest() {
        return new StartNotificationRequest();
    }

    /**
     * Create an instance of {@link StopNotificationRequest }
     * 
     */
    public StopNotificationRequest createStopNotificationRequest() {
        return new StopNotificationRequest();
    }

    /**
     * Create an instance of {@link SendSmsRequest }
     * 
     */
    public SendSmsRequest createSendSmsRequest() {
        return new SendSmsRequest();
    }

    /**
     * Create an instance of {@link SendSmsResponse }
     * 
     */
    public SendSmsResponse createSendSmsResponse() {
        return new SendSmsResponse();
    }

    /**
     * Create an instance of {@link NotifySmsDeliveryStatusRequest }
     * 
     */
    public NotifySmsDeliveryStatusRequest createNotifySmsDeliveryStatusRequest() {
        return new NotifySmsDeliveryStatusRequest();
    }

    /**
     * Create an instance of {@link DeliveryInformation }
     * 
     */
    public DeliveryInformation createDeliveryInformation() {
        return new DeliveryInformation();
    }

    /**
     * Create an instance of {@link GetReceivedSmsRequest }
     * 
     */
    public GetReceivedSmsRequest createGetReceivedSmsRequest() {
        return new GetReceivedSmsRequest();
    }

    /**
     * Create an instance of {@link GetReceivedSmsResponse }
     * 
     */
    public GetReceivedSmsResponse createGetReceivedSmsResponse() {
        return new GetReceivedSmsResponse();
    }

    /**
     * Create an instance of {@link SMSMessage }
     * 
     */
    public SMSMessage createSMSMessage() {
        return new SMSMessage();
    }

    /**
     * Create an instance of {@link GetSmsDeliveryStatusRequest }
     * 
     */
    public GetSmsDeliveryStatusRequest createGetSmsDeliveryStatusRequest() {
        return new GetSmsDeliveryStatusRequest();
    }

    /**
     * Create an instance of {@link GetSmsDeliveryStatusResponse }
     * 
     */
    public GetSmsDeliveryStatusResponse createGetSmsDeliveryStatusResponse() {
        return new GetSmsDeliveryStatusResponse();
    }

    /**
     * Create an instance of {@link NotifySmsReceptionRequest }
     * 
     */
    public NotifySmsReceptionRequest createNotifySmsReceptionRequest() {
        return new NotifySmsReceptionRequest();
    }

    /**
     * Create an instance of {@link SendMessageRequest }
     * 
     */
    public SendMessageRequest createSendMessageRequest() {
        return new SendMessageRequest();
    }

    /**
     * Create an instance of {@link SendMessageResponse }
     * 
     */
    public SendMessageResponse createSendMessageResponse() {
        return new SendMessageResponse();
    }

    /**
     * Create an instance of {@link GetMessageDeliveryStatusRequest }
     * 
     */
    public GetMessageDeliveryStatusRequest createGetMessageDeliveryStatusRequest() {
        return new GetMessageDeliveryStatusRequest();
    }

    /**
     * Create an instance of {@link GetMessageDeliveryStatusResponse }
     * 
     */
    public GetMessageDeliveryStatusResponse createGetMessageDeliveryStatusResponse() {
        return new GetMessageDeliveryStatusResponse();
    }

    /**
     * Create an instance of {@link DeliveryInformation2 }
     * 
     */
    public DeliveryInformation2 createDeliveryInformation2() {
        return new DeliveryInformation2();
    }

    /**
     * Create an instance of {@link GetReceivedMessagesRequest }
     * 
     */
    public GetReceivedMessagesRequest createGetReceivedMessagesRequest() {
        return new GetReceivedMessagesRequest();
    }

    /**
     * Create an instance of {@link GetReceivedMessagesResponse }
     * 
     */
    public GetReceivedMessagesResponse createGetReceivedMessagesResponse() {
        return new GetReceivedMessagesResponse();
    }

    /**
     * Create an instance of {@link MessageReference }
     * 
     */
    public MessageReference createMessageReference() {
        return new MessageReference();
    }

    /**
     * Create an instance of {@link GetMessageRequest }
     * 
     */
    public GetMessageRequest createGetMessageRequest() {
        return new GetMessageRequest();
    }

    /**
     * Create an instance of {@link GetMessageResponse }
     * 
     */
    public GetMessageResponse createGetMessageResponse() {
        return new GetMessageResponse();
    }

    /**
     * Create an instance of {@link MmsMessage }
     * 
     */
    public MmsMessage createMmsMessage() {
        return new MmsMessage();
    }

    /**
     * Create an instance of {@link NotifyMessageReceptionRequest }
     * 
     */
    public NotifyMessageReceptionRequest createNotifyMessageReceptionRequest() {
        return new NotifyMessageReceptionRequest();
    }

    /**
     * Create an instance of {@link NotifyMessageDeliveryReceiptRequest }
     * 
     */
    public NotifyMessageDeliveryReceiptRequest createNotifyMessageDeliveryReceiptRequest() {
        return new NotifyMessageDeliveryReceiptRequest();
    }

    /**
     * Create an instance of {@link GetLocationRequest }
     * 
     */
    public GetLocationRequest createGetLocationRequest() {
        return new GetLocationRequest();
    }

    /**
     * Create an instance of {@link CoordinateReferenceSystem }
     * 
     */
    public CoordinateReferenceSystem createCoordinateReferenceSystem() {
        return new CoordinateReferenceSystem();
    }

    /**
     * Create an instance of {@link GetLocationResponse }
     * 
     */
    public GetLocationResponse createGetLocationResponse() {
        return new GetLocationResponse();
    }

    /**
     * Create an instance of {@link LocationInfo }
     * 
     */
    public LocationInfo createLocationInfo() {
        return new LocationInfo();
    }

    /**
     * Create an instance of {@link GetLocationForGroupRequest }
     * 
     */
    public GetLocationForGroupRequest createGetLocationForGroupRequest() {
        return new GetLocationForGroupRequest();
    }

    /**
     * Create an instance of {@link GetLocationForGroupResponse }
     * 
     */
    public GetLocationForGroupResponse createGetLocationForGroupResponse() {
        return new GetLocationForGroupResponse();
    }

    /**
     * Create an instance of {@link LocationData }
     * 
     */
    public LocationData createLocationData() {
        return new LocationData();
    }

    /**
     * Create an instance of {@link StartPeriodicNotificationRequest }
     * 
     */
    public StartPeriodicNotificationRequest createStartPeriodicNotificationRequest() {
        return new StartPeriodicNotificationRequest();
    }

    /**
     * Create an instance of {@link StartPeriodicNotificationResponse }
     * 
     */
    public StartPeriodicNotificationResponse createStartPeriodicNotificationResponse() {
        return new StartPeriodicNotificationResponse();
    }

    /**
     * Create an instance of {@link EndNotificationRequest }
     * 
     */
    public EndNotificationRequest createEndNotificationRequest() {
        return new EndNotificationRequest();
    }

    /**
     * Create an instance of {@link LocationNotificationRequest }
     * 
     */
    public LocationNotificationRequest createLocationNotificationRequest() {
        return new LocationNotificationRequest();
    }

    /**
     * Create an instance of {@link LocationErrorRequest }
     * 
     */
    public LocationErrorRequest createLocationErrorRequest() {
        return new LocationErrorRequest();
    }

    /**
     * Create an instance of {@link LocationEndRequest }
     * 
     */
    public LocationEndRequest createLocationEndRequest() {
        return new LocationEndRequest();
    }

    /**
     * Create an instance of {@link SendPushRequest }
     * 
     */
    public SendPushRequest createSendPushRequest() {
        return new SendPushRequest();
    }

    /**
     * Create an instance of {@link SendPushResponse }
     * 
     */
    public SendPushResponse createSendPushResponse() {
        return new SendPushResponse();
    }

    /**
     * Create an instance of {@link GetPushDeliveryStatusRequest }
     * 
     */
    public GetPushDeliveryStatusRequest createGetPushDeliveryStatusRequest() {
        return new GetPushDeliveryStatusRequest();
    }

    /**
     * Create an instance of {@link GetPushDeliveryStatusResponse }
     * 
     */
    public GetPushDeliveryStatusResponse createGetPushDeliveryStatusResponse() {
        return new GetPushDeliveryStatusResponse();
    }

    /**
     * Create an instance of {@link DeliveryInformation3 }
     * 
     */
    public DeliveryInformation3 createDeliveryInformation3() {
        return new DeliveryInformation3();
    }

    /**
     * Create an instance of {@link NotifyPushDeliveryReceiptRequest }
     * 
     */
    public NotifyPushDeliveryReceiptRequest createNotifyPushDeliveryReceiptRequest() {
        return new NotifyPushDeliveryReceiptRequest();
    }

    /**
     * Create an instance of {@link MakeUssdRequest }
     * 
     */
    public MakeUssdRequest createMakeUssdRequest() {
        return new MakeUssdRequest();
    }

    /**
     * Create an instance of {@link UssdArray }
     * 
     */
    public UssdArray createUssdArray() {
        return new UssdArray();
    }

    /**
     * Create an instance of {@link MakeUssdResponse }
     * 
     */
    public MakeUssdResponse createMakeUssdResponse() {
        return new MakeUssdResponse();
    }

    /**
     * Create an instance of {@link HandleUssdRequest }
     * 
     */
    public HandleUssdRequest createHandleUssdRequest() {
        return new HandleUssdRequest();
    }

    /**
     * Create an instance of {@link HandleUssdResponse }
     * 
     */
    public HandleUssdResponse createHandleUssdResponse() {
        return new HandleUssdResponse();
    }

    /**
     * Create an instance of {@link UssdContinueRequest }
     * 
     */
    public UssdContinueRequest createUssdContinueRequest() {
        return new UssdContinueRequest();
    }

    /**
     * Create an instance of {@link UssdContinueResponse }
     * 
     */
    public UssdContinueResponse createUssdContinueResponse() {
        return new UssdContinueResponse();
    }

    /**
     * Create an instance of {@link EndUssdRequest }
     * 
     */
    public EndUssdRequest createEndUssdRequest() {
        return new EndUssdRequest();
    }

    /**
     * Create an instance of {@link NotifyUssdEndRequest }
     * 
     */
    public NotifyUssdEndRequest createNotifyUssdEndRequest() {
        return new NotifyUssdEndRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolicyException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csapi.org/schema/common/v2_0", name = "PolicyException")
    public JAXBElement<PolicyException> createPolicyException(PolicyException value) {
        return new JAXBElement<PolicyException>(_PolicyException_QNAME, PolicyException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.csapi.org/schema/common/v2_0", name = "ServiceException")
    public JAXBElement<ServiceException> createServiceException(ServiceException value) {
        return new JAXBElement<ServiceException>(_ServiceException_QNAME, ServiceException.class, null, value);
    }

}
