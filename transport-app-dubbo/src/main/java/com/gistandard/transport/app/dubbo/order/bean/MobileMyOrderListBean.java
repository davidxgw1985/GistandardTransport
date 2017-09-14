package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileMyOrderListBean
 * @Date: 2016/6/7
 * @Description: MS3.0 订单管理-我的订单列表查询返回Bean
 */
public class MobileMyOrderListBean implements Serializable {
    private static final long serialVersionUID = -4195861515237422869L;

    private int orderId;//主键
    private String busiBookNo;//业务订单号
    private int orderType;//订单类型 1：取件单 2：派件单
    private String description;//订单描述信息	类型/品名+重量+体积
    private Date etaPopDate;//预约时间
    private String narrate;//操作要求
    private int createUserId;//建单人账号Id
    private String createUser;//建单人账号Id
    private Date revDate;//接单日期
    private Date finishDate;//完成日期
    private BigDecimal orderPrice;//应收金额
    private String currency;//币值名称
    private BigDecimal predictValue;//运费
    private String predictCurr;//运费币制
    private Integer payType;

    private String startProvide;
    private String startCity;
    private String startCounty;
    private String startAddress;//起点地址
    private BigDecimal startLongitude;//发货经度
    private BigDecimal startLatitude;//发货纬度
    private String startTel;
    private String destProvide;
    private String destCity;
    private String destCounty;
    private String destAddress;//收货地址
    private BigDecimal destLongitude;//收货经度
    private BigDecimal destLatitude;//收货纬度
    private String destTel;

    private Integer transportType;//订单操作类型 0、物流，1、运输，2、快递
    private String productType;//产品类型 国内快递 OGNEXP，同城快递 OTCEXP，国内陆路运输 OGNLLYS 等
    private Integer dispatchId;//签派单号
    private String scheducarno;//实派车单号
    private Integer orderFrom;//1签派广播单，2运输指派单，3签派指派单，4个人指派单，5运输广播单，6个人广播单
    private Boolean stockFlag;//入库状态，false未入库，取发货地址，true已入库，取收货地址
    private Integer busiCtrl;//业务状态控制

    private String startLocus;//派车单起始站点
    private String destnLocus;//派车单目的站点
    private String comQuoteId;//报价单号
    private Integer quotedType;//报价类型
    private String validBillno;//对账单号
    private Integer connectionStatus;//0不是接驳单，1是接驳单
    private String connectionOrderNo;//接驳单单号
    private List<MobileGoodsInfo> goodsInfoList;//货物信息

    private List<MobileScheduSubOrder> subOrderList;//子订单

    private Boolean dispatchFlag;//是否显示签派按钮

    private String msLinkName;//咪站指派的MS姓名
    private String msLinkTel;//咪站指派的MS联系电话
    private String msLinkCarNo;//咪站指派的MS车牌号
    private String destAddressName;//咪站 M-POD 展示目的地市、区；M-W 展示W站点名称
    private String shipAddr;//发货人地址

    private Integer routeSchemaId;  //路由Id(暂时不用)

    private Boolean stydFlag;//是否四通一达订单

    public String getShipAddr() {
        return shipAddr;
    }

    public void setShipAddr(String shipAddr) {
        this.shipAddr = shipAddr;
    }

    public List<MobileScheduSubOrder> getSubOrderList() {
        return subOrderList;
    }

    public void setSubOrderList(List<MobileScheduSubOrder> subOrderList) {
        this.subOrderList = subOrderList;
    }

	public List<MobileGoodsInfo> getGoodsInfoList() {
		return goodsInfoList;
	}

	public void setGoodsInfoList(List<MobileGoodsInfo> goodsInfoList) {
		this.goodsInfoList = goodsInfoList;
	}

	public String getValidBillno() {
		return validBillno;
	}

	public void setValidBillno(String validBillno) {
		this.validBillno = validBillno;
	}

	public Integer getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(Integer connectionStatus) {
		this.connectionStatus = connectionStatus;
	}

	public String getConnectionOrderNo() {
		return connectionOrderNo;
	}

	public void setConnectionOrderNo(String connectionOrderNo) {
		this.connectionOrderNo = connectionOrderNo;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
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

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    public Boolean isDispatchFlag() {
        return dispatchFlag;
    }

    public void setDispatchFlag(Boolean dispatchFlag) {
        this.dispatchFlag = dispatchFlag;
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

    public Integer getRouteSchemaId() {
        return routeSchemaId;
    }

    public void setRouteSchemaId(Integer routeSchemaId) {
        this.routeSchemaId = routeSchemaId;
    }

    public Boolean getStydFlag() {
        return stydFlag;
    }

    public void setStydFlag(Boolean stydFlag) {
        this.stydFlag = stydFlag;
    }
}
