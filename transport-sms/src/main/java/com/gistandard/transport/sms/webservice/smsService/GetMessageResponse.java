
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
 *         &lt;element name="mmsMessage" type="{http://www.csapi.org/schema/mms}MmsMessage"/&gt;
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
    "mmsMessage"
})
@XmlRootElement(name = "getMessageResponse", namespace = "http://www.csapi.org/schema/mms")
public class GetMessageResponse {

    @XmlElement(required = true)
    protected MmsMessage mmsMessage;

    /**
     * 获取mmsMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MmsMessage }
     *     
     */
    public MmsMessage getMmsMessage() {
        return mmsMessage;
    }

    /**
     * 设置mmsMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MmsMessage }
     *     
     */
    public void setMmsMessage(MmsMessage value) {
        this.mmsMessage = value;
    }

}
