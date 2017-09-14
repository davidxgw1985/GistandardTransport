package com.gistandard.transport.order.module.mobilestation.bean.ordermanage;

import com.gistandard.transport.app.dubbo.order.bean.MobileGoodsInfo;
import com.gistandard.transport.app.dubbo.order.bean.MobileSubOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileMyOrderListBean
 * @Date: 2016/12/12
 * @Description: 订单管理-订单列表查询返回Bean
 */
@ApiModel(description = "订单管理-订单列表查询返回Bean")
public class MobileOrderListBean implements Serializable{

    private static final long serialVersionUID = 6177882727545388485L;
    @ApiModelProperty(value = "MobileBookingForm主键Id", position = 1)
    private int orderId;
    @ApiModelProperty(value = "业务订单号", position = 2)
    private String busiBookNo;
    @ApiModelProperty(value = "订单类型 1：取件单 2：派件单", position = 3)
    private int orderType;
    @ApiModelProperty(value = "运费", position = 4)
    private BigDecimal predictValue;
    @ApiModelProperty(value = "运费币制", position = 5)
    private String predictCurr;
    @ApiModelProperty(value = "//订单描述信息 类型/品名+重量+体积", position = 6)
    private String description;
    @ApiModelProperty(value = "预约时间", position = 7)
    private Date etaPopDate;
    @ApiModelProperty(value = "操作要求", position = 8)
    private String narrate;

    @ApiModelProperty(value = "发货省", position = 9)
    private String startProvide;
    @ApiModelProperty(value = "发货市", position = 10)
    private String startCity;
    @ApiModelProperty(value = "发货区", position = 11)
    private String startCounty;
    @ApiModelProperty(value = "发货地址", position = 12)
    private String startAddress;
    @ApiModelProperty(value = "发货经度", position = 13)
    private BigDecimal startLongitude;
    @ApiModelProperty(value = "发货纬度", position = 14)
    private BigDecimal startLatitude;
    @ApiModelProperty(value = "发货联系电话", position = 15)
    private String startTel;
    @ApiModelProperty(value = "收货联系人", position = 22)
    private String startLinkMan;

    @ApiModelProperty(value = "收货省", position = 16)
    private String destProvide;
    @ApiModelProperty(value = "收货市", position = 17)
    private String destCity;
    @ApiModelProperty(value = "收货区", position = 18)
    private String destCounty;
    @ApiModelProperty(value = "收货地址", position = 19)
    private String destAddress;
    @ApiModelProperty(value = "收货经度", position = 20)
    private BigDecimal destLongitude;
    @ApiModelProperty(value = "收货纬度", position = 21)
    private BigDecimal destLatitude;//收货纬度
    @ApiModelProperty(value = "收货联系电话", position = 22)
    private String destTel;
    @ApiModelProperty(value = "收货联系人", position = 22)
    private String destLinkMan;

    @ApiModelProperty(value = "发货站点", position = 23)
    private String startLocus;
    @ApiModelProperty(value = "收货站点", position = 24)
    private String destnLocus;
    @ApiModelProperty(value = "派车单号", position = 25)
    private String scheducarno;
    @ApiModelProperty(value = "产品类型", position = 26)
    private String productType;
    @ApiModelProperty(value = "订单来源 1签派广播单，2运输指派单，3签派指派单，4个人指派单，5运输广播单，6个人广播单", position = 27)
    private Integer orderFrom;
    @ApiModelProperty(value = "入库状态 false未入库，取发货地址，true已入库，取收货地址", position = 28)
    private Boolean stockFlag;
    @ApiModelProperty(value = "订单当前状态", position = 29)
    private Integer busiCtrl;

