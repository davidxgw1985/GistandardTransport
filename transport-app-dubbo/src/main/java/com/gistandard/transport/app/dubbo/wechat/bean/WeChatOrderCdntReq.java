package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;

/**
 * 订单坐标请求
 * @author 员伟
 * @date 2017-09-07
 */
public class WeChatOrderCdntReq implements Serializable {


    private static final long serialVersionUID = 2111857642138002842L;

    private String busiBookNo;// 订单号

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }
}
