
package com.gistandard.transport.calculate.webservice.generalAcctService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>confirmPaymentResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="confirmPaymentResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="confirmPayment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmPaymentResponse", propOrder = {
    "confirmPayment"
})
public class ConfirmPaymentResponse {

    protected String confirmPayment;

    /**
     * 获取confirmPayment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfirmPayment() {
        return confirmPayment;
    }

    /**
     * 设置confirmPayment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfirmPayment(String value) {
        this.confirmPayment = value;
    }

}
