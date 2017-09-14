
package com.gistandard.transport.order.webservice.client.merchant.order;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>dispatchExtend complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="dispatchExtend"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://webService.mobileRecOrder.dispatch.transport.gistandard.com/}dispatch"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="createAccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fromAccountId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="fromCustomerId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="quotedType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="routeSchemaId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="toAccountId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="toCustomerId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dispatchExtend", propOrder = {
    "createAccountName",
    "currencyCode",
    "fromAccountId",
    "fromCustomerId",
    "quotedType",
    "routeSchemaId",
    "toAccountId",
    "toCustomerId",
    "totalPrice",
    "type"
})
public class DispatchExtend
    extends Dispatch
{

    protected String createAccountName;
    protected String currencyCode;
    protected int fromAccountId;
    protected int fromCustomerId;
    protected int quotedType;
    protected Integer routeSchemaId;
    protected int toAccountId;
    protected int toCustomerId;
    protected BigDecimal totalPrice;
    protected String type;

    /**
     * 获取createAccountName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateAccountName() {
        return createAccountName;
    }

    /**
     * 设置createAccountName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateAccountName(String value) {
        this.createAccountName = value;
    }

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
     * 获取fromAccountId属性的值。
     * 
     */
    public int getFromAccountId() {
        return fromAccountId;
    }

    /**
     * 设置fromAccountId属性的值。
     * 
     */
    public void setFromAccountId(int value) {
        this.fromAccountId = value;
    }

    /**
     * 获取fromCustomerId属性的值。
     * 
     */
    public int getFromCustomerId() {
        return fromCustomerId;
    }

    /**
     * 设置fromCustomerId属性的值。
     * 
     */
    public void setFromCustomerId(int value) {
        this.fromCustomerId = value;
    }

    /**
     * 获取quotedType属性的值。
     * 
     */
    public int getQuotedType() {
        return quotedType;
    }

    /**
     * 设置quotedType属性的值。
     * 
     */
    public void setQuotedType(int value) {
        this.quotedType = value;
    }

    /**
     * 获取routeSchemaId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRouteSchemaId() {
        return routeSchemaId;
    }

    /**
     * 设置routeSchemaId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRouteSchemaId(Integer value) {
        this.routeSchemaId = value;
    }

    /**
     * 获取toAccountId属性的值。
     * 
     */
    public int getToAccountId() {
        return toAccountId;
    }

    /**
     * 设置toAccountId属性的值。
     * 
     */
    public void setToAccountId(int value) {
        this.toAccountId = value;
    }

    /**
     * 获取toCustomerId属性的值。
     * 
     */
    public int getToCustomerId() {
        return toCustomerId;
    }

    /**
     * 设置toCustomerId属性的值。
     * 
     */
    public void setToCustomerId(int value) {
        this.toCustomerId = value;
    }

    /**
     * 获取totalPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置totalPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalPrice(BigDecimal value) {
        this.totalPrice = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
