package com.gistandard.transport.app.module.quote.bean;

import com.gistandard.transport.system.webservice.client.payinfo.PlatFormDetailModel;
import com.gistandard.transport.system.webservice.client.payinfo.PlatFormPriceModel;

import java.util.List;

/**
 * Created by m on 2016/8/29.
 */
public class PlatFormDetailModelMore extends PlatFormDetailModel {
    private String startProvince;
    private String startProvinceName;
    private String startCity;
    private String startCityName;
    private String startCounty;
    private String startCountyName;
    private String endProvince;
    private String endProvinceName;
    private String endCity;
    private String endCityName;
    private String endCounty;
    private String endCountyName;

    public String getStartProvince() {
        return startProvince;
    }

    public void setStartProvince(String startProvince) {
        this.startProvince = startProvince;
    }

    public String getStartProvinceName() {
        return startProvinceName;
    }

    public void setStartProvinceName(String startProvinceName) {
        this.startProvinceName = startProvinceName;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getStartCityName() {
        return startCityName;
    }

    public void setStartCityName(String startCityName) {
        this.startCityName = startCityName;
    }

    public String getStartCounty() {
        return startCounty;
    }

    public void setStartCounty(String startCounty) {
        this.startCounty = startCounty;
    }

    public String getStartCountyName() {
        return startCountyName;
    }

    public void setStartCountyName(String startCountyName) {
        this.startCountyName = startCountyName;
    }

    public String getEndProvince() {
        return endProvince;
    }

    public void setEndProvince(String endProvince) {
        this.endProvince = endProvince;
    }

    public String getEndProvinceName() {
        return endProvinceName;
    }

    public void setEndProvinceName(String endProvinceName) {
        this.endProvinceName = endProvinceName;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public String getEndCityName() {
        return endCityName;
    }

    public void setEndCityName(String endCityName) {
        this.endCityName = endCityName;
    }

    public String getEndCounty() {
        return endCounty;
    }

    public void setEndCounty(String endCounty) {
        this.endCounty = endCounty;
    }

    public String getEndCountyName() {
        return endCountyName;
    }

    public void setEndCountyName(String endCountyName) {
        this.endCountyName = endCountyName;
    }

    public void setOutPriceListByKilometor(List<PlatFormPriceModel> outPriceListByKilometor) {
        this.outPriceListByKilometor = outPriceListByKilometor;
    }

    public void setOutPriceListByWeight(List<PlatFormPriceModel> outPriceListByWeight) {
        this.outPriceListByWeight = outPriceListByWeight;
    }
}
