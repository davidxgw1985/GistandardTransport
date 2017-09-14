package com.gistandard.transport.app.dubbo.wechat.bean;

import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

/**
 * @author: xgw
 * @ClassName: WeChatPlatFormOutRes
 * @Date: 2017/8/14
 * @Description: 比较操作返回Bean
 */
public class WeChatPlatFormOutRes extends MsDubboResBean{
    private static final long serialVersionUID = -4753016637321620075L;
    private WeChatPlatFormOutModWrap data;

    public WeChatPlatFormOutModWrap getData() {
        return data;
    }

    public void setData(WeChatPlatFormOutModWrap data) {
        this.data = data;
    }
}
