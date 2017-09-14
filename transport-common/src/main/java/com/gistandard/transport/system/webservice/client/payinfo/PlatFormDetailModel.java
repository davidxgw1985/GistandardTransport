
package com.gistandard.transport.system.webservice.client.payinfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>platFormDetailModel complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="platFormDetailModel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="QuoteNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="QuoteId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="QuoteType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="QuoteTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CarType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CarTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CarCapacity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CarCapacityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TimeLines" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="TimeLinesName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CurrencyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UnitPriceByKilometor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="LightPriceByShare" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="HeavyPriceByShare" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="TotalPriceByTruck" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="TotalWeightByTruck" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="OutPriceListByKilometor" type="{http://webService.external.giifiCalc.transport.gistandard.com/}platFormPriceModel" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="OutPriceListByWeight" type="{http://webService.external.giifiCalc.transport.gistandard.com/}platFormPriceModel" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="OutPriceListBySpecialDelivery" type="{http://webService.external.giifiCalc.transport.gistandard.com/}platFormPriceModel" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="FirstWeightPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="FristWeightUnit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="AdditionalWeightPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="AdditionalWeightUnit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="PredictValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="PredictValueAfterTax" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="RouteId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="StartRoute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EndRoute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "platFormDetailModel", propOrder = {
    "quoteNo",
    "quoteId",
    "quoteType",
    "quoteTypeName",
    "carType",
    "carTypeName",
    "carCapacity",
    "carCapacityName",
    "timeLines",
    "timeLinesName",
    "currency",
    "currencyName",
    "unitPriceByKilometor",
    "lightPriceByShare",
    "heavyPriceByShare",
    "totalPriceByTruck",
    "totalWeightByTruck",
    "outPriceListByKilometor",
    "outPriceListByWeight",
    "outPriceListBySpecialDelivery",
    "firstWeightPrice",
    "fristWeightUnit",
    "additionalWeightPrice",
    "additionalWeightUnit",
    "predictValue",
    "predictValueAfterTax",
    "routeId",
    "startRoute",
    "endRoute"
})
public class PlatFormDetailModel {

    @XmlElement(name = "QuoteNo")
    protected String quoteNo;
    @XmlElement(name = "QuoteId")
    protected Integer quoteId;
    @XmlElement(name = "QuoteType")
    protected Integer quoteType;
    @XmlElement(name = "QuoteTypeName")
    protected String quoteTypeName;
    @XmlElement(name = "CarType")
    protected String carType;
    @XmlElement(name = "CarTypeName")
    protected String carTypeName;
    @XmlElement(name = "CarCapacity")
    protected String carCapacity;
    @XmlElement(name = "CarCapacityName")
    protected String carCapacityName;
    @XmlElement(name = "TimeLines")
    protected Integer timeLines;
    @XmlElement(name = "TimeLinesName")
    protected String timeLinesName;
    @XmlElement(name = "Currency")
    protected String currency;
    @XmlElement(name = "CurrencyName")
    protected String currencyName;
    @XmlElement(name = "UnitPriceByKilometor")
    protected BigDecimal unitPriceByKilometor;
    @XmlElement(name = "LightPriceByShare")
    protected BigDecimal lightPriceByShare;
    @XmlElement(name = "HeavyPriceByShare")
    protected BigDecimal heavyPriceByShare;
    @XmlElement(name = "TotalPriceByTruck")
    protected BigDecimal totalPriceByTruck;
    @XmlElement(name = "TotalWeightByTruck")
    protected BigDecimal totalWeightByTruck;
    @XmlElement(name = "OutPriceListByKilometor", nillable = true)
    protected List<PlatFormPriceModel> outPriceListByKilometor;
    @XmlElement(name = "OutPriceListByWeight", nillable = true)
    protected List<PlatFormPriceModel> outPriceListByWeight;
    @XmlElement(name = "OutPriceListBySpecialDelivery", nillable = true)
    protected List<PlatFormPriceModel> outPriceListBySpecialDelivery;
    @XmlElement(name = "FirstWeightPrice")
    protected BigDecimal firstWeightPrice;
    @XmlElement(name = "FristWeightUnit")
    protected BigDecimal fristWeightUnit;
    @XmlElement(name = "AdditionalWeightPrice")
    protected BigDecimal additionalWeightPrice;
    @XmlElement(name = "AdditionalWeightUnit")
    protected BigDecimal additionalWeightUnit;
    @XmlElement(name = "PredictValue")
    protected BigDecimal predictValue;
    @XmlElement(name = "PredictValueAfterTax")
    protected BigDecimal predictValueAfterTax;
    @XmlElement(name = "RouteId")
    protected String routeId;
    @XmlElement(name = "StartRoute")
    protected String startRoute;
    @XmlElement(name = "EndRoute")
    protected String endRoute;

