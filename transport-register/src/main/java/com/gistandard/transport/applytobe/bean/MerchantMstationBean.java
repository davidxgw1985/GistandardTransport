package com.gistandard.transport.applytobe.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商户用户咪站认证资料填写bean
 */
public class MerchantMstationBean extends MerchantPersonalBean implements Serializable{

    // 详细地址
    @NotBlank(message = "详细地址不能为空")
    @Length(min = 0,max = 64, message = "详细地址超出指定长度")
    private String address;

    @Length(min = 0,max = 50, message = "具体楼层、门牌号超出指定长度")
    private String detailAdd;

    // 站点经度
    @NotNull(message = "经度不能为空")
    @DecimalMax(value = "180",message = "经度数据不合法（-180 ~ 180）")
    @DecimalMin(value = "-180",message = "经度数据不合法（-180 ~ 180）")
    private BigDecimal staLongitude;

    // 站点纬度
    @NotNull(message = "纬度不能为空")
    @DecimalMax(value = "90",message = "纬度数据不合法（-90 ~ 90）")
    @DecimalMin(value = "-90",message = "纬度数据不合法（-90 ~ 90）")
    private BigDecimal staLatitude;

    // 营业时间的开始时间
    @NotNull(message = "{field.notNull}")
    @Length(min = 0,max = 10, message = "营业开始时间超出指定长度")
    private String businessHoursStart;

    // 营业时间的结束时间
    @NotNull(message = "{field.notNull}")
    @Length(min = 0,max = 10, message = "营业结束时间超出指定长度")
    private String businessHoursEnd;

    // 紧急联系人
    @Length(min = 0,max = 20, message = "紧急联系人超出指定长度")
    private String urgentLinkUser;

    // 紧急联系电话
    @Length(min = 0,max = 20, message = "紧急联系电话超出指定长度")
    private String urgentLinkTel;

    // 国家
    @NotNull(message = "{field.notNull}")
    private String country;

    // 省份
    @NotNull(message = "{field.notNull}")
    private String province;

    // 城市
    @NotNull(message = "{field.notNull}")
    private String city;

    // 区县
    @NotNull(message = "{field.notNull}")
    private String county;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAdd() {
        return detailAdd;
    }

    public void setDetailAdd(String detailAdd) {
        this.detailAdd = detailAdd;
    }

    public BigDecimal getStaLongitude() {
        return staLongitude;
    }

    public void setStaLongitude(BigDecimal staLongitude) {
        this.staLongitude = staLongitude;
    }

    public BigDecimal getStaLatitude() {
        return staLatitude;
    }

    public void setStaLatitude(BigDecimal staLatitude) {
        this.staLatitude = staLatitude;
    }

    public String getBusinessHoursStart() {
        return businessHoursStart;
    }

    public void setBusinessHoursStart(String businessHoursStart) {
        this.businessHoursStart = businessHoursStart;
    }

    public String getBusinessHoursEnd() {
        return businessHoursEnd;
    }

    public void setBusinessHoursEnd(String businessHoursEnd) {
        this.businessHoursEnd = businessHoursEnd;
    }

    public String getUrgentLinkUser() {
        return urgentLinkUser;
    }

    public void setUrgentLinkUser(String urgentLinkUser) {
        this.urgentLinkUser = urgentLinkUser;
    }

    public String getUrgentLinkTel() {
        return urgentLinkTel;
    }

    public void setUrgentLinkTel(String urgentLinkTel) {
        this.urgentLinkTel = urgentLinkTel;
    }
}
