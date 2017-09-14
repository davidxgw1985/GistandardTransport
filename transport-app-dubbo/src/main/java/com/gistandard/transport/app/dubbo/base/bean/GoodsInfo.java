package com.gistandard.transport.app.dubbo.base.bean;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by m on 2016/9/30.
 */
public class GoodsInfo implements Serializable {
    private static final long serialVersionUID = -8913847491193312690L;
    private String goodsType;//货物类型
    private String goodsName;//货物品名
    private BigDecimal qty;//货物数量
    private String qtyUnit;//货物单位
    private String custUnit;//自定义货物单位
    private Double weight;//货物重量
    private String weightUnit;//货物重量单位
    private Double length;//货物体积-长
    private Double width;//货物体积-宽
    private Double height;//货物体积-高

    public String getCustUnit() {
        return custUnit;
    }

    public void setCustUnit(String custUnit) {
        this.custUnit = custUnit;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getQtyUnit() {
        return qtyUnit;
    }

    public void setQtyUnit(String qtyUnit) {
        this.qtyUnit = qtyUnit;
    }
}
