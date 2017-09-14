
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllGiBizOrderCountResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiBizOrderCountResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getAllGiBizOrderCountResult" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiBizOrderCountResponse", propOrder = {
    "getAllGiBizOrderCountResult"
})
public class GetAllGiBizOrderCountResponse {

    protected long getAllGiBizOrderCountResult;

    /**
     * 获取getAllGiBizOrderCountResult属性的值。
     * 
     */
    public long getGetAllGiBizOrderCountResult() {
        return getAllGiBizOrderCountResult;
    }

    /**
     * 设置getAllGiBizOrderCountResult属性的值。
     * 
     */
    public void setGetAllGiBizOrderCountResult(long value) {
        this.getAllGiBizOrderCountResult = value;
    }

}
