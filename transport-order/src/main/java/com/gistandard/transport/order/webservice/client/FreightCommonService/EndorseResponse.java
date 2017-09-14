
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
 *         &lt;element name="endorseReturn" type="{http://wss.cpic.com.cn/propertyinsurance/commonservice/freight/types}EndorseResponse"/&gt;
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
    "endorseReturn"
})
@XmlRootElement(name = "endorseResponse")
public class EndorseResponse {

    @XmlElement(required = true)
    protected EndorseResponse2 endorseReturn;

    /**
     * 获取endorseReturn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EndorseResponse2 }
     *     
     */
    public EndorseResponse2 getEndorseReturn() {
        return endorseReturn;
    }

    /**
     * 设置endorseReturn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EndorseResponse2 }
     *     
     */
    public void setEndorseReturn(EndorseResponse2 value) {
        this.endorseReturn = value;
    }

}
