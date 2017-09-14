package com.gistandard.transport.system.common.define;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by m on 2016/5/31.
 */
public enum OrderDefine {
    //订单状态
    ORDER_STATUS_NOORDER("待接单", "0"),
    ORDER_STATUS_CANCLE("已取消", "-1"),
    ORDER_STATUS_FROZEN("冻结", "-4"),
    ORDER_STATUS_HAVEORDER("已接单", "1"),
    ORDER_STATUS_SENDIN("运送中", "2"),
    ORDER_STATUS_RECEIVE("已完成", "6"),//客户确认收货
    ORDER_STATUS_FINISH("已完成", "5"),//已签收
    ORDER_STATUS_GIVEUP("已失败", "-999");

    private String name;
    private String value;

    OrderDefine(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static String getName(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (OrderDefine orderDefine : OrderDefine.values()) {
            if (orderDefine.value.equals(value)) {
                return orderDefine.name;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
