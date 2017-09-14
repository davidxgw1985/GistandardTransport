package com.gistandard.transport.quote.system.common.define;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by shenzhijun on 2016/3/5.
 */
public enum ServiceTypeDefine {
    BASE("基本报价", "0"), ACTUAL("实报实销", "1");

    // 成员变量
    private String name;
    private String value;

    // 构造方法
    private ServiceTypeDefine(String name, String value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(String value) {
        if(StringUtils.isEmpty(value)){
            return null;
        }
        for (ServiceTypeDefine c : ServiceTypeDefine.values()) {
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
