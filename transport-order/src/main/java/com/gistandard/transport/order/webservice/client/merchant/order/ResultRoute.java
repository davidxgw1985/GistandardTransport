
package com.gistandard.transport.order.webservice.client.merchant.order;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>resultRoute complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="resultRoute"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="routeId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="routelst" type="{http://webService.mobileRecOrder.dispatch.transport.gistandard.com/}routeInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultRoute", propOrder = {
    "routeId",
    "routelst"
})
public class ResultRoute {

    protected Integer routeId;
    @XmlElement(nillable = true)
    protected List<RouteInfo> routelst;

    /**
     * 获取routeId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRouteId() {
        return routeId;
    }

    /**
     * 设置routeId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRouteId(Integer value) {
        this.routeId = value;
    }

    /**
     * Gets the value of the routelst property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the routelst property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoutelst().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RouteInfo }
     * 
     * 
     */
    public List<RouteInfo> getRoutelst() {
        if (routelst == null) {
            routelst = new ArrayList<RouteInfo>();
        }
        return this.routelst;
    }

}
