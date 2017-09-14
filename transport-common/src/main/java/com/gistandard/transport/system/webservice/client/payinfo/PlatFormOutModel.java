
package com.gistandard.transport.system.webservice.client.payinfo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>platFormOutModel complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="platFormOutModel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="QuoteCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="QuoteDetailList" type="{http://webService.external.giifiCalc.transport.gistandard.com/}platFormDetailModel" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "platFormOutModel", propOrder = {
    "status",
    "message",
    "quoteCount",
    "quoteDetailList"
})
public class PlatFormOutModel {

    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "Message")
    protected String message;
    @XmlElement(name = "QuoteCount")
    protected Integer quoteCount;
    @XmlElement(name = "QuoteDetailList", nillable = true)
    protected List<PlatFormDetailModel> quoteDetailList;

    /**
     * 获取status属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
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
     * 获取quoteCount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQuoteCount() {
        return quoteCount;
    }

    /**
     * 设置quoteCount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQuoteCount(Integer value) {
        this.quoteCount = value;
    }

    /**
     * Gets the value of the quoteDetailList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the quoteDetailList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuoteDetailList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlatFormDetailModel }
     * 
     * 
     */
    public List<PlatFormDetailModel> getQuoteDetailList() {
        if (quoteDetailList == null) {
            quoteDetailList = new ArrayList<PlatFormDetailModel>();
        }
        return this.quoteDetailList;
    }

}
