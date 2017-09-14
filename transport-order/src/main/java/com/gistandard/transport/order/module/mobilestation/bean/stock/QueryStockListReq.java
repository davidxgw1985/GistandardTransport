package com.gistandard.transport.order.module.mobilestation.bean.stock;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: QueryCurrentStockReq
 * @Date: 2016/2/1
 * @Description: 查询当前库存请求Bean
 */
public class QueryStockListReq extends AppBaseRequest {
    private static final long serialVersionUID = -3290558893196300312L;

    private String busiBookNo;//订单号

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }
}
