package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import com.gistandard.transport.app.dubbo.order.bean.MobileGoodsInfo;
import com.gistandard.transport.app.dubbo.order.bean.MobileOperateInfo;
import com.gistandard.transport.app.dubbo.order.bean.MobileSubOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileStationOrderDetailBean
 * @Date: 2016/1/26
 * @Description: 查询订单详细返回Bean
 */
@ApiModel(description = "查询订单详细返回Bean")
public class MobileOrderDetailBean implements Serializable {
    private static final long serialVersionUID = 2923303959213343294L;

    @ApiModelProperty(value = "订单主键ID", position = 1)
    private Integer orderId;
    @ApiModelProperty(value = "订舱订单表主键", position = 1)
    private Integer bookingFormId;
    @ApiModelProperty(value = "业务订单号", position = 1)
    private String busiBookNo;
    @ApiModelProperty(value = "订单类型 1：取件单 2：派件单", position = 1)
    private Integer orderType;
    @ApiModelProperty(value = "订单描述信息 类型/品名+重量+体积", position = 1)
    private String description;
    @ApiModelProperty(value = "报价信息", position = 1)
    private String quoteDesc;
    @ApiModelProperty(value = "预约时间", position = 1)
    private Date etaPopDate;
    @ApiModelProperty(value = "运费", position = 1)
    private BigDecimal predictValue;
    @ApiModelProperty(value = "运费币制", position = 1)
    private String predictCurr;
    @ApiModelProperty(value = "1到付2平台支付3现金支付", position = 1)
    private Integer payType;
    @ApiModelProperty(value = "收货货款", position = 1)
    private BigDecimal startPayment;
    @ApiModelProperty(value = "收货币制", position = 1)
    private String startCurrency;
    @ApiModelProperty(value = "到付金额", position = 1)
    private BigDecimal destPayment;
    @ApiModelProperty(value = "到付币制", position = 1)
    private String destCurrency;
    @ApiModelProperty(value = "发货省", position = 1)
    private String startProvide;
    @ApiModelProperty(value = "发货市", position = 1)
    private String startCity;
    @ApiModelProperty(value = "发货区", position = 1)
    private String startCounty;
    @ApiModelProperty(value = "发货省市区", position = 1)
    private String startArea;
    @ApiModelProperty(value = "发货地址", position = 1)
    private String startAddress;
    @ApiModelProperty(value = "发货人", position = 1)
    private String startLinkMan;
    @ApiModelProperty(value = "发货人联系电话", position = 1)
    private String startTel;
    @ApiModelProperty(value = "发货经度", position = 1)
    private BigDecimal startLongitude;
    @ApiModelProperty(value = "发货纬度", position = 1)
    private BigDecimal startLatitude;

    @ApiModelProperty(value = "收货省", position = 1)
    private String destProvide;
    @ApiModelProperty(value = "收货市", position = 1)
    private String destCity;
    @ApiModelProperty(value = "收货区", position = 1)
    private String destCounty;
    @ApiModelProperty(value = "收货人", position = 1)
    private String destLinkMan;
    @ApiModelProperty(value = "收货人联系电话", position = 1)
    private String destTel;
    @ApiModelProperty(value = "收货省市区", position = 1)
    private String destArea;
    @ApiModelProperty(value = "收货地址", position = 1)
    private String destAddress;
    @ApiModelProperty(value = "收货经度", position = 1)
    private BigDecimal destLongitude;
    @ApiModelProperty(value = "收货纬度", position = 1)
    private BigDecimal destLatitude;

