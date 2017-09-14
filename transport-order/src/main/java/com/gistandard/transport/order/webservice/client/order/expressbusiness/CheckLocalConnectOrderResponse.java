
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>checkLocalConnectOrderResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="checkLocalConnectOrderResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="checkLocalConnectOrderResponse" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkLocalConnectOrderResponse", propOrder = {
    "checkLocalConnectOrderResponse"
})
public class CheckLocalConnectOrderResponse {

    protected boolean checkLocalConnectOrderResponse;

    /**
     * 获取checkLocalConnectOrderResponse属性的值。
     * 
     */
    public boolean isCheckLocalConnectOrderResponse() {
        return checkLocalConnectOrderResponse;
    }

    /**
     * 设置checkLocalConnectOrderResponse属性的值。
     * 
     */
    public void setCheckLocalConnectOrderResponse(boolean value) {
        this.checkLocalConnectOrderResponse = value;
    }

}
