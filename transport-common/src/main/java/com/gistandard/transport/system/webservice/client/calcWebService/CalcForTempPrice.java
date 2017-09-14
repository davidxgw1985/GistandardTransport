
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CalcForTempPrice complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CalcForTempPrice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="systemFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="allCalcParamaters" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalcForTempPrice", propOrder = {
    "systemFlag",
    "allCalcParamaters"
})
public class CalcForTempPrice {

    protected String systemFlag;
    protected String allCalcParamaters;

    /**
     * 获取systemFlag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemFlag() {
        return systemFlag;
    }

    /**
     * 设置systemFlag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemFlag(String value) {
        this.systemFlag = value;
    }

    /**
     * 获取allCalcParamaters属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllCalcParamaters() {
        return allCalcParamaters;
    }

    /**
     * 设置allCalcParamaters属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllCalcParamaters(String value) {
        this.allCalcParamaters = value;
    }

}
