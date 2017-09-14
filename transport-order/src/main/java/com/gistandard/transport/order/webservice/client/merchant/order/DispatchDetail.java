
package com.gistandard.transport.order.webservice.client.merchant.order;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>dispatchDetail complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="dispatchDetail"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cneeCustAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cneeCustCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cneeCustCounty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cneeCustLinkMan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cneeCustLinkTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cneeCustProvide" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cneeLatitude" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="cneeLongitude" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="comQuoteId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="createUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="createUserId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="destPayment" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="destPaymentCurr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="goodsPayment" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="goodsPaymentCurr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="payType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="quotedType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="shipCustAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="shipCustCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="shipCustCounty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="shipCustLinkMan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="shipCustLinkTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="shipCustPrivide" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="shipLatitude" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="shipLongitude" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dispatchDetail", propOrder = {
    "cneeCustAddr",
    "cneeCustCity",
    "cneeCustCounty",
    "cneeCustLinkMan",
    "cneeCustLinkTel",
    "cneeCustProvide",
    "cneeLatitude",
    "cneeLongitude",
    "comQuoteId",
    "createUser",
    "createUserId",
    "destPayment",
    "destPaymentCurr",
    "goodsPayment",
    "goodsPaymentCurr",
    "payType",
    "quotedType",
    "shipCustAddr",
    "shipCustCity",
    "shipCustCounty",
    "shipCustLinkMan",
    "shipCustLinkTel",
    "shipCustPrivide",
    "shipLatitude",
    "shipLongitude"
})
public class DispatchDetail {

    protected String cneeCustAddr;
    protected String cneeCustCity;
    protected String cneeCustCounty;
    protected String cneeCustLinkMan;
    protected String cneeCustLinkTel;
    protected String cneeCustProvide;
    protected BigDecimal cneeLatitude;
    protected BigDecimal cneeLongitude;
    protected String comQuoteId;
    protected String createUser;
    protected long createUserId;
    protected BigDecimal destPayment;
    protected String destPaymentCurr;
    protected BigDecimal goodsPayment;
    protected String goodsPaymentCurr;
    protected int payType;
    protected int quotedType;
    protected String shipCustAddr;
    protected String shipCustCity;
    protected String shipCustCounty;
    protected String shipCustLinkMan;
    protected String shipCustLinkTel;
    protected String shipCustPrivide;
    protected BigDecimal shipLatitude;
    protected BigDecimal shipLongitude;

    /**
     * 获取cneeCustAddr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCneeCustAddr() {
        return cneeCustAddr;
    }

    /**
     * 设置cneeCustAddr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCneeCustAddr(String value) {
        this.cneeCustAddr = value;
    }

    /**
     * 获取cneeCustCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCneeCustCity() {
        return cneeCustCity;
    }

    /**
     * 设置cneeCustCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCneeCustCity(String value) {
        this.cneeCustCity = value;
    }

    /**
     * 获取cneeCustCounty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCneeCustCounty() {
        return cneeCustCounty;
    }

    /**
     * 设置cneeCustCounty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCneeCustCounty(String value) {
        this.cneeCustCounty = value;
    }

    /**
     * 获取cneeCustLinkMan属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCneeCustLinkMan() {
        return cneeCustLinkMan;
    }

    /**
     * 设置cneeCustLinkMan属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCneeCustLinkMan(String value) {
        this.cneeCustLinkMan = value;
    }

    /**
     * 获取cneeCustLinkTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCneeCustLinkTel() {
        return cneeCustLinkTel;
    }

    /**
     * 设置cneeCustLinkTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCneeCustLinkTel(String value) {
        this.cneeCustLinkTel = value;
    }

    /**
     * 获取cneeCustProvide属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCneeCustProvide() {
        return cneeCustProvide;
    }

    /**
     * 设置cneeCustProvide属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCneeCustProvide(String value) {
        this.cneeCustProvide = value;
    }

    /**
     * 获取cneeLatitude属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCneeLatitude() {
        return cneeLatitude;
    }

    /**
     * 设置cneeLatitude属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCneeLatitude(BigDecimal value) {
        this.cneeLatitude = value;
    }

    /**
     * 获取cneeLongitude属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCneeLongitude() {
        return cneeLongitude;
    }

    /**
     * 设置cneeLongitude属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCneeLongitude(BigDecimal value) {
        this.cneeLongitude = value;
    }

    /**
     * 获取comQuoteId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComQuoteId() {
        return comQuoteId;
    }

    /**
     * 设置comQuoteId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComQuoteId(String value) {
        this.comQuoteId = value;
    }

    /**
     * 获取createUser属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置createUser属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateUser(String value) {
        this.createUser = value;
    }

    /**
     * 获取createUserId属性的值。
     * 
     */
    public long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置createUserId属性的值。
     * 
     */
    public void setCreateUserId(long value) {
        this.createUserId = value;
    }

