package com.gistandard.transport.system.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by m on 2016/6/15.
 */
public enum BookingFormIsJsDefine {
    TO_CALC("待结算", "0"),
    CALC_SUCCESS("已结算", "1"),
    BALANCE_SUCCESS("已对账", "2"),
    PAY_SUCCESS("已支付", "3"),
    PAY_FAIL("支付失败", "4");

    private String name;
    private String value;

    BookingFormIsJsDefine(String name, String value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(String value) {
        if(StringUtils.isEmpty(value)){
            return null;
        }
        for (BookingFormIsJsDefine c : BookingFormIsJsDefine.values()) {
            if (c.getValue().equals(value)) {
                return c.name;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
