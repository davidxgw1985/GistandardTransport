package com.gistandard.transport.base.bean;

/**
 * @author yujie
 * @ClassName ExceptionMessageBean
 * @Description
 * @Version 1.0
 * @Date 2015-06-29
 */
public class ExceptionMessageBean {

    private String exceptionName;

    private String cause;

    private String stackTrace;

    private String message;

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
