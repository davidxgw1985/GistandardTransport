
package com.gistandard.transport.order.webservice.client.FreightCommonService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="request" type="{http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types}ApprovalRequest"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "request"
})
@XmlRootElement(name = "claimReportInfo")
public class ClaimReportInfo {

    @XmlElement(required = true)
    protected ApprovalRequest request;

    /**
     * 获取request属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ApprovalRequest }
     *     
     */
    public ApprovalRequest getRequest() {
        return request;
    }

    /**
     * 设置request属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ApprovalRequest }
     *     
     */
    public void setRequest(ApprovalRequest value) {
        this.request = value;
    }

}
