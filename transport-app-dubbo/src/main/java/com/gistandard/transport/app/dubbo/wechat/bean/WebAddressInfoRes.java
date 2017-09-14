package com.gistandard.transport.app.dubbo.wechat.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;
import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

/**
 * Created by m on 2017/2/7.
 */
public class WebAddressInfoRes extends MsDubboResBean {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WebAddressInfoRes(MsDubboReqBean dubboReqBean) {
        super(dubboReqBean);
    }
}
