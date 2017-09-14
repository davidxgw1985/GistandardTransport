package com.gistandard.transport.system.gps.bean;

import com.gistandard.transport.system.webservice.client.gps.GiPoint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: GiBizOrder
 * @Date: 2017/1/17
 * @Description:
 */
@ApiModel(description = "抢单列表查询返回单个对象")
public class GiBizOrder {
    @ApiModelProperty(value = "要推送的APP", position = 1)
    private String appTag;
    @ApiModelProperty(value = "业务订单号", position = 2)
    private String docNo;                       // 1   业务订单号
    @ApiModelProperty(value = "订单来源", position = 3)
    private String docFrom;                     // 订单来源
    @ApiModelProperty(value = "动作, 即要采取什么动作", position = 4)
    private String action;                      // 动作, 即要采取什么动作
    @ApiModelProperty(value = "多个模块", position = 5)
    private List<String> allModuleCode;         // 查询依据：多个模块（或的关系）
    @ApiModelProperty(value = "多个角色", position = 6)
    private List<String> allRoleCode;           // 查询依据：多个角色（或的关系）
    @ApiModelProperty(value = "订单Id", position = 7)
    private int docId;                          //     订单Id(即 订单自增的Id)
    @ApiModelProperty(value = "订单类型", position = 8)
    private int docType;                        // 2   订单类型    1=取件单，2=派件单
    @ApiModelProperty(value = "业务类型", position = 9)
    private int bizType;                        // 3   业务类型    1=运输，2=快递
    @ApiModelProperty(value = "产品类型", position = 10)
    private String productType;                 // 产品类型(code)， 国内快递etc
    @ApiModelProperty(value = "子定单", position = 11)
    private String jsonSubOrder;                //
    @ApiModelProperty(value = "签派单号", position = 12)
    private int dispatchId;                     //
    @ApiModelProperty(value = "实派车单号", position = 13)
    private String scheducarno;                 //
    @ApiModelProperty(value = "订单总价", position = 14)
    private BigDecimal totalPrice;              // 4       如果需要接单人收单据的总费用，则显示这个价格
    @ApiModelProperty(value = "订单运费", position = 15)
    private BigDecimal transportCost;           // 5       接单人执行完这单可得到的费用
    @ApiModelProperty(value = "币种  元，港币，美元", position = 16)
    private String currencyName;
    @ApiModelProperty(value = "运费币制", position = 17)
    private String predictCurr;
    @ApiModelProperty(value = "总重量 单位kg", position = 18)
    private BigDecimal totalWeight;
    @ApiModelProperty(value = "总体积 单位立方米", position = 19)
    private BigDecimal totalVolume;
    @ApiModelProperty(value = "起始省", position = 20)
    private String sourceProvince;
    @ApiModelProperty(value = "起始市", position = 21)
    private String sourceCity;
    @ApiModelProperty(value = "起始区", position = 22)
    private String sourceDistrict;
    @ApiModelProperty(value = "起始地址", position = 23)
    private String sourceAddress;
    @ApiModelProperty(value = "起始经纬度", position = 24)
    private GiPoint pointSource;
    @ApiModelProperty(value = "目的地省", position = 25)
    private String destProvince;
    @ApiModelProperty(value = "目的地市", position = 26)
    private String destCity;
    @ApiModelProperty(value = "目的地区", position = 27)
    private String destDistrict;
    @ApiModelProperty(value = "到达地址", position = 28)
    private String destAddress;
    @ApiModelProperty(value = "目的地经纬度", position = 29)
    private GiPoint pointDest;
    @ApiModelProperty(value = "描述", position = 30)
    private String description;
    @ApiModelProperty(value = "客户端调用时设置的时间", position = 31)
    private Date tsClientPushed;
    @ApiModelProperty(value = "距离，米", position = 32)
    private double distance;
    @ApiModelProperty(value = "发件人姓名", position = 33)
    private String sourceUserName;
    @ApiModelProperty(value = "发件人电话", position = 34)
    private String sourceUserTel;
    @ApiModelProperty(value = "收件人姓名", position = 35)
    private String destUserName;
    @ApiModelProperty(value = "收件人电话", position = 36)
    private String destUserTel;
    @ApiModelProperty(value = "是否实名用户下的单", position = 37)
    private Boolean isRealName;
    @ApiModelProperty(value = "是否为 嗨付", position = 38)
    private boolean isHifu;
    @ApiModelProperty(value = "订单创建类型 -- CARGO_LOADER =1 虚拟单; 0真实单", position = 38)
    private int typeCreated;


