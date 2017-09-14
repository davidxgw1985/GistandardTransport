package com.gistandard.transport.system.common.define;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by m on 2016/5/27.
 */
public enum WayBillStatusDefine {
    HUB_WAIT_STORAGE("待入库", "1"),
    HUB_CANCEL_RECEIPT("取消收货", "2"),
    HUB_RECEIVE_GOODS("正在收货", "3"),
    HUB_IN_STORAGE("已入库", "4"),
    HUB_WAIT_OUT_STORAGE("待出库", "5"),
    HUB_CANCLE_OUT_STORAGE("取消出库", "6"),
    HUB_OUT_OF_STORAGE("正在出库", "7"),
    HUB_HAS_OUT_STORAGE("已出库", "8"),
    HUB_HAS_DISCHARGED("已排货", "9"),
    HUB_HAS_SENT_CAR("已派车", "10"),
    HUB_LOADING("正在装车", "11"),
    HUB_LOADED("已装车", "12"),
    MS_AGREE_O("接单成功", "13"),
    MS_AGREE_CAR("接受了站点指派", "14"),
    MS_DEPART_POP("已揽件", "15"),
    MS_DEPART_HUB("已发车", "16"),
    MS_DEPART_POD("正在派件", "17"),
    MS_ARRIVE_HUB("已送达站点", "18"),
    MS_ARRIVE_POD("送达，客户已签收", "19"),
    MS_GIVEUP("放弃订单", "20"),
    MS_REJECT("拒绝订单", "21"),
    MS_FAIL("派送失败", "22"),
    MS_STOCKOUT_MI("咪站已分拣出库", "39"),
    ORDER("已下单", "23"),
    CANCLE_ORDER("取消订单", "24"),
    LOGIC_ORDER("已下单", "25"),
    CANCLE_LOGIC_ORDER("取消订单", "26"),
    MERCH_RECIVR_O("已接单", "27"),
    MERCH_RECIVR_I("已接单", "28"),
    MERCH_RECIVR_HUB("已接单", "29"),
    MERCH_SIGNED_O("已签派", "30"),
    MERCH_SIGNED_I("已签派", "31"),
    MERCH_SIGNED_HUB("已签派", "32"),
    MERCH_ARRANGED_O("已安排", "33"),
    MERCH_ARRANGED_HUB("已安排", "34"),
    SIGN_ARRANGED("已安排", "35"),
    SIGN_CONTINUT_SIGN("继续签派", "36"),
    SIGN_SIGNED("已签派", "37"),
    TAKE_SUCCESS("取件成功", "38"),
    DELETE_ORDER("删除订单", "100"),
    FINISH_ORDER("确认送达", "101"),
    EXCEPTION_RECE("异常收件", "102");

    // 成员变量
    private String name;
    private String value;

    // 构造方法
    private WayBillStatusDefine(String name, String value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(String value) {
        if(StringUtils.isEmpty(value)){
            return null;
        }
        for (WayBillStatusDefine c : WayBillStatusDefine.values()) {
            if (c.getValue().equals(value)) {
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

    public String getValue() {
        return value;
    }

    public int getIntValue() {
        return NumberUtils.toInt(value);
    }

    public void setValue(String value) {
        this.value = value;
    }
}
