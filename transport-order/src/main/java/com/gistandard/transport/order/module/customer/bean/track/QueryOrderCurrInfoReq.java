package com.gistandard.transport.order.module.customer.bean.track;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: QueryOrderCurrInfoReq
 * @Date: 2017/8/25
 * @Description: 根据订单号查询订单当前位置信息
 */
public class QueryOrderCurrInfoReq extends AppBaseRequest{
    private static final long serialVersionUID = 6765907528287463735L;
    private String busiBookNo; //订单号

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }
}
