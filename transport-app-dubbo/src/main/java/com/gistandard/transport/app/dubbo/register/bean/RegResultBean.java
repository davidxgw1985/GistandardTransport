package com.gistandard.transport.app.dubbo.register.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhuming on 2017/4/6.
 */
public class RegResultBean implements Serializable {
    private static final long serialVersionUID = -6117234846787514991L;
    private boolean state;

    private String message;

    private Object data;

    private List<RegErrorMessageBean> errorMessages;

    public List<RegErrorMessageBean> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<RegErrorMessageBean> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
