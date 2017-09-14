package com.gistandard.transport.app.dubbo.wa.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: WaConfirmOrderReq
 * @Date: 2017/7/4
 * @Description: 蛙站确认车队竞价请求Bean
 */
public class WaConfirmOrderReq implements Serializable {
    private static final long serialVersionUID = 4553657225376775471L;

    private MobileBookingFormDubbo mobileBookingForm;//订单信息
    private List<MobileSubOrderDubbo> mobileSubOrderList;//子订单信息
    private MobileFleetBiddingDubbo mobileFleetBidding;//竞价信息

    public MobileBookingFormDubbo getMobileBookingForm() {
        return mobileBookingForm;
    }

    public void setMobileBookingForm(MobileBookingFormDubbo mobileBookingForm) {
        this.mobileBookingForm = mobileBookingForm;
    }

    public List<MobileSubOrderDubbo> getMobileSubOrderList() {
        return mobileSubOrderList;
    }

    public void setMobileSubOrderList(List<MobileSubOrderDubbo> mobileSubOrderList) {
        this.mobileSubOrderList = mobileSubOrderList;
    }

    public MobileFleetBiddingDubbo getMobileFleetBidding() {
        return mobileFleetBidding;
    }

    public void setMobileFleetBidding(MobileFleetBiddingDubbo mobileFleetBidding) {
        this.mobileFleetBidding = mobileFleetBidding;
    }
}
