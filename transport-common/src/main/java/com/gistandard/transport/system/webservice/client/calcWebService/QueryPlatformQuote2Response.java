
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>QueryPlatformQuote2Response complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="QueryPlatformQuote2Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://webService.external.giifiCalc.transport.gistandard.com/}platformQuote" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryPlatformQuote2Response", propOrder = {
    "_return"
})
public class QueryPlatformQuote2Response {

    @XmlElement(name = "return")
    protected PlatformQuote _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PlatformQuote }
     *     
     */
    public PlatformQuote getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PlatformQuote }
     *     
     */
    public void setReturn(PlatformQuote value) {
        this._return = value;
    }

}
