
package com.gistandard.transport.order.webservice.client.order.hub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>refuseSchudeCarOrderList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="refuseSchudeCarOrderList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="refuseSchudeCarOrderRequestList" type="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}giveUpSchudeCarOrderRequest" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "refuseSchudeCarOrderList", propOrder = {
    "refuseSchudeCarOrderRequestList"
})
public class RefuseSchudeCarOrderList {

    protected List<GiveUpSchudeCarOrderRequest> refuseSchudeCarOrderRequestList;

    /**
     * Gets the value of the refuseSchudeCarOrderRequestList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refuseSchudeCarOrderRequestList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefuseSchudeCarOrderRequestList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GiveUpSchudeCarOrderRequest }
     * 
     * 
     */
    public List<GiveUpSchudeCarOrderRequest> getRefuseSchudeCarOrderRequestList() {
        if (refuseSchudeCarOrderRequestList == null) {
            refuseSchudeCarOrderRequestList = new ArrayList<GiveUpSchudeCarOrderRequest>();
        }
        return this.refuseSchudeCarOrderRequestList;
    }

}
