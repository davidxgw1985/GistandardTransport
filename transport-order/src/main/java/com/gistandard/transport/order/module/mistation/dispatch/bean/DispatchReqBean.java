package com.gistandard.transport.order.module.mistation.dispatch.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: DispatchReqBean
 * @Date: 2016/12/12
 * @Description: 批量中转单个请求对象
 */
@ApiModel(description = "批量中转单个请求对象")
public class DispatchReqBean {

    @ApiModelProperty(value = "签派的订单号",required = true, position = 1)
    private String busiNo;

    @ApiModelProperty(value = "签派人",required = true, position = 2)
    private Integer createUserId;

    @ApiModelProperty(value = "指派的W站编号",required = true, position = 3)
    private Integer wHubId;

    @ApiModelProperty(value = "被指派站点类型 M:咪站，W:蛙站(即HUB站点)，如m->w 这里赋值W #StationType",required = true, position = 4)
    private String type;

    @ApiModelProperty(value = "操作要求",required = true, position = 5)
    private String narrate;

    @ApiModelProperty(value = "手机站点订单主键",required = true, position = 6)
    private Integer mobileBookingFormId;

    public String getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getWHubId() {
        return wHubId;
    }

    public void setWHubId(Integer wHubId) {
        this.wHubId = wHubId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNarrate() {
        return narrate;
    }

    public void setNarrate(String narrate) {
        this.narrate = narrate;
    }

    public Integer getMobileBookingFormId() {
        return mobileBookingFormId;
    }

    public void setMobileBookingFormId(Integer mobileBookingFormId) {
        this.mobileBookingFormId = mobileBookingFormId;
    }

}
