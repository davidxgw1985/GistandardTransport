package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;

public class ComCounty implements Serializable {
    private static final long serialVersionUID = -8049410783741566045L;
    private Integer id;

    private String areaName;

    private Integer cityId;

    private String pinYin;

    private String pinYinChar;

    private Integer isShowWithCity;

    private Date updateTime;

    private Integer sortNo;

    private Integer provinceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public String getPinYinChar() {
        return pinYinChar;
    }

    public void setPinYinChar(String pinYinChar) {
        this.pinYinChar = pinYinChar;
    }

    public Integer getIsShowWithCity() {
        return isShowWithCity;
    }

    public void setIsShowWithCity(Integer isShowWithCity) {
        this.isShowWithCity = isShowWithCity;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
}