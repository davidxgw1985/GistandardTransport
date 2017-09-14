package com.gistandard.transport.quote.system.common.define;

/**
 * Created by shenzhijun on 2016/3/11.
 */
public enum  BelongType {
    CUSTOMER("商户",1) , USERINFO("司机",2);

    // 成员变量
    private String name;
    private int value;

    // 构造方法
    private BelongType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (BelongType c : BelongType.values()) {
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
