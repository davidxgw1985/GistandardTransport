
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>QueryIarPayinfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="QueryIarPayinfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="busiBookNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dispatchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="scheducarno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="GFUserFromCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="validCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="GFUserToCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ValidBillDocNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryIarPayinfo", propOrder = {
    "busiBookNo",
    "dispatchId",
    "scheducarno",
    "gfUserFromCode",
    "validCode",
    "gfUserToCode",
    "validBillDocNo"
})
public class QueryIarPayinfo {

    protected String busiBookNo;
    protected String dispatchId;
    protected String scheducarno;
    @XmlElement(name = "GFUserFromCode")
    protected String gfUserFromCode;
    protected String validCode;
    @XmlElement(name = "GFUserToCode")
    protected String gfUserToCode;
    @XmlElement(name = "ValidBillDocNo")
    protected String validBillDocNo;

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
     * 获取dispatchId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispatchId() {
        return dispatchId;
    }

    /**
     * 设置dispatchId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispatchId(String value) {
        this.dispatchId = value;
    }

    /**
     * 获取scheducarno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheducarno() {
        return scheducarno;
    }

    /**
     * 设置scheducarno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheducarno(String value) {
        this.scheducarno = value;
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
     * 获取validCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidCode() {
        return validCode;
    }

    /**
     * 设置validCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidCode(String value) {
        this.validCode = value;
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
     * 获取validBillDocNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidBillDocNo() {
        return validBillDocNo;
    }

    /**
     * 设置validBillDocNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidBillDocNo(String value) {
        this.validBillDocNo = value;
    }

}
