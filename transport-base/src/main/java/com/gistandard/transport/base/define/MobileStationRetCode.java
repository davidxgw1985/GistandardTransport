package com.gistandard.transport.base.define;

/**
 * Created by yujie on 2017/3/8.
 */
public enum MobileStationRetCode {

    UNKNOWN_RESULT(0, "未知结果"),
    SUCCESS(1, "操作成功"),
    VERSION_UPDATE_ERROR(100, "app版本升级"),
    VERSION_TEST_ERROR(1003, "当前不能使用压测版本"),
    //kpp 模块 start
    KPP_ROLE_NOT_SUPPORT(1000, "不支持此模块，请先升级为商户"),
    KPP_ROLE_NOT_IN_COMPANY(1001, "未在企业中开通角色，请联系所在企业管理员"),
    KPP_REPEAT_ADD(1002, "重复添加KPP"),
    //地址簿模块 start
    QUERY_ADDRESS_ERROR_INVALID_ACCOUNT(2001, "查询地址错误，无效的帐号ID"),
    //订单模块
    PRICE_QUERY_ERROR(3001, "报价查询错误");

    // 成员变量
    private String name;
    private int value;

    // 构造方法
    private MobileStationRetCode(int value,String name) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (MobileStationRetCode c : MobileStationRetCode.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }

        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
