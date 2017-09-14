
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>batchDeleteBusinessOrder complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="batchDeleteBusinessOrder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="batchDeleteBusinessOrderRequest" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}batchDeleteBusinessOrderRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchDeleteBusinessOrder", propOrder = {
    "batchDeleteBusinessOrderRequest"
})
public class BatchDeleteBusinessOrder {

    protected BatchDeleteBusinessOrderRequest batchDeleteBusinessOrderRequest;

    /**
     * 获取batchDeleteBusinessOrderRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BatchDeleteBusinessOrderRequest }
     *     
     */
    public BatchDeleteBusinessOrderRequest getBatchDeleteBusinessOrderRequest() {
        return batchDeleteBusinessOrderRequest;
    }

    /**
     * 设置batchDeleteBusinessOrderRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BatchDeleteBusinessOrderRequest }
     *     
     */
    public void setBatchDeleteBusinessOrderRequest(BatchDeleteBusinessOrderRequest value) {
        this.batchDeleteBusinessOrderRequest = value;
    }

}
