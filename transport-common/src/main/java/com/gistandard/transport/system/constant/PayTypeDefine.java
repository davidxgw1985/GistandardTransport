package com.gistandard.transport.system.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by m on 2016/6/15.
 */
public enum PayTypeDefine {
    TO_PAY("到付", "1"),
    ONLINE_PAYMENT("在线支付", "2"),
    CASH_PAYMENT("现金支付", "3");

    private String name;
    private String value;

    PayTypeDefine(String name, String value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(String value) {
        if(StringUtils.isEmpty(value)){
            return null;
        }
        for (PayTypeDefine c : PayTypeDefine.values()) {
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
