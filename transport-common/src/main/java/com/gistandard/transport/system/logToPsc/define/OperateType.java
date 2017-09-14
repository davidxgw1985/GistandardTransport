package com.gistandard.transport.system.logToPsc.define;

/**
 * Created by m on 2016/2/4.
 */
public enum OperateType {

    // 通用操作
    LOGIN(1,"登录"),
    SIGN_OUT(2,"退出"),
    UPDATE_PASSWORD(3,"修改密码"),

    // 运输平台操作
    SAVE_LOGISTICS_ORDER(1001,"提交物流订单"),
    CANCEL_LOGISTICS_ORDER(1002,"取消物流订单"),
    CONFIRM_LOGISTICS_ORDER(1003,"确认物流订单"),
    SAVE_LOGISTICS_BUSIDATA(1004,"录入业务数据"),
    DELETE_LOGISTICS_BUSIDATA(1005,"删除业务数据"),

    //嘀讯操作
    DDWORD_ADDRESS_QUERY(150031,"地址簿查询"),
    DDWORD_ADDRESS_ADD(150032,"地址簿新增"),
    DDWORD_ORDER_PLACE(150033,"订单管理-下单"),
    DDWORD_ORDER_QUERY(150034,"订单管理-查询订单"),
    DDWORD_ORDER_TRACK(150035,"订单管理-运送历史"),
    DDWORD_ACCOUNT_TELEPHONE(150036,"通过收件人手机号查出帐号"),
    DDWORD_BROADCAST_ORDER_REV(150037,"广播单接单"),
    DDWORD_BROADCAST_ORDER_CACEL(150038,"广播单取消下单"),
    DDWORD_ORDER_FOLLOW(150039,"订单关注/取消"),
    DDWORD_STATION_QUERY(150040,"查询服务商"),
    DDWORD_QUOTE_QUERY(150041,"查询报价"),
    DDWORD_QUOTE_ITEM_QUERY(150042,"根据类别类型查询条目"),
    DDWORD_DICTIONARY_QUERY(150043,"查询数据字典"),
    DDWORD_CALCFORTEMPPRICE_QUERY(150044,"估算接口"),
    DDWORD_PLATFORM_QUERY(150045,"平台运输/快递预估总价"),
    DDWORD_GPSTOKEN_GET(150046,"获取GPSToken"),

    // 司机APP操作
    DRIVER_APP_VEHICLE_ORDER(2001,"发布报价单"),
    DRIVER_APP_CANCEL_VEHICLE_ORDER(2002,"取消报价单"),
    DRIVER_APP_CONFIRM_VEHICLE_ORDER(2003,"确认报价单"),
    DRIVER_APP_ACCEPT_ASSIGN_ORDER(2004,"接收指派单"),
    DRIVER_APP_ACCEPT_ORDER(2005,"接单"),
    DRIVER_APP_CANCEL_ORDER(2006,"取消接单"),
    DRIVER_APP_SCHEDULE_GOODS(2007,"排货"),
    DRIVER_APP_CANCEL_SCHEDULE_GOODS(2008,"取消排货"),
    DRIVER_APP_DEPART(2009,"发车"),
    DRIVER_APP_DELIVERY(2010,"送达确认"),
    DRIVER_APP_TRANSPORTCALC(2011,"结算");

    // 成员变量
    private Integer opType;//操作类型

    private String opName;//操作名称

    private OperateType(Integer opType,String opName){
        this.opType = opType;
        this.opName = opName;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }
}
