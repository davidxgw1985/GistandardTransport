package com.gistandard.transport.system.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author yujie
 * @ClassName ResultBean
 * @Description 统一返回结果对象
 * @Version 1.0
 * @Date 2015-06-08
 */
public class ResultBean implements Serializable {
    private static final long serialVersionUID = -6147234846787544996L;
    private boolean state;

    private String message;

    private Object data;

    private List<ErrorMessageBean> errorMessages;

    public List<ErrorMessageBean> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<ErrorMessageBean> errorMessages) {
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
