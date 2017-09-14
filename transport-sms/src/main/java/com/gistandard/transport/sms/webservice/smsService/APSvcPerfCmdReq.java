
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
 *         &lt;element name="ApSvcPerfCmdType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "apSvcPerfCmdType"
})
@XmlRootElement(name = "APSvcPerfCmdReq")
public class APSvcPerfCmdReq {

    @XmlElement(name = "APid", required = true)
    protected String aPid;
    @XmlElement(name = "ApSvcPerfCmdType", required = true, nillable = true)
    protected String apSvcPerfCmdType;

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
     * 获取apSvcPerfCmdType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApSvcPerfCmdType() {
        return apSvcPerfCmdType;
    }

    /**
     * 设置apSvcPerfCmdType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApSvcPerfCmdType(String value) {
        this.apSvcPerfCmdType = value;
    }

}
