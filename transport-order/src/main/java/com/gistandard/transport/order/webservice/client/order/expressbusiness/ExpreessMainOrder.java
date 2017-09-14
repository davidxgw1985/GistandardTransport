
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>expreessMainOrder complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="expreessMainOrder"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="biddingStationAcctId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="biddingUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="busiOrderNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="busiOrderType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="carGoLoader" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="carType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="createAcct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="createAcctId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="createTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="deliverDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="deliveryUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliveryUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="driverOfflineQuote" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="isDriverOfflineQuote" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="isJs" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="iscareDeliverDate" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="iscarePickupDate" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="lastStationAcctId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="lastStationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lastStationGiPoint" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}giPoint" minOccurs="0"/&gt;
 *         &lt;element name="mainOrderId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="mainOrderNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="mainTransType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="motorcadeId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="motorcateName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="motorcateQuote" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="motorcateQuoteDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="nextStationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orderStatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="pickupDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="publicMsType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quoteCurrencycode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="selfQuote" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="selfQuoteDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="selfquoteCurrencycode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stationAcctId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="stationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="taxRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="valueAddIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="vehicleAcctId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="vehicleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="vehicleId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "expreessMainOrder", propOrder = {
    "biddingStationAcctId",
    "biddingUserCode",
    "busiOrderNO",
    "busiOrderType",
    "carGoLoader",
    "carType",
    "createAcct",
    "createAcctId",
    "createTime",
    "deliverDate",
    "deliveryUserName",
    "deliveryUserTel",
    "driverOfflineQuote",
    "isDriverOfflineQuote",
    "isJs",
    "iscareDeliverDate",
    "iscarePickupDate",
    "lastStationAcctId",
    "lastStationCode",
    "lastStationGiPoint",
    "mainOrderId",
    "mainOrderNO",
    "mainTransType",
    "motorcadeId",
    "motorcateName",
    "motorcateQuote",
    "motorcateQuoteDate",
    "nextStationCode",
    "orderStatus",
    "pickupDate",
    "productType",
    "publicMsType",
    "quoteCurrencycode",
    "selfQuote",
    "selfQuoteDate",
    "selfquoteCurrencycode",
    "stationAcctId",
    "stationCode",
    "taxRate",
    "valueAddIds",
    "vehicleAcctId",
    "vehicleCode",
    "vehicleId"
})
@XmlSeeAlso({
    MotorcateQuoteReq.class
})
public class ExpreessMainOrder {

    protected Integer biddingStationAcctId;
    protected String biddingUserCode;
    protected String busiOrderNO;
    protected Integer busiOrderType;
    protected String carGoLoader;
    protected String carType;
    protected String createAcct;
    protected Integer createAcctId;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date createTime;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date deliverDate;
    protected String deliveryUserName;
    protected String deliveryUserTel;
    protected BigDecimal driverOfflineQuote;
    protected Integer isDriverOfflineQuote;
    protected Integer isJs;
    protected Integer iscareDeliverDate;
    protected Integer iscarePickupDate;
    protected Integer lastStationAcctId;
    protected String lastStationCode;
    protected GiPoint lastStationGiPoint;
    protected Integer mainOrderId;
    protected String mainOrderNO;
    protected String mainTransType;
    protected Integer motorcadeId;
    protected String motorcateName;
    protected BigDecimal motorcateQuote;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date motorcateQuoteDate;
    protected String nextStationCode;
    protected Integer orderStatus;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date pickupDate;
    protected String productType;
    protected String publicMsType;
    protected String quoteCurrencycode;
    protected BigDecimal selfQuote;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date selfQuoteDate;
    protected String selfquoteCurrencycode;
    protected Integer stationAcctId;
    protected String stationCode;
    protected BigDecimal taxRate;
    protected String valueAddIds;
    protected Integer vehicleAcctId;
    protected String vehicleCode;
    protected Integer vehicleId;

