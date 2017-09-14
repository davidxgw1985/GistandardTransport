
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="NextCommand" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="NextInterval" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ApSvcAuthType" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ApSvcPerfCmdType" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "nextCommand",
    "nextInterval",
    "apSvcAuthType",
    "apSvcPerfCmdType"
})
@XmlRootElement(name = "APStatusRepRsp")
public class APStatusRepRsp {

    @XmlElement(name = "NextCommand", required = true, nillable = true)
    protected String nextCommand;
    @XmlElement(name = "NextInterval")
    protected int nextInterval;
    @XmlElement(name = "ApSvcAuthType", nillable = true)
    protected List<String> apSvcAuthType;
    @XmlElement(name = "ApSvcPerfCmdType", nillable = true)
    protected List<String> apSvcPerfCmdType;

    /**
     * 获取nextCommand属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextCommand() {
        return nextCommand;
    }

    /**
     * 设置nextCommand属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextCommand(String value) {
        this.nextCommand = value;
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

    /**
     * Gets the value of the apSvcAuthType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apSvcAuthType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApSvcAuthType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getApSvcAuthType() {
        if (apSvcAuthType == null) {
            apSvcAuthType = new ArrayList<String>();
        }
        return this.apSvcAuthType;
    }

    /**
     * Gets the value of the apSvcPerfCmdType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apSvcPerfCmdType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApSvcPerfCmdType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getApSvcPerfCmdType() {
        if (apSvcPerfCmdType == null) {
            apSvcPerfCmdType = new ArrayList<String>();
        }
        return this.apSvcPerfCmdType;
    }

}
