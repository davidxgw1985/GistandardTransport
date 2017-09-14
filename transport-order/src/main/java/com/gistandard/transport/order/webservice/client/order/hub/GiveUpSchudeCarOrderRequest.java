
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>giveUpSchudeCarOrderRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="giveUpSchudeCarOrderRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://order.server.mobilestation.webService.business.invent.valueplus.com/}serviceBaseRequest"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="busiBookNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="operateRemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="schudeCarNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="totalLevel" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "giveUpSchudeCarOrderRequest", propOrder = {
    "busiBookNo",
    "operateRemark",
    "schudeCarNo",
    "totalLevel"
})
public class GiveUpSchudeCarOrderRequest
    extends ServiceBaseRequest
{

    protected String busiBookNo;
    protected String operateRemark;
    protected String schudeCarNo;
    protected boolean totalLevel;

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
     * 获取operateRemark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperateRemark() {
        return operateRemark;
    }

    /**
     * 设置operateRemark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperateRemark(String value) {
        this.operateRemark = value;
    }

    /**
     * 获取schudeCarNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchudeCarNo() {
        return schudeCarNo;
    }

    /**
     * 设置schudeCarNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchudeCarNo(String value) {
        this.schudeCarNo = value;
    }

    /**
     * 获取totalLevel属性的值。
     * 
     */
    public boolean isTotalLevel() {
        return totalLevel;
    }

    /**
     * 设置totalLevel属性的值。
     * 
     */
    public void setTotalLevel(boolean value) {
        this.totalLevel = value;
    }

}