    /**
     * 获取biddingStationAcctId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBiddingStationAcctId() {
        return biddingStationAcctId;
    }

    /**
     * 设置biddingStationAcctId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBiddingStationAcctId(Integer value) {
        this.biddingStationAcctId = value;
    }

    /**
     * 获取biddingUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBiddingUserCode() {
        return biddingUserCode;
    }

    /**
     * 设置biddingUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBiddingUserCode(String value) {
        this.biddingUserCode = value;
    }

    /**
     * 获取busiOrderNO属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiOrderNO() {
        return busiOrderNO;
    }

    /**
     * 设置busiOrderNO属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiOrderNO(String value) {
        this.busiOrderNO = value;
    }

    /**
     * 获取busiOrderType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBusiOrderType() {
        return busiOrderType;
    }

    /**
     * 设置busiOrderType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBusiOrderType(Integer value) {
        this.busiOrderType = value;
    }

    /**
     * 获取carGoLoader属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarGoLoader() {
        return carGoLoader;
    }

    /**
     * 设置carGoLoader属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarGoLoader(String value) {
        this.carGoLoader = value;
    }

    /**
     * 获取carType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarType() {
        return carType;
    }

    /**
     * 设置carType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarType(String value) {
        this.carType = value;
    }

    /**
     * 获取createAcct属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateAcct() {
        return createAcct;
    }

    /**
     * 设置createAcct属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateAcct(String value) {
        this.createAcct = value;
    }

    /**
     * 获取createAcctId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCreateAcctId() {
        return createAcctId;
    }

    /**
     * 设置createAcctId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCreateAcctId(Integer value) {
        this.createAcctId = value;
    }

    /**
     * 获取createTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置createTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateTime(Date value) {
        this.createTime = value;
    }

    /**
     * 获取deliverDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDeliverDate() {
        return deliverDate;
    }

    /**
     * 设置deliverDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliverDate(Date value) {
        this.deliverDate = value;
    }

    /**
     * 获取deliveryUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryUserName() {
        return deliveryUserName;
    }

    /**
     * 设置deliveryUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryUserName(String value) {
        this.deliveryUserName = value;
    }

    /**
     * 获取deliveryUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryUserTel() {
        return deliveryUserTel;
    }

    /**
     * 设置deliveryUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryUserTel(String value) {
        this.deliveryUserTel = value;
    }

    /**
     * 获取driverOfflineQuote属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDriverOfflineQuote() {
        return driverOfflineQuote;
    }

    /**
     * 设置driverOfflineQuote属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDriverOfflineQuote(BigDecimal value) {
        this.driverOfflineQuote = value;
    }

    /**
     * 获取isDriverOfflineQuote属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsDriverOfflineQuote() {
        return isDriverOfflineQuote;
    }

    /**
     * 设置isDriverOfflineQuote属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsDriverOfflineQuote(Integer value) {
        this.isDriverOfflineQuote = value;
    }

    /**
     * 获取isJs属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsJs() {
        return isJs;
    }

    /**
     * 设置isJs属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsJs(Integer value) {
        this.isJs = value;
    }

    /**
     * 获取iscareDeliverDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIscareDeliverDate() {
        return iscareDeliverDate;
    }

    /**
     * 设置iscareDeliverDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIscareDeliverDate(Integer value) {
        this.iscareDeliverDate = value;
    }

    /**
     * 获取iscarePickupDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIscarePickupDate() {
        return iscarePickupDate;
    }

    /**
     * 设置iscarePickupDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIscarePickupDate(Integer value) {
        this.iscarePickupDate = value;
    }

    /**
     * 获取lastStationAcctId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLastStationAcctId() {
        return lastStationAcctId;
    }

    /**
     * 设置lastStationAcctId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLastStationAcctId(Integer value) {
        this.lastStationAcctId = value;
    }

    /**
     * 获取lastStationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastStationCode() {
        return lastStationCode;
    }

    /**
     * 设置lastStationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastStationCode(String value) {
        this.lastStationCode = value;
    }

    /**
     * 获取lastStationGiPoint属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GiPoint }
     *     
     */
    public GiPoint getLastStationGiPoint() {
        return lastStationGiPoint;
    }

    /**
     * 设置lastStationGiPoint属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GiPoint }
     *     
     */
    public void setLastStationGiPoint(GiPoint value) {
        this.lastStationGiPoint = value;
    }

