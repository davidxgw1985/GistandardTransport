package com.gistandard.transport.base.exception;

import com.gistandard.transport.base.define.MobileStationRetCode;

/**
 * Created by yjf on 2016/9/30.
 * 客户下单APP自定义业务异常
  */
public class CustomerBizException extends Exception{

    private MobileStationRetCode retCode;

    public CustomerBizException() {
        super();
    }

    public CustomerBizException(MobileStationRetCode retCode) {
        super(retCode.getName());
    }

    public CustomerBizException(String message) {
        super(message);
    }

    public CustomerBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerBizException(Throwable cause) {
        super(cause);
    }

    protected CustomerBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
