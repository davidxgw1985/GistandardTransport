
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>batchQueryICalcForPrice complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="batchQueryICalcForPrice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="batchQueryICalcForPriceRequest" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}batchQueryICalcForPriceRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "batchQueryICalcForPrice", propOrder = {
    "batchQueryICalcForPriceRequest"
})
public class BatchQueryICalcForPrice {

    protected BatchQueryICalcForPriceRequest batchQueryICalcForPriceRequest;

    /**
     * 获取batchQueryICalcForPriceRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BatchQueryICalcForPriceRequest }
     *     
     */
    public BatchQueryICalcForPriceRequest getBatchQueryICalcForPriceRequest() {
        return batchQueryICalcForPriceRequest;
    }

    /**
     * 设置batchQueryICalcForPriceRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BatchQueryICalcForPriceRequest }
     *     
     */
    public void setBatchQueryICalcForPriceRequest(BatchQueryICalcForPriceRequest value) {
        this.batchQueryICalcForPriceRequest = value;
    }

}
