package com.gistandard.transport.order.module.mobilestation.bean.takeout;


import com.gistandard.transport.base.entity.bean.MobileBookingForm;

/**
 * 同城外卖，订单查询，所有使用到的接口
 * Created by zxnui on 9/6/16.
 */
public class TakeOutMobileOrderListRes extends MobileBookingForm {
    private int orderTag;//0无标签 1个人-发货 2发货 3商家-收货 4收货
    private String hscodeNachs;//货物名称
    private String hscodeSpece;//货物code

    public int getOrderTag() {
        return orderTag;
    }

    public void setOrderTag(int orderTag) {
        this.orderTag = orderTag;
    }

    public String getHscodeNachs() {
        return hscodeNachs;
    }

    public void setHscodeNachs(String hscodeNachs) {
        this.hscodeNachs = hscodeNachs;
    }

    public String getHscodeSpece() {
        return hscodeSpece;
    }

    public void setHscodeSpece(String hscodeSpece) {
        this.hscodeSpece = hscodeSpece;
    }
}