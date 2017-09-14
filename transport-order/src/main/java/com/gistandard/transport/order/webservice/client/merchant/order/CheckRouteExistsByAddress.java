
package com.gistandard.transport.order.webservice.client.merchant.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CheckRouteExistsByAddress complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CheckRouteExistsByAddress"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="arg1" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="arg2" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="arg3" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="arg4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="arg5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="arg6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="arg7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="arg8" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="arg9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckRouteExistsByAddress", propOrder = {
    "arg0",
    "arg1",
    "arg2",
    "arg3",
    "arg4",
    "arg5",
    "arg6",
    "arg7",
    "arg8",
    "arg9"
})
public class CheckRouteExistsByAddress {

    protected double arg0;
    protected double arg1;
    protected double arg2;
    protected double arg3;
    protected String arg4;
    protected String arg5;
    protected String arg6;
    protected String arg7;
    protected String arg8;
    protected String arg9;

    /**
     * 获取arg0属性的值。
     * 
     */
    public double getArg0() {
        return arg0;
    }

    /**
     * 设置arg0属性的值。
     * 
     */
    public void setArg0(double value) {
        this.arg0 = value;
    }

    /**
     * 获取arg1属性的值。
     * 
     */
    public double getArg1() {
        return arg1;
    }

    /**
     * 设置arg1属性的值。
     * 
     */
    public void setArg1(double value) {
        this.arg1 = value;
    }

    /**
     * 获取arg2属性的值。
     * 
     */
    public double getArg2() {
        return arg2;
    }

    /**
     * 设置arg2属性的值。
     * 
     */
    public void setArg2(double value) {
        this.arg2 = value;
    }

    /**
     * 获取arg3属性的值。
     * 
     */
    public double getArg3() {
        return arg3;
    }

    /**
     * 设置arg3属性的值。
     * 
     */
    public void setArg3(double value) {
        this.arg3 = value;
    }

    /**
     * 获取arg4属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArg4() {
        return arg4;
    }

    /**
     * 设置arg4属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArg4(String value) {
        this.arg4 = value;
    }

    /**
     * 获取arg5属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArg5() {
        return arg5;
    }

    /**
     * 设置arg5属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArg5(String value) {
        this.arg5 = value;
    }

    /**
     * 获取arg6属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArg6() {
        return arg6;
    }

    /**
     * 设置arg6属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArg6(String value) {
        this.arg6 = value;
    }

    /**
     * 获取arg7属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArg7() {
        return arg7;
    }

    /**
     * 设置arg7属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArg7(String value) {
        this.arg7 = value;
    }

    /**
     * 获取arg8属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArg8() {
        return arg8;
    }

    /**
     * 设置arg8属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArg8(String value) {
        this.arg8 = value;
    }

    /**
     * 获取arg9属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArg9() {
        return arg9;
    }

    /**
     * 设置arg9属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArg9(String value) {
        this.arg9 = value;
    }

}
