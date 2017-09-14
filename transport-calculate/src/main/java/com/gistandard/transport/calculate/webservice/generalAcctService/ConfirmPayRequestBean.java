
package com.gistandard.transport.calculate.webservice.generalAcctService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>confirmPayRequestBean complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="confirmPayRequestBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="validBeanList" type="{http://server.webservice.gistandard.com/}gatrxMstRequestBean" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="validBeanO" type="{http://server.webservice.gistandard.com/}gatrxMstRequestBean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmPayRequestBean", propOrder = {
    "validBeanList",
    "validBeanO"
})
public class ConfirmPayRequestBean {

    @XmlElement(nillable = true)
    protected List<GatrxMstRequestBean> validBeanList;
    protected GatrxMstRequestBean validBeanO;

    /**
     * Gets the value of the validBeanList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validBeanList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidBeanList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GatrxMstRequestBean }
     * 
     * 
     */
    public List<GatrxMstRequestBean> getValidBeanList() {
        if (validBeanList == null) {
            validBeanList = new ArrayList<GatrxMstRequestBean>();
        }
        return this.validBeanList;
    }

    /**
     * 获取validBeanO属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GatrxMstRequestBean }
     *     
     */
    public GatrxMstRequestBean getValidBeanO() {
        return validBeanO;
    }

    /**
     * 设置validBeanO属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GatrxMstRequestBean }
     *     
     */
    public void setValidBeanO(GatrxMstRequestBean value) {
        this.validBeanO = value;
    }

}
