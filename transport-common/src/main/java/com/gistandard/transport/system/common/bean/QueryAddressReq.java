package com.gistandard.transport.system.common.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author kongxm
 * @ClassName AddressReq
 * @Description 客户下单地址管理请求
 * @Version 1.0
 * @Date 2016/1/26
 */
public class QueryAddressReq extends AppBasePagerRequest implements ValidTokenMark{
    @ApiModelProperty(value = "ID 新增地址不需要传", required = false)
    private Integer id;
    @ApiModelProperty(value = "姓名", required = true, position = 1)
    private String name;
    @ApiModelProperty(value = "公司名称", required = false, position = 2)
    private String companyName;
    @ApiModelProperty(value = "手机", required = true, position = 3)
    private String telephone;
    @ApiModelProperty(value = "国家", required = true, position = 4)
    private String country;
    @ApiModelProperty(value = "省", required = true, position = 5)
    private String province;
    @ApiModelProperty(value = "市", required = true, position = 6)
    private String city;
    @ApiModelProperty(value = "区", required = false, position = 7)
    private String county;
    @ApiModelProperty(value = "地址", required = true, position = 8)
    private String address;
    @ApiModelProperty(value = "areaId 新增地址不需要传", required = false, position = 9)
    private String areaId;
    @ApiModelProperty(value = "查询输入信息 新增地址不需要传", required = false, position = 10)
    private String description; // 查询输入信息
    @ApiModelProperty(value = "地址类型 1发货地址，2收货地址", required = true, position = 11)
    private Integer addressType;
    @ApiModelProperty(value = "是否默认（发货、收货）地址 ", required = true, position = 12)
    private boolean defaultAddress;// 是否默认收货地址
    @ApiModelProperty(value = "经度", required = true, position = 13)
    private BigDecimal addressLongitude;// 经度
    @ApiModelProperty(value = "纬度", required = true, position = 14)
    private BigDecimal addressLatitude;// 纬度
    @ApiModelProperty(value = "详细地址", required = true, position = 15)
    private String detailAddress;
    @ApiModelProperty(value = "邮编", required = false, position = 16)
    private String zipCode;
    @ApiModelProperty(value = "入仓号 暂时不用", required = false, position = 17)
    private String prefixCode; // 入仓号
    @ApiModelProperty(value = "性别  1先生 2女士", required = true, position = 18)
    private Integer sex;// 1先生 2女士

    public String getPrefixCode() {
        return prefixCode;
    }

    public void setPrefixCode(String prefixCode) {
        this.prefixCode = prefixCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getAddressType() {
        return addressType;
    }

    public void setAddressType(Integer addressType) {
        this.addressType = addressType;
    }

    public boolean isDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public BigDecimal getAddressLongitude() {
        return addressLongitude;
    }

    public void setAddressLongitude(BigDecimal addressLongitude) {
        this.addressLongitude = addressLongitude;
    }

    public BigDecimal getAddressLatitude() {
        return addressLatitude;
    }

    public void setAddressLatitude(BigDecimal addressLatitude) {
        this.addressLatitude = addressLatitude;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}

