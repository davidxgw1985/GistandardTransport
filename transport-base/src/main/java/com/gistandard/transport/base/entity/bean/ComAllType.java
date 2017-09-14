package com.gistandard.transport.base.entity.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ComAllType implements Serializable {
    private static final long serialVersionUID = 4214300652182607448L;

    @ApiModelProperty(value = "字典Id", required = true, position = 1)
    private Integer id;

    @ApiModelProperty(value = "字典代码", required = true, position = 2)
    private String tType;

    @ApiModelProperty(value = "字典值编号，在本字典下唯一", required = true, position = 3)
    private String tCode;

    @ApiModelProperty(value = "字典值名称", required = true, position = 4)
    private String tName;

    @ApiModelProperty(value = "字典值备注", required = true, position = 5)
    private String tRemark;

    @ApiModelProperty(value = "字典值状态位", required = true, position = 6)
    private Boolean tFalg;

    @ApiModelProperty(value = "字典值排序号", required = true, position = 7)
    private Integer tSort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettType() {
        return tType;
    }

    public void settType(String tType) {
        this.tType = tType;
    }

    public String gettCode() {
        return tCode;
    }

    public void settCode(String tCode) {
        this.tCode = tCode;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettRemark() {
        return tRemark;
    }

    public void settRemark(String tRemark) {
        this.tRemark = tRemark;
    }

    public Boolean gettFalg() {
        return tFalg;
    }

    public void settFalg(Boolean tFalg) {
        this.tFalg = tFalg;
    }

    public Integer gettSort() {
        return tSort;
    }

    public void settSort(Integer tSort) {
        this.tSort = tSort;
    }
}