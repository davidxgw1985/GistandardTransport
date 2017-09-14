
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ApplicationId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="MessageNotification" type="{http://www.csapi.org/schema/common/v2_0}MessageNotificationType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "applicationId",
    "messageNotification"
})
@XmlRootElement(name = "startNotificationRequest", namespace = "http://www.csapi.org/schema/notification")
public class StartNotificationRequest {

    @XmlElement(name = "ApplicationId", required = true, nillable = true)
    protected String applicationId;
    @XmlElement(name = "MessageNotification", nillable = true)
    protected List<MessageNotificationType> messageNotification;

    /**
     * 获取applicationId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * 设置applicationId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationId(String value) {
        this.applicationId = value;
    }

    /**
     * Gets the value of the messageNotification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageNotification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageNotification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageNotificationType }
     * 
     * 
     */
    public List<MessageNotificationType> getMessageNotification() {
        if (messageNotification == null) {
            messageNotification = new ArrayList<MessageNotificationType>();
        }
        return this.messageNotification;
    }

}
