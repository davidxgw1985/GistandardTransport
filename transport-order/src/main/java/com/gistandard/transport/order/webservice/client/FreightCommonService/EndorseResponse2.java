
package com.gistandard.transport.order.webservice.client.FreightCommonService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EndorseResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EndorseResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="checkCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="endorsementInfo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlType(name = "EndorseResponse", propOrder = {
    "checkCode",
    "endorsementInfo",
    "sysMessage"
})
public class EndorseResponse2 {

    @XmlElement(required = true, nillable = true)
    protected String checkCode;
    @XmlElement(required = true, nillable = true)
    protected String endorsementInfo;
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
