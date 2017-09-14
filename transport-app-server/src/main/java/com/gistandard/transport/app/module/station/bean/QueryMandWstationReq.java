package com.gistandard.transport.app.module.station.bean;

/**
 * Created by m on 2016/10/8.
 */
public class QueryMandWstationReq {
    private String busiNo;  //订单编号
    private String type;  //商户类型：OPERATOR_COURIER（快递员）,OPERATOR_CAR_OWNER(司机）,OPERATOR_MSTATION（咪站）
    private Integer userId;  //商户编号
    private String mwType;            //站点类型 M:咪站，W:蛙站(即HUB站点)
    private Integer stationCategoryAttr;            //蛙站性质：1：固定站点，2：移动站点
    private String itemCode;

    public String getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getMwType() {
        return mwType;
    }

    public void setMwType(String mwType) {
        this.mwType = mwType;
    }

    public Integer getStationCategoryAttr() {
        return stationCategoryAttr;
    }

    public void setStationCategoryAttr(Integer stationCategoryAttr) {
        this.stationCategoryAttr = stationCategoryAttr;
    }
}
