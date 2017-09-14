
package com.gistandard.transport.order.webservice.client.order.hub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>succDeliverSchudeCarOrderList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="succDeliverSchudeCarOrderList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="succDeliverSchudeCarOrderRequestList" type="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}succDeliverSchudeCarOrderRequest" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "succDeliverSchudeCarOrderList", propOrder = {
    "succDeliverSchudeCarOrderRequestList"
})
public class SuccDeliverSchudeCarOrderList {

    protected List<SuccDeliverSchudeCarOrderRequest> succDeliverSchudeCarOrderRequestList;

    /**
     * Gets the value of the succDeliverSchudeCarOrderRequestList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the succDeliverSchudeCarOrderRequestList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuccDeliverSchudeCarOrderRequestList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SuccDeliverSchudeCarOrderRequest }
     * 
     * 
     */
    public List<SuccDeliverSchudeCarOrderRequest> getSuccDeliverSchudeCarOrderRequestList() {
        if (succDeliverSchudeCarOrderRequestList == null) {
            succDeliverSchudeCarOrderRequestList = new ArrayList<SuccDeliverSchudeCarOrderRequest>();
        }
        return this.succDeliverSchudeCarOrderRequestList;
    }

}
