package com.gistandard.transport.app.dubbo.fleet.bean;

import java.io.Serializable;

/**
 * @author 员伟
 */
public class FleetDistributeResBean implements Serializable {

    private static final long serialVersionUID = -4064300150377235143L;

    private String busiBookNo;//订单号
    private Integer retCode = 1;//分发状态 1成功，0失败
    private String retMsg = "分发成功！";//提示信息

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
