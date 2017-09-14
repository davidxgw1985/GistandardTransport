package com.gistandard.transport.system.common.define;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yujie on 2016/7/8.
 */
public enum AccountTypeClassEnum {

    PERSON("人员", 1), COMPANY("企业", 2);

    public static final List<HashMap<String, Object>> TYPE_DEFINE_LIST = new ArrayList<>();

    static {
        HashMap<String, Object> personMap = new HashMap<>();
        personMap.put("value", PERSON.value);
        personMap.put("name", PERSON.name);
        TYPE_DEFINE_LIST.add(personMap);
        HashMap<String, Object> companyMap = new HashMap<>();
        companyMap.put("value", COMPANY.value);
        companyMap.put("name", COMPANY.name);
        TYPE_DEFINE_LIST.add(companyMap);
    }

    // 成员变量
    private String name;
    private int value;

    // 构造方法
    private AccountTypeClassEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getNameByValue(int value) {
        for (AccountTypeClassEnum c : AccountTypeClassEnum.values()) {
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
