package com.gistandard.transport.app.dubbo.order.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel(description = "子订单信息")
public class MobileSubOrder implements Serializable {
    private static final long serialVersionUID = -831062417796795384L;

    @ApiModelProperty(value = "子订单主键", position = 1)
    private Integer subId;

    @ApiModelProperty(value = "实派车单号", position = 2)
    private String subScheducarno;

    @ApiModelProperty(value = "业务订单号", position = 3)
    private String subBusiBookNo;

    @ApiModelProperty(value = "订单主表编号", position = 4)
    private Integer subBookingFormId;

    @ApiModelProperty(value = "发货方省", position = 5)
    private String subShipCustProvide;

    @ApiModelProperty(value = "发货方市", position = 6)
    private String subShipCustCity;

    @ApiModelProperty(value = "发货方区", position = 7)
    private String subShipCustCounty;

    @ApiModelProperty(value = "发货方地址", position = 8)
    private String subShipCustAddr;

    @ApiModelProperty(value = "发货方联系人", position = 9)
    private String subShipCustLinkMan;

    @ApiModelProperty(value = "发货方联系电话", position = 10)
    private String subShipCustLinkTel;

    @ApiModelProperty(value = "发货方经度", position = 11)
    private BigDecimal subShipLongitude;

    @ApiModelProperty(value = "发货方纬度", position = 12)
    private BigDecimal subShipLatitude;

    @ApiModelProperty(value = "收货方省", position = 13)
    private String subCneeCustProvide;

    @ApiModelProperty(value = "收货方市", position = 14)
    private String subCneeCustCity;

    @ApiModelProperty(value = "收货方区", position = 15)
    private String subCneeCustCounty;

    @ApiModelProperty(value = "收货方地址", position = 16)
    private String subCneeCustAddr;

    @ApiModelProperty(value = "收货方联系人", position = 17)
    private String subCneeCustLinkMan;

    @ApiModelProperty(value = "收货方联系电话", position = 18)
    private String subCneeCustLinkTel;

    @ApiModelProperty(value = "收货方经度", position = 19)
    private BigDecimal subCneeLongitude;

    @ApiModelProperty(value = "收货方纬度", position = 20)
    private BigDecimal subCneeLatitude;

    @ApiModelProperty(value = "业务状态控制", position = 21)
    private Integer subBusiCtrl;

    @ApiModelProperty(value = "支付方式", position = 22)
    private Integer subPayType;

    @ApiModelProperty(value = "收货货款", position = 23)
    private BigDecimal subGoodsPayment;

    @ApiModelProperty(value = "收货货款币制", position = 24)
    private String subGoodsPaymentCurr;

    @ApiModelProperty(value = "到付金额", position = 25)
    private BigDecimal subDestPayment;

    @ApiModelProperty(value = "到付币制", position = 26)
    private String subDestPaymentCurr;

    @ApiModelProperty(value = "是否参保", position = 27)
    private Boolean subNeedInsure;

    @ApiModelProperty(value = "保险比例", position = 28)
    private BigDecimal subPremiumProportion;

    @ApiModelProperty(value = "保险金额", position = 29)
    private BigDecimal subPremiumValue;

    @ApiModelProperty(value = "货物价值", position = 30)
    private BigDecimal subGoodsValue;

    @ApiModelProperty(value = "结算标识", position = 31)
    private Integer subIsJs;

    @ApiModelProperty(value = "对账单号", position = 32)
    private String subValidBillno;

    @ApiModelProperty(value = "报价单号", position = 33)
    private String subComQuoteId;

    @ApiModelProperty(value = "报价类型", position = 34)
    private Integer subQuotedType;

    @ApiModelProperty(value = "运费", position = 35)
    private BigDecimal subPredictValue;

    @ApiModelProperty(value = "运费币制", position = 36)
    private String subPredictCurr;

    @ApiModelProperty(value = "操作要求", position = 37)
    private String subNarrate;

