package com.gistandard.transport.quote.system.common.define;

/**
 * Created by shenzhijun on 2016/3/1.
 */
public enum QuoteTypeDefine {
    //这个字段针对条目类型中，类型是运输的。（1、整车，2、零担，3、按重量分段，4、按公里，5、按公里分段）
    ZC("整车",1) , LD("零担",2),ZLFD("按重量分段",3),GL("按公里",4),
    GLFD("按公里分段",5),ZDY("自定义",6),AP("按件",7),KD("按重量",8),
    ZS("专送", 9);

    // 成员变量
    private String name;
    private int value;

    // 构造方法
    private QuoteTypeDefine(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (QuoteTypeDefine c : QuoteTypeDefine.values()) {
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
