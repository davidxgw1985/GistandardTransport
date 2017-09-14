package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: AssignOrderResBean
 * @Date: 2017/2/7
 * @Description: 单个指派返回对象
 */
public class AssignOrderResBean implements Serializable{
    private static final long serialVersionUID = 2170356937427819683L;
    private String busiBookNo;//订单号
    private Integer retCode = 1;//指派状态 1成功，0失败
    private String retMsg = "指派成功！";//提示信息

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