    /**
     * 获取quoteNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuoteNo() {
        return quoteNo;
    }

    /**
     * 设置quoteNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuoteNo(String value) {
        this.quoteNo = value;
    }

    /**
     * 获取quoteId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuoteId() {
        return quoteId;
    }

    /**
     * 设置quoteId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuoteId(Integer value) {
        this.quoteId = value;
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
     * 获取quoteTypeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuoteTypeName() {
        return quoteTypeName;
    }

    /**
     * 设置quoteTypeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuoteTypeName(String value) {
        this.quoteTypeName = value;
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
     * 获取carTypeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarTypeName() {
        return carTypeName;
    }

    /**
     * 设置carTypeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarTypeName(String value) {
        this.carTypeName = value;
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
     * 获取carCapacityName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarCapacityName() {
        return carCapacityName;
    }

    /**
     * 设置carCapacityName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarCapacityName(String value) {
        this.carCapacityName = value;
    }

    /**
     * 获取timeLines属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTimeLines() {
        return timeLines;
    }

    /**
     * 设置timeLines属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTimeLines(Integer value) {
        this.timeLines = value;
    }

    /**
     * 获取timeLinesName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeLinesName() {
        return timeLinesName;
    }

    /**
     * 设置timeLinesName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeLinesName(String value) {
        this.timeLinesName = value;
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
     * 获取unitPriceByKilometor属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUnitPriceByKilometor() {
        return unitPriceByKilometor;
    }

    /**
     * 设置unitPriceByKilometor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUnitPriceByKilometor(BigDecimal value) {
        this.unitPriceByKilometor = value;
    }

    /**
     * 获取lightPriceByShare属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLightPriceByShare() {
        return lightPriceByShare;
    }

    /**
     * 设置lightPriceByShare属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLightPriceByShare(BigDecimal value) {
        this.lightPriceByShare = value;
    }

    /**
     * 获取heavyPriceByShare属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHeavyPriceByShare() {
        return heavyPriceByShare;
    }

    /**
     * 设置heavyPriceByShare属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHeavyPriceByShare(BigDecimal value) {
        this.heavyPriceByShare = value;
    }

    /**
     * 获取totalPriceByTruck属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalPriceByTruck() {
        return totalPriceByTruck;
    }

    /**
     * 设置totalPriceByTruck属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalPriceByTruck(BigDecimal value) {
        this.totalPriceByTruck = value;
    }

    /**
     * 获取totalWeightByTruck属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalWeightByTruck() {
        return totalWeightByTruck;
    }

    /**
     * 设置totalWeightByTruck属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalWeightByTruck(BigDecimal value) {
        this.totalWeightByTruck = value;
    }

    /**
     * Gets the value of the outPriceListByKilometor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outPriceListByKilometor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutPriceListByKilometor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlatFormPriceModel }
     * 
     * 
     */
    public List<PlatFormPriceModel> getOutPriceListByKilometor() {
        if (outPriceListByKilometor == null) {
            outPriceListByKilometor = new ArrayList<PlatFormPriceModel>();
        }
        return this.outPriceListByKilometor;
    }

    /**
     * Gets the value of the outPriceListByWeight property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outPriceListByWeight property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutPriceListByWeight().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlatFormPriceModel }
     * 
     * 
     */
    public List<PlatFormPriceModel> getOutPriceListByWeight() {
        if (outPriceListByWeight == null) {
            outPriceListByWeight = new ArrayList<PlatFormPriceModel>();
        }
        return this.outPriceListByWeight;
    }

    /**
     * Gets the value of the outPriceListBySpecialDelivery property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outPriceListBySpecialDelivery property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutPriceListBySpecialDelivery().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlatFormPriceModel }
     * 
     * 
     */
    public List<PlatFormPriceModel> getOutPriceListBySpecialDelivery() {
        if (outPriceListBySpecialDelivery == null) {
            outPriceListBySpecialDelivery = new ArrayList<PlatFormPriceModel>();
        }
        return this.outPriceListBySpecialDelivery;
    }

    /**
     * 获取firstWeightPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFirstWeightPrice() {
        return firstWeightPrice;
    }

    /**
     * 设置firstWeightPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFirstWeightPrice(BigDecimal value) {
        this.firstWeightPrice = value;
    }

    /**
     * 获取fristWeightUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFristWeightUnit() {
        return fristWeightUnit;
    }

    /**
     * 设置fristWeightUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFristWeightUnit(BigDecimal value) {
        this.fristWeightUnit = value;
    }

    /**
     * 获取additionalWeightPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdditionalWeightPrice() {
        return additionalWeightPrice;
    }

    /**
     * 设置additionalWeightPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdditionalWeightPrice(BigDecimal value) {
        this.additionalWeightPrice = value;
    }

    /**
     * 获取additionalWeightUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdditionalWeightUnit() {
        return additionalWeightUnit;
    }

    /**
     * 设置additionalWeightUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdditionalWeightUnit(BigDecimal value) {
        this.additionalWeightUnit = value;
    }

    /**
     * 获取predictValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPredictValue() {
        return predictValue;
    }

    /**
     * 设置predictValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPredictValue(BigDecimal value) {
        this.predictValue = value;
    }

    /**
     * 获取predictValueAfterTax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPredictValueAfterTax() {
        return predictValueAfterTax;
    }

    /**
     * 设置predictValueAfterTax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPredictValueAfterTax(BigDecimal value) {
        this.predictValueAfterTax = value;
    }

    /**
     * 获取routeId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRouteId() {
        return routeId;
    }

    /**
     * 设置routeId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRouteId(String value) {
        this.routeId = value;
    }

    /**
     * 获取startRoute属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartRoute() {
        return startRoute;
    }

    /**
     * 设置startRoute属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartRoute(String value) {
        this.startRoute = value;
    }

    /**
     * 获取endRoute属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndRoute() {
        return endRoute;
    }

    /**
     * 设置endRoute属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndRoute(String value) {
        this.endRoute = value;
    }

}
