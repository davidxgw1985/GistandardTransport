
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getLastMlOrderTraceByBusiNoResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getLastMlOrderTraceByBusiNoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getLastMlOrderTraceByBusiNoResult" type="{http://webservice.manage.gps.com/}mlOrderTrace" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLastMlOrderTraceByBusiNoResponse", propOrder = {
    "getLastMlOrderTraceByBusiNoResult"
})
public class GetLastMlOrderTraceByBusiNoResponse {

    protected MlOrderTrace getLastMlOrderTraceByBusiNoResult;

    /**
     * 获取getLastMlOrderTraceByBusiNoResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MlOrderTrace }
     *     
     */
    public MlOrderTrace getGetLastMlOrderTraceByBusiNoResult() {
        return getLastMlOrderTraceByBusiNoResult;
    }

    /**
     * 设置getLastMlOrderTraceByBusiNoResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MlOrderTrace }
     *     
     */
    public void setGetLastMlOrderTraceByBusiNoResult(MlOrderTrace value) {
        this.getLastMlOrderTraceByBusiNoResult = value;
    }

}
