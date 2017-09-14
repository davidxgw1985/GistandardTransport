
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>takeOverSchudeCarOrderResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="takeOverSchudeCarOrderResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="takeOverSchudeCarOrderResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "takeOverSchudeCarOrderResponse", propOrder = {
    "takeOverSchudeCarOrderResult"
})
public class TakeOverSchudeCarOrderResponse {

    protected String takeOverSchudeCarOrderResult;

    /**
     * 获取takeOverSchudeCarOrderResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTakeOverSchudeCarOrderResult() {
        return takeOverSchudeCarOrderResult;
    }

    /**
     * 设置takeOverSchudeCarOrderResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTakeOverSchudeCarOrderResult(String value) {
        this.takeOverSchudeCarOrderResult = value;
    }

}
