package com.gistandard.transport.app.module.register.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

@ApiModel(value = "APP个人帐号注册第二步参数对象", parent = AppBaseRequest.class)
public class CustomerRegStep2  extends AppBaseRequest implements Serializable {

    // 注册类型：1.用户，2.商户，3.业务中心
    @ApiModelProperty(value = "注册类型（直接使用第一步返回结果进行赋值）",required = false, position = 1)
    private String registerType;

    // 账户类型：1.个人，2.企业
    @ApiModelProperty(value = "帐号类型（直接使用第一步返回结果进行赋值）",required = false, position = 2)
    private String acctType;

    // 居住地区
    @ApiModelProperty(value = "居住地区（直接使用第一步返回结果进行赋值）",required = true, position = 3)
    private String area;

    // 居住城市
    @ApiModelProperty(value = "居住城市（直接使用第一步返回结果进行赋值）",required = true, position = 4)
    private String provinceAndCity;

    // 省份城市区县
    @ApiModelProperty(value = "省份城市区县（直接使用第一步返回结果进行赋值）",required = true, position = 5)
    private String provinceAndCity_sel;

    // 手机国家码前缀
    @ApiModelProperty(value = "手机国家码前缀（直接使用第一步返回结果进行赋值）",required = true, position = 6)
    private String telPrefix;

    // 手机号或者邮箱
    @ApiModelProperty(value = "手机号（直接使用第一步返回结果进行赋值）",required = true, position = 7)
    private String telephone;

    // 验证码
    @ApiModelProperty(value = "验证码",required = false, position = 8)
    private String validateCode;

    // 电信区号
    @ApiModelProperty(value = "电信区号（直接使用第一步返回结果进行赋值）",required = true, position = 9)
    private String telCode;

    // 国家
    @ApiModelProperty(value = "国家（直接使用第一步返回结果进行赋值）",required = false, position = 10)
    private String country;

    // 省份
    @ApiModelProperty(value = "省份（直接使用第一步返回结果进行赋值）",required = false, position = 11)
    private String province;

    // 城市
    @ApiModelProperty(value = "城市（直接使用第一步返回结果进行赋值）",required = false, position = 12)
    private String city;

    // 区县
    @ApiModelProperty(value = "区县（直接使用第一步返回结果进行赋值）",required = false, position = 13)
    private String county;

    // 0：显示个人和企业，1：显示企业
    @ApiModelProperty(value = "不需使用该参数",required = false, position = 14)
    private String showType;

    // 身份证号码
    @ApiModelProperty(value = "身份证号码",required = true, position = 15)
    @NotNull(message="身份证号码不能为空")
    @MinLength(value=0,message = "身份证位数不能小于0")
    @MaxLength(value=18,message = "身份证位数不能超过18")
    private String identno;

    // 真实姓名，企业名称
    @ApiModelProperty(value = "真实姓名",required = true, position = 16)
    @NotNull(message="真实姓名不能为空")
    @MinLength(value=0,message = "真实姓名长度不能小于0")
    @MaxLength(value=200,message = "真实姓名长度不能超过18")
    private String realName;

    // 身份证正面照片
    @ApiModelProperty(value = "身份证正面照片ID",required = true, position = 17)
    @NotNull(message="身份证正面照片不能为空")
    private Integer identityPositiveFileId;

    // 身份证反面照片
    @ApiModelProperty(value = "身份证反面照片ID",required = true, position = 18)
    @NotNull(message = "身份证反面照片不能为空")
    private Integer identityNegativeFileId;

    // 手持证件半身照照片
    @ApiModelProperty(value = "手持证件半身照照片ID",required = true, position = 19)
    @NotNull(message = "手持证件半身照照片不能为空")
    private Integer identityHalfFileId;

    // 身份证正面照片url
    @ApiModelProperty(value = "身份证正面照片url",required = false, position = 20)
    private String identityPositiveUrl;

    // 身份证反面照片url
    @ApiModelProperty(value = "身份证反面照片url",required = false, position = 21)
    private String identityNegativeUrl;

    // 手持证件半身照照片url
    @ApiModelProperty(value = "手持证件半身照照片url",required = false, position = 22)
    private String identityHalfUrl;

    // 真实头像照片ID
    @ApiModelProperty(value = "真实头像ID",required = false, position = 23)
    private Integer portraitFileId;

    // 真实头像照片
    @ApiModelProperty(value = "真实头像url",required = false, position = 24)
    private String userImg;

    // 真实头像照片url
    @ApiModelProperty(value = "真实头像url",required = false, position = 25)
    private String portraitUrl;


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

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getTelCode() {
        return telCode;
    }

    public void setTelCode(String telCode) {
        this.telCode = telCode;
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

    public String getIdentno() {
        return identno;
    }

    public void setIdentno(String identno) {
        this.identno = identno;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getIdentityPositiveFileId() {
        return identityPositiveFileId;
    }

    public void setIdentityPositiveFileId(Integer identityPositiveFileId) {
        this.identityPositiveFileId = identityPositiveFileId;
    }

    public Integer getIdentityNegativeFileId() {
        return identityNegativeFileId;
    }

    public void setIdentityNegativeFileId(Integer identityNegativeFileId) {
        this.identityNegativeFileId = identityNegativeFileId;
    }

    public Integer getIdentityHalfFileId() {
        return identityHalfFileId;
    }

    public void setIdentityHalfFileId(Integer identityHalfFileId) {
        this.identityHalfFileId = identityHalfFileId;
    }

    public String getIdentityPositiveUrl() {
        return identityPositiveUrl;
    }

    public void setIdentityPositiveUrl(String identityPositiveUrl) {
        this.identityPositiveUrl = identityPositiveUrl;
    }

    public String getIdentityNegativeUrl() {
        return identityNegativeUrl;
    }

    public void setIdentityNegativeUrl(String identityNegativeUrl) {
        this.identityNegativeUrl = identityNegativeUrl;
    }

    public String getIdentityHalfUrl() {
        return identityHalfUrl;
    }

    public void setIdentityHalfUrl(String identityHalfUrl) {
        this.identityHalfUrl = identityHalfUrl;
    }

    public Integer getPortraitFileId() {
        return portraitFileId;
    }

    public void setPortraitFileId(Integer portraitFileId) {
        this.portraitFileId = portraitFileId;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }
}
