
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RegResult" type="{http://www.csapi.org/schema/ap}APRegResult"/&gt;
 *         &lt;element name="NextInterval" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "regResult",
    "nextInterval"
})
@XmlRootElement(name = "APRegistrationRsp")
public class APRegistrationRsp {

    @XmlElement(name = "RegResult", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected APRegResult regResult;
    @XmlElement(name = "NextInterval")
    protected int nextInterval;

    /**
     * 获取regResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link APRegResult }
     *     
     */
    public APRegResult getRegResult() {
        return regResult;
    }

    /**
     * 设置regResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link APRegResult }
     *     
     */
    public void setRegResult(APRegResult value) {
        this.regResult = value;
    }

    /**
     * 获取nextInterval属性的值。
     * 
     */
    public int getNextInterval() {
        return nextInterval;
    }

    /**
     * 设置nextInterval属性的值。
     * 
     */
    public void setNextInterval(int value) {
        this.nextInterval = value;
    }

}
