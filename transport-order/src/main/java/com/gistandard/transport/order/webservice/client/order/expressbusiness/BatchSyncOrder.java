
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>batchSyncOrder complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="batchSyncOrder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="batchSyncOrderRequest" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}batchSyncOrderRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchSyncOrder", propOrder = {
    "batchSyncOrderRequest"
})
public class BatchSyncOrder {

    protected BatchSyncOrderRequest batchSyncOrderRequest;

    /**
     * 获取batchSyncOrderRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BatchSyncOrderRequest }
     *     
     */
    public BatchSyncOrderRequest getBatchSyncOrderRequest() {
        return batchSyncOrderRequest;
    }

    /**
     * 设置batchSyncOrderRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BatchSyncOrderRequest }
     *     
     */
    public void setBatchSyncOrderRequest(BatchSyncOrderRequest value) {
        this.batchSyncOrderRequest = value;
    }

}
