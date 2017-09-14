package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;

/**
 * Created by yujie on 2016/10/6.
 */
public class GetGoodsInfoResult extends AppBaseResult {

    public GetGoodsInfoResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private GoodsStockInfo data;

    public GoodsStockInfo getData() {
        return data;
    }

    public void setData(GoodsStockInfo data) {
        this.data = data;
    }
}
