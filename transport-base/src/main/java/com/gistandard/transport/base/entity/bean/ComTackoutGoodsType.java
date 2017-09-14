package com.gistandard.transport.base.entity.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ComTackoutGoodsType implements Serializable {
    private static final long serialVersionUID = 1060659076988783375L;

    @ApiModelProperty(value = "外卖货物类型Id", required = true, position = 1)
    private Integer id;

    @ApiModelProperty(value = "外卖货物类型代码", required = true, position = 2)
    private String typeCode;

    @ApiModelProperty(value = "外卖货物类型中文", required = true, position = 3)
    private String typeCh;

    @ApiModelProperty(value = "外卖货物类型标识", required = true, position = 4)
    private Byte flag;

    @ApiModelProperty(value = "外卖货物类型状态", required = true, position = 5)
    private Integer status;

    @ApiModelProperty(value = "外卖货物类型父Id", required = true, position = 6)
    private Integer parentId;

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
}