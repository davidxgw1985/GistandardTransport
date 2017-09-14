package com.gistandard.transport.order.module.mobilestation.bean.stock;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: StockInOutReq
 * @Date: 2016/2/1
 * @Description: 出入库请求Bean
 */
public class StockInOutReq extends AppBaseRequest {
    private static final long serialVersionUID = 1668850717130095325L;

    private Integer stockType;//出入库类型 0入库 1出库
    /**
     * stockModel 出入库方式
     * 入库：1、手工确认收货，需要订单号或者派车单号
     *       2、扫描MS生成的订单码收货（MS生成派车单二维码、订单二维码），需要订单号或者派车单号，代出库
     *       3、扫描货物二维码（板、箱上二维码）物流需要：订单类型、订单号、板号(箱号)、板型、单板箱数、长、宽、高、重
     *       4、扫描HUB生成的订单二维码收货
     * 快递需要：订单类型、订单号、箱号、包装数量、包装单位、类型、货物名称
     * 出库：1、扫描Hub确认二维码，需要派车单号,操作HUB_IN_ORDERMST表HUBI_CTRL 102改为104
     *       2、生成二维码，供别人扫描，别人代出库
     *       3、扫描货物二维码出库，再上传证据，送达确认，判断该订单是否出库完成
     *       4、短信验证成功，调用 送达确认
     */
    private Integer stockModel;//出入库方式
    private Integer transportType;//订单类型：0物流、1运输、2快递
    private String busiBookNo;//当前操作的订单号
    private String scanBusiBookNo;//扫描得到的订单号
    private String boxType;//货物板型
    private String boxNum;//货物板号、箱号
    private Integer goodsQty;//货物包装数量、单板箱数
    private String goodsQtyUnit;//货物包装数量单位
    private Integer orderFrom;//订单来源
    private String goodsType;//类型
    private String goodsName;//货物名称
    private String acctUsername;//登陆账号
    private String scheducarno;//派车单号
    private Integer otherAccountId;//对方账号ID（在扫描MS的确认码时用到）

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

    public Integer getStockModel() {
        return stockModel;
    }

    public void setStockModel(Integer stockModel) {
        this.stockModel = stockModel;
    }

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
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

    public String getBoxType() {
        return boxType;
    }

    public void setBoxType(String boxType) {
        this.boxType = boxType;
    }

    public String getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(String boxNum) {
        this.boxNum = boxNum;
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

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public String getScanBusiBookNo() {
        return scanBusiBookNo;
    }

    public void setScanBusiBookNo(String scanBusiBookNo) {
        this.scanBusiBookNo = scanBusiBookNo;
    }

    public Integer getOtherAccountId() {
        return otherAccountId;
    }

    public void setOtherAccountId(Integer otherAccountId) {
        this.otherAccountId = otherAccountId;
    }
}
