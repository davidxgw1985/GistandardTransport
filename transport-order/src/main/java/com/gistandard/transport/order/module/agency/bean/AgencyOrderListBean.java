package com.gistandard.transport.order.module.agency.bean;

import com.gistandard.transport.base.entity.bean.MobileGoodsDtl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author kongxm
 * @ClassName AgencyOrderListBean
 * @Description 代理下单订单列表查询返回
 * @Version 1.0
 * @Date 2016/2/2
 */
@ApiModel(description = "自理下单-订单列表查询返回Bean")
public class AgencyOrderListBean {

	@ApiModelProperty(value = "mobileStation订单Id", position = 1)
	private Integer mobileBookingFormId;

	@ApiModelProperty(value = "订单编号", position = 2)
	private String busiBookNo;

	@ApiModelProperty(value = "预估价", position = 3)
	private BigDecimal predictValue;
	
	@ApiModelProperty(value = "货币单位", position = 4)
	private String predictCurr;

	@ApiModelProperty(value = "发货方地址", position = 5)
	private String shipCustAddr;

	@ApiModelProperty(value = "发货方联系人", position = 6)
	private String shipCustLinkMan;

	@ApiModelProperty(value = "发货方联系电话", position = 7)
	private String shipCustLinkTel;

	@ApiModelProperty(value = "收货方地址", position = 8)
	private String cneeCustAddr;

	@ApiModelProperty(value = "收货方联系人", position = 9)
	private String cneeCustLinkMan;

	@ApiModelProperty(value = "收货方联系电话", position = 10)
	private String cneeCustLinkTel;

	@ApiModelProperty(value = "接单人", position = 11)
    private String revUser;

	@ApiModelProperty(value = "订单描述", position = 12)
    private String orderRemark;

	@ApiModelProperty(value = "订单创建日期", position = 13)
	private Date createDate;

	@ApiModelProperty(value = "发货人的省", position = 14)
	private String shipCustProvinceName;

	@ApiModelProperty(value = "发货人的市", position = 15)
	private String shipCustCityName;

	@ApiModelProperty(value = "路由信息", position = 16)
	private String orderRouteInfo;

	@ApiModelProperty(value = "产品类型", position = 17)
	private String productType;
	/**
	 * 不存在子订单的情况下货物信息
	 */
	private List<MobileGoodsDtl> mobileGoodDtlList;//货物信息

	public String getPredictCurr() {
		return predictCurr;
	}

	public void setPredictCurr(String predictCurr) {
		this.predictCurr = predictCurr;
	}

	public Integer getMobileBookingFormId() {
		return mobileBookingFormId;
	}

	public void setMobileBookingFormId(Integer mobileBookingFormId) {
		this.mobileBookingFormId = mobileBookingFormId;
	}

	public String getBusiBookNo() {
		return busiBookNo;
	}

	public void setBusiBookNo(String busiBookNo) {
		this.busiBookNo = busiBookNo;
	}

	public BigDecimal getPredictValue() {
		return predictValue;
	}

	public void setPredictValue(BigDecimal predictValue) {
		this.predictValue = predictValue;
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

	public String getRevUser() {
		return revUser;
	}

	public void setRevUser(String revUser) {
		this.revUser = revUser;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public List<MobileGoodsDtl> getMobileGoodDtlList() {
		return mobileGoodDtlList;
	}

	public void setMobileGoodDtlList(List<MobileGoodsDtl> mobileGoodDtlList) {
		this.mobileGoodDtlList = mobileGoodDtlList;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getShipCustProvinceName() {
		return shipCustProvinceName;
	}

	public void setShipCustProvinceName(String shipCustProvinceName) {
		this.shipCustProvinceName = shipCustProvinceName;
	}

	public String getShipCustCityName() {
		return shipCustCityName;
	}

	public void setShipCustCityName(String shipCustCityName) {
		this.shipCustCityName = shipCustCityName;
	}

	public String getOrderRouteInfo() {
		return orderRouteInfo;
	}

	public void setOrderRouteInfo(String orderRouteInfo) {
		this.orderRouteInfo = orderRouteInfo;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
}
