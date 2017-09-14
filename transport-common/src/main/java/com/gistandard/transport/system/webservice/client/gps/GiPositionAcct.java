
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
 * <p>giPositionAcct complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="giPositionAcct"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="telephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="loginCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="loginName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="loginTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="companyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="companyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="companyBizMode" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="servicingCompanyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="servicingCompanyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="servicingCompanyBizMode" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="allModuleCode" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="allRoleCode" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="sharingPosition" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="bizMode" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="allModuleCodeSys" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="allRoleCodeSys" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="stationCategoryAttr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="businessHoursStart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="businessHoursEnd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isProhibit" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="isToDelete" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="tsToDelete" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="isOpenNow" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="tsCreated" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="tsLastBD" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="tsResynced" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="giPoint" type="{http://webservice.manage.gps.com/}giPoint" minOccurs="0"/&gt;
 *         &lt;element name="pcd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="province" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="district" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="countPicking" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="countDelivering" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="edCntToPickup" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="edCntPicking" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="edCntDelivering" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="edCntOtczs" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="edCntAssigned" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="fixedGiPoint" type="{http://webservice.manage.gps.com/}giPoint" minOccurs="0"/&gt;
 *         &lt;element name="fixedPcd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fixedProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fixedCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fixedDistrict" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fixedAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "giPositionAcct", propOrder = {
    "id",
    "userCode",
    "userName",
    "telephone",
    "userTel",
    "loginCode",
    "loginName",
    "loginTel",
    "companyCode",
    "companyName",
    "companyBizMode",
    "servicingCompanyCode",
    "servicingCompanyName",
    "servicingCompanyBizMode",
    "allModuleCode",
    "allRoleCode",
    "sharingPosition",
    "bizMode",
    "allModuleCodeSys",
    "allRoleCodeSys",
    "type",
    "category",
    "stationCategoryAttr",
    "businessHoursStart",
    "businessHoursEnd",
    "isProhibit",
    "isToDelete",
    "tsToDelete",
    "isOpenNow",
    "tsCreated",
    "tsLastBD",
    "tsResynced",
    "giPoint",
    "pcd",
    "province",
    "city",
    "district",
    "address",
    "countPicking",
    "countDelivering",
    "edCntToPickup",
    "edCntPicking",
    "edCntDelivering",
    "edCntOtczs",
    "edCntAssigned",
    "fixedGiPoint",
    "fixedPcd",
    "fixedProvince",
    "fixedCity",
    "fixedDistrict",
    "fixedAddress"
})
public class GiPositionAcct {

