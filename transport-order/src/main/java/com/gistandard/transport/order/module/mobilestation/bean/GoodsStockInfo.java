package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: GoodsStockInfo
 * @Date: 2016/3/31
 * @Description:
 */
public class GoodsStockInfo implements Serializable{
    private static final long serialVersionUID = -6633567804463665521L;

    private String busiBookNo;//订单号
    private Integer dispatchId;//签派ID
    private String scheducarno;//派车单ID
    private String goodsType;//类型
    private String goodsName;//货物名称
    private Integer goodsQty;//货物数量
    private String goodsQtyUnit;//货物数量单位
    private String goodsQtyUnitName;//货物数量单位
    private Integer orderFrom;//订单来源
    private String startProvide;
    private String startCity;
    private String startCounty;
    private String startAddress;//起点地址
    private String destProvide;
    private String destCity;
    private String destCounty;
    private String destAddress;//收货地址
    private Integer busiCtrl;//业务状态控制
    private String busiCtrlName;//接单状态

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
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

    public Integer getGoodsQty() {
        return goodsQty;
    }

    public void setGoodsQty(Integer goodsQty) {
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

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getStartProvide() {
        return startProvide;
    }

    public void setStartProvide(String startProvide) {
        this.startProvide = startProvide;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getStartCounty() {
        return startCounty;
    }

    public void setStartCounty(String startCounty) {
        this.startCounty = startCounty;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getDestProvide() {
        return destProvide;
    }

    public void setDestProvide(String destProvide) {
        this.destProvide = destProvide;
    }

    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    public String getDestCounty() {
        return destCounty;
    }

    public void setDestCounty(String destCounty) {
        this.destCounty = destCounty;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public String getBusiCtrlName() {
        return busiCtrlName;
    }

    public void setBusiCtrlName(String busiCtrlName) {
        this.busiCtrlName = busiCtrlName;
    }
}
