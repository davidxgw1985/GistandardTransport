package com.gistandard.transport.system.gps.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: GiPositionAcct
 * @Date: 2017/7/18
 * @Description:
 */
public class GiPositionAcct implements Serializable{
    private static final long serialVersionUID = 2187503057373237897L;
    private String userCode;
    private String companyCode;
    private List<String> allModuleCode;             //所有开放的模块，
    private List<String> allRoleCode;               //所有开放的角色, 例如OPERATOR_CAR_OWNER，OPERATOR_COURIER
    private Boolean sharingPosition;                //正在上传位置

    public List<String> getAllModuleCode() {
        return allModuleCode;
    }

    public void setAllModuleCode(List<String> allModuleCode) {
        this.allModuleCode = allModuleCode;
    }

    public List<String> getAllRoleCode() {
        return allRoleCode;
    }

    public void setAllRoleCode(List<String> allRoleCode) {
        this.allRoleCode = allRoleCode;
    }

    public Boolean isSharingPosition() {
        return sharingPosition;
    }

    public void setSharingPosition(Boolean sharingPosition) {
        this.sharingPosition = sharingPosition;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
