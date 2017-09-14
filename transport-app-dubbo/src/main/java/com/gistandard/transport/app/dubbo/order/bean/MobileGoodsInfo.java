package com.gistandard.transport.app.dubbo.order.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: MobileGoodsInfo
 * @Date: 2016/3/3
 * @Description:
 */
@ApiModel(description = "订单货物信息")
public class MobileGoodsInfo implements Serializable{
    private static final long serialVersionUID = -8802078234535179505L;

    @ApiModelProperty(value = "货物编号", position = 1)
    private Integer goodsId;
    @ApiModelProperty(value = "Mobile订仓订单序号", position = 2)
    private Integer mobileBookingFormId;
    @ApiModelProperty(value = "货物类型", position = 3)
    private String goodsType;
    @ApiModelProperty(value = "货物类型名称", position = 4)
    private String goodsTypeName;
    @ApiModelProperty(value = "货物名称", position = 5)
    private String goodsName;
    @ApiModelProperty(value = "数量", position = 6)
    private BigDecimal goodsQty;
    @ApiModelProperty(value = "数量单位编码", position = 7)
    private String goodsQtyUnit;
    @ApiModelProperty(value = "数量单位名称", position = 8)
    private String goodsQtyUnitName;
    @ApiModelProperty(value = "货物长度", position = 9)
    private BigDecimal goodsLength;
    @ApiModelProperty(value = "货物宽度", position = 10)
    private BigDecimal goodsWide;
    @ApiModelProperty(value = "货物高度", position = 11)
    private BigDecimal goodsHeight;
    @ApiModelProperty(value = "货物体积", position = 12)
    private BigDecimal goodsVolume;
    @ApiModelProperty(value = "货物体积单位", position = 13)
    private String goodsVolumeUnit;
    @ApiModelProperty(value = "货物体积单位名称", position = 14)
    private String goodsVolumeUnitName;
    @ApiModelProperty(value = "重量", position = 15)
    private BigDecimal goodsWeight;
    @ApiModelProperty(value = "重量单位编码", position = 16)
    private String goodsWeightUnit;
    @ApiModelProperty(value = "重量单位名称", position = 17)
    private String goodsWeightUnitName;
    @ApiModelProperty(value = "派车单子订单编号", position = 18)
    private Integer mobileScheduOrderId;
    @ApiModelProperty(value = "派车单子订单", position = 19)
    private String mobileScheduOrderNo;

    public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getMobileBookingFormId() {
        return mobileBookingFormId;
    }

    public void setMobileBookingFormId(Integer mobileBookingFormId) {
        this.mobileBookingFormId = mobileBookingFormId;
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

    public BigDecimal getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(BigDecimal goodsQty) {
        this.goodsQty = goodsQty;
    }

    public String getGoodsQtyUnit() {
        return goodsQtyUnit;
    }

    public void setGoodsQtyUnit(String goodsQtyUnit) {
        this.goodsQtyUnit = goodsQtyUnit;
    }

    public BigDecimal getGoodsLength() {
        return goodsLength;
    }

    public void setGoodsLength(BigDecimal goodsLength) {
        this.goodsLength = goodsLength;
    }

    public BigDecimal getGoodsWide() {
        return goodsWide;
    }

    public void setGoodsWide(BigDecimal goodsWide) {
        this.goodsWide = goodsWide;
    }

    public BigDecimal getGoodsHeight() {
        return goodsHeight;
    }

    public void setGoodsHeight(BigDecimal goodsHeight) {
        this.goodsHeight = goodsHeight;
    }

    public BigDecimal getGoodsVolume() {
        return goodsVolume;
    }

    public void setGoodsVolume(BigDecimal goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public String getGoodsVolumeUnit() {
        return goodsVolumeUnit;
    }

    public void setGoodsVolumeUnit(String goodsVolumeUnit) {
        this.goodsVolumeUnit = goodsVolumeUnit;
    }

    public BigDecimal getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(BigDecimal goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsWeightUnit() {
        return goodsWeightUnit;
    }

    public void setGoodsWeightUnit(String goodsWeightUnit) {
        this.goodsWeightUnit = goodsWeightUnit;
    }

    public String getGoodsQtyUnitName() {
        return goodsQtyUnitName;
    }

    public void setGoodsQtyUnitName(String goodsQtyUnitName) {
        this.goodsQtyUnitName = goodsQtyUnitName;
    }

    public String getGoodsVolumeUnitName() {
        return goodsVolumeUnitName;
    }

    public void setGoodsVolumeUnitName(String goodsVolumeUnitName) {
        this.goodsVolumeUnitName = goodsVolumeUnitName;
    }

    public String getGoodsWeightUnitName() {
        return goodsWeightUnitName;
    }

    public void setGoodsWeightUnitName(String goodsWeightUnitName) {
        this.goodsWeightUnitName = goodsWeightUnitName;
    }

    public Integer getMobileScheduOrderId() {
        return mobileScheduOrderId;
    }

    public void setMobileScheduOrderId(Integer mobileScheduOrderId) {
        this.mobileScheduOrderId = mobileScheduOrderId;
    }

    public String getMobileScheduOrderNo() {
        return mobileScheduOrderNo;
    }

    public void setMobileScheduOrderNo(String mobileScheduOrderNo) {
        this.mobileScheduOrderNo = mobileScheduOrderNo;
    }
}
