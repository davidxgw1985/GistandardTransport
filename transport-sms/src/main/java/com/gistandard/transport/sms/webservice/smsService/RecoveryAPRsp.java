
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
 *         &lt;element name="ACK" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
    "ack"
})
@XmlRootElement(name = "RecoveryAPRsp")
public class RecoveryAPRsp {

    @XmlElement(name = "ACK")
    protected boolean ack;

    /**
     * 获取ack属性的值。
     * 
     */
    public boolean isACK() {
        return ack;
    }

    /**
     * 设置ack属性的值。
     * 
     */
    public void setACK(boolean value) {
        this.ack = value;
    }

}
