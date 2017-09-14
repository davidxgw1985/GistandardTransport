
package com.gistandard.transport.order.webservice.client.merchant.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>mobileToMobileParam complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="mobileToMobileParam"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="busiNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dispatchId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="fromGFuserId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quotationNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="scheduCarNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="toGFuserId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mobileToMobileParam", propOrder = {
    "busiNo",
    "dispatchId",
    "fromGFuserId",
    "key",
    "quotationNo",
    "scheduCarNO",
    "toGFuserId",
    "userID"
})
public class MobileToMobileParam {

    protected String busiNo;
    protected Integer dispatchId;
    protected Integer fromGFuserId;
    protected String key;
    protected String quotationNo;
    protected String scheduCarNO;
    protected Integer toGFuserId;
    protected Integer userID;

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
     *     {@link Integer }
     *     
     */
    public Integer getDispatchId() {
        return dispatchId;
    }

    /**
     * 设置dispatchId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDispatchId(Integer value) {
        this.dispatchId = value;
    }

    /**
     * 获取fromGFuserId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFromGFuserId() {
        return fromGFuserId;
    }

    /**
     * 设置fromGFuserId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFromGFuserId(Integer value) {
        this.fromGFuserId = value;
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

    /**
     * 获取toGFuserId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getToGFuserId() {
        return toGFuserId;
    }

    /**
     * 设置toGFuserId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setToGFuserId(Integer value) {
        this.toGFuserId = value;
    }

    /**
     * 获取userID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * 设置userID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUserID(Integer value) {
        this.userID = value;
    }

}
