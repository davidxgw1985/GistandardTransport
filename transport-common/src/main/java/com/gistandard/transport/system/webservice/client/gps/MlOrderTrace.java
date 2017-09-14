
package com.gistandard.transport.system.webservice.client.gps;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>mlOrderTrace complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="mlOrderTrace"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="addressFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="addressTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="allRoleCode" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="allRoleCodeFrom" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="allRoleCodeTo" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="applyTimeFirstAid" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="busiNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cityFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cityTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="districtFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="districtTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isToAccept" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="loginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="loginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="loginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pointFrom" type="{http://webservice.manage.gps.com/}giPoint" minOccurs="0"/&gt;
 *         &lt;element name="pointLoginNow" type="{http://webservice.manage.gps.com/}giPoint" minOccurs="0"/&gt;
 *         &lt;element name="pointTo" type="{http://webservice.manage.gps.com/}giPoint" minOccurs="0"/&gt;
 *         &lt;element name="pointUserNow" type="{http://webservice.manage.gps.com/}giPoint" minOccurs="0"/&gt;
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="provinceFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="provinceTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="remarkFirstAid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="roleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="routeItemIdFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="routeItemIdTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="routeItemSeqFrom" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="routeItemSeqTo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="signedCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="signedName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="signedTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="telephoneFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="telephoneTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="timeControl" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="tsAct" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="tsActualFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="tsActualTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="tsPlanFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="tsPlanTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="tsSigned" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="typeBizAssign" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="typeCancelOrdered" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="typeFirstAid" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="typeFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="typeTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userCodeFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userCodeTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userDistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userNameFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userNameTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mlOrderTrace", propOrder = {
    "action",
    "addressFrom",
    "addressTo",
    "allRoleCode",
    "allRoleCodeFrom",
    "allRoleCodeTo",
    "amount",
    "applyTimeFirstAid",
    "busiNo",
    "cityFrom",
    "cityTo",
    "currencyCode",
    "districtFrom",
    "districtTo",
    "id",
    "isToAccept",
    "loginCode",
    "loginName",
    "loginTel",
    "pointFrom",
    "pointLoginNow",
    "pointTo",
    "pointUserNow",
    "productType",
    "provinceFrom",
    "provinceTo",
    "remarkFirstAid",
    "roleCode",
    "routeItemIdFrom",
    "routeItemIdTo",
    "routeItemSeqFrom",
    "routeItemSeqTo",
    "signedCode",
    "signedName",
    "signedTel",
    "telephoneFrom",
    "telephoneTo",
    "timeControl",
    "tsAct",
    "tsActualFrom",
    "tsActualTo",
    "tsPlanFrom",
    "tsPlanTo",
    "tsSigned",
    "type",
    "typeBizAssign",
    "typeCancelOrdered",
    "typeFirstAid",
    "typeFrom",
    "typeTo",
    "userCity",
    "userCode",
    "userCodeFrom",
    "userCodeTo",
    "userDistrict",
    "userName",
    "userNameFrom",
    "userNameTo",
    "userProvince",
    "userTel"
})
public class MlOrderTrace {

    protected String action;
    protected String addressFrom;
    protected String addressTo;
    @XmlElement(nillable = true)
    protected List<String> allRoleCode;
    @XmlElement(nillable = true)
    protected List<String> allRoleCodeFrom;
    @XmlElement(nillable = true)
    protected List<String> allRoleCodeTo;
    protected Double amount;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date applyTimeFirstAid;
    protected String busiNo;
    protected String cityFrom;
    protected String cityTo;
    protected String currencyCode;
    protected String districtFrom;
    protected String districtTo;
    protected String id;
    protected boolean isToAccept;
    protected String loginCode;
    protected String loginName;
    protected String loginTel;
    protected GiPoint pointFrom;
    protected GiPoint pointLoginNow;
    protected GiPoint pointTo;
    protected GiPoint pointUserNow;
    protected String productType;
    protected String provinceFrom;
    protected String provinceTo;
    protected String remarkFirstAid;
    protected String roleCode;
    protected String routeItemIdFrom;
    protected String routeItemIdTo;
    protected Integer routeItemSeqFrom;
    protected Integer routeItemSeqTo;
    protected String signedCode;
    protected String signedName;
    protected String signedTel;
    protected String telephoneFrom;
    protected String telephoneTo;
    protected double timeControl;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsAct;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsActualFrom;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsActualTo;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsPlanFrom;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsPlanTo;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsSigned;
    protected String type;
    protected int typeBizAssign;
    protected int typeCancelOrdered;
    protected int typeFirstAid;
    protected String typeFrom;
    protected String typeTo;
    protected String userCity;
    protected String userCode;
    protected String userCodeFrom;
    protected String userCodeTo;
    protected String userDistrict;
    protected String userName;
    protected String userNameFrom;
    protected String userNameTo;
    protected String userProvince;
    protected String userTel;

