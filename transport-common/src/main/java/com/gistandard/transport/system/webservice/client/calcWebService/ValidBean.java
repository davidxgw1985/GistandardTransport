
package com.gistandard.transport.system.webservice.client.calcWebService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>validBean complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="validBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="actualAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="couponIdList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="currencyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="docNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="initDoc" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="initDocNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isMultiCalc" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="orderInfoBeanOList" type="{http://webService.external.giifiCalc.transport.gistandard.com/}baseOrderInfoBean" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="payStatus" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="payType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="recGFUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="recGFUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="testFlag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="transportType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tsCreated" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUser3FToCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUser3FToName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validBean", propOrder = {
    "actualAmount",
    "couponIdList",
    "currencyCode",
    "currencyName",
    "docNo",
    "initDoc",
    "initDocNo",
    "isMultiCalc",
    "orderInfoBeanOList",
    "payStatus",
    "payType",
    "price",
    "recGFUserCode",
    "recGFUserName",
    "testFlag",
    "transportType",
    "tsCreated",
    "gfUser3FToCode",
    "gfUser3FToName"
})
public class ValidBean {

    protected BigDecimal actualAmount;
    @XmlElement(nillable = true)
    protected List<Long> couponIdList;
    protected String currencyCode;
    protected String currencyName;
    protected String docNo;
    protected int initDoc;
    protected String initDocNo;
    protected boolean isMultiCalc;
    @XmlElement(nillable = true)
    protected List<BaseOrderInfoBean> orderInfoBeanOList;
    protected int payStatus;
    protected Integer payType;
    protected BigDecimal price;
    protected String recGFUserCode;
    protected String recGFUserName;
    protected Integer testFlag;
    protected String transportType;
    protected String tsCreated;
    @XmlElement(name = "gFUser3FToCode")
    protected String gfUser3FToCode;
    @XmlElement(name = "gFUser3FToName")
    protected String gfUser3FToName;

    /**
     * 获取actualAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    /**
     * 设置actualAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setActualAmount(BigDecimal value) {
        this.actualAmount = value;
    }

    /**
     * Gets the value of the couponIdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the couponIdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCouponIdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getCouponIdList() {
        if (couponIdList == null) {
            couponIdList = new ArrayList<Long>();
        }
        return this.couponIdList;
    }

    /**
     * 获取currencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 设置currencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * 获取currencyName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * 设置currencyName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyName(String value) {
        this.currencyName = value;
    }

    /**
     * 获取docNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocNo() {
        return docNo;
    }

    /**
     * 设置docNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocNo(String value) {
        this.docNo = value;
    }

    /**
     * 获取initDoc属性的值。
     * 
     */
    public int getInitDoc() {
        return initDoc;
    }

    /**
     * 设置initDoc属性的值。
     * 
     */
    public void setInitDoc(int value) {
        this.initDoc = value;
    }

    /**
     * 获取initDocNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitDocNo() {
        return initDocNo;
    }

    /**
     * 设置initDocNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitDocNo(String value) {
        this.initDocNo = value;
    }

    /**
     * 获取isMultiCalc属性的值。
     * 
     */
    public boolean isIsMultiCalc() {
        return isMultiCalc;
    }

    /**
     * 设置isMultiCalc属性的值。
     * 
     */
    public void setIsMultiCalc(boolean value) {
        this.isMultiCalc = value;
    }

    /**
     * Gets the value of the orderInfoBeanOList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderInfoBeanOList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderInfoBeanOList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BaseOrderInfoBean }
     * 
     * 
     */
    public List<BaseOrderInfoBean> getOrderInfoBeanOList() {
        if (orderInfoBeanOList == null) {
            orderInfoBeanOList = new ArrayList<BaseOrderInfoBean>();
        }
        return this.orderInfoBeanOList;
    }

    /**
     * 获取payStatus属性的值。
     * 
     */
    public int getPayStatus() {
        return payStatus;
    }

    /**
     * 设置payStatus属性的值。
     * 
     */
    public void setPayStatus(int value) {
        this.payStatus = value;
    }

    /**
     * 获取payType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 设置payType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPayType(Integer value) {
        this.payType = value;
    }

    /**
     * 获取price属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置price属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * 获取recGFUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecGFUserCode() {
        return recGFUserCode;
    }

    /**
     * 设置recGFUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecGFUserCode(String value) {
        this.recGFUserCode = value;
    }

    /**
     * 获取recGFUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecGFUserName() {
        return recGFUserName;
    }

    /**
     * 设置recGFUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecGFUserName(String value) {
        this.recGFUserName = value;
    }

    /**
     * 获取testFlag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTestFlag() {
        return testFlag;
    }

    /**
     * 设置testFlag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTestFlag(Integer value) {
        this.testFlag = value;
    }

    /**
     * 获取transportType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportType() {
        return transportType;
    }

    /**
     * 设置transportType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportType(String value) {
        this.transportType = value;
    }

    /**
     * 获取tsCreated属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsCreated() {
        return tsCreated;
    }

    /**
     * 设置tsCreated属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsCreated(String value) {
        this.tsCreated = value;
    }

    /**
     * 获取gfUser3FToCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUser3FToCode() {
        return gfUser3FToCode;
    }

    /**
     * 设置gfUser3FToCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUser3FToCode(String value) {
        this.gfUser3FToCode = value;
    }

    /**
     * 获取gfUser3FToName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUser3FToName() {
        return gfUser3FToName;
    }

    /**
     * 设置gfUser3FToName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUser3FToName(String value) {
        this.gfUser3FToName = value;
    }

}
