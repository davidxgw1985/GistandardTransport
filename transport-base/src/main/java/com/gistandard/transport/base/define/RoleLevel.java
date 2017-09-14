package com.gistandard.transport.base.define;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yujie
 * @ClassName State
 * @Description
 * @Version 1.0
 * @Date 2015-06-30
 */
public enum RoleLevel {
    Level1("一级", 1), Level2("二级", 2), Level3("三级", 3), Level4("四级", 4);

    public static final List<HashMap<String, Object>> ROLE_LEVEL_LIST = new ArrayList<HashMap<String, Object>>();

    // 成员变量
    private String name;
    private int value;

    // 构造方法
    private RoleLevel(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (RoleLevel c : RoleLevel.values()) {
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
