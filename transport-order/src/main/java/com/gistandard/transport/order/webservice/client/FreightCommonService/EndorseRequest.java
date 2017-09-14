
package com.gistandard.transport.order.webservice.client.FreightCommonService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EndorseRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EndorseRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="checkCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="endorseType" type="{http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types}EndorseType"/&gt;
 *         &lt;element name="endorsementInfo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="productInfo" type="{http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types}ApprovalProduct"/&gt;
 *         &lt;element name="userInfo" type="{http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types}LoginUser"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EndorseRequest", propOrder = {
    "checkCode",
    "endorseType",
    "endorsementInfo",
    "productInfo",
    "userInfo"
})
public class EndorseRequest {

    @XmlElement(required = true, nillable = true)
    protected String checkCode;
    @XmlElement(required = true, nillable = true)
    protected EndorseType endorseType;
    @XmlElement(required = true, nillable = true)
    protected String endorsementInfo;
    @XmlElement(required = true, nillable = true)
    protected ApprovalProduct productInfo;
    @XmlElement(required = true, nillable = true)
    protected LoginUser userInfo;

    /**
     * 获取checkCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckCode() {
        return checkCode;
    }

    /**
     * 设置checkCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckCode(String value) {
        this.checkCode = value;
    }

    /**
     * 获取endorseType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EndorseType }
     *     
     */
    public EndorseType getEndorseType() {
        return endorseType;
    }

    /**
     * 设置endorseType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EndorseType }
     *     
     */
    public void setEndorseType(EndorseType value) {
        this.endorseType = value;
    }

    /**
     * 获取endorsementInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndorsementInfo() {
        return endorsementInfo;
    }

    /**
     * 设置endorsementInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndorsementInfo(String value) {
        this.endorsementInfo = value;
    }

    /**
     * 获取productInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ApprovalProduct }
     *     
     */
    public ApprovalProduct getProductInfo() {
        return productInfo;
    }

    /**
     * 设置productInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ApprovalProduct }
     *     
     */
    public void setProductInfo(ApprovalProduct value) {
        this.productInfo = value;
    }

    /**
     * 获取userInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LoginUser }
     *     
     */
    public LoginUser getUserInfo() {
        return userInfo;
    }

    /**
     * 设置userInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LoginUser }
     *     
     */
    public void setUserInfo(LoginUser value) {
        this.userInfo = value;
    }

}
