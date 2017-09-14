package com.gistandard.transport.order.module.mobilestation.bean;

import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotBlank;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: UpdateGoodsInfoReq
 * @Date: 1416/2/24
 * @Description: 新增货物或修改货物请求Bean
 */
public class MobileGoodsInfoReq implements Serializable{

    /** serialVersionUID*/
	private static final long serialVersionUID = -2705644393796519258L;
    private Integer goodsId;//Mobile货物信息主键 如果为空，则是新增，有为修改

    @NotBlank(message="货物类型不能为空")
    private String goodsType;//货物类型

    private String goodsName;//货物品名
    
    @NotBlank(message="货物数量不能为空")
    @MinLength(value=0,message = "货物数量位数不能小于0")
    @MaxLength(value=14,message = "货物数量位数不能超过14")
    private String goodsQty;//货物数量
    
    @MaxLength(value=3,message = "货物数量单位不能超过3")
    private String goodsQtyUnit;//货物数量单位
    
    @MinLength(value=0,message = "货物体积位数不能小于0")
    @MaxLength(value=14,message = "货物体积位数不能超过14")
    private String goodsVolume;//货物体积
    

    @MinLength(value=0,message = "货物长度位数不能小于0")
    @MaxLength(value=14,message = "货物长度位数不能超过14")
    private String goodsLength;//货物长度 单位cm
    

    @MinLength(value=0,message = "货物宽度位数不能小于0")
    @MaxLength(value=14,message = "货物宽度位数不能超过14")
    private String goodsWide;//货物宽度 单位cm
    

    @MinLength(value=0,message = "货物高度 位数不能小于0")
    @MaxLength(value=14,message = "货物高度 位数不能超过14")
    private String goodsHeight;//货物高度 单位cm
    
    @MaxLength(value=3,message = "货物体积单位不能超过3")
    private String goodsVolumeUnit;//货物体积单位
    
    @NotBlank(message="货物重量不能为空")
    @MinLength(value=0,message = "货物重量 位数不能小于0")
    @MaxLength(value=14,message = "货物重量 位数不能超过14")
    private String goodsWeight;//货物重量
    
    @MaxLength(value=3,message = "货物重量单位不能超过3")
    private String goodsWeightUnit;//货物重量单位
    
    private String acctUsername;//用户登陆账号


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
