
package com.gistandard.transport.order.webservice.client.order.transport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>resultBean complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="resultBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="responseCode" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="responseData" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *         &lt;element name="responseMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultBean", propOrder = {
    "responseCode",
    "responseData",
    "responseMessage"
})
public class ResultBean {

    protected int responseCode;
    protected Object responseData;
    protected String responseMessage;

    /**
     * 获取responseCode属性的值。
     * 
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * 设置responseCode属性的值。
     * 
     */
    public void setResponseCode(int value) {
        this.responseCode = value;
    }

    /**
     * 获取responseData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getResponseData() {
        return responseData;
    }

    /**
     * 设置responseData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setResponseData(Object value) {
        this.responseData = value;
    }

    /**
     * 获取responseMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * 设置responseMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseMessage(String value) {
        this.responseMessage = value;
    }

}