    /**
     * 获取action属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置action属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * 获取addressFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressFrom() {
        return addressFrom;
    }

    /**
     * 设置addressFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressFrom(String value) {
        this.addressFrom = value;
    }

    /**
     * 获取addressTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressTo() {
        return addressTo;
    }

    /**
     * 设置addressTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressTo(String value) {
        this.addressTo = value;
    }

    /**
     * Gets the value of the allRoleCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allRoleCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllRoleCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAllRoleCode() {
        if (allRoleCode == null) {
            allRoleCode = new ArrayList<String>();
        }
        return this.allRoleCode;
    }

    /**
     * Gets the value of the allRoleCodeFrom property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allRoleCodeFrom property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllRoleCodeFrom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAllRoleCodeFrom() {
        if (allRoleCodeFrom == null) {
            allRoleCodeFrom = new ArrayList<String>();
        }
        return this.allRoleCodeFrom;
    }

    /**
     * Gets the value of the allRoleCodeTo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allRoleCodeTo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllRoleCodeTo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAllRoleCodeTo() {
        if (allRoleCodeTo == null) {
            allRoleCodeTo = new ArrayList<String>();
        }
        return this.allRoleCodeTo;
    }

    /**
     * 获取amount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 设置amount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmount(Double value) {
        this.amount = value;
    }

    /**
     * 获取applyTimeFirstAid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getApplyTimeFirstAid() {
        return applyTimeFirstAid;
    }

    /**
     * 设置applyTimeFirstAid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplyTimeFirstAid(Date value) {
        this.applyTimeFirstAid = value;
    }

    /**
     * 获取busiNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiNo() {
        return busiNo;
    }

    /**
     * 设置busiNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiNo(String value) {
        this.busiNo = value;
    }

    /**
     * 获取cityFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityFrom() {
        return cityFrom;
    }

    /**
     * 设置cityFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityFrom(String value) {
        this.cityFrom = value;
    }

    /**
     * 获取cityTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityTo() {
        return cityTo;
    }

    /**
     * 设置cityTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityTo(String value) {
        this.cityTo = value;
    }

    /**
     * 获取currencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 设置currencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * 获取districtFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictFrom() {
        return districtFrom;
    }

    /**
     * 设置districtFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictFrom(String value) {
        this.districtFrom = value;
    }

    /**
     * 获取districtTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictTo() {
        return districtTo;
    }

    /**
     * 设置districtTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictTo(String value) {
        this.districtTo = value;
    }

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * 获取isToAccept属性的值。
     * 
     */
    public boolean isIsToAccept() {
        return isToAccept;
    }

    /**
     * 设置isToAccept属性的值。
     * 
     */
    public void setIsToAccept(boolean value) {
        this.isToAccept = value;
    }

    /**
     * 获取loginCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginCode() {
        return loginCode;
    }

    /**
     * 设置loginCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginCode(String value) {
        this.loginCode = value;
    }

    /**
     * 获取loginName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置loginName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginName(String value) {
        this.loginName = value;
    }

    /**
     * 获取loginTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginTel() {
        return loginTel;
    }

    /**
     * 设置loginTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginTel(String value) {
        this.loginTel = value;
    }

    /**
     * 获取pointFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GiPoint }
     *     
     */
    public GiPoint getPointFrom() {
        return pointFrom;
    }

