package com.gistandard.transport.base.entity.bean.ex;

import java.util.List;

/**
 * @author yujie
 * @ClassName ProvinceBean
 * @Description
 * @Version 1.0
 * @Date 2015-08-13
 */
public class ComProvinceEx {

    private String provinceId;

    private String provinceName;

    private String countryCode;

    private List<ComCityEx> cityList;

    public List<ComCityEx> getCityList() {
        return cityList;
    }

    public void setCityList(List<ComCityEx> cityList) {
        this.cityList = cityList;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
