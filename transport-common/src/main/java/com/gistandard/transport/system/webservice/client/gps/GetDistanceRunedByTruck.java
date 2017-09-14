
package com.gistandard.transport.system.webservice.client.gps;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>getDistanceRunedByTruck complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getDistanceRunedByTruck"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="truckCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dateBegin" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dateEnd" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDistanceRunedByTruck", propOrder = {
    "truckCode",
    "dateBegin",
    "dateEnd"
})
public class GetDistanceRunedByTruck {

    protected String truckCode;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date dateBegin;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date dateEnd;

    /**
     * 获取truckCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTruckCode() {
        return truckCode;
    }

    /**
     * 设置truckCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTruckCode(String value) {
        this.truckCode = value;
    }

    /**
     * 获取dateBegin属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDateBegin() {
        return dateBegin;
    }

    /**
     * 设置dateBegin属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateBegin(Date value) {
        this.dateBegin = value;
    }

    /**
     * 获取dateEnd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * 设置dateEnd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateEnd(Date value) {
        this.dateEnd = value;
    }

}
