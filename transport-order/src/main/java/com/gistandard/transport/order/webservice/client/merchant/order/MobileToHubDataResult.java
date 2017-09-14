
package com.gistandard.transport.order.webservice.client.merchant.order;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>mobileToHubDataResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="mobileToHubDataResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://webService.mobileRecOrder.dispatch.transport.gistandard.com/}baseRequestResult"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="result" type="{http://webService.mobileRecOrder.dispatch.transport.gistandard.com/}orderObject" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mobileToHubDataResult", propOrder = {
    "result"
})
public class MobileToHubDataResult
    extends BaseRequestResult
{

    @XmlElement(nillable = true)
    protected List<OrderObject> result;

    /**
     * Gets the value of the result property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the result property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderObject }
     * 
     * 
     */
    public List<OrderObject> getResult() {
        if (result == null) {
            result = new ArrayList<OrderObject>();
        }
        return this.result;
    }

}
