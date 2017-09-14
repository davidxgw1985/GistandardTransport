package com.gistandard.transport.base.define;

/**
 * Created by yujie on 2016/7/8.
 */
public enum RegionTypeEnum {

    COUNTRY("国家", 1), PROVINCE("省份", 2), CITY("城市", 3), COUNTY("区县", 4), TOWN("乡镇街道", 4);

    private String name;
    private int value;

    private RegionTypeEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getName(int value) {
        for (RegionTypeEnum c : RegionTypeEnum.values()) {
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