    /**
     * 获取mainOrderId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMainOrderId() {
        return mainOrderId;
    }

    /**
     * 设置mainOrderId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMainOrderId(Integer value) {
        this.mainOrderId = value;
    }

    /**
     * 获取mainOrderNO属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainOrderNO() {
        return mainOrderNO;
    }

    /**
     * 设置mainOrderNO属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainOrderNO(String value) {
        this.mainOrderNO = value;
    }

    /**
     * 获取mainTransType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainTransType() {
        return mainTransType;
    }

    /**
     * 设置mainTransType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainTransType(String value) {
        this.mainTransType = value;
    }

    /**
     * 获取motorcadeId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMotorcadeId() {
        return motorcadeId;
    }

    /**
     * 设置motorcadeId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMotorcadeId(Integer value) {
        this.motorcadeId = value;
    }

    /**
     * 获取motorcateName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotorcateName() {
        return motorcateName;
    }

    /**
     * 设置motorcateName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotorcateName(String value) {
        this.motorcateName = value;
    }

    /**
     * 获取motorcateQuote属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMotorcateQuote() {
        return motorcateQuote;
    }

    /**
     * 设置motorcateQuote属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMotorcateQuote(BigDecimal value) {
        this.motorcateQuote = value;
    }

    /**
     * 获取motorcateQuoteDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getMotorcateQuoteDate() {
        return motorcateQuoteDate;
    }

    /**
     * 设置motorcateQuoteDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotorcateQuoteDate(Date value) {
        this.motorcateQuoteDate = value;
    }

    /**
     * 获取nextStationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextStationCode() {
        return nextStationCode;
    }

    /**
     * 设置nextStationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextStationCode(String value) {
        this.nextStationCode = value;
    }

    /**
     * 获取orderStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置orderStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrderStatus(Integer value) {
        this.orderStatus = value;
    }

    /**
     * 获取pickupDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPickupDate() {
        return pickupDate;
    }

    /**
     * 设置pickupDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupDate(Date value) {
        this.pickupDate = value;
    }

    /**
     * 获取productType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductType() {
        return productType;
    }

    /**
     * 设置productType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductType(String value) {
        this.productType = value;
    }

    /**
     * 获取publicMsType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublicMsType() {
        return publicMsType;
    }

    /**
     * 设置publicMsType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicMsType(String value) {
        this.publicMsType = value;
    }

    /**
     * 获取quoteCurrencycode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuoteCurrencycode() {
        return quoteCurrencycode;
    }

    /**
     * 设置quoteCurrencycode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuoteCurrencycode(String value) {
        this.quoteCurrencycode = value;
    }

    /**
     * 获取selfQuote属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSelfQuote() {
        return selfQuote;
    }

    /**
     * 设置selfQuote属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSelfQuote(BigDecimal value) {
        this.selfQuote = value;
    }

    /**
     * 获取selfQuoteDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getSelfQuoteDate() {
        return selfQuoteDate;
    }

    /**
     * 设置selfQuoteDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelfQuoteDate(Date value) {
        this.selfQuoteDate = value;
    }

    /**
     * 获取selfquoteCurrencycode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelfquoteCurrencycode() {
        return selfquoteCurrencycode;
    }

    /**
     * 设置selfquoteCurrencycode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelfquoteCurrencycode(String value) {
        this.selfquoteCurrencycode = value;
    }

    /**
     * 获取stationAcctId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStationAcctId() {
        return stationAcctId;
    }

    /**
     * 设置stationAcctId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStationAcctId(Integer value) {
        this.stationAcctId = value;
    }

    /**
     * 获取stationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationCode() {
        return stationCode;
    }

    /**
     * 设置stationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationCode(String value) {
        this.stationCode = value;
    }

    /**
     * 获取taxRate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * 设置taxRate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxRate(BigDecimal value) {
        this.taxRate = value;
    }

    /**
     * 获取valueAddIds属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueAddIds() {
        return valueAddIds;
    }

    /**
     * 设置valueAddIds属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueAddIds(String value) {
        this.valueAddIds = value;
    }

    /**
     * 获取vehicleAcctId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVehicleAcctId() {
        return vehicleAcctId;
    }

    /**
     * 设置vehicleAcctId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVehicleAcctId(Integer value) {
        this.vehicleAcctId = value;
    }

    /**
     * 获取vehicleCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehicleCode() {
        return vehicleCode;
    }

    /**
     * 设置vehicleCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehicleCode(String value) {
        this.vehicleCode = value;
    }

    /**
     * 获取vehicleId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVehicleId() {
        return vehicleId;
    }

    /**
     * 设置vehicleId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVehicleId(Integer value) {
        this.vehicleId = value;
    }

}
