
package com.gistandard.transport.order.webservice.client.merchant.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>newMobileToWHubParam complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="newMobileToWHubParam"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="busiNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="createUserId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="operAccount_id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="parentDispatchId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "newMobileToWHubParam", propOrder = {
    "busiNo",
    "createUserId",
    "operAccountId",
    "parentDispatchId",
    "type"
})
public class NewMobileToWHubParam {

    protected String busiNo;
    protected Integer createUserId;
    @XmlElement(name = "operAccount_id")
    protected Integer operAccountId;
    protected Integer parentDispatchId;
    protected String type;

    /**
     * 获取busiNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiNo() {
        return busiNo;
    }

    /**
     * 设置busiNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiNo(String value) {
        this.busiNo = value;
    }

    /**
     * 获取createUserId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置createUserId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCreateUserId(Integer value) {
        this.createUserId = value;
    }

    /**
     * 获取operAccountId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOperAccountId() {
        return operAccountId;
    }

    /**
     * 设置operAccountId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOperAccountId(Integer value) {
        this.operAccountId = value;
    }

    /**
     * 获取parentDispatchId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getParentDispatchId() {
        return parentDispatchId;
    }

    /**
     * 设置parentDispatchId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setParentDispatchId(Integer value) {
        this.parentDispatchId = value;
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
