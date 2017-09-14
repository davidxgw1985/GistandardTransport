
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>batchSyncBusinessOrder complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="batchSyncBusinessOrder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="batchSyncBusinessOrderRequest" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}batchSyncBusinessOrderRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchSyncBusinessOrder", propOrder = {
    "batchSyncBusinessOrderRequest"
})
public class BatchSyncBusinessOrder {

    protected BatchSyncBusinessOrderRequest batchSyncBusinessOrderRequest;

    /**
     * 获取batchSyncBusinessOrderRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BatchSyncBusinessOrderRequest }
     *     
     */
    public BatchSyncBusinessOrderRequest getBatchSyncBusinessOrderRequest() {
        return batchSyncBusinessOrderRequest;
    }

    /**
     * 设置batchSyncBusinessOrderRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BatchSyncBusinessOrderRequest }
     *     
     */
    public void setBatchSyncBusinessOrderRequest(BatchSyncBusinessOrderRequest value) {
        this.batchSyncBusinessOrderRequest = value;
    }

}
