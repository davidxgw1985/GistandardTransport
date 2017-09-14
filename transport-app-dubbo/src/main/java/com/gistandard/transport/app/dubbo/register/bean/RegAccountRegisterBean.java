package com.gistandard.transport.app.dubbo.register.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/4/6.
 */
public class RegAccountRegisterBean implements Serializable {
    private static final long serialVersionUID = -2117234846787514991L;

    // 注册类型：1.用户，2.商户，3.业务中心
    private String registerType;

    // 帐户类型：1.个人，2.企业
    private String acctType;

    // 居住地区
    @NotBlank(message = "居住地区不能为空")
    private String area;

    // 居住城市
    private String provinceAndCity;

    // 省份城市区县
    @NotBlank(message = "居住省份不能为空")
    private String provinceAndCity_sel;

    // 手机国家码前缀
    @NotBlank(message = "手机国家码前缀不能为空")
    private String telPrefix;

    // 手机号或者邮箱
    @NotBlank(message = "手机号不能为空")
    @Length(min = 0,max = 20, message = "手机号长度超过限制")
    private String telephone;

    // 验证码
    @NotBlank(message = "手机验证码不能为空")
    private String validateCode;

    // 电信区号
    @Length(min = 0,max = 5, message = "电信区号长度超过限制")
    private String telCode;

    // 国家
    private String country;

    // 省份
    private String province;

    // 城市
    private String city;

    // 区县
    private String county;

    // 0：显示个人和企业，1：显示企业
    private String showType;

    //推荐人帐号Id
    private Integer recommendAccountId;

    //推荐来源
    private Integer recommendFrom;

    private String regEmail;

    private Integer parentAccountId;

    private Integer createAccountId;

    public Integer getParentAccountId() {
        return parentAccountId;
    }

    public void setParentAccountId(Integer parentAccountId) {
        this.parentAccountId = parentAccountId;
    }

    public Integer getCreateAccountId() {
        return createAccountId;
    }

    public void setCreateAccountId(Integer createAccountId) {
        this.createAccountId = createAccountId;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public void setRegEmail(String regEmail) {
        this.regEmail = regEmail;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvinceAndCity() {
        return provinceAndCity;
    }

    public void setProvinceAndCity(String provinceAndCity) {
        this.provinceAndCity = provinceAndCity;
    }

    public String getProvinceAndCity_sel() {
        return provinceAndCity_sel;
    }

    public void setProvinceAndCity_sel(String provinceAndCity_sel) {
        this.provinceAndCity_sel = provinceAndCity_sel;
    }

    public String getTelPrefix() {
        return telPrefix;
    }

    public void setTelPrefix(String telPrefix) {
        this.telPrefix = telPrefix;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelCode() {
        return telCode;
    }

    public void setTelCode(String telCode) {
        this.telCode = telCode;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

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

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public Integer getRecommendAccountId() {
        return recommendAccountId;
    }

    public void setRecommendAccountId(Integer recommendAccountId) {
        this.recommendAccountId = recommendAccountId;
    }

    public Integer getRecommendFrom() {
        return recommendFrom;
    }

    public void setRecommendFrom(Integer recommendFrom) {
        this.recommendFrom = recommendFrom;
    }
}
