package com.gistandard.transport.quote.system.common.define;

/**
 * Created by shenzhijun on 2016/2/25.
 */
public enum QuoteStatus {
    ENABLE("可用", 1), DISABLE("禁用", 2), DEL("删除", 3);

    // 成员变量
    private String name;
    private int value;

    // 构造方法
    private QuoteStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (QuoteStatus c : QuoteStatus.values()) {
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
