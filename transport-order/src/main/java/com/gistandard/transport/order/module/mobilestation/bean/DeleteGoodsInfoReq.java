package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: DeleteGoodsInfoReq
 * @Date: 2016/3/2
 * @Description: 删除货物信息请求Bean
 */
public class DeleteGoodsInfoReq extends AppBaseRequest {
    private static final long serialVersionUID = -6809666684013077211L;

    private Integer orderId;
    private Integer goodsId;
    private String acctUsername;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }
}
