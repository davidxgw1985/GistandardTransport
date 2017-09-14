package com.gistandard.transport.app.dubbo.register.bean;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/4/6.
 */
public class RegErrorMessageBean implements Serializable {
    private static final long serialVersionUID = -1511180855278383143L;
    private String errorField;

    private String errorMessage;

    private String errorObject;

    public String getErrorObject() {
        return errorObject;
    }

    public void setErrorObject(String errorObject) {
        this.errorObject = errorObject;
    }

    public String getErrorField() {
        return errorField;
    }

    public void setErrorField(String errorField) {
        this.errorField = errorField;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
