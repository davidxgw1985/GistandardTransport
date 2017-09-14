
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getDistanceRunedByTruckResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getDistanceRunedByTruckResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getDistanceRunedByTruckResult" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDistanceRunedByTruckResponse", propOrder = {
    "getDistanceRunedByTruckResult"
})
public class GetDistanceRunedByTruckResponse {

    protected double getDistanceRunedByTruckResult;

    /**
     * 获取getDistanceRunedByTruckResult属性的值。
     * 
     */
    public double getGetDistanceRunedByTruckResult() {
        return getDistanceRunedByTruckResult;
    }

    /**
     * 设置getDistanceRunedByTruckResult属性的值。
     * 
     */
    public void setGetDistanceRunedByTruckResult(double value) {
        this.getDistanceRunedByTruckResult = value;
    }

}
