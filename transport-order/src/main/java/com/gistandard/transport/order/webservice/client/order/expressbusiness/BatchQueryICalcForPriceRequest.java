
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>batchQueryICalcForPriceRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="batchQueryICalcForPriceRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="queryICalcForPriceList" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}queryICalcForPriceVO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchQueryICalcForPriceRequest", propOrder = {
    "queryICalcForPriceList"
})
public class BatchQueryICalcForPriceRequest {

    @XmlElement(nillable = true)
    protected List<QueryICalcForPriceVO> queryICalcForPriceList;

    /**
     * Gets the value of the queryICalcForPriceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the queryICalcForPriceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQueryICalcForPriceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QueryICalcForPriceVO }
     * 
     * 
     */
    public List<QueryICalcForPriceVO> getQueryICalcForPriceList() {
        if (queryICalcForPriceList == null) {
            queryICalcForPriceList = new ArrayList<QueryICalcForPriceVO>();
        }
        return this.queryICalcForPriceList;
    }

}
