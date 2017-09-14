package com.gistandard.transport.quote.system.common.define;

/**
 * Created by shenzhijun on 2016/8/10.
 */
public enum PublicState {
    PUBLIC("公开", "1"), PRIVATE("私密","2"),
    PUBLIC_PRIVATE_TO_ME("公开及对我私密", "3"),PUBLIC_PRIVATE("公开及私密","4");
    // 成员变量
    private String name;
    private String value;

    // 构造方法
    PublicState(String name, String value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (PublicState c : PublicState.values()) {
            if (c.getValue().equals(value)) {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