    @ApiModelProperty(value = "报价单号", position = 30)
    private String comQuoteId;
    @ApiModelProperty(value = "报价类型", position = 31)
    private Integer quotedType;
    @ApiModelProperty(value = "对账单号", position = 32)
    private String validBillno;
    @ApiModelProperty(value = "订单操作类型 0、物流，1、运输，2、快递", position = 33)
    private Integer transportType;
    @ApiModelProperty(value = "货物信息", position = 34)
    private List<MobileGoodsInfo> goodsInfoList;
    @ApiModelProperty(value = "子订单信息", position = 35)
    private List<MobileSubOrder> subOrderList;
    @ApiModelProperty(value = "总共5位，1标识发车，1<<1标识指派咪站、蛙站，1<<2标识咪站派车，1<<3标识咪站中转，1<<4标识咪站排货派车", position = 36)
    private Integer operateFlag = 0;
    @ApiModelProperty(value = "咪站指派的MS姓名", position = 37)
    private String msLinkName;
    @ApiModelProperty(value = "咪站指派的MS联系电话", position = 38)
    private String msLinkTel;
    @ApiModelProperty(value = "咪站指派的MS车牌号", position = 39)
    private String msLinkCarNo;
    @ApiModelProperty(value = "咪站 M-POD 展示目的地市、区；M-W 展示W站点名称", position = 40)
    private String destAddressName;
    @ApiModelProperty(value = "发货人地址", position = 41)
    private String shipAddr;
    @ApiModelProperty(value = "路由Id", position = 42)
    private Integer routeSchemaId;
    @ApiModelProperty(value = "是否四通一达订单", position = 43)
    private Boolean stydFlag;
    @ApiModelProperty(value = "1送货上门，2上门接货", position = 44)
    private String carriAgerecei;
    @ApiModelProperty(value = "快递公司", position = 45)
    private String expressName;
    @ApiModelProperty(value = "快递公司单号", position = 46)
    private String expressOrderNo;
    @ApiModelProperty(value = "建单人账号Id", position = 50)
    private Integer createUserId;
    @ApiModelProperty(value = "建单人账号", position = 51)
    private String createUser;
    @ApiModelProperty(value = "目的地站点名称", position = 52)
    private String destHubName;
    @ApiModelProperty(value = "订单类型分类 1：用户订单 2：商户订单", position = 53)
    private int orderStyle;
    @ApiModelProperty(value = "用户和平台之间的平台报价单号", position = 56)
    private String platQuoteNo;
    @ApiModelProperty(value = "下单人账号", position = 57)
    private String bookingUser;
    @ApiModelProperty(value = "O单付款人账号", position = 58)
    private String payUser;
    @ApiModelProperty(value = "O单下单人是否实名制 1是0否", position = 59)
    private Boolean realNameFlag;
    @ApiModelProperty(value = "O单是否支付 1是0否", position = 60)
    private Boolean payFlag;
    @ApiModelProperty(value = "支付方式 1现金支付 2平台支付 3到付", position = 61)
    private Integer payType;
    @ApiModelProperty(value = "付款人姓名", position = 62)
    private String payPerson;
    @ApiModelProperty(value = "付款人电话", position = 63)
    private String payPersonTelephone;
    @ApiModelProperty(value = "商管中心指派人编号", position = 64)
    private Integer assignUserId;
    @ApiModelProperty(value = "路由信息", position = 65)
    private String orderRouteInfo;
    @ApiModelProperty(value = "派送失败次数 大于0为失败单 为空 为0 是正常单", position = 66)
    private Integer failureTimes;
    @ApiModelProperty(value = "自报价", position = 67)
    private BigDecimal selfQuoteValue;
    @ApiModelProperty(value = "自报价币制", position = 68)
    private String selfQuoteCurr;
    @ApiModelProperty(value = "车队报价时间", position = 68)
    private Date quoteTime;
    @ApiModelProperty(value = "车队报价", position = 67)
    private BigDecimal bidValue;
    @ApiModelProperty(value = "车队报价币制", position = 68)
    private String bidValueCurr;
    @ApiModelProperty(value = "车队报价税率", position = 69)
    private BigDecimal taxRate;
    @ApiModelProperty(value = "1虚拟单0真实单 虚拟单标识", position = 69)
    private Integer fictitiousFlag;
    @ApiModelProperty(value = "嗨付单标识 1嗨付单0 普通单", position = 69)
    private Integer couponFlag;
    @ApiModelProperty(value = "车队名称", position = 68)
    private String fleetName;
    @ApiModelProperty(value = "是否咪站运输指派单 1是0否", position = 68)
    private Boolean miAssignFlag;
    @ApiModelProperty(value = "咪站发起广播竞价时间", position = 68)
    private Date miBidTime;
    @ApiModelProperty(value = "O单的公里数POP-POD", position = 68)
    private BigDecimal bfMileage;

    public BigDecimal getBfMileage() {
        return bfMileage;
    }

    public void setBfMileage(BigDecimal bfMileage) {
        this.bfMileage = bfMileage;
    }

    public Date getMiBidTime() {
        return miBidTime;
    }

    public void setMiBidTime(Date miBidTime) {
        this.miBidTime = miBidTime;
    }

    public Boolean isMiAssignFlag() {
        return miAssignFlag;
    }

