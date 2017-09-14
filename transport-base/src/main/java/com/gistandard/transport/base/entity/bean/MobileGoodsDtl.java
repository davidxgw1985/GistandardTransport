package com.gistandard.transport.base.entity.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MobileGoodsDtl implements Serializable {
    private static final long serialVersionUID = -2182578505968319296L;
    private Integer id;

    private Integer mobileBookingFormId;

    private String goodsType;

    private String goodsName;

    private BigDecimal goodsQty;

    private String goodsQtyUnit;

    private BigDecimal goodsLenght;

    private BigDecimal goodsWide;

    private BigDecimal goodsHeight;

    private BigDecimal goodsVolume;

    private String goodsVolumeUnit;

    private BigDecimal goodsWeight;

    private String goodsWeightUnit;

    private Date createDate;

    private String createUser;

    private Integer mobileScheduOrderId;

    private String goodsQtyUnitName;//数量单位名称
    private String goodsVolumeUnitName;//货物体积单位名称
    private String goodsWeightUnitName;//重量单位名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getGoodsLenght() {
        return goodsLenght;
    }

    public void setGoodsLenght(BigDecimal goodsLenght) {
        this.goodsLenght = goodsLenght;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getMobileScheduOrderId() {
        return mobileScheduOrderId;
    }

    public void setMobileScheduOrderId(Integer mobileScheduOrderId) {
        this.mobileScheduOrderId = mobileScheduOrderId;
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
}