
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CancleCheckBill complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CancleCheckBill"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="gFUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gFUserPwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="validBillno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CancleCheckBill", propOrder = {
    "gfUserCode",
    "gfUserPwd",
    "validBillno"
})
public class CancleCheckBill {

    @XmlElement(name = "gFUserCode")
    protected String gfUserCode;
    @XmlElement(name = "gFUserPwd")
    protected String gfUserPwd;
    protected String validBillno;

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
     * 获取gfUserPwd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserPwd() {
        return gfUserPwd;
    }

    /**
     * 设置gfUserPwd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserPwd(String value) {
        this.gfUserPwd = value;
    }

    /**
     * 获取validBillno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidBillno() {
        return validBillno;
    }

    /**
     * 设置validBillno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidBillno(String value) {
        this.validBillno = value;
    }

}
