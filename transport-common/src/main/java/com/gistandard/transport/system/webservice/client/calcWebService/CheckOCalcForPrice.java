
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>checkOCalcForPrice complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="checkOCalcForPrice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://webService.external.giifiCalc.transport.gistandard.com/}oCalcForPrice"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="scanBusiNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkOCalcForPrice", propOrder = {
    "scanBusiNo"
})
public class CheckOCalcForPrice
    extends OCalcForPrice
{

    protected String scanBusiNo;

    /**
     * 获取scanBusiNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScanBusiNo() {
        return scanBusiNo;
    }

    /**
     * 设置scanBusiNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScanBusiNo(String value) {
        this.scanBusiNo = value;
    }

}
