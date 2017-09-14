package com.gistandard.transport.quote.system.common.define;

/**
 * Created by shenzhijun on 2016/3/23.
 */
public enum  ItemCategory {
    PRODUCT("产品",1) , SERVICE("服务",2), YKJ("一口价",3);
    // 成员变量
    private String itemName;
    private int code;

    // 构造方法
    private ItemCategory(String itemName, int code) {
        this.itemName = itemName;
        this.code = code;
    }

    // 普通方法
    public static String getName(int code) {
        for (ItemCategory c : ItemCategory.values()) {
            if (c.getCode() == code) {
                return c.itemName;
            }
        }

        return null;
    }

    public static ItemCategory getItemCategory(int value) {
        for (ItemCategory c : ItemCategory.values()) {
            if (c.getCode() == value) {
                return c;
            }
        }
        return null;
    }

    // get set 方法

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
