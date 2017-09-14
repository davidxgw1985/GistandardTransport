
package com.gistandard.transport.order.webservice.client.merchant.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>mobileToMobileDataResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="mobileToMobileDataResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://webService.mobileRecOrder.dispatch.transport.gistandard.com/}baseRequestResult"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="busiNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dispatchId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="scheduCarNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mobileToMobileDataResult", propOrder = {
    "busiNo",
    "dispatchId",
    "key",
    "scheduCarNO"
})
public class MobileToMobileDataResult
    extends BaseRequestResult
{

    protected String busiNo;
    protected long dispatchId;
    protected String key;
    protected String scheduCarNO;

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
     */
    public long getDispatchId() {
        return dispatchId;
    }

    /**
     * 设置dispatchId属性的值。
     * 
     */
    public void setDispatchId(long value) {
        this.dispatchId = value;
    }

    /**
     * 获取key属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置key属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * 获取scheduCarNO属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduCarNO() {
        return scheduCarNO;
    }

    /**
     * 设置scheduCarNO属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduCarNO(String value) {
        this.scheduCarNO = value;
    }

}
