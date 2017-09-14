
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
 *         &lt;element name="APid" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ApSvcAuthType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "aPid",
    "apSvcAuthType"
})
@XmlRootElement(name = "APSvcAuthenticReq")
public class APSvcAuthenticReq {

    @XmlElement(name = "APid", required = true)
    protected String aPid;
    @XmlElement(name = "ApSvcAuthType", required = true, nillable = true)
    protected String apSvcAuthType;

    /**
     * 获取aPid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPid() {
        return aPid;
    }

    /**
     * 设置aPid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPid(String value) {
        this.aPid = value;
    }

    /**
     * 获取apSvcAuthType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApSvcAuthType() {
        return apSvcAuthType;
    }

    /**
     * 设置apSvcAuthType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApSvcAuthType(String value) {
        this.apSvcAuthType = value;
    }

}
