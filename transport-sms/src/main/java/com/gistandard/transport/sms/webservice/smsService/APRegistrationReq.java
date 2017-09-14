
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
 *         &lt;element name="Apid" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="APPid" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="HostIP" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="MessageNotification" type="{http://www.csapi.org/schema/common/v2_0}MessageNotificationType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="APWSURI" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
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
    "apid",
    "apPid",
    "hostIP",
    "messageNotification",
    "apwsuri"
})
@XmlRootElement(name = "APRegistrationReq")
public class APRegistrationReq {

    @XmlElement(name = "Apid", required = true, nillable = true)
    protected String apid;
    @XmlElement(name = "APPid")
    protected int apPid;
    @XmlElement(name = "HostIP", required = true, nillable = true)
    protected String hostIP;
    @XmlElement(name = "MessageNotification", nillable = true)
    protected List<MessageNotificationType> messageNotification;
    @XmlElement(name = "APWSURI", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String apwsuri;

    /**
     * 获取apid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApid() {
        return apid;
    }

    /**
     * 设置apid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApid(String value) {
        this.apid = value;
    }

    /**
     * 获取apPid属性的值。
     * 
     */
    public int getAPPid() {
        return apPid;
    }

    /**
     * 设置apPid属性的值。
     * 
     */
    public void setAPPid(int value) {
        this.apPid = value;
    }

    /**
     * 获取hostIP属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostIP() {
        return hostIP;
    }

    /**
     * 设置hostIP属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostIP(String value) {
        this.hostIP = value;
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

    /**
     * 获取apwsuri属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPWSURI() {
        return apwsuri;
    }

    /**
     * 设置apwsuri属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPWSURI(String value) {
        this.apwsuri = value;
    }

}
