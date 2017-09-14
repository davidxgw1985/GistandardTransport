
package com.gistandard.transport.order.webservice.client.merchant.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>requestResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="requestResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://webService.mobileRecOrder.dispatch.transport.gistandard.com/}baseRequestResult"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dispatchID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestResult", propOrder = {
    "dispatchID"
})
public class RequestResult
    extends BaseRequestResult
{

    protected long dispatchID;

    /**
     * 获取dispatchID属性的值。
     * 
     */
    public long getDispatchID() {
        return dispatchID;
    }

    /**
     * 设置dispatchID属性的值。
     * 
     */
    public void setDispatchID(long value) {
        this.dispatchID = value;
    }

}
