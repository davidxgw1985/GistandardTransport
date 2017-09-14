package com.gistandard.transport.order.module.mobilestation.bean.stock;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: QueryStockDetailListBean
 * @Date: 2016/2/2
 * @Description: 查询出入库历史 返回Bean
 */
public class QueryStockDetailListBean implements Serializable{
    private static final long serialVersionUID = -52851508551312693L;
    private int stockType;//出入库类型 0入库 1出库
    private String busiBookNo;//订单号
    private String goodsType;//货物类型
    private String goodsName;//货物名称 类型+品名
    private BigDecimal goodsQty;//货物数量
    private String goodsQtyUnit;//货物单位
    private String goodsQtyUnitName;//货物单位名称
    private String createDate;//出入库日期
    private String boxNum;//箱号

    public int getStockType() {
        return stockType;
    }

    public void setStockType(int stockType) {
        this.stockType = stockType;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
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

    public String getGoodsQtyUnitName() {
        return goodsQtyUnitName;
    }

    public void setGoodsQtyUnitName(String goodsQtyUnitName) {
        this.goodsQtyUnitName = goodsQtyUnitName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(String boxNum) {
        this.boxNum = boxNum;
    }
}
