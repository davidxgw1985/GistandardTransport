package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: UpdateGoodsInfoReq
 * @Date: 2016/2/24
 * @Description: 新增货物或修改货物请求Bean
 */
public class UpdateGoodsInfoReq extends AppBaseRequest {
    private static final long serialVersionUID = -2820622622347129520L;

    private Integer orderId;//MobileBookingForm 主键
    private Integer goodsId;//Mobile货物信息主键 如果为空，则是新增，有为修改
    private String goodsType;//货物类型
    private String goodsName;//货物品名
    private String goodsQty;//货物数量
    private String goodsQtyUnit;//货物数量单位
    private String goodsVolume;//货物体积
    private String goodsLength;//货物长度 单位cm
    private String goodsWide;//货物宽度 单位cm
    private String goodsHeight;//货物高度 单位cm
    private String goodsVolumeUnit;//货物体积单位
    private String goodsWeight;//货物重量
    private String goodsWeightUnit;//货物重量
    private String acctUsername;//用户登陆账号

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(String goodsQty) {
        this.goodsQty = goodsQty;
    }

    public String getGoodsQtyUnit() {
        return goodsQtyUnit;
    }

    public void setGoodsQtyUnit(String goodsQtyUnit) {
        this.goodsQtyUnit = goodsQtyUnit;
    }

    public String getGoodsVolume() {
        return goodsVolume;
    }

    public void setGoodsVolume(String goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public String getGoodsLength() {
        return goodsLength;
    }

    public void setGoodsLength(String goodsLength) {
        this.goodsLength = goodsLength;
    }

    public String getGoodsWide() {
        return goodsWide;
    }

    public void setGoodsWide(String goodsWide) {
        this.goodsWide = goodsWide;
    }

    public String getGoodsHeight() {
        return goodsHeight;
    }

    public void setGoodsHeight(String goodsHeight) {
        this.goodsHeight = goodsHeight;
    }

    public String getGoodsVolumeUnit() {
        return goodsVolumeUnit;
    }

    public void setGoodsVolumeUnit(String goodsVolumeUnit) {
        this.goodsVolumeUnit = goodsVolumeUnit;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsWeightUnit() {
        return goodsWeightUnit;
    }

    public void setGoodsWeightUnit(String goodsWeightUnit) {
        this.goodsWeightUnit = goodsWeightUnit;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }
}
