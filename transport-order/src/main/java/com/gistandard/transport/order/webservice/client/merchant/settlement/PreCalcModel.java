
package com.gistandard.transport.order.webservice.client.merchant.settlement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>preCalcModel complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="preCalcModel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="busiBookNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="busiDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cartons" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="feeWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="forSys" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="goodsCurrencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="goodsValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="grossWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="idQuotationMst" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="idtbPreCalc" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="netWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="pallets" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="paymentTerm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pieces" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="productCatagoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="productCatagoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quotationMstDocNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="routeWayFromCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="routeWayFromName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="routeWayToCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="routeWayToName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="scheduleSheetNo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="stationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="sysCatalog" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="tsAudited" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tsChanged" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tsCreated" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="volume" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="volumeUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="volumeWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="weightUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserChanged" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserCodCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserCodName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserCreated" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserFromCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserFromName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserToCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserToName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "preCalcModel", propOrder = {
    "age",
    "busiBookNo",
    "busiDate",
    "cartons",
    "feeWeight",
    "forSys",
    "goodsCurrencyCode",
    "goodsValue",
    "grossWeight",
    "idQuotationMst",
    "idtbPreCalc",
    "netWeight",
    "pallets",
    "paymentTerm",
    "pieces",
    "productCatagoryCode",
    "productCatagoryName",
    "quotationMstDocNo",
    "remark",
    "routeWayFromCode",
    "routeWayFromName",
    "routeWayToCode",
    "routeWayToName",
    "scheduleSheetNo",
    "stationCode",
    "status",
    "sysCatalog",
    "tsAudited",
    "tsChanged",
    "tsCreated",
    "volume",
    "volumeUnit",
    "volumeWeight",
    "weightUnit",
    "gfUserChanged",
    "gfUserCodCode",
    "gfUserCodName",
    "gfUserCreated",
    "gfUserFromCode",
    "gfUserFromName",
    "gfUserToCode",
    "gfUserToName"
})
public class PreCalcModel {

    protected Integer age;
    protected String busiBookNo;
    protected String busiDate;
    protected BigDecimal cartons;
    protected BigDecimal feeWeight;
    protected Integer forSys;
    protected String goodsCurrencyCode;
    protected BigDecimal goodsValue;
    protected BigDecimal grossWeight;
    protected Long idQuotationMst;
    protected Long idtbPreCalc;
    protected BigDecimal netWeight;
    protected BigDecimal pallets;
    protected String paymentTerm;
    protected BigDecimal pieces;
    protected String productCatagoryCode;
    protected String productCatagoryName;
    protected String quotationMstDocNo;
    protected String remark;
    protected String routeWayFromCode;
    protected String routeWayFromName;
    protected String routeWayToCode;
    protected String routeWayToName;
    protected Long scheduleSheetNo;
    protected String stationCode;
    protected Integer status;
    protected Integer sysCatalog;
    protected String tsAudited;
    protected String tsChanged;
    protected String tsCreated;
    protected BigDecimal volume;
    protected String volumeUnit;
    protected BigDecimal volumeWeight;
    protected String weightUnit;
    @XmlElement(name = "gFUserChanged")
    protected String gfUserChanged;
    @XmlElement(name = "gFUserCodCode")
    protected String gfUserCodCode;
    @XmlElement(name = "gFUserCodName")
    protected String gfUserCodName;
    @XmlElement(name = "gFUserCreated")
    protected String gfUserCreated;
    @XmlElement(name = "gFUserFromCode")
    protected String gfUserFromCode;
    @XmlElement(name = "gFUserFromName")
    protected String gfUserFromName;
    @XmlElement(name = "gFUserToCode")
    protected String gfUserToCode;
    @XmlElement(name = "gFUserToName")
    protected String gfUserToName;

