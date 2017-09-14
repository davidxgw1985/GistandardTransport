
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type鐨� Java 绫汇��
 * 
 * <p>浠ヤ笅妯″紡鐗囨鎸囧畾鍖呭惈鍦ㄦ绫讳腑鐨勯鏈熷唴瀹广��
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="APid" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="APStatus" type="{http://www.csapi.org/schema/ap}APStatusType"/&gt;
 *         &lt;element name="APPid" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "aPid",
    "apStatus",
    "apPid"
})
@XmlRootElement(name = "APStatusRepReq")
public class APStatusRepReq {

    @XmlElement(name = "APid", required = true)
    protected String aPid;
    @XmlElement(name = "APStatus", required = true)
    @XmlSchemaType(name = "string")
    protected APStatusType apStatus;
    @XmlElement(name = "APPid")
    protected int apPid;

    /**
     * 鑾峰彇aPid灞炴�х殑鍊笺��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPid() {
        return aPid;
    }

    /**
     * 璁剧疆aPid灞炴�х殑鍊笺��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPid(String value) {
        this.aPid = value;
    }

    /**
     * 鑾峰彇apStatus灞炴�х殑鍊笺��
     * 
     * @return
     *     possible object is
     *     {@link APStatusType }
     *     
     */
    public APStatusType getAPStatus() {
        return apStatus;
    }

    /**
     * 璁剧疆apStatus灞炴�х殑鍊笺��
     * 
     * @param value
     *     allowed object is
     *     {@link APStatusType }
     *     
     */
    public void setAPStatus(APStatusType value) {
        this.apStatus = value;
    }

    /**
     * 鑾峰彇apPid灞炴�х殑鍊笺��
     * 
     */
    public int getAPPid() {
        return apPid;
    }

    /**
     * 璁剧疆apPid灞炴�х殑鍊笺��
     * 
     */
    public void setAPPid(int value) {
        this.apPid = value;
    }

}
