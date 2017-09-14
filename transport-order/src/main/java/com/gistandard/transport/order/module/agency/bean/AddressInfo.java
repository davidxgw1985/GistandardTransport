package com.gistandard.transport.order.module.agency.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.math.BigDecimal;

/**
 * @author kongxm
 * @ClassName AddressReq
 * @Description 客户下单地址
 * @Version 1.0
 * @Date 2016/1/26
 */
public class AddressInfo extends AppBaseRequest {
    private Integer id;

    private String name;

    private String telephone;

    private String country;

    private String province;

    private String city;

    private String county;

    private String provinceName;//省名称

    private String cityName;//市名称

    private String countyName;//区名称

    private String address;

    private Integer addressType;

    private Boolean defaultAddress;//是否默认收货地址

    private BigDecimal addressLongitude;

    private BigDecimal addressLatitude;

    private String detailAddress;

    private String companyName;

    private String zipCode;

    private Integer areaIdSel;


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
}