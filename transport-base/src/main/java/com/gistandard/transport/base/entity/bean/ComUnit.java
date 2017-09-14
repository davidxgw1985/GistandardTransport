package com.gistandard.transport.base.entity.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ComUnit implements Serializable {
    private static final long serialVersionUID = -8107567054194341599L;

    @ApiModelProperty(value = "单位Id",required = true, position = 1)
    private Integer id;

    @ApiModelProperty(value = "单位代码",required = true, position = 2)
    private String unitCode;

    @ApiModelProperty(value = "单位英文",required = true, position = 3)
    private String unitEn;

    @ApiModelProperty(value = "单位中文",required = true, position = 4)
    private String unitCh;

    @ApiModelProperty(value = "状态标识",required = true, position = 5)
    private Boolean flag;

    @ApiModelProperty(value = "单位类型",required = true, position = 6)
    private Integer unitType;

    @ApiModelProperty(value = "保险接口中单位代码",required = true, position = 7)
    private String insuranceCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitEn() {
        return unitEn;
    }

    public void setUnitEn(String unitEn) {
        this.unitEn = unitEn;
    }

    public String getUnitCh() {
        return unitCh;
    }

    public void setUnitCh(String unitCh) {
        this.unitCh = unitCh;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public String getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(String insuranceCode) {
        this.insuranceCode = insuranceCode;
    }
}