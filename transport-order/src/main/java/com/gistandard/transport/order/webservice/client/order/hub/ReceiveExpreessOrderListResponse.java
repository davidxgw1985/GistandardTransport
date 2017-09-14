
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>receiveExpreessOrderListResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="receiveExpreessOrderListResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="receiveExpreessOrderListResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "receiveExpreessOrderListResponse", propOrder = {
    "receiveExpreessOrderListResult"
})
public class ReceiveExpreessOrderListResponse {

    protected String receiveExpreessOrderListResult;

    /**
     * 获取receiveExpreessOrderListResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiveExpreessOrderListResult() {
        return receiveExpreessOrderListResult;
    }

    /**
     * 设置receiveExpreessOrderListResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiveExpreessOrderListResult(String value) {
        this.receiveExpreessOrderListResult = value;
    }

}
