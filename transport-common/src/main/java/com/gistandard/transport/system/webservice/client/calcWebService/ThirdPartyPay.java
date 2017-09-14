
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>thirdPartyPay complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="thirdPartyPay"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="docNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUser3FToCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUser3FToName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "thirdPartyPay", propOrder = {
    "docNo",
    "gfUser3FToCode",
    "gfUser3FToName",
    "type"
})
public class ThirdPartyPay {

    protected String docNo;
    @XmlElement(name = "gFUser3FToCode")
    protected String gfUser3FToCode;
    @XmlElement(name = "gFUser3FToName")
    protected String gfUser3FToName;
    protected Integer type;

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
     * 获取gfUser3FToCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUser3FToCode() {
        return gfUser3FToCode;
    }

    /**
     * 设置gfUser3FToCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUser3FToCode(String value) {
        this.gfUser3FToCode = value;
    }

    /**
     * 获取gfUser3FToName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUser3FToName() {
        return gfUser3FToName;
    }

    /**
     * 设置gfUser3FToName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUser3FToName(String value) {
        this.gfUser3FToName = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setType(Integer value) {
        this.type = value;
    }

}
