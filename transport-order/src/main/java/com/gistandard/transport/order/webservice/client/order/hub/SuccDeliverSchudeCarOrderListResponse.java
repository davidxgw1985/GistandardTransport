
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>succDeliverSchudeCarOrderListResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="succDeliverSchudeCarOrderListResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="succDeliverSchudeCarOrderResultList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "succDeliverSchudeCarOrderListResponse", propOrder = {
    "succDeliverSchudeCarOrderResultList"
})
public class SuccDeliverSchudeCarOrderListResponse {

    protected String succDeliverSchudeCarOrderResultList;

    /**
     * 获取succDeliverSchudeCarOrderResultList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuccDeliverSchudeCarOrderResultList() {
        return succDeliverSchudeCarOrderResultList;
    }

    /**
     * 设置succDeliverSchudeCarOrderResultList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuccDeliverSchudeCarOrderResultList(String value) {
        this.succDeliverSchudeCarOrderResultList = value;
    }

}
