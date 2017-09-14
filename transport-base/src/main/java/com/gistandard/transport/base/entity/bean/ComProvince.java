package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.util.Date;

public class ComProvince implements Serializable {
    private static final long serialVersionUID = 6184026768913106821L;
    private Integer id;

    private String provinceName;

    private String shortName;

    private Date updateTime;

    private String provincePinyin;

    private String provincePy;

    private Integer countryId;

    private Integer sortNo;

    private Integer cityId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getProvincePinyin() {
        return provincePinyin;
    }

    public void setProvincePinyin(String provincePinyin) {
        this.provincePinyin = provincePinyin;
    }

    public String getProvincePy() {
        return provincePy;
    }

    public void setProvincePy(String provincePy) {
        this.provincePy = provincePy;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }
}