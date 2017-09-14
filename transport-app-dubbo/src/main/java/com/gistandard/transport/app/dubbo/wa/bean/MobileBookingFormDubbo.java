package com.gistandard.transport.app.dubbo.wa.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: MobileBookingFormDubbo
 * @Date: 2017/7/4
 * @Description: 对应MobileBookingForm表
 */
public class MobileBookingFormDubbo implements Serializable{
    private static final long serialVersionUID = -5723489249794077530L;

    private String busiBookNo;//业务订单号
    private String shipCustProvide;//发货方省
    private String shipCustCity;//发货方市
    private String shipCustCounty;//发货方区
    private String shipCustAddr;//发货方地址
    private String shipCustLinkMan;//发货方联系人
    private String shipCustLinkTel;//发货方联系电话
    private BigDecimal shipLongitude;//发货方经度
    private BigDecimal shipLatitude;//发货方纬度
    private String cneeCustProvide;//收货方省
    private String cneeCustCity;//收货方市
    private String cneeCustCounty;//收货方区
    private String cneeCustAddr;//收货方地址
    private String cneeCustLinkMan;//收货方联系人
    private String cneeCustLinkTel;//收货方联系电话
    private BigDecimal cneeLongitude;//收货方经度
    private BigDecimal cneeLatitude;//收货方纬度
    private Date etaPopDate;//ETA POP
    private String teamComsixNo;//接单HUB代码
    private Integer transportType;//订单操作类型
    private Integer orderType;//订单类型
    private Integer payType;//支付方式
    private BigDecimal goodsPayment;//收货货款
    private String goodsPaymentCurr;//收货货款币制
    private BigDecimal destPayment;//到付金额
    private String destPaymentCurr;//到付币制
    private Boolean needInsure;//是否投保
    private BigDecimal premiumProportion;//保险比例
    private BigDecimal premiumValue;//保险费用
    private BigDecimal goodsValue;//货值(USD)
    private Integer createUserId;//创建人账号Id
    private String createUser;//创建人账号
    private Date createDate;
    private Integer createCompanyId;
    private String comQuoteId;//报价单号
    private Integer quotedType;//报价类型
    private BigDecimal predictValue;//运费
    private String predictCurr;//币制
    private Integer orderFrom;//订单来源
    private Integer dispatchId;//签派单号
    private String scheducarno;//派车单号
    private String startLocus;//派车单起始站点
    private Integer startLocusId;//派车单起始站点
    private String destnLocus;//派车单目的站点
    private Integer destnLocusId;//派车单目的站点
    private String productType;//产品类型
    private String narrate;//操作要求
    private Integer busiCtrl;//订单状态：0待接单、1已接单
    private String revUser;//接单人
    private Integer revUserId;//接单人ID
    private Date revDate;//接单日期
    private Integer revCompanyId;//司机、快递员所属企业账号ID
    private int roleId;//3:司机，7：快递员

    private BigDecimal selfQuoteValue;//自报价
    private String selfQuoteCurr;//自报价币制
    private String bidBy;//发起竞价来源 W
    private String bidUser;//发起竞价咪站或蛙站账号
    private Integer bidUserId;//发起竞价咪站或蛙站账号ID
    private Integer vehicleTypeId;//车辆类型ID
    private BigDecimal mileage;//公里数

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public Integer getStartLocusId() {
        return startLocusId;
    }

    public void setStartLocusId(Integer startLocusId) {
        this.startLocusId = startLocusId;
    }

    public Integer getDestnLocusId() {
        return destnLocusId;
    }

