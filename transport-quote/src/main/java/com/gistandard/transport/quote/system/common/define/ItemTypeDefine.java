package com.gistandard.transport.quote.system.common.define;

/**
 * Created by shenzhijun on 2016/3/1.
 */
public enum  ItemTypeDefine {
    LOGISTICS("物流",0) , TRANSPORT("运输",1),EXPRESS("快递",2);

    // 成员变量
    private String name;
    private Integer value;

    // 构造方法
    private ItemTypeDefine(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(Integer value) {
        for (ItemTypeDefine c : ItemTypeDefine.values()) {
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
