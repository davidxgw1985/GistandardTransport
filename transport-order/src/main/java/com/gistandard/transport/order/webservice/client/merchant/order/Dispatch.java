
package com.gistandard.transport.order.webservice.client.merchant.order;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>dispatch complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="dispatch"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="apply_Order_Time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="arrange_Person" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="arrange_Time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="busiBookNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="confirmerId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="createUserId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="disFlag" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="dispatcherId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="oldStatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="operStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="operatorId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="operatorType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="parentIraId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="prepaidAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="prepaidCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quotationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="receivingMode" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="refCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="refEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="refStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="routeDetailId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="scheduCarNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sort" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dispatch", propOrder = {
    "applyOrderTime",
    "arrangePerson",
    "arrangeTime",
    "busiBookNo",
    "comments",
    "confirmerId",
    "createDate",
    "createUserId",
    "disFlag",
    "dispatcherId",
    "id",
    "oldStatus",
    "operStatus",
    "operatorId",
    "operatorType",
    "parentIraId",
    "prepaidAmount",
    "prepaidCurrency",
    "quotationId",
    "receivingMode",
    "refCurrency",
    "refEndDate",
    "refStartDate",
    "routeDetailId",
    "scheduCarNo",
    "sort",
    "status"
})
@XmlSeeAlso({
    DispatchExtend.class
})
public class Dispatch {

    @XmlElement(name = "apply_Order_Time")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar applyOrderTime;
    @XmlElement(name = "arrange_Person")
    protected int arrangePerson;
    @XmlElement(name = "arrange_Time")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar arrangeTime;
    protected String busiBookNo;
    protected String comments;
    protected Integer confirmerId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    protected Integer createUserId;
    protected int disFlag;
    protected Integer dispatcherId;
    protected Long id;
    protected Integer oldStatus;
    protected String operStatus;
    protected Integer operatorId;
    protected int operatorType;
    protected Long parentIraId;
    protected BigDecimal prepaidAmount;
    protected String prepaidCurrency;
    protected String quotationId;
    protected int receivingMode;
    protected String refCurrency;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar refEndDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar refStartDate;
    protected Long routeDetailId;
    protected String scheduCarNo;
    protected Integer sort;
    protected Integer status;

    /**
     * 获取applyOrderTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getApplyOrderTime() {
        return applyOrderTime;
    }

    /**
     * 设置applyOrderTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setApplyOrderTime(XMLGregorianCalendar value) {
        this.applyOrderTime = value;
    }

    /**
     * 获取arrangePerson属性的值。
     * 
     */
    public int getArrangePerson() {
        return arrangePerson;
    }

    /**
     * 设置arrangePerson属性的值。
     * 
     */
    public void setArrangePerson(int value) {
        this.arrangePerson = value;
    }

    /**
     * 获取arrangeTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getArrangeTime() {
        return arrangeTime;
    }

    /**
     * 设置arrangeTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setArrangeTime(XMLGregorianCalendar value) {
        this.arrangeTime = value;
    }

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
     * 获取comments属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * 设置comments属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
    }

    /**
     * 获取confirmerId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getConfirmerId() {
        return confirmerId;
    }

    /**
     * 设置confirmerId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setConfirmerId(Integer value) {
        this.confirmerId = value;
    }

    /**
     * 获取createDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * 设置createDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * 获取createUserId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置createUserId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCreateUserId(Integer value) {
        this.createUserId = value;
    }

    /**
     * 获取disFlag属性的值。
     * 
     */
    public int getDisFlag() {
        return disFlag;
    }

    /**
     * 设置disFlag属性的值。
     * 
     */
    public void setDisFlag(int value) {
        this.disFlag = value;
    }

    /**
     * 获取dispatcherId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDispatcherId() {
        return dispatcherId;
    }

    /**
     * 设置dispatcherId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDispatcherId(Integer value) {
        this.dispatcherId = value;
    }

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * 获取oldStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOldStatus() {
        return oldStatus;
    }

    /**
     * 设置oldStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOldStatus(Integer value) {
        this.oldStatus = value;
    }

    /**
     * 获取operStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperStatus() {
        return operStatus;
    }

    /**
     * 设置operStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperStatus(String value) {
        this.operStatus = value;
    }

    /**
     * 获取operatorId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOperatorId() {
        return operatorId;
    }

    /**
     * 设置operatorId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOperatorId(Integer value) {
        this.operatorId = value;
    }

    /**
     * 获取operatorType属性的值。
     * 
     */
    public int getOperatorType() {
        return operatorType;
    }

    /**
     * 设置operatorType属性的值。
     * 
     */
    public void setOperatorType(int value) {
        this.operatorType = value;
    }

    /**
     * 获取parentIraId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getParentIraId() {
        return parentIraId;
    }

    /**
     * 设置parentIraId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setParentIraId(Long value) {
        this.parentIraId = value;
    }

    /**
     * 获取prepaidAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrepaidAmount() {
        return prepaidAmount;
    }

    /**
     * 设置prepaidAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrepaidAmount(BigDecimal value) {
        this.prepaidAmount = value;
    }

    /**
     * 获取prepaidCurrency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrepaidCurrency() {
        return prepaidCurrency;
    }

    /**
     * 设置prepaidCurrency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrepaidCurrency(String value) {
        this.prepaidCurrency = value;
    }

    /**
     * 获取quotationId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuotationId() {
        return quotationId;
    }

    /**
     * 设置quotationId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuotationId(String value) {
        this.quotationId = value;
    }

    /**
     * 获取receivingMode属性的值。
     * 
     */
    public int getReceivingMode() {
        return receivingMode;
    }

    /**
     * 设置receivingMode属性的值。
     * 
     */
    public void setReceivingMode(int value) {
        this.receivingMode = value;
    }

    /**
     * 获取refCurrency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefCurrency() {
        return refCurrency;
    }

    /**
     * 设置refCurrency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefCurrency(String value) {
        this.refCurrency = value;
    }

    /**
     * 获取refEndDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRefEndDate() {
        return refEndDate;
    }

    /**
     * 设置refEndDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRefEndDate(XMLGregorianCalendar value) {
        this.refEndDate = value;
    }

    /**
     * 获取refStartDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRefStartDate() {
        return refStartDate;
    }

    /**
     * 设置refStartDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRefStartDate(XMLGregorianCalendar value) {
        this.refStartDate = value;
    }

    /**
     * 获取routeDetailId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRouteDetailId() {
        return routeDetailId;
    }

    /**
     * 设置routeDetailId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRouteDetailId(Long value) {
        this.routeDetailId = value;
    }

    /**
     * 获取scheduCarNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduCarNo() {
        return scheduCarNo;
    }

    /**
     * 设置scheduCarNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduCarNo(String value) {
        this.scheduCarNo = value;
    }

    /**
     * 获取sort属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置sort属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSort(Integer value) {
        this.sort = value;
    }

    /**
     * 获取status属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

}
