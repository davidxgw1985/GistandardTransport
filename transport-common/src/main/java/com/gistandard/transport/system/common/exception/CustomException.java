package com.gistandard.transport.system.common.exception;

/**
 * @author yujie
 * @ClassName CustomException
 * @Description
 * @Version 1.0
 * @Date 2016-01-08
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 5732896468804090065L;
    private int exCode;

    private String exMsg ;

    public CustomException(String message) {
        super(message);
        this.exMsg = message;
    }

    public CustomException(int code, String message) {
        super(message);
        this.exCode = code;
        this.exMsg = message;
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
