
package com.gistandard.transport.pay.webservice.generalAcct;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>queryMSInAndOut complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="queryMSInAndOut"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="gFUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="beginDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="payFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="startRecord" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
@XmlType(name = "queryMSInAndOut", propOrder = {
    "gfUserCode",
    "beginDate",
    "endDate",
    "payFlag",
    "startRecord",
    "pageSize"
})
public class QueryMSInAndOut {

    @XmlElement(name = "gFUserCode")
    protected String gfUserCode;
    protected String beginDate;
    protected String endDate;
    protected String payFlag;
    protected int startRecord;
    protected int pageSize;

    /**
     * 获取gfUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserCode() {
        return gfUserCode;
    }

    /**
     * 设置gfUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserCode(String value) {
        this.gfUserCode = value;
    }

    /**
     * 获取beginDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeginDate() {
        return beginDate;
    }

    /**
     * 设置beginDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeginDate(String value) {
        this.beginDate = value;
    }

    /**
     * 获取endDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 设置endDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * 获取payFlag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayFlag() {
        return payFlag;
    }

    /**
     * 设置payFlag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayFlag(String value) {
        this.payFlag = value;
    }

    /**
     * 获取startRecord属性的值。
     * 
     */
    public int getStartRecord() {
        return startRecord;
    }

    /**
     * 设置startRecord属性的值。
     * 
     */
    public void setStartRecord(int value) {
        this.startRecord = value;
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
