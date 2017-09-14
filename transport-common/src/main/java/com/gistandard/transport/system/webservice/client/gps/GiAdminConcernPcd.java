
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>giAdminConcernPcd complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="giAdminConcernPcd"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="district" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isByGps" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="pcd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="province" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="seq" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="seqPcd" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="userCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "giAdminConcernPcd", propOrder = {
    "city",
    "district",
    "id",
    "isByGps",
    "pcd",
    "province",
    "seq",
    "seqPcd",
    "userCode"
})
public class GiAdminConcernPcd {

    protected String city;
    protected String district;
    protected String id;
    protected boolean isByGps;
    protected String pcd;
    protected String province;
    protected int seq;
    protected int seqPcd;
    protected String userCode;

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
     * 获取isByGps属性的值。
     * 
     */
    public boolean isIsByGps() {
        return isByGps;
    }

    /**
     * 设置isByGps属性的值。
     * 
     */
    public void setIsByGps(boolean value) {
        this.isByGps = value;
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
     * 获取seq属性的值。
     * 
     */
    public int getSeq() {
        return seq;
    }

    /**
     * 设置seq属性的值。
     * 
     */
    public void setSeq(int value) {
        this.seq = value;
    }

    /**
     * 获取seqPcd属性的值。
     * 
     */
    public int getSeqPcd() {
        return seqPcd;
    }

    /**
     * 设置seqPcd属性的值。
     * 
     */
    public void setSeqPcd(int value) {
        this.seqPcd = value;
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

}
