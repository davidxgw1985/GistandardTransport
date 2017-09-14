
package com.gistandard.transport.system.webservice.client.payinfo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>platFormPriceModel complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="platFormPriceModel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PointValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="UnitPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="SortNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PriceRuleType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="AddScalar" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="ScalarUnit" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "platFormPriceModel", propOrder = {
    "pointValue",
    "unitPrice",
    "sortNo",
    "remark",
    "priceRuleType",
    "addScalar",
    "scalarUnit"
})
public class PlatFormPriceModel {

    @XmlElement(name = "PointValue")
    protected BigDecimal pointValue;
    @XmlElement(name = "UnitPrice")
    protected BigDecimal unitPrice;
    @XmlElement(name = "SortNo")
    protected Integer sortNo;
    @XmlElement(name = "Remark")
    protected String remark;
    @XmlElement(name = "PriceRuleType")
    protected Integer priceRuleType;
    @XmlElement(name = "AddScalar")
    protected BigDecimal addScalar;
    @XmlElement(name = "ScalarUnit")
    protected Integer scalarUnit;

    /**
     * 获取pointValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPointValue() {
        return pointValue;
    }

    /**
     * 设置pointValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPointValue(BigDecimal value) {
        this.pointValue = value;
    }

    /**
     * 获取unitPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 设置unitPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUnitPrice(BigDecimal value) {
        this.unitPrice = value;
    }

    /**
     * 获取sortNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSortNo() {
        return sortNo;
    }

    /**
     * 设置sortNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSortNo(Integer value) {
        this.sortNo = value;
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
     * 获取priceRuleType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPriceRuleType() {
        return priceRuleType;
    }

    /**
     * 设置priceRuleType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPriceRuleType(Integer value) {
        this.priceRuleType = value;
    }

    /**
     * 获取addScalar属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAddScalar() {
        return addScalar;
    }

    /**
     * 设置addScalar属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAddScalar(BigDecimal value) {
        this.addScalar = value;
    }

    /**
     * 获取scalarUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getScalarUnit() {
        return scalarUnit;
    }

    /**
     * 设置scalarUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setScalarUnit(Integer value) {
        this.scalarUnit = value;
    }

}
