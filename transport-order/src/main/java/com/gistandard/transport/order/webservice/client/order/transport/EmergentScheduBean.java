
package com.gistandard.transport.order.webservice.client.order.transport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>emergentScheduBean complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="emergentScheduBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="boxId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="driver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="scheduCarNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="truckCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="truckType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="vehicleId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "emergentScheduBean", propOrder = {
    "boxId",
    "driver",
    "scheduCarNo",
    "truckCode",
    "truckType",
    "vehicleId"
})
public class EmergentScheduBean {

    protected Integer boxId;
    protected String driver;
    protected String scheduCarNo;
    protected String truckCode;
    protected String truckType;
    protected Integer vehicleId;

    /**
     * 获取boxId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBoxId() {
        return boxId;
    }

    /**
     * 设置boxId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBoxId(Integer value) {
        this.boxId = value;
    }

    /**
     * 获取driver属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver() {
        return driver;
    }

    /**
     * 设置driver属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver(String value) {
        this.driver = value;
    }

    /**
     * 获取scheduCarNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduCarNo() {
        return scheduCarNo;
    }

    /**
     * 设置scheduCarNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduCarNo(String value) {
        this.scheduCarNo = value;
    }

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
     * 获取truckType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTruckType() {
        return truckType;
    }

    /**
     * 设置truckType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTruckType(String value) {
        this.truckType = value;
    }

    /**
     * 获取vehicleId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVehicleId() {
        return vehicleId;
    }

    /**
     * 设置vehicleId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVehicleId(Integer value) {
        this.vehicleId = value;
    }

}
