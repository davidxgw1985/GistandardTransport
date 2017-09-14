package com.gistandard.transport.system.common.define;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yujie on 2016/7/7.
 */
public enum PlatformDefineEnum {

    TRANSPORT("物流开放平台", 1), WORKSPACE("工作任务空间", 2), DDWORD("嘀讯", 3), SUPCOUNT("通用账户", 4);

    public static final List<HashMap<String, Object>> PLATFORM_DEFINE_LIST = new ArrayList<>();

    static {
        HashMap<String, Object> transportMap = new HashMap<>();
        transportMap.put("value", TRANSPORT.value);
        transportMap.put("name", TRANSPORT.name);
        PLATFORM_DEFINE_LIST.add(transportMap);
        HashMap<String, Object> workspaceMap = new HashMap<>();
        workspaceMap.put("value", WORKSPACE.value);
        workspaceMap.put("name", WORKSPACE.name);
        PLATFORM_DEFINE_LIST.add(workspaceMap);
        HashMap<String, Object> ddwordMap = new HashMap<>();
        ddwordMap.put("value", DDWORD.value);
        ddwordMap.put("name", DDWORD.name);
        PLATFORM_DEFINE_LIST.add(ddwordMap);
        HashMap<String, Object> supcountMap = new HashMap<>();
        supcountMap.put("value", SUPCOUNT.value);
        supcountMap.put("name", SUPCOUNT.name);
        PLATFORM_DEFINE_LIST.add(supcountMap);
    }

    // 成员变量
    private String name;
    private int value;

    // 构造方法
    private PlatformDefineEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getNameByValue(int value) {
        for (PlatformDefineEnum c : PlatformDefineEnum.values()) {
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
