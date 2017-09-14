
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>motorcateQuote complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="motorcateQuote"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="motorcateQuoteRequest" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}motorcateQuoteReq" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "motorcateQuote", propOrder = {
    "motorcateQuoteRequest"
})
public class MotorcateQuote {

    protected MotorcateQuoteReq motorcateQuoteRequest;

    /**
     * 获取motorcateQuoteRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MotorcateQuoteReq }
     *     
     */
    public MotorcateQuoteReq getMotorcateQuoteRequest() {
        return motorcateQuoteRequest;
    }

    /**
     * 设置motorcateQuoteRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MotorcateQuoteReq }
     *     
     */
    public void setMotorcateQuoteRequest(MotorcateQuoteReq value) {
        this.motorcateQuoteRequest = value;
    }

}
