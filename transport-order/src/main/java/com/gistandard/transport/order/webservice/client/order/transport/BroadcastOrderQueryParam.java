
package com.gistandard.transport.order.webservice.client.order.transport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>broadcastOrderQueryParam complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="broadcastOrderQueryParam"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="scheduCarNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "broadcastOrderQueryParam", propOrder = {
    "scheduCarNo",
    "status"
})
public class BroadcastOrderQueryParam {

    protected String scheduCarNo;
    protected int status;

    /**
     * 获取scheduCarNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduCarNo() {
        return scheduCarNo;
    }

    /**
     * 设置scheduCarNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduCarNo(String value) {
        this.scheduCarNo = value;
    }

    /**
     * 获取status属性的值。
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

}
