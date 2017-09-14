package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;

public class ComCountry implements Serializable {
    private static final long serialVersionUID = 1122928658371515168L;
    private Integer id;

    private String countryCode;

    private String countryNameEn;

    private String countryNameCh;

    private String countryTtl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryNameEn() {
        return countryNameEn;
    }

    public void setCountryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    public String getCountryNameCh() {
        return countryNameCh;
    }

    public void setCountryNameCh(String countryNameCh) {
        this.countryNameCh = countryNameCh;
    }

    public String getCountryTtl() {
        return countryTtl;
    }

    public void setCountryTtl(String countryTtl) {
        this.countryTtl = countryTtl;
    }
}