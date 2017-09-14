package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;

/**
 * Created by m on 2017/2/6.
 */
public class WeChatOrderTraceReq  implements Serializable {
    private String busiBookNo;//订单号

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }
}
