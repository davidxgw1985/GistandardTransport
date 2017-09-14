package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileAssignOrderDetailBean
 * @Date: 2016/1/26
 * @Description: 查询订单详细返回Bean
 */
public class MobileAssignOrderDetailBean implements Serializable {
    private static final long serialVersionUID = 2923303959213343294L;

    private Integer orderId;//主键
    private String busiBookNo;//业务订单号
    private String scheducarno;//实派车单号
    private Integer orderFrom;//1签派广播单，2运输指派单，3签派指派单，4个人指派
    private String productTypeName;//产品类型名称
    private String startAddress;//起点地址
    private String startLinkMan;//发货人
    private String startTel;//发货人联系电话
    private BigDecimal startLongitude;//发货经度
    private BigDecimal startLatitude;//发货纬度
    private String destLinkMan;//收货人
    private String destAddress;//收货地址
    private String destTel;//收货人联系电话
    private BigDecimal destLongitude;//收货经度
    private BigDecimal destLatitude;//收货纬度
    private Integer revUserId;//接单人编号
    private String revUser;//接单人
    private String revUserName;//接单人姓名
    private Date revDate;//接单日期
    private List<MobileGoodsInfo> goodsInfoList;//货物信息
    private String quotedTypeName;//报价类型名称
    private BigDecimal predictValue;//运费
    private String predictCurr;//运费币制
    private Integer payType;//1到付2平台支付3现金支付
    private Boolean needInsure;//是否投保
    private BigDecimal premiumProportion;//保险费率
    private BigDecimal premiumValue;//保险费用
    private BigDecimal goodsValue;//货值(USD)
    private String narrate;//操作要求
    private Date etaPopDate;//预约时间
    private String pickType;//取件方式

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
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

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
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

    public String getDestLinkMan() {
        return destLinkMan;
    }

    public void setDestLinkMan(String destLinkMan) {
        this.destLinkMan = destLinkMan;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public String getDestTel() {
        return destTel;
    }

    public void setDestTel(String destTel) {
        this.destTel = destTel;
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

    public String getRevUserName() {
        return revUserName;
    }

    public void setRevUserName(String revUserName) {
        this.revUserName = revUserName;
    }

    public Date getRevDate() {
        return revDate;
    }

    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    public List<MobileGoodsInfo> getGoodsInfoList() {
        return goodsInfoList;
    }

    public void setGoodsInfoList(List<MobileGoodsInfo> goodsInfoList) {
        this.goodsInfoList = goodsInfoList;
    }

    public String getQuotedTypeName() {
        return quotedTypeName;
    }

    public void setQuotedTypeName(String quotedTypeName) {
        this.quotedTypeName = quotedTypeName;
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

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }

    public Date getEtaPopDate() {
        return etaPopDate;
    }

    public void setEtaPopDate(Date etaPopDate) {
        this.etaPopDate = etaPopDate;
    }

    public String getPickType() {
        return pickType;
    }

    public void setPickType(String pickType) {
        this.pickType = pickType;
    }
}
