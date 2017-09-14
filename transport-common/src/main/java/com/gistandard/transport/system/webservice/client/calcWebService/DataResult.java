
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>dataResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="dataResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="errNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="refObject" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *         &lt;element name="succeed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataResult", propOrder = {
    "errNum",
    "message",
    "refObject",
    "succeed"
})
@XmlSeeAlso({
    PlatformQuote.class
})
public class DataResult {

    protected Integer errNum;
    protected String message;
    protected Object refObject;
    protected Boolean succeed;

    /**
     * 获取errNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getErrNum() {
        return errNum;
    }

    /**
     * 设置errNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setErrNum(Integer value) {
        this.errNum = value;
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
     * 获取refObject属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRefObject() {
        return refObject;
    }

    /**
     * 设置refObject属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRefObject(Object value) {
        this.refObject = value;
    }

    /**
     * 获取succeed属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSucceed() {
        return succeed;
    }

    /**
     * 设置succeed属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSucceed(Boolean value) {
        this.succeed = value;
    }

}
