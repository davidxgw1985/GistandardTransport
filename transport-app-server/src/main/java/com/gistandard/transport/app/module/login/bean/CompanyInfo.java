package com.gistandard.transport.app.module.login.bean;

import com.gistandard.transport.base.entity.bean.MobileMoudleRel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: CompanyInfo
 * @Date: 2016/11/15
 * @Description:
 */
@ApiModel(description = "用户所属企业信息")
public class CompanyInfo {

    @ApiModelProperty(value = "企业帐号ID", position = 1)
    private Integer companyAccountId;//企业帐号ID
    @ApiModelProperty(value = "企业帐号名称", position = 2)
    private String companyAccountName;//企业帐号名称
    @ApiModelProperty(value = "企业ID", position = 3)
    private Integer companyID;//企业ID
    @ApiModelProperty(value = "企业名称", position = 4)
    private String companyName;//企业名称
    @ApiModelProperty(value = "人员在企业中所属角色类型（1、企业管理员）", position = 5)
    private Integer roleType;//人员在企业中所属角色类型（1、企业管理员）
    @ApiModelProperty(value = "人员在企业下的角色列表，多个角色以英文逗号分割", position = 6)
    private String roleIds;//人员在企业下的角色列表，多个角色以英文逗号分割

    @ApiModelProperty(value = "KPP模块列表", position = 7)
    private List<MobileMoudleRel> mobileMoudleRels;

    public Integer getCompanyAccountId() {
        return companyAccountId;
    }

    public void setCompanyAccountId(Integer companyAccountId) {
        this.companyAccountId = companyAccountId;
    }

    public String getCompanyAccountName() {
        return companyAccountName;
    }

    public void setCompanyAccountName(String companyAccountName) {
        this.companyAccountName = companyAccountName;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public List<MobileMoudleRel> getMobileMoudleRels() {
        return mobileMoudleRels;
    }

    public void setMobileMoudleRels(List<MobileMoudleRel> mobileMoudleRels) {
        this.mobileMoudleRels = mobileMoudleRels;
    }
}
