package com.gistandard.transport.base.define;

/**
 * Created by m on 2016/9/30.
 */
public enum OrderState {

    WAITRE_CEIVING("待接单",0),
    ORDER_ACCEPTED ("已接单",1),
    HUB_RECEIVED("运送中",2),
    SHIPPED("运送中",3),
    EXCEPTION_RECE("异常收件",4),
    FINISHED("已送达",5),
    CANCLE_ORDER("已取消",-1),
    CANCLE_ORDER_WAITRE_AFFIRM("取消待确认",-2),
    INVALID_ORDER("作废",-3),
    CANCLE_ORDER_FROZEN("冻结",-4),
    ALREADY_SAVE("订单已保存", -9),
    WAITING_QUOTE("待报价",12),//同城运输
    WAITING_CONFIRM("待确认",13),//同城运输 确认报价
    INVALID_TRANSPORT("已失效(订单)",-12),//同城运输 订单已失效
    INVALID_QUOTE("已失效(报价)",-13);//同城运输  报价失效

    private String name;
    private Integer value;

    public  static OrderState getOrderState(Integer code){

        for (OrderState c : OrderState.values()) {
            if (c.getValue().intValue() == code.intValue()) {
                return c;
            }
        }
        return null;
    }
    OrderState(String name,Integer value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }


}

