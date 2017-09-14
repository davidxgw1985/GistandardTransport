
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>MessageNotificationType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MessageNotificationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CMAbility" type="{http://www.csapi.org/schema/common/v2_0}CMAbility"/&gt;
 *         &lt;element name="WSURI" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageNotificationType", namespace = "http://www.csapi.org/schema/common/v2_0", propOrder = {
    "cmAbility",
    "wsuri"
})
public class MessageNotificationType {

    @XmlElement(name = "CMAbility", required = true)
    @XmlSchemaType(name = "string")
    protected CMAbility cmAbility;
    @XmlElement(name = "WSURI")
    @XmlSchemaType(name = "anyURI")
    protected List<String> wsuri;

    /**
     * 获取cmAbility属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CMAbility }
     *     
     */
    public CMAbility getCMAbility() {
        return cmAbility;
    }

    /**
     * 设置cmAbility属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CMAbility }
     *     
     */
    public void setCMAbility(CMAbility value) {
        this.cmAbility = value;
    }

    /**
     * Gets the value of the wsuri property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsuri property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSURI().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getWSURI() {
        if (wsuri == null) {
            wsuri = new ArrayList<String>();
        }
        return this.wsuri;
    }

}
