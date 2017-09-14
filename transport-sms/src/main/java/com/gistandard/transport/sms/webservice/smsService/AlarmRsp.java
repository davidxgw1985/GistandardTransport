
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
 *         &lt;element name="recode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="reMsg" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "recode",
    "reMsg"
})
@XmlRootElement(name = "AlarmRsp")
public class AlarmRsp {

    @XmlElement(required = true)
    protected String recode;
    @XmlElement(required = true)
    protected String reMsg;

    /**
     * 获取recode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecode() {
        return recode;
    }

    /**
     * 设置recode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecode(String value) {
        this.recode = value;
    }

    /**
     * 获取reMsg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReMsg() {
        return reMsg;
    }

    /**
     * 设置reMsg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReMsg(String value) {
        this.reMsg = value;
    }

}
