
package com.gistandard.transport.order.webservice.client.merchant.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NewMobileToWHub complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="NewMobileToWHub"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://webService.mobileRecOrder.dispatch.transport.gistandard.com/}newMobileToWHubParam" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NewMobileToWHub", propOrder = {
    "arg0"
})
public class NewMobileToWHub {

    protected NewMobileToWHubParam arg0;

    /**
     * 获取arg0属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NewMobileToWHubParam }
     *     
     */
    public NewMobileToWHubParam getArg0() {
        return arg0;
    }

    /**
     * 设置arg0属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NewMobileToWHubParam }
     *     
     */
    public void setArg0(NewMobileToWHubParam value) {
        this.arg0 = value;
    }

}
