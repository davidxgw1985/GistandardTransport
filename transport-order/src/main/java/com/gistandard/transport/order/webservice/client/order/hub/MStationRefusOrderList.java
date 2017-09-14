
package com.gistandard.transport.order.webservice.client.order.hub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>mStationRefusOrderList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="mStationRefusOrderList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="mStationRefusOrderRequestList" type="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}mStationRevOrderRequest" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mStationRefusOrderList", propOrder = {
    "mStationRefusOrderRequestList"
})
public class MStationRefusOrderList {

    protected List<MStationRevOrderRequest> mStationRefusOrderRequestList;

    /**
     * Gets the value of the mStationRefusOrderRequestList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mStationRefusOrderRequestList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMStationRefusOrderRequestList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MStationRevOrderRequest }
     * 
     * 
     */
    public List<MStationRevOrderRequest> getMStationRefusOrderRequestList() {
        if (mStationRefusOrderRequestList == null) {
            mStationRefusOrderRequestList = new ArrayList<MStationRevOrderRequest>();
        }
        return this.mStationRefusOrderRequestList;
    }

}