    @ApiModelProperty(value = "业务订单日期", position = 1)
    private Date bookingDate;
    @ApiModelProperty(value = "接单HUB代码", position = 1)
    private String teamComsixNo;
    @ApiModelProperty(value = "业务状态控制", position = 1)
    private Integer busiCtrl;
    @ApiModelProperty(value = "订单操作类型 0、物流，1、运输，2、快递", position = 1)
    private Integer transportType;
    @ApiModelProperty(value = "是否投保", position = 1)
    private Boolean needInsure;
    @ApiModelProperty(value = "保险费率", position = 1)
    private BigDecimal premiumProportion;
    @ApiModelProperty(value = "保险费用", position = 1)
    private BigDecimal premiumValue;
    @ApiModelProperty(value = "货值(USD)", position = 1)
    private BigDecimal goodsValue;
    @ApiModelProperty(value = "接单人编号", position = 1)
    private Integer revUserId;
    @ApiModelProperty(value = "接单人", position = 1)
    private String revUser;
    @ApiModelProperty(value = "接单日期", position = 1)
    private Date revDate;
    @ApiModelProperty(value = "完成日期", position = 1)
    private Date finishDate;
    @ApiModelProperty(value = "建单人账号Id", position = 1)
    private Integer createUserId;
    @ApiModelProperty(value = "建单人账号", position = 1)
    private String createUser;
    @ApiModelProperty(value = "创建日期", position = 1)
    private Date createDate;
    @ApiModelProperty(value = "是否结算", position = 1)
    private Integer isJs;
    @ApiModelProperty(value = "结算对账单号", position = 1)
    private String validBillno;
    @ApiModelProperty(value = "报价单号", position = 1)
    private String comQuoteId;
    @ApiModelProperty(value = "报价类型", position = 1)
    private Integer quotedType;
    @ApiModelProperty(value = "签派单号", position = 1)
    private Integer dispatchId;
    @ApiModelProperty(value = "实派车单号", position = 1)
    private String scheducarno;
    @ApiModelProperty(value = "订单来源", position = 1)
    private Integer orderFrom;
    @ApiModelProperty(value = "距离，单位：米", position = 1)
    private BigDecimal distance;
    @ApiModelProperty(value = "失败原因描述", position = 1)
    private String failureDesc;
    @ApiModelProperty(value = "派车单起始站点", position = 1)
    private String startLocus;
    @ApiModelProperty(value = "派车单目的站点", position = 1)
    private String destnLocus;
    @ApiModelProperty(value = "派车单目的站点名称", position = 1)
    private String destnLocusName;
    @ApiModelProperty(value = "操作要求", position = 1)
    private String narrate;
    @ApiModelProperty(value = "产品类型", position = 1)
    private String productType;
    @ApiModelProperty(value = "货物信息", position = 1)
    private List<MobileGoodsInfo> goodsInfoList;
    @ApiModelProperty(value = "子订单信息", position = 1)
    private List<MobileSubOrder> subOrderList;
    @ApiModelProperty(value = "指派时间", position = 1)
    private Date assignDate;
    @ApiModelProperty(value = "指派运费", position = 1)
    private BigDecimal assignPredictValue;
    @ApiModelProperty(value = "指派运费币制", position = 1)
    private String assignPredictCurr;
    @ApiModelProperty(value = "接单人编号", position = 1)
    private Integer assignRevUserId;
    @ApiModelProperty(value = "接单人", position = 1)
    private String assignRevUser;
    @ApiModelProperty(value = "预约取件时间", position = 1)
    private String accesstime;
    @ApiModelProperty(value = "下单人手机号码", position = 1)
    private String createUserTel;
    @ApiModelProperty(value = "派车单总重量，只有派车单有数据", position = 1)
    private BigDecimal totalWht;
    @ApiModelProperty(value = "派车单总体积，只有派车单有数据", position = 1)
    private BigDecimal totalVol;
    @ApiModelProperty(value = "角色ID", position = 1)
    private Integer roleId;
    @ApiModelProperty(value = "用户和平台之间的平台报价单号", position = 1)
    private String platQuoteNo;
    @ApiModelProperty(value = "下单人账号", position = 1)
    private String bookingUser;
    @ApiModelProperty(value = "派车单下面的散件订单号和集包号，无重复", position = 1)
    private List<String> subOrderNoList;
    @ApiModelProperty(value = "O单收货人姓名，用来打印、补打订单二维码使用", position = 1)
    private String destCustLinkMan;
    @ApiModelProperty(value = "O单收货人电话，用来打印、补打订单二维码使用", position = 1)
    private String destCustLinkTel;
    @ApiModelProperty(value = "下单人单位", position = 1)
    private Integer createCompanyId;
    @ApiModelProperty(value = "接单人单位", position = 1)
    private Integer revCompanyId;
    @ApiModelProperty(value = "发货人账号", position = 1)
    private String startUser;
    @ApiModelProperty(value = "收货人账号", position = 1)
    private String destUser;
    @ApiModelProperty(value = "支付人O2ID")
    private String payUser;
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderPrice;
    @ApiModelProperty(value = "订单金额币制")
    private String orderPriceCurr;
    @ApiModelProperty(value = "支付人姓名")
    private String payUserRealName;
    @ApiModelProperty(value = "支付人手机号")
    private String payUserTelephone;
    @ApiModelProperty(value = "O单的支付方式")
    private Integer orderPayType;
    @ApiModelProperty(value = "订单路由，用来打印、补打订单二维码使用")
    private String orderRouteInfo;
    @ApiModelProperty(value = "O单I单结算状态 true都已经结算成功 不允许编辑货物，不允许重复结算，false没有全部结算成功 可以编辑货物 可以重复结算")
    private Boolean jsFlag;
    @ApiModelProperty(value = "O单是否支付 1是0否")
    private Boolean payFlag;
    @ApiModelProperty(value = "O单结算对账单号")
    private String validBillnoForOut;
    @ApiModelProperty(value = "O单POD省 只有快递员POP-M时的订单详情才有值")
    private String destProvideForOut;
    @ApiModelProperty(value = "O单POD市 只有快递员POP-M时的订单详情才有值")
    private String destCityForOut;
    @ApiModelProperty(value = "O单POD区 只有快递员POP-M时的订单详情才有值")
    private String destCountyForOut;
    @ApiModelProperty(value = "O单POD省市区 只有快递员POP-M时的订单详情才有值")
    private String destAreaForOut;
    @ApiModelProperty(value = "O单POD地址 只有快递员POP-M时的订单详情才有值")
    private String destAddressForOut;
    @ApiModelProperty(value = "O单POD经度 只有快递员POP-M时的订单详情才有值")
    private BigDecimal destLongitudeForOut;
    @ApiModelProperty(value = "O单POD维度 只有快递员POP-M时的订单详情才有值")
    private BigDecimal destLatitudeForOut;
    @ApiModelProperty(value = "O单POD收货人 只有快递员POP-M时的订单详情才有值", position = 1)
    private String destLinkManForOut;
    @ApiModelProperty(value = "O单POD收货人联系电话 只有快递员POP-M时的订单详情才有值", position = 1)
    private String destTelForOut;
    @ApiModelProperty(value = "O单POD收货人 只有快递员POP-M时的订单详情才有值", position = 1)
    private String destUserForOut;
    @ApiModelProperty(value = "派送失败次数", position = 1)
    private Integer failureTimes;
    @ApiModelProperty(value = "失败描述信息最后一条", position = 1)
    private MobileOperateInfo mobileOperateInfo;


