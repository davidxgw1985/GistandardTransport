
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>queryOrderDetailRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="queryOrderDetailRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}serviceBaseRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="schduleCarNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryOrderDetailRequest", propOrder = {
    "schduleCarNo"
})
public class QueryOrderDetailRequest
    extends ServiceBaseRequest
{

    protected String schduleCarNo;

    /**
     * 获取schduleCarNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchduleCarNo() {
        return schduleCarNo;
    }

    /**
     * 设置schduleCarNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchduleCarNo(String value) {
        this.schduleCarNo = value;
    }

}
