
package com.gistandard.transport.order.webservice.client.merchant.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>orderObject complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="orderObject"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="busiNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dispatchId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="quotationNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderObject", propOrder = {
    "busiNo",
    "dispatchId",
    "quotationNo"
})
public class OrderObject {

    protected String busiNo;
    protected Long dispatchId;
    protected String quotationNo;

    /**
     * 获取busiNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiNo() {
        return busiNo;
    }

    /**
     * 设置busiNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiNo(String value) {
        this.busiNo = value;
    }

    /**
     * 获取dispatchId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDispatchId() {
        return dispatchId;
    }

    /**
     * 设置dispatchId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDispatchId(Long value) {
        this.dispatchId = value;
    }

    /**
     * 获取quotationNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuotationNo() {
        return quotationNo;
    }

    /**
     * 设置quotationNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuotationNo(String value) {
        this.quotationNo = value;
    }

}
