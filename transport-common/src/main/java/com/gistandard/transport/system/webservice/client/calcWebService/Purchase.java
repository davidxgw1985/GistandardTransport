
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>purchase complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="purchase"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="purchaseCoupon" type="{http://webService.external.giifiCalc.transport.gistandard.com/}purchaseCoupon" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "purchase", propOrder = {
    "purchaseCoupon"
})
public class Purchase {

    protected PurchaseCoupon purchaseCoupon;

    /**
     * 获取purchaseCoupon属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PurchaseCoupon }
     *     
     */
    public PurchaseCoupon getPurchaseCoupon() {
        return purchaseCoupon;
    }

    /**
     * 设置purchaseCoupon属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PurchaseCoupon }
     *     
     */
    public void setPurchaseCoupon(PurchaseCoupon value) {
        this.purchaseCoupon = value;
    }

}
