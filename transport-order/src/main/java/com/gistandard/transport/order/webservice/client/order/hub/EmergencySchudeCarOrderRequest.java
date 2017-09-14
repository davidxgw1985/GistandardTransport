
package com.gistandard.transport.order.webservice.client.order.hub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>emergencySchudeCarOrderRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="emergencySchudeCarOrderRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}serviceBaseRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="empreessSchudeCarOrderList" type="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}empreessSchudeCarOrder" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="revUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="revUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="revUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "emergencySchudeCarOrderRequest", propOrder = {
    "empreessSchudeCarOrderList",
    "revUser",
    "revUserId",
    "revUserTel"
})
public class EmergencySchudeCarOrderRequest
    extends ServiceBaseRequest
{

    @XmlElement(nillable = true)
    protected List<EmpreessSchudeCarOrder> empreessSchudeCarOrderList;
    protected String revUser;
    protected String revUserId;
    protected String revUserTel;

    /**
     * Gets the value of the empreessSchudeCarOrderList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the empreessSchudeCarOrderList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmpreessSchudeCarOrderList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmpreessSchudeCarOrder }
     * 
     * 
     */
    public List<EmpreessSchudeCarOrder> getEmpreessSchudeCarOrderList() {
        if (empreessSchudeCarOrderList == null) {
            empreessSchudeCarOrderList = new ArrayList<EmpreessSchudeCarOrder>();
        }
        return this.empreessSchudeCarOrderList;
    }

    /**
     * 获取revUser属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevUser() {
        return revUser;
    }

    /**
     * 设置revUser属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevUser(String value) {
        this.revUser = value;
    }

    /**
     * 获取revUserId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevUserId() {
        return revUserId;
    }

    /**
     * 设置revUserId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevUserId(String value) {
        this.revUserId = value;
    }

    /**
     * 获取revUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevUserTel() {
        return revUserTel;
    }

    /**
     * 设置revUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevUserTel(String value) {
        this.revUserTel = value;
    }

}
