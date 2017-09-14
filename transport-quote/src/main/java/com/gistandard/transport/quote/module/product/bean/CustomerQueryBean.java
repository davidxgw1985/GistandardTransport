package com.gistandard.transport.quote.module.product.bean;


import com.gistandard.transport.base.bean.grid.GridBean;

/**
 * Created by shenzhijun on 2016/2/24.
 */
public class CustomerQueryBean extends GridBean {

    //角色信息多个角色逗号分割,
    private String roles;

    private String country ;

    private String province ;

    private String city ;

    private String county ;

    private String areaInput ;

    private String custName;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public String getAreaInput() {
        return areaInput;
    }

    public void setAreaInput(String areaInput) {
        this.areaInput = areaInput;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
}
