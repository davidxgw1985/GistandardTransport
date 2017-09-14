package com.gistandard.transport.app.dubbo.wechat.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

import java.math.BigDecimal;

/**
 * Created by m on 2016/9/30.
 */
public class AddressInfo extends MsDubboReqBean {
    private Integer id;

    private String name;//姓名

    private String telephone;//电话

    private String country;//国家代码

    private String province;//省代码

    private String city;//市代码

    private String county;//区代码

    private String provinceName;//省名称

    private String cityName;//市名称

    private String countyName;//区名称

    private String address;//地址（街道）

    private String detailAddress;//门牌号

    private Integer addressType;//类型： 1发货地址，2收货地址

    private Boolean defaultAddress;//是否默认收货地址

    private BigDecimal addressLongitude;//经度

    private BigDecimal addressLatitude;//纬度

    private String companyName;//公司

    private String zipCode;//邮编

    private Integer areaIdSel;

    private String ssq;//省市区

    private Integer sex;//1先生 2女士

    private String weChatId;//微信客户标识

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Boolean isDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Integer getAddressType() {
        return addressType;
    }

    public void setAddressType(Integer addressType) {
        this.addressType = addressType;
    }

    public BigDecimal getAddressLongitude() {
        return addressLongitude;
    }

    public void setAddressLongitude(BigDecimal addressLongitude) {
        this.addressLongitude = addressLongitude;
    }

    public BigDecimal getAddressLatitude() {
        return addressLatitude;
    }

    public void setAddressLatitude(BigDecimal addressLatitude) {
        this.addressLatitude = addressLatitude;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getAreaIdSel() {
        return areaIdSel;
    }

    public void setAreaIdSel(Integer areaIdSel) {
        this.areaIdSel = areaIdSel;
    }

    public String getSsq() {
        return ssq;
    }

    public void setSsq(String ssq) {
        this.ssq = ssq;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(String weChatId) {
        this.weChatId = weChatId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
}