package com.gistandard.transport.order.module.mobilestation.bean.userorder;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: xgw
 * @ClassName: GetOrderPayStatusReq
 * @Date: 2016/7/30
 * @Description: 获取订单的支付信息请求Bean
 */
@ApiModel(description = "获取订单的支付信息请求Bean", parent = AppBaseRequest.class)
public class GetOrderPayStatusReq extends AppBaseRequest{
    private static final long serialVersionUID = 3263431675593462208L;

    @ApiModelProperty(value = "订单号",required = true, position = 1)
    private String busiBookNo;

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }
}
