
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>deleteBusinessOrderVO complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="deleteBusinessOrderVO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="businessOrderNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteBusinessOrderVO", propOrder = {
    "businessOrderNO",
    "stationCode"
})
public class DeleteBusinessOrderVO {

    protected String businessOrderNO;
    protected String stationCode;

    /**
     * 获取businessOrderNO属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessOrderNO() {
        return businessOrderNO;
    }

    /**
     * 设置businessOrderNO属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessOrderNO(String value) {
        this.businessOrderNO = value;
    }

    /**
     * 获取stationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationCode() {
        return stationCode;
    }

    /**
     * 设置stationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationCode(String value) {
        this.stationCode = value;
    }

}