    /**
     * 获取age属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置age属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAge(Integer value) {
        this.age = value;
    }

    /**
     * 获取busiBookNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiBookNo() {
        return busiBookNo;
    }

    /**
     * 设置busiBookNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiBookNo(String value) {
        this.busiBookNo = value;
    }

    /**
     * 获取busiDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiDate() {
        return busiDate;
    }

    /**
     * 设置busiDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiDate(String value) {
        this.busiDate = value;
    }

    /**
     * 获取cartons属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCartons() {
        return cartons;
    }

    /**
     * 设置cartons属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCartons(BigDecimal value) {
        this.cartons = value;
    }

    /**
     * 获取feeWeight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFeeWeight() {
        return feeWeight;
    }

    /**
     * 设置feeWeight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFeeWeight(BigDecimal value) {
        this.feeWeight = value;
    }

    /**
     * 获取forSys属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getForSys() {
        return forSys;
    }

    /**
     * 设置forSys属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setForSys(Integer value) {
        this.forSys = value;
    }

    /**
     * 获取goodsCurrencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsCurrencyCode() {
        return goodsCurrencyCode;
    }

    /**
     * 设置goodsCurrencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsCurrencyCode(String value) {
        this.goodsCurrencyCode = value;
    }

    /**
     * 获取goodsValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGoodsValue() {
        return goodsValue;
    }

    /**
     * 设置goodsValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGoodsValue(BigDecimal value) {
        this.goodsValue = value;
    }

    /**
     * 获取grossWeight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    /**
     * 设置grossWeight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGrossWeight(BigDecimal value) {
        this.grossWeight = value;
    }

    /**
     * 获取idQuotationMst属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdQuotationMst() {
        return idQuotationMst;
    }

    /**
     * 设置idQuotationMst属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdQuotationMst(Long value) {
        this.idQuotationMst = value;
    }

    /**
     * 获取idtbPreCalc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdtbPreCalc() {
        return idtbPreCalc;
    }

    /**
     * 设置idtbPreCalc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdtbPreCalc(Long value) {
        this.idtbPreCalc = value;
    }

    /**
     * 获取netWeight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNetWeight() {
        return netWeight;
    }

    /**
     * 设置netWeight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNetWeight(BigDecimal value) {
        this.netWeight = value;
    }

    /**
     * 获取pallets属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPallets() {
        return pallets;
    }

    /**
     * 设置pallets属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPallets(BigDecimal value) {
        this.pallets = value;
    }

    /**
     * 获取paymentTerm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentTerm() {
        return paymentTerm;
    }

    /**
     * 设置paymentTerm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentTerm(String value) {
        this.paymentTerm = value;
    }

    /**
     * 获取pieces属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPieces() {
        return pieces;
    }

    /**
     * 设置pieces属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPieces(BigDecimal value) {
        this.pieces = value;
    }

    /**
     * 获取productCatagoryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCatagoryCode() {
        return productCatagoryCode;
    }

    /**
     * 设置productCatagoryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCatagoryCode(String value) {
        this.productCatagoryCode = value;
    }

    /**
     * 获取productCatagoryName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCatagoryName() {
        return productCatagoryName;
    }

    /**
     * 设置productCatagoryName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCatagoryName(String value) {
        this.productCatagoryName = value;
    }

    /**
     * 获取quotationMstDocNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuotationMstDocNo() {
        return quotationMstDocNo;
    }

    /**
     * 设置quotationMstDocNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuotationMstDocNo(String value) {
        this.quotationMstDocNo = value;
    }

    /**
     * 获取remark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * 获取routeWayFromCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRouteWayFromCode() {
        return routeWayFromCode;
    }

    /**
     * 设置routeWayFromCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRouteWayFromCode(String value) {
        this.routeWayFromCode = value;
    }

    /**
     * 获取routeWayFromName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRouteWayFromName() {
        return routeWayFromName;
    }

    /**
     * 设置routeWayFromName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRouteWayFromName(String value) {
        this.routeWayFromName = value;
    }

    /**
     * 获取routeWayToCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRouteWayToCode() {
        return routeWayToCode;
    }

    /**
     * 设置routeWayToCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRouteWayToCode(String value) {
        this.routeWayToCode = value;
    }

    /**
     * 获取routeWayToName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRouteWayToName() {
        return routeWayToName;
    }

    /**
     * 设置routeWayToName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRouteWayToName(String value) {
        this.routeWayToName = value;
    }

    /**
     * 获取scheduleSheetNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getScheduleSheetNo() {
        return scheduleSheetNo;
    }

    /**
     * 设置scheduleSheetNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setScheduleSheetNo(Long value) {
        this.scheduleSheetNo = value;
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
     * 获取status属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    /**
     * 获取sysCatalog属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSysCatalog() {
        return sysCatalog;
    }

    /**
     * 设置sysCatalog属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSysCatalog(Integer value) {
        this.sysCatalog = value;
    }

    /**
     * 获取tsAudited属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsAudited() {
        return tsAudited;
    }

    /**
     * 设置tsAudited属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsAudited(String value) {
        this.tsAudited = value;
    }

    /**
     * 获取tsChanged属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTsChanged() {
        return tsChanged;
    }

    /**
     * 设置tsChanged属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsChanged(String value) {
        this.tsChanged = value;
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
     * 获取volume属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVolume() {
        return volume;
    }

    /**
     * 设置volume属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVolume(BigDecimal value) {
        this.volume = value;
    }

    /**
     * 获取volumeUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVolumeUnit() {
        return volumeUnit;
    }

    /**
     * 设置volumeUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVolumeUnit(String value) {
        this.volumeUnit = value;
    }

    /**
     * 获取volumeWeight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVolumeWeight() {
        return volumeWeight;
    }

    /**
     * 设置volumeWeight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVolumeWeight(BigDecimal value) {
        this.volumeWeight = value;
    }

    /**
     * 获取weightUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeightUnit() {
        return weightUnit;
    }

    /**
     * 设置weightUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeightUnit(String value) {
        this.weightUnit = value;
    }

    /**
     * 获取gfUserChanged属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserChanged() {
        return gfUserChanged;
    }

    /**
     * 设置gfUserChanged属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserChanged(String value) {
        this.gfUserChanged = value;
    }

    /**
     * 获取gfUserCodCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserCodCode() {
        return gfUserCodCode;
    }

    /**
     * 设置gfUserCodCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserCodCode(String value) {
        this.gfUserCodCode = value;
    }

    /**
     * 获取gfUserCodName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserCodName() {
        return gfUserCodName;
    }

    /**
     * 设置gfUserCodName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserCodName(String value) {
        this.gfUserCodName = value;
    }

    /**
     * 获取gfUserCreated属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserCreated() {
        return gfUserCreated;
    }

    /**
     * 设置gfUserCreated属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserCreated(String value) {
        this.gfUserCreated = value;
    }

    /**
     * 获取gfUserFromCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserFromCode() {
        return gfUserFromCode;
    }

    /**
     * 设置gfUserFromCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserFromCode(String value) {
        this.gfUserFromCode = value;
    }

    /**
     * 获取gfUserFromName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserFromName() {
        return gfUserFromName;
    }

    /**
     * 设置gfUserFromName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserFromName(String value) {
        this.gfUserFromName = value;
    }

    /**
     * 获取gfUserToCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserToCode() {
        return gfUserToCode;
    }

    /**
     * 设置gfUserToCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserToCode(String value) {
        this.gfUserToCode = value;
    }

    /**
     * 获取gfUserToName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserToName() {
        return gfUserToName;
    }

    /**
     * 设置gfUserToName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserToName(String value) {
        this.gfUserToName = value;
    }

}
