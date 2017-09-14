package com.gistandard.transport.applytobe.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 业务中心认证资料填写bean
 */
public class ServiceCenterBean {

    // 所属行业
    @NotBlank(message = "{field.notNull}")
    @Length(min = 0,max = 16, message = "{StationRegisterBean.custGild.length}")
    private String custGild;

    // 企业法人
    @NotBlank(message = "{field.notNull}")
    @Length(min = 0,max = 16, message = "{StationRegisterBean.custMan.length}")
    private String custMan;

    // 详细地址
    @NotBlank(message = "{field.notNull}")
    @Length(min = 0,max = 64, message = "{StationRegisterBean.custAdd.length}")
    private String custAdd;

    @Length(min = 0,max = 50, message = "{StationRegisterBean.detailAdd.length}")
    private String detailAdd;

    // 企业简称
    @NotBlank(message = "{field.notNull}")
    @Length(min = 0,max = 8, message = "{StationRegisterBean.custTtl.length}")
    private String custTtl;

    // 企业编号
    @Length(min = 0,max = 32, message = "{CompanyRegisterBean.customNo.length}")
    private String customNo;

    // 站点经度
    @NotNull(message = "{field.notNull}")
    @DecimalMax(value = "180",message = "{StationRegisterBean.staLongitude}")
    @DecimalMin(value = "-180",message = "{StationRegisterBean.staLongitude}")
    private BigDecimal staLongitude;

    // 站点纬度
    @NotNull(message = "{field.notNull}")
    @DecimalMax(value = "90",message = "{StationRegisterBean.staLatitude}")
    @DecimalMin(value = "-90",message = "{StationRegisterBean.staLatitude}")
    private BigDecimal staLatitude;

    // 第一联系人姓名
    @NotBlank(message = "{field.notNull}")
    @Length(min = 0,max = 16, message = "{StationRegisterBean.flinkMan.length}")
    private String flinkMan;

    // 第一联系人办公电话
    @NotBlank(message = "{field.notNull}")
    @Length(min = 0,max = 32, message = "{StationRegisterBean.fworkTel.length}")
    private String fworkTel;

    // 第一联系人传真
    @Length(min = 0,max = 256, message = "{StationRegisterBean.fworkFax.length}")
    private String fworkFax;

    // 第一联系人邮箱
    @Length(min = 0,max = 48, message = "{StationRegisterBean.femail.length}")
    private String femail;

    // 注册资本
    @NotNull(message = "{field.notNull}")
    @Max(value = 999999999, message = "{StationRegisterBean.registerFund.length}")
    private Integer registerFund;

    // 注册资本币种
    @NotBlank(message = "{field.notNull}")
    @Length(min = 0,max = 24, message = "{StationRegisterBean.registerFundCurrCode.length}")
    private String registerFundCurrCode;

    // 成立时间
    @NotBlank(message = "{field.notNull}")
    @Length(min = 0,max = 10, message = "{StationRegisterBean.foundedYearsStr.length}")
    private String foundedYearsStr;

    // 场地面积
    @NotNull(message = "{field.notNull}")
    @DecimalMax(value = "10000000000",message = "{StationRegisterBean.areaNum.length}")
    private BigDecimal areaNum;

    // 员工人数
    @NotBlank(message = "{field.notNull}")
    @Length(min = 0,max = 50, message = "{StationRegisterBean.staffNum.length}")
    private String staffNum;

    // 自有车辆
    @NotNull(message = "{field.notNull}")
    @Max(value = 1000000,message = "{StationRegisterBean.selfOwnerCarNum.length}")
    private Integer selfOwnerCarNum;

    public String getCustGild() {
        return custGild;
    }

    public void setCustGild(String custGild) {
        this.custGild = custGild;
    }

    public String getCustMan() {
        return custMan;
    }

    public void setCustMan(String custMan) {
        this.custMan = custMan;
    }

    public String getCustAdd() {
        return custAdd;
    }

    public void setCustAdd(String custAdd) {
        this.custAdd = custAdd;
    }

    public String getDetailAdd() {
        return detailAdd;
    }

    public void setDetailAdd(String detailAdd) {
        this.detailAdd = detailAdd;
    }

    public String getCustTtl() {
        return custTtl;
    }

    public void setCustTtl(String custTtl) {
        this.custTtl = custTtl;
    }

    public String getCustomNo() {
        return customNo;
    }

    public void setCustomNo(String customNo) {
        this.customNo = customNo;
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

    public String getFlinkMan() {
        return flinkMan;
    }

    public void setFlinkMan(String flinkMan) {
        this.flinkMan = flinkMan;
    }

    public String getFworkTel() {
        return fworkTel;
    }

    public void setFworkTel(String fworkTel) {
        this.fworkTel = fworkTel;
    }

    public String getFworkFax() {
        return fworkFax;
    }

    public void setFworkFax(String fworkFax) {
        this.fworkFax = fworkFax;
    }

    public String getFemail() {
        return femail;
    }

    public void setFemail(String femail) {
        this.femail = femail;
    }

    public Integer getRegisterFund() {
        return registerFund;
    }

    public void setRegisterFund(Integer registerFund) {
        this.registerFund = registerFund;
    }

    public String getRegisterFundCurrCode() {
        return registerFundCurrCode;
    }

    public void setRegisterFundCurrCode(String registerFundCurrCode) {
        this.registerFundCurrCode = registerFundCurrCode;
    }

    public String getFoundedYearsStr() {
        return foundedYearsStr;
    }

    public void setFoundedYearsStr(String foundedYearsStr) {
        this.foundedYearsStr = foundedYearsStr;
    }

    public BigDecimal getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(BigDecimal areaNum) {
        this.areaNum = areaNum;
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
    }

    public Integer getSelfOwnerCarNum() {
        return selfOwnerCarNum;
    }

    public void setSelfOwnerCarNum(Integer selfOwnerCarNum) {
        this.selfOwnerCarNum = selfOwnerCarNum;
    }
}
