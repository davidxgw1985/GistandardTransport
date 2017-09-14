
package com.gistandard.transport.order.webservice.client.merchant.order;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>hubToHubDataResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="hubToHubDataResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://webService.mobileRecOrder.dispatch.transport.gistandard.com/}baseRequestResult"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dispatch" type="{http://webService.mobileRecOrder.dispatch.transport.gistandard.com/}dispatchExtend" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hubToHubDataResult", propOrder = {
    "dispatch"
})
public class HubToHubDataResult
    extends BaseRequestResult
{

    @XmlElement(nillable = true)
    protected List<DispatchExtend> dispatch;

    /**
     * Gets the value of the dispatch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dispatch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDispatch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DispatchExtend }
     * 
     * 
     */
    public List<DispatchExtend> getDispatch() {
        if (dispatch == null) {
            dispatch = new ArrayList<DispatchExtend>();
        }
        return this.dispatch;
    }

}
