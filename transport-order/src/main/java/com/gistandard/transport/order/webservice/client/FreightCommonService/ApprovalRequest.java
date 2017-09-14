
package com.gistandard.transport.order.webservice.client.FreightCommonService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ApprovalRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ApprovalRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="checkCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="formCommit" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="policyInfo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlType(name = "ApprovalRequest", propOrder = {
    "checkCode",
    "formCommit",
    "policyInfo",
    "productInfo",
    "userInfo"
})
public class ApprovalRequest {

    @XmlElement(required = true, nillable = true)
    protected String checkCode;
    protected boolean formCommit;
    @XmlElement(required = true, nillable = true)
    protected String policyInfo;
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
     * 获取formCommit属性的值。
     * 
     */
    public boolean isFormCommit() {
        return formCommit;
    }

    /**
     * 设置formCommit属性的值。
     * 
     */
    public void setFormCommit(boolean value) {
        this.formCommit = value;
    }

    /**
     * 获取policyInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyInfo() {
        return policyInfo;
    }

    /**
     * 设置policyInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyInfo(String value) {
        this.policyInfo = value;
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
