
package com.gistandard.transport.system.webservice.client.calcWebService;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>platformQuote complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="platformQuote"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://webService.external.giifiCalc.transport.gistandard.com/}dataResult"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="itemCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="itemId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="itemType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="o2id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="quoteNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quoteType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="rentPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="userScope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "platformQuote", propOrder = {
    "currencyCode",
    "itemCode",
    "itemId",
    "itemName",
    "itemType",
    "name",
    "o2Id",
    "price",
    "quoteNo",
    "quoteType",
    "rentPrice",
    "userScope"
})
public class PlatformQuote
    extends DataResult
{

    protected String currencyCode;
    protected String itemCode;
    protected int itemId;
    protected String itemName;
    protected int itemType;
    protected String name;
    @XmlElement(name = "o2id")
    protected String o2Id;
    protected BigDecimal price;
    protected String quoteNo;
    protected Integer quoteType;
    protected BigDecimal rentPrice;
    protected String userScope;

    /**
     * 获取currencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 设置currencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
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
     * 获取itemId属性的值。
     * 
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * 设置itemId属性的值。
     * 
     */
    public void setItemId(int value) {
        this.itemId = value;
    }

    /**
     * 获取itemName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置itemName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemName(String value) {
        this.itemName = value;
    }

    /**
     * 获取itemType属性的值。
     * 
     */
    public int getItemType() {
        return itemType;
    }

    /**
     * 设置itemType属性的值。
     * 
     */
    public void setItemType(int value) {
        this.itemType = value;
    }

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取o2Id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getO2Id() {
        return o2Id;
    }

    /**
     * 设置o2Id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setO2Id(String value) {
        this.o2Id = value;
    }

    /**
     * 获取price属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置price属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

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
     * 获取rentPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    /**
     * 设置rentPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRentPrice(BigDecimal value) {
        this.rentPrice = value;
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
