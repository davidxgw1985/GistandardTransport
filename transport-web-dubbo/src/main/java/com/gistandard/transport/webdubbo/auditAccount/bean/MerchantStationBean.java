package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商户用户站点认证资料填写bean
 */
public class MerchantStationBean implements Serializable {

    // 所属行业
    private String custGild;

    // 企业法人
    private String custMan;

    // 详细地址
    private String custAdd;

    // 门牌号码
    private String detailAdd;

    // 企业简称
    private String custTtl;

    // 企业编号
    private String customNo;

    // 站点经度
    private BigDecimal staLongitude;

    // 站点纬度
    private BigDecimal staLatitude;

    // 第一联系人姓名
    private String flinkMan;

    // 第一联系人办公电话
    private String fworkTel;

    // 第一联系人传真
    private String fworkFax;

    // 第一联系人邮箱
    private String femail;

    // 注册资本
    private Integer registerFund;

    // 注册资本币种
    private String registerFundCurrCode;

    // 成立时间
    private String foundedYearsStr;

    // 场地面积
    private BigDecimal areaNum;

    // 员工人数
    private String staffNum;

    // 自有车辆
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