    public void setMiAssignFlag(Boolean miAssignFlag) {
        this.miAssignFlag = miAssignFlag;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public Integer getFictitiousFlag() {
        return fictitiousFlag;
    }

    public void setFictitiousFlag(Integer fictitiousFlag) {
        this.fictitiousFlag = fictitiousFlag;
    }

    public Integer getCouponFlag() {
        return couponFlag;
    }

    public void setCouponFlag(Integer couponFlag) {
        this.couponFlag = couponFlag;
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

    public Date getQuoteTime() {
        return quoteTime;
    }

    public void setQuoteTime(Date quoteTime) {
        this.quoteTime = quoteTime;
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

    public String getStartLinkMan() {
        return startLinkMan;
    }

    public void setStartLinkMan(String startLinkMan) {
        this.startLinkMan = startLinkMan;
    }

    public String getDestLinkMan() {
        return destLinkMan;
    }

    public void setDestLinkMan(String destLinkMan) {
        this.destLinkMan = destLinkMan;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEtaPopDate() {
        return etaPopDate;
    }

    public void setEtaPopDate(Date etaPopDate) {
        this.etaPopDate = etaPopDate;
    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
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

    public String getStartTel() {
        return startTel;
    }

    public void setStartTel(String startTel) {
        this.startTel = startTel;
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

    public String getDestTel() {
        return destTel;
    }

    public void setDestTel(String destTel) {
        this.destTel = destTel;
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

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Boolean isStockFlag() {
        return stockFlag;
    }

    public void setStockFlag(Boolean stockFlag) {
        this.stockFlag = stockFlag;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
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

    public String getValidBillno() {
        return validBillno;
    }

    public void setValidBillno(String validBillno) {
        this.validBillno = validBillno;
    }

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
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

    public Integer getOperateFlag() {
        return operateFlag;
    }

    public void setOperateFlag(Integer operateFlag) {
        this.operateFlag = operateFlag;
    }

    public String getMsLinkName() {
        return msLinkName;
    }

    public void setMsLinkName(String msLinkName) {
        this.msLinkName = msLinkName;
    }

    public String getMsLinkTel() {
        return msLinkTel;
    }

    public void setMsLinkTel(String msLinkTel) {
        this.msLinkTel = msLinkTel;
    }

    public String getMsLinkCarNo() {
        return msLinkCarNo;
    }

    public void setMsLinkCarNo(String msLinkCarNo) {
        this.msLinkCarNo = msLinkCarNo;
    }

    public String getDestAddressName() {
        return destAddressName;
    }

    public void setDestAddressName(String destAddressName) {
        this.destAddressName = destAddressName;
    }

    public String getShipAddr() {
        return shipAddr;
    }

    public void setShipAddr(String shipAddr) {
        this.shipAddr = shipAddr;
    }

    public Integer getRouteSchemaId() {
        return routeSchemaId;
    }

    public void setRouteSchemaId(Integer routeSchemaId) {
        this.routeSchemaId = routeSchemaId;
    }

    public Boolean isStydFlag() {
        return stydFlag;
    }

    public void setStydFlag(Boolean stydFlag) {
        this.stydFlag = stydFlag;
    }

    public String getCarriAgerecei() {
        return carriAgerecei;
    }

    public void setCarriAgerecei(String carriAgerecei) {
        this.carriAgerecei = carriAgerecei;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressOrderNo() {
        return expressOrderNo;
    }

    public void setExpressOrderNo(String expressOrderNo) {
        this.expressOrderNo = expressOrderNo;
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

    public String getDestHubName() {
        return destHubName;
    }

    public void setDestHubName(String destHubName) {
        this.destHubName = destHubName;
    }

    public int getOrderStyle() {
        return orderStyle;
    }

    public void setOrderStyle(int orderStyle) {
        this.orderStyle = orderStyle;
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

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public Boolean isRealNameFlag() {
        return realNameFlag;
    }

    public void setRealNameFlag(Boolean realNameFlag) {
        this.realNameFlag = realNameFlag;
    }

    public Boolean isPayFlag() {
        return payFlag;
    }

    public void setPayFlag(Boolean payFlag) {
        this.payFlag = payFlag;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayPerson() {
        return payPerson;
    }

    public void setPayPerson(String payPerson) {
        this.payPerson = payPerson;
    }

    public String getPayPersonTelephone() {
        return payPersonTelephone;
    }

    public void setPayPersonTelephone(String payPersonTelephone) {
        this.payPersonTelephone = payPersonTelephone;
    }

    public Integer getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(Integer assignUserId) {
        this.assignUserId = assignUserId;
    }

    public String getOrderRouteInfo() {
        return orderRouteInfo;
    }

    public void setOrderRouteInfo(String orderRouteInfo) {
        this.orderRouteInfo = orderRouteInfo;
    }

    public Integer getFailureTimes() {
        return failureTimes;
    }

    public void setFailureTimes(Integer failureTimes) {
        this.failureTimes = failureTimes;
    }
}