    public int getTypeCreated() {
        return typeCreated;
    }

    public void setTypeCreated(int typeCreated) {
        this.typeCreated = typeCreated;
    }

    public boolean getIsHifu() {
        return isHifu;
    }

    public void setIsHifu(boolean isHifu) {
        this.isHifu = isHifu;
    }

    public String getAppTag() {
        return appTag;
    }

    public void setAppTag(String appTag) {
        this.appTag = appTag;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getDocFrom() {
        return docFrom;
    }

    public void setDocFrom(String docFrom) {
        this.docFrom = docFrom;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<String> getAllModuleCode() {
        return allModuleCode;
    }

    public void setAllModuleCode(List<String> allModuleCode) {
        this.allModuleCode = allModuleCode;
    }

    public List<String> getAllRoleCode() {
        return allRoleCode;
    }

    public void setAllRoleCode(List<String> allRoleCode) {
        this.allRoleCode = allRoleCode;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public int getBizType() {
        return bizType;
    }

    public void setBizType(int bizType) {
        this.bizType = bizType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getJsonSubOrder() {
        return jsonSubOrder;
    }

    public void setJsonSubOrder(String jsonSubOrder) {
        this.jsonSubOrder = jsonSubOrder;
    }

    public int getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(int dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(BigDecimal transportCost) {
        this.transportCost = transportCost;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getPredictCurr() {
        return predictCurr;
    }

    public void setPredictCurr(String predictCurr) {
        this.predictCurr = predictCurr;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(BigDecimal totalVolume) {
        this.totalVolume = totalVolume;
    }

    public String getSourceProvince() {
        return sourceProvince;
    }

    public void setSourceProvince(String sourceProvince) {
        this.sourceProvince = sourceProvince;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getSourceDistrict() {
        return sourceDistrict;
    }

    public void setSourceDistrict(String sourceDistrict) {
        this.sourceDistrict = sourceDistrict;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public GiPoint getPointSource() {
        return pointSource;
    }

    public void setPointSource(GiPoint pointSource) {
        this.pointSource = pointSource;
    }

    public String getDestProvince() {
        return destProvince;
    }

    public void setDestProvince(String destProvince) {
        this.destProvince = destProvince;
    }

    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    public String getDestDistrict() {
        return destDistrict;
    }

    public void setDestDistrict(String destDistrict) {
        this.destDistrict = destDistrict;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public GiPoint getPointDest() {
        return pointDest;
    }

    public void setPointDest(GiPoint pointDest) {
        this.pointDest = pointDest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTsClientPushed() {
        return tsClientPushed;
    }

    public void setTsClientPushed(Date tsClientPushed) {
        this.tsClientPushed = tsClientPushed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getSourceUserName() {
        return sourceUserName;
    }

    public void setSourceUserName(String sourceUserName) {
        this.sourceUserName = sourceUserName;
    }

    public String getSourceUserTel() {
        return sourceUserTel;
    }

    public void setSourceUserTel(String sourceUserTel) {
        this.sourceUserTel = sourceUserTel;
    }

    public String getDestUserName() {
        return destUserName;
    }

    public void setDestUserName(String destUserName) {
        this.destUserName = destUserName;
    }

    public String getDestUserTel() {
        return destUserTel;
    }

    public void setDestUserTel(String destUserTel) {
        this.destUserTel = destUserTel;
    }

    public Boolean getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(Boolean isRealName) {
        this.isRealName = isRealName;
    }

}
