package com.gistandard.transport.order.webservice.server.mobilestation.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: getOrderAddressInfoReq
 * @Date: 2016/3/16
 * @Description: 获取订单地址信息请求bean
 */
public class GetOrderAddressInfoReq implements Serializable {

    private static final long serialVersionUID = -4796876910342415146L;

    private String busiNo;//订单编号Code
    private Integer type;//0开始位置，1到达位置

    public String getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
