
package com.gistandard.transport.calculate.webservice.generalAcctService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>gatrxMstRequestBean complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="gatrxMstRequestBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="calcType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="couponIdList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="divideConnectWithAmountList" type="{http://server.webservice.gistandard.com/}divideConnectWithAmount" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="docNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="multiCalc" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="payType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gatrxMstRequestBean", propOrder = {
    "calcType",
    "couponIdList",
    "divideConnectWithAmountList",
    "docNo",
    "multiCalc",
    "payType",
    "type"
})
public class GatrxMstRequestBean {

    protected int calcType;
    @XmlElement(nillable = true)
    protected List<Long> couponIdList;
    @XmlElement(nillable = true)
    protected List<DivideConnectWithAmount> divideConnectWithAmountList;
    protected String docNo;
    protected boolean multiCalc;
    protected int payType;
    protected int type;

    /**
     * 获取calcType属性的值。
     * 
     */
    public int getCalcType() {
        return calcType;
    }

    /**
     * 设置calcType属性的值。
     * 
     */
    public void setCalcType(int value) {
        this.calcType = value;
    }

    /**
     * Gets the value of the couponIdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the couponIdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCouponIdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getCouponIdList() {
        if (couponIdList == null) {
            couponIdList = new ArrayList<Long>();
        }
        return this.couponIdList;
    }

    /**
     * Gets the value of the divideConnectWithAmountList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the divideConnectWithAmountList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDivideConnectWithAmountList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DivideConnectWithAmount }
     * 
     * 
     */
    public List<DivideConnectWithAmount> getDivideConnectWithAmountList() {
        if (divideConnectWithAmountList == null) {
            divideConnectWithAmountList = new ArrayList<DivideConnectWithAmount>();
        }
        return this.divideConnectWithAmountList;
    }

    /**
     * 获取docNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocNo() {
        return docNo;
    }

    /**
     * 设置docNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocNo(String value) {
        this.docNo = value;
    }

    /**
     * 获取multiCalc属性的值。
     * 
     */
    public boolean isMultiCalc() {
        return multiCalc;
    }

    /**
     * 设置multiCalc属性的值。
     * 
     */
    public void setMultiCalc(boolean value) {
        this.multiCalc = value;
    }

    /**
     * 获取payType属性的值。
     * 
     */
    public int getPayType() {
        return payType;
    }

    /**
     * 设置payType属性的值。
     * 
     */
    public void setPayType(int value) {
        this.payType = value;
    }

    /**
     * 获取type属性的值。
     * 
     */
    public int getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     */
    public void setType(int value) {
        this.type = value;
    }

}
