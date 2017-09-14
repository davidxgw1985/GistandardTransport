package com.gistandard.transport.order.module.genno.define;

import java.text.DecimalFormat;

/**
 * Created by yujie on 2016/9/9.
 */
public class GenerateOrderNumberDefine {
    //分区总数10w
    public static final int PARTITION_TOTAL = 100000;

    //序列总数1w
    public static final int SEQUENCE_NUMBER_TOTAL = 10000;

    public static final String ORDER_NUMBER_INDEX_PREFIX = "ORDER_NUMBER_INDEX_";

    public static final String ORDER_NUMBER_SEQ_PREFIX = "ORDER_NUMBER_SEQ_";

    public static DecimalFormat partitionFormat = new DecimalFormat("00000");

    public static DecimalFormat orderNumFormat = new DecimalFormat("0000");

    public static String getOrderNumberIndexKey(Integer cityId){
        return ORDER_NUMBER_INDEX_PREFIX + cityId;
    }

    public static String getOrderNumberSeqKey(Integer cityId, Integer partitionNo){
        return ORDER_NUMBER_SEQ_PREFIX + cityId + "_" + partitionNo;
    }

    public static String genOrderNumner(Integer cityId, Integer partitionNo, Integer orderNumIndex){
        StringBuilder builder = new StringBuilder();
        builder.append(cityId)
                .append(partitionFormat.format(partitionNo))
                .append(orderNumFormat.format(orderNumIndex));
        return builder.toString();
    }
}