    /**
     * 获取destPayment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDestPayment() {
        return destPayment;
    }

    /**
     * 设置destPayment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDestPayment(BigDecimal value) {
        this.destPayment = value;
    }

    /**
     * 获取destPaymentCurr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestPaymentCurr() {
        return destPaymentCurr;
    }

    /**
     * 设置destPaymentCurr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestPaymentCurr(String value) {
        this.destPaymentCurr = value;
    }

    /**
     * 获取goodsPayment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGoodsPayment() {
        return goodsPayment;
    }

    /**
     * 设置goodsPayment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGoodsPayment(BigDecimal value) {
        this.goodsPayment = value;
    }

    /**
     * 获取goodsPaymentCurr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsPaymentCurr() {
        return goodsPaymentCurr;
    }

    /**
     * 设置goodsPaymentCurr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsPaymentCurr(String value) {
        this.goodsPaymentCurr = value;
    }

    /**
     * 获取payType属性的值。
     * 
     */
    public int getPayType() {
        return payType;
    }

    /**
     * 设置payType属性的值。
     * 
     */
    public void setPayType(int value) {
        this.payType = value;
    }

    /**
     * 获取quotedType属性的值。
     * 
     */
    public int getQuotedType() {
        return quotedType;
    }

    /**
     * 设置quotedType属性的值。
     * 
     */
    public void setQuotedType(int value) {
        this.quotedType = value;
    }

    /**
     * 获取shipCustAddr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipCustAddr() {
        return shipCustAddr;
    }

    /**
     * 设置shipCustAddr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipCustAddr(String value) {
        this.shipCustAddr = value;
    }

    /**
     * 获取shipCustCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipCustCity() {
        return shipCustCity;
    }

    /**
     * 设置shipCustCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipCustCity(String value) {
        this.shipCustCity = value;
    }

    /**
     * 获取shipCustCounty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipCustCounty() {
        return shipCustCounty;
    }

    /**
     * 设置shipCustCounty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipCustCounty(String value) {
        this.shipCustCounty = value;
    }

    /**
     * 获取shipCustLinkMan属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipCustLinkMan() {
        return shipCustLinkMan;
    }

    /**
     * 设置shipCustLinkMan属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipCustLinkMan(String value) {
        this.shipCustLinkMan = value;
    }

    /**
     * 获取shipCustLinkTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipCustLinkTel() {
        return shipCustLinkTel;
    }

    /**
     * 设置shipCustLinkTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipCustLinkTel(String value) {
        this.shipCustLinkTel = value;
    }

    /**
     * 获取shipCustPrivide属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipCustPrivide() {
        return shipCustPrivide;
    }

    /**
     * 设置shipCustPrivide属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipCustPrivide(String value) {
        this.shipCustPrivide = value;
    }

    /**
     * 获取shipLatitude属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getShipLatitude() {
        return shipLatitude;
    }

    /**
     * 设置shipLatitude属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setShipLatitude(BigDecimal value) {
        this.shipLatitude = value;
    }

    /**
     * 获取shipLongitude属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getShipLongitude() {
        return shipLongitude;
    }

    /**
     * 设置shipLongitude属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setShipLongitude(BigDecimal value) {
        this.shipLongitude = value;
    }

}
