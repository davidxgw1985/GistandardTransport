
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MmsMessage complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MmsMessage"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bodyText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MmsMessage", namespace = "http://www.csapi.org/schema/mms", propOrder = {
    "bodyText",
    "content"
})
public class MmsMessage {

    @XmlElement(required = true)
    protected String bodyText;
    @XmlElement(required = true)
    protected String content;

    /**
     * 获取bodyText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBodyText() {
        return bodyText;
    }

    /**
     * 设置bodyText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBodyText(String value) {
        this.bodyText = value;
    }

    /**
     * 获取content属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置content属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

}
