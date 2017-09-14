
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllGiBizOrderBizModeCountResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiBizOrderBizModeCountResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getAllGiBizOrderBizModeCountResult" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiBizOrderBizModeCountResponse", propOrder = {
    "getAllGiBizOrderBizModeCountResult"
})
public class GetAllGiBizOrderBizModeCountResponse {

    protected long getAllGiBizOrderBizModeCountResult;

    /**
     * 获取getAllGiBizOrderBizModeCountResult属性的值。
     * 
     */
    public long getGetAllGiBizOrderBizModeCountResult() {
        return getAllGiBizOrderBizModeCountResult;
    }

    /**
     * 设置getAllGiBizOrderBizModeCountResult属性的值。
     * 
     */
    public void setGetAllGiBizOrderBizModeCountResult(long value) {
        this.getAllGiBizOrderBizModeCountResult = value;
    }

}
