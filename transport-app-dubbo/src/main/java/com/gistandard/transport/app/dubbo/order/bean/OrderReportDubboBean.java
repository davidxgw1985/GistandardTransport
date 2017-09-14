package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单报表查询dubbo接口返回数据
 * @author 员伟
 * @date 2017-08-31
 */
public class OrderReportDubboBean implements Serializable{

    private static final long serialVersionUID = 2332185301122123513L;

    private String busiBookNo;//订单号

    private Date bookingDate;//下单时间

    private String payUser;//付款账号

    private String payUserRealName;//付款人姓名

    private String transportType;//产品类型 OTCKD OTCZS OTCYSEX

    private String createUser;//下单人账号

    private String carriageDelivAddr;//发货地址

    private String carriageReceiAddr;//收货地址

    private String shipCustlinkMan;//发件人

    private String shipCustlinkTel;//发件人联系方式

    private String cneeCustLinkMan;//收件人

    private String cneeCustLinkTel;//收件人联系方式

    private BigDecimal predictValue;//总金额

    private String predictCurr;//总金额币制

    private Integer recommendAccountId;//推荐人账号ID

    private String createUserName;//下单人姓名

    private String recommendAcctUser;//推荐人账号

    private String recommendAcctUserName;//推荐人姓名

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getPayUserRealName() {
        return payUserRealName;
    }

    public void setPayUserRealName(String payUserRealName) {
        this.payUserRealName = payUserRealName;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCarriageDelivAddr() {
        return carriageDelivAddr;
    }

    public void setCarriageDelivAddr(String carriageDelivAddr) {
        this.carriageDelivAddr = carriageDelivAddr;
    }

    public String getCarriageReceiAddr() {
        return carriageReceiAddr;
    }

    public void setCarriageReceiAddr(String carriageReceiAddr) {
        this.carriageReceiAddr = carriageReceiAddr;
    }

    public String getShipCustlinkMan() {
        return shipCustlinkMan;
    }

    public void setShipCustlinkMan(String shipCustlinkMan) {
        this.shipCustlinkMan = shipCustlinkMan;
    }

    public String getShipCustlinkTel() {
        return shipCustlinkTel;
    }

    public void setShipCustlinkTel(String shipCustlinkTel) {
        this.shipCustlinkTel = shipCustlinkTel;
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

    public Integer getRecommendAccountId() {
        return recommendAccountId;
    }

    public void setRecommendAccountId(Integer recommendAccountId) {
        this.recommendAccountId = recommendAccountId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getRecommendAcctUser() {
        return recommendAcctUser;
    }

    public void setRecommendAcctUser(String recommendAcctUser) {
        this.recommendAcctUser = recommendAcctUser;
    }

    public String getRecommendAcctUserName() {
        return recommendAcctUserName;
    }

    public void setRecommendAcctUserName(String recommendAcctUserName) {
        this.recommendAcctUserName = recommendAcctUserName;
    }


}
