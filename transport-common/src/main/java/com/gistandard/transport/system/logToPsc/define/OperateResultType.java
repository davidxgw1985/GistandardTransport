package com.gistandard.transport.system.logToPsc.define;

/**
 * Created by m on 2016/2/4.
 */
public enum OperateResultType {

    FAILED(0, "失败"),
    SUCCESS(1, "成功"),
    UNKNOWN(2, "结果未知");

    // 成员变量
    private Integer value;//操作类型

    private String name;//操作名称

    private OperateResultType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
