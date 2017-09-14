package com.gistandard.transport.system.center.pay.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

/**
 * Created by m on 2016/9/1.
 */
public class SafePayInfoReq extends AppBaseRequest implements ValidTokenMark{
    private String busiBookNo;//订单号

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }
}