    protected String id;
    protected String userCode;
    protected String userName;
    protected String telephone;
    protected String userTel;
    protected String loginCode;
    protected String loginName;
    protected String loginTel;
    protected String companyCode;
    protected String companyName;
    protected int companyBizMode;
    protected String servicingCompanyCode;
    protected String servicingCompanyName;
    protected int servicingCompanyBizMode;
    @XmlElement(nillable = true)
    protected List<String> allModuleCode;
    @XmlElement(nillable = true)
    protected List<String> allRoleCode;
    protected Boolean sharingPosition;
    protected int bizMode;
    @XmlElement(nillable = true)
    protected List<String> allModuleCodeSys;
    @XmlElement(nillable = true)
    protected List<String> allRoleCodeSys;
    protected String type;
    protected Integer category;
    protected Integer stationCategoryAttr;
    protected String businessHoursStart;
    protected String businessHoursEnd;
    protected Boolean isProhibit;
    protected Boolean isToDelete;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsToDelete;
    protected Boolean isOpenNow;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsCreated;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsLastBD;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date tsResynced;
    protected GiPoint giPoint;
    protected String pcd;
    protected String province;
    protected String city;
    protected String district;
    protected String address;
    protected long countPicking;
    protected long countDelivering;
    protected long edCntToPickup;
    protected long edCntPicking;
    protected long edCntDelivering;
    protected long edCntOtczs;
    protected long edCntAssigned;
    protected GiPoint fixedGiPoint;
    protected String fixedPcd;
    protected String fixedProvince;
    protected String fixedCity;
    protected String fixedDistrict;
    protected String fixedAddress;

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
     * 获取telephone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置telephone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephone(String value) {
        this.telephone = value;
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
     * 获取companyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * 设置companyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyCode(String value) {
        this.companyCode = value;
    }

    /**
     * 获取companyName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置companyName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * 获取companyBizMode属性的值。
     * 
     */
    public int getCompanyBizMode() {
        return companyBizMode;
    }

    /**
     * 设置companyBizMode属性的值。
     * 
     */
    public void setCompanyBizMode(int value) {
        this.companyBizMode = value;
    }

    /**
     * 获取servicingCompanyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicingCompanyCode() {
        return servicingCompanyCode;
    }

    /**
     * 设置servicingCompanyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicingCompanyCode(String value) {
        this.servicingCompanyCode = value;
    }

    /**
     * 获取servicingCompanyName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicingCompanyName() {
        return servicingCompanyName;
    }

    /**
     * 设置servicingCompanyName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicingCompanyName(String value) {
        this.servicingCompanyName = value;
    }

    /**
     * 获取servicingCompanyBizMode属性的值。
     * 
     */
    public int getServicingCompanyBizMode() {
        return servicingCompanyBizMode;
    }

    /**
     * 设置servicingCompanyBizMode属性的值。
     * 
     */
    public void setServicingCompanyBizMode(int value) {
        this.servicingCompanyBizMode = value;
    }

    /**
     * Gets the value of the allModuleCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allModuleCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllModuleCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAllModuleCode() {
        if (allModuleCode == null) {
            allModuleCode = new ArrayList<String>();
        }
        return this.allModuleCode;
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
     * 获取sharingPosition属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSharingPosition() {
        return sharingPosition;
    }

    /**
     * 设置sharingPosition属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSharingPosition(Boolean value) {
        this.sharingPosition = value;
    }

    /**
     * 获取bizMode属性的值。
     * 
     */
    public int getBizMode() {
        return bizMode;
    }

    /**
     * 设置bizMode属性的值。
     * 
     */
    public void setBizMode(int value) {
        this.bizMode = value;
    }

    /**
     * Gets the value of the allModuleCodeSys property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allModuleCodeSys property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllModuleCodeSys().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAllModuleCodeSys() {
        if (allModuleCodeSys == null) {
            allModuleCodeSys = new ArrayList<String>();
        }
        return this.allModuleCodeSys;
    }

    /**
     * Gets the value of the allRoleCodeSys property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allRoleCodeSys property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllRoleCodeSys().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAllRoleCodeSys() {
        if (allRoleCodeSys == null) {
            allRoleCodeSys = new ArrayList<String>();
        }
        return this.allRoleCodeSys;
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
     * 获取category属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * 设置category属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCategory(Integer value) {
        this.category = value;
    }

    /**
     * 获取stationCategoryAttr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStationCategoryAttr() {
        return stationCategoryAttr;
    }

    /**
     * 设置stationCategoryAttr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStationCategoryAttr(Integer value) {
        this.stationCategoryAttr = value;
    }

    /**
     * 获取businessHoursStart属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessHoursStart() {
        return businessHoursStart;
    }

    /**
     * 设置businessHoursStart属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessHoursStart(String value) {
        this.businessHoursStart = value;
    }

    /**
     * 获取businessHoursEnd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessHoursEnd() {
        return businessHoursEnd;
    }

    /**
     * 设置businessHoursEnd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessHoursEnd(String value) {
        this.businessHoursEnd = value;
    }

    /**
     * 获取isProhibit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsProhibit() {
        return isProhibit;
    }

    /**
     * 设置isProhibit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsProhibit(Boolean value) {
        this.isProhibit = value;
    }

    /**
     * 获取isToDelete属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsToDelete() {
        return isToDelete;
    }

    /**
     * 设置isToDelete属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsToDelete(Boolean value) {
        this.isToDelete = value;
    }

    /**
     * 获取tsToDelete属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTsToDelete() {
        return tsToDelete;
    }

    /**
     * 设置tsToDelete属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsToDelete(Date value) {
        this.tsToDelete = value;
    }

    /**
     * 获取isOpenNow属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsOpenNow() {
        return isOpenNow;
    }

    /**
     * 设置isOpenNow属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsOpenNow(Boolean value) {
        this.isOpenNow = value;
    }

    /**
     * 获取tsCreated属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTsCreated() {
        return tsCreated;
    }

    /**
     * 设置tsCreated属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsCreated(Date value) {
        this.tsCreated = value;
    }

    /**
     * 获取tsLastBD属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTsLastBD() {
        return tsLastBD;
    }

    /**
     * 设置tsLastBD属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsLastBD(Date value) {
        this.tsLastBD = value;
    }

    /**
     * 获取tsResynced属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTsResynced() {
        return tsResynced;
    }

    /**
     * 设置tsResynced属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTsResynced(Date value) {
        this.tsResynced = value;
    }

    /**
     * 获取giPoint属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GiPoint }
     *     
     */
    public GiPoint getGiPoint() {
        return giPoint;
    }

    /**
     * 设置giPoint属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GiPoint }
     *     
     */
    public void setGiPoint(GiPoint value) {
        this.giPoint = value;
    }

    /**
     * 获取pcd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcd() {
        return pcd;
    }

    /**
     * 设置pcd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcd(String value) {
        this.pcd = value;
    }

    /**
     * 获取province属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置province属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvince(String value) {
        this.province = value;
    }

    /**
     * 获取city属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置city属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * 获取district属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置district属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrict(String value) {
        this.district = value;
    }

    /**
     * 获取address属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置address属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * 获取countPicking属性的值。
     * 
     */
    public long getCountPicking() {
        return countPicking;
    }

    /**
     * 设置countPicking属性的值。
     * 
     */
    public void setCountPicking(long value) {
        this.countPicking = value;
    }

    /**
     * 获取countDelivering属性的值。
     * 
     */
    public long getCountDelivering() {
        return countDelivering;
    }

    /**
     * 设置countDelivering属性的值。
     * 
     */
    public void setCountDelivering(long value) {
        this.countDelivering = value;
    }

    /**
     * 获取edCntToPickup属性的值。
     * 
     */
    public long getEdCntToPickup() {
        return edCntToPickup;
    }

    /**
     * 设置edCntToPickup属性的值。
     * 
     */
    public void setEdCntToPickup(long value) {
        this.edCntToPickup = value;
    }

    /**
     * 获取edCntPicking属性的值。
     * 
     */
    public long getEdCntPicking() {
        return edCntPicking;
    }

    /**
     * 设置edCntPicking属性的值。
     * 
     */
    public void setEdCntPicking(long value) {
        this.edCntPicking = value;
    }

    /**
     * 获取edCntDelivering属性的值。
     * 
     */
    public long getEdCntDelivering() {
        return edCntDelivering;
    }

    /**
     * 设置edCntDelivering属性的值。
     * 
     */
    public void setEdCntDelivering(long value) {
        this.edCntDelivering = value;
    }

    /**
     * 获取edCntOtczs属性的值。
     * 
     */
    public long getEdCntOtczs() {
        return edCntOtczs;
    }

    /**
     * 设置edCntOtczs属性的值。
     * 
     */
    public void setEdCntOtczs(long value) {
        this.edCntOtczs = value;
    }

    /**
     * 获取edCntAssigned属性的值。
     * 
     */
    public long getEdCntAssigned() {
        return edCntAssigned;
    }

    /**
     * 设置edCntAssigned属性的值。
     * 
     */
    public void setEdCntAssigned(long value) {
        this.edCntAssigned = value;
    }

    /**
     * 获取fixedGiPoint属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GiPoint }
     *     
     */
    public GiPoint getFixedGiPoint() {
        return fixedGiPoint;
    }

    /**
     * 设置fixedGiPoint属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GiPoint }
     *     
     */
    public void setFixedGiPoint(GiPoint value) {
        this.fixedGiPoint = value;
    }

    /**
     * 获取fixedPcd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixedPcd() {
        return fixedPcd;
    }

    /**
     * 设置fixedPcd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixedPcd(String value) {
        this.fixedPcd = value;
    }

    /**
     * 获取fixedProvince属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixedProvince() {
        return fixedProvince;
    }

    /**
     * 设置fixedProvince属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixedProvince(String value) {
        this.fixedProvince = value;
    }

    /**
     * 获取fixedCity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixedCity() {
        return fixedCity;
    }

    /**
     * 设置fixedCity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixedCity(String value) {
        this.fixedCity = value;
    }

    /**
     * 获取fixedDistrict属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixedDistrict() {
        return fixedDistrict;
    }

    /**
     * 设置fixedDistrict属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixedDistrict(String value) {
        this.fixedDistrict = value;
    }

    /**
     * 获取fixedAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixedAddress() {
        return fixedAddress;
    }

    /**
     * 设置fixedAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixedAddress(String value) {
        this.fixedAddress = value;
    }

}
