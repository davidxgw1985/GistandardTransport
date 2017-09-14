
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>batchDeleteBusinessOrderRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="batchDeleteBusinessOrderRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="businessOrderNOList" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}deleteBusinessOrderVO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchDeleteBusinessOrderRequest", propOrder = {
    "businessOrderNOList"
})
public class BatchDeleteBusinessOrderRequest {

    @XmlElement(nillable = true)
    protected List<DeleteBusinessOrderVO> businessOrderNOList;

    /**
     * Gets the value of the businessOrderNOList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the businessOrderNOList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusinessOrderNOList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeleteBusinessOrderVO }
     * 
     * 
     */
    public List<DeleteBusinessOrderVO> getBusinessOrderNOList() {
        if (businessOrderNOList == null) {
            businessOrderNOList = new ArrayList<DeleteBusinessOrderVO>();
        }
        return this.businessOrderNOList;
    }

}
