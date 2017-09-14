package com.gistandard.transport.order.module.customer.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

/**
 * Created by m on 2016/7/13.
 */
public class GoodsTypeBean  extends AppBaseRequest implements ValidTokenMark{
    private String itemCode;//产品类别
    private String goodsTypes;//货物类型，多个的话用','分隔

    public String getItemCode() {
        return itemCode;
    }

    public GoodsTypeBean setItemCode(String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public String getGoodsTypes() {
        return goodsTypes;
    }

    public GoodsTypeBean setGoodsTypes(String goodsTypes) {
        this.goodsTypes = goodsTypes;
        return this;
    }
}
