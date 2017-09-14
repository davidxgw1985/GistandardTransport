package com.gistandard.transport.order.webservice.server.mobilestation.bean;

import com.gistandard.transport.base.define.SystemDefine;


/**
 * @author yjf
 * @ClassName MobileStationCancelOrderResBean
 * @Description 移动station取消结果Bean
 * @Version 1.0
 * @Date 2016-03-23
 */
public class MobileStationCancelOrderResBean {
    private int retCode;//返回代码  1为成功 其它为失败
    private String retMsg;//返回信息

    public MobileStationCancelOrderResBean(){
        retCode = SystemDefine.SUCCESS;
        retMsg = "Success";
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
}
