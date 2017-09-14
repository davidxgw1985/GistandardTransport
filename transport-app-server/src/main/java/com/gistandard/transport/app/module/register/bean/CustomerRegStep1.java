package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

@ApiModel(value = "APP个人帐号注册第一步参数对象", parent = AppBaseRequest.class)
public class CustomerRegStep1 extends AppBaseRequest implements Serializable {

    // 注册类型：1.用户，2.商户，3.业务中心
    @ApiModelProperty(value = "注册类型：1.用户，2.商户，3.业务中心，4.商业中心（不用填写该参数）",required = false, position = 1)
    private String registerType;

    // 帐号类型：1.个人，2.企业
    @ApiModelProperty(value = "帐号类型：1.个人，2.企业（不用填写该参数）",required = false, position = 2)
    private String acctType;

    // 居住地区
    @ApiModelProperty(value = "居住地区",required = true, position = 3)
    @NotNull(message="居住地区不能为空")
    private String area;

    // 居住城市
    @ApiModelProperty(value = "居住城市",required = true, position = 4)
    @NotNull(message="居住城市不能为空")
    private String provinceAndCity;

    // 省份城市区县
    @ApiModelProperty(value = "省份城市区县",required = true, position = 5)
    @NotNull(message="省市区域ID不能为空")
    private String provinceAndCity_sel;

    // 手机国家码前缀
    @ApiModelProperty(value = "手机国家码前缀",required = true, position = 6)
    @NotNull(message="国家码前缀不能为空")
    private String telPrefix;

    // 手机号码
    @ApiModelProperty(value = "手机号码",required = true, position = 7)
    @NotNull(message="手机号码")
    @MinLength(value=0,message = "手机号码位数不能小于0")
    @MaxLength(value=20,message = "手机号码位数不能超过20")
    private String telephone;

    // 验证码
    @ApiModelProperty(value = "验证码",required = true, position = 8)
    private String validateCode;

    // 电信区号
    @ApiModelProperty(value = "电信区号(不用填写该参数，根据所传省市数据查询)",required = false, position = 9)
    @MinLength(value=0,message = "电信区号位数不能小于0")
    @MaxLength(value=5,message = "电信区号位数不能超过5")
    private String telCode;

    // 国家
    @ApiModelProperty(value = "国家(不用填写该参数)",required = false, position = 10)
    private String country;

    // 省份
    @ApiModelProperty(value = "省份(不用填写该参数)",required = false, position = 11)
    private String province;

    // 城市
    @ApiModelProperty(value = "城市(不用填写该参数)",required = false, position = 12)
    private String city;

    // 区县
    @ApiModelProperty(value = "区县(不用填写该参数)",required = false, position = 13)
    private String county;

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
}
