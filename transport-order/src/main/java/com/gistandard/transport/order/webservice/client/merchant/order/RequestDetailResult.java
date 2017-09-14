
package com.gistandard.transport.order.webservice.client.merchant.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>requestDetailResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="requestDetailResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://webService.mobileRecOrder.dispatch.transport.gistandard.com/}baseRequestResult"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dispatchDetail" type="{http://webService.mobileRecOrder.dispatch.transport.gistandard.com/}dispatchDetail" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestDetailResult", propOrder = {
    "dispatchDetail"
})
public class RequestDetailResult
    extends BaseRequestResult
{

    protected DispatchDetail dispatchDetail;

    /**
     * 获取dispatchDetail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DispatchDetail }
     *     
     */
    public DispatchDetail getDispatchDetail() {
        return dispatchDetail;
    }

    /**
     * 设置dispatchDetail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DispatchDetail }
     *     
     */
    public void setDispatchDetail(DispatchDetail value) {
        this.dispatchDetail = value;
    }

}
