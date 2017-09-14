package com.gistandard.transport.base.bean.app;

import com.gistandard.transport.base.define.SystemDefine;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: BaseResBean
 * @Date: 2015/12/24
 * @Description: 返回结果基类
 */
public class BaseResBean implements Serializable {
    private static final long serialVersionUID = 1512752703165247890L;

    private int retCode;//返回代码  1为成功 其它为失败
    private String retMsg;//返回信息
    private long reqId;//请求ReqId
    private Object data;//返回内容

    public BaseResBean() {
        retCode = SystemDefine.SUCCESS;
        retMsg = "Success";
    }

    public BaseResBean(BaseReqBean reqBean) {
        retCode = SystemDefine.SUCCESS;
        retMsg = "Success";
        if (reqBean != null) {
            reqId = reqBean.getReqId();
        }
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
