package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ComCity implements Serializable {
    private static final long serialVersionUID = 1293801529434515616L;
    private Integer id;

    private String name;

    private String shortName;

    private String cityPinyin;

    private String cityShortPY;

    private Boolean hotCity;

    private Date lastModifyTime;

    private Integer sortNo;

    private Integer provinceId;

    // 电信区号，固话前缀
    private String telCode;

    private String countryTtl;

    //经度
    private BigDecimal longitude;

    //纬度
    private BigDecimal latitude;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCityPinyin() {
        return cityPinyin;
    }

    public void setCityPinyin(String cityPinyin) {
        this.cityPinyin = cityPinyin;
    }

    public String getCityShortPY() {
        return cityShortPY;
    }

    public void setCityShortPY(String cityShortPY) {
        this.cityShortPY = cityShortPY;
    }

    public Boolean getHotCity() {
        return hotCity;
    }

    public void setHotCity(Boolean hotCity) {
        this.hotCity = hotCity;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getTelCode() {
        return telCode;
    }

    public void setTelCode(String telCode) {
        this.telCode = telCode;
    }

    public String getCountryTtl() {
        return countryTtl;
    }

    public void setCountryTtl(String countryTtl) {
        this.countryTtl = countryTtl;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}