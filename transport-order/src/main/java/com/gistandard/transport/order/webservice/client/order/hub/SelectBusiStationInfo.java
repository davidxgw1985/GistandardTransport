
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>selectBusiStationInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="selectBusiStationInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="selectBusiStationInfoRequest" type="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}selectBusiStationInfoRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "selectBusiStationInfo", propOrder = {
    "selectBusiStationInfoRequest"
})
public class SelectBusiStationInfo {

    protected SelectBusiStationInfoRequest selectBusiStationInfoRequest;

    /**
     * 获取selectBusiStationInfoRequest属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SelectBusiStationInfoRequest }
     *     
     */
    public SelectBusiStationInfoRequest getSelectBusiStationInfoRequest() {
        return selectBusiStationInfoRequest;
    }

    /**
     * 设置selectBusiStationInfoRequest属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SelectBusiStationInfoRequest }
     *     
     */
    public void setSelectBusiStationInfoRequest(SelectBusiStationInfoRequest value) {
        this.selectBusiStationInfoRequest = value;
    }

}