    @ApiModelProperty(value = "同城运输 车辆信息", position = 35)
    private CarInfo carInfo;
    @ApiModelProperty(value = "同城运输 车队名称", position = 35)
    private String fleetName;
    @ApiModelProperty(value = "同城运输 报价时间", position = 35)
    private Date quoteTime;
    @ApiModelProperty(value = "同城运输 提货时间", position = 35)
    private Date pickTime;
    @ApiModelProperty(value = "同城运输 送货时间", position = 35)
    private Date deliveryTime;
    @ApiModelProperty(value = "同城运输 司机姓名", position = 35)
    private String driverName;
    @ApiModelProperty(value = "同城运输 司机电话", position = 35)
    private String driverTel;
    @ApiModelProperty(value = "O单下单人是否实名制 1是0否", position = 59)
    private Boolean realNameFlag;
    @ApiModelProperty(value = "商管中心指派人编号", position = 64)
    private Integer assignUserId;
    @ApiModelProperty(value = "咪站自报价")
    private BigDecimal selfQuoteValue;
    @ApiModelProperty(value = "咪站自报价币制")
    private String selfQuoteCurr;
    @ApiModelProperty(value = "车队报价税后价")
    private BigDecimal bidValue;
    @ApiModelProperty(value = "车队报价税后价币制")
    private String bidValueCurr;
    @ApiModelProperty(value = "车队报价税率")
    private BigDecimal taxRate;
    @ApiModelProperty(value = "订单类型分类 1：用户订单 2：商户订单")
    private int orderStyle;
    @ApiModelProperty(value = "是否咪站指派单 1 是0否")
    private Boolean miAssignFlag;
    @ApiModelProperty(value = "发起竞价咪站还是蛙站")
    private String bidBy;

