package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileAssignOrderListBean
 * @Date: 2016/6/7
 * @Description: MS3.0 商管中心-指派订单列表查询返回Bean
 */
public class MobileAssignOrderListBean implements Serializable {

    private static final long serialVersionUID = -8839113886614881918L;
    private int orderId;
    private String busiBookNo;//业务订单号
    private int assignStatue;// 商管中心指派状态 0待指派、1已指派
    private Date createDate;//下单日期

    private String startAddress;//起点地址
    private BigDecimal startLongitude;//发货经度
    private BigDecimal startLatitude;//发货纬度
    private String startLinkMan;//发货人姓名
    private String startTel;//发货人电话
    private String destAddress;//收货地址
    private BigDecimal destLongitude;//收货经度
    private BigDecimal destLatitude;//收货纬度
    private String destLinkMan;//收货人姓名
    private String destTel;//收货人电话

    private String productType;//产品类型 国内快递 OGNEXP，同城快递 OTCEXP，国内陆路运输 OGNLLYS 等
    private String scheducarno;//实派车单号
    private Integer orderFrom;//1签派广播单，2运输指派单，3签派指派单，4个人指派单，5运输广播单，6个人广播单
    private Date pushDate;//推送日期
    private Integer transportType;//0物流  1运输  2快递
    private List<String> allRoleCode;           // 查询依据：多个角色（或的关系）

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

    public int getAssignStatue() {
        return assignStatue;
    }

    public void setAssignStatue(int assignStatue) {
        this.assignStatue = assignStatue;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    public Date getPushDate() {
        return pushDate;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    public List<String> getAllRoleCode() {
        return allRoleCode;
    }

    public void setAllRoleCode(List<String> allRoleCode) {
        this.allRoleCode = allRoleCode;
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
}
