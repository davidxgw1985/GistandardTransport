package com.gistandard.transport.system.common.station.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: BusinessRelation
 * @Date: 2017/2/27
 * @Description: 商业中心 业务员
 */
@ApiModel(description = "商业中心 业务员列表查询返回Bean")
public class BusinessRelation implements Serializable{
    private static final long serialVersionUID = -2691757457107595356L;

    @ApiModelProperty(value = "账号ID", position = 1)
    private Integer accountId;//账号ID
    @ApiModelProperty(value = "帐户名称", position = 2)
    private String acctUsername;//帐户名称
    @ApiModelProperty(value = "姓名", position = 3)
    private String realname;//姓名
    @ApiModelProperty(value = "电话", position = 4)
    private String telephone;//电话
    @ApiModelProperty(value = "省市区地址", position = 5)
    private String address;//省市区地址

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
