
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
 *         &lt;element name="LogoutResult" type="{http://www.csapi.org/schema/ap}APLogoutResult"/&gt;
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
    "logoutResult"
})
@XmlRootElement(name = "APLogOutRsp")
public class APLogOutRsp {

    @XmlElement(name = "LogoutResult", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected APLogoutResult logoutResult;

    /**
     * 获取logoutResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link APLogoutResult }
     *     
     */
    public APLogoutResult getLogoutResult() {
        return logoutResult;
    }

    /**
     * 设置logoutResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link APLogoutResult }
     *     
     */
    public void setLogoutResult(APLogoutResult value) {
        this.logoutResult = value;
    }

}
