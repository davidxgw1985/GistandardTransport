package com.gistandard.transport.app.dubbo.pojo.res;


import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

import java.io.Serializable;

/**
 * Created by yujie on 2017/3/1.
 */
public class MsDubboResBean implements Serializable {
    private static final long serialVersionUID = -1842827004867806928L;

    public MsDubboResBean() {
        this.retCode = 1;
    }

    public MsDubboResBean(MsDubboReqBean dubboReqBean) {
        this.retCode = 1;
        if (dubboReqBean != null) {
            this.reqId = dubboReqBean.getReqId();
        }
    }

    private int retCode;// 返回代码 1为成功 其它为失败

    private String retMsg = "成功";// 返回信息

    private long reqId;// 请求ReqId

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public long getReqId() {
        return reqId;
    }

    public void setReqId(long reqId) {
        this.reqId = reqId;
    }
}
