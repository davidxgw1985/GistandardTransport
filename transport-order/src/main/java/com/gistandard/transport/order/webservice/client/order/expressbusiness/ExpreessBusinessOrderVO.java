
package com.gistandard.transport.order.webservice.client.order.expressbusiness;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>expreessBusinessOrderVO complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="expreessBusinessOrderVO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="busiBookNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="busiOrderNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="busiOrderType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="deliveryAcctId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="deliveryUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="deliveryUserTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="expreessOrderId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="inInventoryStatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="lastStation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lastStationAcctId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="lastStationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lastStationGiPoint" type="{http://businessOrder.server.mobilestation.webService.business.invent.valueplus.com/}giPoint" minOccurs="0"/&gt;
 *         &lt;element name="mainOrderId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="nextStation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nextStationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="station" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "expreessBusinessOrderVO", propOrder = {
    "busiBookNO",
    "busiOrderNO",
    "busiOrderType",
    "deliveryAcctId",
    "deliveryUserName",
    "deliveryUserTel",
    "expreessOrderId",
    "inInventoryStatus",
    "lastStation",
    "lastStationAcctId",
    "lastStationCode",
    "lastStationGiPoint",
    "mainOrderId",
    "nextStation",
    "nextStationCode",
    "productType",
    "station",
    "stationCode"
})
public class ExpreessBusinessOrderVO {

    protected String busiBookNO;
    protected String busiOrderNO;
    protected Integer busiOrderType;
    protected Integer deliveryAcctId;
    protected String deliveryUserName;
    protected String deliveryUserTel;
    protected Integer expreessOrderId;
    protected Integer inInventoryStatus;
    protected String lastStation;
    protected Integer lastStationAcctId;
    protected String lastStationCode;
    protected GiPoint lastStationGiPoint;
    protected Integer mainOrderId;
    protected String nextStation;
    protected String nextStationCode;
    protected String productType;
    protected String station;
    protected String stationCode;

    /**
     * 获取busiBookNO属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiBookNO() {
        return busiBookNO;
    }

    /**
     * 设置busiBookNO属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiBookNO(String value) {
        this.busiBookNO = value;
    }

    /**
     * 获取busiOrderNO属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusiOrderNO() {
        return busiOrderNO;
    }

    /**
     * 设置busiOrderNO属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusiOrderNO(String value) {
        this.busiOrderNO = value;
    }

    /**
     * 获取busiOrderType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBusiOrderType() {
        return busiOrderType;
    }

    /**
     * 设置busiOrderType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBusiOrderType(Integer value) {
        this.busiOrderType = value;
    }

    /**
     * 获取deliveryAcctId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDeliveryAcctId() {
        return deliveryAcctId;
    }

    /**
     * 设置deliveryAcctId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeliveryAcctId(Integer value) {
        this.deliveryAcctId = value;
    }

    /**
     * 获取deliveryUserName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryUserName() {
        return deliveryUserName;
    }

    /**
     * 设置deliveryUserName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryUserName(String value) {
        this.deliveryUserName = value;
    }

    /**
     * 获取deliveryUserTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryUserTel() {
        return deliveryUserTel;
    }

    /**
     * 设置deliveryUserTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryUserTel(String value) {
        this.deliveryUserTel = value;
    }

    /**
     * 获取expreessOrderId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExpreessOrderId() {
        return expreessOrderId;
    }

    /**
     * 设置expreessOrderId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExpreessOrderId(Integer value) {
        this.expreessOrderId = value;
    }

    /**
     * 获取inInventoryStatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInInventoryStatus() {
        return inInventoryStatus;
    }

    /**
     * 设置inInventoryStatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInInventoryStatus(Integer value) {
        this.inInventoryStatus = value;
    }

    /**
     * 获取lastStation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastStation() {
        return lastStation;
    }

    /**
     * 设置lastStation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastStation(String value) {
        this.lastStation = value;
    }

    /**
     * 获取lastStationAcctId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLastStationAcctId() {
        return lastStationAcctId;
    }

    /**
     * 设置lastStationAcctId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLastStationAcctId(Integer value) {
        this.lastStationAcctId = value;
    }

    /**
     * 获取lastStationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastStationCode() {
        return lastStationCode;
    }

    /**
     * 设置lastStationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastStationCode(String value) {
        this.lastStationCode = value;
    }

    /**
     * 获取lastStationGiPoint属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GiPoint }
     *     
     */
    public GiPoint getLastStationGiPoint() {
        return lastStationGiPoint;
    }

    /**
     * 设置lastStationGiPoint属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GiPoint }
     *     
     */
    public void setLastStationGiPoint(GiPoint value) {
        this.lastStationGiPoint = value;
    }

    /**
     * 获取mainOrderId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMainOrderId() {
        return mainOrderId;
    }

    /**
     * 设置mainOrderId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMainOrderId(Integer value) {
        this.mainOrderId = value;
    }

    /**
     * 获取nextStation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextStation() {
        return nextStation;
    }

    /**
     * 设置nextStation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextStation(String value) {
        this.nextStation = value;
    }

    /**
     * 获取nextStationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextStationCode() {
        return nextStationCode;
    }

    /**
     * 设置nextStationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextStationCode(String value) {
        this.nextStationCode = value;
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
     * 获取station属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStation() {
        return station;
    }

    /**
     * 设置station属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStation(String value) {
        this.station = value;
    }

    /**
     * 获取stationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationCode() {
        return stationCode;
    }

    /**
     * 设置stationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationCode(String value) {
        this.stationCode = value;
    }

}
