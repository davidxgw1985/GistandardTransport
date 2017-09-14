package com.gistandard.transport.system.common.exception;

/**
 * @author yujie
 * @ClassName ExceptionStatus
 * @Description
 * @Version 1.0
 * @Date 2016-01-28
 */
public enum  ExceptionStatus {

    NOT_SOLVE("未解决", 1), SOLVED("已解决", 2), DELETED("未知异常", 3);

    // 成员变量
    private String name;
    private int value;

    // 构造方法
    ExceptionStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (ExceptionStatus c : ExceptionStatus.values()) {
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
