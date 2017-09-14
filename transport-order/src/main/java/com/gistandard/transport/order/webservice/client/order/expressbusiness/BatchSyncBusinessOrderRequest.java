
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>batchSyncBusinessOrderRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="batchSyncBusinessOrderRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="expreessBusinessOrderList" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}expreessBusinessOrderVO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="expreessMainOrder" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}expreessMainOrder" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchSyncBusinessOrderRequest", propOrder = {
    "expreessBusinessOrderList",
    "expreessMainOrder"
})
public class BatchSyncBusinessOrderRequest {

    @XmlElement(nillable = true)
    protected List<ExpreessBusinessOrderVO> expreessBusinessOrderList;
    protected ExpreessMainOrder expreessMainOrder;

    /**
     * Gets the value of the expreessBusinessOrderList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the expreessBusinessOrderList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExpreessBusinessOrderList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExpreessBusinessOrderVO }
     * 
     * 
     */
    public List<ExpreessBusinessOrderVO> getExpreessBusinessOrderList() {
        if (expreessBusinessOrderList == null) {
            expreessBusinessOrderList = new ArrayList<ExpreessBusinessOrderVO>();
        }
        return this.expreessBusinessOrderList;
    }

    /**
     * 获取expreessMainOrder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ExpreessMainOrder }
     *     
     */
    public ExpreessMainOrder getExpreessMainOrder() {
        return expreessMainOrder;
    }

    /**
     * 设置expreessMainOrder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ExpreessMainOrder }
     *     
     */
    public void setExpreessMainOrder(ExpreessMainOrder value) {
        this.expreessMainOrder = value;
    }

    public void setExpreessBusinessOrderList(List<ExpreessBusinessOrderVO> expreessBusinessOrderList) {
        this.expreessBusinessOrderList = expreessBusinessOrderList;
    }
}
