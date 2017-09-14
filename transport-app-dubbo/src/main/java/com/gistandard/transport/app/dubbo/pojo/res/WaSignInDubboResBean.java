package com.gistandard.transport.app.dubbo.pojo.res;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

import java.io.Serializable;

/**
 * 洼站扫描二维码签到dubbo返回
 * @author 员伟
 */
public class WaSignInDubboResBean extends MsDubboResBean implements Serializable {


    private static final long serialVersionUID = 7852213777839621273L;


    public WaSignInDubboResBean(MsDubboReqBean msDubboReqBean) {
        super(msDubboReqBean);
    }

    public WaSignInDubboResBean() {
        super();
    }

    private Object data;//返回内容

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
