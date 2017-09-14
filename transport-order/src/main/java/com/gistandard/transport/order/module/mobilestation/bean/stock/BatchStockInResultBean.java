package com.gistandard.transport.order.module.mobilestation.bean.stock;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/12/2.
 */
@ApiModel(description = "米站收件结果对象", parent = BatchStockInReqBean.class)
public class BatchStockInResultBean extends BatchStockInReqBean {

    @ApiModelProperty(value = "收件结果（true成功，false失败）", required = true, position = 3)
    private boolean stockFlag;

    @ApiModelProperty(value = "是否重新打印订单", required = true, position = 4)
    private boolean rePrintOrder;

    @ApiModelProperty(value = "失败原因", required = true, position = 5)
    private String msg; //失败原因

    @ApiModelProperty(value = "收件人姓名", required = true, position = 6)
    private String cneeCustLinkMan;

    @ApiModelProperty(value = "收件人手机号", required = true, position = 7)
    private String cneeCustLinkTel;

    @ApiModelProperty(value = "路由线路信息", required = true, position = 8)
    private String routePathInfo;

    public boolean getStockFlag() {
        return stockFlag;
    }

    public void setStockFlag(boolean stockFlag) {
        this.stockFlag = stockFlag;
    }

    public boolean getRePrintOrder() {
        return rePrintOrder;
    }

    public void setRePrintOrder(boolean rePrintOrder) {
        this.rePrintOrder = rePrintOrder;
    }

    public String getMsg() {
        return msg;
    }

    public BatchStockInResultBean setMsg(String msg) {
        this.msg = msg;
        return this;
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

    public String getRoutePathInfo() {
        return routePathInfo;
    }

    public void setRoutePathInfo(String routePathInfo) {
        this.routePathInfo = routePathInfo;
    }
}
