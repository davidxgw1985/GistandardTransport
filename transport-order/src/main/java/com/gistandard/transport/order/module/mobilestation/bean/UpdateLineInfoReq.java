package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: UpdateLineInfoReq
 * @Date: 2016/2/24
 * @Description: 修改线路信息请求
 */
@ApiModel(description = "修改线路信息请求对象", parent = AppBaseRequest.class)
public class UpdateLineInfoReq extends AppBaseRequest {

    @ApiModelProperty(value = "Mobile订单表主键", required = true, position = 1)
    private Integer orderId;//Mobile订单表主键
    @ApiModelProperty(value = "发货地址", required = true, position = 2)
    private String shipCustAddr;//发货地址
    @ApiModelProperty(value = "发货方经度", required = true, position = 3)
    private String shipLongitude;//发货方经度
    @ApiModelProperty(value = "发货方纬度", required = true, position = 4)
    private String shipLatitude;//发货方纬度
    @ApiModelProperty(value = "收货地址", required = true, position = 5)
    private String cneeCustAddr;//收货地址
    @ApiModelProperty(value = "收货方经度", required = true, position = 6)
    private String cneeLongitude;//收货方经度
    @ApiModelProperty(value = "收货方纬度", required = true, position = 7)
    private String cneeLatitude;//收货方纬度
    @ApiModelProperty(value = "发货方省", required = false, position = 8)
    private Integer shipProvince;//发货方省
    @ApiModelProperty(value = "发货方市", required = false, position = 9)
    private Integer shipCity;//发货方市
    @ApiModelProperty(value = "发货方区", required = false, position = 10)
    private Integer shipCounty;//发货方区
    @ApiModelProperty(value = "收货方省", required = false, position = 11)
    private Integer cneeProvince;//收货方省
    @ApiModelProperty(value = "收货方市", required = false, position = 12)
    private Integer cneeCity;//收货方市
    @ApiModelProperty(value = "收货方区", required = false, position = 13)
    private Integer cneeCounty;//收货方区
    @ApiModelProperty(value = "起点到终点的公里数(同城专送时需要 单位公里)", required = false, position = 14)
    private BigDecimal mileage;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getShipCustAddr() {
        return shipCustAddr;
    }

    public void setShipCustAddr(String shipCustAddr) {
        this.shipCustAddr = shipCustAddr;
    }

    public String getShipLongitude() {
        return shipLongitude;
    }

    public void setShipLongitude(String shipLongitude) {
        this.shipLongitude = shipLongitude;
    }

    public String getShipLatitude() {
        return shipLatitude;
    }

    public void setShipLatitude(String shipLatitude) {
        this.shipLatitude = shipLatitude;
    }

    public String getCneeCustAddr() {
        return cneeCustAddr;
    }

    public void setCneeCustAddr(String cneeCustAddr) {
        this.cneeCustAddr = cneeCustAddr;
    }

    public String getCneeLongitude() {
        return cneeLongitude;
    }

    public void setCneeLongitude(String cneeLongitude) {
        this.cneeLongitude = cneeLongitude;
    }

    public String getCneeLatitude() {
        return cneeLatitude;
    }

    public void setCneeLatitude(String cneeLatitude) {
        this.cneeLatitude = cneeLatitude;
    }

    public Integer getShipProvince() {
        return shipProvince;
    }

    public void setShipProvince(Integer shipProvince) {
        this.shipProvince = shipProvince;
    }

    public Integer getShipCity() {
        return shipCity;
    }

    public void setShipCity(Integer shipCity) {
        this.shipCity = shipCity;
    }

    public Integer getShipCounty() {
        return shipCounty;
    }

    public void setShipCounty(Integer shipCounty) {
        this.shipCounty = shipCounty;
    }

    public Integer getCneeProvince() {
        return cneeProvince;
    }

    public void setCneeProvince(Integer cneeProvince) {
        this.cneeProvince = cneeProvince;
    }

    public Integer getCneeCity() {
        return cneeCity;
    }

    public void setCneeCity(Integer cneeCity) {
        this.cneeCity = cneeCity;
    }

    public Integer getCneeCounty() {
        return cneeCounty;
    }

    public void setCneeCounty(Integer cneeCounty) {
        this.cneeCounty = cneeCounty;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }
}
