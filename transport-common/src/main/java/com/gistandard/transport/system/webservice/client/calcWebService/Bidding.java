
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>bidding complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="bidding"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="calcForBidding" type="{http://webService.external.giifiCalc.transport.gistandard.com/}calcForBidding" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bidding", propOrder = {
    "calcForBidding"
})
public class Bidding {

    protected CalcForBidding calcForBidding;

    /**
     * 获取calcForBidding属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CalcForBidding }
     *     
     */
    public CalcForBidding getCalcForBidding() {
        return calcForBidding;
    }

    /**
     * 设置calcForBidding属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CalcForBidding }
     *     
     */
    public void setCalcForBidding(CalcForBidding value) {
        this.calcForBidding = value;
    }

}
