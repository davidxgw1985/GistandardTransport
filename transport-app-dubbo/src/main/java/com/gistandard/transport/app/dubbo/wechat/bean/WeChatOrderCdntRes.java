package com.gistandard.transport.app.dubbo.wechat.bean;

import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

/**
 * 订单坐标信息返回
 * @author 员伟
 * @date 2017-09-07
 */
public class WeChatOrderCdntRes extends MsDubboResBean {

    private WeChatOrderCdntBean data;

    public WeChatOrderCdntBean getData() {
        return data;
    }

    public void setData(WeChatOrderCdntBean data) {
        this.data = data;
    }
}
