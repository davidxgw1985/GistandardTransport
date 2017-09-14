
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>motorcateQuoteReq complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="motorcateQuoteReq"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}expreessMainOrder"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="motorcateAcctCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="motorcateQuoteAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="vehicleOwnerAcctCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "motorcateQuoteReq", propOrder = {
    "motorcateAcctCode",
    "motorcateQuoteAction",
    "vehicleOwnerAcctCode"
})
@XmlSeeAlso({
    MainOrderNotifyRequest.class
})
public class MotorcateQuoteReq
    extends ExpreessMainOrder
{

    protected String motorcateAcctCode;
    protected String motorcateQuoteAction;
    protected String vehicleOwnerAcctCode;

    /**
     * 获取motorcateAcctCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotorcateAcctCode() {
        return motorcateAcctCode;
    }

    /**
     * 设置motorcateAcctCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotorcateAcctCode(String value) {
        this.motorcateAcctCode = value;
    }

    /**
     * 获取motorcateQuoteAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotorcateQuoteAction() {
        return motorcateQuoteAction;
    }

    /**
     * 设置motorcateQuoteAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotorcateQuoteAction(String value) {
        this.motorcateQuoteAction = value;
    }

    /**
     * 获取vehicleOwnerAcctCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehicleOwnerAcctCode() {
        return vehicleOwnerAcctCode;
    }

    /**
     * 设置vehicleOwnerAcctCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehicleOwnerAcctCode(String value) {
        this.vehicleOwnerAcctCode = value;
    }

}
