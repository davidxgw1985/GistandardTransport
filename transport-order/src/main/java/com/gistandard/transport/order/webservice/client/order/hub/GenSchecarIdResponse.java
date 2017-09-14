
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>genSchecarIdResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="genSchecarIdResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="genSchecarIdResult" type="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}genSchecarIdRequestResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "genSchecarIdResponse", propOrder = {
    "genSchecarIdResult"
})
public class GenSchecarIdResponse {

    protected GenSchecarIdRequestResponse genSchecarIdResult;

    /**
     * 获取genSchecarIdResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GenSchecarIdRequestResponse }
     *     
     */
    public GenSchecarIdRequestResponse getGenSchecarIdResult() {
        return genSchecarIdResult;
    }

    /**
     * 设置genSchecarIdResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GenSchecarIdRequestResponse }
     *     
     */
    public void setGenSchecarIdResult(GenSchecarIdRequestResponse value) {
        this.genSchecarIdResult = value;
    }

}
