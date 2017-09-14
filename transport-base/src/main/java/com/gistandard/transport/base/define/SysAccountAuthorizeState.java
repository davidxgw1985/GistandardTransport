package com.gistandard.transport.base.define;

/**
 * @author yujie
 * @ClassName SysAccountState
 * @Description
 * @Version 1.0
 * @Date 2015-09-01
 */
public enum SysAccountAuthorizeState {

    AUTHORIZE_SUCCESS("已授权", 1),
    RELEASE_AUTHORIZE("已解除授权", 2) ,
    NO_AUTHORIZE("未授权", 3);

    private String name;

    private int value;

    private SysAccountAuthorizeState(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (SysAccountAuthorizeState c : SysAccountAuthorizeState.values()) {
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
