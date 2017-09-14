
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>QueryPlatformQuote complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="QueryPlatformQuote"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="systemFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ItemCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UserScope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryPlatformQuote", propOrder = {
    "systemFlag",
    "itemCode",
    "userScope"
})
public class QueryPlatformQuote {

    protected String systemFlag;
    @XmlElement(name = "ItemCode")
    protected String itemCode;
    @XmlElement(name = "UserScope")
    protected String userScope;

    /**
     * 获取systemFlag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemFlag() {
        return systemFlag;
    }

    /**
     * 设置systemFlag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemFlag(String value) {
        this.systemFlag = value;
    }

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
     * 获取userScope属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserScope() {
        return userScope;
    }

    /**
     * 设置userScope属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserScope(String value) {
        this.userScope = value;
    }

}
