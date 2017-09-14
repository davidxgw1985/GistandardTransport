package com.gistandard.transport.app.dubbo.wechat.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

import java.io.Serializable;

/**
 * 微信预约下单返回
 * Created by yjf on 2017/2/6.
 */
public class WeChatOrderRes extends MsDubboResBean implements Serializable {
    private static final long serialVersionUID = 6655698891245929814L;
    private String busiBookNo;//订单号
    private Integer orderId;//订单ID

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public WeChatOrderRes(MsDubboReqBean appBaseRequest) {
        super(appBaseRequest);
    }
}