    public String getBidBy() {
        return bidBy;
    }

    public void setBidBy(String bidBy) {
        this.bidBy = bidBy;
    }

    public Boolean isMiAssignFlag() {
        return miAssignFlag;
    }

    public void setMiAssignFlag(Boolean miAssignFlag) {
        this.miAssignFlag = miAssignFlag;
    }

    public int getOrderStyle() {
        return orderStyle;
    }

    public void setOrderStyle(int orderStyle) {
        this.orderStyle = orderStyle;
    }

    public Integer getRevCompanyId() {
        return revCompanyId;
    }

    public void setRevCompanyId(Integer revCompanyId) {
        this.revCompanyId = revCompanyId;
    }

    public String getDestnLocusName() {
        return destnLocusName;
    }

    public void setDestnLocusName(String destnLocusName) {
        this.destnLocusName = destnLocusName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getBidValue() {
        return bidValue;
    }

    public void setBidValue(BigDecimal bidValue) {
        this.bidValue = bidValue;
    }

    public String getBidValueCurr() {
        return bidValueCurr;
    }

    public void setBidValueCurr(String bidValueCurr) {
        this.bidValueCurr = bidValueCurr;
    }

    public BigDecimal getSelfQuoteValue() {
        return selfQuoteValue;
    }

    public void setSelfQuoteValue(BigDecimal selfQuoteValue) {
        this.selfQuoteValue = selfQuoteValue;
    }

    public String getSelfQuoteCurr() {
        return selfQuoteCurr;
    }

    public void setSelfQuoteCurr(String selfQuoteCurr) {
        this.selfQuoteCurr = selfQuoteCurr;
    }

    public Boolean isRealNameFlag() {
        return realNameFlag;
    }

    public void setRealNameFlag(Boolean realNameFlag) {
        this.realNameFlag = realNameFlag;
    }

    public Integer getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(Integer assignUserId) {
        this.assignUserId = assignUserId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverTel() {
        return driverTel;
    }

    public void setDriverTel(String driverTel) {
        this.driverTel = driverTel;
    }

    public Integer getFailureTimes() {
        return failureTimes;
    }

    public void setFailureTimes(Integer failureTimes) {
        this.failureTimes = failureTimes;
    }

    public String getDestUserForOut() {
        return destUserForOut;
    }

    public void setDestUserForOut(String destUserForOut) {
        this.destUserForOut = destUserForOut;
    }

    public MobileOperateInfo getMobileOperateInfo() {
        return mobileOperateInfo;
    }

    public void setMobileOperateInfo(MobileOperateInfo mobileOperateInfo) {
        this.mobileOperateInfo = mobileOperateInfo;
    }

    public String getOrderPriceCurr() {
        return orderPriceCurr;
    }

    public void setOrderPriceCurr(String orderPriceCurr) {
        this.orderPriceCurr = orderPriceCurr;
    }

    public Integer getOrderPayType() {
        return orderPayType;
    }

    public void setOrderPayType(Integer orderPayType) {
        this.orderPayType = orderPayType;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getPayUserRealName() {
        return payUserRealName;
    }

    public void setPayUserRealName(String payUserRealName) {
        this.payUserRealName = payUserRealName;
    }

    public String getPayUserTelephone() {
        return payUserTelephone;
    }

    public void setPayUserTelephone(String payUserTelephone) {
        this.payUserTelephone = payUserTelephone;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getBookingFormId() {
        return bookingFormId;
    }

    public void setBookingFormId(Integer bookingFormId) {
        this.bookingFormId = bookingFormId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuoteDesc() {
        return quoteDesc;
    }

    public void setQuoteDesc(String quoteDesc) {
        this.quoteDesc = quoteDesc;
    }

    public Date getEtaPopDate() {
        return etaPopDate;
    }

    public void setEtaPopDate(Date etaPopDate) {
        this.etaPopDate = etaPopDate;
    }

    public BigDecimal getPredictValue() {
        return predictValue;
    }

    public void setPredictValue(BigDecimal predictValue) {
        this.predictValue = predictValue;
    }

    public String getPredictCurr() {
        return predictCurr;
    }

    public void setPredictCurr(String predictCurr) {
        this.predictCurr = predictCurr;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getStartPayment() {
        return startPayment;
    }

    public void setStartPayment(BigDecimal startPayment) {
        this.startPayment = startPayment;
    }

    public String getStartCurrency() {
        return startCurrency;
    }

    public void setStartCurrency(String startCurrency) {
        this.startCurrency = startCurrency;
    }

    public BigDecimal getDestPayment() {
        return destPayment;
    }

    public void setDestPayment(BigDecimal destPayment) {
        this.destPayment = destPayment;
    }

    public String getDestCurrency() {
        return destCurrency;
    }

    public void setDestCurrency(String destCurrency) {
        this.destCurrency = destCurrency;
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

    public String getStartLinkMan() {
        return startLinkMan;
    }

    public void setStartLinkMan(String startLinkMan) {
        this.startLinkMan = startLinkMan;
    }

    public String getStartTel() {
        return startTel;
    }

    public void setStartTel(String startTel) {
        this.startTel = startTel;
    }

    public BigDecimal getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(BigDecimal startLongitude) {
        this.startLongitude = startLongitude;
    }

    public BigDecimal getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(BigDecimal startLatitude) {
        this.startLatitude = startLatitude;
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

    public String getDestLinkMan() {
        return destLinkMan;
    }

    public void setDestLinkMan(String destLinkMan) {
        this.destLinkMan = destLinkMan;
    }

    public String getDestTel() {
        return destTel;
    }

    public void setDestTel(String destTel) {
        this.destTel = destTel;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public BigDecimal getDestLongitude() {
        return destLongitude;
    }

    public void setDestLongitude(BigDecimal destLongitude) {
        this.destLongitude = destLongitude;
    }

    public BigDecimal getDestLatitude() {
        return destLatitude;
    }

    public void setDestLatitude(BigDecimal destLatitude) {
        this.destLatitude = destLatitude;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTeamComsixNo() {
        return teamComsixNo;
    }

    public void setTeamComsixNo(String teamComsixNo) {
        this.teamComsixNo = teamComsixNo;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    public Boolean isNeedInsure() {
        return needInsure;
    }

    public void setNeedInsure(Boolean needInsure) {
        this.needInsure = needInsure;
    }

    public BigDecimal getPremiumProportion() {
        return premiumProportion;
    }

    public void setPremiumProportion(BigDecimal premiumProportion) {
        this.premiumProportion = premiumProportion;
    }

    public BigDecimal getPremiumValue() {
        return premiumValue;
    }

    public void setPremiumValue(BigDecimal premiumValue) {
        this.premiumValue = premiumValue;
    }

    public BigDecimal getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(BigDecimal goodsValue) {
        this.goodsValue = goodsValue;
    }

    public Integer getRevUserId() {
        return revUserId;
    }

    public void setRevUserId(Integer revUserId) {
        this.revUserId = revUserId;
    }

    public String getRevUser() {
        return revUser;
    }

    public void setRevUser(String revUser) {
        this.revUser = revUser;
    }

    public Date getRevDate() {
        return revDate;
    }

    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsJs() {
        return isJs;
    }

    public void setIsJs(Integer isJs) {
        this.isJs = isJs;
    }

    public String getValidBillno() {
        return validBillno;
    }

    public void setValidBillno(String validBillno) {
        this.validBillno = validBillno;
    }

    public String getComQuoteId() {
        return comQuoteId;
    }

    public void setComQuoteId(String comQuoteId) {
        this.comQuoteId = comQuoteId;
    }

    public Integer getQuotedType() {
        return quotedType;
    }

    public void setQuotedType(Integer quotedType) {
        this.quotedType = quotedType;
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

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public String getFailureDesc() {
        return failureDesc;
    }

    public void setFailureDesc(String failureDesc) {
        this.failureDesc = failureDesc;
    }

    public String getStartLocus() {
        return startLocus;
    }

    public void setStartLocus(String startLocus) {
        this.startLocus = startLocus;
    }

    public String getDestnLocus() {
        return destnLocus;
    }

    public void setDestnLocus(String destnLocus) {
        this.destnLocus = destnLocus;
    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<MobileGoodsInfo> getGoodsInfoList() {
        return goodsInfoList;
    }

    public void setGoodsInfoList(List<MobileGoodsInfo> goodsInfoList) {
        this.goodsInfoList = goodsInfoList;
    }

    public List<MobileSubOrder> getSubOrderList() {
        return subOrderList;
    }

    public void setSubOrderList(List<MobileSubOrder> subOrderList) {
        this.subOrderList = subOrderList;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public BigDecimal getAssignPredictValue() {
        return assignPredictValue;
    }

    public void setAssignPredictValue(BigDecimal assignPredictValue) {
        this.assignPredictValue = assignPredictValue;
    }

    public String getAssignPredictCurr() {
        return assignPredictCurr;
    }

    public void setAssignPredictCurr(String assignPredictCurr) {
        this.assignPredictCurr = assignPredictCurr;
    }

    public Integer getAssignRevUserId() {
        return assignRevUserId;
    }

    public void setAssignRevUserId(Integer assignRevUserId) {
        this.assignRevUserId = assignRevUserId;
    }

    public String getAssignRevUser() {
        return assignRevUser;
    }

    public void setAssignRevUser(String assignRevUser) {
        this.assignRevUser = assignRevUser;
    }

    public String getAccesstime() {
        return accesstime;
    }

    public void setAccesstime(String accesstime) {
        this.accesstime = accesstime;
    }

    public String getCreateUserTel() {
        return createUserTel;
    }

    public void setCreateUserTel(String createUserTel) {
        this.createUserTel = createUserTel;
    }

    public BigDecimal getTotalWht() {
        return totalWht;
    }

    public void setTotalWht(BigDecimal totalWht) {
        this.totalWht = totalWht;
    }

    public BigDecimal getTotalVol() {
        return totalVol;
    }

    public void setTotalVol(BigDecimal totalVol) {
        this.totalVol = totalVol;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPlatQuoteNo() {
        return platQuoteNo;
    }

    public void setPlatQuoteNo(String platQuoteNo) {
        this.platQuoteNo = platQuoteNo;
    }

    public String getBookingUser() {
        return bookingUser;
    }

    public void setBookingUser(String bookingUser) {
        this.bookingUser = bookingUser;
    }

    public List<String> getSubOrderNoList() {
        return subOrderNoList;
    }

    public void setSubOrderNoList(List<String> subOrderNoList) {
        this.subOrderNoList = subOrderNoList;
    }

    public String getDestCustLinkMan() {
        return destCustLinkMan;
    }

    public void setDestCustLinkMan(String destCustLinkMan) {
        this.destCustLinkMan = destCustLinkMan;
    }

    public String getDestCustLinkTel() {
        return destCustLinkTel;
    }

    public void setDestCustLinkTel(String destCustLinkTel) {
        this.destCustLinkTel = destCustLinkTel;
    }

    public Integer getCreateCompanyId() {
        return createCompanyId;
    }

    public void setCreateCompanyId(Integer createCompanyId) {
        this.createCompanyId = createCompanyId;
    }

    public String getStartUser() {
        return startUser;
    }

    public void setStartUser(String startUser) {
        this.startUser = startUser;
    }

    public String getDestUser() {
        return destUser;
    }

    public void setDestUser(String destUser) {
        this.destUser = destUser;
    }

    public String getStartArea() {
        return startArea;
    }

    public void setStartArea(String startArea) {
        this.startArea = startArea;
    }

    public String getDestArea() {
        return destArea;
    }

    public void setDestArea(String destArea) {
        this.destArea = destArea;
    }

    public String getOrderRouteInfo() {
        return orderRouteInfo;
    }

    public void setOrderRouteInfo(String orderRouteInfo) {
        this.orderRouteInfo = orderRouteInfo;
    }

    public Boolean isPayFlag() {
        return payFlag;
    }

    public void setPayFlag(Boolean payFlag) {
        this.payFlag = payFlag;
    }

    public String getValidBillnoForOut() {
        return validBillnoForOut;
    }

    public void setValidBillnoForOut(String validBillnoForOut) {
        this.validBillnoForOut = validBillnoForOut;
    }

    public String getDestProvideForOut() {
        return destProvideForOut;
    }

    public void setDestProvideForOut(String destProvideForOut) {
        this.destProvideForOut = destProvideForOut;
    }

    public String getDestCityForOut() {
        return destCityForOut;
    }

    public void setDestCityForOut(String destCityForOut) {
        this.destCityForOut = destCityForOut;
    }

    public String getDestCountyForOut() {
        return destCountyForOut;
    }

    public void setDestCountyForOut(String destCountyForOut) {
        this.destCountyForOut = destCountyForOut;
    }

    public String getDestAddressForOut() {
        return destAddressForOut;
    }

    public void setDestAddressForOut(String destAddressForOut) {
        this.destAddressForOut = destAddressForOut;
    }

    public BigDecimal getDestLongitudeForOut() {
        return destLongitudeForOut;
    }

    public void setDestLongitudeForOut(BigDecimal destLongitudeForOut) {
        this.destLongitudeForOut = destLongitudeForOut;
    }

    public BigDecimal getDestLatitudeForOut() {
        return destLatitudeForOut;
    }

    public void setDestLatitudeForOut(BigDecimal destLatitudeForOut) {
        this.destLatitudeForOut = destLatitudeForOut;
    }

    public String getDestLinkManForOut() {
        return destLinkManForOut;
    }

    public void setDestLinkManForOut(String destLinkManForOut) {
        this.destLinkManForOut = destLinkManForOut;
    }

    public String getDestTelForOut() {
        return destTelForOut;
    }

    public void setDestTelForOut(String destTelForOut) {
        this.destTelForOut = destTelForOut;
    }

    public Boolean isJsFlag() {
        return jsFlag;
    }

    public void setJsFlag(Boolean jsFlag) {
        this.jsFlag = jsFlag;
    }

    public String getDestAreaForOut() {
        return destAreaForOut;
    }

    public void setDestAreaForOut(String destAreaForOut) {
        this.destAreaForOut = destAreaForOut;
    }

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public Date getQuoteTime() {
        return quoteTime;
    }

    public void setQuoteTime(Date quoteTime) {
        this.quoteTime = quoteTime;
    }

    public Date getPickTime() {
        return pickTime;
    }

    public void setPickTime(Date pickTime) {
        this.pickTime = pickTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
