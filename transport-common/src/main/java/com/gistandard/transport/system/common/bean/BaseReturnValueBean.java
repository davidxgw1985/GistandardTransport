package com.gistandard.transport.system.common.bean;

import java.util.List;

/**
 * Created by yujie on 2016/8/4.
 */
public class BaseReturnValueBean {

    private boolean state;

    private int code;

    private String message;

    private List<ErrorMessageBean> errorMessages;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorMessageBean> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<ErrorMessageBean> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
