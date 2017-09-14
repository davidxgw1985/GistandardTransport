
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;


/**
 * <p>TimeMetric complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="TimeMetric"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Metric" type="{http://www.csapi.org/schema/common/v2_0}TimeMetricsValues"/&gt;
 *         &lt;element name="Units" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeMetric", namespace = "http://www.csapi.org/schema/common/v2_0", propOrder = {
    "metric",
    "units"
})
public class TimeMetric {

    @XmlElement(name = "Metric", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected TimeMetricsValues metric;
    @XmlElement(name = "Units")
    protected int units;

    /**
     * 获取metric属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeMetricsValues }
     *     
     */
    public TimeMetricsValues getMetric() {
        return metric;
    }

    /**
     * 设置metric属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeMetricsValues }
     *     
     */
    public void setMetric(TimeMetricsValues value) {
        this.metric = value;
    }

    /**
     * 获取units属性的值。
     * 
     */
    public int getUnits() {
        return units;
    }

    /**
     * 设置units属性的值。
     * 
     */
    public void setUnits(int value) {
        this.units = value;
    }

}
