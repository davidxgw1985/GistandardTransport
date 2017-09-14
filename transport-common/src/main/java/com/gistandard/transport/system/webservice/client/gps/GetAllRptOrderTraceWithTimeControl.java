
package com.gistandard.transport.system.webservice.client.gps;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>getAllRptOrderTraceWithTimeControl complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllRptOrderTraceWithTimeControl"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="districtName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gicbFenceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="busiDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllRptOrderTraceWithTimeControl", propOrder = {
    "cityName",
    "districtName",
    "gicbFenceName",
    "busiDate",
    "productType"
})
public class GetAllRptOrderTraceWithTimeControl {

    protected String cityName;
    protected String districtName;
    protected String gicbFenceName;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date busiDate;
    protected String productType;

    /**
     * 获取cityName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置cityName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityName(String value) {
        this.cityName = value;
    }

    /**
     * 获取districtName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 设置districtName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictName(String value) {
        this.districtName = value;
    }

    /**
     * 获取gicbFenceName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGicbFenceName() {
        return gicbFenceName;
    }

    /**
     * 设置gicbFenceName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGicbFenceName(String value) {
        this.gicbFenceName = value;
    }

    /**
     * 获取busiDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getBusiDate() {
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
    public void setBusiDate(Date value) {
        this.busiDate = value;
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

}
