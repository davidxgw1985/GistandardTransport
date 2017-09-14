
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllMiStationByStopId complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllMiStationByStopId"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idGiMiRoutePlan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="miStopId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllMiStationByStopId", propOrder = {
    "idGiMiRoutePlan",
    "miStopId"
})
public class GetAllMiStationByStopId {

    protected String idGiMiRoutePlan;
    protected String miStopId;

    /**
     * 获取idGiMiRoutePlan属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdGiMiRoutePlan() {
        return idGiMiRoutePlan;
    }

    /**
     * 设置idGiMiRoutePlan属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdGiMiRoutePlan(String value) {
        this.idGiMiRoutePlan = value;
    }

    /**
     * 获取miStopId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiStopId() {
        return miStopId;
    }

    /**
     * 设置miStopId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiStopId(String value) {
        this.miStopId = value;
    }

}
