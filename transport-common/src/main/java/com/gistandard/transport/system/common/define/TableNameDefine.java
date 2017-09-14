package com.gistandard.transport.system.common.define;

import java.util.HashMap;

/**
 * Created by m on 2016/3/7.
 */
public enum TableNameDefine {
    MOBILE_BOOKING_FORM("MOBILE_BOOKING_FORM", 1),
    BOOKING_FORM("BOOKING_FORM", 2),
    SCHEDU_GOODS("SCHEDU_GOODS", 3);

    public static final HashMap<Integer, Object> TABLE_NAME_MAP = new HashMap<Integer, Object>();

    static {
        TABLE_NAME_MAP.put(MOBILE_BOOKING_FORM.getValue(), MOBILE_BOOKING_FORM.getName());
        TABLE_NAME_MAP.put(BOOKING_FORM.getValue(), BOOKING_FORM.getName());
        TABLE_NAME_MAP.put(SCHEDU_GOODS.getValue(), SCHEDU_GOODS.getName());

    }

    TableNameDefine(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (TableNameDefine c : TableNameDefine.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }

        return null;
    }

    private String name;

    private int value;

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
