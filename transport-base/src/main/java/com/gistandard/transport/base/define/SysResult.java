package com.gistandard.transport.base.define;

/**
 * @author yujie
 * @ClassName SysResult
 * @Description
 * @Version 1.0
 * @Date 2015-07-29
 */
public enum SysResult {
    SUCCESS("成功", 1), FAILED("失败", 2),
    invalidAccessToken("不合法的访问Token",40001),
    invalidRefreshToken("不合法的刷新Token",40002),
    invalidTokenMark("不合法的Token信息",50002);

    // 成员变量
    private String name;
    private int value;

    // 构造方法
     SysResult(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (SysResult c : SysResult.values()) {
            if (c.getValue() == value) {
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
