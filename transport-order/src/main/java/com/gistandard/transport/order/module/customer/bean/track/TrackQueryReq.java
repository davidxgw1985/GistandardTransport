package com.gistandard.transport.order.module.customer.bean.track;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.base.bean.app.ValidTokenMark;

import java.io.Serializable;

/**
 * @ClassName TrackQueryReq
 * @Description 运单跟踪查询Bean
 * @Version 1.0
 * @Date 2016/3/31
 */
public class TrackQueryReq extends AppBaseRequest implements Serializable,ValidTokenMark {
    private static final long serialVersionUID = -6951490005787222393L;
    private String busiBookNo; //订单的流水号

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }
}
