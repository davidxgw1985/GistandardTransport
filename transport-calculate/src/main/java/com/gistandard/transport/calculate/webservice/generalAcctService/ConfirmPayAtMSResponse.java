
package com.gistandard.transport.calculate.webservice.generalAcctService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>confirmPayAtMSResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="confirmPayAtMSResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="confirmPayAtMS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmPayAtMSResponse", propOrder = {
    "confirmPayAtMS"
})
public class ConfirmPayAtMSResponse {

    protected String confirmPayAtMS;

    /**
     * 获取confirmPayAtMS属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfirmPayAtMS() {
        return confirmPayAtMS;
    }

    /**
     * 设置confirmPayAtMS属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfirmPayAtMS(String value) {
        this.confirmPayAtMS = value;
    }

}