    /**
     * 设置pointFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GiPoint }
     *     
     */
    public void setPointFrom(GiPoint value) {
        this.pointFrom = value;
    }

    /**
     * 获取pointLoginNow属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GiPoint }
     *     
     */
    public GiPoint getPointLoginNow() {
        return pointLoginNow;
    }

    /**
     * 设置pointLoginNow属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GiPoint }
     *     
     */
    public void setPointLoginNow(GiPoint value) {
        this.pointLoginNow = value;
    }

    /**
     * 获取pointTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GiPoint }
     *     
     */
    public GiPoint getPointTo() {
        return pointTo;
    }

    /**
     * 设置pointTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GiPoint }
     *     
     */
    public void setPointTo(GiPoint value) {
        this.pointTo = value;
    }

    /**
     * 获取pointUserNow属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GiPoint }
     *     
     */
    public GiPoint getPointUserNow() {
        return pointUserNow;
    }

    /**
     * 设置pointUserNow属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GiPoint }
     *     
     */
    public void setPointUserNow(GiPoint value) {
        this.pointUserNow = value;
    }

    /**
     * 获取productType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductType() {
        return productType;
    }

    /**
     * 设置productType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductType(String value) {
        this.productType = value;
    }

    /**
     * 获取provinceFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceFrom() {
        return provinceFrom;
    }

    /**
     * 设置provinceFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceFrom(String value) {
        this.provinceFrom = value;
    }

    /**
     * 获取provinceTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceTo() {
        return provinceTo;
    }

    /**
     * 设置provinceTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceTo(String value) {
        this.provinceTo = value;
    }

    /**
     * 获取remarkFirstAid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarkFirstAid() {
        return remarkFirstAid;
    }

    /**
     * 设置remarkFirstAid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarkFirstAid(String value) {
        this.remarkFirstAid = value;
    }

    /**
     * 获取roleCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置roleCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleCode(String value) {
        this.roleCode = value;
    }

    /**
     * 获取routeItemIdFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRouteItemIdFrom() {
        return routeItemIdFrom;
    }

    /**
     * 设置routeItemIdFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRouteItemIdFrom(String value) {
        this.routeItemIdFrom = value;
    }

    /**
     * 获取routeItemIdTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRouteItemIdTo() {
        return routeItemIdTo;
    }

    /**
     * 设置routeItemIdTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRouteItemIdTo(String value) {
        this.routeItemIdTo = value;
    }

    /**
     * 获取routeItemSeqFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRouteItemSeqFrom() {
        return routeItemSeqFrom;
    }

    /**
     * 设置routeItemSeqFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRouteItemSeqFrom(Integer value) {
        this.routeItemSeqFrom = value;
    }

    /**
     * 获取routeItemSeqTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRouteItemSeqTo() {
        return routeItemSeqTo;
    }

    /**
     * 设置routeItemSeqTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRouteItemSeqTo(Integer value) {
        this.routeItemSeqTo = value;
    }

    /**
     * 获取signedCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignedCode() {
        return signedCode;
    }

    /**
     * 设置signedCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignedCode(String value) {
        this.signedCode = value;
    }

    /**
     * 获取signedName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignedName() {
        return signedName;
    }

    /**
     * 设置signedName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignedName(String value) {
        this.signedName = value;
    }

    /**
     * 获取signedTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignedTel() {
        return signedTel;
    }

    /**
     * 设置signedTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignedTel(String value) {
        this.signedTel = value;
    }

    /**
     * 获取telephoneFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephoneFrom() {
        return telephoneFrom;
    }

    /**
     * 设置telephoneFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephoneFrom(String value) {
        this.telephoneFrom = value;
    }

    /**
     * 获取telephoneTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephoneTo() {
        return telephoneTo;
    }

    /**
     * 设置telephoneTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephoneTo(String value) {
        this.telephoneTo = value;
    }

    /**
     * 获取timeControl属性的值。
     * 
     */
    public double getTimeControl() {
        return timeControl;
    }

    /**
     * 设置timeControl属性的值。
     * 
     */
    public void setTimeControl(double value) {
        this.timeControl = value;
    }

