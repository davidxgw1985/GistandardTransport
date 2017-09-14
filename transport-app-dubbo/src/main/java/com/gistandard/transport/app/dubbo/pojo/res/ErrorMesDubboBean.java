package com.gistandard.transport.app.dubbo.pojo.res;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/6/20 0020.
 */
public class ErrorMesDubboBean implements Serializable {

    private static final long serialVersionUID = 3075814540735471261L;

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
