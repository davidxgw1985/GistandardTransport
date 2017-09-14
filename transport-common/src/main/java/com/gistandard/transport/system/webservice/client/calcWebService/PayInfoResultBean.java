
package com.gistandard.transport.system.webservice.client.calcWebService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>payInfoResultBean complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="payInfoResultBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="allPayInfoBeam" type="{http://webService.external.giifiCalc.transport.gistandard.com/}payInfoBeam" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="busiBookNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "payInfoResultBean", propOrder = {
    "allPayInfoBeam",
    "busiBookNo",
    "message",
    "resultCode"
})
public class PayInfoResultBean {

    @XmlElement(nillable = true)
    protected List<PayInfoBeam> allPayInfoBeam;
    protected String busiBookNo;
    protected String message;
    protected int resultCode;

    /**
     * Gets the value of the allPayInfoBeam property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allPayInfoBeam property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllPayInfoBeam().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PayInfoBeam }
     * 
     * 
     */
    public List<PayInfoBeam> getAllPayInfoBeam() {
        if (allPayInfoBeam == null) {
            allPayInfoBeam = new ArrayList<PayInfoBeam>();
        }
        return this.allPayInfoBeam;
    }

    /**
     * 获取busiBookNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiBookNo() {
        return busiBookNo;
    }

    /**
     * 设置busiBookNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiBookNo(String value) {
        this.busiBookNo = value;
    }

    /**
     * 获取message属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置message属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * 获取resultCode属性的值。
     * 
     */
    public int getResultCode() {
        return resultCode;
    }

    /**
     * 设置resultCode属性的值。
     * 
     */
    public void setResultCode(int value) {
        this.resultCode = value;
    }

}
