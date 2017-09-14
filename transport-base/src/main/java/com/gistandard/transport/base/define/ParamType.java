package com.gistandard.transport.base.define;

/**
 * Created by m on 2016/1/22.
 */
public enum ParamType {
        STR("字符串", 1),
        INT("整形", 2),
        FLOAT("浮点型", 3),
        JSON("json字符串", 4);

    ParamType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 成员变量
    private String name;
    private int value;

    public static String getName(int value) {
        for (ParamType p : ParamType.values()) {
            if (p.getValue() == value) {
                return p.name;
            }
        }
        return null;
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
