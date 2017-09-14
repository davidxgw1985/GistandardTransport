package com.gistandard.transport.base.exception;

import com.gistandard.transport.base.define.MobileStationRetCode;

/**
 * @author kongxm
 * @ClassName MobileStationBizException
 * @Description MobileStationAPP自定义业务异常
 * @Version 1.0
 * @Date 2016/1/27
 */
public class MobileStationBizException extends Exception {

    private int exCode;

    private String exMsg ;

    public MobileStationBizException() {
        super();
    }

    public MobileStationBizException(String message) {
        super(message, null, false, false);
    }

    public MobileStationBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public MobileStationBizException(MobileStationRetCode mobileStationRetCode){
        super(mobileStationRetCode.getName(), null, false, false);
        this.exCode = mobileStationRetCode.getValue();
        this.exMsg = mobileStationRetCode.getName();
    }

    public MobileStationBizException(MobileStationRetCode mobileStationRetCode, Exception e){
        super(e);
        this.exCode = mobileStationRetCode.getValue();
        this.exMsg = mobileStationRetCode.getName();
    }

    public MobileStationBizException(Throwable cause) {
        super(cause);
    }

    protected MobileStationBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getExCode() {
        return exCode;
    }

    public void setExCode(int exCode) {
        this.exCode = exCode;
    }

    public String getExMsg() {
        return exMsg;
    }

    public void setExMsg(String exMsg) {
        this.exMsg = exMsg;
    }
}
