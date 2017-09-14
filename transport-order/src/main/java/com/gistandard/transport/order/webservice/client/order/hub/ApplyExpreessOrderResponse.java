
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>applyExpreessOrderResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="applyExpreessOrderResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="applyExpreessOrderResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "applyExpreessOrderResponse", propOrder = {
    "applyExpreessOrderResult"
})
public class ApplyExpreessOrderResponse {

    protected String applyExpreessOrderResult;

    /**
     * 获取applyExpreessOrderResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplyExpreessOrderResult() {
        return applyExpreessOrderResult;
    }

    /**
     * 设置applyExpreessOrderResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplyExpreessOrderResult(String value) {
        this.applyExpreessOrderResult = value;
    }

}
