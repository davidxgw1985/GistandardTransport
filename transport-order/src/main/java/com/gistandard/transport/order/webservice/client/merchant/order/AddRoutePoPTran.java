
package com.gistandard.transport.order.webservice.client.merchant.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AddRoutePoPTran complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AddRoutePoPTran"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="busiBookNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fromAccountId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddRoutePoPTran", propOrder = {
    "busiBookNo",
    "fromAccountId"
})
public class AddRoutePoPTran {

    protected String busiBookNo;
    protected Integer fromAccountId;

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
     * 获取fromAccountId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFromAccountId() {
        return fromAccountId;
    }

    /**
     * 设置fromAccountId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFromAccountId(Integer value) {
        this.fromAccountId = value;
    }

}
