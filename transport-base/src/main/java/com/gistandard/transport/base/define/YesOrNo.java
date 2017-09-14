package com.gistandard.transport.base.define;


/**
 * @author sjl
 * @ClassName YesOrNo
 * @Description
 * @Version 1.0
 * @Date 2016-01-12
 */
public enum YesOrNo {

    YES("是", 1, true),

    NO("否", 0, false);

    // 成员变量
    private String name;

    private int value;

    private boolean boolValue;

    // 构造方法
    private YesOrNo(String name, int value, boolean boolValue) {
        this.name = name;
        this.value = value;
        this.boolValue = boolValue;
    }

    // 普通方法
    public static String getName(int value) {
        for (YesOrNo c : YesOrNo.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }

        return null;
    }

    // 普通方法
    public static String getName(boolean boolValue) {
        for (YesOrNo c : YesOrNo.values()) {
            if (c.isBoolValue() == boolValue) {
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

    public boolean isBoolValue() {
        return boolValue;
    }

    public void setBoolValue(boolean boolValue) {
        this.boolValue = boolValue;
    }
}
