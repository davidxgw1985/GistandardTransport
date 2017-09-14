
package com.gistandard.transport.system.webservice.client.payinfo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>platFormInModel complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="platFormInModel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ItemCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FromCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FromProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FromCityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FromCountyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ArriveCountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ArriveProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ArriveCityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ArriveCountyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CarType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CarCapacity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="QuoteType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Volume" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="Weight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="Currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OperateType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="startRecord" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="mileage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "platFormInModel", propOrder = {
    "itemCode",
    "fromCountryCode",
    "fromProvinceCode",
    "fromCityCode",
    "fromCountyCode",
    "arriveCountryCode",
    "arriveProvinceCode",
    "arriveCityCode",
    "arriveCountyCode",
    "carType",
    "carCapacity",
    "quoteType",
    "volume",
    "weight",
    "currency",
    "operateType",
    "startRecord",
    "pageSize",
    "mileage"
})
public class PlatFormInModel {

    @XmlElement(name = "ItemCode")
    protected String itemCode;
    @XmlElement(name = "FromCountryCode")
    protected String fromCountryCode;
    @XmlElement(name = "FromProvinceCode")
    protected String fromProvinceCode;
    @XmlElement(name = "FromCityCode")
    protected String fromCityCode;
    @XmlElement(name = "FromCountyCode")
    protected String fromCountyCode;
    @XmlElement(name = "ArriveCountryCode")
    protected String arriveCountryCode;
    @XmlElement(name = "ArriveProvinceCode")
    protected String arriveProvinceCode;
    @XmlElement(name = "ArriveCityCode")
    protected String arriveCityCode;
    @XmlElement(name = "ArriveCountyCode")
    protected String arriveCountyCode;
    @XmlElement(name = "CarType")
    protected String carType;
    @XmlElement(name = "CarCapacity")
    protected String carCapacity;
    @XmlElement(name = "QuoteType")
    protected Integer quoteType;
    @XmlElement(name = "Volume")
    protected BigDecimal volume;
    @XmlElement(name = "Weight")
    protected BigDecimal weight;
    @XmlElement(name = "Currency")
    protected String currency;
    @XmlElement(name = "OperateType")
    protected String operateType;
    protected Integer startRecord;
    protected Integer pageSize;
    protected BigDecimal mileage;

    /**
     * 获取itemCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置itemCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemCode(String value) {
        this.itemCode = value;
    }

    /**
     * 获取fromCountryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromCountryCode() {
        return fromCountryCode;
    }

    /**
     * 设置fromCountryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromCountryCode(String value) {
        this.fromCountryCode = value;
    }

    /**
     * 获取fromProvinceCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromProvinceCode() {
        return fromProvinceCode;
    }

    /**
     * 设置fromProvinceCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromProvinceCode(String value) {
        this.fromProvinceCode = value;
    }

    /**
     * 获取fromCityCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromCityCode() {
        return fromCityCode;
    }

    /**
     * 设置fromCityCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromCityCode(String value) {
        this.fromCityCode = value;
    }

    /**
     * 获取fromCountyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromCountyCode() {
        return fromCountyCode;
    }

    /**
     * 设置fromCountyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromCountyCode(String value) {
        this.fromCountyCode = value;
    }

    /**
     * 获取arriveCountryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArriveCountryCode() {
        return arriveCountryCode;
    }

    /**
     * 设置arriveCountryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArriveCountryCode(String value) {
        this.arriveCountryCode = value;
    }

    /**
     * 获取arriveProvinceCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArriveProvinceCode() {
        return arriveProvinceCode;
    }

    /**
     * 设置arriveProvinceCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArriveProvinceCode(String value) {
        this.arriveProvinceCode = value;
    }

    /**
     * 获取arriveCityCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArriveCityCode() {
        return arriveCityCode;
    }

    /**
     * 设置arriveCityCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArriveCityCode(String value) {
        this.arriveCityCode = value;
    }

    /**
     * 获取arriveCountyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArriveCountyCode() {
        return arriveCountyCode;
    }

    /**
     * 设置arriveCountyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArriveCountyCode(String value) {
        this.arriveCountyCode = value;
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
     * 获取carCapacity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarCapacity() {
        return carCapacity;
    }

    /**
     * 设置carCapacity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarCapacity(String value) {
        this.carCapacity = value;
    }

    /**
     * 获取quoteType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuoteType() {
        return quoteType;
    }

    /**
     * 设置quoteType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuoteType(Integer value) {
        this.quoteType = value;
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
     * 获取weight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * 设置weight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWeight(BigDecimal value) {
        this.weight = value;
    }

    /**
     * 获取currency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置currency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * 获取operateType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置operateType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateType(String value) {
        this.operateType = value;
    }

    /**
     * 获取startRecord属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStartRecord() {
        return startRecord;
    }

    /**
     * 设置startRecord属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStartRecord(Integer value) {
        this.startRecord = value;
    }

    /**
     * 获取pageSize属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 设置pageSize属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPageSize(Integer value) {
        this.pageSize = value;
    }

    /**
     * 获取mileage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMileage() {
        return mileage;
    }

    /**
     * 设置mileage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMileage(BigDecimal value) {
        this.mileage = value;
    }

}
