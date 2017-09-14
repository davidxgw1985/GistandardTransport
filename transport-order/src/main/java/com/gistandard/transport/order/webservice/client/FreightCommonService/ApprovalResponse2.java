
package com.gistandard.transport.order.webservice.client.FreightCommonService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ApprovalResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ApprovalResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="checkCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="policyInfo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="sysMessage" type="{http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types}SysMessage"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApprovalResponse", propOrder = {
    "checkCode",
    "policyInfo",
    "sysMessage"
})
public class ApprovalResponse2 {

    @XmlElement(required = true, nillable = true)
    protected String checkCode;
    @XmlElement(required = true, nillable = true)
    protected String policyInfo;
    @XmlElement(required = true, nillable = true)
    protected SysMessage sysMessage;

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
     * 获取sysMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SysMessage }
     *     
     */
    public SysMessage getSysMessage() {
        return sysMessage;
    }

    /**
     * 设置sysMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SysMessage }
     *     
     */
    public void setSysMessage(SysMessage value) {
        this.sysMessage = value;
    }

}
