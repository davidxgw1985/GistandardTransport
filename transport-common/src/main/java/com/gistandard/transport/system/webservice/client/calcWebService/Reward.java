
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>reward complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="reward"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="calcForReward" type="{http://webService.external.giifiCalc.transport.gistandard.com/}calcForReward" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reward", propOrder = {
    "calcForReward"
})
public class Reward {

    protected CalcForReward calcForReward;

    /**
     * 获取calcForReward属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CalcForReward }
     *     
     */
    public CalcForReward getCalcForReward() {
        return calcForReward;
    }

    /**
     * 设置calcForReward属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CalcForReward }
     *     
     */
    public void setCalcForReward(CalcForReward value) {
        this.calcForReward = value;
    }

}
