package com.gistandard.transport.base.entity.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ComGoodsType implements Serializable {
    private static final long serialVersionUID = -9192804683579508134L;

    @ApiModelProperty(value = "货物类型id",required = true, position = 1)
    private Integer id;

    @ApiModelProperty(value = "货物类型代码",required = true, position = 2)
    private String typeCode;

    @ApiModelProperty(value = "货物类型中文",required = true, position = 3)
    private String typeCh;

    @ApiModelProperty(value = "货物类型标识",required = true, position = 4)
    private Byte flag;

    @ApiModelProperty(value = "货物类型等级，暂时未用",required = true, position = 5)
    private String typeKind;

    @ApiModelProperty(value = "货物类型保险接口中代码",required = true, position = 6)
    private String insuranceCode;

    @ApiModelProperty(value = "货物类型状态",required = true, position = 7)
    private Integer status;

    @ApiModelProperty(value = "货物类型父Id,暂时未用",required = true, position = 8)
    private Integer parentId;

    @ApiModelProperty(value = "货物类型英文，暂时未用",required = true, position = 9)
    private String typeEn;

    @ApiModelProperty(value = "归属模块OTCKD,OTCZS（多个模块逗号分割）",required = true, position = 10)
    private String moduleCodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCh() {
        return typeCh;
    }

    public void setTypeCh(String typeCh) {
        this.typeCh = typeCh;
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public String getTypeKind() {
        return typeKind;
    }

    public void setTypeKind(String typeKind) {
        this.typeKind = typeKind;
    }

    public String getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(String insuranceCode) {
        this.insuranceCode = insuranceCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTypeEn() {
        return typeEn;
    }

    public void setTypeEn(String typeEn) {
        this.typeEn = typeEn;
    }

    public String getModuleCodes() {
        return moduleCodes;
    }

    public void setModuleCodes(String moduleCodes) {
        this.moduleCodes = moduleCodes;
    }
}