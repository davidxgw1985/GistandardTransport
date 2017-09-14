package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.transport.app.dubbo.order.bean.MobileGoodsInfo;
import com.gistandard.transport.app.dubbo.order.bean.MobileSubOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: longfeng
 * @ClassName: MobileAcceptOrderQueryListBean
 * @Date: 2016/1/26
 * @Description: 接单-订单列表查询返回Bean
 */
@ApiModel(description = "接单-订单列表查询返回Bean")
public class MobileAcceptOrderQueryListBean implements Comparable<MobileAcceptOrderQueryListBean>{
    private static final long serialVersionUID = -4195861515237422869L;
    @ApiModelProperty(value = "主键", position = 1)
    private Integer orderId;
    @ApiModelProperty(value = "订单号", position = 2)
    private int bookingFormId;
    @ApiModelProperty(value = "业务订单号", position = 3)
    private String busiBookNo;
    @ApiModelProperty(value = "订单类型 1：取件单 2：派件单", position = 4)
    private int orderType;
    @ApiModelProperty(value = "订单描述信息", position = 5)
    private String description;
    @ApiModelProperty(value = "预约时间", position = 6)
    private Date etaPopDate;
    @ApiModelProperty(value = "建单人账号Id", position = 7)
    private Integer createUserId;
    @ApiModelProperty(value = "建单人账号", position = 8)
    private String createUser;
    @ApiModelProperty(value = "订单创建日期", position = 9)
    private Date createDate;
    @ApiModelProperty(value = "接单日期", position = 10)
    private Date revDate;
    @ApiModelProperty(value = "完成日期", position = 11)
    private Date finishDate;
    @ApiModelProperty(value = "联系电话", position = 12)
    private String linkTel;
    @ApiModelProperty(value = "发货人联系电话", position = 13)
    private String startTel;
    @ApiModelProperty(value = "收货人联系电话", position = 14)
    private String destTel;
    @ApiModelProperty(value = "运费", position = 17)
    private BigDecimal predictValue;//
    @ApiModelProperty(value = "运费币制", position = 18)
    private String predictCurr;
    @ApiModelProperty(value = "支付方式", position = 19)
    private Integer payType;
    @ApiModelProperty(value = "起点省", position = 24)
    private String startProvide;
    @ApiModelProperty(value = "起点市", position = 25)
    private String startCity;
    @ApiModelProperty(value = "起点区", position = 26)
    private String startCounty;
    @ApiModelProperty(value = "起点地址", position = 27)
    private String startAddress;
    @ApiModelProperty(value = "起点经度", position = 28)
    private BigDecimal startLongitude;
    @ApiModelProperty(value = "起点纬度", position = 29)
    private BigDecimal startLatitude;
    @ApiModelProperty(value = "收货省", position = 30)
    private String destProvide;
    @ApiModelProperty(value = "收货市", position = 31)
    private String destCity;
    @ApiModelProperty(value = "收货区", position = 32)
    private String destCounty;
    @ApiModelProperty(value = "收货地址", position = 33)
    private String destAddress;
    @ApiModelProperty(value = "收货经度", position = 34)
    private BigDecimal destLongitude;
    @ApiModelProperty(value = "收货纬度", position = 35)
    private BigDecimal destLatitude;
    @ApiModelProperty(value = "订单操作类型 0、物流，1、运输，2、快递", position = 36)
    private Integer transportType;
    @ApiModelProperty(value = "签派单号", position = 37)
    private Integer dispatchId;
    @ApiModelProperty(value = "实派车单号", position = 38)
    private String scheducarno;
    @ApiModelProperty(value = "报价单号", position = 39)
    private String comQuoteId;
    @ApiModelProperty(value = "报价类型", position = 40)
    private Integer quotedType;
    @ApiModelProperty(value = "距离，单位：米", position = 41)
    private BigDecimal distance;
    @ApiModelProperty(value = "订单来源", position = 42)
    private Integer orderFrom;
    @ApiModelProperty(value = "入库状态，false未入库，取发货地址，true已入库，取收货地址", position = 35)
    private Boolean stockFlag;
    @ApiModelProperty(value = "", position = 43)
    private Integer flag;
    @ApiModelProperty(value = "业务状态控制", position = 44)
    private Integer busiCtrl;
    @ApiModelProperty(value = "派车单起始站点", position = 45)
    private String startLocus;
    @ApiModelProperty(value = "派车单目的站点", position = 46)
    private String destnLocus;
    @ApiModelProperty(value = "产品类型", position = 47)
    private String productType;
    @ApiModelProperty(value = "操作要求", position = 48)
    private String narrate;
    @ApiModelProperty(value = "货物信息", position = 49)
    private List<MobileGoodsInfo> goodsInfoList;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
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


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getBookingFormId() {
        return bookingFormId;
    }

    public void setBookingFormId(int bookingFormId) {
        this.bookingFormId = bookingFormId;
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

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getStartTel() {
        return startTel;
    }

    public void setStartTel(String startTel) {
        this.startTel = startTel;
    }

    public String getDestTel() {
        return destTel;
    }

    public void setDestTel(String destTel) {
        this.destTel = destTel;
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

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
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

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public void setStockFlag(Boolean stockFlag) {
        this.stockFlag = stockFlag;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Boolean isStockFlag() {
        return stockFlag;
    }

    public Integer getBusiCtrl() {
        return busiCtrl;
    }

    public void setBusiCtrl(Integer busiCtrl) {
        this.busiCtrl = busiCtrl;
    }

    public List<MobileGoodsInfo> getGoodsInfoList() {
        return goodsInfoList;
    }

    public void setGoodsInfoList(List<MobileGoodsInfo> goodsInfoList) {
        this.goodsInfoList = goodsInfoList;
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

    @Override
    public int compareTo(MobileAcceptOrderQueryListBean listBean) {
        return this.distance.compareTo(listBean.getDistance());
    }
}
