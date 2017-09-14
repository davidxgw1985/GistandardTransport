package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 帐号升级单位枚举值
 */
public class UnitBean implements Serializable{

    // 车辆类型
    private List<ComAllType> carTypeList;

    // 车辆集装箱类型
    private List<ComAllType> boxTypeList;

    // 车辆集装箱空重标识
    private List<ComAllType> emptyWeightFlagList;

    // 车辆集装箱来源代码
    private List<ComAllType> sourceCodeList;

    // 车辆托架或拖挂车类型
    private List<ComAllType> trayTypeList;

    // 币制
    private List<ComCurrency> currencyList;

    // 员工人数
    private List<ComAllType> employeesList;

    // 自有车辆
    private List<ComAllType> ownerCarList;

    // 服务范围
    private List<ComAllType> serviceScopeList;

    public List<ComAllType> getCarTypeList() {
        return carTypeList;
    }

    public void setCarTypeList(List<ComAllType> carTypeList) {
        this.carTypeList = carTypeList;
    }

    public List<ComAllType> getBoxTypeList() {
        return boxTypeList;
    }

    public void setBoxTypeList(List<ComAllType> boxTypeList) {
        this.boxTypeList = boxTypeList;
    }

    public List<ComAllType> getEmptyWeightFlagList() {
        return emptyWeightFlagList;
    }

    public void setEmptyWeightFlagList(List<ComAllType> emptyWeightFlagList) {
        this.emptyWeightFlagList = emptyWeightFlagList;
    }

    public List<ComAllType> getSourceCodeList() {
        return sourceCodeList;
    }

    public void setSourceCodeList(List<ComAllType> sourceCodeList) {
        this.sourceCodeList = sourceCodeList;
    }

    public List<ComAllType> getTrayTypeList() {
        return trayTypeList;
    }

    public void setTrayTypeList(List<ComAllType> trayTypeList) {
        this.trayTypeList = trayTypeList;
    }

    public List<ComCurrency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<ComCurrency> currencyList) {
        this.currencyList = currencyList;
    }

    public List<ComAllType> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<ComAllType> employeesList) {
        this.employeesList = employeesList;
    }

    public List<ComAllType> getOwnerCarList() {
        return ownerCarList;
    }

    public void setOwnerCarList(List<ComAllType> ownerCarList) {
        this.ownerCarList = ownerCarList;
    }

    public List<ComAllType> getServiceScopeList() {
        return serviceScopeList;
    }

    public void setServiceScopeList(List<ComAllType> serviceScopeList) {
        this.serviceScopeList = serviceScopeList;
    }
}
