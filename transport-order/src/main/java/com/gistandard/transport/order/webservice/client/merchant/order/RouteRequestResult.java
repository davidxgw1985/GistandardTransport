
package com.gistandard.transport.order.webservice.client.merchant.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>routeRequestResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="routeRequestResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="data" type="{http://webService.mobileRecOrder.dispatch.transport.gistandard.com/}resultRoute" minOccurs="0"/&gt;
 *         &lt;element name="mesasge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "routeRequestResult", propOrder = {
    "data",
    "mesasge",
    "status"
})
public class RouteRequestResult {

    protected ResultRoute data;
    protected String mesasge;
    protected int status;

    /**
     * 获取data属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ResultRoute }
     *     
     */
    public ResultRoute getData() {
        return data;
    }

    /**
     * 设置data属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ResultRoute }
     *     
     */
    public void setData(ResultRoute value) {
        this.data = value;
    }

    /**
     * 获取mesasge属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMesasge() {
        return mesasge;
    }

    /**
     * 设置mesasge属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMesasge(String value) {
        this.mesasge = value;
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