    @ApiModelProperty(value = "MobileBookingForm主键", position = 38)
    private Integer subMobileBookingFormId;

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getSubScheducarno() {
        return subScheducarno;
    }

    public void setSubScheducarno(String subScheducarno) {
        this.subScheducarno = subScheducarno;
    }

    public String getSubBusiBookNo() {
        return subBusiBookNo;
    }

    public void setSubBusiBookNo(String subBusiBookNo) {
        this.subBusiBookNo = subBusiBookNo;
    }

    public Integer getSubBookingFormId() {
        return subBookingFormId;
    }

    public void setSubBookingFormId(Integer subBookingFormId) {
        this.subBookingFormId = subBookingFormId;
    }

    public String getSubShipCustProvide() {
        return subShipCustProvide;
    }

    public void setSubShipCustProvide(String subShipCustProvide) {
        this.subShipCustProvide = subShipCustProvide;
    }

    public String getSubShipCustCity() {
        return subShipCustCity;
    }

    public void setSubShipCustCity(String subShipCustCity) {
        this.subShipCustCity = subShipCustCity;
    }

    public String getSubShipCustCounty() {
        return subShipCustCounty;
    }

    public void setSubShipCustCounty(String subShipCustCounty) {
        this.subShipCustCounty = subShipCustCounty;
    }

    public String getSubShipCustAddr() {
        return subShipCustAddr;
    }

    public void setSubShipCustAddr(String subShipCustAddr) {
        this.subShipCustAddr = subShipCustAddr;
    }

    public String getSubShipCustLinkMan() {
        return subShipCustLinkMan;
    }

    public void setSubShipCustLinkMan(String subShipCustLinkMan) {
        this.subShipCustLinkMan = subShipCustLinkMan;
    }

    public String getSubShipCustLinkTel() {
        return subShipCustLinkTel;
    }

    public void setSubShipCustLinkTel(String subShipCustLinkTel) {
        this.subShipCustLinkTel = subShipCustLinkTel;
    }

    public BigDecimal getSubShipLongitude() {
        return subShipLongitude;
    }

    public void setSubShipLongitude(BigDecimal subShipLongitude) {
        this.subShipLongitude = subShipLongitude;
    }

    public BigDecimal getSubShipLatitude() {
        return subShipLatitude;
    }

    public void setSubShipLatitude(BigDecimal subShipLatitude) {
        this.subShipLatitude = subShipLatitude;
    }

    public String getSubCneeCustProvide() {
        return subCneeCustProvide;
    }

    public void setSubCneeCustProvide(String subCneeCustProvide) {
        this.subCneeCustProvide = subCneeCustProvide;
    }

    public String getSubCneeCustCity() {
        return subCneeCustCity;
    }

    public void setSubCneeCustCity(String subCneeCustCity) {
        this.subCneeCustCity = subCneeCustCity;
    }

    public String getSubCneeCustCounty() {
        return subCneeCustCounty;
    }

    public void setSubCneeCustCounty(String subCneeCustCounty) {
        this.subCneeCustCounty = subCneeCustCounty;
    }

    public String getSubCneeCustAddr() {
        return subCneeCustAddr;
    }

    public void setSubCneeCustAddr(String subCneeCustAddr) {
        this.subCneeCustAddr = subCneeCustAddr;
    }

    public String getSubCneeCustLinkMan() {
        return subCneeCustLinkMan;
    }

    public void setSubCneeCustLinkMan(String subCneeCustLinkMan) {
        this.subCneeCustLinkMan = subCneeCustLinkMan;
    }

    public String getSubCneeCustLinkTel() {
        return subCneeCustLinkTel;
    }

    public void setSubCneeCustLinkTel(String subCneeCustLinkTel) {
        this.subCneeCustLinkTel = subCneeCustLinkTel;
    }

    public BigDecimal getSubCneeLongitude() {
        return subCneeLongitude;
    }

    public void setSubCneeLongitude(BigDecimal subCneeLongitude) {
        this.subCneeLongitude = subCneeLongitude;
    }

