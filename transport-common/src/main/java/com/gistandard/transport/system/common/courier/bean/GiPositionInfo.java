package com.gistandard.transport.system.common.courier.bean;

import com.gistandard.transport.system.webservice.client.gps.GiPoint;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: GiPositionInfo
 * @Date: 2017/9/7
 * @Description:
 */
public class GiPositionInfo implements Serializable{
    private static final long serialVersionUID = 5723799109948679580L;
    private GiPoint giPoint;
    private String userCode;
    private String userName;
    private String servicingCompanyCode;            //正在为该公司服务, 即快递员现在为哪家公司服务--员工快递员
    private List<String> allRoleCode;               //所有开放的角色, 例如OPERATOR_CAR_OWNER，OPERATOR_COURIER

    public GiPoint getGiPoint() {
        return giPoint;
    }

    public void setGiPoint(GiPoint giPoint) {
        this.giPoint = giPoint;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getServicingCompanyCode() {
        return servicingCompanyCode;
    }

    public void setServicingCompanyCode(String servicingCompanyCode) {
        this.servicingCompanyCode = servicingCompanyCode;
    }

    public List<String> getAllRoleCode() {
        return allRoleCode;
    }

    public void setAllRoleCode(List<String> allRoleCode) {
        this.allRoleCode = allRoleCode;
    }
}
