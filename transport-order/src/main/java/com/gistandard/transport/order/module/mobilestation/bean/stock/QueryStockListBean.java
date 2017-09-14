package com.gistandard.transport.order.module.mobilestation.bean.stock;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: QueryStockListBean
 * @Date: 2016/2/2
 * @Description: 查询当前库存返回Bean
 */
public class QueryStockListBean implements Serializable{
    private static final long serialVersionUID = 5637723345646431138L;

    private String busiBookNo;//订单号
    private String goodsType;//货物类型
    private String goodsName;//货物名称 类型+品名
    private String goodsUnit;//货物单位
    private String goodsUnitName;//货物单位名称
    private BigDecimal goodsBookQty;//账面库存数量
    private BigDecimal goodsQty;//实际库存数量

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

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getGoodsUnitName() {
        return goodsUnitName;
    }

    public void setGoodsUnitName(String goodsUnitName) {
        this.goodsUnitName = goodsUnitName;
    }

    public BigDecimal getGoodsBookQty() {
        return goodsBookQty;
    }

    public void setGoodsBookQty(BigDecimal goodsBookQty) {
        this.goodsBookQty = goodsBookQty;
    }

    public BigDecimal getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(BigDecimal goodsQty) {
        this.goodsQty = goodsQty;
    }
}