    /**
     * 获取tsAct属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTsAct() {
        return tsAct;
    }

    /**
     * 设置tsAct属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsAct(Date value) {
        this.tsAct = value;
    }

    /**
     * 获取tsActualFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTsActualFrom() {
        return tsActualFrom;
    }

    /**
     * 设置tsActualFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsActualFrom(Date value) {
        this.tsActualFrom = value;
    }

    /**
     * 获取tsActualTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTsActualTo() {
        return tsActualTo;
    }

    /**
     * 设置tsActualTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsActualTo(Date value) {
        this.tsActualTo = value;
    }

    /**
     * 获取tsPlanFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTsPlanFrom() {
        return tsPlanFrom;
    }

    /**
     * 设置tsPlanFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsPlanFrom(Date value) {
        this.tsPlanFrom = value;
    }

    /**
     * 获取tsPlanTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTsPlanTo() {
        return tsPlanTo;
    }

    /**
     * 设置tsPlanTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsPlanTo(Date value) {
        this.tsPlanTo = value;
    }

    /**
     * 获取tsSigned属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTsSigned() {
        return tsSigned;
    }

    /**
     * 设置tsSigned属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsSigned(Date value) {
        this.tsSigned = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * 获取typeBizAssign属性的值。
     * 
     */
    public int getTypeBizAssign() {
        return typeBizAssign;
    }

    /**
     * 设置typeBizAssign属性的值。
     * 
     */
    public void setTypeBizAssign(int value) {
        this.typeBizAssign = value;
    }

    /**
     * 获取typeCancelOrdered属性的值。
     * 
     */
    public int getTypeCancelOrdered() {
        return typeCancelOrdered;
    }

    /**
     * 设置typeCancelOrdered属性的值。
     * 
     */
    public void setTypeCancelOrdered(int value) {
        this.typeCancelOrdered = value;
    }

    /**
     * 获取typeFirstAid属性的值。
     * 
     */
    public int getTypeFirstAid() {
        return typeFirstAid;
    }

    /**
     * 设置typeFirstAid属性的值。
     * 
     */
    public void setTypeFirstAid(int value) {
        this.typeFirstAid = value;
    }

    /**
     * 获取typeFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeFrom() {
        return typeFrom;
    }

    /**
     * 设置typeFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeFrom(String value) {
        this.typeFrom = value;
    }

    /**
     * 获取typeTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeTo() {
        return typeTo;
    }

    /**
     * 设置typeTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeTo(String value) {
        this.typeTo = value;
    }

    /**
     * 获取userCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserCity() {
        return userCity;
    }

    /**
     * 设置userCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserCity(String value) {
        this.userCity = value;
    }

    /**
     * 获取userCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置userCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserCode(String value) {
        this.userCode = value;
    }

    /**
     * 获取userCodeFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserCodeFrom() {
        return userCodeFrom;
    }

    /**
     * 设置userCodeFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserCodeFrom(String value) {
        this.userCodeFrom = value;
    }

    /**
     * 获取userCodeTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserCodeTo() {
        return userCodeTo;
    }

    /**
     * 设置userCodeTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserCodeTo(String value) {
        this.userCodeTo = value;
    }

    /**
     * 获取userDistrict属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserDistrict() {
        return userDistrict;
    }

    /**
     * 设置userDistrict属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserDistrict(String value) {
        this.userDistrict = value;
    }

    /**
     * 获取userName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置userName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * 获取userNameFrom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserNameFrom() {
        return userNameFrom;
    }

    /**
     * 设置userNameFrom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserNameFrom(String value) {
        this.userNameFrom = value;
    }

    /**
     * 获取userNameTo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserNameTo() {
        return userNameTo;
    }

    /**
     * 设置userNameTo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserNameTo(String value) {
        this.userNameTo = value;
    }

    /**
     * 获取userProvince属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserProvince() {
        return userProvince;
    }

    /**
     * 设置userProvince属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserProvince(String value) {
        this.userProvince = value;
    }

    /**
     * 获取userTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserTel() {
        return userTel;
    }

    /**
     * 设置userTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserTel(String value) {
        this.userTel = value;
    }

}
