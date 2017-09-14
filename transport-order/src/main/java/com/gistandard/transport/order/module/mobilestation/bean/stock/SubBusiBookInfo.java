package com.gistandard.transport.order.module.mobilestation.bean.stock;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: SubBusiBookInfo
 * @Date: 2016/4/26
 * @Description:
 */
public class SubBusiBookInfo implements Serializable{
    private static final long serialVersionUID = 2899896286907449716L;

    private String busiBookNo;//订单号

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

}
