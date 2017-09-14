package com.gistandard.transport.order.module.mobilestation.bean.userorder;

import com.gistandard.transport.base.bean.app.BaseReqBean;

/**
 * @author: xgw
 * @ClassName: SingleOrderReq
 * @Date: 2016/6/11
 * @Description: 用户订单-转单接口请求Bean
 */
public class SingleOrderReq extends BaseReqBean {
    private static final long serialVersionUID = 2792946223508656903L;

    private Integer mobileBookingFormId;//订单ID
    private String busiBookNo;//订单号

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getMobileBookingFormId() {
        return mobileBookingFormId;
    }

    public void setMobileBookingFormId(Integer mobileBookingFormId) {
        this.mobileBookingFormId = mobileBookingFormId;
    }

}
