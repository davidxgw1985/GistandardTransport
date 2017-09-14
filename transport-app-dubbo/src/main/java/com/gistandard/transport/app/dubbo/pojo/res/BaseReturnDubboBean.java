package com.gistandard.transport.app.dubbo.pojo.res;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhuming on 2017/6/20 0020.
 */
public class BaseReturnDubboBean implements Serializable {

    private static final long serialVersionUID = 3075814540735771261L;
    private boolean state;

    private int code;

    private String message;

    private List<ErrorMesDubboBean> errorMessages;

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

    public List<ErrorMesDubboBean> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<ErrorMesDubboBean> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
