
package com.gistandard.transport.calculate.webservice.generalAcctService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>confirmPayment1Response complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="confirmPayment1Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="confirmPayment1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmPayment1Response", propOrder = {
    "confirmPayment1"
})
public class ConfirmPayment1Response {

    protected String confirmPayment1;

    /**
     * 获取confirmPayment1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfirmPayment1() {
        return confirmPayment1;
    }

    /**
     * 设置confirmPayment1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfirmPayment1(String value) {
        this.confirmPayment1 = value;
    }

}
