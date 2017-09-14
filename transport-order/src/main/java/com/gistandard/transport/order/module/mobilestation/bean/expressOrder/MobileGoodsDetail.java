
package com.gistandard.transport.order.module.mobilestation.bean.expressOrder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>mobileGoodsDetail complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="mobileGoodsDetail"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="createUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="goodsHeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="goodsLenght" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="goodsName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="goodsQty" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="goodsQtyUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="goodsQtyUnitName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="goodsType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="goodsVolume" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="goodsVolumeUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="goodsVolumeUnitName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="goodsWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="goodsWeightUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="goodsWeightUnitName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="goodsWide" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="schudeOrderId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mobileGoodsDetail", propOrder = {
    "createDate",
    "createUser",
    "goodsHeight",
    "goodsLenght",
    "goodsName",
    "goodsQty",
    "goodsQtyUnit",
    "goodsQtyUnitName",
    "goodsType",
    "goodsVolume",
    "goodsVolumeUnit",
    "goodsVolumeUnitName",
    "goodsWeight",
    "goodsWeightUnit",
    "goodsWeightUnitName",
    "goodsWide",
    "schudeOrderId"
})
public class MobileGoodsDetail {

    protected Date createDate;
    protected String createUser;
    protected BigDecimal goodsHeight;
    protected BigDecimal goodsLenght;
    protected String goodsName;
    protected BigDecimal goodsQty;
    protected String goodsQtyUnit;
    protected String goodsQtyUnitName;
    protected String goodsType;
    protected BigDecimal goodsVolume;
    protected String goodsVolumeUnit;
    protected String goodsVolumeUnitName;
    protected BigDecimal goodsWeight;
    protected String goodsWeightUnit;
    protected String goodsWeightUnitName;
    protected BigDecimal goodsWide;
    protected Integer schudeOrderId;

    /**
     * ��ȡcreateDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * ����createDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(Date value) {
        this.createDate = value;
    }

    /**
     * ��ȡcreateUser���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * ����createUser���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateUser(String value) {
        this.createUser = value;
    }

    /**
     * ��ȡgoodsHeight���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGoodsHeight() {
        return goodsHeight;
    }

    /**
     * ����goodsHeight���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGoodsHeight(BigDecimal value) {
        this.goodsHeight = value;
    }

    /**
     * ��ȡgoodsLenght���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGoodsLenght() {
        return goodsLenght;
    }

    /**
     * ����goodsLenght���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGoodsLenght(BigDecimal value) {
        this.goodsLenght = value;
    }

    /**
     * ��ȡgoodsName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * ����goodsName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsName(String value) {
        this.goodsName = value;
    }

    /**
     * ��ȡgoodsQty���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGoodsQty() {
        return goodsQty;
    }

    /**
     * ����goodsQty���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGoodsQty(BigDecimal value) {
        this.goodsQty = value;
    }

    /**
     * ��ȡgoodsQtyUnit���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsQtyUnit() {
        return goodsQtyUnit;
    }

    /**
     * ����goodsQtyUnit���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsQtyUnit(String value) {
        this.goodsQtyUnit = value;
    }

    /**
     * ��ȡgoodsQtyUnitName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsQtyUnitName() {
        return goodsQtyUnitName;
    }

    /**
     * ����goodsQtyUnitName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsQtyUnitName(String value) {
        this.goodsQtyUnitName = value;
    }

    /**
     * ��ȡgoodsType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * ����goodsType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsType(String value) {
        this.goodsType = value;
    }

    /**
     * ��ȡgoodsVolume���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGoodsVolume() {
        return goodsVolume;
    }

    /**
     * ����goodsVolume���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGoodsVolume(BigDecimal value) {
        this.goodsVolume = value;
    }

    /**
     * ��ȡgoodsVolumeUnit���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsVolumeUnit() {
        return goodsVolumeUnit;
    }

    /**
     * ����goodsVolumeUnit���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsVolumeUnit(String value) {
        this.goodsVolumeUnit = value;
    }

    /**
     * ��ȡgoodsVolumeUnitName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsVolumeUnitName() {
        return goodsVolumeUnitName;
    }

    /**
     * ����goodsVolumeUnitName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsVolumeUnitName(String value) {
        this.goodsVolumeUnitName = value;
    }

    /**
     * ��ȡgoodsWeight���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGoodsWeight() {
        return goodsWeight;
    }

    /**
     * ����goodsWeight���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGoodsWeight(BigDecimal value) {
        this.goodsWeight = value;
    }

    /**
     * ��ȡgoodsWeightUnit���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsWeightUnit() {
        return goodsWeightUnit;
    }

    /**
     * ����goodsWeightUnit���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsWeightUnit(String value) {
        this.goodsWeightUnit = value;
    }

    /**
     * ��ȡgoodsWeightUnitName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoodsWeightUnitName() {
        return goodsWeightUnitName;
    }

    /**
     * ����goodsWeightUnitName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoodsWeightUnitName(String value) {
        this.goodsWeightUnitName = value;
    }

    /**
     * ��ȡgoodsWide���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGoodsWide() {
        return goodsWide;
    }

    /**
     * ����goodsWide���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGoodsWide(BigDecimal value) {
        this.goodsWide = value;
    }

    /**
     * ��ȡschudeOrderId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSchudeOrderId() {
        return schudeOrderId;
    }

    /**
     * ����schudeOrderId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSchudeOrderId(Integer value) {
        this.schudeOrderId = value;
    }

}
