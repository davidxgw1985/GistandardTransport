
package com.gistandard.transport.calculate.webservice.generalAcctService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>confirmPayResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="confirmPayResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="confirmPay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmPayResponse", propOrder = {
    "confirmPay"
})
public class ConfirmPayResponse {

    protected String confirmPay;

    /**
     * 获取confirmPay属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfirmPay() {
        return confirmPay;
    }

    /**
     * 设置confirmPay属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfirmPay(String value) {
        this.confirmPay = value;
    }

}
