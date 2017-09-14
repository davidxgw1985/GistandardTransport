
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="alarmId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="orgseverity" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="orgtype" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="probablecause" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="eventTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ackTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="clearTime" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="activestatus" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="alarmtitle" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="alarmText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "alarmId",
    "orgseverity",
    "orgtype",
    "probablecause",
    "eventTime",
    "ackTime",
    "clearTime",
    "activestatus",
    "alarmtitle",
    "alarmText"
})
@XmlRootElement(name = "AlarmReq")
public class AlarmReq {

    @XmlElement(required = true)
    protected String alarmId;
    protected int orgseverity;
    protected int orgtype;
    @XmlElement(required = true)
    protected String probablecause;
    @XmlElement(required = true)
    protected String eventTime;
    @XmlElement(required = true)
    protected String ackTime;
    @XmlElement(required = true)
    protected String clearTime;
    protected int activestatus;
    @XmlElement(required = true)
    protected String alarmtitle;
    @XmlElement(required = true)
    protected String alarmText;

    /**
     * 获取alarmId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmId() {
        return alarmId;
    }

    /**
     * 设置alarmId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmId(String value) {
        this.alarmId = value;
    }

    /**
     * 获取orgseverity属性的值。
     * 
     */
    public int getOrgseverity() {
        return orgseverity;
    }

    /**
     * 设置orgseverity属性的值。
     * 
     */
    public void setOrgseverity(int value) {
        this.orgseverity = value;
    }

    /**
     * 获取orgtype属性的值。
     * 
     */
    public int getOrgtype() {
        return orgtype;
    }

    /**
     * 设置orgtype属性的值。
     * 
     */
    public void setOrgtype(int value) {
        this.orgtype = value;
    }

    /**
     * 获取probablecause属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProbablecause() {
        return probablecause;
    }

    /**
     * 设置probablecause属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProbablecause(String value) {
        this.probablecause = value;
    }

    /**
     * 获取eventTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * 设置eventTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventTime(String value) {
        this.eventTime = value;
    }

    /**
     * 获取ackTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAckTime() {
        return ackTime;
    }

    /**
     * 设置ackTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAckTime(String value) {
        this.ackTime = value;
    }

    /**
     * 获取clearTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearTime() {
        return clearTime;
    }

    /**
     * 设置clearTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearTime(String value) {
        this.clearTime = value;
    }

    /**
     * 获取activestatus属性的值。
     * 
     */
    public int getActivestatus() {
        return activestatus;
    }

    /**
     * 设置activestatus属性的值。
     * 
     */
    public void setActivestatus(int value) {
        this.activestatus = value;
    }

    /**
     * 获取alarmtitle属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmtitle() {
        return alarmtitle;
    }

    /**
     * 设置alarmtitle属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmtitle(String value) {
        this.alarmtitle = value;
    }

    /**
     * 获取alarmText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmText() {
        return alarmText;
    }

    /**
     * 设置alarmText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmText(String value) {
        this.alarmText = value;
    }

}
