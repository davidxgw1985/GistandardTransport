
package com.gistandard.transport.system.webservice.client.calcWebService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>validResultBean complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="validResultBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="allValidBillMst" type="{http://webService.external.giifiCalc.transport.gistandard.com/}validBean" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="resultRecords" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="succeed" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="totalRecords" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="oValidBean" type="{http://webService.external.giifiCalc.transport.gistandard.com/}validBean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validResultBean", propOrder = {
    "allValidBillMst",
    "message",
    "resultRecords",
    "succeed",
    "totalRecords",
    "oValidBean"
})
public class ValidResultBean {

    @XmlElement(nillable = true)
    protected List<ValidBean> allValidBillMst;
    protected String message;
    protected int resultRecords;
    protected boolean succeed;
    protected Long totalRecords;
    protected ValidBean oValidBean;

    /**
     * Gets the value of the allValidBillMst property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allValidBillMst property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllValidBillMst().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValidBean }
     * 
     * 
     */
    public List<ValidBean> getAllValidBillMst() {
        if (allValidBillMst == null) {
            allValidBillMst = new ArrayList<ValidBean>();
        }
        return this.allValidBillMst;
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
     * 获取resultRecords属性的值。
     * 
     */
    public int getResultRecords() {
        return resultRecords;
    }

    /**
     * 设置resultRecords属性的值。
     * 
     */
    public void setResultRecords(int value) {
        this.resultRecords = value;
    }

    /**
     * 获取succeed属性的值。
     * 
     */
    public boolean isSucceed() {
        return succeed;
    }

    /**
     * 设置succeed属性的值。
     * 
     */
    public void setSucceed(boolean value) {
        this.succeed = value;
    }

    /**
     * 获取totalRecords属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalRecords() {
        return totalRecords;
    }

    /**
     * 设置totalRecords属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalRecords(Long value) {
        this.totalRecords = value;
    }

    /**
     * 获取oValidBean属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ValidBean }
     *     
     */
    public ValidBean getOValidBean() {
        return oValidBean;
    }

    /**
     * 设置oValidBean属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ValidBean }
     *     
     */
    public void setOValidBean(ValidBean value) {
        this.oValidBean = value;
    }

}
