package com.gistandard.transport.base.entity.bean.ex;

import java.util.List;

/**
 * @author yujie
 * @ClassName CityBean
 * @Description
 * @Version 1.0
 * @Date 2015-08-13
 */
public class ComCityEx {

    private String cityId;

    private String cityName;

    private String provinceId;

    private String pinYin;

    private String pinYinChar;

    private String telCode;

    private List<ComCountyEx> countyList;

    public List<ComCountyEx> getCountyList() {
        return countyList;
    }

    public void setCountyList(List<ComCountyEx> countyList) {
        this.countyList = countyList;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getPinYinChar() {
        return pinYinChar;
    }

    public void setPinYinChar(String pinYinChar) {
        this.pinYinChar = pinYinChar;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public String getTelCode() {
        return telCode;
    }

    public void setTelCode(String telCode) {
        this.telCode = telCode;
    }
}
