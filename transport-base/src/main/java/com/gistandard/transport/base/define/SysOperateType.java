package com.gistandard.transport.base.define;

/**
 * @author yujie
 * @ClassName SysOperateType
 * @Description
 * @Version 1.0
 * @Date 2015-09-25
 */
public enum SysOperateType {

    WAIT_AUTHORIZE("待审核", 1), AUTHORIZE_SUCCESS("可用", 2), AUTHORIZE_FAILED("审核未通过", 3) , LOCKED("已禁用", 4), DELETED("已删除", 5);

    private String name;

    private int value;

    private SysOperateType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (SysOperateType c : SysOperateType.values()) {
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
