package com.gistandard.transport.base.entity.bean.ex;

import java.util.List;

/**
 * @author yujie
 * @ClassName CountryBean
 * @Description
 * @Version 1.0
 * @Date 2015-08-13
 */
public class ComCountryEx {
    private String countryCode;

    private String countryName;

    private List<ComProvinceEx> provinceList;

    public List<ComProvinceEx> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ComProvinceEx> provinceList) {
        this.provinceList = provinceList;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