    public void setDestnLocusId(Integer destnLocusId) {
        this.destnLocusId = destnLocusId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getShipCustProvide() {
        return shipCustProvide;
    }

    public void setShipCustProvide(String shipCustProvide) {
        this.shipCustProvide = shipCustProvide;
    }

    public String getShipCustCity() {
        return shipCustCity;
    }

    public void setShipCustCity(String shipCustCity) {
        this.shipCustCity = shipCustCity;
    }

    public String getShipCustCounty() {
        return shipCustCounty;
    }

    public void setShipCustCounty(String shipCustCounty) {
        this.shipCustCounty = shipCustCounty;
    }

    public String getShipCustAddr() {
        return shipCustAddr;
    }

    public void setShipCustAddr(String shipCustAddr) {
        this.shipCustAddr = shipCustAddr;
    }

    public String getShipCustLinkMan() {
        return shipCustLinkMan;
    }

    public void setShipCustLinkMan(String shipCustLinkMan) {
        this.shipCustLinkMan = shipCustLinkMan;
    }

    public String getShipCustLinkTel() {
        return shipCustLinkTel;
    }

    public void setShipCustLinkTel(String shipCustLinkTel) {
        this.shipCustLinkTel = shipCustLinkTel;
    }

    public BigDecimal getShipLongitude() {
        return shipLongitude;
    }

    public void setShipLongitude(BigDecimal shipLongitude) {
        this.shipLongitude = shipLongitude;
    }

    public BigDecimal getShipLatitude() {
        return shipLatitude;
    }

    public void setShipLatitude(BigDecimal shipLatitude) {
        this.shipLatitude = shipLatitude;
    }

    public String getCneeCustProvide() {
        return cneeCustProvide;
    }

    public void setCneeCustProvide(String cneeCustProvide) {
        this.cneeCustProvide = cneeCustProvide;
    }

    public String getCneeCustCity() {
        return cneeCustCity;
    }

    public void setCneeCustCity(String cneeCustCity) {
        this.cneeCustCity = cneeCustCity;
    }

    public String getCneeCustCounty() {
        return cneeCustCounty;
    }

    public void setCneeCustCounty(String cneeCustCounty) {
        this.cneeCustCounty = cneeCustCounty;
    }

    public String getCneeCustAddr() {
        return cneeCustAddr;
    }

    public void setCneeCustAddr(String cneeCustAddr) {
        this.cneeCustAddr = cneeCustAddr;
    }

    public String getCneeCustLinkMan() {
        return cneeCustLinkMan;
    }

    public void setCneeCustLinkMan(String cneeCustLinkMan) {
        this.cneeCustLinkMan = cneeCustLinkMan;
    }

    public String getCneeCustLinkTel() {
        return cneeCustLinkTel;
    }

    public void setCneeCustLinkTel(String cneeCustLinkTel) {
        this.cneeCustLinkTel = cneeCustLinkTel;
    }

    public BigDecimal getCneeLongitude() {
        return cneeLongitude;
    }

    public void setCneeLongitude(BigDecimal cneeLongitude) {
        this.cneeLongitude = cneeLongitude;
    }

    public BigDecimal getCneeLatitude() {
        return cneeLatitude;
    }

    public void setCneeLatitude(BigDecimal cneeLatitude) {
        this.cneeLatitude = cneeLatitude;
    }

    public Date getEtaPopDate() {
        return etaPopDate;
    }

    public void setEtaPopDate(Date etaPopDate) {
        this.etaPopDate = etaPopDate;
    }

    public String getTeamComsixNo() {
        return teamComsixNo;
    }

    public void setTeamComsixNo(String teamComsixNo) {
        this.teamComsixNo = teamComsixNo;
    }

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getGoodsPayment() {
        return goodsPayment;
    }

    public void setGoodsPayment(BigDecimal goodsPayment) {
        this.goodsPayment = goodsPayment;
    }

    public String getGoodsPaymentCurr() {
        return goodsPaymentCurr;
    }

    public void setGoodsPaymentCurr(String goodsPaymentCurr) {
        this.goodsPaymentCurr = goodsPaymentCurr;
    }

    public BigDecimal getDestPayment() {
        return destPayment;
    }

    public void setDestPayment(BigDecimal destPayment) {
        this.destPayment = destPayment;
    }

    public String getDestPaymentCurr() {
        return destPaymentCurr;
    }

    public void setDestPaymentCurr(String destPaymentCurr) {
        this.destPaymentCurr = destPaymentCurr;
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

    public Integer getCreateCompanyId() {
        return createCompanyId;
    }

    public void setCreateCompanyId(Integer createCompanyId) {
        this.createCompanyId = createCompanyId;
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

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public String getRevUser() {
        return revUser;
    }

    public void setRevUser(String revUser) {
        this.revUser = revUser;
    }

    public Integer getRevUserId() {
        return revUserId;
    }

    public void setRevUserId(Integer revUserId) {
        this.revUserId = revUserId;
    }

    public Date getRevDate() {
        return revDate;
    }

    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    public Integer getRevCompanyId() {
        return revCompanyId;
    }

    public void setRevCompanyId(Integer revCompanyId) {
        this.revCompanyId = revCompanyId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public String getBidBy() {
        return bidBy;
    }

    public void setBidBy(String bidBy) {
        this.bidBy = bidBy;
    }

    public String getBidUser() {
        return bidUser;
    }

    public void setBidUser(String bidUser) {
        this.bidUser = bidUser;
    }

    public Integer getBidUserId() {
        return bidUserId;
    }

    public void setBidUserId(Integer bidUserId) {
        this.bidUserId = bidUserId;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
}
