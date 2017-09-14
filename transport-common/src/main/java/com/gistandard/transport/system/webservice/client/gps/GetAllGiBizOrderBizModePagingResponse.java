
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllGiBizOrderBizModePagingResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiBizOrderBizModePagingResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getAllGiBizOrderBizModePagingResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiBizOrderBizModePagingResponse", propOrder = {
    "getAllGiBizOrderBizModePagingResult"
})
public class GetAllGiBizOrderBizModePagingResponse {

    protected String getAllGiBizOrderBizModePagingResult;

    /**
     * 获取getAllGiBizOrderBizModePagingResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetAllGiBizOrderBizModePagingResult() {
        return getAllGiBizOrderBizModePagingResult;
    }

    /**
     * 设置getAllGiBizOrderBizModePagingResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetAllGiBizOrderBizModePagingResult(String value) {
        this.getAllGiBizOrderBizModePagingResult = value;
    }

}