    public BigDecimal getSubCneeLatitude() {
        return subCneeLatitude;
    }

    public void setSubCneeLatitude(BigDecimal subCneeLatitude) {
        this.subCneeLatitude = subCneeLatitude;
    }

    public Integer getSubBusiCtrl() {
        return subBusiCtrl;
    }

    public void setSubBusiCtrl(Integer subBusiCtrl) {
        this.subBusiCtrl = subBusiCtrl;
    }

    public Integer getSubPayType() {
        return subPayType;
    }

    public void setSubPayType(Integer subPayType) {
        this.subPayType = subPayType;
    }

    public BigDecimal getSubGoodsPayment() {
        return subGoodsPayment;
    }

    public void setSubGoodsPayment(BigDecimal subGoodsPayment) {
        this.subGoodsPayment = subGoodsPayment;
    }

    public String getSubGoodsPaymentCurr() {
        return subGoodsPaymentCurr;
    }

    public void setSubGoodsPaymentCurr(String subGoodsPaymentCurr) {
        this.subGoodsPaymentCurr = subGoodsPaymentCurr;
    }

    public BigDecimal getSubDestPayment() {
        return subDestPayment;
    }

    public void setSubDestPayment(BigDecimal subDestPayment) {
        this.subDestPayment = subDestPayment;
    }

    public String getSubDestPaymentCurr() {
        return subDestPaymentCurr;
    }

    public void setSubDestPaymentCurr(String subDestPaymentCurr) {
        this.subDestPaymentCurr = subDestPaymentCurr;
    }

    public Boolean isSubNeedInsure() {
        return subNeedInsure;
    }

    public void setSubNeedInsure(Boolean subNeedInsure) {
        this.subNeedInsure = subNeedInsure;
    }

    public BigDecimal getSubPremiumProportion() {
        return subPremiumProportion;
    }

    public void setSubPremiumProportion(BigDecimal subPremiumProportion) {
        this.subPremiumProportion = subPremiumProportion;
    }

    public BigDecimal getSubPremiumValue() {
        return subPremiumValue;
    }

    public void setSubPremiumValue(BigDecimal subPremiumValue) {
        this.subPremiumValue = subPremiumValue;
    }

    public BigDecimal getSubGoodsValue() {
        return subGoodsValue;
    }

    public void setSubGoodsValue(BigDecimal subGoodsValue) {
        this.subGoodsValue = subGoodsValue;
    }

    public Integer getSubIsJs() {
        return subIsJs;
    }

    public void setSubIsJs(Integer subIsJs) {
        this.subIsJs = subIsJs;
    }

    public String getSubValidBillno() {
        return subValidBillno;
    }

    public void setSubValidBillno(String subValidBillno) {
        this.subValidBillno = subValidBillno;
    }

    public String getSubComQuoteId() {
        return subComQuoteId;
    }

    public void setSubComQuoteId(String subComQuoteId) {
        this.subComQuoteId = subComQuoteId;
    }

    public Integer getSubQuotedType() {
        return subQuotedType;
    }

    public void setSubQuotedType(Integer subQuotedType) {
        this.subQuotedType = subQuotedType;
    }

    public BigDecimal getSubPredictValue() {
        return subPredictValue;
    }

    public void setSubPredictValue(BigDecimal subPredictValue) {
        this.subPredictValue = subPredictValue;
    }

    public String getSubPredictCurr() {
        return subPredictCurr;
    }

    public void setSubPredictCurr(String subPredictCurr) {
        this.subPredictCurr = subPredictCurr;
    }

    public String getSubNarrate() {
        return subNarrate;
    }

    public void setSubNarrate(String subNarrate) {
        this.subNarrate = subNarrate;
    }

    public Integer getSubMobileBookingFormId() {
        return subMobileBookingFormId;
    }

    public void setSubMobileBookingFormId(Integer subMobileBookingFormId) {
        this.subMobileBookingFormId = subMobileBookingFormId;
    }
}