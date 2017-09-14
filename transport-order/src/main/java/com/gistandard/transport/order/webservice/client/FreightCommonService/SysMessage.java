
package com.gistandard.transport.order.webservice.client.FreightCommonService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SysMessage complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SysMessage"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="errorMsg" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="errorType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="resultStatus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SysMessage", propOrder = {
    "errorCode",
    "errorMsg",
    "errorType",
    "resultStatus"
})
public class SysMessage {

    @XmlElement(required = true, nillable = true)
    protected String errorCode;
    @XmlElement(required = true, nillable = true)
    protected String errorMsg;
    @XmlElement(required = true, nillable = true)
    protected String errorType;
    @XmlElement(required = true, nillable = true)
    protected String resultStatus;

    /**
     * 获取errorCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 设置errorCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * 获取errorMsg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 设置errorMsg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMsg(String value) {
        this.errorMsg = value;
    }

    /**
     * 获取errorType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorType() {
        return errorType;
    }

    /**
     * 设置errorType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorType(String value) {
        this.errorType = value;
    }

    /**
     * 获取resultStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultStatus() {
        return resultStatus;
    }

    /**
     * 设置resultStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultStatus(String value) {
        this.resultStatus = value;
    }

}
