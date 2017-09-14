
package com.gistandard.transport.order.webservice.client.order.hub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ExpreessException complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ExpreessException"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *         &lt;element name="level" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="msg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExpreessException", propOrder = {
    "data",
    "level",
    "msg",
    "code",
    "message"
})
public class ExpreessException {

    protected Object data;
    protected Integer level;
    protected String msg;
    protected String code;
    protected String message;

    /**
     * 获取data属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置data属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setData(Object value) {
        this.data = value;
    }

    /**
     * 获取level属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置level属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLevel(Integer value) {
        this.level = value;
    }

    /**
     * 获取msg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置msg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsg(String value) {
        this.msg = value;
    }

    /**
     * 获取code属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置code属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
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

}
