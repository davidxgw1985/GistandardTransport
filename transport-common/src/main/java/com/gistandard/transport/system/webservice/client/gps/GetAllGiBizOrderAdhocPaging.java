
package com.gistandard.transport.system.webservice.client.gps;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>getAllGiBizOrderAdhocPaging complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiBizOrderAdhocPaging"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sourceProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sourceCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sourceDistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="destProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="destCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="destDistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="docNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="roleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tsCreatedFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="tsCreatedTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="pageNum" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiBizOrderAdhocPaging", propOrder = {
    "sourceProvince",
    "sourceCity",
    "sourceDistrict",
    "destProvince",
    "destCity",
    "destDistrict",
    "docNo",
    "roleCode",
    "tsCreatedFrom",
    "tsCreatedTo",
    "pageNum",
    "pageSize"
})
public class GetAllGiBizOrderAdhocPaging {

    protected String sourceProvince;
    protected String sourceCity;
    protected String sourceDistrict;
    protected String destProvince;
    protected String destCity;
    protected String destDistrict;
    protected String docNo;
    protected String roleCode;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsCreatedFrom;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsCreatedTo;
    protected int pageNum;
    protected int pageSize;

    /**
     * 获取sourceProvince属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceProvince() {
        return sourceProvince;
    }

    /**
     * 设置sourceProvince属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceProvince(String value) {
        this.sourceProvince = value;
    }

    /**
     * 获取sourceCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceCity() {
        return sourceCity;
    }

    /**
     * 设置sourceCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceCity(String value) {
        this.sourceCity = value;
    }

    /**
     * 获取sourceDistrict属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceDistrict() {
        return sourceDistrict;
    }

    /**
     * 设置sourceDistrict属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceDistrict(String value) {
        this.sourceDistrict = value;
    }

    /**
     * 获取destProvince属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestProvince() {
        return destProvince;
    }

    /**
     * 设置destProvince属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestProvince(String value) {
        this.destProvince = value;
    }

    /**
     * 获取destCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestCity() {
        return destCity;
    }

    /**
     * 设置destCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestCity(String value) {
        this.destCity = value;
    }

    /**
     * 获取destDistrict属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestDistrict() {
        return destDistrict;
    }

    /**
     * 设置destDistrict属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestDistrict(String value) {
        this.destDistrict = value;
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
     * 获取roleCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置roleCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleCode(String value) {
        this.roleCode = value;
    }

    /**
     * 获取tsCreatedFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTsCreatedFrom() {
        return tsCreatedFrom;
    }

    /**
     * 设置tsCreatedFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsCreatedFrom(Date value) {
        this.tsCreatedFrom = value;
    }

    /**
     * 获取tsCreatedTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTsCreatedTo() {
        return tsCreatedTo;
    }

    /**
     * 设置tsCreatedTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsCreatedTo(Date value) {
        this.tsCreatedTo = value;
    }

    /**
     * 获取pageNum属性的值。
     * 
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * 设置pageNum属性的值。
     * 
     */
    public void setPageNum(int value) {
        this.pageNum = value;
    }

    /**
     * 获取pageSize属性的值。
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置pageSize属性的值。
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
    }

}
