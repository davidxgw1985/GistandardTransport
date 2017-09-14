package com.gistandard.transport.base.define;

import java.util.ArrayList;
import java.util.List;

/**
 * 同城运输增值服务枚举类
 * @author 员伟
 * @createDate 2017-06-15 13:56:22
 */
public enum ValueAddEnum {

    PICK_UP_CARRY_SERVICE("提货搬运","THBY"),
    DELIVERY_CARRY_SERVICE("送货搬运","SHBY"),
    WITH_PALLETS_SERVICE("带栈板","DZB"),
    PACKAGE_SERVICE("包装","BZ");

    private String valueAddName;
    private String valueAddCode;

    ValueAddEnum(String valueAddName, String valueAddCode) {
        this.valueAddName = valueAddName;
        this.valueAddCode = valueAddCode;
    }

    public static List<String> getValueAddNameList(){
        List<String> valueAddNameList = new ArrayList<>();
        valueAddNameList.add(PICK_UP_CARRY_SERVICE.getValueAddName());
        valueAddNameList.add(DELIVERY_CARRY_SERVICE.getValueAddName());
        valueAddNameList.add(WITH_PALLETS_SERVICE.getValueAddName());
        valueAddNameList.add(PACKAGE_SERVICE.getValueAddName());
        return valueAddNameList;
    }

    public static List<String> getValueAddCodeList(){
        List<String> valueAddCodeList = new ArrayList<>();
        valueAddCodeList.add(PICK_UP_CARRY_SERVICE.getValueAddCode());
        valueAddCodeList.add(DELIVERY_CARRY_SERVICE.getValueAddCode());
        valueAddCodeList.add(WITH_PALLETS_SERVICE.getValueAddCode());
        valueAddCodeList.add(PACKAGE_SERVICE.getValueAddCode());
        return valueAddCodeList;
    }

    public String getValueAddName() {
        return valueAddName;
    }

    public void setValueAddName(String valueAddName) {
        this.valueAddName = valueAddName;
    }

    public String getValueAddCode() {
        return valueAddCode;
    }

    public void setValueAddCode(String valueAddCode) {
        this.valueAddCode = valueAddCode;
    }
}
